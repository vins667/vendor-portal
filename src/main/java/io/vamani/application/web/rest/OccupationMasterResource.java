package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.OccupationMaster;
import io.vamani.application.repository.OccupationMasterRepository;
import io.vamani.application.security.SecurityUtils;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OccupationMaster.
 */
@RestController
@RequestMapping("/api")
public class OccupationMasterResource {

    private final Logger log = LoggerFactory.getLogger(OccupationMasterResource.class);

    private static final String ENTITY_NAME = "occupationMaster";

    private final OccupationMasterRepository occupationMasterRepository;

    public OccupationMasterResource(OccupationMasterRepository occupationMasterRepository) {
        this.occupationMasterRepository = occupationMasterRepository;
    }

    /**
     * POST  /occupation-masters : Create a new occupationMaster.
     *
     * @param occupationMaster the occupationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new occupationMaster, or with status 400 (Bad Request) if the occupationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/occupation-masters")
    @Timed
    public ResponseEntity<OccupationMaster> createOccupationMaster(@Valid @RequestBody OccupationMaster occupationMaster) throws URISyntaxException {
        log.debug("REST request to save OccupationMaster : {}", occupationMaster);
        if (occupationMaster.getId() != null) {
            throw new BadRequestAlertException("A new occupationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        occupationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        occupationMaster.setCreatedDate(Instant.now());
        OccupationMaster result = occupationMasterRepository.save(occupationMaster);
        return ResponseEntity.created(new URI("/api/occupation-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /occupation-masters : Updates an existing occupationMaster.
     *
     * @param occupationMaster the occupationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated occupationMaster,
     * or with status 400 (Bad Request) if the occupationMaster is not valid,
     * or with status 500 (Internal Server Error) if the occupationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/occupation-masters")
    @Timed
    public ResponseEntity<OccupationMaster> updateOccupationMaster(@Valid @RequestBody OccupationMaster occupationMaster) throws URISyntaxException {
        log.debug("REST request to update OccupationMaster : {}", occupationMaster);
        if (occupationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        occupationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        occupationMaster.setLastUpdatedDate(Instant.now());
        OccupationMaster result = occupationMasterRepository.save(occupationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, occupationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /occupation-masters : get all the occupationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of occupationMasters in body
     */
    @GetMapping("/occupation-masters")
    @Timed
    public ResponseEntity<List<OccupationMaster>> getAllOccupationMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of OccupationMasters");
        Page<OccupationMaster> page = occupationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/occupation-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /occupation-masters/:id : get the "id" occupationMaster.
     *
     * @param id the id of the occupationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the occupationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/occupation-masters/{id}")
    @Timed
    public ResponseEntity<OccupationMaster> getOccupationMaster(@PathVariable Long id) {
        log.debug("REST request to get OccupationMaster : {}", id);
        Optional<OccupationMaster> occupationMaster = occupationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(occupationMaster);
    }

    /**
     * DELETE  /occupation-masters/:id : delete the "id" occupationMaster.
     *
     * @param id the id of the occupationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/occupation-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteOccupationMaster(@PathVariable Long id) {
        log.debug("REST request to delete OccupationMaster : {}", id);

        occupationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
