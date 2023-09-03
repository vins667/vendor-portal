package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerReferenceDetails;
import io.vamani.application.repository.WorkerReferenceDetailsRepository;
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
 * REST controller for managing WorkerReferenceDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerReferenceDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerReferenceDetailsResource.class);

    private static final String ENTITY_NAME = "workerReferenceDetails";

    private final WorkerReferenceDetailsRepository workerReferenceDetailsRepository;

    public WorkerReferenceDetailsResource(WorkerReferenceDetailsRepository workerReferenceDetailsRepository) {
        this.workerReferenceDetailsRepository = workerReferenceDetailsRepository;
    }

    /**
     * POST  /worker-reference-details : Create a new workerReferenceDetails.
     *
     * @param workerReferenceDetails the workerReferenceDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerReferenceDetails, or with status 400 (Bad Request) if the workerReferenceDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-reference-details")
    @Timed
    public ResponseEntity<WorkerReferenceDetails> createWorkerReferenceDetails(@Valid @RequestBody WorkerReferenceDetails workerReferenceDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerReferenceDetails : {}", workerReferenceDetails);
        if (workerReferenceDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerReferenceDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerReferenceDetails result = workerReferenceDetailsRepository.save(workerReferenceDetails);
        return ResponseEntity.created(new URI("/api/worker-reference-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-reference-details : Updates an existing workerReferenceDetails.
     *
     * @param workerReferenceDetails the workerReferenceDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerReferenceDetails,
     * or with status 400 (Bad Request) if the workerReferenceDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerReferenceDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-reference-details")
    @Timed
    public ResponseEntity<WorkerReferenceDetails> updateWorkerReferenceDetails(@Valid @RequestBody WorkerReferenceDetails workerReferenceDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerReferenceDetails : {}", workerReferenceDetails);
        if (workerReferenceDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerReferenceDetails result = workerReferenceDetailsRepository.save(workerReferenceDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerReferenceDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-reference-details : get all the workerReferenceDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerReferenceDetails in body
     */
    @GetMapping("/worker-reference-details")
    @Timed
    public ResponseEntity<List<WorkerReferenceDetails>> getAllWorkerReferenceDetails(Pageable pageable) {
        log.debug("REST request to get a page of WorkerReferenceDetails");
        Page<WorkerReferenceDetails> page = workerReferenceDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-reference-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-reference-details/:id : get the "id" workerReferenceDetails.
     *
     * @param id the id of the workerReferenceDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerReferenceDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-reference-details/{id}")
    @Timed
    public ResponseEntity<WorkerReferenceDetails> getWorkerReferenceDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerReferenceDetails : {}", id);
        Optional<WorkerReferenceDetails> workerReferenceDetails = workerReferenceDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerReferenceDetails);
    }

    /**
     * DELETE  /worker-reference-details/:id : delete the "id" workerReferenceDetails.
     *
     * @param id the id of the workerReferenceDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-reference-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerReferenceDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerReferenceDetails : {}", id);

        workerReferenceDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
