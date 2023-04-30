import java.util.Set;

public class TransportadoraPremium extends Transportadora implements Premium {
    public final static double impostoPremium = 0.25;
    public static final double P_PEQUENAS = 6.99;
    public static final double P_MEDIAS = 14.99;
    public static final double P_GRANDES = 19.99;

    public TransportadoraPremium(){
        super();
    }

    public TransportadoraPremium (String nome, double lucro){
        super(nome,lucro);
    }

    public TransportadoraPremium (double lucro,String nome, double dinheiro_feito){
        super(lucro,nome,dinheiro_feito);
    }

    public TransportadoraPremium( TransportadoraPremium o){
        super(o);
    }

    public TransportadoraPremium clone(){
        return new TransportadoraPremium(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("TransportadoraPremium :: " + this.getNome());
        sb.append(", Lucro: " + this.getLucro());
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        return super.equals(o);
    }

    public double precoTransporte(Set<Artigo> artigos) {
        int conta = 0, contaprem = 0;
        for(Artigo c : artigos){
            if(c.getTransp().equals(this.getNome())){
                if(c instanceof Premium) contaprem++;
                else conta++;
            }
        }

        double base = 0;
        if(conta == 1) base = PEQUENAS;
        else if(conta >= 2 && conta <=5 ) base = MEDIAS;
        else if(conta > 5) base = GRANDES;

        double preco = base * (1.0+this.getLucro()) * (1.0+IMPOSTO) * 0.9;

        if(contaprem == 1) base = P_PEQUENAS;
        else if(contaprem >= 2 && conta <=5 ) base = P_MEDIAS;
        else if(contaprem > 5) base = P_GRANDES;

        preco += base * (1.0+this.getLucro()) * (1.0+impostoPremium) * 0.9;

        return preco;
    }
}
