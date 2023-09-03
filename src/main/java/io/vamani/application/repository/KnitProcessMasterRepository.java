package io.vamani.application.repository;

import io.vamani.application.domain.KnitProcessMaster;
import io.vamani.application.domain.YarnCountMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KnitProcessMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KnitProcessMasterRepository extends JpaRepository<KnitProcessMaster, Long> {
	@Query("select knitProcessMaster from KnitProcessMaster knitProcessMaster where knitProcessMaster.code like ?1 and knitProcessMaster.description like ?2")
    Page<KnitProcessMaster> findByFilter(String code, String name, Pageable pageable);
}
