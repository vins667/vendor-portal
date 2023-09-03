package io.vamani.application.repository;

import io.vamani.application.domain.FabricOthersMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FabricOthersMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricOthersMasterRepository extends JpaRepository<FabricOthersMaster, Long> {

}
