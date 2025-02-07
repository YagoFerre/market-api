package yago.ferreira.marketapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE produto SET ativo = false WHERE id = ?")
@SQLRestriction("ativo = true")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("produto")
    private List<File> produtoImagem = new ArrayList<>();

    public Produto() {
    }

    public Produto(Long id, String titulo, String descricao, BigDecimal preco, Boolean ativo, Usuario usuario, List<File> produtoImagem) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
        this.usuario = usuario;
        this.produtoImagem = produtoImagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<File> getProdutoImagem() {
        return produtoImagem;
    }

    public void setProdutoImagem(List<File> produtoImagem) {
        this.produtoImagem = produtoImagem;
    }

}
