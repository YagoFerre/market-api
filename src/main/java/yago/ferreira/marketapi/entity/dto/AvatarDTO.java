package yago.ferreira.marketapi.entity.dto;

public class AvatarDTO {

    private Long id;
    private String nome;
    private String filePath;

    public AvatarDTO() {
    }

    public AvatarDTO(Long id, String nome, String filePath) {
        this.id = id;
        this.nome = nome;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
