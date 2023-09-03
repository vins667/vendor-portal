package io.vamani.application.repository;
import io.vamani.application.domain.VcutOperationIssueMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VcutOperationIssueMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutOperationIssueMasterRepository extends JpaRepository<VcutOperationIssueMaster, Long> {
}
