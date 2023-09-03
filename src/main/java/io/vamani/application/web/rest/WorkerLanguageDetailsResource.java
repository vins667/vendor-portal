package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.WorkerLanguageDetails;
import io.vamani.application.repository.WorkerLanguageDetailsRepository;
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
 * REST controller for managing WorkerLanguageDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerLanguageDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerLanguageDetailsResource.class);

    private static final String ENTITY_NAME = "workerLanguageDetails";

    private final WorkerLanguageDetailsRepository workerLanguageDetailsRepository;

    public WorkerLanguageDetailsResource(WorkerLanguageDetailsRepository workerLanguageDetailsRepository) {
        this.workerLanguageDetailsRepository = workerLanguageDetailsRepository;
    }

    /**
     * POST  /worker-language-details : Create a new workerLanguageDetails.
     *
     * @param workerLanguageDetails the workerLanguageDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerLanguageDetails, or with status 400 (Bad Request) if the workerLanguageDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-language-details")
    @Timed
    public ResponseEntity<WorkerLanguageDetails> createWorkerLanguageDetails(@Valid @RequestBody WorkerLanguageDetails workerLanguageDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerLanguageDetails : {}", workerLanguageDetails);
        if (workerLanguageDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerLanguageDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerLanguageDetails result = workerLanguageDetailsRepository.save(workerLanguageDetails);
        return ResponseEntity.created(new URI("/api/worker-language-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worker-language-details : Updates an existing workerLanguageDetails.
     *
     * @param workerLanguageDetails the workerLanguageDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerLanguageDetails,
     * or with status 400 (Bad Request) if the workerLanguageDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerLanguageDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-language-details")
    @Timed
    public ResponseEntity<WorkerLanguageDetails> updateWorkerLanguageDetails(@Valid @RequestBody WorkerLanguageDetails workerLanguageDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerLanguageDetails : {}", workerLanguageDetails);
        if (workerLanguageDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerLanguageDetails result = workerLanguageDetailsRepository.save(workerLanguageDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerLanguageDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-language-details : get all the workerLanguageDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerLanguageDetails in body
     */
    @GetMapping("/worker-language-details")
    @Timed
    public ResponseEntity<List<WorkerLanguageDetails>> getAllWorkerLanguageDetails(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of WorkerLanguageDetails");
        Page<WorkerLanguageDetails> page = workerLanguageDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-language-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-language-details/:id : get the "id" workerLanguageDetails.
     *
     * @param id the id of the workerLanguageDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerLanguageDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-language-details/{id}")
    @Timed
    public ResponseEntity<WorkerLanguageDetails> getWorkerLanguageDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerLanguageDetails : {}", id);
        Optional<WorkerLanguageDetails> workerLanguageDetails = workerLanguageDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerLanguageDetails);
    }

    /**
     * DELETE  /worker-language-details/:id : delete the "id" workerLanguageDetails.
     *
     * @param id the id of the workerLanguageDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-language-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerLanguageDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerLanguageDetails : {}", id);

        workerLanguageDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
