package io.vamani.application.repository;

import io.vamani.application.domain.StitchLineIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the StitchLineIssue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StitchLineIssueRepository extends JpaRepository<StitchLineIssue, Long> {
    @Query("select stitchLineIssue from StitchLineIssue stitchLineIssue where stitchLineIssue.style like ?1 and stitchLineIssue.color like ?2 and stitchLineIssue.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?3)")
    Page<StitchLineIssue> findAllByTypeAndPonoAndStyle(String style, String color, String login, Pageable pageable);

    @Query(value = "select sli.plant_code, sli.plant_description, sli.projectcode, slid.decosubcode_01, slid.decosubcode_07, sli.destination,"
        +" slid.decosubcode_08, slid.line, slid.line_desc, slid.bundle_code, slid.cut_plan_bundle_id, slid.baseprimaryquantityunit, cpbd.product_code, cpbd.id product_id"
        +" from stitch_line_issue sli, stitch_line_issue_details slid, cut_plan_bundle_details cpbd  where sli.id = slid.stitch_line_issue_id and"
        +" slid.cut_plan_bundle_id = cpbd.cut_plan_bundle_id and sli.projectcode=?1"
        +" order by sli.plant_code, slid.line, slid.cut_plan_bundle_id, cpbd.id", nativeQuery = true)
    List<Object[]> findAllByProject(String project);
}
