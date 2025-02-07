package yago.ferreira.marketapi.domain.entity;

import jakarta.persistence.*;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String filePath;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Avatar() {
    }

    public Avatar(Long id, String nome, String filePath, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.filePath = filePath;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
