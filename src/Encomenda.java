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
    private Set<String> artigos;
    private Map<Transportadora, Integer> contador;
    private Embalagem dim;
    private double preco;
    private LocalDateTime data_inicial;
    private State estado;

    public Encomenda(){
        this.codigo = this.count++;
        this.artigos = new HashSet<>();
        this.dim = Embalagem.Pequeno;
        this.data_inicial = LocalDateTime.now();
        this.estado = State.Pendente;
        this.preco = 0;
        this.contador = new HashMap<>();
    }

    public Encomenda(Set<String> lista, State estado){
        this.codigo = this.count++;
        setArtigos(lista);
        defDimensaoCaixa();
        this.data_inicial = LocalDateTime.now();
        this.estado = estado;
        //this.preco = calculaPrecoEnc();
    }

    public Encomenda(Set<String> lista, Embalagem dim, LocalDateTime data, State estado,Double preco) {
        setArtigos(lista);
        this.codigo = this.count++;
        this.dim = dim;
        this.data_inicial = data;
        this.estado = estado;
        this.preco = preco;
        //this.preco = calculaPrecoEnc();
    }

    public Encomenda(Encomenda o){
        this.codigo = o.getCodigo();
        this.artigos = o.getArtigos();
        this.dim = o.getDim();
        this.data_inicial = o.getData_inicial();
        this.estado = o.getEstado();
        this.preco = o.getPreco();
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Set<String> getArtigos() {
        Set<String> novo = new HashSet<>();

        for(String c : this.artigos){
            novo.add(c);
        }
        return novo;
    }

    public void setArtigos(Set<String> artigos) {
        this.artigos = new HashSet<>();
        for(String c : artigos){
            this.artigos.add(c);
        }
    }

    public Embalagem getDim() {
        return this.dim;
    }

    public void setDim(Embalagem dim) {
        this.dim = dim;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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

    public String toLog(){
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda:" + this.codigo);
        sb.append("," + this.dim );
        sb.append("," + this.data_inicial);
        sb.append("," + this.estado);
        sb.append("," + this.preco);

        for(String c : this.artigos){
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
        for(String c : this.artigos){
            sb.append(c.toString() + ", ");
        }
        sb.append(" Preço: " + this.preco);
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Encomenda l = (Encomenda) o;
        return  this.dim == l.getDim() &&
                this.estado == l.getEstado() &&
                this.data_inicial.equals(l.getData_inicial()) &&
                Double.compare(this.preco, l.getPreco()) == 0 &&
                this.artigos.equals(l.getArtigos());
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
        double preco = this.preco;
        this.artigos.add(c.getCodAlfaNum());
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
        setPreco(preco);
    }

    public boolean contem (Artigo artigo){
        return this.artigos.contains(artigo);
    }

    public void removeArtEncomenda(Artigo c){
        double preco = this.preco;
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

        this.artigos.remove(c.getCodAlfaNum());
        defDimensaoCaixa();
        setPreco(preco);
    }

    public boolean isRefundable(){
        if(this.data_inicial.plusDays(2).isBefore(this.data_inicial)) return true;
        return false;
    }


    public double calculaPrecoFinal() {
        double preco_transporte = 0;
        if(this.artigos.size() != 0){
            for(Map.Entry<Transportadora, Integer> c : this.contador.entrySet()){
                Transportadora transp = c.getKey();
                Integer num = c.getValue();
                preco_transporte += transp.precoTransporte(num);
            }
            preco_transporte += this.preco;
            if(this.dim == Embalagem.Pequeno)
                preco_transporte += 0.29;
            else if(this.dim == Embalagem.Medio)
                preco_transporte += 0.79;
            else preco_transporte += 1.29;
        }

        return preco_transporte;
    }

    public void atualizaEncomenda(){
        defDimensaoCaixa();
        this.estado = State.Finalizada;
        setPreco(calculaPrecoFinal());
        setData_inicial(LocalDateTime.now());
    }

    public void showPrecoAtual(){
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Preço atual: "+ df.format(calculaPrecoFinal()));
    }

}
