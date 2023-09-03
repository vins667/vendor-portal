package io.vamani.application.web.rest;
import io.vamani.application.domain.RecruitmentDistrict;
import io.vamani.application.repository.RecruitmentDistrictRepository;
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
 * REST controller for managing RecruitmentDistrict.
 */
@RestController
@RequestMapping("/api")
public class RecruitmentDistrictResource {

    private final Logger log = LoggerFactory.getLogger(RecruitmentDistrictResource.class);

    private static final String ENTITY_NAME = "recruitmentDistrict";

    private final RecruitmentDistrictRepository recruitmentDistrictRepository;

    public RecruitmentDistrictResource(RecruitmentDistrictRepository recruitmentDistrictRepository) {
        this.recruitmentDistrictRepository = recruitmentDistrictRepository;
    }

    /**
     * POST  /recruitment-districts : Create a new recruitmentDistrict.
     *
     * @param recruitmentDistrict the recruitmentDistrict to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recruitmentDistrict, or with status 400 (Bad Request) if the recruitmentDistrict has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recruitment-districts")
    public ResponseEntity<RecruitmentDistrict> createRecruitmentDistrict(@Valid @RequestBody RecruitmentDistrict recruitmentDistrict) throws URISyntaxException {
        log.debug("REST request to save RecruitmentDistrict : {}", recruitmentDistrict);
        if (recruitmentDistrict.getId() != null) {
            throw new BadRequestAlertException("A new recruitmentDistrict cannot already have an ID", ENTITY_NAME, "idexists");
        }
        recruitmentDistrict.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentDistrict.setCreatedDate(Instant.now());
        RecruitmentDistrict result = recruitmentDistrictRepository.save(recruitmentDistrict);
        return ResponseEntity.created(new URI("/api/recruitment-districts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recruitment-districts : Updates an existing recruitmentDistrict.
     *
     * @param recruitmentDistrict the recruitmentDistrict to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recruitmentDistrict,
     * or with status 400 (Bad Request) if the recruitmentDistrict is not valid,
     * or with status 500 (Internal Server Error) if the recruitmentDistrict couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recruitment-districts")
    public ResponseEntity<RecruitmentDistrict> updateRecruitmentDistrict(@Valid @RequestBody RecruitmentDistrict recruitmentDistrict) throws URISyntaxException {
        log.debug("REST request to update RecruitmentDistrict : {}", recruitmentDistrict);
        if (recruitmentDistrict.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        recruitmentDistrict.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentDistrict.setLastUpdatedDate(Instant.now());
        RecruitmentDistrict result = recruitmentDistrictRepository.save(recruitmentDistrict);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recruitmentDistrict.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recruitment-districts : get all the recruitmentDistricts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recruitmentDistricts in body
     */
    @GetMapping("/recruitment-districts")
    public ResponseEntity<List<RecruitmentDistrict>> getAllRecruitmentDistricts(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of RecruitmentDistricts");
        Page<RecruitmentDistrict> page = recruitmentDistrictRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recruitment-districts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /recruitment-districts/:id : get the "id" recruitmentDistrict.
     *
     * @param id the id of the recruitmentDistrict to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentDistrict, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-districts/{id}")
    public ResponseEntity<RecruitmentDistrict> getRecruitmentDistrict(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentDistrict : {}", id);
        Optional<RecruitmentDistrict> recruitmentDistrict = recruitmentDistrictRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(recruitmentDistrict);
    }

    /**
     * GET  /recruitment-districts/:id : get the "id" recruitmentDistrict.
     *
     * @param id the id of the recruitmentDistrict to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentDistrict, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-districts-state/{id}")
    public ResponseEntity<List<RecruitmentDistrict>> getRecruitmentDistrictByState(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentDistrict : {}", id);
        List<RecruitmentDistrict> recruitmentDistrict = recruitmentDistrictRepository.findAllByStateId(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(recruitmentDistrict));
    }

    /**
     * DELETE  /recruitment-districts/:id : delete the "id" recruitmentDistrict.
     *
     * @param id the id of the recruitmentDistrict to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recruitment-districts/{id}")
    public ResponseEntity<Void> deleteRecruitmentDistrict(@PathVariable Long id) {
        log.debug("REST request to delete RecruitmentDistrict : {}", id);
        recruitmentDistrictRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
