package io.vamani.application.repository;

import io.vamani.application.domain.TdsComputation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TdsComputation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsComputationRepository extends JpaRepository<TdsComputation, Long> {
    @Query("select tdsComputation from TdsComputation tdsComputation where tdsComputation.cardNo=?1 and tdsComputation.financialYear=?2")
    TdsComputation findByCardNoAndYear(String cardNo, String year);

    @Query("select tdsComputation from TdsComputation tdsComputation where (tdsComputation.cardNo like ?1 or tdsComputation.name like ?2) and tdsComputation.financialYear=?3")
    Page<TdsComputation> findAllByCardNo(String cardNo, String name, String year, Pageable pageable);

    @Query("select tdsComputation.cardNo,tdsComputation.totalTaxLiability,tdsComputation.balanceTaxValue,tdsComputation.totalTaxIncome,tdsComputation.taxDeductValue, tdsComputation.rentExempt, tdsComputation.pendingMonth, tdsComputation.deductCode1, tdsComputation.deductAmount1, tdsComputation.deductCode2, tdsComputation.deductAmount2, tdsComputation.deductCode3, tdsComputation.deductAmount3, tdsComputation.deductCode4, tdsComputation.deductAmount4, tdsComputation.deductCode5, tdsComputation.deductAmount5, tdsComputation.deductCode6, tdsComputation.deductAmount6 from TdsComputation tdsComputation where tdsComputation.financialYear = ?1 order by tdsComputation.cardNo")
    List<Object> findAllTdsComputaion(String financialYear);
}
