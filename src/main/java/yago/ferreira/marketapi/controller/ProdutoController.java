package yago.ferreira.marketapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.entity.dto.ProdutoDTO;
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
    public ResponseEntity<ProdutoDTO> salvarProduto(
            @RequestPart("produto") @Valid ProdutoDTO produtoDTO,
            @RequestPart("imagens") List<MultipartFile> imagens) {
        return ResponseEntity.ok().body(produtoService.criarProduto(produtoDTO, imagens));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(
            @PathVariable @Positive Long id,
            @RequestPart("produto") @Valid ProdutoDTO produtoDTO,
            @RequestPart("imagens") List<MultipartFile> imagens
    ) {
        return ResponseEntity.ok().body(produtoService.atualizarProduto(id, produtoDTO, imagens));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable @Positive Long id) {
        ProdutoDTO produtoEncontrado = produtoService.listProdutoById(id);
        return ResponseEntity.ok().body(produtoEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable @NotNull @Positive Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
