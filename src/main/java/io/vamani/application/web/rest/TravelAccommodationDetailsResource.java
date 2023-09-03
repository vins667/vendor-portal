package io.vamani.application.web.rest;

import io.vamani.application.domain.TravelAccommodationDetails;
import io.vamani.application.repository.TravelAccommodationDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.TravelAccommodationDetails}.
 */
@RestController
@RequestMapping("/api")
public class TravelAccommodationDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TravelAccommodationDetailsResource.class);

    private static final String ENTITY_NAME = "travelAccommodationDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelAccommodationDetailsRepository travelAccommodationDetailsRepository;

    public TravelAccommodationDetailsResource(TravelAccommodationDetailsRepository travelAccommodationDetailsRepository) {
        this.travelAccommodationDetailsRepository = travelAccommodationDetailsRepository;
    }

    /**
     * {@code POST  /travel-accommodation-details} : Create a new travelAccommodationDetails.
     *
     * @param travelAccommodationDetails the travelAccommodationDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelAccommodationDetails, or with status {@code 400 (Bad Request)} if the travelAccommodationDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-accommodation-details")
    public ResponseEntity<TravelAccommodationDetails> createTravelAccommodationDetails(@Valid @RequestBody TravelAccommodationDetails travelAccommodationDetails) throws URISyntaxException {
        log.debug("REST request to save TravelAccommodationDetails : {}", travelAccommodationDetails);
        if (travelAccommodationDetails.getId() != null) {
            throw new BadRequestAlertException("A new travelAccommodationDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelAccommodationDetails result = travelAccommodationDetailsRepository.save(travelAccommodationDetails);
        return ResponseEntity.created(new URI("/api/travel-accommodation-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-accommodation-details} : Updates an existing travelAccommodationDetails.
     *
     * @param travelAccommodationDetails the travelAccommodationDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelAccommodationDetails,
     * or with status {@code 400 (Bad Request)} if the travelAccommodationDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelAccommodationDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-accommodation-details")
    public ResponseEntity<TravelAccommodationDetails> updateTravelAccommodationDetails(@Valid @RequestBody TravelAccommodationDetails travelAccommodationDetails) throws URISyntaxException {
        log.debug("REST request to update TravelAccommodationDetails : {}", travelAccommodationDetails);
        if (travelAccommodationDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelAccommodationDetails result = travelAccommodationDetailsRepository.save(travelAccommodationDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelAccommodationDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-accommodation-details} : get all the travelAccommodationDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelAccommodationDetails in body.
     */
    @GetMapping("/travel-accommodation-details")
    public ResponseEntity<List<TravelAccommodationDetails>> getAllTravelAccommodationDetails(Pageable pageable) {
        log.debug("REST request to get a page of TravelAccommodationDetails");
        Page<TravelAccommodationDetails> page = travelAccommodationDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-accommodation-details/:id} : get the "id" travelAccommodationDetails.
     *
     * @param id the id of the travelAccommodationDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelAccommodationDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-accommodation-details/{id}")
    public ResponseEntity<TravelAccommodationDetails> getTravelAccommodationDetails(@PathVariable Long id) {
        log.debug("REST request to get TravelAccommodationDetails : {}", id);
        Optional<TravelAccommodationDetails> travelAccommodationDetails = travelAccommodationDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(travelAccommodationDetails);
    }

    /**
     * {@code DELETE  /travel-accommodation-details/:id} : delete the "id" travelAccommodationDetails.
     *
     * @param id the id of the travelAccommodationDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-accommodation-details/{id}")
    public ResponseEntity<Void> deleteTravelAccommodationDetails(@PathVariable Long id) {
        log.debug("REST request to delete TravelAccommodationDetails : {}", id);
        travelAccommodationDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
