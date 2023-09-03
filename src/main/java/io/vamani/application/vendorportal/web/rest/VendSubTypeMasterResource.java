package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.VendSubTypeMaster;
import io.vamani.application.vendorportal.repository.VendSubTypeMasterRepository;
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
 * REST controller for managing VendSubTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class VendSubTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(VendSubTypeMasterResource.class);

    private static final String ENTITY_NAME = "vendSubTypeMaster";

    private final VendSubTypeMasterRepository vendSubTypeMasterRepository;

    public VendSubTypeMasterResource(VendSubTypeMasterRepository vendSubTypeMasterRepository) {
        this.vendSubTypeMasterRepository = vendSubTypeMasterRepository;
    }

    /**
     * POST  /vend-sub-type-masters : Create a new vendSubTypeMaster.
     *
     * @param vendSubTypeMaster the vendSubTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendSubTypeMaster, or with status 400 (Bad Request) if the vendSubTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vend-sub-type-masters")
    @Timed
    public ResponseEntity<VendSubTypeMaster> createVendSubTypeMaster(@Valid @RequestBody VendSubTypeMaster vendSubTypeMaster) throws URISyntaxException {
        log.debug("REST request to save VendSubTypeMaster : {}", vendSubTypeMaster);
        if (vendSubTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new vendSubTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vendSubTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vendSubTypeMaster.setCreatedDate(Instant.now());
        VendSubTypeMaster result = vendSubTypeMasterRepository.save(vendSubTypeMaster);
        return ResponseEntity.created(new URI("/api/vend-sub-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vend-sub-type-masters : Updates an existing vendSubTypeMaster.
     *
     * @param vendSubTypeMaster the vendSubTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendSubTypeMaster,
     * or with status 400 (Bad Request) if the vendSubTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the vendSubTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vend-sub-type-masters")
    @Timed
    public ResponseEntity<VendSubTypeMaster> updateVendSubTypeMaster(@Valid @RequestBody VendSubTypeMaster vendSubTypeMaster) throws URISyntaxException {
        log.debug("REST request to update VendSubTypeMaster : {}", vendSubTypeMaster);
        if (vendSubTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        vendSubTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vendSubTypeMaster.setLastUpdatedDate(Instant.now());
        VendSubTypeMaster result = vendSubTypeMasterRepository.save(vendSubTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendSubTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vend-sub-type-masters : get all the vendSubTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendSubTypeMasters in body
     */
    @GetMapping("/vend-sub-type-masters")
    @Timed
    public ResponseEntity<List<VendSubTypeMaster>> getAllVendSubTypeMasters(Pageable pageable) {
        log.debug("REST request to get a page of VendSubTypeMasters");
        Page<VendSubTypeMaster> page = vendSubTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vend-sub-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vend-sub-type-masters/:id : get the "id" vendSubTypeMaster.
     *
     * @param id the id of the vendSubTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendSubTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/vend-sub-type-masters/{id}")
    @Timed
    public ResponseEntity<VendSubTypeMaster> getVendSubTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get VendSubTypeMaster : {}", id);
        Optional<VendSubTypeMaster> vendSubTypeMaster = vendSubTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendSubTypeMaster);
    }

    /**
     * GET  /vend-sub-type-masters/:id : get the "id" vendSubTypeMaster.
     *
     * @param id the id of the vendSubTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendSubTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/vend-sub-type-masters-vend-type/{id}")
    @Timed
    public ResponseEntity<List<VendSubTypeMaster>> getVendSubTypeMasterByVendType(@PathVariable Long id) {
        log.debug("REST request to get VendSubTypeMaster : {}", id);
        List<VendSubTypeMaster> vendSubTypeMasters = vendSubTypeMasterRepository.findAllByVendTypeMaster(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(vendSubTypeMasters));
    }

    /**
     * DELETE  /vend-sub-type-masters/:id : delete the "id" vendSubTypeMaster.
     *
     * @param id the id of the vendSubTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vend-sub-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendSubTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete VendSubTypeMaster : {}", id);

        vendSubTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
