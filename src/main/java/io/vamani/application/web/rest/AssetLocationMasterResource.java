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
import io.vamani.application.domain.AssetLocationMaster;
import io.vamani.application.repository.AssetLocationMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetLocationMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetLocationMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetLocationMasterResource.class);

    private static final String ENTITY_NAME = "assetLocationMaster";

    private final AssetLocationMasterRepository assetLocationMasterRepository;

    public AssetLocationMasterResource(AssetLocationMasterRepository assetLocationMasterRepository) {
        this.assetLocationMasterRepository = assetLocationMasterRepository;
    }

    /**
     * POST  /asset-location-masters : Create a new assetLocationMaster.
     *
     * @param assetLocationMaster the assetLocationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetLocationMaster, or with status 400 (Bad Request) if the assetLocationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-location-masters")
    @Timed
    public ResponseEntity<AssetLocationMaster> createAssetLocationMaster(@Valid @RequestBody AssetLocationMaster assetLocationMaster) throws URISyntaxException {
        log.debug("REST request to save AssetLocationMaster : {}", assetLocationMaster);
        if (assetLocationMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetLocationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetLocationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetLocationMaster.setCreatedDate(Instant.now());
        AssetLocationMaster result = assetLocationMasterRepository.save(assetLocationMaster);
        return ResponseEntity.created(new URI("/api/asset-location-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-location-masters : Updates an existing assetLocationMaster.
     *
     * @param assetLocationMaster the assetLocationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetLocationMaster,
     * or with status 400 (Bad Request) if the assetLocationMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetLocationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-location-masters")
    @Timed
    public ResponseEntity<AssetLocationMaster> updateAssetLocationMaster(@Valid @RequestBody AssetLocationMaster assetLocationMaster) throws URISyntaxException {
        log.debug("REST request to update AssetLocationMaster : {}", assetLocationMaster);
        if (assetLocationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetLocationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetLocationMaster.setLastUpdatedDate(Instant.now());
        AssetLocationMaster result = assetLocationMasterRepository.save(assetLocationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetLocationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-location-masters : get all the assetLocationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetLocationMasters in body
     */
    @GetMapping("/asset-location-masters")
    @Timed
    public ResponseEntity<List<AssetLocationMaster>> getAllAssetLocationMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetLocationMasters");
        Page<AssetLocationMaster> page = assetLocationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-location-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-location-masters/:id : get the "id" assetLocationMaster.
     *
     * @param id the id of the assetLocationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetLocationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-location-masters/{id}")
    @Timed
    public ResponseEntity<AssetLocationMaster> getAssetLocationMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetLocationMaster : {}", id);
        Optional<AssetLocationMaster> assetLocationMaster = assetLocationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetLocationMaster);
    }

    /**
     * DELETE  /asset-location-masters/:id : delete the "id" assetLocationMaster.
     *
     * @param id the id of the assetLocationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-location-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetLocationMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetLocationMaster : {}", id);

        assetLocationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
