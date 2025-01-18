package yago.ferreira.marketapi.service.produto;

import jakarta.transaction.Transactional;
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

    @Transactional
    public ProdutoDTO criarProduto(Produto produto, List<MultipartFile> imagens) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = usuarioService.findUsuarioLogado(((Usuario) auth.getPrincipal()).getEmail());

        produto.setUsuario(usuarioLogado);
        Produto produtoSalvo = produtoRepository.save(produto);

        List<File> produtoImagens = getProdutoImagens(imagens, produtoSalvo);
        produto.setProdutoImagem(produtoImagens);

        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    public ProdutoDTO atualizarProduto(Long id, Produto produto, List<MultipartFile> imagens) {
        return produtoRepository.findById(id)
                .map(produtoFound -> {
                    produtoFound.setTitulo(produto.getTitulo());
                    produtoFound.setDescricao(produto.getDescricao());
                    produtoFound.setPreco(produto.getPreco());
                    produtoFound.setAtivo(produto.getAtivo());
                    // TODO lógica para atualizar as imagens do produto

                    return produtoMapper.toDTO(produtoRepository.save(produtoFound));
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

    private List<File> getProdutoImagens(List<MultipartFile> files, Produto produto) {
        File fileImage = new File();

        return files.stream().map(file -> {
            String filePath = fileService.storeFile(file);

            fileImage.setNome(file.getOriginalFilename());
            fileImage.setFilePath(filePath);
            fileImage.setProduto(produto);
            return fileImage;
        }).toList();
    }
}
