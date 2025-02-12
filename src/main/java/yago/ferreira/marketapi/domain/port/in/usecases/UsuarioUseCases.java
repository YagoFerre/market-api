package yago.ferreira.marketapi.domain.port.in.usecases;

import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;

public interface UsuarioUseCases {
    Usuario executeFindUsuarioLogado(String email);
    Usuario executeCreateUsuario(RegisterRequestDomain domainObj, FileInput file);
    Usuario executeUpdateUsuario(Usuario domainObj, FileInput file);
}
