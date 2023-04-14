import java.util.HashMap;
import java.util.Map;

public class ArtigoUserManager {
    public Map<String, Integer> artigo_belongs;

    public ArtigoUserManager(){
        this.artigo_belongs = new HashMap<>();
    }

    public ArtigoUserManager(Map<String, Integer> a){
        setArtigo_belongs(a);
    }

    public ArtigoUserManager(ArtigoUserManager c){
        this.artigo_belongs = c.getArtigo_belongs();
    }

    public Map<String, Integer> getArtigo_belongs() {
        Map<String, Integer> novo = new HashMap<>();
        for(Map.Entry<String, Integer> c : this.artigo_belongs.entrySet()){
            String aux = c.getKey();
            Integer use = c.getValue();
            novo.put(aux,use);
        }
        return novo;
    }

    public void setArtigo_belongs(Map<String, Integer> artigo_belongs) {
        this.artigo_belongs = new HashMap<>();
        for(Map.Entry<String, Integer> c : artigo_belongs.entrySet()){
            String aux = c.getKey();
            Integer use = c.getValue();
            this.artigo_belongs.put(aux,use);
        }
    }

    void addArtigoToUser(String codigoAlfaNumerico, Integer codigoUser){
        this.artigo_belongs.put(codigoAlfaNumerico, codigoUser);
    }

    void removeArtigoInUser(String codigoAlfaNumerico){
        this.artigo_belongs.remove(codigoAlfaNumerico);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> c : this.artigo_belongs.entrySet()){
            sb.append("Código Alfanumérico:: " + c.getKey() + "  Código User: " + c.getValue() + "\n");
        }
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        ArtigoUserManager l = (ArtigoUserManager) o;
        return SetDeepClone.ArtigoUserManagerDeepClone(this.artigo_belongs,l.getArtigo_belongs());
    }
}
