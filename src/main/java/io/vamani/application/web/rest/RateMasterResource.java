package io.vamani.application.web.rest;

import io.vamani.application.domain.RateMaster;
import io.vamani.application.repository.RateMasterRepository;
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
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing {@link io.vamani.application.domain.RateMaster}.
 */
@RestController
@RequestMapping("/api")
public class RateMasterResource {

    private final Logger log = LoggerFactory.getLogger(RateMasterResource.class);

    private static final String ENTITY_NAME = "rateMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RateMasterRepository rateMasterRepository;

    public RateMasterResource(RateMasterRepository rateMasterRepository) {
        this.rateMasterRepository = rateMasterRepository;
    }

    /**
     * {@code POST  /rate-masters} : Create a new rateMaster.
     *
     * @param rateMaster the rateMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rateMaster, or with status {@code 400 (Bad Request)} if the rateMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rate-masters")
    public ResponseEntity<RateMaster> createRateMaster(@Valid @RequestBody RateMaster rateMaster) throws URISyntaxException {
        log.debug("REST request to save RateMaster : {}", rateMaster);
        if (rateMaster.getId() != null) {
            throw new BadRequestAlertException("A new rateMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RateMaster rateMasters= rateMasterRepository.findAllEndDate();
        if(rateMasters!=null) {
        	 rateMasters.setEndDate(Instant.now());
             rateMasterRepository.save(rateMasters);
        }
        rateMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        rateMaster.setCreatedDate(Instant.now());
        RateMaster result = rateMasterRepository.save(rateMaster);
        return ResponseEntity.created(new URI("/api/rate-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rate-masters} : Updates an existing rateMaster.
     *
     * @param rateMaster the rateMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rateMaster,
     * or with status {@code 400 (Bad Request)} if the rateMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rateMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rate-masters")
    public ResponseEntity<RateMaster> updateRateMaster(@Valid @RequestBody RateMaster rateMaster) throws URISyntaxException {
        log.debug("REST request to update RateMaster : {}", rateMaster);
        if (rateMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        rateMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        rateMaster.setLastUpdatedDate(Instant.now());
        RateMaster result = rateMasterRepository.save(rateMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rateMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rate-masters} : get all the rateMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rateMasters in body.
     */
    @GetMapping("/rate-masters")
    public ResponseEntity<List<RateMaster>> getAllRateMasters(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of RateMasters");
        Page<RateMaster> page = rateMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rate-masters} : get all the rateMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rateMasters in body.
     */
    @GetMapping("/rate-masters-qry")
    public  ResponseEntity<RateMaster> getAllRateMasters() {
        log.debug("REST request to get a page of RateMasters");
        Optional<RateMaster> rateMaster= rateMasterRepository.findEndDate();
        return ResponseUtil.wrapOrNotFound(rateMaster);
    }

    /**
     * {@code GET  /rate-masters} : get all the rateMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rateMasters in body.
     */
    @GetMapping("/rate-masters-qry/{vehicleType}")
    public  ResponseEntity<RateMaster> getAllRateMasters(@PathVariable String vehicleType) {
        log.debug("REST request to get a page of RateMasters");
        Optional<RateMaster> rateMaster= rateMasterRepository.findEndDate(vehicleType);
        return ResponseUtil.wrapOrNotFound(rateMaster);
    }

    
    /**
     * {@code GET  /rate-masters/:id} : get the "id" rateMaster.
     *
     * @param id the id of the rateMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rateMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rate-masters/{id}")
    public ResponseEntity<RateMaster> getRateMaster(@PathVariable Long id) {
        log.debug("REST request to get RateMaster : {}", id);
        Optional<RateMaster> rateMaster = rateMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(rateMaster);
    }

    /**
     * {@code DELETE  /rate-masters/:id} : delete the "id" rateMaster.
     *
     * @param id the id of the rateMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rate-masters/{id}")
    public ResponseEntity<Void> deleteRateMaster(@PathVariable Long id) {
        log.debug("REST request to delete RateMaster : {}", id);
        rateMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
