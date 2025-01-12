package yago.ferreira.marketapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.entity.Produto;
import yago.ferreira.marketapi.service.produto.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarProduto(
            @RequestPart("produto") Produto produto,
            @RequestPart("imagens") List<MultipartFile> imagens) {
        produtoService.criarProduto(produto, imagens);

        return ResponseEntity.ok().build();
    }
}
