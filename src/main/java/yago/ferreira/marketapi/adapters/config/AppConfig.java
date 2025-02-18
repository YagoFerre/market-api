package yago.ferreira.marketapi.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yago.ferreira.marketapi.adapters.out.encoder.PasswordEncoderImpl;
import yago.ferreira.marketapi.application.service.file.FileServiceImpl;
import yago.ferreira.marketapi.application.service.usuario.UsuarioServiceImpl;
import yago.ferreira.marketapi.domain.port.in.usecases.FileUseCases;
import yago.ferreira.marketapi.domain.port.in.usecases.UsuarioUseCases;
import yago.ferreira.marketapi.domain.port.out.repository.UsuarioRepository;

@Configuration
public class AppConfig {

    @Bean
    public UsuarioUseCases usuarioUseCases(UsuarioRepository usuarioRepository, FileServiceImpl fileServiceImpl, PasswordEncoderImpl passwordEncoder) {
        return new UsuarioServiceImpl(usuarioRepository, fileServiceImpl, passwordEncoder);
    }

}
