package io.vamani.application.repository;

import io.vamani.application.domain.RecruitmentDocumentMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RecruitmentDocumentMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruitmentDocumentMasterRepository extends JpaRepository<RecruitmentDocumentMaster, Long> {
    @Query("select recruitmentDocumentMaster from RecruitmentDocumentMaster recruitmentDocumentMaster where recruitmentDocumentMaster.documentType in (?1) order by recruitmentDocumentMaster.id")
    List<RecruitmentDocumentMaster> findAllByDocumentType(List<String> documentType);
}
