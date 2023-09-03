package io.vamani.application.repository;

import io.vamani.application.domain.RecruitmentCountryMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RecruitmentCountryMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruitmentCountryMasterRepository extends JpaRepository<RecruitmentCountryMaster, Long> {
    @Query("select recruitmentCountryMaster from RecruitmentCountryMaster recruitmentCountryMaster order by recruitmentCountryMaster.description")
    List<RecruitmentCountryMaster> orderedAll();
}
