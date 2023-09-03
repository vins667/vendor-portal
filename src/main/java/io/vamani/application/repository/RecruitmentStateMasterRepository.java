package io.vamani.application.repository;

import io.vamani.application.domain.RecruitmentStateMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RecruitmentStateMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruitmentStateMasterRepository extends JpaRepository<RecruitmentStateMaster, Long> {
    @Query("select recruitmentStateMaster from RecruitmentStateMaster recruitmentStateMaster where recruitmentStateMaster.recruitmentCountryMaster.id = ?1 order by recruitmentStateMaster.description")
    List<RecruitmentStateMaster> findAllByCountryId(Long countryMasterId);
}
