package io.vamani.application.repository;

import io.vamani.application.domain.PackingProgressEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PackingProgressDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PackingProgressEntryRepository extends JpaRepository<PackingProgressEntry, Long> {
    @Query("select packingProgressEntry from PackingProgressEntry packingProgressEntry where packingProgressEntry.packingProductionEntry.id = ?1")
    List<PackingProgressEntry> findAllByPackingProductionEntryId(Long packingProductionEntryId);
}
