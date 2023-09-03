package io.vamani.application.repository;

import io.vamani.application.domain.EmployeeInformationUpdate;
import io.vamani.application.domain.MobileAttendance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data  repository for the EmployeeInformationUpdate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeInformationUpdateRepository extends JpaRepository<EmployeeInformationUpdate, Long> {

    @Query("select employeeInformationUpdate from EmployeeInformationUpdate employeeInformationUpdate where employeeInformationUpdate.userCode.login = ?1")
    Page<EmployeeInformationUpdate> findAll(String empCode, Pageable pageable);

    @Query("select employeeInformationUpdate from EmployeeInformationUpdate employeeInformationUpdate where employeeInformationUpdate.userCode.login like ?1 and employeeInformationUpdate.flag='E' and employeeInformationUpdate.factory = ?2")
    Page<EmployeeInformationUpdate> findAllByHrApprovedByPending(String empCode, String factory, Pageable var1);

    @Query("select employeeInformationUpdate from EmployeeInformationUpdate employeeInformationUpdate where employeeInformationUpdate.createdDate between ?1 and ?2 and employeeInformationUpdate.userCode.login like ?3 and employeeInformationUpdate.flag in ('C') and employeeInformationUpdate.factory = ?4")
    Page<EmployeeInformationUpdate> findAllByHrApprovedByApproved(Instant dateFrom, Instant dateTo, String empCode, String factory, Pageable var1);

    @Query("select employeeInformationUpdate from EmployeeInformationUpdate employeeInformationUpdate where employeeInformationUpdate.createdDate between ?1 and ?2 and employeeInformationUpdate.userCode.login like ?3 and employeeInformationUpdate.flag=?4 and employeeInformationUpdate.factory = ?5")
    Page<EmployeeInformationUpdate> findAllByHrApprovedByRejected(Instant dateFrom, Instant dateTo, String empCode, String flag, String factory, Pageable var1);


    @Query("select employeeInformationUpdate from EmployeeInformationUpdate employeeInformationUpdate where employeeInformationUpdate.userCode.login=?1 and employeeInformationUpdate.flag ='E'")
    List<EmployeeInformationUpdate> findAllByEmpCode(String empCode);

    @Query("select employeeInformationUpdate from EmployeeInformationUpdate employeeInformationUpdate where employeeInformationUpdate.flag ='C' and employeeInformationUpdate.processFlag is null")
    List<EmployeeInformationUpdate> findAllUnProcess();

}
