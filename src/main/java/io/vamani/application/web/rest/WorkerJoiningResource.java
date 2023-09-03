package io.vamani.application.web.rest;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.*;
import io.vamani.application.model.TrailMockSearchOperation;
import io.vamani.application.model.WorkerDocumentDetailsBean;
import io.vamani.application.model.WorkerJoiningBean;
import io.vamani.application.model.WorkerWorkFlowBean;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkerJoining.
 */
@RestController
@RequestMapping("/api")
public class WorkerJoiningResource {
    private final Logger log = LoggerFactory.getLogger(WorkerJoiningResource.class);
    private static final String ENTITY_NAME = "workerJoining";
    private final WorkerJoiningRepository workerJoiningRepository;

    @Autowired
    private WorkerJoinFlowDetailsRepository workerJoinFlowDetailsRepository;

    @Autowired
    private WorkerRecruitmentRepository workerRecruitmentRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private TrailMockOperationRepository trailMockOperationRepository;

    @Autowired
    private WorkerFamilyDetailsRepository workerFamilyDetailsRepository;

    @Autowired
    private WorkerJobsDetailsRepository workerJobsDetailsRepository;

    @Autowired
    private WorkerLanguageDetailsRepository workerLanguageDetailsRepository;

    @Autowired
    private WorkerNominationDetailsRepository workerNominationDetailsRepository;

    @Autowired
    private WorkerAddressDetailsRepository workerAddressDetailsRepository;

    @Autowired
    private WorkerEducationDetailsRepository workerEducationDetailsRepository;

    @Autowired
    private WorkerReferenceDetailsRepository workerReferenceDetailsRepository;

    @Autowired
    private WorkerDocumentDetailsRepository workerDocumentDetailsRepository;

    @Autowired
    private WorkerWorkFlowRepository workerWorkFlowRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public WorkerJoiningResource(WorkerJoiningRepository workerJoiningRepository) {
        this.workerJoiningRepository = workerJoiningRepository;
    }

