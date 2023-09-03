package io.vamani.application.repository;

import io.vamani.application.domain.BillRegisterImportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data  repository for the BillRegisterImportDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillRegisterImportDetailsRepository extends JpaRepository<BillRegisterImportDetails, Long> {

    @Query("select billRegisterDetails from BillRegisterImportDetails billRegisterDetails where billRegisterDetails.billRegisterImport.id = ?1 order by billRegisterDetails.id")
    List<BillRegisterImportDetails> findAllByBillRegisterMaster(Long id);

    @Query("select coalesce(sum(billRegisterDetails.quantity), 0) from BillRegisterImport billRegisterImport "
        + " inner join BillRegisterImportDetails billRegisterDetails on billRegisterDetails.billRegisterImport.id = billRegisterImport.id"
        + " where billRegisterImport.billtype = ?1 and billRegisterDetails.code = ?2 and billRegisterDetails.orderdate = ?3")
    BigDecimal totalByBilltypeAndBillcode(String billtype, String code, LocalDate orderDate);

    @Modifying
    @Transactional
    @Query("delete from BillRegisterImportDetails billRegisterDetails where billRegisterDetails.billRegisterImport.id = ?1")
    void deleteAllByBillRegisterMaster(Long id);
}
