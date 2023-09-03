package io.vamani.application.repository;
import io.vamani.application.domain.VcutMainEntryIssueDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the VcutMainEntryIssueDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutMainEntryIssueDetailsRepository extends JpaRepository<VcutMainEntryIssueDetails, Long> {
    @Modifying
    @Transactional
    @Query("delete from VcutMainEntryIssueDetails cutMainEntryIssueDetails where cutMainEntryIssueDetails.vcutMainEntryMasterId = ?1")
    void deleteAllByVcutId(Long vcutId);
}