    /**
     * POST  /worker-joinings : Create a new workerJoining.
     *
     * @param workerJoining the workerJoining to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerJoining, or with status 400 (Bad Request) if the workerJoining has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-joinings")
    public ResponseEntity<WorkerJoining> createWorkerJoining(@Valid @RequestBody WorkerJoining workerJoining) throws URISyntaxException {
        log.debug("REST request to save WorkerJoining : {}", workerJoining);
        if (workerJoining.getId() != null) {
            throw new BadRequestAlertException("A new workerJoining cannot already have an ID", ENTITY_NAME, "idexists");
        }
        workerJoining.setName(workerJoining.getName().toUpperCase());
        workerJoining.setGuardianName(workerJoining.getGuardianName().toUpperCase());
        workerJoining.setMotherName(workerJoining.getMotherName().toUpperCase());
        if (workerJoining.getSupervisorName() != null)
            workerJoining.setSupervisorName(workerJoining.getSupervisorName().toUpperCase());
        if (workerJoining.getLocalAddress() != null)
            workerJoining.setLocalAddress(workerJoining.getLocalAddress().toUpperCase());
        if (workerJoining.getPermanentAddress() != null)
            workerJoining.setPermanentAddress(workerJoining.getPermanentAddress().toUpperCase());
        workerJoining.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        workerJoining.setCreatedDate(Instant.now());
        WorkerJoining result = workerJoiningRepository.save(workerJoining);
        return ResponseEntity.created(new URI("/api/worker-joinings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-joinings : Updates an existing workerJoining.
     *
     * @param !workerJoining the workerJoining to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerJoining,
     * or with status 400 (Bad Request) if the workerJoining is not valid,
     * or with status 500 (Internal Server Error) if the workerJoining couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/worker-joinings-update")
    public ResponseEntity<WorkerJoining> updateWorkerJoining(@RequestBody WorkerJoiningBean workerJoiningBean) throws URISyntaxException {
        log.debug("REST request to update WorkerJoining : {}", workerJoiningBean);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        if (workerJoiningBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerJoining workerJoining = new WorkerJoining();
        BeanUtils.copyProperties(workerJoiningBean, workerJoining);

        workerJoining.setName(workerJoining.getName().toUpperCase());
        workerJoining.setGuardianName(workerJoining.getGuardianName().toUpperCase());
        workerJoining.setMotherName(workerJoining.getMotherName().toUpperCase());
        if (workerJoining.getSupervisorName() != null)
            workerJoining.setSupervisorName(workerJoining.getSupervisorName().toUpperCase());
        if (workerJoining.getLocalAddress() != null)
            workerJoining.setLocalAddress(workerJoining.getLocalAddress().toUpperCase());
        if (workerJoining.getPermanentAddress() != null)
            workerJoining.setPermanentAddress(workerJoining.getPermanentAddress().toUpperCase());
        workerJoining.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        workerJoining.setLastUpdatedDate(Instant.now());
        WorkerJoining result = workerJoiningRepository.save(workerJoining);

        for (WorkerFamilyDetails workerFamilyDetails : workerJoiningBean.getWorkerFamilyDetails()) {
            if (workerFamilyDetails.getName() != null && workerFamilyDetails.getName().length() > 0) {
                workerFamilyDetails.setName(workerFamilyDetails.getName().toUpperCase());
                workerFamilyDetails.setWorkerJoining(result);
                workerFamilyDetails.createdBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                workerFamilyDetails.createdDate(Instant.now());
                try {
                    workerFamilyDetailsRepository.save(workerFamilyDetails);
                } catch (Exception e) {}
            }
        }

        for (WorkerJobsDetails workerJobsDetails : workerJoiningBean.getWorkerJobsDetails()) {
            if (workerJobsDetails.getCompanyName() != null && workerJobsDetails.getCompanyName().length() > 0) {
                workerJobsDetails.setCompanyName(workerJobsDetails.getCompanyName().toUpperCase());
                workerJobsDetails.setDesignation(workerJobsDetails.getDesignation() != null ? workerJobsDetails.getDesignation().toUpperCase() : workerJobsDetails.getDesignation());
                workerJobsDetails.setReasonLeavig(workerJobsDetails.getReasonLeavig() != null ? workerJobsDetails.getReasonLeavig().toUpperCase() : workerJobsDetails.getReasonLeavig());
                workerJobsDetails.setWorkerJoining(result);
                workerJobsDetails.createdBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                workerJobsDetails.createdDate(Instant.now());
                try {
                    workerJobsDetailsRepository.save(workerJobsDetails);
                } catch (Exception e) {}
            }
        }

        for (WorkerLanguageDetails workerLanguageDetails : workerJoiningBean.getWorkerLanguageDetails()) {
            if (workerLanguageDetails.getLanguageMasterId() != null && workerLanguageDetails.getLanguageMasterId().longValue() > 0) {
                workerLanguageDetails.setWorkerJoining(result);
                workerLanguageDetails.createdBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                workerLanguageDetails.createdDate(Instant.now());
                try {
                    workerLanguageDetailsRepository.save(workerLanguageDetails);
                } catch (Exception e) {}
            }
        }

        for (WorkerNominationDetails workerNominationDetails : workerJoiningBean.getWorkerNominationDetails()) {
            if (workerNominationDetails.getWorkerFamilyDetails() != null && workerNominationDetails.getWorkerFamilyDetails().getId() != null) {
                workerNominationDetails.setWorkerJoining(result);
                workerNominationDetails.createdBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                workerNominationDetails.createdDate(Instant.now());
                try {
                    workerNominationDetailsRepository.save(workerNominationDetails);
                } catch (Exception e) {}
            }
        }

        for (WorkerAddressDetails workerAddressDetails : workerJoiningBean.getWorkerAddressDetails()) {
            if (workerAddressDetails.getAddressType() != null && workerAddressDetails.getAddressType().length() > 0 && workerAddressDetails.getAddressLine1() != null && workerAddressDetails.getAddressLine1().length() > 0) {
                workerAddressDetails.setAddressLine1((workerAddressDetails.getAddressLine1() != null ? workerAddressDetails.getAddressLine1().toUpperCase() : workerAddressDetails.getAddressLine1()));
                workerAddressDetails.setAddressLine2((workerAddressDetails.getAddressLine2() != null ? workerAddressDetails.getAddressLine2().toUpperCase() : workerAddressDetails.getAddressLine2()));
                workerAddressDetails.setAddressLine3((workerAddressDetails.getAddressLine3() != null ? workerAddressDetails.getAddressLine3().toUpperCase() : workerAddressDetails.getAddressLine3()));
                workerAddressDetails.setAddressLine4((workerAddressDetails.getAddressLine4() != null ? workerAddressDetails.getAddressLine4().toUpperCase() : workerAddressDetails.getAddressLine4()));

                workerAddressDetails.setWorkerJoining(result);
                try {
                    workerAddressDetailsRepository.save(workerAddressDetails);
                } catch (Exception e) {}
            }
        }

        for (WorkerEducationDetails workerEducationDetails : workerJoiningBean.getWorkerEducationDetails()) {
            if (workerEducationDetails.getEducationMaster() != null && workerEducationDetails.getEducationMaster().getId() != null) {
                workerEducationDetails.setWorkerJoining(result);
                try {
                    workerEducationDetailsRepository.save(workerEducationDetails);
                } catch (Exception e) {}
            }
        }

        for (WorkerReferenceDetails workerReferenceDetails : workerJoiningBean.getWorkerReferenceDetails()) {
            if (workerReferenceDetails.getName() != null && workerReferenceDetails.getOrganization() != null) {
                workerReferenceDetails.setName(workerReferenceDetails.getName().toUpperCase());
                workerReferenceDetails.setOrganization(workerReferenceDetails.getOrganization() != null ? workerReferenceDetails.getOrganization().toUpperCase() : workerReferenceDetails.getOrganization());
                workerReferenceDetails.setDesignation(workerReferenceDetails.getDesignation() != null ? workerReferenceDetails.getDesignation().toUpperCase() : workerReferenceDetails.getDesignation());
                workerReferenceDetails.setWorkerJoining(result);
                try {
                    workerReferenceDetailsRepository.save(workerReferenceDetails);
                } catch (Exception e) {}
            }
        }

        for (WorkerDocumentDetails workerDocumentDetails : workerJoiningBean.getWorkerDocumentDetails()) {
            if (workerDocumentDetails.getDocumentType() != null && workerDocumentDetails.getRecruitmentDocumentMaster() != null) {
                if (workerDocumentDetails != null && workerDocumentDetails.getId() != null && workerDocumentDetails.getId().longValue() > 0) {
                    WorkerDocumentDetails workerDocumentDetails1 = workerDocumentDetailsRepository.findByWorkerJoiningIdAndDocId(result.getId(), workerDocumentDetails.getRecruitmentDocumentMaster().getId(), workerDocumentDetails.getDocumentType());
                    if(workerDocumentDetails1 != null) {
                    } else {
                        workerDocumentDetails.setId(null);
                    }
                }
                workerDocumentDetails.setRemarks(workerDocumentDetails.getRemarks() != null ? workerDocumentDetails.getRemarks().toUpperCase() : workerDocumentDetails.getRemarks());
                workerDocumentDetails.setWorkerJoining(result);
                try {
                    WorkerDocumentDetails result1 = workerDocumentDetailsRepository.save(workerDocumentDetails);
                    /*if (result1 != null && workerDocumentDetailsBean.getMultipartFile() != null) {
                        MultipartFile multipartFile = workerDocumentDetailsBean.getMultipartFile();
                        String extn = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."), multipartFile.getOriginalFilename().length());
                        result1.setFileName(result.getId() + extn);
                        byte[] bytes = multipartFile.getBytes();
                        Path path = Paths.get(UPLOADED_FOLDER + "workerrecruitemt/document/" + result.getId() + extn);
                        Files.write(path, bytes);
                        workerDocumentDetailsRepository.save(result1);
                    }*/
                } catch (Exception e) {
                }
            }
        }
        /*workerJoiningBean1.setWorkerFamilyDetails(workerFamilyDetailsRepository.findByWorkerJoiningId(result.getId()));
        workerJoiningBean1.setWorkerJobsDetails(workerJobsDetailsRepository.findByWorkerJoiningId(result.getId()));
        workerJoiningBean1.setWorkerLanguageDetails(workerLanguageDetailsRepository.findByWorkerJoiningId(result.getId()));
        workerJoiningBean1.setWorkerNominationDetails(workerNominationDetailsRepository.findByWorkerJoiningId(result.getId()));
        workerJoiningBean1.setWorkerAddressDetails(workerAddressDetailsRepository.findByWorkerJoiningId(result.getId()));
        workerJoiningBean1.setWorkerEducationDetails(workerEducationDetailsRepository.findByWorkerJoiningId(result.getId()));
        workerJoiningBean1.setWorkerReferenceDetails(workerReferenceDetailsRepository.findByWorkerJoiningId(result.getId()));
        workerJoiningBean1.setWorkerDocumentDetails(workerDocumentDetailsRepository.findByWorkerJoiningId(result.getId()));*/
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /worker-joinings : Create a new workerJoining.
     *
     * @param !workerJoining the workerJoining to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerJoining, or with status 400 (Bad Request) if the workerJoining has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-joinings-custom")
    @Timed
    public ResponseEntity<List<WorkerRecruitment>> getAllWorkerJoinings(@Valid @RequestBody TrailMockSearchOperation search) {
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        log.debug("REST request to get a page of WorkerJoinings");
        String name = "%";
        String aadharNo = "%";
        String department = "%";
        String designation = "%";

        if (search.getName() != null && search.getName().length() > 0) {
            name = "%" + search.getName().toUpperCase() + "%";
        }
        if (search.getAadharNo() != null && search.getAadharNo().length() > 0) {
            aadharNo = "%" + search.getAadharNo() + "%";
        }
        if (search.getDepartment() != null && search.getDepartment().length() > 0) {
            department = "%" + search.getDepartment().toUpperCase() + "%";
        }
        if (search.getDesignation() != null && search.getDesignation().length() > 0) {
            designation = "%" + search.getDesignation().toUpperCase() + "%";
        }
        Page<WorkerRecruitment> page = workerJoiningRepository.findAllByFilterJoining(name, aadharNo, department, designation, employeeView.getSubSname().trim(), search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-joinings-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-joinings/:id : get the "id" workerJoining.
     *
     * @param id the id of the workerJoining to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerJoining, or with status 404 (Not Found)
     */
    @GetMapping("/worker-joinings/{id}")
    public ResponseEntity<WorkerJoiningBean> getWorkerJoining(@PathVariable Long id) {
        log.debug("REST request to get WorkerJoining : {}", id);
        WorkerJoiningBean workerJoiningBean = new WorkerJoiningBean();
        WorkerJoining workerJoining = workerJoiningRepository.findByWorkerJoining(id);
        if (workerJoining != null) {
            BeanUtils.copyProperties(workerJoining, workerJoiningBean);
            if (workerJoiningBean.getStatus() != null && (workerJoining.getStatus().equalsIgnoreCase("A") || workerJoiningBean.getStatus().equalsIgnoreCase("R"))) {
                workerJoiningBean.setAllowEntry(false);
            } else if (workerJoiningBean.getStatus() != null && workerJoining.getStatus().equalsIgnoreCase("F")) {
                WorkerJoinFlowDetails workerJoinFlowDetails = workerJoinFlowDetailsRepository.getEntryWorkerJoinFlowDetailsByJoiningId(workerJoiningBean.getId());
                if (workerJoinFlowDetails.getEmpCode() != null && workerJoinFlowDetails.getEmpCode().equalsIgnoreCase(SecurityUtils.getCurrentUserLogin().orElse(null))) {
                    workerJoiningBean.setAllowEntry(true);
                } else {
                    workerJoiningBean.setAllowEntry(false);
                }
            } else if (workerJoiningBean.getCreatedBy() != null && workerJoiningBean.getCreatedBy().equalsIgnoreCase(SecurityUtils.getCurrentUserLogin().orElse(null))) {
                workerJoiningBean.setAllowEntry(true);
            } else {
                workerJoiningBean.setAllowEntry(false);
            }
            workerJoiningBean.setWorkerFamilyDetails(workerFamilyDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));
            workerJoiningBean.setWorkerJobsDetails(workerJobsDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));
            workerJoiningBean.setWorkerLanguageDetails(workerLanguageDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));
            workerJoiningBean.setWorkerNominationDetails(workerNominationDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));
            workerJoiningBean.setWorkerAddressDetails(workerAddressDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));
            workerJoiningBean.setWorkerEducationDetails(workerEducationDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));
            workerJoiningBean.setWorkerReferenceDetails(workerReferenceDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));
            workerJoiningBean.setWorkerDocumentDetails(workerDocumentDetailsRepository.findByWorkerJoiningId(workerJoining.getId()));



            TrailMockOperation trailMockOperation = trailMockOperationRepository.findByWorkerRecruitment(workerJoining.getWorkerRecruitment().getId());
            if (trailMockOperation != null) {
                if (workerJoiningBean.getGrade() != null) {
                } else {
                    workerJoiningBean.setGrade(trailMockOperation.getGrade());
                }

                WorkerWorkFlowBean workerWorkFlowBean = new WorkerWorkFlowBean();
                workerWorkFlowBean.setMockId(id);
                workerWorkFlowBean.setRecStatus(trailMockOperation.getWorkerRecruitment().getStatus());
                WorkerWorkFlow workerWorkFlow = workerWorkFlowRepository.getEntryWorkerWorkFlowByMockId(id);
                List<WorkerWorkFlow> workerWorkFlows = workerWorkFlowRepository.getWorkerWorkFlowByMockIdDesc(id);
                if (workerWorkFlows != null && workerWorkFlows.size() > 0) {
                    workerWorkFlowBean.setWorkerWorkFlows(workerWorkFlows);
                }
                workerJoiningBean.setWorkerWorkFlowBean(workerWorkFlowBean);
            }
        } else {
            WorkerRecruitment recruitment = workerRecruitmentRepository.findById(id).orElse(null);
            workerJoiningBean.setAllowEntry(true);
            workerJoiningBean.setWorkerRecruitment(recruitment);
            workerJoiningBean.setName(recruitment.getName());
            workerJoiningBean.setAadharNo(recruitment.getAadharNo());
            workerJoiningBean.setPanNo(recruitment.getPanNo());
            workerJoiningBean.setBankBranch(recruitment.getBankBranch());
            workerJoiningBean.setBankMaster(recruitment.getBankMaster());
            workerJoiningBean.setBankAccNo(recruitment.getBankAccNo());
            workerJoiningBean.setDob(recruitment.getDob());
            workerJoiningBean.setGuardianName(recruitment.getFatherName());
            workerJoiningBean.setPermanentAddress(recruitment.getAddress());
            workerJoiningBean.setLocalAddress(recruitment.getCorespondAddress());
            workerJoiningBean.setDepartmentMaster(recruitment.getDepartmentMaster());
            workerJoiningBean.setDesignationMaster(recruitment.getDesignationMaster());
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(workerJoiningBean));
    }

    /**
     * DELETE  /worker-joinings/:id : delete the "id" workerJoining.
     *
     * @param id the id of the workerJoining to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-joinings/{id}")
    public ResponseEntity<Void> deleteWorkerJoining(@PathVariable Long id) {
        log.debug("REST request to delete WorkerJoining : {}", id);
        workerJoiningRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
