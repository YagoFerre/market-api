package yago.ferreira.marketapi.adapters.in.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.UsuarioDTO;
import yago.ferreira.marketapi.adapters.in.controller.dto.request.RegisterRequest;
import yago.ferreira.marketapi.adapters.out.mappers.FileMapper;
import yago.ferreira.marketapi.adapters.out.mappers.UsuarioMapper;
import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.domain.port.in.usecases.UsuarioUseCases;

@Service
public class UsuarioService {

    private final UsuarioUseCases usuarioUseCases;
    private final UsuarioMapper usuarioMapper;
    private final FileMapper fileMapper;

    public UsuarioService(UsuarioUseCases usuarioUseCases, UsuarioMapper usuarioMapper, FileMapper fileMapper) {
        this.usuarioUseCases = usuarioUseCases;
        this.usuarioMapper = usuarioMapper;
        this.fileMapper = fileMapper;
    }

    @Transactional
    public UsuarioDTO createUsuario(RegisterRequest request, MultipartFile multipartFile) {
        RegisterRequestDomain registerRequestDomain = usuarioMapper.toRegisterRequestDomain(request);
        FileInput fileInputDomain = fileMapper.toFileInputDomain(multipartFile);
        Usuario usuarioDomain = usuarioUseCases.executeCreateUsuario(registerRequestDomain, fileInputDomain);
        return usuarioMapper.toDto(usuarioDomain);
    }

    @Transactional
    public UsuarioDTO updateUsuario(UsuarioDTO dto, MultipartFile multipartFile) {
        Usuario usuarioDomain = usuarioMapper.toUsuarioDomain(dto);
        FileInput fileInputDomain = fileMapper.toFileInputDomain(multipartFile);
        return usuarioMapper.toDto(usuarioUseCases.executeUpdateUsuario(usuarioDomain, fileInputDomain));
    }

}
