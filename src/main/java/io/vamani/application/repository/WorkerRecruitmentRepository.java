package io.vamani.application.repository;

import io.vamani.application.domain.WorkerRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkerRecruitment entity.
 */
@Repository
public interface WorkerRecruitmentRepository extends JpaRepository<WorkerRecruitment, Long> {
    @Query("select workerRecruitment from WorkerRecruitment workerRecruitment where workerRecruitment.name like ?1 and workerRecruitment.aadharNo like ?2 and workerRecruitment.departmentMaster.deptDesc like ?3 and workerRecruitment.designationMaster.designationName like ?4 and workerRecruitment.factoryCode=?5")
    Page<WorkerRecruitment> findAllByFilter(String name, String aadharNo, String department, String designation, String factoryCode, Pageable pageable);
}
