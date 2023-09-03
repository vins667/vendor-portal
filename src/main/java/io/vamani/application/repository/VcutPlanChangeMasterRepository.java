package io.vamani.application.repository;
import io.vamani.application.domain.VcutPlanChangeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the VcutPlanChangeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutPlanChangeMasterRepository extends JpaRepository<VcutPlanChangeMaster, Long> {
    @Query("select vcutPlanChangeMaster from VcutPlanChangeMaster vcutPlanChangeMaster order by id")
    List<VcutPlanChangeMaster> findAll();
}
