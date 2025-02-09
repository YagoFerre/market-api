package yago.ferreira.marketapi.application.service.produto;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.domain.entity.File;
import yago.ferreira.marketapi.domain.entity.Produto;
import yago.ferreira.marketapi.domain.entity.Usuario;
import yago.ferreira.marketapi.domain.dto.ProdutoDTO;
import yago.ferreira.marketapi.utils.mapper.ProdutoMapper;
import yago.ferreira.marketapi.domain.response.FileResponse;
import yago.ferreira.marketapi.infra.config.exceptions.RecordNotFoundException;
import yago.ferreira.marketapi.adapters.out.repository.JpaProdutoRepository;
import yago.ferreira.marketapi.application.service.file.FileService;
import yago.ferreira.marketapi.application.service.usuario.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final JpaProdutoRepository produtoRepository;
    private final UsuarioService usuarioService;
    private final FileService fileService;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(JpaProdutoRepository produtoRepository, UsuarioService usuarioService, FileService fileService, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.usuarioService = usuarioService;
        this.fileService = fileService;
        this.produtoMapper = produtoMapper;
    }

    public Page<ProdutoDTO> listarProdutos(int pagina, int itens) {
        Page<Produto> produtos = produtoRepository.findAll(PageRequest.of(pagina, itens));
        return produtos.map(produtoMapper::toDTO);
    }

    public Page<ProdutoDTO> listarProdutosUsuario(int pagina, int itens) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((Usuario) auth.getPrincipal()).getEmail();

        Page<Produto> produtosUsuario = produtoRepository.findAllByUsuarioEmail(email, PageRequest.of(pagina, itens));
        return produtosUsuario.map(produtoMapper::toDTO);
    }

    @Transactional
    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO, List<MultipartFile> imagens) {
        Produto produto = produtoMapper.toEntity(produtoDTO);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = usuarioService.findUsuarioLogado(((Usuario) auth.getPrincipal()).getEmail());

        produto.setUsuario(usuarioLogado);

        if (imagens != null) {
            salvarImagensDoProduto(produto, imagens);
        }

        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    @Transactional
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO, List<MultipartFile> imagens) {
        return produtoRepository.findById(id)
                .map(produtoFound -> {
                    Produto produto = produtoMapper.toEntity(produtoDTO);

                    produtoFound.setTitulo(produto.getTitulo());
                    produtoFound.setDescricao(produto.getDescricao());
                    produtoFound.setPreco(produto.getPreco());
                    produtoFound.setAtivo(produto.getAtivo());

                    if (imagens != null) {
                        atualizarImagensDoProduto(produtoFound, imagens);
                    }

                    // Adiciona novas imagens se tiver
                    Produto produtoAtualizado = produtoRepository.save(produtoFound);
                    return produtoMapper.toDTO(produtoAtualizado);
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO listProdutoById(Long id) {
        return produtoRepository.findById(id)
                .map(produtoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    private void salvarImagensDoProduto(Produto produto, List<MultipartFile> imagens) {
        List<File> produtoImagens = getProdutoImagens(imagens, produto);
        produto.setProdutoImagem(produtoImagens);
    }

    private void atualizarImagensDoProduto(Produto produto, List<MultipartFile> imagens) {
        produto.getProdutoImagem().clear();
        List<File> produtoImagens = getProdutoImagens(imagens, produto);
        produto.getProdutoImagem().addAll(produtoImagens);
    }

    private List<File> getProdutoImagens(List<MultipartFile> files, Produto produto) {
        return files.stream().map(file -> {
            FileResponse fileResponse = fileService.storeFile(file);

            File fileImage = new File();
            fileImage.setNome(fileResponse.getFileName());
            fileImage.setFilePath(fileResponse.getFilePath());
            fileImage.setProduto(produto);
            return fileImage;
        }).collect(Collectors.toList());
    }

}
