package io.vamani.application.repository;

import io.vamani.application.domain.CutPlanEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the CutPlanEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutPlanEntryRepository extends JpaRepository<CutPlanEntry, Long> {
    @Query("select cutPlanEntry from CutPlanEntry cutPlanEntry where cutPlanEntry.productionCode like ?1 and cutPlanEntry.style like ?2 and cutPlanEntry.color like ?3 and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4)")
    Page<CutPlanEntry> findAllByPonoAndStyle(String pono, String style, String color, String login, Pageable pageable);

    @Query("select cutPlanEntry from CutPlanEntry cutPlanEntry where CAST(cutPlanEntry.id as text) like ?1 and cutPlanEntry.productionCode like ?2 and cutPlanEntry.style like ?3 and cutPlanEntry.color like ?4 and cutPlanEntry.releaseBy is null and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?5)")
    Page<CutPlanEntry> findAllByPlanAndPonoAndStylePending(String plan, String pono, String style, String color, String login, Pageable pageable);

    @Query("select cutPlanEntry from CutPlanEntry cutPlanEntry where CAST(cutPlanEntry.id as text) like ?1 and cutPlanEntry.productionCode like ?2 and cutPlanEntry.style like ?3 and cutPlanEntry.color like ?4 and cutPlanEntry.releaseBy is not null and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?5)")
    Page<CutPlanEntry> findAllByPlanAndPonoAndStyle(String plan, String pono, String style, String color, String login, Pageable pageable);

    @Query("select distinct cutPlanEntry.id, cutPlanEntry.plantCode, cutPlanEntry.plantDescription, cutPlanEntry.productionCode, cutPlanEntry.style, cutPlanEntry.color, cutPlanEntry.colorDesc, cutPlanEntry.destination, cutPlanEntry.destinationDesc, cutPlanEntry.summerizedDescription from CutPlanEntry cutPlanEntry inner join CutPlanEntryDetails cutPlanEntryDetail on  cutPlanEntry.id = cutPlanEntryDetail.cutPlanEntry.id  where CAST(cutPlanEntry.id as text) like ?1 and cutPlanEntry.productionCode like ?2 and cutPlanEntry.style like ?3 and cutPlanEntry.color like ?4 and cutPlanEntry.releaseBy is not null and cutPlanEntryDetail.issuedDate is null and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?5)")
    Page<Object[]> findAllByPlanAndPonoAndStylePendingIssue(String plan, String pono, String style, String color, String login, Pageable pageable);

    @Query("select distinct cutPlanEntry.id, cutPlanEntry.plantCode, cutPlanEntry.plantDescription, cutPlanEntry.productionCode, cutPlanEntry.style, cutPlanEntry.color, cutPlanEntry.colorDesc, cutPlanEntry.destination, cutPlanEntry.destinationDesc, cutPlanEntry.summerizedDescription from CutPlanEntry cutPlanEntry inner join CutPlanEntryDetails cutPlanEntryDetail on  cutPlanEntry.id = cutPlanEntryDetail.cutPlanEntry.id  where CAST(cutPlanEntry.id as text) like ?1 and cutPlanEntry.productionCode like ?2 and cutPlanEntry.style like ?3 and cutPlanEntry.color like ?4 and cutPlanEntry.releaseBy is not null and cutPlanEntryDetail.issuedDate is not null and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?5)")
    Page<Object[]> findAllByPlanAndPonoAndStyleIssued(String plan, String pono, String style, String color, String login, Pageable pageable);

    @Query("select cutPlanEntry from CutPlanEntry cutPlanEntry where CAST(cutPlanEntry.id as text) like ?1 and cutPlanEntry.productionCode like ?2 and cutPlanEntry.style like ?3 and cutPlanEntry.releaseBy is not null and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4) and cutPlanEntry.progressEntryDate is null and cutPlanEntry.progressPostedDate is null")
    Page<CutPlanEntry> findAllByPlanAndPonoAndStyleProgressPending(String plan, String pono, String style, String login, Pageable pageable);

    @Query("select cutPlanEntry from CutPlanEntry cutPlanEntry where CAST(cutPlanEntry.id as text) like ?1 and cutPlanEntry.productionCode like ?2 and cutPlanEntry.style like ?3 and cutPlanEntry.releaseBy is not null and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4) and cutPlanEntry.progressEntryDate is not null and cutPlanEntry.progressPostedDate is null")
    Page<CutPlanEntry> findAllByPlanAndPonoAndStyleProgressProgressed(String plan, String pono, String style, String login, Pageable pageable);

    @Query("select cutPlanEntry from CutPlanEntry cutPlanEntry where CAST(cutPlanEntry.id as text) like ?1 and cutPlanEntry.productionCode like ?2 and cutPlanEntry.style like ?3 and cutPlanEntry.releaseBy is not null and cutPlanEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?4) and cutPlanEntry.progressPostedDate is not null")
    Page<CutPlanEntry> findAllByPlanAndPonoAndStyleProgressPosted(String plan, String pono, String style, String login, Pageable pageable);

    @Query("select coalesce(count(cutPlanEntry), 0) from CutPlanEntry cutPlanEntry where cutPlanEntry.markerMasterEntry.id = ?1")
    Long countAllByMarkerMasterEntryId(Long markerMasterEntryId);

    @Query("select markerEntryDetails.sizeCode, sum(markerEntryDetails.sizeQty * cutPlanEntry.noPlies) from CutPlanEntry cutPlanEntry inner join MarkerEntryDetails markerEntryDetails on cutPlanEntry.markerMasterEntry.id = markerEntryDetails.markerMasterEntry.id where cutPlanEntry.productionCode = ?1 group by markerEntryDetails.sizeCode")
    List<Object[]> findAllSizeByProductionOrder(String productionOrder);

    @Query("select markerEntryDetails.sizeCode, sum(markerEntryDetails.sizeQty * cutPlanEntry.noPlies) from CutPlanEntry cutPlanEntry inner join MarkerEntryDetails markerEntryDetails on cutPlanEntry.markerMasterEntry.id = markerEntryDetails.markerMasterEntry.id where cutPlanEntry.productionCode = ?1 and "
        +" cutPlanEntry.style = ?2 and cutPlanEntry.color = ?3 and cutPlanEntry.destination = ?4 and coalesce(cutPlanEntry.subcode01, '')=?5 and coalesce(cutPlanEntry.subcode02, '')=?6 and"
        +" coalesce(cutPlanEntry.subcode03, '')=?7 and coalesce(cutPlanEntry.subcode04, '')=?8 and coalesce(cutPlanEntry.subcode05, '')=?9 and coalesce(cutPlanEntry.subcode06, '')=?10 and coalesce(cutPlanEntry.subcode07, '')=?11 and coalesce(cutPlanEntry.subcode08, '')=?12 and"
        +" coalesce(cutPlanEntry.subcode09, '')=?13 and coalesce(cutPlanEntry.subcode10, '')=?14 and cutPlanEntry.id <> ?15 group by markerEntryDetails.sizeCode")
    List<Object[]> findAllSizeByProductionOrder(String productionOrder, String style, String color, String destination, String ressubcode01, String ressubcode02, String ressubcode03, String ressubcode04, String ressubcode05, String ressubcode06, String ressubcode07, String ressubcode08, String ressubcode09, String ressubcode10, Long cutPlanEntryId);

    @Query("select coalesce(SUM(cutPlanProgress.primaryquantity),0) FROM CutPlanEntry cutPlanEntry, CutPlanProgress cutPlanProgress where cutPlanEntry.id=cutPlanProgress.cutPlanEntry.id and"
        +" cutPlanEntry.plantCode=?1 and cutPlanEntry.productionCode=?2 and cutPlanProgress.subcode07=?3 and cutPlanProgress.subcode08=?4 and coalesce(cutPlanEntry.subcode01, '')=?5 and coalesce(cutPlanEntry.subcode02, '')=?6 and"
        +" coalesce(cutPlanEntry.subcode03, '')=?7 and coalesce(cutPlanEntry.subcode04, '')=?8 and coalesce(cutPlanEntry.subcode05, '')=?9 and coalesce(cutPlanEntry.subcode06, '')=?10 and coalesce(cutPlanEntry.subcode07, '')=?11 and coalesce(cutPlanEntry.subcode08, '')=?12 and"
        +" coalesce(cutPlanEntry.subcode09, '')=?13 and coalesce(cutPlanEntry.subcode10, '')=?14 and coalesce(cutPlanEntry.destination, '') = ?15 and cutPlanEntry.progressPostedDate IS NOT NULL AND cutPlanProgress.operationcode='CUTTING'")
    Double sumPrimaryQuantityByPOAndSize(String plantCode, String productionOrderCode, String subcode07, String subcode08, String ressubcode01, String ressubcode02, String ressubcode03, String ressubcode04, String ressubcode05, String ressubcode06, String ressubcode07, String ressubcode08, String ressubcode09, String ressubcode10, String destination);

    @Query("select coalesce(SUM(cutPlanProgress.primaryquantity),0) FROM CutPlanEntry cutPlanEntry, CutPlanProgress cutPlanProgress where cutPlanEntry.id=cutPlanProgress.cutPlanEntry.id and"
        +" cutPlanEntry.plantCode=?1 and cutPlanEntry.productionCode=?2 and cutPlanProgress.subcode05=?3 and cutPlanProgress.subcode06=?4 and coalesce(cutPlanEntry.subcode01, '')=?5 and coalesce(cutPlanEntry.subcode02, '')=?6 and"
        +" coalesce(cutPlanEntry.subcode03, '')=?7 and coalesce(cutPlanEntry.subcode04, '')=?8 and coalesce(cutPlanEntry.subcode05, '')=?9 and coalesce(cutPlanEntry.subcode06, '')=?10 and coalesce(cutPlanEntry.subcode07, '')=?11 and coalesce(cutPlanEntry.subcode08, '')=?12 and"
        +" coalesce(cutPlanEntry.subcode09, '')=?13 and coalesce(cutPlanEntry.subcode10, '')=?14 and coalesce(cutPlanEntry.destination, '') = ?15 and cutPlanEntry.progressPostedDate IS NOT NULL AND cutPlanProgress.operationcode='CUTTING'")
    Double sumHFDPrimaryQuantityByPOAndSize(String plantCode, String productionOrderCode, String subcode07, String subcode08, String ressubcode01, String ressubcode02, String ressubcode03, String ressubcode04, String ressubcode05, String ressubcode06, String ressubcode07, String ressubcode08, String ressubcode09, String ressubcode10, String destination);

    @Query(value = "select mme.id, mme.marker_code, med.size_code, sum(coalesce(cpe.actual_no_plies, cpe.no_plies) * med.size_qty) size_qty, max(med.size_qty) ratio from cut_plan_entry cpe, marker_master_entry mme, marker_entry_details med"
        +" where cpe.marker_master_entry_id = mme.id and mme.id = med.marker_master_entry_id and"
        +" cpe.style = ?1 and cpe.color = ?2 and cpe.destination = ?3 and coalesce(cpe.subcode_01, '')=?4 and coalesce(cpe.subcode_02, '')=?5 and"
        +" coalesce(cpe.subcode_03, '')=?6 and coalesce(cpe.subcode_04, '')=?7 and coalesce(cpe.subcode_05, '')=?8 and coalesce(cpe.subcode_06, '')=?9 and coalesce(cpe.subcode_07, '')=?10 and coalesce(cpe.subcode_08, '')=?11 and"
        +" coalesce(cpe.subcode_09, '')=?12 and coalesce(cpe.subcode_10, '')=?13"
        +" group by  mme.id, mme.marker_code, med.size_code order by mme.id", nativeQuery = true)
    List<Object[]> findAllByStyleColorAndDestination(String style, String color, String destination, String ressubcode01, String ressubcode02, String ressubcode03, String ressubcode04, String ressubcode05, String ressubcode06, String ressubcode07, String ressubcode08, String ressubcode09, String ressubcode10);

    @Query(value = "select mme.id, mme.marker_code, med.size_code, sum(coalesce(cpe.actual_no_plies, cpe.no_plies) * med.size_qty) size_qty, max(med.size_qty) ratio from cut_plan_entry cpe, marker_master_entry mme, marker_entry_details med"
        +" where cpe.marker_master_entry_id = mme.id and mme.id = med.marker_master_entry_id and"
        +" cpe.production_code = ?1 and cpe.style = ?2 and cpe.color = ?3 and cpe.destination = ?4 and coalesce(cpe.subcode_01, '')=?5 and coalesce(cpe.subcode_02, '')=?6 and"
        +" coalesce(cpe.subcode_03, '')=?7 and coalesce(cpe.subcode_04, '')=?8 and coalesce(cpe.subcode_05, '')=?9 and coalesce(cpe.subcode_06, '')=?10 and coalesce(cpe.subcode_07, '')=?11 and coalesce(cpe.subcode_08, '')=?12 and"
        +" coalesce(cpe.subcode_09, '')=?13 and coalesce(cpe.subcode_10, '')=?14"
        +" group by  mme.id, mme.marker_code, med.size_code order by mme.id", nativeQuery = true)
    List<Object[]> findAllByPOAndStyleColorAndDestination(String productioncode, String style, String color, String destination, String ressubcode01, String ressubcode02, String ressubcode03, String ressubcode04, String ressubcode05, String ressubcode06, String ressubcode07, String ressubcode08, String ressubcode09, String ressubcode10);
}
