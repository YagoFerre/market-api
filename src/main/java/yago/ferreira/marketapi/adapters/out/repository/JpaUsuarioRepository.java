package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import yago.ferreira.marketapi.adapters.out.entities.JpaUsuario;

@Repository
public interface JpaUsuarioRepository extends JpaRepository<JpaUsuario, Long> {
    UserDetails findByEmail(String email);
    JpaUsuario findUsuarioByEmail(String email);
}
