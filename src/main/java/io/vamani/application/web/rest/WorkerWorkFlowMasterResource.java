package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerForwardTypeMaster;
import io.vamani.application.domain.WorkerWorkFlowMaster;
import io.vamani.application.repository.WorkerWorkFlowMasterRepository;
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
 * REST controller for managing WorkerWorkFlowMaster.
 */
@RestController
@RequestMapping("/api")
public class WorkerWorkFlowMasterResource {

    private final Logger log = LoggerFactory.getLogger(WorkerWorkFlowMasterResource.class);

    private static final String ENTITY_NAME = "workerWorkFlowMaster";

    private final WorkerWorkFlowMasterRepository workerWorkFlowMasterRepository;

    public WorkerWorkFlowMasterResource(WorkerWorkFlowMasterRepository workerWorkFlowMasterRepository) {
        this.workerWorkFlowMasterRepository = workerWorkFlowMasterRepository;
    }

    /**
     * POST  /worker-work-flow-masters : Create a new workerWorkFlowMaster.
     *
     * @param workerWorkFlowMaster the workerWorkFlowMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerWorkFlowMaster, or with status 400 (Bad Request) if the workerWorkFlowMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-work-flow-masters")
    @Timed
    public ResponseEntity<WorkerWorkFlowMaster> createWorkerWorkFlowMaster(@Valid @RequestBody WorkerWorkFlowMaster workerWorkFlowMaster) throws URISyntaxException {
        log.debug("REST request to save WorkerWorkFlowMaster : {}", workerWorkFlowMaster);
        if (workerWorkFlowMaster.getId() != null) {
            throw new BadRequestAlertException("A new workerWorkFlowMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerWorkFlowMaster result = workerWorkFlowMasterRepository.save(workerWorkFlowMaster);
        return ResponseEntity.created(new URI("/api/worker-work-flow-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-work-flow-masters : Updates an existing workerWorkFlowMaster.
     *
     * @param workerWorkFlowMaster the workerWorkFlowMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerWorkFlowMaster,
     * or with status 400 (Bad Request) if the workerWorkFlowMaster is not valid,
     * or with status 500 (Internal Server Error) if the workerWorkFlowMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-work-flow-masters")
    @Timed
    public ResponseEntity<WorkerWorkFlowMaster> updateWorkerWorkFlowMaster(@Valid @RequestBody WorkerWorkFlowMaster workerWorkFlowMaster) throws URISyntaxException {
        log.debug("REST request to update WorkerWorkFlowMaster : {}", workerWorkFlowMaster);
        if (workerWorkFlowMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerWorkFlowMaster result = workerWorkFlowMasterRepository.save(workerWorkFlowMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerWorkFlowMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-work-flow-masters : get all the workerWorkFlowMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerWorkFlowMasters in body
     */
    @GetMapping("/worker-work-flow-masters")
    @Timed
    public ResponseEntity<List<WorkerWorkFlowMaster>> getAllWorkerWorkFlowMasters(Pageable pageable) {
        log.debug("REST request to get a page of WorkerWorkFlowMasters");
        Page<WorkerWorkFlowMaster> page = workerWorkFlowMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-work-flow-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-work-flow-masters/:id : get the "id" workerWorkFlowMaster.
     *
     * @param id the id of the workerWorkFlowMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerWorkFlowMaster, or with status 404 (Not Found)
     */
    @GetMapping("/worker-work-flow-masters/{id}")
    @Timed
    public ResponseEntity<WorkerWorkFlowMaster> getWorkerWorkFlowMaster(@PathVariable Long id) {
        log.debug("REST request to get WorkerWorkFlowMaster : {}", id);
        Optional<WorkerWorkFlowMaster> workerWorkFlowMaster = workerWorkFlowMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerWorkFlowMaster);
    }

    /**
     * DELETE  /worker-work-flow-masters/:id : delete the "id" workerWorkFlowMaster.
     *
     * @param id the id of the workerWorkFlowMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-work-flow-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerWorkFlowMaster(@PathVariable Long id) {
        log.debug("REST request to delete WorkerWorkFlowMaster : {}", id);

        workerWorkFlowMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /worker-work-flow-forward-fetch : get all the WorkerForwardTypeMaster.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rnsEmpLinkMasters in body
     */
    @GetMapping("/worker-work-flow-forward-fetch")
    @Timed
    public ResponseEntity<List<WorkerForwardTypeMaster>> getDistinctByForwardEmpTypeByEmpCode() {
        List<WorkerForwardTypeMaster> workerForwardList = new ArrayList<>();
        log.debug("REST request to get a page of WorkerForwardTypeMaster");
        List<Object[]> workerForwards = workerWorkFlowMasterRepository.getDistinctByForwardEmpTypeByEmpCode(SecurityUtils.getCurrentUserLogin().orElse(null));
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
    @GetMapping("/worker-forward-type-masters-fetch/{forwardEmpType}")
    @Timed
    public ResponseEntity<List<WorkerWorkFlowMaster>> getAllWorkerWorkFlowMasterByEmpCode(@PathVariable String forwardEmpType) {
        log.debug("REST request to get a page of RnsEmpLinkMasters");
        return ResponseEntity.ok().body(workerWorkFlowMasterRepository.getWorkerWorkFlowMasterByEmpCodeWithFlag(SecurityUtils.getCurrentUserLogin().orElse(null), forwardEmpType));
    }
}
