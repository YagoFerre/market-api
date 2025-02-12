package yago.ferreira.marketapi.adapters.out.mappers;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import yago.ferreira.marketapi.adapters.out.entities.JpaProduto;
import yago.ferreira.marketapi.adapters.in.controller.dto.ProdutoDTO;
import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO toDto(Produto produto);

    Produto toDomainEntity(ProdutoDTO produtoDTO);

    JpaProduto toJpaEntity(Produto domainObj);

    Produto toDomain(JpaProduto jpaEntity);

    PageResponse<Produto> toPageResponse(Page<JpaProduto> jpaEntity);

    Page<ProdutoDTO> toPageDTO(PageResponse<Produto> produtoPageResponse);
}
