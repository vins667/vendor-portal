package io.vamani.application.web.rest;

import io.vamani.application.domain.KnitTypeMaster;
import io.vamani.application.repository.KnitTypeMasterRepository;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link KnitTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class KnitTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(KnitTypeMasterResource.class);

    private static final String ENTITY_NAME = "knitTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KnitTypeMasterRepository knitTypeMasterRepository;

    public KnitTypeMasterResource(KnitTypeMasterRepository knitTypeMasterRepository) {
        this.knitTypeMasterRepository = knitTypeMasterRepository;
    }

    /**
     * {@code POST  /knit-type-masters} : Create a new knitTypeMaster.
     *
     * @param knitTypeMaster the knitTypeMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new knitTypeMaster, or with status {@code 400 (Bad Request)} if the knitTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/knit-type-masters")
    public ResponseEntity<KnitTypeMaster> createKnitTypeMaster(@Valid @RequestBody KnitTypeMaster knitTypeMaster) throws URISyntaxException {
        log.debug("REST request to save KnitTypeMaster : {}", knitTypeMaster);
        if (knitTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new knitTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        knitTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        knitTypeMaster.setCreatedDate(Instant.now());
        KnitTypeMaster result = knitTypeMasterRepository.save(knitTypeMaster);
        return ResponseEntity.created(new URI("/api/knit-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /knit-type-masters} : Updates an existing knitTypeMaster.
     *
     * @param knitTypeMaster the knitTypeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated knitTypeMaster,
     * or with status {@code 400 (Bad Request)} if the knitTypeMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the knitTypeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/knit-type-masters")
    public ResponseEntity<KnitTypeMaster> updateKnitTypeMaster(@Valid @RequestBody KnitTypeMaster knitTypeMaster) throws URISyntaxException {
        log.debug("REST request to update KnitTypeMaster : {}", knitTypeMaster);
        if (knitTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        knitTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        knitTypeMaster.setCreatedDate(Instant.now());
        KnitTypeMaster result = knitTypeMasterRepository.save(knitTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, knitTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /knit-type-masters} : get all the knitTypeMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of knitTypeMasters in body.
     */
    @GetMapping("/knit-type-masters")
    public ResponseEntity<List<KnitTypeMaster>> getAllKnitTypeMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of KnitTypeMasters");
        Page<KnitTypeMaster> page = knitTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /knit-type-masters/:id} : get the "id" knitTypeMaster.
     *
     * @param id the id of the knitTypeMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the knitTypeMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/knit-type-masters/{id}")
    public ResponseEntity<KnitTypeMaster> getKnitTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get KnitTypeMaster : {}", id);
        Optional<KnitTypeMaster> knitTypeMaster = knitTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(knitTypeMaster);
    }

    /**
     * {@code DELETE  /knit-type-masters/:id} : delete the "id" knitTypeMaster.
     *
     * @param id the id of the knitTypeMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/knit-type-masters/{id}")
    public ResponseEntity<Void> deleteKnitTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete KnitTypeMaster : {}", id);
        knitTypeMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
