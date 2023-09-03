package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorContact;
import io.vamani.application.vendorportal.domain.VendorTaxRegistration;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorContact entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorContactRepository extends JpaRepository<VendorContact, Long> {
    @Query("select vendorContact from VendorContact vendorContact where vendorContact.vendorId=?1")
	VendorContact findByVendorId(Long vendorId);
    @Query("select vendorTaxRegistration from VendorTaxRegistration vendorTaxRegistration where vendorTaxRegistration.vendorId=?1")
    VendorTaxRegistration findByTaxVendorId(Long vendorId);
}
