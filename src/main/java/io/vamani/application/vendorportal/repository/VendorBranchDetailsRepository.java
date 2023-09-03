package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorBranchDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the VendorBranchDetails entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorBranchDetailsRepository extends JpaRepository<VendorBranchDetails, Long> {
    @Query("select vendorBranchDetails from VendorBranchDetails vendorBranchDetails where vendorBranchDetails.vendorId=?1")
    Page<VendorBranchDetails> findAll(Long vendorId, Pageable pageable);

    @Query("select vendorBranchDetails from VendorBranchDetails vendorBranchDetails where vendorBranchDetails.vendorId=?1")
    List<VendorBranchDetails> findByVendorId(Long vendorId);

    @Query("select count(vendorBranchDetails) from VendorBranchDetails vendorBranchDetails where vendorBranchDetails.vendorId=?1")
    Long countAllByVendorId(Long vendorId);
}
