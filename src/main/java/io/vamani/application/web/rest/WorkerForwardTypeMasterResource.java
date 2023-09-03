package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerForwardTypeMaster;
import io.vamani.application.repository.WorkerForwardTypeMasterRepository;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkerForwardTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class WorkerForwardTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(WorkerForwardTypeMasterResource.class);

    private static final String ENTITY_NAME = "workerForwardTypeMaster";

    private final WorkerForwardTypeMasterRepository workerForwardTypeMasterRepository;

    public WorkerForwardTypeMasterResource(WorkerForwardTypeMasterRepository workerForwardTypeMasterRepository) {
        this.workerForwardTypeMasterRepository = workerForwardTypeMasterRepository;
    }

    /**
     * POST  /worker-forward-type-masters : Create a new workerForwardTypeMaster.
     *
     * @param workerForwardTypeMaster the workerForwardTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerForwardTypeMaster, or with status 400 (Bad Request) if the workerForwardTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-forward-type-masters")
    @Timed
    public ResponseEntity<WorkerForwardTypeMaster> createWorkerForwardTypeMaster(@Valid @RequestBody WorkerForwardTypeMaster workerForwardTypeMaster) throws URISyntaxException {
        log.debug("REST request to save WorkerForwardTypeMaster : {}", workerForwardTypeMaster);
        if (workerForwardTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new workerForwardTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerForwardTypeMaster result = workerForwardTypeMasterRepository.save(workerForwardTypeMaster);
        return ResponseEntity.created(new URI("/api/worker-forward-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-forward-type-masters : Updates an existing workerForwardTypeMaster.
     *
     * @param workerForwardTypeMaster the workerForwardTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerForwardTypeMaster,
     * or with status 400 (Bad Request) if the workerForwardTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the workerForwardTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-forward-type-masters")
    @Timed
    public ResponseEntity<WorkerForwardTypeMaster> updateWorkerForwardTypeMaster(@Valid @RequestBody WorkerForwardTypeMaster workerForwardTypeMaster) throws URISyntaxException {
        log.debug("REST request to update WorkerForwardTypeMaster : {}", workerForwardTypeMaster);
        if (workerForwardTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerForwardTypeMaster result = workerForwardTypeMasterRepository.save(workerForwardTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerForwardTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-forward-type-masters : get all the workerForwardTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerForwardTypeMasters in body
     */
    @GetMapping("/worker-forward-type-masters")
    @Timed
    public ResponseEntity<List<WorkerForwardTypeMaster>> getAllWorkerForwardTypeMasters(Pageable pageable) {
        log.debug("REST request to get a page of WorkerForwardTypeMasters");
        Page<WorkerForwardTypeMaster> page = workerForwardTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-forward-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-forward-type-masters/:id : get the "id" workerForwardTypeMaster.
     *
     * @param id the id of the workerForwardTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerForwardTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/worker-forward-type-masters/{id}")
    @Timed
    public ResponseEntity<WorkerForwardTypeMaster> getWorkerForwardTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get WorkerForwardTypeMaster : {}", id);
        Optional<WorkerForwardTypeMaster> workerForwardTypeMaster = workerForwardTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerForwardTypeMaster);
    }

    /**
     * DELETE  /worker-forward-type-masters/:id : delete the "id" workerForwardTypeMaster.
     *
     * @param id the id of the workerForwardTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-forward-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerForwardTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete WorkerForwardTypeMaster : {}", id);

        workerForwardTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
