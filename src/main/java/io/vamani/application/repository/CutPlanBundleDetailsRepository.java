package io.vamani.application.repository;

import io.vamani.application.domain.CutPlanBundleDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the CutPlanBundleDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutPlanBundleDetailsRepository extends JpaRepository<CutPlanBundleDetails, Long> {
    @Query("select cutPlanBundleDetails.productCode from CutPlanBundleDetails cutPlanBundleDetails where cutPlanBundleDetails.id = ?1")
    String findSequenceById(Long id);

    @Query(value = "select id, product_code from cut_plan_bundle_details where id in("
                    +" select min(id) from cut_plan_bundle_details where cut_plan_bundle_id=?1"
                    +" union"
                    +" select max(id) from cut_plan_bundle_details where cut_plan_bundle_id=?1) order by id", nativeQuery = true)
    List<Object[]> findAllProductCodeById(Long id);
}
