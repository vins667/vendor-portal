package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.vendorportal.domain.CurrencyMaster;
import io.vamani.application.vendorportal.repository.CurrencyMasterRepository;
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
 * REST controller for managing CurrencyMaster.
 */
@RestController
@RequestMapping("/api")
public class CurrencyMasterResource {

    private final Logger log = LoggerFactory.getLogger(CurrencyMasterResource.class);

    private static final String ENTITY_NAME = "currencyMaster";

    private final CurrencyMasterRepository currencyMasterRepository;

    public CurrencyMasterResource(CurrencyMasterRepository currencyMasterRepository) {
        this.currencyMasterRepository = currencyMasterRepository;
    }

    /**
     * POST  /currency-masters : Create a new currencyMaster.
     *
     * @param currencyMaster the currencyMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new currencyMaster, or with status 400 (Bad Request) if the currencyMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/currency-masters")
    public ResponseEntity<CurrencyMaster> createCurrencyMaster(@Valid @RequestBody CurrencyMaster currencyMaster) throws URISyntaxException {
        log.debug("REST request to save CurrencyMaster : {}", currencyMaster);
        if (currencyMaster.getId() != null) {
            throw new BadRequestAlertException("A new currencyMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        currencyMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        currencyMaster.setCreatedDate(Instant.now());
        CurrencyMaster result = currencyMasterRepository.save(currencyMaster);
        return ResponseEntity.created(new URI("/api/currency-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /currency-masters : Updates an existing currencyMaster.
     *
     * @param currencyMaster the currencyMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated currencyMaster,
     * or with status 400 (Bad Request) if the currencyMaster is not valid,
     * or with status 500 (Internal Server Error) if the currencyMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/currency-masters")
    public ResponseEntity<CurrencyMaster> updateCurrencyMaster(@Valid @RequestBody CurrencyMaster currencyMaster) throws URISyntaxException {
        log.debug("REST request to update CurrencyMaster : {}", currencyMaster);
        if (currencyMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        currencyMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        currencyMaster.setCreatedDate(Instant.now());
        CurrencyMaster result = currencyMasterRepository.save(currencyMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, currencyMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /currency-masters : get all the currencyMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of currencyMasters in body
     */
    @GetMapping("/currency-masters")
    public ResponseEntity<List<CurrencyMaster>> getAllCurrencyMasters(Pageable pageable) {
        log.debug("REST request to get a page of CurrencyMasters");
        Page<CurrencyMaster> page = currencyMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/currency-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /currency-masters/:id : get the "id" currencyMaster.
     *
     * @param id the id of the currencyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the currencyMaster, or with status 404 (Not Found)
     */
    @GetMapping("/currency-masters/{id}")
    public ResponseEntity<CurrencyMaster> getCurrencyMaster(@PathVariable Long id) {
        log.debug("REST request to get CurrencyMaster : {}", id);
        Optional<CurrencyMaster> currencyMaster = currencyMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(currencyMaster);
    }

    /**
     * DELETE  /currency-masters/:id : delete the "id" currencyMaster.
     *
     * @param id the id of the currencyMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/currency-masters/{id}")
    public ResponseEntity<Void> deleteCurrencyMaster(@PathVariable Long id) {
        log.debug("REST request to delete CurrencyMaster : {}", id);
        currencyMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
