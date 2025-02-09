package yago.ferreira.marketapi.adapters.out.entities;

import jakarta.persistence.*;

@Entity
public class JpaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private JpaProduto produto;

    public JpaFile() {
    }

    public JpaFile(Long id, String nome, String filePath, JpaProduto produto) {
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

    public JpaProduto getProduto() {
        return produto;
    }

    public void setProduto(JpaProduto produto) {
        this.produto = produto;
    }

}
