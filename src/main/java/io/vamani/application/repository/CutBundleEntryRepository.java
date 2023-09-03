package io.vamani.application.repository;

import io.vamani.application.domain.CutBundleEntry;

import io.vamani.application.domain.CutPlanBundleDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CutBundleEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutBundleEntryRepository extends JpaRepository<CutBundleEntry, Long> {
    @Query("select cutBundleEntry from CutBundleEntry cutBundleEntry where cutBundleEntry.productionCode = ?1 and cutBundleEntry.plantCode = ?2 and cutBundleEntry.style = ?3 and cutBundleEntry.color = ?4 and cutBundleEntry.destination = ?5 and cutBundleEntry.size = ?6")
    CutBundleEntry findByDetails(String productionorder, String plantCode, String style, String color, String destination, String sizeCode);

    @Query(value = "select distinct porduction_counter_code, production_code, plant_code, style, color, destination, destination_desc, save_flag from cut_bundle_entry"
                    + " where production_code like ?1 and style like ?2 and (plant_code in (select plant_code from jhi_user_plant where login=?3))",
           countQuery = "select count(distinct coalesce(production_code,'') || coalesce(plant_code, '') || coalesce(style, '') || coalesce(color, '') || coalesce(destination, '') || coalesce(save_flag, false)) from cut_bundle_entry"
                    + " where production_code like ?1 and style like ?2 and (plant_code in (select plant_code from jhi_user_plant where login=?3))", nativeQuery = true)
    Page<Object[]> findAllByProductionorderAndStyle(String productionorder, String style, String login, Pageable pageable);

    @Query("select cutPlanBundleDetail from CutPlanBundleDetails cutPlanBundleDetail inner join StitchLineIssueDetails stitchLineIssueDetail on cutPlanBundleDetail.cutPlanBundle.id = stitchLineIssueDetail.cutPlanBundleId "
        + " inner join StitchLineIssue stitchLineIssue on stitchLineIssue.id = stitchLineIssueDetail.stitchLineIssue.id"
        + " where stitchLineIssue.postedBy is not null and cutPlanBundleDetail.id=?1 and stitchLineIssue.line=?2")
    CutPlanBundleDetails findByCutBundleDetailsId(Long cutPlanBundleDetailsId, String lineNumber);
}
