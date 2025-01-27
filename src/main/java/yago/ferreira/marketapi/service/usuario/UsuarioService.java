package yago.ferreira.marketapi.service.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.entity.dto.UsuarioDTO;
import yago.ferreira.marketapi.entity.mapper.UsuarioMapper;
import yago.ferreira.marketapi.entity.request.RegisterRequest;
import yago.ferreira.marketapi.exceptions.EmailAlreadyExistsException;
import yago.ferreira.marketapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public Usuario findUsuarioLogado(String email) {
        return usuarioRepository.findUsuarioByEmail(email);
    }

    public UsuarioDTO createUsuario(RegisterRequest request) {
        UserDetails usuarioExistente = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioExistente != null) {
            throw new EmailAlreadyExistsException();
        }

        Usuario usuario = usuarioMapper.registerToEntity(request);
        String senhaEncrypted = new BCryptPasswordEncoder().encode(request.getSenha());

        usuario.setSenha(senhaEncrypted);
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }
}
