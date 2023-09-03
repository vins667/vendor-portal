package io.vamani.application.web.rest;

import io.vamani.application.domain.TravelForexDetails;
import io.vamani.application.repository.TravelForexDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.TravelForexDetails}.
 */
@RestController
@RequestMapping("/api")
public class TravelForexDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TravelForexDetailsResource.class);

    private static final String ENTITY_NAME = "travelForexDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelForexDetailsRepository travelForexDetailsRepository;

    public TravelForexDetailsResource(TravelForexDetailsRepository travelForexDetailsRepository) {
        this.travelForexDetailsRepository = travelForexDetailsRepository;
    }

    /**
     * {@code POST  /travel-forex-details} : Create a new travelForexDetails.
     *
     * @param travelForexDetails the travelForexDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelForexDetails, or with status {@code 400 (Bad Request)} if the travelForexDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-forex-details")
    public ResponseEntity<TravelForexDetails> createTravelForexDetails(@Valid @RequestBody TravelForexDetails travelForexDetails) throws URISyntaxException {
        log.debug("REST request to save TravelForexDetails : {}", travelForexDetails);
        if (travelForexDetails.getId() != null) {
            throw new BadRequestAlertException("A new travelForexDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelForexDetails result = travelForexDetailsRepository.save(travelForexDetails);
        return ResponseEntity.created(new URI("/api/travel-forex-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-forex-details} : Updates an existing travelForexDetails.
     *
     * @param travelForexDetails the travelForexDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelForexDetails,
     * or with status {@code 400 (Bad Request)} if the travelForexDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelForexDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-forex-details")
    public ResponseEntity<TravelForexDetails> updateTravelForexDetails(@Valid @RequestBody TravelForexDetails travelForexDetails) throws URISyntaxException {
        log.debug("REST request to update TravelForexDetails : {}", travelForexDetails);
        if (travelForexDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelForexDetails result = travelForexDetailsRepository.save(travelForexDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelForexDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-forex-details} : get all the travelForexDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelForexDetails in body.
     */
    @GetMapping("/travel-forex-details")
    public ResponseEntity<List<TravelForexDetails>> getAllTravelForexDetails(Pageable pageable) {
        log.debug("REST request to get a page of TravelForexDetails");
        Page<TravelForexDetails> page = travelForexDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-forex-details/:id} : get the "id" travelForexDetails.
     *
     * @param id the id of the travelForexDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelForexDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-forex-details/{id}")
    public ResponseEntity<TravelForexDetails> getTravelForexDetails(@PathVariable Long id) {
        log.debug("REST request to get TravelForexDetails : {}", id);
        Optional<TravelForexDetails> travelForexDetails = travelForexDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(travelForexDetails);
    }

    /**
     * {@code DELETE  /travel-forex-details/:id} : delete the "id" travelForexDetails.
     *
     * @param id the id of the travelForexDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-forex-details/{id}")
    public ResponseEntity<Void> deleteTravelForexDetails(@PathVariable Long id) {
        log.debug("REST request to delete TravelForexDetails : {}", id);
        travelForexDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
