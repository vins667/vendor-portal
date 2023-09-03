package io.vamani.application.repository;

import io.vamani.application.domain.CutPlanEntryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Spring Data  repository for the CutPlanEntryDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutPlanEntryDetailsRepository extends JpaRepository<CutPlanEntryDetails, Long> {
    @Query("select cutPlanEntryDetails from CutPlanEntryDetails cutPlanEntryDetails where cutPlanEntryDetails.cutPlanEntry.id = ?1 order by cutPlanEntryDetails.id")
    List<CutPlanEntryDetails> findAllByCutPlanEntryId(Long cutPlanEntryId);


    @Query("select cutPlanEntryDetails from CutPlanEntryDetails cutPlanEntryDetails where cutPlanEntryDetails.cutPlanEntry.id = ?1 order by cutPlanEntryDetails.elementscode")
    List<CutPlanEntryDetails> findAllByCutPlanEntryIdOrderByRoll(Long cutPlanEntryId);

    @Query("select cutPlanEntryDetails.elementscode from CutPlanEntryDetails cutPlanEntryDetails where cutPlanEntryDetails.decosubcode01 = ?1 and cutPlanEntryDetails.decosubcode02 = ?2 and cutPlanEntryDetails.decosubcode03 = ?3 and cutPlanEntryDetails.decosubcode04 = ?4 and cutPlanEntryDetails.decosubcode05 = ?5 and cutPlanEntryDetails.decosubcode06 = ?6 and cutPlanEntryDetails.decosubcode07 = ?7 and cutPlanEntryDetails.decosubcode08 = ?8 and cutPlanEntryDetails.decosubcode09 = ?9 and cutPlanEntryDetails.decosubcode10 = ?10 and cutPlanEntryDetails.issuedBy is null")
    List<String> findAllElementsByItems(String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10);

    @Query("select cutPlanEntryDetails.elementscode from CutPlanEntryDetails cutPlanEntryDetails where cutPlanEntryDetails.decosubcode01 = ?1 and cutPlanEntryDetails.decosubcode02 = ?2 and cutPlanEntryDetails.decosubcode03 = ?3 and cutPlanEntryDetails.decosubcode04 = ?4 and cutPlanEntryDetails.decosubcode05 = ?5 and cutPlanEntryDetails.decosubcode06 = ?6 and cutPlanEntryDetails.decosubcode07 = ?7 and cutPlanEntryDetails.decosubcode08 = ?8 and cutPlanEntryDetails.decosubcode09 = ?9 and cutPlanEntryDetails.decosubcode10 = ?10 and cutPlanEntryDetails.issuedBy is null and cutPlanEntryDetails.cutPlanEntry.id <> ?11")
    List<String> findAllElementsByItems(String subcode01, String subcode02, String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08, String subcode09, String subcode10, Long cutPlanMasterId);

    @Modifying
    @Transactional
    @Query("delete from CutPlanEntryDetails cutPlanEntryDetails where cutPlanEntryDetails.cutPlanEntry.id=?1")
    void deleteAllByCutPlanEntryId(Long masterId);
}
