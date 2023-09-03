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
import io.vamani.application.domain.EducationTypeMaster;
import io.vamani.application.repository.EducationTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing EducationTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class EducationTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(EducationTypeMasterResource.class);

    private static final String ENTITY_NAME = "educationTypeMaster";

    private final EducationTypeMasterRepository educationTypeMasterRepository;

    public EducationTypeMasterResource(EducationTypeMasterRepository educationTypeMasterRepository) {
        this.educationTypeMasterRepository = educationTypeMasterRepository;
    }

    /**
     * POST  /education-type-masters : Create a new educationTypeMaster.
     *
     * @param educationTypeMaster the educationTypeMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationTypeMaster, or with status 400 (Bad Request) if the educationTypeMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/education-type-masters")
    public ResponseEntity<EducationTypeMaster> createEducationTypeMaster(@Valid @RequestBody EducationTypeMaster educationTypeMaster) throws URISyntaxException {
        log.debug("REST request to save EducationTypeMaster : {}", educationTypeMaster);
        if (educationTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new educationTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        educationTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        educationTypeMaster.setCreatedDate(Instant.now());
        EducationTypeMaster result = educationTypeMasterRepository.save(educationTypeMaster);
        return ResponseEntity.created(new URI("/api/education-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /education-type-masters : Updates an existing educationTypeMaster.
     *
     * @param educationTypeMaster the educationTypeMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationTypeMaster,
     * or with status 400 (Bad Request) if the educationTypeMaster is not valid,
     * or with status 500 (Internal Server Error) if the educationTypeMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/education-type-masters")
    public ResponseEntity<EducationTypeMaster> updateEducationTypeMaster(@Valid @RequestBody EducationTypeMaster educationTypeMaster) throws URISyntaxException {
        log.debug("REST request to update EducationTypeMaster : {}", educationTypeMaster);
        if (educationTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        educationTypeMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        educationTypeMaster.setLastUpdatedDate(Instant.now());
        EducationTypeMaster result = educationTypeMasterRepository.save(educationTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /education-type-masters : get all the educationTypeMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of educationTypeMasters in body
     */
    @GetMapping("/education-type-masters")
    public ResponseEntity<List<EducationTypeMaster>> getAllEducationTypeMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of EducationTypeMasters");
        Page<EducationTypeMaster> page = educationTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/education-type-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /education-type-masters/:id : get the "id" educationTypeMaster.
     *
     * @param id the id of the educationTypeMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationTypeMaster, or with status 404 (Not Found)
     */
    @GetMapping("/education-type-masters/{id}")
    public ResponseEntity<EducationTypeMaster> getEducationTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get EducationTypeMaster : {}", id);
        Optional<EducationTypeMaster> educationTypeMaster = educationTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(educationTypeMaster);
    }

    /**
     * DELETE  /education-type-masters/:id : delete the "id" educationTypeMaster.
     *
     * @param id the id of the educationTypeMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/education-type-masters/{id}")
    public ResponseEntity<Void> deleteEducationTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete EducationTypeMaster : {}", id);
        educationTypeMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
