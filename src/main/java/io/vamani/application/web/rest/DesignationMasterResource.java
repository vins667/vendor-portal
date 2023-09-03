package io.vamani.application.web.rest;
import io.vamani.application.domain.DesignationMaster;
import io.vamani.application.repository.DesignationMasterRepository;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DesignationMaster.
 */
@RestController
@RequestMapping("/api")
public class DesignationMasterResource {

    private final Logger log = LoggerFactory.getLogger(DesignationMasterResource.class);

    private static final String ENTITY_NAME = "designationMaster";

    private final DesignationMasterRepository designationMasterRepository;

    public DesignationMasterResource(DesignationMasterRepository designationMasterRepository) {
        this.designationMasterRepository = designationMasterRepository;
    }

    /**
     * POST  /designation-masters : Create a new designationMaster.
     *
     * @param designationMaster the designationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new designationMaster, or with status 400 (Bad Request) if the designationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/designation-masters")
    public ResponseEntity<DesignationMaster> createDesignationMaster(@Valid @RequestBody DesignationMaster designationMaster) throws URISyntaxException {
        log.debug("REST request to save DesignationMaster : {}", designationMaster);
        if (designationMaster.getId() != null) {
            throw new BadRequestAlertException("A new designationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        designationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        designationMaster.setCreatedDate(Instant.now());
        DesignationMaster result = designationMasterRepository.save(designationMaster);
        return ResponseEntity.created(new URI("/api/designation-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /designation-masters : Updates an existing designationMaster.
     *
     * @param designationMaster the designationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated designationMaster,
     * or with status 400 (Bad Request) if the designationMaster is not valid,
     * or with status 500 (Internal Server Error) if the designationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/designation-masters")
    public ResponseEntity<DesignationMaster> updateDesignationMaster(@Valid @RequestBody DesignationMaster designationMaster) throws URISyntaxException {
        log.debug("REST request to update DesignationMaster : {}", designationMaster);
        if (designationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        designationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        designationMaster.setLastUpdatedDate(Instant.now());
        DesignationMaster result = designationMasterRepository.save(designationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, designationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /designation-masters : get all the designationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of designationMasters in body
     */
    @GetMapping("/designation-masters")
    public ResponseEntity<List<DesignationMaster>> getAllDesignationMasters(@PageableDefault(value = Integer.MAX_VALUE) @SortDefault(sort = "designationName", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of DesignationMasters");
        Page<DesignationMaster> page = designationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/designation-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /designation-masters/:id : get the "id" designationMaster.
     *
     * @param id the id of the designationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the designationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/designation-masters/{id}")
    public ResponseEntity<DesignationMaster> getDesignationMaster(@PathVariable Long id) {
        log.debug("REST request to get DesignationMaster : {}", id);
        Optional<DesignationMaster> designationMaster = designationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(designationMaster);
    }

    /**
     * DELETE  /designation-masters/:id : delete the "id" designationMaster.
     *
     * @param id the id of the designationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/designation-masters/{id}")
    public ResponseEntity<Void> deleteDesignationMaster(@PathVariable Long id) {
        log.debug("REST request to delete DesignationMaster : {}", id);
        designationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
