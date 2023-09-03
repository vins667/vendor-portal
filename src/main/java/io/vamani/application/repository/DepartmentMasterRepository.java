package io.vamani.application.repository;

import io.vamani.application.domain.DepartmentMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the DepartmentMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartmentMasterRepository extends JpaRepository<DepartmentMaster, Long> {
    @Query("select departmentMaster from DepartmentMaster departmentMaster where departmentMaster.deptCode=?1")
    DepartmentMaster findByDeptCode(String deptCode);

    @Query("select departmentMaster from DepartmentMaster departmentMaster order by departmentMaster.deptDesc")
    List<DepartmentMaster> orderedAll();
}
