import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArtigosCatalogo {
    public Set<Artigo> artigos;

    public ArtigosCatalogo(){
        this.artigos = new HashSet<>();
    }

    public ArtigosCatalogo(Set<Artigo> a){
        setArtigos(a);
    }

    public ArtigosCatalogo(ArtigosCatalogo a){
        this.artigos = a.getArtigos();
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

    void addArtigo(Artigo c){
        this.artigos.add(c.clone());
    }

    void remArtigo(Artigo c){
        this.artigos.remove(c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Artigo c : this.artigos){
            sb.append(c.toString());
        }
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        ArtigosCatalogo l = (ArtigosCatalogo) o;
        return SetDeepClone.isDeepCloneSet(this.artigos, l.getArtigos());
    }
}
