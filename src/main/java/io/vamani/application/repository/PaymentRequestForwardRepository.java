package io.vamani.application.repository;

import io.vamani.application.domain.PaymentRequestForward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the PaymentRequestForward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestForwardRepository extends JpaRepository<PaymentRequestForward, Long> {
    @Query("select paymentRequestForward from PaymentRequestForward paymentRequestForward where (paymentRequestForward.empCode like ?1 or paymentRequestForward.empName like ?2) and (paymentRequestForward.forwardCode like ?3 or paymentRequestForward.forwardName like ?4)")
    Page<PaymentRequestForward> findAllByEmpCodeAndForwardCode(String empCode, String empName, String forwardCode, String forwardName, Pageable pageable);

    @Query("select paymentRequestForward from PaymentRequestForward paymentRequestForward where paymentRequestForward.empCode = ?1 and paymentRequestForward.flag = ?2")
    List<PaymentRequestForward> findAllByEmpCodeAndForwardType(String empCode, String type);

    @Query(value="SELECT DISTINCT FLAG, CASE WHEN FLAG='F' THEN 'FORWARD' WHEN FLAG = 'A' THEN 'APPROVED' ELSE 'REJECTED' END DESCRIPTION FROM PAYMENT_REQUEST_FORWARD WHERE EMP_CODE=?1", nativeQuery = true)
    List<Object[]> findAllByForward(String empCode);
}
