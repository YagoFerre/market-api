package yago.ferreira.marketapi.adapters.out.entities;

import jakarta.persistence.*;

@Entity(name = "file")
public class JpaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String filePath;

    public JpaFile() {
    }

    public JpaFile(Long id, String nome, String filePath) {
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
