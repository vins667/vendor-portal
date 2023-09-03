package io.vamani.application.web.rest;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.*;
import io.vamani.application.model.IndustrialTrailReportBean;
import io.vamani.application.model.TrailMockOperationBean;
import io.vamani.application.model.TrailMockSearchOperation;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.TrailMockOperationRepository;
import io.vamani.application.repository.WorkerRecruitmentRepository;
import io.vamani.application.repository.WorkerWorkFlowRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing TrailMockOperation.
 */
@RestController
@RequestMapping("/api")
public class TrailMockOperationResource {

    private final Logger log = LoggerFactory.getLogger(TrailMockOperationResource.class);

    private static final String ENTITY_NAME = "trailMockOperation";

    private final TrailMockOperationRepository trailMockOperationRepository;

    @Autowired
    private WorkerRecruitmentRepository workerRecruitmentRepository;

    @Autowired
    private WorkerWorkFlowRepository workerWorkFlowRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public TrailMockOperationResource(TrailMockOperationRepository trailMockOperationRepository) {
        this.trailMockOperationRepository = trailMockOperationRepository;
    }

    /**
     * POST  /trail-mock-operations : Create a new trailMockOperation.
     *
     * @param trailMockOperation the trailMockOperation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trailMockOperation, or with status 400 (Bad Request) if the trailMockOperation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trail-mock-operations")
    public ResponseEntity<TrailMockOperation> createTrailMockOperation(@Valid @RequestBody TrailMockOperation trailMockOperation) throws URISyntaxException {
        log.debug("REST request to save TrailMockOperation : {}", trailMockOperation);
        if (trailMockOperation.getId() != null) {
            throw new BadRequestAlertException("A new trailMockOperation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        trailMockOperation.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        trailMockOperation.setCreatedDate(Instant.now());
        TrailMockOperation result = trailMockOperationRepository.save(trailMockOperation);
        if (result != null) {
            WorkerRecruitment workerRecruitment = workerRecruitmentRepository.findById(result.getWorkerRecruitment().getId()).orElse(null);
            workerRecruitment.setStatus("A");
            workerRecruitmentRepository.save(workerRecruitment);
        }
        return ResponseEntity.created(new URI("/api/trail-mock-operations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /employee-views-custom : get all the employeeViews.
     *
     * @param search the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @PostMapping("/trail-mock-operations-custom")
    @Timed
    public ResponseEntity<List<WorkerRecruitment>> getAllTrailMockOperationCustom(@Valid @RequestBody TrailMockSearchOperation search) {
        log.debug("REST request to get a page of TrailMockOperation");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
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
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("name").ascending()));
        Page<WorkerRecruitment> page = trailMockOperationRepository.findAllByFilter(name, aadharNo, department, designation, employeeView.getSubSname().trim(), search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trail-mock-operations-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * PUT  /trail-mock-operations : Updates an existing trailMockOperation.
     *
     * @param trailMockOperation the trailMockOperation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trailMockOperation,
     * or with status 400 (Bad Request) if the trailMockOperation is not valid,
     * or with status 500 (Internal Server Error) if the trailMockOperation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trail-mock-operations")
    public ResponseEntity<TrailMockOperation> updateTrailMockOperation(@Valid @RequestBody TrailMockOperation trailMockOperation) throws URISyntaxException {
        log.debug("REST request to update TrailMockOperation : {}", trailMockOperation);
        if (trailMockOperation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        trailMockOperation.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        trailMockOperation.setLastUpdatedDate(Instant.now());
        TrailMockOperation result = trailMockOperationRepository.save(trailMockOperation);
        if (result != null) {
            WorkerRecruitment workerRecruitment = workerRecruitmentRepository.findById(result.getWorkerRecruitment().getId()).orElse(null);
            workerRecruitment.setStatus("A");
            workerRecruitmentRepository.save(workerRecruitment);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trailMockOperation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trail-mock-operations/:id : get the "id" trailMockOperation.
     *
     * @param id the id of the trailMockOperation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trailMockOperation, or with status 404 (Not Found)
     */
    @GetMapping("/trail-mock-operations/{id}")
    public ResponseEntity<TrailMockOperationBean> getTrailMockOperation(@PathVariable Long id) {
        log.debug("REST request to get TrailMockOperation : {}", id);
        TrailMockOperation trialMockOperation = trailMockOperationRepository.findByWorkerRecruitment(id);
        TrailMockOperationBean trailMockOperationBean = new TrailMockOperationBean();
        if (trialMockOperation != null) {
            BeanUtils.copyProperties(trialMockOperation, trailMockOperationBean);
            if (trialMockOperation.getWorkerRecruitment() != null && trialMockOperation.getWorkerRecruitment().getStatus().equalsIgnoreCase("H")) {
                trailMockOperationBean.setAllowEntry(false);
            } else {
                WorkerWorkFlow workerWorkFlow = workerWorkFlowRepository.getEntryWorkerWorkFlowByMockId(trialMockOperation.getId());
                if (workerWorkFlow != null) {
                    if (workerWorkFlow.getEmpCode().equalsIgnoreCase(SecurityUtils.getCurrentUserLogin().orElse(null)) &&
                        trailMockOperationBean.getCreatedBy() != null && trailMockOperationBean.getCreatedBy().equalsIgnoreCase(SecurityUtils.getCurrentUserLogin().orElse(null))) {
                        trailMockOperationBean.setAllowEntry(true);
                    } else {
                        trailMockOperationBean.setAllowEntry(false);
                    }
                } else if (trailMockOperationBean.getCreatedBy() != null && trailMockOperationBean.getCreatedBy().equalsIgnoreCase(SecurityUtils.getCurrentUserLogin().orElse(null))) {
                    trailMockOperationBean.setAllowEntry(true);
                } else {
                    trailMockOperationBean.setAllowEntry(false);
                }
            }
        } else {
            trialMockOperation = new TrailMockOperation();
            WorkerRecruitment recruitment = workerRecruitmentRepository.findById(id).orElse(null);
            trailMockOperationBean.setWorkerRecruitment(recruitment);
            trailMockOperationBean.setAllowEntry(true);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(trailMockOperationBean));
    }

