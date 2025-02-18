package yago.ferreira.marketapi.domain.model;

public class RegisterRequestDomain {
    private String email;
    private String nome;
    private String senha;
    private File avatar;

    public RegisterRequestDomain() {
    }

    public RegisterRequestDomain(String email, String nome, String senha, File avatar) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.avatar = avatar;
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
