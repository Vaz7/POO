import java.time.LocalDate;

public class GandaFixe {
    public static void main(String[] args) {
        LocalDate data = LocalDate.now().minusYears(3);
//        Sapatilha test2 = new Sapatilha(42, true,"Branco", data.plusYears(1), true, false, Common3artigos.Estado.BOM, 1, 450, "como novo", "Nike", "012345678905");
//        Sapatilha test = new Sapatilha(46, true,"Branco", data, false, false, Common3artigos.Estado.BOM, 3, 112,"usado poucas vezes", "Adidas", "111982743854");
//        Sapatilha test3 = new Sapatilha(42, true,"Branco", data.plusYears(1), true, false, Common3artigos.Estado.BOM, 1, 450, "como novo", "Nike", "012345678905");
//        System.out.println(test2.toString());
//        System.out.println(test.toString());
//
//        System.out.println(test2.equals(test3));
//        System.out.println(test.equals(test2));

//        Tshirt test4 = new Tshirt(Tshirt.Tamanho.L, Tshirt.Padrao.Riscas, false, Common3artigos.Estado.RAZOAVEL, 3, "usada", "Puma", "12037123192", 25);
//        Tshirt test5 = new Tshirt(Tshirt.Tamanho.S, Tshirt.Padrao.Liso, false, Common3artigos.Estado.MUITO_BOM, 3, "usada", "Nike", "12037124552", 19);
//        Tshirt test6 = new Tshirt(Tshirt.Tamanho.S, Tshirt.Padrao.Liso, true, Common3artigos.Estado.MUITO_BOM, 3, "usada", "Nike", "12037124552", 19);
//
//        System.out.println(test4.toString());
//        System.out.println(test5.toString());
//        System.out.println(test5.equals(test6));
//        System.out.println(test5.equals(test4));

        Mala test9 = new Mala(true, Common3artigos.Estado.MUITO_BOM, 1, "mala vunita", "177564765143", "Chanel", 433, Mala.Dimensao.Grande, "Pele de urso", data.minusYears(3), false);
        Mala test8 = new Mala(false, Common3artigos.Estado.RAZOAVEL, 1, "mala vunita", "177834765143", "Prada", 1025, Mala.Dimensao.Pequena, "Pele de renekton", data.minusYears(5), true);
        Mala test7 = new Mala(false, Common3artigos.Estado.RAZOAVEL, 1, "mala vunita", "177834765143", "Prada", 1025, Mala.Dimensao.Pequena, "Pele de renekton", data.minusYears(5), true);

        System.out.println(test9.toString());
        System.out.println(test8.toString());
        System.out.println(test8.equals(test7));
        System.out.println(test8.equals(test9));
    }
}
