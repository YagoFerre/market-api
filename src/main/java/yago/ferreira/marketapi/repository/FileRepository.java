package yago.ferreira.marketapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yago.ferreira.marketapi.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
