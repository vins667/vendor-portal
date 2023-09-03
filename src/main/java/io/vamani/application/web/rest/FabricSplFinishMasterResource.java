package io.vamani.application.web.rest;

import io.vamani.application.domain.FabricSplFinishMaster;
import io.vamani.application.repository.FabricSplFinishMasterRepository;
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
 * REST controller for managing {@link FabricSplFinishMaster}.
 */
@RestController
@RequestMapping("/api")
public class FabricSplFinishMasterResource {

    private final Logger log = LoggerFactory.getLogger(FabricSplFinishMasterResource.class);

    private static final String ENTITY_NAME = "fabricSplFinishMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FabricSplFinishMasterRepository fabricSplFinishMasterRepository;

    public FabricSplFinishMasterResource(FabricSplFinishMasterRepository fabricSplFinishMasterRepository) {
        this.fabricSplFinishMasterRepository = fabricSplFinishMasterRepository;
    }

    /**
     * {@code POST  /fabric-spl-finish-masters} : Create a new fabricSplFinishMaster.
     *
     * @param fabricSplFinishMaster the fabricSplFinishMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fabricSplFinishMaster, or with status {@code 400 (Bad Request)} if the fabricSplFinishMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fabric-spl-finish-masters")
    public ResponseEntity<FabricSplFinishMaster> createFabricSplFinishMaster(@Valid @RequestBody FabricSplFinishMaster fabricSplFinishMaster) throws URISyntaxException {
        log.debug("REST request to save FabricSplFinishMaster : {}", fabricSplFinishMaster);
        if (fabricSplFinishMaster.getId() != null) {
            throw new BadRequestAlertException("A new fabricSplFinishMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fabricSplFinishMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricSplFinishMaster.setCreatedDate(Instant.now());
        FabricSplFinishMaster result = fabricSplFinishMasterRepository.save(fabricSplFinishMaster);
        return ResponseEntity.created(new URI("/api/fabric-spl-finish-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fabric-spl-finish-masters} : Updates an existing fabricSplFinishMaster.
     *
     * @param fabricSplFinishMaster the fabricSplFinishMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fabricSplFinishMaster,
     * or with status {@code 400 (Bad Request)} if the fabricSplFinishMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fabricSplFinishMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fabric-spl-finish-masters")
    public ResponseEntity<FabricSplFinishMaster> updateFabricSplFinishMaster(@Valid @RequestBody FabricSplFinishMaster fabricSplFinishMaster) throws URISyntaxException {
        log.debug("REST request to update FabricSplFinishMaster : {}", fabricSplFinishMaster);
        if (fabricSplFinishMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        fabricSplFinishMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricSplFinishMaster.setCreatedDate(Instant.now());
        FabricSplFinishMaster result = fabricSplFinishMasterRepository.save(fabricSplFinishMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fabricSplFinishMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fabric-spl-finish-masters} : get all the fabricSplFinishMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricSplFinishMasters in body.
     */
    @GetMapping("/fabric-spl-finish-masters")
    public ResponseEntity<List<FabricSplFinishMaster>> getAllFabricSplFinishMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of FabricSplFinishMasters");
        Page<FabricSplFinishMaster> page = fabricSplFinishMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fabric-spl-finish-masters/:id} : get the "id" fabricSplFinishMaster.
     *
     * @param id the id of the fabricSplFinishMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabricSplFinishMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabric-spl-finish-masters/{id}")
    public ResponseEntity<FabricSplFinishMaster> getFabricSplFinishMaster(@PathVariable Long id) {
        log.debug("REST request to get FabricSplFinishMaster : {}", id);
        Optional<FabricSplFinishMaster> fabricSplFinishMaster = fabricSplFinishMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fabricSplFinishMaster);
    }

    /**
     * {@code DELETE  /fabric-spl-finish-masters/:id} : delete the "id" fabricSplFinishMaster.
     *
     * @param id the id of the fabricSplFinishMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fabric-spl-finish-masters/{id}")
    public ResponseEntity<Void> deleteFabricSplFinishMaster(@PathVariable Long id) {
        log.debug("REST request to delete FabricSplFinishMaster : {}", id);
        fabricSplFinishMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
