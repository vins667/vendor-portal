package io.vamani.application.repository;

import io.vamani.application.domain.VendorBuyerAuditLinking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the VendorBuyerAuditLinking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorBuyerAuditLinkingRepository extends JpaRepository<VendorBuyerAuditLinking, Long> {

    @Query("select vendorBuyerAuditLinking from VendorBuyerAuditLinking vendorBuyerAuditLinking where vendorBuyerAuditLinking.vendorMasterCode = ?1")
    VendorBuyerAuditLinking findByVendorCode(String vendorCode);

    /*@Query(value = "select distinct vendor_buyer_audit_linking from VendorBuyerAuditLinking vendor_buyer_audit_linking left join fetch vendor_buyer_audit_linking.buyerMasters",
        countQuery = "select count(distinct vendor_buyer_audit_linking) from VendorBuyerAuditLinking vendor_buyer_audit_linking")
    Page<VendorBuyerAuditLinking> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct vendor_buyer_audit_linking from VendorBuyerAuditLinking vendor_buyer_audit_linking left join fetch vendor_buyer_audit_linking.buyerMasters")
    List<VendorBuyerAuditLinking> findAllWithEagerRelationships();

    @Query("select vendor_buyer_audit_linking from VendorBuyerAuditLinking vendor_buyer_audit_linking left join fetch vendor_buyer_audit_linking.buyerMasters where vendor_buyer_audit_linking.id =:id")
    Optional<VendorBuyerAuditLinking> findOneWithEagerRelationships(@Param("id") Long id);*/

}
