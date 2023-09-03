package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Department;
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
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("select department from Department department order by department.depCode")
    List<Department> findAll();
}
