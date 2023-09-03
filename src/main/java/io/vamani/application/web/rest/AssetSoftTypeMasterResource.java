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
import io.vamani.application.domain.AssetSoftTypeMaster;
import io.vamani.application.repository.AssetSoftTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetSoftTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetSoftTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetSoftTypeMasterResource.class);

    private static final String ENTITY_NAME = "assetSoftTypeMaster";

    private final AssetSoftTypeMasterRepository assetSoftTypeMasterRepository;

    public AssetSoftTypeMasterResource(AssetSoftTypeMasterRepository assetSoftTypeMasterRepository) {
        this.assetSoftTypeMasterRepository = assetSoftTypeMasterRepository;
    }

    /**
     * POST  /asset-soft-type-masters : Create a new assetSoftTypeMaster.
     *
     * @param assetSoftTypeMaster the assetSoftTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetSoftTypeMaster, or with status 400 (Bad Request) if the assetSoftTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-soft-type-masters")
    @Timed
    public ResponseEntity<AssetSoftTypeMaster> createAssetSoftTypeMaster(@Valid @RequestBody AssetSoftTypeMaster assetSoftTypeMaster) throws URISyntaxException {
        log.debug("REST request to save AssetSoftTypeMaster : {}", assetSoftTypeMaster);
        if (assetSoftTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetSoftTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetSoftTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSoftTypeMaster.setCreatedDate(Instant.now());
        AssetSoftTypeMaster result = assetSoftTypeMasterRepository.save(assetSoftTypeMaster);
        return ResponseEntity.created(new URI("/api/asset-soft-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-soft-type-masters : Updates an existing assetSoftTypeMaster.
     *
     * @param assetSoftTypeMaster the assetSoftTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetSoftTypeMaster,
     * or with status 400 (Bad Request) if the assetSoftTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetSoftTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-soft-type-masters")
    @Timed
    public ResponseEntity<AssetSoftTypeMaster> updateAssetSoftTypeMaster(@Valid @RequestBody AssetSoftTypeMaster assetSoftTypeMaster) throws URISyntaxException {
        log.debug("REST request to update AssetSoftTypeMaster : {}", assetSoftTypeMaster);
        if (assetSoftTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetSoftTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSoftTypeMaster.setLastUpdatedDate(Instant.now());
        AssetSoftTypeMaster result = assetSoftTypeMasterRepository.save(assetSoftTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetSoftTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-soft-type-masters : get all the assetSoftTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetSoftTypeMasters in body
     */
    @GetMapping("/asset-soft-type-masters")
    @Timed
    public ResponseEntity<List<AssetSoftTypeMaster>> getAllAssetSoftTypeMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetSoftTypeMasters");
        Page<AssetSoftTypeMaster> page = assetSoftTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-soft-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-soft-type-masters/:id : get the "id" assetSoftTypeMaster.
     *
     * @param id the id of the assetSoftTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetSoftTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-soft-type-masters/{id}")
    @Timed
    public ResponseEntity<AssetSoftTypeMaster> getAssetSoftTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetSoftTypeMaster : {}", id);
        Optional<AssetSoftTypeMaster> assetSoftTypeMaster = assetSoftTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetSoftTypeMaster);
    }

    /**
     * DELETE  /asset-soft-type-masters/:id : delete the "id" assetSoftTypeMaster.
     *
     * @param id the id of the assetSoftTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-soft-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetSoftTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetSoftTypeMaster : {}", id);

        assetSoftTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
