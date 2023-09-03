package io.vamani.application.vendorportal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.vendorportal.domain.*;
import io.vamani.application.domain.User;
import io.vamani.application.vendorportal.model.ProfileWorkFlowBean;
import io.vamani.application.vendorportal.repository.*;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing ProfileWorkFlow.
 */
@RestController
@RequestMapping("/api")
public class ProfileWorkFlowResource {

    private final Logger log = LoggerFactory.getLogger(ProfileWorkFlowResource.class);

    private static final String ENTITY_NAME = "profileWorkFlow";

    private final ProfileWorkFlowRepository profileWorkFlowRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VendorsRepository vendorsRepository;

    @Autowired
    private UserVendorRepository userVendorRepository;

    @Autowired
    private VendorContactRepository vendorContactRepository;

    @Autowired
    private VendorContactTransactionRepository vendorContactTransactionRepository;

    @Autowired
    private VendorTaxRegistrationRepository vendorTaxRegistrationRepository;

    @Autowired
    private VendorTaxRegistrationTransactionRepository vendorTaxRegistrationTransactionRepository;

    @Autowired
    private VendorBankDetailsRepository vendorBankDetailsRepository;

    @Autowired
    private VendorBankDetailsTransactionRepository vendorBankDetailsTransactionRepository;

    @Autowired
    private VendorNominationRepository vendorNominationRepository;

    @Autowired
    private VendorNominationBuyerMasterRepository vendorNominationBuyerMasterRepository;

    @Autowired
    private VendorNominationTransactionRepository vendorNominationTransactionRepository;

    @Autowired
    private VendorAdditionalInfoRepository vendorAdditionalInfoRepository;

    @Autowired
    private VendorAdditionalInfoTransactionRepository vendorAdditionalInfoTransactionRepository;

    @Autowired
    private VendorBranchDetailsRepository vendorBranchDetailsRepository;

    @Autowired
    private VendorBranchDetailsTransactionRepository vendorBranchDetailsTransactionRepository;

    @Autowired
    private VendorDocumentRepository vendorDocumentRepository;

    @Autowired
    private VendorDocumentTrancationRepository vendorDocumentTrancationRepository;

    @Autowired
    private VendorUsersRepository vendorUsersRepository;

    @Autowired
    private VendorUsersTransactionRepository vendorUsersTransactionRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public ProfileWorkFlowResource(ProfileWorkFlowRepository profileWorkFlowRepository) {
        this.profileWorkFlowRepository = profileWorkFlowRepository;
    }

