package yago.ferreira.marketapi.domain.repository;

import yago.ferreira.marketapi.domain.entity.Produto;
import yago.ferreira.marketapi.domain.response.PageResponse;

import java.util.Optional;

public interface ProdutoRepository {
    Produto save(Produto domainObj);
    PageResponse<Produto> findAll(int pagina, int itens);
    Optional<Produto> findById(Long id);
    void deleteById(Long id);
    PageResponse<Produto> findAllByUsuarioEmail(String email, int pagina, int itens);
}
