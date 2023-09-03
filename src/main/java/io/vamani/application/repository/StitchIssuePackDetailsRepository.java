package io.vamani.application.repository;

import io.vamani.application.domain.StitchIssuePackDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the StitchIssuePackDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StitchIssuePackDetailsRepository extends JpaRepository<StitchIssuePackDetails, Long> {
    @Query("select StitchIssuePackDetails from StitchIssuePackDetails StitchIssuePackDetails where StitchIssuePackDetails.stitchIssuePack.id = ?1 order by StitchIssuePackDetails.id")
    List<StitchIssuePackDetails> findAllByStitchIssuePackId(Long cutPlanIssueStitchId);

    @Query(value = "select stdi.production_code, stdi.groupstepnumber, stdi.demandcountercode, stdi.demandcode,"
        + " stdi.itemtypecode, stdi.subcode_01, stdi.subcode_02, stdi.subcode_03, stdi.subcode_04,"
        + " stdi.subcode_05, stdi.subcode_06, stdi.subcode_07, stdi.subcode_08, stdi.subcode_09, stdi.subcode_10,"
        + " stdi.logicalwarehousecode, stdi.physicalwarehousecode, stdi.primaryuomcode, stdi.secondaryuomcode, sum(primaryquantity) primaryquantity,"
        + " sum(stdi.secondaryquantity) secondaryquantity"
        + " from stitch_issue_pack_details sipd, stitch_stock_details stdi"
        + " where sipd.stitch_stock_details_id = stdi.id and sipd.stitch_issue_pack_id = ?1"
        + " group by stdi.production_code, stdi.groupstepnumber, stdi.demandcountercode, stdi.demandcode,"
        + " stdi.itemtypecode, stdi.subcode_01, stdi.subcode_02, stdi.subcode_03, stdi.subcode_04,"
        + " stdi.subcode_05, stdi.subcode_06, stdi.subcode_07, stdi.subcode_08, stdi.subcode_09, stdi.subcode_10,"
        + " stdi.logicalwarehousecode, stdi.physicalwarehousecode, stdi.primaryuomcode, stdi.secondaryuomcode", nativeQuery = true)
   List<Object[]> findAllObjectByStitchIssuePackId(Long cutPlanIssueStitchId);

    @Query("select StitchIssuePackDetails from StitchIssuePackDetails StitchIssuePackDetails where StitchIssuePackDetails.cutPlanBundleId = ?1 and StitchIssuePackDetails.stitchIssuePack.recieptPostedBy is not null")
    List<StitchIssuePackDetails> findStitchIssuePackDetailsByBundleId(Long bundleId);

    @Query("select StitchIssuePackDetails from StitchIssuePackDetails StitchIssuePackDetails where StitchIssuePackDetails.cutPlanBundleDetailsId = ?1 and StitchIssuePackDetails.stitchIssuePack.recieptPostedBy is not null")
    StitchIssuePackDetails findStitchIssuePackDetailsByPieceId(Long pieceId);

    @Query("select StitchIssuePackDetails from StitchIssuePackDetails StitchIssuePackDetails where StitchIssuePackDetails.stitchStockDetailsId = ?1")
    StitchIssuePackDetails findStitchIssuePackDetailsByStockId(Long stockId);

}
