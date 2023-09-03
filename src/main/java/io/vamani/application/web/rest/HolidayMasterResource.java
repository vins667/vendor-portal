package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.HolidayMaster;
import io.vamani.application.repository.HolidayMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing HolidayMaster.
 */
@RestController
@RequestMapping("/api")
public class HolidayMasterResource {

    private final Logger log = LoggerFactory.getLogger(HolidayMasterResource.class);

    private static final String ENTITY_NAME = "holidayMaster";

    private final HolidayMasterRepository holidayMasterRepository;

    public HolidayMasterResource(HolidayMasterRepository holidayMasterRepository) {
        this.holidayMasterRepository = holidayMasterRepository;
    }

    /**
     * POST  /holiday-masters : Create a new holidayMaster.
     *
     * @param holidayMaster the holidayMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new holidayMaster, or with status 400 (Bad Request) if the holidayMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/holiday-masters")
    @Timed
    public ResponseEntity<HolidayMaster> createHolidayMaster(@Valid @RequestBody HolidayMaster holidayMaster) throws URISyntaxException {
        log.debug("REST request to save HolidayMaster : {}", holidayMaster);
        if (holidayMaster.getId() != null) {
            throw new BadRequestAlertException("A new holidayMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HolidayMaster result = holidayMasterRepository.save(holidayMaster);
        return ResponseEntity.created(new URI("/api/holiday-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /holiday-masters : Updates an existing holidayMaster.
     *
     * @param holidayMaster the holidayMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated holidayMaster,
     * or with status 400 (Bad Request) if the holidayMaster is not valid,
     * or with status 500 (Internal Server Error) if the holidayMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/holiday-masters")
    @Timed
    public ResponseEntity<HolidayMaster> updateHolidayMaster(@Valid @RequestBody HolidayMaster holidayMaster) throws URISyntaxException {
        log.debug("REST request to update HolidayMaster : {}", holidayMaster);
        if (holidayMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HolidayMaster result = holidayMasterRepository.save(holidayMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, holidayMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /holiday-masters : get all the holidayMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of holidayMasters in body
     */
    @GetMapping("/holiday-masters")
    @Timed
    public List<HolidayMaster> getAllHolidayMasters() {
        log.debug("REST request to get all HolidayMasters");
        return holidayMasterRepository.findAll();
    }

    /**
     * GET  /holiday-masters/:id : get the "id" holidayMaster.
     *
     * @param id the id of the holidayMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the holidayMaster, or with status 404 (Not Found)
     */
    @GetMapping("/holiday-masters/{id}")
    @Timed
    public ResponseEntity<HolidayMaster> getHolidayMaster(@PathVariable Long id) {
        log.debug("REST request to get HolidayMaster : {}", id);
        Optional<HolidayMaster> holidayMaster = holidayMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(holidayMaster);
    }

    /**
     * DELETE  /holiday-masters/:id : delete the "id" holidayMaster.
     *
     * @param id the id of the holidayMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/holiday-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteHolidayMaster(@PathVariable Long id) {
        log.debug("REST request to delete HolidayMaster : {}", id);

        holidayMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
