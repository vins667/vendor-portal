package io.vamani.application.repository;

import io.vamani.application.domain.LeaveMasterRemarksDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the LeaveMasterRemarksDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveMasterRemarksDetailsRepository extends JpaRepository<LeaveMasterRemarksDetails, Long> {
    @Query("select leaveMasterRemarksDetails from LeaveMasterRemarksDetails leaveMasterRemarksDetails where leaveMasterRemarksDetails.leaveMaster.id=?1 and leaveMasterRemarksDetails.forwardCode is not null order by leaveMasterRemarksDetails.id")
    List<LeaveMasterRemarksDetails> getLeaveMasterRemarksDetailsByLeaveMasterId(Long leaveMasterId);

    @Query("select leaveMasterRemarksDetails from LeaveMasterRemarksDetails leaveMasterRemarksDetails where leaveMasterRemarksDetails.leaveMaster.id=?1 and leaveMasterRemarksDetails.forwardCode is not null order by leaveMasterRemarksDetails.id desc")
    List<LeaveMasterRemarksDetails> getLeaveMasterRemarksDetailsByLeaveMasterIdDesc(Long leaveMasterId);

    @Query("select leaveMasterRemarksDetails from LeaveMasterRemarksDetails leaveMasterRemarksDetails where leaveMasterRemarksDetails.leaveMaster.id=?1 and (leaveMasterRemarksDetails.forwardCode is null or leaveMasterRemarksDetails.forwardCode=\'\') order by leaveMasterRemarksDetails.id")
    LeaveMasterRemarksDetails getEntryLeaveMasterRemarksDetailsByLeaveMasterId(Long leaveMasterId);
}
