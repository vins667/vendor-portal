package io.vamani.application.web.rest;

import io.vamani.application.domain.CostingGroupDetails;
import io.vamani.application.model.CostingProcessMasterSearch;
import io.vamani.application.repository.CostingGroupDetailsRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.vamani.application.domain.CostingGroupDetails}.
 */
@RestController
@RequestMapping("/api")
public class CostingGroupDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CostingGroupDetailsResource.class);

    private static final String ENTITY_NAME = "costingGroupDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CostingGroupDetailsRepository costingGroupDetailsRepository;

    public CostingGroupDetailsResource(CostingGroupDetailsRepository costingGroupDetailsRepository) {
        this.costingGroupDetailsRepository = costingGroupDetailsRepository;
    }

    /**
     * {@code POST  /costing-group-details} : Create a new costingGroupDetails.
     *
     * @param costingGroupDetails the costingGroupDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costingGroupDetails, or with status {@code 400 (Bad Request)} if the costingGroupDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/costing-group-details")
    public ResponseEntity<CostingGroupDetails> createCostingGroupDetails(@Valid @RequestBody CostingGroupDetails costingGroupDetails) throws URISyntaxException {
        log.debug("REST request to save CostingGroupDetails : {}", costingGroupDetails);
        if (costingGroupDetails.getId() != null) {
            throw new BadRequestAlertException("A new costingGroupDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costingGroupDetails.setCode(costingGroupDetails.getCode().trim().toUpperCase());
        costingGroupDetails.setDescription(costingGroupDetails.getDescription().trim().toUpperCase());
        costingGroupDetails.setMasterType(costingGroupDetails.getMasterType().trim().toUpperCase());
        costingGroupDetails.setUgcType(costingGroupDetails.getUgcType().trim().toUpperCase());
        costingGroupDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingGroupDetails.setCreatedDate(Instant.now());
        CostingGroupDetails result = costingGroupDetailsRepository.save(costingGroupDetails);
        return ResponseEntity.created(new URI("/api/costing-group-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /costing-group-details} : Updates an existing costingGroupDetails.
     *
     * @param costingGroupDetails the costingGroupDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costingGroupDetails,
     * or with status {@code 400 (Bad Request)} if the costingGroupDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costingGroupDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/costing-group-details")
    public ResponseEntity<CostingGroupDetails> updateCostingGroupDetails(@Valid @RequestBody CostingGroupDetails costingGroupDetails) throws URISyntaxException {
        log.debug("REST request to update CostingGroupDetails : {}", costingGroupDetails);
        if (costingGroupDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        costingGroupDetails.setCode(costingGroupDetails.getCode().trim().toUpperCase());
        costingGroupDetails.setDescription(costingGroupDetails.getDescription().trim().toUpperCase());
        costingGroupDetails.setMasterType(costingGroupDetails.getMasterType().trim().toUpperCase());
        costingGroupDetails.setUgcType(costingGroupDetails.getUgcType().trim().toUpperCase());
        costingGroupDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingGroupDetails.setLastUpdatedDate(Instant.now());
        CostingGroupDetails result = costingGroupDetailsRepository.save(costingGroupDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costingGroupDetails.getId().toString()))
            .body(result);
    }
    

    /**
     * {@code GET  /costing-group-details} : get all the costingGroupDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingGroupDetails in body.
     */
    @GetMapping("/costing-group-details")
    public ResponseEntity<List<CostingGroupDetails>> getAllCostingGroupDetails(Pageable pageable) {
        log.debug("REST request to get a page of CostingGroupDetails");
        Page<CostingGroupDetails> page = costingGroupDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code POST  /costing-group-details} : get all the costingGroupDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingGroupDetails in body.
     */
    @PostMapping("/costing-group-details-qry")
    public ResponseEntity<List<CostingGroupDetails>> getAllCostingGroupDetails(@Valid @RequestBody CostingProcessMasterSearch search) {
        log.debug("REST request to get a page of CostingGroupDetails");
        String code = "%";
        String description = "%";
        if (search.getProcesscode() != null) {
        	code = "%" + search.getProcesscode().toUpperCase().trim() + "%";
        }
        if (search.getProcessdesc() != null) {
        	description = "%" + search.getProcessdesc().toUpperCase().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<CostingGroupDetails> page = costingGroupDetailsRepository.findAllByCodeAndDesc(code,description,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /costing-group-details/:id} : get the "id" costingGroupDetails.
     *
     * @param id the id of the costingGroupDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costingGroupDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/costing-group-details/{id}")
    public ResponseEntity<CostingGroupDetails> getCostingGroupDetails(@PathVariable Long id) {
        log.debug("REST request to get CostingGroupDetails : {}", id);
        Optional<CostingGroupDetails> costingGroupDetails = costingGroupDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(costingGroupDetails);
    }

    /**
     * {@code DELETE  /costing-group-details/:id} : delete the "id" costingGroupDetails.
     *
     * @param id the id of the costingGroupDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/costing-group-details/{id}")
    public ResponseEntity<Void> deleteCostingGroupDetails(@PathVariable Long id) {
        log.debug("REST request to delete CostingGroupDetails : {}", id);
        costingGroupDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
