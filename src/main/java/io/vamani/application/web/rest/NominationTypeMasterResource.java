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
import io.vamani.application.domain.NominationTypeMaster;
import io.vamani.application.repository.NominationTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing NominationTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class NominationTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(NominationTypeMasterResource.class);

    private static final String ENTITY_NAME = "nominationTypeMaster";

    private final NominationTypeMasterRepository nominationTypeMasterRepository;

    public NominationTypeMasterResource(NominationTypeMasterRepository nominationTypeMasterRepository) {
        this.nominationTypeMasterRepository = nominationTypeMasterRepository;
    }

    /**
     * POST  /nomination-type-masters : Create a new nominationTypeMaster.
     *
     * @param nominationTypeMaster the nominationTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nominationTypeMaster, or with status 400 (Bad Request) if the nominationTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nomination-type-masters")
    public ResponseEntity<NominationTypeMaster> createNominationTypeMaster(@Valid @RequestBody NominationTypeMaster nominationTypeMaster) throws URISyntaxException {
        log.debug("REST request to save NominationTypeMaster : {}", nominationTypeMaster);
        if (nominationTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new nominationTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        nominationTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        nominationTypeMaster.setCreatedDate(Instant.now());
        NominationTypeMaster result = nominationTypeMasterRepository.save(nominationTypeMaster);
        return ResponseEntity.created(new URI("/api/nomination-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nomination-type-masters : Updates an existing nominationTypeMaster.
     *
     * @param nominationTypeMaster the nominationTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nominationTypeMaster,
     * or with status 400 (Bad Request) if the nominationTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the nominationTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nomination-type-masters")
    public ResponseEntity<NominationTypeMaster> updateNominationTypeMaster(@Valid @RequestBody NominationTypeMaster nominationTypeMaster) throws URISyntaxException {
        log.debug("REST request to update NominationTypeMaster : {}", nominationTypeMaster);
        if (nominationTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        nominationTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        nominationTypeMaster.setLastUpdatedDate(Instant.now());
        NominationTypeMaster result = nominationTypeMasterRepository.save(nominationTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nominationTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nomination-type-masters : get all the nominationTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nominationTypeMasters in body
     */
    @GetMapping("/nomination-type-masters")
    public ResponseEntity<List<NominationTypeMaster>> getAllNominationTypeMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of NominationTypeMasters");
        Page<NominationTypeMaster> page = nominationTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nomination-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /nomination-type-masters/:id : get the "id" nominationTypeMaster.
     *
     * @param id the id of the nominationTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nominationTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/nomination-type-masters/{id}")
    public ResponseEntity<NominationTypeMaster> getNominationTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get NominationTypeMaster : {}", id);
        Optional<NominationTypeMaster> nominationTypeMaster = nominationTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nominationTypeMaster);
    }

    /**
     * DELETE  /nomination-type-masters/:id : delete the "id" nominationTypeMaster.
     *
     * @param id the id of the nominationTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nomination-type-masters/{id}")
    public ResponseEntity<Void> deleteNominationTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete NominationTypeMaster : {}", id);
        nominationTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
