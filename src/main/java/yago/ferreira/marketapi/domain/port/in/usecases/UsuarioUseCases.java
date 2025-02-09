package yago.ferreira.marketapi.domain.port.in.usecases;

import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.UsuarioDTO;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.adapters.in.controller.dto.request.RegisterRequest;

public interface UsuarioUseCases {
    Usuario findUsuarioLogado(String email);
    UsuarioDTO createUsuario(RegisterRequest request, MultipartFile file);
    UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, MultipartFile file);
}
