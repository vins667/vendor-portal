package io.vamani.application.web.rest;
import io.vamani.application.domain.ReportTypeMaster;
import io.vamani.application.repository.ReportTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.vamani.application.domain.ReportTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class ReportTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ReportTypeMasterResource.class);

    private static final String ENTITY_NAME = "reportTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportTypeMasterRepository reportTypeMasterRepository;

    public ReportTypeMasterResource(ReportTypeMasterRepository reportTypeMasterRepository) {
        this.reportTypeMasterRepository = reportTypeMasterRepository;
    }

    /**
     * {@code POST  /report-type-masters} : Create a new reportTypeMaster.
     *
     * @param reportTypeMaster the reportTypeMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportTypeMaster, or with status {@code 400 (Bad Request)} if the reportTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/report-type-masters")
    public ResponseEntity<ReportTypeMaster> createReportTypeMaster(@Valid @RequestBody ReportTypeMaster reportTypeMaster) throws URISyntaxException {
        log.debug("REST request to save ReportTypeMaster : {}", reportTypeMaster);
        if (reportTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new reportTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        reportTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        reportTypeMaster.setCreatedDate(Instant.now());
        ReportTypeMaster result = reportTypeMasterRepository.save(reportTypeMaster);
        return ResponseEntity.created(new URI("/api/report-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-type-masters} : Updates an existing reportTypeMaster.
     *
     * @param reportTypeMaster the reportTypeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportTypeMaster,
     * or with status {@code 400 (Bad Request)} if the reportTypeMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportTypeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/report-type-masters")
    public ResponseEntity<ReportTypeMaster> updateReportTypeMaster(@Valid @RequestBody ReportTypeMaster reportTypeMaster) throws URISyntaxException {
        log.debug("REST request to update ReportTypeMaster : {}", reportTypeMaster);
        if (reportTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportTypeMaster result = reportTypeMasterRepository.save(reportTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reportTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /report-type-masters} : get all the reportTypeMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportTypeMasters in body.
     */
    @GetMapping("/report-type-masters")
    public ResponseEntity<List<ReportTypeMaster>> getAllReportTypeMasters(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of ReportTypeMasters");
        Page<ReportTypeMaster> page = reportTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-type-masters/:id} : get the "id" reportTypeMaster.
     *
     * @param id the id of the reportTypeMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportTypeMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/report-type-masters/{id}")
    public ResponseEntity<ReportTypeMaster> getReportTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get ReportTypeMaster : {}", id);
        Optional<ReportTypeMaster> reportTypeMaster = reportTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(reportTypeMaster);
    }

    /**
     * {@code DELETE  /report-type-masters/:id} : delete the "id" reportTypeMaster.
     *
     * @param id the id of the reportTypeMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/report-type-masters/{id}")
    public ResponseEntity<Void> deleteReportTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ReportTypeMaster : {}", id);
        reportTypeMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
