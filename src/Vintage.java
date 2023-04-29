import UserExceptions.ArtigoDoesntExistException;
import UserExceptions.EncomendaDoesntExistException;
import UserExceptions.TransportadoraDoesntExistException;
import UserExceptions.UserDoesntExistException;
import jdk.jshell.execution.Util;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Vintage implements Serializable {
    private Map<Integer,Encomenda> encomendas;
    private Map<Integer,String> encomendas_utilizadores_ligacao;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Transportadora> transportadoras;
    private Map<String,String> artigos_utilizadores_ligacao;
    private Map<String,Artigo> artigos;
    private Map<String,Artigo> artigos_vendidos;


    public Vintage(){
        this.encomendas = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.transportadoras = new HashMap<>();
        this.artigos = new HashMap<>();
        this.artigos_utilizadores_ligacao = new HashMap<>();
        this.encomendas_utilizadores_ligacao = new HashMap<>();
        this.artigos_vendidos = new HashMap<>();
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

    public Map<String, Artigo> getArtigos_vendidos() {
        Map<String,Artigo> novo = new HashMap<>();

        for(Map.Entry<String, Artigo> c : this.artigos_vendidos.entrySet()){
            String aux = c.getKey();
            Artigo use = c.getValue().clone();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setArtigos_vendidos(Map<String, Artigo> artigos_vendidos) {
        this.artigos = new HashMap<String,Artigo>();
        for(Map.Entry<String, Artigo> c : artigos_vendidos.entrySet()){
            String aux = c.getKey();
            Artigo use = c.getValue().clone();
            this.artigos_vendidos.put(aux,use);
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

    public Artigo findArtigo(String codigo) throws ArtigoDoesntExistException {
        Artigo ret = this.artigos.get(codigo);
        if(ret == null) throw new ArtigoDoesntExistException(codigo);
        return ret.clone();
    }

    public Map<Integer, String> getEncomendas_utilizadores_ligacao() {
        Map<Integer,String> novo = new HashMap<>();

        for(Map.Entry<Integer, String> c : this.encomendas_utilizadores_ligacao.entrySet()){
            Integer aux = c.getKey();
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

    public Map<Integer,Encomenda> getEncomendas() {
        Map<Integer,Encomenda> novo = new HashMap<>();
        for(Map.Entry<Integer,Encomenda> c : this.encomendas.entrySet()){
            int aux = c.getKey();
            Encomenda use = (Encomenda) c.getValue();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setEncomendas(Map<Integer,Encomenda> encomendas) {
        this.encomendas = new HashMap<>();
        for(Map.Entry<Integer,Encomenda> c : encomendas.entrySet()){
            int aux = c.getKey();
            Encomenda use = (Encomenda) c.getValue();
            this.encomendas.put(aux,use);
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

    public Transportadora getTransportadoraEspecifico(String nome) throws TransportadoraDoesntExistException {
        Transportadora ret = this.transportadoras.get(nome);
        if(ret == null) throw new TransportadoraDoesntExistException(nome);
        return ret.clone();
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

    public List<Transportadora> getListaTransportadoras(){
        return this.transportadoras.values().stream()
                .collect(Collectors.toList());
    }

    public void addTransportadora(Transportadora a){
        this.transportadoras.put(a.getNome(), a.clone());
    }


    public void addArigoVenda (String email, Artigo artigo) throws UserDoesntExistException {
        Utilizador utilizador = utilizadores.get(email);
        if(utilizador == null) throw new UserDoesntExistException(email);

        utilizador.addArtigoParaVender(artigo.getCodAlfaNum());
        artigos_utilizadores_ligacao.put(artigo.getCodAlfaNum(),email);
        artigos.put(artigo.getCodAlfaNum(),artigo);
    }

    public void addEncomenda(String email,Encomenda encomenda) throws UserDoesntExistException, ArtigoDoesntExistException{
        Utilizador utilizador = utilizadores.get(email);
        if(utilizador == null) throw new UserDoesntExistException(email);

        encomendas_utilizadores_ligacao.put(encomenda.getCodigo(),email); // isto está estupido, encomendas não é um map
        encomendas.put(encomenda.getCodigo(),encomenda);

        Set<String> artigos = encomenda.getArtigos();
        for(String c : artigos){
            Artigo aux = this.artigos.get(c);
            if(aux == null) throw new ArtigoDoesntExistException(c);
            String user = this.artigos_utilizadores_ligacao.get(c);
            if(user == null) throw new ArtigoDoesntExistException(c);
            Utilizador utilizador1 = this.utilizadores.get(user);
            if(utilizador1 == null) throw new UserDoesntExistException(user);
            utilizador1.addArtigoVendido(aux);
            this.artigos_vendidos.put(c,aux);
            utilizador1.removeArtigoParaVender(c);
            utilizador1.setDinheiro_vendas(utilizador1.getDinheiro_vendas() + aux.getPreco_curr());
            utilizador.setDinheiro_compras(utilizador.getDinheiro_compras() + aux.getPreco_curr());
            this.artigos.remove(c);
        }
        utilizador.addEncomenda(encomenda.getCodigo());
    }

    public void addArigoVendido (String email, Artigo artigo){
        //este get é do map!!!
        Utilizador utilizador = utilizadores.get(email);
        utilizador.addArtigoVendido(artigo.clone());
        this.artigos_vendidos.put(artigo.getCodAlfaNum(), artigo.clone());
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
            Map<String, Artigo> vendidos = use.getVendido();
            lines.add(use.toLog());


            for(String art : para_venda){
                Artigo a = artigos.get(art);
                lines.add(a.toLogVender());
            }
            for(Artigo vend : vendidos.values()){
                lines.add(vend.toLogVendidos());
            }

            for(Integer enc : use.getEncomendas()){
                lines.add(this.encomendas.get(enc).toLog());
            }

        }
        return lines;
    }

    public List<Artigo> getListaArtigos(){
        return this.artigos.values().stream()
                .collect(Collectors.toList());
    }

    /**
     * Serve para obter uma lista com todos os artigos presentes numa encomenda.
     * @param set de codigos alfanumericos de artigos existente numa encomenda.
     * @return lista de artigos.
     */
    public List<Artigo> getListaArtigos(Set<String> set){
        List<Artigo> novo = new ArrayList<>();
        for(String c : set){
            novo.add(this.artigos.get(c));
        }
        return novo;
    }

    public String getUserFromEncomenda(int cod) throws EncomendaDoesntExistException{
        String nome = this.encomendas_utilizadores_ligacao.get(cod);
        if(nome == null) throw new EncomendaDoesntExistException(Integer.toString(cod));
        return nome;
    }

    public void devolveEncomenda(int num_encomenda, String email) throws UserDoesntExistException, EncomendaDoesntExistException{

        Utilizador utilizador = utilizadores.get(email);
        if(utilizador == null) throw new UserDoesntExistException(email);

        encomendas_utilizadores_ligacao.remove(num_encomenda);

        Encomenda encomenda = this.encomendas.get(num_encomenda);
        if(encomenda == null) throw new EncomendaDoesntExistException(Integer.toString(num_encomenda));

        //if(encomenda.isRefundable()==false) return false;

        //else {
            for (String codigo : encomenda.getArtigos()) {
                Artigo artigo = this.artigos_vendidos.get(codigo).clone();
                String user = this.artigos_utilizadores_ligacao.get(artigo.getCodAlfaNum());
                Utilizador utilizador1 = this.utilizadores.get(user);
                utilizador1.removeArtigoVendido(artigo.getCodAlfaNum());
                utilizador1.addArtigoParaVender(artigo.getCodAlfaNum());
                utilizador1.setDinheiro_vendas(utilizador1.getDinheiro_vendas() - artigo.getPreco_curr());
                utilizador.setDinheiro_compras(utilizador.getDinheiro_compras() - artigo.getPreco_curr());
                this.artigos_vendidos.remove(artigo.getCodAlfaNum());
                this.artigos.put(artigo.getCodAlfaNum(), artigo);
            //}

            this.encomendas.remove(num_encomenda);
            utilizador.removeEncomenda(num_encomenda);
            //return true;
        }
    }

    public void atualizaEncomendas(LocalDateTime data,int nrHoras){
        for(Encomenda c : this.encomendas.values()){
            LocalDateTime aux = c.getData_inicial();
            if(aux.plusHours(nrHoras).isAfter(data)){
                c.setEstado(Encomenda.State.Expedida);
            }
        }
    }

    //public Transportadora transportadoraMaiorVolFact(){
    //
    //}

}

