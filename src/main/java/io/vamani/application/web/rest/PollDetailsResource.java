package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.PollDetails;
import io.vamani.application.repository.PollDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PollDetails.
 */
@RestController
@RequestMapping("/api")
public class PollDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PollDetailsResource.class);

    private static final String ENTITY_NAME = "pollDetails";

    private final PollDetailsRepository pollDetailsRepository;

    public PollDetailsResource(PollDetailsRepository pollDetailsRepository) {
        this.pollDetailsRepository = pollDetailsRepository;
    }

    /**
     * POST  /poll-details : Create a new pollDetails.
     *
     * @param pollDetails the pollDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pollDetails, or with status 400 (Bad Request) if the pollDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poll-details")
    @Timed
    public ResponseEntity<PollDetails> createPollDetails(@Valid @RequestBody PollDetails pollDetails) throws URISyntaxException {
        log.debug("REST request to save PollDetails : {}", pollDetails);
        if (pollDetails.getId() != null) {
            throw new BadRequestAlertException("A new pollDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PollDetails result = pollDetailsRepository.save(pollDetails);
        return ResponseEntity.created(new URI("/api/poll-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /poll-details : Updates an existing pollDetails.
     *
     * @param pollDetails the pollDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pollDetails,
     * or with status 400 (Bad Request) if the pollDetails is not valid,
     * or with status 500 (Internal Server Error) if the pollDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poll-details")
    @Timed
    public ResponseEntity<PollDetails> updatePollDetails(@Valid @RequestBody PollDetails pollDetails) throws URISyntaxException {
        log.debug("REST request to update PollDetails : {}", pollDetails);
        if (pollDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PollDetails result = pollDetailsRepository.save(pollDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pollDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poll-details : get all the pollDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pollDetails in body
     */
    @GetMapping("/poll-details")
    @Timed
    public List<PollDetails> getAllPollDetails() {
        log.debug("REST request to get all PollDetails");
        return pollDetailsRepository.findAll();
    }

    /**
     * GET  /poll-details/:id : get the "id" pollDetails.
     *
     * @param id the id of the pollDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pollDetails, or with status 404 (Not Found)
     */
    @GetMapping("/poll-details/{id}")
    @Timed
    public ResponseEntity<PollDetails> getPollDetails(@PathVariable Long id) {
        log.debug("REST request to get PollDetails : {}", id);
        Optional<PollDetails> pollDetails = pollDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pollDetails);
    }

    /**
     * DELETE  /poll-details/:id : delete the "id" pollDetails.
     *
     * @param id the id of the pollDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poll-details/{id}")
    @Timed
    public ResponseEntity<Void> deletePollDetails(@PathVariable Long id) {
        log.debug("REST request to delete PollDetails : {}", id);

        pollDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
