package io.vamani.application.repository;

import io.vamani.application.domain.WorkerJobsDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Spring Data  repository for the WorkerJobsDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerJobsDetailsRepository extends JpaRepository<WorkerJobsDetails, Long> {

    @Modifying
    @Transactional
    @Query("delete from WorkerJobsDetails workerJobsDetails where workerJobsDetails.workerJoining.id =?1")
    void deleteByWorkerJoiningId(Long workerJoiningId);

    @Query("select workerJobsDetails from WorkerJobsDetails workerJobsDetails where workerJobsDetails.workerJoining.id =?1 order by workerJobsDetails.id")
    List<WorkerJobsDetails> findByWorkerJoiningId(Long workerJoiningId);
}
