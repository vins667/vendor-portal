package io.vamani.application.repository;

import io.vamani.application.domain.VendorAuditQuesMaster;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VendorAuditQuesMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorAuditQuesMasterRepository extends JpaRepository<VendorAuditQuesMaster, Long> {
    @Query("select vendorAuditQuesMaster from VendorAuditQuesMasterWD vendorAuditQuesMaster")
    Page findAll(Pageable pageable);
}
