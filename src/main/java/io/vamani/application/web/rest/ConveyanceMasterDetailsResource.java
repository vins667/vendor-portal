package io.vamani.application.web.rest;

import io.vamani.application.domain.ConveyanceMasterDetails;
import io.vamani.application.repository.ConveyanceMasterDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.ConveyanceMasterDetails}.
 */
@RestController
@RequestMapping("/api")
public class ConveyanceMasterDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ConveyanceMasterDetailsResource.class);

    private static final String ENTITY_NAME = "conveyanceMasterDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConveyanceMasterDetailsRepository conveyanceMasterDetailsRepository;

    public ConveyanceMasterDetailsResource(ConveyanceMasterDetailsRepository conveyanceMasterDetailsRepository) {
        this.conveyanceMasterDetailsRepository = conveyanceMasterDetailsRepository;
    }

    /**
     * {@code POST  /conveyance-master-details} : Create a new conveyanceMasterDetails.
     *
     * @param conveyanceMasterDetails the conveyanceMasterDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conveyanceMasterDetails, or with status {@code 400 (Bad Request)} if the conveyanceMasterDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conveyance-master-details")
    public ResponseEntity<ConveyanceMasterDetails> createConveyanceMasterDetails(@Valid @RequestBody ConveyanceMasterDetails conveyanceMasterDetails) throws URISyntaxException {
        log.debug("REST request to save ConveyanceMasterDetails : {}", conveyanceMasterDetails);
        if (conveyanceMasterDetails.getId() != null) {
            throw new BadRequestAlertException("A new conveyanceMasterDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConveyanceMasterDetails result = conveyanceMasterDetailsRepository.save(conveyanceMasterDetails);
        return ResponseEntity.created(new URI("/api/conveyance-master-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conveyance-master-details} : Updates an existing conveyanceMasterDetails.
     *
     * @param conveyanceMasterDetails the conveyanceMasterDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conveyanceMasterDetails,
     * or with status {@code 400 (Bad Request)} if the conveyanceMasterDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conveyanceMasterDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conveyance-master-details")
    public ResponseEntity<ConveyanceMasterDetails> updateConveyanceMasterDetails(@Valid @RequestBody ConveyanceMasterDetails conveyanceMasterDetails) throws URISyntaxException {
        log.debug("REST request to update ConveyanceMasterDetails : {}", conveyanceMasterDetails);
        if (conveyanceMasterDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConveyanceMasterDetails result = conveyanceMasterDetailsRepository.save(conveyanceMasterDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, conveyanceMasterDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conveyance-master-details} : get all the conveyanceMasterDetails.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conveyanceMasterDetails in body.
     */
    @GetMapping("/conveyance-master-details")
    public ResponseEntity<List<ConveyanceMasterDetails>> getAllConveyanceMasterDetails(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of ConveyanceMasterDetails");
        Page<ConveyanceMasterDetails> page = conveyanceMasterDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /conveyance-master-details/:id} : get the "id" conveyanceMasterDetails.
     *
     * @param id the id of the conveyanceMasterDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conveyanceMasterDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conveyance-master-details/{id}")
    public ResponseEntity<ConveyanceMasterDetails> getConveyanceMasterDetails(@PathVariable Long id) {
        log.debug("REST request to get ConveyanceMasterDetails : {}", id);
        Optional<ConveyanceMasterDetails> conveyanceMasterDetails = conveyanceMasterDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(conveyanceMasterDetails);
    }

    /**
     * {@code DELETE  /conveyance-master-details/:id} : delete the "id" conveyanceMasterDetails.
     *
     * @param id the id of the conveyanceMasterDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conveyance-master-details/{id}")
    public ResponseEntity<Void> deleteConveyanceMasterDetails(@PathVariable Long id) {
        log.debug("REST request to delete ConveyanceMasterDetails : {}", id);
        conveyanceMasterDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
