package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerEducationDetails;
import io.vamani.application.repository.WorkerEducationDetailsRepository;
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
 * REST controller for managing WorkerEducationDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerEducationDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerEducationDetailsResource.class);

    private static final String ENTITY_NAME = "workerEducationDetails";

    private final WorkerEducationDetailsRepository workerEducationDetailsRepository;

    public WorkerEducationDetailsResource(WorkerEducationDetailsRepository workerEducationDetailsRepository) {
        this.workerEducationDetailsRepository = workerEducationDetailsRepository;
    }

    /**
     * POST  /worker-education-details : Create a new workerEducationDetails.
     *
     * @param workerEducationDetails the workerEducationDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerEducationDetails, or with status 400 (Bad Request) if the workerEducationDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-education-details")
    @Timed
    public ResponseEntity<WorkerEducationDetails> createWorkerEducationDetails(@Valid @RequestBody WorkerEducationDetails workerEducationDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerEducationDetails : {}", workerEducationDetails);
        if (workerEducationDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerEducationDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerEducationDetails result = workerEducationDetailsRepository.save(workerEducationDetails);
        return ResponseEntity.created(new URI("/api/worker-education-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-education-details : Updates an existing workerEducationDetails.
     *
     * @param workerEducationDetails the workerEducationDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerEducationDetails,
     * or with status 400 (Bad Request) if the workerEducationDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerEducationDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-education-details")
    @Timed
    public ResponseEntity<WorkerEducationDetails> updateWorkerEducationDetails(@Valid @RequestBody WorkerEducationDetails workerEducationDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerEducationDetails : {}", workerEducationDetails);
        if (workerEducationDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerEducationDetails result = workerEducationDetailsRepository.save(workerEducationDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerEducationDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-education-details : get all the workerEducationDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerEducationDetails in body
     */
    @GetMapping("/worker-education-details")
    @Timed
    public ResponseEntity<List<WorkerEducationDetails>> getAllWorkerEducationDetails(Pageable pageable) {
        log.debug("REST request to get a page of WorkerEducationDetails");
        Page<WorkerEducationDetails> page = workerEducationDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-education-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-education-details/:id : get the "id" workerEducationDetails.
     *
     * @param id the id of the workerEducationDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerEducationDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-education-details/{id}")
    @Timed
    public ResponseEntity<WorkerEducationDetails> getWorkerEducationDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerEducationDetails : {}", id);
        Optional<WorkerEducationDetails> workerEducationDetails = workerEducationDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerEducationDetails);
    }

    /**
     * DELETE  /worker-education-details/:id : delete the "id" workerEducationDetails.
     *
     * @param id the id of the workerEducationDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-education-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerEducationDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerEducationDetails : {}", id);

        workerEducationDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
