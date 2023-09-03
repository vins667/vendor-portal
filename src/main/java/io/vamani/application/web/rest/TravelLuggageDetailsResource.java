package io.vamani.application.web.rest;

import io.vamani.application.domain.TravelLuggageDetails;
import io.vamani.application.repository.TravelLuggageDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.TravelLuggageDetails}.
 */
@RestController
@RequestMapping("/api")
public class TravelLuggageDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TravelLuggageDetailsResource.class);

    private static final String ENTITY_NAME = "travelLuggageDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelLuggageDetailsRepository travelLuggageDetailsRepository;

    public TravelLuggageDetailsResource(TravelLuggageDetailsRepository travelLuggageDetailsRepository) {
        this.travelLuggageDetailsRepository = travelLuggageDetailsRepository;
    }

    /**
     * {@code POST  /travel-luggage-details} : Create a new travelLuggageDetails.
     *
     * @param travelLuggageDetails the travelLuggageDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelLuggageDetails, or with status {@code 400 (Bad Request)} if the travelLuggageDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-luggage-details")
    public ResponseEntity<TravelLuggageDetails> createTravelLuggageDetails(@Valid @RequestBody TravelLuggageDetails travelLuggageDetails) throws URISyntaxException {
        log.debug("REST request to save TravelLuggageDetails : {}", travelLuggageDetails);
        if (travelLuggageDetails.getId() != null) {
            throw new BadRequestAlertException("A new travelLuggageDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelLuggageDetails result = travelLuggageDetailsRepository.save(travelLuggageDetails);
        return ResponseEntity.created(new URI("/api/travel-luggage-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-luggage-details} : Updates an existing travelLuggageDetails.
     *
     * @param travelLuggageDetails the travelLuggageDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelLuggageDetails,
     * or with status {@code 400 (Bad Request)} if the travelLuggageDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelLuggageDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-luggage-details")
    public ResponseEntity<TravelLuggageDetails> updateTravelLuggageDetails(@Valid @RequestBody TravelLuggageDetails travelLuggageDetails) throws URISyntaxException {
        log.debug("REST request to update TravelLuggageDetails : {}", travelLuggageDetails);
        if (travelLuggageDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelLuggageDetails result = travelLuggageDetailsRepository.save(travelLuggageDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelLuggageDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-luggage-details} : get all the travelLuggageDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelLuggageDetails in body.
     */
    @GetMapping("/travel-luggage-details")
    public ResponseEntity<List<TravelLuggageDetails>> getAllTravelLuggageDetails(Pageable pageable) {
        log.debug("REST request to get a page of TravelLuggageDetails");
        Page<TravelLuggageDetails> page = travelLuggageDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-luggage-details/:id} : get the "id" travelLuggageDetails.
     *
     * @param id the id of the travelLuggageDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelLuggageDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-luggage-details/{id}")
    public ResponseEntity<TravelLuggageDetails> getTravelLuggageDetails(@PathVariable Long id) {
        log.debug("REST request to get TravelLuggageDetails : {}", id);
        Optional<TravelLuggageDetails> travelLuggageDetails = travelLuggageDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(travelLuggageDetails);
    }

    /**
     * {@code DELETE  /travel-luggage-details/:id} : delete the "id" travelLuggageDetails.
     *
     * @param id the id of the travelLuggageDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-luggage-details/{id}")
    public ResponseEntity<Void> deleteTravelLuggageDetails(@PathVariable Long id) {
        log.debug("REST request to delete TravelLuggageDetails : {}", id);
        travelLuggageDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
