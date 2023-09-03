package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Subdept;
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
public interface SubdeptRepository extends JpaRepository<Subdept, Long> {
    @Query("select subdept from Subdept subdept order by subdept.sdepCode")
    List<Subdept> orderedAll();
}
