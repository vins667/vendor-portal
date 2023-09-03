package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.vendorportal.domain.VendSubTypeMaster;
import io.vamani.application.vendorportal.domain.VendTypeMaster;
import io.vamani.application.vendorportal.domain.VendorBranchDetails;
import io.vamani.application.vendorportal.domain.VendorBranchDetailsTransaction;
import io.vamani.application.vendorportal.model.BranchSearch;
import io.vamani.application.vendorportal.model.VendorBranchDetailsBean;
import io.vamani.application.vendorportal.model.VendorBranchDetailsTransactionBean;
import io.vamani.application.vendorportal.repository.VendSubTypeMasterRepository;
import io.vamani.application.vendorportal.repository.VendTypeMasterRepository;
import io.vamani.application.vendorportal.repository.VendorBranchDetailsRepository;
import io.vamani.application.vendorportal.repository.VendorBranchDetailsTransactionRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing VendorBranchDetails.
 */
@RestController
@RequestMapping("/api")
public class VendorBranchDetailsResource {

    private final Logger log = LoggerFactory.getLogger(VendorBranchDetailsResource.class);

    private static final String ENTITY_NAME = "vendorBranchDetails";

    private final VendorBranchDetailsRepository vendorBranchDetailsRepository;

    @Autowired
    private VendorBranchDetailsTransactionRepository vendorBranchDetailsTransactionRepository;

    @Autowired
    private VendTypeMasterRepository vendTypeMasterRepository;

    @Autowired
    private VendSubTypeMasterRepository vendSubTypeMasterRepository;

    public VendorBranchDetailsResource(VendorBranchDetailsRepository vendorBranchDetailsRepository) {
        this.vendorBranchDetailsRepository = vendorBranchDetailsRepository;
    }

    /**
     * POST  /vendor-branch-details : Create a new vendorBranchDetails.
     *
     * @param vendorBranchDetails the vendorBranchDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendorBranchDetails, or with status 400 (Bad Request) if the vendorBranchDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    /*@PostMapping("/vendor-branch-details")
    @Timed
    public ResponseEntity<VendorBranchDetails> createVendorBranchDetails(@Valid @RequestBody VendorBranchDetails vendorBranchDetails) throws URISyntaxException {
        log.debug("REST request to save VendorBranchDetails : {}", vendorBranchDetails);
        if (vendorBranchDetails.getId() != null) {
            throw new BadRequestAlertException("A new vendorBranchDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        if (user.getVendors().getId()== null) {
            throw new BadRequestAlertException("A VendorId does not exist", ENTITY_NAME, "VendorIdnotexists");
        }
        vendorBranchDetails.setVendorId(user.getVendors().getId());
        vendorBranchDetails.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        vendorBranchDetails.setCreatedDate(Instant.now());
        VendorBranchDetails result = vendorBranchDetailsRepository.save(vendorBranchDetails);
        return ResponseEntity.created(new URI("/api/vendor-branch-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }*/

