import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SapatilhaPremium extends Sapatilha implements Premium {
    private double premiumPrice;

    public SapatilhaPremium(){
        super();
        this.premiumPrice = 0;
    }

    public SapatilhaPremium(boolean novo, String desc, String marca, double preco_base, Transportadora transportadora, double tamanho, boolean atacadores, String cor, LocalDate colecao){
        super(novo, desc, marca,preco_base,transportadora, tamanho, atacadores,cor,colecao);
        this.premiumPrice = calculaPreco();
    }

    public SapatilhaPremium(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora, double tamanho, boolean atacadores, String cor, LocalDate colecao) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora,tamanho,atacadores,cor,colecao);
        this.premiumPrice = calculaPreco();
    }

    public SapatilhaPremium(SapatilhaPremium o){
        super(o);
        this.premiumPrice = calculaPreco();
    }

    public double getPremiumPrice() {
        return premiumPrice;
    }

    public void setPremiumPrice(double premiumPrice) {
        this.premiumPrice = premiumPrice;
    }

    public SapatilhaPremium clone(){
        return new SapatilhaPremium(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum() + "--");
        sb.append("Sapatilha Premium " + this.getMarca());
        sb.append(", Novo: " + this.isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Tamanho: " + this.getTamanho());
        sb.append(", Atacadores: " + this.isAtacadores());
        sb.append(", Cor: " + this.getCor());
        sb.append(", Coleção: " + this.getColecao());
        sb.append(", Preço Atual: " + this.premiumPrice);
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        SapatilhaPremium sapatilha = (SapatilhaPremium) o;
        return Double.compare(sapatilha.getPremiumPrice(), this.premiumPrice) == 0;
    }

    public double calculaPreco() {
        double preco = this.getPreco_base();
        long year_interval = ChronoUnit.YEARS.between(this.getColecao(), LocalDate.now());
        preco += (double)year_interval * 0.1 * preco;

        return preco;
    }
}
