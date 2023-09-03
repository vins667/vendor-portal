package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.TrailMockOperation;
import io.vamani.application.domain.WorkerRecruitment;
import io.vamani.application.domain.WorkerWorkFlow;
import io.vamani.application.model.WorkerWorkFlowBean;
import io.vamani.application.model.WorkerWorkFlowDetail;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkerWorkFlow.
 */
@RestController
@RequestMapping("/api")
public class WorkerWorkFlowResource {

    private final Logger log = LoggerFactory.getLogger(WorkerWorkFlowResource.class);

    private static final String ENTITY_NAME = "workerWorkFlow";

    private final WorkerWorkFlowRepository workerWorkFlowRepository;

    @Autowired
    private TrailMockOperationRepository trailMockOperationRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private WorkerRecruitmentRepository workerRecruitmentRepository;

    public WorkerWorkFlowResource(WorkerWorkFlowRepository workerWorkFlowRepository) {
        this.workerWorkFlowRepository = workerWorkFlowRepository;
    }

    /**
     * POST  /worker-work-flows : Create a new workerWorkFlow.
     *
     * @param workerWorkFlow the workerWorkFlow to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerWorkFlow, or with status 400 (Bad Request) if the workerWorkFlow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-work-flows")
    @Timed
    public ResponseEntity<WorkerWorkFlow> createWorkerWorkFlow(@Valid @RequestBody WorkerWorkFlow workerWorkFlow) throws URISyntaxException {
        log.debug("REST request to save WorkerWorkFlow : {}", workerWorkFlow);
        if (workerWorkFlow.getId() != null) {
            throw new BadRequestAlertException("A new workerWorkFlow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerWorkFlow result = workerWorkFlowRepository.save(workerWorkFlow);
        return ResponseEntity.created(new URI("/api/worker-work-flows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-work-flows : Updates an existing workerWorkFlow.
     *
     * @param workerWorkFlow the workerWorkFlow to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerWorkFlow,
     * or with status 400 (Bad Request) if the workerWorkFlow is not valid,
     * or with status 500 (Internal Server Error) if the workerWorkFlow couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-work-flows")
    @Timed
    public ResponseEntity<WorkerWorkFlow> updateWorkerWorkFlow(@Valid @RequestBody WorkerWorkFlowDetail workerWorkFlowDetail) throws URISyntaxException {
        log.debug("REST request to update WorkerWorkFlow : {}", workerWorkFlowDetail);
        String emailTemplateCode = null;
        WorkerWorkFlow workerWorkFlow = new WorkerWorkFlow();
        BeanUtils.copyProperties(workerWorkFlowDetail, workerWorkFlow);
        if (workerWorkFlow.getId() == null) {
            workerWorkFlow.setId(null);
            if (workerWorkFlowDetail.getUserType().equals("H")) {
                workerWorkFlow.setAuthType("C");
            }
            workerWorkFlow.setAuthDate(Instant.now());
            WorkerWorkFlow result = workerWorkFlowRepository.save(workerWorkFlow);

            if (workerWorkFlowDetail.getUserType() != null && workerWorkFlowDetail.getUserType().equals("H")) {
                TrailMockOperation trailMockOperation = trailMockOperationRepository.findById(result.getMockId()).orElse(null);
                WorkerRecruitment workerRecruitment = workerRecruitmentRepository.findById(trailMockOperation.getWorkerRecruitment().getId()).orElse(null);
                workerRecruitment.setStatus("H");
                workerRecruitmentRepository.save(workerRecruitment);
            } else if (result != null && result.getAuthType() != null && (result.getAuthType().equalsIgnoreCase("C") || result.getAuthType().equalsIgnoreCase("R"))) {
            } else {
                WorkerWorkFlow workerWorkFlowNew = new WorkerWorkFlow();
                workerWorkFlowNew.setId(null);
                workerWorkFlowNew.setMockId(result.getMockId());
                workerWorkFlowNew.setEmpCode(result.getForwardCode());
                workerWorkFlowNew.setEmpName(result.getForwardName());
                workerWorkFlowNew.setRemarks("");
                workerWorkFlowNew.setForwardCode(null);
                workerWorkFlowNew.setAuthType("");
                workerWorkFlowNew.setAuthDate(Instant.now());
                WorkerWorkFlow result1 = workerWorkFlowRepository.save(workerWorkFlowNew);
            }
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);

        } else {
            workerWorkFlow.setAuthDate(Instant.now());
            if (workerWorkFlowDetail.getUserType().equals("H")) {
                workerWorkFlow.setAuthType("C");
            }
            WorkerWorkFlow result = workerWorkFlowRepository.save(workerWorkFlow);
            if (workerWorkFlowDetail.getUserType() != null && workerWorkFlowDetail.getUserType().equals("H")) {
                TrailMockOperation trailMockOperation = trailMockOperationRepository.findById(result.getMockId()).orElse(null);
                WorkerRecruitment workerRecruitment = workerRecruitmentRepository.findById(trailMockOperation.getWorkerRecruitment().getId()).orElse(null);
                workerRecruitment.setStatus("H");
                workerRecruitmentRepository.save(workerRecruitment);
            } else if (result != null && result.getAuthType() != null && (result.getAuthType().equalsIgnoreCase("C") || result.getAuthType().equalsIgnoreCase("R"))) {
            } else {
                WorkerWorkFlow workerWorkFlowNew = new WorkerWorkFlow();
                workerWorkFlowNew.setId(null);
                workerWorkFlowNew.setMockId(result.getMockId());
                workerWorkFlowNew.setEmpCode(result.getForwardCode());
                workerWorkFlowNew.setEmpName(result.getForwardName());
                workerWorkFlowNew.setRemarks("");
                workerWorkFlowNew.setForwardCode(null);
                workerWorkFlowNew.setAuthType("");
                workerWorkFlowNew.setAuthDate(Instant.now());
                WorkerWorkFlow result1 = workerWorkFlowRepository.save(workerWorkFlowNew);
            }
            if (result != null) {
            }
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }

    /**
     * GET  /worker-work-flows : get all the workerWorkFlows.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerWorkFlows in body
     */
    @GetMapping("/worker-work-flows")
    @Timed
    public ResponseEntity<List<WorkerWorkFlow>> getAllWorkerWorkFlows(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of WorkerWorkFlows");
        Page<WorkerWorkFlow> page = workerWorkFlowRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-work-flows");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-work-flows/:id : get the "id" workerWorkFlow.
     *
     * @param id the id of the workerWorkFlow to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerWorkFlow, or with status 404 (Not Found)
     */
    @GetMapping("/worker-work-flows/{id}")
    @Timed
    public ResponseEntity<WorkerWorkFlowBean> getWorkerWorkFlow(@PathVariable Long id) {
        log.debug("REST request to get WorkerWorkFlow : {}", id);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        TrailMockOperation trailMockOperation = trailMockOperationRepository.findById(id).orElse(null);

        WorkerWorkFlowBean workerWorkFlowBean = new WorkerWorkFlowBean();
        workerWorkFlowBean.setMockId(id);
        workerWorkFlowBean.setRecStatus(trailMockOperation.getWorkerRecruitment().getStatus());
        WorkerWorkFlow workerWorkFlow = workerWorkFlowRepository.getEntryWorkerWorkFlowByMockId(id);
        List<WorkerWorkFlow> workerWorkFlows = workerWorkFlowRepository.getWorkerWorkFlowByMockId(id);
        if (workerWorkFlows != null && workerWorkFlows.size() > 0) {
            workerWorkFlowBean.setWorkerWorkFlows(workerWorkFlows);
            if (workerWorkFlow != null && workerWorkFlow.getId() != null && workerWorkFlow.getId() > 0) {
                workerWorkFlowBean.setId(workerWorkFlow.getId());
                if (currentUser != null && currentUser.equalsIgnoreCase(workerWorkFlow.getEmpCode())) {
                    workerWorkFlowBean.setEmpCode(workerWorkFlow.getEmpCode());
                    workerWorkFlowBean.setEmpName(workerWorkFlow.getEmpName());
                    workerWorkFlowBean.setAllowEntry(true);
                } else {
                    workerWorkFlowBean.setEmpCode(workerWorkFlow.getEmpCode());
                    workerWorkFlowBean.setEmpName(workerWorkFlow.getEmpName());
                    workerWorkFlowBean.setAllowEntry(false);
                }
            } else {
                EmployeeView employeeView = employeeViewRepository.findById(currentUser).orElse(null);
                workerWorkFlowBean.setId(new Long(0));
                workerWorkFlowBean.setEmpCode(currentUser);
                workerWorkFlowBean.setEmpName(employeeView.getName());
                workerWorkFlowBean.setAllowEntry(false);
            }
        } else {
            EmployeeView employeeView = employeeViewRepository.findById(trailMockOperation.getCreatedBy()).orElse(null);
            if (currentUser != null && currentUser.equalsIgnoreCase(trailMockOperation.getCreatedBy())) {
                workerWorkFlowBean.setEmpCode(currentUser);
                workerWorkFlowBean.setEmpName(employeeView.getName());
                workerWorkFlowBean.setAllowEntry(true);
            } else {
                workerWorkFlowBean.setEmpCode(employeeView.getLogin());
                workerWorkFlowBean.setEmpName(employeeView.getName());
                workerWorkFlowBean.setAllowEntry(false);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(workerWorkFlowBean));
    }

    /**
     * DELETE  /worker-work-flows/:id : delete the "id" workerWorkFlow.
     *
     * @param id the id of the workerWorkFlow to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-work-flows/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerWorkFlow(@PathVariable Long id) {
        log.debug("REST request to delete WorkerWorkFlow : {}", id);

        workerWorkFlowRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
