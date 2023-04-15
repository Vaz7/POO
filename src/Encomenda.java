import java.time.LocalDate;
import java.util.*;

public class Encomenda {
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
    private Map<String, Artigo> artigos;
    private Embalagem dim;
    private double preco;
    private LocalDate data;
    private State estado;

    public Encomenda(){
        this.artigos = new HashMap<>();
        this.dim = Embalagem.Pequeno;
        this.data = LocalDate.now();
        this.estado = State.Pendente;
        this.preco = 0;
    }

    public Encomenda(Map<String,Artigo> lista, State estado){
        setArtigos(lista);
        defDimensaoCaixa();
        this.data = LocalDate.now();
        this.estado = estado;
        //this.preco = calculaPrecoEnc();
    }

    public Encomenda(Map<String,Artigo> lista, Embalagem dim, LocalDate data, State estado) {
        setArtigos(lista);
        this.dim = dim;
        this.data = data;
        this.estado = estado;
        //this.preco = calculaPrecoEnc();
    }

    public Encomenda(Encomenda o){
        this.artigos = o.getArtigos();
        this.dim = o.getDim();
        this.data = o.getData();
        this.estado = o.getEstado();
        this.preco = o.getPreco();
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

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public State getEstado() {
        return this.estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
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
        this.artigos.put(c.getCodAlfaNum(),c.clone());
        preco += c.getPreco_curr();
        if(c.isNovo()){
            preco += 0.5;
        }
        else preco += 0.25;
        setPreco(preco);
    }

    public void removeArtEncomenda(Artigo c){
        double preco = this.preco;
        preco -= c.getPreco_curr();
        if(c.isNovo()) preco -= 0.5;
        else preco -= 0.25;
        this.artigos.remove(c.getCodAlfaNum());
        setPreco(preco);
    }

    public boolean isRefundable(){
        if(this.data.plusDays(2).isBefore(this.data)) return true;
        return false;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda :: \n");
        sb.append("Embalagem: " + this.dim + "\n");
        sb.append("Data de Criação: " + this.data + "\n");
        sb.append("Estado : " + this.estado + "\n");
        sb.append("Artigos: \n");
        for(Artigo c : this.artigos.values()){
            sb.append(c.toString() + "\n");
        }
        sb.append("Preço: " + this.preco + "\n");
        return sb.toString();
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

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Encomenda l = (Encomenda) o;
        return  this.dim == l.getDim() &&
                this.estado == l.getEstado() &&
                this.data.equals(l.getData()) &&
                Double.compare(this.preco, l.getPreco()) == 0 &&
                isDeepCloneMap(this.artigos, l.getArtigos());

    }
}
