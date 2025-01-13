package yago.ferreira.marketapi.entity.mapper;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.entity.dto.UsuarioDTO;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario();
    }

}
