package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Color;
import io.vamani.application.db2.domain.ColorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ColorRepository extends JpaRepository<Color, ColorId> {
    @Query("select color.id.code, color.longdescription from Color color where color.id.colorfoldercode = 'COL' and color.id.code in ?1 ")
    List<Object[]> findAllColorsByCodes(List<String> codes);
}
