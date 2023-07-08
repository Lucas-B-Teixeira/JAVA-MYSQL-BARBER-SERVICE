package entity;

public class Servico implements ServicoInterface {
    private int id;
    private String modelo;
    private String tipo;
    private double preco;
    private String duracao;

    public Servico(int id, String modelo, String tipo, double preco, String duracao) {
        this.id = id;
        this.modelo = modelo;
        this.tipo = tipo;
        this.preco = preco;
        this.duracao = duracao;
    }

    public Servico(String modelo, String tipo, double preco, String duracao){
        this(0, modelo, tipo, preco, duracao);
    }

    //#######################################################################

    @Override
    public String getModelo(){ return this.modelo; }

    @Override
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String getTipo(){ return this.tipo; }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public double getPreco(){ return this.preco; }

    @Override
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String getDuracao(){ return this.duracao; }

    @Override
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    //#######################################################################
}
