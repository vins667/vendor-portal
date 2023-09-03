package io.vamani.application.repository;

import io.vamani.application.domain.FabricUomMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FabricUomMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricUomMasterRepository extends JpaRepository<FabricUomMaster, Long> {

}
