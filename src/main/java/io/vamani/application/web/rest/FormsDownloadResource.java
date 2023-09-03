package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.FormsDownload;
import io.vamani.application.repository.FormsDownloadRepository;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FormsDownload.
 */
@RestController
@RequestMapping("/api")
public class FormsDownloadResource {

    private final Logger log = LoggerFactory.getLogger(FormsDownloadResource.class);

    private static final String ENTITY_NAME = "formsDownload";

    private final FormsDownloadRepository formsDownloadRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public FormsDownloadResource(FormsDownloadRepository formsDownloadRepository) {
        this.formsDownloadRepository = formsDownloadRepository;
    }

    /**
     * POST  /forms-downloads : Create a new formsDownload.
     *
     * @param formsDownload the formsDownload to create
     * @return the ResponseEntity with status 201 (Created) and with body the new formsDownload, or with status 400 (Bad Request) if the formsDownload has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/forms-downloads")
    @Timed
    public ResponseEntity<FormsDownload> createFormsDownload(@Valid @RequestBody FormsDownload formsDownload) throws URISyntaxException {
        log.debug("REST request to save FormsDownload : {}", formsDownload);
        if (formsDownload.getId() != null) {
            throw new BadRequestAlertException("A new formsDownload cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormsDownload result = formsDownloadRepository.save(formsDownload);
        return ResponseEntity.created(new URI("/api/forms-downloads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/forms-downloads-download/{id}")
    @Timed
    public ResponseEntity<Object> getFormsDownloads(@PathVariable Long id) throws FileNotFoundException, IOException {
        log.debug("REST request to get Feedback : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        FormsDownload formsDownload = formsDownloadRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "forms/"+formsDownload.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "forms/" + formsDownload.getFileName());
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
     * PUT  /forms-downloads : Updates an existing formsDownload.
     *
     * @param formsDownload the formsDownload to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated formsDownload,
     * or with status 400 (Bad Request) if the formsDownload is not valid,
     * or with status 500 (Internal Server Error) if the formsDownload couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/forms-downloads")
    @Timed
    public ResponseEntity<FormsDownload> updateFormsDownload(@Valid @RequestBody FormsDownload formsDownload) throws URISyntaxException {
        log.debug("REST request to update FormsDownload : {}", formsDownload);
        if (formsDownload.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormsDownload result = formsDownloadRepository.save(formsDownload);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, formsDownload.getId().toString()))
            .body(result);
    }

    /**
     * GET  /forms-downloads : get all the formsDownloads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of formsDownloads in body
     */
    @GetMapping("/forms-downloads")
    @Timed
    public List<FormsDownload> getAllFormsDownloads() {
        log.debug("REST request to get all FormsDownloads");
        List<FormsDownload> formsDownloadsTemp = formsDownloadRepository.findAll();
        List<FormsDownload> formsDownloads = new ArrayList<>();
        for (FormsDownload formsDownload : formsDownloadsTemp) {
            try {
                formsDownload.setFileName(MD5UrlEncryption.fakeUploadUrl("forms/" + formsDownload.getFileName()));
            } catch (Exception e) {
            }
            formsDownloads.add(formsDownload);
        }
        return formsDownloads;
    }

    /**
     * GET  /forms-downloads/:id : get the "id" formsDownload.
     *
     * @param id the id of the formsDownload to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the formsDownload, or with status 404 (Not Found)
     */
    @GetMapping("/forms-downloads/{id}")
    @Timed
    public ResponseEntity<FormsDownload> getFormsDownload(@PathVariable Long id) {
        log.debug("REST request to get FormsDownload : {}", id);
        Optional<FormsDownload> formsDownload = formsDownloadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(formsDownload);
    }

    /**
     * DELETE  /forms-downloads/:id : delete the "id" formsDownload.
     *
     * @param id the id of the formsDownload to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/forms-downloads/{id}")
    @Timed
    public ResponseEntity<Void> deleteFormsDownload(@PathVariable Long id) {
        log.debug("REST request to delete FormsDownload : {}", id);

        formsDownloadRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
