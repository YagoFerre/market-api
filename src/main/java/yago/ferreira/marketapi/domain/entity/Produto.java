package yago.ferreira.marketapi.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo = true;
    private Usuario usuario;
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
