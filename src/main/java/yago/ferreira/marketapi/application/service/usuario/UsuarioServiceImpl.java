package yago.ferreira.marketapi.application.service.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.UsuarioDTO;
import yago.ferreira.marketapi.adapters.in.controller.dto.request.RegisterRequest;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.adapters.in.controller.mappers.FileMapper;
import yago.ferreira.marketapi.adapters.out.entities.JpaAvatar;
import yago.ferreira.marketapi.adapters.out.entities.JpaUsuario;
import yago.ferreira.marketapi.adapters.out.mappers.UsuarioMapper;
import yago.ferreira.marketapi.adapters.out.repository.JpaUsuarioRepository;
import yago.ferreira.marketapi.application.service.file.FileService;
import yago.ferreira.marketapi.domain.exceptions.EmailAlreadyExistsException;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.domain.port.in.usecases.UsuarioUseCases;

@Service
public class UsuarioServiceImpl implements UsuarioUseCases {

    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final FileMapper fileMapper;
    private final FileService fileService;

    public UsuarioServiceImpl(JpaUsuarioRepository jpaUsuarioRepository, UsuarioMapper usuarioMapper, FileMapper fileMapper, FileService fileService) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.fileMapper = fileMapper;
        this.fileService = fileService;
    }

    @Override
    public Usuario findUsuarioLogado(String email) {
        return usuarioMapper.toDomainEntity(jpaUsuarioRepository.findUsuarioByEmail(email));
    }

    @Override
    public Usuario createUsuario(RegisterRequestDomain domainObj, FileInput fileDomain) {
        RegisterRequest request = usuarioMapper.toRegisterRequestEntity(domainObj);
        MultipartFile file = fileMapper.toMultipartFile(fileDomain);

        UserDetails usuarioExistente = jpaUsuarioRepository.findByEmail(request.getEmail());

        if (usuarioExistente != null) {
            throw new EmailAlreadyExistsException();
        }

        JpaUsuario jpaUsuario = usuarioMapper.registerToUser(request);
        String senhaEncrypted = new BCryptPasswordEncoder().encode(request.getSenha());

        jpaUsuario.setSenha(senhaEncrypted);

        if (file != null) {
            jpaUsuario.setAvatar(getAvatar(file, jpaUsuario));
        }

        return usuarioMapper.toDomainEntity(jpaUsuarioRepository.save(jpaUsuario));
    }

    @Override
    public Usuario updateUsuario(Usuario usuarioDomain, FileInput fileDomain) {
        UsuarioDTO usuarioDTO = usuarioMapper.domainToDto(usuarioDomain);
        MultipartFile file = fileMapper.toMultipartFile(fileDomain);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((Usuario) auth.getPrincipal()).getEmail();

        JpaUsuario usuarioAtualJpa = jpaUsuarioRepository.findUsuarioByEmail(email);

        usuarioAtualJpa.setNome(usuarioDTO.getNome());
        usuarioAtualJpa.setEmail(usuarioDTO.getEmail());

        if (file != null) {
            usuarioAtualJpa.setAvatar(getAvatarAtualizado(file, usuarioAtualJpa));
        }

        return usuarioMapper.toDomainEntity(jpaUsuarioRepository.save(usuarioAtualJpa));
    }

    private JpaAvatar getAvatarAtualizado(MultipartFile file, JpaUsuario jpaUsuario) {
        if (file.isEmpty()) {
            jpaUsuario.setAvatar(new JpaAvatar());
            return null;
        }

        FileResponse fileResponse = fileService.storeAvatar(file);

        JpaAvatar usuarioAvatar = new JpaAvatar();

        if (jpaUsuario.getAvatar() != null) {
            usuarioAvatar = jpaUsuario.getAvatar();
        }

        usuarioAvatar.setNome(fileResponse.getFileName());
        usuarioAvatar.setFilePath(fileResponse.getFilePath());
        usuarioAvatar.setUsuario(jpaUsuario);
        return usuarioAvatar;
    }

    private JpaAvatar getAvatar(MultipartFile file, JpaUsuario jpaUsuario) {
        FileResponse fileResponse = fileService.storeAvatar(file);

        JpaAvatar usuarioAvatar = new JpaAvatar();
        usuarioAvatar.setNome(fileResponse.getFileName());
        usuarioAvatar.setFilePath(fileResponse.getFilePath());
        usuarioAvatar.setUsuario(jpaUsuario);
        return usuarioAvatar;
    }

}
