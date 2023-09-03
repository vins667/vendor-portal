package io.vamani.application.repository;

import io.vamani.application.domain.KnitTypeMaster;
import io.vamani.application.domain.YarnCountMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KnitTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KnitTypeMasterRepository extends JpaRepository<KnitTypeMaster, Long> {
	@Query("select knitTypeMaster from KnitTypeMaster knitTypeMaster where knitTypeMaster.code like ?1 and knitTypeMaster.description like ?2")
    Page<KnitTypeMaster> findByFilter(String code, String name, Pageable pageable);
}
