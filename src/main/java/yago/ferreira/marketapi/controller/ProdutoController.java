package yago.ferreira.marketapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yago.ferreira.marketapi.entity.dto.ProdutoDTO;
import yago.ferreira.marketapi.service.produto.ProdutoService;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "15") int itens
    ) {
        return ResponseEntity.ok(produtoService.listarProdutos(pagina, itens).stream().collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable @Positive Long id) {
        ProdutoDTO produtoEncontrado = produtoService.listProdutoById(id);
        return ResponseEntity.ok().body(produtoEncontrado);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable @NotNull @Positive Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
