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
import io.vamani.application.domain.AssetSupplierMaster;
import io.vamani.application.repository.AssetSupplierMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing AssetSupplierMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetSupplierMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetSupplierMasterResource.class);

    private static final String ENTITY_NAME = "assetSupplierMaster";

    private final AssetSupplierMasterRepository assetSupplierMasterRepository;

    public AssetSupplierMasterResource(AssetSupplierMasterRepository assetSupplierMasterRepository) {
        this.assetSupplierMasterRepository = assetSupplierMasterRepository;
    }

    /**
     * POST  /asset-supplier-masters : Create a new assetSupplierMaster.
     *
     * @param assetSupplierMaster the assetSupplierMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetSupplierMaster, or with status 400 (Bad Request) if the assetSupplierMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-supplier-masters")
    @Timed
    public ResponseEntity<AssetSupplierMaster> createAssetSupplierMaster(@Valid @RequestBody AssetSupplierMaster assetSupplierMaster) throws URISyntaxException {
        log.debug("REST request to save AssetSupplierMaster : {}", assetSupplierMaster);
        if (assetSupplierMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetSupplierMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetSupplierMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSupplierMaster.setCreatedDate(Instant.now());
        AssetSupplierMaster result = assetSupplierMasterRepository.save(assetSupplierMaster);
        return ResponseEntity.created(new URI("/api/asset-supplier-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-supplier-masters : Updates an existing assetSupplierMaster.
     *
     * @param assetSupplierMaster the assetSupplierMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetSupplierMaster,
     * or with status 400 (Bad Request) if the assetSupplierMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetSupplierMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-supplier-masters")
    @Timed
    public ResponseEntity<AssetSupplierMaster> updateAssetSupplierMaster(@Valid @RequestBody AssetSupplierMaster assetSupplierMaster) throws URISyntaxException {
        log.debug("REST request to update AssetSupplierMaster : {}", assetSupplierMaster);
        if (assetSupplierMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetSupplierMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetSupplierMaster.setLastUpdatedDate(Instant.now());
        AssetSupplierMaster result = assetSupplierMasterRepository.save(assetSupplierMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetSupplierMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-supplier-masters : get all the assetSupplierMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetSupplierMasters in body
     */
    @GetMapping("/asset-supplier-masters")
    @Timed
    public ResponseEntity<List<AssetSupplierMaster>> getAllAssetSupplierMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetSupplierMasters");
        Page<AssetSupplierMaster> page = assetSupplierMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-supplier-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-supplier-masters/:id : get the "id" assetSupplierMaster.
     *
     * @param id the id of the assetSupplierMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetSupplierMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-supplier-masters/{id}")
    @Timed
    public ResponseEntity<AssetSupplierMaster> getAssetSupplierMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetSupplierMaster : {}", id);
        Optional<AssetSupplierMaster> assetSupplierMaster = assetSupplierMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetSupplierMaster);
    }

    /**
     * DELETE  /asset-supplier-masters/:id : delete the "id" assetSupplierMaster.
     *
     * @param id the id of the assetSupplierMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-supplier-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetSupplierMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetSupplierMaster : {}", id);

        assetSupplierMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
