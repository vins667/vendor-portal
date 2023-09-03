package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerAddressDetails;
import io.vamani.application.repository.WorkerAddressDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkerAddressDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerAddressDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerAddressDetailsResource.class);

    private static final String ENTITY_NAME = "workerAddressDetails";

    private final WorkerAddressDetailsRepository workerAddressDetailsRepository;

    public WorkerAddressDetailsResource(WorkerAddressDetailsRepository workerAddressDetailsRepository) {
        this.workerAddressDetailsRepository = workerAddressDetailsRepository;
    }

    /**
     * POST  /worker-address-details : Create a new workerAddressDetails.
     *
     * @param workerAddressDetails the workerAddressDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerAddressDetails, or with status 400 (Bad Request) if the workerAddressDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-address-details")
    @Timed
    public ResponseEntity<WorkerAddressDetails> createWorkerAddressDetails(@Valid @RequestBody WorkerAddressDetails workerAddressDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerAddressDetails : {}", workerAddressDetails);
        if (workerAddressDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerAddressDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerAddressDetails result = workerAddressDetailsRepository.save(workerAddressDetails);
        return ResponseEntity.created(new URI("/api/worker-address-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-address-details : Updates an existing workerAddressDetails.
     *
     * @param workerAddressDetails the workerAddressDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerAddressDetails,
     * or with status 400 (Bad Request) if the workerAddressDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerAddressDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-address-details")
    @Timed
    public ResponseEntity<WorkerAddressDetails> updateWorkerAddressDetails(@Valid @RequestBody WorkerAddressDetails workerAddressDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerAddressDetails : {}", workerAddressDetails);
        if (workerAddressDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerAddressDetails result = workerAddressDetailsRepository.save(workerAddressDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerAddressDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-address-details : get all the workerAddressDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerAddressDetails in body
     */
    @GetMapping("/worker-address-details")
    @Timed
    public ResponseEntity<List<WorkerAddressDetails>> getAllWorkerAddressDetails(Pageable pageable) {
        log.debug("REST request to get a page of WorkerAddressDetails");
        Page<WorkerAddressDetails> page = workerAddressDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-address-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-address-details/:id : get the "id" workerAddressDetails.
     *
     * @param id the id of the workerAddressDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerAddressDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-address-details/{id}")
    @Timed
    public ResponseEntity<WorkerAddressDetails> getWorkerAddressDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerAddressDetails : {}", id);
        Optional<WorkerAddressDetails> workerAddressDetails = workerAddressDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerAddressDetails);
    }

    /**
     * DELETE  /worker-address-details/:id : delete the "id" workerAddressDetails.
     *
     * @param id the id of the workerAddressDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-address-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerAddressDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerAddressDetails : {}", id);

        workerAddressDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
