package io.vamani.application.repository;

import io.vamani.application.domain.VcutSessionMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the VcutSessionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutSessionMasterRepository extends JpaRepository<VcutSessionMaster, Long> {
	@Query("select vcutSessionMaster from VcutSessionMaster vcutSessionMaster where vcutSessionMaster.id=?1")
	VcutSessionMaster findVcutSessionMaster(Long id);

    @Override
    @Query("select vcutSessionMaster from VcutSessionMaster vcutSessionMaster order by vcutSessionMaster.id")
    List<VcutSessionMaster> findAll();
}
