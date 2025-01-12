package yago.ferreira.marketapi.entity.dto;

import yago.ferreira.marketapi.entity.File;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal preco;
    private UsuarioDTO usuario;
    private List<File> imagens;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String titulo, String descricao, BigDecimal preco, UsuarioDTO usuario, List<File> imagens) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.usuario = usuario;
        this.imagens = imagens;
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

    public List<File> getImagens() {
        return imagens;
    }

    public void setImagens(List<File> imagens) {
        this.imagens = imagens;
    }

}
