public class Transportadora {
    public final double PEQUENAS = 3.99;
    public final double MEDIAS = 5.99;
    public final double GRANDES = 10.99;
    public final double IMPOSTO = 0.15;
    private double lucro;
    private boolean premium;
    private String nome;
    private double dinheiro_feito;

    public Transportadora(){
        this.nome = "boua";
        this.premium = false;
        this.lucro = 0;
        this.dinheiro_feito = 0;
    }

    public Transportadora(boolean premium, double lucro, String nome, double dinheiro_feito){
        this.nome = nome;
        this.premium = premium;
        this.lucro = lucro;
        this.dinheiro_feito = dinheiro_feito;
    }

    public Transportadora(Transportadora o){
        this.premium = o.isPremium();
        this.lucro = o.getLucro();
        this.nome = o.getNome();
        this.dinheiro_feito = o.getDinheiro_feito();
    }

    public double getDinheiro_feito() {
        return dinheiro_feito;
    }

    public void setDinheiro_feito(double dinheiro_feito) {
        this.dinheiro_feito = dinheiro_feito;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

//    public double calculaPrecoExped(Encomenda o){
//        double preco = 0;
//        if(o.getDim() == Encomenda.Embalagem.Pequeno)
//            preco = PEQUENAS * this.lucro * (1.0+IMPOSTO) * 0.9;
//        else if (o.getDim() == Encomenda.Embalagem.Medio)
//            preco = MEDIAS * this.lucro * (1.0+IMPOSTO) * 0.9;
//        else if (o.getDim() == Encomenda.Embalagem.Grande)
//            preco = GRANDES * this.lucro * (1.0+IMPOSTO) * 0.9;
//
//        return preco;
//    }

    public Transportadora clone(){
        return new Transportadora(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadora :: " + this.nome);
        sb.append(", Lucro: " + this.lucro);
        sb.append(", Premium: " + this.premium + "\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Transportadora l = (Transportadora) o;
        return Double.compare(this.lucro, l.getLucro()) == 0 &&
                this.premium == l.isPremium() &&
                this.nome.equals(l.getNome());

    }
}
