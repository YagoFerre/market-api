package yago.ferreira.marketapi.application.service.produto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;
import yago.ferreira.marketapi.application.service.file.FileServiceImpl;
import yago.ferreira.marketapi.application.service.usuario.UsuarioServiceImpl;
import yago.ferreira.marketapi.domain.exceptions.RecordNotFoundException;
import yago.ferreira.marketapi.domain.model.File;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.domain.port.in.usecases.ProdutoUseCases;
import yago.ferreira.marketapi.domain.port.out.repository.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoServiceImpl implements ProdutoUseCases {

    private final ProdutoRepository produtoRepository;
    private final UsuarioServiceImpl usuarioServiceImpl;
    private final FileServiceImpl fileServiceImpl;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, UsuarioServiceImpl usuarioServiceImpl, FileServiceImpl fileServiceImpl) {
        this.produtoRepository = produtoRepository;
        this.usuarioServiceImpl = usuarioServiceImpl;
        this.fileServiceImpl = fileServiceImpl;
    }

    @Override
    public PageResponse<Produto> executeListarProdutos(int pagina, int itens) {
        return produtoRepository.findAll(pagina, itens);
    }

    @Override
    public PageResponse<Produto> executeListarProdutosUsuario(int pagina, int itens) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((Usuario) auth.getPrincipal()).getEmail();

        return produtoRepository.findAllByUsuarioEmail(email, pagina, itens);
    }

    @Override
    public Produto executeCriarProduto(Produto produto, List<FileInput> imagens) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = usuarioServiceImpl.executeFindUsuarioLogado(((Usuario) auth.getPrincipal()).getEmail());

        produto.setUsuario(usuarioLogado);

        if (imagens != null) {
            salvarImagensDoProduto(produto, imagens);
        }

        return produtoRepository.save(produto);
    }

    @Override
    public Produto executeAtualizarProduto(Long id, Produto produto, List<FileInput> imagens) {
        return produtoRepository.findById(id)
                .map(produtoFound -> {
                    produtoFound.setTitulo(produto.getTitulo());
                    produtoFound.setDescricao(produto.getDescricao());
                    produtoFound.setPreco(produto.getPreco());
                    produtoFound.setAtivo(produto.getAtivo());

                    if (imagens != null) {
                        atualizarImagensDoProduto(produtoFound, imagens);
                    }

                    // Adiciona novas imagens se tiver
                    return produtoRepository.save(produtoFound);
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public void executeDeletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto executeListProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    private void salvarImagensDoProduto(Produto produto, List<FileInput> imagens) {
        List<File> produtoImagens = getProdutoImagens(imagens);
        produto.setProdutoImagem(produtoImagens);
    }

    private void atualizarImagensDoProduto(Produto produto, List<FileInput> imagens) {
        produto.getProdutoImagem().clear();
        List<File> produtoImagens = getProdutoImagens(imagens);
        produto.getProdutoImagem().addAll(produtoImagens);
    }

    private List<File> getProdutoImagens(List<FileInput> files) {
        return files.stream().map(file -> {
            FileResponse fileResponse = fileServiceImpl.executeStoreFile(file);

            File fileImage = new File();
            fileImage.setNome(fileResponse.getFileName());
            fileImage.setFilePath(fileResponse.getFilePath());
            return fileImage;
        }).collect(Collectors.toList());
    }

}
