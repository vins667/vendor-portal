package io.vamani.application.vendorportal.model;

import java.io.Serializable;

public class VendorContactTaxRegistration implements Serializable {
    private Long vendorId;
    private String vendorName;
    private Long organizationTypeId;
    private String transactionNature;
    private String otherTransactionNature;
    private String mobileNumber;
    private String telephoneNumber;
    private String faxNumber;
    private String email;
    private String website;
    private Boolean branchesPresent;
    private String cinno;
    private String contactPerson;
    private String contactDesignation;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private Long countryId;
    private Long stateId;
    private Long pincode;
    private String panTaxNumber;
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
    private Boolean addressForTax;
    private Boolean sisterConcernNo;
    private String vendorCode;
    private String gstNumber;

    private String gstNumber1;
    private String gstNumber2;
    private String gstNumber3;
    private String gstNumber4;
    private String gstNumber5;

    private Boolean gstApplicable;
    private Long vendTypeMasterId;
    private Long vendSubTypeMasterId;

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Long getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(Long organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
    }

    public String getTransactionNature() {
        return transactionNature;
    }

    public void setTransactionNature(String transactionNature) {
        this.transactionNature = transactionNature;
    }

    public String getOtherTransactionNature() {
        return otherTransactionNature;
    }

    public void setOtherTransactionNature(String otherTransactionNature) {
        this.otherTransactionNature = otherTransactionNature;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getBranchesPresent() {
        return branchesPresent;
    }

    public void setBranchesPresent(Boolean branchesPresent) {
        this.branchesPresent = branchesPresent;
    }

    public String getCinno() {
        return cinno;
    }

    public void setCinno(String cinno) {
        this.cinno = cinno;
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

    public String getPanTaxNumber() {
        return panTaxNumber;
    }

    public void setPanTaxNumber(String panTaxNumber) {
        this.panTaxNumber = panTaxNumber;
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

    public Boolean getAddressForTax() {
        return addressForTax;
    }

    public void setAddressForTax(Boolean addressForTax) {
        this.addressForTax = addressForTax;
    }

    public Boolean getSisterConcernNo() {
        return sisterConcernNo;
    }

    public void setSisterConcernNo(Boolean sisterConcernNo) {
        this.sisterConcernNo = sisterConcernNo;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
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

    public String getGstNumber1() {
        if (gstNumber != null && gstNumber.split(" ").length==5) {
            gstNumber1 = gstNumber.split(" ")[0];
        }
        return gstNumber1;
    }

    public void setGstNumber1(String gstNumber1) {
        this.gstNumber1 = gstNumber1;
    }

    public String getGstNumber2() {
        if (gstNumber != null && gstNumber.split(" ").length==5) {
            gstNumber2 = gstNumber.split(" ")[1];
        }
        return gstNumber2;
    }

    public void setGstNumber2(String gstNumber2) {
        this.gstNumber2 = gstNumber2;
    }

    public String getGstNumber3() {
        if (gstNumber != null && gstNumber.split(" ").length==5) {
            gstNumber3 = gstNumber.split(" ")[2];
        }
        return gstNumber3;
    }

    public void setGstNumber3(String gstNumber3) {
        this.gstNumber3 = gstNumber3;
    }

    public String getGstNumber4() {
        if (gstNumber != null && gstNumber.split(" ").length==5) {
            gstNumber4 = gstNumber.split(" ")[3];
        }
        return gstNumber4;
    }

    public void setGstNumber4(String gstNumber4) {
        this.gstNumber4 = gstNumber4;
    }

    public String getGstNumber5() {
        if (gstNumber != null && gstNumber.split(" ").length==5) {
            gstNumber5 = gstNumber.split(" ")[4];
        }
        return gstNumber5;
    }

    public void setGstNumber5(String gstNumber5) {
        this.gstNumber5 = gstNumber5;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson != null ? contactPerson.toUpperCase() : "";
    }

    public String getContactDesignation() {
        return contactDesignation;
    }

    public void setContactDesignation(String contactDesignation) {
        this.contactDesignation = contactDesignation != null ? contactDesignation.toUpperCase() : "";
    }

    @Override
	public String toString() {
		return "VendorContactTaxRegistration [vendorId=" + vendorId + ", vendorName=" + vendorName
				+ ", organizationTypeId=" + organizationTypeId + ", transactionNature=" + transactionNature
				+ ", otherTransactionNature=" + otherTransactionNature + ", mobileNumber=" + mobileNumber
				+ ", telephoneNumber=" + telephoneNumber + ", faxNumber=" + faxNumber + ", email=" + email
				+ ", website=" + website + ", branchesPresent=" + branchesPresent + ", cinno=" + cinno
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3
				+ ", addressLine4=" + addressLine4 + ", countryId=" + countryId + ", stateId=" + stateId + ", pincode="
				+ pincode + ", panTaxNumber=" + panTaxNumber + ", registeredForSaleTax=" + registeredForSaleTax
				+ ", salesTaxVatTinNumber=" + salesTaxVatTinNumber + ", registeredServiceTax=" + registeredServiceTax
				+ ", serviceTaxNumber=" + serviceTaxNumber + ", exciseApplicable=" + exciseApplicable
				+ ", exciseRegistrationNumber=" + exciseRegistrationNumber + ", exciseRange=" + exciseRange
				+ ", exciseDivision=" + exciseDivision + ", exciseAddressLine1=" + exciseAddressLine1
				+ ", exciseAddressLine2=" + exciseAddressLine2 + ", divisionAddressLine1=" + divisionAddressLine1
				+ ", divisionAddressLine2=" + divisionAddressLine2 + ", addressForTax=" + addressForTax
				+ ", sisterConcernNo=" + sisterConcernNo + ", vendorCode=" + vendorCode + ", gstNumber=" + gstNumber
				+ ", gstApplicable=" + gstApplicable + ", vendTypeMasterId=" + vendTypeMasterId
				+ ", vendSubTypeMasterId=" + vendSubTypeMasterId + "]";
	}


}
