package yago.ferreira.marketapi.domain.entity;

public class File {
    private Long id;
    private String nome;
    private String filePath;
    private Produto produto;

    public File() {
    }

    public File(Long id, String nome, String filePath, Produto produto) {
        this.id = id;
        this.nome = nome;
        this.filePath = filePath;
        this.produto = produto;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
