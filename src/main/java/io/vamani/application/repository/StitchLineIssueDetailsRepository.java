package io.vamani.application.repository;

import io.vamani.application.domain.StitchLineIssueDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the StitchLineIssueDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StitchLineIssueDetailsRepository extends JpaRepository<StitchLineIssueDetails, Long> {
    @Query("select stitchLineIssueDetails from StitchLineIssueDetails stitchLineIssueDetails where stitchLineIssueDetails.stitchLineIssue.id = ?1 order by stitchLineIssueDetails.id")
    List<StitchLineIssueDetails> findAllByStitchLineIssueId(Long stitchLineIssueIs);

    @Query("select stitchLineIssueDetails from StitchLineIssueDetails stitchLineIssueDetails where stitchLineIssueDetails.cutPlanBundleId = ?1")
    StitchLineIssueDetails findStitchLineIssueDetailsByBundleId(Long bundleId);
}
