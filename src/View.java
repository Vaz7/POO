import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {
    public int logInMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("1. Login/Sign-in");
        System.out.println("0. Terminar Programa");

        int option = 0;
        do{
            option = leInteiro();
            if(option != 1 && option != 0)
                System.out.println("Introduza o valor 0 ou 1.");
        } while(option != 1 && option != 0);

        return option;
    }

    public String logIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("Insira o seu email:");

        String option = sc.next();
        return option;
    }

    public String[] accountCreation() {
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[3];
        System.out.println("Please type in your account data!");


        System.out.print("Enter your name: ");
        utilizadorInput[0] = scanner.nextLine();

        System.out.print("Enter your home adress ");
        utilizadorInput[1] = scanner.nextLine();

        System.out.print("Enter your nif: ");
        utilizadorInput[2] = Integer.toString(leInteiro());
        return utilizadorInput;
    }

    public String menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("1 - Criar Artigo");
        System.out.println("2 - Criar Transportadora");
        System.out.println("3 - Criar Utilizador");
        System.out.println("4 - Fazer Encomenda");
        System.out.println("5 - Devolver Encomenda");
        System.out.println("6 - Mudar Data");
        System.out.println("7 - Guardar num ficheiro de objetos");
        System.out.println("8 - Escreve ficheiro de txt");
        System.out.println("0 - Terminar Sessão");
        System.out.println("Indique a opcao: ");

        String option = sc.next();
        return option;
    }

    public int avancaData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique quantas horas pretende avançar:");
        int opt = 0;
        do{
            opt = leInteiro();

        } while(opt < 0);
        return opt;
    }

    public int txtOrObject() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique como pretende carregar os dados:");
        System.out.println("1. Popular com os dados de teste");
        System.out.println("2. Ficheiro de Objetos");


        int opt = 0;
        do{
            opt = leInteiro();
            if(opt != 1 && opt !=2)
                System.out.println("Introduza um inteiro com o valor 1 ou 2.");
        } while(opt != 1 && opt != 2);

        return opt;
    }

    public int tipoArtigoCriacao() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que tipo de artigo queres criar?");
        System.out.println("1. Tshirt");
        System.out.println("2. Mala");
        System.out.println("3. Sapatilha");
        System.out.println("0 - Voltar ao Menu Inicial");

        int option = 0;
        do{
            option = leInteiro();
            if(option != 1 && option != 2 && option != 3 && option != 0)
                System.out.println("Introduza o valor 0, 1, 2 ou 3.");
        } while(option != 1 && option != 2 && option != 3 && option != 0);

        return option;
    }

    public String[] artigoCreation(int option){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[11];
        System.out.println("Por favor escreva os dados relativos ao seu Artigo!");

        System.out.println("Indique se o artigo é novo/usado (true/false): ");
        utilizadorInput[0] = leBoolean();

        if (utilizadorInput[0].equalsIgnoreCase("false")) {
            System.out.println("Em que estado se encontra o artigo(PESSIMO/MAU/RAZOAVEL/BOM/MUITO_BOM)? ");
            utilizadorInput[1] = leEstado();

            System.out.println("Número de donos: ");
            utilizadorInput[2] = Integer.toString(leInteiro());
        }
        System.out.println("Descrição do Artigo: ");
        utilizadorInput[3] = scanner.nextLine();
        System.out.println("Marca: ");
        utilizadorInput[4] = scanner.nextLine();
        System.out.println("Preco Base: ");
        utilizadorInput[5] = Double.toString(leDouble());

        switch (option) {
            case 1:
                System.out.println("Indique o Tamanho da Tshirt (S/M/L/XL): ");
                utilizadorInput[6] = leTamanho();
                System.out.println("Indique o Padrao da Tshirt (RISCAS/LISO/PALMEIRAS): ");
                utilizadorInput[7] = lePadrao();
                break;
            case 2:
                System.out.println("Indique a dimensão da Mala (PEQUENO/MEDIO/GRANDE): ");
                utilizadorInput[6] = leDimensao();
                System.out.println("Indique o seu material: ");
                utilizadorInput[7] = scanner.nextLine();
                System.out.println("Indique no formato aaaa-mm-dd a data da sua coleção: ");
                utilizadorInput[8] = leLocalDate();
                break;
            case 3:
                System.out.println("Indique o Tamanho da Sapatilha: ");
                utilizadorInput[6] = Double.toString(leDouble());
                System.out.println("Indique se tem atacadores (true/false): ");
                utilizadorInput[7] = leBoolean();
                System.out.println("Indique a sua cor: ");
                utilizadorInput[8] = scanner.nextLine();
                System.out.println("Indique no formato aaaa-mm-dd a data da sua coleção: ");
                utilizadorInput[9] = leLocalDate();
        }

        return utilizadorInput;
    }

    public String[] transportadoraCreation() {
        String[] utilizadorInput = new String[5];
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual o nome da transportadora?:");
        utilizadorInput[0] = sc.nextLine();

        System.out.println("Indique a taxa de lucro da transportadora (entre 0.00 e 1.00):");
        utilizadorInput[1] = Double.toString(leDouble());

        System.out.println("A transportadora é premium? (true/false):");
        utilizadorInput[2] = leBoolean();

        return utilizadorInput;
    }

    public int OpcaoEncomenda() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual das seguintes opções pretende?");
        System.out.println("1. Adicionar Artigo");
        System.out.println("2. Remover Artigo");
        System.out.println("3. Finalizar Encomenda");
        System.out.println("4. Cancelar Encomenda");
        int opt = 0;
        do{
            opt = leInteiro();
            if(opt != 1 && opt != 2 && opt != 3 && opt != 4){
                System.out.println("Introduza o valor 1, 2, 3 ou 4.");
            }
        } while(opt != 1 && opt != 2 && opt != 3 && opt != 4);
        return opt;
    }

    public int codEncomenda() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o código da encomenda que pretende devolver:");
        int enc = leInteiro();
        return enc;
    }

    public void encomendaNaoExistente() {
        System.out.println("Não há nenhuma encomenda atribuida a este utilizador.");
    }

    public String removeArtigo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o código alfanumérico do artigo que pretende remover da sua encomenda:");
        String artigo = sc.nextLine();
        return artigo;
    }

    public void encomendaNaoAssociada(String email){
        System.out.println("Essa encomenda não está associada a " + email);
    }

    public String encomendaCreation() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o código alfanumérico do Artigo que pretende:");
        String artigo = sc.nextLine();

        return artigo;
    }

    public String[] userCreation() {
        String[] utilizadorInput = new String[4];
        Scanner sc = new Scanner(System.in);

        System.out.println("Indique o seu email: ");
        utilizadorInput[0] = sc.nextLine();
        System.out.println("Indique o seu nome: ");
        utilizadorInput[1] = sc.nextLine();
        System.out.println("Indique a sua morada: ");
        utilizadorInput[2] = sc.nextLine();
        System.out.println("Indique o seu NIF: ");
        utilizadorInput[3] = Integer.toString(leInteiro());

        return utilizadorInput;
    }

    public String escolheTransportadora() {
        Scanner sc = new Scanner(System.in);
        System.out.println("De entre as Transportadoras anteriores escreva o nome da que pretende: ");

        String ret = sc.nextLine();
        return ret;
    }


    public String tipoArtigoCompra() {
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

    public void imprimeTransportadora(List<Transportadora> list, boolean premium){
        if(premium == true) list.stream()
                .filter(Transportadora::isPremium)
                .forEach(System.out::println);
        else
            list.stream()
                    .filter(t -> !t.isPremium())
                    .forEach(System.out::println);
    }

    public int artPremium(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual das seguintes opções representa o seu artigo?");
        System.out.println("1. Artigo Normal");
        System.out.println("2. Artigo Premium");

        int opt = 0;
        do{
            opt = leInteiro();
            if(opt != 1 && opt !=2)
                System.out.println("Introduza um valor igual a 1 ou 2.");
        } while (opt != 1 && opt != 2);

        return opt;
    }

    public int leInteiro(){
        Scanner sc = new Scanner(System.in);

        boolean loop = false;
        int option = 0;
        do{
            try{
                option = sc.nextInt();
                loop = true;
            } catch (Exception e){
                if(sc.hasNext()) sc.next();
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um inteiro.");
            }
        } while(!loop);

        return option;
    }

    public double leDouble(){
        Scanner sc = new Scanner(System.in);

        boolean loop = false;
        double option = 0;
        do{
            try{
                option = sc.nextDouble();
                loop = true;
            } catch (Exception e){
                if(sc.hasNext()) sc.next();
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um double.");
            }
        } while(!loop);

        return option;
    }

    public String leBoolean(){
        Scanner sc = new Scanner(System.in);
        boolean loop = false, opt = false;
        do{
            try{
                opt = sc.nextBoolean();
                loop = true;
            } catch (Exception e){
                if(sc.hasNext()) sc.next();
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um boolean.");
            }
        } while(!loop);
        return Boolean.toString(opt);
    }

    public String leEstado(){
        Scanner sc = new Scanner(System.in);
        String ret = null;
        boolean flag = true;
        do{
            ret = sc.nextLine();
            try{
                Artigo.Estado test = Artigo.Estado.valueOf(ret.toUpperCase());
                flag = false;
            } catch (Exception e){
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um Estado correspondente a um dos valores expostos.");
            }
        } while(flag);
        return ret;
    }

    public String leTamanho(){
        Scanner sc = new Scanner(System.in);
        String ret = null;
        boolean flag = true;
        do{
            ret = sc.nextLine();
            try{
                Tshirt.Tamanho test = Tshirt.Tamanho.valueOf(ret.toUpperCase());
                flag = false;
            } catch (Exception e){
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um Tamanho correspondente a um dos valores expostos.");
            }
        } while(flag);
        return ret;
    }

    public String lePadrao(){
        Scanner sc = new Scanner(System.in);
        String ret = null;
        boolean flag = true;
        do{
            ret = sc.nextLine();
            try{
                Tshirt.Padrao test = Tshirt.Padrao.valueOf(ret.toUpperCase());
                flag = false;
            } catch (Exception e){
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um Padrão correspondente a um dos valores expostos.");
            }
        } while(flag);
        return ret;
    }

    public String leDimensao(){
        Scanner sc = new Scanner(System.in);
        String ret = null;
        boolean flag = true;
        do{
            ret = sc.nextLine();
            try{
                Mala.Dim test = Mala.Dim.valueOf(ret.toUpperCase());
                flag = false;
            } catch (Exception e){
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza uma Dimensão correspondente a um dos valores expostos.");
            }
        } while(flag);
        return ret;
    }

    public String leLocalDate(){
        Scanner sc = new Scanner(System.in);
        String ret = null;
        boolean flag = true;
        do{
            ret = sc.nextLine();
            try{
                LocalDate test = LocalDate.parse(ret);
                flag = false;
            } catch (Exception e){
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza o formato dum LocalDate como foi mencionado.");
            }
        } while(flag);
        return ret;
    }

    public void imprimeArtigos(List<Artigo> list){
        for (Artigo c : list) {
            System.out.println(c.toString());
        }
    }

    public int imprimeEncomendasUser(String email, Map<Integer,Encomenda> encs, Map<Integer,String> lig){
        int contador = 0;
        for(Encomenda c : encs.values()){
            String name = lig.get(c.getCodigo());
            if(name != null && name.equals(email)){
                System.out.println(c.toString());
                contador++;
            }
        }
        return contador;
    }





    public void invalidOption() {
        System.out.println("Opcao invalida.");
    }

}