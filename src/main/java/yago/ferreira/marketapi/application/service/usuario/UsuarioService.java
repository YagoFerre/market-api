package yago.ferreira.marketapi.application.service.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.domain.entity.Avatar;
import yago.ferreira.marketapi.domain.entity.Usuario;
import yago.ferreira.marketapi.domain.dto.UsuarioDTO;
import yago.ferreira.marketapi.utils.mapper.UsuarioMapper;
import yago.ferreira.marketapi.domain.request.RegisterRequest;
import yago.ferreira.marketapi.domain.response.FileResponse;
import yago.ferreira.marketapi.infra.config.exceptions.EmailAlreadyExistsException;
import yago.ferreira.marketapi.adapters.out.repository.JpaUsuarioRepository;
import yago.ferreira.marketapi.application.service.file.FileService;

@Service
public class UsuarioService {

    private final JpaUsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final FileService fileService;

    public UsuarioService(JpaUsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, FileService fileService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.fileService = fileService;
    }

    public Usuario findUsuarioLogado(String email) {
        return usuarioRepository.findUsuarioByEmail(email);
    }

    public UsuarioDTO createUsuario(RegisterRequest request, MultipartFile file) {
        UserDetails usuarioExistente = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioExistente != null) {
            throw new EmailAlreadyExistsException();
        }

        Usuario usuario = usuarioMapper.registerToEntity(request);
        String senhaEncrypted = new BCryptPasswordEncoder().encode(request.getSenha());

        usuario.setSenha(senhaEncrypted);

        if (file != null) {
            usuario.setAvatar(getAvatar(file, usuario));
        }

        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO, MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((Usuario) auth.getPrincipal()).getEmail();

        Usuario usuarioAtual = usuarioRepository.findUsuarioByEmail(email);

        usuarioAtual.setNome(usuarioDTO.getNome());
        usuarioAtual.setEmail(usuarioDTO.getEmail());

        if (file != null) {
            usuarioAtual.setAvatar(getAvatarAtualizado(file, usuarioAtual));
        }

        return usuarioMapper.toDTO(usuarioRepository.save(usuarioAtual));
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