    /**
     * POST  /profile-work-flows : Create a new profileWorkFlow.
     *
     * @param profileWorkFlow the profileWorkFlow to create
     * @return the ResponseEntity with status 201 (Created) and with body the new profileWorkFlow, or with status 400 (Bad Request) if the profileWorkFlow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profile-work-flows")
    public ResponseEntity<ProfileWorkFlow> createProfileWorkFlow(@Valid @RequestBody ProfileWorkFlowBean profileWorkFlowBean) throws URISyntaxException {
        log.debug("REST request to save ProfileWorkFlow : {}", profileWorkFlowBean);
        if (profileWorkFlowBean.getId() != null) {
            throw new BadRequestAlertException("A new profileWorkFlow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfileWorkFlow profileWorkFlow = new ProfileWorkFlow();
        BeanUtils.copyProperties(profileWorkFlowBean, profileWorkFlow);
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        profileWorkFlow.setUserCode(user.getLogin());
        profileWorkFlow.setUserName(user.getFirstName() + (user.getLastName() != null ? " " + user.getLastName() : ""));
        profileWorkFlow.setCreatedBy(user.getLogin());
        profileWorkFlow.setCreatedDate(Instant.now());
        ProfileWorkFlow result = profileWorkFlowRepository.save(profileWorkFlow);
        Long vendorId = result.getVendorId();
        if (profileWorkFlow.getForwardFlag() != null && profileWorkFlow.getForwardFlag().equalsIgnoreCase("A")) {
            Vendors vendors = vendorsRepository.findById(result.getVendorId()).orElse(null);
            vendors.setVendorCode(profileWorkFlowBean.getVendorCode());
            vendors.setVendorShortName(profileWorkFlowBean.getVendorShortName());
            vendors.setDeliveryTermMasterId(profileWorkFlowBean.getDeliveryTermMasterId());
            vendors.setPayTermMasterId(profileWorkFlowBean.getPayTermMasterId());
            vendors.setShipmentTermMasterId(profileWorkFlowBean.getShipmentTermMasterId());
            vendors.setCurrencyMasterId(profileWorkFlowBean.getCurrencyMasterId());
            vendors.setOrderAllowed(profileWorkFlowBean.getOrderAllowed());
            vendors.setApprovalStatus(result.getForwardFlag());
            vendors.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            vendors.setApprovedDate(Instant.now());
            vendors.setApproved(true);
            Vendors result1 = vendorsRepository.save(vendors);
            boolean prevUser = false;
            if (result1 != null && result1.getApprovalStatus().equalsIgnoreCase("A")) {
                List<UserVendor> userVendors = userVendorRepository.findAllByVendorId(result.getVendorId());
                if (userVendors != null && userVendors.size() > 0) {
                    for (UserVendor userVendor : userVendors) {
                        if (userVendor.getApproved() == true) {
                            prevUser = true;
                        }
                        userVendor.setApproved(true);
                        userVendorRepository.save(userVendor);
                    }
                }
            }
            if (vendorId != null && vendorId.longValue() > 0 && prevUser == true) {
                // Vendor Contact Update push
                VendorContactTransaction vendorContactTransaction = vendorContactTransactionRepository.findByVendorId(vendorId);
                VendorContact vendorContact = vendorContactRepository.findByVendorId(vendorId);
                if (vendorContactTransaction != null) {
                    VendorContact vendorContactTemp = new VendorContact();
                    BeanUtils.copyProperties(vendorContact, vendorContactTemp);
                    BeanUtils.copyProperties(vendorContactTransaction, vendorContact, "id", "createdBy", "createdDate");
                    vendorContact.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorContact.setLastUpdatedDate(Instant.now());
                    vendorContactRepository.save(vendorContact);

                    vendorContactTransaction.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorContactTransaction.setLastUpdatedDate(Instant.now());
                    vendorContactTransaction.setApprovedDate(Instant.now());
                    vendorContactTransactionRepository.save(vendorContactTransaction);
                }

                // Vendor Tax Update push
                VendorTaxRegistrationTransaction vendorTaxRegistrationTransaction = vendorContactTransactionRepository.findByTaxVendorId(vendorId);
                VendorTaxRegistration vendorTaxRegistration = vendorContactRepository.findByTaxVendorId(vendorId);
                if (vendorTaxRegistrationTransaction != null) {
                    VendorContact vendorTaxRegistrationTemp = new VendorContact();
                    BeanUtils.copyProperties(vendorTaxRegistration, vendorTaxRegistrationTemp);
                    BeanUtils.copyProperties(vendorTaxRegistrationTransaction, vendorTaxRegistration, "id", "createdBy", "createdDate");
                    vendorTaxRegistration.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorTaxRegistration.setLastUpdatedDate(Instant.now());
                    vendorTaxRegistrationRepository.save(vendorTaxRegistration);

                    vendorTaxRegistrationTransaction.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorTaxRegistrationTransaction.setLastUpdatedDate(Instant.now());
                    vendorTaxRegistrationTransaction.setApprovedDate(Instant.now());
                    vendorTaxRegistrationTransactionRepository.save(vendorTaxRegistrationTransaction);
                }

                // Vendor Bank Update push
                VendorBankDetailsTransaction vendorBankDetailsTransaction = vendorBankDetailsTransactionRepository.findByVendorId(vendorId);
                VendorBankDetails vendorBankDetails = vendorBankDetailsRepository.findByVendorId(vendorId);
                if (vendorBankDetailsTransaction != null) {
                    VendorBankDetails vendorBankDetailsTemp = new VendorBankDetails();
                    BeanUtils.copyProperties(vendorBankDetails, vendorBankDetailsTemp);
                    BeanUtils.copyProperties(vendorBankDetailsTransaction, vendorBankDetails, "id", "createdBy", "createdDate");
                    vendorBankDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorBankDetails.setLastUpdatedDate(Instant.now());
                    vendorBankDetailsRepository.save(vendorBankDetails);

                    vendorBankDetailsTransaction.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorBankDetailsTransaction.setLastUpdatedDate(Instant.now());
                    vendorBankDetailsTransaction.setApprovedDate(Instant.now());
                    vendorBankDetailsTransactionRepository.save(vendorBankDetailsTransaction);
                }

                // Vendor Branch Update push
                List<VendorBranchDetailsTransaction> vendorBranchDetailsTransactions = vendorBranchDetailsTransactionRepository.findByVendorId(vendorId);
                List<VendorBranchDetails> vendorBranchDetails = vendorBranchDetailsRepository.findByVendorId(vendorId);
                Set<Long> vendorBranchDetailsDeleted = new HashSet<>();
                if (vendorBranchDetailsTransactions != null) {
                    for (VendorBranchDetailsTransaction vendorBranchDetailsTransaction : vendorBranchDetailsTransactions) {
                        for (VendorBranchDetails branchDetails : vendorBranchDetails) {
                            if (branchDetails.getDataFlag() != null && branchDetails.getDataFlag().equalsIgnoreCase("D")) {
                                vendorBranchDetailsDeleted.add(branchDetails.getId());
                            } else if (vendorBranchDetailsTransaction.getBranchId() != null && vendorBranchDetailsTransaction.getBranchId().longValue() == branchDetails.getId().longValue()) {
                                VendorBranchDetails vendorBranchDetailsTemp = new VendorBranchDetails();
                                BeanUtils.copyProperties(branchDetails, vendorBranchDetailsTemp);
                                BeanUtils.copyProperties(vendorBranchDetailsTransaction, branchDetails, "id", "createdBy", "createdDate");
                                branchDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                branchDetails.setLastUpdatedDate(Instant.now());
                                branchDetails.setDataFlag(null);
                                vendorBranchDetailsRepository.save(branchDetails);

                                vendorBranchDetailsTransaction.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                vendorBranchDetailsTransaction.setLastUpdatedDate(Instant.now());
                                vendorBranchDetailsTransaction.setApprovedDate(Instant.now());
                                vendorBranchDetailsTransactionRepository.save(vendorBranchDetailsTransaction);
                            }
                        }
                    }
                    if (vendorBranchDetailsDeleted != null && vendorBranchDetailsDeleted.size() > 0) {
                        for (Long id : vendorBranchDetailsDeleted) {
                            vendorBranchDetailsRepository.deleteById(id);
                        }
                    }
                    for (VendorBranchDetailsTransaction vendorBranchDetailsTransaction : vendorBranchDetailsTransactions) {
                        if (vendorBranchDetailsTransaction.getDataFlag() != null && vendorBranchDetailsTransaction.getDataFlag().equalsIgnoreCase("D")) {
                            vendorBranchDetailsTransaction.setApprovedDate(Instant.now());
                            vendorBranchDetailsTransactionRepository.save(vendorBranchDetailsTransaction);
                        }
                    }
                }

                // Vendor Nomination
                VendorNominationTransaction vendorNominationTransaction = vendorNominationTransactionRepository.findByVendorId(vendorId);
                VendorNomination vendorNomination = vendorNominationRepository.findByVendorId(vendorId);
                if(vendorNominationTransaction != null) {
                    if(vendorNomination != null) {
                        vendorNomination.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        vendorNomination.setLastUpdatedDate(Instant.now());
                        VendorNomination nomination = vendorNominationRepository.save(vendorNomination);
                        vendorNominationBuyerMasterRepository.deleteAllByVendorNominationBuyerMaster(nomination.getId());
                        for(BuyerMaster buyerMaster : vendorNominationTransaction.getBuyerMasters()) {
                            vendorNominationBuyerMasterRepository.save(new VendorNominationBuyerMaster(new VendorNominationBuyerMasterId(buyerMaster.getId(), nomination.getId())));
                        }
                    } else {
                        vendorNomination = new VendorNomination();
                        vendorNomination.setVendorId(vendorId);
                        vendorNomination.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        vendorNomination.setCreatedDate(Instant.now());
                        VendorNomination nomination = vendorNominationRepository.save(vendorNomination);
                        for(BuyerMaster buyerMaster : vendorNominationTransaction.getBuyerMasters()) {
                            vendorNominationBuyerMasterRepository.save(new VendorNominationBuyerMaster(new VendorNominationBuyerMasterId(buyerMaster.getId(), nomination.getId())));
                        }
                    }
                    vendorNominationTransaction.setApprovedDate(Instant.now());
                    vendorNominationTransactionRepository.save(vendorNominationTransaction);
                }

                // Vendor Additional Update push
                VendorAdditionalInfoTransaction vendorAdditionalInfoTransaction = vendorAdditionalInfoTransactionRepository.findByVendorId(vendorId);
                VendorAdditionalInfo vendorAdditionalInfo = vendorAdditionalInfoRepository.findByVendorId(vendorId);
                if (vendorAdditionalInfoTransaction != null) {
                    VendorAdditionalInfo vendorAdditionalInfoTemp = new VendorAdditionalInfo();
                    BeanUtils.copyProperties(vendorAdditionalInfo, vendorAdditionalInfoTemp);
                    BeanUtils.copyProperties(vendorAdditionalInfoTransaction, vendorAdditionalInfo, "id", "createdBy", "createdDate");
                    vendorAdditionalInfo.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorAdditionalInfo.setLastUpdatedDate(Instant.now());
                    vendorAdditionalInfoRepository.save(vendorAdditionalInfo);

                    vendorAdditionalInfoTransaction.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    vendorAdditionalInfoTransaction.setLastUpdatedDate(Instant.now());
                    vendorAdditionalInfoTransaction.setApprovedDate(Instant.now());
                    vendorAdditionalInfoTransactionRepository.save(vendorAdditionalInfoTransaction);
                }

                // Vendor Contact User
                List<VendorUsersTransaction> vendorUsersTransactions = vendorUsersTransactionRepository.findAllByVendorId(vendorId);
                List<VendorUsers> vendorUsers = vendorUsersRepository.findByVendorId(vendorId);
                if (vendorUsersTransactions != null) {
                    Set<Long> vendorUserDetailsDeleted = new HashSet<>();
                    for (VendorUsersTransaction usersTransaction : vendorUsersTransactions) {
                        for (VendorUsers users : vendorUsers) {
                            if (usersTransaction.getDataFlag() != null && usersTransaction.getDataFlag().equalsIgnoreCase("D") && users.getId().longValue() == usersTransaction.getVendorUsersId().longValue()) {
                                vendorUserDetailsDeleted.add(users.getId());
                            } else if (users.getId().longValue() == usersTransaction.getVendorUsersId().longValue()) {
                                BeanUtils.copyProperties(usersTransaction, users, "id", "createdBy", "createdDate");
                                users.setDataFlag(null);
                                users.setVendorId(vendorId);
                                users.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                users.setLastUpdatedDate(Instant.now());
                                VendorUsers users1 = vendorUsersRepository.save(users);
                                if (users1 != null) {
                                    usersTransaction.setApprovedDate(Instant.now());
                                    vendorUsersTransactionRepository.save(usersTransaction);
                                }
                            }
                        }
                    }
                    if(vendorUserDetailsDeleted !=  null) {
                        for(Long id : vendorUserDetailsDeleted) {
                            vendorUsersRepository.deleteById(id);
                        }
                        for (VendorUsersTransaction usersTransaction : vendorUsersTransactions) {
                            if (usersTransaction.getDataFlag() != null && usersTransaction.getDataFlag().equalsIgnoreCase("D")) {
                                usersTransaction.setApprovedDate(Instant.now());
                                vendorUsersTransactionRepository.save(usersTransaction);
                            }
                        }
                    }
                }

                // Vendor Document Update push
                String UPLOADED_FOLDER = applicationProperties.getUploadPath();
                List<VendorDocumentTrancation> vendorDocumentTrancations = vendorDocumentTrancationRepository.findByVendorId(vendorId);
                List<VendorDocument> vendorDocuments = vendorDocumentRepository.findByVendorId(vendorId);
                Set<Long> vendorDocumentsDeleted = new HashSet<>();
                if (vendorDocumentTrancations != null) {
                    for (VendorDocumentTrancation vendorDocumentTrancation : vendorDocumentTrancations) {
                        for (VendorDocument vendorDocument : vendorDocuments) {
                            if (vendorDocument.getDataFlag() != null && vendorDocument.getDataFlag().equalsIgnoreCase("D")) {
                                vendorDocumentsDeleted.add(vendorDocument.getId());
                            } else if (vendorDocumentTrancation.getDocumentId() != null && vendorDocumentTrancation.getDocumentId().longValue() == vendorDocument.getDocumentId().longValue()) {
                                vendorDocument.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                vendorDocument.setLastUpdatedDate(Instant.now());
                                vendorDocument.setDataFlag(null);

                                String newFile = null;

                                // Backup File
                                try {
                                    newFile = "Approved_" + vendorDocument.getDocumentFilePath();
                                    Path source = Paths.get(UPLOADED_FOLDER + "vendor-documents/" + vendorDocument.getDocumentFilePath());
                                    Path newDir = Paths.get(UPLOADED_FOLDER + "vendor-documents/");
                                    Files.move(source, newDir.resolve("Approved_" + vendorDocument.getDocumentFilePath()));
                                } catch (Exception e) {
                                }

                                // Transaction to Main Replace
                                try {
                                    Path source = Paths.get(UPLOADED_FOLDER + "vendor-documents/delete/" + vendorDocumentTrancation.getDocumentFilePath());
                                    Path newDir = Paths.get(UPLOADED_FOLDER + "vendor-documents/");
                                    String ext = vendorDocumentTrancation.getDocumentFilePath().substring(vendorDocumentTrancation.getDocumentFilePath().lastIndexOf("."), vendorDocumentTrancation.getDocumentFilePath().length());
                                    Files.move(source, newDir.resolve(vendorDocument.getId().longValue() + ext));
                                    vendorDocument.setDocumentFilePath(vendorDocument.getId().longValue() + ext);
                                    vendorDocumentRepository.save(vendorDocument);
                                } catch (Exception e) {
                                }

                                // Backup to Transaction
                                try {
                                    Path source = Paths.get(UPLOADED_FOLDER + "vendor-documents/" + vendorDocument.getDocumentFilePath());
                                    Path newDir = Paths.get(UPLOADED_FOLDER + "vendor-documents/delete/");
                                    Files.move(source, newDir.resolve(newFile));
                                    vendorDocumentTrancation.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                    vendorDocumentTrancation.setLastUpdatedDate(Instant.now());
                                    vendorDocumentTrancation.setApprovedDate(Instant.now());
                                    vendorDocumentTrancationRepository.save(vendorDocumentTrancation);
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                    if (vendorDocumentsDeleted != null && vendorDocumentsDeleted.size() > 0) {
                        for (Long id : vendorDocumentsDeleted) {
                            vendorDocumentRepository.deleteById(id);
                        }
                    }


                    for (VendorDocumentTrancation vendorDocumentTrancation : vendorDocumentTrancations) {
                        if (vendorDocumentTrancation.getDataFlag() != null && vendorDocumentTrancation.getDataFlag().equalsIgnoreCase("N")) {
                            VendorDocument vendorDocument = new VendorDocument();
                            BeanUtils.copyProperties(vendorDocumentTrancation, vendorDocument, "id", "createdBy", "createdDate");
                            vendorDocument.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                            vendorDocument.setCreatedDate(Instant.now());
                            vendorDocument.setDataFlag(null);
                            vendorDocument.setId(null);
                            VendorDocument vendorDocumentResult = vendorDocumentRepository.save(vendorDocument);
                            if (vendorDocumentResult != null) {
                                // Transaction to Main Replace
                                try {
                                    Path source = Paths.get(UPLOADED_FOLDER + "vendor-documents/delete/" + vendorDocumentTrancation.getDocumentFilePath());
                                    Path newDir = Paths.get(UPLOADED_FOLDER + "vendor-documents/");
                                    String ext = vendorDocumentTrancation.getDocumentFilePath().substring(vendorDocumentTrancation.getDocumentFilePath().lastIndexOf("."), vendorDocumentTrancation.getDocumentFilePath().length());
                                    Files.copy(source, newDir.resolve(vendorDocumentResult.getId().longValue() + ext));
                                    vendorDocumentResult.setDocumentFilePath(vendorDocumentResult.getId().longValue() + ext);
                                    vendorDocumentRepository.save(vendorDocumentResult);
                                } catch (Exception e) {
                                }
                                vendorDocumentTrancation.setApprovedDate(Instant.now());
                                vendorDocumentTrancationRepository.save(vendorDocumentTrancation);
                            }
                        } else if (vendorDocumentTrancation.getDataFlag() != null && vendorDocumentTrancation.getDataFlag().equalsIgnoreCase("D")) {
                            vendorDocumentTrancation.setApprovedDate(Instant.now());
                            vendorDocumentTrancationRepository.save(vendorDocumentTrancation);
                        }
                    }
                }
            }
        } else {
            Vendors vendors = vendorsRepository.findById(result.getVendorId()).orElse(null);
            vendors.setApprovalStatus(result.getForwardFlag());
            vendorsRepository.save(vendors);
        }

        return ResponseEntity.created(new URI("/api/profile-work-flows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profile-work-flows : Updates an existing profileWorkFlow.
     *
     * @param profileWorkFlow the profileWorkFlow to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated profileWorkFlow,
     * or with status 400 (Bad Request) if the profileWorkFlow is not valid,
     * or with status 500 (Internal Server Error) if the profileWorkFlow couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profile-work-flows")
    public ResponseEntity<ProfileWorkFlow> updateProfileWorkFlow(@Valid @RequestBody ProfileWorkFlow profileWorkFlow) throws URISyntaxException {
        log.debug("REST request to update ProfileWorkFlow : {}", profileWorkFlow);
        if (profileWorkFlow.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfileWorkFlow result = profileWorkFlowRepository.save(profileWorkFlow);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profileWorkFlow.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profile-work-flows : get all the profileWorkFlows.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of profileWorkFlows in body
     */
    @GetMapping("/profile-work-flows")
    public ResponseEntity<List<ProfileWorkFlow>> getAllProfileWorkFlows(Pageable pageable) {
        log.debug("REST request to get a page of ProfileWorkFlows");
        Page<ProfileWorkFlow> page = profileWorkFlowRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profile-work-flows");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /profile-work-flows/:id : get the "id" profileWorkFlow.
     *
     * @param id the id of the profileWorkFlow to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the profileWorkFlow, or with status 404 (Not Found)
     */
    @GetMapping("/profile-work-flows/{id}")
    public ResponseEntity<ProfileWorkFlow> getProfileWorkFlow(@PathVariable Long id) {
        log.debug("REST request to get ProfileWorkFlow : {}", id);
        Optional<ProfileWorkFlow> profileWorkFlow = profileWorkFlowRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(profileWorkFlow);
    }

    /**
     * DELETE  /profile-work-flows/:id : delete the "id" profileWorkFlow.
     *
     * @param id the id of the profileWorkFlow to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profile-work-flows/{id}")
    public ResponseEntity<Void> deleteProfileWorkFlow(@PathVariable Long id) {
        log.debug("REST request to delete ProfileWorkFlow : {}", id);
        profileWorkFlowRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
