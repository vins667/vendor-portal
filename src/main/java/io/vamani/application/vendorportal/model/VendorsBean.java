package io.vamani.application.vendorportal.model;

import io.vamani.application.vendorportal.domain.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class VendorsBean implements Serializable {
    private Long id;
    private String vendorCode;
    private String vendorName;
    private String vendorShortName;
    private String approvalStatus;
    private String requestedBy;
    private Instant requestedDate;
    private String approvedBy;
    private Instant approvedDate;
    private boolean approved = false;
    private Long payTermMasterId;
    private Long shipmentTermMasterId;
    private Long currencyMasterId;
    private boolean orderAllowed;
    private Long deliveryTermMasterId;

    private String vendorContactStatus;
    private String bankStatus;
    private String branchStatus;
    private String nominationStatus;
    private String addInfoStatus;
    private String peopleStatus;
    private String documentStatus;
    private String termsStatus;

    private VendorContactTaxRegistration vendorContactTaxRegistration;
    private VendorContactTaxRegistration vendorContactTaxRegistrationCompare;
    private VendorBankDetails vendorBankDetails;
    private VendorBankDetailsTransaction vendorBankDetailsCompare;
    private VendorNomination vendorNomination;
    private VendorNominationTransaction vendorNominationTransaction;
    private VendorAdditionalInfo vendorAdditionalInfo;
    private VendorAdditionalInfoTransaction vendorAdditionalInfoCompare;
    private VendorTerms vendorTerms;
    private List<VendorUsers> vendorUsers;
    private List<VendorDocumentBean> vendorDocuments;
    private List<ProfileWorkFlow> profileWorkFlows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorShortName() {
        return vendorShortName;
    }

    public void setVendorShortName(String vendorShortName) {
        this.vendorShortName = vendorShortName;
    }

    public VendorContactTaxRegistration getVendorContactTaxRegistration() {
        return vendorContactTaxRegistration;
    }

    public void setVendorContactTaxRegistration(VendorContactTaxRegistration vendorContactTaxRegistration) {
        this.vendorContactTaxRegistration = vendorContactTaxRegistration;
    }

    public String getVendorContactStatus() {
        return vendorContactStatus;
    }

    public void setVendorContactStatus(String vendorContactStatus) {
        this.vendorContactStatus = vendorContactStatus;
    }

    public String getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(String bankStatus) {
        this.bankStatus = bankStatus;
    }

    public String getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(String branchStatus) {
        this.branchStatus = branchStatus;
    }

    public String getNominationStatus() {
        return nominationStatus;
    }

    public void setNominationStatus(String nominationStatus) {
        this.nominationStatus = nominationStatus;
    }

    public String getAddInfoStatus() {
        return addInfoStatus;
    }

    public void setAddInfoStatus(String addInfoStatus) {
        this.addInfoStatus = addInfoStatus;
    }

    public String getPeopleStatus() {
        return peopleStatus;
    }

    public void setPeopleStatus(String peopleStatus) {
        this.peopleStatus = peopleStatus;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getTermsStatus() {
        return termsStatus;
    }

    public void setTermsStatus(String termsStatus) {
        this.termsStatus = termsStatus;
    }

    public VendorBankDetails getVendorBankDetails() {
        return vendorBankDetails;
    }

    public void setVendorBankDetails(VendorBankDetails vendorBankDetails) {
        this.vendorBankDetails = vendorBankDetails;
    }

    public VendorNomination getVendorNomination() {
        return vendorNomination;
    }

    public void setVendorNomination(VendorNomination vendorNomination) {
        this.vendorNomination = vendorNomination;
    }

    public VendorAdditionalInfo getVendorAdditionalInfo() {
        return vendorAdditionalInfo;
    }

    public void setVendorAdditionalInfo(VendorAdditionalInfo vendorAdditionalInfo) {
        this.vendorAdditionalInfo = vendorAdditionalInfo;
    }

    public List<VendorUsers> getVendorUsers() {
        return vendorUsers;
    }

    public void setVendorUsers(List<VendorUsers> vendorUsers) {
        this.vendorUsers = vendorUsers;
    }

    public List<VendorDocumentBean> getVendorDocuments() {
        return vendorDocuments;
    }

    public void setVendorDocuments(List<VendorDocumentBean> vendorDocuments) {
        this.vendorDocuments = vendorDocuments;
    }

    public VendorTerms getVendorTerms() {
        return vendorTerms;
    }

    public void setVendorTerms(VendorTerms vendorTerms) {
        this.vendorTerms = vendorTerms;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Instant getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Instant requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public List<ProfileWorkFlow> getProfileWorkFlows() {
        return profileWorkFlows;
    }

    public void setProfileWorkFlows(List<ProfileWorkFlow> profileWorkFlows) {
        this.profileWorkFlows = profileWorkFlows;
    }

    public boolean isApproved() { return approved; }

    public boolean getApproved() { return approved; }

    public void setApproved(boolean approved) { this.approved = approved; }

    public VendorContactTaxRegistration getVendorContactTaxRegistrationCompare() {
        return vendorContactTaxRegistrationCompare;
    }

    public void setVendorContactTaxRegistrationCompare(VendorContactTaxRegistration vendorContactTaxRegistrationCompare) {
        this.vendorContactTaxRegistrationCompare = vendorContactTaxRegistrationCompare;
    }

    public VendorBankDetailsTransaction getVendorBankDetailsCompare() {
        return vendorBankDetailsCompare;
    }

    public void setVendorBankDetailsCompare(VendorBankDetailsTransaction vendorBankDetailsCompare) {
        this.vendorBankDetailsCompare = vendorBankDetailsCompare;
    }

    public VendorAdditionalInfoTransaction getVendorAdditionalInfoCompare() {
        return vendorAdditionalInfoCompare;
    }

    public void setVendorAdditionalInfoCompare(VendorAdditionalInfoTransaction vendorAdditionalInfoCompare) {
        this.vendorAdditionalInfoCompare = vendorAdditionalInfoCompare;
    }

    public Long getPayTermMasterId() {
        return payTermMasterId;
    }

    public void setPayTermMasterId(Long payTermMasterId) {
        this.payTermMasterId = payTermMasterId;
    }

    public Long getShipmentTermMasterId() {
        return shipmentTermMasterId;
    }

    public void setShipmentTermMasterId(Long shipmentTermMasterId) {
        this.shipmentTermMasterId = shipmentTermMasterId;
    }

    public Long getCurrencyMasterId() {
        return currencyMasterId;
    }

    public void setCurrencyMasterId(Long currencyMasterId) {
        this.currencyMasterId = currencyMasterId;
    }

    public boolean getOrderAllowed() {
        return orderAllowed;
    }

    public void setOrderAllowed(boolean orderAllowed) {
        this.orderAllowed = orderAllowed;
    }

    public Long getDeliveryTermMasterId() {
        return deliveryTermMasterId;
    }

    public void setDeliveryTermMasterId(Long deliveryTermMasterId) {
        this.deliveryTermMasterId = deliveryTermMasterId;
    }

    public VendorNominationTransaction getVendorNominationTransaction() {
        return vendorNominationTransaction;
    }

    public void setVendorNominationTransaction(VendorNominationTransaction vendorNominationTransaction) {
        this.vendorNominationTransaction = vendorNominationTransaction;
    }
}
