package io.vamani.application.repository;

import io.vamani.application.domain.PackingLineIssueTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PackingLineIssue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackingLineIssueTransactionRepository extends JpaRepository<PackingLineIssueTransaction, Long> {
}
