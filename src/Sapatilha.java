import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Sapatilha {
    private double tamanho;
    private boolean atacadores;
    private String cor;
    private LocalDate colecao;
    private boolean premium;
    private boolean novo;
    private Common3artigos.Estado estado;
    private int n_donos;
    private double preco_base;
    private double preco_curr;
    private String desc;
    private String marca;
    private String codAlfaNum;



    public Sapatilha(){
        this.tamanho = 0;
        this.atacadores = false;
        this.cor = "";
        this.colecao = LocalDate.now();
        this.premium = false;
        this.novo = false;
        this.estado = Common3artigos.Estado.RAZOAVEL;
        this.n_donos = 0;
        this.preco_base = 0;
        this.preco_curr = 0;
        this.desc = "";
        this.marca = "";
        this.codAlfaNum = "";
    }

    public Sapatilha(double tamanho, boolean atacadores, String cor, LocalDate colecao, boolean premium, boolean novo, Common3artigos.Estado estado, int n_donos, double preco_base, String desc, String marca, String codAlfaNum) {
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.colecao = colecao;
        this.premium = premium;
        this.novo = novo;
        this.estado = estado;
        this.n_donos = n_donos;
        this.preco_base = preco_base;
        this.preco_curr = calculaPrecoDesconto();
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = codAlfaNum;
    }

    public Sapatilha(Sapatilha o){
        this.tamanho = o.getTamanho();
        this.atacadores = o.isAtacadores();
        this.cor = o.getCor();
        this.colecao = o.getColecao();
        this.premium = o.isPremium();
        this.novo = o.isNovo();
        this.estado = o.getEstado();
        this.n_donos = o.getN_donos();
        this.preco_base = o.getPreco_base();
        this.preco_curr = o.getPreco_curr();
        this.desc = o.getDesc();
        this.marca = o.getMarca();
        this.codAlfaNum = o.getCodAlfaNum();
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodAlfaNum() {
        return this.codAlfaNum;
    }

    public void setCodAlfaNum(String codAlfaNum) {
        this.codAlfaNum = codAlfaNum;
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

    public double getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isAtacadores() {
        return this.atacadores;
    }

    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    private double calculaPrecoDesconto() {
        double preco = this.preco_base;
        long year_interval = ChronoUnit.YEARS.between(this.colecao, LocalDate.now());
        if(!this.premium) {
            if (year_interval >= 1 || this.tamanho > 45) {
                switch (this.getEstado()) {
                    case PESSIMO:
                        preco -= (preco/this.n_donos)*(1.0/5.0);
                        break;
                    case MAU:
                        preco -= (preco/this.n_donos)*(1.0/4.0);
                        break;
                    case RAZOAVEL:
                        preco -= (preco/this.n_donos)*(1.0/3.0);
                        break;
                    case BOM:
                        preco -= (preco/this.n_donos)*(1.0/2.0);
                        break;
                    case MUITO_BOM:
                        preco -= (preco/this.n_donos);
                        break;
                }
            }
        }
        else {
            preco += (double)year_interval * 0.1 * preco;
        }
        return preco;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sapatilha :: " + this.codAlfaNum +"\n");
        sb.append("Marca: " + this.marca + "\n");
        sb.append("Tamanho: " + this.tamanho +"\n");
        sb.append("Cor: " + this.cor + "\n");
        sb.append("Atacadores: " + this.atacadores + "\n");
        sb.append("Colecao: " + this.colecao + "\n");
        sb.append("Premium: " + isPremium() + "\n");
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

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Sapatilha l = (Sapatilha) o;
        return this.tamanho == l.getTamanho() &&
                this.premium == l.isPremium() &&
                this.atacadores == l.isAtacadores() &&
                this.colecao.equals(l.getColecao()) &&
                this.cor.equals(l.getCor()) &&
                this.novo == l.isNovo() &&
                this.estado == l.getEstado() &&
                this.n_donos == l.getN_donos() &&
                this.preco_base == l.getPreco_base() &&
                this.preco_curr == l.getPreco_curr() &&
                this.desc.equals(l.getDesc()) &&
                this.marca.equals(l.getMarca()) &&
                this.codAlfaNum.equals(l.getCodAlfaNum());
    }
}
