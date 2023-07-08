package entity;

public class Agendamento implements ServicoInterface {
    private int id;
    private String agedamento;
    private String data_agend;
    private String hora_inicio;
    private String hora_termino;
    private int id_usuario;
    private String modelo;
    private String tipo;
    private double preco;
    private String duracao;

    public Agendamento(int id, String agedamento, String data_agend, String hora_inicio, String hora_termino, int id_usuario,
    String modelo, String tipo, double preco, String duracao) {
        this.id = id;
        this.agedamento = agedamento;
        this.data_agend = data_agend;
        this.hora_inicio = hora_inicio;
        this.hora_termino = hora_termino;
        this.id_usuario = id_usuario;
        this.modelo = modelo;
        this.tipo = tipo;
        this.preco = preco;
        this.duracao = duracao;
    }

    public Agendamento(String agedamento, String data_agend, String hora_inicio, String hora_termino, int id_usuario,
                       String modelo, String tipo, double preco, String duracao) {
        this(0, agedamento, data_agend, hora_inicio, hora_termino, id_usuario, modelo, tipo, preco, duracao);
    }

    public int getId() {
        return id;
    }

    public String getAgedamento() {
        return agedamento;
    }

    public void setAgedamento(String agedamento) {
        this.agedamento = agedamento;
    }

    public String getData_agend() {
        return data_agend;
    }

    public void setData_agend(String data_agend) {
        this.data_agend = data_agend;
    }

    public String gethora_inicio() {
        return hora_inicio;
    }

    public void setHora_agend(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_termino() {
        return hora_termino;
    }

    public void setHora_termino(String hora_termino) {
        this.hora_termino = hora_termino;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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
