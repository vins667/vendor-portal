package io.vamani.application.repository;

import io.vamani.application.domain.TrimsTemplateDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the TrimsTemplateDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrimsTemplateDetailsRepository extends JpaRepository<TrimsTemplateDetails, Long> {
    @Modifying
    @Transactional
    @Query("delete from TrimsTemplateDetails trimsTemplateDetails where trimsTemplateDetails.trimsTemplateMaster.id =?1")
    void deleteAllByTemplateMasterId(Long masterId);
}
