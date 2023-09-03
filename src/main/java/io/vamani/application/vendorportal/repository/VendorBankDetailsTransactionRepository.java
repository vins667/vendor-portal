package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorBankDetailsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorBankDetailsTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorBankDetailsTransactionRepository extends JpaRepository<VendorBankDetailsTransaction, Long> {
    @Query("select vendorBankDetailsTransaction from VendorBankDetailsTransaction vendorBankDetailsTransaction where vendorBankDetailsTransaction.vendorId=?1 and vendorBankDetailsTransaction.approvedDate is null")
    VendorBankDetailsTransaction findByVendorId(Long vendorId);
}
