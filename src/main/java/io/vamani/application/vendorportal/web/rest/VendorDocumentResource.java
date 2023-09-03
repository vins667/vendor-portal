package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.vendorportal.domain.VendorDocument;
import io.vamani.application.vendorportal.domain.VendorDocumentTrancation;
import io.vamani.application.vendorportal.model.VendorDocumentBean;
import io.vamani.application.vendorportal.repository.VendorDocumentRepository;
import io.vamani.application.vendorportal.repository.VendorDocumentTrancationRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing VendorDocument.
 */
@RestController
@RequestMapping("/api")
public class VendorDocumentResource {

    private final Logger log = LoggerFactory.getLogger(VendorDocumentResource.class);

    private static final String ENTITY_NAME = "vendorDocument";

    private final VendorDocumentRepository vendorDocumentRepository;

    @Autowired
    private VendorDocumentTrancationRepository vendorDocumentTrancationRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public VendorDocumentResource(VendorDocumentRepository vendorDocumentRepository) {
        this.vendorDocumentRepository = vendorDocumentRepository;
    }

    /**
     * PUT  /vendor-documents : Updates an existing vendorDocument.
     *
     * @param vendorDocument the vendorDocument to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendorDocument,
     * or with status 400 (Bad Request) if the vendorDocument is not valid,
     * or with status 500 (Internal Server Error) if the vendorDocument couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendor-documents")
    @Timed
    public ResponseEntity<VendorDocument> updateVendorDocument(@Valid @RequestBody VendorDocument vendorDocument) throws URISyntaxException {
        log.debug("REST request to update VendorDocument : {}", vendorDocument);
        if (vendorDocument.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorDocument result = vendorDocumentRepository.save(vendorDocument);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendorDocument.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-documents-download/{id}")
    @Timed
    public ResponseEntity<Object> getVendorDocumentDownload(@PathVariable Long id) throws FileNotFoundException, IOException{
        log.debug("REST request to get WorkerDocumentDetails : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        VendorDocument vendorDocument = vendorDocumentRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "vendor-documents/"+vendorDocument.getDocumentFilePath());
        Path path = Paths.get(UPLOADED_FOLDER + "vendor-documents/" + vendorDocument.getDocumentFilePath());
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
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-documents-transaction-download/{id}")
    @Timed
    public ResponseEntity<Object> getVendorDocumentTransactionDownload(@PathVariable Long id) throws FileNotFoundException, IOException{
        log.debug("REST request to get WorkerDocumentDetails : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        VendorDocumentTrancation vendorDocument = vendorDocumentTrancationRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "vendor-documents/delete/"+vendorDocument.getDocumentFilePath());
        Path path = Paths.get(UPLOADED_FOLDER + "vendor-documents/delete/" + vendorDocument.getDocumentFilePath());
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
}
