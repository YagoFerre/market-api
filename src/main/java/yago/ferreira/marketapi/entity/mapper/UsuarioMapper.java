package yago.ferreira.marketapi.entity.mapper;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.entity.dto.UsuarioDTO;
import yago.ferreira.marketapi.entity.request.RegisterRequest;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNome(usuario.getNome());
        return usuarioDTO;
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario();
    }

    public Usuario registerToEntity(RegisterRequest request) {
        if (request == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNome());
        usuario.setSenha(request.getSenha());
        return usuario;
    }

}
