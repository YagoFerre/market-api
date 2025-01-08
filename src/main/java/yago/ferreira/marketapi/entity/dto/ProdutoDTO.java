package yago.ferreira.marketapi.entity.dto;

import yago.ferreira.marketapi.entity.File;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal preco;
    private UsuarioDTO usuario;
    private File imagem;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String titulo, String descricao, BigDecimal preco, UsuarioDTO usuario, File imagem) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.usuario = usuario;
        this.imagem = imagem;
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public File getImagem() {
        return imagem;
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }

}
