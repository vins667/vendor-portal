package io.vamani.application.repository;

import io.vamani.application.domain.WorkerJoinFlowMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerJoinFlowMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerJoinFlowMasterRepository extends JpaRepository<WorkerJoinFlowMaster, Long> {
    @Query("select distinct workerForwardTypeMaster.code, workerForwardTypeMaster.description FROM WorkerJoinFlowMaster workerJoinFlowMaster, WorkerForwardTypeMaster workerForwardTypeMaster where workerJoinFlowMaster.workerForwardTypeMaster.code=workerForwardTypeMaster.code and workerJoinFlowMaster.empCode =?1")
    List<Object[]> getDistinctByForwardEmpTypeByEmpCode(String empCode);

    @Query("select workerJoinFlowMaster from WorkerJoinFlowMaster workerJoinFlowMaster where workerJoinFlowMaster.empCode=?1 and workerJoinFlowMaster.workerForwardTypeMaster.code=?2")
    List<WorkerJoinFlowMaster> getWorkerJoinFlowMasterByEmpCodeWithFlag(String empCode, String forwardEmpType);
}
