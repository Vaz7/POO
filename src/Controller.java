import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import UserExceptions.UserDoesntExistException;
import UserExceptions.UserAlreadyExistsException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {
    private View view;
    private Vintage vintage;
    private boolean run;
    private String current_user;
    private boolean logged;

    public static void main(String[] args) {

        Controller controller = new Controller();

        try {
            controller.loadFromLog("./src/log.txt");
        } catch (IOException fnf) {
            fnf.getMessage();
        }
        catch (UserAlreadyExistsException e){
            e.getMessage();
        }

            do {
                if (!controller.logged)
                    controller.logIn();

                if (controller.logged)
                    controller.displayMenu();

            } while (controller.run);

            try{
                controller.writeToLog("./src/log.txt");
            }
            catch (IOException e){
                e.getMessage();
            }

        }

    public Controller(){
        this.view = new View();
        this.vintage = new Vintage();
        this.run = true;
        this.logged = false;
    }

    public void displayMenu(){
        int op2 = Integer.parseInt(this.view.menu());
        switch(op2){
            case 0:
                this.logged = false;
                return;
            case 1:
                createArtigo(); //falta adicionar À struct etc
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }

    public void logIn() {
        int op1 = Integer.parseInt(this.view.logInMenu());
        switch(op1){
            case 0:
                this.run = false;
                return;
            case 1:{
                String username;
                boolean flag=true;
                while(flag){
                    username = this.view.logIn();
                    if(this.vintage.userExists(username)){
                        current_user = this.vintage.getUserEspecifico(username).getEmail();
                        this.logged = true;
                        flag = false;
                    }
                    else{
                        String[] tokens = this.view.accountCreation();
                        try{
                            Utilizador aux = new Utilizador(username, tokens[0], tokens[1],Integer.parseInt(tokens[2]), 0.0, 0.0);
                            this.vintage.addUser(aux);
                            current_user = aux.getEmail();
                            this.logged = true;
                            flag = false;
                        } catch (NumberFormatException e){
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
    }

    public void createArtigo(){
        boolean flag = true;
        Transportadora transportadora = new Transportadora();
        this.vintage.addTransportadora(transportadora);
        Transportadora c;
        while(flag){
            try{
                this.vintage.printTransportadoras();
                String transp = this.view.escolheTransportadora();
                c = this.vintage.getTransportadoraEspecifico(transp);

                while(flag){
                    try{
                        int artcode = Integer.parseInt(this.view.tipoArtigoCriacao());
                        String[] tokens = this.view.artigoCreation(artcode);
                        Artigo art1 = null;
                        switch(artcode){
                            case 1: // talvez diferenciar construtor para produtos novos/usados(assim como no método da view)
                                if(tokens[0].toLowerCase().equals("false")){
                                    art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens[6]), Tshirt.Padrao.valueOf(tokens[7]));
                                }
                                else{
                                    art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens[6]), Tshirt.Padrao.valueOf(tokens[7]));
                                }
                                break;
                            case 2:
                                if(tokens[0].toLowerCase().equals("false")){
                                    art1 = new Mala(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens[6]), tokens[7], LocalDate.parse(tokens[8]), Boolean.parseBoolean(tokens[9]));
                                }
                                else{
                                    art1 = new Mala(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens[6]), tokens[7], LocalDate.parse(tokens[8]), Boolean.parseBoolean(tokens[9]));
                                }
                                break;
                            case 3:
                                if(tokens[0].toLowerCase().equals("false")){
                                    art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], LocalDate.parse(tokens[9]), Boolean.parseBoolean(tokens[10]));
                                }
                                else{
                                    art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens[6]), Boolean.parseBoolean(tokens[7]), tokens[8], LocalDate.parse(tokens[9]), Boolean.parseBoolean(tokens[10]));
                                }
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


    public void loadFromLog(String name ) throws IOException,UserAlreadyExistsException{

        File fich = new File(name);
        FileInputStream sc = new FileInputStream(fich);
        BufferedReader buff = new BufferedReader(new InputStreamReader(sc));

        Utilizador currentUser = new Utilizador();
        String data;

        String strings[];
        String sub_strings[];
        while ((data = buff.readLine())!=null) {

            strings = data.split(",");
            sub_strings = strings[0].split(":"); //para separar nos ":"


            //System.out.println(data);

            if(sub_strings[0].toLowerCase().equals("transportadora")){
                Transportadora transportadora = new Transportadora(Boolean.parseBoolean(sub_strings[1]),Double.parseDouble(strings[1]),strings[2],Double.parseDouble(strings[3]));
                this.vintage.addTransportadora(transportadora);
            }

            else if(sub_strings[0].toLowerCase().equals("utilizador")){
                currentUser = new Utilizador(sub_strings[1],strings[1],strings[2],Integer.parseInt(strings[3]),Double.parseDouble(strings[4]),Double.parseDouble(strings[5]));

                if(this.vintage.userExists(sub_strings[1])){
                    throw new UserAlreadyExistsException(sub_strings[1]);
                }
                else{
                    this.vintage.addUser(currentUser);
                }


            }

            else if(sub_strings[0].toLowerCase().equals("tshirt_vendida") || sub_strings[0].toLowerCase().equals("tshirt_vender")){

                if(Boolean.parseBoolean(sub_strings[1])==true && strings.length==7){

                    //Tshirt:true,Tshirt Name,123.45,M,stripes,Tshirt Transportadora padrao,Transportadora transportadora

                    //da maneira que isto esta, os campos de enums tem de aparecer escritos EXATAMENTE iguais, prob depois muda se as enums para maiusculas e faz se Tshirt.Padrao.valueOf(strings[5].ToUpperCase())
                    Tshirt tshirt = new Tshirt(Boolean.parseBoolean(sub_strings[1]),strings[1],strings[2],Double.parseDouble(strings[3]), vintage.getTransportadoraEspecifico(strings[6]), Tshirt.Tamanho.valueOf(strings[4]), Tshirt.Padrao.valueOf(strings[5]));

                    if(sub_strings[0].toLowerCase().equals("tshirt_vendida")){
                        vintage.addArigoVendido(currentUser.getEmail(),tshirt);
                    }
                    else{
                        vintage.addArigoVenda(currentUser.getEmail(),tshirt); //depois temos de verificar no caso em que o user nao existe
                    }

                }

                else if(Boolean.parseBoolean(sub_strings[1])==false && strings.length==9){

                    Tshirt tshirt = new Tshirt(Boolean.parseBoolean(sub_strings[1]),Integer.parseInt(strings[1]),Artigo.Estado.valueOf(strings[2]),strings[3],strings[4],Double.parseDouble(strings[5]),vintage.getTransportadoraEspecifico(strings[6]), Tshirt.Tamanho.valueOf(strings[7]), Tshirt.Padrao.valueOf(strings[8]));

                    if(sub_strings[0].toLowerCase().equals("tshirt_vendida")){
                        vintage.addArigoVendido(currentUser.getEmail(),tshirt);
                    }
                    else{
                        vintage.addArigoVenda(currentUser.getEmail(),tshirt); //depois temos de verificar no caso em que o user nao existe
                    }
                }

                else{
                    System.out.println("erro");//depois meter exception quando percebermos como funfa
                }

            }

            else if(sub_strings[0].toLowerCase().equals("mala_vendida") || sub_strings[0].toLowerCase().equals("mala_vender")){

                if(Boolean.parseBoolean(sub_strings[1])==true && strings.length==9){

                    Mala mala = new Mala(Boolean.parseBoolean(sub_strings[1]),strings[1],strings[2],Double.parseDouble(strings[3]), vintage.getTransportadoraEspecifico(strings[8]), Mala.Dim.valueOf(strings[4]),strings[5], LocalDate.parse(strings[6]),Boolean.parseBoolean(strings[7]));
                    if(sub_strings[0].toLowerCase().equals("mala_vendida")){
                        vintage.addArigoVendido(currentUser.getEmail(),mala);
                    }
                    else{
                        vintage.addArigoVenda(currentUser.getEmail(),mala); //depois temos de verificar no caso em que o user nao existe
                    }
                }
                else if(Boolean.parseBoolean(sub_strings[1])==false && strings.length==11){

                    Mala mala = new Mala(Boolean.parseBoolean(sub_strings[1]),Integer.parseInt(strings[1]),Artigo.Estado.valueOf(strings[2]),strings[3],strings[4],Double.parseDouble(strings[5]), vintage.getTransportadoraEspecifico(strings[10]), Mala.Dim.valueOf(strings[6]),strings[7],LocalDate.parse(strings[8]),Boolean.parseBoolean(strings[9]));
                    if(sub_strings[0].toLowerCase().equals("mala_vendida")){
                        vintage.addArigoVendido(currentUser.getEmail(),mala);
                    }
                    else{
                        vintage.addArigoVenda(currentUser.getEmail(),mala); //depois temos de verificar no caso em que o user nao existe
                    }
                }


            }

            else if(sub_strings[0].toLowerCase().equals("sapatilha_vendida")||sub_strings[0].toLowerCase().equals("sapatilha_vender")){

                if(Boolean.parseBoolean(sub_strings[1])==true && strings.length==10){

                    Sapatilha sapatilha = new Sapatilha(Boolean.parseBoolean(sub_strings[1]),strings[1],strings[2],Double.parseDouble(strings[3]), vintage.getTransportadoraEspecifico(strings[9]), Double.parseDouble(strings[4]),Boolean.parseBoolean(strings[5]),strings[6],LocalDate.parse(strings[7]),Boolean.parseBoolean(strings[8]));
                    if(sub_strings[0].toLowerCase().equals("sapatilha_vendida")){
                        vintage.addArigoVendido(currentUser.getEmail(),sapatilha);
                    }
                    else{
                        vintage.addArigoVenda(currentUser.getEmail(),sapatilha); //depois temos de verificar no caso em que o user nao existe
                    }
                }

                else if(Boolean.parseBoolean(sub_strings[1])==false && strings.length==12){

                    Sapatilha sapatilha = new Sapatilha(Boolean.parseBoolean(sub_strings[1]),Integer.parseInt(strings[1]),Artigo.Estado.valueOf(strings[2]),strings[3],strings[4],Double.parseDouble(strings[5]), vintage.getTransportadoraEspecifico(strings[11]), Double.parseDouble(strings[6]),Boolean.parseBoolean(strings[7]),strings[8],LocalDate.parse(strings[9]),Boolean.parseBoolean(strings[10]));
                    if(sub_strings[0].toLowerCase().equals("sapatilha_vendida")){
                        vintage.addArigoVendido(currentUser.getEmail(),sapatilha);
                    }
                    else{
                        vintage.addArigoVenda(currentUser.getEmail(),sapatilha); //depois temos de verificar no caso em que o user nao existe
                    }
                }

            }
            else{
                System.out.println("erro");
            }

        }
    }


    //falta ler e escrever os ficheiros para venda e para vender
    public void writeToLog(String name) throws IOException {
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



}
