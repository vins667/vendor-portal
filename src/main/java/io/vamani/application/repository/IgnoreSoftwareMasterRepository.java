package io.vamani.application.repository;

import io.vamani.application.domain.IgnoreSoftwareMaster;
import io.vamani.application.domain.WorkerRecruitment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IgnoreSoftwareMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IgnoreSoftwareMasterRepository extends JpaRepository<IgnoreSoftwareMaster, Long> {
	 @Query("select ignoreSoftwareMaster from IgnoreSoftwareMaster ignoreSoftwareMaster where ignoreSoftwareMaster.swName like ?1 and ignoreSoftwareMaster.swPublisher like ?2")
	 Page<IgnoreSoftwareMaster> findAllByFilter(String swName, String swPublisher, Pageable pageable);
}
