package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.StaffWork;
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
public interface StaffWorkRepository extends JpaRepository<StaffWork, Long> {
    @Query("select staffWork from StaffWork staffWork order by staffWork.swDesc")
    List<StaffWork> orderedAll();
}
