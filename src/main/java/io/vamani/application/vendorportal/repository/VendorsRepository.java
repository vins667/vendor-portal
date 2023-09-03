package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.Vendors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the VendSubTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorsRepository extends JpaRepository<Vendors, Long> {

    @Query("select vendor from Vendors vendor where coalesce(vendor.vendorCode, 'N') like ?1 AND vendor.vendorName like ?2 and vendor.approvalStatus=?3")
    Page<Vendors> findAllByVendorCodeAndVendorNameAndApprovalStatus(String vendorCode, String vendorName, String approvalStatus, Pageable pageable);

    @Query("select vendor from Vendors vendor where coalesce(vendor.vendorCode, 'N') like ?1 AND vendor.vendorName like ?2 and vendor.approvalStatus in ('F', 'Q')")
    Page<Vendors> findAllByVendorCodeAndVendorNameAndApprovalStatusPending(String vendorCode, String vendorName, Pageable pageable);

    @Query("select vendor from Vendors vendor where vendor.emailDomain=?1")
    List<Vendors> findByEmailDomain(String emailDomain);

}
