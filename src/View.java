import java.util.Scanner;

public class View {
    public String logInMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("1. Login/Sign-in");
        System.out.println("0. Terminar");

        String option = sc.next();
        sc.close();
        return option;
    }

    public String logIn(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("Insira o seu email:");
        System.out.println("0. Terminar");

        String option = sc.next();
        sc.close();
        return option;
    }

    public String[] accountCreation(){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[4];
        System.out.println("Please type in your account data!");

        System.out.print("Enter your email: ");
        utilizadorInput[0] = scanner.nextLine();

        System.out.print("Enter your name: ");
        utilizadorInput[1] = scanner.nextLine();

        System.out.print("Enter your home adress ");
        utilizadorInput[2] = scanner.nextLine();

        System.out.print("Enter your nif: ");
        utilizadorInput[3] = scanner.nextLine();
        scanner.close();
        return utilizadorInput;
    }

    public String menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("1 - Criar Artigo");
        System.out.println("2 - Criar Transportadora");
        System.out.println("3 - Criar Utilizador");
        System.out.println("4 - Fazer Encomenda");
        System.out.println("5 - Mudar Data");
        System.out.println("0 - Terminar");
        System.out.println("Indique a opcao: ");

        String option = sc.next();
        sc.close();
        return option;
    }

    public String tipoArtigo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Que tipo de produtos estás à procura?");
        System.out.println("1. Tshirts");
        System.out.println("2. Malas");
        System.out.println("3. Sapatilhas");
        System.out.println("0 - Voltar ao Menu Inicial");

        String option = sc.next();
        sc.close();
        return option;
    }

    public void invalidOption() {
        System.out.println("Opcao invalida.");
    }


//    public static void artigosMenu(UserManager manager) {
//        Scanner scanner = new Scanner(System.in);
//        int choice;
//        boolean flag = true;
//
//        System.out.println("What type of product are you looking for?");
//
//        System.out.println("1. Tshirts");
//        System.out.println("2. Malas");
//        System.out.println("3. Sapatilhas");
//
//        while(flag) {
//            choice = scanner.nextInt();
//            switch (choice) {
//                case 1:
//                    for(Artigo t : manager.getTshirts()){
//                        System.out.println(t.toString());
//                    }
//                    flag = false;
//                    break;
//                case 2:
//                    for(Artigo m : manager.getMalas()){
//                        System.out.println(m.toString());
//                    }
//                    flag = false;
//                    break;
//                case 3:
//                    for(Artigo s : manager.getSapatilhas()){
//                        System.out.println(s.toString());
//                    }
//                    flag = false;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    System.out.print("-->");
//                    break;
//            }
//        }
//    }
}
