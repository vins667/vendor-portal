package io.vamani.application.repository;
import io.vamani.application.domain.VcutOperationRejectMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VcutOperationRejectMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutOperationRejectMasterRepository extends JpaRepository<VcutOperationRejectMaster, Long> {
}
