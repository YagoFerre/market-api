package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;
import yago.ferreira.marketapi.adapters.out.entities.JpaProduto;
import yago.ferreira.marketapi.adapters.out.mappers.ProdutoMapper;
import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.domain.port.out.repository.ProdutoRepository;

import java.util.Optional;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final JpaProdutoRepository jpaProdutoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoRepositoryImpl(JpaProdutoRepository jpaProdutoRepository, ProdutoMapper produtoMapper) {
        this.jpaProdutoRepository = jpaProdutoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public Produto save(Produto domainObj) {
        JpaProduto jpaProduto = produtoMapper.toJpaEntity(domainObj);
        return produtoMapper.toDomain(jpaProdutoRepository.save(jpaProduto));
    }

    @Override
    public PageResponse<Produto> findAll(int pagina, int itens) {
        Page<JpaProduto> jpaProdutoPage = jpaProdutoRepository.findAll(PageRequest.of(pagina, itens));
        return produtoMapper.toPageResponse(jpaProdutoPage);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return jpaProdutoRepository.findById(id).map(produtoMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaProdutoRepository.deleteById(id);
    }

    @Override
    public PageResponse<Produto> findAllByUsuarioEmail(String email, int pagina, int itens) {
        Page<JpaProduto> jpaProdutoPage = jpaProdutoRepository.findAllByUsuarioEmail(email, PageRequest.of(pagina, itens));
        return produtoMapper.toPageResponse(jpaProdutoPage);
    }

}
