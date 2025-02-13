package yago.ferreira.marketapi.adapters.out.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import yago.ferreira.marketapi.adapters.out.entities.JpaProduto;
import yago.ferreira.marketapi.adapters.in.controller.dto.ProdutoDTO;
import yago.ferreira.marketapi.domain.model.Produto;
import yago.ferreira.marketapi.adapters.in.controller.dto.response.PageResponse;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    ProdutoDTO toDto(Produto produto);

    Produto toDomainEntity(ProdutoDTO produtoDTO);

    JpaProduto toJpaEntity(Produto domainObj);

    Produto toDomain(JpaProduto jpaEntity);

    // @Todo o erro de build talvez seja pelo nome dos atributos
    PageResponse<Produto> toPageResponse(Page<JpaProduto> jpaEntity);

    default Page<ProdutoDTO> toPageDTO(PageResponse<Produto> page) {
        List<ProdutoDTO> content = page.getContent()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(
                content,
                PageRequest.of(page.getPage(), page.getSize()),
                page.getTotalElements()
        );
    }
}
