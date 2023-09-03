package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorTaxRegistration.
 */
@Entity
@Table(name = "vendor_tax_registration")
public class VendorTaxRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorTaxRegistrationSeq", sequenceName="vendor_tax_registration_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorTaxRegistrationSeq")
    private Long id;

    @NotNull
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;

    @Size(max = 100)
    @Column(name = "address_line_1", length = 100)
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line_2", length = 100)
    private String addressLine2;

    @Size(max = 100)
    @Column(name = "address_line_3", length = 100)
    private String addressLine3;

    @Size(max = 100)
    @Column(name = "address_line_4", length = 100)
    private String addressLine4;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "pincode")
    private Long pincode;

    @Size(max = 45)
    @Column(name = "pan_tax_number", length = 45)
    private String panTaxNumber;

    @NotNull
    @Column(name = "registered_for_sale_tax", nullable = false)
    private Boolean registeredForSaleTax;

    @Size(max = 45)
    @Column(name = "sales_tax_vat_tin_number", length = 45)
    private String salesTaxVatTinNumber;

    @NotNull
    @Column(name = "registered_service_tax", nullable = false)
    private Boolean registeredServiceTax;

    @Size(max = 45)
    @Column(name = "service_tax_number", length = 45)
    private String serviceTaxNumber;

    @NotNull
    @Column(name = "excise_applicable", nullable = false)
    private Boolean exciseApplicable;

    @Size(max = 45)
    @Column(name = "excise_registration_number", length = 45)
    private String exciseRegistrationNumber;

    @Size(max = 45)
    @Column(name = "excise_range", length = 45)
    private String exciseRange;

    @Size(max = 45)
    @Column(name = "excise_division", length = 45)
    private String exciseDivision;

    @Size(max = 45)
    @Column(name = "excise_address_line_1", length = 45)
    private String exciseAddressLine1;

    @Size(max = 45)
    @Column(name = "excise_address_line_2", length = 45)
    private String exciseAddressLine2;

    @Size(max = 45)
    @Column(name = "division_address_line_1", length = 45)
    private String divisionAddressLine1;

    @Size(max = 45)
    @Column(name = "division_address_line_2", length = 45)
    private String divisionAddressLine2;

    @NotNull
    @Column(name = "address_for_tax", nullable = false)
    private Boolean addressForTax;

    @NotNull
    @Column(name = "sister_concern_no", nullable = false)
    private Boolean sisterConcernNo;

    @Size(max = 45)
    @Column(name = "vendor_code", length = 45)
    private String vendorCode;

    @Size(max = 45)
    @Column(name = "gst_number", length = 45)
    private String gstNumber;

    @NotNull
    @Column(name = "gst_applicable", nullable = false)
    private Boolean gstApplicable;

    @Column(name = "vend_type_master_id")
    private Long vendTypeMasterId;

    @Column(name = "vend_sub_type_master_id")
    private Long vendSubTypeMasterId;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

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

    public VendorTaxRegistration vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public VendorTaxRegistration addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1 != null ? addressLine1.toUpperCase() : "";
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public VendorTaxRegistration addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2 != null ? addressLine2.toUpperCase() : "";
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public VendorTaxRegistration addressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3 != null ? addressLine3.toUpperCase() : "";
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public VendorTaxRegistration addressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
        return this;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4 != null ? addressLine4.toUpperCase() : "";
    }

    public Long getCountryId() {
        return countryId;
    }

    public VendorTaxRegistration countryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public VendorTaxRegistration stateId(Long stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getPincode() {
        return pincode;
    }

    public VendorTaxRegistration pincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getPanTaxNumber() {
        return panTaxNumber;
    }

    public VendorTaxRegistration panTaxNumber(String panTaxNumber) {
        this.panTaxNumber = panTaxNumber;
        return this;
    }

    public void setPanTaxNumber(String panTaxNumber) {
        this.panTaxNumber = panTaxNumber != null ? panTaxNumber.toUpperCase() : "";
    }

    public Boolean isRegisteredForSaleTax() {
        return registeredForSaleTax;
    }

    public VendorTaxRegistration registeredForSaleTax(Boolean registeredForSaleTax) {
        this.registeredForSaleTax = registeredForSaleTax;
        return this;
    }

    public void setRegisteredForSaleTax(Boolean registeredForSaleTax) {
        this.registeredForSaleTax = registeredForSaleTax;
    }

    public String getSalesTaxVatTinNumber() {
        return salesTaxVatTinNumber;
    }

    public VendorTaxRegistration salesTaxVatTinNumber(String salesTaxVatTinNumber) {
        this.salesTaxVatTinNumber = salesTaxVatTinNumber;
        return this;
    }

    public void setSalesTaxVatTinNumber(String salesTaxVatTinNumber) {
        this.salesTaxVatTinNumber = salesTaxVatTinNumber != null ? salesTaxVatTinNumber.toUpperCase() : "";
    }

    public Boolean isRegisteredServiceTax() {
        return registeredServiceTax;
    }

    public VendorTaxRegistration registeredServiceTax(Boolean registeredServiceTax) {
        this.registeredServiceTax = registeredServiceTax;
        return this;
    }

    public void setRegisteredServiceTax(Boolean registeredServiceTax) {
        this.registeredServiceTax = registeredServiceTax;
    }

    public String getServiceTaxNumber() {
        return serviceTaxNumber;
    }

    public VendorTaxRegistration serviceTaxNumber(String serviceTaxNumber) {
        this.serviceTaxNumber = serviceTaxNumber;
        return this;
    }

    public void setServiceTaxNumber(String serviceTaxNumber) {
        this.serviceTaxNumber = serviceTaxNumber != null ? serviceTaxNumber.toUpperCase() : "";
    }

    public Boolean isExciseApplicable() {
        return exciseApplicable;
    }

    public VendorTaxRegistration exciseApplicable(Boolean exciseApplicable) {
        this.exciseApplicable = exciseApplicable;
        return this;
    }

    public void setExciseApplicable(Boolean exciseApplicable) {
        this.exciseApplicable = exciseApplicable;
    }

    public String getExciseRegistrationNumber() {
        return exciseRegistrationNumber;
    }

    public VendorTaxRegistration exciseRegistrationNumber(String exciseRegistrationNumber) {
        this.exciseRegistrationNumber = exciseRegistrationNumber;
        return this;
    }

    public void setExciseRegistrationNumber(String exciseRegistrationNumber) {
        this.exciseRegistrationNumber = exciseRegistrationNumber != null ? exciseRegistrationNumber.toUpperCase() : "";
    }

    public String getExciseRange() {
        return exciseRange;
    }

    public VendorTaxRegistration exciseRange(String exciseRange) {
        this.exciseRange = exciseRange;
        return this;
    }

    public void setExciseRange(String exciseRange) {
        this.exciseRange = exciseRange != null ? exciseRange.toUpperCase() : "";
    }

    public String getExciseDivision() {
        return exciseDivision;
    }

    public VendorTaxRegistration exciseDivision(String exciseDivision) {
        this.exciseDivision = exciseDivision;
        return this;
    }

    public void setExciseDivision(String exciseDivision) {
        this.exciseDivision = exciseDivision != null ? exciseDivision.toUpperCase() : "";
    }

    public String getExciseAddressLine1() {
        return exciseAddressLine1;
    }

    public VendorTaxRegistration exciseAddressLine1(String exciseAddressLine1) {
        this.exciseAddressLine1 = exciseAddressLine1;
        return this;
    }

    public void setExciseAddressLine1(String exciseAddressLine1) {
        this.exciseAddressLine1 = exciseAddressLine1 != null ? exciseAddressLine1.toUpperCase() : "";
    }

    public String getExciseAddressLine2() {
        return exciseAddressLine2;
    }

    public VendorTaxRegistration exciseAddressLine2(String exciseAddressLine2) {
        this.exciseAddressLine2 = exciseAddressLine2;
        return this;
    }

    public void setExciseAddressLine2(String exciseAddressLine2) {
        this.exciseAddressLine2 = exciseAddressLine2 != null ? exciseAddressLine2.toUpperCase() : "";
    }

    public String getDivisionAddressLine1() {
        return divisionAddressLine1;
    }

    public VendorTaxRegistration divisionAddressLine1(String divisionAddressLine1) {
        this.divisionAddressLine1 = divisionAddressLine1;
        return this;
    }

    public void setDivisionAddressLine1(String divisionAddressLine1) {
        this.divisionAddressLine1 = divisionAddressLine1 != null ? divisionAddressLine1.toUpperCase() : "";
    }

    public String getDivisionAddressLine2() {
        return divisionAddressLine2;
    }

    public VendorTaxRegistration divisionAddressLine2(String divisionAddressLine2) {
        this.divisionAddressLine2 = divisionAddressLine2;
        return this;
    }

    public void setDivisionAddressLine2(String divisionAddressLine2) {
        this.divisionAddressLine2 = divisionAddressLine2 != null ? divisionAddressLine2.toUpperCase() : "";
    }

    public Boolean isAddressForTax() {
        return addressForTax;
    }

    public VendorTaxRegistration addressForTax(Boolean addressForTax) {
        this.addressForTax = addressForTax;
        return this;
    }

    public void setAddressForTax(Boolean addressForTax) {
        this.addressForTax = addressForTax;
    }

    public Boolean isSisterConcernNo() {
        return sisterConcernNo;
    }

    public VendorTaxRegistration sisterConcernNo(Boolean sisterConcernNo) {
        this.sisterConcernNo = sisterConcernNo;
        return this;
    }

    public void setSisterConcernNo(Boolean sisterConcernNo) {
        this.sisterConcernNo = sisterConcernNo;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public VendorTaxRegistration vendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
        return this;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode != null ? vendorCode.toUpperCase() : "";
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public VendorTaxRegistration gstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
        return this;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber != null ? gstNumber.toUpperCase() : "";
    }

    public Boolean isGstApplicable() {
        return gstApplicable;
    }

    public VendorTaxRegistration gstApplicable(Boolean gstApplicable) {
        this.gstApplicable = gstApplicable;
        return this;
    }

    public void setGstApplicable(Boolean gstApplicable) {
        this.gstApplicable = gstApplicable;
    }

    public Long getVendTypeMasterId() {
        return vendTypeMasterId;
    }

    public VendorTaxRegistration vendTypeMasterId(Long vendTypeMasterId) {
        this.vendTypeMasterId = vendTypeMasterId;
        return this;
    }

    public void setVendTypeMasterId(Long vendTypeMasterId) {
        this.vendTypeMasterId = vendTypeMasterId;
    }

    public Long getVendSubTypeMasterId() {
        return vendSubTypeMasterId;
    }

    public VendorTaxRegistration vendSubTypeMasterId(Long vendSubTypeMasterId) {
        this.vendSubTypeMasterId = vendSubTypeMasterId;
        return this;
    }

    public void setVendSubTypeMasterId(Long vendSubTypeMasterId) {
        this.vendSubTypeMasterId = vendSubTypeMasterId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorTaxRegistration createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorTaxRegistration createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorTaxRegistration lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorTaxRegistration lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        VendorTaxRegistration vendorTaxRegistration = (VendorTaxRegistration) o;
        if (vendorTaxRegistration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorTaxRegistration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorTaxRegistration{" +
            "id=" + getId() +
            ", vendorId=" + getVendorId() +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", addressLine3='" + getAddressLine3() + "'" +
            ", addressLine4='" + getAddressLine4() + "'" +
            ", countryId=" + getCountryId() +
            ", stateId=" + getStateId() +
            ", pincode=" + getPincode() +
            ", panTaxNumber='" + getPanTaxNumber() + "'" +
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
            ", addressForTax='" + isAddressForTax() + "'" +
            ", sisterConcernNo='" + isSisterConcernNo() + "'" +
            ", vendorCode='" + getVendorCode() + "'" +
            ", gstNumber='" + getGstNumber() + "'" +
            ", gstApplicable='" + isGstApplicable() + "'" +
            ", vendTypeMasterId=" + getVendTypeMasterId() +
            ", vendSubTypeMasterId=" + getVendSubTypeMasterId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
