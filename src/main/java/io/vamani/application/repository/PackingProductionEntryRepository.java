package io.vamani.application.repository;

import io.vamani.application.domain.PackingLineIssue;
import io.vamani.application.domain.PackingProductionEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PackingProgressEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackingProductionEntryRepository extends JpaRepository<PackingProductionEntry, Long> {
    @Query("select packingProductionEntry from PackingProductionEntry packingProductionEntry where packingProductionEntry.style like ?1 and packingProductionEntry.color like ?2 and packingProductionEntry.plantCode in (select userPlant.id.plantCode from UserPlant userPlant where userPlant.id.login = ?3)")
    Page<PackingProductionEntry> findAllByTypeAndPonoAndStyle(String style, String color, String login, Pageable pageable);
}
