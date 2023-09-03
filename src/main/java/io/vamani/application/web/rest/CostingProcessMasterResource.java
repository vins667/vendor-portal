package io.vamani.application.web.rest;
import io.vamani.application.domain.CostingProcessMaster;
import io.vamani.application.model.CostingProcessMasterSearch;
import io.vamani.application.repository.CostingProcessMasterRepository;
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
 * REST controller for managing {@link io.vamani.application.domain.CostingProcessMaster}.
 */
@RestController
@RequestMapping("/api")
public class CostingProcessMasterResource {

    private final Logger log = LoggerFactory.getLogger(CostingProcessMasterResource.class);

    private static final String ENTITY_NAME = "costingProcessMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CostingProcessMasterRepository costingProcessMasterRepository;

    public CostingProcessMasterResource(CostingProcessMasterRepository costingProcessMasterRepository) {
        this.costingProcessMasterRepository = costingProcessMasterRepository;
    }

    /**
     * {@code POST  /costing-process-masters} : Create a new costingProcessMaster.
     *
     * @param costingProcessMaster the costingProcessMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costingProcessMaster, or with status {@code 400 (Bad Request)} if the costingProcessMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/costing-process-masters")
    public ResponseEntity<CostingProcessMaster> createCostingProcessMaster(@Valid @RequestBody CostingProcessMaster costingProcessMaster) throws URISyntaxException {
        log.debug("REST request to save CostingProcessMaster : {}", costingProcessMaster);
        if (costingProcessMaster.getId() != null) {
            throw new BadRequestAlertException("A new costingProcessMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costingProcessMaster.setProcesscode(costingProcessMaster.getProcesscode().toUpperCase().trim());
        costingProcessMaster.setProcessdesc(costingProcessMaster.getProcessdesc().toUpperCase().trim());
        costingProcessMaster.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingProcessMaster.setCreateddate(Instant.now());
        CostingProcessMaster result = costingProcessMasterRepository.save(costingProcessMaster);
        return ResponseEntity.created(new URI("/api/costing-process-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /costing-process-masters} : Updates an existing costingProcessMaster.
     *
     * @param costingProcessMaster the costingProcessMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costingProcessMaster,
     * or with status {@code 400 (Bad Request)} if the costingProcessMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costingProcessMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/costing-process-masters")
    public ResponseEntity<CostingProcessMaster> updateCostingProcessMaster(@Valid @RequestBody CostingProcessMaster costingProcessMaster) throws URISyntaxException {
        log.debug("REST request to update CostingProcessMaster : {}", costingProcessMaster);
        if (costingProcessMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        costingProcessMaster.setProcesscode(costingProcessMaster.getProcesscode().toUpperCase().trim());
        costingProcessMaster.setProcessdesc(costingProcessMaster.getProcessdesc().toUpperCase().trim());
        costingProcessMaster.setUpdatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
        costingProcessMaster.setUpdateddate(Instant.now());
        CostingProcessMaster result = costingProcessMasterRepository.save(costingProcessMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costingProcessMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /costing-process-masters} : get all the costingProcessMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costingProcessMasters in body.
     */
    @PostMapping("/costing-process-masters-qry")
    public ResponseEntity<List<CostingProcessMaster>> getAllCostingProcessMasters(@Valid @RequestBody CostingProcessMasterSearch search) {
        log.debug("REST request to get a page of CostingProcessMasters");
        String processcode = "%";
        String processdesc = "%";
        if (search.getProcesscode() != null) {
        	processcode = "%" + search.getProcesscode().toUpperCase().trim() + "%";
        }
        if (search.getProcessdesc() != null) {
        	processdesc = "%" + search.getProcessdesc().toUpperCase().trim() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<CostingProcessMaster> page = costingProcessMasterRepository.findAllByCodeAndDsc(processcode,processdesc,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

  
   @GetMapping("/costing-process-masters-list")
   public ResponseEntity<List<CostingProcessMaster>> getAllCostingProcessMasters() {
       log.debug("REST request to get a page of CostingProcessMasters");
       List<CostingProcessMaster> costingProcessMaster = costingProcessMasterRepository.findAllCostingProcessMaster();
       return ResponseUtil.wrapOrNotFound(Optional.of(costingProcessMaster));
   }

    
    /**
     * {@code GET  /costing-process-masters/:id} : get the "id" costingProcessMaster.
     *
     * @param id the id of the costingProcessMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costingProcessMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/costing-process-masters/{id}")
    public ResponseEntity<CostingProcessMaster> getCostingProcessMaster(@PathVariable Long id) {
        log.debug("REST request to get CostingProcessMaster : {}", id);
        Optional<CostingProcessMaster> costingProcessMaster = costingProcessMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(costingProcessMaster);
    }

    /**
     * {@code DELETE  /costing-process-masters/:id} : delete the "id" costingProcessMaster.
     *
     * @param id the id of the costingProcessMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/costing-process-masters/{id}")
    public ResponseEntity<Void> deleteCostingProcessMaster(@PathVariable Long id) {
        log.debug("REST request to delete CostingProcessMaster : {}", id);
        costingProcessMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
