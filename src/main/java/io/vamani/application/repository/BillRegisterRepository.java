package io.vamani.application.repository;

import io.vamani.application.domain.BillRegister;
import io.vamani.application.domain.BillRegister;
import io.vamani.application.model.BillRegisterBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;

/**
 * Spring Data SQL repository for the BillRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillRegisterRepository extends JpaRepository<BillRegister, Long> {
    @Query("select billRegister from BillRegister billRegister where billRegister.code = ?1")
    BillRegister findByCode(String code);

    @Query("select billRegister from BillRegister billRegister where billRegister.code like ?1")
    Page<BillRegister> findAllByInvoice(String invoicecode, Pageable pageable);

    @Query("select billRegister from BillRegister billRegister where billRegister.code like ?1 and billRegister.invoicedate between ?2 and ?3")
    Page<BillRegister> findAllByInvoicedate(String invoicecode, Instant billDateFrom, Instant billDateTo, Pageable pageable);
}
