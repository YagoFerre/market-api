package yago.ferreira.marketapi.domain.port.out.repository;

import yago.ferreira.marketapi.domain.model.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario domainObj);
    Usuario findByEmail(String email);
}
