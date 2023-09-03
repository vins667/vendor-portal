package io.vamani.application.repository;

import io.vamani.application.domain.StitchIssuePackTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the StitchIssuePackTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StitchIssuePackTransactionRepository extends JpaRepository<StitchIssuePackTransaction, Long> {
    @Query("select stitchIssuePackTransaction from StitchIssuePackTransaction stitchIssuePackTransaction where stitchIssuePackTransaction.stitchIssuePackId = ?1")
    List<StitchIssuePackTransaction> getStitchIssuePackTransactionByStitchIssuePackId(Long stitchIssuePackId);
}
