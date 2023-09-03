package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerJobsDetails;
import io.vamani.application.repository.WorkerJobsDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkerJobsDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerJobsDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerJobsDetailsResource.class);

    private static final String ENTITY_NAME = "workerJobsDetails";

    private final WorkerJobsDetailsRepository workerJobsDetailsRepository;

    public WorkerJobsDetailsResource(WorkerJobsDetailsRepository workerJobsDetailsRepository) {
        this.workerJobsDetailsRepository = workerJobsDetailsRepository;
    }

    /**
     * POST  /worker-jobs-details : Create a new workerJobsDetails.
     *
     * @param workerJobsDetails the workerJobsDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerJobsDetails, or with status 400 (Bad Request) if the workerJobsDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-jobs-details")
    @Timed
    public ResponseEntity<WorkerJobsDetails> createWorkerJobsDetails(@Valid @RequestBody WorkerJobsDetails workerJobsDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerJobsDetails : {}", workerJobsDetails);
        if (workerJobsDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerJobsDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerJobsDetails result = workerJobsDetailsRepository.save(workerJobsDetails);
        return ResponseEntity.created(new URI("/api/worker-jobs-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-jobs-details : Updates an existing workerJobsDetails.
     *
     * @param workerJobsDetails the workerJobsDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerJobsDetails,
     * or with status 400 (Bad Request) if the workerJobsDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerJobsDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-jobs-details")
    @Timed
    public ResponseEntity<WorkerJobsDetails> updateWorkerJobsDetails(@Valid @RequestBody WorkerJobsDetails workerJobsDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerJobsDetails : {}", workerJobsDetails);
        if (workerJobsDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerJobsDetails result = workerJobsDetailsRepository.save(workerJobsDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerJobsDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-jobs-details : get all the workerJobsDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerJobsDetails in body
     */
    @GetMapping("/worker-jobs-details")
    @Timed
    public ResponseEntity<List<WorkerJobsDetails>> getAllWorkerJobsDetails(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of WorkerJobsDetails");
        Page<WorkerJobsDetails> page = workerJobsDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-jobs-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-jobs-details/:id : get the "id" workerJobsDetails.
     *
     * @param id the id of the workerJobsDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerJobsDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-jobs-details/{id}")
    @Timed
    public ResponseEntity<WorkerJobsDetails> getWorkerJobsDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerJobsDetails : {}", id);
        Optional<WorkerJobsDetails> workerJobsDetails = workerJobsDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerJobsDetails);
    }

    /**
     * DELETE  /worker-jobs-details/:id : delete the "id" workerJobsDetails.
     *
     * @param id the id of the workerJobsDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-jobs-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerJobsDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerJobsDetails : {}", id);

        workerJobsDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
