package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.vendorportal.domain.DeliveryTermMaster;
import io.vamani.application.vendorportal.repository.DeliveryTermMasterRepository;
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
 * REST controller for managing DeliveryTermMaster.
 */
@RestController
@RequestMapping("/api")
public class DeliveryTermMasterResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryTermMasterResource.class);

    private static final String ENTITY_NAME = "deliveryTermMaster";

    private final DeliveryTermMasterRepository deliveryTermMasterRepository;

    public DeliveryTermMasterResource(DeliveryTermMasterRepository deliveryTermMasterRepository) {
        this.deliveryTermMasterRepository = deliveryTermMasterRepository;
    }

    /**
     * POST  /delivery-term-masters : Create a new deliveryTermMaster.
     *
     * @param deliveryTermMaster the deliveryTermMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deliveryTermMaster, or with status 400 (Bad Request) if the deliveryTermMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/delivery-term-masters")
    public ResponseEntity<DeliveryTermMaster> createDeliveryTermMaster(@Valid @RequestBody DeliveryTermMaster deliveryTermMaster) throws URISyntaxException {
        log.debug("REST request to save DeliveryTermMaster : {}", deliveryTermMaster);
        if (deliveryTermMaster.getId() != null) {
            throw new BadRequestAlertException("A new deliveryTermMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        deliveryTermMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        deliveryTermMaster.setCreatedDate(Instant.now());
        DeliveryTermMaster result = deliveryTermMasterRepository.save(deliveryTermMaster);
        return ResponseEntity.created(new URI("/api/delivery-term-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delivery-term-masters : Updates an existing deliveryTermMaster.
     *
     * @param deliveryTermMaster the deliveryTermMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveryTermMaster,
     * or with status 400 (Bad Request) if the deliveryTermMaster is not valid,
     * or with status 500 (Internal Server Error) if the deliveryTermMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delivery-term-masters")
    public ResponseEntity<DeliveryTermMaster> updateDeliveryTermMaster(@Valid @RequestBody DeliveryTermMaster deliveryTermMaster) throws URISyntaxException {
        log.debug("REST request to update DeliveryTermMaster : {}", deliveryTermMaster);
        if (deliveryTermMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        deliveryTermMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        deliveryTermMaster.setLastUpdatedDate(Instant.now());
        DeliveryTermMaster result = deliveryTermMasterRepository.save(deliveryTermMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveryTermMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /delivery-term-masters : get all the deliveryTermMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryTermMasters in body
     */
    @GetMapping("/delivery-term-masters")
    public ResponseEntity<List<DeliveryTermMaster>> getAllDeliveryTermMasters(Pageable pageable) {
        log.debug("REST request to get a page of DeliveryTermMasters");
        Page<DeliveryTermMaster> page = deliveryTermMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-term-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /delivery-term-masters/:id : get the "id" deliveryTermMaster.
     *
     * @param id the id of the deliveryTermMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deliveryTermMaster, or with status 404 (Not Found)
     */
    @GetMapping("/delivery-term-masters/{id}")
    public ResponseEntity<DeliveryTermMaster> getDeliveryTermMaster(@PathVariable Long id) {
        log.debug("REST request to get DeliveryTermMaster : {}", id);
        Optional<DeliveryTermMaster> deliveryTermMaster = deliveryTermMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(deliveryTermMaster);
    }

    /**
     * DELETE  /delivery-term-masters/:id : delete the "id" deliveryTermMaster.
     *
     * @param id the id of the deliveryTermMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delivery-term-masters/{id}")
    public ResponseEntity<Void> deleteDeliveryTermMaster(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryTermMaster : {}", id);
        deliveryTermMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
