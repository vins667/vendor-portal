package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.AssetAuditSoftwareKeyDetails;
import io.vamani.application.repository.AssetAuditSoftwareKeyDetailsRepository;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AssetAuditSoftwareKeyDetails.
 */
@RestController
@RequestMapping("/api")
public class AssetAuditSoftwareKeyDetailsResource {

    private final Logger log = LoggerFactory.getLogger(AssetAuditSoftwareKeyDetailsResource.class);

    private static final String ENTITY_NAME = "assetAuditSoftwareKeyDetails";

    private final AssetAuditSoftwareKeyDetailsRepository assetAuditSoftwareKeyDetailsRepository;

    public AssetAuditSoftwareKeyDetailsResource(AssetAuditSoftwareKeyDetailsRepository assetAuditSoftwareKeyDetailsRepository) {
        this.assetAuditSoftwareKeyDetailsRepository = assetAuditSoftwareKeyDetailsRepository;
    }

    /**
     * POST  /asset-audit-software-key-details : Create a new assetAuditSoftwareKeyDetails.
     *
     * @param assetAuditSoftwareKeyDetails the assetAuditSoftwareKeyDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetAuditSoftwareKeyDetails, or with status 400 (Bad Request) if the assetAuditSoftwareKeyDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-audit-software-key-details")
    @Timed
    public ResponseEntity<AssetAuditSoftwareKeyDetails> createAssetAuditSoftwareKeyDetails(@Valid @RequestBody AssetAuditSoftwareKeyDetails assetAuditSoftwareKeyDetails) throws URISyntaxException {
        log.debug("REST request to save AssetAuditSoftwareKeyDetails : {}", assetAuditSoftwareKeyDetails);
        if (assetAuditSoftwareKeyDetails.getId() != null) {
            throw new BadRequestAlertException("A new assetAuditSoftwareKeyDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetAuditSoftwareKeyDetails result = assetAuditSoftwareKeyDetailsRepository.save(assetAuditSoftwareKeyDetails);
        return ResponseEntity.created(new URI("/api/asset-audit-software-key-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-audit-software-key-details : Updates an existing assetAuditSoftwareKeyDetails.
     *
     * @param assetAuditSoftwareKeyDetails the assetAuditSoftwareKeyDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetAuditSoftwareKeyDetails,
     * or with status 400 (Bad Request) if the assetAuditSoftwareKeyDetails is not valid,
     * or with status 500 (Internal Server Error) if the assetAuditSoftwareKeyDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-audit-software-key-details")
    @Timed
    public ResponseEntity<AssetAuditSoftwareKeyDetails> updateAssetAuditSoftwareKeyDetails(@Valid @RequestBody AssetAuditSoftwareKeyDetails assetAuditSoftwareKeyDetails) throws URISyntaxException {
        log.debug("REST request to update AssetAuditSoftwareKeyDetails : {}", assetAuditSoftwareKeyDetails);
        if (assetAuditSoftwareKeyDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssetAuditSoftwareKeyDetails result = assetAuditSoftwareKeyDetailsRepository.save(assetAuditSoftwareKeyDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetAuditSoftwareKeyDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-audit-software-key-details : get all the assetAuditSoftwareKeyDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetAuditSoftwareKeyDetails in body
     */
    @GetMapping("/asset-audit-software-key-details")
    @Timed
    public ResponseEntity<List<AssetAuditSoftwareKeyDetails>> getAllAssetAuditSoftwareKeyDetails(Pageable pageable) {
        log.debug("REST request to get a page of AssetAuditSoftwareKeyDetails");
        Page<AssetAuditSoftwareKeyDetails> page = assetAuditSoftwareKeyDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-audit-software-key-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-audit-software-key-details/:id : get the "id" assetAuditSoftwareKeyDetails.
     *
     * @param id the id of the assetAuditSoftwareKeyDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetAuditSoftwareKeyDetails, or with status 404 (Not Found)
     */
    @GetMapping("/asset-audit-software-key-details/{id}")
    @Timed
    public ResponseEntity<AssetAuditSoftwareKeyDetails> getAssetAuditSoftwareKeyDetails(@PathVariable Long id) {
        log.debug("REST request to get AssetAuditSoftwareKeyDetails : {}", id);
        Optional<AssetAuditSoftwareKeyDetails> assetAuditSoftwareKeyDetails = assetAuditSoftwareKeyDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetAuditSoftwareKeyDetails);
    }

    /**
     * DELETE  /asset-audit-software-key-details/:id : delete the "id" assetAuditSoftwareKeyDetails.
     *
     * @param id the id of the assetAuditSoftwareKeyDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-audit-software-key-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetAuditSoftwareKeyDetails(@PathVariable Long id) {
        log.debug("REST request to delete AssetAuditSoftwareKeyDetails : {}", id);

        assetAuditSoftwareKeyDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
