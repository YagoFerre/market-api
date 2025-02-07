package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yago.ferreira.marketapi.domain.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
