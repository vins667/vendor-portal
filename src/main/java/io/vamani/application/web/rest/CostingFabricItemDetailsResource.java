package io.vamani.application.web.rest;

import io.vamani.application.domain.CostingFabricItemDetails;
import io.vamani.application.model.CostingProcessMasterSearch;
import io.vamani.application.repository.CostingFabricItemDetailsRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.CostingFabricItemDetails}.
 */
@RestController
@RequestMapping("/api")
public class CostingFabricItemDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CostingFabricItemDetailsResource.class);

    private static final String ENTITY_NAME = "costingFabricItemDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CostingFabricItemDetailsRepository costingFabricItemDetailsRepository;

    public CostingFabricItemDetailsResource(CostingFabricItemDetailsRepository costingFabricItemDetailsRepository) {
        this.costingFabricItemDetailsRepository = costingFabricItemDetailsRepository;
    }

    /**
     * {@code POST  /costing-fabric-item-details} : Create a new costingFabricItemDetails.
     *
     * @param costingFabricItemDetails the costingFabricItemDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costingFabricItemDetails, or with status {@code 400 (Bad Request)} if the costingFabricItemDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/costing-fabric-item-details")
    public ResponseEntity<CostingFabricItemDetails> createCostingFabricItemDetails(@Valid @RequestBody CostingFabricItemDetails costingFabricItemDetails) throws URISyntaxException {
        log.debug("REST request to save CostingFabricItemDetails : {}", costingFabricItemDetails);
        if (costingFabricItemDetails.getId() != null) {
            throw new BadRequestAlertException("A new costingFabricItemDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costingFabricItemDetails.setItemType(costingFabricItemDetails.getItemType().trim().toUpperCase());
        costingFabricItemDetails.setCode(costingFabricItemDetails.getCode().trim().toUpperCase());
        costingFabricItemDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingFabricItemDetails.setCreatedDate(Instant.now());
        CostingFabricItemDetails result = costingFabricItemDetailsRepository.save(costingFabricItemDetails);
        return ResponseEntity.created(new URI("/api/costing-fabric-item-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /costing-fabric-item-details} : Updates an existing costingFabricItemDetails.
     *
     * @param costingFabricItemDetails the costingFabricItemDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costingFabricItemDetails,
     * or with status {@code 400 (Bad Request)} if the costingFabricItemDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costingFabricItemDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/costing-fabric-item-details")
    public ResponseEntity<CostingFabricItemDetails> updateCostingFabricItemDetails(@Valid @RequestBody CostingFabricItemDetails costingFabricItemDetails) throws URISyntaxException {
        log.debug("REST request to update CostingFabricItemDetails : {}", costingFabricItemDetails);
        if (costingFabricItemDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        costingFabricItemDetails.setItemType(costingFabricItemDetails.getItemType().trim().toUpperCase());
        costingFabricItemDetails.setCode(costingFabricItemDetails.getCode().trim().toUpperCase());
        costingFabricItemDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingFabricItemDetails.setLastUpdatedDate(Instant.now());
        CostingFabricItemDetails result = costingFabricItemDetailsRepository.save(costingFabricItemDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costingFabricItemDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /costing-fabric-item-details} : get all the costingFabricItemDetails.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingFabricItemDetails in body.
     */
    @PostMapping("/costing-fabric-item-details-qry")
    public ResponseEntity<List<CostingFabricItemDetails>> getAllCostingFabricItemDetails(@Valid @RequestBody CostingProcessMasterSearch search) {
        log.debug("REST request to get a page of CostingFabricItemDetails");
        String itemtype = "%";
        String code = "%";
        if (search.getProcesscode() != null) {
        	itemtype = "%" + search.getProcesscode().toUpperCase().trim() + "%";
        }
        if (search.getProcessdesc() != null) {
        	code = "%" + search.getProcessdesc().toUpperCase().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<CostingFabricItemDetails> page = costingFabricItemDetailsRepository.findAllByItemAndCode(itemtype,code,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /costing-fabric-item-details/:id} : get the "id" costingFabricItemDetails.
     *
     * @param id the id of the costingFabricItemDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costingFabricItemDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/costing-fabric-item-details/{id}")
    public ResponseEntity<CostingFabricItemDetails> getCostingFabricItemDetails(@PathVariable Long id) {
        log.debug("REST request to get CostingFabricItemDetails : {}", id);
        Optional<CostingFabricItemDetails> costingFabricItemDetails = costingFabricItemDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(costingFabricItemDetails);
    }

    /**
     * {@code DELETE  /costing-fabric-item-details/:id} : delete the "id" costingFabricItemDetails.
     *
     * @param id the id of the costingFabricItemDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/costing-fabric-item-details/{id}")
    public ResponseEntity<Void> deleteCostingFabricItemDetails(@PathVariable Long id) {
        log.debug("REST request to delete CostingFabricItemDetails : {}", id);
        costingFabricItemDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
