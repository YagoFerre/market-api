package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;
import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.domain.port.out.repository.ProdutoRepository;

import java.util.Optional;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoRepository produtoRepository;

    public ProdutoRepositoryImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto save(Produto domainObj) {
        return produtoRepository.save(domainObj);
    }

    @Override
    public PageResponse<Produto> findAll(int pagina, int itens) {
        return produtoRepository.findAll(pagina, itens);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public PageResponse<Produto> findAllByUsuarioEmail(String email, int pagina, int itens) {
        return produtoRepository.findAllByUsuarioEmail(email, pagina, itens);
    }

}
