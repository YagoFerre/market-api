package yago.ferreira.marketapi.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yago.ferreira.marketapi.adapters.out.entities.JpaFile;

@Repository
public interface JpaFileRepository extends JpaRepository<JpaFile, Long> {
}
