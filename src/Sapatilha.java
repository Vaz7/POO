import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Sapatilha extends Artigo {
    private double tamanho;
    private boolean atacadores;
    private String cor;
    private LocalDate colecao;
    private boolean premium;

    private double preco_curr;

    public Sapatilha(){
        super();
        this.tamanho = 0;
        this.atacadores = false;
        this.cor = "";
        this.colecao = LocalDate.now();
        this.premium = false;
        this.preco_curr = getPreco_base();
    }

    public Sapatilha(boolean novo, String desc, String marca, double preco_base, double tamanho, boolean atacadores, String cor, LocalDate colecao, boolean premium,Transportadora transportadora) {
        super(novo, desc, marca,preco_base,transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.colecao = colecao;
        this.premium = premium;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Sapatilha(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, double tamanho, boolean atacadores, String cor, LocalDate colecao, boolean premium, Transportadora transportadora) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.colecao = colecao;
        this.premium = premium;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Sapatilha(Sapatilha o) {
        super(o);
        setNovo(o.isNovo());
        setN_donos(o.getN_donos());
        setEstado(o.getEstado());
        setDesc(o.getDesc());
        setMarca(o.getMarca());
        setCodAlfaNum(o.getCodAlfaNum());
        setPreco_base(o.getPreco_base());
        this.tamanho = o.getTamanho();
        this.atacadores = o.isAtacadores();
        this.cor = o.getCor();
        this.colecao = o.getColecao();
        this.premium = o.isPremium();
        this.preco_curr = o.getPreco_curr();
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
        double preco = this.getPreco_base();
        int donos = this.getN_donos();
        long year_interval = ChronoUnit.YEARS.between(this.colecao, LocalDate.now());
        if(!this.premium) {
            if (year_interval >= 1 || this.tamanho > 45) {
                switch (this.getEstado()) {
                    case PESSIMO:
                        preco -= (preco/donos)*(1.0/5.0);
                        break;
                    case MAU:
                        preco -= (preco/donos)*(1.0/4.0);
                        break;
                    case RAZOAVEL:
                        preco -= (preco/donos)*(1.0/3.0);
                        break;
                    case BOM:
                        preco -= (preco/donos)*(1.0/2.0);
                        break;
                    case MUITO_BOM:
                        preco -= (preco/donos);
                        break;
                }
            }
        }
        else {
            preco += (double)year_interval * 0.1 * preco;
        }
        return preco;
    }

    public Sapatilha clone(){
        return new Sapatilha(this);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum() + "--");
        sb.append("Sapatilha " + this.getMarca());
        sb.append(", Novo: " + this.isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Tamanho: " + this.tamanho);
        sb.append(", Atacadores: " + this.atacadores);
        sb.append(", Cor: " + this.cor);
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
        Sapatilha sapatilha = (Sapatilha) o;
        return Double.compare(sapatilha.tamanho, tamanho) == 0 &&
                atacadores == sapatilha.atacadores &&
                premium == sapatilha.premium &&
                Double.compare(sapatilha.preco_curr, preco_curr) == 0 &&
                this.cor.equals(sapatilha.getCor()) &&
                this.colecao.equals(sapatilha.getColecao());
    }
}
