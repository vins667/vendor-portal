package io.vamani.application.repository;

import io.vamani.application.domain.PaymentRequestFormDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the PaymentRequestFormDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestFormDetailsRepository extends JpaRepository<PaymentRequestFormDetails, Long> {
    @Query("select paymentRequestFormDetails from PaymentRequestFormDetails paymentRequestFormDetails where paymentRequestFormDetails.paymentRequestFormId = ?1 and paymentRequestFormDetails.forwardCode is not null order by paymentRequestFormDetails.id")
    List<PaymentRequestFormDetails> findAllByPaymentRequestFormId(Long id);


    @Query("select paymentRequestFormDetails from PaymentRequestFormDetails paymentRequestFormDetails where paymentRequestFormDetails.paymentRequestFormId = ?1 order by paymentRequestFormDetails.id")
    List<PaymentRequestFormDetails> findAllWSByPaymentRequestFormId(Long id);

    @Query("select paymentRequestFormDetails from PaymentRequestFormDetails paymentRequestFormDetails where paymentRequestFormDetails.paymentRequestFormId = ?1 and paymentRequestFormDetails.forwardCode is null order by paymentRequestFormDetails.id")
    PaymentRequestFormDetails findByPaymentRequestFormId(Long id);
}
