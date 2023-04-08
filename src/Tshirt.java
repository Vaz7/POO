public class Tshirt {
    public enum Tamanho{
        S,
        M,
        L,
        XL
    }

    public enum Padrao{
        Liso,
        Riscas,
        Palmeiras
    }

    private Tamanho tamanho;
    private Padrao padrao;
    private boolean novo;
    private Common3artigos.Estado estado;
    private int n_donos;
    private String desc;
    private String marca;
    private String codAlfaNum;
    private double preco_base;
    private double preco_curr;

    public Tshirt(){
        this.tamanho = Tamanho.S;
        this.padrao = Padrao.Liso;
        this.novo = false;
        this.estado = Common3artigos.Estado.MAU;
        this.n_donos = 0;
        this.desc = "";
        this.marca = "";
        this.codAlfaNum = "";
        this.preco_base = 0;
        this.preco_curr = 0;
    }

    public Tshirt(Tamanho tamanho, Padrao padrao, boolean novo, Common3artigos.Estado estado, int n_donos, String desc, String marca, String codAlfaNum, double preco_base) {
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.novo = novo;
        this.estado = estado;
        this.n_donos = n_donos;
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = codAlfaNum;
        this.preco_base = preco_base;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Tshirt(Tshirt o){
        this.padrao = o.getPadrao();
        this.desc = o.getDesc();
        this.novo = o.isNovo();
        this.estado = o.getEstado();
        this.marca = o.getMarca();
        this.tamanho = o.getTamanho();
        this.n_donos = o.getN_donos();
        this.codAlfaNum = o.getCodAlfaNum();
        this.preco_base = o.getPreco_base();
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


    private double calculaPrecoDesconto(){
        double preco = this.preco_base;
        if(this.padrao != Padrao.Liso && !this.novo){
            preco *= 0.5;
        }
        return preco;
    }


    public Tshirt clone(){
        return new Tshirt(this);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("TShirt :: " + this.codAlfaNum +"\n");
        sb.append("Marca: " + this.marca + "\n");
        sb.append("Tamanho: " + this.tamanho +"\n");
        sb.append("Padrão: " + this.padrao + "\n");
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

        Tshirt l = (Tshirt) o;
        return this.tamanho == l.getTamanho() &&
                this.novo == l.isNovo() &&
                this.estado == l.getEstado() &&
                this.n_donos == l.getN_donos() &&
                this.preco_base == l.getPreco_base() &&
                this.preco_curr == l.getPreco_curr() &&
                this.desc.equals(l.getDesc()) &&
                this.marca.equals(l.getMarca()) &&
                this.codAlfaNum.equals(l.getCodAlfaNum()) &&
                this.padrao == l.getPadrao();
    }
}
