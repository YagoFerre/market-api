package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yago.ferreira.marketapi.adapters.out.entities.JpaProduto;

@Repository
public interface JpaProdutoRepository extends JpaRepository<JpaProduto, Long> {
    Page<JpaProduto> findAllByUsuarioEmail(@Param("email") String email, Pageable pageable);
}
