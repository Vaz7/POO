import jdk.jshell.execution.Util;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class Vintage {
    private Set<Encomenda> encomendas;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Transportadora> transportadoras;

    private Map<String,String> artigos_utilizadores_ligacao;

    private Map<String,Artigo> artigos;
    public Vintage(){
        this.encomendas = new HashSet<>();
        this.utilizadores = new HashMap<>();
        this.transportadoras = new HashMap<>();
        this.artigos = new HashMap<>();
        this.artigos_utilizadores_ligacao = new HashMap<>();
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

    public Set<Encomenda> getEncomendas() {
        Set<Encomenda> novo = new HashSet<>();
        for(Encomenda c : this.encomendas){
            novo.add(c.clone());
        }
        return novo;
    }

    public void setEncomendas(Set<Encomenda> encomendas) {
        this.encomendas = new HashSet<>();
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

    public boolean userExists(String nome){
        if(this.utilizadores.containsKey(nome)) return true;
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

    public static boolean isDeepCloneSet(Set<Encomenda> set1, Set<Encomenda> set2) {
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

    public void dumpToFile() {
        try {
            FileWriter writer = new FileWriter("userDump.txt");

            // Loop through the map and write each entry to the file
            for (Map.Entry<String, Utilizador> entry : utilizadores.entrySet()) {
                String key = entry.getKey();
                Utilizador value = entry.getValue();

                // Write the key and value to the file
                writer.write(value.getEmail() +", " + value.getNome() + ", " + value.getNif() + "\n");
                // You can modify the format or content of the output as needed
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error occurred while writing to file: " + e.getMessage());
        }
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
        return isDeepCloneSet(this.encomendas, c.getEncomendas()) &&
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


    void addArigoVenda (String email, Artigo artigo){

        //este get é do map!!!
        Utilizador utilizador = utilizadores.get(email);

        utilizador.addArtigoParaVender(artigo.getCodAlfaNum());
        artigos_utilizadores_ligacao.put(email,artigo.getCodAlfaNum());
        artigos.put(artigo.getCodAlfaNum(),artigo);
    }

}


