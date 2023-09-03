package io.vamani.application.repository;
import io.vamani.application.domain.FollowupBuyer;
import io.vamani.application.domain.JobWorkFollowup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the JobWorkFollowup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobWorkFollowupRepository extends JpaRepository<JobWorkFollowup, Long> {
    @Query("select jobWorkFollowup from JobWorkFollowup jobWorkFollowup where jobWorkFollowup.jobworkcode like ?1 and jobWorkFollowup.jobworkname like ?2")
    Page<JobWorkFollowup> findAllByJobworkcodeAndJoAndJobworkname(String jobworkcode, String jobworkname, Pageable pageable);
}
