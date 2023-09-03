package io.vamani.application.web.rest;

import io.vamani.application.domain.TravelCurrencyMaster;
import io.vamani.application.repository.TravelCurrencyMasterRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.TravelCurrencyMaster}.
 */
@RestController
@RequestMapping("/api")
public class TravelCurrencyMasterResource {

    private final Logger log = LoggerFactory.getLogger(TravelCurrencyMasterResource.class);

    private static final String ENTITY_NAME = "travelCurrencyMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TravelCurrencyMasterRepository travelCurrencyMasterRepository;

    public TravelCurrencyMasterResource(TravelCurrencyMasterRepository travelCurrencyMasterRepository) {
        this.travelCurrencyMasterRepository = travelCurrencyMasterRepository;
    }

    /**
     * {@code POST  /travel-currency-masters} : Create a new travelCurrencyMaster.
     *
     * @param travelCurrencyMaster the travelCurrencyMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelCurrencyMaster, or with status {@code 400 (Bad Request)} if the travelCurrencyMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/travel-currency-masters")
    public ResponseEntity<TravelCurrencyMaster> createTravelCurrencyMaster(@Valid @RequestBody TravelCurrencyMaster travelCurrencyMaster) throws URISyntaxException {
        log.debug("REST request to save TravelCurrencyMaster : {}", travelCurrencyMaster);
        if (travelCurrencyMaster.getId() != null) {
            throw new BadRequestAlertException("A new travelCurrencyMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TravelCurrencyMaster result = travelCurrencyMasterRepository.save(travelCurrencyMaster);
        return ResponseEntity.created(new URI("/api/travel-currency-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /travel-currency-masters} : Updates an existing travelCurrencyMaster.
     *
     * @param travelCurrencyMaster the travelCurrencyMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelCurrencyMaster,
     * or with status {@code 400 (Bad Request)} if the travelCurrencyMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelCurrencyMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-currency-masters")
    public ResponseEntity<TravelCurrencyMaster> updateTravelCurrencyMaster(@Valid @RequestBody TravelCurrencyMaster travelCurrencyMaster) throws URISyntaxException {
        log.debug("REST request to update TravelCurrencyMaster : {}", travelCurrencyMaster);
        if (travelCurrencyMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelCurrencyMaster result = travelCurrencyMasterRepository.save(travelCurrencyMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelCurrencyMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-currency-masters} : get all the travelCurrencyMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelCurrencyMasters in body.
     */
    @GetMapping("/travel-currency-masters")
    public ResponseEntity<List<TravelCurrencyMaster>> getAllTravelCurrencyMasters(Pageable pageable) {
        log.debug("REST request to get a page of TravelCurrencyMasters");
        Page<TravelCurrencyMaster> page = travelCurrencyMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /travel-currency-masters/:id} : get the "id" travelCurrencyMaster.
     *
     * @param id the id of the travelCurrencyMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelCurrencyMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-currency-masters/{id}")
    public ResponseEntity<TravelCurrencyMaster> getTravelCurrencyMaster(@PathVariable Long id) {
        log.debug("REST request to get TravelCurrencyMaster : {}", id);
        Optional<TravelCurrencyMaster> travelCurrencyMaster = travelCurrencyMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(travelCurrencyMaster);
    }

    /**
     * {@code DELETE  /travel-currency-masters/:id} : delete the "id" travelCurrencyMaster.
     *
     * @param id the id of the travelCurrencyMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-currency-masters/{id}")
    public ResponseEntity<Void> deleteTravelCurrencyMaster(@PathVariable Long id) {
        log.debug("REST request to delete TravelCurrencyMaster : {}", id);
        travelCurrencyMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
