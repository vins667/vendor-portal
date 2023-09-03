package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.vendorportal.domain.TemplateDetails;
import io.vamani.application.vendorportal.repository.TemplateDetailsBreakupRepository;
import io.vamani.application.vendorportal.repository.TemplateDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TemplateDetails.
 */
@RestController
@RequestMapping("/api")
public class TemplateDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TemplateDetailsResource.class);

    private static final String ENTITY_NAME = "templateDetails";

    private final TemplateDetailsRepository templateDetailsRepository;

    @Autowired
    private TemplateDetailsBreakupRepository templateDetailsBreakupRepository;

    public TemplateDetailsResource(TemplateDetailsRepository templateDetailsRepository) {
        this.templateDetailsRepository = templateDetailsRepository;
    }

    /**
     * POST  /template-details : Create a new templateDetails.
     *
     * @param templateDetails the templateDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new templateDetails, or with status 400 (Bad Request) if the templateDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/template-details")
    @Timed
    public ResponseEntity<TemplateDetails> createTemplateDetails(@Valid @RequestBody TemplateDetails templateDetails) throws URISyntaxException {
        log.debug("REST request to save TemplateDetails : {}", templateDetails);
        if (templateDetails.getId() != null) {
            throw new BadRequestAlertException("A new templateDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemplateDetails result = templateDetailsRepository.save(templateDetails);
        return ResponseEntity.created(new URI("/api/template-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /template-details : Updates an existing templateDetails.
     *
     * @param templateDetails the templateDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated templateDetails,
     * or with status 400 (Bad Request) if the templateDetails is not valid,
     * or with status 500 (Internal Server Error) if the templateDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/template-details")
    @Timed
    public ResponseEntity<TemplateDetails> updateTemplateDetails(@Valid @RequestBody TemplateDetails templateDetails) throws URISyntaxException {
        log.debug("REST request to update TemplateDetails : {}", templateDetails);
        if (templateDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemplateDetails result = templateDetailsRepository.save(templateDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, templateDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /template-details : get all the templateDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of templateDetails in body
     */
    @GetMapping("/template-details")
    @Timed
    public ResponseEntity<List<TemplateDetails>> getAllTemplateDetails(Pageable pageable) {
        log.debug("REST request to get a page of TemplateDetails");
        Page<TemplateDetails> page = templateDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/template-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /template-details/:id : get the "id" templateDetails.
     *
     * @param id the id of the templateDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the templateDetails, or with status 404 (Not Found)
     */
    @GetMapping("/template-details/{id}")
    @Timed
    public ResponseEntity<TemplateDetails> getTemplateDetails(@PathVariable Long id) {
        log.debug("REST request to get TemplateDetails : {}", id);
        Optional<TemplateDetails> templateDetails = templateDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(templateDetails);
    }

    /**
     * DELETE  /template-details/:id : delete the "id" templateDetails.
     *
     * @param id the id of the templateDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/template-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteTemplateDetails(@PathVariable Long id) {
        log.debug("REST request to delete TemplateDetails : {}", id);

        templateDetailsBreakupRepository.deleteAllByTemplateDetailsId(id);
        templateDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
