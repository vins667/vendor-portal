package io.vamani.application.web.rest;

import io.vamani.application.domain.VcutOperationRejectMaster;
import io.vamani.application.repository.VcutOperationRejectMasterRepository;
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
 * REST controller for managing {@link VcutOperationRejectMaster}.
 */
@RestController
@RequestMapping("/api")
public class VcutOperationRejectMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutOperationRejectMasterResource.class);

    private static final String ENTITY_NAME = "vcutOperationRejectMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutOperationRejectMasterRepository vcutOperationRejectMasterRepository;

    public VcutOperationRejectMasterResource(VcutOperationRejectMasterRepository vcutOperationRejectMasterRepository) {
        this.vcutOperationRejectMasterRepository = vcutOperationRejectMasterRepository;
    }

    /**
     * {@code POST  /vcut-operation-reject-masters} : Create a new vcutOperationRejectMaster.
     *
     * @param vcutOperationRejectMaster the vcutOperationRejectMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutOperationRejectMaster, or with status {@code 400 (Bad Request)} if the vcutOperationRejectMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-operation-reject-masters")
    public ResponseEntity<VcutOperationRejectMaster> createVcutOperationRejectMaster(@Valid @RequestBody VcutOperationRejectMaster vcutOperationRejectMaster) throws URISyntaxException {
        log.debug("REST request to save VcutOperationRejectMaster : {}", vcutOperationRejectMaster);
        if (vcutOperationRejectMaster.getId() != null) {
            throw new BadRequestAlertException("A new vcutOperationRejectMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VcutOperationRejectMaster result = vcutOperationRejectMasterRepository.save(vcutOperationRejectMaster);
        return ResponseEntity.created(new URI("/api/vcut-operation-reject-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vcut-operation-reject-masters} : Updates an existing vcutOperationRejectMaster.
     *
     * @param vcutOperationRejectMaster the vcutOperationRejectMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutOperationRejectMaster,
     * or with status {@code 400 (Bad Request)} if the vcutOperationRejectMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutOperationRejectMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-operation-reject-masters")
    public ResponseEntity<VcutOperationRejectMaster> updateVcutOperationRejectMaster(@Valid @RequestBody VcutOperationRejectMaster vcutOperationRejectMaster) throws URISyntaxException {
        log.debug("REST request to update VcutOperationRejectMaster : {}", vcutOperationRejectMaster);
        if (vcutOperationRejectMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VcutOperationRejectMaster result = vcutOperationRejectMasterRepository.save(vcutOperationRejectMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutOperationRejectMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vcut-operation-reject-masters} : get all the vcutOperationRejectMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutOperationRejectMasters in body.
     */
    @GetMapping("/vcut-operation-reject-masters")
    public ResponseEntity<List<VcutOperationRejectMaster>> getAllVcutOperationRejectMasters(Pageable pageable) {
        log.debug("REST request to get a page of VcutOperationRejectMasters");
        Page<VcutOperationRejectMaster> page = vcutOperationRejectMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-operation-reject-masters/:id} : get the "id" vcutOperationRejectMaster.
     *
     * @param id the id of the vcutOperationRejectMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutOperationRejectMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-operation-reject-masters/{id}")
    public ResponseEntity<VcutOperationRejectMaster> getVcutOperationRejectMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutOperationRejectMaster : {}", id);
        Optional<VcutOperationRejectMaster> vcutOperationRejectMaster = vcutOperationRejectMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vcutOperationRejectMaster);
    }

    /**
     * {@code DELETE  /vcut-operation-reject-masters/:id} : delete the "id" vcutOperationRejectMaster.
     *
     * @param id the id of the vcutOperationRejectMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-operation-reject-masters/{id}")
    public ResponseEntity<Void> deleteVcutOperationRejectMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutOperationRejectMaster : {}", id);
        vcutOperationRejectMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
