package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerJoinFlowBean;
import io.vamani.application.domain.WorkerJoinFlowDetails;
import io.vamani.application.domain.WorkerJoining;
import io.vamani.application.model.WorkerJoinFlowDetail;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.WorkerJoinFlowDetailsRepository;
import io.vamani.application.repository.WorkerJoiningRepository;
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
 * REST controller for managing WorkerJoinFlowDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerJoinFlowDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerJoinFlowDetailsResource.class);

    private static final String ENTITY_NAME = "workerJoinFlowDetails";

    private final WorkerJoinFlowDetailsRepository workerJoinFlowDetailsRepository;

    @Autowired
    private WorkerJoiningRepository workerJoiningRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    public WorkerJoinFlowDetailsResource(WorkerJoinFlowDetailsRepository workerJoinFlowDetailsRepository) {
        this.workerJoinFlowDetailsRepository = workerJoinFlowDetailsRepository;
    }

    /**
     * POST  /worker-join-flow-details : Create a new workerJoinFlowDetails.
     *
     * @param workerJoinFlowDetails the workerJoinFlowDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerJoinFlowDetails, or with status 400 (Bad Request) if the workerJoinFlowDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-join-flow-details")
    @Timed
    public ResponseEntity<WorkerJoinFlowDetails> createWorkerJoinFlowDetails(@Valid @RequestBody WorkerJoinFlowDetails workerJoinFlowDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerJoinFlowDetails : {}", workerJoinFlowDetails);
        if (workerJoinFlowDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerJoinFlowDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerJoinFlowDetails result = workerJoinFlowDetailsRepository.save(workerJoinFlowDetails);
        return ResponseEntity.created(new URI("/api/worker-join-flow-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-join-flow-details : Updates an existing workerJoinFlowDetails.
     *
     * @param workerJoinFlowDetails the workerJoinFlowDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerJoinFlowDetails,
     * or with status 400 (Bad Request) if the workerJoinFlowDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerJoinFlowDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-join-flow-details")
    @Timed
    public ResponseEntity<WorkerJoinFlowDetails> updateWorkerJoinFlowDetails(@Valid @RequestBody WorkerJoinFlowDetail workerJoinFlowDetail) throws URISyntaxException {
        log.debug("REST request to update WorkerJoinFlowDetails : {}", workerJoinFlowDetail);
        String emailTemplateCode = null;
        WorkerJoinFlowDetails workerJoinFlowDetails = new WorkerJoinFlowDetails();
        BeanUtils.copyProperties(workerJoinFlowDetail, workerJoinFlowDetails);
        if (workerJoinFlowDetails.getId() == null) {
            workerJoinFlowDetails.setId(null);
            workerJoinFlowDetails.setAuthDate(Instant.now());
            WorkerJoinFlowDetails result = workerJoinFlowDetailsRepository.save(workerJoinFlowDetails);

            if (workerJoinFlowDetails.getAuthType() != null && (workerJoinFlowDetails.getAuthType().equals("A") || workerJoinFlowDetails.getAuthType().equals("R"))) {
                WorkerJoining workerJoining = workerJoiningRepository.findById(result.getJoiningId()).orElse(null);
                workerJoining.setStatus(workerJoinFlowDetails.getAuthType());
                workerJoiningRepository.save(workerJoining);
            } else {
                WorkerJoinFlowDetails workerJoinFlowDetailsNew = new WorkerJoinFlowDetails();
                workerJoinFlowDetailsNew.setId(null);
                workerJoinFlowDetailsNew.setJoiningId(result.getJoiningId());
                workerJoinFlowDetailsNew.setEmpCode(result.getForwardCode());
                workerJoinFlowDetailsNew.setEmpName(result.getForwardName());
                workerJoinFlowDetailsNew.setRemarks("");
                workerJoinFlowDetailsNew.setForwardCode(null);
                workerJoinFlowDetailsNew.setAuthType("");
                workerJoinFlowDetailsNew.setAuthDate(Instant.now());
                WorkerJoinFlowDetails result1 = workerJoinFlowDetailsRepository.save(workerJoinFlowDetailsNew);
            }
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);

        } else {
            workerJoinFlowDetails.setAuthDate(Instant.now());
            WorkerJoinFlowDetails result = workerJoinFlowDetailsRepository.save(workerJoinFlowDetails);
            if (workerJoinFlowDetails.getAuthType() != null && (workerJoinFlowDetails.getAuthType().equals("A") || workerJoinFlowDetails.getAuthType().equals("R"))) {
                WorkerJoining workerJoining = workerJoiningRepository.findById(result.getJoiningId()).orElse(null);
                workerJoining.setStatus(workerJoinFlowDetails.getAuthType());
                workerJoiningRepository.save(workerJoining);
            } else {
                WorkerJoinFlowDetails workerJoinFlowDetailsNew = new WorkerJoinFlowDetails();
                workerJoinFlowDetailsNew.setId(null);
                workerJoinFlowDetailsNew.setJoiningId(result.getJoiningId());
                workerJoinFlowDetailsNew.setEmpCode(result.getForwardCode());
                workerJoinFlowDetailsNew.setEmpName(result.getForwardName());
                workerJoinFlowDetailsNew.setRemarks("");
                workerJoinFlowDetailsNew.setForwardCode(null);
                workerJoinFlowDetailsNew.setAuthType("");
                workerJoinFlowDetailsNew.setAuthDate(Instant.now());
                WorkerJoinFlowDetails result1 = workerJoinFlowDetailsRepository.save(workerJoinFlowDetailsNew);
            }
            if (result != null) {
            }
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }

    /**
     * GET  /worker-join-flow-details : get all the workerJoinFlowDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerJoinFlowDetails in body
     */
    @GetMapping("/worker-join-flow-details")
    @Timed
    public ResponseEntity<List<WorkerJoinFlowDetails>> getAllWorkerJoinFlowDetails(Pageable pageable) {
        log.debug("REST request to get a page of WorkerJoinFlowDetails");
        Page<WorkerJoinFlowDetails> page = workerJoinFlowDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-join-flow-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-join-flow-details/:id : get the "id" workerJoinFlowDetails.
     *
     * @param id the id of the workerJoinFlowDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerJoinFlowDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-join-flow-details/{id}")
    @Timed
    public ResponseEntity<WorkerJoinFlowBean> getWorkerJoinFlowDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerJoinFlowDetails : {}", id);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        WorkerJoining workerJoining = workerJoiningRepository.findById(id).orElse(null);

        WorkerJoinFlowBean workerJoinFlowBean = new WorkerJoinFlowBean();
        workerJoinFlowBean.setJoiningId(id);
        workerJoinFlowBean.setRecStatus(workerJoining.getStatus());
        WorkerJoinFlowDetails workerJoinFlowDetail = workerJoinFlowDetailsRepository.getEntryWorkerJoinFlowDetailsByJoiningId(id);
        List<WorkerJoinFlowDetails> workerJoinFlowDetails = workerJoinFlowDetailsRepository.getWorkerJoinFlowDetailsByJoiningId(id);
        if (workerJoinFlowDetails != null && workerJoinFlowDetails.size() > 0) {
            workerJoinFlowBean.setWorkerJoinFlowDetails(workerJoinFlowDetails);
            if (workerJoinFlowDetail != null && workerJoinFlowDetail.getId() != null && workerJoinFlowDetail.getId() > 0) {
                workerJoinFlowBean.setId(workerJoinFlowDetail.getId());
                if (currentUser != null && currentUser.equalsIgnoreCase(workerJoinFlowDetail.getEmpCode())) {
                    workerJoinFlowBean.setEmpCode(workerJoinFlowDetail.getEmpCode());
                    workerJoinFlowBean.setEmpName(workerJoinFlowDetail.getEmpName());
                    workerJoinFlowBean.setAllowEntry(true);
                } else {
                    workerJoinFlowBean.setEmpCode(workerJoinFlowDetail.getEmpCode());
                    workerJoinFlowBean.setEmpName(workerJoinFlowDetail.getEmpName());
                    workerJoinFlowBean.setAllowEntry(false);
                }
            } else {
                EmployeeView employeeView = employeeViewRepository.findById(currentUser).orElse(null);
                workerJoinFlowBean.setId(new Long(0));
                workerJoinFlowBean.setEmpCode(currentUser);
                workerJoinFlowBean.setEmpName(employeeView.getName());
                workerJoinFlowBean.setAllowEntry(false);
            }
        } else {
            EmployeeView employeeView = employeeViewRepository.findById(workerJoining.getCreatedBy()).orElse(null);
            if (currentUser != null && currentUser.equalsIgnoreCase(workerJoining.getCreatedBy())) {
                workerJoinFlowBean.setEmpCode(currentUser);
                workerJoinFlowBean.setEmpName(employeeView.getName());
                workerJoinFlowBean.setAllowEntry(true);
            } else {
                workerJoinFlowBean.setEmpCode(employeeView.getLogin());
                workerJoinFlowBean.setEmpName(employeeView.getName());
                workerJoinFlowBean.setAllowEntry(false);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(workerJoinFlowBean));
    }

    /**
     * DELETE  /worker-join-flow-details/:id : delete the "id" workerJoinFlowDetails.
     *
     * @param id the id of the workerJoinFlowDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-join-flow-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerJoinFlowDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerJoinFlowDetails : {}", id);

        workerJoinFlowDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
