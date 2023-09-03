package io.vamani.application.vendorportal.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class VendorBranchDetailsBean implements Serializable {
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

    private String vendTypeMasterDesc;

    private Long vendSubTypeMasterId;

    private String vendSubTypeMasterDesc;

    private String addressId;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private String dataFlag;

    private String branchContactNo;

    private String branchFaxNo;

    private String branchWebsite;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public VendorBranchDetailsBean vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public VendorBranchDetailsBean addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public VendorBranchDetailsBean addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public VendorBranchDetailsBean addressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public VendorBranchDetailsBean addressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
        return this;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public Long getCountryId() {
        return countryId;
    }

    public VendorBranchDetailsBean countryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public VendorBranchDetailsBean stateId(Long stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getPincode() {
        return pincode;
    }

    public VendorBranchDetailsBean pincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Boolean isAddressForTax() {
        return addressForTax;
    }

    public VendorBranchDetailsBean addressForTax(Boolean addressForTax) {
        this.addressForTax = addressForTax;
        return this;
    }

    public void setAddressForTax(Boolean addressForTax) {
        this.addressForTax = addressForTax;
    }

    public Boolean isRegisteredForSaleTax() {
        return registeredForSaleTax;
    }

    public VendorBranchDetailsBean registeredForSaleTax(Boolean registeredForSaleTax) {
        this.registeredForSaleTax = registeredForSaleTax;
        return this;
    }

    public void setRegisteredForSaleTax(Boolean registeredForSaleTax) {
        this.registeredForSaleTax = registeredForSaleTax;
    }

    public String getSalesTaxVatTinNumber() {
        return salesTaxVatTinNumber;
    }

    public VendorBranchDetailsBean salesTaxVatTinNumber(String salesTaxVatTinNumber) {
        this.salesTaxVatTinNumber = salesTaxVatTinNumber;
        return this;
    }

    public void setSalesTaxVatTinNumber(String salesTaxVatTinNumber) {
        this.salesTaxVatTinNumber = salesTaxVatTinNumber;
    }

    public Boolean isRegisteredServiceTax() {
        return registeredServiceTax;
    }

    public VendorBranchDetailsBean registeredServiceTax(Boolean registeredServiceTax) {
        this.registeredServiceTax = registeredServiceTax;
        return this;
    }

    public void setRegisteredServiceTax(Boolean registeredServiceTax) {
        this.registeredServiceTax = registeredServiceTax;
    }

    public String getServiceTaxNumber() {
        return serviceTaxNumber;
    }

    public VendorBranchDetailsBean serviceTaxNumber(String serviceTaxNumber) {
        this.serviceTaxNumber = serviceTaxNumber;
        return this;
    }

    public void setServiceTaxNumber(String serviceTaxNumber) {
        this.serviceTaxNumber = serviceTaxNumber;
    }

    public Boolean isExciseApplicable() {
        return exciseApplicable;
    }

    public VendorBranchDetailsBean exciseApplicable(Boolean exciseApplicable) {
        this.exciseApplicable = exciseApplicable;
        return this;
    }

    public void setExciseApplicable(Boolean exciseApplicable) {
        this.exciseApplicable = exciseApplicable;
    }

    public String getExciseRegistrationNumber() {
        return exciseRegistrationNumber;
    }

    public VendorBranchDetailsBean exciseRegistrationNumber(String exciseRegistrationNumber) {
        this.exciseRegistrationNumber = exciseRegistrationNumber;
        return this;
    }

    public void setExciseRegistrationNumber(String exciseRegistrationNumber) {
        this.exciseRegistrationNumber = exciseRegistrationNumber;
    }

    public String getExciseRange() {
        return exciseRange;
    }

    public VendorBranchDetailsBean exciseRange(String exciseRange) {
        this.exciseRange = exciseRange;
        return this;
    }

    public void setExciseRange(String exciseRange) {
        this.exciseRange = exciseRange;
    }

    public String getExciseDivision() {
        return exciseDivision;
    }

    public VendorBranchDetailsBean exciseDivision(String exciseDivision) {
        this.exciseDivision = exciseDivision;
        return this;
    }

    public void setExciseDivision(String exciseDivision) {
        this.exciseDivision = exciseDivision;
    }

    public String getExciseAddressLine1() {
        return exciseAddressLine1;
    }

    public VendorBranchDetailsBean exciseAddressLine1(String exciseAddressLine1) {
        this.exciseAddressLine1 = exciseAddressLine1;
        return this;
    }

    public void setExciseAddressLine1(String exciseAddressLine1) {
        this.exciseAddressLine1 = exciseAddressLine1;
    }

    public String getExciseAddressLine2() {
        return exciseAddressLine2;
    }

    public VendorBranchDetailsBean exciseAddressLine2(String exciseAddressLine2) {
        this.exciseAddressLine2 = exciseAddressLine2;
        return this;
    }

    public void setExciseAddressLine2(String exciseAddressLine2) {
        this.exciseAddressLine2 = exciseAddressLine2;
    }

    public String getDivisionAddressLine1() {
        return divisionAddressLine1;
    }

    public VendorBranchDetailsBean divisionAddressLine1(String divisionAddressLine1) {
        this.divisionAddressLine1 = divisionAddressLine1;
        return this;
    }

    public void setDivisionAddressLine1(String divisionAddressLine1) {
        this.divisionAddressLine1 = divisionAddressLine1;
    }

    public String getDivisionAddressLine2() {
        return divisionAddressLine2;
    }

    public VendorBranchDetailsBean divisionAddressLine2(String divisionAddressLine2) {
        this.divisionAddressLine2 = divisionAddressLine2;
        return this;
    }

    public void setDivisionAddressLine2(String divisionAddressLine2) {
        this.divisionAddressLine2 = divisionAddressLine2;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public VendorBranchDetailsBean gstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
        return this;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public Boolean isGstApplicable() {
        return gstApplicable;
    }

    public VendorBranchDetailsBean gstApplicable(Boolean gstApplicable) {
        this.gstApplicable = gstApplicable;
        return this;
    }

    public void setGstApplicable(Boolean gstApplicable) {
        this.gstApplicable = gstApplicable;
    }

    public Long getVendTypeMasterId() {
        return vendTypeMasterId;
    }

    public VendorBranchDetailsBean vendTypeMasterId(Long vendTypeMasterId) {
        this.vendTypeMasterId = vendTypeMasterId;
        return this;
    }

    public void setVendTypeMasterId(Long vendTypeMasterId) {
        this.vendTypeMasterId = vendTypeMasterId;
    }

    public Long getVendSubTypeMasterId() {
        return vendSubTypeMasterId;
    }

    public VendorBranchDetailsBean vendSubTypeMasterId(Long vendSubTypeMasterId) {
        this.vendSubTypeMasterId = vendSubTypeMasterId;
        return this;
    }

    public void setVendSubTypeMasterId(Long vendSubTypeMasterId) {
        this.vendSubTypeMasterId = vendSubTypeMasterId;
    }

    public String getAddressId() {
        return addressId;
    }

    public VendorBranchDetailsBean addressId(String addressId) {
        this.addressId = addressId;
        return this;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorBranchDetailsBean createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorBranchDetailsBean createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorBranchDetailsBean lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorBranchDetailsBean lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public Boolean getAddressForTax() {
        return addressForTax;
    }

    public Boolean getRegisteredForSaleTax() {
        return registeredForSaleTax;
    }

    public Boolean getRegisteredServiceTax() {
        return registeredServiceTax;
    }

    public Boolean getExciseApplicable() {
        return exciseApplicable;
    }

    public Boolean getGstApplicable() {
        return gstApplicable;
    }

    public String getVendTypeMasterDesc() {
        return vendTypeMasterDesc;
    }

    public void setVendTypeMasterDesc(String vendTypeMasterDesc) {
        this.vendTypeMasterDesc = vendTypeMasterDesc;
    }

    public String getVendSubTypeMasterDesc() {
        return vendSubTypeMasterDesc;
    }

    public void setVendSubTypeMasterDesc(String vendSubTypeMasterDesc) {
        this.vendSubTypeMasterDesc = vendSubTypeMasterDesc;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }

    public String getGstNumber1() {
        if (gstNumber != null) {
            gstNumber1 = gstNumber.split(" ")[0];
        }
        return gstNumber1;
    }

    public void setGstNumber1(String gstNumber1) {
        this.gstNumber1 = gstNumber1;
    }

    public String getGstNumber2() {
        if (gstNumber != null) {
            gstNumber2 = gstNumber.split(" ")[1];
        }
        return gstNumber2;
    }

    public void setGstNumber2(String gstNumber2) {
        this.gstNumber2 = gstNumber2;
    }

    public String getGstNumber3() {
        if (gstNumber != null) {
            gstNumber3 = gstNumber.split(" ")[2];
        }
        return gstNumber3;
    }

    public void setGstNumber3(String gstNumber3) {
        this.gstNumber3 = gstNumber3;
    }

    public String getGstNumber4() {
        if (gstNumber != null) {
            gstNumber4 = gstNumber.split(" ")[3];
        }
        return gstNumber4;
    }

    public void setGstNumber4(String gstNumber4) {
        this.gstNumber4 = gstNumber4;
    }

    public String getGstNumber5() {
        if (gstNumber != null) {
            gstNumber5 = gstNumber.split(" ")[4];
        }
        return gstNumber5;
    }

    public void setGstNumber5(String gstNumber5) {
        this.gstNumber5 = gstNumber5;
    }

    public String getBranchContactNo() {
        return branchContactNo;
    }

    public void setBranchContactNo(String branchContactNo) {
        this.branchContactNo = branchContactNo != null ? branchContactNo.toUpperCase() : "";
    }

    public String getBranchFaxNo() {
        return branchFaxNo;
    }

    public void setBranchFaxNo(String branchFaxNo) {
        this.branchFaxNo = branchFaxNo != null ? branchFaxNo.toUpperCase() : "";
    }

    public String getBranchWebsite() {
        return branchWebsite;
    }

    public void setBranchWebsite(String branchWebsite) {
        this.branchWebsite = branchWebsite != null ? branchWebsite.toLowerCase() : "";
    }

// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VendorBranchDetailsBean VendorBranchDetailsBean = (VendorBranchDetailsBean) o;
        if (VendorBranchDetailsBean.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), VendorBranchDetailsBean.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorBranchDetailsBean{" +
            "id=" + getId() +
            ", vendorId=" + getVendorId() +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", addressLine3='" + getAddressLine3() + "'" +
            ", addressLine4='" + getAddressLine4() + "'" +
            ", countryId=" + getCountryId() +
            ", stateId=" + getStateId() +
            ", pincode=" + getPincode() +
            ", addressForTax='" + isAddressForTax() + "'" +
            ", registeredForSaleTax='" + isRegisteredForSaleTax() + "'" +
            ", salesTaxVatTinNumber='" + getSalesTaxVatTinNumber() + "'" +
            ", registeredServiceTax='" + isRegisteredServiceTax() + "'" +
            ", serviceTaxNumber='" + getServiceTaxNumber() + "'" +
            ", exciseApplicable='" + isExciseApplicable() + "'" +
            ", exciseRegistrationNumber='" + getExciseRegistrationNumber() + "'" +
            ", exciseRange='" + getExciseRange() + "'" +
            ", exciseDivision='" + getExciseDivision() + "'" +
            ", exciseAddressLine1='" + getExciseAddressLine1() + "'" +
            ", exciseAddressLine2='" + getExciseAddressLine2() + "'" +
            ", divisionAddressLine1='" + getDivisionAddressLine1() + "'" +
            ", divisionAddressLine2='" + getDivisionAddressLine2() + "'" +
            ", gstNumber='" + getGstNumber() + "'" +
            ", gstApplicable='" + isGstApplicable() + "'" +
            ", vendTypeMasterId=" + getVendTypeMasterId() +
            ", vendSubTypeMasterId=" + getVendSubTypeMasterId() +
            ", addressId='" + getAddressId() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
