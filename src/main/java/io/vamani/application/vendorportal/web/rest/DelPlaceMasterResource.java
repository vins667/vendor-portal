package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.vendorportal.domain.DelPlaceMaster;
import io.vamani.application.vendorportal.repository.DelPlaceMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
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
 * REST controller for managing DelPlaceMaster.
 */
@RestController
@RequestMapping("/api")
public class DelPlaceMasterResource {

    private final Logger log = LoggerFactory.getLogger(DelPlaceMasterResource.class);

    private static final String ENTITY_NAME = "delPlaceMaster";

    private final DelPlaceMasterRepository delPlaceMasterRepository;

    public DelPlaceMasterResource(DelPlaceMasterRepository delPlaceMasterRepository) {
        this.delPlaceMasterRepository = delPlaceMasterRepository;
    }

    /**
     * POST  /del-place-masters : Create a new delPlaceMaster.
     *
     * @param delPlaceMaster the delPlaceMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new delPlaceMaster, or with status 400 (Bad Request) if the delPlaceMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/del-place-masters")
    public ResponseEntity<DelPlaceMaster> createDelPlaceMaster(@Valid @RequestBody DelPlaceMaster delPlaceMaster) throws URISyntaxException {
        log.debug("REST request to save DelPlaceMaster : {}", delPlaceMaster);
        if (delPlaceMaster.getId() != null) {
            throw new BadRequestAlertException("A new delPlaceMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        delPlaceMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        delPlaceMaster.setCreatedDate(Instant.now());
        DelPlaceMaster result = delPlaceMasterRepository.save(delPlaceMaster);
        return ResponseEntity.created(new URI("/api/del-place-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /del-place-masters : Updates an existing delPlaceMaster.
     *
     * @param delPlaceMaster the delPlaceMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated delPlaceMaster,
     * or with status 400 (Bad Request) if the delPlaceMaster is not valid,
     * or with status 500 (Internal Server Error) if the delPlaceMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/del-place-masters")
    public ResponseEntity<DelPlaceMaster> updateDelPlaceMaster(@Valid @RequestBody DelPlaceMaster delPlaceMaster) throws URISyntaxException {
        log.debug("REST request to update DelPlaceMaster : {}", delPlaceMaster);
        if (delPlaceMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        delPlaceMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        delPlaceMaster.setLastUpdatedDate(Instant.now());
        DelPlaceMaster result = delPlaceMasterRepository.save(delPlaceMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, delPlaceMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /del-place-masters : get all the delPlaceMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of delPlaceMasters in body
     */
    @GetMapping("/del-place-masters")
    public ResponseEntity<List<DelPlaceMaster>> getAllDelPlaceMasters(Pageable pageable) {
        log.debug("REST request to get a page of DelPlaceMasters");
        Page<DelPlaceMaster> page = delPlaceMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/del-place-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /del-place-masters/:id : get the "id" delPlaceMaster.
     *
     * @param id the id of the delPlaceMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the delPlaceMaster, or with status 404 (Not Found)
     */
    @GetMapping("/del-place-masters/{id}")
    public ResponseEntity<DelPlaceMaster> getDelPlaceMaster(@PathVariable Long id) {
        log.debug("REST request to get DelPlaceMaster : {}", id);
        Optional<DelPlaceMaster> delPlaceMaster = delPlaceMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(delPlaceMaster);
    }

    /**
     * DELETE  /del-place-masters/:id : delete the "id" delPlaceMaster.
     *
     * @param id the id of the delPlaceMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/del-place-masters/{id}")
    public ResponseEntity<Void> deleteDelPlaceMaster(@PathVariable Long id) {
        log.debug("REST request to delete DelPlaceMaster : {}", id);
        delPlaceMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
