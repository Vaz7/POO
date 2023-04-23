

import java.io.Serializable;
import java.util.*;

public class Utilizador implements Serializable {
    private static int contador=1;
    private int code;
    private String email;
    private String nome;
    private String morada;
    private int nif;
    private Map<String, Artigo> vendido;
    private Set<String> para_vender;

    private double dinheiro_vendas;
    private double dinheiro_compras;

    public Utilizador(){
        this.code = this.contador++;
        this.email = "";
        this.morada = "";
        this.nome = "";
        this.nif = 0;
        this.vendido = new HashMap<>();
        this.para_vender = new HashSet<>();
        this.dinheiro_compras = 0;
        this.dinheiro_vendas = 0;
    }

    public Utilizador(String email, String nome, String morada, int nif) {
        this.code = this.contador++;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.vendido = new HashMap<>();
        this.para_vender = new HashSet<>();
        this.dinheiro_vendas = 0.0;
        this.dinheiro_compras = 0.0;
    }

    public Utilizador(String email, String nome, String morada, int nif, double dinheiro_vendas, double dinheiro_compras) {
        this.code = this.contador++;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.vendido = new HashMap<>();
        this.para_vender = new HashSet<>();
        this.dinheiro_vendas = dinheiro_vendas;
        this.dinheiro_compras = dinheiro_compras;
    }
    public Utilizador(String email, String nome, String morada, int nif, Map<String, Artigo> vendido, Set<String> para_vender, double dinheiro_vendas, double dinheiro_compras) {
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

    public String getEmail(){
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

    public HashMap<String, Artigo> getVendido() {
        HashMap<String, Artigo> novo = new HashMap<>();
        for(Map.Entry<String, Artigo> c : this.vendido.entrySet()){
            String key = c.getKey();
            Artigo clone = c.getValue().clone();
            novo.put(key,clone);
        }
        return novo;
    }

    public void setVendido(Map<String, Artigo> vendido) {
        this.vendido = new HashMap<>();
        for(Map.Entry<String,Artigo> c : vendido.entrySet()){
            String key = c.getKey();
            Artigo clone = c.getValue().clone();
            this.vendido.put(key,clone);
        }
    }

    public Set<String> getPara_vender() {
        Set<String> novo = new HashSet<>();

        for(String c : this.para_vender){
            novo.add(c);
        }
        return novo;
    }

    public void setPara_vender(Set<String> a) {
        this.para_vender = new HashSet<>();
        for(String c : a){
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
        this.vendido.put(c.getCodAlfaNum(), c);
    }

    public void addArtigoParaVender(String c){
        this.para_vender.add(c);
    }

    public void removeArtigoParaVender(String c){
        this.para_vender.remove(c);
    }
    public void removeArtigoVendido(String c){this.vendido.remove(c);}
//    private void calculaDinheiroCompras(){
//        double preco = 0;
//        for(Artigo c : this.vendido){
//            preco += c.getPreco_curr();
//        }
//        setDinheiro_vendas(preco);
//    }

    public static boolean isDeepCloneSet(Set<String> set1, Set<String> set2) {
        if (set1 == null || set2 == null) {
            return set1 == set2;
        }

        if (set1.size() != set2.size()) {
            return false;
        }
        Iterator<String> it = set1.iterator();
        while (it.hasNext()) {
            String obj1 = it.next();
            boolean found = false;
            for (String obj2 : set2) {
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
        for(Artigo c : this.vendido.values()){
            sb.append(c + "\n");
        }
        sb.append("]\n");
        sb.append("Artigos à Venda:: [");
        for(String c : this.para_vender){
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
                isDeepCloneSet(this.para_vender, l.getPara_vender()); //&&
                //isDeepCloneSet(this.vendido, l.getVendido()); falta a função para o map

    }

    public String toLog(){
        return ("Utilizador:" + this.getEmail() + "," + this.getNome() + "," + this.getMorada() + "," + this.getNif()+ "," + this.getDinheiro_vendas() + "," + this.getDinheiro_compras());
    }
}
