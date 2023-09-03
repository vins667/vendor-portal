package io.vamani.application.web.rest;

import io.vamani.application.domain.VcutOperationIssueMaster;
import io.vamani.application.repository.VcutOperationIssueMasterRepository;
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
 * REST controller for managing {@link VcutOperationIssueMaster}.
 */
@RestController
@RequestMapping("/api")
public class VcutOperationIssueMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutOperationIssueMasterResource.class);

    private static final String ENTITY_NAME = "vcutOperationIssueMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutOperationIssueMasterRepository vcutOperationIssueMasterRepository;

    public VcutOperationIssueMasterResource(VcutOperationIssueMasterRepository vcutOperationIssueMasterRepository) {
        this.vcutOperationIssueMasterRepository = vcutOperationIssueMasterRepository;
    }

    /**
     * {@code POST  /vcut-operation-issue-masters} : Create a new vcutOperationIssueMaster.
     *
     * @param vcutOperationIssueMaster the vcutOperationIssueMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutOperationIssueMaster, or with status {@code 400 (Bad Request)} if the vcutOperationIssueMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-operation-issue-masters")
    public ResponseEntity<VcutOperationIssueMaster> createVcutOperationIssueMaster(@Valid @RequestBody VcutOperationIssueMaster vcutOperationIssueMaster) throws URISyntaxException {
        log.debug("REST request to save VcutOperationIssueMaster : {}", vcutOperationIssueMaster);
        if (vcutOperationIssueMaster.getId() != null) {
            throw new BadRequestAlertException("A new vcutOperationIssueMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VcutOperationIssueMaster result = vcutOperationIssueMasterRepository.save(vcutOperationIssueMaster);
        return ResponseEntity.created(new URI("/api/vcut-operation-issue-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vcut-operation-issue-masters} : Updates an existing vcutOperationIssueMaster.
     *
     * @param vcutOperationIssueMaster the vcutOperationIssueMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutOperationIssueMaster,
     * or with status {@code 400 (Bad Request)} if the vcutOperationIssueMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutOperationIssueMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-operation-issue-masters")
    public ResponseEntity<VcutOperationIssueMaster> updateVcutOperationIssueMaster(@Valid @RequestBody VcutOperationIssueMaster vcutOperationIssueMaster) throws URISyntaxException {
        log.debug("REST request to update VcutOperationIssueMaster : {}", vcutOperationIssueMaster);
        if (vcutOperationIssueMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VcutOperationIssueMaster result = vcutOperationIssueMasterRepository.save(vcutOperationIssueMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutOperationIssueMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vcut-operation-issue-masters} : get all the vcutOperationIssueMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutOperationIssueMasters in body.
     */
    @GetMapping("/vcut-operation-issue-masters")
    public ResponseEntity<List<VcutOperationIssueMaster>> getAllVcutOperationIssueMasters(Pageable pageable) {
        log.debug("REST request to get a page of VcutOperationIssueMasters");
        Page<VcutOperationIssueMaster> page = vcutOperationIssueMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-operation-issue-masters/:id} : get the "id" vcutOperationIssueMaster.
     *
     * @param id the id of the vcutOperationIssueMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutOperationIssueMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-operation-issue-masters/{id}")
    public ResponseEntity<VcutOperationIssueMaster> getVcutOperationIssueMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutOperationIssueMaster : {}", id);
        Optional<VcutOperationIssueMaster> vcutOperationIssueMaster = vcutOperationIssueMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vcutOperationIssueMaster);
    }

    /**
     * {@code DELETE  /vcut-operation-issue-masters/:id} : delete the "id" vcutOperationIssueMaster.
     *
     * @param id the id of the vcutOperationIssueMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-operation-issue-masters/{id}")
    public ResponseEntity<Void> deleteVcutOperationIssueMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutOperationIssueMaster : {}", id);
        vcutOperationIssueMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
