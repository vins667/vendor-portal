package io.vamani.application.web.rest;

import io.vamani.application.domain.TravelPassengerDetails;
import io.vamani.application.repository.TravelPassengerDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.TravelPassengerDetails}.
 */
@RestController
@RequestMapping("/api")
public class TravelPassengerDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TravelPassengerDetailsResource.class);

    private static final String ENTITY_NAME = "travelPassengerDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelPassengerDetailsRepository travelPassengerDetailsRepository;

    public TravelPassengerDetailsResource(TravelPassengerDetailsRepository travelPassengerDetailsRepository) {
        this.travelPassengerDetailsRepository = travelPassengerDetailsRepository;
    }

    /**
     * {@code POST  /travel-passenger-details} : Create a new travelPassengerDetails.
     *
     * @param travelPassengerDetails the travelPassengerDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelPassengerDetails, or with status {@code 400 (Bad Request)} if the travelPassengerDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-passenger-details")
    public ResponseEntity<TravelPassengerDetails> createTravelPassengerDetails(@Valid @RequestBody TravelPassengerDetails travelPassengerDetails) throws URISyntaxException {
        log.debug("REST request to save TravelPassengerDetails : {}", travelPassengerDetails);
        if (travelPassengerDetails.getId() != null) {
            throw new BadRequestAlertException("A new travelPassengerDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelPassengerDetails result = travelPassengerDetailsRepository.save(travelPassengerDetails);
        return ResponseEntity.created(new URI("/api/travel-passenger-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-passenger-details} : Updates an existing travelPassengerDetails.
     *
     * @param travelPassengerDetails the travelPassengerDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelPassengerDetails,
     * or with status {@code 400 (Bad Request)} if the travelPassengerDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelPassengerDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-passenger-details")
    public ResponseEntity<TravelPassengerDetails> updateTravelPassengerDetails(@Valid @RequestBody TravelPassengerDetails travelPassengerDetails) throws URISyntaxException {
        log.debug("REST request to update TravelPassengerDetails : {}", travelPassengerDetails);
        if (travelPassengerDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelPassengerDetails result = travelPassengerDetailsRepository.save(travelPassengerDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelPassengerDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-passenger-details} : get all the travelPassengerDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelPassengerDetails in body.
     */
    @GetMapping("/travel-passenger-details")
    public ResponseEntity<List<TravelPassengerDetails>> getAllTravelPassengerDetails(Pageable pageable) {
        log.debug("REST request to get a page of TravelPassengerDetails");
        Page<TravelPassengerDetails> page = travelPassengerDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-passenger-details/:id} : get the "id" travelPassengerDetails.
     *
     * @param id the id of the travelPassengerDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelPassengerDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-passenger-details/{id}")
    public ResponseEntity<TravelPassengerDetails> getTravelPassengerDetails(@PathVariable Long id) {
        log.debug("REST request to get TravelPassengerDetails : {}", id);
        Optional<TravelPassengerDetails> travelPassengerDetails = travelPassengerDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(travelPassengerDetails);
    }

    /**
     * {@code DELETE  /travel-passenger-details/:id} : delete the "id" travelPassengerDetails.
     *
     * @param id the id of the travelPassengerDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-passenger-details/{id}")
    public ResponseEntity<Void> deleteTravelPassengerDetails(@PathVariable Long id) {
        log.debug("REST request to delete TravelPassengerDetails : {}", id);
        travelPassengerDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
