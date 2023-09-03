package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Woff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the EmployeeView entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface WoffRepository extends JpaRepository<Woff, Long> {
    @Query("select woff from Woff woff order by woff.wCode")
    List<Woff> orderedAll();
}
