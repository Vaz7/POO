import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Artigo> artigos;
    private Embalagem dim;
    private double preco;
    private LocalDate data;
    private State estado;

    public Encomenda(){
        this.artigos = new HashSet<>();
        this.dim = Embalagem.Pequeno;
        this.data = LocalDate.now();
        this.estado = State.Pendente;
        this.preco = 0;
    }

    public Encomenda(Set<Artigo> lista, State estado){
        setArtigos(lista);
        defDimensaoCaixa();
        this.data = LocalDate.now();
        this.estado = estado;
        this.preco = calculaPrecoEnc();
    }

    public Encomenda(Set<Artigo> lista, Embalagem dim, LocalDate data, State estado) {
        setArtigos(lista);
        this.dim = dim;
        this.data = data;
        this.estado = estado;
        this.preco = calculaPrecoEnc();
    }

    public Encomenda(Encomenda o){
        this.artigos = o.getArtigos();
        this.dim = o.getDim();
        this.data = o.getData();
        this.estado = o.getEstado();
        this.preco = o.getPreco();
    }

    public Set<Artigo> getArtigos() {
        Set<Artigo> novo = new HashSet<>();
        for(Artigo c : this.artigos){
            novo.add(c.clone());
        }
        return novo;
    }

    public void setArtigos(Set<Artigo> lista) {
        this.artigos = new HashSet<>();
        for(Artigo c : lista){
            this.artigos.add(c.clone());
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

    private double calculaPrecoEnc(){
        double preco = 0;
        for(Artigo c : this.artigos){
            if(c instanceof Sapatilha){
                preco += ((Sapatilha) c).getPreco_curr();
                if(c.isNovo()){
                    preco += 0.5;
                }
                else preco += 0.25;
            }
            else if(c instanceof Tshirt){
                preco += ((Tshirt) c).getPreco_curr();
                if(c.isNovo()){
                    preco += 0.5;
                }
                else preco += 0.25;
            }
            else if(c instanceof Mala){
                preco += ((Mala) c).getPreco_curr();
                if(c.isNovo()){
                    preco += 0.5;
                }
                else preco += 0.25;
            }
        }
        return preco;
    }

    public void addArtEncomenda(Artigo c){
        double preco = this.preco;
        this.artigos.add(c.clone());
        if(c instanceof Sapatilha){
            preco += ((Sapatilha) c).getPreco_curr();
            if(c.isNovo()){
                preco += 0.5;
            }
            else preco += 0.25;
        }
        else if(c instanceof Tshirt){
            preco += ((Tshirt) c).getPreco_curr();
            if(c.isNovo()){
                preco += 0.5;
            }
            else preco += 0.25;
        }
        else if(c instanceof Mala){
            preco += ((Mala) c).getPreco_curr();
            if(c.isNovo()){
                preco += 0.5;
            }
            else preco += 0.25;
        }
        setPreco(preco);
    }

    public void removeArtEncomenda(Artigo c){
        double preco = this.preco;
        if(c instanceof Sapatilha){
            preco -= ((Sapatilha) c).getPreco_curr();
            if(c.isNovo()){
                preco -= 0.5;
            }
            else preco -= 0.25;
        }
        else if(c instanceof Tshirt){
            preco -= ((Tshirt) c).getPreco_curr();
            if(c.isNovo()){
                preco -= 0.5;
            }
            else preco -= 0.25;
        }
        else if(c instanceof Mala){
            preco -= ((Mala) c).getPreco_curr();
            if(c.isNovo()){
                preco -= 0.5;
            }
            else preco -= 0.25;
        }
        this.artigos.remove(c.clone());
        setPreco(preco);
    }

    public boolean isRefundable(){
        if(this.data.plusDays(2).isBefore(this.data.now())) return true;
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
        for(Artigo c : this.artigos){
            sb.append(c.toString() + "\n");
        }
        sb.append("Preço: " + this.preco + "\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Encomenda l = (Encomenda) o;
        return  this.dim == l.getDim() &&
                this.estado == l.getEstado() &&
                this.data.equals(l.getData()) &&
                Double.compare(this.preco, l.getPreco()) == 0 &&
                SetDeepClone.isDeepCloneSet(this.artigos, l.getArtigos());

    }
}
