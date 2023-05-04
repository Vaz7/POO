import UserExceptions.*;
import jdk.jshell.execution.Util;

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

    public Utilizador getUserEspecifico(String mail) throws UserDoesntExistException{
        if(!this.utilizadores.containsKey(mail))
            throw new UserDoesntExistException(mail);
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

    public Set<Transportadora> getListaTransportadoras(boolean prem){
        Set<Transportadora> novo;
        if(prem == true){
            novo = this.transportadoras.values()
                    .stream()
                    .filter(t -> t instanceof Premium)
                    .collect(Collectors.toSet());
        }
        else{
            novo = this.transportadoras.values()
                    .stream()
                    .filter(t -> !(t instanceof Premium))
                    .collect(Collectors.toSet());
        }
        return novo;
    }

    public void addTransportadora(Transportadora a){
        this.transportadoras.put(a.getNome(), a);
    }


    public void addArigoVenda (String email, Artigo artigo) throws UserDoesntExistException {
        Utilizador utilizador = utilizadores.get(email);
        if(utilizador == null) throw new UserDoesntExistException(email);

        utilizador.addArtigoParaVender(artigo.getCodAlfaNum());
        artigos_utilizadores_ligacao.put(artigo.getCodAlfaNum(),email);
        artigos.put(artigo.getCodAlfaNum(),artigo);
    }

    public void addEncomenda(String email,Encomenda encomenda) throws UserDoesntExistException, ArtigoDoesntExistException, TransportadoraDoesntExistException {
        Utilizador utilizador = utilizadores.get(email);
        if(utilizador == null) throw new UserDoesntExistException(email);

        encomendas_utilizadores_ligacao.put(encomenda.getCodigo(),email); // isto está estupido, encomendas não é um map
        encomendas.put(encomenda.getCodigo(),encomenda);

        Set<Artigo> artigos = encomenda.getArtigos();
        for(Artigo c : artigos){
            String codalfa = c.getCodAlfaNum();
            Artigo aux = this.artigos.get(codalfa);
            if(aux == null) throw new ArtigoDoesntExistException(codalfa);
            String user = this.artigos_utilizadores_ligacao.get(codalfa);
            if(user == null) throw new ArtigoDoesntExistException(codalfa);
            Utilizador utilizador1 = this.utilizadores.get(user);
            if(utilizador1 == null) throw new UserDoesntExistException(user);
            utilizador1.addArtigoVendido(aux);
            this.artigos_vendidos.put(codalfa,aux);
            utilizador1.removeArtigoParaVender(codalfa);
            utilizador1.setDinheiro_vendas(utilizador1.getDinheiro_vendas() + aux.getPreco_curr());
            utilizador.setDinheiro_compras(utilizador.getDinheiro_compras() + aux.getPreco_curr());
            this.artigos.remove(codalfa);
            utilizador1.addEncomendaVendida(encomenda);
        }
        try{
            atualizaTransportadoras(encomenda);
        } catch (TransportadoraDoesntExistException e){
            e.getMessage();
        }
        utilizador.addEncomendaComprada(encomenda);
    }

    public void atualizaTransportadoras(Encomenda a) throws TransportadoraDoesntExistException{
        Map<Transportadora, Integer> aux = a.getContador();
        for(Map.Entry<Transportadora, Integer> c : aux.entrySet()){
            Transportadora trans = c.getKey();
            int valor = c.getValue();
            Transportadora original = this.transportadoras.get(trans.getNome());
            if(original == null) throw new TransportadoraDoesntExistException(trans.getNome());
            original.addLucro(valor);
        }
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

            for(Encomenda enc : use.getEncomendasCompradas()){
                lines.add(enc.toLogC());
            }

            for(Encomenda enc : use.getEncomendas_vendidas()){
                lines.add(enc.toLogV());
            }



        }
        return lines;
    }


    /**
     * Obtem a lista com todos os artigos sem incluir os que dado utilizador tem à venda.
     * @param email Utilizador que está logged atualmente na app.
     * @return lista de artigos
     */
    public Set<Artigo> getListaArtigos(String email){
        return this.artigos.values().stream()
                .filter(t -> !this.artigos_utilizadores_ligacao.get(t.getCodAlfaNum()).equals(email))
                .collect(Collectors.toSet());
    }

    /**
     * Serve para obter um Set com todos os artigos presentes numa encomenda.
     * @param set de codigos alfanumericos de artigos existente numa encomenda.
     * @return Set de artigos.
     */
    public Set<Artigo> getListaArtigos(Set<String> set){
        Set<Artigo> novo = new HashSet<>();
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

    public void devolveEncomenda(int num_encomenda, String email, LocalDateTime data) throws UserDoesntExistException, EncomendaDoesntExistException, EncomendaNotRefundableException{

        Utilizador utilizador = utilizadores.get(email);
        if(utilizador == null) throw new UserDoesntExistException(email);

        encomendas_utilizadores_ligacao.remove(num_encomenda);

        Encomenda encomenda = this.encomendas.get(num_encomenda);
        if(encomenda == null) throw new EncomendaDoesntExistException(Integer.toString(num_encomenda));

        if(encomenda.isRefundable(data)==false) throw new EncomendaNotRefundableException(Integer.toString(num_encomenda));

        for (Artigo art : encomenda.getArtigos()) {
            String cod = art.getCodAlfaNum();
            Artigo artigo = this.artigos_vendidos.get(cod);
            String user = this.artigos_utilizadores_ligacao.get(cod);
            Utilizador utilizador1 = this.utilizadores.get(user);
            utilizador1.removeArtigoVendido(artigo.getCodAlfaNum());
            utilizador1.addArtigoParaVender(artigo.getCodAlfaNum());
            utilizador1.setDinheiro_vendas(utilizador1.getDinheiro_vendas() - artigo.getPreco_curr());
            utilizador.setDinheiro_compras(utilizador.getDinheiro_compras() - artigo.getPreco_curr());
            this.artigos_vendidos.remove(artigo.getCodAlfaNum());
            this.artigos.put(artigo.getCodAlfaNum(), artigo);

            this.encomendas.remove(num_encomenda);
            utilizador.removeEncomenda(encomenda);
        }
    }

    public Map<Integer,String> atualizaEncomendas(LocalDateTime data,int nrHoras){
        Map<Integer,String> recibos = new HashMap<>();
        for(Encomenda c : this.encomendas.values()){
            LocalDateTime aux = c.getData_inicial();
            if(aux.plusHours(nrHoras).isAfter(data.plusHours(2)) && !c.getEstado().equals(Encomenda.State.valueOf("Expedida"))){
                c.setEstado(Encomenda.State.Expedida);
            }
            if(data.isBefore(aux.plusHours(48)) || data.equals(aux.plusHours(48))){
                recibos.put(c.getCodigo(), toRecibo(c));
            }
        }
        return recibos;
    }

    public String toRecibo(Encomenda c){
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador:" + this.encomendas_utilizadores_ligacao.get(c.getCodigo()) + "\n");
        sb.append("Número da Encomenda:" + c.getCodigo() + "\n");
        sb.append("Dimensão da Embalagem: " + c.getDim() + "\n");
        sb.append("Data de Compra: " + c.getData_inicial() + "\n");
        sb.append("Artigos: \n");
        for(Artigo b : c.getArtigos()){
            sb.append(b.toString() +"\n");
        }
        sb.append("Preço Final (c/IVA): " + (c.getPrecoArtigos()+c.getPreco_transporte()));
        return sb.toString();
    }


    //1ª estatistica
    public Map<String, List<Encomenda>> getEncomendasPeriodo(LocalDate start, LocalDate end){
        LocalDateTime inicio = start.atStartOfDay().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fim = end.atStartOfDay().withHour(23).withMinute(59).withSecond(59);
        Map<String, List<Encomenda>> aux = new HashMap<>();
        for(Utilizador c : this.utilizadores.values()){
            List<Encomenda> lista = new ArrayList<>();
            for(Encomenda d : c.getEncomendas_vendidas()){
                if((d.getData_inicial().isAfter(inicio) || d.getData_inicial().isEqual(inicio)) && (d.getData_inicial().isBefore(fim) ||  d.getData_inicial().isEqual(fim)))
                    lista.add(d);
            }
            aux.put(c.getEmail(),lista);
        }
        return aux;
    }

    //1ª estatistica
    public double calculcaGanhoEncomendaUser(String email, Encomenda enc){
        double preco = 0;
        Set<Artigo> artigos = enc.getArtigos();
        for(Artigo c : artigos){
            if(this.artigos_utilizadores_ligacao.get(c.getCodAlfaNum()).equals(email))
                preco += c.getPreco_curr();
        }
        return preco;
    }

    //1ª estatistica
    public double calculaTotalGanhoUser(String email, List<Encomenda> encomendas) {
        double totalGasto = 0;
        for (Encomenda enc : encomendas) {
            totalGasto += calculcaGanhoEncomendaUser(email, enc);
        }
        return totalGasto;
    }

    public double calculaDinheiroVintage(){
        return this.encomendas.values()
                .stream()
                .map(Encomenda::calculaPrecoSatisfacao)
                .reduce(0.0, (subtotal, element) -> subtotal + element);
    }

    public Transportadora maiorFaturacaoTransportadora(){
        return this.transportadoras.values()
                .stream()
                .max(Comparator.comparingDouble(Transportadora::getDinheiro_feito))
                .orElse(null);

    }

}

