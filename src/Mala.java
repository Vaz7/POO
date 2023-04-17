import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Mala extends Artigo {
    public enum Dim{
        Pequeno,
        Medio,
        Grande
    }

    private Dim dimensao;
    private String material;
    private LocalDate colecao;
    private boolean premium;
    private double preco_curr;

    public Mala(){
        this.dimensao = Dim.Pequeno;
        this.material = "";
        this.colecao = LocalDate.now();
        this.premium = false;
        this.preco_curr = this.getPreco_base();
    }

    public Mala(boolean novo, String desc, String marca , double preco_base, Dim dimensao, String material, LocalDate colecao, boolean premium, Transportadora transportadora) {
        super(novo, desc, marca, preco_base,transportadora);
        this.dimensao = dimensao;
        this.material = material;
        this.colecao = colecao;
        this.premium = premium;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Mala(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Dim dimensao, String material, LocalDate colecao, boolean premium,Transportadora transportadora) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora);
        this.dimensao = dimensao;
        this.material = material;
        this.colecao = colecao;
        this.premium = premium;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Mala(Mala o) {
        super(o);
        this.dimensao = o.getDimensao();
        this.material = o.getMaterial();
        this.colecao = o.getColecao();
        this.premium = o.isPremium();
        this.preco_curr = o.getPreco_curr();
    }

    public Dim getDimensao() {
        return this.dimensao;
    }

    public void setDimensao(Dim dimensao) {
        this.dimensao = dimensao;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public LocalDate getColecao() {
        return this.colecao;
    }

    public void setColecao(LocalDate colecao) {
        this.colecao = colecao;
    }

    public boolean isPremium() {
        return this.premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getPreco_curr() {
        return this.preco_curr;
    }

    public void setPreco_curr(double preco_curr) {
        this.preco_curr = preco_curr;
    }

    private double calculaPrecoDesconto(){
        double preco = this.getPreco_base();
        long year_interval = ChronoUnit.YEARS.between(this.colecao, LocalDate.now());
        if(!this.premium) {
            switch (this.dimensao) {
                case Pequeno:
                    preco -= preco * 0.25;
                    break;
                case Medio:
                    preco -= preco * 0.15;
                    break;
                case Grande:
                    preco -= preco * 0.1;
                    break;
            }
        }
        else{
            preco += (double)year_interval * 0.1 * preco;
        }
        return preco;
    }

    public Mala clone(){
        return new Mala(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum() + "--");
        sb.append("Mala " + this.getCodAlfaNum());
        sb.append(this.getMarca());
        sb.append(", Novo: " + isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Dimensão: " + this.dimensao);
        sb.append(", Material: " + this.material);
        sb.append(", Coleção: " + this.colecao);
        sb.append(", Premium: " + this.premium);
        sb.append(", Preço Atual: " + this.preco_curr);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        Mala mala = (Mala) o;
        return premium == mala.premium &&
                Double.compare(mala.preco_curr, preco_curr) == 0 &&
                this.colecao.equals(mala.getColecao()) &&
                this.dimensao == mala.getDimensao() &&
                this.material.equals(mala.getMaterial());
    }

    public String toLog(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sapatilha:")
                .append(this.getCodAlfaNum() + ",")
                .append(this.getMarca() + ",")
                .append(this.isNovo() + ",")
                .append(this.getEstado() + ",")
                .append(this.getN_donos() + ",")
                .append(this.getPreco_base() + ",")
                .append(this.getDesc() + ",")
                .append(this.getTransp() + ",")
                .append(this.getDimensao() + ",")
                .append(this.getMaterial() + ",")
                .append(this.getColecao() + ",")
                .append(this.isPremium() + ",")
                .append(this.getPreco_curr() + "\n");
        return sb.toString();
    }
}
