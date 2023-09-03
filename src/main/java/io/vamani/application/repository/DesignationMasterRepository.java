package io.vamani.application.repository;

import io.vamani.application.domain.DesignationMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the DesignationMaster entity.
 */
@Repository
public interface DesignationMasterRepository extends JpaRepository<DesignationMaster, Long> {
    @Query("select designationMaster from DesignationMaster designationMaster where designationMaster.designationCode=?1")
    DesignationMaster findByDesignationCode(String designationCode);

    @Query("select designationMaster from DesignationMaster designationMaster order by designationMaster.designationName")
    List<DesignationMaster> orderedAll();
}
