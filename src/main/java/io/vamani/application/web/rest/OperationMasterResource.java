package io.vamani.application.web.rest;
import io.vamani.application.domain.OperationMaster;
import io.vamani.application.repository.OperationMasterRepository;
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
 * REST controller for managing OperationMaster.
 */
@RestController
@RequestMapping("/api")
public class OperationMasterResource {

    private final Logger log = LoggerFactory.getLogger(OperationMasterResource.class);

    private static final String ENTITY_NAME = "operationMaster";

    private final OperationMasterRepository operationMasterRepository;

    public OperationMasterResource(OperationMasterRepository operationMasterRepository) {
        this.operationMasterRepository = operationMasterRepository;
    }

    /**
     * POST  /operation-masters : Create a new operationMaster.
     *
     * @param operationMaster the operationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new operationMaster, or with status 400 (Bad Request) if the operationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/operation-masters")
    public ResponseEntity<OperationMaster> createOperationMaster(@Valid @RequestBody OperationMaster operationMaster) throws URISyntaxException {
        log.debug("REST request to save OperationMaster : {}", operationMaster);
        if (operationMaster.getId() != null) {
            throw new BadRequestAlertException("A new operationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        operationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        operationMaster.setCreatedDate(Instant.now());
        OperationMaster result = operationMasterRepository.save(operationMaster);
        return ResponseEntity.created(new URI("/api/operation-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /operation-masters : Updates an existing operationMaster.
     *
     * @param operationMaster the operationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated operationMaster,
     * or with status 400 (Bad Request) if the operationMaster is not valid,
     * or with status 500 (Internal Server Error) if the operationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/operation-masters")
    public ResponseEntity<OperationMaster> updateOperationMaster(@Valid @RequestBody OperationMaster operationMaster) throws URISyntaxException {
        log.debug("REST request to update OperationMaster : {}", operationMaster);
        if (operationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        operationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        operationMaster.setLastUpdatedDate(Instant.now());
        OperationMaster result = operationMasterRepository.save(operationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, operationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /operation-masters : get all the operationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of operationMasters in body
     */
    @GetMapping("/operation-masters")
    public ResponseEntity<List<OperationMaster>> getAllOperationMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of OperationMasters");
        Page<OperationMaster> page = operationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/operation-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /operation-masters/:id : get the "id" operationMaster.
     *
     * @param id the id of the operationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the operationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/operation-masters/{id}")
    public ResponseEntity<OperationMaster> getOperationMaster(@PathVariable Long id) {
        log.debug("REST request to get OperationMaster : {}", id);
        Optional<OperationMaster> operationMaster = operationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(operationMaster);
    }

    /**
     * DELETE  /operation-masters/:id : delete the "id" operationMaster.
     *
     * @param id the id of the operationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/operation-masters/{id}")
    public ResponseEntity<Void> deleteOperationMaster(@PathVariable Long id) {
        log.debug("REST request to delete OperationMaster : {}", id);
        operationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
