package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.RecruitmentDocumentMaster;
import io.vamani.application.repository.RecruitmentDocumentMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RecruitmentDocumentMaster.
 */
@RestController
@RequestMapping("/api")
public class RecruitmentDocumentMasterResource {

    private final Logger log = LoggerFactory.getLogger(RecruitmentDocumentMasterResource.class);

    private static final String ENTITY_NAME = "recruitmentDocumentMaster";

    private final RecruitmentDocumentMasterRepository recruitmentDocumentMasterRepository;

    public RecruitmentDocumentMasterResource(RecruitmentDocumentMasterRepository recruitmentDocumentMasterRepository) {
        this.recruitmentDocumentMasterRepository = recruitmentDocumentMasterRepository;
    }

    /**
     * POST  /recruitment-document-masters : Create a new recruitmentDocumentMaster.
     *
     * @param recruitmentDocumentMaster the recruitmentDocumentMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recruitmentDocumentMaster, or with status 400 (Bad Request) if the recruitmentDocumentMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recruitment-document-masters")
    @Timed
    public ResponseEntity<RecruitmentDocumentMaster> createRecruitmentDocumentMaster(@Valid @RequestBody RecruitmentDocumentMaster recruitmentDocumentMaster) throws URISyntaxException {
        log.debug("REST request to save RecruitmentDocumentMaster : {}", recruitmentDocumentMaster);
        if (recruitmentDocumentMaster.getId() != null) {
            throw new BadRequestAlertException("A new recruitmentDocumentMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        recruitmentDocumentMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentDocumentMaster.setCreatedDate(Instant.now());
        RecruitmentDocumentMaster result = recruitmentDocumentMasterRepository.save(recruitmentDocumentMaster);
        return ResponseEntity.created(new URI("/api/recruitment-document-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recruitment-document-masters : Updates an existing recruitmentDocumentMaster.
     *
     * @param recruitmentDocumentMaster the recruitmentDocumentMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recruitmentDocumentMaster,
     * or with status 400 (Bad Request) if the recruitmentDocumentMaster is not valid,
     * or with status 500 (Internal Server Error) if the recruitmentDocumentMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recruitment-document-masters")
    @Timed
    public ResponseEntity<RecruitmentDocumentMaster> updateRecruitmentDocumentMaster(@Valid @RequestBody RecruitmentDocumentMaster recruitmentDocumentMaster) throws URISyntaxException {
        log.debug("REST request to update RecruitmentDocumentMaster : {}", recruitmentDocumentMaster);
        if (recruitmentDocumentMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        recruitmentDocumentMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentDocumentMaster.setLastUpdatedDate(Instant.now());
        RecruitmentDocumentMaster result = recruitmentDocumentMasterRepository.save(recruitmentDocumentMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recruitmentDocumentMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recruitment-document-masters : get all the recruitmentDocumentMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recruitmentDocumentMasters in body
     */
    @GetMapping("/recruitment-document-masters")
    @Timed
    public ResponseEntity<List<RecruitmentDocumentMaster>> getAllRecruitmentDocumentMasters(Pageable pageable) {
        log.debug("REST request to get a page of RecruitmentDocumentMasters");
        Page<RecruitmentDocumentMaster> page = recruitmentDocumentMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recruitment-document-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /recruitment-document-masters/:id : get the "id" recruitmentDocumentMaster.
     *
     * @param id the id of the recruitmentDocumentMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentDocumentMaster, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-document-masters/{id}")
    @Timed
    public ResponseEntity<RecruitmentDocumentMaster> getRecruitmentDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentDocumentMaster : {}", id);
        Optional<RecruitmentDocumentMaster> recruitmentDocumentMaster = recruitmentDocumentMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(recruitmentDocumentMaster);
    }

    /**
     * GET  /recruitment-document-masters/:id : get the "id" recruitmentDocumentMaster.
     *
     * @param id the id of the recruitmentDocumentMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentDocumentMaster, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-document-masters-type/{type}")
    @Timed
    public ResponseEntity<List<RecruitmentDocumentMaster>> getRecruitmentDocumentMasterByType(@PathVariable String type) {
        log.debug("REST request to get RecruitmentDocumentMaster : {}", type);
        List<String> docTypes = new ArrayList();
        docTypes.add(type);
        docTypes.add("A");
        List<RecruitmentDocumentMaster> recruitmentDocumentMaster = recruitmentDocumentMasterRepository.findAllByDocumentType(docTypes);
        return ResponseUtil.wrapOrNotFound(Optional.of(recruitmentDocumentMaster));
    }

    /**
     * DELETE  /recruitment-document-masters/:id : delete the "id" recruitmentDocumentMaster.
     *
     * @param id the id of the recruitmentDocumentMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recruitment-document-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecruitmentDocumentMaster(@PathVariable Long id) {
        log.debug("REST request to delete RecruitmentDocumentMaster : {}", id);

        recruitmentDocumentMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
