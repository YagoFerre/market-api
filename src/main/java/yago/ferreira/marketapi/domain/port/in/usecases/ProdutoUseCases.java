package yago.ferreira.marketapi.domain.port.in.usecases;

import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.Produto;

import java.util.List;

public interface ProdutoUseCases {
    PageResponse<Produto> executeListarProdutos(int pagina, int itens);
    PageResponse<Produto> executeListarProdutosUsuario(int pagina, int itens);
    Produto executeCriarProduto(Produto Produto, List<FileInput> imagens);
    Produto executeAtualizarProduto(Long id, Produto Produto, List<FileInput> imagens);
    void executeDeletarProduto(Long id);
    Produto executeListProdutoById(Long id);
}
