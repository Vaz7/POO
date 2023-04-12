import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Utilizador {
    private static int contador=1;
    private int code;
    private String email;
    private String nome;
    private String morada;
    private int nif;
    private Set<Artigo> vendido;
    private Set<Artigo> para_vender;

    private double dinheiro_vendas;
    private double dinheiro_compras;

    public Utilizador(){
        this.code = this.contador++;
        this.email = "";
        this.morada = "";
        this.nif = 0;
        this.vendido = new HashSet<>();
        this.para_vender = new HashSet<>();
        this.dinheiro_compras = 0;
        this.dinheiro_vendas = 0;
    }

    public Utilizador(String email, String nome, String morada, int nif, Set<Artigo> vendido, Set<Artigo> para_vender, double dinheiro_vendas, double dinheiro_compras) {
        this.code = this.contador++;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        setVendido(vendido);
        setPara_vender(para_vender);
        this.dinheiro_vendas = dinheiro_vendas;
        this.dinheiro_compras = dinheiro_compras;
    }

    public Utilizador(Utilizador o){
        this.code = o.getCode();
        this.email = o.getEmail();
        this.nome = o.getNome();
        this.morada = o.getMorada();
        this.nif = o.getNif();
        this.vendido = o.getVendido();
        this.para_vender = o.getPara_vender();
        this.dinheiro_compras = o.getDinheiro_compras();
        this.dinheiro_vendas = o.getDinheiro_vendas();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNif() {
        return this.nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public Set<Artigo> getVendido() {
        Set<Artigo> novo = new HashSet<>();
        for(Artigo c : this.vendido){
            novo.add(c);
        }
        return novo;
    }

    public void setVendido(Set<Artigo> vendido) {
        this.vendido = new HashSet<Artigo>();
        for(Artigo c : vendido){
            this.vendido.add(c);
        }
    }

    public Set<Artigo> getPara_vender() {
        Set<Artigo> novo = new HashSet<>();
        for(Artigo c : this.para_vender){
            novo.add(c);
        }
        return novo;
    }

    public void setPara_vender(Set<Artigo> para_vender) {
        this.para_vender = new HashSet<Artigo>();
        for(Artigo c : para_vender){
            this.para_vender.add(c);
        }
    }

    public double getDinheiro_vendas() {
        return this.dinheiro_vendas;
    }

    public void setDinheiro_vendas(double dinheiro_vendas) {
        this.dinheiro_vendas = dinheiro_vendas;
    }

    public double getDinheiro_compras() {
        return this.dinheiro_compras;
    }

    public void setDinheiro_compras(double dinheiro_compras) {
        this.dinheiro_compras = dinheiro_compras;
    }

    public void addArtigoVendido(Artigo c){
        this.vendido.add(c.clone());
    }

    public void addArtigoParaVender(Artigo c){
        this.para_vender.add(c.clone());
    }

    public void removeArtigoParaVender(Artigo c){
        this.para_vender.remove(c.clone());
    }

    private void calculaDinheiroCompras(){
        double preco = 0;
        for(Artigo c : this.vendido){
            preco += c.getPreco_curr();
        }
        setDinheiro_vendas(preco);
    }

    public Utilizador clone(){
        return new Utilizador(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Código no Sistema:: " + this.code + "\n");
        sb.append("Email:: " + this.email + "\n");
        sb.append("Nome:: " + this.nome + "\n");
        sb.append("Morada:: " + this.morada + "\n");
        sb.append("NIF:: " + this.nif + "\n");
        sb.append("Artigos Vendidos:: [");
        for(Artigo c : this.vendido){
            sb.append(c + "\n");
        }
        sb.append("]\n");
        sb.append("Artigos à Venda:: [");
        for(Artigo c : this.para_vender){
            sb.append(c + "\n");
        }
        sb.append("]\n");
        sb.append("Dinheiro Feito em Vendas:: " + this.dinheiro_vendas + "\n");
        sb.append("Dinheiro Gasto em Compras:: " + this.dinheiro_compras + "\n");

        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Utilizador l = (Utilizador) o;
        return  this.code == l.getCode() &&
                this.email.equals(l.getEmail()) &&
                this.nome.equals(l.getNome()) &&
                this.morada.equals(l.getMorada()) &&
                this.nif == l.getNif() &&
                Double.compare(this.dinheiro_compras, l.getDinheiro_compras()) == 0 &&
                Double.compare(this.dinheiro_vendas, l.getDinheiro_vendas()) == 0 &&
                SetDeepClone.isDeepClone(this.para_vender, l.getPara_vender()) &&
                SetDeepClone.isDeepClone(this.vendido, l.getVendido());

    }
}
