package yago.ferreira.marketapi.application.service.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.FileResponse;
import yago.ferreira.marketapi.adapters.out.encoder.PasswordEncoderImpl;
import yago.ferreira.marketapi.adapters.out.entities.JpaUsuario;
import yago.ferreira.marketapi.application.service.file.FileServiceImpl;
import yago.ferreira.marketapi.domain.exceptions.EmailAlreadyExistsException;
import yago.ferreira.marketapi.domain.model.File;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.domain.port.in.usecases.UsuarioUseCases;
import yago.ferreira.marketapi.domain.port.out.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioUseCases {

    private final UsuarioRepository usuarioRepository;
    private final FileServiceImpl fileServiceImpl;
    private final PasswordEncoderImpl passwordEncoderImpl;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, FileServiceImpl fileServiceImpl, PasswordEncoderImpl passwordEncoderImpl) {
        this.usuarioRepository = usuarioRepository;
        this.fileServiceImpl = fileServiceImpl;
        this.passwordEncoderImpl = passwordEncoderImpl;
    }

    @Override
    public Usuario executeFindUsuarioLogado(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario executeCreateUsuario(RegisterRequestDomain domainObj, FileInput fileDomain) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(domainObj.getEmail());

        if (usuarioExistente != null) {
            throw new EmailAlreadyExistsException();
        }

        domainObj.setSenha(passwordEncoderImpl.encode(domainObj.getSenha()));

        if (fileDomain != null) {
            domainObj.setAvatar(getAvatar(fileDomain));
        }

        return usuarioRepository.save(Usuario.criar(domainObj));
    }

    @Override
    public Usuario executeUpdateUsuario(Usuario usuarioDomain, FileInput fileDomain) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((JpaUsuario) auth.getPrincipal()).getEmail();

        Usuario usuarioAtual = usuarioRepository.findByEmail(email);

        usuarioAtual.setNome(usuarioDomain.getNome());
        usuarioAtual.setEmail(usuarioDomain.getEmail());

        if (fileDomain != null) {
            usuarioAtual.setAvatar(getAvatarAtualizado(fileDomain, usuarioAtual));
        }

        return usuarioRepository.save(usuarioAtual);
    }

    private File getAvatarAtualizado(FileInput file, Usuario usuario) {
        if (file.getBytes().length == 0) {
            usuario.setAvatar(new File());
            return null;
        }

        FileResponse fileResponse = fileServiceImpl.storeAvatar(file);

        File usuarioAvatar = new File();

        if (usuario.getAvatar() != null) {
            usuarioAvatar = usuario.getAvatar();
        }

        usuarioAvatar.setNome(fileResponse.getFileName());
        usuarioAvatar.setFilePath(fileResponse.getFilePath());
        return usuarioAvatar;
    }

    private File getAvatar(FileInput file) {
        if (file.getBytes().length == 0) {
            return null;
        }

        FileResponse fileResponse = fileServiceImpl.storeAvatar(file);

        File usuarioAvatar = new File();
        usuarioAvatar.setNome(fileResponse.getFileName());
        usuarioAvatar.setFilePath(fileResponse.getFilePath());
        return usuarioAvatar;
    }

}
