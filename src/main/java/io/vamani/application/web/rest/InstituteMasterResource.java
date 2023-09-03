package io.vamani.application.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.InstituteMaster;
import io.vamani.application.repository.InstituteMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing InstituteMaster.
 */
@RestController
@RequestMapping("/api")
public class InstituteMasterResource {

    private final Logger log = LoggerFactory.getLogger(InstituteMasterResource.class);

    private static final String ENTITY_NAME = "instituteMaster";

    private final InstituteMasterRepository instituteMasterRepository;

    public InstituteMasterResource(InstituteMasterRepository instituteMasterRepository) {
        this.instituteMasterRepository = instituteMasterRepository;
    }

    /**
     * POST  /institute-masters : Create a new instituteMaster.
     *
     * @param instituteMaster the instituteMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instituteMaster, or with status 400 (Bad Request) if the instituteMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/institute-masters")
    public ResponseEntity<InstituteMaster> createInstituteMaster(@Valid @RequestBody InstituteMaster instituteMaster) throws URISyntaxException {
        log.debug("REST request to save InstituteMaster : {}", instituteMaster);
        if (instituteMaster.getId() != null) {
            throw new BadRequestAlertException("A new instituteMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        instituteMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        instituteMaster.setCreatedDate(Instant.now());
        InstituteMaster result = instituteMasterRepository.save(instituteMaster);
        return ResponseEntity.created(new URI("/api/institute-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /institute-masters : Updates an existing instituteMaster.
     *
     * @param instituteMaster the instituteMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instituteMaster,
     * or with status 400 (Bad Request) if the instituteMaster is not valid,
     * or with status 500 (Internal Server Error) if the instituteMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/institute-masters")
    public ResponseEntity<InstituteMaster> updateInstituteMaster(@Valid @RequestBody InstituteMaster instituteMaster) throws URISyntaxException {
        log.debug("REST request to update InstituteMaster : {}", instituteMaster);
        if (instituteMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        instituteMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        instituteMaster.setLastUpdatedDate(Instant.now());
        InstituteMaster result = instituteMasterRepository.save(instituteMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instituteMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /institute-masters : get all the instituteMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of instituteMasters in body
     */
    @GetMapping("/institute-masters")
    public ResponseEntity<List<InstituteMaster>> getAllInstituteMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of InstituteMasters");
        Page<InstituteMaster> page = instituteMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/institute-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /institute-masters/:id : get the "id" instituteMaster.
     *
     * @param id the id of the instituteMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instituteMaster, or with status 404 (Not Found)
     */
    @GetMapping("/institute-masters/{id}")
    public ResponseEntity<InstituteMaster> getInstituteMaster(@PathVariable Long id) {
        log.debug("REST request to get InstituteMaster : {}", id);
        Optional<InstituteMaster> instituteMaster = instituteMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(instituteMaster);
    }

    /**
     * DELETE  /institute-masters/:id : delete the "id" instituteMaster.
     *
     * @param id the id of the instituteMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/institute-masters/{id}")
    public ResponseEntity<Void> deleteInstituteMaster(@PathVariable Long id) {
        log.debug("REST request to delete InstituteMaster : {}", id);
        instituteMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
