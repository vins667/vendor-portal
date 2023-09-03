package io.vamani.application.mssql.repository;

import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.model.EmployeeViewBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the EmployeeView entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("partnerTransactionManager")
@PersistenceContext(name = "partnerEntityManagerFactory")
public interface EmployeeViewRepository extends JpaRepository<EmployeeView, String> {

    Optional<EmployeeView> findByCardNo(String cardNo);

    @Query("select employeeView from EmployeeView employeeView where EXTRACT (day FROM employeeView.dob) = ?1 AND EXTRACT (month FROM employeeView.dob) = ?2") // and employeeView.subSname=?3
    List<EmployeeView> getBirthdayList(int dayOfMonth, int month, String subCode);

    @Query("select employeeView from EmployeeView employeeView where EXTRACT (day FROM employeeView.doj) = ?1 AND EXTRACT (month FROM employeeView.doj) = ?2 AND EXTRACT (year FROM employeeView.doj) < ?3") // and employeeView.subSname=?3
    List<EmployeeView> getAnniversaryList(int dayOfMonth, int month, int year);

    @Query("select employeeView from EmployeeView employeeView where employeeView.cardNo like ?1 and employeeView.name like ?2 and employeeView.depCodeDesc like ?3 and employeeView.desCodeDesc like ?4")
    Page<EmployeeView> findAllByFilter(String empCode, String name, String department, String designation, Pageable pageable);

    @Query("select employeeView.empCode,employeeView.name from EmployeeView employeeView where employeeView.cardNo like ?1 and employeeView.name like ?2 ")
    Page<Object[]> findByEmpByfilter(String empCode, String name, Pageable pageable);

    @Query("select employeeView from EmployeeView employeeView where employeeView.cardNo like ?1 and employeeView.name like ?2 and employeeView.depCodeDesc like ?3 and employeeView.desCodeDesc like ?4 and employeeView.factory like ?5")
    Page<EmployeeView> findAllByFilter(String empCode, String name, String department, String designation, String factory, Pageable pageable);

    @Query("select employeeView from EmployeeView employeeView where employeeView.login in(?1)")
    List<EmployeeView> findAllByLogins(List<String> logins);

    @Query("select employeeView from EmployeeMatView employeeView where employeeView.cardNo in(?1) order by employeeView.cardNo")
    List<EmployeeMatView> findAllCardNo(List cardNos);

    @Query("select employeeView from EmployeeView employeeView where employeeView.cardNo in(?1) order by employeeView.cardNo")
    List<EmployeeView> findAllEmployeeCardNo(List cardNos);

    @Query("select employeeView from EmployeeView employeeView where employeeView.cardNo not in(?1) and employeeView.totSal>?2 order by employeeView.cardNo")
    List<EmployeeView> findAllEmployeeCardNoNotIn(List cardNos, double totSal);

    @Query("select employeeView from EmployeeMatView employeeView where employeeView.cardNo like ?1 and employeeView.cardNo not in(?2) and employeeView.totSal>?3")
    Page<EmployeeMatView> findAllCardNoNotFill(String cardNo, List cardNos, Double totSal, Pageable pageable);

    @Query("select employeeView from EmployeeMatView employeeView where employeeView.cardNo like ?1 and employeeView.cardNo not in(?2) and employeeView.totSal>?3 and employeeView.resignDate is null")
    Page<EmployeeMatView> findAllCardNoNotFillAndNotResign(String cardNo, List cardNos, Double totSal, Pageable pageable);

    @Query("select employeeView from EmployeeView employeeView where employeeView.totSal>?1 order by employeeView.cardNo")
    List<EmployeeView> findAllCardNosByTotalSalary(Double totSal);

    @Query("select employeeView from EmployeeView employeeView where employeeView.supervisor like ?1")
    List<EmployeeView> findAllBySupervisor(String superviser);

    @Query("select employeeView from EmployeeView employeeView where employeeView.searchText like ?1 order by employeeView.name")
    List<EmployeeView> findAllBySmartSearch(String searchText);

