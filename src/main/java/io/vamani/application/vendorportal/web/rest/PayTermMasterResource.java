package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.vendorportal.domain.PayTermMaster;
import io.vamani.application.vendorportal.repository.PayTermMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
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
 * REST controller for managing PayTermMaster.
 */
@RestController
@RequestMapping("/api")
public class PayTermMasterResource {

    private final Logger log = LoggerFactory.getLogger(PayTermMasterResource.class);

    private static final String ENTITY_NAME = "payTermMaster";

    private final PayTermMasterRepository payTermMasterRepository;

    public PayTermMasterResource(PayTermMasterRepository payTermMasterRepository) {
        this.payTermMasterRepository = payTermMasterRepository;
    }

    /**
     * POST  /pay-term-masters : Create a new payTermMaster.
     *
     * @param payTermMaster the payTermMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new payTermMaster, or with status 400 (Bad Request) if the payTermMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pay-term-masters")
    public ResponseEntity<PayTermMaster> createPayTermMaster(@Valid @RequestBody PayTermMaster payTermMaster) throws URISyntaxException {
        log.debug("REST request to save PayTermMaster : {}", payTermMaster);
        if (payTermMaster.getId() != null) {
            throw new BadRequestAlertException("A new payTermMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        payTermMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        payTermMaster.setCreatedDate(Instant.now());
        PayTermMaster result = payTermMasterRepository.save(payTermMaster);
        return ResponseEntity.created(new URI("/api/pay-term-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pay-term-masters : Updates an existing payTermMaster.
     *
     * @param payTermMaster the payTermMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated payTermMaster,
     * or with status 400 (Bad Request) if the payTermMaster is not valid,
     * or with status 500 (Internal Server Error) if the payTermMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pay-term-masters")
    public ResponseEntity<PayTermMaster> updatePayTermMaster(@Valid @RequestBody PayTermMaster payTermMaster) throws URISyntaxException {
        log.debug("REST request to update PayTermMaster : {}", payTermMaster);
        if (payTermMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        payTermMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        payTermMaster.setCreatedDate(Instant.now());
        PayTermMaster result = payTermMasterRepository.save(payTermMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, payTermMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pay-term-masters : get all the payTermMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of payTermMasters in body
     */
    @GetMapping("/pay-term-masters")
    public ResponseEntity<List<PayTermMaster>> getAllPayTermMasters(Pageable pageable) {
        log.debug("REST request to get a page of PayTermMasters");
        Page<PayTermMaster> page = payTermMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pay-term-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /pay-term-masters/:id : get the "id" payTermMaster.
     *
     * @param id the id of the payTermMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the payTermMaster, or with status 404 (Not Found)
     */
    @GetMapping("/pay-term-masters/{id}")
    public ResponseEntity<PayTermMaster> getPayTermMaster(@PathVariable Long id) {
        log.debug("REST request to get PayTermMaster : {}", id);
        Optional<PayTermMaster> payTermMaster = payTermMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(payTermMaster);
    }

    /**
     * DELETE  /pay-term-masters/:id : delete the "id" payTermMaster.
     *
     * @param id the id of the payTermMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pay-term-masters/{id}")
    public ResponseEntity<Void> deletePayTermMaster(@PathVariable Long id) {
        log.debug("REST request to delete PayTermMaster : {}", id);
        payTermMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
