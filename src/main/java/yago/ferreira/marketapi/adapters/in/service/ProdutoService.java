package yago.ferreira.marketapi.adapters.in.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.ProdutoDTO;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;
import yago.ferreira.marketapi.adapters.out.mappers.FileMapper;
import yago.ferreira.marketapi.adapters.out.mappers.ProdutoMapper;
import yago.ferreira.marketapi.application.service.produto.ProdutoServiceImpl;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.Produto;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoServiceImpl produtoServiceImpl;
    private final ProdutoMapper produtoMapper;
    private final FileMapper fileMapper;

    public ProdutoService(ProdutoServiceImpl produtoServiceImpl, ProdutoMapper produtoMapper, FileMapper fileMapper) {
        this.produtoServiceImpl = produtoServiceImpl;
        this.produtoMapper = produtoMapper;
        this.fileMapper = fileMapper;
    }

    public Page<ProdutoDTO> listarProdutos(int pagina, int itens) {
        PageResponse<Produto> produtoPageResponse = produtoServiceImpl.executeListarProdutos(pagina, itens);
        return produtoMapper.toPageDTO(produtoPageResponse);
    }

    public Page<ProdutoDTO> listarProdutosUsuario(int pagina, int itens) {
        PageResponse<Produto> produtoPageResponse = produtoServiceImpl.executeListarProdutosUsuario(pagina, itens);
        return produtoMapper.toPageDTO(produtoPageResponse);
    }

    @Transactional
    public ProdutoDTO criarProduto(ProdutoDTO dto, List<MultipartFile> imagens) {
        Produto produtoDomain = produtoMapper.toDomainEntity(dto);
        List<FileInput> fileInputListDomain = fileMapper.toFileInputDomainList(imagens);

        Produto produto = produtoServiceImpl.executeCriarProduto(produtoDomain, fileInputListDomain);
        return produtoMapper.toDto(produto);
    }

    @Transactional
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto, List<MultipartFile> imagens) {
        Produto produtoDomain = produtoMapper.toDomainEntity(dto);
        List<FileInput> fileInputListDomain = fileMapper.toFileInputDomainList(imagens);

        Produto produto = produtoServiceImpl.executeAtualizarProduto(id, produtoDomain, fileInputListDomain);
        return produtoMapper.toDto(produto);
    }

    public void deletarProduto(Long id) {
        produtoServiceImpl.executeDeletarProduto(id);
    }

    public ProdutoDTO listProdutoById(Long id) {
        Produto produto = produtoServiceImpl.executeListProdutoById(id);
        return produtoMapper.toDto(produto);
    }
}
