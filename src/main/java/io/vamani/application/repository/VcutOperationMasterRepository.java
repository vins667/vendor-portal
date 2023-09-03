package io.vamani.application.repository;
import io.vamani.application.domain.VcutOperationMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the VcutOperationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutOperationMasterRepository extends JpaRepository<VcutOperationMaster, Long> {
    @Query("select cutOperationMaster from VcutOperationMaster cutOperationMaster where cutOperationMaster.style = ?1 and cutOperationMaster.description = ?2")
    VcutOperationMaster findByStyleAndDescription(String styleNo, String description);

    @Query("select cutOperationMaster from VcutOperationMaster cutOperationMaster where cutOperationMaster.style = ?1 order by cutOperationMaster.description")
    List<VcutOperationMaster> findByStyle(String styleNo);

    @Query("select cutOperationMaster from VcutOperationMaster cutOperationMaster where cutOperationMaster.style = ?1 and cutOperationMaster.inspection = true order by cutOperationMaster.id")
    List<VcutOperationMaster> findByStyleAndInspection(String styleNo);

    @Query("select distinct cutOperationMaster.itemName, cutOperationMaster.style  from VcutOperationMaster cutOperationMaster where cutOperationMaster.style like ?1 and cutOperationMaster.itemName like ?2")
    Page<Object[]> findByStyleNoAndItem(String styleNo, String poNo , Pageable page);
}
