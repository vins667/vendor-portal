package io.vamani.application.repository;

import io.vamani.application.domain.TrailMockOperation;
import io.vamani.application.domain.WorkerJoining;
import io.vamani.application.domain.WorkerRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkerJoining entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkerJoiningRepository extends JpaRepository<WorkerJoining, Long> {
    @Query("select workerRecruitment from WorkerRecruitment workerRecruitment, JobProfile jobProfile where workerRecruitment.departmentMaster.deptCode = jobProfile.department and workerRecruitment.designationMaster.designationCode = jobProfile.designation and  (COALESCE(jobProfile.flowType,'N')='N' OR workerRecruitment.status='H') and workerRecruitment.name like ?1 and workerRecruitment.aadharNo like ?2  and workerRecruitment.departmentMaster.deptDesc like ?3 and workerRecruitment.designationMaster.designationName like ?4 and workerRecruitment.factoryCode=?5 order by workerRecruitment.id desc")
    Page<WorkerRecruitment> findAllByFilterJoining(String name, String aadharNo, String department, String designation, String factoryCode, Pageable pageable);
	
	 @Query("select workerJoining from WorkerJoining workerJoining where workerJoining.workerRecruitment.id=?1")
	 WorkerJoining findByWorkerJoining(Long id);

}
