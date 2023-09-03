package io.vamani.application.repository;

import io.vamani.application.domain.WorkerReferenceDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerReferenceDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerReferenceDetailsRepository extends JpaRepository<WorkerReferenceDetails, Long> {
    @Query("select workerReferenceDetails from WorkerReferenceDetails workerReferenceDetails where workerReferenceDetails.workerJoining.id =?1 order by workerReferenceDetails.id")
    List<WorkerReferenceDetails> findByWorkerJoiningId(Long workerJoiningId);
}
