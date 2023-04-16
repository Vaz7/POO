import java.security.SecureRandom;
import java.util.Random;

public abstract class Artigo {
    public enum Estado {
        PESSIMO("1"),
        MAU("2"),
        RAZOAVEL("3"),
        BOM("4"),
        MUITO_BOM("5");

        private String valor;

        private Estado(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }

    private boolean novo;
    private int n_donos;
    private Estado estado;
    private String desc;
    private String marca;
    private String codAlfaNum;
    private double preco_base;
    private Transportadora transp;

    public Artigo(){
        this.novo = false;
        this.n_donos = 0;
        this.estado = Estado.PESSIMO;
        this.marca = "";
        this.desc = "";
        this.codAlfaNum = "";
        this.preco_base = 0;
        this.transp = new Transportadora();
    }

    public Artigo(boolean novo, String desc, String marca, double preco_base,Transportadora transportadora) {
        this.novo = novo;
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = alfanumericalGenerator();
        this.preco_base = preco_base;
        this.transp = transportadora.clone();
    }

    public Artigo(boolean novo, int n_donos, Estado estado, String desc,String marca, double preco_base, Transportadora transportadora) {
        this.novo = novo;
        this.n_donos = n_donos;
        this.estado = estado;
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = alfanumericalGenerator();
        this.preco_base = preco_base;
        this.transp = transportadora.clone();
    }

    public Artigo(Artigo o){
        this.novo = o.isNovo();
        this.n_donos = o.getN_donos();
        this.estado = o.getEstado();
        this.desc = o.getDesc();
        this.marca = o.getMarca();
        this.codAlfaNum = o.getCodAlfaNum();
        this.preco_base = o.getPreco_base();
        this.transp = o.getTransp();
    }

    public Transportadora getTransp() {
        return this.transp.clone();
    }

    public void setTransp(Transportadora transp) {
        this.transp = transp.clone();
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

    public abstract Artigo clone();

    public abstract String toString();

    public abstract double getPreco_curr();

    public String alfanumericalGenerator() {

        String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int LENGTH = 12;


        StringBuilder sb = new StringBuilder(LENGTH);
        Random random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

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
                this.codAlfaNum.equals(l.getCodAlfaNum()) &&
                this.transp.equals(l.getTransp());
    }


}
