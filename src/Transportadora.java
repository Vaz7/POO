public class Transportadora {
    public final double PEQUENAS = 3.99;
    public final double MEDIAS = 5.99;
    public final double GRANDES = 10.99;
    public final double IMPOSTO = 0.15;
    private double preco_transporte;
    private double lucro;
    private boolean premium;

    public Transportadora(){
        this.preco_transporte = 0;
        this.premium = false;
        this.lucro = 0;
    }

    public Transportadora(boolean premium, double lucro){
        this.premium = premium;
        this.lucro = lucro;
    }

    public Transportadora(Transportadora o){
        this.premium = o.isPremium();
        this.lucro = o.getLucro();
        this.preco_transporte = o.getPreco_transporte();
    }

    public double getPreco_transporte() {
        return preco_transporte;
    }

    public void setPreco_transporte(double preco_transporte) {
        this.preco_transporte = preco_transporte;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double calculaPrecoExped(Encomenda o){
        double preco = 0;
        if(o.getDim() == Encomenda.Embalagem.Pequeno)
            preco = PEQUENAS * this.lucro * (1.0+IMPOSTO) * 0.9;
        else if (o.getDim() == Encomenda.Embalagem.Medio)
            preco = MEDIAS * this.lucro * (1.0+IMPOSTO) * 0.9;
        else if (o.getDim() == Encomenda.Embalagem.Grande)
            preco = GRANDES * this.lucro * (1.0+IMPOSTO) * 0.9;

        return preco;
    }

    public void addPreco(Encomenda o){
        setPreco_transporte(calculaPrecoExped(o));
    }

    public Transportadora clone(){
        return new Transportadora(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadora :: \n");
        sb.append("Lucro Transportadora: " + this.lucro + "\n");
        sb.append("Premium: " + this.premium + "\n");
        sb.append("Preço: " + this.preco_transporte + "\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Transportadora l = (Transportadora) o;
        return Double.compare(this.preco_transporte, l.getPreco_transporte()) == 0 &&
                Double.compare(this.lucro, l.getLucro()) == 0 &&
                this.premium == l.isPremium();

    }
}
