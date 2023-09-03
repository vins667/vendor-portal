package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.Monthly;
import io.vamani.application.mssql.domain.MonthlyFinance;
import io.vamani.application.mssql.domain.MonthlyFinanceId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface MonthlyFinanceRepository extends JpaRepository<MonthlyFinance, MonthlyFinanceId> {
    @Query("select monthlyFinance from MonthlyFinance monthlyFinance where monthlyFinance.id.empCode=?1 and monthlyFinance.id.monthYear<>?2 order by monthlyFinance.year, monthlyFinance.month")
    List<MonthlyFinance> findAllByEmpCode(String empCode, String monthYear);

    @Query("select monthlyFinance from MonthlyFinance monthlyFinance, EmployeeMatView employeeView  where monthlyFinance.id.empCode = employeeView.empCode and employeeView.pan = ?1 and monthlyFinance.id.monthYear<>?2 order by monthlyFinance.year, monthlyFinance.month")
    List<MonthlyFinance> findAllByPan(String pan, String monthYear);
}
