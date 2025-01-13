package yago.ferreira.marketapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.entity.Produto;
import yago.ferreira.marketapi.service.produto.ProdutoService;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarProduto(
            @RequestPart("produto") @Valid Produto produto,
            @RequestPart("imagens") List<MultipartFile> imagens) {
        produtoService.criarProduto(produto, imagens);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable @Positive Long id) {
        Produto produtoEncontrado = produtoService.listProdutoById(id);
        return ResponseEntity.ok().body(produtoEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable @NotNull @Positive Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
