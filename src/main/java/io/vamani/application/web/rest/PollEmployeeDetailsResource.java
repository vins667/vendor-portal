package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.PollEmployeeDetails;
import io.vamani.application.model.Message;
import io.vamani.application.repository.PollEmployeeDetailsRepository;
import io.vamani.application.security.SecurityUtils;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PollEmployeeDetails.
 */
@RestController
@RequestMapping("/api")
public class PollEmployeeDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PollEmployeeDetailsResource.class);

    private static final String ENTITY_NAME = "pollEmployeeDetails";

    private final PollEmployeeDetailsRepository pollEmployeeDetailsRepository;

    public PollEmployeeDetailsResource(PollEmployeeDetailsRepository pollEmployeeDetailsRepository) {
        this.pollEmployeeDetailsRepository = pollEmployeeDetailsRepository;
    }

    /**
     * POST  /poll-employee-details : Create a new pollEmployeeDetails.
     *
     * @param pollEmployeeDetails the pollEmployeeDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pollEmployeeDetails, or with status 400 (Bad Request) if the pollEmployeeDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poll-employee-details")
    @Timed
    public ResponseEntity<Message> createPollEmployeeDetails(@Valid @RequestBody PollEmployeeDetails pollEmployeeDetails) throws URISyntaxException {
        log.debug("REST request to save PollEmployeeDetails : {}", pollEmployeeDetails);
        PollEmployeeDetails details = pollEmployeeDetailsRepository.findAllByPollMasterAndCreatedBy(pollEmployeeDetails.getPollMaster(), SecurityUtils.getCurrentUserLogin().orElse(null));
        if (details != null) {
            return ResponseUtil.wrapOrNotFound(Optional.of(new Message("error", "Poll already saved!!!")));
        } else {
            pollEmployeeDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            pollEmployeeDetails.setCreatedDate(Instant.now());
            if (pollEmployeeDetails.getId() != null) {
                throw new BadRequestAlertException("A new pollEmployeeDetails cannot already have an ID", ENTITY_NAME, "idexists");
            }
            PollEmployeeDetails result = pollEmployeeDetailsRepository.save(pollEmployeeDetails);
            return ResponseUtil.wrapOrNotFound(Optional.of(new Message("success", "Poll save successfully!!!")));
        }
    }

    /**
     * PUT  /poll-employee-details : Updates an existing pollEmployeeDetails.
     *
     * @param pollEmployeeDetails the pollEmployeeDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pollEmployeeDetails,
     * or with status 400 (Bad Request) if the pollEmployeeDetails is not valid,
     * or with status 500 (Internal Server Error) if the pollEmployeeDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poll-employee-details")
    @Timed
    public ResponseEntity<PollEmployeeDetails> updatePollEmployeeDetails(@Valid @RequestBody PollEmployeeDetails pollEmployeeDetails) throws URISyntaxException {
        log.debug("REST request to update PollEmployeeDetails : {}", pollEmployeeDetails);
        if (pollEmployeeDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PollEmployeeDetails result = pollEmployeeDetailsRepository.save(pollEmployeeDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pollEmployeeDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poll-employee-details : get all the pollEmployeeDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pollEmployeeDetails in body
     */
    @GetMapping("/poll-employee-details")
    @Timed
    public List<PollEmployeeDetails> getAllPollEmployeeDetails() {
        log.debug("REST request to get all PollEmployeeDetails");
        return pollEmployeeDetailsRepository.findAll();
    }

    /**
     * GET  /poll-employee-details/:id : get the "id" pollEmployeeDetails.
     *
     * @param id the id of the pollEmployeeDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pollEmployeeDetails, or with status 404 (Not Found)
     */
    @GetMapping("/poll-employee-details/{id}")
    @Timed
    public ResponseEntity<PollEmployeeDetails> getPollEmployeeDetails(@PathVariable Long id) {
        log.debug("REST request to get PollEmployeeDetails : {}", id);
        Optional<PollEmployeeDetails> pollEmployeeDetails = pollEmployeeDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pollEmployeeDetails);
    }

    /**
     * DELETE  /poll-employee-details/:id : delete the "id" pollEmployeeDetails.
     *
     * @param id the id of the pollEmployeeDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poll-employee-details/{id}")
    @Timed
    public ResponseEntity<Void> deletePollEmployeeDetails(@PathVariable Long id) {
        log.debug("REST request to delete PollEmployeeDetails : {}", id);

        pollEmployeeDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
