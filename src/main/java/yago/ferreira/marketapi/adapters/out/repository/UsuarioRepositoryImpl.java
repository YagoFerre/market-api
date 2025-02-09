package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.security.core.userdetails.UserDetails;
import yago.ferreira.marketapi.adapters.out.entities.JpaUsuario;
import yago.ferreira.marketapi.domain.entity.DomainUserDetails;
import yago.ferreira.marketapi.domain.entity.Usuario;
import yago.ferreira.marketapi.domain.repository.UsuarioRepository;
import yago.ferreira.marketapi.utils.mapper.UsuarioMapper;

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
    public DomainUserDetails findByEmail(String email) {
        UserDetails userDetailsSpring = jpaUsuarioRepository.findByEmail(email);
        return usuarioMapper.toDomainUserDetails(userDetailsSpring);
    }

    @Override
    public Usuario findUsuarioByEmail(String email) {
        JpaUsuario jpaEntity = jpaUsuarioRepository.findUsuarioByEmail(email);
        return usuarioMapper.toDomainEntity(jpaEntity);
    }
}
