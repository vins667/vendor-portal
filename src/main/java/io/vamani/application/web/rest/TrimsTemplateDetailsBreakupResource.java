package io.vamani.application.web.rest;

import io.vamani.application.domain.TrimTemplateDetailsBreakupId;
import io.vamani.application.domain.TrimsTemplateDetailsBreakup;
import io.vamani.application.repository.TrimsTemplateDetailsBreakupRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link TrimsTemplateDetailsBreakup}.
 */
@RestController
@RequestMapping("/api")
public class TrimsTemplateDetailsBreakupResource {

    private final Logger log = LoggerFactory.getLogger(TrimsTemplateDetailsBreakupResource.class);

    private static final String ENTITY_NAME = "trimsTemplateDetailsBreakup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrimsTemplateDetailsBreakupRepository trimsTemplateDetailsBreakupRepository;

    public TrimsTemplateDetailsBreakupResource(TrimsTemplateDetailsBreakupRepository trimsTemplateDetailsBreakupRepository) {
        this.trimsTemplateDetailsBreakupRepository = trimsTemplateDetailsBreakupRepository;
    }

    /**
     * {@code POST  /trims-template-details-breakups} : Create a new trimsTemplateDetailsBreakup.
     *
     * @param trimsTemplateDetailsBreakup the trimsTemplateDetailsBreakup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trimsTemplateDetailsBreakup, or with status {@code 400 (Bad Request)} if the trimsTemplateDetailsBreakup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trims-template-details-breakups")
    public ResponseEntity<TrimsTemplateDetailsBreakup> createTrimsTemplateDetailsBreakup(@Valid @RequestBody TrimsTemplateDetailsBreakup trimsTemplateDetailsBreakup) throws URISyntaxException {
        log.debug("REST request to save TrimsTemplateDetailsBreakup : {}", trimsTemplateDetailsBreakup);
        if (trimsTemplateDetailsBreakup.getId() != null) {
            throw new BadRequestAlertException("A new trimsTemplateDetailsBreakup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrimsTemplateDetailsBreakup result = trimsTemplateDetailsBreakupRepository.save(trimsTemplateDetailsBreakup);
        return ResponseEntity.created(new URI("/api/trims-template-details-breakups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trims-template-details-breakups} : Updates an existing trimsTemplateDetailsBreakup.
     *
     * @param trimsTemplateDetailsBreakup the trimsTemplateDetailsBreakup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trimsTemplateDetailsBreakup,
     * or with status {@code 400 (Bad Request)} if the trimsTemplateDetailsBreakup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trimsTemplateDetailsBreakup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trims-template-details-breakups")
    public ResponseEntity<TrimsTemplateDetailsBreakup> updateTrimsTemplateDetailsBreakup(@Valid @RequestBody TrimsTemplateDetailsBreakup trimsTemplateDetailsBreakup) throws URISyntaxException {
        log.debug("REST request to update TrimsTemplateDetailsBreakup : {}", trimsTemplateDetailsBreakup);
        if (trimsTemplateDetailsBreakup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrimsTemplateDetailsBreakup result = trimsTemplateDetailsBreakupRepository.save(trimsTemplateDetailsBreakup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, trimsTemplateDetailsBreakup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trims-template-details-breakups} : get all the trimsTemplateDetailsBreakups.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trimsTemplateDetailsBreakups in body.
     */
    @GetMapping("/trims-template-details-breakups")
    public ResponseEntity<List<TrimsTemplateDetailsBreakup>> getAllTrimsTemplateDetailsBreakups(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of TrimsTemplateDetailsBreakups");
        Page<TrimsTemplateDetailsBreakup> page = trimsTemplateDetailsBreakupRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trims-template-details-breakups/:id} : get the "id" trimsTemplateDetailsBreakup.
     *
     * @param id the id of the trimsTemplateDetailsBreakup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trimsTemplateDetailsBreakup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trims-template-details-breakups/{id}/{did}")
    public ResponseEntity<TrimsTemplateDetailsBreakup> getTrimsTemplateDetailsBreakup(@PathVariable Long id, @PathVariable Long did) {
        log.debug("REST request to get TrimsTemplateDetailsBreakup : {}", id);
        Optional<TrimsTemplateDetailsBreakup> trimsTemplateDetailsBreakup = trimsTemplateDetailsBreakupRepository.findById(new TrimTemplateDetailsBreakupId(id, did));
        return ResponseUtil.wrapOrNotFound(trimsTemplateDetailsBreakup);
    }

    /**
     * {@code DELETE  /trims-template-details-breakups/:id} : delete the "id" trimsTemplateDetailsBreakup.
     *
     * @param id the id of the trimsTemplateDetailsBreakup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trims-template-details-breakups/{id}/{did}")
    public ResponseEntity<Void> deleteTrimsTemplateDetailsBreakup(@PathVariable Long id, @PathVariable Long did) {
        log.debug("REST request to delete TrimsTemplateDetailsBreakup : {}", id);
        trimsTemplateDetailsBreakupRepository.deleteById(new TrimTemplateDetailsBreakupId(id, did));
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
