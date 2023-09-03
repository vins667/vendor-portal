package io.vamani.application.repository;

import io.vamani.application.domain.PaymentRequestInvoice;
import io.vamani.application.domain.PaymentRequestInvoiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data SQL repository for the PaymentRequestInvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestInvoiceRepository extends JpaRepository<PaymentRequestInvoice, PaymentRequestInvoiceId> {
    @Modifying
    @Transactional
    @Query("delete from PaymentRequestInvoice paymentRequestInvoice where paymentRequestInvoice.id.paymentRequestFormId = ?1")
    void deleteByPaymentRequestFormId(Long paymentRequestFormId);

    @Query("select paymentRequestInvoice from PaymentRequestInvoice paymentRequestInvoice where paymentRequestInvoice.id.paymentRequestFormId = ?1")
    List<PaymentRequestInvoice> findByPaymentRequestFormId(Long paymentRequestFormId);
}
