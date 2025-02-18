package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.adapters.out.entities.JpaUsuario;
import yago.ferreira.marketapi.adapters.out.mappers.UsuarioMapper;
import yago.ferreira.marketapi.domain.model.Usuario;
import yago.ferreira.marketapi.domain.port.out.repository.UsuarioRepository;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepositoryImpl(JpaUsuarioRepository jpaUsuarioRepository, UsuarioMapper usuarioMapper) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario save(Usuario domainObj) {
        JpaUsuario jpaEntity = usuarioMapper.toJpaEntity(domainObj);
        return usuarioMapper.toDomainEntity(jpaUsuarioRepository.save(jpaEntity));
    }

    @Override
    public Usuario findByEmail(String email) {
        JpaUsuario jpaEntity = jpaUsuarioRepository.findByEmail(email);
        return usuarioMapper.toDomainEntity(jpaEntity);
    }
}
