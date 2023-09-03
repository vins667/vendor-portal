package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.MonthlyDetail;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
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
public interface MonthlyDetailRepository extends JpaRepository<MonthlyDetail, Integer> {
    @Override
    @Query("select monthlyDetail from MonthlyDetail monthlyDetail order by monthlyDetail.monthNo")
    List<MonthlyDetail> findAll();
}
