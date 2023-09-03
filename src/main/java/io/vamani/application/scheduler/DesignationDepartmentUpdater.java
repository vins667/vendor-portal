package io.vamani.application.scheduler;

import io.vamani.application.domain.DepartmentMaster;
import io.vamani.application.domain.DesignationMaster;
import io.vamani.application.domain.JobProfile;
import io.vamani.application.mssql.repository.EmployeeAllViewRepository;
import io.vamani.application.repository.DepartmentMasterRepository;
import io.vamani.application.repository.DesignationMasterRepository;
import io.vamani.application.repository.JobProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
public class DesignationDepartmentUpdater {

    @Autowired
    private EmployeeAllViewRepository employeeAllViewRepository;

    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;

    @Autowired
    private DesignationMasterRepository designationMasterRepository;

    @Autowired
    private JobProfileRepository jobProfileRepository;

    @Scheduled(cron = "0 49 12 * * ?")
    public void publishUpdates() {
        System.out.println("Designation Department Updater Scheduler started");
        List<Object[]> departmentList = employeeAllViewRepository.getDistinctDeptCode();
        if (departmentList != null) {
            for (Object[] objects : departmentList) {
                DepartmentMaster departmentMaster = departmentMasterRepository.findByDeptCode(objects[0].toString().trim());
                if (departmentMaster != null) {} else {
                    departmentMaster = new DepartmentMaster();
                    departmentMaster.setDeptCode(objects[0].toString().trim());
                    departmentMaster.setDeptDesc(objects[1].toString().trim());
                    departmentMaster.setStatus("Y");
                    departmentMaster.setCreatedBy("admin");
                    departmentMaster.setCreatedDate(Instant.now());
                    departmentMasterRepository.save(departmentMaster);
                }
            }
        }

        List<Object[]> designationList = employeeAllViewRepository.getDistinctDesignCode();
        if (designationList != null) {
            for (Object[] objects : designationList) {
                DesignationMaster designationMaster = designationMasterRepository.findByDesignationCode(objects[0].toString().trim());
                if (designationMaster != null) {} else {
                    designationMaster = new DesignationMaster();
                    designationMaster.setDesignationCode(objects[0].toString().trim());
                    designationMaster.setDesignationName(objects[1].toString().trim());
                    designationMaster.setStatus("Y");
                    designationMaster.setFlowType("N");
                    designationMaster.setCreatedBy("admin");
                    designationMaster.setCreatedDate(Instant.now());
                    designationMasterRepository.save(designationMaster);
                }
            }
        }

        /*List<Object[]> departmentDesignationList = employeeAllViewRepository.getDistinctDeptCodeAndDesignCode();
        if (departmentDesignationList != null) {
            for (Object[] objects : departmentDesignationList) {
                JobProfile jobProfile = jobProfileRepository.getDistinctByDepartmentAndDesignationAndSwCode(objects[0].toString(), objects[2].toString(), objects[4].toString(), objects[5].toString());
                if (jobProfile != null) {} else {
                    jobProfile = new JobProfile();
                    jobProfile.setDepartment(objects[0].toString());
                    jobProfile.setFileName("JD-Default.pdf");
                    jobProfile.setDepartmentDesc(objects[1].toString());
                    jobProfile.setDesignation(objects[2].toString());
                    jobProfile.setDesignationDesc(objects[3].toString());
                    jobProfile.setSwCode(objects[4].toString());
                    jobProfile.setFactory(objects[5].toString());
                    jobProfile.setFlowType("N");
                    jobProfile.setCreatedBy("admin");
                    jobProfile.setCreatedDate(Instant.now());
                    try {
                        jobProfileRepository.save(jobProfile);
                    } catch (Exception e) {
                    }
                }
            }
        }*/
        System.out.println("Designation Department Updater Scheduler ended");
    }
}
