package io.vamani.application.repository;

import io.vamani.application.domain.FabricSplFinishMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FabricSplFinishMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricSplFinishMasterRepository extends JpaRepository<FabricSplFinishMaster, Long> {

}
