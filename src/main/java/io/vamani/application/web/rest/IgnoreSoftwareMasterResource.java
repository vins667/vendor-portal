package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.IgnoreSoftwareMaster;
import io.vamani.application.model.IgnoreSoftwareSearchMaster;
import io.vamani.application.repository.IgnoreSoftwareMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * REST controller for managing IgnoreSoftwareMaster.
 */
@RestController
@RequestMapping("/api")
public class IgnoreSoftwareMasterResource {

    private final Logger log = LoggerFactory.getLogger(IgnoreSoftwareMasterResource.class);

    private static final String ENTITY_NAME = "ignoreSoftwareMaster";

    private final IgnoreSoftwareMasterRepository ignoreSoftwareMasterRepository;

    public IgnoreSoftwareMasterResource(IgnoreSoftwareMasterRepository ignoreSoftwareMasterRepository) {
        this.ignoreSoftwareMasterRepository = ignoreSoftwareMasterRepository;
    }

    /**
     * POST  /ignore-software-masters : Create a new ignoreSoftwareMaster.
     *
     * @param ignoreSoftwareMaster the ignoreSoftwareMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ignoreSoftwareMaster, or with status 400 (Bad Request) if the ignoreSoftwareMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ignore-software-masters")
    @Timed
    public ResponseEntity<IgnoreSoftwareMaster> createIgnoreSoftwareMaster(@Valid @RequestBody IgnoreSoftwareMaster ignoreSoftwareMaster) throws URISyntaxException {
        log.debug("REST request to save IgnoreSoftwareMaster : {}", ignoreSoftwareMaster);
        if (ignoreSoftwareMaster.getId() != null) {
            throw new BadRequestAlertException("A new ignoreSoftwareMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ignoreSoftwareMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        ignoreSoftwareMaster.setCreatedDate(Instant.now());
        IgnoreSoftwareMaster result = ignoreSoftwareMasterRepository.save(ignoreSoftwareMaster);
        return ResponseEntity.created(new URI("/api/ignore-software-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ignore-software-masters : Updates an existing ignoreSoftwareMaster.
     *
     * @param ignoreSoftwareMaster the ignoreSoftwareMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ignoreSoftwareMaster,
     * or with status 400 (Bad Request) if the ignoreSoftwareMaster is not valid,
     * or with status 500 (Internal Server Error) if the ignoreSoftwareMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ignore-software-masters")
    @Timed
    public ResponseEntity<IgnoreSoftwareMaster> updateIgnoreSoftwareMaster(@Valid @RequestBody IgnoreSoftwareMaster ignoreSoftwareMaster) throws URISyntaxException {
        log.debug("REST request to update IgnoreSoftwareMaster : {}", ignoreSoftwareMaster);
        if (ignoreSoftwareMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ignoreSoftwareMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        ignoreSoftwareMaster.setLastUpdatedDate(Instant.now());
        IgnoreSoftwareMaster result = ignoreSoftwareMasterRepository.save(ignoreSoftwareMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ignoreSoftwareMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ignore-software-masters : get all the ignoreSoftwareMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ignoreSoftwareMasters in body
     */
    @GetMapping("/ignore-software-masters")
    @Timed
    public ResponseEntity<List<IgnoreSoftwareMaster>> getAllIgnoreSoftwareMasters(Pageable pageable) {
        log.debug("REST request to get a page of IgnoreSoftwareMasters");
        Page<IgnoreSoftwareMaster> page = ignoreSoftwareMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ignore-software-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ignore-software-masters/:id : get the "id" ignoreSoftwareMaster.
     *
     * @param id the id of the ignoreSoftwareMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ignoreSoftwareMaster, or with status 404 (Not Found)
     */
    @GetMapping("/ignore-software-masters/{id}")
    @Timed
    public ResponseEntity<IgnoreSoftwareMaster> getIgnoreSoftwareMaster(@PathVariable Long id) {
        log.debug("REST request to get IgnoreSoftwareMaster : {}", id);
        Optional<IgnoreSoftwareMaster> ignoreSoftwareMaster = ignoreSoftwareMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ignoreSoftwareMaster);
    }

    /**
     * DELETE  /ignore-software-masters/:id : delete the "id" ignoreSoftwareMaster.
     *
     * @param id the id of the ignoreSoftwareMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ignore-software-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteIgnoreSoftwareMaster(@PathVariable Long id) {
        log.debug("REST request to delete IgnoreSoftwareMaster : {}", id);

        ignoreSoftwareMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    
    @PostMapping("/ignore-software-masters-custom")
    @Timed
    public ResponseEntity<List<IgnoreSoftwareMaster>>getAllIgnoreSoftwareMasterCustom(@Valid @RequestBody IgnoreSoftwareSearchMaster search) {
        log.debug("REST request to get a page of IgnoreSoftwareMaster");
        String swName = "%";
        String swPublisher = "%";
        if (search.getSwName() != null) {
        	swName = "%" + search.getSwName().toUpperCase() + "%";
        }
        if (search.getSwPublisher() != null) {
        	swPublisher = "%" + search.getSwPublisher() + "%";
        }
        
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        Page<IgnoreSoftwareMaster> page = ignoreSoftwareMasterRepository.findAllByFilter(swName,swPublisher,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ignore-software-masters-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
