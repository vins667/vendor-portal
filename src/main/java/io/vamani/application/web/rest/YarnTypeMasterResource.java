package io.vamani.application.web.rest;

import io.vamani.application.domain.YarnTypeMaster;
import io.vamani.application.repository.YarnTypeMasterRepository;
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
 * REST controller for managing {@link YarnTypeMaster}.
 */
@RestController
@RequestMapping("/api")
public class YarnTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(YarnTypeMasterResource.class);

    private static final String ENTITY_NAME = "yarnTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final YarnTypeMasterRepository yarnTypeMasterRepository;

    public YarnTypeMasterResource(YarnTypeMasterRepository yarnTypeMasterRepository) {
        this.yarnTypeMasterRepository = yarnTypeMasterRepository;
    }

    /**
     * {@code POST  /yarn-type-masters} : Create a new yarnTypeMaster.
     *
     * @param yarnTypeMaster the yarnTypeMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new yarnTypeMaster, or with status {@code 400 (Bad Request)} if the yarnTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/yarn-type-masters")
    public ResponseEntity<YarnTypeMaster> createYarnTypeMaster(@Valid @RequestBody YarnTypeMaster yarnTypeMaster) throws URISyntaxException {
        log.debug("REST request to save YarnTypeMaster : {}", yarnTypeMaster);
        if (yarnTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new yarnTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        yarnTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        yarnTypeMaster.setCreatedDate(Instant.now());
        YarnTypeMaster result = yarnTypeMasterRepository.save(yarnTypeMaster);
        return ResponseEntity.created(new URI("/api/yarn-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /yarn-type-masters} : Updates an existing yarnTypeMaster.
     *
     * @param yarnTypeMaster the yarnTypeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated yarnTypeMaster,
     * or with status {@code 400 (Bad Request)} if the yarnTypeMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the yarnTypeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/yarn-type-masters")
    public ResponseEntity<YarnTypeMaster> updateYarnTypeMaster(@Valid @RequestBody YarnTypeMaster yarnTypeMaster) throws URISyntaxException {
        log.debug("REST request to update YarnTypeMaster : {}", yarnTypeMaster);
        if (yarnTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        yarnTypeMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        yarnTypeMaster.setCreatedDate(Instant.now());
        YarnTypeMaster result = yarnTypeMasterRepository.save(yarnTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, yarnTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /yarn-type-masters} : get all the yarnTypeMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of yarnTypeMasters in body.
     */
    @GetMapping("/yarn-type-masters")
    public ResponseEntity<List<YarnTypeMaster>> getAllYarnTypeMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of YarnTypeMasters");
        Page<YarnTypeMaster> page = yarnTypeMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /yarn-type-masters/:id} : get the "id" yarnTypeMaster.
     *
     * @param id the id of the yarnTypeMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the yarnTypeMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/yarn-type-masters/{id}")
    public ResponseEntity<YarnTypeMaster> getYarnTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get YarnTypeMaster : {}", id);
        Optional<YarnTypeMaster> yarnTypeMaster = yarnTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(yarnTypeMaster);
    }

    /**
     * {@code DELETE  /yarn-type-masters/:id} : delete the "id" yarnTypeMaster.
     *
     * @param id the id of the yarnTypeMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/yarn-type-masters/{id}")
    public ResponseEntity<Void> deleteYarnTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete YarnTypeMaster : {}", id);
        yarnTypeMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
