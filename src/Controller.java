import java.util.Scanner;

public class Controller {
    private View view;
    private Vintage vintage;
    private boolean run;
    private Utilizador current_user;

    public static void main(String[] args) {
        Controller controller = new Controller();

        do {
            controller.displayMenu();

        } while (controller.run);
    }

    public Controller(){
        this.view = new View();
        this.vintage = new Vintage();
        this.run = true;
    }

    public void displayMenu(){
        int op1 = Integer.parseInt(this.view.logInMenu());
        switch(op1){
            case 0:
                this.run = false;
                return;
            case 1:
                logIn();
                break;
            default:
                this.view.invalidOption();
                break;
        }

        int op2 = Integer.parseInt(this.view.menu());
        switch(op2){
            case 0:
                this.run = false;
                return;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                int artcode = Integer.parseInt(this.view.tipoArtigoCriacao());
                createArtigo(artcode); //falta adicionar À struct etc
                break;
            case 5:
                break;
        }

    }

    public void logIn(){ //falta escrever no file
        Scanner scanner = new Scanner(System.in);
        String username;
        boolean flag=true;
        while(flag){
            username = this.view.logIn();
            switch(username){
                case "0":
                    this.run = false;
                    return;
                default:
                    if(this.vintage.userExists(username)){
                        current_user = this.vintage.getUserEspecifico(username);
                        flag = false;
                    }
                    else{
                        String[] tokens = this.view.accountCreation();
                        Utilizador aux = new Utilizador(tokens[0], tokens[1], tokens[2],Integer.parseInt(tokens[3]), 0.0, 0.0);
                        this.vintage.addUser(aux);
                        flag = false;
                    }
            }
        }
    }

    public void createArtigo(int i){
        String[] tokens = this.view.artigoCreation(i);
        switch(i){
            case 1: // FALTA MOSTRAR AS TRANSPORTADORAS TODAS ANTES DE ESCOLHER, MÉTODO DE IMPRESSÃO EM VINTAGE
                Transportadora transp = this.vintage.getTransportadoraEspecifico(tokens[6]);
                Artigo art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), transp, Tshirt.Tamanho.valueOf(tokens[7]), Tshirt.Padrao.valueOf(tokens[8]));
        }
    }

}
