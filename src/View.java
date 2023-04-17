import java.util.Scanner;

public class View {
    public String logInMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("1. Login/Sign-in");
        System.out.println("0. Terminar Programa");

        String option = sc.next();
        return option;
    }

    public String logIn(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("Insira o seu email:");

        String option = sc.next();
        return option;
    }

    public String[] accountCreation(){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[3];
        System.out.println("Please type in your account data!");


        System.out.print("Enter your name: ");
        utilizadorInput[0] = scanner.nextLine();

        System.out.print("Enter your home adress ");
        utilizadorInput[1] = scanner.nextLine();

        System.out.print("Enter your nif: ");
        utilizadorInput[2] = scanner.nextLine();
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
        System.out.println("0 - Terminar Sessão");
        System.out.println("Indique a opcao: ");

        String option = sc.next();
        return option;
    }

    public void erroParametros(){
        System.out.println("Os parametros utilizados estão errados!");
    }

    public String tipoArtigoCriacao(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Que tipo de artigo queres criar?");
        System.out.println("1. Tshirt");
        System.out.println("2. Mala");
        System.out.println("3. Sapatilha");
        System.out.println("0 - Voltar ao Menu Inicial");

        String option = sc.next();
        return option;
    }

    public void ArtigoCriadoSucesso(){
        System.out.println("O artigo foi criado com sucesso!");
    }

    public String[] artigoCreation(int option){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[11];
        System.out.println("Por favor escreva os dados relativos ao seu Artigo!");

        System.out.println("Indique se o artigo é novo/usado (true/false): ");
        utilizadorInput[0] = scanner.nextLine();
        System.out.println("Em que estado se encontra o artigo(PESSIMO/MAU/RAZOAVEL/BOM/MUITO_BOM)? ");
        utilizadorInput[1] = scanner.nextLine();
        System.out.println("Número de donos: ");
        utilizadorInput[2] = scanner.nextLine();
        System.out.println("Descrição do Artigo: ");
        utilizadorInput[3] = scanner.nextLine();
        System.out.println("Marca: ");
        utilizadorInput[4] = scanner.nextLine();
        System.out.println("Preco Base: ");
        utilizadorInput[5] = scanner.nextLine();

        switch(option){
            case 1:
                System.out.println("Indique o Tamanho da Tshirt (S,M,L,XL): ");
                utilizadorInput[6] = scanner.nextLine();
                System.out.println("Indique o Padrao da Tshirt (Riscas, Liso, Palmeiras): ");
                utilizadorInput[7] = scanner.nextLine();
                break;
            case 2:
                System.out.println("Indique a dimensão da Mala: ");
                utilizadorInput[6] = scanner.nextLine();
                System.out.println("Indique o seu material: ");
                utilizadorInput[7] = scanner.nextLine();
                System.out.println("Indique no formato aaaa-mm-dd a data da sua coleção: ");
                utilizadorInput[8] = scanner.nextLine();
                System.out.println("Indique se é Premium (escreva true ou false): ");
                utilizadorInput[9] = scanner.nextLine();
                break;
            case 3:
                System.out.println("Indique o Tamanho da Sapatilha: ");
                utilizadorInput[6] = scanner.nextLine();
                System.out.println("Indique se tem atacadores (escreva true ou false): ");
                utilizadorInput[7] = scanner.nextLine();
                System.out.println("Indique a sua cor: ");
                utilizadorInput[8] = scanner.nextLine();
                System.out.println("Indique no formato aaaa-mm-dd a data da sua coleção: ");
                utilizadorInput[9] = scanner.nextLine();
                System.out.println("Indique se é Premium (escreva true ou false): ");
                utilizadorInput[10] = scanner.nextLine();
        }

        return utilizadorInput;
    }

    public String escolheTransportadora(){
        Scanner sc = new Scanner(System.in);
        System.out.println("De entre as Transportadoras anteriores escreva o nome da que pretende: ");

        String ret = sc.next();
        return ret;
    }

    public void invalidaTransportadora(){
        System.out.println("O nome da transportadora que escreveu não existe!");
    }

    public String tipoArtigoCompra(){
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
