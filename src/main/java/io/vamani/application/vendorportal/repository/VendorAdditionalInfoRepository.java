package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorAdditionalInfo;
import io.vamani.application.vendorportal.domain.VendorBankDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorAdditionalInfo entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorAdditionalInfoRepository extends JpaRepository<VendorAdditionalInfo, Long> {
	@Query("select vendorAdditionalInfo from VendorAdditionalInfo vendorAdditionalInfo where vendorAdditionalInfo.vendorId=?1")
	VendorAdditionalInfo findByVendorId(Long vendorId);

}
