package io.vamani.application.repository;

import io.vamani.application.domain.FabricSubstractDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the FabricSubstractDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricSubstractDetailsRepository extends JpaRepository<FabricSubstractDetails, Long> {

    @Query("select fabricSubstractDetails from FabricSubstractDetails fabricSubstractDetails where fabricSubstractDetails.fabricSubstractMaster.id=?1 order by fabricSubstractDetails.description")
    List<FabricSubstractDetails> findAllByMasterId(Long MasterId);
}
