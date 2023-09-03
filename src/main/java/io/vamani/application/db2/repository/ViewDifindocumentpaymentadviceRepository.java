package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.ViewDifindocumentpaymentadvice;
import io.vamani.application.db2.domain.ViewDifindocumentpaymentadviceId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ViewDifindocumentpaymentadviceRepository extends JpaRepository<ViewDifindocumentpaymentadvice, ViewDifindocumentpaymentadviceId> {
    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.postingdate between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6)")
    Page<ViewDifindocumentpaymentadvice> findAllByPostingDateAndChequeNumber(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and CAST(viewDifindocumentpaymentadvice.utrdate as date) between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6)")
    Page<ViewDifindocumentpaymentadvice> findAllByUtrDateAndChequeNumber(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.chequenumber like ?2 and (viewDifindocumentpaymentadvice.customersuppliercode like ?3 or viewDifindocumentpaymentadvice.customersuppliername like ?4)")
    Page<ViewDifindocumentpaymentadvice> findAllByChequeNumber(String companycode, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.postingdate between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6) and viewDifindocumentpaymentadvice.advicesent = true")
    Page<ViewDifindocumentpaymentadvice> findAllByPostingDateAndChequeNumberSent(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and CAST(viewDifindocumentpaymentadvice.utrdate as date) between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6) and viewDifindocumentpaymentadvice.advicesent = true")
    Page<ViewDifindocumentpaymentadvice> findAllByUtrDateAndChequeNumberSent(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.chequenumber like ?2 and (viewDifindocumentpaymentadvice.customersuppliercode like ?3 or viewDifindocumentpaymentadvice.customersuppliername like ?4) and viewDifindocumentpaymentadvice.advicesent = true")
    Page<ViewDifindocumentpaymentadvice> findAllByChequeNumberSent(String companycode, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.postingdate between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6) and viewDifindocumentpaymentadvice.utrnumber is not null and viewDifindocumentpaymentadvice.emailaddress is not null and (viewDifindocumentpaymentadvice.advicesent is null or viewDifindocumentpaymentadvice.advicesent = false)")
    Page<ViewDifindocumentpaymentadvice> findAllByPostingDateAndChequeNumberUnsent(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and CAST(viewDifindocumentpaymentadvice.utrdate as date) between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6) and viewDifindocumentpaymentadvice.utrnumber is not null and viewDifindocumentpaymentadvice.emailaddress is not null and (viewDifindocumentpaymentadvice.advicesent is null or viewDifindocumentpaymentadvice.advicesent = false)")
    Page<ViewDifindocumentpaymentadvice> findAllByUtrDateAndChequeNumberUnsent(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.chequenumber like ?2 and (viewDifindocumentpaymentadvice.customersuppliercode like ?3 or viewDifindocumentpaymentadvice.customersuppliername like ?4) and viewDifindocumentpaymentadvice.utrnumber is not null and viewDifindocumentpaymentadvice.emailaddress is not null and viewDifindocumentpaymentadvice.advicesent is null")
    Page<ViewDifindocumentpaymentadvice> findAllByChequeNumberUnsent(String companycode, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.postingdate between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6) and viewDifindocumentpaymentadvice.utrnumber is not null and viewDifindocumentpaymentadvice.emailaddress is null")
    Page<ViewDifindocumentpaymentadvice> findAllByPostingDateAndChequeNumberMissing(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and CAST(viewDifindocumentpaymentadvice.utrdate as date) between ?2 and ?3 and viewDifindocumentpaymentadvice.chequenumber like ?4 and (viewDifindocumentpaymentadvice.customersuppliercode like ?5 or viewDifindocumentpaymentadvice.customersuppliername like ?6) and viewDifindocumentpaymentadvice.utrnumber is not null and viewDifindocumentpaymentadvice.emailaddress is null")
    Page<ViewDifindocumentpaymentadvice> findAllByUtrDateAndChequeNumberMissing(String companycode, Date dateForm, Date dateTo, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.chequenumber like ?2 and (viewDifindocumentpaymentadvice.customersuppliercode like ?3 or viewDifindocumentpaymentadvice.customersuppliername like ?4) and viewDifindocumentpaymentadvice.utrnumber is not null and viewDifindocumentpaymentadvice.emailaddress is null")
    Page<ViewDifindocumentpaymentadvice> findAllByChequeNumberMissing(String companycode, String chequeNumber, String supplierCode, String supplierName, Pageable pageable);

    @Query("select viewDifindocumentpaymentadvice from ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice where viewDifindocumentpaymentadvice.id.companycode = ?1 and viewDifindocumentpaymentadvice.chequenumber = ?2")
    List<ViewDifindocumentpaymentadvice> findAllByChequeNumber(String companycode, String chequeNumber);
}
