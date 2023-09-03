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
import io.vamani.application.domain.RecruitmentCountryMaster;
import io.vamani.application.repository.RecruitmentCountryMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing RecruitmentCountryMaster.
 */
@RestController
@RequestMapping("/api")
public class RecruitmentCountryMasterResource {

    private final Logger log = LoggerFactory.getLogger(RecruitmentCountryMasterResource.class);

    private static final String ENTITY_NAME = "recruitmentCountryMaster";

    private final RecruitmentCountryMasterRepository recruitmentCountryMasterRepository;

    public RecruitmentCountryMasterResource(RecruitmentCountryMasterRepository recruitmentCountryMasterRepository) {
        this.recruitmentCountryMasterRepository = recruitmentCountryMasterRepository;
    }

    /**
     * POST  /recruitment-country-masters : Create a new recruitmentCountryMaster.
     *
     * @param recruitmentCountryMaster the recruitmentCountryMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recruitmentCountryMaster, or with status 400 (Bad Request) if the recruitmentCountryMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recruitment-country-masters")
    public ResponseEntity<RecruitmentCountryMaster> createRecruitmentCountryMaster(@Valid @RequestBody RecruitmentCountryMaster recruitmentCountryMaster) throws URISyntaxException {
        log.debug("REST request to save RecruitmentCountryMaster : {}", recruitmentCountryMaster);
        if (recruitmentCountryMaster.getId() != null) {
            throw new BadRequestAlertException("A new recruitmentCountryMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        recruitmentCountryMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentCountryMaster.setCreatedDate(Instant.now());
        RecruitmentCountryMaster result = recruitmentCountryMasterRepository.save(recruitmentCountryMaster);
        return ResponseEntity.created(new URI("/api/recruitment-country-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recruitment-country-masters : Updates an existing recruitmentCountryMaster.
     *
     * @param recruitmentCountryMaster the recruitmentCountryMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recruitmentCountryMaster,
     * or with status 400 (Bad Request) if the recruitmentCountryMaster is not valid,
     * or with status 500 (Internal Server Error) if the recruitmentCountryMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recruitment-country-masters")
    public ResponseEntity<RecruitmentCountryMaster> updateRecruitmentCountryMaster(@Valid @RequestBody RecruitmentCountryMaster recruitmentCountryMaster) throws URISyntaxException {
        log.debug("REST request to update RecruitmentCountryMaster : {}", recruitmentCountryMaster);
        if (recruitmentCountryMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        recruitmentCountryMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentCountryMaster.setLastUpdatedDate(Instant.now());
        RecruitmentCountryMaster result = recruitmentCountryMasterRepository.save(recruitmentCountryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recruitmentCountryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recruitment-country-masters : get all the recruitmentCountryMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recruitmentCountryMasters in body
     */
    @GetMapping("/recruitment-country-masters")
    public ResponseEntity<List<RecruitmentCountryMaster>> getAllRecruitmentCountryMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of RecruitmentCountryMasters");
        Page<RecruitmentCountryMaster> page = recruitmentCountryMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recruitment-country-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /recruitment-country-masters/:id : get the "id" recruitmentCountryMaster.
     *
     * @param id the id of the recruitmentCountryMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentCountryMaster, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-country-masters/{id}")
    public ResponseEntity<RecruitmentCountryMaster> getRecruitmentCountryMaster(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentCountryMaster : {}", id);
        Optional<RecruitmentCountryMaster> recruitmentCountryMaster = recruitmentCountryMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(recruitmentCountryMaster);
    }

    /**
     * DELETE  /recruitment-country-masters/:id : delete the "id" recruitmentCountryMaster.
     *
     * @param id the id of the recruitmentCountryMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recruitment-country-masters/{id}")
    public ResponseEntity<Void> deleteRecruitmentCountryMaster(@PathVariable Long id) {
        log.debug("REST request to delete RecruitmentCountryMaster : {}", id);
        recruitmentCountryMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
