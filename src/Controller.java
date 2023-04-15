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
                break;
            case 1:
                logIn();
                break;
            default:
                this.view.invalidOption();
                break;
        }
    }

    public void logIn(){
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

    
}
