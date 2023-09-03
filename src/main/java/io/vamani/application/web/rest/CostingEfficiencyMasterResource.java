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
import io.vamani.application.domain.CostingEfficiencyMaster;
import io.vamani.application.model.CostingProcessMasterSearch;
import io.vamani.application.repository.CostingEfficiencyMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link io.vamani.application.domain.CostingEfficiencyMaster}.
 */
@RestController
@RequestMapping("/api")
public class CostingEfficiencyMasterResource {

    private final Logger log = LoggerFactory.getLogger(CostingEfficiencyMasterResource.class);

    private static final String ENTITY_NAME = "costingEfficiencyMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CostingEfficiencyMasterRepository costingEfficiencyMasterRepository;

    public CostingEfficiencyMasterResource(CostingEfficiencyMasterRepository costingEfficiencyMasterRepository) {
        this.costingEfficiencyMasterRepository = costingEfficiencyMasterRepository;
    }

    /**
     * {@code POST  /costing-efficiency-masters} : Create a new costingEfficiencyMaster.
     *
     * @param costingEfficiencyMaste the costingEfficiencyMaste to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costingEfficiencyMaste, or with status {@code 400 (Bad Request)} if the costingEfficiencyMaste has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/costing-efficiency-masters")
    public ResponseEntity<CostingEfficiencyMaster> createCostingEfficiencyMaster(@Valid @RequestBody CostingEfficiencyMaster costingEfficiencyMaster) throws URISyntaxException {
        log.debug("REST request to save CostingEfficiencyMaster : {}", costingEfficiencyMaster);
        if (costingEfficiencyMaster.getId() != null) {
            throw new BadRequestAlertException("A new costingEfficiencyMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costingEfficiencyMaster.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingEfficiencyMaster.setCreateddate(Instant.now());
        CostingEfficiencyMaster result = costingEfficiencyMasterRepository.save(costingEfficiencyMaster);
        return ResponseEntity.created(new URI("/api/costing-efficiency-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /costing-efficiency-masters} : Updates an existing costingEfficiencyMaster.
     *
     * @param costingEfficiencyMaste the costingEfficiencyMaste to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costingEfficiencyMaste,
     * or with status {@code 400 (Bad Request)} if the costingEfficiencyMaste is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costingEfficiencyMaste couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/costing-efficiency-masters")
    public ResponseEntity<CostingEfficiencyMaster> updateCostingEfficiencyMaster(@Valid @RequestBody CostingEfficiencyMaster costingEfficiencyMaster) throws URISyntaxException {
        log.debug("REST request to update CostingEfficiencyMaste : {}", costingEfficiencyMaster);
        if (costingEfficiencyMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        costingEfficiencyMaster.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingEfficiencyMaster.setUpdateddate(Instant.now());
        CostingEfficiencyMaster result = costingEfficiencyMasterRepository.save(costingEfficiencyMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costingEfficiencyMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /costing-efficiency-masters} : get all the costingEfficiencyMastes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingEfficiencyMastes in body.
     */
    @GetMapping("/costing-efficiency-masters")
    public ResponseEntity<List<CostingEfficiencyMaster>> getAllCostingEfficiencyMastes(Pageable pageable) {
        log.debug("REST request to get a page of CostingEfficiencyMastes");
        Page<CostingEfficiencyMaster> page = costingEfficiencyMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

   
    @PostMapping("/costing-efficiency-masters-qry")
    public ResponseEntity<List<CostingEfficiencyMaster>> getAllCostingEfficiencyMasters(@Valid @RequestBody CostingProcessMasterSearch search) {
        log.debug("REST request to get a page of CostingEfficiencyMasters");
        String fromqty = "%";
        String toqty = "%";
        if (search.getProcesscode() != null) {
        	fromqty = "%" + search.getProcesscode() + "%";
        }
        if (search.getProcessdesc() != null) {
        	toqty = "%" + search.getProcessdesc() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<CostingEfficiencyMaster> page = costingEfficiencyMasterRepository.findAllByFromQtyAndToQty(fromqty,toqty,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /costing-efficiency-masters/:id} : get the "id" costingEfficiencyMaster.
     *
     * @param id the id of the costingEfficiencyMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costingEfficiencyMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/costing-efficiency-masters/{id}")
    public ResponseEntity<CostingEfficiencyMaster> getCostingEfficiencyMaster(@PathVariable Long id) {
        log.debug("REST request to get CostingEfficiencyMaste : {}", id);
        Optional<CostingEfficiencyMaster> costingEfficiencyMaster = costingEfficiencyMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(costingEfficiencyMaster);
    }

    /**
     * {@code DELETE  /costing-efficiency-masters/:id} : delete the "id" costingEfficiencyMaster.
     *
     * @param id the id of the costingEfficiencyMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/costing-efficiency-masters/{id}")
    public ResponseEntity<Void> deleteCostingEfficiencyMaste(@PathVariable Long id) {
        log.debug("REST request to delete CostingEfficiencyMaster : {}", id);
        costingEfficiencyMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
