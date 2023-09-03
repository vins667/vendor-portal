package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Floor entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface FloorRepository extends JpaRepository<Floor, Long> {
    @Query("select floor from Floor floor order by floor.flDesc")
    List<Floor> orderedAll();
}
