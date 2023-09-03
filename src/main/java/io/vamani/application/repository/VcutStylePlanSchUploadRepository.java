package io.vamani.application.repository;

import io.vamani.application.domain.VcutStylePlanSchUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AssetFileUploadMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutStylePlanSchUploadRepository extends JpaRepository<VcutStylePlanSchUpload, Long> {
}
