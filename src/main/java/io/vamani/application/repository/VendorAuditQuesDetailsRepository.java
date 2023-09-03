package io.vamani.application.repository;

import io.vamani.application.domain.VendorAuditQuesDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the VendorAuditQuesDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorAuditQuesDetailsRepository extends JpaRepository<VendorAuditQuesDetails, Long> {
    @Query("select vendorAuditQuesDetails from VendorAuditQuesDetails vendorAuditQuesDetails where vendorAuditQuesDetails.vendorAuditQuesMaster.id = ?1 order by vendorAuditQuesDetails.id")
    List<VendorAuditQuesDetails> findAllByMasterId(Long masterId);


    @Query("select vendorAuditQuesDetails from VendorAuditQuesDetails vendorAuditQuesDetails where vendorAuditQuesDetails.id in ?1 order by vendorAuditQuesDetails.id")
    List<VendorAuditQuesDetails> findAllByDetailsId(List<Long> detailId);
}
