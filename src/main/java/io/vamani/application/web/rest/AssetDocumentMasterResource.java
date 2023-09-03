package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.AssetDocumentMaster;
import io.vamani.application.repository.AssetDocumentMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AssetDocumentMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetDocumentMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetDocumentMasterResource.class);

    private static final String ENTITY_NAME = "assetDocumentMaster";

    private final AssetDocumentMasterRepository assetDocumentMasterRepository;

    public AssetDocumentMasterResource(AssetDocumentMasterRepository assetDocumentMasterRepository) {
        this.assetDocumentMasterRepository = assetDocumentMasterRepository;
    }

    /**
     * POST  /asset-document-masters : Create a new assetDocumentMaster.
     *
     * @param assetDocumentMaster the assetDocumentMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetDocumentMaster, or with status 400 (Bad Request) if the assetDocumentMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-document-masters")
    @Timed
    public ResponseEntity<AssetDocumentMaster> createAssetDocumentMaster(@Valid @RequestBody AssetDocumentMaster assetDocumentMaster) throws URISyntaxException {
        log.debug("REST request to save AssetDocumentMaster : {}", assetDocumentMaster);
        if (assetDocumentMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetDocumentMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetDocumentMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetDocumentMaster.setCreatedDate(Instant.now());
        AssetDocumentMaster result = assetDocumentMasterRepository.save(assetDocumentMaster);
        return ResponseEntity.created(new URI("/api/asset-document-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-document-masters : Updates an existing assetDocumentMaster.
     *
     * @param assetDocumentMaster the assetDocumentMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetDocumentMaster,
     * or with status 400 (Bad Request) if the assetDocumentMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetDocumentMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-document-masters")
    @Timed
    public ResponseEntity<AssetDocumentMaster> updateAssetDocumentMaster(@Valid @RequestBody AssetDocumentMaster assetDocumentMaster) throws URISyntaxException {
        log.debug("REST request to update AssetDocumentMaster : {}", assetDocumentMaster);
        if (assetDocumentMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetDocumentMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetDocumentMaster.setLastUpdatedDate(Instant.now());
        AssetDocumentMaster result = assetDocumentMasterRepository.save(assetDocumentMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetDocumentMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-document-masters : get all the assetDocumentMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetDocumentMasters in body
     */
    @GetMapping("/asset-document-masters")
    @Timed
    public ResponseEntity<List<AssetDocumentMaster>> getAllAssetDocumentMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of AssetDocumentMasters");
        Page<AssetDocumentMaster> page = assetDocumentMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-document-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-document-masters/:id : get the "id" assetDocumentMaster.
     *
     * @param id the id of the assetDocumentMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetDocumentMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-document-masters/{id}")
    @Timed
    public ResponseEntity<AssetDocumentMaster> getAssetDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetDocumentMaster : {}", id);
        Optional<AssetDocumentMaster> assetDocumentMaster = assetDocumentMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetDocumentMaster);
    }

    /**
     * DELETE  /asset-document-masters/:id : delete the "id" assetDocumentMaster.
     *
     * @param id the id of the assetDocumentMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-document-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetDocumentMaster : {}", id);

        assetDocumentMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
