package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.SalaryRate;
import io.vamani.application.mssql.domain.SalaryRateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the EmployeeView entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface SalaryRateRepository extends JpaRepository<SalaryRate, SalaryRateId> {
    @Query("select salaryRate from SalaryRate salaryRate where salaryRate.tdat in (select max(salaryRate.tdat) from SalaryRate salaryRate where salaryRate.id.empCode=?1) and salaryRate.id.empCode=?2")
    SalaryRate findByLastDetail(String empCode, String empCode1);
}
