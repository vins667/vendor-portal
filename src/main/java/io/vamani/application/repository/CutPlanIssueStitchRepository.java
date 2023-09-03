package io.vamani.application.repository;

import io.vamani.application.domain.CutPlanIssueStitch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CutPlanIssueStitch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutPlanIssueStitchRepository extends JpaRepository<CutPlanIssueStitch, Long> {
    @Query("select cutPlanIssueStitch from CutPlanIssueStitch cutPlanIssueStitch where cutPlanIssueStitch.transactionType like ?1 and cutPlanIssueStitch.style like ?2 and cutPlanIssueStitch.color like ?3 and cutPlanIssueStitch.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4)")
    Page<CutPlanIssueStitch> findAllByTypeAndPonoAndStyle(String type, String style, String color, String login, Pageable pageable);

    @Query("select cutPlanIssueStitch from CutPlanIssueStitch cutPlanIssueStitch where cutPlanIssueStitch.postedBy is not null and cutPlanIssueStitch.transactionType like ?1 and cutPlanIssueStitch.style like ?2 and cutPlanIssueStitch.color like ?3 and cutPlanIssueStitch.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4)")
    Page<CutPlanIssueStitch> findAllByTypeAndPonoAndStylePostedOnly(String type, String style, String color, String login, Pageable pageable);
}
