package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.TemplateDetailsBreakup;
import io.vamani.application.vendorportal.domain.TemplateDetailsBreakupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring Data  repository for the TemplateDetailsBreakup entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface TemplateDetailsBreakupRepository extends JpaRepository<TemplateDetailsBreakup, TemplateDetailsBreakupId> {

    @Query("select templateDetailsBreakup from TemplateDetailsBreakup templateDetailsBreakup where templateDetailsBreakup.id.templateDetailsId=?1 order by templateDetailsBreakup.id.id")
    List<TemplateDetailsBreakup> findAllByTemplateDetailsId(Long detailId);

    @Modifying
    @Transactional
    @Query("delete from TemplateDetailsBreakup templateDetailsBreakup where templateDetailsBreakup.id.templateDetailsId =?1")
    void deleteAllByTemplateDetailsId(Long workerJoiningId);
}
