package io.vamani.application.web.rest;

import io.vamani.application.domain.AuditGroupMaster;
import io.vamani.application.repository.AuditGroupMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link AuditGroupMaster}.
 */
@RestController
@RequestMapping("/api")
public class AuditGroupMasterResource {

    private final Logger log = LoggerFactory.getLogger(AuditGroupMasterResource.class);

    private static final String ENTITY_NAME = "auditGroupMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditGroupMasterRepository auditGroupMasterRepository;

    public AuditGroupMasterResource(AuditGroupMasterRepository auditGroupMasterRepository) {
        this.auditGroupMasterRepository = auditGroupMasterRepository;
    }

    /**
     * {@code POST  /audit-group-masters} : Create a new auditGroupMaster.
     *
     * @param auditGroupMaster the auditGroupMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditGroupMaster, or with status {@code 400 (Bad Request)} if the auditGroupMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/audit-group-masters")
    public ResponseEntity<AuditGroupMaster> createAuditGroupMaster(@Valid @RequestBody AuditGroupMaster auditGroupMaster) throws URISyntaxException {
        log.debug("REST request to save AuditGroupMaster : {}", auditGroupMaster);
        if (auditGroupMaster.getId() != null) {
            throw new BadRequestAlertException("A new auditGroupMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        auditGroupMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        auditGroupMaster.setCreatedDate(Instant.now());
        AuditGroupMaster result = auditGroupMasterRepository.save(auditGroupMaster);
        return ResponseEntity.created(new URI("/api/audit-group-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /audit-group-masters} : Updates an existing auditGroupMaster.
     *
     * @param auditGroupMaster the auditGroupMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditGroupMaster,
     * or with status {@code 400 (Bad Request)} if the auditGroupMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditGroupMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/audit-group-masters")
    public ResponseEntity<AuditGroupMaster> updateAuditGroupMaster(@Valid @RequestBody AuditGroupMaster auditGroupMaster) throws URISyntaxException {
        log.debug("REST request to update AuditGroupMaster : {}", auditGroupMaster);
        if (auditGroupMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        auditGroupMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        auditGroupMaster.setLastUpdatedDate(Instant.now());
        AuditGroupMaster result = auditGroupMasterRepository.save(auditGroupMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, auditGroupMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /audit-group-masters} : get all the auditGroupMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditGroupMasters in body.
     */
    @GetMapping("/audit-group-masters")
    public ResponseEntity<List<AuditGroupMaster>> getAllAuditGroupMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of AuditGroupMasters");
        Page<AuditGroupMaster> page = auditGroupMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /audit-group-masters/:id} : get the "id" auditGroupMaster.
     *
     * @param id the id of the auditGroupMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditGroupMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/audit-group-masters/{id}")
    public ResponseEntity<AuditGroupMaster> getAuditGroupMaster(@PathVariable Long id) {
        log.debug("REST request to get AuditGroupMaster : {}", id);
        Optional<AuditGroupMaster> auditGroupMaster = auditGroupMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auditGroupMaster);
    }

    /**
     * {@code DELETE  /audit-group-masters/:id} : delete the "id" auditGroupMaster.
     *
     * @param id the id of the auditGroupMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/audit-group-masters/{id}")
    public ResponseEntity<Void> deleteAuditGroupMaster(@PathVariable Long id) {
        log.debug("REST request to delete AuditGroupMaster : {}", id);
        auditGroupMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
