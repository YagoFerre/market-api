package yago.ferreira.marketapi.adapters.out.mappers;

import org.mapstruct.Mapper;
import yago.ferreira.marketapi.adapters.in.controller.dto.UsuarioDTO;
import yago.ferreira.marketapi.adapters.in.controller.dto.request.RegisterRequest;
import yago.ferreira.marketapi.adapters.out.entities.JpaUsuario;
import yago.ferreira.marketapi.domain.model.RegisterRequestDomain;
import yago.ferreira.marketapi.domain.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario domainObj);

    UsuarioDTO domainToDto(Usuario domainObj);

    Usuario toDomainEntity(JpaUsuario jpaEntity);

    Usuario toUsuarioDomain(UsuarioDTO dto);

    RegisterRequestDomain toRegisterRequestDomain(RegisterRequest request);

    JpaUsuario toJpaEntity(Usuario domainObj);

    JpaUsuario toJpa(UsuarioDTO usuarioDTO);

    Usuario registerToUser(RegisterRequestDomain domainObj);

}
