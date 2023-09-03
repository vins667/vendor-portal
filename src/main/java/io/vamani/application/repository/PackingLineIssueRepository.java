package io.vamani.application.repository;

import io.vamani.application.domain.PackingLineIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PackingLineIssue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackingLineIssueRepository extends JpaRepository<PackingLineIssue, Long> {
    @Query("select packingLineIssue from PackingLineIssue packingLineIssue where packingLineIssue.style like ?1 and packingLineIssue.color like ?2 and packingLineIssue.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?3)")
    Page<PackingLineIssue> findAllByTypeAndPonoAndStyle(String style, String color, String login, Pageable pageable);
}
