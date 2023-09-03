package io.vamani.application.web.rest;

import io.vamani.application.domain.FabricSubstractDetails;
import io.vamani.application.repository.FabricSubstractDetailsRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link FabricSubstractDetails}.
 */
@RestController
@RequestMapping("/api")
public class FabricSubstractDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FabricSubstractDetailsResource.class);

    private static final String ENTITY_NAME = "fabricSubstractDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FabricSubstractDetailsRepository fabricSubstractDetailsRepository;

    public FabricSubstractDetailsResource(FabricSubstractDetailsRepository fabricSubstractDetailsRepository) {
        this.fabricSubstractDetailsRepository = fabricSubstractDetailsRepository;
    }

    /**
     * {@code POST  /fabric-substract-details} : Create a new fabricSubstractDetails.
     *
     * @param fabricSubstractDetails the fabricSubstractDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fabricSubstractDetails, or with status {@code 400 (Bad Request)} if the fabricSubstractDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fabric-substract-details")
    public ResponseEntity<FabricSubstractDetails> createFabricSubstractDetails(@Valid @RequestBody FabricSubstractDetails fabricSubstractDetails) throws URISyntaxException {
        log.debug("REST request to save FabricSubstractDetails : {}", fabricSubstractDetails);
        if (fabricSubstractDetails.getId() != null) {
            throw new BadRequestAlertException("A new fabricSubstractDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fabricSubstractDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricSubstractDetails.setCreatedDate(Instant.now());
        FabricSubstractDetails result = fabricSubstractDetailsRepository.save(fabricSubstractDetails);
        return ResponseEntity.created(new URI("/api/fabric-substract-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fabric-substract-details} : Updates an existing fabricSubstractDetails.
     *
     * @param fabricSubstractDetails the fabricSubstractDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fabricSubstractDetails,
     * or with status {@code 400 (Bad Request)} if the fabricSubstractDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fabricSubstractDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fabric-substract-details")
    public ResponseEntity<FabricSubstractDetails> updateFabricSubstractDetails(@Valid @RequestBody FabricSubstractDetails fabricSubstractDetails) throws URISyntaxException {
        log.debug("REST request to update FabricSubstractDetails : {}", fabricSubstractDetails);
        if (fabricSubstractDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        fabricSubstractDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        fabricSubstractDetails.setCreatedDate(Instant.now());
        FabricSubstractDetails result = fabricSubstractDetailsRepository.save(fabricSubstractDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fabricSubstractDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fabric-substract-details} : get all the fabricSubstractDetails.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricSubstractDetails in body.
     */
    @GetMapping("/fabric-substract-details")
    public ResponseEntity<List<FabricSubstractDetails>> getAllFabricSubstractDetails(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of FabricSubstractDetails");
        Page<FabricSubstractDetails> page = fabricSubstractDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fabric-substract-details/:id} : get the "id" fabricSubstractDetails.
     *
     * @param id the id of the fabricSubstractDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabricSubstractDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabric-substract-details/{id}")
    public ResponseEntity<FabricSubstractDetails> getFabricSubstractDetails(@PathVariable Long id) {
        log.debug("REST request to get FabricSubstractDetails : {}", id);
        Optional<FabricSubstractDetails> fabricSubstractDetails = fabricSubstractDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fabricSubstractDetails);
    }

    /**
     * {@code GET  /fabric-substract-details/:id} : get the "id" fabricSubstractDetails.
     *
     * @param id the id of the fabricSubstractDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fabricSubstractDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fabric-substract-details-by-master-id/{id}")
    public ResponseEntity<List<FabricSubstractDetails>> getFabricSubstractDetailsByMasterId(@PathVariable Long id) {
        log.debug("REST request to get FabricSubstractDetails : {}", id);
        List<FabricSubstractDetails> fabricSubstractDetails = fabricSubstractDetailsRepository.findAllByMasterId(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(fabricSubstractDetails));
    }

    /**
     * {@code DELETE  /fabric-substract-details/:id} : delete the "id" fabricSubstractDetails.
     *
     * @param id the id of the fabricSubstractDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fabric-substract-details/{id}")
    public ResponseEntity<Void> deleteFabricSubstractDetails(@PathVariable Long id) {
        log.debug("REST request to delete FabricSubstractDetails : {}", id);
        fabricSubstractDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
