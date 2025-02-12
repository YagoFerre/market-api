package yago.ferreira.marketapi.application.service.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.adapters.out.mappers.FileMapper;
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

@Component
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
    public Usuario executeFindUsuarioLogado(String email) {
        return usuarioMapper.toDomainEntity(jpaUsuarioRepository.findUsuarioByEmail(email));
    }

    @Override
    public Usuario executeCreateUsuario(RegisterRequestDomain domainObj, FileInput fileDomain) {
        MultipartFile file = fileMapper.toMultipartFile(fileDomain);

        UserDetails usuarioExistente = jpaUsuarioRepository.findByEmail(domainObj.getEmail());

        if (usuarioExistente != null) {
            throw new EmailAlreadyExistsException();
        }

        JpaUsuario jpaUsuario = usuarioMapper.registerToUser(domainObj);
        String senhaEncrypted = new BCryptPasswordEncoder().encode(domainObj.getSenha());

        jpaUsuario.setSenha(senhaEncrypted);

        if (file != null) {
            jpaUsuario.setAvatar(getAvatar(file, jpaUsuario));
        }

        return usuarioMapper.toDomainEntity(jpaUsuarioRepository.save(jpaUsuario));
    }

    @Override
    public Usuario executeUpdateUsuario(Usuario usuarioDomain, FileInput fileDomain) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((Usuario) auth.getPrincipal()).getEmail();

        JpaUsuario usuarioAtualJpa = jpaUsuarioRepository.findUsuarioByEmail(email);

        usuarioAtualJpa.setNome(usuarioDomain.getNome());
        usuarioAtualJpa.setEmail(usuarioDomain.getEmail());

        if (fileDomain != null) {
            usuarioAtualJpa.setAvatar(getAvatarAtualizado(fileMapper.toMultipartFile(fileDomain), usuarioAtualJpa));
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
