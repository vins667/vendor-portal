package io.vamani.application.web.rest;

import io.vamani.application.domain.FabricOthersMaster;
import io.vamani.application.repository.FabricOthersMasterRepository;
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
 * REST controller for managing {@link FabricOthersMaster}.
 */
@RestController
@RequestMapping("/api")
public class FabricOthersMasterResource {

    private final Logger log = LoggerFactory.getLogger(FabricOthersMasterResource.class);

    private static final String ENTITY_NAME = "fabricOthersMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FabricOthersMasterRepository fabricOthersMasterRepository;

    public FabricOthersMasterResource(FabricOthersMasterRepository fabricOthersMasterRepository) {
        this.fabricOthersMasterRepository = fabricOthersMasterRepository;
    }

    /**
     * {@code POST  /fabric-others-masters} : Create a new fabricOthersMaster.
     *
     * @param fabricOthersMaster the fabricOthersMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fabricOthersMaster, or with status {@code 400 (Bad Request)} if the fabricOthersMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fabric-others-masters")
    public ResponseEntity<FabricOthersMaster> createFabricOthersMaster(@Valid @RequestBody FabricOthersMaster fabricOthersMaster) throws URISyntaxException {
        log.debug("REST request to save FabricOthersMaster : {}", fabricOthersMaster);
        if (fabricOthersMaster.getId() != null) {
            throw new BadRequestAlertException("A new fabricOthersMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fabricOthersMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricOthersMaster.setCreatedDate(Instant.now());
        FabricOthersMaster result = fabricOthersMasterRepository.save(fabricOthersMaster);
        return ResponseEntity.created(new URI("/api/fabric-others-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fabric-others-masters} : Updates an existing fabricOthersMaster.
     *
     * @param fabricOthersMaster the fabricOthersMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fabricOthersMaster,
     * or with status {@code 400 (Bad Request)} if the fabricOthersMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fabricOthersMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fabric-others-masters")
    public ResponseEntity<FabricOthersMaster> updateFabricOthersMaster(@Valid @RequestBody FabricOthersMaster fabricOthersMaster) throws URISyntaxException {
        log.debug("REST request to update FabricOthersMaster : {}", fabricOthersMaster);
        if (fabricOthersMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        fabricOthersMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricOthersMaster.setLastUpdatedDate(Instant.now());
        FabricOthersMaster result = fabricOthersMasterRepository.save(fabricOthersMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fabricOthersMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fabric-others-masters} : get all the fabricOthersMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricOthersMasters in body.
     */
    @GetMapping("/fabric-others-masters")
    public ResponseEntity<List<FabricOthersMaster>> getAllFabricOthersMasters(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of FabricOthersMasters");
        Page<FabricOthersMaster> page = fabricOthersMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fabric-others-masters/:id} : get the "id" fabricOthersMaster.
     *
     * @param id the id of the fabricOthersMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabricOthersMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabric-others-masters/{id}")
    public ResponseEntity<FabricOthersMaster> getFabricOthersMaster(@PathVariable Long id) {
        log.debug("REST request to get FabricOthersMaster : {}", id);
        Optional<FabricOthersMaster> fabricOthersMaster = fabricOthersMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fabricOthersMaster);
    }

    /**
     * {@code DELETE  /fabric-others-masters/:id} : delete the "id" fabricOthersMaster.
     *
     * @param id the id of the fabricOthersMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fabric-others-masters/{id}")
    public ResponseEntity<Void> deleteFabricOthersMaster(@PathVariable Long id) {
        log.debug("REST request to delete FabricOthersMaster : {}", id);
        fabricOthersMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
