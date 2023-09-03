package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.AssetFileUploadDetails;
import io.vamani.application.domain.AssetFileUploadMaster;
import io.vamani.application.domain.AssetMaster;
import io.vamani.application.domain.AssetSupplierMaster;
import io.vamani.application.model.AssetFileUploadBean;
import io.vamani.application.model.AssetFileUploadMasterBean;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AssetFileUploadMaster.
 */
@RestController
@RequestMapping("/api")
public class AssetFileUploadMasterResource {

    private final Logger log = LoggerFactory.getLogger(AssetFileUploadMasterResource.class);

    private static final String ENTITY_NAME = "assetFileUploadMaster";

    private final AssetFileUploadMasterRepository assetFileUploadMasterRepository;

    @Autowired
    private AssetDocumentMasterRepository assetDocumentMasterRepository;

    @Autowired
    private AssetSupplierMasterRepository assetSupplierMasterRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private AssetMasterRepository assetMasterRepository;

    @Autowired
    private AssetFileUploadDetailsRepository assetFileUploadDetailsRepository;

    public AssetFileUploadMasterResource(AssetFileUploadMasterRepository assetFileUploadMasterRepository) {
        this.assetFileUploadMasterRepository = assetFileUploadMasterRepository;
    }

    /**
     * POST  /asset-file-upload-masters : Create a new assetFileUploadMaster.
     *
     * @param !assetFileUploadMaster the assetFileUploadMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetFileUploadMaster, or with status 400 (Bad Request) if the assetFileUploadMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/asset-file-upload-masters", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<AssetFileUploadBean> createAssetFileUploadMaster(@RequestParam(required = false) MultipartFile file, String invoiceNumber, String assetSupplierId, String assetDocumentId, String id) throws URISyntaxException, IOException {
        log.debug("REST request to save AssetFileUploadMaster : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        AssetFileUploadMaster assetFileUploadMaster = new AssetFileUploadMaster();
        assetFileUploadMaster.setInvoiceNumber(invoiceNumber);
        assetFileUploadMaster.setAssetDocumentMaster(assetDocumentMasterRepository.findById(Long.parseLong(assetDocumentId)).orElse(null));
        assetFileUploadMaster.setAssetSupplierMaster(assetSupplierMasterRepository.findById(Long.parseLong(assetSupplierId)).orElse(null));
        assetFileUploadMaster.setFileName("DEMO");
        assetFileUploadMaster.setDisplayFileName(file.getOriginalFilename());
        assetFileUploadMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetFileUploadMaster.setCreatedDate(Instant.now());
        AssetFileUploadMaster result = assetFileUploadMasterRepository.save(assetFileUploadMaster);
        String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        String fileName = result.getId() + extn;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + "asset/" + fileName);
        Files.write(path, bytes);
        result.setFileName(fileName);
        result = assetFileUploadMasterRepository.save(result);

        AssetMaster assetMaster = assetMasterRepository.findById(Long.parseLong(id)).orElse(null);
        AssetFileUploadDetails assetFileUploadDetails = new AssetFileUploadDetails();
        assetFileUploadDetails.setAssetMaster(assetMaster);
        assetFileUploadDetails.setAssetFileUploadMaster(result);
        assetFileUploadDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        assetFileUploadDetails.setCreatedDate(Instant.now());
        AssetFileUploadDetails result1 = assetFileUploadDetailsRepository.save(assetFileUploadDetails);

        AssetFileUploadBean assetFileUploadBean = new AssetFileUploadBean();
        assetFileUploadBean.setAssetMaster(assetMaster);
        List<AssetFileUploadDetails> assetFileUploadDetailsList = assetFileUploadDetailsRepository.findAllByAssetMasterId(Long.parseLong(id));
        assetFileUploadBean.setAssetFileUploadDetails(assetFileUploadDetailsList);

        List<AssetFileUploadMaster> assetFileUploadMasters = assetFileUploadMasterRepository.findAllByInvoiceNumberAndAssetSupplierMasterId(assetMaster.getInvoiceNumber(), assetMaster.getAssetSupplierMaster().getId());
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
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/asset-file-upload-masters-download/{id}")
    @Timed
    public ResponseEntity<Object> getAssetFileUploadMasterDownload(@PathVariable Long id) throws FileNotFoundException, IOException {
        log.debug("REST request to get AssetFileUploadMaster : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        AssetFileUploadMaster assetFileUploadMaster = assetFileUploadMasterRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "asset/"+assetFileUploadMaster.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "asset/" + assetFileUploadMaster.getFileName());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        String mimeType = Files.probeContentType(path);
        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType(mimeType)).body(resource);
        return responseEntity;
    }

    /**
     * PUT  /asset-file-upload-masters : Updates an existing assetFileUploadMaster.
     *
     * @param assetFileUploadMaster the assetFileUploadMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetFileUploadMaster,
     * or with status 400 (Bad Request) if the assetFileUploadMaster is not valid,
     * or with status 500 (Internal Server Error) if the assetFileUploadMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/asset-file-upload-masters")
    @Timed
    public ResponseEntity<AssetFileUploadMaster> updateAssetFileUploadMaster(@Valid @RequestBody AssetFileUploadMaster assetFileUploadMaster) throws URISyntaxException {
        log.debug("REST request to update AssetFileUploadMaster : {}", assetFileUploadMaster);
        if (assetFileUploadMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssetFileUploadMaster result = assetFileUploadMasterRepository.save(assetFileUploadMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetFileUploadMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /asset-file-upload-masters : get all the assetFileUploadMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of assetFileUploadMasters in body
     */
    @GetMapping("/asset-file-upload-masters")
    @Timed
    public ResponseEntity<List<AssetFileUploadMaster>> getAllAssetFileUploadMasters(Pageable pageable) {
        log.debug("REST request to get a page of AssetFileUploadMasters");
        Page<AssetFileUploadMaster> page = assetFileUploadMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/asset-file-upload-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /asset-file-upload-masters/:id : get the "id" assetFileUploadMaster.
     *
     * @param id the id of the assetFileUploadMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetFileUploadMaster, or with status 404 (Not Found)
     */
    @GetMapping("/asset-file-upload-masters/{id}")
    @Timed
    public ResponseEntity<AssetFileUploadMaster> getAssetFileUploadMaster(@PathVariable Long id) {
        log.debug("REST request to get AssetFileUploadMaster : {}", id);
        Optional<AssetFileUploadMaster> assetFileUploadMaster = assetFileUploadMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assetFileUploadMaster);
    }

    /**
     * DELETE  /asset-file-upload-masters/:id : delete the "id" assetFileUploadMaster.
     *
     * @param id the id of the assetFileUploadMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/asset-file-upload-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetFileUploadMaster(@PathVariable Long id) {
        log.debug("REST request to delete AssetFileUploadMaster : {}", id);

        assetFileUploadMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
