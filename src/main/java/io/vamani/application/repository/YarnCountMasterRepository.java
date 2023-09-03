package io.vamani.application.repository;

import io.vamani.application.domain.AssetAuditDetails;
import io.vamani.application.domain.YarnCountMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the YarnCountMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface YarnCountMasterRepository extends JpaRepository<YarnCountMaster, Long> {
	@Query("select yarnCountMaster from YarnCountMaster yarnCountMaster where yarnCountMaster.code like ?1 and yarnCountMaster.description like ?2")
    Page<YarnCountMaster> findByFilter(String code, String name, Pageable pageable);
}
