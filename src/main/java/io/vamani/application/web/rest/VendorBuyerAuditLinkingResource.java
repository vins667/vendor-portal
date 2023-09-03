package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.VendorBuyerAuditLinking;
import io.vamani.application.domain.VendorBuyerAuditLinkingBuyerMaster;
import io.vamani.application.domain.VendorBuyerAuditLinkingBuyerMasterId;
import io.vamani.application.model.VendorBuyerAuditBean;
import io.vamani.application.repository.VendorBuyerAuditLinkingBuyerMasterRepository;
import io.vamani.application.repository.VendorBuyerAuditLinkingRepository;
import io.vamani.application.repository.VendorMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.vendorportal.domain.BuyerMaster;
import io.vamani.application.vendorportal.repository.BuyerMasterRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VendorBuyerAuditLinking.
 */
@RestController
@RequestMapping("/api")
public class VendorBuyerAuditLinkingResource {

    private final Logger log = LoggerFactory.getLogger(VendorBuyerAuditLinkingResource.class);

    private static final String ENTITY_NAME = "vendorBuyerAuditLinking";

    private final VendorBuyerAuditLinkingRepository vendorBuyerAuditLinkingRepository;

    @Autowired
    private VendorBuyerAuditLinkingBuyerMasterRepository vendorBuyerAuditLinkingBuyerMasterRepository;

    @Autowired
    private VendorMasterRepository vendorMasterRepository;

    @Autowired
    private BuyerMasterRepository buyerMasterRepository;

    public VendorBuyerAuditLinkingResource(VendorBuyerAuditLinkingRepository vendorBuyerAuditLinkingRepository) {
        this.vendorBuyerAuditLinkingRepository = vendorBuyerAuditLinkingRepository;
    }

