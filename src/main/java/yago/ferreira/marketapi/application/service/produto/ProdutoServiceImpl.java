package yago.ferreira.marketapi.application.service.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;
import yago.ferreira.marketapi.adapters.out.entities.JpaFile;
import yago.ferreira.marketapi.adapters.out.entities.JpaProduto;
import yago.ferreira.marketapi.adapters.out.mappers.FileMapper;
import yago.ferreira.marketapi.adapters.out.mappers.ProdutoMapper;
import yago.ferreira.marketapi.adapters.out.repository.JpaProdutoRepository;
import yago.ferreira.marketapi.application.service.file.FileService;
import yago.ferreira.marketapi.application.service.usuario.UsuarioServiceImpl;
import yago.ferreira.marketapi.domain.exceptions.RecordNotFoundException;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.domain.port.in.usecases.ProdutoUseCases;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoServiceImpl implements ProdutoUseCases {

    private final JpaProdutoRepository jpaProdutoRepository;
    private final UsuarioServiceImpl usuarioServiceImpl;
    private final FileService fileService;
    private final ProdutoMapper produtoMapper;
    private final FileMapper fileMapper;

    public ProdutoServiceImpl(JpaProdutoRepository jpaProdutoRepository, UsuarioServiceImpl usuarioServiceImpl, FileService fileService, ProdutoMapper produtoMapper, FileMapper fileMapper) {
        this.jpaProdutoRepository = jpaProdutoRepository;
        this.usuarioServiceImpl = usuarioServiceImpl;
        this.fileService = fileService;
        this.produtoMapper = produtoMapper;
        this.fileMapper = fileMapper;
    }

    @Override
    public PageResponse<Produto> executeListarProdutos(int pagina, int itens) {
        return produtoMapper.toPageResponse(jpaProdutoRepository.findAll(PageRequest.of(pagina, itens)));
    }

    @Override
    public PageResponse<Produto> executeListarProdutosUsuario(int pagina, int itens) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((Usuario) auth.getPrincipal()).getEmail();

        Page<JpaProduto> produtosUsuario = jpaProdutoRepository.findAllByUsuarioEmail(email, PageRequest.of(pagina, itens));
        return produtoMapper.toPageResponse(produtosUsuario);
    }

    @Override
    public Produto executeCriarProduto(Produto produto, List<FileInput> imagens) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = usuarioServiceImpl.executeFindUsuarioLogado(((Usuario) auth.getPrincipal()).getEmail());

        produto.setUsuario(usuarioLogado);

        if (imagens != null) {
            salvarImagensDoProduto(produtoMapper.toJpaEntity(produto), fileMapper.toMultipartFileList(imagens));
        }

        JpaProduto jpaProduto = produtoMapper.toJpaEntity(produto);
        return produtoMapper.toDomain(jpaProdutoRepository.save(jpaProduto));
    }

    @Override
    public Produto executeAtualizarProduto(Long id, Produto produto, List<FileInput> imagens) {
        return jpaProdutoRepository.findById(id)
                .map(produtoFound -> {
                    produtoFound.setTitulo(produto.getTitulo());
                    produtoFound.setDescricao(produto.getDescricao());
                    produtoFound.setPreco(produto.getPreco());
                    produtoFound.setAtivo(produto.getAtivo());

                    if (imagens != null) {
                        atualizarImagensDoProduto(produtoFound, fileMapper.toMultipartFileList(imagens));
                    }

                    // Adiciona novas imagens se tiver
                    JpaProduto produtoAtualizado = jpaProdutoRepository.save(produtoFound);
                    return produtoMapper.toDomain(produtoAtualizado);
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public void executeDeletarProduto(Long id) {
        jpaProdutoRepository.deleteById(id);
    }

    @Override
    public Produto executeListProdutoById(Long id) {
        return jpaProdutoRepository.findById(id)
                .map(produtoMapper::toDomain)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    private void salvarImagensDoProduto(JpaProduto jpaProduto, List<MultipartFile> imagens) {
        List<JpaFile> produtoImagens = getProdutoImagens(imagens, jpaProduto);
        jpaProduto.setProdutoImagem(produtoImagens);
    }

    private void atualizarImagensDoProduto(JpaProduto jpaProduto, List<MultipartFile> imagens) {
        jpaProduto.getProdutoImagem().clear();
        List<JpaFile> produtoImagens = getProdutoImagens(imagens, jpaProduto);
        jpaProduto.getProdutoImagem().addAll(produtoImagens);
    }

    private List<JpaFile> getProdutoImagens(List<MultipartFile> files, JpaProduto jpaProduto) {
        return files.stream().map(file -> {
            FileResponse fileResponse = fileService.storeFile(file);

            JpaFile fileImage = new JpaFile();
            fileImage.setNome(fileResponse.getFileName());
            fileImage.setFilePath(fileResponse.getFilePath());
            fileImage.setProduto(jpaProduto);
            return fileImage;
        }).collect(Collectors.toList());
    }

}
