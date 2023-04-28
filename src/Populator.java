import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Populator {





    public static Vintage populateData(Vintage vintage){

        Transportadora transp1 = new Transportadora(true,15010,"gls",18000);
        Transportadora transp2 = new Transportadora(true,20000,"ctt",25000);
        Transportadora transp3 = new Transportadora(true,17000,"dhl",19000);
        Transportadora transp4 = new Transportadora(true,17500,"mrw",20000);

        Utilizador user1 = new Utilizador("henrique@vaz.pt","Henrique Vaz","Rua das cenas, n12",260485489,12.5,11.2);
        vintage.addUser(user1);
        Artigo art1 =new Tshirt(true,"Tshirt nova","Ferrari",110,transp1,Tshirt.Tamanho.XL,Tshirt.Padrao.LISO);
        Artigo art2 =new Tshirt(true,"Tshirt nova","Lacoste",90,transp2,Tshirt.Tamanho.L,Tshirt.Padrao.PALMEIRAS);
        Artigo art3 = new Mala(true,"Artigo novo","prada",245,transp3, Mala.Dim.GRANDE,"pele sintética",LocalDate.parse("2023-12-12"),false);
        vintage.addArigoVenda(user1.getEmail(),art1);
        vintage.addArigoVenda(user1.getEmail(),art2);
        vintage.addArigoVenda(user1.getEmail(),art3);

        Utilizador user2 = new Utilizador("goncalo@goncalves.pt","Gonçalo Gonçalves","Rua das batatas, n15",123456789,145.4,151.1);
        vintage.addUser(user2);
        Artigo art4 =new Tshirt(true,"Tshirt nova","Puma",60,transp1,Tshirt.Tamanho.XL,Tshirt.Padrao.LISO);
        Artigo art5 =new Tshirt(true,"Tshirt nova","Nude project",150,transp2,Tshirt.Tamanho.L,Tshirt.Padrao.PALMEIRAS);
        Artigo art6 =new Mala(true,"Artigo novo","Tous",40,transp3, Mala.Dim.GRANDE,"pele sintética",LocalDate.parse("2023-12-12"),false);
        vintage.addArigoVenda(user2.getEmail(),art4);
        vintage.addArigoVenda(user2.getEmail(),art5);
        vintage.addArigoVenda(user2.getEmail(),art6) ;

        Encomenda encDoUser1 = new Encomenda();
        Encomenda encDoUser2 = new Encomenda();


        encDoUser1.addArtEncomenda(art4);
        encDoUser1.addArtEncomenda(art5);
        encDoUser2.addArtEncomenda(art1);

        vintage.addEncomenda(user1.getEmail(),encDoUser1);
        vintage.addEncomenda(user2.getEmail(),encDoUser2);


        return vintage;
    }

}
