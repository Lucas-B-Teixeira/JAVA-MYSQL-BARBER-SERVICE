package entity;

public class Horarios {
    private int opcao;
    private String dia;
    private String hora;
    private Agendamento agendamento;

    public Horarios(int opcao, String dia, String hora, Agendamento agendamento) {
        this.opcao = opcao;
        this.dia = dia;
        this.hora = hora;
        this.agendamento = agendamento;
    }

    public int getOpcao() {
        return opcao;
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}
