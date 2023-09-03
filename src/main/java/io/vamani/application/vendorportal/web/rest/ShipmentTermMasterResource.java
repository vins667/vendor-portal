package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.vendorportal.domain.ShipmentTermMaster;
import io.vamani.application.vendorportal.repository.ShipmentTermMasterRepository;
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
 * REST controller for managing ShipmentTermMaster.
 */
@RestController
@RequestMapping("/api")
public class ShipmentTermMasterResource {

    private final Logger log = LoggerFactory.getLogger(ShipmentTermMasterResource.class);

    private static final String ENTITY_NAME = "shipmentTermMaster";

    private final ShipmentTermMasterRepository shipmentTermMasterRepository;

    public ShipmentTermMasterResource(ShipmentTermMasterRepository shipmentTermMasterRepository) {
        this.shipmentTermMasterRepository = shipmentTermMasterRepository;
    }

    /**
     * POST  /shipment-term-masters : Create a new shipmentTermMaster.
     *
     * @param shipmentTermMaster the shipmentTermMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shipmentTermMaster, or with status 400 (Bad Request) if the shipmentTermMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shipment-term-masters")
    public ResponseEntity<ShipmentTermMaster> createShipmentTermMaster(@Valid @RequestBody ShipmentTermMaster shipmentTermMaster) throws URISyntaxException {
        log.debug("REST request to save ShipmentTermMaster : {}", shipmentTermMaster);
        if (shipmentTermMaster.getId() != null) {
            throw new BadRequestAlertException("A new shipmentTermMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        shipmentTermMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        shipmentTermMaster.setCreatedDate(Instant.now());
        ShipmentTermMaster result = shipmentTermMasterRepository.save(shipmentTermMaster);
        return ResponseEntity.created(new URI("/api/shipment-term-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shipment-term-masters : Updates an existing shipmentTermMaster.
     *
     * @param shipmentTermMaster the shipmentTermMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shipmentTermMaster,
     * or with status 400 (Bad Request) if the shipmentTermMaster is not valid,
     * or with status 500 (Internal Server Error) if the shipmentTermMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shipment-term-masters")
    public ResponseEntity<ShipmentTermMaster> updateShipmentTermMaster(@Valid @RequestBody ShipmentTermMaster shipmentTermMaster) throws URISyntaxException {
        log.debug("REST request to update ShipmentTermMaster : {}", shipmentTermMaster);
        if (shipmentTermMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        shipmentTermMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        shipmentTermMaster.setLastUpdatedDate(Instant.now());
        ShipmentTermMaster result = shipmentTermMasterRepository.save(shipmentTermMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shipmentTermMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shipment-term-masters : get all the shipmentTermMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of shipmentTermMasters in body
     */
    @GetMapping("/shipment-term-masters")
    public ResponseEntity<List<ShipmentTermMaster>> getAllShipmentTermMasters(Pageable pageable) {
        log.debug("REST request to get a page of ShipmentTermMasters");
        Page<ShipmentTermMaster> page = shipmentTermMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/shipment-term-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /shipment-term-masters/:id : get the "id" shipmentTermMaster.
     *
     * @param id the id of the shipmentTermMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shipmentTermMaster, or with status 404 (Not Found)
     */
    @GetMapping("/shipment-term-masters/{id}")
    public ResponseEntity<ShipmentTermMaster> getShipmentTermMaster(@PathVariable Long id) {
        log.debug("REST request to get ShipmentTermMaster : {}", id);
        Optional<ShipmentTermMaster> shipmentTermMaster = shipmentTermMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(shipmentTermMaster);
    }

    /**
     * DELETE  /shipment-term-masters/:id : delete the "id" shipmentTermMaster.
     *
     * @param id the id of the shipmentTermMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shipment-term-masters/{id}")
    public ResponseEntity<Void> deleteShipmentTermMaster(@PathVariable Long id) {
        log.debug("REST request to delete ShipmentTermMaster : {}", id);
        shipmentTermMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
