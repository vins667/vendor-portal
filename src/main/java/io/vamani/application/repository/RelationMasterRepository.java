package io.vamani.application.repository;

import io.vamani.application.domain.RelationMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RelationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelationMasterRepository extends JpaRepository<RelationMaster, Long> {
    @Query("select relationMaster from RelationMaster relationMaster order by relationMaster.description")
    List<RelationMaster> orderedAll();

}
