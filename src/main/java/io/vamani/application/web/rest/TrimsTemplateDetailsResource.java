package io.vamani.application.web.rest;

import io.vamani.application.domain.TrimsTemplateDetails;
import io.vamani.application.repository.TrimsTemplateDetailsRepository;
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
 * REST controller for managing {@link TrimsTemplateDetails}.
 */
@RestController
@RequestMapping("/api")
public class TrimsTemplateDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TrimsTemplateDetailsResource.class);

    private static final String ENTITY_NAME = "trimsTemplateDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrimsTemplateDetailsRepository trimsTemplateDetailsRepository;

    public TrimsTemplateDetailsResource(TrimsTemplateDetailsRepository trimsTemplateDetailsRepository) {
        this.trimsTemplateDetailsRepository = trimsTemplateDetailsRepository;
    }

    /**
     * {@code POST  /trims-template-details} : Create a new trimsTemplateDetails.
     *
     * @param trimsTemplateDetails the trimsTemplateDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trimsTemplateDetails, or with status {@code 400 (Bad Request)} if the trimsTemplateDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trims-template-details")
    public ResponseEntity<TrimsTemplateDetails> createTrimsTemplateDetails(@Valid @RequestBody TrimsTemplateDetails trimsTemplateDetails) throws URISyntaxException {
        log.debug("REST request to save TrimsTemplateDetails : {}", trimsTemplateDetails);
        if (trimsTemplateDetails.getId() != null) {
            throw new BadRequestAlertException("A new trimsTemplateDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrimsTemplateDetails result = trimsTemplateDetailsRepository.save(trimsTemplateDetails);
        return ResponseEntity.created(new URI("/api/trims-template-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trims-template-details} : Updates an existing trimsTemplateDetails.
     *
     * @param trimsTemplateDetails the trimsTemplateDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trimsTemplateDetails,
     * or with status {@code 400 (Bad Request)} if the trimsTemplateDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trimsTemplateDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trims-template-details")
    public ResponseEntity<TrimsTemplateDetails> updateTrimsTemplateDetails(@Valid @RequestBody TrimsTemplateDetails trimsTemplateDetails) throws URISyntaxException {
        log.debug("REST request to update TrimsTemplateDetails : {}", trimsTemplateDetails);
        if (trimsTemplateDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrimsTemplateDetails result = trimsTemplateDetailsRepository.save(trimsTemplateDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, trimsTemplateDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trims-template-details} : get all the trimsTemplateDetails.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trimsTemplateDetails in body.
     */
    @GetMapping("/trims-template-details")
    public ResponseEntity<List<TrimsTemplateDetails>> getAllTrimsTemplateDetails(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of TrimsTemplateDetails");
        Page<TrimsTemplateDetails> page = trimsTemplateDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trims-template-details/:id} : get the "id" trimsTemplateDetails.
     *
     * @param id the id of the trimsTemplateDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trimsTemplateDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trims-template-details/{id}")
    public ResponseEntity<TrimsTemplateDetails> getTrimsTemplateDetails(@PathVariable Long id) {
        log.debug("REST request to get TrimsTemplateDetails : {}", id);
        Optional<TrimsTemplateDetails> trimsTemplateDetails = trimsTemplateDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(trimsTemplateDetails);
    }

    /**
     * {@code DELETE  /trims-template-details/:id} : delete the "id" trimsTemplateDetails.
     *
     * @param id the id of the trimsTemplateDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trims-template-details/{id}")
    public ResponseEntity<Void> deleteTrimsTemplateDetails(@PathVariable Long id) {
        log.debug("REST request to delete TrimsTemplateDetails : {}", id);
        trimsTemplateDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
