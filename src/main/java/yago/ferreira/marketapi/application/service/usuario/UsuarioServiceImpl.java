package yago.ferreira.marketapi.application.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.adapters.out.mappers.UsuarioMapper;
import yago.ferreira.marketapi.adapters.out.repository.JpaUsuarioRepository;
import yago.ferreira.marketapi.application.service.file.FileService;
import yago.ferreira.marketapi.domain.model.Avatar;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.domain.port.in.usecases.UsuarioUseCases;

@Service
public class UsuarioServiceImpl implements UsuarioUseCases {

    private final JpaUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final FileService fileService;

    public UsuarioServiceImpl(JpaUsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, FileService fileService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.fileService = fileService;
    }

    @Override
    public Usuario findUsuarioLogado(String email) {
        return null;
    }

    @Override
    public Usuario createUsuario(RegisterRequestDomain request, FileInput file) {
        return null;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario, FileInput file) {
        return null;
    }

    private Avatar getAvatarAtualizado(MultipartFile file, Usuario usuario) {
        if (file.isEmpty()) {
            usuario.setAvatar(new Avatar());
            return null;
        }

        FileResponse fileResponse = fileService.storeAvatar(file);

        Avatar usuarioAvatar = new Avatar();

        if (usuario.getAvatar() != null) {
            usuarioAvatar = usuario.getAvatar();
        }

        usuarioAvatar.setNome(fileResponse.getFileName());
        usuarioAvatar.setFilePath(fileResponse.getFilePath());
        usuarioAvatar.setUsuario(usuario);
        return usuarioAvatar;
    }

    private Avatar getAvatar(MultipartFile file, Usuario usuario) {
        FileResponse fileResponse = fileService.storeAvatar(file);

        Avatar usuarioAvatar = new Avatar();
        usuarioAvatar.setNome(fileResponse.getFileName());
        usuarioAvatar.setFilePath(fileResponse.getFilePath());
        usuarioAvatar.setUsuario(usuario);
        return usuarioAvatar;
    }

}
