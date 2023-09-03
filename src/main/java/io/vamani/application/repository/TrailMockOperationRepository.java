package io.vamani.application.repository;

import io.vamani.application.domain.TrailMockOperation;
import io.vamani.application.domain.WorkerRecruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TrailMockOperation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrailMockOperationRepository extends JpaRepository<TrailMockOperation, Long> {

    @Query(value = "select distinct trail_mock_operation from TrailMockOperation trail_mock_operation left join fetch trail_mock_operation.operationMasters left join fetch trail_mock_operation.machineMasters",
        countQuery = "select count(distinct trail_mock_operation) from TrailMockOperation trail_mock_operation")
    Page<TrailMockOperation> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct trail_mock_operation from TrailMockOperation trail_mock_operation left join fetch trail_mock_operation.operationMasters left join fetch trail_mock_operation.machineMasters")
    List<TrailMockOperation> findAllWithEagerRelationships();

    @Query("select trail_mock_operation from TrailMockOperation trail_mock_operation left join fetch trail_mock_operation.operationMasters left join fetch trail_mock_operation.machineMasters where trail_mock_operation.id =:id")
    Optional<TrailMockOperation> findOneWithEagerRelationships(@Param("id") Long id);
    
    @Query("select trailMockOperation from TrailMockOperation trailMockOperation where trailMockOperation.workerRecruitment.id=?1")
    TrailMockOperation findByWorkerRecruitment(Long workerRecruitmentId);
    
    @Query("select workerRecruitment from WorkerRecruitment workerRecruitment, JobProfile jobProfile where workerRecruitment.departmentMaster.deptCode = jobProfile.department and workerRecruitment.designationMaster.designationCode = jobProfile.designation and  jobProfile.flowType='O' and workerRecruitment.name like ?1 and workerRecruitment.aadharNo like ?2  and workerRecruitment.departmentMaster.deptDesc like ?3 and workerRecruitment.designationMaster.designationName like ?4 and workerRecruitment.factoryCode=?5 order by workerRecruitment.id desc")
    Page<WorkerRecruitment> findAllByFilter(String name, String aadharNo, String department, String designation, String factoryCode, Pageable pageable);
}
