import jdk.jshell.execution.Util;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Serializable;
import java.util.*;

public class Vintage implements Serializable {
    private List<Encomenda> encomendas;
    private Map<String,String> encomendas_utilizadores_ligacao;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Transportadora> transportadoras;
    private Map<String,String> artigos_utilizadores_ligacao;
    private Map<String,Artigo> artigos;


    public Vintage(){
        this.encomendas = new ArrayList<>();
        this.utilizadores = new HashMap<>();
        this.transportadoras = new HashMap<>();
        this.artigos = new HashMap<>();
        this.artigos_utilizadores_ligacao = new HashMap<>();
        this.encomendas_utilizadores_ligacao = new HashMap<>();
    }

    public Map<String, Artigo> getArtigos() {
        Map<String,Artigo> novo = new HashMap<>();

        for(Map.Entry<String, Artigo> c : this.artigos.entrySet()){
            String aux = c.getKey();
            Artigo use = c.getValue().clone();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setArtigos(Map<String, Artigo> artigos) {
        this.artigos = new HashMap<String,Artigo>();
        for(Map.Entry<String, Artigo> c : artigos.entrySet()){
            String aux = c.getKey();
            Artigo use = c.getValue().clone();
            this.artigos.put(aux,use);
        }
    }

    public Map<String, String> getArtigos_utilizadores_ligacao() {
        Map<String,String> novo = new HashMap<>();

        for(Map.Entry<String, String> c : this.artigos_utilizadores_ligacao.entrySet()){
            String aux = c.getKey();
            String use = (String) c.getValue();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setArtigos_utilizadores_ligacao(Map<String, String> artigos_utilizadores_ligacao) {
        this.artigos_utilizadores_ligacao = new HashMap<String,String>();
        for(Map.Entry<String, String> c : artigos_utilizadores_ligacao.entrySet()){
            String aux = c.getKey();
            String use = (String) c.getValue();
            this.artigos_utilizadores_ligacao.put(aux,use);
        }
    }

    public Artigo findArtigo(String codigo){
        return this.artigos.get(codigo).clone();
    }

    public Map<String, String> getEncomendas_utilizadores_ligacao() {
        Map<String,String> novo = new HashMap<>();

        for(Map.Entry<String, String> c : this.artigos_utilizadores_ligacao.entrySet()){
            String aux = c.getKey();
            String use = (String) c.getValue();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setEncomendas_utilizadores_ligacao(Map<String, String> encomendas_utilizadores_ligacao) {
        this.artigos_utilizadores_ligacao = new HashMap<String,String>();
        for(Map.Entry<String, String> c : artigos_utilizadores_ligacao.entrySet()){
            String aux = c.getKey();
            String use = (String) c.getValue();
            this.artigos_utilizadores_ligacao.put(aux,use);
        }
    }

    public List<Encomenda> getEncomendas() {
        List<Encomenda> novo = new ArrayList<>();
        for(Encomenda c : this.encomendas){
            novo.add(c.clone());
        }
        return novo;
    }

    public void setEncomendas(Set<Encomenda> encomendas) {
        this.encomendas = new ArrayList<>();
        for(Encomenda c : encomendas){
            this.encomendas.add(c.clone());
        }
    }

    public Map<String, Utilizador> getUtilizadores() {
        Map<String,Utilizador> novo = new HashMap<>();

        for(Map.Entry<String, Utilizador> c : this.utilizadores.entrySet()){
            String aux = c.getKey();
            Utilizador use = c.getValue().clone();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = new HashMap<String,Utilizador>();
        for(Map.Entry<String, Utilizador> c : utilizadores.entrySet()){
            String aux = c.getKey();
            Utilizador use = c.getValue().clone();
            this.utilizadores.put(aux,use);
        }
    }

    public Map<String, Transportadora> getTransportadoras() {
        Map<String,Transportadora> novo = new HashMap<>();

        for(Map.Entry<String, Transportadora> c : this.transportadoras.entrySet()){
            String aux = c.getKey();
            Transportadora use = c.getValue().clone();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setTransportadoras(Map<String, Transportadora> transportadoras) {
        this.transportadoras = new HashMap<String,Transportadora>();
        for(Map.Entry<String, Transportadora> c : transportadoras.entrySet()){
            String aux = c.getKey();
            Transportadora use = c.getValue().clone();
            this.transportadoras.put(aux,use);
        }
    }

    public boolean userExists(String email){
        if(this.utilizadores.containsKey(email)) return true;
        else return false;
    }

    public Utilizador getUserEspecifico(String mail){
        return this.utilizadores.get(mail).clone();
    }

    public void addUser(Utilizador a){
        this.utilizadores.put(a.getEmail(), a.clone());
    }

    public Transportadora getTransportadoraEspecifico(String nome){
        return this.transportadoras.get(nome).clone();
    }

    public static boolean isDeepCloneList(List<Encomenda> set1, List<Encomenda> set2) {
        if (set1 == null || set2 == null) {
            return set1 == set2;
        }

        if (set1.size() != set2.size()) {
            return false;
        }
        Iterator<Encomenda> it = set1.iterator();
        while (it.hasNext()) {
            Encomenda obj1 = it.next();
            boolean found = false;
            for (Encomenda obj2 : set2) {
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

    public static boolean isDeepCloneMap(Map<String, Utilizador> map1, Map<String, Utilizador> map2) {

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

            Utilizador obj1 = map1.get(key);
            Utilizador obj2 = map2.get(key);

            if (!obj1.equals(obj2)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isDeepCloneMapTransport(Map<String, Transportadora> map1, Map<String, Transportadora> map2) {

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

            Transportadora obj1 = map1.get(key);
            Transportadora obj2 = map2.get(key);

            if (!obj1.equals(obj2)) {
                return false;
            }
        }

        return true;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        Vintage c = (Vintage) o;
        return isDeepCloneList(this.encomendas, c.getEncomendas()) &&
                isDeepCloneMap(c.getUtilizadores(), this.utilizadores) &&
                isDeepCloneMapTransport(this.transportadoras, c.getTransportadoras());
    }

    public void printTransportadoras(){
        for(Transportadora c : this.transportadoras.values()){
            System.out.println(c.toString());
        }
    }

    public void addTransportadora(Transportadora a){
        this.transportadoras.put(a.getNome(), a.clone());
    }


    public void addArigoVenda (String email, Artigo artigo){

        //este get é do map!!!
        Utilizador utilizador = utilizadores.get(email);

        utilizador.addArtigoParaVender(artigo.getCodAlfaNum());
        artigos_utilizadores_ligacao.put(email,artigo.getCodAlfaNum());
        artigos.put(artigo.getCodAlfaNum(),artigo);
    }

    public void addEncomenda(String email,Encomenda encomenda){
        Utilizador utilizador = utilizadores.get(email);
        utilizador.addEncomenda(encomenda.getCodigo());
        encomendas_utilizadores_ligacao.put(email,Integer.toString(encomenda.getCodigo()));
        encomendas.add(encomenda);
    }

    public void addArigoVendido (String email, Artigo artigo){
        //este get é do map!!!
        Utilizador utilizador = utilizadores.get(email);

        utilizador.addArtigoVendido(artigo.getCodAlfaNum());
        artigos_utilizadores_ligacao.put(email,artigo.getCodAlfaNum());
        artigos.put(artigo.getCodAlfaNum(),artigo);
    }

    public List<String> toLog(){
        List<String> lines = new ArrayList<>();

        for (Map.Entry<String, Transportadora> c : this.getTransportadoras().entrySet()) {
            String aux = c.getKey();
            Transportadora use = c.getValue().clone();
            lines.add(use.toLog());
        }

        for(Map.Entry<String,Utilizador> u : this.getUtilizadores().entrySet()){
            String aux = u.getKey();
            Utilizador use = u.getValue().clone();
            Set<String> para_venda = use.getPara_vender();
            Set<String> vendidos = use.getVendido();
            lines.add(use.toLog());


            for(String art : para_venda){
                Artigo a = artigos.get(art);
                lines.add(a.toLogVender());
            }
            for(String vend : vendidos){
                Artigo b = artigos.get(vend);
                lines.add(b.toLogVendidos());
            }

        }


        return lines;
    }
}


