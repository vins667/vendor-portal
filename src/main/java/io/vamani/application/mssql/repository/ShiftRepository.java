package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Timet entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("select shift from Shift shift order by shift.sftCode")
    List<Shift> orderedAll();

    @Query("select shift from Shift shift where shift.sftUcode = ?1")
    Shift findBySftUcode(String sftUcode);
}
