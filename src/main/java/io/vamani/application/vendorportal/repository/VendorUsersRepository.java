package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorBankDetails;
import io.vamani.application.vendorportal.domain.VendorUsers;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the VendorUsers entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorUsersRepository extends JpaRepository<VendorUsers, Long> {
	 @Query("select vendorUsers from VendorUsers vendorUsers where vendorUsers.vendorId=?1")
     List<VendorUsers> findByVendorId(Long vendorId);

}
