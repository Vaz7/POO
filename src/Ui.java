import java.util.Scanner;

public class Ui {

    public static void menu(UserManager manager){
        System.out.println("Welcome to Vintage shop!");
        System.out.println("");
        System.out.println("1. Login/Sign-in");
        System.out.println("0. Exit");
        System.out.print("->");
        Scanner scanner = new Scanner(System.in);
        boolean flag=true;
        int choice;

        while(flag) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    loginMenu(manager);
                    flag = false;
                    break;
                case 0:
                    System.out.println("Thats all, folks!");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.print("-->");
                    break;
            }
        }

    }
    public static void loginMenu(UserManager manager){
        Scanner scanner = new Scanner(System.in);
        String username;


        System.out.println("Please type your username");
        username = scanner.nextLine();

        if(manager.userExists(username)){
            System.out.println("Welcome back " + username);
            artigosMenu(manager);
        }
        else{
            System.out.println("Please type in your account data!");

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your home adress ");
            String morada = scanner.nextLine();

            System.out.print("Enter your nif: ");
            String nif = scanner.nextLine();

            int nifInt = Integer.parseInt(nif);

            Utilizador newUser = new Utilizador(username,email,name,morada,nifInt,0.0,0.0);

            manager.addUser(newUser);
            System.out.println("Welcome to our store " + username);
            artigosMenu(manager);
        }
    }

    public static void artigosMenu(UserManager manager) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean flag = true;

        System.out.println("What type of product are you looking for?");

        System.out.println("1. Tshirts");
        System.out.println("2. Malas");
        System.out.println("3. Sapatilhas");

        while(flag) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for(Artigo t : manager.getTshirts()){
                        System.out.println(t.toString());
                    }
                    flag = false;
                    break;
                case 2:
                    for(Artigo m : manager.getMalas()){
                        System.out.println(m.toString());
                    }
                    flag = false;
                    break;
                case 3:
                    for(Artigo s : manager.getSapatilhas()){
                        System.out.println(s.toString());
                    }
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.print("-->");
                    break;
            }
        }
    }
}