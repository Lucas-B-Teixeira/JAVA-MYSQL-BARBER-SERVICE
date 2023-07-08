package entity;

public class Usuario {
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String nascimento;
    private String telefone;

    public Usuario(int id, String nome, String sobrenome, String email, String nascimento, String telefone){
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.nascimento = nascimento;
        this.telefone = telefone;
    }

    public Usuario(String nome, String sobrenome, String email, String nascimento, String telefone){
        this(0, nome, sobrenome, email, nascimento, telefone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
