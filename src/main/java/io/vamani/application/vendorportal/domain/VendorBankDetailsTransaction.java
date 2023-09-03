package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorBankDetailsTransaction.
 */
@Entity
@Table(name = "vendor_bank_details_transaction")
public class VendorBankDetailsTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="vendorBankDetailsTransSeq", sequenceName="vendor_bank_details_trans_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorBankDetailsTransSeq")
    private Long id;

    @NotNull
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;

    @Size(max = 45)
    @Column(name = "bank_name", length = 45)
    private String bankName;

    @Size(max = 10)
    @Column(name = "account_type", length = 10)
    private String accountType;

    @Size(max = 45)
    @Column(name = "ifsc", length = 45)
    private String ifsc;

    @Size(max = 45)
    @Column(name = "swift_code", length = 45)
    private String swiftCode;

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

    @Size(max = 10)
    @Column(name = "pin_code", length = 10)
    private String pinCode;

    @Size(max = 45)
    @Column(name = "bank_mail_id", length = 45)
    private String bankMailId;

    @Size(max = 45)
    @Column(name = "account_number", length = 45)
    private String accountNumber;

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

    @Column(name = "approved_date")
    private Instant approvedDate;

    @Column(name = "ignore_history")
    private Boolean ignoreHistory;

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

    public VendorBankDetailsTransaction vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getBankName() {
        return bankName;
    }

    public VendorBankDetailsTransaction bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName != null ? bankName.toUpperCase() : "";
    }

    public String getAccountType() {
        return accountType;
    }

    public VendorBankDetailsTransaction accountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType != null ? accountType.toUpperCase() : "";
    }

    public String getIfsc() {
        return ifsc;
    }

    public VendorBankDetailsTransaction ifsc(String ifsc) {
        this.ifsc = ifsc;
        return this;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc != null ? ifsc.toUpperCase() : "";
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public VendorBankDetailsTransaction swiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode != null ? swiftCode.toUpperCase() : "";
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public VendorBankDetailsTransaction addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1 != null ? addressLine1.toUpperCase() : "";
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public VendorBankDetailsTransaction addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2 != null ? addressLine2.toUpperCase() : "";
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public VendorBankDetailsTransaction addressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
        return this;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3 != null ? addressLine3.toUpperCase() : "";
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public VendorBankDetailsTransaction addressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
        return this;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4 != null ? addressLine4.toUpperCase() : "";
    }

    public Long getCountryId() {
        return countryId;
    }

    public VendorBankDetailsTransaction countryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public VendorBankDetailsTransaction stateId(Long stateId) {
        this.stateId = stateId;
        return this;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public VendorBankDetailsTransaction pinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode != null ? pinCode.toUpperCase() : "";
    }

    public String getBankMailId() {
        return bankMailId;
    }

    public VendorBankDetailsTransaction bankMailId(String bankMailId) {
        this.bankMailId = bankMailId;
        return this;
    }

    public void setBankMailId(String bankMailId) {
        this.bankMailId = bankMailId != null ? bankMailId.toLowerCase() : "";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public VendorBankDetailsTransaction accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber != null ? accountNumber.toUpperCase() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorBankDetailsTransaction createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorBankDetailsTransaction createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorBankDetailsTransaction lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorBankDetailsTransaction lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public VendorBankDetailsTransaction approvedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
        return this;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VendorBankDetailsTransaction vendorBankDetailsTransaction = (VendorBankDetailsTransaction) o;
        if (vendorBankDetailsTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorBankDetailsTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorBankDetailsTransaction{" +
            "id=" + getId() +
            ", vendorId=" + getVendorId() +
            ", bankName='" + getBankName() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", ifsc='" + getIfsc() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", addressLine3='" + getAddressLine3() + "'" +
            ", addressLine4='" + getAddressLine4() + "'" +
            ", countryId=" + getCountryId() +
            ", stateId=" + getStateId() +
            ", pinCode='" + getPinCode() + "'" +
            ", bankMailId='" + getBankMailId() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            "}";
    }
}
