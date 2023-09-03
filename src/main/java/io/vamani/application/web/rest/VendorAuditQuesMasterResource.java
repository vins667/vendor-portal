package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.VendorAuditQuesDetails;
import io.vamani.application.domain.VendorAuditQuesMaster;
import io.vamani.application.model.VendorAuditQuesMasterBean;
import io.vamani.application.repository.VendorAuditQuesDetailsRepository;
import io.vamani.application.repository.VendorAuditQuesMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing VendorAuditQuesMaster.
 */
@RestController
@RequestMapping("/api")
public class VendorAuditQuesMasterResource {

    private final Logger log = LoggerFactory.getLogger(VendorAuditQuesMasterResource.class);

    private static final String ENTITY_NAME = "vendorAuditQuesMaster";

    private final VendorAuditQuesMasterRepository vendorAuditQuesMasterRepository;

    @Autowired
    private VendorAuditQuesDetailsRepository vendorAuditQuesDetailsRepository;

    public VendorAuditQuesMasterResource(VendorAuditQuesMasterRepository vendorAuditQuesMasterRepository) {
        this.vendorAuditQuesMasterRepository = vendorAuditQuesMasterRepository;
    }

    /**
     * POST  /vendor-audit-ques-masters : Create a new vendorAuditQuesMaster.
     *
     * @param vendorAuditQuesMaster the vendorAuditQuesMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendorAuditQuesMaster, or with status 400 (Bad Request) if the vendorAuditQuesMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vendor-audit-ques-masters")
    @Timed
    public ResponseEntity<VendorAuditQuesMaster> createVendorAuditQuesMaster(@Valid @RequestBody VendorAuditQuesMasterBean vendorAuditQuesMasterBean) throws URISyntaxException {
        log.debug("REST request to save VendorAuditQuesMaster : {}", vendorAuditQuesMasterBean);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        if (vendorAuditQuesMasterBean.getId() != null) {
            throw new BadRequestAlertException("A new vendorAuditQuesMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendorAuditQuesMaster vendorAuditQuesMaster = new VendorAuditQuesMaster();
        BeanUtils.copyProperties(vendorAuditQuesMasterBean, vendorAuditQuesMaster, "vendorAuditQuesDetails");
        vendorAuditQuesMaster.setCreatedBy(currentUser);
        vendorAuditQuesMaster.setCreatedDate(Instant.now());
        VendorAuditQuesMaster result = vendorAuditQuesMasterRepository.save(vendorAuditQuesMaster);
        if (result != null && vendorAuditQuesMasterBean.getVendorAuditQuesDetails() != null) {
            for (VendorAuditQuesDetails vendorAuditQuesDetails : vendorAuditQuesMasterBean.getVendorAuditQuesDetails()) {
                if (vendorAuditQuesDetails.getAuditQuestion() != null && vendorAuditQuesDetails.getAuditQuestion().length() > 0) {
                    vendorAuditQuesDetails.setVendorAuditQuesMaster(result);
                    vendorAuditQuesDetails.setCreatedBy(currentUser);
                    vendorAuditQuesDetails.setCreatedDate(Instant.now());
                    vendorAuditQuesDetailsRepository.save(vendorAuditQuesDetails);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/vendor-audit-ques-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vendor-audit-ques-masters : Updates an existing vendorAuditQuesMaster.
     *
     * @param vendorAuditQuesMaster the vendorAuditQuesMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendorAuditQuesMaster,
     * or with status 400 (Bad Request) if the vendorAuditQuesMaster is not valid,
     * or with status 500 (Internal Server Error) if the vendorAuditQuesMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendor-audit-ques-masters")
    @Timed
    public ResponseEntity<VendorAuditQuesMaster> updateVendorAuditQuesMaster(@Valid @RequestBody VendorAuditQuesMasterBean vendorAuditQuesMasterBean) throws URISyntaxException {
        log.debug("REST request to update VendorAuditQuesMaster : {}", vendorAuditQuesMasterBean);
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        if (vendorAuditQuesMasterBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorAuditQuesMaster vendorAuditQuesMaster = new VendorAuditQuesMaster();
        BeanUtils.copyProperties(vendorAuditQuesMasterBean, vendorAuditQuesMaster, "vendorAuditQuesDetails");
        vendorAuditQuesMaster.setLastUpdatedBy(currentUser);
        vendorAuditQuesMaster.setLastUpdatedDate(Instant.now());
        VendorAuditQuesMaster result = vendorAuditQuesMasterRepository.save(vendorAuditQuesMaster);
        if (result != null && vendorAuditQuesMasterBean.getVendorAuditQuesDetails() != null) {
            for (VendorAuditQuesDetails vendorAuditQuesDetails : vendorAuditQuesMasterBean.getVendorAuditQuesDetails()) {
                if (vendorAuditQuesDetails.getAuditQuestion() != null && vendorAuditQuesDetails.getAuditQuestion().length() > 0) {
                    vendorAuditQuesDetails.setVendorAuditQuesMaster(result);
                    if(vendorAuditQuesDetails != null && vendorAuditQuesDetails.getId() != null) {
                        vendorAuditQuesDetails.setLastUpdatedBy(currentUser);
                        vendorAuditQuesDetails.setLastUpdatedDate(Instant.now());
                    } else {
                        vendorAuditQuesDetails.setCreatedBy(currentUser);
                        vendorAuditQuesDetails.setCreatedDate(Instant.now());
                    }
                    vendorAuditQuesDetailsRepository.save(vendorAuditQuesDetails);
                }
            }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendorAuditQuesMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vendor-audit-ques-masters : get all the vendorAuditQuesMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendorAuditQuesMasters in body
     */
    @GetMapping("/vendor-audit-ques-masters")
    @Timed
    public ResponseEntity<List<VendorAuditQuesMaster>> getAllVendorAuditQuesMasters(Pageable pageable) {
        log.debug("REST request to get a page of VendorAuditQuesMasters");
        Page<VendorAuditQuesMaster> page = vendorAuditQuesMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vendor-audit-ques-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vendor-audit-ques-masters/:id : get the "id" vendorAuditQuesMaster.
     *
     * @param id the id of the vendorAuditQuesMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorAuditQuesMaster, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-audit-ques-masters/{id}")
    @Timed
    public ResponseEntity<VendorAuditQuesMaster> getVendorAuditQuesMaster(@PathVariable Long id) {
        log.debug("REST request to get VendorAuditQuesMaster : {}", id);
        Optional<VendorAuditQuesMaster> vendorAuditQuesMaster = vendorAuditQuesMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendorAuditQuesMaster);
    }

    /**
     * DELETE  /vendor-audit-ques-masters/:id : delete the "id" vendorAuditQuesMaster.
     *
     * @param id the id of the vendorAuditQuesMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vendor-audit-ques-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendorAuditQuesMaster(@PathVariable Long id) {
        log.debug("REST request to delete VendorAuditQuesMaster : {}", id);

        vendorAuditQuesMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
