package io.vamani.application.web.rest;

import io.vamani.application.domain.FabricUomMaster;
import io.vamani.application.repository.FabricUomMasterRepository;
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
 * REST controller for managing {@link FabricUomMaster}.
 */
@RestController
@RequestMapping("/api")
public class FabricUomMasterResource {

    private final Logger log = LoggerFactory.getLogger(FabricUomMasterResource.class);

    private static final String ENTITY_NAME = "fabricUomMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FabricUomMasterRepository fabricUomMasterRepository;

    public FabricUomMasterResource(FabricUomMasterRepository fabricUomMasterRepository) {
        this.fabricUomMasterRepository = fabricUomMasterRepository;
    }

    /**
     * {@code POST  /fabric-uom-masters} : Create a new fabricUomMaster.
     *
     * @param fabricUomMaster the fabricUomMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fabricUomMaster, or with status {@code 400 (Bad Request)} if the fabricUomMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fabric-uom-masters")
    public ResponseEntity<FabricUomMaster> createFabricUomMaster(@Valid @RequestBody FabricUomMaster fabricUomMaster) throws URISyntaxException {
        log.debug("REST request to save FabricUomMaster : {}", fabricUomMaster);
        if (fabricUomMaster.getId() != null) {
            throw new BadRequestAlertException("A new fabricUomMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
		fabricUomMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricUomMaster.setCreatedDate(Instant.now());
        FabricUomMaster result = fabricUomMasterRepository.save(fabricUomMaster);
        return ResponseEntity.created(new URI("/api/fabric-uom-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fabric-uom-masters} : Updates an existing fabricUomMaster.
     *
     * @param fabricUomMaster the fabricUomMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fabricUomMaster,
     * or with status {@code 400 (Bad Request)} if the fabricUomMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fabricUomMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fabric-uom-masters")
    public ResponseEntity<FabricUomMaster> updateFabricUomMaster(@Valid @RequestBody FabricUomMaster fabricUomMaster) throws URISyntaxException {
        log.debug("REST request to update FabricUomMaster : {}", fabricUomMaster);
        if (fabricUomMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
		fabricUomMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricUomMaster.setCreatedDate(Instant.now());
        FabricUomMaster result = fabricUomMasterRepository.save(fabricUomMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fabricUomMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fabric-uom-masters} : get all the fabricUomMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricUomMasters in body.
     */
    @GetMapping("/fabric-uom-masters")
    public ResponseEntity<List<FabricUomMaster>> getAllFabricUomMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of FabricUomMasters");
        Page<FabricUomMaster> page = fabricUomMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fabric-uom-masters/:id} : get the "id" fabricUomMaster.
     *
     * @param id the id of the fabricUomMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabricUomMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabric-uom-masters/{id}")
    public ResponseEntity<FabricUomMaster> getFabricUomMaster(@PathVariable Long id) {
        log.debug("REST request to get FabricUomMaster : {}", id);
        Optional<FabricUomMaster> fabricUomMaster = fabricUomMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fabricUomMaster);
    }

    /**
     * {@code DELETE  /fabric-uom-masters/:id} : delete the "id" fabricUomMaster.
     *
     * @param id the id of the fabricUomMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fabric-uom-masters/{id}")
    public ResponseEntity<Void> deleteFabricUomMaster(@PathVariable Long id) {
        log.debug("REST request to delete FabricUomMaster : {}", id);
        fabricUomMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
