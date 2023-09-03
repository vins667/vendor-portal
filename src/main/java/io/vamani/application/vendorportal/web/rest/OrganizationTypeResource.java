package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.OrganizationType;
import io.vamani.application.vendorportal.repository.OrganizationTypeRepository;
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
 * REST controller for managing OrganizationType.
 */
@RestController
@RequestMapping("/api")
public class OrganizationTypeResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationTypeResource.class);

    private static final String ENTITY_NAME = "organizationType";

    private final OrganizationTypeRepository organizationTypeRepository;

    public OrganizationTypeResource(OrganizationTypeRepository organizationTypeRepository) {
        this.organizationTypeRepository = organizationTypeRepository;
    }

    /**
     * POST  /organization-types : Create a new organizationType.
     *
     * @param organizationType the organizationType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new organizationType, or with status 400 (Bad Request) if the organizationType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/organization-types")
    @Timed
    public ResponseEntity<OrganizationType> createOrganizationType(@Valid @RequestBody OrganizationType organizationType) throws URISyntaxException {
        log.debug("REST request to save OrganizationType : {}", organizationType);
        if (organizationType.getId() != null) {
            throw new BadRequestAlertException("A new organizationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        organizationType.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        organizationType.setCreatedDate(Instant.now());
        OrganizationType result = organizationTypeRepository.save(organizationType);
        return ResponseEntity.created(new URI("/api/organization-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /organization-types : Updates an existing organizationType.
     *
     * @param organizationType the organizationType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated organizationType,
     * or with status 400 (Bad Request) if the organizationType is not valid,
     * or with status 500 (Internal Server Error) if the organizationType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/organization-types")
    @Timed
    public ResponseEntity<OrganizationType> updateOrganizationType(@Valid @RequestBody OrganizationType organizationType) throws URISyntaxException {
        log.debug("REST request to update OrganizationType : {}", organizationType);
        if (organizationType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        organizationType.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        organizationType.setLastUpdatedDate(Instant.now());
        OrganizationType result = organizationTypeRepository.save(organizationType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, organizationType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /organization-types : get all the organizationTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of organizationTypes in body
     */
    @GetMapping("/organization-types")
    @Timed
    public ResponseEntity<List<OrganizationType>> getAllOrganizationTypes(@PageableDefault(sort = { "description" }, value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of OrganizationTypes");
        Page<OrganizationType> page = organizationTypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/organization-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /organization-types/:id : get the "id" organizationType.
     *
     * @param id the id of the organizationType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the organizationType, or with status 404 (Not Found)
     */
    @GetMapping("/organization-types/{id}")
    @Timed
    public ResponseEntity<OrganizationType> getOrganizationType(@PathVariable Long id) {
        log.debug("REST request to get OrganizationType : {}", id);
        Optional<OrganizationType> organizationType = organizationTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(organizationType);
    }

    /**
     * DELETE  /organization-types/:id : delete the "id" organizationType.
     *
     * @param id the id of the organizationType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/organization-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrganizationType(@PathVariable Long id) {
        log.debug("REST request to delete OrganizationType : {}", id);

        organizationTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
