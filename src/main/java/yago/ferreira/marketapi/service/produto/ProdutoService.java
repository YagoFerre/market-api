package yago.ferreira.marketapi.service.produto;

import org.springframework.stereotype.Service;
import yago.ferreira.marketapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public criarProduto() {}
}
