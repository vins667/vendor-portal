package io.vamani.application.repository;

import io.vamani.application.domain.VcutStylePlanSessionBreakup;
import io.vamani.application.domain.VcutStylePlanSessionBreakupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Spring Data  repository for the VcutPlanChangeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutStylePlanSessionBreakupRepository extends JpaRepository<VcutStylePlanSessionBreakup, VcutStylePlanSessionBreakupId> {
    @Query("select vcutStylePlanSessionBreakup from VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup where vcutStylePlanSessionBreakup.id.vcutStylePlanUploadId = ?1 order by vcutStylePlanSessionBreakup.order")
    List<VcutStylePlanSessionBreakup> findAllByVcutPlanId(Long planId);

    @Modifying
    @Transactional
    @Query("delete from VcutStylePlanSessionBreakup vcutStylePlanSessionBreakup where vcutStylePlanSessionBreakup.id.vcutStylePlanUploadId = ?1")
    void deleteByPlanId(Long planId);
}
