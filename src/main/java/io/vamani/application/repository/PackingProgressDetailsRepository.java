package io.vamani.application.repository;

import io.vamani.application.domain.PackingLineIssueDetails;
import io.vamani.application.domain.PackingProgressDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PackingProgressDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackingProgressDetailsRepository extends JpaRepository<PackingProgressDetails, Long> {
    @Query("select packingProgressDetails from PackingProgressDetails packingProgressDetails where packingProgressDetails.cutPlanBundleDetailsId = ?1")
    List<PackingProgressDetails> findPackingProgressDetailsByPieceId(Long pieceId);

    @Query("select packingProgressDetails from PackingProgressDetails packingProgressDetails where packingProgressDetails.packingProgressEntry.id = ?1 order by packingProgressDetails.id")
    List<PackingProgressDetails> findPackingProgressDetailsByPackingProgressEntryId(Long packingProgressEntryId);

    @Query(value = "select stdi.itemtypecode, stdi.decosubcode_01, stdi.decosubcode_02, stdi.decosubcode_03, stdi.decosubcode_04,"
        + " stdi.decosubcode_05, stdi.decosubcode_06, stdi.decosubcode_07, stdi.decosubcode_08, stdi.decosubcode_09, stdi.decosubcode_10,"
        + " stdi.baseprimaryunitcode, stdi.basesecondaryunitcode, sum(baseprimaryquantityunit) primaryquantity,"
        + " sum(stdi.basesecondaryquantityunit) secondaryquantity"
        + " from packing_progress_details stdi"
        + " where stdi.packing_progress_entry_id = ?1"
        + " group by stdi.itemtypecode, stdi.decosubcode_01, stdi.decosubcode_02, stdi.decosubcode_03, stdi.decosubcode_04,"
        + " stdi.decosubcode_05, stdi.decosubcode_06, stdi.decosubcode_07, stdi.decosubcode_08, stdi.decosubcode_09, stdi.decosubcode_10,"
        + " stdi.baseprimaryunitcode, stdi.basesecondaryunitcode", nativeQuery = true)
    List<Object[]> findAllObjectByPackingProgressEntryId(Long cutPlanIssueStitchId);
}
