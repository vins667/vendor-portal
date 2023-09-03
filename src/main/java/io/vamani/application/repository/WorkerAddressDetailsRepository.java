package io.vamani.application.repository;

import io.vamani.application.domain.WorkerAddressDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the WorkerAddressDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerAddressDetailsRepository extends JpaRepository<WorkerAddressDetails, Long> {
    @Query("select workerAddressDetails from WorkerAddressDetails workerAddressDetails where workerAddressDetails.workerJoining.id =?1 order by workerAddressDetails.id")
    List<WorkerAddressDetails> findByWorkerJoiningId(Long workerJoiningId);
}
