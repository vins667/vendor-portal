package io.vamani.application.repository;

import io.vamani.application.domain.WorkerJoinFlowDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerJoinFlowDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerJoinFlowDetailsRepository extends JpaRepository<WorkerJoinFlowDetails, Long> {
    @Query("select workerJoinFlowDetails from WorkerJoinFlowDetails workerJoinFlowDetails where workerJoinFlowDetails.joiningId=?1 and workerJoinFlowDetails.forwardCode is not null order by workerJoinFlowDetails.id")
    List<WorkerJoinFlowDetails> getWorkerJoinFlowDetailsByJoiningId(Long mockId);

    @Query("select workerJoinFlowDetails from WorkerJoinFlowDetails workerJoinFlowDetails where workerJoinFlowDetails.joiningId=?1 and (workerJoinFlowDetails.forwardCode is null or workerJoinFlowDetails.forwardCode=\'\') order by workerJoinFlowDetails.id")
    WorkerJoinFlowDetails getEntryWorkerJoinFlowDetailsByJoiningId(Long mockId);
}
