package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerNominationDetails;
import io.vamani.application.repository.WorkerNominationDetailsRepository;
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
 * REST controller for managing WorkerNominationDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerNominationDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerNominationDetailsResource.class);

    private static final String ENTITY_NAME = "workerNominationDetails";

    private final WorkerNominationDetailsRepository workerNominationDetailsRepository;

    public WorkerNominationDetailsResource(WorkerNominationDetailsRepository workerNominationDetailsRepository) {
        this.workerNominationDetailsRepository = workerNominationDetailsRepository;
    }

    /**
     * POST  /worker-nomination-details : Create a new workerNominationDetails.
     *
     * @param workerNominationDetails the workerNominationDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerNominationDetails, or with status 400 (Bad Request) if the workerNominationDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-nomination-details")
    @Timed
    public ResponseEntity<WorkerNominationDetails> createWorkerNominationDetails(@Valid @RequestBody WorkerNominationDetails workerNominationDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerNominationDetails : {}", workerNominationDetails);
        if (workerNominationDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerNominationDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerNominationDetails result = workerNominationDetailsRepository.save(workerNominationDetails);
        return ResponseEntity.created(new URI("/api/worker-nomination-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-nomination-details : Updates an existing workerNominationDetails.
     *
     * @param workerNominationDetails the workerNominationDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerNominationDetails,
     * or with status 400 (Bad Request) if the workerNominationDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerNominationDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-nomination-details")
    @Timed
    public ResponseEntity<WorkerNominationDetails> updateWorkerNominationDetails(@Valid @RequestBody WorkerNominationDetails workerNominationDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerNominationDetails : {}", workerNominationDetails);
        if (workerNominationDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerNominationDetails result = workerNominationDetailsRepository.save(workerNominationDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerNominationDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-nomination-details : get all the workerNominationDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerNominationDetails in body
     */
    @GetMapping("/worker-nomination-details")
    @Timed
    public ResponseEntity<List<WorkerNominationDetails>> getAllWorkerNominationDetails(Pageable pageable) {
        log.debug("REST request to get a page of WorkerNominationDetails");
        Page<WorkerNominationDetails> page = workerNominationDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-nomination-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-nomination-details/:id : get the "id" workerNominationDetails.
     *
     * @param id the id of the workerNominationDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerNominationDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-nomination-details/{id}")
    @Timed
    public ResponseEntity<WorkerNominationDetails> getWorkerNominationDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerNominationDetails : {}", id);
        Optional<WorkerNominationDetails> workerNominationDetails = workerNominationDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerNominationDetails);
    }

    /**
     * DELETE  /worker-nomination-details/:id : delete the "id" workerNominationDetails.
     *
     * @param id the id of the workerNominationDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-nomination-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerNominationDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerNominationDetails : {}", id);

        workerNominationDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
