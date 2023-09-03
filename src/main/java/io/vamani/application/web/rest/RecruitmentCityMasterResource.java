package io.vamani.application.web.rest;
import io.vamani.application.domain.RecruitmentCityMaster;
import io.vamani.application.repository.RecruitmentCityMasterRepository;
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
 * REST controller for managing RecruitmentCityMaster.
 */
@RestController
@RequestMapping("/api")
public class RecruitmentCityMasterResource {

    private final Logger log = LoggerFactory.getLogger(RecruitmentCityMasterResource.class);

    private static final String ENTITY_NAME = "recruitmentCityMaster";

    private final RecruitmentCityMasterRepository recruitmentCityMasterRepository;

    public RecruitmentCityMasterResource(RecruitmentCityMasterRepository recruitmentCityMasterRepository) {
        this.recruitmentCityMasterRepository = recruitmentCityMasterRepository;
    }

    /**
     * POST  /recruitment-city-masters : Create a new recruitmentCityMaster.
     *
     * @param recruitmentCityMaster the recruitmentCityMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recruitmentCityMaster, or with status 400 (Bad Request) if the recruitmentCityMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recruitment-city-masters")
    public ResponseEntity<RecruitmentCityMaster> createRecruitmentCityMaster(@Valid @RequestBody RecruitmentCityMaster recruitmentCityMaster) throws URISyntaxException {
        log.debug("REST request to save RecruitmentCityMaster : {}", recruitmentCityMaster);
        if (recruitmentCityMaster.getId() != null) {
            throw new BadRequestAlertException("A new recruitmentCityMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        recruitmentCityMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentCityMaster.setCreatedDate(Instant.now());
        RecruitmentCityMaster result = recruitmentCityMasterRepository.save(recruitmentCityMaster);
        return ResponseEntity.created(new URI("/api/recruitment-city-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recruitment-city-masters : Updates an existing recruitmentCityMaster.
     *
     * @param recruitmentCityMaster the recruitmentCityMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recruitmentCityMaster,
     * or with status 400 (Bad Request) if the recruitmentCityMaster is not valid,
     * or with status 500 (Internal Server Error) if the recruitmentCityMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recruitment-city-masters")
    public ResponseEntity<RecruitmentCityMaster> updateRecruitmentCityMaster(@Valid @RequestBody RecruitmentCityMaster recruitmentCityMaster) throws URISyntaxException {
        log.debug("REST request to update RecruitmentCityMaster : {}", recruitmentCityMaster);
        if (recruitmentCityMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        recruitmentCityMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentCityMaster.setLastUpdatedDate(Instant.now());
        RecruitmentCityMaster result = recruitmentCityMasterRepository.save(recruitmentCityMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recruitmentCityMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recruitment-city-masters : get all the recruitmentCityMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recruitmentCityMasters in body
     */
    @GetMapping("/recruitment-city-masters")
    public ResponseEntity<List<RecruitmentCityMaster>> getAllRecruitmentCityMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of RecruitmentCityMasters");
        Page<RecruitmentCityMaster> page = recruitmentCityMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recruitment-city-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /recruitment-city-masters/:id : get the "id" recruitmentCityMaster.
     *
     * @param id the id of the recruitmentCityMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentCityMaster, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-city-masters/{id}")
    public ResponseEntity<RecruitmentCityMaster> getRecruitmentCityMaster(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentCityMaster : {}", id);
        Optional<RecruitmentCityMaster> recruitmentCityMaster = recruitmentCityMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(recruitmentCityMaster);
    }

    /**
     * GET  /recruitment-city-masters/:id : get the "id" recruitmentCityMaster.
     *
     * @param id the id of the recruitmentCityMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentCityMaster, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-city-masters-district/{id}")
    public ResponseEntity<List<RecruitmentCityMaster>> getRecruitmentCityMasterByDistrict(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentCityMaster : {}", id);
        List<RecruitmentCityMaster> recruitmentCityMaster = recruitmentCityMasterRepository.findAllByDistrictId(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(recruitmentCityMaster));
    }

    /**
     * DELETE  /recruitment-city-masters/:id : delete the "id" recruitmentCityMaster.
     *
     * @param id the id of the recruitmentCityMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recruitment-city-masters/{id}")
    public ResponseEntity<Void> deleteRecruitmentCityMaster(@PathVariable Long id) {
        log.debug("REST request to delete RecruitmentCityMaster : {}", id);
        recruitmentCityMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
