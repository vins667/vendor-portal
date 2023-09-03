package io.vamani.application.vendorportal.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class VendorBranchDetailsTransactionBean implements Serializable {
    private Long id;

    private Long vendorId;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String addressLine4;

    private Long countryId;

    private Long stateId;

    private Long pincode;

    private Boolean addressForTax;

    private Boolean registeredForSaleTax;

    private String salesTaxVatTinNumber;

    private Boolean registeredServiceTax;

    private String serviceTaxNumber;

    private Boolean exciseApplicable;

    private String exciseRegistrationNumber;

    private String exciseRange;

    private String exciseDivision;

    private String exciseAddressLine1;

    private String exciseAddressLine2;

    private String divisionAddressLine1;

    private String divisionAddressLine2;

    private String gstNumber;

    private String gstNumber1;

    private String gstNumber2;

    private String gstNumber3;

    private String gstNumber4;

    private String gstNumber5;

    private Boolean gstApplicable;

    private Long vendTypeMasterId;

    private Long vendSubTypeMasterId;

    private String addressId;

    private String branchContactNo;

    private String branchFaxNo;

    private String branchWebsite;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private Instant approvedDate;

    private Boolean ignoreHistory;

    private Long branchId;

    private String dataFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Boolean getAddressForTax() {
        return addressForTax;
    }

    public void setAddressForTax(Boolean addressForTax) {
        this.addressForTax = addressForTax;
    }

    public Boolean getRegisteredForSaleTax() {
        return registeredForSaleTax;
    }

    public void setRegisteredForSaleTax(Boolean registeredForSaleTax) {
        this.registeredForSaleTax = registeredForSaleTax;
    }

    public String getSalesTaxVatTinNumber() {
        return salesTaxVatTinNumber;
    }

    public void setSalesTaxVatTinNumber(String salesTaxVatTinNumber) {
        this.salesTaxVatTinNumber = salesTaxVatTinNumber;
    }

    public Boolean getRegisteredServiceTax() {
        return registeredServiceTax;
    }

    public void setRegisteredServiceTax(Boolean registeredServiceTax) {
        this.registeredServiceTax = registeredServiceTax;
    }

    public String getServiceTaxNumber() {
        return serviceTaxNumber;
    }

    public void setServiceTaxNumber(String serviceTaxNumber) {
        this.serviceTaxNumber = serviceTaxNumber;
    }

    public Boolean getExciseApplicable() {
        return exciseApplicable;
    }

    public void setExciseApplicable(Boolean exciseApplicable) {
        this.exciseApplicable = exciseApplicable;
    }

    public String getExciseRegistrationNumber() {
        return exciseRegistrationNumber;
    }

    public void setExciseRegistrationNumber(String exciseRegistrationNumber) {
        this.exciseRegistrationNumber = exciseRegistrationNumber;
    }

    public String getExciseRange() {
        return exciseRange;
    }

    public void setExciseRange(String exciseRange) {
        this.exciseRange = exciseRange;
    }

    public String getExciseDivision() {
        return exciseDivision;
    }

    public void setExciseDivision(String exciseDivision) {
        this.exciseDivision = exciseDivision;
    }

    public String getExciseAddressLine1() {
        return exciseAddressLine1;
    }

    public void setExciseAddressLine1(String exciseAddressLine1) {
        this.exciseAddressLine1 = exciseAddressLine1;
    }

    public String getExciseAddressLine2() {
        return exciseAddressLine2;
    }

    public void setExciseAddressLine2(String exciseAddressLine2) {
        this.exciseAddressLine2 = exciseAddressLine2;
    }

    public String getDivisionAddressLine1() {
        return divisionAddressLine1;
    }

    public void setDivisionAddressLine1(String divisionAddressLine1) {
        this.divisionAddressLine1 = divisionAddressLine1;
    }

    public String getDivisionAddressLine2() {
        return divisionAddressLine2;
    }

    public void setDivisionAddressLine2(String divisionAddressLine2) {
        this.divisionAddressLine2 = divisionAddressLine2;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public Boolean getGstApplicable() {
        return gstApplicable;
    }

    public void setGstApplicable(Boolean gstApplicable) {
        this.gstApplicable = gstApplicable;
    }

    public Long getVendTypeMasterId() {
        return vendTypeMasterId;
    }

    public void setVendTypeMasterId(Long vendTypeMasterId) {
        this.vendTypeMasterId = vendTypeMasterId;
    }

    public Long getVendSubTypeMasterId() {
        return vendSubTypeMasterId;
    }

    public void setVendSubTypeMasterId(Long vendSubTypeMasterId) {
        this.vendSubTypeMasterId = vendSubTypeMasterId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getBranchContactNo() {
        return branchContactNo;
    }

    public void setBranchContactNo(String branchContactNo) {
        this.branchContactNo = branchContactNo;
    }

    public String getBranchFaxNo() {
        return branchFaxNo;
    }

    public void setBranchFaxNo(String branchFaxNo) {
        this.branchFaxNo = branchFaxNo;
    }

    public String getBranchWebsite() {
        return branchWebsite;
    }

    public void setBranchWebsite(String branchWebsite) {
        this.branchWebsite = branchWebsite;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Boolean getIgnoreHistory() {
        return ignoreHistory;
    }

    public void setIgnoreHistory(Boolean ignoreHistory) {
        this.ignoreHistory = ignoreHistory;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }

    public String getGstNumber1() {
        if (gstNumber != null && gstNumber.length()>0) {
            gstNumber1 = gstNumber.split(" ")[0];
        }
        return gstNumber1;
    }

    public void setGstNumber1(String gstNumber1) {
        this.gstNumber1 = gstNumber1;
    }

    public String getGstNumber2() {
        if (gstNumber != null && gstNumber.length()>0) {
            gstNumber2 = gstNumber.split(" ")[1];
        }
        return gstNumber2;
    }

    public void setGstNumber2(String gstNumber2) {
        this.gstNumber2 = gstNumber2;
    }

    public String getGstNumber3() {
        if (gstNumber != null && gstNumber.length()>0) {
            gstNumber3 = gstNumber.split(" ")[2];
        }
        return gstNumber3;
    }

    public void setGstNumber3(String gstNumber3) {
        this.gstNumber3 = gstNumber3;
    }

    public String getGstNumber4() {
        if (gstNumber != null && gstNumber.length()>0) {
            gstNumber4 = gstNumber.split(" ")[3];
        }
        return gstNumber4;
    }

    public void setGstNumber4(String gstNumber4) {
        this.gstNumber4 = gstNumber4;
    }

    public String getGstNumber5() {
        if (gstNumber != null && gstNumber.length()>0) {
            gstNumber5 = gstNumber.split(" ")[4];
        }
        return gstNumber5;
    }

    public void setGstNumber5(String gstNumber5) {
        this.gstNumber5 = gstNumber5;
    }

    @Override
    public String toString() {
        return "VendorBranchDetailsTransactionBean{" +
            "id=" + id +
            ", vendorId=" + vendorId +
            ", addressLine1='" + addressLine1 + '\'' +
            ", addressLine2='" + addressLine2 + '\'' +
            ", addressLine3='" + addressLine3 + '\'' +
            ", addressLine4='" + addressLine4 + '\'' +
            ", countryId=" + countryId +
            ", stateId=" + stateId +
            ", pincode=" + pincode +
            ", addressForTax=" + addressForTax +
            ", registeredForSaleTax=" + registeredForSaleTax +
            ", salesTaxVatTinNumber='" + salesTaxVatTinNumber + '\'' +
            ", registeredServiceTax=" + registeredServiceTax +
            ", serviceTaxNumber='" + serviceTaxNumber + '\'' +
            ", exciseApplicable=" + exciseApplicable +
            ", exciseRegistrationNumber='" + exciseRegistrationNumber + '\'' +
            ", exciseRange='" + exciseRange + '\'' +
            ", exciseDivision='" + exciseDivision + '\'' +
            ", exciseAddressLine1='" + exciseAddressLine1 + '\'' +
            ", exciseAddressLine2='" + exciseAddressLine2 + '\'' +
            ", divisionAddressLine1='" + divisionAddressLine1 + '\'' +
            ", divisionAddressLine2='" + divisionAddressLine2 + '\'' +
            ", gstNumber='" + gstNumber + '\'' +
            ", gstApplicable=" + gstApplicable +
            ", vendTypeMasterId=" + vendTypeMasterId +
            ", vendSubTypeMasterId=" + vendSubTypeMasterId +
            ", addressId='" + addressId + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", approvedDate=" + approvedDate +
            ", ignoreHistory=" + ignoreHistory +
            ", branchId=" + branchId +
            ", dataFlag='" + dataFlag + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorBranchDetailsTransactionBean that = (VendorBranchDetailsTransactionBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(vendorId, that.vendorId) &&
            Objects.equals(addressLine1, that.addressLine1) &&
            Objects.equals(addressLine2, that.addressLine2) &&
            Objects.equals(addressLine3, that.addressLine3) &&
            Objects.equals(addressLine4, that.addressLine4) &&
            Objects.equals(countryId, that.countryId) &&
            Objects.equals(stateId, that.stateId) &&
            Objects.equals(pincode, that.pincode) &&
            Objects.equals(addressForTax, that.addressForTax) &&
            Objects.equals(registeredForSaleTax, that.registeredForSaleTax) &&
            Objects.equals(salesTaxVatTinNumber, that.salesTaxVatTinNumber) &&
            Objects.equals(registeredServiceTax, that.registeredServiceTax) &&
            Objects.equals(serviceTaxNumber, that.serviceTaxNumber) &&
            Objects.equals(exciseApplicable, that.exciseApplicable) &&
            Objects.equals(exciseRegistrationNumber, that.exciseRegistrationNumber) &&
            Objects.equals(exciseRange, that.exciseRange) &&
            Objects.equals(exciseDivision, that.exciseDivision) &&
            Objects.equals(exciseAddressLine1, that.exciseAddressLine1) &&
            Objects.equals(exciseAddressLine2, that.exciseAddressLine2) &&
            Objects.equals(divisionAddressLine1, that.divisionAddressLine1) &&
            Objects.equals(divisionAddressLine2, that.divisionAddressLine2) &&
            Objects.equals(gstNumber, that.gstNumber) &&
            Objects.equals(gstApplicable, that.gstApplicable) &&
            Objects.equals(vendTypeMasterId, that.vendTypeMasterId) &&
            Objects.equals(vendSubTypeMasterId, that.vendSubTypeMasterId) &&
            Objects.equals(addressId, that.addressId) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(approvedDate, that.approvedDate) &&
            Objects.equals(ignoreHistory, that.ignoreHistory) &&
            Objects.equals(branchId, that.branchId) &&
            Objects.equals(dataFlag, that.dataFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendorId, addressLine1, addressLine2, addressLine3, addressLine4, countryId, stateId, pincode, addressForTax, registeredForSaleTax, salesTaxVatTinNumber, registeredServiceTax, serviceTaxNumber, exciseApplicable, exciseRegistrationNumber, exciseRange, exciseDivision, exciseAddressLine1, exciseAddressLine2, divisionAddressLine1, divisionAddressLine2, gstNumber, gstApplicable, vendTypeMasterId, vendSubTypeMasterId, addressId, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, approvedDate, ignoreHistory, branchId, dataFlag);
    }
}
