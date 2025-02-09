package yago.ferreira.marketapi.application.usecases;

import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.application.dto.UsuarioDTO;
import yago.ferreira.marketapi.domain.entity.Usuario;
import yago.ferreira.marketapi.application.request.RegisterRequest;

public interface UsuarioUseCases {
    Usuario findUsuarioLogado(String email);
    UsuarioDTO createUsuario(RegisterRequest request, MultipartFile file);
    UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, MultipartFile file);
}
