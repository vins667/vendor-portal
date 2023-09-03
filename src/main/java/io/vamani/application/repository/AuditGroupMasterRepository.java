package io.vamani.application.repository;

import io.vamani.application.domain.AuditGroupMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AuditGroupMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditGroupMasterRepository extends JpaRepository<AuditGroupMaster, Long> {

}