    /**
     * POST  /vendor-buyer-audit-linkings : Create a new vendorBuyerAuditLinking.
     *
     * @param !vendorBuyerAuditLinking the vendorBuyerAuditLinking to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendorBuyerAuditLinking, or with status 400 (Bad Request) if the vendorBuyerAuditLinking has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vendor-buyer-audit-linkings")
    @Timed
    public ResponseEntity<VendorBuyerAuditLinking> createVendorBuyerAuditLinking(@Valid @RequestBody VendorBuyerAuditBean vendorBuyerAuditBean) throws URISyntaxException {
        VendorBuyerAuditLinking result = null;
        if (vendorBuyerAuditBean != null && vendorBuyerAuditBean.getVendorMaster() != null) {
            String vendorCode = vendorBuyerAuditBean.getVendorMaster().getCode();
            VendorBuyerAuditLinking vendorBuyerAuditLinking = vendorBuyerAuditLinkingRepository.findByVendorCode(vendorCode);
            if (vendorBuyerAuditLinking != null) {
                vendorBuyerAuditLinking.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                vendorBuyerAuditLinking.setLastUpdatedDate(Instant.now());
                result = vendorBuyerAuditLinkingRepository.save(vendorBuyerAuditLinking);
            } else {
                vendorBuyerAuditLinking = new VendorBuyerAuditLinking();
                vendorBuyerAuditLinking.setVendorMasterCode(vendorCode);
                vendorBuyerAuditLinking.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                vendorBuyerAuditLinking.setCreatedDate(Instant.now());
                result = vendorBuyerAuditLinkingRepository.save(vendorBuyerAuditLinking);
            }
            if (result != null) {
                if(vendorBuyerAuditBean.getBuyerMasters() != null && vendorBuyerAuditBean.getBuyerMasters().size()>0) {
                    vendorBuyerAuditLinkingBuyerMasterRepository.deleteByVendorBuyerId(result.getId());
                    for (BuyerMaster buyerMaster : vendorBuyerAuditBean.getBuyerMasters()) {
                        VendorBuyerAuditLinkingBuyerMaster vendorBuyerAuditLinkingBuyerMaster = new VendorBuyerAuditLinkingBuyerMaster(new VendorBuyerAuditLinkingBuyerMasterId(buyerMaster.getBuyerCode(), result.getId()));
                        vendorBuyerAuditLinkingBuyerMasterRepository.save(vendorBuyerAuditLinkingBuyerMaster);
                    }
                }
            }
        }
        if(result != null) {
            return ResponseEntity.created(new URI("/api/vendor-buyer-audit-linkings/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
        } else {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "400", "Record not save")).build();
        }
    }

    /**
     * PUT  /vendor-buyer-audit-linkings : Updates an existing vendorBuyerAuditLinking.
     *
     * @param vendorBuyerAuditLinking the vendorBuyerAuditLinking to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendorBuyerAuditLinking,
     * or with status 400 (Bad Request) if the vendorBuyerAuditLinking is not valid,
     * or with status 500 (Internal Server Error) if the vendorBuyerAuditLinking couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendor-buyer-audit-linkings")
    @Timed
    public ResponseEntity<VendorBuyerAuditLinking> updateVendorBuyerAuditLinking(@Valid @RequestBody VendorBuyerAuditLinking vendorBuyerAuditLinking) throws URISyntaxException {
        log.debug("REST request to update VendorBuyerAuditLinking : {}", vendorBuyerAuditLinking);
        if (vendorBuyerAuditLinking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorBuyerAuditLinking result = vendorBuyerAuditLinkingRepository.save(vendorBuyerAuditLinking);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendorBuyerAuditLinking.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vendor-buyer-audit-linkings : get all the vendorBuyerAuditLinkings.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of vendorBuyerAuditLinkings in body
     */
    @GetMapping("/vendor-buyer-audit-linkings")
    @Timed
    public ResponseEntity<List<VendorBuyerAuditBean>> getAllVendorBuyerAuditLinkings(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of VendorBuyerAuditLinkings");
        Page<VendorBuyerAuditLinking> page = vendorBuyerAuditLinkingRepository.findAll(pageable);
        List<VendorBuyerAuditBean> vendorBuyerAuditBeans = new ArrayList<>();
        if (page != null && page.getContent() != null) {
            for (VendorBuyerAuditLinking vendorBuyerAuditLinking : page.getContent()) {
                VendorBuyerAuditBean vendorBuyerAuditBean = new VendorBuyerAuditBean();
                BeanUtils.copyProperties(vendorBuyerAuditLinking, vendorBuyerAuditBean);
                vendorBuyerAuditBean.setVendorMaster(vendorMasterRepository.findByCode(vendorBuyerAuditLinking.getVendorMasterCode()));
                List<VendorBuyerAuditLinkingBuyerMaster> vendorBuyerAuditLinkingBuyerMasters = vendorBuyerAuditLinkingBuyerMasterRepository.findAllByVendorBuyerId(vendorBuyerAuditBean.getId());
                List<String> buyerCodeList = new ArrayList<>();
                for (VendorBuyerAuditLinkingBuyerMaster vendorBuyerAuditLinkingBuyerMaster : vendorBuyerAuditLinkingBuyerMasters) {
                    buyerCodeList.add(vendorBuyerAuditLinkingBuyerMaster.getId().getBuyerMastersCode());
                }
                if(buyerCodeList != null && buyerCodeList.size()>0) {
                    vendorBuyerAuditBean.setBuyerMasters(buyerMasterRepository.findAllByCode(buyerCodeList));
                }
                vendorBuyerAuditBeans.add(vendorBuyerAuditBean);
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/vendor-buyer-audit-linkings?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(vendorBuyerAuditBeans);
    }

    /**
     * GET  /vendor-buyer-audit-linkings/:id : get the "id" vendorBuyerAuditLinking.
     *
     * @param id the id of the vendorBuyerAuditLinking to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorBuyerAuditLinking, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-buyer-audit-linkings/{id}")
    @Timed
    public ResponseEntity<VendorBuyerAuditBean> getVendorBuyerAuditLinking(@PathVariable Long id) {
        log.debug("REST request to get VendorBuyerAuditLinking : {}", id);
        VendorBuyerAuditBean vendorBuyerAuditBean = new VendorBuyerAuditBean();
        VendorBuyerAuditLinking vendorBuyerAuditLinking = vendorBuyerAuditLinkingRepository.findById(id).orElse(null);
        vendorBuyerAuditBean.setVendorMaster(vendorMasterRepository.findByCode(vendorBuyerAuditLinking.getVendorMasterCode()));
        List<VendorBuyerAuditLinkingBuyerMaster> vendorBuyerAuditLinkingBuyerMasters = vendorBuyerAuditLinkingBuyerMasterRepository.findAllByVendorBuyerId(id);
        List<String> buyerCodeList = new ArrayList<>();
        for (VendorBuyerAuditLinkingBuyerMaster vendorBuyerAuditLinkingBuyerMaster : vendorBuyerAuditLinkingBuyerMasters) {
            buyerCodeList.add(vendorBuyerAuditLinkingBuyerMaster.getId().getBuyerMastersCode());
        }
        if(buyerCodeList != null && buyerCodeList.size()>0) {
            vendorBuyerAuditBean.setBuyerMasters(buyerMasterRepository.findAllByCode(buyerCodeList));
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vendorBuyerAuditBean));
    }

    /**
     * DELETE  /vendor-buyer-audit-linkings/:id : delete the "id" vendorBuyerAuditLinking.
     *
     * @param id the id of the vendorBuyerAuditLinking to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vendor-buyer-audit-linkings/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendorBuyerAuditLinking(@PathVariable Long id) {
        log.debug("REST request to delete VendorBuyerAuditLinking : {}", id);

        vendorBuyerAuditLinkingRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/vendor-buyer-audits-linking-vendor-code/{vendorCode}")
    @Timed
    public ResponseEntity<List<BuyerMaster>> findByVendorBuyerAuditLinking(@PathVariable String vendorCode) {
        List<BuyerMaster> buyerMasters = new ArrayList<>();
        VendorBuyerAuditLinking vendorBuyerAuditLinking = vendorBuyerAuditLinkingRepository.findByVendorCode(vendorCode);
        if (vendorBuyerAuditLinking != null) {
            List<VendorBuyerAuditLinkingBuyerMaster> vendorBuyerAuditLinkingBuyerMasters = vendorBuyerAuditLinkingBuyerMasterRepository.findAllByVendorBuyerId(vendorBuyerAuditLinking.getId());
            List<String> buyerCodeList = new ArrayList<>();
            for (VendorBuyerAuditLinkingBuyerMaster vendorBuyerAuditLinkingBuyerMaster : vendorBuyerAuditLinkingBuyerMasters) {
                buyerCodeList.add(vendorBuyerAuditLinkingBuyerMaster.getId().getBuyerMastersCode());
            }
            if(buyerCodeList != null && buyerCodeList.size()>0) {
                buyerMasters = buyerMasterRepository.findAllByCode(buyerCodeList);
            }
        }
        return ResponseEntity.ok().body(buyerMasters);
    }
}
