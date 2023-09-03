package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.EmployeeAllView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the EmployeeAllView entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface EmployeeAllViewRepository extends JpaRepository<EmployeeAllView, String> {
    @Query("select distinct employeeAllView.depCode, employeeAllView.depCodeDesc from EmployeeAllView employeeAllView")
    List<Object[]> getDistinctDeptCode();

    @Query("select distinct employeeAllView.desCode, employeeAllView.desCodeDesc from EmployeeAllView employeeAllView")
    List<Object[]> getDistinctDesignCode();

    @Query("select distinct employeeAllView.depCode, employeeAllView.depCodeDesc, employeeAllView.desCode, employeeAllView.desCodeDesc, employeeAllView.swCode, employeeAllView.factory from EmployeeAllView employeeAllView where employeeAllView.factory is not null")
    List<Object[]> getDistinctDeptCodeAndDesignCode();

    @Query("select employeeAllView from EmployeeAllView employeeAllView where employeeAllView.cardNo like ?1 and employeeAllView.name like ?2 and employeeAllView.adhNo like ?3 and employeeAllView.pan like ?4 and employeeAllView.phone like ?5 and employeeAllView.rdate is null")
    Page<EmployeeAllView> findAllByActive(String empCode, String name, String aadhar, String panNo,String mobileNo, Pageable pageable);

    @Query("select employeeAllView from EmployeeAllView employeeAllView where employeeAllView.cardNo like ?1 and employeeAllView.name like ?2 and employeeAllView.adhNo like ?3 and employeeAllView.pan like ?4 and employeeAllView.phone like ?5 and employeeAllView.rdate is not null")
    Page<EmployeeAllView> findAllByResigned(String empCode, String name, String aadhar, String panNo,String mobileNo, Pageable pageable);

}
