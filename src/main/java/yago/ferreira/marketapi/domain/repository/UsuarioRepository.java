package yago.ferreira.marketapi.domain.repository;

import yago.ferreira.marketapi.domain.entity.DomainUserDetails;
import yago.ferreira.marketapi.domain.entity.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario domainObj);
    DomainUserDetails findByEmail(String email);
    Usuario findUsuarioByEmail(String email);
}
