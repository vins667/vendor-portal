package io.vamani.application.repository;

import io.vamani.application.domain.FabricCreationContentDetails;
import io.vamani.application.domain.FabricCreationContentDetailsId;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the FabricCreationContentDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FabricCreationContentDetailsRepository extends JpaRepository<FabricCreationContentDetails, FabricCreationContentDetailsId> {
    @Modifying
    @Transactional
    @Query("delete from FabricCreationContentDetails fabricCreationContentDetails where fabricCreationContentDetails.id.fabricCreationMaster.id =?1")
    void deleteAllByFabricCreationMasterId(Long fabricCreationMasterId);
}
