package io.vamani.application.web.rest;

import io.vamani.application.domain.KnitProcessMaster;
import io.vamani.application.repository.KnitProcessMasterRepository;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing {@link KnitProcessMaster}.
 */
@RestController
@RequestMapping("/api")
public class KnitProcessMasterResource {

    private final Logger log = LoggerFactory.getLogger(KnitProcessMasterResource.class);

    private static final String ENTITY_NAME = "knitProcessMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KnitProcessMasterRepository knitProcessMasterRepository;

    public KnitProcessMasterResource(KnitProcessMasterRepository knitProcessMasterRepository) {
        this.knitProcessMasterRepository = knitProcessMasterRepository;
    }

    /**
     * {@code POST  /knit-process-masters} : Create a new knitProcessMaster.
     *
     * @param knitProcessMaster the knitProcessMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new knitProcessMaster, or with status {@code 400 (Bad Request)} if the knitProcessMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/knit-process-masters")
    public ResponseEntity<KnitProcessMaster> createKnitProcessMaster(@Valid @RequestBody KnitProcessMaster knitProcessMaster) throws URISyntaxException {
        log.debug("REST request to save KnitProcessMaster : {}", knitProcessMaster);
        if (knitProcessMaster.getId() != null) {
            throw new BadRequestAlertException("A new knitProcessMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        knitProcessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        knitProcessMaster.setCreatedDate(Instant.now());
        KnitProcessMaster result = knitProcessMasterRepository.save(knitProcessMaster);
        return ResponseEntity.created(new URI("/api/knit-process-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /knit-process-masters} : Updates an existing knitProcessMaster.
     *
     * @param knitProcessMaster the knitProcessMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated knitProcessMaster,
     * or with status {@code 400 (Bad Request)} if the knitProcessMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the knitProcessMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/knit-process-masters")
    public ResponseEntity<KnitProcessMaster> updateKnitProcessMaster(@Valid @RequestBody KnitProcessMaster knitProcessMaster) throws URISyntaxException {
        log.debug("REST request to update KnitProcessMaster : {}", knitProcessMaster);
        if (knitProcessMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        knitProcessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        knitProcessMaster.setCreatedDate(Instant.now());
        KnitProcessMaster result = knitProcessMasterRepository.save(knitProcessMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, knitProcessMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /knit-process-masters} : get all the knitProcessMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of knitProcessMasters in body.
     */
    @GetMapping("/knit-process-masters")
    public ResponseEntity<List<KnitProcessMaster>> getAllKnitProcessMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of KnitProcessMasters");
        Page<KnitProcessMaster> page = knitProcessMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /knit-process-masters/:id} : get the "id" knitProcessMaster.
     *
     * @param id the id of the knitProcessMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the knitProcessMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/knit-process-masters/{id}")
    public ResponseEntity<KnitProcessMaster> getKnitProcessMaster(@PathVariable Long id) {
        log.debug("REST request to get KnitProcessMaster : {}", id);
        Optional<KnitProcessMaster> knitProcessMaster = knitProcessMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(knitProcessMaster);
    }

    /**
     * {@code DELETE  /knit-process-masters/:id} : delete the "id" knitProcessMaster.
     *
     * @param id the id of the knitProcessMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/knit-process-masters/{id}")
    public ResponseEntity<Void> deleteKnitProcessMaster(@PathVariable Long id) {
        log.debug("REST request to delete KnitProcessMaster : {}", id);
        knitProcessMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
