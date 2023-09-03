package io.vamani.application.repository;

import io.vamani.application.domain.WorkerEducationDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerEducationDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerEducationDetailsRepository extends JpaRepository<WorkerEducationDetails, Long> {
    @Query("select workerEducationDetails from WorkerEducationDetails workerEducationDetails where workerEducationDetails.workerJoining.id =?1 order by workerEducationDetails.id")
    List<WorkerEducationDetails> findByWorkerJoiningId(Long workerJoiningId);
}
