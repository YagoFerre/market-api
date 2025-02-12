package yago.ferreira.marketapi.application.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yago.ferreira.marketapi.adapters.out.repository.JpaUsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final JpaUsuarioRepository jpaUsuarioRepository;

    public AuthorizationService(JpaUsuarioRepository jpaUsuarioRepository) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jpaUsuarioRepository.findByEmail(username);
    }
}
