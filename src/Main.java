import jdk.jshell.execution.Util;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        LocalDate data = LocalDate.now().minusYears(3);
        Artigo test2 = new Sapatilha(false,2, Artigo.Estado.RAZOAVEL,"bom estado","Puma",110,46,false,"Branco",data.minusYears(2), false);
        Artigo test3 = new Sapatilha(false,5, Artigo.Estado.MAU,"por arranjar","Adidas",90,42,true,"Branco",data.minusYears(6), false);
        Artigo test4 = new Sapatilha(false,2, Artigo.Estado.RAZOAVEL,"bom estado","Puma",110,46,false,"Branco",data.minusYears(2), false);

//        System.out.println(test2.toString());
//        System.out.println(test3.toString());
//        System.out.println(test2.equals(test3));
//        System.out.println(test4.equals(test2));

        Artigo test5 = new Tshirt(false, 2, Artigo.Estado.BOM, "BOM ESTADO", "Levis", 30, Tshirt.Tamanho.L, Tshirt.Padrao.Liso);
        Artigo test6 = new Tshirt(false, 4, Artigo.Estado.BOM, "Bonzinho ESTADO", "united colors", 56, Tshirt.Tamanho.L, Tshirt.Padrao.Riscas);
        Artigo test7 = new Tshirt(false, 2, Artigo.Estado.BOM, "BOM ESTADO", "Levis", 30, Tshirt.Tamanho.L, Tshirt.Padrao.Liso);

//        System.out.println(test5);
//        System.out.println(test6.toString());
//        System.out.println(test5.equals(test7));
//        System.out.println(test5.equals(test6));

        Artigo test8 = new Mala(false, 3, Artigo.Estado.BOM, "ruim", "chanel", 995.99, Mala.Dim.Pequeno, "pele de tigre", data.minusYears(5), false);
        Artigo test9 = new Mala(false, 5, Artigo.Estado.RAZOAVEL, "ruim de bala", "prada", 2000.99, Mala.Dim.Medio, "pele de mamute", data.minusYears(23), true);
        Artigo test10 = new Mala(false, 3, Artigo.Estado.BOM, "ruim", "chanel", 995.99, Mala.Dim.Pequeno, "pele de tigre", data.minusYears(5), false);

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
        System.out.println(b);

        Utilizador user1 = new Utilizador();
        Utilizador user2 = new Utilizador();
        Utilizador user3 = new Utilizador();

        System.out.println(user1.toString());
        System.out.println(user2.toString());
        System.out.println(user3.toString());
//        b.removeArtEncomenda(test9);
//        System.out.println(b);

    }
}
