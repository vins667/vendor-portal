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

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.AssetTypeMaster;
import io.vamani.application.repository.AssetTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetTypeMasterResource.class);

    private static final String ENTITY_NAME = "assetTypeMaster";

    private final AssetTypeMasterRepository assetTypeMasterRepository;

    public AssetTypeMasterResource(AssetTypeMasterRepository assetTypeMasterRepository) {
        this.assetTypeMasterRepository = assetTypeMasterRepository;
    }

    /**
     * POST  /asset-type-masters : Create a new assetTypeMaster.
     *
     * @param assetTypeMaster the assetTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetTypeMaster, or with status 400 (Bad Request) if the assetTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-type-masters")
    @Timed
    public ResponseEntity<AssetTypeMaster> createAssetTypeMaster(@Valid @RequestBody AssetTypeMaster assetTypeMaster) throws URISyntaxException {
        log.debug("REST request to save AssetTypeMaster : {}", assetTypeMaster);
        if (assetTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetTypeMaster.setCreatedDate(Instant.now());
        AssetTypeMaster result = assetTypeMasterRepository.save(assetTypeMaster);
        return ResponseEntity.created(new URI("/api/asset-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-type-masters : Updates an existing assetTypeMaster.
     *
     * @param assetTypeMaster the assetTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetTypeMaster,
     * or with status 400 (Bad Request) if the assetTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-type-masters")
    @Timed
    public ResponseEntity<AssetTypeMaster> updateAssetTypeMaster(@Valid @RequestBody AssetTypeMaster assetTypeMaster) throws URISyntaxException {
        log.debug("REST request to update AssetTypeMaster : {}", assetTypeMaster);
        if (assetTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetTypeMaster.setLastUpdatedDate(Instant.now());
        AssetTypeMaster result = assetTypeMasterRepository.save(assetTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-type-masters : get all the assetTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetTypeMasters in body
     */
    @GetMapping("/asset-type-masters")
    @Timed
    public ResponseEntity<List<AssetTypeMaster>> getAllAssetTypeMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetTypeMasters");
        Page<AssetTypeMaster> page = assetTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-type-masters/:id : get the "id" assetTypeMaster.
     *
     * @param id the id of the assetTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-type-masters/{id}")
    @Timed
    public ResponseEntity<AssetTypeMaster> getAssetTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetTypeMaster : {}", id);
        Optional<AssetTypeMaster> assetTypeMaster = assetTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetTypeMaster);
    }

    /**
     * DELETE  /asset-type-masters/:id : delete the "id" assetTypeMaster.
     *
     * @param id the id of the assetTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetTypeMaster : {}", id);

        assetTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
