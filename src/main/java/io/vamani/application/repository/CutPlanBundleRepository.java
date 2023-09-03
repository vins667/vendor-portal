package io.vamani.application.repository;

import io.vamani.application.domain.CutBundleEntry;
import io.vamani.application.domain.CutPlanBundle;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data  repository for the CutPlanBundle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutPlanBundleRepository extends JpaRepository<CutPlanBundle, Long> {
    @Query("select cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, min(cutPlanBundleDetails.id), max(cutPlanBundleDetails.id)  from CutPlanBundle cutPlanBundle inner join CutPlanBundleDetails cutPlanBundleDetails on cutPlanBundle.id = cutPlanBundleDetails.cutPlanBundle.id  where cutPlanBundle.productionCode = ?1 and cutPlanBundle.plantCode = ?2 and cutPlanBundle.projectcode = ?3 and cutPlanBundle.subcode07 = ?4 and cutPlanBundle.destination = ?5 and cutPlanBundle.subcode08 = ?6 group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByDetails(String productionorder, String plantCode, String style, String color, String destination, String sizeCode);

    @Query(value = "SELECT CUTPLANBUNDLE.BUNDLE_CODE, CUTPLANBUNDLE.PRIMARYQUANTITY, MIN(CUTPLANBUNDLEDETAILS.ID), MAX(CUTPLANBUNDLEDETAILS.ID)  FROM CUT_PLAN_BUNDLE CUTPLANBUNDLE INNER JOIN CUT_PLAN_BUNDLE_DETAILS CUTPLANBUNDLEDETAILS ON CUTPLANBUNDLE.ID = CUTPLANBUNDLEDETAILS.CUT_PLAN_BUNDLE_ID  WHERE CUTPLANBUNDLE.PRODUCTION_CODE = ?1 AND CUTPLANBUNDLE.PLANT_CODE = ?2 AND CUTPLANBUNDLE.PROJECTCODE = ?3 AND CUTPLANBUNDLE.SUBCODE_07 = ?4 AND CUTPLANBUNDLE.DESTINATION = ?5 AND CUTPLANBUNDLE.SUBCODE_08 = ?6 GROUP BY CUTPLANBUNDLE.BUNDLE_CODE, CUTPLANBUNDLE.PRIMARYQUANTITY, CUTPLANBUNDLE.ID ORDER BY CUTPLANBUNDLE.ID", nativeQuery = true)
    List<Object[]> findByNativeDetails(String productionorder, String plantCode, String style, String color, String destination, String sizeCode);

    @Query("select cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, min(cutPlanBundleDetails.id), max(cutPlanBundleDetails.id)  from CutPlanBundle cutPlanBundle inner join CutPlanBundleDetails cutPlanBundleDetails on cutPlanBundle.id = cutPlanBundleDetails.cutPlanBundle.id  where cutPlanBundle.productionCode = ?1 and cutPlanBundle.plantCode = ?2 and cutPlanBundle.projectcode = ?3 and cutPlanBundle.subcode05 = ?4 and cutPlanBundle.destination = ?5 and cutPlanBundle.subcode06 = ?6 group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByHFDetails(String productionorder, String plantCode, String style, String color, String destination, String sizeCode);

    @Query(value = "SELECT CUTPLANBUNDLE.BUNDLE_CODE, CUTPLANBUNDLE.PRIMARYQUANTITY, MIN(CUTPLANBUNDLEDETAILS.ID), MAX(CUTPLANBUNDLEDETAILS.ID)  FROM CUT_PLAN_BUNDLE CUTPLANBUNDLE INNER JOIN CUT_PLAN_BUNDLE_DETAILS CUTPLANBUNDLEDETAILS ON CUTPLANBUNDLE.ID = CUTPLANBUNDLEDETAILS.CUT_PLAN_BUNDLE_ID WHERE CUTPLANBUNDLE.PRODUCTION_CODE = ?1 AND CUTPLANBUNDLE.PLANT_CODE = ?2 AND CUTPLANBUNDLE.PROJECTCODE = ?3 AND CUTPLANBUNDLE.SUBCODE_05 = ?4 AND CUTPLANBUNDLE.DESTINATION = ?5 AND CUTPLANBUNDLE.SUBCODE_06 = ?6 GROUP BY CUTPLANBUNDLE.BUNDLE_CODE, CUTPLANBUNDLE.PRIMARYQUANTITY, CUTPLANBUNDLE.ID ORDER BY CUTPLANBUNDLE.ID", nativeQuery = true)
    List<Object[]> findByNativeHFDetails(String productionorder, String plantCode, String style, String color, String destination, String sizeCode);

    @Query("select cutPlanBundle.subcode08, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode07) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printFlag is null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByDetailsBundle(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select cutPlanBundle.subcode06, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode05) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printFlag is null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByHFCDetailsBundle(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select cutPlanBundle.subcode08, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode07) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printPieceFlag is null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByDetailsPiece(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select cutPlanBundle.subcode06, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode05) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printPieceFlag is null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByHFCDetailsPiece(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select cutPlanBundle.subcode08, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode07) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printFlag is not null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByDetailsBundleExist(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select cutPlanBundle.subcode06, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode05) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printFlag is not null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByHFCDetailsBundleExist(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select cutPlanBundle.subcode08, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode07) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printPieceFlag is not null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByDetailsPieceExist(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select cutPlanBundle.subcode06, cutPlanBundle.id, cutPlanBundle.bundleCode from CutPlanBundle cutPlanBundle  where trim(cutPlanBundle.productionCode) = ?1 and trim(cutPlanBundle.plantCode) = ?2 and trim(cutPlanBundle.projectcode) = ?3 and trim(cutPlanBundle.subcode05) = ?4 and trim(cutPlanBundle.destination) = ?5 and cutPlanBundle.printPieceFlag is not null group by cutPlanBundle.bundleCode, cutPlanBundle.primaryquantity, cutPlanBundle.id order by cutPlanBundle.id")
    List<Object[]> findByHFCDetailsPieceExist(String productionorder, String plantCode, String style, String color, String destination);

    @Query("select coalesce(substring(cutPlanBundleDetails.productCode, 2, length(cutPlanBundleDetails.productCode)), '0') from CutPlanBundleDetails cutPlanBundleDetails where cutPlanBundleDetails.id = (select max(cutPlanBundleDetails.id)  from CutPlanBundle cutPlanBundle inner join CutPlanBundleDetails cutPlanBundleDetails on cutPlanBundle.id = cutPlanBundleDetails.cutPlanBundle.id  where cutPlanBundle.productionCode = ?1 and cutPlanBundle.plantCode = ?2 and cutPlanBundle.subcode01 = ?3 and cutPlanBundle.subcode07 = ?4 and cutPlanBundle.subcode08 = ?5)")
    String findMaxPcByDetails(String productionorder, String plantCode, String style, String color, String sizeCode);

    @Query("select coalesce(substring(cutPlanBundleDetails.productCode, 2, length(cutPlanBundleDetails.productCode)), '0') from CutPlanBundleDetails cutPlanBundleDetails where cutPlanBundleDetails.id = (select max(cutPlanBundleDetails.id)  from CutPlanBundle cutPlanBundle inner join CutPlanBundleDetails cutPlanBundleDetails on cutPlanBundle.id = cutPlanBundleDetails.cutPlanBundle.id  where cutPlanBundle.productionCode = ?1 and cutPlanBundle.plantCode = ?2 and cutPlanBundle.subcode01 = ?3 and cutPlanBundle.subcode05 = ?4 and cutPlanBundle.subcode06 = ?5)")
    String findMaxHFPcByDetails(String productionorder, String plantCode, String style, String color, String sizeCode);

    @Query("select distinct trim(cutPlanBundle.subcode07) from CutPlanBundle cutPlanBundle where trim(cutPlanBundle.subcode01) = ?1 AND LENGTH(TRIM(cutPlanBundle.subcode07))>0")
    List<String> findAllColorsByStyle(String style);

    @Query("select distinct trim(cutPlanBundle.subcode05) from CutPlanBundle cutPlanBundle where trim(cutPlanBundle.subcode01) = ?1")
    List<String> findAllHFColorsByStyle(String style);

    @Modifying
    @Transactional
    @Query("update CutPlanBundle cutPlanBundle set cutPlanBundle.printFlag='Y' where cutPlanBundle.id in (?1)")
    void updatePrintFlag(List<Long> ids);

    @Modifying
    @Transactional
    @Query("update CutPlanBundle cutPlanBundle set cutPlanBundle.printPieceFlag='Y' where cutPlanBundle.id in (?1)")
    void updatePiecePrintFlag(List<Long> ids);
}
