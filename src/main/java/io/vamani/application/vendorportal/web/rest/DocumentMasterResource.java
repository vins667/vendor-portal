package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.Country;
import io.vamani.application.vendorportal.domain.DocumentMaster;
import io.vamani.application.vendorportal.repository.CountryRepository;
import io.vamani.application.vendorportal.repository.DocumentMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
 * REST controller for managing DocumentMaster.
 */
@RestController
@RequestMapping("/api")
public class DocumentMasterResource {

    private final Logger log = LoggerFactory.getLogger(DocumentMasterResource.class);

    private static final String ENTITY_NAME = "documentMaster";

    private final DocumentMasterRepository documentMasterRepository;

    @Autowired
    private CountryRepository countryRepository;

    public DocumentMasterResource(DocumentMasterRepository documentMasterRepository) {
        this.documentMasterRepository = documentMasterRepository;
    }

    /**
     * POST  /document-masters : Create a new documentMaster.
     *
     * @param documentMaster the documentMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentMaster, or with status 400 (Bad Request) if the documentMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/document-masters")
    @Timed
    public ResponseEntity<DocumentMaster> createDocumentMaster(@Valid @RequestBody DocumentMaster documentMaster) throws URISyntaxException {
        log.debug("REST request to save DocumentMaster : {}", documentMaster);
        if (documentMaster.getId() != null) {
            throw new BadRequestAlertException("A new documentMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentMaster result = documentMasterRepository.save(documentMaster);
        return ResponseEntity.created(new URI("/api/document-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /document-masters : Updates an existing documentMaster.
     *
     * @param documentMaster the documentMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentMaster,
     * or with status 400 (Bad Request) if the documentMaster is not valid,
     * or with status 500 (Internal Server Error) if the documentMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/document-masters")
    @Timed
    public ResponseEntity<DocumentMaster> updateDocumentMaster(@Valid @RequestBody DocumentMaster documentMaster) throws URISyntaxException {
        log.debug("REST request to update DocumentMaster : {}", documentMaster);
        if (documentMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentMaster result = documentMasterRepository.save(documentMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /document-masters : get all the documentMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of documentMasters in body
     */
    @GetMapping("/document-masters")
    @Timed
    public ResponseEntity<List<DocumentMaster>> getAllDocumentMasters(@PageableDefault(value = Integer.MAX_VALUE) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of DocumentMasters");
        Page<DocumentMaster> page = documentMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/document-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /document-masters : get all the documentMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of documentMasters in body
     */
    @GetMapping("/document-masters-country/{id}")
    @Timed
    public ResponseEntity<List<DocumentMaster>> getAllDocumentMastersCountry(@PathVariable Long id) {
        log.debug("REST request to get a page of DocumentMasters");
        Country country = countryRepository.findById(id).orElse(null);
        if(country != null && country.getCountryCode() != null && country.getCountryCode().equalsIgnoreCase("IN")) {
            List<DocumentMaster> page = documentMasterRepository.findAllByCountry("IN");
            return ResponseEntity.ok().body(page);
        } else {
            List<DocumentMaster> page = documentMasterRepository.findAllByCountry("OT");
            return ResponseEntity.ok().body(page);
        }
    }

    /**
     * GET  /document-masters/:id : get the "id" documentMaster.
     *
     * @param id the id of the documentMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documentMaster, or with status 404 (Not Found)
     */
    @GetMapping("/document-masters/{id}")
    @Timed
    public ResponseEntity<DocumentMaster> getDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to get DocumentMaster : {}", id);
        Optional<DocumentMaster> documentMaster = documentMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(documentMaster);
    }

    /**
     * DELETE  /document-masters/:id : delete the "id" documentMaster.
     *
     * @param id the id of the documentMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/document-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to delete DocumentMaster : {}", id);

        documentMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
