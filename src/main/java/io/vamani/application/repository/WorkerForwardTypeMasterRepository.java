package io.vamani.application.repository;

import io.vamani.application.domain.WorkerForwardTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkerForwardTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerForwardTypeMasterRepository extends JpaRepository<WorkerForwardTypeMaster, Long> {

}
