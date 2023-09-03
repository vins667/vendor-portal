package io.vamani.application.repository;

import io.vamani.application.domain.AuditQuesBuyerMappingDtls;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the AuditQuesBuyerMappingDtls entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditQuesBuyerMappingDtlsRepository extends JpaRepository<AuditQuesBuyerMappingDtls, Long> {
    @Query("select auditQuesBuyerMappingDtls from AuditQuesBuyerMappingDtls auditQuesBuyerMappingDtls where auditQuesBuyerMappingDtls.auditQuesBuyerMapping.id = ?1")
    List<AuditQuesBuyerMappingDtls> findAllByMasterId(Long masterId);

    @Query("select auditQuesBuyerMappingDtls from AuditQuesBuyerMappingDtls auditQuesBuyerMappingDtls where auditQuesBuyerMappingDtls.auditQuesBuyerMapping.id = ?1 and auditQuesBuyerMappingDtls.buyerMasterId=?2 and auditQuesBuyerMappingDtls.allowValid = true")
    List<AuditQuesBuyerMappingDtls> findAllByMasterIdAAndBuyerMasterId(Long masterId, String buyerCode);

    @Query("select distinct auditQuesBuyerMappingDtls.auditQuesBuyerMapping.id, auditQuesBuyerMappingDtls.auditName from AuditQuesBuyerMappingDtls auditQuesBuyerMappingDtls where auditQuesBuyerMappingDtls.buyerMasterId=?1 order by auditQuesBuyerMappingDtls.auditName")
    List<Object[]> findByBuyerMasterId(String buyerCode);
}
