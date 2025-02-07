package yago.ferreira.marketapi.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yago.ferreira.marketapi.domain.entity.Usuario;
import yago.ferreira.marketapi.domain.request.AuthenticationRequest;
import yago.ferreira.marketapi.domain.response.LoginResponse;
import yago.ferreira.marketapi.application.service.security.TokenService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
