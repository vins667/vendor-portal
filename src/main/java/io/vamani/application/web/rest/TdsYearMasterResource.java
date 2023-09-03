package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.TdsYearMaster;
import io.vamani.application.repository.TdsYearMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TdsYearMaster.
 */
@RestController
@RequestMapping("/api")
public class TdsYearMasterResource {

    private final Logger log = LoggerFactory.getLogger(TdsYearMasterResource.class);

    private static final String ENTITY_NAME = "tdsYearMaster";

    private final TdsYearMasterRepository tdsYearMasterRepository;

    public TdsYearMasterResource(TdsYearMasterRepository tdsYearMasterRepository) {
        this.tdsYearMasterRepository = tdsYearMasterRepository;
    }

    /**
     * POST  /tds-year-masters : Create a new tdsYearMaster.
     *
     * @param tdsYearMaster the tdsYearMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tdsYearMaster, or with status 400 (Bad Request) if the tdsYearMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tds-year-masters")
    @Timed
    public ResponseEntity<TdsYearMaster> createTdsYearMaster(@Valid @RequestBody TdsYearMaster tdsYearMaster) throws URISyntaxException {
        log.debug("REST request to save TdsYearMaster : {}", tdsYearMaster);
        if (tdsYearMaster.getId() != null) {
            throw new BadRequestAlertException("A new tdsYearMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TdsYearMaster result = tdsYearMasterRepository.save(tdsYearMaster);
        return ResponseEntity.created(new URI("/api/tds-year-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tds-year-masters : Updates an existing tdsYearMaster.
     *
     * @param tdsYearMaster the tdsYearMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tdsYearMaster,
     * or with status 400 (Bad Request) if the tdsYearMaster is not valid,
     * or with status 500 (Internal Server Error) if the tdsYearMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tds-year-masters")
    @Timed
    public ResponseEntity<TdsYearMaster> updateTdsYearMaster(@Valid @RequestBody TdsYearMaster tdsYearMaster) throws URISyntaxException {
        log.debug("REST request to update TdsYearMaster : {}", tdsYearMaster);
        if (tdsYearMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TdsYearMaster result = tdsYearMasterRepository.save(tdsYearMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tdsYearMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tds-year-masters : get all the tdsYearMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsYearMasters in body
     */
    @GetMapping("/tds-year-masters")
    @Timed
    public ResponseEntity<List<TdsYearMaster>> getAllTdsYearMasters(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("REST request to get a page of TdsYearMasters");
        Page<TdsYearMaster> page = tdsYearMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tds-year-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tds-year-masters/:id : get the "id" tdsYearMaster.
     *
     * @param id the id of the tdsYearMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsYearMaster, or with status 404 (Not Found)
     */
    @GetMapping("/tds-year-masters/{id}")
    @Timed
    public ResponseEntity<TdsYearMaster> getTdsYearMaster(@PathVariable Long id) {
        log.debug("REST request to get TdsYearMaster : {}", id);
        Optional<TdsYearMaster> tdsYearMaster = tdsYearMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tdsYearMaster);
    }

    /**
     * GET  /tds-year-masters/:id : get the "id" tdsYearMaster.
     *
     * @param id the id of the tdsYearMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsYearMaster, or with status 404 (Not Found)
     */
    @GetMapping("/tds-year-masters-year/{year}")
    @Timed
    public ResponseEntity<TdsYearMaster> getTdsYearMasterByYear(@PathVariable String year) {
        log.debug("REST request to get TdsYearMaster : {}", year);
        TdsYearMaster tdsYearMaster = tdsYearMasterRepository.findByCode(year);
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsYearMaster));
    }

    /**
     * GET  /tds-year-masters/:id : get the "id" tdsYearMaster.
     *
     * @param id the id of the tdsYearMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsYearMaster, or with status 404 (Not Found)
     */
    @GetMapping("/tds-year-masters-active")
    @Timed
    public ResponseEntity<TdsYearMaster> getTdsYearMasterActive() {
        List<TdsYearMaster> tdsYearMasters = tdsYearMasterRepository.findByActive();
        TdsYearMaster tdsYearMaster = null;
        if (tdsYearMasters != null && tdsYearMasters.size() > 0) {
            tdsYearMaster = tdsYearMasters.get(0);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsYearMaster));
    }

    /**
     * DELETE  /tds-year-masters/:id : delete the "id" tdsYearMaster.
     *
     * @param id the id of the tdsYearMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tds-year-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteTdsYearMaster(@PathVariable Long id) {
        log.debug("REST request to delete TdsYearMaster : {}", id);

        tdsYearMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
