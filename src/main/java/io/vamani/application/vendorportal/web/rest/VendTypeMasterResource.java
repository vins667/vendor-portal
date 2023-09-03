package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.VendTypeMaster;
import io.vamani.application.vendorportal.repository.VendTypeMasterRepository;
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
 * REST controller for managing VendTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class VendTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(VendTypeMasterResource.class);

    private static final String ENTITY_NAME = "vendTypeMaster";

    private final VendTypeMasterRepository vendTypeMasterRepository;

    public VendTypeMasterResource(VendTypeMasterRepository vendTypeMasterRepository) {
        this.vendTypeMasterRepository = vendTypeMasterRepository;
    }

    /**
     * POST  /vend-type-masters : Create a new vendTypeMaster.
     *
     * @param vendTypeMaster the vendTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendTypeMaster, or with status 400 (Bad Request) if the vendTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vend-type-masters")
    @Timed
    public ResponseEntity<VendTypeMaster> createVendTypeMaster(@Valid @RequestBody VendTypeMaster vendTypeMaster) throws URISyntaxException {
        log.debug("REST request to save VendTypeMaster : {}", vendTypeMaster);
        if (vendTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new vendTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vendTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vendTypeMaster.setCreatedDate(Instant.now());
        VendTypeMaster result = vendTypeMasterRepository.save(vendTypeMaster);
        return ResponseEntity.created(new URI("/api/vend-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vend-type-masters : Updates an existing vendTypeMaster.
     *
     * @param vendTypeMaster the vendTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendTypeMaster,
     * or with status 400 (Bad Request) if the vendTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the vendTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vend-type-masters")
    @Timed
    public ResponseEntity<VendTypeMaster> updateVendTypeMaster(@Valid @RequestBody VendTypeMaster vendTypeMaster) throws URISyntaxException {
        log.debug("REST request to update VendTypeMaster : {}", vendTypeMaster);
        if (vendTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        vendTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vendTypeMaster.setLastUpdatedDate(Instant.now());
        VendTypeMaster result = vendTypeMasterRepository.save(vendTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vend-type-masters : get all the vendTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendTypeMasters in body
     */
    @GetMapping("/vend-type-masters")
    @Timed
    public ResponseEntity<List<VendTypeMaster>> getAllVendTypeMasters(Pageable pageable) {
        log.debug("REST request to get a page of VendTypeMasters");
        Page<VendTypeMaster> page = vendTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vend-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vend-type-masters/:id : get the "id" vendTypeMaster.
     *
     * @param id the id of the vendTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/vend-type-masters/{id}")
    @Timed
    public ResponseEntity<VendTypeMaster> getVendTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get VendTypeMaster : {}", id);
        Optional<VendTypeMaster> vendTypeMaster = vendTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendTypeMaster);
    }

    /**
     * DELETE  /vend-type-masters/:id : delete the "id" vendTypeMaster.
     *
     * @param id the id of the vendTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vend-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete VendTypeMaster : {}", id);

        vendTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
