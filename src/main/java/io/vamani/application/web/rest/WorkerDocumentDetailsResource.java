package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.WorkerDocumentDetails;
import io.vamani.application.repository.WorkerDocumentDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkerDocumentDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkerDocumentDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkerDocumentDetailsResource.class);

    private static final String ENTITY_NAME = "workerDocumentDetails";

    private final WorkerDocumentDetailsRepository workerDocumentDetailsRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public WorkerDocumentDetailsResource(WorkerDocumentDetailsRepository workerDocumentDetailsRepository) {
        this.workerDocumentDetailsRepository = workerDocumentDetailsRepository;
    }

    /**
     * POST  /worker-document-details : Create a new workerDocumentDetails.
     *
     * @param workerDocumentDetails the workerDocumentDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerDocumentDetails, or with status 400 (Bad Request) if the workerDocumentDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worker-document-details")
    @Timed
    public ResponseEntity<WorkerDocumentDetails> createWorkerDocumentDetails(@Valid @RequestBody WorkerDocumentDetails workerDocumentDetails) throws URISyntaxException {
        log.debug("REST request to save WorkerDocumentDetails : {}", workerDocumentDetails);
        if (workerDocumentDetails.getId() != null) {
            throw new BadRequestAlertException("A new workerDocumentDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkerDocumentDetails result = workerDocumentDetailsRepository.save(workerDocumentDetails);
        return ResponseEntity.created(new URI("/api/worker-document-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /worker-document-details : Create a new workerDocumentDetails.
     *
     * @param !workerDocumentDetails the workerDocumentDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerDocumentDetails, or with status 400 (Bad Request) if the workerDocumentDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/worker-document-details-signature", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<WorkerDocumentDetails> createWorkerDocumentDetailsUpload(@RequestParam(required = false) MultipartFile file, String id, String docId, String docType) throws URISyntaxException, IOException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        WorkerDocumentDetails workerDocumentDetails = workerDocumentDetailsRepository.findByWorkerJoiningIdAndDocId(Long.parseLong(id), Long.parseLong(docId), docType);
        if (file != null && !file.isEmpty()) {
            String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String fileName = workerDocumentDetails.getId() + extn;
            workerDocumentDetails.setFileName(fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "workerrecruitemt/signature/" + fileName);
            Files.write(path, bytes);
            workerDocumentDetails = workerDocumentDetailsRepository.save(workerDocumentDetails);
        }
        return ResponseEntity.created(new URI("/api/worker-document-details/" + workerDocumentDetails.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, workerDocumentDetails.getId().toString()))
            .body(workerDocumentDetails);
    }

    /**
     * POST  /worker-document-details : Create a new workerDocumentDetails.
     *
     * @param !workerDocumentDetails the workerDocumentDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workerDocumentDetails, or with status 400 (Bad Request) if the workerDocumentDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/worker-document-details-document", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<WorkerDocumentDetails> createWorkerDocumentDetailsDocument(@RequestParam(required = false) MultipartFile file, String id, String docId, String docType) throws URISyntaxException, IOException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        WorkerDocumentDetails workerDocumentDetails = workerDocumentDetailsRepository.findByWorkerJoiningIdAndDocId(Long.parseLong(id), Long.parseLong(docId), docType);
        if (file != null && !file.isEmpty()) {
            String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String fileName = workerDocumentDetails.getId() + extn;
            workerDocumentDetails.setFileName(fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "workerrecruitemt/document/" + fileName);
            Files.write(path, bytes);
            workerDocumentDetails = workerDocumentDetailsRepository.save(workerDocumentDetails);
        }
        return ResponseEntity.created(new URI("/api/worker-document-details/" + workerDocumentDetails.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, workerDocumentDetails.getId().toString()))
            .body(workerDocumentDetails);
    }

    /**
     * PUT  /worker-document-details : Updates an existing workerDocumentDetails.
     *
     * @param workerDocumentDetails the workerDocumentDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workerDocumentDetails,
     * or with status 400 (Bad Request) if the workerDocumentDetails is not valid,
     * or with status 500 (Internal Server Error) if the workerDocumentDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worker-document-details")
    @Timed
    public ResponseEntity<WorkerDocumentDetails> updateWorkerDocumentDetails(@Valid @RequestBody WorkerDocumentDetails workerDocumentDetails) throws URISyntaxException {
        log.debug("REST request to update WorkerDocumentDetails : {}", workerDocumentDetails);
        if (workerDocumentDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkerDocumentDetails result = workerDocumentDetailsRepository.save(workerDocumentDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workerDocumentDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worker-document-details : get all the workerDocumentDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workerDocumentDetails in body
     */
    @GetMapping("/worker-document-details")
    @Timed
    public ResponseEntity<List<WorkerDocumentDetails>> getAllWorkerDocumentDetails(Pageable pageable) {
        log.debug("REST request to get a page of WorkerDocumentDetails");
        Page<WorkerDocumentDetails> page = workerDocumentDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/worker-document-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worker-document-details/:id : get the "id" workerDocumentDetails.
     *
     * @param id the id of the workerDocumentDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workerDocumentDetails, or with status 404 (Not Found)
     */
    @GetMapping("/worker-document-details/{id}")
    @Timed
    public ResponseEntity<WorkerDocumentDetails> getWorkerDocumentDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkerDocumentDetails : {}", id);
        Optional<WorkerDocumentDetails> workerDocumentDetails = workerDocumentDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workerDocumentDetails);
    }

    /**
     * DELETE  /worker-document-details/:id : delete the "id" workerDocumentDetails.
     *
     * @param id the id of the workerDocumentDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worker-document-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkerDocumentDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkerDocumentDetails : {}", id);

        workerDocumentDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/worker-document-details-download/{id}")
    @Timed
    public ResponseEntity<Object> getNewsDetailsAttachesDownload(@PathVariable Long id) throws FileNotFoundException, IOException{
        log.debug("REST request to get WorkerDocumentDetails : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        WorkerDocumentDetails workerDocumentDetails = workerDocumentDetailsRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "workerrecruitemt/document/"+workerDocumentDetails.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "workerrecruitemt/document/" + workerDocumentDetails.getFileName());
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
    @GetMapping("/worker-document-details-signature-download/{id}")
    @Timed
    public ResponseEntity<Object> getNewsDetailsAttachesSignatgureDownload(@PathVariable Long id) throws FileNotFoundException, IOException{
        log.debug("REST request to get WorkerDocumentDetails : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        WorkerDocumentDetails workerDocumentDetails = workerDocumentDetailsRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "workerrecruitemt/signature/"+workerDocumentDetails.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "workerrecruitemt/signature/" + workerDocumentDetails.getFileName());
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
