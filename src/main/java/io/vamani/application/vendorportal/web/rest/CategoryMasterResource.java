package io.vamani.application.vendorportal.web.rest;
import io.vamani.application.vendorportal.domain.CategoryMaster;
import io.vamani.application.vendorportal.repository.CategoryMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing CategoryMaster.
 */
@RestController
@RequestMapping("/api")
public class CategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(CategoryMasterResource.class);

    private static final String ENTITY_NAME = "categoryMaster";

    private final CategoryMasterRepository categoryMasterRepository;

    public CategoryMasterResource(CategoryMasterRepository categoryMasterRepository) {
        this.categoryMasterRepository = categoryMasterRepository;
    }

    /**
     * POST  /category-masters : Create a new categoryMaster.
     *
     * @param categoryMaster the categoryMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryMaster, or with status 400 (Bad Request) if the categoryMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/category-masters")
    public ResponseEntity<CategoryMaster> createCategoryMaster(@Valid @RequestBody CategoryMaster categoryMaster) throws URISyntaxException {
        log.debug("REST request to save CategoryMaster : {}", categoryMaster);
        if (categoryMaster.getId() != null) {
            throw new BadRequestAlertException("A new categoryMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        categoryMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        categoryMaster.setCreatedDate(Instant.now());
        CategoryMaster result = categoryMasterRepository.save(categoryMaster);
        return ResponseEntity.created(new URI("/api/category-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /category-masters : Updates an existing categoryMaster.
     *
     * @param categoryMaster the categoryMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryMaster,
     * or with status 400 (Bad Request) if the categoryMaster is not valid,
     * or with status 500 (Internal Server Error) if the categoryMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/category-masters")
    public ResponseEntity<CategoryMaster> updateCategoryMaster(@Valid @RequestBody CategoryMaster categoryMaster) throws URISyntaxException {
        log.debug("REST request to update CategoryMaster : {}", categoryMaster);
        if (categoryMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        categoryMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        categoryMaster.setLastUpdatedDate(Instant.now());
        CategoryMaster result = categoryMasterRepository.save(categoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /category-masters : get all the categoryMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of categoryMasters in body
     */
    @GetMapping("/category-masters")
    public ResponseEntity<List<CategoryMaster>> getAllCategoryMasters(Pageable pageable) {
        log.debug("REST request to get a page of CategoryMasters");
        Page<CategoryMaster> page = categoryMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/category-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /category-masters/:id : get the "id" categoryMaster.
     *
     * @param id the id of the categoryMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoryMaster, or with status 404 (Not Found)
     */
    @GetMapping("/category-masters/{id}")
    public ResponseEntity<CategoryMaster> getCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get CategoryMaster : {}", id);
        Optional<CategoryMaster> categoryMaster = categoryMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(categoryMaster);
    }

    /**
     * DELETE  /category-masters/:id : delete the "id" categoryMaster.
     *
     * @param id the id of the categoryMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/category-masters/{id}")
    public ResponseEntity<Void> deleteCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete CategoryMaster : {}", id);
        categoryMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
