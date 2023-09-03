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
import io.vamani.application.domain.AssetWarrantyTypeMaster;
import io.vamani.application.repository.AssetWarrantyTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetWarrantyTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetWarrantyTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetWarrantyTypeMasterResource.class);

    private static final String ENTITY_NAME = "assetWarrantyTypeMaster";

    private final AssetWarrantyTypeMasterRepository assetWarrantyTypeMasterRepository;

    public AssetWarrantyTypeMasterResource(AssetWarrantyTypeMasterRepository assetWarrantyTypeMasterRepository) {
        this.assetWarrantyTypeMasterRepository = assetWarrantyTypeMasterRepository;
    }

    /**
     * POST  /asset-warranty-type-masters : Create a new assetWarrantyTypeMaster.
     *
     * @param assetWarrantyTypeMaster the assetWarrantyTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetWarrantyTypeMaster, or with status 400 (Bad Request) if the assetWarrantyTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-warranty-type-masters")
    @Timed
    public ResponseEntity<AssetWarrantyTypeMaster> createAssetWarrantyTypeMaster(@Valid @RequestBody AssetWarrantyTypeMaster assetWarrantyTypeMaster) throws URISyntaxException {
        log.debug("REST request to save AssetWarrantyTypeMaster : {}", assetWarrantyTypeMaster);
        if (assetWarrantyTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetWarrantyTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetWarrantyTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetWarrantyTypeMaster.setCreatedDate(Instant.now());
        AssetWarrantyTypeMaster result = assetWarrantyTypeMasterRepository.save(assetWarrantyTypeMaster);
        return ResponseEntity.created(new URI("/api/asset-warranty-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-warranty-type-masters : Updates an existing assetWarrantyTypeMaster.
     *
     * @param assetWarrantyTypeMaster the assetWarrantyTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetWarrantyTypeMaster,
     * or with status 400 (Bad Request) if the assetWarrantyTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetWarrantyTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-warranty-type-masters")
    @Timed
    public ResponseEntity<AssetWarrantyTypeMaster> updateAssetWarrantyTypeMaster(@Valid @RequestBody AssetWarrantyTypeMaster assetWarrantyTypeMaster) throws URISyntaxException {
        log.debug("REST request to update AssetWarrantyTypeMaster : {}", assetWarrantyTypeMaster);
        if (assetWarrantyTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetWarrantyTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetWarrantyTypeMaster.setLastUpdatedDate(Instant.now());
        AssetWarrantyTypeMaster result = assetWarrantyTypeMasterRepository.save(assetWarrantyTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetWarrantyTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-warranty-type-masters : get all the assetWarrantyTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetWarrantyTypeMasters in body
     */
    @GetMapping("/asset-warranty-type-masters")
    @Timed
    public ResponseEntity<List<AssetWarrantyTypeMaster>> getAllAssetWarrantyTypeMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetWarrantyTypeMasters");
        Page<AssetWarrantyTypeMaster> page = assetWarrantyTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-warranty-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-warranty-type-masters/:id : get the "id" assetWarrantyTypeMaster.
     *
     * @param id the id of the assetWarrantyTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetWarrantyTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-warranty-type-masters/{id}")
    @Timed
    public ResponseEntity<AssetWarrantyTypeMaster> getAssetWarrantyTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetWarrantyTypeMaster : {}", id);
        Optional<AssetWarrantyTypeMaster> assetWarrantyTypeMaster = assetWarrantyTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetWarrantyTypeMaster);
    }

    /**
     * DELETE  /asset-warranty-type-masters/:id : delete the "id" assetWarrantyTypeMaster.
     *
     * @param id the id of the assetWarrantyTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-warranty-type-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetWarrantyTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetWarrantyTypeMaster : {}", id);

        assetWarrantyTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
