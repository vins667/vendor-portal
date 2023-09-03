package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.audit.repository.SystemRepository;
import io.vamani.application.domain.*;
import io.vamani.application.model.*;
import io.vamani.application.repository.*;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing AssetMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetMasterResource.class);

    private static final String ENTITY_NAME = "assetMaster";

    private final AssetMasterRepository assetMasterRepository;

    @Autowired
    private AssetAuditDetailsRepository assetAuditDetailsRepository;

    @Autowired
    private AssetAuditSoftwareDetailsRepository assetAuditSoftwareDetailsRepository;

    @Autowired
    private AssetAuditSoftwareKeyDetailsRepository assetAuditSoftwareKeyDetailsRepository;

    @Autowired
    private AssetFileUploadMasterRepository assetFileUploadMasterRepository;

    @Autowired
    private AssetFileUploadDetailsRepository assetFileUploadDetailsRepository;

    @Autowired
    private AssetMasterAssetAuditDetailsMasterRepository assetMasterAssetAuditDetailsMasterRepository;

    public AssetMasterResource(AssetMasterRepository assetMasterRepository) {
        this.assetMasterRepository = assetMasterRepository;
    }

    /**
     * POST  /asset-masters : Create a new assetMaster.
     *
     * @param assetMaster the assetMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetMaster, or with status 400 (Bad Request) if the assetMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-masters")
    @Timed
    public ResponseEntity<AssetMaster> createAssetMaster(@Valid @RequestBody AssetMaster assetMaster) throws URISyntaxException {
        log.debug("REST request to save AssetMaster : {}", assetMaster);
        if (assetMaster.getId() != null) {
            throw new BadRequestAlertException("A new assetMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        assetMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetMaster.setCreatedDate(Instant.now());
        AssetMaster result = assetMasterRepository.save(assetMaster);
        if (assetMaster != null && assetMaster.getAssetAuditDetails() != null && assetMaster.getAssetAuditDetails().size() > 0) {
            for (AssetAuditDetails assetAuditDetails : assetMaster.getAssetAuditDetails()) {
                assetAuditDetails.setAssetCode(assetMaster.getAssetCode());
                assetAuditDetailsRepository.save(assetAuditDetails);
            }
        }
        return ResponseEntity.created(new URI("/api/asset-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /asset-masters : Updates an existing assetMaster.
     *
     * @param assetMaster the assetMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetMaster,
     * or with status 400 (Bad Request) if the assetMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-masters")
    @Timed
    public ResponseEntity<AssetMaster> updateAssetMaster(@Valid @RequestBody AssetMasterBean assetMasterBean) throws URISyntaxException {
        log.debug("REST request to update AssetMaster : {}", assetMasterBean);
        if (assetMasterBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        assetAuditDetailsRepository.deleteAssetUUIDByMasterId(assetMasterBean.getId());
        AssetMaster assetMaster = new AssetMaster();
        BeanUtils.copyProperties(assetMasterBean, assetMaster, "assetAuditDetails");
        assetMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetMaster.setLastUpdatedDate(Instant.now());
        AssetMaster result = assetMasterRepository.save(assetMaster);
        if (assetMasterBean != null && assetMasterBean.getAssetAuditDetails() != null && assetMasterBean.getAssetAuditDetails().size() > 0) {
            for (AssetAuditDetails assetAuditDetails : assetMasterBean.getAssetAuditDetails()) {
                assetAuditDetails.setAssetCode(assetMaster.getAssetCode());
                assetAuditDetailsRepository.save(assetAuditDetails);
                AssetMasterAssetAuditDetailsMaster assetMasterAssetAuditDetailsMaster = new AssetMasterAssetAuditDetailsMaster();
                assetMasterAssetAuditDetailsMaster.setId(new AssetMasterAssetAuditDetailsMasterId(assetMaster.getId(), assetAuditDetails.getUuid()));
                assetMasterAssetAuditDetailsMasterRepository.save(assetMasterAssetAuditDetailsMaster);
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-masters : get all the assetMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetMasters in body
     */
    @GetMapping("/asset-masters")
    @Timed
    public ResponseEntity<List<AssetMaster>> getAllAssetMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetMasters");
        Page<AssetMaster> page = assetMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-masters/:id : get the "id" assetMaster.
     *
     * @param id the id of the assetMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-masters/{id}")
    @Timed
    public ResponseEntity<AssetMaster> getAssetMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetMaster : {}", id);
        AssetMaster assetMaster = assetMasterRepository.findById(id).orElse(null);
        assetMaster.setAssetAuditDetails(assetMasterRepository.findByAssetId(assetMaster.getId(), assetMaster.getId()));
        return ResponseUtil.wrapOrNotFound(Optional.of(assetMaster));
    }

    /**
     * DELETE  /asset-masters/:id : delete the "id" assetMaster.
     *
     * @param id the id of the assetMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetMaster : {}", id);

        assetMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /asset-masters/:id : get the "id" assetMaster.
     *
     * @param id the id of the assetMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-masters-attachment/{id}")
    @Timed
    public ResponseEntity<AssetFileUploadBean> getAssetMasterAttachment(@PathVariable Long id) {
        log.debug("REST request to get AssetFileUploadBean : {}", id);
        AssetFileUploadBean assetFileUploadBean = new AssetFileUploadBean();
        AssetMaster assetMaster = assetMasterRepository.findById(id).orElse(null);
        assetFileUploadBean.setAssetMaster(assetMaster);
        List<AssetFileUploadDetails> assetFileUploadDetails = assetFileUploadDetailsRepository.findAllByAssetMasterId(id);
        assetFileUploadBean.setAssetFileUploadDetails(assetFileUploadDetails);

        List<AssetFileUploadMaster> assetFileUploadMasters = assetFileUploadMasterRepository.findAllByInvoiceNumberAndAssetSupplierMasterId(assetMaster.getInvoiceNumber(), assetMaster.getAssetSupplierMaster().getId());
        List<AssetFileUploadMasterBean> assetFileUploadMasterBeans = new ArrayList<>();
        for (AssetFileUploadMaster assetFileUploadMaster : assetFileUploadMasters) {
            AssetFileUploadMasterBean assetFileUploadMasterBean = new AssetFileUploadMasterBean();
            BeanUtils.copyProperties(assetFileUploadMaster, assetFileUploadMasterBean);
            assetFileUploadMasterBean.setDetailExist(false);
            for (AssetFileUploadDetails fileUploadDetails : assetFileUploadDetails) {
                if(fileUploadDetails.getAssetFileUploadMaster().getId().longValue() == assetFileUploadMasterBean.getId().longValue()) {
                    assetFileUploadMasterBean.setDetailExist(true);
                }
            }
            assetFileUploadMasterBeans.add(assetFileUploadMasterBean);
        }
        assetFileUploadBean.setAssetFileUploadMasters(assetFileUploadMasterBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(assetFileUploadBean));
    }

    /**
     * GET  /asset-masters/:id : get the "id" assetMaster.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the assetMaster, or with status 404 (Not Found)
     */
    @PostMapping("/asset-masters-compare")
    @Timed
    public ResponseEntity<AssetCompareBean> getAssetMasterCompare(@Valid @RequestBody AssetCompareBean assetCompareBean) {
        List<AssetHardwareCompareBean> assetHardwareCompareBeansTemp = new ArrayList<>();
        List<AssetHardwareCompareBean> assetHardwareCompareBeans = new ArrayList<>();

        // Hardware Comparison
        List<Object[]> oldDetails = assetAuditDetailsRepository.fetchDifferenceHardWare(Date.from(assetCompareBean.getStartTime()), Date.from(assetCompareBean.getEndTime()));
        for (Object[] objects : oldDetails) {
            AssetHardwareCompareBean assetHardwareCompareBean = new AssetHardwareCompareBean();
            assetHardwareCompareBean.setUuid(objects[0].toString());
            assetHardwareCompareBean.setName(objects[1].toString());
            assetHardwareCompareBean.setIp(objects[2].toString());
            assetHardwareCompareBean.setStorageCount(Long.parseLong(objects[3].toString()));
            assetHardwareCompareBean.setMemoryCount(Long.parseLong(objects[4].toString()));
            assetHardwareCompareBean.setSystemId(Long.parseLong(objects[5].toString()));
            assetHardwareCompareBean.setHostname(objects[6].toString());
            assetHardwareCompareBean.setType("OLD");
            assetHardwareCompareBeansTemp.add(assetHardwareCompareBean);
        }
        List<Object[]> newDetails = assetAuditDetailsRepository.fetchDifferenceHardWare(Date.from(assetCompareBean.getEndTime()), Date.from(assetCompareBean.getStartTime()));
        for (Object[] objects : newDetails) {
            AssetHardwareCompareBean assetHardwareCompareBean = new AssetHardwareCompareBean();
            assetHardwareCompareBean.setUuid(objects[0].toString());
            assetHardwareCompareBean.setName(objects[1].toString());
            assetHardwareCompareBean.setIp(objects[2].toString());
            assetHardwareCompareBean.setStorageCount(Long.parseLong(objects[3].toString()));
            assetHardwareCompareBean.setMemoryCount(Long.parseLong(objects[4].toString()));
            assetHardwareCompareBean.setSystemId(Long.parseLong(objects[5].toString()));
            assetHardwareCompareBean.setType("NEW");
            assetHardwareCompareBean.setHostname(objects[6].toString());
            assetHardwareCompareBeansTemp.add(assetHardwareCompareBean);
        }
        Collections.sort(assetHardwareCompareBeansTemp,
            Comparator.comparing(AssetHardwareCompareBean :: getUuid)
                      .thenComparing(AssetHardwareCompareBean :: getType));
        int ctr = 0;
        String prevUuid = null;
        String newUuid = null;
        String even = "#f2f3ae";
        String odd = "#ffffff";
        String colorExist = null;
        for (AssetHardwareCompareBean assetHardwareCompareBean : assetHardwareCompareBeansTemp) {
            if (ctr == 0) {
                ++ctr;
                prevUuid = assetHardwareCompareBean.getUuid();
                newUuid = assetHardwareCompareBean.getUuid();
                assetHardwareCompareBean.setColor(even);
                colorExist = "even";
                assetHardwareCompareBeans.add(assetHardwareCompareBean);
            } else {
                newUuid = assetHardwareCompareBean.getUuid();
                if (prevUuid.equalsIgnoreCase(newUuid)) {
                    if (colorExist.equalsIgnoreCase("even")) {
                        assetHardwareCompareBean.setColor(even);
                        colorExist = "even";
                    } else {
                        assetHardwareCompareBean.setColor(odd);
                        colorExist = "odd";
                    }
                    assetHardwareCompareBeans.add(assetHardwareCompareBean);
                } else {
                    prevUuid = assetHardwareCompareBean.getUuid();
                    if (colorExist.equalsIgnoreCase("even")) {
                        assetHardwareCompareBean.setColor(odd);
                        colorExist = "odd";
                    } else {
                        assetHardwareCompareBean.setColor(even);
                        colorExist = "even";
                    }
                    assetHardwareCompareBeans.add(assetHardwareCompareBean);
                }
            }
        }

        // Software Comparison
        List<AssetSoftwareCompareBean> assetSoftwareCompareBeansTemp = new ArrayList<>();
        List<AssetSoftwareCompareBean> assetSoftwareCompareBeans = new ArrayList<>();
        List<Object[]> oldSoftwareDetails = assetAuditSoftwareDetailsRepository.fetchDifferenceSoftware(Date.from(assetCompareBean.getStartTime()), Date.from(assetCompareBean.getEndTime()));
        for (Object[] objects : oldSoftwareDetails) {
            AssetSoftwareCompareBean assetSoftwareCompareBean = new AssetSoftwareCompareBean();
            assetSoftwareCompareBean.setUuid(objects[0].toString());
            assetSoftwareCompareBean.setName(objects[1].toString());
            assetSoftwareCompareBean.setPublisher(objects[2].toString());
            assetSoftwareCompareBean.setType("DELETED");
            assetSoftwareCompareBeansTemp.add(assetSoftwareCompareBean);
        }
        List<Object[]> newSoftwareDetails = assetAuditSoftwareDetailsRepository.fetchDifferenceSoftware(Date.from(assetCompareBean.getEndTime()), Date.from(assetCompareBean.getStartTime()));
        for (Object[] objects : newSoftwareDetails) {
            AssetSoftwareCompareBean assetSoftwareCompareBean = new AssetSoftwareCompareBean();
            assetSoftwareCompareBean.setUuid(objects[0].toString());
            assetSoftwareCompareBean.setName(objects[1].toString());
            assetSoftwareCompareBean.setPublisher(objects[2].toString());
            assetSoftwareCompareBean.setType("INSTALLED");
            assetSoftwareCompareBeansTemp.add(assetSoftwareCompareBean);
        }
        Collections.sort(assetSoftwareCompareBeansTemp,
            Comparator.comparing(AssetSoftwareCompareBean :: getUuid)
                .thenComparing(AssetSoftwareCompareBean :: getType)
                .thenComparing(AssetSoftwareCompareBean :: getPublisher)
                .thenComparing(AssetSoftwareCompareBean :: getName));

        ctr = 0;
        prevUuid = null;
        newUuid = null;
        even = "#f2f3ae";
        odd = "#ffffff";
        colorExist = null;
        for (AssetSoftwareCompareBean assetSoftwareCompareBean : assetSoftwareCompareBeansTemp) {
            if (ctr == 0) {
                ++ctr;
                prevUuid = assetSoftwareCompareBean.getUuid();
                newUuid = assetSoftwareCompareBean.getUuid();
                assetSoftwareCompareBean.setColor(even);
                colorExist = "even";
                assetSoftwareCompareBeans.add(assetSoftwareCompareBean);
            } else {
                newUuid = assetSoftwareCompareBean.getUuid();
                if (prevUuid.equalsIgnoreCase(newUuid)) {
                    if (colorExist.equalsIgnoreCase("even")) {
                        assetSoftwareCompareBean.setColor(even);
                        colorExist = "even";
                    } else {
                        assetSoftwareCompareBean.setColor(odd);
                        colorExist = "odd";
                    }
                    assetSoftwareCompareBeans.add(assetSoftwareCompareBean);
                } else {
                    prevUuid = assetSoftwareCompareBean.getUuid();
                    if (colorExist.equalsIgnoreCase("even")) {
                        assetSoftwareCompareBean.setColor(odd);
                        colorExist = "odd";
                    } else {
                        assetSoftwareCompareBean.setColor(even);
                        colorExist = "even";
                    }
                    assetSoftwareCompareBeans.add(assetSoftwareCompareBean);
                }
            }
        }

        // Software Key Comparison
        List<AssetSoftwareKeyCompareBean> assetSoftwareKeyCompareBeans = new ArrayList<>();
        List<AssetSoftwareKeyCompareBean> assetSoftwareKeyCompareBeansTemp = new ArrayList<>();
        List<Object[]> oldSoftwareKeyDetails = assetAuditSoftwareKeyDetailsRepository.fetchDifferenceSoftwareKey(Date.from(assetCompareBean.getStartTime()), Date.from(assetCompareBean.getEndTime()));
        for (Object[] objects : oldSoftwareKeyDetails) {
            AssetSoftwareKeyCompareBean assetSoftwareKeyCompareBean = new AssetSoftwareKeyCompareBean();
            assetSoftwareKeyCompareBean.setUuid(objects[0].toString());
            assetSoftwareKeyCompareBean.setName(objects[1].toString());
            assetSoftwareKeyCompareBean.setJhiKey(objects[2].toString());
            assetSoftwareKeyCompareBean.setType("OLD");
            assetSoftwareKeyCompareBeansTemp.add(assetSoftwareKeyCompareBean);
        }
        List<Object[]> newSoftwareKeyDetails = assetAuditSoftwareKeyDetailsRepository.fetchDifferenceSoftwareKey(Date.from(assetCompareBean.getEndTime()), Date.from(assetCompareBean.getStartTime()));
        for (Object[] objects : newSoftwareKeyDetails) {
            AssetSoftwareKeyCompareBean assetSoftwareKeyCompareBean = new AssetSoftwareKeyCompareBean();
            assetSoftwareKeyCompareBean.setUuid(objects[0].toString());
            assetSoftwareKeyCompareBean.setName(objects[1].toString());
            assetSoftwareKeyCompareBean.setJhiKey(objects[2].toString());
            assetSoftwareKeyCompareBean.setType("NEW");
            assetSoftwareKeyCompareBeansTemp.add(assetSoftwareKeyCompareBean);
        }
        Collections.sort(assetSoftwareKeyCompareBeansTemp,
            Comparator.comparing(AssetSoftwareKeyCompareBean :: getUuid)
                .thenComparing(AssetSoftwareKeyCompareBean :: getType)
                .thenComparing(AssetSoftwareKeyCompareBean :: getName));

        ctr = 0;
        prevUuid = null;
        newUuid = null;
        even = "#f2f3ae";
        odd = "#ffffff";
        colorExist = null;
        for (AssetSoftwareKeyCompareBean assetSoftwareKeyCompareBean : assetSoftwareKeyCompareBeansTemp) {
            if (ctr == 0) {
                ++ctr;
                prevUuid = assetSoftwareKeyCompareBean.getUuid();
                newUuid = assetSoftwareKeyCompareBean.getUuid();
                assetSoftwareKeyCompareBean.setColor(even);
                colorExist = "even";
                assetSoftwareKeyCompareBeans.add(assetSoftwareKeyCompareBean);
            } else {
                newUuid = assetSoftwareKeyCompareBean.getUuid();
                if (prevUuid.equalsIgnoreCase(newUuid)) {
                    if (colorExist.equalsIgnoreCase("even")) {
                        assetSoftwareKeyCompareBean.setColor(even);
                        colorExist = "even";
                    } else {
                        assetSoftwareKeyCompareBean.setColor(odd);
                        colorExist = "odd";
                    }
                    assetSoftwareKeyCompareBeans.add(assetSoftwareKeyCompareBean);
                } else {
                    prevUuid = assetSoftwareKeyCompareBean.getUuid();
                    if (colorExist.equalsIgnoreCase("even")) {
                        assetSoftwareKeyCompareBean.setColor(odd);
                        colorExist = "odd";
                    } else {
                        assetSoftwareKeyCompareBean.setColor(even);
                        colorExist = "even";
                    }
                    assetSoftwareKeyCompareBeans.add(assetSoftwareKeyCompareBean);
                }
            }
        }

        assetCompareBean.setAssetHardwareCompareBeans(assetHardwareCompareBeans);
        assetCompareBean.setAssetSoftwareCompareBeans(assetSoftwareCompareBeans);
        assetCompareBean.setAssetSoftwareKeyCompareBeans(assetSoftwareKeyCompareBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(assetCompareBean));
    }
}
