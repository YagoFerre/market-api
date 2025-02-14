package yago.ferreira.marketapi.adapters.in.controller.dto;

public class UsuarioDTO {

    private String email;
    private String nome;
    private FileDTO avatar;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String email, String nome, FileDTO avatar) {
        this.email = email;
        this.nome = nome;
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

    public FileDTO getAvatar() {
        return avatar;
    }

    public void setAvatar(FileDTO avatar) {
        this.avatar = avatar;
    }

}
