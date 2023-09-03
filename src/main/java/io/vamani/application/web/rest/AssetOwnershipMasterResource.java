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
import io.vamani.application.domain.AssetOwnershipMaster;
import io.vamani.application.repository.AssetOwnershipMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetOwnershipMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetOwnershipMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetOwnershipMasterResource.class);

    private static final String ENTITY_NAME = "assetOwnershipMaster";

    private final AssetOwnershipMasterRepository assetOwnershipMasterRepository;

    public AssetOwnershipMasterResource(AssetOwnershipMasterRepository assetOwnershipMasterRepository) {
        this.assetOwnershipMasterRepository = assetOwnershipMasterRepository;
    }

    /**
     * POST  /asset-ownership-masters : Create a new assetOwnershipMaster.
     *
     * @param assetOwnershipMaster the assetOwnershipMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetOwnershipMaster, or with status 400 (Bad Request) if the assetOwnershipMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-ownership-masters")
    @Timed
    public ResponseEntity<AssetOwnershipMaster> createAssetOwnershipMaster(@Valid @RequestBody AssetOwnershipMaster assetOwnershipMaster) throws URISyntaxException {
        log.debug("REST request to save AssetOwnershipMaster : {}", assetOwnershipMaster);
        if (assetOwnershipMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetOwnershipMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetOwnershipMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetOwnershipMaster.setCreatedDate(Instant.now());
        AssetOwnershipMaster result = assetOwnershipMasterRepository.save(assetOwnershipMaster);
        return ResponseEntity.created(new URI("/api/asset-ownership-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-ownership-masters : Updates an existing assetOwnershipMaster.
     *
     * @param assetOwnershipMaster the assetOwnershipMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetOwnershipMaster,
     * or with status 400 (Bad Request) if the assetOwnershipMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetOwnershipMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-ownership-masters")
    @Timed
    public ResponseEntity<AssetOwnershipMaster> updateAssetOwnershipMaster(@Valid @RequestBody AssetOwnershipMaster assetOwnershipMaster) throws URISyntaxException {
        log.debug("REST request to update AssetOwnershipMaster : {}", assetOwnershipMaster);
        if (assetOwnershipMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetOwnershipMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetOwnershipMaster.setLastUpdatedDate(Instant.now());
        AssetOwnershipMaster result = assetOwnershipMasterRepository.save(assetOwnershipMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetOwnershipMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-ownership-masters : get all the assetOwnershipMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetOwnershipMasters in body
     */
    @GetMapping("/asset-ownership-masters")
    @Timed
    public ResponseEntity<List<AssetOwnershipMaster>> getAllAssetOwnershipMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetOwnershipMasters");
        Page<AssetOwnershipMaster> page = assetOwnershipMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-ownership-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-ownership-masters/:id : get the "id" assetOwnershipMaster.
     *
     * @param id the id of the assetOwnershipMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetOwnershipMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-ownership-masters/{id}")
    @Timed
    public ResponseEntity<AssetOwnershipMaster> getAssetOwnershipMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetOwnershipMaster : {}", id);
        Optional<AssetOwnershipMaster> assetOwnershipMaster = assetOwnershipMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetOwnershipMaster);
    }

    /**
     * DELETE  /asset-ownership-masters/:id : delete the "id" assetOwnershipMaster.
     *
     * @param id the id of the assetOwnershipMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-ownership-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetOwnershipMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetOwnershipMaster : {}", id);

        assetOwnershipMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
