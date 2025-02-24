package yago.ferreira.marketapi.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.adapters.in.controller.dto.UsuarioDTO;
import yago.ferreira.marketapi.adapters.in.controller.dto.request.RegisterRequest;
import yago.ferreira.marketapi.adapters.in.service.UsuarioService;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUser(
            @RequestPart("usuario") @Valid RegisterRequest registerRequest,
            @RequestPart(value = "avatar", required = false) MultipartFile avatar
    ) throws IOException {
        return ResponseEntity.ok(usuarioService.createUsuario(registerRequest, avatar));
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioDTO> updateUser(
            @RequestPart("usuario") @Valid UsuarioDTO usuarioDTO,
            @RequestPart(value = "avatar", required = false) MultipartFile avatar
    ) throws IOException {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuarioDTO, avatar));
    }
}
