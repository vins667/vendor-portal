package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.NewsDetailsBody;
import io.vamani.application.repository.NewsDetailsBodyRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NewsDetailsBody.
 */
@RestController
@RequestMapping("/api")
public class NewsDetailsBodyResource {

    private final Logger log = LoggerFactory.getLogger(NewsDetailsBodyResource.class);

    private static final String ENTITY_NAME = "newsDetailsBody";

    private final NewsDetailsBodyRepository newsDetailsBodyRepository;

    public NewsDetailsBodyResource(NewsDetailsBodyRepository newsDetailsBodyRepository) {
        this.newsDetailsBodyRepository = newsDetailsBodyRepository;
    }

    /**
     * POST  /news-details-bodies : Create a new newsDetailsBody.
     *
     * @param newsDetailsBody the newsDetailsBody to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newsDetailsBody, or with status 400 (Bad Request) if the newsDetailsBody has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/news-details-bodies")
    @Timed
    public ResponseEntity<NewsDetailsBody> createNewsDetailsBody(@Valid @RequestBody NewsDetailsBody newsDetailsBody) throws URISyntaxException {
        log.debug("REST request to save NewsDetailsBody : {}", newsDetailsBody);
        if (newsDetailsBody.getId() != null) {
            throw new BadRequestAlertException("A new newsDetailsBody cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NewsDetailsBody result = newsDetailsBodyRepository.save(newsDetailsBody);
        return ResponseEntity.created(new URI("/api/news-details-bodies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /news-details-bodies : Updates an existing newsDetailsBody.
     *
     * @param newsDetailsBody the newsDetailsBody to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated newsDetailsBody,
     * or with status 400 (Bad Request) if the newsDetailsBody is not valid,
     * or with status 500 (Internal Server Error) if the newsDetailsBody couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/news-details-bodies")
    @Timed
    public ResponseEntity<NewsDetailsBody> updateNewsDetailsBody(@Valid @RequestBody NewsDetailsBody newsDetailsBody) throws URISyntaxException {
        log.debug("REST request to update NewsDetailsBody : {}", newsDetailsBody);
        if (newsDetailsBody.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NewsDetailsBody result = newsDetailsBodyRepository.save(newsDetailsBody);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsDetailsBody.getId().toString()))
            .body(result);
    }

    /**
     * GET  /news-details-bodies : get all the newsDetailsBodies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of newsDetailsBodies in body
     */
    @GetMapping("/news-details-bodies")
    @Timed
    public List<NewsDetailsBody> getAllNewsDetailsBodies() {
        log.debug("REST request to get all NewsDetailsBodies");
        return newsDetailsBodyRepository.findAll();
    }

    /**
     * GET  /news-details-bodies/:id : get the "id" newsDetailsBody.
     *
     * @param id the id of the newsDetailsBody to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newsDetailsBody, or with status 404 (Not Found)
     */
    @GetMapping("/news-details-bodies/{id}")
    @Timed
    public ResponseEntity<NewsDetailsBody> getNewsDetailsBody(@PathVariable Long id) {
        log.debug("REST request to get NewsDetailsBody : {}", id);
        Optional<NewsDetailsBody> newsDetailsBody = newsDetailsBodyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(newsDetailsBody);
    }

    /**
     * DELETE  /news-details-bodies/:id : delete the "id" newsDetailsBody.
     *
     * @param id the id of the newsDetailsBody to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/news-details-bodies/{id}")
    @Timed
    public ResponseEntity<Void> deleteNewsDetailsBody(@PathVariable Long id) {
        log.debug("REST request to delete NewsDetailsBody : {}", id);

        newsDetailsBodyRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
