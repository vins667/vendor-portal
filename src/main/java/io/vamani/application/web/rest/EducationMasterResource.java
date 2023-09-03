package io.vamani.application.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.EducationMaster;
import io.vamani.application.repository.EducationMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing EducationMaster.
 */
@RestController
@RequestMapping("/api")
public class EducationMasterResource {

    private final Logger log = LoggerFactory.getLogger(EducationMasterResource.class);

    private static final String ENTITY_NAME = "educationMaster";

    private final EducationMasterRepository educationMasterRepository;

    public EducationMasterResource(EducationMasterRepository educationMasterRepository) {
        this.educationMasterRepository = educationMasterRepository;
    }

    /**
     * POST  /education-masters : Create a new educationMaster.
     *
     * @param educationMaster the educationMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationMaster, or with status 400 (Bad Request) if the educationMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/education-masters")
    public ResponseEntity<EducationMaster> createEducationMaster(@Valid @RequestBody EducationMaster educationMaster) throws URISyntaxException {
        log.debug("REST request to save EducationMaster : {}", educationMaster);
        if (educationMaster.getId() != null) {
            throw new BadRequestAlertException("A new educationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        educationMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        educationMaster.setCreatedDate(Instant.now());
        EducationMaster result = educationMasterRepository.save(educationMaster);
        return ResponseEntity.created(new URI("/api/education-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /education-masters : Updates an existing educationMaster.
     *
     * @param educationMaster the educationMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationMaster,
     * or with status 400 (Bad Request) if the educationMaster is not valid,
     * or with status 500 (Internal Server Error) if the educationMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/education-masters")
    public ResponseEntity<EducationMaster> updateEducationMaster(@Valid @RequestBody EducationMaster educationMaster) throws URISyntaxException {
        log.debug("REST request to update EducationMaster : {}", educationMaster);
        if (educationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        educationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        educationMaster.setLastUpdatedDate(Instant.now());
        EducationMaster result = educationMasterRepository.save(educationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /education-masters : get all the educationMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of educationMasters in body
     */
    @GetMapping("/education-masters")
    public ResponseEntity<List<EducationMaster>> getAllEducationMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of EducationMasters");
        Page<EducationMaster> page = educationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/education-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /education-masters/:id : get the "id" educationMaster.
     *
     * @param id the id of the educationMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationMaster, or with status 404 (Not Found)
     */
    @GetMapping("/education-masters/{id}")
    public ResponseEntity<EducationMaster> getEducationMaster(@PathVariable Long id) {
        log.debug("REST request to get EducationMaster : {}", id);
        Optional<EducationMaster> educationMaster = educationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(educationMaster);
    }

    /**
     * DELETE  /education-masters/:id : delete the "id" educationMaster.
     *
     * @param id the id of the educationMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/education-masters/{id}")
    public ResponseEntity<Void> deleteEducationMaster(@PathVariable Long id) {
        log.debug("REST request to delete EducationMaster : {}", id);
        educationMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