    /**
     * PUT  /vendor-branch-details : Updates an existing vendorBranchDetails.
     *
     * @param vendorBranchDetails the vendorBranchDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendorBranchDetails,
     * or with status 400 (Bad Request) if the vendorBranchDetails is not valid,
     * or with status 500 (Internal Server Error) if the vendorBranchDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendor-branch-details")
    @Timed
    public ResponseEntity<VendorBranchDetails> updateVendorBranchDetails(@Valid @RequestBody VendorBranchDetails vendorBranchDetails) throws URISyntaxException {
        log.debug("REST request to update VendorBranchDetails : {}", vendorBranchDetails);
        if (vendorBranchDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorBranchDetails result = vendorBranchDetailsRepository.save(vendorBranchDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vendorBranchDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vendor-branch-details : get all the vendorBranchDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendorBranchDetails in body
     */
    @PostMapping("/vendor-branch-details")
    @Timed
    public ResponseEntity<List<VendorBranchDetailsBean>> getAllVendorBranchDetails(@Valid @RequestBody BranchSearch branchSearch) {
        log.debug("REST request to get a page of VendorBranchDetails");
        branchSearch.setSize(10);
        Long vendorId = branchSearch.getVendorId();
        branchSearch.setPage(PageRequest.of(branchSearch.getPageNo(), branchSearch.getSize(), Sort.by("id").ascending()));
        Page<VendorBranchDetails> page = vendorBranchDetailsRepository.findAll(vendorId, branchSearch.getPage());
        List<VendorBranchDetailsTransaction> vendorBranchDetailsTransactions = vendorBranchDetailsTransactionRepository.findByVendorId(vendorId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vendor-branch-details");
        List<VendorBranchDetailsBean> vendorBranchDetailsBeans = new ArrayList<>();
        Map<Long, VendTypeMaster> vendTypeMasterMap = new HashMap<>();
        Map<Long, VendSubTypeMaster> vendSubTypeMasterMap = new HashMap<>();
        for (VendorBranchDetails vendorBranchDetails : page.getContent()) {
            VendorBranchDetailsBean detailsBean = new VendorBranchDetailsBean();
            boolean exist = false;
            VendorBranchDetailsTransaction vendorBranchDetailsTransaction = null;
            for (VendorBranchDetailsTransaction branchDetailsTransaction : vendorBranchDetailsTransactions) {
                if (branchDetailsTransaction != null && branchDetailsTransaction.getBranchId() != null && branchDetailsTransaction.getBranchId().longValue() == vendorBranchDetails.getId().longValue()) {
                    exist = true;
                    vendorBranchDetailsTransaction = branchDetailsTransaction;
                }
            }
            if (exist == true && vendorBranchDetailsTransaction != null) {
                BeanUtils.copyProperties(vendorBranchDetailsTransaction, detailsBean);
                detailsBean.setId(vendorBranchDetails.getId());
                detailsBean.setDataFlag(vendorBranchDetails.getDataFlag());
            } else {
                BeanUtils.copyProperties(vendorBranchDetails, detailsBean);
            }

            if (detailsBean.getVendTypeMasterId() != null) {
                VendTypeMaster vendTypeMaster = null;
                if (vendTypeMasterMap.containsKey(detailsBean.getVendTypeMasterId())) {
                    vendTypeMaster = vendTypeMasterMap.get(detailsBean.getVendTypeMasterId());
                } else {
                    vendTypeMaster = vendTypeMasterRepository.findById(detailsBean.getVendTypeMasterId()).orElse(null);
                    vendTypeMasterMap.put(detailsBean.getVendTypeMasterId(), vendTypeMaster);
                }
                if(vendTypeMaster != null) {
                    detailsBean.setVendTypeMasterDesc(vendTypeMaster.getDescription());
                }
            }

            if (detailsBean.getVendSubTypeMasterId() != null) {
                VendSubTypeMaster vendSubTypeMaster = null;
                if (vendSubTypeMasterMap.containsKey(detailsBean.getVendSubTypeMasterId())) {
                    vendSubTypeMaster = vendSubTypeMasterMap.get(detailsBean.getVendSubTypeMasterId());
                } else {
                    vendSubTypeMaster = vendSubTypeMasterRepository.findById(detailsBean.getVendSubTypeMasterId()).orElse(null);
                    vendSubTypeMasterMap.put(detailsBean.getVendSubTypeMasterId(), vendSubTypeMaster);
                }
                if(vendSubTypeMaster != null) {
                    detailsBean.setVendSubTypeMasterDesc(vendSubTypeMaster.getDescription());
                }
            }

            vendorBranchDetailsBeans.add(detailsBean);
        }
        return ResponseEntity.ok().headers(headers).body(vendorBranchDetailsBeans);
    }

    /**
     * GET  /vendor-branch-details/:id : get the "id" vendorBranchDetails.
     *
     * @param id the id of the vendorBranchDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorBranchDetails, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-branch-details/{id}")
    @Timed
    public ResponseEntity<VendorBranchDetailsBean> getVendorBranchDetails(@PathVariable Long id) {
        log.debug("REST request to get VendorBranchDetails : {}", id);
        VendorBranchDetailsBean vendorBranchDetailsBean = new VendorBranchDetailsBean();
        VendorBranchDetails vendorBranchDetails = vendorBranchDetailsRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(vendorBranchDetails, vendorBranchDetailsBean);
        return ResponseUtil.wrapOrNotFound(Optional.of(vendorBranchDetailsBean));
    }

    /**
     * DELETE  /vendor-branch-details/:id : delete the "id" vendorBranchDetails.
     *
     * @param id the id of the vendorBranchDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vendor-branch-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendorBranchDetails(@PathVariable Long id) {
        log.debug("REST request to delete VendorBranchDetails : {}", id);

        vendorBranchDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }



    /**
     * GET  /vendor-branch-details/:id : get the "id" vendorBranchDetails.
     *
     * @param id the id of the vendorBranchDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendorBranchDetails, or with status 404 (Not Found)
     */
    @GetMapping("/vendor-branch-details-transaction/{id}")
    @Timed
    public ResponseEntity<VendorBranchDetailsTransactionBean> getVendorBranchDetailsTransaction(@PathVariable Long id) {
        log.debug("REST request to get VendorBranchDetails : {}", id);
        VendorBranchDetailsTransactionBean vendorBranchDetailsTransactionBean = new VendorBranchDetailsTransactionBean();
        VendorBranchDetailsTransaction vendorBranchDetailsTransaction = vendorBranchDetailsTransactionRepository.findByBranchId(id);
        if (vendorBranchDetailsTransaction != null) {
            vendorBranchDetailsTransaction.setId(id);

            BeanUtils.copyProperties(vendorBranchDetailsTransaction, vendorBranchDetailsTransactionBean);
        } else {
            vendorBranchDetailsTransaction = new VendorBranchDetailsTransaction();
            VendorBranchDetails vendorBranchDetails = vendorBranchDetailsRepository.findById(id).orElse(null);

            BeanUtils.copyProperties(vendorBranchDetails, vendorBranchDetailsTransactionBean);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vendorBranchDetailsTransactionBean));
    }
}
