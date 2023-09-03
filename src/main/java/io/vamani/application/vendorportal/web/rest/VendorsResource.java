package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.*;
import io.vamani.application.vendorportal.model.VendorContactTaxRegistration;
import io.vamani.application.vendorportal.model.VendorDocumentBean;
import io.vamani.application.vendorportal.model.VendorsBean;
import io.vamani.application.vendorportal.model.VendorsModel;
import io.vamani.application.vendorportal.repository.*;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * REST controller for managing Vendors.
 */
@RestController
@RequestMapping("/api")
public class VendorsResource {

    private final Logger log = LoggerFactory.getLogger(VendorsResource.class);

    private static final String ENTITY_NAME = "vendors";

    private final VendorsRepository vendorsRepository;

    @Autowired
    private VendorContactRepository vendorContactRepository;

    @Autowired
    private VendorContactTransactionRepository vendorContactTransactionRepository;

    @Autowired
    private VendorBankDetailsRepository vendorBankDetailsRepository;

    @Autowired
    private VendorBankDetailsTransactionRepository vendorBankDetailsTransactionRepository;

    @Autowired
    private VendorBranchDetailsRepository vendorBranchDetailsRepository;

    @Autowired
    private VendorBranchDetailsTransactionRepository vendorBranchDetailsTransactionRepository;

    @Autowired
    private VendorNominationRepository vendorNominationRepository;

    @Autowired
    private VendorAdditionalInfoRepository vendorAdditionalInfoRepository;

    @Autowired
    private VendorAdditionalInfoTransactionRepository vendorAdditionalInfoTransactionRepository;

    @Autowired
    private VendorUsersRepository vendorUsersRepository;

    @Autowired
    private DocumentMasterRepository documentMasterRepository;

    @Autowired
    private VendorDocumentRepository vendorDocumentRepository;

    @Autowired
    private VendorDocumentTrancationRepository vendorDocumentTrancationRepository;

    @Autowired
    private VendorTermsRepository vendorTermsRepository;

    @Autowired
    private ProfileWorkFlowRepository profileWorkFlowRepository;

    public VendorsResource(VendorsRepository vendorsRepository) {
        this.vendorsRepository = vendorsRepository;
    }

