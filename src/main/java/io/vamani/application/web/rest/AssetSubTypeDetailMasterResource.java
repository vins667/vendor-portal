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
import io.vamani.application.domain.AssetSubTypeDetailMaster;
import io.vamani.application.repository.AssetSubTypeDetailMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetSubTypeDetailMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetSubTypeDetailMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetSubTypeDetailMasterResource.class);

    private static final String ENTITY_NAME = "assetSubTypeDetailMaster";

    private final AssetSubTypeDetailMasterRepository assetSubTypeDetailMasterRepository;

    public AssetSubTypeDetailMasterResource(AssetSubTypeDetailMasterRepository assetSubTypeDetailMasterRepository) {
        this.assetSubTypeDetailMasterRepository = assetSubTypeDetailMasterRepository;
    }

    /**
     * POST  /asset-sub-type-detail-masters : Create a new assetSubTypeDetailMaster.
     *
     * @param assetSubTypeDetailMaster the assetSubTypeDetailMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetSubTypeDetailMaster, or with status 400 (Bad Request) if the assetSubTypeDetailMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-sub-type-detail-masters")
    @Timed
    public ResponseEntity<AssetSubTypeDetailMaster> createAssetSubTypeDetailMaster(@Valid @RequestBody AssetSubTypeDetailMaster assetSubTypeDetailMaster) throws URISyntaxException {
        log.debug("REST request to save AssetSubTypeDetailMaster : {}", assetSubTypeDetailMaster);
        if (assetSubTypeDetailMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetSubTypeDetailMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetSubTypeDetailMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSubTypeDetailMaster.setCreatedDate(Instant.now());
        AssetSubTypeDetailMaster result = assetSubTypeDetailMasterRepository.save(assetSubTypeDetailMaster);
        return ResponseEntity.created(new URI("/api/asset-sub-type-detail-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-sub-type-detail-masters : Updates an existing assetSubTypeDetailMaster.
     *
     * @param assetSubTypeDetailMaster the assetSubTypeDetailMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetSubTypeDetailMaster,
     * or with status 400 (Bad Request) if the assetSubTypeDetailMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetSubTypeDetailMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-sub-type-detail-masters")
    @Timed
    public ResponseEntity<AssetSubTypeDetailMaster> updateAssetSubTypeDetailMaster(@Valid @RequestBody AssetSubTypeDetailMaster assetSubTypeDetailMaster) throws URISyntaxException {
        log.debug("REST request to update AssetSubTypeDetailMaster : {}", assetSubTypeDetailMaster);
        if (assetSubTypeDetailMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetSubTypeDetailMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSubTypeDetailMaster.setLastUpdatedDate(Instant.now());
        AssetSubTypeDetailMaster result = assetSubTypeDetailMasterRepository.save(assetSubTypeDetailMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetSubTypeDetailMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-sub-type-detail-masters : get all the assetSubTypeDetailMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetSubTypeDetailMasters in body
     */
    @GetMapping("/asset-sub-type-detail-masters")
    @Timed
    public ResponseEntity<List<AssetSubTypeDetailMaster>> getAllAssetSubTypeDetailMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetSubTypeDetailMasters");
        Page<AssetSubTypeDetailMaster> page = assetSubTypeDetailMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-sub-type-detail-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-sub-type-detail-masters/:id : get the "id" assetSubTypeDetailMaster.
     *
     * @param id the id of the assetSubTypeDetailMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetSubTypeDetailMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-sub-type-detail-masters/{id}")
    @Timed
    public ResponseEntity<AssetSubTypeDetailMaster> getAssetSubTypeDetailMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetSubTypeDetailMaster : {}", id);
        Optional<AssetSubTypeDetailMaster> assetSubTypeDetailMaster = assetSubTypeDetailMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetSubTypeDetailMaster);
    }

    /**
     * DELETE  /asset-sub-type-detail-masters/:id : delete the "id" assetSubTypeDetailMaster.
     *
     * @param id the id of the assetSubTypeDetailMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-sub-type-detail-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetSubTypeDetailMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetSubTypeDetailMaster : {}", id);

        assetSubTypeDetailMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /asset-sub-type-detail-masters : get all the assetSubTypeDetailMaster.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetSubTypeDetailMaster in body
     */
    @GetMapping("/asset-sub-type-detail-masters-asset-types/{id}")
    @Timed
    public ResponseEntity<List<AssetSubTypeDetailMaster>> getAllAssetSubTypeDetailMasterByAssetTypes(@PathVariable Long id) {
        log.debug("REST request to get a page of AssetSubTypeMaster");
        List<AssetSubTypeDetailMaster> list = assetSubTypeDetailMasterRepository.findByAssetSubTypeMaster(id);
        return ResponseEntity.ok().body(list);
    }
}
