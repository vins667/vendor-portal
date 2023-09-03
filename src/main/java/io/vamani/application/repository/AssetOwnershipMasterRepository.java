package io.vamani.application.repository;

import io.vamani.application.domain.AssetOwnershipMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetOwnershipMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetOwnershipMasterRepository extends JpaRepository<AssetOwnershipMaster, Long> {

}
