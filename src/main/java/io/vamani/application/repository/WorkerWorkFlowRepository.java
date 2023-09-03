package io.vamani.application.repository;

import io.vamani.application.domain.WorkerWorkFlow;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerWorkFlow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerWorkFlowRepository extends JpaRepository<WorkerWorkFlow, Long> {

    @Query("select workerWorkFlow from WorkerWorkFlow workerWorkFlow where workerWorkFlow.mockId=?1 and workerWorkFlow.forwardCode is not null order by workerWorkFlow.id")
    List<WorkerWorkFlow> getWorkerWorkFlowByMockId(Long mockId);

    @Query("select workerWorkFlow from WorkerWorkFlow workerWorkFlow where workerWorkFlow.mockId=?1 and workerWorkFlow.forwardCode is not null order by workerWorkFlow.id desc")
    List<WorkerWorkFlow> getWorkerWorkFlowByMockIdDesc(Long mockId);

    @Query("select workerWorkFlow from WorkerWorkFlow workerWorkFlow where workerWorkFlow.mockId=?1 and (workerWorkFlow.forwardCode is null or workerWorkFlow.forwardCode=\'\') order by workerWorkFlow.id")
    WorkerWorkFlow getEntryWorkerWorkFlowByMockId(Long quoteId);
}
