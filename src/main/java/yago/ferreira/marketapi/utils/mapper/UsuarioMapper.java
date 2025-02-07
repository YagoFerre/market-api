package yago.ferreira.marketapi.utils.mapper;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.domain.entity.Avatar;
import yago.ferreira.marketapi.domain.entity.Usuario;
import yago.ferreira.marketapi.domain.dto.AvatarDTO;
import yago.ferreira.marketapi.domain.dto.UsuarioDTO;
import yago.ferreira.marketapi.domain.request.RegisterRequest;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setAvatar(toAvatarDTO(usuario.getAvatar()));
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

    private AvatarDTO toAvatarDTO(Avatar avatar) {
        if (avatar == null) {
            return null;
        }

        AvatarDTO avatarDTO = new AvatarDTO();

        avatarDTO.setId(avatar.getId());
        avatarDTO.setNome(avatar.getNome());
        avatarDTO.setFilePath(avatar.getFilePath());
        return avatarDTO;
    }

    private Avatar toAvatarEntity(AvatarDTO avatarDTO) {
        if (avatarDTO == null) {
            return null;
        }

        Avatar avatar = new Avatar();
        avatar.setId(avatarDTO.getId());
        avatar.setNome(avatarDTO.getNome());
        avatar.setFilePath(avatarDTO.getFilePath());
        return avatar;
    }

}
