package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.AuditQuesBuyerMappingDtls;
import io.vamani.application.domain.VendorMaster;
import io.vamani.application.model.Master;
import io.vamani.application.model.VendorBuyerAuditDetailsBean;
import io.vamani.application.repository.AuditQuesBuyerMappingDtlsRepository;
import io.vamani.application.repository.VendorMasterRepository;
import io.vamani.application.vendorportal.repository.BuyerMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AuditQuesBuyerMappingDtls.
 */
@RestController
@RequestMapping("/api")
public class AuditQuesBuyerMappingDtlsResource {

    private final Logger log = LoggerFactory.getLogger(AuditQuesBuyerMappingDtlsResource.class);

    private static final String ENTITY_NAME = "auditQuesBuyerMappingDtls";

    private final AuditQuesBuyerMappingDtlsRepository auditQuesBuyerMappingDtlsRepository;

    @Autowired
    private VendorMasterRepository vendorMasterRepository;

    @Autowired
    private BuyerMasterRepository buyerMasterRepository;

    public AuditQuesBuyerMappingDtlsResource(AuditQuesBuyerMappingDtlsRepository auditQuesBuyerMappingDtlsRepository) {
        this.auditQuesBuyerMappingDtlsRepository = auditQuesBuyerMappingDtlsRepository;
    }

    /**
     * POST  /audit-ques-buyer-mapping-dtls : Create a new auditQuesBuyerMappingDtls.
     *
     * @param auditQuesBuyerMappingDtls the auditQuesBuyerMappingDtls to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditQuesBuyerMappingDtls, or with status 400 (Bad Request) if the auditQuesBuyerMappingDtls has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-ques-buyer-mapping-dtls")
    @Timed
    public ResponseEntity<AuditQuesBuyerMappingDtls> createAuditQuesBuyerMappingDtls(@Valid @RequestBody AuditQuesBuyerMappingDtls auditQuesBuyerMappingDtls) throws URISyntaxException {
        log.debug("REST request to save AuditQuesBuyerMappingDtls : {}", auditQuesBuyerMappingDtls);
        if (auditQuesBuyerMappingDtls.getId() != null) {
            throw new BadRequestAlertException("A new auditQuesBuyerMappingDtls cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuditQuesBuyerMappingDtls result = auditQuesBuyerMappingDtlsRepository.save(auditQuesBuyerMappingDtls);
        return ResponseEntity.created(new URI("/api/audit-ques-buyer-mapping-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-ques-buyer-mapping-dtls : Updates an existing auditQuesBuyerMappingDtls.
     *
     * @param auditQuesBuyerMappingDtls the auditQuesBuyerMappingDtls to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditQuesBuyerMappingDtls,
     * or with status 400 (Bad Request) if the auditQuesBuyerMappingDtls is not valid,
     * or with status 500 (Internal Server Error) if the auditQuesBuyerMappingDtls couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-ques-buyer-mapping-dtls")
    @Timed
    public ResponseEntity<AuditQuesBuyerMappingDtls> updateAuditQuesBuyerMappingDtls(@Valid @RequestBody AuditQuesBuyerMappingDtls auditQuesBuyerMappingDtls) throws URISyntaxException {
        log.debug("REST request to update AuditQuesBuyerMappingDtls : {}", auditQuesBuyerMappingDtls);
        if (auditQuesBuyerMappingDtls.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuditQuesBuyerMappingDtls result = auditQuesBuyerMappingDtlsRepository.save(auditQuesBuyerMappingDtls);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditQuesBuyerMappingDtls.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-ques-buyer-mapping-dtls : get all the auditQuesBuyerMappingDtls.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditQuesBuyerMappingDtls in body
     */
    @GetMapping("/audit-ques-buyer-mapping-dtls")
    @Timed
    public ResponseEntity<List<AuditQuesBuyerMappingDtls>> getAllAuditQuesBuyerMappingDtls(Pageable pageable) {
        log.debug("REST request to get a page of AuditQuesBuyerMappingDtls");
        Page<AuditQuesBuyerMappingDtls> page = auditQuesBuyerMappingDtlsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-ques-buyer-mapping-dtls");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /audit-ques-buyer-mapping-dtls/:id : get the "id" auditQuesBuyerMappingDtls.
     *
     * @param id the id of the auditQuesBuyerMappingDtls to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditQuesBuyerMappingDtls, or with status 404 (Not Found)
     */
    @GetMapping("/audit-ques-buyer-mapping-dtls/{id}")
    @Timed
    public ResponseEntity<AuditQuesBuyerMappingDtls> getAuditQuesBuyerMappingDtls(@PathVariable Long id) {
        log.debug("REST request to get AuditQuesBuyerMappingDtls : {}", id);
        Optional<AuditQuesBuyerMappingDtls> auditQuesBuyerMappingDtls = auditQuesBuyerMappingDtlsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auditQuesBuyerMappingDtls);
    }

    /**
     * DELETE  /audit-ques-buyer-mapping-dtls/:id : delete the "id" auditQuesBuyerMappingDtls.
     *
     * @param id the id of the auditQuesBuyerMappingDtls to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-ques-buyer-mapping-dtls/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditQuesBuyerMappingDtls(@PathVariable Long id) {
        log.debug("REST request to delete AuditQuesBuyerMappingDtls : {}", id);

        auditQuesBuyerMappingDtlsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /audit-ques-buyer-mapping-dtls/:id : get the "id" auditQuesBuyerMappingDtls.
     *
     * @param id the id of the auditQuesBuyerMappingDtls to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditQuesBuyerMappingDtls, or with status 404 (Not Found)
     */
    @PostMapping("/audit-ques-buyer-mapping-dtls-buyer-code")
    @Timed
    public ResponseEntity<VendorBuyerAuditDetailsBean> getAuditQuesBuyerMappingDtlsByVendorCode(@RequestBody Master search) {
        VendorBuyerAuditDetailsBean vendorBuyerAuditDetailsBean = new VendorBuyerAuditDetailsBean();
        vendorBuyerAuditDetailsBean.setVendorMaster(vendorMasterRepository.findByCode(search.getId()));
        vendorBuyerAuditDetailsBean.setBuyerMaster(buyerMasterRepository.findByCode(search.getDesc()));
        List<Master> masterList = new ArrayList<>();
        List<Object[]> objects = auditQuesBuyerMappingDtlsRepository.findByBuyerMasterId(search.getDesc());
        for (Object[] object : objects) {
            Master master = new Master();
            master.setId(object[0].toString());
            master.setDesc(object[1].toString());
            masterList.add(master);
        }
        vendorBuyerAuditDetailsBean.setMasters(masterList);
        return ResponseUtil.wrapOrNotFound(Optional.of(vendorBuyerAuditDetailsBean));
    }



    /**
     * GET  /audit-ques-buyer-mapping-dtls/:id : get the "id" auditQuesBuyerMappingDtls.
     *
     * @param id the id of the auditQuesBuyerMappingDtls to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditQuesBuyerMappingDtls, or with status 404 (Not Found)
     */
    @PostMapping("/audit-ques-buyer-mapping-dtls-by-audits")
    @Timed
    public ResponseEntity<List<Master>> getAuditQuesBuyerMappingDtlsByAudits(@RequestBody Master search) {
        List<Master> masterList = new ArrayList<>();
        List<Object[]> objects = auditQuesBuyerMappingDtlsRepository.findByBuyerMasterId(search.getDesc());
        for (Object[] object : objects) {
            Master master = new Master();
            master.setId(object[0].toString());
            master.setDesc(object[1].toString());
            masterList.add(master);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(masterList));
    }
}
