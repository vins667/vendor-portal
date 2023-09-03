package io.vamani.application.repository;

import io.vamani.application.domain.JobWorkFollowupDetails;
import io.vamani.application.domain.JobWorkFollowupReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the JobWorkFollowup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobWorkFollowupReminderRepository extends JpaRepository<JobWorkFollowupReminder, Long> {

    @Query("select jobWorkFollowupReminder  from JobWorkFollowupReminder jobWorkFollowupReminder where jobWorkFollowupReminder.jobWorkFollowup.id = ?1 and jobWorkFollowupReminder.reminderDate < ?2 and jobWorkFollowupReminder.reminderSent is null")
    List<JobWorkFollowupReminder> findAllByJobWorkFollowupIdAndFinYear(Long jobWorkFollowupId, LocalDate jobWorkDate);

    @Modifying
    @Transactional
    @Query("delete from JobWorkFollowupReminder jobWorkFollowupReminder where jobWorkFollowupReminder.jobWorkFollowup.id = ?1 and jobWorkFollowupReminder.finYear = ?2")
    void deleteAllByJobWorkFollowupIdAndFinYear(Long jobWorkFollowupId, Long finYear);
}
