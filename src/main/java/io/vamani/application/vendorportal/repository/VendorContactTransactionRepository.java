package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorContactTransaction;
import io.vamani.application.vendorportal.domain.VendorTaxRegistrationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorContactTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorContactTransactionRepository extends JpaRepository<VendorContactTransaction, Long> {
    @Query("select vendorContactTransaction from VendorContactTransaction vendorContactTransaction where vendorContactTransaction.vendorId=?1 and vendorContactTransaction.approvedDate is null")
    VendorContactTransaction findByVendorId(Long vendorId);


    @Query("select vendorTaxRegistrationTransaction from VendorTaxRegistrationTransaction vendorTaxRegistrationTransaction where vendorTaxRegistrationTransaction.vendorId=?1 and vendorTaxRegistrationTransaction.approvedDate is null")
    VendorTaxRegistrationTransaction findByTaxVendorId(Long vendorId);
}
