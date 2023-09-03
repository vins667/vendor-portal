package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorNominationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface VendorNominationTransactionRepository extends JpaRepository<VendorNominationTransaction, Long> {
    @Query("select vendorNominationTransaction from VendorNominationTransaction vendorNominationTransaction where vendorNominationTransaction.vendorId=?1 and vendorNominationTransaction.approvedDate is null")
    VendorNominationTransaction findByVendorId(Long vendorId);
}
