package io.vamani.application.repository;

import io.vamani.application.domain.YarnCountMaster;
import io.vamani.application.domain.YarnTypeMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the YarnTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface YarnTypeMasterRepository extends JpaRepository<YarnTypeMaster, Long> {
	@Query("select yarnTypeMaster from YarnTypeMaster yarnTypeMaster where yarnTypeMaster.code like ?1 and yarnTypeMaster.description like ?2")
    Page<YarnTypeMaster> findByFilter(String code, String name, Pageable pageable);
}