    @Query("select employeeView.cardNo,employeeView.name,dayStatus.status, dayStatus.id.dayno from EmployeeView employeeView, DayStatus dayStatus where employeeView.empCode= dayStatus.id.empCode and dayStatus.id.dayno between ?1 and ?2 and dayStatus.id.dayno < ?3 and dayStatus.status in('H1', 'H2', 'A', 'MS')")
// and  employeeView.cardNo in (?4)
    List<Object[]> findAllByCardNoAndDate(Timestamp dateTo, Timestamp dateFrom, Timestamp now);

    @Query(value = "select A.dayno, A.dep_code, A.dep_code_desc depCodeDesc, A.des_code, A.des_code_desc desCodeDesc,A.sub_sname subSname,"
        + "SUM(A.PRESENT_COUNT_ONROLL)pcountOnroll, SUM(A.ABSENT_COUNT_ONROLL)abcountOnroll, "
        + "SUM(A.PRESENT_COUNT_CUTPIS)pcountCutpic,  SUM(A.ABSENT_COUNT_CUTPIS)abcountCutpic, A.factory_desc "
        + "FROM (select ev.emp_code, ev.sub_sname, ev.dep_code, ev.des_code, ev.dep_code_desc, ev.des_code_desc, dv.dayno,  dv.status, ev.factory_desc, "
        + "COALESCE(case when dv.status='P' AND COALESCE(ev.sub_sname,'')<>'VAMANI-1'  then 1 else 0 end,'') PRESENT_COUNT_ONROLL,  "
        + "COALESCE(case when dv.status='A' AND COALESCE(ev.sub_sname,'')<>'VAMANI-1'  then 1 else 0 end,'') ABSENT_COUNT_ONROLL,  "
        + "COALESCE(case when dv.status='P' AND COALESCE(ev.sub_sname,'')='VAMANI-1'  then 1 else 0 end,'') PRESENT_COUNT_CUTPIS,  "
        + "COALESCE(case when dv.status='A' AND COALESCE(ev.sub_sname,'')='VAMANI-1'  then 1 else 0 end,'') ABSENT_COUNT_CUTPIS  "
        + "from day_status_view dv, employee_mat_view ev where dv.emp_code = ev.emp_code and  "
        + " dv.dayno  between ?1 and ?2 and ev.factory=?3) A  "
        + "GROUP BY A.dayno, A.sub_sname, A.dep_code_desc, A.des_code_desc, A.dep_code, A.des_code, A.factory_desc "
        + "ORDER BY A.dayno, A.dep_code_desc, A.des_code_desc, A.factory_desc ", nativeQuery = true)
    List<Object[]> GetAllMMRAttnByDeptCode(Instant dateFrom, Instant dateTo, String factoryCode);

    @Query(value = "select employeeAllView.card_no as col_0_0_, employeeAllView.name as col_1_0_, employeeAllView.dep_code_desc as col_2_0_, employeeAllView.des_code_desc as col_3_0_, employeeAllView.sw_code as col_4_0_, employeeAllView.pay_code as col_5_0_, dayStatus.status as col_6_0_, dayStatus.dayno, case when dayStatus.status in('MS', 'P', 'OD', 'od1', 'od2', 'H1', 'H2') then 1 else 2 end from employee_mat_view employeeAllView inner join day_status_view dayStatus on (employeeAllView.emp_code=dayStatus.emp_code) where dayStatus.dayno = ?1 and employeeAllView.factory=?2 AND employeeAllView.sub_code = ?3 AND employeeAllView.sec_code=?4 order by case when dayStatus.status in('MS', 'P', 'OD', 'od1', 'od2') then 1 else 2 end, dayStatus.status, employeeAllView.card_no", nativeQuery = true)
    List<Object[]> getStatusAndDepart(String dateFrom, String factory, String subComp, String line);

