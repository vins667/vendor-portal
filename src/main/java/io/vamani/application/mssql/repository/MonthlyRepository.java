package io.vamani.application.mssql.repository;
import io.vamani.application.mssql.domain.Monthly;
import io.vamani.application.mssql.domain.MonthlyFinance;
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
public interface MonthlyRepository extends JpaRepository<Monthly, String> {

    @Query("select monthly from Monthly monthly where monthly.id.empCode=?1")
    Page<Monthly> findAllByEmpCode(String empCode, Pageable pageable);

    @Query("select monthly from Monthly monthly where monthly.id.empCode=?1 order by monthly.year, monthly.month")
    List<Monthly> findAllByEmpCode(String empCode);

    @Query("select monthly from Monthly monthly where monthly.id.empCode=?1 and monthly.id.monthYear=?2 order by monthly.id.monthYear")
    Monthly findByEmpCodeAndMonthYear(String empCode, String monthYear);

    @Query( value="select monthly.month_year,employeeView.card_no,employeeView.name,employeeView.doj,employeeView.pan,monthly.day_no,monthly.all1,monthly.all2, monthly.all3,monthly.all4, monthly.all5, monthly.all6, monthly.all7, monthly.earn1,monthly.earn2,monthly.earn3,monthly.earn4,monthly.earn5,monthly.earn6,monthly.earn7,(monthly.tot_sal+monthly.arr_amt) tot_sal,monthly.dall1,monthly.dall2,monthly.dall3,monthly.dall4, monthly.dall5, monthly.ded1,monthly.ded2, monthly.ded3, monthly.ded4, monthly.ded5, monthly.net_sal, monthly.arr1, monthly.arr2, monthly.arr3, monthly.arr4, monthly.arr5, monthly.arr6, monthly.arr7, employeeView.factory_desc, employeeView.sub_code_desc, employeeView.r_date  from monthly_view monthly join employee_mat_view employeeView on monthly.emp_code = employeeView.emp_code where employeeView.card_no like ?1 and CONVERT(date,('01-'+monthly.month_year),105) between ?2 and ?3 order by employeeView.sub_code_desc, employeeView.factory_desc, employeeView.card_no, monthly.year, monthly.month",nativeQuery = true)
    List<Object> findAllByMonths(String cardNo, String fromDate, String toDate);

    //@Query("select monthly.id.monthYear, employeeView.cardNo, employeeView.name, employeeView.doj, employeeView.pan, monthly.dayNo, monthly.all1, monthly.all2, monthly.all3, monthly.all4, monthly.all5, monthly.all6, monthly.earn1, monthly.earn2, monthly.earn3, monthly.earn4, monthly.earn5, monthly.earn6, monthly.totSal, monthly.dall1, monthly.dall2, monthly.dall3, monthly.dall4, monthly.dall5, monthly.ded1, monthly.ded2, monthly.ded3, monthly.ded4, monthly.ded5, monthly.netSal from Monthly monthly join EmployeeView employeeView on monthly.id.empCode = employeeView.empCode where employeeView.cardNo like ?1 and monthly.id.monthYear between ?2 and ?3 order by monthly.year, monthly.month")
    @Query( value="select monthly.month_year,employeeView.card_no,employeeView.name,employeeView.doj,employeeView.pan,monthly.day_no,monthly.all1,monthly.all2, monthly.all3,monthly.all4, monthly.all5, monthly.all6, monthly.all7, monthly.earn1,monthly.earn2,monthly.earn3,monthly.earn4,monthly.earn5,monthly.earn6,monthly.earn7,(monthly.tot_sal+monthly.arr_amt) tot_sal,monthly.dall1,monthly.dall2,monthly.dall3,monthly.dall4, monthly.dall5, monthly.ded1,monthly.ded2, monthly.ded3, monthly.ded4, monthly.ded5,monthly.net_sal, monthly.arr1, monthly.arr2, monthly.arr3, monthly.arr4, monthly.arr5, monthly.arr6, monthly.arr7, employeeView.factory_desc, employeeView.sub_code_desc, employeeView.r_date  from monthly_view monthly join employee_mat_view employeeView on monthly.emp_code = employeeView.emp_code where employeeView.card_no like ?1 and CONVERT(date,('01-'+monthly.month_year),105) between ?2 and ?3 and employeeView.factory = ?4 order by employeeView.sub_code_desc, employeeView.factory_desc, employeeView.card_no, monthly.year, monthly.month",nativeQuery = true)
    List<Object> findAllByMonths(String cardNo, String fromDate, String toDate, String factory);
}
