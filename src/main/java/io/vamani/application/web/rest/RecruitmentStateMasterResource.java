package io.vamani.application.web.rest;
import io.vamani.application.domain.RecruitmentStateMaster;
import io.vamani.application.repository.RecruitmentStateMasterRepository;
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
 * REST controller for managing RecruitmentStateMaster.
 */
@RestController
@RequestMapping("/api")
public class RecruitmentStateMasterResource {

    private final Logger log = LoggerFactory.getLogger(RecruitmentStateMasterResource.class);

    private static final String ENTITY_NAME = "recruitmentStateMaster";

    private final RecruitmentStateMasterRepository recruitmentStateMasterRepository;

    public RecruitmentStateMasterResource(RecruitmentStateMasterRepository recruitmentStateMasterRepository) {
        this.recruitmentStateMasterRepository = recruitmentStateMasterRepository;
    }

    /**
     * POST  /recruitment-state-masters : Create a new recruitmentStateMaster.
     *
     * @param recruitmentStateMaster the recruitmentStateMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recruitmentStateMaster, or with status 400 (Bad Request) if the recruitmentStateMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recruitment-state-masters")
    public ResponseEntity<RecruitmentStateMaster> createRecruitmentStateMaster(@Valid @RequestBody RecruitmentStateMaster recruitmentStateMaster) throws URISyntaxException {
        log.debug("REST request to save RecruitmentStateMaster : {}", recruitmentStateMaster);
        if (recruitmentStateMaster.getId() != null) {
            throw new BadRequestAlertException("A new recruitmentStateMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        recruitmentStateMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentStateMaster.setCreatedDate(Instant.now());
        RecruitmentStateMaster result = recruitmentStateMasterRepository.save(recruitmentStateMaster);
        return ResponseEntity.created(new URI("/api/recruitment-state-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recruitment-state-masters : Updates an existing recruitmentStateMaster.
     *
     * @param recruitmentStateMaster the recruitmentStateMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recruitmentStateMaster,
     * or with status 400 (Bad Request) if the recruitmentStateMaster is not valid,
     * or with status 500 (Internal Server Error) if the recruitmentStateMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recruitment-state-masters")
    public ResponseEntity<RecruitmentStateMaster> updateRecruitmentStateMaster(@Valid @RequestBody RecruitmentStateMaster recruitmentStateMaster) throws URISyntaxException {
        log.debug("REST request to update RecruitmentStateMaster : {}", recruitmentStateMaster);
        if (recruitmentStateMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        recruitmentStateMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        recruitmentStateMaster.setLastUpdatedDate(Instant.now());
        RecruitmentStateMaster result = recruitmentStateMasterRepository.save(recruitmentStateMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recruitmentStateMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recruitment-state-masters : get all the recruitmentStateMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recruitmentStateMasters in body
     */
    @GetMapping("/recruitment-state-masters")
    public ResponseEntity<List<RecruitmentStateMaster>> getAllRecruitmentStateMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of RecruitmentStateMasters");
        Page<RecruitmentStateMaster> page = recruitmentStateMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recruitment-state-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /recruitment-state-masters/:id : get the "id" recruitmentStateMaster.
     *
     * @param id the id of the recruitmentStateMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentStateMaster, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-state-masters/{id}")
    public ResponseEntity<RecruitmentStateMaster> getRecruitmentStateMaster(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentStateMaster : {}", id);
        Optional<RecruitmentStateMaster> recruitmentStateMaster = recruitmentStateMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(recruitmentStateMaster);
    }

    /**
     * GET  /recruitment-state-masters/:id : get the "id" recruitmentStateMaster.
     *
     * @param id the id of the recruitmentStateMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recruitmentStateMaster, or with status 404 (Not Found)
     */
    @GetMapping("/recruitment-state-masters-country/{id}")
    public ResponseEntity<List<RecruitmentStateMaster>> getRecruitmentStateMasterByCountry(@PathVariable Long id) {
        log.debug("REST request to get RecruitmentStateMaster : {}", id);
        List<RecruitmentStateMaster> recruitmentStateMaster = recruitmentStateMasterRepository.findAllByCountryId(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(recruitmentStateMaster));
    }

    /**
     * DELETE  /recruitment-state-masters/:id : delete the "id" recruitmentStateMaster.
     *
     * @param id the id of the recruitmentStateMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recruitment-state-masters/{id}")
    public ResponseEntity<Void> deleteRecruitmentStateMaster(@PathVariable Long id) {
        log.debug("REST request to delete RecruitmentStateMaster : {}", id);
        recruitmentStateMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
