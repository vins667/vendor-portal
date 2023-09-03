package io.vamani.application.repository;

import io.vamani.application.domain.TrimsTemplateMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrimsTemplateMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrimsTemplateMasterRepository extends JpaRepository<TrimsTemplateMaster, Long> {

    @Query("select trimsTemplateMaster from TrimsTemplateMaster trimsTemplateMaster where trimsTemplateMaster.description = ?1")
    TrimsTemplateMaster findByDescription(String description);


    @Query("select trimsTemplateMaster from TrimsTemplateMaster trimsTemplateMaster where trimsTemplateMaster.accessoriesCode = ?1")
    TrimsTemplateMaster findByCode(String code);
}
