package io.vamani.application.repository;

import io.vamani.application.domain.FabricCreationWarpDetails;
import io.vamani.application.domain.FabricCreationWarpDetailsId;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the FabricCreationWarpDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricCreationWarpDetailsRepository extends JpaRepository<FabricCreationWarpDetails, FabricCreationWarpDetailsId> {

    @Modifying
    @Transactional
    @Query("delete from FabricCreationWarpDetails fabricCreationWarpDetails where fabricCreationWarpDetails.id.fabricCreationMaster.id =?1")
    void deleteAllByFabricCreationMasterId(Long fabricCreationMasterId);
}
