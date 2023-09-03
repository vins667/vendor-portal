package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.PoliciesGroup;
import io.vamani.application.repository.PoliciesGroupRepository;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PoliciesGroup.
 */
@RestController
@RequestMapping("/api")
public class PoliciesGroupResource {

    private final Logger log = LoggerFactory.getLogger(PoliciesGroupResource.class);

    private static final String ENTITY_NAME = "policiesGroup";

    private final PoliciesGroupRepository policiesGroupRepository;

    public PoliciesGroupResource(PoliciesGroupRepository policiesGroupRepository) {
        this.policiesGroupRepository = policiesGroupRepository;
    }

    /**
     * POST  /policies-groups : Create a new policiesGroup.
     *
     * @param policiesGroup the policiesGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new policiesGroup, or with status 400 (Bad Request) if the policiesGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/policies-groups")
    @Timed
    public ResponseEntity<PoliciesGroup> createPoliciesGroup(@Valid @RequestBody PoliciesGroup policiesGroup) throws URISyntaxException {
        log.debug("REST request to save PoliciesGroup : {}", policiesGroup);
        if (policiesGroup.getId() != null) {
            throw new BadRequestAlertException("A new policiesGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PoliciesGroup result = policiesGroupRepository.save(policiesGroup);
        return ResponseEntity.created(new URI("/api/policies-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /policies-groups : Updates an existing policiesGroup.
     *
     * @param policiesGroup the policiesGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated policiesGroup,
     * or with status 400 (Bad Request) if the policiesGroup is not valid,
     * or with status 500 (Internal Server Error) if the policiesGroup couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/policies-groups")
    @Timed
    public ResponseEntity<PoliciesGroup> updatePoliciesGroup(@Valid @RequestBody PoliciesGroup policiesGroup) throws URISyntaxException {
        log.debug("REST request to update PoliciesGroup : {}", policiesGroup);
        if (policiesGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PoliciesGroup result = policiesGroupRepository.save(policiesGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, policiesGroup.getId().toString()))
            .body(result);
    }

    /**
     * GET  /policies-groups : get all the policiesGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of policiesGroups in body
     */
    @GetMapping("/policies-groups")
    @Timed
    public ResponseEntity<List<PoliciesGroup>> getAllPoliciesGroups(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of PoliciesGroups");
        Page<PoliciesGroup> page = policiesGroupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/policies-groups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /policies-groups/:id : get the "id" policiesGroup.
     *
     * @param id the id of the policiesGroup to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the policiesGroup, or with status 404 (Not Found)
     */
    @GetMapping("/policies-groups/{id}")
    @Timed
    public ResponseEntity<PoliciesGroup> getPoliciesGroup(@PathVariable Long id) {
        log.debug("REST request to get PoliciesGroup : {}", id);
        Optional<PoliciesGroup> policiesGroup = policiesGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(policiesGroup);
    }

    /**
     * DELETE  /policies-groups/:id : delete the "id" policiesGroup.
     *
     * @param id the id of the policiesGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/policies-groups/{id}")
    @Timed
    public ResponseEntity<Void> deletePoliciesGroup(@PathVariable Long id) {
        log.debug("REST request to delete PoliciesGroup : {}", id);

        policiesGroupRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
