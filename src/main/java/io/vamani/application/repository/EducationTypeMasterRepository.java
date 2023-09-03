package io.vamani.application.repository;

import io.vamani.application.domain.EducationTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the EducationTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationTypeMasterRepository extends JpaRepository<EducationTypeMaster, Long> {
    @Query("select educationTypeMaster from EducationTypeMaster educationTypeMaster order by educationTypeMaster.description")
    List<EducationTypeMaster> orderedAll();
}
