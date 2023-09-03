package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.domain.EmployeeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the EmployeeView entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface EmployeeMatViewRepository extends JpaRepository<EmployeeMatView, String> {

    Optional<EmployeeMatView> findByCardNo(String cardNo);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where EXTRACT (day FROM employeeMatView.dob) = ?1 AND EXTRACT (month FROM employeeMatView.dob) = ?2 and employeeMatView.subSname=?3")
    List<EmployeeMatView> getBirthdayList(int dayOfMonth, int month, String subCode);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where EXTRACT (day FROM employeeMatView.doj) = ?1 AND EXTRACT (month FROM employeeMatView.doj) = ?2 and employeeMatView.subSname=?3")
    List<EmployeeMatView> getAnniversaryList(int dayOfMonth, int month, String subCode);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.cardNo like ?1 and employeeMatView.name like ?2 and employeeMatView.depCodeDesc like ?3 and employeeMatView.desCodeDesc like ?4")
    Page<EmployeeMatView> findAllByFilter(String empCode, String name, String department, String designation, Pageable pageable);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.cardNo like ?1 and employeeMatView.name like ?2 and employeeMatView.depCodeDesc like ?3 and employeeMatView.desCodeDesc like ?4 and employeeMatView.factory like ?5")
    Page<EmployeeMatView> findAllByFilter(String empCode, String name, String department, String designation, String factory, Pageable pageable);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.login in(?1)")
    List<EmployeeMatView> findAllByLogins(List<String> logins);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.cardNo in(?1) order by employeeMatView.cardNo")
    List<EmployeeMatView> findAllCardNo(List cardNos);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.cardNo like ?1 and employeeMatView.cardNo not in(?2) and employeeMatView.totSal>?3")
    Page<EmployeeMatView> findAllCardNoNotFill(String cardNo, List cardNos, Double totSal, Pageable pageable);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.totSal>?1 order by employeeMatView.cardNo")
    List<EmployeeMatView> findAllCardNosByTotalSalary(Double totSal);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.supervisor like ?1")
    List<EmployeeMatView> findAllBySupervisor(String superviser);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.searchText like ?1 order by employeeMatView.name")
    List<EmployeeMatView> findAllBySmartSearch(String searchText);

    @Query("select employeeMatView.cardNo,employeeMatView.name,dayStatus.status, dayStatus.id.dayno from EmployeeMatView employeeMatView, DayStatus dayStatus where employeeMatView.empCode= dayStatus.id.empCode and dayStatus.id.dayno between ?1 and ?2 and dayStatus.id.dayno < ?3 and dayStatus.status in('H1', 'H2', 'A', 'MS')")// and  employeeMatView.cardNo in (?4)
    List<Object[]> findAllByCardNoAndDate(Timestamp dateTo, Timestamp dateFrom, Timestamp now);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where employeeMatView.login = ?1")
    EmployeeMatView findByLogins( String logins);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where (employeeMatView.resignDate is null or employeeMatView.resignDate = employeeMatView.doj or employeeMatView.resignDate >= ?1) and employeeMatView.totSal>=?2 and employeeMatView.factory in(?3) order by employeeMatView.cardNo")
    List<EmployeeMatView> findAllByJoiningAndTotalSalary(ZonedDateTime zonedDateTime, Double totSal, List<String> factories);

    @Query("select employeeMatView from EmployeeMatView employeeMatView where (employeeMatView.resignDate is null or employeeMatView.resignDate = employeeMatView.doj or employeeMatView.resignDate >= ?1) and employeeMatView.totSal>=?2 order by employeeMatView.cardNo")
    List<EmployeeMatView> findAllByJoiningAndTotalSalary(ZonedDateTime zonedDateTime, Double totSal);
}
