package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.TdsGroupMaster;
import io.vamani.application.repository.TdsGroupMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
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
 * REST controller for managing TdsGroupMaster.
 */
@RestController
@RequestMapping("/api")
public class TdsGroupMasterResource {

    private final Logger log = LoggerFactory.getLogger(TdsGroupMasterResource.class);

    private static final String ENTITY_NAME = "tdsGroupMaster";

    private final TdsGroupMasterRepository tdsGroupMasterRepository;

    public TdsGroupMasterResource(TdsGroupMasterRepository tdsGroupMasterRepository) {
        this.tdsGroupMasterRepository = tdsGroupMasterRepository;
    }

    /**
     * POST  /tds-group-masters : Create a new tdsGroupMaster.
     *
     * @param tdsGroupMaster the tdsGroupMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tdsGroupMaster, or with status 400 (Bad Request) if the tdsGroupMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tds-group-masters")
    @Timed
    public ResponseEntity<TdsGroupMaster> createTdsGroupMaster(@Valid @RequestBody TdsGroupMaster tdsGroupMaster) throws URISyntaxException {
        log.debug("REST request to save TdsGroupMaster : {}", tdsGroupMaster);
        if (tdsGroupMaster.getId() != null) {
            throw new BadRequestAlertException("A new tdsGroupMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tdsGroupMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        tdsGroupMaster.setCreatedDate(Instant.now());
        TdsGroupMaster result = tdsGroupMasterRepository.save(tdsGroupMaster);
        return ResponseEntity.created(new URI("/api/tds-group-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tds-group-masters : Updates an existing tdsGroupMaster.
     *
     * @param tdsGroupMaster the tdsGroupMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tdsGroupMaster,
     * or with status 400 (Bad Request) if the tdsGroupMaster is not valid,
     * or with status 500 (Internal Server Error) if the tdsGroupMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tds-group-masters")
    @Timed
    public ResponseEntity<TdsGroupMaster> updateTdsGroupMaster(@Valid @RequestBody TdsGroupMaster tdsGroupMaster) throws URISyntaxException {
        log.debug("REST request to update TdsGroupMaster : {}", tdsGroupMaster);
        if (tdsGroupMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        tdsGroupMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        tdsGroupMaster.setLastUpdatedDate(Instant.now());
        TdsGroupMaster result = tdsGroupMasterRepository.save(tdsGroupMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tdsGroupMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tds-group-masters : get all the tdsGroupMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsGroupMasters in body
     */
    @GetMapping("/tds-group-masters")
    @Timed
    public ResponseEntity<List<TdsGroupMaster>> getAllTdsGroupMasters(Pageable pageable) {
        log.debug("REST request to get a page of TdsGroupMasters");
        Page<TdsGroupMaster> page = tdsGroupMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tds-group-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tds-group-masters/:id : get the "id" tdsGroupMaster.
     *
     * @param id the id of the tdsGroupMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsGroupMaster, or with status 404 (Not Found)
     */
    @GetMapping("/tds-group-masters/{id}")
    @Timed
    public ResponseEntity<TdsGroupMaster> getTdsGroupMaster(@PathVariable Long id) {
        log.debug("REST request to get TdsGroupMaster : {}", id);
        Optional<TdsGroupMaster> tdsGroupMaster = tdsGroupMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tdsGroupMaster);
    }

    /**
     * DELETE  /tds-group-masters/:id : delete the "id" tdsGroupMaster.
     *
     * @param id the id of the tdsGroupMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tds-group-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteTdsGroupMaster(@PathVariable Long id) {
        log.debug("REST request to delete TdsGroupMaster : {}", id);

        tdsGroupMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
