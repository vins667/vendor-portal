package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Cost;
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
public interface CostRepository extends JpaRepository<Cost, Long> {
    @Query("select cost from Cost cost order by cost.desc1")
    List<Cost> orderedAll();
}
