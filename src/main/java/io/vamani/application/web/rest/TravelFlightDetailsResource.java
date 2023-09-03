package io.vamani.application.web.rest;

import io.vamani.application.domain.TravelFlightDetails;
import io.vamani.application.repository.TravelFlightDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.TravelFlightDetails}.
 */
@RestController
@RequestMapping("/api")
public class TravelFlightDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TravelFlightDetailsResource.class);

    private static final String ENTITY_NAME = "travelFlightDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelFlightDetailsRepository travelFlightDetailsRepository;

    public TravelFlightDetailsResource(TravelFlightDetailsRepository travelFlightDetailsRepository) {
        this.travelFlightDetailsRepository = travelFlightDetailsRepository;
    }

    /**
     * {@code POST  /travel-flight-details} : Create a new travelFlightDetails.
     *
     * @param travelFlightDetails the travelFlightDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelFlightDetails, or with status {@code 400 (Bad Request)} if the travelFlightDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-flight-details")
    public ResponseEntity<TravelFlightDetails> createTravelFlightDetails(@Valid @RequestBody TravelFlightDetails travelFlightDetails) throws URISyntaxException {
        log.debug("REST request to save TravelFlightDetails : {}", travelFlightDetails);
        if (travelFlightDetails.getId() != null) {
            throw new BadRequestAlertException("A new travelFlightDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelFlightDetails result = travelFlightDetailsRepository.save(travelFlightDetails);
        return ResponseEntity.created(new URI("/api/travel-flight-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-flight-details} : Updates an existing travelFlightDetails.
     *
     * @param travelFlightDetails the travelFlightDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelFlightDetails,
     * or with status {@code 400 (Bad Request)} if the travelFlightDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelFlightDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-flight-details")
    public ResponseEntity<TravelFlightDetails> updateTravelFlightDetails(@Valid @RequestBody TravelFlightDetails travelFlightDetails) throws URISyntaxException {
        log.debug("REST request to update TravelFlightDetails : {}", travelFlightDetails);
        if (travelFlightDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelFlightDetails result = travelFlightDetailsRepository.save(travelFlightDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelFlightDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-flight-details} : get all the travelFlightDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelFlightDetails in body.
     */
    @GetMapping("/travel-flight-details")
    public ResponseEntity<List<TravelFlightDetails>> getAllTravelFlightDetails(Pageable pageable) {
        log.debug("REST request to get a page of TravelFlightDetails");
        Page<TravelFlightDetails> page = travelFlightDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-flight-details/:id} : get the "id" travelFlightDetails.
     *
     * @param id the id of the travelFlightDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelFlightDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-flight-details/{id}")
    public ResponseEntity<TravelFlightDetails> getTravelFlightDetails(@PathVariable Long id) {
        log.debug("REST request to get TravelFlightDetails : {}", id);
        Optional<TravelFlightDetails> travelFlightDetails = travelFlightDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(travelFlightDetails);
    }

    /**
     * {@code DELETE  /travel-flight-details/:id} : delete the "id" travelFlightDetails.
     *
     * @param id the id of the travelFlightDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-flight-details/{id}")
    public ResponseEntity<Void> deleteTravelFlightDetails(@PathVariable Long id) {
        log.debug("REST request to delete TravelFlightDetails : {}", id);
        travelFlightDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
