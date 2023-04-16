

import java.util.*;

public class Utilizador {
    private static int contador=1;
    private int code;
    private String email;
    private String nome;
    private String morada;
    private int nif;
    private Set<Artigo> vendido;
    private Map<String,Artigo> para_vender;

    private double dinheiro_vendas;
    private double dinheiro_compras;

    public Utilizador(){
        this.code = this.contador++;
        this.email = "";
        this.morada = "";
        this.nome = "";
        this.nif = 0;
        this.vendido = new HashSet<>();
        this.para_vender = new HashMap<>();
        this.dinheiro_compras = 0;
        this.dinheiro_vendas = 0;
    }

    public Utilizador(String email, String nome, String morada, int nif, double dinheiro_vendas, double dinheiro_compras) {
        this.code = this.contador++;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.vendido = new HashSet<>();
        this.para_vender = new HashMap<>();
        this.dinheiro_vendas = dinheiro_vendas;
        this.dinheiro_compras = dinheiro_compras;
    }
    public Utilizador(String email, String nome, String morada, int nif, Set<Artigo> vendido, Map<String,Artigo> para_vender, double dinheiro_vendas, double dinheiro_compras) {
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

    public static boolean isDeepCloneSet(Set<Artigo> set1, Set<Artigo> set2) {
        if (set1 == null || set2 == null) {
            return set1 == set2;
        }

        if (set1.size() != set2.size()) {
            return false;
        }
        Iterator<Artigo> it = set1.iterator();
        while (it.hasNext()) {
            Artigo obj1 = it.next();
            boolean found = false;
            for (Artigo obj2 : set2) {
                if (obj2.equals(obj1)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDeepCloneMap(Map<String, Artigo> map1, Map<String, Artigo> map2) {

        if (map1 == null || map2 == null) {
            return map1 == map2;
        }

        if (map1.size() != map2.size()) {
            return false;
        }

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                return false;
            }

            Artigo obj1 = map1.get(key);
            Artigo obj2 = map2.get(key);

            if (!obj1.equals(obj2)) {
                return false;
            }
        }

        return true;
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
        return  this.code == l.getCode() &&
                this.email.equals(l.getEmail()) &&
                this.nome.equals(l.getNome()) &&
                this.morada.equals(l.getMorada()) &&
                this.nif == l.getNif() &&
                Double.compare(this.dinheiro_compras, l.getDinheiro_compras()) == 0 &&
                Double.compare(this.dinheiro_vendas, l.getDinheiro_vendas()) == 0 &&
                isDeepCloneMap(this.para_vender, l.getPara_vender()) &&
                isDeepCloneSet(this.vendido, l.getVendido());

    }
}
