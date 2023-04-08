import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Mala {
    public enum Dimensao{
        Pequena,
        Media,
        Grande
    }
    private boolean novo;
    private Common3artigos.Estado estado;
    private int n_donos;
    private String desc;
    private String codAlfaNum;
    private String marca;
    private double preco_base;
    private double preco_curr;
    private Dimensao dimensao;
    private String material;
    private LocalDate colecao;
    private boolean premium;

    public Mala(){
        this.novo = false;
        this.estado = Common3artigos.Estado.BOM;
        this.n_donos = 0;
        this.desc = "";
        this.codAlfaNum = "";
        this.marca = "";
        this.preco_base = 0;
        this.preco_curr = 0;
        this.dimensao = Dimensao.Pequena;
        this.material = "";
        this.colecao = LocalDate.now();
        this.premium = false;
    }

    public Mala(boolean novo, Common3artigos.Estado estado, int n_donos, String desc, String codAlfaNum, String marca, double preco_base, Dimensao dimensao, String material, LocalDate colecao, boolean premium) {
        this.novo = novo;
        this.estado = estado;
        this.n_donos = n_donos;
        this.desc = desc;
        this.codAlfaNum = codAlfaNum;
        this.marca = marca;
        this.preco_base = preco_base;
        this.dimensao = dimensao;
        this.material = material;
        this.colecao = colecao;
        this.premium = premium;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Mala(Mala o){
        this.novo = o.isNovo();
        this.estado = o.getEstado();
        this.n_donos = o.getN_donos();
        this.desc = o.getDesc();
        this.codAlfaNum = o.getCodAlfaNum();
        this.marca = o.getMarca();
        this.preco_base = o.getPreco_base();
        this.preco_curr = o.getPreco_curr();
        this.dimensao = o.getDimensao();
        this.material = o.getMaterial();
        this.colecao = o.getColecao();
        this.premium = o.isPremium();

    }

    public boolean isNovo() {
        return this.novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public Common3artigos.Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Common3artigos.Estado estado) {
        this.estado = estado;
    }

    public int getN_donos() {
        return this.n_donos;
    }

    public void setN_donos(int n_donos) {
        this.n_donos = n_donos;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCodAlfaNum() {
        return this.codAlfaNum;
    }

    public void setCodAlfaNum(String codAlfaNum) {
        this.codAlfaNum = codAlfaNum;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco_base() {
        return this.preco_base;
    }

    public void setPreco_base(double preco_base) {
        this.preco_base = preco_base;
    }

    public double getPreco_curr() {
        return this.preco_curr;
    }

    public void setPreco_curr(double preco_curr) {
        this.preco_curr = preco_curr;
    }

    public Dimensao getDimensao() {
        return this.dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
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

    private double calculaPrecoDesconto(){
        double preco = this.preco_base;
        long year_interval = ChronoUnit.YEARS.between(this.colecao, LocalDate.now());
        if(!this.premium) {
            switch (this.dimensao) {
                case Pequena:
                    preco -= preco * 0.25;
                    break;
                case Media:
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Mala :: " + this.codAlfaNum +"\n");
        sb.append("Marca: " + this.marca + "\n");
        sb.append("Dimensão: " + this.dimensao +"\n");
        sb.append("Material: " + this.material + "\n");
        sb.append("Coleção: " + this.colecao + "\n");
        sb.append("Premium: " + this.premium + "\n");
        sb.append("Novo: " + isNovo() + "\n");
        if(!isNovo()) {
            sb.append("Estado: " + this.estado + "\n");
            sb.append("Nº de donos: " + this.n_donos + "\n");
        }
        sb.append("Preço Base: " + this.preco_base + "\n");
        sb.append("Preço com Desconto: " + this.preco_curr + "\n");
        sb.append("Descrição: " + this.desc + "\n");

        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Mala l = (Mala) o;
        return this.dimensao == l.getDimensao() &&
                this.novo == l.isNovo() &&
                this.estado == l.getEstado() &&
                this.n_donos == l.getN_donos() &&
                this.preco_base == l.getPreco_base() &&
                this.preco_curr == l.getPreco_curr() &&
                this.desc.equals(l.getDesc()) &&
                this.marca.equals(l.getMarca()) &&
                this.codAlfaNum.equals(l.getCodAlfaNum()) &&
                this.material.equals(l.getMaterial()) &&
                this.premium == l.isPremium() &&
                this.colecao.equals(l.getColecao());
    }
}
