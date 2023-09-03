package io.vamani.application.repository;

import io.vamani.application.domain.LeaveMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the LeaveMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveMasterRepository extends JpaRepository<LeaveMaster, Long> {

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login=?1 and ?2 between leaveMaster.leaveDateFrom and leaveMaster.leaveDateTo and leaveMaster.flag<>'R'")
    List<LeaveMaster> findByEmpCodeAndLeaveDate(String empCode, Instant instant);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login=?1 and leaveMaster.leaveDateFrom between ?2 and ?3 and leaveMaster.flag<>'R'")
    List<LeaveMaster> findByEmpCodeAndLeaveDateFrom(String empCode, Instant dateFrom, Instant dateTo);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login=?1 and leaveMaster.leaveDateTo between ?2 and ?3 and leaveMaster.flag<>'R'")
    List<LeaveMaster> findByEmpCodeAndLeaveDateTo(String empCode, Instant dateFrom, Instant dateTo);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login=?1")
    Page<LeaveMaster> findAllByEmpCode(String empCode, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login = ?1 and leaveMaster.leaveDateFrom between ?2 AND ?3 order by leaveMaster.leaveDateFrom")
    List<LeaveMaster> findAllByEmpCodeAndMonth(String empCode, Instant first, Instant last);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.hodApprovedBy = ?1 and leaveMaster.leaveDateFrom between ?2 AND ?3 and leaveMaster.flag<>'C' order by leaveMaster.leaveDateFrom")
    List<LeaveMaster> findAllByEmpCodeAndMonthApproval(String empCode, Instant first, Instant last);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.hodApprovedBy=?1 and leaveMaster.userCode.login like ?2 and leaveMaster.flag='E' ")
    Page<LeaveMaster> findAllByHodApprovedByPending(String hodApprovedBy, String empCode, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.hodApprovedBy=?1 and leaveMaster.leaveDateFrom between ?2 and ?3 and leaveMaster.userCode.login like ?4 and leaveMaster.flag in ('A','C')")
    Page<LeaveMaster> findAllByHodApprovedByApproved(String hodApprovedBy, Instant dateFrom, Instant dateTo, String empCode, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.hodApprovedBy=?1 and leaveMaster.leaveDateFrom between ?2 and ?3 and leaveMaster.userCode.login like ?4 and leaveMaster.flag=?5")
    Page<LeaveMaster> findAllByHodApprovedByRejected(String hodApprovedBy, Instant dateFrom, Instant dateTo, String empCode, String flag, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login like ?1 and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?2) and leaveMaster.flag='A' ")
    Page<LeaveMaster> findAllByHrApprovedByPending(String empCode, String factory, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.leaveDateFrom between ?6 and ?7 and leaveMaster.flag='A' ")
    Page<LeaveMaster> findAllByHrApprovedByAndLeaveDatePending(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.hodApprovedDate between ?6 and ?7 and leaveMaster.flag='A' ")
    Page<LeaveMaster> findAllByHrApprovedByAndApprovedDatePending(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.leaveDateFrom between ?1 and ?2 and leaveMaster.userCode.login like ?3 and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?4) and leaveMaster.flag='C' ")
    Page<LeaveMaster> findAllByHrApprovedByApproved(Instant dateFrom, Instant dateTo, String empCode, String factory, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.leaveDateFrom between ?6 and ?7 and leaveMaster.flag='C' ")
    Page<LeaveMaster> findAllByHrApprovedByAndLeaveDateApproved(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.hodApprovedDate between ?6 and ?7 and leaveMaster.flag='C' ")
    Page<LeaveMaster> findAllByHrApprovedByAndApprovedDateApproved(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.hrApprovedDate between ?6 and ?7 and leaveMaster.flag='C' ")
    Page<LeaveMaster> findAllByHrApprovedByAndHRApprovedDateApproved(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.leaveDateFrom between ?1 and ?2 order by leaveMaster.id")
    List<LeaveMaster> findAllByHrApprovedList(Instant dateFrom, Instant dateTo);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.leaveDateFrom between ?1 and ?2 and leaveMaster.userCode.login like ?3 and leaveMaster.flag='C' and leaveMaster.processFlag='Y' and leaveMaster.factory=?4")
    Page<LeaveMaster> findAllByHrApprovedByApprovedDirect(Instant dateFrom, Instant dateTo, String empCode, String factory, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.leaveDateFrom between ?1 and ?2 and leaveMaster.userCode.login like ?3 and leaveMaster.hrApprovedDate is not null and leaveMaster.flag='R' and leaveMaster.factory in (select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?4) ")
    Page<LeaveMaster> findAllByHrApprovedByRejected(Instant dateFrom, Instant dateTo, String empCode, String factory, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.leaveDateFrom between ?6 and ?7 and leaveMaster.flag='R' ")
    Page<LeaveMaster> findAllByHrApprovedByAndLeaveDateRejected(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.hodApprovedDate between ?6 and ?7 and leaveMaster.flag='R' ")
    Page<LeaveMaster> findAllByHrApprovedByAndApprovedDateRejected(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select leaveMaster from LeaveMaster leaveMaster left outer join User user on leaveMaster.hodApprovedBy = user.login where (leaveMaster.userCode.login like ?1 or leaveMaster.userCode.firstName like ?2) and (leaveMaster.hodApprovedBy like ?3 or user.firstName like ?4) and leaveMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?5) and leaveMaster.hrApprovedDate between ?6 and ?7 and leaveMaster.flag='R' ")
    Page<LeaveMaster> findAllByHrApprovedByAndHRRejectedDateRejected(String empCode, String empName, String hodCode, String hodName, String factory, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select sum(leaveMaster.noDays) from LeaveMaster leaveMaster where leaveMaster.userCode.login=?1 and leaveMaster.leaveTypeMaster.leaveCode=?2 and leaveMaster.flag NOT IN ('C','R', 'P')")
    Float getNoOfDays(String login, String leaveType);

    // CL and SL Check
    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login=?1 and ?2 between leaveMaster.leaveDateFrom and leaveMaster.leaveDateTo and leaveMaster.flag<>'R' and leaveMaster.leaveTypeMaster.leaveCode=?3 and leaveMaster.leaveSubTypeMaster.subTypeCode=?4")
    List<LeaveMaster> findByEmpCodeAndLeaveDateAndLeaveType(String empCode, Instant instant, String leaveType, String leaveSubType);

    // CL and SL Check
    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.userCode.login=?1 and ?2 between leaveMaster.leaveDateFrom and leaveMaster.leaveDateTo and leaveMaster.flag<>'R' and leaveMaster.leaveTypeMaster.leaveCode=?3")
    List<LeaveMaster> findByEmpCodeAndLeaveDateAndLeaveType(String empCode, Instant instant, String leaveType);

    @Override
    @Query("select leaveMaster from LeaveMaster leaveMaster left join fetch leaveMaster.mobileAttendances where leaveMaster.id = ?1")
    Optional<LeaveMaster> findById(Long aLong);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.flag = 'C' and leaveMaster.leaveDateFrom between ?1 and ?2 and leaveMaster.factory=?3")
    List<LeaveMaster> findAllByLeaveDate(Instant dateFrom, Instant dateTo, String factory);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.flag=?1 and leaveMaster.factory=?2")
    List<LeaveMaster> findAllByLeaveStatus(String flag, String factory);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.flag=?1 and leaveMaster.hodApprovedBy not in(select escapeEmployee.id.cardNo from EscapeEmployee escapeEmployee where escapeEmployee.id.type='LEAVE_PENDING')")
    List<LeaveMaster> findAllByLeaveStatusHOD(String flag);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.flag=?1")
    List<LeaveMaster> findAllByLeaveStatus(String flag);

    @Query("select leaveMaster from LeaveMaster leaveMaster where leaveMaster.flag=?1 and leaveMaster.leaveDateFrom <= ?2")
    List<LeaveMaster> findAllByLeaveStatus(String flag, Instant dateFrom);
}
