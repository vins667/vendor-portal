package io.vamani.application.web.rest;

import io.vamani.application.domain.MarkerEntryDetails;
import io.vamani.application.repository.MarkerEntryDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.vamani.application.domain.MarkerEntryDetails}.
 */
@RestController
@RequestMapping("/api")
public class MarkerEntryDetailsResource {

    private final Logger log = LoggerFactory.getLogger(MarkerEntryDetailsResource.class);

    private static final String ENTITY_NAME = "markerEntryDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MarkerEntryDetailsRepository markerEntryDetailsRepository;

    public MarkerEntryDetailsResource(MarkerEntryDetailsRepository markerEntryDetailsRepository) {
        this.markerEntryDetailsRepository = markerEntryDetailsRepository;
    }

    /**
     * {@code POST  /marker-entry-details} : Create a new markerEntryDetails.
     *
     * @param markerEntryDetails the markerEntryDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new markerEntryDetails, or with status {@code 400 (Bad Request)} if the markerEntryDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/marker-entry-details")
    public ResponseEntity<MarkerEntryDetails> createMarkerEntryDetails(@Valid @RequestBody MarkerEntryDetails markerEntryDetails) throws URISyntaxException {
        log.debug("REST request to save MarkerEntryDetails : {}", markerEntryDetails);
        if (markerEntryDetails.getId() != null) {
            throw new BadRequestAlertException("A new markerEntryDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MarkerEntryDetails result = markerEntryDetailsRepository.save(markerEntryDetails);
        return ResponseEntity.created(new URI("/api/marker-entry-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /marker-entry-details} : Updates an existing markerEntryDetails.
     *
     * @param markerEntryDetails the markerEntryDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated markerEntryDetails,
     * or with status {@code 400 (Bad Request)} if the markerEntryDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the markerEntryDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/marker-entry-details")
    public ResponseEntity<MarkerEntryDetails> updateMarkerEntryDetails(@Valid @RequestBody MarkerEntryDetails markerEntryDetails) throws URISyntaxException {
        log.debug("REST request to update MarkerEntryDetails : {}", markerEntryDetails);
        if (markerEntryDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MarkerEntryDetails result = markerEntryDetailsRepository.save(markerEntryDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, markerEntryDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /marker-entry-details} : get all the markerEntryDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of markerEntryDetails in body.
     */
    @GetMapping("/marker-entry-details")
    public ResponseEntity<List<MarkerEntryDetails>> getAllMarkerEntryDetails(Pageable pageable) {
        log.debug("REST request to get a page of MarkerEntryDetails");
        Page<MarkerEntryDetails> page = markerEntryDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /marker-entry-details/:id} : get the "id" markerEntryDetails.
     *
     * @param id the id of the markerEntryDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the markerEntryDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/marker-entry-details/{id}")
    public ResponseEntity<MarkerEntryDetails> getMarkerEntryDetails(@PathVariable Long id) {
        log.debug("REST request to get MarkerEntryDetails : {}", id);
        Optional<MarkerEntryDetails> markerEntryDetails = markerEntryDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(markerEntryDetails);
    }

    /**
     * {@code DELETE  /marker-entry-details/:id} : delete the "id" markerEntryDetails.
     *
     * @param id the id of the markerEntryDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/marker-entry-details/{id}")
    public ResponseEntity<Void> deleteMarkerEntryDetails(@PathVariable Long id) {
        log.debug("REST request to delete MarkerEntryDetails : {}", id);
        markerEntryDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
