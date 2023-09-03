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
import io.vamani.application.domain.LanguageMaster;
import io.vamani.application.repository.LanguageMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;

/**
 * REST controller for managing LanguageMaster.
 */
@RestController
@RequestMapping("/api")
public class LanguageMasterResource {

    private final Logger log = LoggerFactory.getLogger(LanguageMasterResource.class);

    private static final String ENTITY_NAME = "languageMaster";

    private final LanguageMasterRepository languageMasterRepository;

    public LanguageMasterResource(LanguageMasterRepository languageMasterRepository) {
        this.languageMasterRepository = languageMasterRepository;
    }

    /**
     * POST  /language-masters : Create a new languageMaster.
     *
     * @param languageMaster the languageMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new languageMaster, or with status 400 (Bad Request) if the languageMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/language-masters")
    public ResponseEntity<LanguageMaster> createLanguageMaster(@Valid @RequestBody LanguageMaster languageMaster) throws URISyntaxException {
        log.debug("REST request to save LanguageMaster : {}", languageMaster);
        if (languageMaster.getId() != null) {
            throw new BadRequestAlertException("A new languageMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        languageMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        languageMaster.setCreatedDate(Instant.now());;
        LanguageMaster result = languageMasterRepository.save(languageMaster);
        return ResponseEntity.created(new URI("/api/language-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /language-masters : Updates an existing languageMaster.
     *
     * @param languageMaster the languageMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated languageMaster,
     * or with status 400 (Bad Request) if the languageMaster is not valid,
     * or with status 500 (Internal Server Error) if the languageMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/language-masters")
    public ResponseEntity<LanguageMaster> updateLanguageMaster(@Valid @RequestBody LanguageMaster languageMaster) throws URISyntaxException {
        log.debug("REST request to update LanguageMaster : {}", languageMaster);
        if (languageMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        languageMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        languageMaster.setLastUpdatedDate(Instant.now());
        LanguageMaster result = languageMasterRepository.save(languageMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, languageMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /language-masters : get all the languageMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of languageMasters in body
     */
    @GetMapping("/language-masters")
    public ResponseEntity<List<LanguageMaster>> getAllLanguageMasters(@PageableDefault(value = Integer.MAX_VALUE)  @SortDefault(sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        log.debug("REST request to get a page of LanguageMasters");
        Page<LanguageMaster> page = languageMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/language-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /language-masters/:id : get the "id" languageMaster.
     *
     * @param id the id of the languageMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the languageMaster, or with status 404 (Not Found)
     */
    @GetMapping("/language-masters/{id}")
    public ResponseEntity<LanguageMaster> getLanguageMaster(@PathVariable Long id) {
        log.debug("REST request to get LanguageMaster : {}", id);
        Optional<LanguageMaster> languageMaster = languageMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(languageMaster);
    }

    /**
     * DELETE  /language-masters/:id : delete the "id" languageMaster.
     *
     * @param id the id of the languageMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/language-masters/{id}")
    public ResponseEntity<Void> deleteLanguageMaster(@PathVariable Long id) {
        log.debug("REST request to delete LanguageMaster : {}", id);
        languageMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
