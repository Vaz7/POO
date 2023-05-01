import UserExceptions.TransportadoraDoesntExistException;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Encomenda implements Serializable {

    private static int count=1;
    private int codigo;
    public enum Embalagem{
        Pequeno,
        Medio,
        Grande
    }
    public enum State{
        Pendente,
        Finalizada,
        Expedida
    }
    private Set<Artigo> artigos; // agregação com o artigo
    private Map<Transportadora, Integer> contador;
    private Embalagem dim;
    private double preco_artigos;
    private double preco_transporte;
    private LocalDateTime data_inicial;
    private State estado;

    public Encomenda(){
        this.codigo = this.count++;
        this.artigos = new HashSet<>();
        this.dim = Embalagem.Pequeno;
        this.data_inicial = LocalDateTime.now();
        this.estado = State.Pendente;
        this.preco_artigos = 0;
        this.preco_transporte = 0;
        this.contador = new HashMap<>();
    }

    public Encomenda(Set<Artigo> lista, State estado){
        this.codigo = this.count++;
        setArtigos(lista);
        defDimensaoCaixa();
        this.data_inicial = LocalDateTime.now();
        this.estado = estado;
        this.preco_artigos = calculaPrecoArtigos();
        this.preco_transporte = calculaPrecoTransporte();
        atualizaEncomenda();
    }

    public Encomenda(Set<Artigo> lista, Embalagem dim, LocalDateTime data, State estado,Double preco) {
        setArtigos(lista);
        this.codigo = this.count++;
        this.dim = dim;
        this.data_inicial = data;
        this.estado = estado;
        this.preco_artigos = preco;
        this.preco_transporte = calculaPrecoTransporte();
        atualizaEncomenda();
    }

    public Encomenda(Encomenda o){
        this.codigo = o.getCodigo();
        this.artigos = o.getArtigos();
        this.dim = o.getDim();
        this.data_inicial = o.getData_inicial();
        this.estado = o.getEstado();
        this.preco_artigos = o.getPrecoArtigos();
        this.preco_transporte = o.getPreco_transporte();
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Encomenda.count = count;
    }

    public Map<Transportadora, Integer> getContador() {
        Map<Transportadora, Integer> novo = new HashMap<>();
        for(Map.Entry<Transportadora, Integer> c : this.contador.entrySet()){
            Transportadora clone = c.getKey().clone();
            Integer aux = c.getValue();
            novo.put(clone, aux);
        }
        return novo;
    }

    public void setContador(Map<Transportadora, Integer> contador) {
        this.contador = new HashMap<>();
        for(Map.Entry<Transportadora, Integer> c : contador.entrySet()){
            Transportadora clone = c.getKey().clone();
            Integer aux = c.getValue();
            this.contador.put(clone, aux);
        }
    }

    public double getPreco_transporte() {
        return preco_transporte;
    }

    public void setPreco_transporte(double preco_transporte) {
        this.preco_transporte = preco_transporte;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Set<Artigo> getArtigos() {
        Set<Artigo> novo = new HashSet<>();

        for(Artigo c : this.artigos){
            novo.add(c.clone());
        }
        return novo;
    }

    public void setArtigos(Set<Artigo> artigos) {
        this.artigos = new HashSet<>();
        for(Artigo c : artigos){
            this.artigos.add(c.clone());
        }
    }

    public Embalagem getDim() {
        return this.dim;
    }

    public void setDim(Embalagem dim) {
        this.dim = dim;
    }

    public double getPrecoArtigos() {
        return this.preco_artigos;
    }

    public void setPrecoArtigos(double preco) {
        this.preco_artigos = preco;
    }

    public LocalDateTime getData_inicial() {
        return this.data_inicial;
    }

    public void setData_inicial(LocalDateTime data_inicial) {
        this.data_inicial = data_inicial;
    }

    public State getEstado() {
        return this.estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public String toLogC(){
        StringBuilder sb = new StringBuilder();
        sb.append("EncomendaComprada:" + this.codigo);
        sb.append("," + this.dim );
        sb.append("," + this.data_inicial);
        sb.append("," + this.estado);
        sb.append("," + (this.preco_artigos + this.preco_transporte));

        for(Artigo c: this.artigos){
            sb.append("," + c.toString());
        }
        return sb.toString();
    }

    public String toLogV(){
        StringBuilder sb = new StringBuilder();
        sb.append("EncomendaVendida:" + this.codigo);
        sb.append("," + this.dim );
        sb.append("," + this.data_inicial);
        sb.append("," + this.estado);
        sb.append("," + (this.preco_artigos + this.preco_transporte));

        for(Artigo c: this.artigos){
            sb.append("," + c.toString());
        }
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda:" + this.codigo + ",");
        sb.append(" Embalagem: " + this.dim + ",");
        sb.append(" Data de Criação: " + this.data_inicial + ",");
        sb.append(" Estado : " + this.estado + ",");
        sb.append(" Artigos: ");
        for(Artigo c : this.artigos){
            sb.append(c.toString() + "\n");
        }
        sb.append(" Preço: " + (this.preco_artigos + this.preco_transporte));
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Encomenda l = (Encomenda) o;
        return  this.dim == l.getDim() &&
                this.estado == l.getEstado() &&
                this.data_inicial.equals(l.getData_inicial()) &&
                Double.compare(this.preco_artigos, l.getPrecoArtigos()) == 0 &&
                this.artigos.equals(l.getArtigos()) &&
                Double.compare(this.preco_transporte, l.getPreco_transporte()) == 0;
    }

    private void defDimensaoCaixa(){
        int n = this.artigos.size();
        Embalagem j;
        if(n>= 5) j = Embalagem.Grande;
        else if(n > 1 && n<5) j = Embalagem.Medio;
        else j = Embalagem.Pequeno;
        setDim(j);
    }

    public void addArtEncomenda(Artigo c){
        double preco = this.preco_artigos;
        this.artigos.add(c);
        Transportadora aux = c.getTransp();

        if (this.contador.containsKey(aux)) {
            int contagem = this.contador.get(aux);
            this.contador.put(aux, contagem + 1);
        }
        else {
            this.contador.put(aux, 1);
        }

        preco += c.getPreco_curr();
        if(c.isNovo()){
            preco += 0.5;
        }
        else preco += 0.25;
        defDimensaoCaixa();
        setPrecoArtigos(preco);
        setPreco_transporte(calculaPrecoTransporte());
    }

    public boolean contem (Artigo artigo){
        return this.artigos.contains(artigo);
    }

    public void removeArtEncomenda(Artigo c){
        double preco = this.preco_artigos;
        preco -= c.getPreco_curr();
        if(c.isNovo()) preco -= 0.5;
        else preco -= 0.25;

        int contagem = this.contador.get(c.getTransp());
        if(contagem == 1){
            this.contador.remove(c.getTransp());
        }
        else{
            this.contador.put(c.getTransp(), contagem - 1);
        }

        this.artigos.remove(c);
        defDimensaoCaixa();
        setPrecoArtigos(preco);
        setPreco_transporte(calculaPrecoTransporte());
    }

    public double calculaPrecoTransporte() {
        double preco_transporte = 0;
        if(this.artigos.size() != 0){
            for(Map.Entry<Transportadora, Integer> c : this.contador.entrySet()){
                Transportadora transp = c.getKey();
                Integer num = c.getValue();
                preco_transporte += transp.precoTransporte(num);
            }
            if(this.dim == Embalagem.Pequeno)
                preco_transporte += 0.29;
            else if(this.dim == Embalagem.Medio)
                preco_transporte += 0.79;
            else preco_transporte += 1.29;
        }

        return preco_transporte;
    }

    public double calculaPrecoArtigos(){
        double preco = 0;
        for(Artigo c : this.artigos){
            if(c.isNovo()) preco += 0.5;
            else preco += 0.25;
            preco += c.getPreco_curr();
        }
        return preco;
    }

    public boolean isRefundable(LocalDateTime data){
        if(data.isAfter(this.data_inicial.plusHours(50))) return false;
        return true;
    }

    public void atualizaEncomenda(){
        defDimensaoCaixa();
        this.estado = State.Finalizada;
        setData_inicial(LocalDateTime.now());
    }

    public int getTranspOccur(Transportadora a) throws TransportadoraDoesntExistException {
        int b = 0;
        System.out.println(this.contador);
        if(this.contador.containsKey(a)) {
            b = this.contador.get(a);
        } else {
            throw new TransportadoraDoesntExistException(a.getNome());
        }
        return b;
    }

    public void showPrecoAtual(){
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Preço atual: "+ df.format(calculaPrecoTransporte() + + this.preco_artigos));
    }

    public double calculaPrecoSatisfacao(){
        return this.artigos.stream()
                .map(enc -> enc.isNovo())
                .map(novo -> {
                    if(novo == false)
                        return 0.25;
                    else return 0.5;
                })
                .reduce(0.0,(subtotal, element) -> subtotal + element);
    }


}
