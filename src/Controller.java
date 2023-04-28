import java.io.*;
import java.time.LocalDate;
import java.util.*;
import UserExceptions.UserAlreadyExistsException;

public class Controller {
    private View view;
    private Vintage vintage;
    private String current_user;
    private Encomenda encomenda_atual;
    private boolean run;
    private boolean logged;

    public static void main(String[] args) {

        Controller controller = new Controller();
        String filename = controller.loadFileMenu();

            do {
                if (!controller.logged)
                    controller.logIn();

                if (controller.logged)
                    controller.displayMenu();

            } while (controller.run);
        }

    public Controller(){
        this.view = new View();
        this.vintage = new Vintage();
        this.run = true;
        this.logged = false;
        this.encomenda_atual = new Encomenda();
    }

    public String loadFileMenu() {
        boolean flag = true;
        String nome = "";
        while(flag){
            try{
                int opt = Integer.parseInt(this.view.txtOrObject());
                switch(opt){
                    case 1:
                        Populator.populateData(vintage);
                        break;
                    case 2:
                        try{
                            loadFromObjectFile();
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                }
                flag = false;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return nome;
    }


    public void displayMenu(){
        try{
            int op2 = Integer.parseInt(this.view.menu());
            switch(op2){
                case 0:
                    this.logged = false;
                    return;
                case 1:
                    createArtigo(); //falta adicionar À struct etc
                    break;
                case 2:
                    createTransportadora();
                    break;
                case 3:
                    criaUtilizador();
                    break;
                case 4:
                    criaEncomenda();
                    break;
                case 5:
                    int quantos = this.vintage.imprimeEncomendas(this.current_user);
                    if(quantos != 0){
                        String aux = view.codEncomenda();
                        this.vintage.devolveEncomenda(Integer.parseInt(aux),current_user);
                    }
                    else{
                        this.view.encomendaNaoExistente();
                    }
                    break;
                case 6:
                    break;
                case 7:
                    writeToObjectFile();
                    break;
                default:
                    break;
            }
        } catch(Exception e){
            System.out.println("Opção Inválida! " + e.getMessage());
        }
    }

    public void logIn() {
        try {
            int op1 = Integer.parseInt(this.view.logInMenu());
            switch (op1) {
                case 0:
                    this.run = false;
                    return;
                case 1: {
                    String username;
                    boolean flag = true;
                    while (flag) {
                        username = this.view.logIn();
                        if (this.vintage.userExists(username)) {
                            current_user = this.vintage.getUserEspecifico(username).getEmail();
                            this.logged = true;
                            flag = false;
                        } else {
                            String[] tokens = this.view.accountCreation();
                            try {
                                Utilizador aux = new Utilizador(username, tokens[0], tokens[1], Integer.parseInt(tokens[2]), 0.0, 0.0);
                                this.vintage.addUser(aux);
                                current_user = aux.getEmail();
                                this.logged = true;
                                flag = false;
                            } catch (NumberFormatException e) {
                                System.out.println("Os parâmetros utilizados estão errados!" + e.getMessage());
                            }
                        }
                    }
                    break;
                }
                default:
                    this.view.invalidOption();
                    break;
            }
        } catch(Exception e){
            System.out.println("Opção Inválida! " + e.getMessage());
        }
    }

    public void createArtigo(){
        boolean flag = true;
        Transportadora c;
        while(flag){
            try{
                this.vintage.printTransportadoras();
                String transp = this.view.escolheTransportadora();
                c = this.vintage.getTransportadoraEspecifico(transp);

                while(flag){
                    try{
                        int artcode = Integer.parseInt(this.view.tipoArtigoCriacao());
                        if(artcode == 0) return;
                        String[] tokens = this.view.artigoCreation(artcode);
                        Artigo art1 = null;
                        switch(artcode){
                            case 1: // talvez diferenciar construtor para produtos novos/usados(assim como no método da view)
                                if(tokens[0].toLowerCase().equals("false")){
                                    art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens[6].toUpperCase()), Tshirt.Padrao.valueOf(tokens[7].toUpperCase()));
                                }
                                else{
                                    art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens[6].toUpperCase()), Tshirt.Padrao.valueOf(tokens[7].toUpperCase()));
                                }
                                break;
                            case 2:
                                if(tokens[0].toLowerCase().equals("false")){
                                    art1 = new Mala(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens[6].toUpperCase()), tokens[7], LocalDate.parse(tokens[8]), Boolean.parseBoolean(tokens[9]));
                                }
                                else{
                                    art1 = new Mala(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens[6].toUpperCase()), tokens[7], LocalDate.parse(tokens[8]), Boolean.parseBoolean(tokens[9]));
                                }
                                break;
                            case 3:
                                if(tokens[0].toLowerCase().equals("false")){
                                    art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], LocalDate.parse(tokens[9]), Boolean.parseBoolean(tokens[10]));
                                }
                                else{
                                    art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], LocalDate.parse(tokens[9]), Boolean.parseBoolean(tokens[10]));
                                }
                            case 0:
                                return;
                            default:
                                this.view.invalidOption();
                                break;
                        }
                        this.vintage.addArigoVenda(this.current_user, art1);
                        System.out.println(art1);
                        flag = false;
                    } catch (Exception e){
                        System.out.println("Os parâmetros utilizados estão errados!" + e.getMessage());
                    }
                }
            } catch(Exception e){
                System.out.println("A transportadora utilizada não existe!" + e.getMessage());
            }
        }
    }

    public void createTransportadora(){

        String tokens[] = this.view.transportadoraCreation();
        try{
            Transportadora transportadora = new Transportadora(tokens[0], Double.parseDouble(tokens[1]), Boolean.parseBoolean(tokens[2]));
            this.vintage.addTransportadora(transportadora);
        } catch (Exception e){
            System.out.println("Os parâmetros utilizados estão errados!" + e.getMessage());
        }
    }

    public void criaEncomenda(){
        boolean flag = true;
        String artigo;
        while(flag){
            try{
                int opt = Integer.parseInt(this.view.OpcaoEncomenda());
                switch(opt){
                    case 1:
                        this.vintage.showArtigos();
                        artigo = this.view.encomendaCreation();
                        if(!this.encomenda_atual.contem(this.vintage.findArtigo(artigo))){
                            this.encomenda_atual.addArtEncomenda(this.vintage.findArtigo(artigo));
                        }
                        this.encomenda_atual.showPrecoAtual();
                        break;
                    case 2:
                        this.vintage.showArtigos(this.encomenda_atual.getArtigos());
                        artigo = this.view.removeArtigo();
                        this.encomenda_atual.removeArtEncomenda(this.vintage.findArtigo(artigo));
                        this.encomenda_atual.showPrecoAtual();
                        break;
                    case 3:
                        this.encomenda_atual.atualizaEncomenda();
                        this.vintage.addEncomenda(this.current_user, this.encomenda_atual);
                        this.encomenda_atual = new Encomenda();
                        flag = false;
                        break;
                    case 4:
                        this.encomenda_atual = new Encomenda();
                        flag = false;
                        break;
                    default:
                        this.view.invalidOption();
                        break;
                }
            } catch (Exception e){
                System.out.println("" + e.getMessage());
            }
        }
    }

    public void criaUtilizador(){
        String tokens[] = this.view.userCreation();
        try{
            Utilizador utilizador = new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
            this.vintage.addUser(utilizador);
        } catch(Exception e){
            System.out.println("Os parâmetros utilizados estão errados!" + e.getMessage());
        }
    }

    public void writeToObjectFile() throws IOException{
        FileOutputStream fos = new FileOutputStream("state.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.vintage);
        oos.write(Encomenda.getCount());
        oos.flush();
        oos.close();
    }

    public void loadFromObjectFile() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("state.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.vintage = (Vintage) ois.readObject();
        Encomenda.setCount(ois.read());
        ois.close();
    }
}
