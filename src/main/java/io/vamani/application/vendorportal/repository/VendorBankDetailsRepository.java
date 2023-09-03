package io.vamani.application.vendorportal.repository;
import io.vamani.application.vendorportal.domain.VendorBankDetails;
import io.vamani.application.vendorportal.domain.VendorContact;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorBankDetails entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorBankDetailsRepository extends JpaRepository<VendorBankDetails, Long> {
	 @Query("select vendorBankDetails from VendorBankDetails vendorBankDetails where vendorBankDetails.vendorId=?1")
	 VendorBankDetails findByVendorId(Long vendorId);
}
