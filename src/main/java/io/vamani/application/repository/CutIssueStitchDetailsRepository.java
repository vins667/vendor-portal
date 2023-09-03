package io.vamani.application.repository;

import io.vamani.application.domain.CutIssueStitchDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the CutIssueStitchDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutIssueStitchDetailsRepository extends JpaRepository<CutIssueStitchDetails, Long> {
    @Query("select cutIssueStitchDetails from CutIssueStitchDetails cutIssueStitchDetails where cutIssueStitchDetails.cutPlanIssueStitch.id = ?1 order by cutIssueStitchDetails.id")
    List<CutIssueStitchDetails> findAllByCutPlanIssueStitchId(Long cutPlanIssueStitchId);

    @Query("select cutIssueStitchDetails from CutIssueStitchDetails cutIssueStitchDetails where cutIssueStitchDetails.cutPlanBundleId = ?1")
    CutIssueStitchDetails findCutIssueStitchDetailsByBundleId(Long bundleId);
}
