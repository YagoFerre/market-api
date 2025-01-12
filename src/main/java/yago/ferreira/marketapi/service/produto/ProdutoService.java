package yago.ferreira.marketapi.service.produto;

import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.entity.File;
import yago.ferreira.marketapi.entity.Produto;
import yago.ferreira.marketapi.entity.Usuario;
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

    public ProdutoService(ProdutoRepository produtoRepository, UsuarioService usuarioService, FileService fileService) {
        this.produtoRepository = produtoRepository;
        this.usuarioService = usuarioService;
        this.fileService = fileService;
    }

    @Transactional
    public Produto criarProduto(Produto produto, List<MultipartFile> imagens) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = usuarioService.findUsuarioLogado(((Usuario) auth.getPrincipal()).getEmail());

        produto.setUsuario(usuarioLogado);
        Produto produtoResponse = produtoRepository.save(produto);

        List<File> produtoImagens = imagens.stream().map(imagem -> {
            String filePath = fileService.storeFile(imagem);
            File file = new File();
            file.setNome(imagem.getOriginalFilename());
            file.setFilePath(filePath);
            file.setProduto(produto);
            return file;
        }).toList();

        produtoResponse.setProdutoImagem(produtoImagens);
        return produtoRepository.save(produtoResponse);
    }
}
