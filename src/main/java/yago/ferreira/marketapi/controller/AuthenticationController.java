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
import yago.ferreira.marketapi.entity.dto.AuthenticationDTO;
import yago.ferreira.marketapi.entity.dto.LoginResponseDTO;
import yago.ferreira.marketapi.entity.dto.RegisterDTO;
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
    public ResponseEntity createUser(@RequestBody @Valid RegisterDTO registerDTO) {
        UserDetails usuarioExistente = usuarioRepository.findByEmail(registerDTO.getEmail());

        if (usuarioExistente != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaEncrypted = new BCryptPasswordEncoder().encode(registerDTO.getSenha());

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(registerDTO.getEmail());
        novoUsuario.setNome(registerDTO.getNome());
        novoUsuario.setSenha(senhaEncrypted);

        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
