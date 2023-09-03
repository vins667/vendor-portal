package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.VendorMaster;
import io.vamani.application.model.Master;
import io.vamani.application.repository.VendorMasterRepository;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VendorMaster.
 */
@RestController
@RequestMapping("/api")
public class VendorMasterResource {

    private final Logger log = LoggerFactory.getLogger(VendorMasterResource.class);

    private static final String ENTITY_NAME = "vendorMaster";

    private final VendorMasterRepository vendorMasterRepository;

    public VendorMasterResource(VendorMasterRepository vendorMasterRepository) {
        this.vendorMasterRepository = vendorMasterRepository;
    }

    /**
     * POST  /vendor-masters : Create a new vendorMaster.
     *
     * @param vendorMaster the vendorMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendorMaster, or with status 400 (Bad Request) if the vendorMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vendor-masters")
    @Timed
    public ResponseEntity<VendorMaster> createVendorMaster(@Valid @RequestBody VendorMaster vendorMaster) throws URISyntaxException {
        log.debug("REST request to save VendorMaster : {}", vendorMaster);
        if (vendorMaster.getId() != null) {
            throw new BadRequestAlertException("A new vendorMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendorMaster result = vendorMasterRepository.save(vendorMaster);
        return ResponseEntity.created(new URI("/api/vendor-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vendor-masters : Updates an existing vendorMaster.
     *
     * @param vendorMaster the vendorMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendorMaster,
     * or with status 400 (Bad Request) if the vendorMaster is not valid,
     * or with status 500 (Internal Server Error) if the vendorMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendor-masters")
    @Timed
    public ResponseEntity<VendorMaster> updateVendorMaster(@Valid @RequestBody VendorMaster vendorMaster) throws URISyntaxException {
        log.debug("REST request to update VendorMaster : {}", vendorMaster);
        if (vendorMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorMaster result = vendorMasterRepository.save(vendorMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendorMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vendor-masters : get all the vendorMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendorMasters in body
     */
    @GetMapping("/vendor-masters")
    @Timed
    public ResponseEntity<List<VendorMaster>> getAllVendorMasters(Pageable pageable) {
        log.debug("REST request to get a page of VendorMasters");
        Page<VendorMaster> page = vendorMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vendor-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vendor-masters/:id : get the "id" vendorMaster.
     *
     * @param id the id of the vendorMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorMaster, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-masters/{id}")
    @Timed
    public ResponseEntity<VendorMaster> getVendorMaster(@PathVariable Long id) {
        log.debug("REST request to get VendorMaster : {}", id);
        Optional<VendorMaster> vendorMaster = vendorMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendorMaster);
    }

    /**
     * DELETE  /vendor-masters/:id : delete the "id" vendorMaster.
     *
     * @param id the id of the vendorMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vendor-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendorMaster(@PathVariable Long id) {
        log.debug("REST request to delete VendorMaster : {}", id);

        vendorMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code POST  /fabric-content-masters} : get all the fabricContentMasters.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fabricContentMasters in body.
     */
    @PostMapping("/vendor-masters-custom-search")
    public ResponseEntity<List<VendorMaster>> searchAllFabricContentMasters(@RequestBody Master search) {
        log.debug("REST request to get a page of VendorMasters");
        String desc = "%";
        if (search.getDesc() != null) {
            desc = search.getDesc().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("description").ascending()));
        Page<VendorMaster> page = vendorMasterRepository.findAllByCodeAndDesc(desc, desc, search.getPage());
        HttpHeaders headers = io.vamani.application.web.rest.util.PaginationUtil.generatePaginationHttpHeaders(page, "/api/vendor-masters-custom-search");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
