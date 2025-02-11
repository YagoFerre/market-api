package yago.ferreira.marketapi.adapters.out.mappers;

import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import yago.ferreira.marketapi.adapters.in.controller.dto.request.RegisterRequest;
import yago.ferreira.marketapi.adapters.out.entities.JpaUsuario;
import yago.ferreira.marketapi.adapters.in.controller.dto.UsuarioDTO;
import yago.ferreira.marketapi.domain.model.DomainUserDetails;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    JpaUsuario toJpa(UsuarioDTO usuarioDTO);

    UsuarioDTO toDto(JpaUsuario jpaUsuario);

    UsuarioDTO domainToDto(Usuario domainObj);

    JpaUsuario toJpaEntity(Usuario domainObj);

    Usuario toDomainEntity(JpaUsuario jpaEntity);

    DomainUserDetails toDomainUserDetails(UserDetails userDetails);

    RegisterRequest toRegisterRequestEntity(RegisterRequestDomain domainObj);

    JpaUsuario registerToUser(RegisterRequest request);

}
