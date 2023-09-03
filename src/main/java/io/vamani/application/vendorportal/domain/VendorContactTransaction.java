package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorContactTransaction.
 */
@Entity
@Table(name = "vendor_contact_transaction")
public class VendorContactTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "vendorContactTransSeq", sequenceName = "vendor_contact_trans_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "vendorContactTransSeq")
    private Long id;

    @NotNull
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;

    @NotNull
    @Size(max = 200)
    @Column(name = "vendor_name", length = 200, nullable = false)
    private String vendorName;

    @Column(name = "organization_type_id")
    private Long organizationTypeId;

    @Size(max = 200)
    @Column(name = "transaction_nature", length = 200)
    private String transactionNature;

    @Size(max = 100)
    @Column(name = "other_transaction_nature", length = 100)
    private String otherTransactionNature;

    @Size(max = 100)
    @Column(name = "contact_person", length = 100)
    private String contactPerson;

    @Size(max = 100)
    @Column(name = "contact_designation", length = 100)
    private String contactDesignation;

    @Size(max = 16)
    @Column(name = "mobile_number", length = 16)
    private String mobileNumber;

    @Size(max = 16)
    @Column(name = "telephone_number", length = 16)
    private String telephoneNumber;

    @Size(max = 16)
    @Column(name = "fax_number", length = 16)
    private String faxNumber;

    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;

    @Size(max = 200)
    @Column(name = "website", length = 200)
    private String website;

    @NotNull
    @Column(name = "branches_present", nullable = false)
    private Boolean branchesPresent;

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

    @Size(max = 25)
    @Column(name = "cinno", length = 25)
    private String cinno;

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

    public VendorContactTransaction vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public VendorContactTransaction vendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName != null ? vendorName.toUpperCase() : "";
    }

    public Long getOrganizationTypeId() {
        return organizationTypeId;
    }

    public VendorContactTransaction organizationTypeId(Long organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
        return this;
    }

    public void setOrganizationTypeId(Long organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
    }

    public String getTransactionNature() {
        return transactionNature;
    }

    public VendorContactTransaction transactionNature(String transactionNature) {
        this.transactionNature = transactionNature;
        return this;
    }

    public void setTransactionNature(String transactionNature) {
        this.transactionNature = transactionNature;
    }

    public String getOtherTransactionNature() {
        return otherTransactionNature;
    }

    public VendorContactTransaction otherTransactionNature(String otherTransactionNature) {
        this.otherTransactionNature = otherTransactionNature;
        return this;
    }

    public void setOtherTransactionNature(String otherTransactionNature) {
        this.otherTransactionNature = otherTransactionNature;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public VendorContactTransaction mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public VendorContactTransaction telephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public VendorContactTransaction faxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
        return this;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public VendorContactTransaction email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.toLowerCase() : "";
    }

    public String getWebsite() {
        return website;
    }

    public VendorContactTransaction website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website != null ? website.toLowerCase() : "";
    }

    public Boolean isBranchesPresent() {
        return branchesPresent;
    }

    public VendorContactTransaction branchesPresent(Boolean branchesPresent) {
        this.branchesPresent = branchesPresent;
        return this;
    }

    public void setBranchesPresent(Boolean branchesPresent) {
        this.branchesPresent = branchesPresent;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorContactTransaction createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorContactTransaction createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorContactTransaction lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorContactTransaction lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCinno() {
        return cinno;
    }

    public VendorContactTransaction cinno(String cinno) {
        this.cinno = cinno;
        return this;
    }

    public void setCinno(String cinno) {
        this.cinno = cinno != null ? cinno.toUpperCase() : "";
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public VendorContactTransaction approvedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public Boolean getBranchesPresent() {
        return branchesPresent;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Boolean isIgnoreHistory() {
        return ignoreHistory;
    }

    public Boolean getIgnoreHistory() {
        return ignoreHistory;
    }

    public void setIgnoreHistory(Boolean ignoreHistory) {
        this.ignoreHistory = ignoreHistory;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VendorContactTransaction vendorContactTransaction = (VendorContactTransaction) o;
        if (vendorContactTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorContactTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorContactTransaction{" +
            "id=" + getId() +
            ", vendorId=" + getVendorId() +
            ", vendorName='" + getVendorName() + "'" +
            ", organizationTypeId=" + getOrganizationTypeId() +
            ", transactionNature='" + getTransactionNature() + "'" +
            ", otherTransactionNature='" + getOtherTransactionNature() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", telephoneNumber='" + getTelephoneNumber() + "'" +
            ", faxNumber='" + getFaxNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", website='" + getWebsite() + "'" +
            ", branchesPresent='" + isBranchesPresent() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", cinno='" + getCinno() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            "}";
    }
}
