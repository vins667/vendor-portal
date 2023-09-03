package io.vamani.application.repository;

import io.vamani.application.domain.BillRegisterImport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data  repository for the BillRegisterImport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillRegisterImportRepository extends JpaRepository<BillRegisterImport, Long> {
    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<BillRegisterImport> findAllByBillnumber(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<BillRegisterImport> findAllByBillnumber(List<String> billtypes, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldate(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldate(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSub(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSub(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateRec(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateRec(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<BillRegisterImport> findAllByBillnumberPending(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<BillRegisterImport> findAllByBillnumberPending(List<String> billtypes, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldatePending(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldatePending(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldatePendingSub(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldatePendingSub(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldatePendingRec(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldatePendingRec(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<BillRegisterImport> findAllByBillnumberSubmitted(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<BillRegisterImport> findAllByBillnumberSubmitted(List<String> billtypes, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSubmitted(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSubmitted(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSubmittedSub(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSubmittedSub(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSubmittedRec(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<BillRegisterImport> findAllByBilldateSubmittedRec(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billnumber = ?1 and billRegisterImport.customersuppliercode = ?2 and billRegisterImport.billdate between ?3 and ?4")
    List<BillRegisterImport> findAllByBillNumberAndSupplierAndBilldate(String invoicecode, String suppliercode, LocalDate billDateFrom, LocalDate billDateTo);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billnumber = ?1 and billRegisterImport.customersuppliercode = ?2 and billRegisterImport.billdate between ?3 and ?4 and billRegisterImport.id <> ?5")
    List<BillRegisterImport> findAllByBillNumberAndSupplierAndBilldateAndNotId(String invoicecode, String suppliercode, LocalDate billDateFrom, LocalDate billDateTo, Long id);


    @Query("select billRegisterImport.billnumber, billRegisterImport.billdate, billRegisterImportDetail.projectcode, "
        + " billRegisterImportDetail.summarizeddescription, billRegisterImportDetail.userprimaryuomcode, "
        + " billRegisterImportDetail.quantity, billRegisterImportDetail.price, billRegisterImportDetail.quantity * billRegisterImportDetail.price as value"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetail on"
        + " billRegisterImport.id = billRegisterImportDetail.billRegisterImport.id"
        + " where billRegisterImport.billtype = 'SUPPLIER' and billRegisterImport.billnumber like ?1")
    Page<Object[]> findAllBySupplierCodeAndCILike(String code, Pageable pageable);



    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldatePendingForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldatePendingForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldatePendingSubForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldatePendingSubForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldatePendingRecForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldatePendingRecForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<Object[]> findAllByBillnumberPendingForReport(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<Object[]> findAllByBillnumberPendingForReport(List<String> billtypes, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<Object[]> findAllByBillnumberSubmittedForReport(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<Object[]> findAllByBillnumberSubmittedForReport(List<String> billtypes, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubmittedForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubmittedForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubmittedSubForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubmittedSubForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubmittedRecForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.receiveDate is not null and billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubmittedRecForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<Object[]> findAllByBillnumberForReport(String billtype, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and (billRegisterImport.customersuppliercode like ?3 or upper(billRegisterImport.customersuppliername) like ?4)")
    Page<Object[]> findAllByBillnumberForReport(List<String> billtypes, String invoicecode, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.billdate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.submitDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateSubForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype like ?1 and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateRecForReport(String billtype, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport.id, billRegisterImport.billdate, billRegisterImportDetails.summarizeddescription, billRegisterImport.customersuppliername, billRegisterImportDetails.projectcode,"
        + " billRegisterImport.billtype, billRegisterImport.billnumber, billRegisterImportDetails.orderdate, "
        + " billRegisterImportDetails.grossvalue, billRegisterImportDetails.quantity, billRegisterImportDetails.price, billRegisterImportFx.customersuppliername as customersuppliernamefx, billRegisterImportDetails.code, billRegisterImport.remarks"
        + " from BillRegisterImport billRegisterImport inner join BillRegisterImportDetails billRegisterImportDetails on billRegisterImport.id = billRegisterImportDetails.billRegisterImport.id "
        + " left outer join BillRegisterImport billRegisterImportFx on billRegisterImportDetails.code = billRegisterImportFx.billnumber and billRegisterImportDetails.orderdate = billRegisterImportFx.billdate"
        + " where billRegisterImport.billtype in (?1) and billRegisterImport.billnumber like ?2 and billRegisterImport.receiveDate between ?3 and ?4 and (billRegisterImport.customersuppliercode like ?5 or upper(billRegisterImport.customersuppliername) like ?6)")
    Page<Object[]> findAllByBilldateRecForReport(List<String> billtypes, String invoicecode, LocalDate billDateFrom, LocalDate billDateTo, String supplierCode, String supplierName, Pageable pageable);

    @Query("select billRegisterImport from BillRegisterImport billRegisterImport where billRegisterImport.billtype = 'SUPPLIER' and billRegisterImport.billnumber = ?1 and billRegisterImport.billdate = ?2")
    List<BillRegisterImport> findAllByBillnumberAndBillDate(String invoicecode, LocalDate billdate);

}
