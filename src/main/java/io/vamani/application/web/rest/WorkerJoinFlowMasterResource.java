package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerForwardTypeMaster;
import io.vamani.application.domain.WorkerJoinFlowMaster;
import io.vamani.application.repository.WorkerJoinFlowMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkerJoinFlowMaster.
 */
@RestController
@RequestMapping("/api")
public class WorkerJoinFlowMasterResource {

    private final Logger log = LoggerFactory.getLogger(WorkerJoinFlowMasterResource.class);

    private static final String ENTITY_NAME = "workerJoinFlowMaster";

    private final WorkerJoinFlowMasterRepository workerJoinFlowMasterRepository;

    public WorkerJoinFlowMasterResource(WorkerJoinFlowMasterRepository workerJoinFlowMasterRepository) {
        this.workerJoinFlowMasterRepository = workerJoinFlowMasterRepository;
    }

    /**
     * POST  /worker-join-flow-masters : Create a new workerJoinFlowMaster.
     *
     * @param workerJoinFlowMaster the workerJoinFlowMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerJoinFlowMaster, or with status 400 (Bad Request) if the workerJoinFlowMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-join-flow-masters")
    @Timed
    public ResponseEntity<WorkerJoinFlowMaster> createWorkerJoinFlowMaster(@Valid @RequestBody WorkerJoinFlowMaster workerJoinFlowMaster) throws URISyntaxException {
        log.debug("REST request to save WorkerJoinFlowMaster : {}", workerJoinFlowMaster);
        if (workerJoinFlowMaster.getId() != null) {
            throw new BadRequestAlertException("A new workerJoinFlowMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerJoinFlowMaster result = workerJoinFlowMasterRepository.save(workerJoinFlowMaster);
        return ResponseEntity.created(new URI("/api/worker-join-flow-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-join-flow-masters : Updates an existing workerJoinFlowMaster.
     *
     * @param workerJoinFlowMaster the workerJoinFlowMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerJoinFlowMaster,
     * or with status 400 (Bad Request) if the workerJoinFlowMaster is not valid,
     * or with status 500 (Internal Server Error) if the workerJoinFlowMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-join-flow-masters")
    @Timed
    public ResponseEntity<WorkerJoinFlowMaster> updateWorkerJoinFlowMaster(@Valid @RequestBody WorkerJoinFlowMaster workerJoinFlowMaster) throws URISyntaxException {
        log.debug("REST request to update WorkerJoinFlowMaster : {}", workerJoinFlowMaster);
        if (workerJoinFlowMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerJoinFlowMaster result = workerJoinFlowMasterRepository.save(workerJoinFlowMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerJoinFlowMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-join-flow-masters : get all the workerJoinFlowMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerJoinFlowMasters in body
     */
    @GetMapping("/worker-join-flow-masters")
    @Timed
    public ResponseEntity<List<WorkerJoinFlowMaster>> getAllWorkerJoinFlowMasters(Pageable pageable) {
        log.debug("REST request to get a page of WorkerJoinFlowMasters");
        Page<WorkerJoinFlowMaster> page = workerJoinFlowMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-join-flow-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-join-flow-masters/:id : get the "id" workerJoinFlowMaster.
     *
     * @param id the id of the workerJoinFlowMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerJoinFlowMaster, or with status 404 (Not Found)
     */
    @GetMapping("/worker-join-flow-masters/{id}")
    @Timed
    public ResponseEntity<WorkerJoinFlowMaster> getWorkerJoinFlowMaster(@PathVariable Long id) {
        log.debug("REST request to get WorkerJoinFlowMaster : {}", id);
        Optional<WorkerJoinFlowMaster> workerJoinFlowMaster = workerJoinFlowMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerJoinFlowMaster);
    }

    /**
     * DELETE  /worker-join-flow-masters/:id : delete the "id" workerJoinFlowMaster.
     *
     * @param id the id of the workerJoinFlowMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-join-flow-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerJoinFlowMaster(@PathVariable Long id) {
        log.debug("REST request to delete WorkerJoinFlowMaster : {}", id);

        workerJoinFlowMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /worker-work-flow-forward-fetch : get all the WorkerForwardTypeMaster.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rnsEmpLinkMasters in body
     */
    @GetMapping("/worker-join-flow-masters-forward-fetch")
    @Timed
    public ResponseEntity<List<WorkerForwardTypeMaster>> getDistinctByForwardEmpTypeByEmpCode() {
        List<WorkerForwardTypeMaster> workerForwardList = new ArrayList<>();
        log.debug("REST request to get a page of WorkerForwardTypeMaster");
        List<Object[]> workerForwards = workerJoinFlowMasterRepository.getDistinctByForwardEmpTypeByEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
        if (workerForwards != null && workerForwards.size() > 0) {
            for (Object[] forward : workerForwards) {
                WorkerForwardTypeMaster bean = new WorkerForwardTypeMaster();
                bean.setCode(forward[0].toString());
                bean.setDescription(forward[1].toString());
                workerForwardList.add(bean);
            }
        }
        return ResponseEntity.ok().body(workerForwardList);
    }

    /**
     * GET  /rns-emp-link-masters-fetch : get all the rnsEmpLinkMasters.
     *
     * @param forwardEmpType the forwardEmpType of the RnsEmpLinkMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and the list of rnsEmpLinkMasters in body
     */
    @GetMapping("/worker-join-flow-masters-users-fetch/{forwardEmpType}")
    @Timed
    public ResponseEntity<List<WorkerJoinFlowMaster>> getAllWorkerWorkFlowMasterByEmpCode(@PathVariable String forwardEmpType) {
        log.debug("REST request to get a page of RnsEmpLinkMasters");
        return ResponseEntity.ok().body(workerJoinFlowMasterRepository.getWorkerJoinFlowMasterByEmpCodeWithFlag(SecurityUtils.getCurrentUserLogin().orElse(null), forwardEmpType));
    }
}
