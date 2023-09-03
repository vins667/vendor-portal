package io.vamani.application.repository;

import io.vamani.application.domain.AuditQuesBuyerMapping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AuditQuesBuyerMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditQuesBuyerMappingRepository extends JpaRepository<AuditQuesBuyerMapping, Long> {
    @Query("select auditQuesBuyerMapping from AuditQuesBuyerMapping auditQuesBuyerMapping where auditQuesBuyerMapping.vendorAuditQuesMaster.id = ?1")
    AuditQuesBuyerMapping findByAuditId(Long auditId);
}
