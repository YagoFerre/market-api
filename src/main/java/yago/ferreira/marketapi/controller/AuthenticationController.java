package yago.ferreira.marketapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.entity.request.AuthenticationRequest;
import yago.ferreira.marketapi.entity.response.LoginResponse;
import yago.ferreira.marketapi.entity.request.RegisterRequest;
import yago.ferreira.marketapi.repository.UsuarioRepository;
import yago.ferreira.marketapi.service.security.TokenService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody @Valid RegisterRequest registerRequest) {
        UserDetails usuarioExistente = usuarioRepository.findByEmail(registerRequest.getEmail());

        if (usuarioExistente != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaEncrypted = new BCryptPasswordEncoder().encode(registerRequest.getSenha());

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(registerRequest.getEmail());
        novoUsuario.setNome(registerRequest.getNome());
        novoUsuario.setSenha(senhaEncrypted);

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
