package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.NewsMaster;
import io.vamani.application.repository.NewsMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NewsMaster.
 */
@RestController
@RequestMapping("/api")
public class NewsMasterResource {

    private final Logger log = LoggerFactory.getLogger(NewsMasterResource.class);

    private static final String ENTITY_NAME = "newsMaster";

    private final NewsMasterRepository newsMasterRepository;

    public NewsMasterResource(NewsMasterRepository newsMasterRepository) {
        this.newsMasterRepository = newsMasterRepository;
    }

    /**
     * POST  /news-masters : Create a new newsMaster.
     *
     * @param newsMaster the newsMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newsMaster, or with status 400 (Bad Request) if the newsMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/news-masters")
    @Timed
    public ResponseEntity<NewsMaster> createNewsMaster(@Valid @RequestBody NewsMaster newsMaster) throws URISyntaxException {
        log.debug("REST request to save NewsMaster : {}", newsMaster);
        if (newsMaster.getId() != null) {
            throw new BadRequestAlertException("A new newsMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NewsMaster result = newsMasterRepository.save(newsMaster);
        return ResponseEntity.created(new URI("/api/news-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /news-masters : Updates an existing newsMaster.
     *
     * @param newsMaster the newsMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated newsMaster,
     * or with status 400 (Bad Request) if the newsMaster is not valid,
     * or with status 500 (Internal Server Error) if the newsMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/news-masters")
    @Timed
    public ResponseEntity<NewsMaster> updateNewsMaster(@Valid @RequestBody NewsMaster newsMaster) throws URISyntaxException {
        log.debug("REST request to update NewsMaster : {}", newsMaster);
        if (newsMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NewsMaster result = newsMasterRepository.save(newsMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /news-masters : get all the newsMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of newsMasters in body
     */
    @GetMapping("/news-masters")
    @Timed
    public ResponseEntity<List<NewsMaster>> getAllNewsMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of NewsMasters");
        Page<NewsMaster> page = newsMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/news-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /news-masters/:id : get the "id" newsMaster.
     *
     * @param id the id of the newsMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newsMaster, or with status 404 (Not Found)
     */
    @GetMapping("/news-masters/{id}")
    @Timed
    public ResponseEntity<NewsMaster> getNewsMaster(@PathVariable Long id) {
        log.debug("REST request to get NewsMaster : {}", id);
        Optional<NewsMaster> newsMaster = newsMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(newsMaster);
    }

    /**
     * DELETE  /news-masters/:id : delete the "id" newsMaster.
     *
     * @param id the id of the newsMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/news-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteNewsMaster(@PathVariable Long id) {
        log.debug("REST request to delete NewsMaster : {}", id);

        newsMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
