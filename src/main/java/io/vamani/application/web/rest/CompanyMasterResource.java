package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.CompanyMaster;
import io.vamani.application.repository.CompanyMasterRepository;
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
 * REST controller for managing CompanyMaster.
 */
@RestController
@RequestMapping("/api")
public class CompanyMasterResource {

    private final Logger log = LoggerFactory.getLogger(CompanyMasterResource.class);

    private static final String ENTITY_NAME = "companyMaster";

    private final CompanyMasterRepository companyMasterRepository;

    public CompanyMasterResource(CompanyMasterRepository companyMasterRepository) {
        this.companyMasterRepository = companyMasterRepository;
    }

    /**
     * POST  /company-masters : Create a new companyMaster.
     *
     * @param companyMaster the companyMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new companyMaster, or with status 400 (Bad Request) if the companyMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/company-masters")
    @Timed
    public ResponseEntity<CompanyMaster> createCompanyMaster(@Valid @RequestBody CompanyMaster companyMaster) throws URISyntaxException {
        log.debug("REST request to save CompanyMaster : {}", companyMaster);
        if (companyMaster.getId() != null) {
            throw new BadRequestAlertException("A new companyMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompanyMaster result = companyMasterRepository.save(companyMaster);
        return ResponseEntity.created(new URI("/api/company-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /company-masters : Updates an existing companyMaster.
     *
     * @param companyMaster the companyMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated companyMaster,
     * or with status 400 (Bad Request) if the companyMaster is not valid,
     * or with status 500 (Internal Server Error) if the companyMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/company-masters")
    @Timed
    public ResponseEntity<CompanyMaster> updateCompanyMaster(@Valid @RequestBody CompanyMaster companyMaster) throws URISyntaxException {
        log.debug("REST request to update CompanyMaster : {}", companyMaster);
        if (companyMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompanyMaster result = companyMasterRepository.save(companyMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, companyMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /company-masters : get all the companyMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of companyMasters in body
     */
    @GetMapping("/company-masters")
    @Timed
    public List<CompanyMaster> getAllCompanyMasters() {
        log.debug("REST request to get all CompanyMasters");
        return companyMasterRepository.findAll();
    }

    /**
     * GET  /company-masters/:id : get the "id" companyMaster.
     *
     * @param id the id of the companyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyMaster, or with status 404 (Not Found)
     */
    @GetMapping("/company-masters/{id}")
    @Timed
    public ResponseEntity<CompanyMaster> getCompanyMaster(@PathVariable Long id) {
        log.debug("REST request to get CompanyMaster : {}", id);
        Optional<CompanyMaster> companyMaster = companyMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(companyMaster);
    }

    /**
     * DELETE  /company-masters/:id : delete the "id" companyMaster.
     *
     * @param id the id of the companyMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/company-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompanyMaster(@PathVariable Long id) {
        log.debug("REST request to delete CompanyMaster : {}", id);

        companyMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
