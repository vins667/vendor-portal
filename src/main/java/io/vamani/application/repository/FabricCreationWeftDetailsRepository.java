package io.vamani.application.repository;

import io.vamani.application.domain.FabricCreationWeftDetails;
import io.vamani.application.domain.FabricCreationWeftDetailsId;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the FabricCreationWeftDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricCreationWeftDetailsRepository extends JpaRepository<FabricCreationWeftDetails, FabricCreationWeftDetailsId> {
    @Modifying
    @Transactional
    @Query("delete from FabricCreationWeftDetails fabricCreationWeftDetails where fabricCreationWeftDetails.id.fabricCreationMaster.id =?1")
    void deleteAllByFabricCreationMasterId(Long fabricCreationMasterId);
}
