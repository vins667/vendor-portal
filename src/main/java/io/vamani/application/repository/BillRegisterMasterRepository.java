package io.vamani.application.repository;

import io.vamani.application.domain.BillRegisterMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data  repository for the BillRegisterMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillRegisterMasterRepository extends JpaRepository<BillRegisterMaster, Long> {
    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.billtype like ?1 and billRegister.billnumber like ?2 and (billRegister.customersuppliercode like ?3 or upper(billRegister.customersuppliername) like ?4)")
    Page<BillRegisterMaster> findAllByBillnumber(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.billdate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldate(String billtype, String invoicecode, Instant billDateFrom, Instant billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.submitDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateSub(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.receiveDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateRec(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and (billRegister.customersuppliercode like ?3 or upper(billRegister.customersuppliername) like ?4)")
    Page<BillRegisterMaster> findAllByBillnumberPending(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.billdate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldatePending(String billtype, String invoicecode, Instant billDateFrom, Instant billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.submitDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldatePendingSub(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.receiveDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldatePendingRec(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is not null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and (billRegister.customersuppliercode like ?3 or upper(billRegister.customersuppliername) like ?4)")
    Page<BillRegisterMaster> findAllByBillnumberSubmitted(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is not null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.billdate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateSubmitted(String billtype, String invoicecode, Instant billDateFrom, Instant billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is not null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.submitDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateSubmittedSub(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.receiveDate is not null and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.receiveDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateSubmittedRec(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.queryFlag = true and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and (billRegister.customersuppliercode like ?3 or upper(billRegister.customersuppliername) like ?4)")
    Page<BillRegisterMaster> findAllByBillnumberQuery(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.queryFlag = true and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.billdate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateQuery(String billtype, String invoicecode, Instant billDateFrom, Instant billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.queryFlag = true and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.submitDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateQuerySub(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.queryFlag = true and billRegister.billtype like ?1 and billRegister.billnumber like ?2 and billRegister.receiveDate between ?3 and ?4 and (billRegister.customersuppliercode like ?5 or upper(billRegister.customersuppliername) like ?6)")
    Page<BillRegisterMaster> findAllByBilldateQueryRec(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.billnumber = ?1 and billRegister.customersuppliercode = ?2 and billRegister.billdate between ?3 and ?4")
    List<BillRegisterMaster> findAllByBillNumberAndSupplierAndBilldate(String invoicecode, String suppliercode, LocalDate billDateFrom, LocalDate billDateTo);

    @Query("select billRegister from BillRegisterMaster billRegister where billRegister.billnumber = ?1 and billRegister.customersuppliercode = ?2 and billRegister.billdate between ?3 and ?4 and billRegister.id <> ?5")
    List<BillRegisterMaster> findAllByBillNumberAndSupplierAndBilldateAndNotId(String invoicecode, String suppliercode, LocalDate billDateFrom, LocalDate billDateTo, Long id);

}
