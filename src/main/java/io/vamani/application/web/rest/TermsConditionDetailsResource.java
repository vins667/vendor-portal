package io.vamani.application.web.rest;

import io.vamani.application.domain.TermsConditionDetails;
import io.vamani.application.repository.TermsConditionDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.TermsConditionDetails}.
 */
@RestController
@RequestMapping("/api")
public class TermsConditionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TermsConditionDetailsResource.class);

    private static final String ENTITY_NAME = "termsConditionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TermsConditionDetailsRepository termsConditionDetailsRepository;

    public TermsConditionDetailsResource(TermsConditionDetailsRepository termsConditionDetailsRepository) {
        this.termsConditionDetailsRepository = termsConditionDetailsRepository;
    }

    /**
     * {@code POST  /terms-condition-details} : Create a new termsConditionDetails.
     *
     * @param termsConditionDetails the termsConditionDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new termsConditionDetails, or with status {@code 400 (Bad Request)} if the termsConditionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/terms-condition-details")
    public ResponseEntity<TermsConditionDetails> createTermsConditionDetails(@Valid @RequestBody TermsConditionDetails termsConditionDetails) throws URISyntaxException {
        log.debug("REST request to save TermsConditionDetails : {}", termsConditionDetails);
        if (termsConditionDetails.getId() != null) {
            throw new BadRequestAlertException("A new termsConditionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TermsConditionDetails result = termsConditionDetailsRepository.save(termsConditionDetails);
        return ResponseEntity.created(new URI("/api/terms-condition-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /terms-condition-details} : Updates an existing termsConditionDetails.
     *
     * @param termsConditionDetails the termsConditionDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated termsConditionDetails,
     * or with status {@code 400 (Bad Request)} if the termsConditionDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the termsConditionDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/terms-condition-details")
    public ResponseEntity<TermsConditionDetails> updateTermsConditionDetails(@Valid @RequestBody TermsConditionDetails termsConditionDetails) throws URISyntaxException {
        log.debug("REST request to update TermsConditionDetails : {}", termsConditionDetails);
        if (termsConditionDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TermsConditionDetails result = termsConditionDetailsRepository.save(termsConditionDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, termsConditionDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /terms-condition-details} : get all the termsConditionDetails.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of termsConditionDetails in body.
     */
    @GetMapping("/terms-condition-details")
    public ResponseEntity<List<TermsConditionDetails>> getAllTermsConditionDetails(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of TermsConditionDetails");
        Page<TermsConditionDetails> page = termsConditionDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /terms-condition-details/:id} : get the "id" termsConditionDetails.
     *
     * @param id the id of the termsConditionDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the termsConditionDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/terms-condition-details/{id}")
    public ResponseEntity<TermsConditionDetails> getTermsConditionDetails(@PathVariable Long id) {
        log.debug("REST request to get TermsConditionDetails : {}", id);
        Optional<TermsConditionDetails> termsConditionDetails = termsConditionDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(termsConditionDetails);
    }

    /**
     * {@code DELETE  /terms-condition-details/:id} : delete the "id" termsConditionDetails.
     *
     * @param id the id of the termsConditionDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/terms-condition-details/{id}")
    public ResponseEntity<Void> deleteTermsConditionDetails(@PathVariable Long id) {
        log.debug("REST request to delete TermsConditionDetails : {}", id);
        termsConditionDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
