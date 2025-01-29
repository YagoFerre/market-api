package yago.ferreira.marketapi.service.produto;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.entity.File;
import yago.ferreira.marketapi.entity.Produto;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.entity.dto.ProdutoDTO;
import yago.ferreira.marketapi.entity.mapper.ProdutoMapper;
import yago.ferreira.marketapi.exceptions.RecordNotFoundException;
import yago.ferreira.marketapi.repository.ProdutoRepository;
import yago.ferreira.marketapi.service.file.FileService;
import yago.ferreira.marketapi.service.usuario.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final UsuarioService usuarioService;
    private final FileService fileService;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, UsuarioService usuarioService, FileService fileService, ProdutoMapper produtoMapper) {
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
        Produto produtoSalvo = salvarImagensDoProduto(produto, imagens);
        return produtoMapper.toDTO(produtoSalvo);
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

                    atualizarImagensDoProduto(produtoFound, imagens);

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

    private Produto salvarImagensDoProduto(Produto produto, List<MultipartFile> imagens) {
        List<File> produtoImagens = getProdutoImagens(imagens, produto);
        produto.setProdutoImagem(produtoImagens);
        return produtoRepository.save(produto);
    }

    private void atualizarImagensDoProduto(Produto produto, List<MultipartFile> imagens) {
        produto.getProdutoImagem().clear();
        List<File> produtoImagens = getProdutoImagens(imagens, produto);
        produto.getProdutoImagem().addAll(produtoImagens);
    }

    private List<File> getProdutoImagens(List<MultipartFile> files, Produto produto) {
        return files.stream().map(file -> {
            String filePath = fileService.storeFile(file);

            File fileImage = new File();
            fileImage.setNome(file.getOriginalFilename().replace(' ', '_'));
            fileImage.setFilePath(filePath);
            fileImage.setProduto(produto);
            return fileImage;
        }).collect(Collectors.toList());
    }

}
