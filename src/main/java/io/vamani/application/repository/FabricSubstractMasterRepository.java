package io.vamani.application.repository;

import io.vamani.application.domain.FabricSubstractMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FabricSubstractMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricSubstractMasterRepository extends JpaRepository<FabricSubstractMaster, Long> {

}
