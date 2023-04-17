import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import UserExceptions.UserDoesntExistException;
import java.util.ArrayList;
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

            do {
                if (!controller.logged)
                    controller.logIn();

                if (controller.logged)
                    controller.displayMenu();

            } while (controller.run);

            controller.vintage.dumpToFile();
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
                        switch(artcode){
                            case 1: // talvez diferenciar construtor para produtos novos/usados(assim como no método da view)
                                Artigo art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens[6]), Tshirt.Padrao.valueOf(tokens[7]));
                                this.vintage.addArigoVenda(this.current_user, art1);
                                Utilizador a = this.vintage.getUserEspecifico(this.current_user);
                                System.out.println(a.getPara_vender());
                                flag = false;
                                break;
                            case 2:
                                break;
                        }
                    } catch (Exception e){
                        System.out.println("Os parâmetros utilizados estão errados!" + e.getMessage());
                    }
                }
            } catch(Exception e){
                System.out.println("A transportadora utilizada não existe!" + e.getMessage());
            }
        }
    }


    public void loadFromLog(String name ) throws IOException{

        File fich = new File(name);
        FileInputStream sc = new FileInputStream(fich);
        BufferedReader buff = new BufferedReader(new InputStreamReader(sc));

        Utilizador currentUser = new Utilizador();
        String data;

        String strings[];

        while ((data = buff.readLine())!=null) {

            strings = data.split(",");

            System.out.println(data);

            if(strings[0].toLowerCase()=="transportadora"){
                System.out.println("added transp");
                //Transportadora transportadora = new Transportadora(Boolean.parseBoolean(transportadoraM.group(2)),Double.parseDouble(transportadoraM.group(3)),transportadoraM.group(4),Double.parseDouble(transportadoraM.group(5)));
                //this.vintage.addTransportadora(transportadora);
            }

            else if(strings[0].toLowerCase()=="utilizador"){
                System.out.println("adding user");
            }

            else if(strings[0].toLowerCase()=="tshirt"){
                System.out.println("adding tshirt");
                //Tshirt tshirt = new Tshirt(Boolean.parseBoolean(tshirtM.group(2)),tshirtM.group(3),tshirtM.group(4),Double.parseDouble(tshirtM.group(5)),Tshirt.Tamanho.valueOf(tshirtM.group(6)), Tshirt.Padrao.valueOf(tshirtM.group(7)),vintage.getTransportadoraEspecifico(tshirtM.group(8)));
                //String email = currentUser.getEmail();
                //vintage.addArigoVenda(email,tshirt);
            }

            else if(strings[0].toLowerCase()=="mala"){
                System.out.println("adding mala");
            }

            else if(strings[0].toLowerCase()=="sapatilha"){
                System.out.println("adding sapatilha");
            }

        }
    }


}
