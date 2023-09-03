package io.vamani.application.web.rest;

import io.vamani.application.domain.YarnCountMaster;
import io.vamani.application.repository.YarnCountMasterRepository;
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
 * REST controller for managing {@link YarnCountMaster}.
 */
@RestController
@RequestMapping("/api")
public class YarnCountMasterResource {

    private final Logger log = LoggerFactory.getLogger(YarnCountMasterResource.class);

    private static final String ENTITY_NAME = "yarnCountMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final YarnCountMasterRepository yarnCountMasterRepository;

    public YarnCountMasterResource(YarnCountMasterRepository yarnCountMasterRepository) {
        this.yarnCountMasterRepository = yarnCountMasterRepository;
    }

    /**
     * {@code POST  /yarn-count-masters} : Create a new yarnCountMaster.
     *
     * @param yarnCountMaster the yarnCountMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new yarnCountMaster, or with status {@code 400 (Bad Request)} if the yarnCountMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/yarn-count-masters")
    public ResponseEntity<YarnCountMaster> createYarnCountMaster(@Valid @RequestBody YarnCountMaster yarnCountMaster) throws URISyntaxException {
        log.debug("REST request to save YarnCountMaster : {}", yarnCountMaster);
        if (yarnCountMaster.getId() != null) {
            throw new BadRequestAlertException("A new yarnCountMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        yarnCountMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        yarnCountMaster.setCreatedDate(Instant.now());
        YarnCountMaster result = yarnCountMasterRepository.save(yarnCountMaster);
        return ResponseEntity.created(new URI("/api/yarn-count-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /yarn-count-masters} : Updates an existing yarnCountMaster.
     *
     * @param yarnCountMaster the yarnCountMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated yarnCountMaster,
     * or with status {@code 400 (Bad Request)} if the yarnCountMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the yarnCountMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/yarn-count-masters")
    public ResponseEntity<YarnCountMaster> updateYarnCountMaster(@Valid @RequestBody YarnCountMaster yarnCountMaster) throws URISyntaxException {
        log.debug("REST request to update YarnCountMaster : {}", yarnCountMaster);
        if (yarnCountMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        yarnCountMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        yarnCountMaster.setCreatedDate(Instant.now());
        YarnCountMaster result = yarnCountMasterRepository.save(yarnCountMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, yarnCountMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /yarn-count-masters} : get all the yarnCountMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of yarnCountMasters in body.
     */
    @GetMapping("/yarn-count-masters")
    public ResponseEntity<List<YarnCountMaster>> getAllYarnCountMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of YarnCountMasters");
        Page<YarnCountMaster> page = yarnCountMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /yarn-count-masters/:id} : get the "id" yarnCountMaster.
     *
     * @param id the id of the yarnCountMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the yarnCountMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/yarn-count-masters/{id}")
    public ResponseEntity<YarnCountMaster> getYarnCountMaster(@PathVariable Long id) {
        log.debug("REST request to get YarnCountMaster : {}", id);
        Optional<YarnCountMaster> yarnCountMaster = yarnCountMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(yarnCountMaster);
    }

    /**
     * {@code DELETE  /yarn-count-masters/:id} : delete the "id" yarnCountMaster.
     *
     * @param id the id of the yarnCountMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/yarn-count-masters/{id}")
    public ResponseEntity<Void> deleteYarnCountMaster(@PathVariable Long id) {
        log.debug("REST request to delete YarnCountMaster : {}", id);
        yarnCountMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
