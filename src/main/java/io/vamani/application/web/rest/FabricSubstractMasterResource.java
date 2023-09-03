package io.vamani.application.web.rest;

import io.vamani.application.domain.FabricSubstractMaster;
import io.vamani.application.repository.FabricSubstractMasterRepository;
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
 * REST controller for managing {@link FabricSubstractMaster}.
 */
@RestController
@RequestMapping("/api")
public class FabricSubstractMasterResource {

    private final Logger log = LoggerFactory.getLogger(FabricSubstractMasterResource.class);

    private static final String ENTITY_NAME = "fabricSubstractMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FabricSubstractMasterRepository fabricSubstractMasterRepository;

    public FabricSubstractMasterResource(FabricSubstractMasterRepository fabricSubstractMasterRepository) {
        this.fabricSubstractMasterRepository = fabricSubstractMasterRepository;
    }

    /**
     * {@code POST  /fabric-substract-masters} : Create a new fabricSubstractMaster.
     *
     * @param fabricSubstractMaster the fabricSubstractMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fabricSubstractMaster, or with status {@code 400 (Bad Request)} if the fabricSubstractMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fabric-substract-masters")
    public ResponseEntity<FabricSubstractMaster> createFabricSubstractMaster(@Valid @RequestBody FabricSubstractMaster fabricSubstractMaster) throws URISyntaxException {
        log.debug("REST request to save FabricSubstractMaster : {}", fabricSubstractMaster);
        if (fabricSubstractMaster.getId() != null) {
            throw new BadRequestAlertException("A new fabricSubstractMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fabricSubstractMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricSubstractMaster.setCreatedDate(Instant.now());
        FabricSubstractMaster result = fabricSubstractMasterRepository.save(fabricSubstractMaster);
        return ResponseEntity.created(new URI("/api/fabric-substract-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fabric-substract-masters} : Updates an existing fabricSubstractMaster.
     *
     * @param fabricSubstractMaster the fabricSubstractMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fabricSubstractMaster,
     * or with status {@code 400 (Bad Request)} if the fabricSubstractMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fabricSubstractMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fabric-substract-masters")
    public ResponseEntity<FabricSubstractMaster> updateFabricSubstractMaster(@Valid @RequestBody FabricSubstractMaster fabricSubstractMaster) throws URISyntaxException {
        log.debug("REST request to update FabricSubstractMaster : {}", fabricSubstractMaster);
        if (fabricSubstractMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        fabricSubstractMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricSubstractMaster.setCreatedDate(Instant.now());
        FabricSubstractMaster result = fabricSubstractMasterRepository.save(fabricSubstractMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fabricSubstractMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fabric-substract-masters} : get all the fabricSubstractMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricSubstractMasters in body.
     */
    @GetMapping("/fabric-substract-masters")
    public ResponseEntity<List<FabricSubstractMaster>> getAllFabricSubstractMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of FabricSubstractMasters");
        Page<FabricSubstractMaster> page = fabricSubstractMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fabric-substract-masters/:id} : get the "id" fabricSubstractMaster.
     *
     * @param id the id of the fabricSubstractMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabricSubstractMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabric-substract-masters/{id}")
    public ResponseEntity<FabricSubstractMaster> getFabricSubstractMaster(@PathVariable Long id) {
        log.debug("REST request to get FabricSubstractMaster : {}", id);
        Optional<FabricSubstractMaster> fabricSubstractMaster = fabricSubstractMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fabricSubstractMaster);
    }

    /**
     * {@code DELETE  /fabric-substract-masters/:id} : delete the "id" fabricSubstractMaster.
     *
     * @param id the id of the fabricSubstractMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fabric-substract-masters/{id}")
    public ResponseEntity<Void> deleteFabricSubstractMaster(@PathVariable Long id) {
        log.debug("REST request to delete FabricSubstractMaster : {}", id);
        fabricSubstractMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
