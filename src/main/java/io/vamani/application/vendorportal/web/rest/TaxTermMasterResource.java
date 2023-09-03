package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.vendorportal.domain.TaxTermMaster;
import io.vamani.application.vendorportal.repository.TaxTermMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
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
 * REST controller for managing TaxTermMaster.
 */
@RestController
@RequestMapping("/api")
public class TaxTermMasterResource {

    private final Logger log = LoggerFactory.getLogger(TaxTermMasterResource.class);

    private static final String ENTITY_NAME = "taxTermMaster";

    private final TaxTermMasterRepository taxTermMasterRepository;

    public TaxTermMasterResource(TaxTermMasterRepository taxTermMasterRepository) {
        this.taxTermMasterRepository = taxTermMasterRepository;
    }

    /**
     * POST  /tax-term-masters : Create a new taxTermMaster.
     *
     * @param taxTermMaster the taxTermMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new taxTermMaster, or with status 400 (Bad Request) if the taxTermMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tax-term-masters")
    public ResponseEntity<TaxTermMaster> createTaxTermMaster(@Valid @RequestBody TaxTermMaster taxTermMaster) throws URISyntaxException {
        log.debug("REST request to save TaxTermMaster : {}", taxTermMaster);
        if (taxTermMaster.getId() != null) {
            throw new BadRequestAlertException("A new taxTermMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taxTermMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        taxTermMaster.setCreatedDate(Instant.now());
        TaxTermMaster result = taxTermMasterRepository.save(taxTermMaster);
        return ResponseEntity.created(new URI("/api/tax-term-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tax-term-masters : Updates an existing taxTermMaster.
     *
     * @param taxTermMaster the taxTermMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated taxTermMaster,
     * or with status 400 (Bad Request) if the taxTermMaster is not valid,
     * or with status 500 (Internal Server Error) if the taxTermMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tax-term-masters")
    public ResponseEntity<TaxTermMaster> updateTaxTermMaster(@Valid @RequestBody TaxTermMaster taxTermMaster) throws URISyntaxException {
        log.debug("REST request to update TaxTermMaster : {}", taxTermMaster);
        if (taxTermMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        taxTermMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        taxTermMaster.setCreatedDate(Instant.now());
        TaxTermMaster result = taxTermMasterRepository.save(taxTermMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taxTermMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tax-term-masters : get all the taxTermMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of taxTermMasters in body
     */
    @GetMapping("/tax-term-masters")
    public ResponseEntity<List<TaxTermMaster>> getAllTaxTermMasters(Pageable pageable) {
        log.debug("REST request to get a page of TaxTermMasters");
        Page<TaxTermMaster> page = taxTermMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tax-term-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tax-term-masters/:id : get the "id" taxTermMaster.
     *
     * @param id the id of the taxTermMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the taxTermMaster, or with status 404 (Not Found)
     */
    @GetMapping("/tax-term-masters/{id}")
    public ResponseEntity<TaxTermMaster> getTaxTermMaster(@PathVariable Long id) {
        log.debug("REST request to get TaxTermMaster : {}", id);
        Optional<TaxTermMaster> taxTermMaster = taxTermMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(taxTermMaster);
    }

    /**
     * DELETE  /tax-term-masters/:id : delete the "id" taxTermMaster.
     *
     * @param id the id of the taxTermMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tax-term-masters/{id}")
    public ResponseEntity<Void> deleteTaxTermMaster(@PathVariable Long id) {
        log.debug("REST request to delete TaxTermMaster : {}", id);
        taxTermMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
