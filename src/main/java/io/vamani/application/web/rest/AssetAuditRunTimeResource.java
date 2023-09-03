package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.AssetAuditRunTime;
import io.vamani.application.repository.AssetAuditRunTimeRepository;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing AssetAuditRunTime.
 */
@RestController
@RequestMapping("/api")
public class AssetAuditRunTimeResource {
    private final Logger log = LoggerFactory.getLogger(AssetAuditRunTimeResource.class);
    private static final String ENTITY_NAME = "assetAuditRunTime";
    private final AssetAuditRunTimeRepository assetAuditRunTimeRepository;

    public AssetAuditRunTimeResource(AssetAuditRunTimeRepository assetAuditRunTimeRepository) {
        this.assetAuditRunTimeRepository = assetAuditRunTimeRepository;
    }

    /**
     * GET  /asset-audit-details : get all the assetAuditDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetAuditDetails in body
     */
    @GetMapping("/asset-audit-run-times")
    @Timed
    public ResponseEntity<List<AssetAuditRunTime>> getAllAssetAuditRunTime(@PageableDefault(value = 50) @SortDefault(sort = "runTime", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("REST request to get a page of AssetAuditRunTime");
        Page<AssetAuditRunTime> page = assetAuditRunTimeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-audit-run-times");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-audit-details : get all the assetAuditDetails.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetAuditDetails in body
     */
    @PostMapping("/asset-audit-run-times")
    @Timed
    public ResponseEntity<List<AssetAuditRunTime>> getAllAssetAuditRunTime(@RequestBody AssetAuditRunTime assetAuditRunTime) {
        log.debug("REST request to get a page of AssetAuditRunTime");
        List<AssetAuditRunTime> assetAuditRunTimes = assetAuditRunTimeRepository.findAllGreaterThanId(assetAuditRunTime.getRunTime());
        return ResponseEntity.ok().body(assetAuditRunTimes);
    }
}
