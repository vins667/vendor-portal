package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.Quotes;
import io.vamani.application.repository.QuotesRepository;
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
 * REST controller for managing Quotes.
 */
@RestController
@RequestMapping("/api")
public class QuotesResource {

    private final Logger log = LoggerFactory.getLogger(QuotesResource.class);

    private static final String ENTITY_NAME = "quotes";

    private final QuotesRepository quotesRepository;

    public QuotesResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    /**
     * POST  /quotes : Create a new quotes.
     *
     * @param quotes the quotes to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotes, or with status 400 (Bad Request) if the quotes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotes")
    @Timed
    public ResponseEntity<Quotes> createQuotes(@Valid @RequestBody Quotes quotes) throws URISyntaxException {
        log.debug("REST request to save Quotes : {}", quotes);
        if (quotes.getId() != null) {
            throw new BadRequestAlertException("A new quotes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Quotes result = quotesRepository.save(quotes);
        return ResponseEntity.created(new URI("/api/quotes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotes : Updates an existing quotes.
     *
     * @param quotes the quotes to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotes,
     * or with status 400 (Bad Request) if the quotes is not valid,
     * or with status 500 (Internal Server Error) if the quotes couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotes")
    @Timed
    public ResponseEntity<Quotes> updateQuotes(@Valid @RequestBody Quotes quotes) throws URISyntaxException {
        log.debug("REST request to update Quotes : {}", quotes);
        if (quotes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Quotes result = quotesRepository.save(quotes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotes.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotes : get all the quotes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quotes in body
     */
    @GetMapping("/quotes")
    @Timed
    public List<Quotes> getAllQuotes() {
        log.debug("REST request to get all Quotes");
        return quotesRepository.findAll();
    }

    /**
     * GET  /quotes/:id : get the "id" quotes.
     *
     * @param id the id of the quotes to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotes, or with status 404 (Not Found)
     */
    @GetMapping("/quotes/{id}")
    @Timed
    public ResponseEntity<Quotes> getQuotes(@PathVariable Long id) {
        log.debug("REST request to get Quotes : {}", id);
        Optional<Quotes> quotes = quotesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(quotes);
    }

    /**
     * DELETE  /quotes/:id : delete the "id" quotes.
     *
     * @param id the id of the quotes to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotes/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotes(@PathVariable Long id) {
        log.debug("REST request to delete Quotes : {}", id);

        quotesRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
