package io.vamani.application.repository;

import io.vamani.application.domain.VcutMainEntryMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data  repository for the VcutMainEntryMasterEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutMainEntryMasterEntityRepository extends JpaRepository<VcutMainEntryMasterEntity, Long> {
    @Query("select vcutMainEntryMasterEntity from VcutMainEntryMasterEntity vcutMainEntryMasterEntity where vcutMainEntryMasterEntity.vcutStylePlanUploadId = ?1 and vcutMainEntryMasterEntity.entryTime=?2")
    VcutMainEntryMasterEntity findByPlanIdAndTime(Long planId, Instant entryDate);

    @Query("select vcutMainEntryMasterEntity from VcutMainEntryMasterEntity vcutMainEntryMasterEntity where vcutMainEntryMasterEntity.vcutStylePlanUploadId = ?1 order by vcutMainEntryMasterEntity.entryTime")
    List<VcutMainEntryMasterEntity> findByPlanId(Long planId);

    @Query("select vcutMainEntryMasterEntity.entryTime, vcutMainEntryIssueDetails.vcutOperationIssueMasterId  from VcutMainEntryMasterEntity vcutMainEntryMasterEntity inner join VcutMainEntryIssueDetails vcutMainEntryIssueDetails on vcutMainEntryMasterEntity.id=vcutMainEntryIssueDetails.vcutMainEntryMasterId where vcutMainEntryMasterEntity.entryType='ALT' and vcutMainEntryMasterEntity.vcutStylePlanUploadId = ?1 group by vcutMainEntryMasterEntity.entryTime, vcutMainEntryIssueDetails.vcutOperationIssueMasterId  order by vcutMainEntryMasterEntity.entryTime")
    List<Object[]> findByDefectsPlanId(Long planId);

    @Query("select vcutMainEntryMasterEntity.entryTime, vcutMainEntryMasterEntity.vcutOperationMasterId, count(distinct vcutMainEntryIssueDetails.vcutOperationIssueMasterId) as countOperation  from VcutMainEntryMasterEntity vcutMainEntryMasterEntity inner join VcutMainEntryIssueDetails vcutMainEntryIssueDetails on vcutMainEntryMasterEntity.id=vcutMainEntryIssueDetails.vcutMainEntryMasterId where vcutMainEntryMasterEntity.entryType='ALT' and vcutMainEntryMasterEntity.vcutStylePlanUploadId = ?1 group by vcutMainEntryMasterEntity.entryTime, vcutMainEntryMasterEntity.vcutOperationMasterId  order by vcutMainEntryMasterEntity.entryTime")
    List<Object[]> findByDefectsOperationPlanId(Long planId);

    @Query("select vcutMainEntryIssueDetails.coordinateType, vcutMainEntryIssueDetails.coordinateX, vcutMainEntryIssueDetails.coordinateY  from VcutMainEntryMasterEntity vcutMainEntryMasterEntity inner join VcutMainEntryIssueDetails vcutMainEntryIssueDetails on vcutMainEntryMasterEntity.id=vcutMainEntryIssueDetails.vcutMainEntryMasterId where vcutMainEntryMasterEntity.entryType='ALT' and vcutMainEntryMasterEntity.vcutStylePlanUploadId = ?1  order by vcutMainEntryMasterEntity.entryTime")
    List<Object[]> findByDefectsAxisPlanId(Long planId);
}
