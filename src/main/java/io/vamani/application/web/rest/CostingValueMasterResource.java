package io.vamani.application.web.rest;

import io.vamani.application.domain.CostingValueMaster;
import io.vamani.application.model.CostingProcessMasterSearch;
import io.vamani.application.repository.CostingValueMasterRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.CostingValueMaster}.
 */
@RestController
@RequestMapping("/api")
public class CostingValueMasterResource {

    private final Logger log = LoggerFactory.getLogger(CostingValueMasterResource.class);

    private static final String ENTITY_NAME = "costingValueMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CostingValueMasterRepository costingValueMasterRepository;

    public CostingValueMasterResource(CostingValueMasterRepository costingValueMasterRepository) {
        this.costingValueMasterRepository = costingValueMasterRepository;
    }

    /**
     * {@code POST  /costing-value-masters} : Create a new costingValueMaster.
     *
     * @param costingValueMaster the costingValueMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costingValueMaster, or with status {@code 400 (Bad Request)} if the costingValueMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/costing-value-masters")
    public ResponseEntity<CostingValueMaster> createCostingValueMaster(@Valid @RequestBody CostingValueMaster costingValueMaster) throws URISyntaxException {
        log.debug("REST request to save CostingValueMaster : {}", costingValueMaster);
        if (costingValueMaster.getId() != null) {
            throw new BadRequestAlertException("A new costingValueMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costingValueMaster.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingValueMaster.setCreateddate((Instant.now()));
        CostingValueMaster result = costingValueMasterRepository.save(costingValueMaster);
        return ResponseEntity.created(new URI("/api/costing-value-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /costing-value-masters} : Updates an existing costingValueMaster.
     *
     * @param costingValueMaster the costingValueMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costingValueMaster,
     * or with status {@code 400 (Bad Request)} if the costingValueMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costingValueMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/costing-value-masters")
    public ResponseEntity<CostingValueMaster> updateCostingValueMaster(@Valid @RequestBody CostingValueMaster costingValueMaster) throws URISyntaxException {
        log.debug("REST request to update CostingValueMaster : {}", costingValueMaster);
        if (costingValueMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        costingValueMaster.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingValueMaster.setUpdateddate(Instant.now());
        CostingValueMaster result = costingValueMasterRepository.save(costingValueMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costingValueMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /costing-value-masters} : get all the costingValueMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingValueMasters in body.
     */
    @PostMapping("/costing-value-masters-qry")
    public ResponseEntity<List<CostingValueMaster>> getAllCostingValueMasters(@Valid @RequestBody CostingProcessMasterSearch search) {
        log.debug("REST request to get a page of CostingValueMasters");
        String processname = "%";
        String valuetype = "%";
        if (search.getProcesscode() != null) {
        	processname = "%" + search.getProcesscode().toUpperCase().trim() + "%";
        }
        if (search.getProcessdesc() != null) {
        	valuetype = "%" + search.getProcessdesc().toUpperCase().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<CostingValueMaster> page = costingValueMasterRepository.findAllByCodeAndDsc(processname,valuetype,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /costing-value-masters/:id} : get the "id" costingValueMaster.
     *
     * @param id the id of the costingValueMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costingValueMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/costing-value-masters/{id}")
    public ResponseEntity<CostingValueMaster> getCostingValueMaster(@PathVariable Long id) {
        log.debug("REST request to get CostingValueMaster : {}", id);
        Optional<CostingValueMaster> costingValueMaster = costingValueMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(costingValueMaster);
    }

    /**
     * {@code DELETE  /costing-value-masters/:id} : delete the "id" costingValueMaster.
     *
     * @param id the id of the costingValueMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/costing-value-masters/{id}")
    public ResponseEntity<Void> deleteCostingValueMaster(@PathVariable Long id) {
        log.debug("REST request to delete CostingValueMaster : {}", id);
        costingValueMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
