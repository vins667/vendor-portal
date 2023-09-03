package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the Grade entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query("select grade from Grade grade order by grade.desc1")
    List<Grade> orderedAll();
}
