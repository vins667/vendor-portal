package io.vamani.application.repository;

import io.vamani.application.domain.PackingLineIssueDetails;
import io.vamani.application.domain.StitchIssuePackDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PackingLineIssueDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackingLineIssueDetailsRepository extends JpaRepository<PackingLineIssueDetails, Long> {
    @Query("select packingLineIssueDetails from PackingLineIssueDetails packingLineIssueDetails where packingLineIssueDetails.packingLineIssue.id = ?1 order by packingLineIssueDetails.id")
    List<PackingLineIssueDetails> findAllByPackingLineIssueId(Long packingLineIssueIs);

    @Query("select packingLineIssueDetails from PackingLineIssueDetails packingLineIssueDetails where packingLineIssueDetails.cutPlanBundleDetailsId = ?1")
    PackingLineIssueDetails findPackingLineIssueDetailsByPieceId(Long pieceId);

    @Query("select packingLineIssueDetails from PackingLineIssueDetails packingLineIssueDetails where packingLineIssueDetails.cutPlanBundleDetailsId = ?1 and packingLineIssueDetails.packingLineIssue.postedBy is not null")
    PackingLineIssueDetails findPackingLineIssueDetailsByPostedPieceId(Long pieceId);

    @Query("select packingLineIssueDetails from PackingLineIssueDetails packingLineIssueDetails where packingLineIssueDetails.cutPlanBundleId = ?1 and packingLineIssueDetails.packingLineIssue.postedBy is not null")
    List<PackingLineIssueDetails> findPackingLineIssueDetailsByBundleId(Long bundleId);

    @Query(value = "select stdi.itemtypecode, stdi.decosubcode_01, stdi.decosubcode_02, stdi.decosubcode_03, stdi.decosubcode_04,"
        + " stdi.decosubcode_05, stdi.decosubcode_06, stdi.decosubcode_07, stdi.decosubcode_08, stdi.decosubcode_09, stdi.decosubcode_10,"
        + " stdi.baseprimaryunitcode, stdi.basesecondaryunitcode, sum(baseprimaryquantityunit) primaryquantity,"
        + " sum(stdi.basesecondaryquantityunit) secondaryquantity"
        + " from packing_line_issue_details stdi"
        + " where stdi.packing_line_issue_id = ?1"
        + " group by stdi.itemtypecode, stdi.decosubcode_01, stdi.decosubcode_02, stdi.decosubcode_03, stdi.decosubcode_04,"
        + " stdi.decosubcode_05, stdi.decosubcode_06, stdi.decosubcode_07, stdi.decosubcode_08, stdi.decosubcode_09, stdi.decosubcode_10,"
        + " stdi.baseprimaryunitcode, stdi.basesecondaryunitcode", nativeQuery = true)
    List<Object[]> findAllObjectByPackingLineIssueId(Long cutPlanIssueStitchId);
}
