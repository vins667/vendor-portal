package io.vamani.application.repository;

import io.vamani.application.domain.TrimTemplateDetailsBreakupId;
import io.vamani.application.domain.TrimsTemplateDetailsBreakup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data repository for the TrimsTemplateDetailsBreakup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrimsTemplateDetailsBreakupRepository extends JpaRepository<TrimsTemplateDetailsBreakup, TrimTemplateDetailsBreakupId> {

	@Modifying
	@Transactional
	@Query("delete from TrimsTemplateDetailsBreakup trimsTemplateDetailsBreakup where trimsTemplateDetailsBreakup.id.trimsTemplateDetailsId =?1")
	void deleteAllByTemplateDetailsId(Long JoiningId);

    @Modifying
    @Transactional
    @Query("delete from TrimsTemplateDetailsBreakup trimsTemplateDetailsBreakup where trimsTemplateDetailsBreakup.id.trimsTemplateDetailsId in (select trimsTemplateDetails.id from TrimsTemplateDetails trimsTemplateDetails where trimsTemplateDetails.trimsTemplateMaster.id =?1)")
    void deleteAllByTemplateMasterId(Long JoiningId);

    @Query(value = "select COALESCE(max(id),'0') +1 ctr from trims_template_details_breakup where trim_template_details_id = ?1", nativeQuery = true)
    Long findMaxSeries(Long id);

}
