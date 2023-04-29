import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MalaPremium extends Mala implements Premium{

    private double premiumPrice;

    public MalaPremium(){
        super();
        this.premiumPrice = 0;
    }

    public MalaPremium(boolean novo, String desc, String marca , double preco_base, Transportadora transportadora, Dim dimensao, String material, LocalDate colecao){
        super(novo, desc, marca, preco_base,transportadora,dimensao,material,colecao);
        this.premiumPrice = calculaPreco();
    }

    public MalaPremium(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora, Dim dimensao, String material, LocalDate colecao){
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora,dimensao,material,colecao);
        this.premiumPrice = calculaPreco();
    }

    public MalaPremium(MalaPremium o){
        super(o);
        this.premiumPrice = calculaPreco();
    }

    public double getPremiumPrice() {
        return premiumPrice;
    }

    public void setPremiumPrice(double premiumPrice) {
        this.premiumPrice = premiumPrice;
    }

    public MalaPremium clone(){
        return new MalaPremium(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum()+"--");
        sb.append("MalaPremium " + this.getMarca());
        sb.append(", Novo: " + isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Dimensão: " + this.getDimensao());
        sb.append(", Material: " + this.getMaterial());
        sb.append(", Coleção: " + this.getColecao());
        sb.append(", Preço Atual: " + this.premiumPrice);
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        MalaPremium mala = (MalaPremium) o;
        return Double.compare(mala.getPremiumPrice(), this.premiumPrice) == 0;
    }

    @Override
    public double calculaPreco() {
        double preco = this.getPreco_base();
        long year_interval = ChronoUnit.YEARS.between(this.getColecao(), LocalDate.now());
        preco += (double)year_interval * 0.1 * preco;
        return preco;
    }
}
