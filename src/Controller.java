import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import UserExceptions.*;

public class Controller {
    private View view;
    private Vintage vintage;
    private String current_user;
    private Encomenda encomenda_atual;
    private boolean run;
    private boolean logged;
    private LocalDateTime data;

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.loadFileMenu();

        do {
            if (!controller.logged)
                controller.logIn();

            if (controller.logged)
                controller.displayMenu();

        } while (controller.run);
    }

    public Controller(){
        this.data = LocalDateTime.now();
        this.view = new View();
        this.vintage = new Vintage();
        this.run = true;
        this.logged = false;
        this.encomenda_atual = new Encomenda();
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String loadFileMenu() {
        boolean flag = true;
        String nome = "";
        while(flag){
            int opt = this.view.txtOrObject();
            switch(opt){
                case 1:
                    this.vintage = Populator.populateData();
                    flag = false;
                    break;
                case 2:
                    try{
                        loadFromObjectFile();
                        flag = false;
                    } catch (FileNotFoundException fne){
                        fne.getMessage();
                    } catch (IOException io){
                        io.getMessage();
                    } catch (ClassNotFoundException cnfe){
                        cnfe.getMessage();
                    }
                    break;
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
                    try{
                        createArtigo(); //falta adicionar À struct etc
                    } catch (Exception e){
                        e.getMessage();
                    }
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
                    int quantos = this.view.imprimeEncomendasUser(this.current_user, this.vintage.getEncomendas(), this.vintage.getEncomendas_utilizadores_ligacao());
                    if(quantos != 0){
                        int cod = this.view.codEncomenda();
                        String nome;
                        try{
                            nome = this.vintage.getUserFromEncomenda(cod);
                            if(nome.equals(this.current_user)){
                                this.vintage.devolveEncomenda(cod,current_user, this.data);
                            }
                            else
                                this.view.encomendaNaoAssociada(this.current_user);
                        } catch (EncomendaDoesntExistException e){
                            e.getMessage();
                        } catch (UserDoesntExistException udee){
                            udee.getMessage();
                        }
                        break;
                    }
                    else{
                        this.view.encomendaNaoExistente();
                    }
                    break;
                case 6:
                    avancaData(); //para ja o tempo para passar de finalizada a expedida é de 5h
                    break;
                case 7:
                    writeToObjectFile();
                    break;
                default:
                    break;
                case 8:
                    try{
                        writeToLog();
                    }
                    catch (IOException fne){
                        fne.getMessage();
                    }
                    break;
                case 9:
                    calculaEstatisticas();
            }
        } catch(Exception e){
            System.out.println("Opção Inválida! " + e.getMessage());
        }
    }

    public void logIn() {
        int op1 = this.view.logInMenu();
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
                        Utilizador aux = new Utilizador(username, tokens[0], tokens[1], Integer.parseInt(tokens[2]), 0.0, 0.0);
                        this.vintage.addUser(aux);
                        current_user = aux.getEmail();
                        this.logged = true;
                        flag = false;
                    }
                }
                break;
            }
        }
    }

    public void createArtigo(){ //adaptar para premium
        boolean flag = true;
        boolean flag2 = true;
        Transportadora c = null;
        int premium = this.view.artPremium();
        boolean prem = (premium == 1) ? false : true;
        while(flag){
            Set<Transportadora> transpSet = this.vintage.getListaTransportadoras(prem);
            this.view.imprimeTransportadora(transpSet);
            String transp = this.view.escolheTransportadora();

            try{
                c = this.vintage.getTransportadoraEspecifico(transp);
                if(transpSet.contains(c))
                    flag = false;
                else
                    this.view.transpNaoCorresponde();

            } catch (TransportadoraDoesntExistException e){
                e.getMessage();
            }

            while(flag2 && !flag){
                int artcode = this.view.tipoArtigoCriacao(prem);
                if(artcode == 0) return;
                String[] tokens = this.view.artigoCreation(artcode);
                Artigo art1 = null;
                switch(artcode){
                    case 3:
                        if(!prem){
                            if(tokens[0].toLowerCase().equals("false")){
                                art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens[6].toUpperCase()), Tshirt.Padrao.valueOf(tokens[7].toUpperCase()));
                            }
                            else{
                                art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens[6].toUpperCase()), Tshirt.Padrao.valueOf(tokens[7].toUpperCase()));
                            }
                        }
                        else{

                        }
                        break;
                    case 1:
                        if(tokens[0].toLowerCase().equals("false")){
                            art1 = new Mala(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens[6].toUpperCase()), tokens[7], LocalDate.parse(tokens[8]));
                        }
                        else{
                            art1 = new Mala(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens[6].toUpperCase()), tokens[7], LocalDate.parse(tokens[8]));
                        }
                        break;
                    case 2:
                        if(tokens[0].toLowerCase().equals("false")){
                            art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], LocalDate.parse(tokens[9]));
                        }
                        else{
                            art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], LocalDate.parse(tokens[9]));
                        }
                    case 0:
                        return;
                }
                try{
                    this.vintage.addArigoVenda(this.current_user, art1);
                    flag2 = false;
                } catch (UserDoesntExistException udee){
                    udee.getMessage();
                } catch (Exception e){
                    e.getMessage();
                }
            }
        }
    }

    public void createTransportadora(){ // adaptar para premium tmb
        Transportadora transportadora = null;
        String tokens[] = this.view.transportadoraCreation();
        if(tokens[3].equalsIgnoreCase("false")) {
            transportadora = new Transportadora(tokens[0], Double.parseDouble(tokens[1]));
        }
        else{
            transportadora = new TransportadoraPremium(tokens[0], Double.parseDouble(tokens[1]));
        }
        this.vintage.addTransportadora(transportadora);
    }

    public void criaEncomenda(){
        boolean flag = true;
        String artigo;
        while(flag){
            int opt = this.view.OpcaoEncomenda();
            switch(opt){
                case 1:
                    this.view.imprimeArtigos(this.vintage.getListaArtigos(this.current_user));
                    artigo = this.view.encomendaCreation();
                    try{
                        Artigo art = this.vintage.findArtigo(artigo);
                        if(!this.encomenda_atual.contem(art)){
                            this.encomenda_atual.addArtEncomenda(art);
                        }
                    } catch (ArtigoDoesntExistException e){
                        e.getMessage();
                    }
                    this.encomenda_atual.showPrecoAtual();
                    break;
                case 2:
                    this.view.imprimeArtigos(this.encomenda_atual.getArtigos());
                    artigo = this.view.removeArtigo();
                    try{
                        this.encomenda_atual.removeArtEncomenda(this.vintage.findArtigo(artigo));
                    } catch (ArtigoDoesntExistException e){
                        e.getMessage();
                    }
                    this.encomenda_atual.showPrecoAtual();
                    break;
                case 3:
                    if(this.encomenda_atual.getArtigos().size() != 0){
                        this.encomenda_atual.atualizaEncomenda();
                        try{
                            this.vintage.addEncomenda(this.current_user, this.encomenda_atual);
                        } catch (UserDoesntExistException udee){
                            udee.getMessage();
                        } catch (ArtigoDoesntExistException adee){
                            adee.getMessage();
                        } catch (TransportadoraDoesntExistException tdee){
                            tdee.getMessage();
                        }
                    }
                    this.encomenda_atual = new Encomenda();
                    flag = false;
                    break;
                case 4:
                    this.encomenda_atual = new Encomenda();
                    flag = false;
                    break;
            }
        }
    }


    public void criaUtilizador(){
        String tokens[] = this.view.userCreation();
        Utilizador utilizador = new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
        this.vintage.addUser(utilizador);
    }

    public void calculaEstatisticas() {
        boolean flag = true;
        LocalDate datas[];
        while (flag) {
            try {
                int op2 = this.view.menuEstatisticas();
                switch (op2) {
                    case 0:
                        flag = false; // Exit loop
                        break;
                    case 1:
                        datas = this.view.intervaloTempo();
                        System.out.println("stats1");
                        break;
                    case 2:
                        System.out.println("stats2");
                        break;
                    case 3:
                        System.out.println("stats3");
                        break;
                    case 4:
                        datas = this.view.intervaloTempo();
                        System.out.println("stats4");
                        break;
                    case 5:
                        System.out.println("stats5");
                        break;
                    default:
                        System.out.println("Opção Inválida!");
                        break;
                }
            } catch(Exception e) {
                System.out.println("Opção Inválida! " + e.getMessage());
            }
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

    public void loadFromObjectFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("state.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.vintage = (Vintage) ois.readObject();
        Encomenda.setCount(ois.read());
        ois.close();
    }

    //depois vai para o lixo
    public void writeToLog() throws IOException {
        String name = "./src/log.txt";

        File file = new File(name);
        FileOutputStream fos = new FileOutputStream(file);
        try (PrintWriter pw = new PrintWriter(fos)) {

            List<String> lista;

            lista = this.vintage.toLog();

            for (String s : lista) {
                pw.println(s);
            }

        }
        fos.close();
    }


    public void avancaData() throws IOException{
        int nrHoras = this.view.avancaData();

        this.data = this.data.plusHours(nrHoras);

        Map<Integer,String> recibos = this.vintage.atualizaEncomendas(this.data,nrHoras);

        for(Map.Entry<Integer,String> a : recibos.entrySet()){
            int cod = a.getKey();
            String recib = a.getValue();
            String nome = "./src/Recibos/" + cod + ".txt";
            File file = new File(nome);
            FileOutputStream fos = new FileOutputStream(file);

            try (PrintWriter pw = new PrintWriter(fos)){
                pw.println(recib);
            }
            fos.close();
        }
        System.out.println("Foram avançadas " + nrHoras + " horas");
    }

}
