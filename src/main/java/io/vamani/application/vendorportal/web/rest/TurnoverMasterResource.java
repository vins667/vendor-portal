package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.TurnoverMaster;
import io.vamani.application.vendorportal.repository.TurnoverMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
 * REST controller for managing TurnoverMaster.
 */
@RestController
@RequestMapping("/api")
public class TurnoverMasterResource {

    private final Logger log = LoggerFactory.getLogger(TurnoverMasterResource.class);

    private static final String ENTITY_NAME = "turnoverMaster";

    private final TurnoverMasterRepository turnoverMasterRepository;

    public TurnoverMasterResource(TurnoverMasterRepository turnoverMasterRepository) {
        this.turnoverMasterRepository = turnoverMasterRepository;
    }

    /**
     * POST  /turnover-masters : Create a new turnoverMaster.
     *
     * @param turnoverMaster the turnoverMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new turnoverMaster, or with status 400 (Bad Request) if the turnoverMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/turnover-masters")
    @Timed
    public ResponseEntity<TurnoverMaster> createTurnoverMaster(@Valid @RequestBody TurnoverMaster turnoverMaster) throws URISyntaxException {
        log.debug("REST request to save TurnoverMaster : {}", turnoverMaster);
        if (turnoverMaster.getId() != null) {
            throw new BadRequestAlertException("A new turnoverMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TurnoverMaster result = turnoverMasterRepository.save(turnoverMaster);
        return ResponseEntity.created(new URI("/api/turnover-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /turnover-masters : Updates an existing turnoverMaster.
     *
     * @param turnoverMaster the turnoverMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated turnoverMaster,
     * or with status 400 (Bad Request) if the turnoverMaster is not valid,
     * or with status 500 (Internal Server Error) if the turnoverMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/turnover-masters")
    @Timed
    public ResponseEntity<TurnoverMaster> updateTurnoverMaster(@Valid @RequestBody TurnoverMaster turnoverMaster) throws URISyntaxException {
        log.debug("REST request to update TurnoverMaster : {}", turnoverMaster);
        if (turnoverMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TurnoverMaster result = turnoverMasterRepository.save(turnoverMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, turnoverMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /turnover-masters : get all the turnoverMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of turnoverMasters in body
     */
    @GetMapping("/turnover-masters")
    @Timed
    public ResponseEntity<List<TurnoverMaster>> getAllTurnoverMasters(@PageableDefault(sort = { "id" }, value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of TurnoverMasters");
        Page<TurnoverMaster> page = turnoverMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/turnover-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /turnover-masters/:id : get the "id" turnoverMaster.
     *
     * @param id the id of the turnoverMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the turnoverMaster, or with status 404 (Not Found)
     */
    @GetMapping("/turnover-masters/{id}")
    @Timed
    public ResponseEntity<TurnoverMaster> getTurnoverMaster(@PathVariable Long id) {
        log.debug("REST request to get TurnoverMaster : {}", id);
        Optional<TurnoverMaster> turnoverMaster = turnoverMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(turnoverMaster);
    }

    /**
     * DELETE  /turnover-masters/:id : delete the "id" turnoverMaster.
     *
     * @param id the id of the turnoverMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/turnover-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteTurnoverMaster(@PathVariable Long id) {
        log.debug("REST request to delete TurnoverMaster : {}", id);

        turnoverMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
