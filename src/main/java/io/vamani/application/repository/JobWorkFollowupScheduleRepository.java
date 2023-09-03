package io.vamani.application.repository;

import io.vamani.application.domain.JobWorkFollowupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the JobWorkFollowup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobWorkFollowupScheduleRepository extends JpaRepository<JobWorkFollowupSchedule, Long> {
    @Query("select jobWorkFollowupSchedule from JobWorkFollowupSchedule jobWorkFollowupSchedule where jobWorkFollowupSchedule.jobWorkFollowup.id = ?1 and jobWorkFollowupSchedule.finYear = ?2")
    JobWorkFollowupSchedule findByJobWorkFollowupIdAndFinYear(Long jobWorkFollowupId, Long finYear);
}
