package io.vamani.application.repository;

import io.vamani.application.domain.EducationMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the EducationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationMasterRepository extends JpaRepository<EducationMaster, Long> {
    @Query("select educationMaster from EducationMaster educationMaster order by educationMaster.description")
    List<EducationMaster> orderedAll();
}
