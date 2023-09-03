package io.vamani.application.repository;
import io.vamani.application.domain.CutPlanProgressEntry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CutPlanProgressEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutPlanProgressEntryRepository extends JpaRepository<CutPlanProgressEntry, Long> {
    @Query("select cutPlanProgressEntry from CutPlanProgressEntry cutPlanProgressEntry where cutPlanProgressEntry.cutPlanEntry.id = ?1 order by cutPlanProgressEntry.id")
    List<CutPlanProgressEntry> findAllByCutPlanEntryId(Long cutPlanEntryId);
}
