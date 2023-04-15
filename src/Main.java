import jdk.jshell.execution.Util;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Transportadora transportadora = new Transportadora();
        LocalDate data = LocalDate.now().minusYears(3);
        Artigo test2 = new Sapatilha(false,2, Artigo.Estado.RAZOAVEL,"bom estado","Puma",110,46,false,"Branco",data.minusYears(2), false,transportadora);
        Artigo test3 = new Sapatilha(false,5, Artigo.Estado.MAU,"por arranjar","Adidas",90,42,true,"Branco",data.minusYears(6), false,transportadora);
        Artigo test4 = new Sapatilha(false,2, Artigo.Estado.RAZOAVEL,"bom estado","Puma",110,46,false,"Branco",data.minusYears(2), false,transportadora);

//        System.out.println(test2.toString());
//        System.out.println(test3.toString());
//        System.out.println(test2.equals(test3));
//        System.out.println(test4.equals(test2));

        Artigo test5 = new Tshirt(false, 2, Artigo.Estado.BOM, "BOM ESTADO", "Levis", 30, Tshirt.Tamanho.L, Tshirt.Padrao.Liso,transportadora);
        Artigo test6 = new Tshirt(false, 4, Artigo.Estado.BOM, "Bonzinho ESTADO", "united colors", 56, Tshirt.Tamanho.L, Tshirt.Padrao.Riscas,transportadora);
        Artigo test7 = new Tshirt(false, 2, Artigo.Estado.BOM, "BOM ESTADO", "Levis", 30, Tshirt.Tamanho.L, Tshirt.Padrao.Liso,transportadora);

//        System.out.println(test5);
//        System.out.println(test6.toString());
//        System.out.println(test5.equals(test7));
//        System.out.println(test5.equals(test6));

        Artigo test8 = new Mala(false, 3, Artigo.Estado.BOM, "ruim", "chanel", 995.99, Mala.Dim.Pequeno, "pele de tigre", data.minusYears(5), false,transportadora);
        Artigo test9 = new Mala(false, 5, Artigo.Estado.RAZOAVEL, "ruim de bala", "prada", 2000.99, Mala.Dim.Medio, "pele de mamute", data.minusYears(23), true,transportadora);
        Artigo test10 = new Mala(false, 3, Artigo.Estado.BOM, "ruim", "chanel", 995.99, Mala.Dim.Pequeno, "pele de tigre", data.minusYears(5), false,transportadora);

//        System.out.println(test8);
//        System.out.println(test9.toString());
//        System.out.println(test8.equals(test10));
//        System.out.println(test8.equals(test9));

        Set<Artigo> lista = new HashSet<>();
        lista.add(test2);
        lista.add(test3);
        lista.add(test4);
        lista.add(test5);
        lista.add(test6);
        lista.add(test7);
        lista.add(test8);

        Encomenda b = new Encomenda(lista, Encomenda.State.Pendente);
        //System.out.println(b.toString());
//        b.addArtEncomenda(test9);
        //System.out.println(b);

        Utilizador user2 = new Utilizador();
        Utilizador user1 = new Utilizador();
        Utilizador user3 = new Utilizador("joaquimberto","cao@gato.pt","Joaquim Alberto","rua dos caes",12345678,12.4,11.4);

        //System.out.println(user1.toString());
        //System.out.println(user2.toString());
        //System.out.println(user3.toString());
//        b.removeArtEncomenda(test9);
//        System.out.println(b);

        UserManager manager = new UserManager();


        user3.addArtigoParaVender(test3);
        user3.addArtigoParaVender(test4);
        user3.addArtigoParaVender(test2);
        user3.addArtigoParaVender(test5);
        user3.addArtigoParaVender(test6);
        user3.addArtigoParaVender(test7);
        user3.addArtigoParaVender(test8);
        user3.addArtigoParaVender(test9);
        user3.addArtigoParaVender(test10);

        manager.addUser(user1);
        manager.addUser(user2);
        manager.addUser(user3);



        //System.out.println("-----------------------------------------------------");
        //System.out.println(manager.toString());

        //Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //System.out.println("Enter product code to buy:");

        //String cod = myObj.nextLine();  // Read user input


        Ui.menu(manager);
        manager.dumpToFile();
        //if(manager.existsArtigo(cod)){
        //    System.out.println("The item is available, the seller is: " + manager.whoSellsArtigo(cod));
        //}
        //else System.out.println("ja foti");

    }
}
