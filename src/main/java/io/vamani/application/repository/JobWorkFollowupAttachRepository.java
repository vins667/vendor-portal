package io.vamani.application.repository;

import io.vamani.application.domain.JobWorkFollowupDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the JobWorkFollowup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobWorkFollowupAttachRepository extends JpaRepository<JobWorkFollowupDetails, Long> {

}
