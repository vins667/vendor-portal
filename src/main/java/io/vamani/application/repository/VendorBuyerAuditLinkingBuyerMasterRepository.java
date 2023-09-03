package io.vamani.application.repository;

import io.vamani.application.domain.VendorBuyerAuditLinkingBuyerMaster;
import io.vamani.application.domain.VendorBuyerAuditLinkingBuyerMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data  repository for the VendorBuyerAuditLinking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorBuyerAuditLinkingBuyerMasterRepository extends JpaRepository<VendorBuyerAuditLinkingBuyerMaster, VendorBuyerAuditLinkingBuyerMasterId> {

    @Query("select vendorBuyerAuditLinkingBuyerMaster from VendorBuyerAuditLinkingBuyerMaster vendorBuyerAuditLinkingBuyerMaster where vendorBuyerAuditLinkingBuyerMaster.id.vendorBuyerAuditLinkingsId=?1")
    List<VendorBuyerAuditLinkingBuyerMaster> findAllByVendorBuyerId(Long vendorBuyerId);

    @Transactional
    @Modifying
    @Query("delete from VendorBuyerAuditLinkingBuyerMaster vendorBuyerAuditLinkingBuyerMaster where vendorBuyerAuditLinkingBuyerMaster.id.vendorBuyerAuditLinkingsId=?1")
    void deleteByVendorBuyerId(Long vendorBuyerId);
}
