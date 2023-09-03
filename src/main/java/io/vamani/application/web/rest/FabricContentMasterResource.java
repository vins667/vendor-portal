package io.vamani.application.web.rest;

import io.vamani.application.domain.FabricContentMaster;
import io.vamani.application.model.Master;
import io.vamani.application.repository.FabricContentMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * REST controller for managing {@link FabricContentMaster}.
 */
@RestController
@RequestMapping("/api")
public class FabricContentMasterResource {

    private final Logger log = LoggerFactory.getLogger(FabricContentMasterResource.class);

    private static final String ENTITY_NAME = "fabricContentMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FabricContentMasterRepository fabricContentMasterRepository;

    public FabricContentMasterResource(FabricContentMasterRepository fabricContentMasterRepository) {
        this.fabricContentMasterRepository = fabricContentMasterRepository;
    }

    /**
     * {@code POST  /fabric-content-masters} : Create a new fabricContentMaster.
     *
     * @param fabricContentMaster the fabricContentMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fabricContentMaster, or with status {@code 400 (Bad Request)} if the fabricContentMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fabric-content-masters")
    public ResponseEntity<FabricContentMaster> createFabricContentMaster(@Valid @RequestBody FabricContentMaster fabricContentMaster) throws URISyntaxException {
        log.debug("REST request to save FabricContentMaster : {}", fabricContentMaster);
        if (fabricContentMaster.getId() != null) {
            throw new BadRequestAlertException("A new fabricContentMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fabricContentMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricContentMaster.setCreatedDate(Instant.now());
        FabricContentMaster result = fabricContentMasterRepository.save(fabricContentMaster);
        return ResponseEntity.created(new URI("/api/fabric-content-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fabric-content-masters} : Updates an existing fabricContentMaster.
     *
     * @param fabricContentMaster the fabricContentMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fabricContentMaster,
     * or with status {@code 400 (Bad Request)} if the fabricContentMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fabricContentMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fabric-content-masters")
    public ResponseEntity<FabricContentMaster> updateFabricContentMaster(@Valid @RequestBody FabricContentMaster fabricContentMaster) throws URISyntaxException {
        log.debug("REST request to update FabricContentMaster : {}", fabricContentMaster);
        if (fabricContentMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        fabricContentMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricContentMaster.setLastUpdatedDate(Instant.now());
        FabricContentMaster result = fabricContentMasterRepository.save(fabricContentMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fabricContentMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fabric-content-masters} : get all the fabricContentMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricContentMasters in body.
     */
    @GetMapping("/fabric-content-masters")
    public ResponseEntity<List<FabricContentMaster>> getAllFabricContentMasters(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of FabricContentMasters");
        Page<FabricContentMaster> page = fabricContentMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code POST  /fabric-content-masters} : get all the fabricContentMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricContentMasters in body.
     */
    @PostMapping("/fabric-content-masters-search")
    public ResponseEntity<List<FabricContentMaster>> searchAllFabricContentMasters(@RequestBody Master search) {
        log.debug("REST request to get a page of FabricContentMasters");
        String desc = "%";
        if (search.getDesc() != null) {
            desc = search.getDesc().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("description").ascending()));
        Page<FabricContentMaster> page = fabricContentMasterRepository.findAllByCodeAndDesc(desc, desc, search.getPage());
        HttpHeaders headers = io.vamani.application.web.rest.util.PaginationUtil.generatePaginationHttpHeaders(page, "/api/fabric-content-masters-search");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fabric-content-masters/:id} : get the "id" fabricContentMaster.
     *
     * @param id the id of the fabricContentMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabricContentMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabric-content-masters/{id}")
    public ResponseEntity<FabricContentMaster> getFabricContentMaster(@PathVariable Long id) {
        log.debug("REST request to get FabricContentMaster : {}", id);
        Optional<FabricContentMaster> fabricContentMaster = fabricContentMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fabricContentMaster);
    }

    /**
     * {@code DELETE  /fabric-content-masters/:id} : delete the "id" fabricContentMaster.
     *
     * @param id the id of the fabricContentMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fabric-content-masters/{id}")
    public ResponseEntity<Void> deleteFabricContentMaster(@PathVariable Long id) {
        log.debug("REST request to delete FabricContentMaster : {}", id);
        fabricContentMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
