package io.vamani.application.repository;

import io.vamani.application.domain.AssetCompanyMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssetCompanyMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetCompanyMasterRepository extends JpaRepository<AssetCompanyMaster, Long> {

}
