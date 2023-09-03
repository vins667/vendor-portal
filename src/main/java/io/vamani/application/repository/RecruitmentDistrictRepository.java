package io.vamani.application.repository;

import io.vamani.application.domain.RecruitmentDistrict;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RecruitmentDistrict entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruitmentDistrictRepository extends JpaRepository<RecruitmentDistrict, Long> {
    @Query("select recruitmentDistrict from RecruitmentDistrict recruitmentDistrict where recruitmentDistrict.recruitmentStateMaster.id = ?1 order by recruitmentDistrict.description")
    List<RecruitmentDistrict> findAllByStateId(Long stateMasterId);
}
