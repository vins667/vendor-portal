package io.vamani.application.repository;

    import io.vamani.application.domain.WorkerFamilyDetails;
    import org.springframework.data.jpa.repository.*;
    import org.springframework.stereotype.Repository;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;


/**
 * Spring Data  repository for the WorkerFamilyDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerFamilyDetailsRepository extends JpaRepository<WorkerFamilyDetails, Long> {

    @Modifying
    @Transactional
    @Query("delete from WorkerFamilyDetails workerFamilyDetails where workerFamilyDetails.workerJoining.id =?1")
    void deleteByWorkerJoiningId(Long workerJoiningId);

    @Query("select workerFamilyDetails from WorkerFamilyDetails workerFamilyDetails where workerFamilyDetails.workerJoining.id =?1 order by workerFamilyDetails.id")
    List<WorkerFamilyDetails> findByWorkerJoiningId(Long workerJoiningId);
}
