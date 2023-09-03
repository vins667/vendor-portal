package io.vamani.application.repository;
import io.vamani.application.domain.PaymentRequestForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data  repository for the PaymentRequestForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestFormRepository extends JpaRepository<PaymentRequestForm, Long> {

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.requestDate between ?1 and ?2 and paymentRequestForm.status=?3 and (paymentRequestForm.supplierCode like ?4 or paymentRequestForm.supplierName like ?5) and paymentRequestForm.company like ?6 and paymentRequestForm.division like ?7 and paymentRequestForm.businessunitcode like ?8 and CAST(paymentRequestForm.id as text) like ?9")
    Page<PaymentRequestForm> findAllByRequestNoLike(Instant billDateFrom, Instant billDateTo, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, Pageable pageable);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.status = ?1 and (paymentRequestForm.supplierCode like ?2 or paymentRequestForm.supplierName like ?3) and paymentRequestForm.company like ?4 and paymentRequestForm.division like ?5 and paymentRequestForm.businessunitcode like ?6 and CAST(paymentRequestForm.id as text) like ?7")
    Page<PaymentRequestForm> findAllByRequestNoLike(String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, Pageable pageable);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.requestDate between ?1 and ?2 and paymentRequestForm.status=?3 and (paymentRequestForm.supplierCode like ?4 or paymentRequestForm.supplierName like ?5) and paymentRequestForm.company like ?6 and paymentRequestForm.division like ?7 and paymentRequestForm.businessunitcode like ?8 and CAST(paymentRequestForm.id as text) like ?9 and paymentRequestForm.forwardCode=?10")
    Page<PaymentRequestForm> findAllByRequestNoLike(Instant billDateFrom, Instant billDateTo, String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, String forwardCode, Pageable pageable);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.status = ?1 and (paymentRequestForm.supplierCode like ?2 or paymentRequestForm.supplierName like ?3) and paymentRequestForm.company like ?4 and paymentRequestForm.division like ?5 and paymentRequestForm.businessunitcode like ?6 and CAST(paymentRequestForm.id as text) like ?7 and paymentRequestForm.forwardCode=?8")
    Page<PaymentRequestForm> findAllByRequestNoLike(String flag, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, String id, String forwardCode, Pageable pageable);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.requestDate between ?1 and ?2 and (paymentRequestForm.supplierCode like ?3 or paymentRequestForm.supplierName like ?4) and paymentRequestForm.company like ?5 and paymentRequestForm.division like ?6 and paymentRequestForm.businessunitcode like ?7")
    Page<PaymentRequestForm> findAllByRequestNoLike(Instant billDateFrom, Instant billDateTo, String suppliercustomercode, String suppliercustomerdesc, String company, String division, String businessunitcode, Pageable pageable);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm ")
    List<PaymentRequestForm> getPaymentRequestReport();


    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.supplierCode = ?1 and paymentRequestForm.poNo = ?2")
    List<PaymentRequestForm> findAllByPONo(String supplierCode, String poNo);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.supplierCode = ?1 and paymentRequestForm.piNo = ?2")
    List<PaymentRequestForm> findAllByPINo(String supplierCode, String piNo);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.supplierCode = ?1 and paymentRequestForm.poNo = ?2 and paymentRequestForm.id <> ?3")
    List<PaymentRequestForm> findAllByPONoAndId(String supplierCode, String poNo, Long id);

    @Query("select paymentRequestForm from PaymentRequestForm paymentRequestForm where paymentRequestForm.supplierCode = ?1 and paymentRequestForm.piNo = ?2 and paymentRequestForm.id <> ?3")
    List<PaymentRequestForm> findAllByPINoAndId(String supplierCode, String piNo, Long id);

}
