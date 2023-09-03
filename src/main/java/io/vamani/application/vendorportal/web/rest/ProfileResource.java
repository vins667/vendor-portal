package io.vamani.application.vendorportal.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.vendorportal.domain.*;
import io.vamani.application.vendorportal.model.VendorContactTaxRegistration;
import io.vamani.application.vendorportal.model.VendorDocumentBean;
import io.vamani.application.vendorportal.model.VendorsBean;
import io.vamani.application.vendorportal.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing State.
 */
@RestController
@RequestMapping("/api")
public class ProfileResource {
    private final Logger log = LoggerFactory.getLogger(StateResource.class);

    private static final String ENTITY_NAME = "profile";

    @Autowired
    private VendorsRepository vendorsRepository;

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
    private VendorNominationTransactionRepository vendorNominationTransactionRepository;

    @Autowired
    private VendorAdditionalInfoRepository vendorAdditionalInfoRepository;

    @Autowired
    private VendorAdditionalInfoTransactionRepository vendorAdditionalInfoTransactionRepository;

    @Autowired
    private VendorUsersRepository vendorUsersRepository;

    @Autowired
    private VendorUsersTransactionRepository vendorUsersTransactionRepository;

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

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/vendors-profile/{id}")
    @Timed
    public ResponseEntity<VendorsBean> getProfile(@PathVariable Long id) {
        Vendors vendors = vendorsRepository.findById(id).orElse(null);
        VendorsBean vendorsBean = new VendorsBean();
        BeanUtils.copyProperties(vendors, vendorsBean);

        Long countryId = 0L;
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
            countryId = vendorTaxRegistration.getCountryId();
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
                countryId = vendorTaxRegistrationTransaction.getCountryId();
            } else {
                vendorContactTaxRegistrationCompare.setRegisteredForSaleTax(vendorTaxRegistration.isRegisteredForSaleTax());
                vendorContactTaxRegistrationCompare.setRegisteredServiceTax(vendorTaxRegistration.isRegisteredServiceTax());
                vendorContactTaxRegistrationCompare.setExciseApplicable(vendorTaxRegistration.isExciseApplicable());
                vendorContactTaxRegistrationCompare.setAddressForTax(vendorTaxRegistration.isAddressForTax());
                vendorContactTaxRegistrationCompare.setGstApplicable(vendorTaxRegistration.isGstApplicable());
                vendorContactTaxRegistrationCompare.setSisterConcernNo(vendorTaxRegistration.isSisterConcernNo());
                BeanUtils.copyProperties(vendorTaxRegistration, vendorContactTaxRegistrationCompare);
                countryId = vendorTaxRegistration.getCountryId();
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

        Long branchCountTransaction = vendorBranchDetailsTransactionRepository.countAllByVendorId(id);
        if (branchCountTransaction > 0) {
            vendorsBean.setBranchStatus("O");
        } else {
            Long branchCount = vendorBranchDetailsRepository.countAllByVendorId(id);
            if (branchCount > 0) {
                vendorsBean.setBranchStatus("Y");
            } else {
                vendorsBean.setBranchStatus("Y");
            }
        }

        // Vendor Nomination Details
        VendorNominationTransaction vendorNominationTransaction = vendorNominationTransactionRepository.findByVendorId(id);
        VendorNomination vendorNomination = vendorNominationRepository.findByVendorId(id);
        if (vendorNominationTransaction != null) {
            vendorsBean.setNominationStatus("O");
        } else {
            vendorsBean.setNominationStatus("Y");
            if (vendorNomination != null) {
            } else {
                vendorNomination = new VendorNomination();
                vendorNomination.setVendorId(id);
            }
        }
        vendorsBean.setVendorNomination(vendorNomination);
        vendorsBean.setVendorNominationTransaction(vendorNominationTransaction);

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

        List<VendorUsersTransaction> vendorUsersTransactions = vendorUsersTransactionRepository.findAllByVendorId(id);
        List<VendorUsers> vendorUsers = vendorUsersRepository.findByVendorId(id);
        if(vendorUsersTransactions != null && vendorUsersTransactions.size()>0) {
            vendorsBean.setPeopleStatus("O");
            List<VendorUsers> vendorUsersList = new ArrayList<>();
            for(VendorUsers users : vendorUsers) {
                boolean exist = false;
                VendorUsersTransaction transaction = null;
                for(VendorUsersTransaction vendorUsersTransaction : vendorUsersTransactions) {
                    if(users.getId().longValue() == vendorUsersTransaction.getVendorUsersId().longValue()) {
                        exist = true;
                        transaction = vendorUsersTransaction;
                    }
                }
                if(exist == true) {
                    VendorUsers users1 = new VendorUsers();
                    BeanUtils.copyProperties(transaction, users1);
                    users1.setId(users.getId());
                    vendorUsersList.add(users1);
                } else {
                    vendorUsersList.add(users);
                }
            }
            vendorsBean.setVendorUsers(vendorUsersList);
        } else {
            if (vendorUsers != null && vendorUsers.size()>0) {
                vendorsBean.setPeopleStatus("Y");
            } else {
                vendorsBean.setPeopleStatus("Y");
            }
            vendorsBean.setVendorUsers(vendorUsers);
        }

        List<VendorDocument> vendorDocumentsTemp = vendorDocumentRepository.findByVendorId(id);
        List<VendorDocumentTrancation> vendorDocumentTrancations = vendorDocumentTrancationRepository.findByVendorId(id);
        List<VendorDocumentBean> vendorDocuments = new ArrayList<>();
        List<Object> objectList = null;
        if(countryId != null && countryId>0){
            Country country = countryRepository.findById(countryId).orElse(null);
            if(country != null && country.getCountryCode().equalsIgnoreCase("IN")) {
                objectList = documentMasterRepository.countAllByRequiredField("IN");
            } else {
                objectList = documentMasterRepository.countAllByRequiredField("OT");
            }
        }
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
            vendorsBean.setDocumentStatus("N");
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
}
