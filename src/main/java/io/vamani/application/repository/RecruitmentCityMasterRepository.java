package io.vamani.application.repository;

import io.vamani.application.domain.RecruitmentCityMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RecruitmentCityMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruitmentCityMasterRepository extends JpaRepository<RecruitmentCityMaster, Long> {
    @Query("select recruitmentCityMaster from RecruitmentCityMaster recruitmentCityMaster where recruitmentCityMaster.recruitmentDistrict.id = ?1 order by recruitmentCityMaster.description")
    List<RecruitmentCityMaster> findAllByDistrictId(Long stateMasterId);
}
