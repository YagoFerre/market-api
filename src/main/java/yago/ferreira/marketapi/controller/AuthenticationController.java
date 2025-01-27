package yago.ferreira.marketapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.entity.dto.UsuarioDTO;
import yago.ferreira.marketapi.entity.request.AuthenticationRequest;
import yago.ferreira.marketapi.entity.request.RegisterRequest;
import yago.ferreira.marketapi.entity.response.LoginResponse;
import yago.ferreira.marketapi.service.security.TokenService;
import yago.ferreira.marketapi.service.usuario.UsuarioService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(UsuarioService usuarioService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUser(
            @RequestPart("usuario") @Valid RegisterRequest registerRequest,
            @RequestPart("avatar") MultipartFile avatar
    ) {
        return ResponseEntity.ok(usuarioService.createUsuario(registerRequest, avatar));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
