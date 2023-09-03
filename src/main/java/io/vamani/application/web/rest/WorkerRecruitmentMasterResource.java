package io.vamani.application.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.model.WorkerMasterBean;
import io.vamani.application.mssql.repository.*;
import io.vamani.application.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for managing WorkerRecruitment.
 */
@RestController
@RequestMapping("/api")
public class WorkerRecruitmentMasterResource {
    private final Logger log = LoggerFactory.getLogger(WorkerRecruitmentResource.class);

    private static final String ENTITY_NAME = " WorkerRecruitmentMaster";

    @Autowired
    private BankMasterRepository bankMasterRepository;

    @Autowired
    private CatgoryRepository catgoryRepository;

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private FoodcatRepository foodcatRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private RecruitmentCountryMasterRepository recruitmentCountryMasterRepository;

    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;

    @Autowired
    private DesignationMasterRepository designationMasterRepository;

    @Autowired
    private EducationMasterRepository educationMasterRepository;

    @Autowired
    private EducationTypeMasterRepository educationTypeMasterRepository;

    @Autowired
    private InstituteMasterRepository instituteMasterRepository;

    @Autowired
    private LanguageMasterRepository languageMasterRepository;

    @Autowired
    private NominationTypeMasterRepository nominationTypeMasterRepository;

    @Autowired
    private OccupationMasterRepository occupationMasterRepository;

    @Autowired
    private RelationMasterRepository relationMasterRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private StaffWorkRepository staffWorkRepository;

    @Autowired
    private SubdeptRepository subdeptRepository;

    @Autowired
    private WoffRepository woffRepository;

    public WorkerRecruitmentMasterResource() {
    }

    @GetMapping("/worker-recruitments-master")
    public ResponseEntity<WorkerMasterBean> getWorkerRecruitment() {
        WorkerMasterBean workerMasterBean = new WorkerMasterBean();

        workerMasterBean.setBankMasters(bankMasterRepository.orderedAll());
        workerMasterBean.setCatgories(catgoryRepository.orderedAll());
        workerMasterBean.setCosts(costRepository.orderedAll());
        workerMasterBean.setCountries(recruitmentCountryMasterRepository.orderedAll());
        workerMasterBean.setDepartmentMasters(departmentMasterRepository.orderedAll());
        workerMasterBean.setDesignationMasters(designationMasterRepository.orderedAll());
        workerMasterBean.setEducationMasters(educationMasterRepository.orderedAll());
        workerMasterBean.setEducationTypeMasters(educationTypeMasterRepository.orderedAll());
        workerMasterBean.setFloors(floorRepository.orderedAll());
        workerMasterBean.setFoodcats(foodcatRepository.orderedAll());
        workerMasterBean.setGrades(gradeRepository.orderedAll());
        workerMasterBean.setInstituteMasters(instituteMasterRepository.orderedAll());
        workerMasterBean.setLanguageMasters(languageMasterRepository.orderedAll());
        workerMasterBean.setNominationTypeMasters(nominationTypeMasterRepository.orderedAll());
        workerMasterBean.setOccupationMasters(occupationMasterRepository.orderedAll());
        workerMasterBean.setRelationMasters(relationMasterRepository.orderedAll());
        workerMasterBean.setSections(sectionRepository.orderedAll());
        workerMasterBean.setShifts(shiftRepository.orderedAll());
        workerMasterBean.setStaffWorks(staffWorkRepository.orderedAll());
        workerMasterBean.setSubdepts(subdeptRepository.orderedAll());
        workerMasterBean.setWoffs(woffRepository.orderedAll());

        return ResponseUtil.wrapOrNotFound(Optional.of(workerMasterBean));
    }
}
