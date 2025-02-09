package yago.ferreira.marketapi.domain.port.out.repository;

import yago.ferreira.marketapi.domain.model.DomainUserDetails;
import yago.ferreira.marketapi.domain.model.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario domainObj);
    DomainUserDetails findByEmail(String email);
    Usuario findUsuarioByEmail(String email);
}
