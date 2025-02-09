package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import yago.ferreira.marketapi.adapters.out.entities.JpaProduto;
import yago.ferreira.marketapi.domain.entity.Produto;
import yago.ferreira.marketapi.domain.repository.ProdutoRepository;
import yago.ferreira.marketapi.domain.response.PageResponse;
import yago.ferreira.marketapi.utils.mapper.ProdutoMapper;

import java.util.Optional;

public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final JpaProdutoRepository jpaProdutoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoRepositoryImpl(JpaProdutoRepository jpaProdutoRepository, ProdutoMapper produtoMapper) {
        this.jpaProdutoRepository = jpaProdutoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public Produto save(Produto domainObj) {
        JpaProduto jpaEntity = produtoMapper.toJpaEntity(domainObj);
        return produtoMapper.toDomain(jpaProdutoRepository.save(jpaEntity));
    }

    @Override
    public PageResponse<Produto> findAll(int pagina, int itens) {
        Pageable pageable = PageRequest.of(pagina, itens);
        Page<JpaProduto> jpaEntityPage = jpaProdutoRepository.findAll(pageable);
        return produtoMapper.toPageResponse(jpaEntityPage);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        Optional<JpaProduto> jpaEntity = jpaProdutoRepository.findById(id);
        return jpaEntity.map(produtoMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaProdutoRepository.deleteById(id);
    }

    @Override
    public PageResponse<Produto> findAllByUsuarioEmail(String email, int pagina, int itens) {
        Pageable pageable = PageRequest.of(pagina, itens);
        Page<JpaProduto> jpaEntityPage = jpaProdutoRepository.findAllByUsuarioEmail(email, pageable);
        return produtoMapper.toPageResponse(jpaEntityPage);
    }

}
