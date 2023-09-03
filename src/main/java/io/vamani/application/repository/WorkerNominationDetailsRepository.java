package io.vamani.application.repository;

import io.vamani.application.domain.WorkerNominationDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerNominationDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerNominationDetailsRepository extends JpaRepository<WorkerNominationDetails, Long> {
    @Query("select workerNominationDetails from WorkerNominationDetails workerNominationDetails where workerNominationDetails.workerJoining.id =?1 order by workerNominationDetails.id")
    List<WorkerNominationDetails> findByWorkerJoiningId(Long workerJoiningId);
}
