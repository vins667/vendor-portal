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
import io.vamani.application.domain.AssetSubTypeMaster;
import io.vamani.application.repository.AssetSubTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetSubTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetSubTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetSubTypeMasterResource.class);

    private static final String ENTITY_NAME = "assetSubTypeMaster";

    private final AssetSubTypeMasterRepository assetSubTypeMasterRepository;

    public AssetSubTypeMasterResource(AssetSubTypeMasterRepository assetSubTypeMasterRepository) {
        this.assetSubTypeMasterRepository = assetSubTypeMasterRepository;
    }

    /**
     * POST  /asset-sub-type-masters : Create a new assetSubTypeMaster.
     *
     * @param assetSubTypeMaster the assetSubTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetSubTypeMaster, or with status 400 (Bad Request) if the assetSubTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-sub-type-masters")
    @Timed
    public ResponseEntity<AssetSubTypeMaster> createAssetSubTypeMaster(@Valid @RequestBody AssetSubTypeMaster assetSubTypeMaster) throws URISyntaxException {
        log.debug("REST request to save AssetSubTypeMaster : {}", assetSubTypeMaster);
        if (assetSubTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetSubTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetSubTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSubTypeMaster.setCreatedDate(Instant.now());
        AssetSubTypeMaster result = assetSubTypeMasterRepository.save(assetSubTypeMaster);
        return ResponseEntity.created(new URI("/api/asset-sub-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-sub-type-masters : Updates an existing assetSubTypeMaster.
     *
     * @param assetSubTypeMaster the assetSubTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetSubTypeMaster,
     * or with status 400 (Bad Request) if the assetSubTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetSubTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-sub-type-masters")
    @Timed
    public ResponseEntity<AssetSubTypeMaster> updateAssetSubTypeMaster(@Valid @RequestBody AssetSubTypeMaster assetSubTypeMaster) throws URISyntaxException {
        log.debug("REST request to update AssetSubTypeMaster : {}", assetSubTypeMaster);
        if (assetSubTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetSubTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSubTypeMaster.setLastUpdatedDate(Instant.now());
        AssetSubTypeMaster result = assetSubTypeMasterRepository.save(assetSubTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetSubTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-sub-type-masters : get all the assetSubTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetSubTypeMasters in body
     */
    @GetMapping("/asset-sub-type-masters")
    @Timed
    public ResponseEntity<List<AssetSubTypeMaster>> getAllAssetSubTypeMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetSubTypeMasters");
        Page<AssetSubTypeMaster> page = assetSubTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-sub-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-soft-type-masters : get all the assetSoftTypeMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetSoftTypeMasters in body
     */
    @GetMapping("/asset-sub-type-masters-tangibility/{id}")
    @Timed
    public ResponseEntity<List<AssetSubTypeMaster>> getAllAssetSubTypeMastersByTangibility(@PathVariable Long id) {
        log.debug("REST request to get a page of AssetSubTypeMaster");
        List<AssetSubTypeMaster> list = assetSubTypeMasterRepository.findByAssetTypeMaster(id);
        return ResponseEntity.ok().body(list);
    }

    /**
     * GET  /asset-sub-type-masters/:id : get the "id" assetSubTypeMaster.
     *
     * @param id the id of the assetSubTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetSubTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-sub-type-masters/{id}")
    @Timed
    public ResponseEntity<AssetSubTypeMaster> getAssetSubTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetSubTypeMaster : {}", id);
        Optional<AssetSubTypeMaster> assetSubTypeMaster = assetSubTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetSubTypeMaster);
    }

    /**
     * DELETE  /asset-sub-type-masters/:id : delete the "id" assetSubTypeMaster.
     *
     * @param id the id of the assetSubTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-sub-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetSubTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetSubTypeMaster : {}", id);

        assetSubTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