    @Query(value=" SELECT ev.dep_code_desc, ev.des_code_desc, coalesce( CASE WHEN ev.sw_code = 2 THEN 1 ELSE 0 END, 0) staff, coalesce( CASE WHEN ev.sw_code = 3 THEN 1 ELSE 0 END, 0) worker,"
        + " coalesce( CASE WHEN ev.sw_code = 2 THEN dv.sal ELSE 0 END, 0) staff_sal, coalesce( CASE WHEN ev.sw_code = 3 THEN dv.sal ELSE 0 END, 0) worker_sal, ev.factory, coalesce( CASE WHEN ev.sw_code = 2 and ev.pfallowed<>0 THEN 1 ELSE 0 END, 0) staff_pf_amt,"
        + " coalesce( CASE WHEN ev.sw_code = 3 and ev.pfallowed<>0 THEN 1 ELSE 0 END, 0) worker_pf_amt, coalesce( CASE WHEN ev.sw_code = 2 THEN dv.ot_amt ELSE 0 END, 0) staff_ot_amt, coalesce( CASE WHEN ev.sw_code = 3 THEN dv.ot_amt ELSE 0 END, 0) worker_ot_amt,"
        + " coalesce( CASE WHEN ev.sw_code = 2 and ev.esicut<>0 THEN 1 ELSE 0 END, 0) staff_esi_amt, coalesce( CASE WHEN ev.sw_code = 3 and ev.esicut<>0 THEN 1 ELSE 0 END, 0) worker_esi_amt, coalesce( CASE WHEN ev.sw_code = 2 THEN dv.basic_sal1 ELSE 0 END, 0) staff_sal_basic,"
        + " coalesce( CASE WHEN ev.sw_code = 3 THEN dv.basic_sal1 ELSE 0 END, 0) worker_sal_basic, coalesce( CASE WHEN ev.sw_code = 2 THEN dv.food_amt ELSE 0 END, 0) staff_food_amt, coalesce( CASE WHEN ev.sw_code = 3 THEN dv.food_amt ELSE 0 END, 0) worker_food_amt, ev.card_no, ev.name "
        + " FROM employee_full_view ev,  day_status_view dv  WHERE ev.emp_code = dv.emp_code AND ev.factory = ?1 AND ev.sub_code = ?2 AND ev.sec_code=?3 AND dv.dayno = ?4 order by ev.dep_code_desc, ev.card_no",nativeQuery=true)
    List<Object[]> getCTC29(String factory, String subComp, String line, String dateFrom);

    @Query(value="SELECT"
        +" ev.card_no, ev.name, ev.des_code_desc,ev.dep_code_desc, CASE WHEN ev.sw_code = 2 then 'Staff' else 'Worker' end cadre,"
        +" ev.doj, dv.wday, dv.pday, dv.basic_rate, dv.gross_rate, dv.basic_sal1, dv.sal, dv.othr, dv.ot_amt, dv.food_amt, ev.pfallowed, ev.esicut, ev.sub_dept_desc"
        +" FROM employee_full_view ev,  day_status_view dv  WHERE ev.emp_code = dv.emp_code AND ev.factory = ?1 AND ev.sub_code = ?2 AND ev.sec_code=?3 AND dv.dayno = ?4 order by ev.dep_code_desc, ev.card_no",nativeQuery=true)
    List<Object[]> getCTC29Details(String factory, String subComp, String line, String dateFrom);

    @Query("select employeeView from EmployeeView employeeView where employeeView.login =?1 order by employeeView.cardNo")
    EmployeeView findByLogin(String login);

    @Query("select employeeView from EmployeeView employeeView where employeeView.factory =?1 and employeeView.email is not null order by employeeView.cardNo")
    List<EmployeeView> findByFactoryAndEmailNotNull(String factory);

    @Query(value="SELECT DES_CODE, DES_CODE_DESC, DEP_CODE, DEP_CODE_DESC, CAT_CODE, CAT_NAME, COALESCE(COUNT(*), 0)"
        + " FROM EMPLOYEE_FULL_VIEW ES, DAY_STATUS DS WHERE ES.EMP_CODE = DS.EMP_CODE AND DS.IN_TM<>'' AND FACTORY=?1 AND DEP_CODE in(?2) AND convert(varchar, DS.DAYNO, 23)=?3"
        + " AND R_DATE IS NULL GROUP BY DES_CODE, DES_CODE_DESC, DEP_CODE, DEP_CODE_DESC, CAT_CODE, CAT_NAME ORDER BY 2,3,6", nativeQuery = true)
    List<Object[]> fetchManPower(String factoryCode, List<String> departmentCode, String date);

    @Query("select employeeView from EmployeeView  employeeView where employeeView.totSal>=?1 and employeeView.doj between  ?2 and ?3")
    List<EmployeeView> findAllEmployeeBySalary(Double salary, ZonedDateTime dateFrom, ZonedDateTime dateTo);
}
