package io.vamani.application.vendorportal.repository;
import io.vamani.application.vendorportal.domain.VendorTerms;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the Country entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorTermsRepository extends JpaRepository<VendorTerms, Long> {
	@Query("select vendorTerms from VendorTerms vendorTerms where vendorTerms.vendorId= ?1")
	VendorTerms findByVendorId(long vendorId);
}
