package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerFamilyDetails;
import io.vamani.application.repository.WorkerFamilyDetailsRepository;
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
 * REST controller for managing WorkerFamilyDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerFamilyDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerFamilyDetailsResource.class);

    private static final String ENTITY_NAME = "workerFamilyDetails";

    private final WorkerFamilyDetailsRepository workerFamilyDetailsRepository;

    public WorkerFamilyDetailsResource(WorkerFamilyDetailsRepository workerFamilyDetailsRepository) {
        this.workerFamilyDetailsRepository = workerFamilyDetailsRepository;
    }

    /**
     * POST  /worker-family-details : Create a new workerFamilyDetails.
     *
     * @param workerFamilyDetails the workerFamilyDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerFamilyDetails, or with status 400 (Bad Request) if the workerFamilyDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-family-details")
    @Timed
    public ResponseEntity<WorkerFamilyDetails> createWorkerFamilyDetails(@Valid @RequestBody WorkerFamilyDetails workerFamilyDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerFamilyDetails : {}", workerFamilyDetails);
        if (workerFamilyDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerFamilyDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerFamilyDetails result = workerFamilyDetailsRepository.save(workerFamilyDetails);
        return ResponseEntity.created(new URI("/api/worker-family-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-family-details : Updates an existing workerFamilyDetails.
     *
     * @param workerFamilyDetails the workerFamilyDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerFamilyDetails,
     * or with status 400 (Bad Request) if the workerFamilyDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerFamilyDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-family-details")
    @Timed
    public ResponseEntity<WorkerFamilyDetails> updateWorkerFamilyDetails(@Valid @RequestBody WorkerFamilyDetails workerFamilyDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerFamilyDetails : {}", workerFamilyDetails);
        if (workerFamilyDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerFamilyDetails result = workerFamilyDetailsRepository.save(workerFamilyDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerFamilyDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-family-details : get all the workerFamilyDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerFamilyDetails in body
     */
    @GetMapping("/worker-family-details")
    @Timed
    public ResponseEntity<List<WorkerFamilyDetails>> getAllWorkerFamilyDetails(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of WorkerFamilyDetails");
        Page<WorkerFamilyDetails> page = workerFamilyDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-family-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-family-details/:id : get the "id" workerFamilyDetails.
     *
     * @param id the id of the workerFamilyDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerFamilyDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-family-details/{id}")
    @Timed
    public ResponseEntity<List<WorkerFamilyDetails>> getWorkerFamilyDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerFamilyDetails : {}", id);
        List<WorkerFamilyDetails> workerFamilyDetails = workerFamilyDetailsRepository.findByWorkerJoiningId(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(workerFamilyDetails));
    }

    /**
     * DELETE  /worker-family-details/:id : delete the "id" workerFamilyDetails.
     *
     * @param id the id of the workerFamilyDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-family-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerFamilyDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerFamilyDetails : {}", id);

        workerFamilyDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
