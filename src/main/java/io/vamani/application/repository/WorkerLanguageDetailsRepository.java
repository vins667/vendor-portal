package io.vamani.application.repository;

import io.vamani.application.domain.WorkerLanguageDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerLanguageDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerLanguageDetailsRepository extends JpaRepository<WorkerLanguageDetails, Long> {
    @Query("select workerLanguageDetails from WorkerLanguageDetails workerLanguageDetails where workerLanguageDetails.workerJoining.id =?1 order by workerLanguageDetails.id")
    List<WorkerLanguageDetails> findByWorkerJoiningId(Long workerJoiningId);
}
