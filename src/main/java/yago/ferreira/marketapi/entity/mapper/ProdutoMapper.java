package yago.ferreira.marketapi.entity.mapper;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.entity.Produto;
import yago.ferreira.marketapi.entity.dto.FileDTO;
import yago.ferreira.marketapi.entity.dto.ProdutoDTO;
import yago.ferreira.marketapi.entity.dto.UsuarioDTO;

import java.util.List;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDTO(Produto produto) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                produto.getUsuario().getNome(),
                produto.getUsuario().getEmail()
        );

        List<FileDTO> filesDTO = produto.getProdutoImagem()
                .stream()
                .map(file -> new FileDTO(file.getId(), file.getNome(), file.getFilePath()))
                .toList();

        return new ProdutoDTO(
                produto.getId(),
                produto.getTitulo(),
                produto.getDescricao(),
                produto.getPreco(),
                usuarioDTO,
                filesDTO
        );
    }

    public Produto toEntity(ProdutoDTO produtoDTO) {
        return new Produto();
    }

}
