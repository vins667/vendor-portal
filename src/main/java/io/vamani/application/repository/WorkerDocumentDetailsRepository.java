package io.vamani.application.repository;

import io.vamani.application.domain.WorkerDocumentDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerDocumentDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerDocumentDetailsRepository extends JpaRepository<WorkerDocumentDetails, Long> {
    @Query("select workerDocumentDetails from WorkerDocumentDetails workerDocumentDetails where workerDocumentDetails.workerJoining.id =?1 order by workerDocumentDetails.id")
    List<WorkerDocumentDetails> findByWorkerJoiningId(Long workerJoiningId);

    @Query("select workerDocumentDetails from WorkerDocumentDetails workerDocumentDetails where workerDocumentDetails.workerJoining.id =?1 and workerDocumentDetails.recruitmentDocumentMaster.id=?2 and workerDocumentDetails.documentType=?3")
    WorkerDocumentDetails findByWorkerJoiningIdAndDocId(Long workerJoiningId, Long docId, String documentType);
}
