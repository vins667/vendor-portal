package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.TravelApplicationMaster;
import io.vamani.application.domain.TravelMasterAttach;
import io.vamani.application.repository.TravelApplicationMasterRepository;
import io.vamani.application.repository.TravelMasterAttachRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import io.vamani.application.config.ApplicationProperties;
import java.nio.file.Files;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.vamani.application.domain.TravelMasterAttach}.
 */
@RestController
@RequestMapping("/api")
public class TravelMasterAttachResource {

    private final Logger log = LoggerFactory.getLogger(TravelMasterAttachResource.class);

    private static final String ENTITY_NAME = "travelMasterAttach";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    
    @Autowired
    private TravelApplicationMasterRepository travelApplicationMasterRepository;
    
    @Autowired
    private ApplicationProperties applicationProperties;

    private final TravelMasterAttachRepository travelMasterAttachRepository;

    public TravelMasterAttachResource(TravelMasterAttachRepository travelMasterAttachRepository) {
        this.travelMasterAttachRepository = travelMasterAttachRepository;
    }

    /**
     * {@code POST  /travel-master-attaches} : Create a new travelMasterAttach.
     *
     * @param travelMasterAttach the travelMasterAttach to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new travelMasterAttach, or with status {@code 400 (Bad Request)} if the travelMasterAttach has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws java.io.IOException 
     */
    @PostMapping(value = "/travel-master-attaches", consumes = {"multipart/form-data"})
    public ResponseEntity<List<TravelMasterAttach>> createTravelMasterAttach(@RequestParam(required = false) MultipartFile file,String attchType, String id) throws URISyntaxException, IOException {
    	String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        TravelApplicationMaster travelApplicationMaster = travelApplicationMasterRepository.findById(new Long(id)).orElse(null);
        TravelMasterAttach travelMasterAttach = new TravelMasterAttach();
        travelMasterAttach.setTravelApplicationMaster(travelApplicationMaster);
        travelMasterAttach.setAttchType(attchType);
        travelMasterAttach.setAttachFile("DEMO");
        travelMasterAttach.setAttachDisplayFile("DEMO");
        travelMasterAttach.setCreatedDate(Instant.now());
        travelMasterAttach.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        TravelMasterAttach result = travelMasterAttachRepository.save(travelMasterAttach);
        String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        result.setAttachFile(result.getId() + extn);
        result.setAttachDisplayFile(file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + "travel/" + result.getId() + extn);
        Files.write(path, bytes);
        result = travelMasterAttachRepository.save(result);

        return ResponseEntity.created(new URI("/api/travel-master-attaches/" + id))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, travelApplicationMaster.getId().toString()))
            .body(travelMasterAttachRepository.findAllByMasterId(new Long(id)));
    }

    /**
     * {@code PUT  /travel-master-attaches} : Updates an existing travelMasterAttach.
     *
     * @param travelMasterAttach the travelMasterAttach to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated travelMasterAttach,
     * or with status {@code 400 (Bad Request)} if the travelMasterAttach is not valid,
     * or with status {@code 500 (Internal Server Error)} if the travelMasterAttach couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/travel-master-attaches")
    public ResponseEntity<TravelMasterAttach> updateTravelMasterAttach(@Valid @RequestBody TravelMasterAttach travelMasterAttach) throws URISyntaxException {
        log.debug("REST request to update TravelMasterAttach : {}", travelMasterAttach);
        if (travelMasterAttach.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TravelMasterAttach result = travelMasterAttachRepository.save(travelMasterAttach);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, travelMasterAttach.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /travel-master-attaches} : get all the travelMasterAttaches.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of travelMasterAttaches in body.
     */
    @GetMapping("/travel-master-attaches")
    public ResponseEntity<List<TravelMasterAttach>> getAllTravelMasterAttaches(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of TravelMasterAttaches");
        Page<TravelMasterAttach> page = travelMasterAttachRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/travel-master-attaches-download/{id}")
    @Timed
    public ResponseEntity<Object> getTravelMasterAttachesDownload(@PathVariable Long id) throws FileNotFoundException, java.io.IOException {
        log.debug("REST request to get WorkerDocumentDetails : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        TravelMasterAttach travelMasterAttach = travelMasterAttachRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "travel/"+travelMasterAttach.getAttachFile());
        Path path = Paths.get(UPLOADED_FOLDER + "travel/" + travelMasterAttach.getAttachFile());
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
     * {@code GET  /travel-master-attaches/:id} : get the "id" travelMasterAttach.
     *
     * @param id the id of the travelMasterAttach to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the travelMasterAttach, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/travel-master-attaches/{id}")
    public ResponseEntity<List<TravelMasterAttach>> getTravelMasterAttach(@PathVariable Long id) {
        log.debug("REST request to get TravelMasterAttach : {}", id);
        List<TravelMasterAttach> travelMasterAttaches = travelMasterAttachRepository.findAllByMasterId(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(travelMasterAttaches));
    }

    /**
     * {@code DELETE  /travel-master-attaches/:id} : delete the "id" travelMasterAttach.
     *
     * @param id the id of the travelMasterAttach to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/travel-master-attaches/{id}")
    public ResponseEntity<Void> deleteTravelMasterAttach(@PathVariable Long id) {
        log.debug("REST request to delete TravelMasterAttach : {}", id);
        travelMasterAttachRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
