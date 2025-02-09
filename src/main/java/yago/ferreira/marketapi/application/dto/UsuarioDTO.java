package yago.ferreira.marketapi.application.dto;

public class UsuarioDTO {

    private String email;
    private String nome;
    private AvatarDTO avatar;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String email, String nome, AvatarDTO avatar) {
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

    public AvatarDTO getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarDTO avatar) {
        this.avatar = avatar;
    }

}
