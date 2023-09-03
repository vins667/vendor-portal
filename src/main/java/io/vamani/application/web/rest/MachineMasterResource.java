package io.vamani.application.web.rest;
import io.vamani.application.domain.MachineMaster;
import io.vamani.application.repository.MachineMasterRepository;
import io.vamani.application.security.SecurityUtils;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MachineMaster.
 */
@RestController
@RequestMapping("/api")
public class MachineMasterResource {

    private final Logger log = LoggerFactory.getLogger(MachineMasterResource.class);

    private static final String ENTITY_NAME = "machineMaster";

    private final MachineMasterRepository machineMasterRepository;

    public MachineMasterResource(MachineMasterRepository machineMasterRepository) {
        this.machineMasterRepository = machineMasterRepository;
    }

    /**
     * POST  /machine-masters : Create a new machineMaster.
     *
     * @param machineMaster the machineMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new machineMaster, or with status 400 (Bad Request) if the machineMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/machine-masters")
    public ResponseEntity<MachineMaster> createMachineMaster(@Valid @RequestBody MachineMaster machineMaster) throws URISyntaxException {
        log.debug("REST request to save MachineMaster : {}", machineMaster);
        if (machineMaster.getId() != null) {
            throw new BadRequestAlertException("A new machineMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        machineMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        machineMaster.setCreatedDate(Instant.now());
        MachineMaster result = machineMasterRepository.save(machineMaster);
        return ResponseEntity.created(new URI("/api/machine-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /machine-masters : Updates an existing machineMaster.
     *
     * @param machineMaster the machineMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated machineMaster,
     * or with status 400 (Bad Request) if the machineMaster is not valid,
     * or with status 500 (Internal Server Error) if the machineMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/machine-masters")
    public ResponseEntity<MachineMaster> updateMachineMaster(@Valid @RequestBody MachineMaster machineMaster) throws URISyntaxException {
        log.debug("REST request to update MachineMaster : {}", machineMaster);
        if (machineMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        machineMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        machineMaster.setLastUpdatedDate(Instant.now());
        MachineMaster result = machineMasterRepository.save(machineMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, machineMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /machine-masters : get all the machineMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of machineMasters in body
     */
    @GetMapping("/machine-masters")
    public ResponseEntity<List<MachineMaster>> getAllMachineMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of MachineMasters");
        Page<MachineMaster> page = machineMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/machine-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /machine-masters/:id : get the "id" machineMaster.
     *
     * @param id the id of the machineMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the machineMaster, or with status 404 (Not Found)
     */
    @GetMapping("/machine-masters/{id}")
    public ResponseEntity<MachineMaster> getMachineMaster(@PathVariable Long id) {
        log.debug("REST request to get MachineMaster : {}", id);
        Optional<MachineMaster> machineMaster = machineMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(machineMaster);
    }

    /**
     * DELETE  /machine-masters/:id : delete the "id" machineMaster.
     *
     * @param id the id of the machineMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/machine-masters/{id}")
    public ResponseEntity<Void> deleteMachineMaster(@PathVariable Long id) {
        log.debug("REST request to delete MachineMaster : {}", id);
        machineMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
