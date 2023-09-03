package io.vamani.application.repository;

import io.vamani.application.domain.JobWorkFollowupDetails;
import io.vamani.application.domain.JobWorkFollowupSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Spring Data  repository for the JobWorkFollowup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobWorkFollowupDetailsRepository extends JpaRepository<JobWorkFollowupDetails, Long> {
    @Query("select jobWorkFollowupDetails from JobWorkFollowupDetails jobWorkFollowupDetails where jobWorkFollowupDetails.jobWorkFollowup.id = ?1 and jobWorkFollowupDetails.finYear = ?2")
    List<JobWorkFollowupDetails> findAllByJobWorkFollowupIdAndFinYear(Long jobWorkFollowupId, Long finYear);
    @Modifying
    @Transactional
    @Query("delete from JobWorkFollowupDetails jobWorkFollowupDetails where jobWorkFollowupDetails.jobWorkFollowup.id = ?1 and jobWorkFollowupDetails.finYear = ?2")
    void deleteAllByJobWorkFollowupIdAndFinYear(Long jobWorkFollowupId, Long finYear);

    @Query("select jobWorkFollowupDetails from JobWorkFollowupDetails jobWorkFollowupDetails where jobWorkFollowupDetails.submitDate is null and (jobWorkFollowupDetails.jobWorkFollowup.responsiblepersoncode01 = ?1 or jobWorkFollowupDetails.jobWorkFollowup.responsiblepersoncode02 = ?2 or jobWorkFollowupDetails.jobWorkFollowup.responsiblepersoncode03 = ?3)")
    Page<JobWorkFollowupDetails> findAllByPendingWorks(String user1, String user2, String user3, Pageable pageable);

    @Query("select jobWorkFollowupDetails from JobWorkFollowupDetails jobWorkFollowupDetails where jobWorkFollowupDetails.submitDate is not null and (jobWorkFollowupDetails.jobWorkFollowup.responsiblepersoncode01 = ?1 or jobWorkFollowupDetails.jobWorkFollowup.responsiblepersoncode02 = ?2 or jobWorkFollowupDetails.jobWorkFollowup.responsiblepersoncode03 = ?3)")
    Page<JobWorkFollowupDetails> findAllByClosedWorks(String user1, String user2, String user3, Pageable pageable);
}
