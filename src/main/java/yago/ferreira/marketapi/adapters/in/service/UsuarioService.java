package yago.ferreira.marketapi.adapters.in.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.UsuarioDTO;
import yago.ferreira.marketapi.adapters.in.controller.dto.request.RegisterRequest;
import yago.ferreira.marketapi.adapters.out.mappers.FileMapper;
import yago.ferreira.marketapi.adapters.out.mappers.UsuarioMapper;
import yago.ferreira.marketapi.application.service.usuario.UsuarioServiceImpl;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;

@Service
public class UsuarioService {

    private final UsuarioServiceImpl usuarioServiceImpl;
    private final UsuarioMapper usuarioMapper;
    private final FileMapper fileMapper;

    public UsuarioService(UsuarioServiceImpl usuarioServiceImpl, UsuarioMapper usuarioMapper, FileMapper fileMapper) {
        this.usuarioServiceImpl = usuarioServiceImpl;
        this.usuarioMapper = usuarioMapper;
        this.fileMapper = fileMapper;
    }

    @Transactional
    public UsuarioDTO createUsuario(RegisterRequest request, MultipartFile multipartFile) {
        RegisterRequestDomain registerRequestDomain = usuarioMapper.toRegisterRequestDomain(request);
        FileInput fileInputDomain = fileMapper.toFileInputDomain(multipartFile);
        Usuario usuarioDomain = usuarioServiceImpl.executeCreateUsuario(registerRequestDomain, fileInputDomain);
        return usuarioMapper.toDto(usuarioDomain);
    }

    @Transactional
    public UsuarioDTO updateUsuario(UsuarioDTO dto, MultipartFile multipartFile) {
        Usuario usuarioDomain = usuarioMapper.toUsuarioDomain(dto);
        FileInput fileInputDomain = fileMapper.toFileInputDomain(multipartFile);
        return usuarioMapper.toDto(usuarioServiceImpl.executeUpdateUsuario(usuarioDomain, fileInputDomain));
    }

    public UsuarioDTO findUsuarioLogado(String email) {
        Usuario domainObj = usuarioServiceImpl.executeFindUsuarioLogado(email);
        return usuarioMapper.toDto(domainObj);
    }

}
