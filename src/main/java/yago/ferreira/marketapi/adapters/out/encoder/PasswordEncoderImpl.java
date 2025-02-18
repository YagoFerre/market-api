package yago.ferreira.marketapi.adapters.out.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import yago.ferreira.marketapi.domain.port.in.usecases.PasswordEncoderUseCases;

public class PasswordEncoderImpl implements PasswordEncoderUseCases {
    @Override
    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
