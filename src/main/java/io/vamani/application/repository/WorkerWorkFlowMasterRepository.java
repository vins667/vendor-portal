package io.vamani.application.repository;

import io.vamani.application.domain.WorkerWorkFlowMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerWorkFlowMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerWorkFlowMasterRepository extends JpaRepository<WorkerWorkFlowMaster, Long> {
    @Query("select distinct workerForwardTypeMaster.code, workerForwardTypeMaster.description FROM WorkerWorkFlowMaster workerWorkFlowMaster, WorkerForwardTypeMaster workerForwardTypeMaster where workerWorkFlowMaster.workerForwardTypeMaster.code=workerForwardTypeMaster.code and workerWorkFlowMaster.empCode =?1")
    List<Object[]> getDistinctByForwardEmpTypeByEmpCode(String empCode);

    @Query("select workerWorkFlowMaster from WorkerWorkFlowMaster workerWorkFlowMaster where workerWorkFlowMaster.empCode=?1 and workerWorkFlowMaster.workerForwardTypeMaster.code=?2")
    List<WorkerWorkFlowMaster> getWorkerWorkFlowMasterByEmpCodeWithFlag(String empCode, String forwardEmpType);
}
