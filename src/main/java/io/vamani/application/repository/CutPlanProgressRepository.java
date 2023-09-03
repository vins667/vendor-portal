package io.vamani.application.repository;

import io.vamani.application.domain.CutPlanProgress;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CutPlanProgress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutPlanProgressRepository extends JpaRepository<CutPlanProgress, Long> {
}
