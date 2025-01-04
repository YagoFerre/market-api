package yago.ferreira.marketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yago.ferreira.marketapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
