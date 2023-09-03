package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.TdsGroupDetails;
import io.vamani.application.repository.TdsGroupDetailsRepository;
import io.vamani.application.security.SecurityUtils;
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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TdsGroupDetails.
 */
@RestController
@RequestMapping("/api")
public class TdsGroupDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TdsGroupDetailsResource.class);

    private static final String ENTITY_NAME = "tdsGroupDetails";
   
    private final TdsGroupDetailsRepository tdsGroupDetailsRepository;

    public TdsGroupDetailsResource(TdsGroupDetailsRepository tdsGroupDetailsRepository) {
        this.tdsGroupDetailsRepository = tdsGroupDetailsRepository;
    }

    /**
     * POST  /tds-group-details : Create a new tdsGroupDetails.
     *
     * @param tdsGroupDetails the tdsGroupDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tdsGroupDetails, or with status 400 (Bad Request) if the tdsGroupDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tds-group-details")
    @Timed
    public ResponseEntity<TdsGroupDetails> createTdsGroupDetails(@Valid @RequestBody TdsGroupDetails tdsGroupDetails) throws URISyntaxException {
        log.debug("REST request to save TdsGroupDetails : {}", tdsGroupDetails);
        if (tdsGroupDetails.getId() != null) {
            throw new BadRequestAlertException("A new tdsGroupDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tdsGroupDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        tdsGroupDetails.setCreatedDate(Instant.now());
        TdsGroupDetails result = tdsGroupDetailsRepository.save(tdsGroupDetails);
        return ResponseEntity.created(new URI("/api/tds-group-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tds-group-details : Updates an existing tdsGroupDetails.
     *
     * @param tdsGroupDetails the tdsGroupDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tdsGroupDetails,
     * or with status 400 (Bad Request) if the tdsGroupDetails is not valid,
     * or with status 500 (Internal Server Error) if the tdsGroupDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tds-group-details")
    @Timed
    public ResponseEntity<TdsGroupDetails> updateTdsGroupDetails(@Valid @RequestBody TdsGroupDetails tdsGroupDetails) throws URISyntaxException {
        log.debug("REST request to update TdsGroupDetails : {}", tdsGroupDetails);
        if (tdsGroupDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        tdsGroupDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        tdsGroupDetails.setLastUpdatedDate(Instant.now());
        TdsGroupDetails result = tdsGroupDetailsRepository.save(tdsGroupDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tdsGroupDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tds-group-details : get all the tdsGroupDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsGroupDetails in body
     */
    @GetMapping("/tds-group-details")
    @Timed
    public ResponseEntity<List<TdsGroupDetails>> getAllTdsGroupDetails(Pageable pageable) {
        log.debug("REST request to get a page of TdsGroupDetails");
        Page<TdsGroupDetails> page = tdsGroupDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tds-group-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tds-group-details/:id : get the "id" tdsGroupDetails.
     *
     * @param id the id of the tdsGroupDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsGroupDetails, or with status 404 (Not Found)
     */
    @GetMapping("/tds-group-details/{id}")
    @Timed
    public ResponseEntity<TdsGroupDetails> getTdsGroupDetails(@PathVariable Long id) {
        log.debug("REST request to get TdsGroupDetails : {}", id);
        Optional<TdsGroupDetails> tdsGroupDetails = tdsGroupDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tdsGroupDetails);
    }

    /**
     * DELETE  /tds-group-details/:id : delete the "id" tdsGroupDetails.
     *
     * @param id the id of the tdsGroupDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tds-group-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteTdsGroupDetails(@PathVariable Long id) {
        log.debug("REST request to delete TdsGroupDetails : {}", id);

        tdsGroupDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
