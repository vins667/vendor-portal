package io.vamani.application.repository;

import io.vamani.application.domain.JobProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the JobProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobProfileRepository extends JpaRepository<JobProfile, Long> {
    @Query("select jobProfile from JobProfile jobProfile where jobProfile.factory=?1 and jobProfile.department=?2 and jobProfile.swCode='2' order by jobProfile.ordering")
    List<JobProfile> findByDepartment(String factory, String department);

    @Query("select jobProfile from JobProfile jobProfile where jobProfile.factory=?1 and jobProfile.swCode='2' order by jobProfile.departmentDesc, jobProfile.ordering")
    List<JobProfile> findAllByCustom(String factory);

    @Query("select jobProfile from JobProfile jobProfile where jobProfile.factory = ?1 and jobProfile.departmentDesc like ?2 and jobProfile.designationDesc like ?3 and coalesce(jobProfile.fileName, 'JD-Default.pdf') = 'JD-Default.pdf' and jobProfile.swCode='2'")
    Page<JobProfile> findAllPending(String factory, String department, String designation, Pageable pageable);

    @Query("select jobProfile from JobProfile jobProfile where jobProfile.factory = ?1 and jobProfile.departmentDesc like ?2 and jobProfile.designationDesc like ?3 and coalesce(jobProfile.fileName, 'JD-Default.pdf') <> 'JD-Default.pdf' and jobProfile.swCode='2'")
    Page<JobProfile> findAllClosed(String factory, String department, String designation, Pageable pageable);

    @Query("select jobProfile from JobProfile jobProfile where jobProfile.department = ?1 and jobProfile.designation = ?2 and jobProfile.swCode = ?3 and jobProfile.factory = ?4")
    JobProfile getDistinctByDepartmentAndDesignationAndSwCode(String department, String designation, String swCode, String factory);

    @Query("select distinct jobProfile.department, jobProfile.departmentDesc from JobProfile jobProfile where jobProfile.swCode='2' order by jobProfile.departmentDesc")
    List<Object[]> getDistinctByDepartmentDesc();
    
    @Query("select distinct jobProfile from JobProfile jobProfile where jobProfile.factory=?1  order by jobProfile.departmentDesc, jobProfile.department, jobProfile.designationDesc, jobProfile.designation")
    List<JobProfile> findAllByFactory(String factory);
}
