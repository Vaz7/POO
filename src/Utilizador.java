import java.util.*;

public class Utilizador {
    private String username;
    private String email;
    private String nome;
    private String morada;
    private int nif;
    private Set<Artigo> vendido;
    private Map<String,Artigo> para_vender;

    private double dinheiro_vendas;
    private double dinheiro_compras;

    public Utilizador(){
        this.username = "";
        this.email = "";
        this.morada = "";
        this.nif = 0;
        this.vendido = new HashSet<>();
        this.para_vender = new HashMap<>();
        this.dinheiro_compras = 0;
        this.dinheiro_vendas = 0;
    }

    public Utilizador(String username,String email, String nome, String morada, int nif, double dinheiro_vendas, double dinheiro_compras) {
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.vendido = new HashSet<>();
        this.para_vender = new HashMap<>();
        this.dinheiro_vendas = dinheiro_vendas;
        this.dinheiro_compras = dinheiro_compras;
    }
    public Utilizador(String username,String email, String nome, String morada, int nif, Set<Artigo> vendido, Map<String,Artigo> para_vender, double dinheiro_vendas, double dinheiro_compras) {
        this.username = username;
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
        this.username = o.getUsername();
        this.email = o.getEmail();
        this.nome = o.getNome();
        this.morada = o.getMorada();
        this.nif = o.getNif();
        this.vendido = o.getVendido();
        this.para_vender = o.getPara_vender();
        this.dinheiro_compras = o.getDinheiro_compras();
        this.dinheiro_vendas = o.getDinheiro_vendas();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Map<String,Artigo> getPara_vender() {
        Map<String,Artigo> novo = new HashMap<>();

        for(Map.Entry<String, Artigo> c : this.para_vender.entrySet()){
            String aux = c.getKey();
            Artigo use = c.getValue().clone();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setPara_vender(Map<String,Artigo> para_vender) {
        this.para_vender = new HashMap<String,Artigo>();
        for(Map.Entry<String, Artigo> c : para_vender.entrySet()){
            String aux = c.getKey();
            Artigo use = c.getValue().clone();
            this.para_vender.put(aux,use);
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
        this.para_vender.put(c.getCodAlfaNum(),c.clone());
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
        sb.append("Username:: " + this.username + "\n");
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
        for(Artigo c : this.para_vender.values()){
            sb.append(c.toString() + "\n");
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
        return  this.username == l.getUsername() &&
                this.email.equals(l.getEmail()) &&
                this.nome.equals(l.getNome()) &&
                this.morada.equals(l.getMorada()) &&
                this.nif == l.getNif() &&
                Double.compare(this.dinheiro_compras, l.getDinheiro_compras()) == 0 &&
                Double.compare(this.dinheiro_vendas, l.getDinheiro_vendas()) == 0 &&
                SetDeepClone.isDeepCloneMap(this.para_vender, l.getPara_vender()) &&
                SetDeepClone.isDeepCloneSet(this.vendido, l.getVendido());

    }
}