    /**
     * DELETE  /trail-mock-operations/:id : delete the "id" trailMockOperation.
     *
     * @param id the id of the trailMockOperation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trail-mock-operations/{id}")
    public ResponseEntity<Void> deleteTrailMockOperation(@PathVariable Long id) {
        log.debug("REST request to delete TrailMockOperation : {}", id);
        trailMockOperationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @GetMapping("/trail-mock-operations-reports/{id}")
    @Timed
    public @ResponseBody void getTrailMockOperationReports(@Valid @PathVariable Long id, HttpServletResponse response) throws JRException, IOException {
        log.debug("REST request to get a page of TrailMockOperations");
        TrailMockOperation trailMockOperation = trailMockOperationRepository.findByWorkerRecruitment(id);
        List<IndustrialTrailReportBean> trailMockOperations = new ArrayList<IndustrialTrailReportBean>();
        if(trailMockOperation != null) {
            IndustrialTrailReportBean industrialTrailReportBean = new IndustrialTrailReportBean();
            industrialTrailReportBean.setId(trailMockOperation.getId());
            industrialTrailReportBean.setName(trailMockOperation.getWorkerRecruitment().getName());
            industrialTrailReportBean.setFatherName(trailMockOperation.getWorkerRecruitment().getFatherName());
            industrialTrailReportBean.setSplMachine((trailMockOperation.getSplMachineKnowledge() != null && trailMockOperation.getSplMachineKnowledge().equalsIgnoreCase("Y")) ? "Yes" : "No");

            StringBuffer machineName = new StringBuffer();
            for (MachineMaster machineMaster : trailMockOperation.getMachineMasters()) {
                machineName.append(machineMaster.getMachineName() + ",");
            }
            if (machineName.length() > 0) {
                machineName.substring(machineName.lastIndexOf(",") + 1, machineName.length());
            }
            industrialTrailReportBean.setMachineName(machineName.toString());

            industrialTrailReportBean.setSnls(trailMockOperation.isSnls());
            industrialTrailReportBean.setDnls(trailMockOperation.isDnls());
            industrialTrailReportBean.setOl(trailMockOperation.isOl());

            StringBuffer operations = new StringBuffer();
            for (OperationMaster operationMaster : trailMockOperation.getOperationMasters()) {
                operations.append(operationMaster.getDescription() + ",");
            }
            if (operations.length() > 0) {
                operations.substring(operations.lastIndexOf(",") + 1, operations.length());
            }
            industrialTrailReportBean.setOperations(operations.toString());

            industrialTrailReportBean.setAchieveRating(trailMockOperation.getAchiveRating().toString());
            industrialTrailReportBean.setResult((trailMockOperation.getResult() != null && trailMockOperation.getResult().equalsIgnoreCase("P") ? "Pass" : "Fail"));
            industrialTrailReportBean.setTimeTaken(trailMockOperation.getTimeTaken());
            industrialTrailReportBean.setGrade(trailMockOperation.getGrade());
            industrialTrailReportBean.setRemarks(trailMockOperation.getRemarks());

            trailMockOperations.add(industrialTrailReportBean);
        }
        String path = applicationProperties.getTemplatePath()+"jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path+"/TrailMockOperation.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(trailMockOperations);


        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR",path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, jrDataSource);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","attachment; filename=LeaveReport.pdf");

        final OutputStream outputStream = response.getOutputStream();

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
        exporter.exportReport();
    }
    
}
