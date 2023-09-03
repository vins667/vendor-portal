package io.vamani.application.repository;
import io.vamani.application.domain.VcutMainEntryMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data  repository for the VcutMainEntryMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutMainEntryMasterRepository extends JpaRepository<VcutMainEntryMaster, Long> {
    @Query("select vcutMainEntryMaster from VcutMainEntryMaster vcutMainEntryMaster left join fetch vcutMainEntryMaster.vcutMainEntryIssueDetails where vcutMainEntryMaster.vcutStylePlanUpload.id = ?1 and vcutMainEntryMaster.entryTime=?2")
    VcutMainEntryMaster findByPlanIdAndTime(Long planId, Instant entryDate);

    @Query("select vcutMainEntryMaster from VcutMainEntryMaster vcutMainEntryMaster left join fetch vcutMainEntryMaster.vcutMainEntryIssueDetails vcutMainEntryIssueDetails where vcutMainEntryMaster.vcutStylePlanUpload.id = ?1 order by vcutMainEntryMaster.entryTime")
    List<VcutMainEntryMaster> findByPlanId(Long planId);

    @Query("select vcutMainEntryMaster.entryTime, vcutMainEntryIssueDetails.vcutOperationIssueMasterId  from VcutMainEntryMaster vcutMainEntryMaster inner join VcutMainEntryIssueDetails vcutMainEntryIssueDetails on vcutMainEntryMaster.id=vcutMainEntryIssueDetails.vcutMainEntryMasterId where vcutMainEntryMaster.entryType='ALT' and vcutMainEntryMaster.vcutStylePlanUpload.id = ?1 group by vcutMainEntryMaster.entryTime, vcutMainEntryIssueDetails.vcutOperationIssueMasterId  order by vcutMainEntryMaster.entryTime")
    List<Object[]> findByDefectsPlanId(Long planId);

    @Query("select vcutMainEntryMaster.entryTime, vcutMainEntryMaster.vcutOperationMaster.id, count(distinct vcutMainEntryIssueDetails.vcutOperationIssueMasterId) as countOperation  from VcutMainEntryMaster vcutMainEntryMaster inner join VcutMainEntryIssueDetails vcutMainEntryIssueDetails on vcutMainEntryMaster.id=vcutMainEntryIssueDetails.vcutMainEntryMasterId where vcutMainEntryMaster.entryType='ALT' and vcutMainEntryMaster.vcutStylePlanUpload.id = ?1 group by vcutMainEntryMaster.entryTime, vcutMainEntryMaster.vcutOperationMaster.id  order by vcutMainEntryMaster.entryTime")
    List<Object[]> findByDefectsOperationPlanId(Long planId);

    @Query("select vcutMainEntryIssueDetails.coordinateType, vcutMainEntryIssueDetails.coordinateX, vcutMainEntryIssueDetails.coordinateY  from VcutMainEntryMaster vcutMainEntryMaster inner join VcutMainEntryIssueDetails vcutMainEntryIssueDetails on vcutMainEntryMaster.id=vcutMainEntryIssueDetails.vcutMainEntryMasterId where vcutMainEntryMaster.entryType='ALT' and vcutMainEntryMaster.vcutStylePlanUpload.id = ?1  order by vcutMainEntryMaster.entryTime")
    List<Object[]> findByDefectsAxisPlanId(Long planId);
}
