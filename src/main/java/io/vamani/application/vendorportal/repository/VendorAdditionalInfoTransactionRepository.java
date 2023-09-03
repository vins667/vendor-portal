package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorAdditionalInfoTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorAdditionalInfoTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorAdditionalInfoTransactionRepository extends JpaRepository<VendorAdditionalInfoTransaction, Long> {
    @Query("select vendorAdditionalInfoTransaction from VendorAdditionalInfoTransaction vendorAdditionalInfoTransaction where vendorAdditionalInfoTransaction.vendorId=?1 and vendorAdditionalInfoTransaction.approvedDate is null")
    VendorAdditionalInfoTransaction findByVendorId(Long vendorId);
}
