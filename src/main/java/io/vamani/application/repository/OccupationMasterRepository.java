package io.vamani.application.repository;

import io.vamani.application.domain.OccupationMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the OccupationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OccupationMasterRepository extends JpaRepository<OccupationMaster, Long> {
    @Query("select occupationMaster from OccupationMaster occupationMaster order by occupationMaster.description")
    List<OccupationMaster> orderedAll();
}
