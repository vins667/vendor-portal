package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.VendorAuditQuesDetails;
import io.vamani.application.model.VendorAuditGroupMasterBean;
import io.vamani.application.model.VendorAuditQuesDetailsBean;
import io.vamani.application.repository.VendorAuditQuesDetailsRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.*;

/**
 * REST controller for managing VendorAuditQuesDetails.
 */
@RestController
@RequestMapping("/api")
public class VendorAuditQuesDetailsResource {

    private final Logger log = LoggerFactory.getLogger(VendorAuditQuesDetailsResource.class);

    private static final String ENTITY_NAME = "vendorAuditQuesDetails";

    private final VendorAuditQuesDetailsRepository vendorAuditQuesDetailsRepository;

    public VendorAuditQuesDetailsResource(VendorAuditQuesDetailsRepository vendorAuditQuesDetailsRepository) {
        this.vendorAuditQuesDetailsRepository = vendorAuditQuesDetailsRepository;
    }

    /**
     * POST  /vendor-audit-ques-details : Create a new vendorAuditQuesDetails.
     *
     * @param vendorAuditQuesDetails the vendorAuditQuesDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendorAuditQuesDetails, or with status 400 (Bad Request) if the vendorAuditQuesDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vendor-audit-ques-details")
    @Timed
    public ResponseEntity<VendorAuditQuesDetails> createVendorAuditQuesDetails(@Valid @RequestBody VendorAuditQuesDetails vendorAuditQuesDetails) throws URISyntaxException {
        log.debug("REST request to save VendorAuditQuesDetails : {}", vendorAuditQuesDetails);
        if (vendorAuditQuesDetails.getId() != null) {
            throw new BadRequestAlertException("A new vendorAuditQuesDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendorAuditQuesDetails result = vendorAuditQuesDetailsRepository.save(vendorAuditQuesDetails);
        return ResponseEntity.created(new URI("/api/vendor-audit-ques-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vendor-audit-ques-details : Updates an existing vendorAuditQuesDetails.
     *
     * @param vendorAuditQuesDetails the vendorAuditQuesDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendorAuditQuesDetails,
     * or with status 400 (Bad Request) if the vendorAuditQuesDetails is not valid,
     * or with status 500 (Internal Server Error) if the vendorAuditQuesDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendor-audit-ques-details")
    @Timed
    public ResponseEntity<VendorAuditQuesDetails> updateVendorAuditQuesDetails(@Valid @RequestBody VendorAuditQuesDetails vendorAuditQuesDetails) throws URISyntaxException {
        log.debug("REST request to update VendorAuditQuesDetails : {}", vendorAuditQuesDetails);
        if (vendorAuditQuesDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorAuditQuesDetails result = vendorAuditQuesDetailsRepository.save(vendorAuditQuesDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendorAuditQuesDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vendor-audit-ques-details : get all the vendorAuditQuesDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendorAuditQuesDetails in body
     */
    @GetMapping("/vendor-audit-ques-details")
    @Timed
    public ResponseEntity<List<VendorAuditQuesDetails>> getAllVendorAuditQuesDetails(Pageable pageable) {
        log.debug("REST request to get a page of VendorAuditQuesDetails");
        Page<VendorAuditQuesDetails> page = vendorAuditQuesDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vendor-audit-ques-details");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vendor-audit-ques-details/:id : get the "id" vendorAuditQuesDetails.
     *
     * @param id the id of the vendorAuditQuesDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorAuditQuesDetails, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-audit-ques-details/{id}")
    @Timed
    public ResponseEntity<VendorAuditQuesDetails> getVendorAuditQuesDetails(@PathVariable Long id) {
        log.debug("REST request to get VendorAuditQuesDetails : {}", id);
        Optional<VendorAuditQuesDetails> vendorAuditQuesDetails = vendorAuditQuesDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendorAuditQuesDetails);
    }

    /**
     * GET  /vendor-audit-ques-details/:id : get the "id" vendorAuditQuesDetails.
     *
     * @param id the id of the vendorAuditQuesDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorAuditQuesDetails, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-audit-ques-details-master-id/{id}")
    @Timed
    public ResponseEntity<List<VendorAuditGroupMasterBean>> SearchVendorAuditQuesDetailsByMasterId(@PathVariable Long id) {
        log.debug("REST request to get VendorAuditQuesDetails : {}", id);
        List<VendorAuditQuesDetails> vendorAuditQuesDetails = vendorAuditQuesDetailsRepository.findAllByMasterId(id);
        List<VendorAuditGroupMasterBean> vendorAuditGroupMasterBeans = new ArrayList<>();
        Map<Long, List<VendorAuditQuesDetailsBean>> auditMap = new HashMap<>();
        Map<Long, String> groupNameMap = new HashMap<>();
        for (VendorAuditQuesDetails vendorAuditQuesDetail : vendorAuditQuesDetails) {
            if (auditMap.containsKey(vendorAuditQuesDetail.getAuditGroupMaster().getId())) {
                List<VendorAuditQuesDetailsBean> auditQuesDetails = auditMap.get(vendorAuditQuesDetail.getAuditGroupMaster().getId());
                VendorAuditQuesDetailsBean vendorAuditQuesDetailsBean = new VendorAuditQuesDetailsBean();
                BeanUtils.copyProperties(vendorAuditQuesDetail, vendorAuditQuesDetailsBean);
                auditQuesDetails.add(vendorAuditQuesDetailsBean);
                auditMap.put(vendorAuditQuesDetail.getAuditGroupMaster().getId(), auditQuesDetails);
            } else {
                List<VendorAuditQuesDetailsBean> auditQuesDetails = new ArrayList<>();
                VendorAuditQuesDetailsBean vendorAuditQuesDetailsBean = new VendorAuditQuesDetailsBean();
                BeanUtils.copyProperties(vendorAuditQuesDetail, vendorAuditQuesDetailsBean);
                auditQuesDetails.add(vendorAuditQuesDetailsBean);
                groupNameMap.put(vendorAuditQuesDetail.getAuditGroupMaster().getId(), vendorAuditQuesDetail.getAuditGroupMaster().getDescription());
                auditMap.put(vendorAuditQuesDetail.getAuditGroupMaster().getId(), auditQuesDetails);
            }
        }
        for (Long key : auditMap.keySet()) {
            VendorAuditGroupMasterBean vendorAuditGroupMasterBean = new VendorAuditGroupMasterBean();
            vendorAuditGroupMasterBean.setId(key);
            vendorAuditGroupMasterBean.setGroupName(groupNameMap.get(key));
            vendorAuditGroupMasterBean.setVendorAuditQuesDetails(auditMap.get(key));
            vendorAuditGroupMasterBeans.add(vendorAuditGroupMasterBean);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vendorAuditGroupMasterBeans));
    }

    /**
     * DELETE  /vendor-audit-ques-details/:id : delete the "id" vendorAuditQuesDetails.
     *
     * @param id the id of the vendorAuditQuesDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vendor-audit-ques-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendorAuditQuesDetails(@PathVariable Long id) {
        log.debug("REST request to delete VendorAuditQuesDetails : {}", id);

        vendorAuditQuesDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
