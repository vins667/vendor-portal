package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.EmpHist;
import io.vamani.application.mssql.domain.EmpHistId;
import io.vamani.application.mssql.domain.Timet;
import io.vamani.application.mssql.domain.TimetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the Timet entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface EmpHistRepository extends JpaRepository<EmpHist, EmpHistId> {
    @Query("select empHist from EmpHist empHist where empHist.id.empCode=?1 and empHist.id.monthYear=?2 order by empHist.id.monthYear")
    EmpHist findByEmpCodeAndMonthYear(String empCode, String monthYear);
}
