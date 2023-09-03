package io.vamani.application.web.rest;
import io.vamani.application.domain.RelationMaster;
import io.vamani.application.repository.RelationMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
 * REST controller for managing RelationMaster.
 */
@RestController
@RequestMapping("/api")
public class RelationMasterResource {

    private final Logger log = LoggerFactory.getLogger(RelationMasterResource.class);

    private static final String ENTITY_NAME = "relationMaster";

    private final RelationMasterRepository relationMasterRepository;

    public RelationMasterResource(RelationMasterRepository relationMasterRepository) {
        this.relationMasterRepository = relationMasterRepository;
    }

    /**
     * POST  /relation-masters : Create a new relationMaster.
     *
     * @param relationMaster the relationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new relationMaster, or with status 400 (Bad Request) if the relationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/relation-masters")
    public ResponseEntity<RelationMaster> createRelationMaster(@Valid @RequestBody RelationMaster relationMaster) throws URISyntaxException {
        log.debug("REST request to save RelationMaster : {}", relationMaster);
        if (relationMaster.getId() != null) {
            throw new BadRequestAlertException("A new relationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelationMaster result = relationMasterRepository.save(relationMaster);
        return ResponseEntity.created(new URI("/api/relation-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /relation-masters : Updates an existing relationMaster.
     *
     * @param relationMaster the relationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated relationMaster,
     * or with status 400 (Bad Request) if the relationMaster is not valid,
     * or with status 500 (Internal Server Error) if the relationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/relation-masters")
    public ResponseEntity<RelationMaster> updateRelationMaster(@Valid @RequestBody RelationMaster relationMaster) throws URISyntaxException {
        log.debug("REST request to update RelationMaster : {}", relationMaster);
        if (relationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelationMaster result = relationMasterRepository.save(relationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, relationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /relation-masters : get all the relationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of relationMasters in body
     */
    @GetMapping("/relation-masters")
    public ResponseEntity<List<RelationMaster>> getAllRelationMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of RelationMasters");
        Page<RelationMaster> page = relationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relation-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /relation-masters/:id : get the "id" relationMaster.
     *
     * @param id the id of the relationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the relationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/relation-masters/{id}")
    public ResponseEntity<RelationMaster> getRelationMaster(@PathVariable Long id) {
        log.debug("REST request to get RelationMaster : {}", id);
        Optional<RelationMaster> relationMaster = relationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(relationMaster);
    }

    /**
     * DELETE  /relation-masters/:id : delete the "id" relationMaster.
     *
     * @param id the id of the relationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/relation-masters/{id}")
    public ResponseEntity<Void> deleteRelationMaster(@PathVariable Long id) {
        log.debug("REST request to delete RelationMaster : {}", id);
        relationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
