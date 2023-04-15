import jdk.jshell.execution.Util;

import java.util.*;

public class Vintage {
    private Set<Encomenda> encomendas;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Transportadora> transportadoras;

    public Vintage(){
        this.encomendas = new HashSet<>();
        this.utilizadores = new HashMap<>();
        this.transportadoras = new HashMap<>();
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
}
