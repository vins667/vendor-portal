package io.vamani.application.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.CostingGroupMaster;
import io.vamani.application.model.CostingProcessMasterSearch;
import io.vamani.application.repository.CostingGroupMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link io.vamani.application.domain.CostingGroupMaster}.
 */
@RestController
@RequestMapping("/api")
public class CostingGroupMasterResource {

    private final Logger log = LoggerFactory.getLogger(CostingGroupMasterResource.class);

    private static final String ENTITY_NAME = "costingGroupMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CostingGroupMasterRepository costingGroupMasterRepository;

    public CostingGroupMasterResource(CostingGroupMasterRepository costingGroupMasterRepository) {
        this.costingGroupMasterRepository = costingGroupMasterRepository;
    }

    /**
     * {@code POST  /costing-group-masters} : Create a new costingGroupMaster.
     *
     * @param costingGroupMaster the costingGroupMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costingGroupMaster, or with status {@code 400 (Bad Request)} if the costingGroupMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/costing-group-masters")
    public ResponseEntity<CostingGroupMaster> createCostingGroupMaster(@Valid @RequestBody CostingGroupMaster costingGroupMaster) throws URISyntaxException {
        log.debug("REST request to save CostingGroupMaster : {}", costingGroupMaster);
        if (costingGroupMaster.getId() != null) {
            throw new BadRequestAlertException("A new costingGroupMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costingGroupMaster.setCode(costingGroupMaster.getCode().trim().toUpperCase());
        costingGroupMaster.setDescription(costingGroupMaster.getDescription().trim().toUpperCase());
        costingGroupMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingGroupMaster.setCreatedDate(Instant.now());
        CostingGroupMaster result = costingGroupMasterRepository.save(costingGroupMaster);
        return ResponseEntity.created(new URI("/api/costing-group-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /costing-group-masters} : Updates an existing costingGroupMaster.
     *
     * @param costingGroupMaster the costingGroupMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costingGroupMaster,
     * or with status {@code 400 (Bad Request)} if the costingGroupMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costingGroupMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/costing-group-masters")
    public ResponseEntity<CostingGroupMaster> updateCostingGroupMaster(@Valid @RequestBody CostingGroupMaster costingGroupMaster) throws URISyntaxException {
        log.debug("REST request to update CostingGroupMaster : {}", costingGroupMaster);
        if (costingGroupMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        costingGroupMaster.setCode(costingGroupMaster.getCode().trim().toUpperCase());
        costingGroupMaster.setDescription(costingGroupMaster.getDescription().trim().toUpperCase());
        costingGroupMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingGroupMaster.setLastUpdatedDate(Instant.now());
        CostingGroupMaster result = costingGroupMasterRepository.save(costingGroupMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costingGroupMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /costing-group-masters} : get all the costingGroupMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingGroupMasters in body.
     */
    @GetMapping("/costing-group-masters")
    public ResponseEntity<List<CostingGroupMaster>> getAllCostingGroupMasters(Pageable pageable) {
        log.debug("REST request to get a page of CostingGroupMasters");
        Page<CostingGroupMaster> page = costingGroupMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * {@code POST  /costing-group-masters} : get all the costingGroupMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingGroupMasters in body.
     */
    @PostMapping("/costing-group-masters-qry")
    public ResponseEntity<List<CostingGroupMaster>> getAllCostingGroupMasters(@Valid @RequestBody CostingProcessMasterSearch search) {
        log.debug("REST request to get a page of CostingGroupMasters");
        String code = "%";
        String description = "%";
        if (search.getProcesscode() != null) {
        	code = "%" + search.getProcesscode().toUpperCase().trim() + "%";
        }
        if (search.getProcessdesc() != null) {
        	description = "%" + search.getProcessdesc().toUpperCase().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<CostingGroupMaster> page = costingGroupMasterRepository.findAllByCodeAndDesc(code,description,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /costing-group-masters/:id} : get the "id" costingGroupMaster.
     *
     * @param id the id of the costingGroupMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costingGroupMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/costing-group-masters/{id}")
    public ResponseEntity<CostingGroupMaster> getCostingGroupMaster(@PathVariable Long id) {
        log.debug("REST request to get CostingGroupMaster : {}", id);
        Optional<CostingGroupMaster> costingGroupMaster = costingGroupMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(costingGroupMaster);
    }

    /**
     * {@code DELETE  /costing-group-masters/:id} : delete the "id" costingGroupMaster.
     *
     * @param id the id of the costingGroupMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/costing-group-masters/{id}")
    public ResponseEntity<Void> deleteCostingGroupMaster(@PathVariable Long id) {
        log.debug("REST request to delete CostingGroupMaster : {}", id);
        costingGroupMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