    /**
     * POST  /vendors : Create a new vendors.
     *
     * @param vendors the vendors to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vendors, or with status 400 (Bad Request) if the vendors has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vendors")
    @Timed
    public ResponseEntity<Vendors> createVendors(@Valid @RequestBody Vendors vendors) throws URISyntaxException {
        log.debug("REST request to save Vendors : {}", vendors);
        if (vendors.getId() != null) {
            throw new BadRequestAlertException("A new vendors cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vendors result = vendorsRepository.save(vendors);
        return ResponseEntity.created(new URI("/api/vendors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vendors : Updates an existing vendors.
     *
     * @param vendors the vendors to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vendors,
     * or with status 400 (Bad Request) if the vendors is not valid,
     * or with status 500 (Internal Server Error) if the vendors couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vendors")
    @Timed
    public ResponseEntity<VendorsBean> updateVendors(@Valid @RequestBody Vendors vendors) throws URISyntaxException {
        log.debug("REST request to update Vendors : {}", vendors);
        if (vendors.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Vendors result = vendorsRepository.save(vendors);
        Long id = result.getId();

        VendorsBean vendorsBean = new VendorsBean();
        BeanUtils.copyProperties(result, vendorsBean);

        // Vendor Contact
        VendorContactTaxRegistration vendorContactTaxRegistration = null;
        VendorContact vendorContact = vendorContactRepository.findByVendorId(id);
        VendorTaxRegistration vendorTaxRegistration = vendorContactRepository.findByTaxVendorId(id);
        if (vendorContact != null && vendorTaxRegistration != null) {
            vendorsBean.setVendorContactStatus("Y");
            vendorContactTaxRegistration = new VendorContactTaxRegistration();
            vendorContactTaxRegistration.setBranchesPresent(vendorContact.isBranchesPresent());
            vendorContactTaxRegistration.setRegisteredForSaleTax(vendorTaxRegistration.isRegisteredForSaleTax());
            vendorContactTaxRegistration.setRegisteredServiceTax(vendorTaxRegistration.isRegisteredServiceTax());
            vendorContactTaxRegistration.setExciseApplicable(vendorTaxRegistration.isExciseApplicable());
            vendorContactTaxRegistration.setAddressForTax(vendorTaxRegistration.isAddressForTax());
            vendorContactTaxRegistration.setGstApplicable(vendorTaxRegistration.isGstApplicable());
            vendorContactTaxRegistration.setSisterConcernNo(vendorTaxRegistration.isSisterConcernNo());
            BeanUtils.copyProperties(vendorContact, vendorContactTaxRegistration);
            BeanUtils.copyProperties(vendorTaxRegistration, vendorContactTaxRegistration);
        } else {
            vendorsBean.setVendorContactStatus("N");
            vendorContactTaxRegistration = new VendorContactTaxRegistration();
            vendorContactTaxRegistration.setVendorId(id);
        }
        vendorsBean.setVendorContactTaxRegistration(vendorContactTaxRegistration);

        VendorContactTaxRegistration vendorContactTaxRegistrationCompare = null;
        VendorContactTransaction vendorContactTransaction = vendorContactTransactionRepository.findByVendorId(id);
        VendorTaxRegistrationTransaction vendorTaxRegistrationTransaction = vendorContactTransactionRepository.findByTaxVendorId(id);
        if(vendorContactTransaction != null || vendorTaxRegistrationTransaction != null) {
            vendorContactTaxRegistrationCompare = new VendorContactTaxRegistration();
            if(vendorContactTransaction != null) {
                vendorsBean.setVendorContactStatus("O");
                vendorContactTaxRegistrationCompare.setBranchesPresent(vendorContactTransaction.isBranchesPresent());
                BeanUtils.copyProperties(vendorContactTransaction, vendorContactTaxRegistrationCompare);
            } else {
                vendorContactTaxRegistrationCompare.setBranchesPresent(vendorContact.isBranchesPresent());
                BeanUtils.copyProperties(vendorContact, vendorContactTaxRegistrationCompare);
            }

            if (vendorTaxRegistrationTransaction != null) {
                vendorsBean.setVendorContactStatus("O");
                vendorContactTaxRegistrationCompare.setRegisteredForSaleTax(vendorTaxRegistrationTransaction.isRegisteredForSaleTax());
                vendorContactTaxRegistrationCompare.setRegisteredServiceTax(vendorTaxRegistrationTransaction.isRegisteredServiceTax());
                vendorContactTaxRegistrationCompare.setExciseApplicable(vendorTaxRegistrationTransaction.isExciseApplicable());
                vendorContactTaxRegistrationCompare.setAddressForTax(vendorTaxRegistrationTransaction.isAddressForTax());
                vendorContactTaxRegistrationCompare.setGstApplicable(vendorTaxRegistrationTransaction.isGstApplicable());
                vendorContactTaxRegistrationCompare.setSisterConcernNo(vendorTaxRegistrationTransaction.isSisterConcernNo());
                BeanUtils.copyProperties(vendorTaxRegistrationTransaction, vendorContactTaxRegistrationCompare);
            } else {
                vendorContactTaxRegistrationCompare.setRegisteredForSaleTax(vendorTaxRegistration.isRegisteredForSaleTax());
                vendorContactTaxRegistrationCompare.setRegisteredServiceTax(vendorTaxRegistration.isRegisteredServiceTax());
                vendorContactTaxRegistrationCompare.setExciseApplicable(vendorTaxRegistration.isExciseApplicable());
                vendorContactTaxRegistrationCompare.setAddressForTax(vendorTaxRegistration.isAddressForTax());
                vendorContactTaxRegistrationCompare.setGstApplicable(vendorTaxRegistration.isGstApplicable());
                vendorContactTaxRegistrationCompare.setSisterConcernNo(vendorTaxRegistration.isSisterConcernNo());
                BeanUtils.copyProperties(vendorTaxRegistration, vendorContactTaxRegistrationCompare);
            }
            vendorsBean.setVendorContactTaxRegistrationCompare(vendorContactTaxRegistrationCompare);
        }

        // Vendor Bank Details
        VendorBankDetails vendorBankDetails = vendorBankDetailsRepository.findByVendorId(id);
        VendorBankDetailsTransaction vendorBankDetailsTransactionCompare = vendorBankDetailsTransactionRepository.findByVendorId(id);
        if(vendorBankDetailsTransactionCompare != null) {
            vendorsBean.setBankStatus("O");
            vendorsBean.setVendorBankDetailsCompare(vendorBankDetailsTransactionCompare);
        } else {
            if (vendorBankDetails != null) {
                vendorsBean.setBankStatus("Y");
            } else {
                vendorsBean.setBankStatus("N");
                vendorBankDetails = new VendorBankDetails();
                vendorBankDetails.setVendorId(id);
            }
        }
        vendorsBean.setVendorBankDetails(vendorBankDetails);

        if (vendorContactTaxRegistrationCompare != null && vendorContactTaxRegistrationCompare.getBranchesPresent() != null && vendorContactTaxRegistrationCompare.getBranchesPresent() == true) {
            Long branchCountTransaction = vendorBranchDetailsTransactionRepository.countAllByVendorId(id);
            if (branchCountTransaction > 0) {
                vendorsBean.setBranchStatus("O");
            } else {
                vendorsBean.setBranchStatus("N");
            }
        } else if (vendorContactTaxRegistration != null && vendorContactTaxRegistration.getBranchesPresent() != null && vendorContactTaxRegistration.getBranchesPresent() == true) {
            Long branchCountTransaction = vendorBranchDetailsTransactionRepository.countAllByVendorId(id);
            Long branchCount = vendorBranchDetailsRepository.countAllByVendorId(id);
            if (branchCountTransaction > 0) {
                vendorsBean.setBranchStatus("O");
            } else {
                if (branchCount > 0) {
                    vendorsBean.setBranchStatus("Y");
                } else {
                    vendorsBean.setBranchStatus("N");
                }
            }
        } else {
            vendorsBean.setBranchStatus("Y");
        }

        // Vendor Nomination Details
        VendorNomination vendorNomination = vendorNominationRepository.findByVendorId(id);
        if (vendorNomination != null) {
            vendorsBean.setNominationStatus("Y");
        } else {
            vendorsBean.setNominationStatus("N");
            vendorNomination = new VendorNomination();
            vendorNomination.setVendorId(id);
        }
        vendorsBean.setVendorNomination(vendorNomination);

        // Vendor Additional Infor
        VendorAdditionalInfo vendorAdditionalInfo = vendorAdditionalInfoRepository.findByVendorId(id);
        VendorAdditionalInfoTransaction vendorAdditionalInfoTransactionCompare = vendorAdditionalInfoTransactionRepository.findByVendorId(id);
        if (vendorAdditionalInfoTransactionCompare != null) {
            vendorsBean.setAddInfoStatus("O");
            vendorsBean.setVendorAdditionalInfoCompare(vendorAdditionalInfoTransactionCompare);
        } else {
            if (vendorAdditionalInfo != null) {
                vendorsBean.setAddInfoStatus("Y");
            } else {
                vendorAdditionalInfo = new VendorAdditionalInfo();
                vendorAdditionalInfo.setVendorId(id);
                vendorsBean.setAddInfoStatus("N");
            }
        }
        vendorsBean.setVendorAdditionalInfo(vendorAdditionalInfo);

        List<VendorUsers> vendorUsers = vendorUsersRepository.findByVendorId(id);
        if (vendorUsers != null && vendorUsers.size()>0) {
            vendorsBean.setPeopleStatus("Y");
        } else {
            vendorsBean.setPeopleStatus("Y");
        }
        vendorsBean.setVendorUsers(vendorUsers);

        List<VendorDocument> vendorDocumentsTemp = vendorDocumentRepository.findByVendorId(id);
        List<VendorDocumentTrancation> vendorDocumentTrancations = vendorDocumentTrancationRepository.findByVendorId(id);
        List<VendorDocumentBean> vendorDocuments = new ArrayList<>();
        List<Object> objectList = documentMasterRepository.countAllByRequiredField();
        int existCounter = 0;
        if(objectList != null && objectList.size()>0) {
            boolean exist = false;
            for (Object o : objectList) {
                for (VendorDocument vendorDocument : vendorDocumentsTemp) {
                    if (Long.parseLong(o.toString()) == vendorDocument.getDocumentId().longValue() && !(vendorDocument.getDataFlag() != null && vendorDocument.getDataFlag().equalsIgnoreCase("D"))) {
                        exist = true;
                    }
                }
                if(exist == false) {
                    ++existCounter;
                }
            }
            if(existCounter>0) {
                vendorsBean.setDocumentStatus("N");
            } else {
                vendorsBean.setDocumentStatus("Y");
            }
        } else {
            vendorsBean.setDocumentStatus("Y");
        }
        for (VendorDocument vendorDocument : vendorDocumentsTemp) {
            if (vendorDocument.getDataFlag() != null && vendorDocument.getDataFlag().length() > 0) {
                vendorsBean.setDocumentStatus("O");
            }
            VendorDocumentBean vendorDocumentBean = new VendorDocumentBean();
            BeanUtils.copyProperties(vendorDocument, vendorDocumentBean);
            vendorDocuments.add(vendorDocumentBean);
        }
        if (vendorDocumentTrancations != null && vendorDocumentTrancations.size()>0) {
            vendorsBean.setDocumentStatus("O");
            for (VendorDocumentTrancation documentTrancation : vendorDocumentTrancations) {
                boolean exist = false;
                int index = 0;
                int selIndex = 0;
                for (VendorDocumentBean vendorDocumentBean : vendorDocuments) {
                    if (vendorDocumentBean.getDocumentId().longValue() == documentTrancation.getDocumentId().longValue()) {
                        exist = true;
                        selIndex = index;
                    }
                    ++index;
                }
                if (exist == true) {
                    VendorDocumentBean vendorDocumentBean = new VendorDocumentBean();
                    BeanUtils.copyProperties(documentTrancation, vendorDocumentBean);
                    vendorDocumentBean.setTableType("T");
                    vendorDocuments.set(selIndex, vendorDocumentBean);
                } else {
                    VendorDocumentBean vendorDocumentBean = new VendorDocumentBean();
                    BeanUtils.copyProperties(documentTrancation, vendorDocumentBean);
                    vendorDocumentBean.setTableType("T");
                    vendorDocuments.add(vendorDocumentBean);
                }
            }
        }
        vendorsBean.setVendorDocuments(vendorDocuments);

        VendorTerms vendorTerms = vendorTermsRepository.findByVendorId(id);
        if (vendorTerms != null) {
            vendorsBean.setTermsStatus("Y");
        } else {
            vendorTerms = new VendorTerms();
            vendorTerms.setVendorId(id);
            vendorsBean.setTermsStatus("N");
        }
        vendorsBean.setVendorTerms(vendorTerms);

        vendorsBean.setProfileWorkFlows(profileWorkFlowRepository.findAllByVendorId(id));

        return ResponseEntity.ok().body(vendorsBean);
    }

    /**
     * GET  /vendors : get all the vendors.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendors in body
     */
    @GetMapping("/vendors")
    @Timed
    public ResponseEntity<List<Vendors>> getAllVendors(Pageable pageable) {
        log.debug("REST request to get a page of Vendors");
        Page<Vendors> page = vendorsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vendors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vendors : get all the vendors.
     *
     * @param @pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vendors in body
     */
    @PostMapping("/vendors-custom")
    @Timed
    public ResponseEntity<List<Vendors>> getAllVendorsCustom(@Valid @RequestBody VendorsModel search) {
        log.debug("REST request to get a page of Vendors");
        String vendorCode = "%";
        if (search.getVendorCode() != null) {
            vendorCode = search.getVendorCode().toUpperCase() + "%";
        }
        String vendorName = "%";
        if (search.getVendorName() != null) {
            vendorName = search.getVendorName().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").descending()));
        Page<Vendors> page = null;
        if (search.getApprovalStatus() != null && search.getApprovalStatus().equalsIgnoreCase("F")) {
            page = vendorsRepository.findAllByVendorCodeAndVendorNameAndApprovalStatusPending(vendorCode, vendorName, search.getPage());
        } else {
            page = vendorsRepository.findAllByVendorCodeAndVendorNameAndApprovalStatus(vendorCode, vendorName, search.getApprovalStatus(), search.getPage());
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vendors");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vendors/:id : get the "id" vendors.
     *
     * @param id the id of the vendors to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vendors, or with status 404 (Not Found)
     */
    @GetMapping("/vendors/{id}")
    @Timed
    public ResponseEntity<Vendors> getVendors(@PathVariable Long id) {
        log.debug("REST request to get Vendors : {}", id);
        Optional<Vendors> vendors = vendorsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendors);
    }

    /**
     * DELETE  /vendors/:id : delete the "id" vendors.
     *
     * @param id the id of the vendors to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vendors/{id}")
    @Timed
    public ResponseEntity<Void> deleteVendors(@PathVariable Long id) {
        log.debug("REST request to delete Vendors : {}", id);

        vendorsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
