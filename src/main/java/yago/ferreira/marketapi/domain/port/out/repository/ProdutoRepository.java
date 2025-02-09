package yago.ferreira.marketapi.domain.port.out.repository;

import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;

import java.util.Optional;

public interface ProdutoRepository {
    Produto save(Produto domainObj);
    PageResponse<Produto> findAll(int pagina, int itens);
    Optional<Produto> findById(Long id);
    void deleteById(Long id);
    PageResponse<Produto> findAllByUsuarioEmail(String email, int pagina, int itens);
}
