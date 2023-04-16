public class Tshirt extends Artigo{
    public enum Tamanho{
        S,
        M,
        L,
        XL
    }
    public enum Padrao{
        Riscas,
        Liso,
        Palmeiras
    }

    private Tamanho tamanho;
    private Padrao padrao;
    private double preco_curr;

    public Tshirt(){
        super();
        this.tamanho = Tamanho.S;
        this.padrao = Padrao.Riscas;
        this.preco_curr = getPreco_base();
    }

    public Tshirt(boolean novo, String desc, String marca, double preco_base, Tamanho tamanho, Padrao padrao,Transportadora transportadora) {
        super(novo, desc, marca, preco_base,transportadora);
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Tshirt(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora);
        //this.tamanho = tamanho;
        //this.padrao = padrao;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Tshirt(Tshirt o) {
        super(o);
        this.tamanho = o.getTamanho();
        this.padrao = o.getPadrao();
        this.preco_curr = o.getPreco_curr();
    }

    public Tamanho getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Padrao getPadrao() {
        return this.padrao;
    }

    public void setPadrao(Padrao padrao) {
        this.padrao = padrao;
    }

    public double getPreco_curr() {
        return this.preco_curr;
    }

    public void setPreco_curr(double preco_curr) {
        this.preco_curr = preco_curr;
    }

    private double calculaPrecoDesconto(){
        double preco = this.getPreco_base();
        if(this.padrao != Padrao.Liso && !this.isNovo()){
            preco *= 0.5;
        }
        return preco;
    }

    public Tshirt clone(){
        return new Tshirt(this);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCodAlfaNum() + "--");
        sb.append("T-shirt ");
        sb.append(getMarca());
        sb.append(", Novo: " + isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + getEstado());
            sb.append(", Nº de donos: " + getN_donos());
        }
        sb.append(", Preço Base: " + getPreco_base());
        sb.append(", Descrição: " + getDesc());
        sb.append(", Tamanho: " + this.tamanho);
        sb.append(", Padrão: " + this.padrao);
        sb.append(", Preço Atual: " + this.preco_curr);
        return sb.toString();
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        Tshirt tshirt = (Tshirt) o;
        return Double.compare(tshirt.preco_curr, preco_curr) == 0 &&
                this.padrao == tshirt.getPadrao() &&
                this.tamanho == tshirt.getTamanho();
    }
}
