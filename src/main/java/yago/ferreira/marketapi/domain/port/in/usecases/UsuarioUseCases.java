package yago.ferreira.marketapi.domain.port.in.usecases;

import yago.ferreira.marketapi.domain.model.FileInput;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;

public interface UsuarioUseCases {
    Usuario findUsuarioLogado(String email);
    Usuario createUsuario(RegisterRequestDomain request, FileInput file);
    Usuario updateUsuario(Usuario usuario, FileInput file);
}
