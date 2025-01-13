package yago.ferreira.marketapi.entity.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDTO {

    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank
    @NotNull
    private String descricao;

    @NotBlank
    @NotNull
    private BigDecimal preco;

    private UsuarioDTO usuario;

    private List<FileDTO> produtoImagem;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String titulo, String descricao, BigDecimal preco, UsuarioDTO usuario, List<FileDTO> produtoImagem) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.usuario = usuario;
        this.produtoImagem = produtoImagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @NotNull String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank @NotNull String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @NotNull String descricao) {
        this.descricao = descricao;
    }

    public @NotBlank @NotNull BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(@NotBlank @NotNull BigDecimal preco) {
        this.preco = preco;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<FileDTO> getProdutoImagem() {
        return produtoImagem;
    }

    public void setProdutoImagem(List<FileDTO> produtoImagem) {
        this.produtoImagem = produtoImagem;
    }

}
