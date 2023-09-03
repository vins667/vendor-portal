package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.DepartmentMaster;
import io.vamani.application.repository.DepartmentMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DepartmentMaster.
 */
@RestController
@RequestMapping("/api")
public class DepartmentMasterResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentMasterResource.class);

    private static final String ENTITY_NAME = "departmentMaster";

    private final DepartmentMasterRepository departmentMasterRepository;

    public DepartmentMasterResource(DepartmentMasterRepository departmentMasterRepository) {
        this.departmentMasterRepository = departmentMasterRepository;
    }

    /**
     * POST  /department-masters : Create a new departmentMaster.
     *
     * @param departmentMaster the departmentMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new departmentMaster, or with status 400 (Bad Request) if the departmentMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/department-masters")
    @Timed
    public ResponseEntity<DepartmentMaster> createDepartmentMaster(@Valid @RequestBody DepartmentMaster departmentMaster) throws URISyntaxException {
        log.debug("REST request to save DepartmentMaster : {}", departmentMaster);
        if (departmentMaster.getId() != null) {
            throw new BadRequestAlertException("A new departmentMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        departmentMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        departmentMaster.setCreatedDate(Instant.now());
        DepartmentMaster result = departmentMasterRepository.save(departmentMaster);
        return ResponseEntity.created(new URI("/api/department-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /department-masters : Updates an existing departmentMaster.
     *
     * @param departmentMaster the departmentMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated departmentMaster,
     * or with status 400 (Bad Request) if the departmentMaster is not valid,
     * or with status 500 (Internal Server Error) if the departmentMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/department-masters")
    @Timed
    public ResponseEntity<DepartmentMaster> updateDepartmentMaster(@Valid @RequestBody DepartmentMaster departmentMaster) throws URISyntaxException {
        log.debug("REST request to update DepartmentMaster : {}", departmentMaster);
        if (departmentMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        departmentMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        departmentMaster.setLastUpdatedDate(Instant.now());
        DepartmentMaster result = departmentMasterRepository.save(departmentMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, departmentMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /department-masters : get all the departmentMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of departmentMasters in body
     */
    @GetMapping("/department-masters")
    @Timed
    public ResponseEntity<List<DepartmentMaster>> getAllDepartmentMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "deptDesc", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of DepartmentMasters");
        Page<DepartmentMaster> page = departmentMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/department-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /department-masters/:id : get the "id" departmentMaster.
     *
     * @param id the id of the departmentMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the departmentMaster, or with status 404 (Not Found)
     */
    @GetMapping("/department-masters/{id}")
    @Timed
    public ResponseEntity<DepartmentMaster> getDepartmentMaster(@PathVariable Long id) {
        log.debug("REST request to get DepartmentMaster : {}", id);
        Optional<DepartmentMaster> departmentMaster = departmentMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(departmentMaster);
    }

    /**
     * DELETE  /department-masters/:id : delete the "id" departmentMaster.
     *
     * @param id the id of the departmentMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/department-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepartmentMaster(@PathVariable Long id) {
        log.debug("REST request to delete DepartmentMaster : {}", id);

        departmentMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
