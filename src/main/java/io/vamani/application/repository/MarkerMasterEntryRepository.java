package io.vamani.application.repository;
import io.vamani.application.domain.DirectBookingEntry;
import io.vamani.application.domain.MarkerMasterEntry;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MarkerMasterEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarkerMasterEntryRepository extends JpaRepository<MarkerMasterEntry, Long> {

	@Query("select (COALESCE(count(markerMasterEntry.id), 0)+1) from MarkerMasterEntry markerMasterEntry where markerMasterEntry.style =?1 and markerMasterEntry.color =?2 ")
	String getNewMarkerCode(String style, String color);

	@Query("select count(markerMasterEntry) from MarkerMasterEntry markerMasterEntry where markerMasterEntry.style =?1 and markerMasterEntry.color =?2 ")
	String getAllMarkerByKey(String id, String desc);

    @Query("select markerMasterEntry from MarkerMasterEntry markerMasterEntry where markerMasterEntry.style =?1 and markerMasterEntry.color =?2 order by markerMasterEntry.id")
    List<MarkerMasterEntry> getAllMarkersByStyleAndColor(String style, String color);

	@Query("select markerMasterEntry from MarkerMasterEntry markerMasterEntry where markerMasterEntry.style =?1 and markerMasterEntry.color =?2 and markerMasterEntry.subcode01 =?3 and markerMasterEntry.subcode02 =?4 and markerMasterEntry.subcode03 =?5 and markerMasterEntry.subcode04 =?6 and markerMasterEntry.subcode05 =?7 and markerMasterEntry.subcode06 =?8 and markerMasterEntry.subcode07 =?9 and markerMasterEntry.subcode08 =?10 and markerMasterEntry.subcode09 =?11 and markerMasterEntry.subcode10 =?12 and markerMasterEntry.plantCode = ?13 and markerMasterEntry.approvalFlag = 'A' order by markerMasterEntry.id")
	List<MarkerMasterEntry> getMarkerByStyleAndColorsApproval(String style, String color, String subcode01, String subcode02,
			String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08,
			String subcode09, String subcode10, String plantCode);

    @Query("select markerMasterEntry from MarkerMasterEntry markerMasterEntry where markerMasterEntry.style =?1 and markerMasterEntry.color =?2 and markerMasterEntry.subcode01 =?3 and markerMasterEntry.subcode02 =?4 and markerMasterEntry.subcode03 =?5 and markerMasterEntry.subcode04 =?6 and markerMasterEntry.subcode05 =?7 and markerMasterEntry.subcode06 =?8 and markerMasterEntry.subcode07 =?9 and markerMasterEntry.subcode08 =?10 and markerMasterEntry.subcode09 =?11 and markerMasterEntry.subcode10 =?12 and markerMasterEntry.plantCode = ?13 order by markerMasterEntry.id")
    List<MarkerMasterEntry> getMarkerByStyleAndColors(String style, String color, String subcode01, String subcode02,
                                                      String subcode03, String subcode04, String subcode05, String subcode06, String subcode07, String subcode08,
                                                      String subcode09, String subcode10, String plantCode);

    @Query(value="select distinct markerMasterEntry.plant_code, markerMasterEntry.plant_description, markerMasterEntry.style, markerMasterEntry.color, markerMasterEntry.color_desc, markerMasterEntry.item_code, markerMasterEntry.subcode01, markerMasterEntry.subcode02, markerMasterEntry.subcode03, markerMasterEntry.subcode04, markerMasterEntry.subcode05, markerMasterEntry.subcode06, markerMasterEntry.subcode07, markerMasterEntry.subcode08, markerMasterEntry.subcode09, markerMasterEntry.subcode10, markerMasterEntry.width, markerMasterEntry.body_fabric, markerMasterEntry.item_type, markerMasterEntry.planned_avg from marker_master_entry markerMasterEntry where markerMasterEntry.style like ?1 and markerMasterEntry.color like ?2 and markerMasterEntry.plant_code in (select userPlant.plant_code from jhi_user_plant userPlant where userPlant.login = ?3)",
        countQuery = "select COUNT(distinct CONCAT(COALESCE(markerMasterEntry.plant_code, ''), COALESCE(markerMasterEntry.style, ''), COALESCE(markerMasterEntry.color, ''), COALESCE(markerMasterEntry.plant_code, ''), COALESCE(markerMasterEntry.item_code, ''))) from marker_master_entry markerMasterEntry where markerMasterEntry.style like ?1 and markerMasterEntry.color like ?2 and markerMasterEntry.plant_code in (select userPlant.plant_code from jhi_user_plant userPlant where userPlant.login = ?3)", nativeQuery = true)
    Page<Object[]> findAllByStyle(String style, String color, String login, Pageable pageable);

    @Query(value="select distinct markerMasterEntry.plant_code, markerMasterEntry.plant_description, markerMasterEntry.style, markerMasterEntry.color, markerMasterEntry.color_desc, markerMasterEntry.item_code, markerMasterEntry.subcode01, markerMasterEntry.subcode02, markerMasterEntry.subcode03, markerMasterEntry.subcode04, markerMasterEntry.subcode05, markerMasterEntry.subcode06, markerMasterEntry.subcode07, markerMasterEntry.subcode08, markerMasterEntry.subcode09, markerMasterEntry.subcode10, markerMasterEntry.width, markerMasterEntry.body_fabric, markerMasterEntry.item_type, markerMasterEntry.planned_avg, markerMasterEntry.actual_avg, markerMasterEntry.marker_code, markerMasterEntry.id, markerMasterEntry.approval_flag from marker_master_entry markerMasterEntry where markerMasterEntry.style like ?1 and markerMasterEntry.color like ?2 and markerMasterEntry.approval_flag=?3 and markerMasterEntry.plant_code in (select userPlant.plant_code from jhi_user_plant userPlant where userPlant.login = ?4)",
        countQuery = "select COUNT(distinct CONCAT(COALESCE(markerMasterEntry.plant_code, ''), COALESCE(markerMasterEntry.style, ''), COALESCE(markerMasterEntry.color, ''), COALESCE(markerMasterEntry.plant_code, ''), COALESCE(markerMasterEntry.item_code, ''))) from marker_master_entry markerMasterEntry where markerMasterEntry.style like ?1 and markerMasterEntry.color like ?2 and markerMasterEntry.plant_code in (select userPlant.plant_code from jhi_user_plant userPlant where userPlant.login = ?3)", nativeQuery = true)
    Page<Object[]> findAllByStatus(String projectcode, String color, String status, String login, Pageable page);
}
