package yago.ferreira.marketapi.domain.model;

public class Usuario {
    private Long id;
    private String email;
    private String nome;
    private String senha;
    private File avatar;

    public Usuario() {
    }

    public Usuario(Long id, String email, String nome, String senha, File avatar) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

}
