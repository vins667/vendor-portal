package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorNominationBuyerMaster;
import io.vamani.application.vendorportal.domain.VendorNominationBuyerMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

/**
 * Spring Data  repository for the VendorNominationTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorNominationBuyerMasterRepository extends JpaRepository<VendorNominationBuyerMaster, VendorNominationBuyerMasterId> {
    @Modifying
    @Transactional
    @Query("delete from VendorNominationBuyerMaster vendorNominationBuyerMaster where vendorNominationBuyerMaster.id.vendorNominationId =?1")
    void deleteAllByVendorNominationBuyerMaster(Long vendorNominationId);
}
