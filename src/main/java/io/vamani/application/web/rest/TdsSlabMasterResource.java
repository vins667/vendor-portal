package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.TdsSlabMaster;
import io.vamani.application.model.TdsSlabSearchMaster;
import io.vamani.application.repository.TdsSlabMasterRepository;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TdsSlabMaster.
 */
@RestController
@RequestMapping("/api")
public class TdsSlabMasterResource {

    private final Logger log = LoggerFactory.getLogger(TdsSlabMasterResource.class);

    private static final String ENTITY_NAME = "tdsSlabMaster";

    private final TdsSlabMasterRepository tdsSlabMasterRepository;

    public TdsSlabMasterResource(TdsSlabMasterRepository tdsSlabMasterRepository) {
        this.tdsSlabMasterRepository = tdsSlabMasterRepository;
    }

    /**
     * POST  /tds-slab-masters : Create a new tdsSlabMaster.
     *
     * @param tdsSlabMaster the tdsSlabMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tdsSlabMaster, or with status 400 (Bad Request) if the tdsSlabMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tds-slab-masters")
    @Timed
    public ResponseEntity<TdsSlabMaster> createTdsSlabMaster(@Valid @RequestBody TdsSlabMaster[] tdsSlabMaster) throws URISyntaxException {
        log.debug("REST request to save TdsSlabMaster : {}");
        TdsSlabMaster result=null;
        for(TdsSlabMaster tdsSlabMasterBean:tdsSlabMaster) {
    		tdsSlabMasterBean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        	tdsSlabMasterBean.setCreatedDate(Instant.now());
            result = tdsSlabMasterRepository.save(tdsSlabMasterBean);
        }
        return ResponseEntity.created(new URI("/api/tds-slab-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tds-slab-masters : Updates an existing tdsSlabMaster.
     *
     * @param tdsSlabMaster the tdsSlabMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tdsSlabMaster,
     * or with status 400 (Bad Request) if the tdsSlabMaster is not valid,
     * or with status 500 (Internal Server Error) if the tdsSlabMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tds-slab-masters")
    @Timed
    public ResponseEntity<TdsSlabMaster> updateTdsSlabMaster(@Valid @RequestBody TdsSlabMaster tdsSlabMaster) throws URISyntaxException {
        log.debug("REST request to update TdsSlabMaster : {}", tdsSlabMaster);
        if (tdsSlabMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TdsSlabMaster result = tdsSlabMasterRepository.save(tdsSlabMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tdsSlabMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tds-slab-masters : get all the tdsSlabMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tdsSlabMasters in body
     */
    @GetMapping("/tds-slab-masters")
    @Timed
    public ResponseEntity<List<TdsSlabMaster>> getAllTdsSlabMasters(Pageable pageable) {
        log.debug("REST request to get a page of TdsSlabMasters");
        Page<TdsSlabMaster> page = tdsSlabMasterRepository.findAllWithUnique(pageable);
        //Page<TdsSlabMaster> page = tdsSlabMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tds-slab-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    } 
    /**
     * GET  /tds-slab-masters/:id : get the "id" tdsSlabMaster.
     *
     * @param id the id of the tdsSlabMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tdsSlabMaster, or with status 404 (Not Found)
     */
    @GetMapping("/tds-slab-masters/{id}")
    @Timed
    public ResponseEntity<TdsSlabMaster> getTdsSlabMaster(@PathVariable Long id) {
        log.debug("REST request to get TdsSlabMaster : {}", id);
        Optional<TdsSlabMaster> tdsSlabMaster = tdsSlabMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tdsSlabMaster);
    }

    @PostMapping("/tds-slab-masters-edit")
    @Timed
    public ResponseEntity <List<TdsSlabMaster>> getAllTdsSlabMasterEdit(@Valid @RequestBody TdsSlabSearchMaster search) {
        log.debug("REST request to save TdsSlabMaster : {}");
        List<TdsSlabMaster> tdsSlabMaster=tdsSlabMasterRepository.findAllByYear(search.getFinYear(), search.getGender());
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsSlabMaster));
    }
    
    /**
     * DELETE  /tds-slab-masters/:id : delete the "id" tdsSlabMaster.
     *
     * @param id the id of the tdsSlabMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tds-slab-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteTdsSlabMaster(@PathVariable Long id) {
        log.debug("REST request to delete TdsSlabMaster : {}", id);
        tdsSlabMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
