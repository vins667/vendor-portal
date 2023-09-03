package io.vamani.application.web.rest;

import io.vamani.application.domain.VcutUserDeviceMaster;
import io.vamani.application.repository.VcutUserDeviceMasterRepository;
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
 * REST controller for managing {@link VcutUserDeviceMaster}.
 */
@RestController
@RequestMapping("/api")
public class VcutUserDeviceMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutUserDeviceMasterResource.class);

    private static final String ENTITY_NAME = "vcutUserDeviceMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutUserDeviceMasterRepository vcutUserDeviceMasterRepository;

    public VcutUserDeviceMasterResource(VcutUserDeviceMasterRepository vcutUserDeviceMasterRepository) {
        this.vcutUserDeviceMasterRepository = vcutUserDeviceMasterRepository;
    }

    /**
     * {@code POST  /vcut-user-device-masters} : Create a new vcutUserDeviceMaster.
     *
     * @param vcutUserDeviceMaster the vcutUserDeviceMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutUserDeviceMaster, or with status {@code 400 (Bad Request)} if the vcutUserDeviceMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-user-device-masters")
    public ResponseEntity<VcutUserDeviceMaster> createVcutUserDeviceMaster(@Valid @RequestBody VcutUserDeviceMaster vcutUserDeviceMaster) throws URISyntaxException {
        log.debug("REST request to save VcutUserDeviceMaster : {}", vcutUserDeviceMaster);
        if (vcutUserDeviceMaster.getId() != null) {
            throw new BadRequestAlertException("A new vcutUserDeviceMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VcutUserDeviceMaster result = vcutUserDeviceMasterRepository.save(vcutUserDeviceMaster);
        return ResponseEntity.created(new URI("/api/vcut-user-device-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vcut-user-device-masters} : Updates an existing vcutUserDeviceMaster.
     *
     * @param vcutUserDeviceMaster the vcutUserDeviceMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutUserDeviceMaster,
     * or with status {@code 400 (Bad Request)} if the vcutUserDeviceMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutUserDeviceMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-user-device-masters")
    public ResponseEntity<VcutUserDeviceMaster> updateVcutUserDeviceMaster(@Valid @RequestBody VcutUserDeviceMaster vcutUserDeviceMaster) throws URISyntaxException {
        log.debug("REST request to update VcutUserDeviceMaster : {}", vcutUserDeviceMaster);
        if (vcutUserDeviceMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VcutUserDeviceMaster result = vcutUserDeviceMasterRepository.save(vcutUserDeviceMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutUserDeviceMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vcut-user-device-masters} : get all the vcutUserDeviceMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutUserDeviceMasters in body.
     */
    @GetMapping("/vcut-user-device-masters")
    public ResponseEntity<List<VcutUserDeviceMaster>> getAllVcutUserDeviceMasters(Pageable pageable) {
        log.debug("REST request to get a page of VcutUserDeviceMasters");
        Page<VcutUserDeviceMaster> page = vcutUserDeviceMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-user-device-masters/:id} : get the "id" vcutUserDeviceMaster.
     *
     * @param id the id of the vcutUserDeviceMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutUserDeviceMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-user-device-masters/{id}")
    public ResponseEntity<VcutUserDeviceMaster> getVcutUserDeviceMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutUserDeviceMaster : {}", id);
        Optional<VcutUserDeviceMaster> vcutUserDeviceMaster = vcutUserDeviceMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vcutUserDeviceMaster);
    }

    /**
     * {@code DELETE  /vcut-user-device-masters/:id} : delete the "id" vcutUserDeviceMaster.
     *
     * @param id the id of the vcutUserDeviceMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-user-device-masters/{id}")
    public ResponseEntity<Void> deleteVcutUserDeviceMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutUserDeviceMaster : {}", id);
        vcutUserDeviceMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
