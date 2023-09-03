package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.NewsDetails;
import io.vamani.application.domain.NewsDetailsAttach;
import io.vamani.application.repository.NewsDetailsAttachRepository;
import io.vamani.application.repository.NewsDetailsBodyRepository;
import io.vamani.application.repository.NewsDetailsRepository;
import io.vamani.application.security.SecurityUtils;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NewsDetailsAttach.
 */
@RestController
@RequestMapping("/api")
public class NewsDetailsAttachResource {

    private final Logger log = LoggerFactory.getLogger(NewsDetailsAttachResource.class);

    private static final String ENTITY_NAME = "newsDetailsAttach";

    private final NewsDetailsAttachRepository newsDetailsAttachRepository;

    @Autowired
    private NewsDetailsRepository newsDetailsRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private NewsDetailsBodyRepository newsDetailsBodyRepository;

    public NewsDetailsAttachResource(NewsDetailsAttachRepository newsDetailsAttachRepository) {
        this.newsDetailsAttachRepository = newsDetailsAttachRepository;
    }

    /**
     * POST  /news-details-attaches : Create a new newsDetailsAttach.
     *
     * @param @newsDetailsAttach the newsDetailsAttach to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newsDetailsAttach, or with status 400 (Bad Request) if the newsDetailsAttach has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/news-details-attaches", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<NewsDetails> createNewsDetailsAttach(@RequestParam(required = false) MultipartFile[] file, String id) throws URISyntaxException, IOException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        NewsDetails newsDetails = newsDetailsRepository.findById(new Long(id)).orElse(null);
        if (file != null && file.length > 0) {
            for (MultipartFile multipartFile : file) {
                NewsDetailsAttach newsDetailsAttach = new NewsDetailsAttach();
                newsDetailsAttach.setNewsDetails(newsDetails);
                newsDetailsAttach.setCreatedDate(Instant.now());
                newsDetailsAttach.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                newsDetailsAttach.setAttachDisplayFile("DEMO");
                newsDetailsAttach.setAttachFile("DEMO");
                NewsDetailsAttach result = newsDetailsAttachRepository.save(newsDetailsAttach);
                String extn = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."), multipartFile.getOriginalFilename().length());
                result.setAttachFile(result.getId() + extn);
                result.setAttachDisplayFile(multipartFile.getOriginalFilename());
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + "news/" + result.getId() + extn);
                Files.write(path, bytes);
                result = newsDetailsAttachRepository.save(result);
            }
        }
        newsDetails.setNewsDetailsBodies(newsDetailsBodyRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
        newsDetails.setNewsDetailsAttaches(newsDetailsAttachRepository.findAllByNewsDetailsOrderByIdAsc(newsDetails));
        return ResponseEntity.created(new URI("/api/news-details/" + id))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, newsDetails.getId().toString()))
            .body(newsDetails);
    }


    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/news-details-attaches-download/{id}")
    @Timed
    public ResponseEntity<Object> getNewsDetailsAttachesDownload(@PathVariable Long id) throws FileNotFoundException, IOException{
        log.debug("REST request to get Feedback : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        NewsDetailsAttach newsDetailsAttach = newsDetailsAttachRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "news/"+newsDetailsAttach.getAttachFile());
        Path path = Paths.get(UPLOADED_FOLDER + "news/" + newsDetailsAttach.getAttachFile());
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
     * PUT  /news-details-attaches : Updates an existing newsDetailsAttach.
     *
     * @param newsDetailsAttach the newsDetailsAttach to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated newsDetailsAttach,
     * or with status 400 (Bad Request) if the newsDetailsAttach is not valid,
     * or with status 500 (Internal Server Error) if the newsDetailsAttach couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/news-details-attaches")
    @Timed
    public ResponseEntity<NewsDetailsAttach> updateNewsDetailsAttach(@Valid @RequestBody NewsDetailsAttach newsDetailsAttach) throws URISyntaxException {
        log.debug("REST request to update NewsDetailsAttach : {}", newsDetailsAttach);
        if (newsDetailsAttach.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NewsDetailsAttach result = newsDetailsAttachRepository.save(newsDetailsAttach);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsDetailsAttach.getId().toString()))
            .body(result);
    }

    /**
     * GET  /news-details-attaches : get all the newsDetailsAttaches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of newsDetailsAttaches in body
     */
    @GetMapping("/news-details-attaches")
    @Timed
    public List<NewsDetailsAttach> getAllNewsDetailsAttaches() {
        log.debug("REST request to get all NewsDetailsAttaches");
        return newsDetailsAttachRepository.findAll();
    }

    /**
     * GET  /news-details-attaches/:id : get the "id" newsDetailsAttach.
     *
     * @param id the id of the newsDetailsAttach to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newsDetailsAttach, or with status 404 (Not Found)
     */
    @GetMapping("/news-details-attaches/{id}")
    @Timed
    public ResponseEntity<NewsDetailsAttach> getNewsDetailsAttach(@PathVariable Long id) {
        log.debug("REST request to get NewsDetailsAttach : {}", id);
        Optional<NewsDetailsAttach> newsDetailsAttach = newsDetailsAttachRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(newsDetailsAttach);
    }

    /**
     * DELETE  /news-details-attaches/:id : delete the "id" newsDetailsAttach.
     *
     * @param id the id of the newsDetailsAttach to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/news-details-attaches/{id}")
    @Timed
    public ResponseEntity<Void> deleteNewsDetailsAttach(@PathVariable Long id) {
        log.debug("REST request to delete NewsDetailsAttach : {}", id);

        newsDetailsAttachRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
