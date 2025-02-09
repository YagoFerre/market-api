package yago.ferreira.marketapi.adapters.out.entities;

import jakarta.persistence.*;

@Entity
public class JpaAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String filePath;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private JpaUsuario usuario;

    public JpaAvatar() {
    }

    public JpaAvatar(Long id, String nome, String filePath, JpaUsuario usuario) {
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

    public JpaUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(JpaUsuario usuario) {
        this.usuario = usuario;
    }

}
