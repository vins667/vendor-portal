package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.AssetCompanyMaster;
import io.vamani.application.repository.AssetCompanyMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing AssetCompanyMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetCompanyMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetCompanyMasterResource.class);

    private static final String ENTITY_NAME = "assetCompanyMaster";

    private final AssetCompanyMasterRepository assetCompanyMasterRepository;

    public AssetCompanyMasterResource(AssetCompanyMasterRepository assetCompanyMasterRepository) {
        this.assetCompanyMasterRepository = assetCompanyMasterRepository;
    }

    /**
     * POST  /asset-company-masters : Create a new assetCompanyMaster.
     *
     * @param assetCompanyMaster the assetCompanyMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetCompanyMaster, or with status 400 (Bad Request) if the assetCompanyMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-company-masters")
    @Timed
    public ResponseEntity<AssetCompanyMaster> createAssetCompanyMaster(@Valid @RequestBody AssetCompanyMaster assetCompanyMaster) throws URISyntaxException {
        log.debug("REST request to save AssetCompanyMaster : {}", assetCompanyMaster);
        if (assetCompanyMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetCompanyMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetCompanyMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetCompanyMaster.setCreatedDate(Instant.now());
        AssetCompanyMaster result = assetCompanyMasterRepository.save(assetCompanyMaster);
        return ResponseEntity.created(new URI("/api/asset-company-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-company-masters : Updates an existing assetCompanyMaster.
     *
     * @param assetCompanyMaster the assetCompanyMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetCompanyMaster,
     * or with status 400 (Bad Request) if the assetCompanyMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetCompanyMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-company-masters")
    @Timed
    public ResponseEntity<AssetCompanyMaster> updateAssetCompanyMaster(@Valid @RequestBody AssetCompanyMaster assetCompanyMaster) throws URISyntaxException {
        log.debug("REST request to update AssetCompanyMaster : {}", assetCompanyMaster);
        if (assetCompanyMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetCompanyMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetCompanyMaster.setLastUpdatedDate(Instant.now());
        AssetCompanyMaster result = assetCompanyMasterRepository.save(assetCompanyMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetCompanyMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-company-masters : get all the assetCompanyMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetCompanyMasters in body
     */
    @GetMapping("/asset-company-masters")
    @Timed
    public ResponseEntity<List<AssetCompanyMaster>> getAllAssetCompanyMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetCompanyMasters");
        Page<AssetCompanyMaster> page = assetCompanyMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-company-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-company-masters/:id : get the "id" assetCompanyMaster.
     *
     * @param id the id of the assetCompanyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetCompanyMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-company-masters/{id}")
    @Timed
    public ResponseEntity<AssetCompanyMaster> getAssetCompanyMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetCompanyMaster : {}", id);
        Optional<AssetCompanyMaster> assetCompanyMaster = assetCompanyMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetCompanyMaster);
    }

    /**
     * DELETE  /asset-company-masters/:id : delete the "id" assetCompanyMaster.
     *
     * @param id the id of the assetCompanyMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-company-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetCompanyMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetCompanyMaster : {}", id);

        assetCompanyMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
