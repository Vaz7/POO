public class Artigo {
    public enum Estado {
        PESSIMO,
        MAU,
        RAZOAVEL,
        BOM,
        MUITO_BOM
    }
    private boolean novo;
    private int n_donos;
    private Estado estado;
    private String desc;
    private String marca;
    private String codAlfaNum;
    private double preco_base;

    public Artigo(){
        this.novo = false;
        this.n_donos = 0;
        this.estado = Estado.PESSIMO;
        this.marca = "";
        this.desc = "";
        this.codAlfaNum = "";
        this.preco_base = 0;
    }

    public Artigo(boolean novo, String desc, String marca, String codAlfaNum, double preco_base) {
        this.novo = novo;
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = codAlfaNum;
        this.preco_base = preco_base;
    }

    public Artigo(boolean novo, int n_donos, Estado estado, String desc, String marca, String codAlfaNum, double preco_base) {
        this.novo = novo;
        this.n_donos = n_donos;
        this.estado = estado;
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = codAlfaNum;
        this.preco_base = preco_base;
    }

    public Artigo(Artigo o){
        this.novo = o.isNovo();
        this.n_donos = o.getN_donos();
        this.estado = o.getEstado();
        this.desc = o.getDesc();
        this.marca = o.getMarca();
        this.codAlfaNum = o.getCodAlfaNum();
        this.preco_base = o.getPreco_base();
    }

    public int getN_donos() {
        return this.n_donos;
    }

    public void setN_donos(int n_donos) {
        this.n_donos = n_donos;
    }

    public boolean isNovo() {
        return this.novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public Artigo clone(){
        return new Artigo(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo :: " + this.codAlfaNum +"\n");
        sb.append("Marca: " + this.marca + "\n");
        sb.append("Novo: " + isNovo() + "\n");
        if(!isNovo()) {
            sb.append("Estado: " + this.estado + "\n");
            sb.append("Nº de donos: " + this.n_donos + "\n");
        }
        sb.append("Preço Base: " + this.preco_base + "\n");
        sb.append("Descrição: " + this.desc + "\n");

        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Artigo l = (Artigo) o;
        return  this.novo == l.isNovo() &&
                this.estado == l.getEstado() &&
                this.n_donos == l.getN_donos() &&
                Double.compare(this.preco_base, l.getPreco_base()) == 0 &&
                this.desc.equals(l.getDesc()) &&
                this.marca.equals(l.getMarca()) &&
                this.codAlfaNum.equals(l.getCodAlfaNum());
    }


}
