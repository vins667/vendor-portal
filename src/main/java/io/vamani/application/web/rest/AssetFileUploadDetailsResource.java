package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.AssetFileUploadDetails;
import io.vamani.application.domain.AssetFileUploadMaster;
import io.vamani.application.model.AssetFileUploadBean;
import io.vamani.application.model.AssetFileUploadMasterBean;
import io.vamani.application.repository.AssetFileUploadDetailsRepository;
import io.vamani.application.repository.AssetFileUploadMasterRepository;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AssetFileUploadDetails.
 */
@RestController
@RequestMapping("/api")
public class AssetFileUploadDetailsResource {

    private final Logger log = LoggerFactory.getLogger(AssetFileUploadDetailsResource.class);

    private static final String ENTITY_NAME = "assetFileUploadDetails";

    private final AssetFileUploadDetailsRepository assetFileUploadDetailsRepository;

    @Autowired
    private AssetFileUploadMasterRepository assetFileUploadMasterRepository;

    public AssetFileUploadDetailsResource(AssetFileUploadDetailsRepository assetFileUploadDetailsRepository) {
        this.assetFileUploadDetailsRepository = assetFileUploadDetailsRepository;
    }

    /**
     * POST  /asset-file-upload-details : Create a new assetFileUploadDetails.
     *
     * @param assetFileUploadDetails the assetFileUploadDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetFileUploadDetails, or with status 400 (Bad Request) if the assetFileUploadDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/asset-file-upload-details")
    @Timed
    public ResponseEntity<AssetFileUploadBean> createAssetFileUploadDetails(@Valid @RequestBody AssetFileUploadDetails assetFileUploadDetails) throws URISyntaxException {
        log.debug("REST request to save AssetFileUploadDetails : {}", assetFileUploadDetails);
        if (assetFileUploadDetails.getId() != null) {
            throw new BadRequestAlertException("A new assetFileUploadDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetFileUploadDetails result = assetFileUploadDetailsRepository.save(assetFileUploadDetails);

        AssetFileUploadBean assetFileUploadBean = new AssetFileUploadBean();
        assetFileUploadBean.setAssetMaster(result.getAssetMaster());
        List<AssetFileUploadDetails> assetFileUploadDetailsList = assetFileUploadDetailsRepository.findAllByAssetMasterId(result.getAssetMaster().getId());
        assetFileUploadBean.setAssetFileUploadDetails(assetFileUploadDetailsList);

        List<AssetFileUploadMaster> assetFileUploadMasters = assetFileUploadMasterRepository.findAllByInvoiceNumberAndAssetSupplierMasterId(result.getAssetMaster().getInvoiceNumber(), result.getAssetMaster().getAssetSupplierMaster().getId());
        List<AssetFileUploadMasterBean> assetFileUploadMasterBeans = new ArrayList<>();
        for (AssetFileUploadMaster assetFileUploadMaster1 : assetFileUploadMasters) {
            AssetFileUploadMasterBean assetFileUploadMasterBean = new AssetFileUploadMasterBean();
            BeanUtils.copyProperties(assetFileUploadMaster1, assetFileUploadMasterBean);
            assetFileUploadMasterBean.setDetailExist(false);
            for (AssetFileUploadDetails fileUploadDetails : assetFileUploadDetailsList) {
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
     * PUT  /asset-file-upload-details : Updates an existing assetFileUploadDetails.
     *
     * @param assetFileUploadDetails the assetFileUploadDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetFileUploadDetails,
     * or with status 400 (Bad Request) if the assetFileUploadDetails is not valid,
     * or with status 500 (Internal Server Error) if the assetFileUploadDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-file-upload-details")
    @Timed
    public ResponseEntity<AssetFileUploadDetails> updateAssetFileUploadDetails(@Valid @RequestBody AssetFileUploadDetails assetFileUploadDetails) throws URISyntaxException {
        log.debug("REST request to update AssetFileUploadDetails : {}", assetFileUploadDetails);
        if (assetFileUploadDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssetFileUploadDetails result = assetFileUploadDetailsRepository.save(assetFileUploadDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetFileUploadDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-file-upload-details : get all the assetFileUploadDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetFileUploadDetails in body
     */
    @GetMapping("/asset-file-upload-details")
    @Timed
    public ResponseEntity<List<AssetFileUploadDetails>> getAllAssetFileUploadDetails(Pageable pageable) {
        log.debug("REST request to get a page of AssetFileUploadDetails");
        Page<AssetFileUploadDetails> page = assetFileUploadDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-file-upload-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-file-upload-details/:id : get the "id" assetFileUploadDetails.
     *
     * @param id the id of the assetFileUploadDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetFileUploadDetails, or with status 404 (Not Found)
     */
    @GetMapping("/asset-file-upload-details/{id}")
    @Timed
    public ResponseEntity<AssetFileUploadDetails> getAssetFileUploadDetails(@PathVariable Long id) {
        log.debug("REST request to get AssetFileUploadDetails : {}", id);
        Optional<AssetFileUploadDetails> assetFileUploadDetails = assetFileUploadDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetFileUploadDetails);
    }

