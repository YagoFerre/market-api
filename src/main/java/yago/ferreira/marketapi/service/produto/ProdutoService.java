package yago.ferreira.marketapi.service.produto;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yago.ferreira.marketapi.entity.Produto;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.repository.ProdutoRepository;
import yago.ferreira.marketapi.service.usuario.UsuarioService;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final UsuarioService usuarioService;

    public ProdutoService(ProdutoRepository produtoRepository, UsuarioService usuarioService) {
        this.produtoRepository = produtoRepository;
        this.usuarioService = usuarioService;
    }

    public Produto criarProduto(Produto produto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = usuarioService.findUsuarioLogado(((Usuario) auth.getPrincipal()).getEmail());

        produto.setUsuario(usuarioLogado);
        return produtoRepository.save(produto);
    }
}
