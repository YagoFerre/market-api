package yago.ferreira.marketapi.service.usuario;

import org.springframework.stereotype.Service;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findUsuarioLogado(String email) {
        return usuarioRepository.findUsuarioByEmail(email);
    }
}