    /**
     * DELETE  /asset-file-upload-details/:id : delete the "id" assetFileUploadDetails.
     *
     * @param id the id of the assetFileUploadDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-file-upload-details/{id}")
    @Timed
    public ResponseEntity<AssetFileUploadBean> deleteAssetFileUploadDetails(@PathVariable Long id) {
        log.debug("REST request to delete AssetFileUploadDetails : {}", id);
        AssetFileUploadDetails result = assetFileUploadDetailsRepository.findById(id).orElse(null);
        assetFileUploadDetailsRepository.deleteById(id);

        List<AssetFileUploadDetails> assetFileUploadDetails = assetFileUploadDetailsRepository.findAllByAssetFileUploadMasterId(result.getAssetFileUploadMaster().getId());

        if (assetFileUploadDetails != null && assetFileUploadDetails.size() > 0) {
        } else {
            assetFileUploadMasterRepository.deleteById(result.getAssetFileUploadMaster().getId());
        }

        AssetFileUploadBean assetFileUploadBean = new AssetFileUploadBean();
        assetFileUploadBean.setAssetMaster(result.getAssetMaster());
        List<AssetFileUploadDetails> assetFileUploadDetailsList = assetFileUploadDetailsRepository.findAllByAssetMasterId(result.getAssetMaster().getId());
        assetFileUploadBean.setAssetFileUploadDetails(assetFileUploadDetailsList);

        List<AssetFileUploadMaster> assetFileUploadMasters = assetFileUploadMasterRepository.findAllByInvoiceNumberAndAssetSupplierMasterId(result.getAssetMaster().getInvoiceNumber(), result.getAssetMaster().getAssetSupplierMaster().getId());
        List<AssetFileUploadMasterBean> assetFileUploadMasterBeans = new ArrayList<>();
        for (AssetFileUploadMaster assetFileUploadMaster1 : assetFileUploadMasters) {
            AssetFileUploadMasterBean assetFileUploadMasterBean = new AssetFileUploadMasterBean();
            BeanUtils.copyProperties(assetFileUploadMaster1, assetFileUploadMasterBean);
            assetFileUploadMasterBean.setDetailExist(false);
            for (AssetFileUploadDetails fileUploadDetails : assetFileUploadDetailsList) {
                if(fileUploadDetails.getAssetFileUploadMaster().getId().longValue() == assetFileUploadMasterBean.getId().longValue()) {
                    assetFileUploadMasterBean.setDetailExist(true);
                }
            }
            assetFileUploadMasterBeans.add(assetFileUploadMasterBean);
        }
        assetFileUploadBean.setAssetFileUploadMasters(assetFileUploadMasterBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(assetFileUploadBean));
    }
}
