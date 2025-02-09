package yago.ferreira.marketapi.utils.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import yago.ferreira.marketapi.adapters.out.entities.JpaProduto;
import yago.ferreira.marketapi.adapters.in.controller.dto.ProdutoDTO;
import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.adapters.in.controller.response.PageResponse;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO toDto(JpaProduto jpaEntity);

    Produto toEntity(ProdutoDTO produtoDTO);

    JpaProduto toJpaEntity(Produto domainObj);

    Produto toDomain(JpaProduto jpaEntity);

    PageResponse<Produto> toPageResponse(Page<JpaProduto> jpaEntity);
}
