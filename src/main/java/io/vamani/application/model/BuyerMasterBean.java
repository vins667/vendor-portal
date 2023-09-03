package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class BuyerMasterBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String buyerCode;

    private String buyerName;

    private String contactNo;

    private String emailId;

    private String address;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private String auditName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerMasterBean that = (BuyerMasterBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(buyerCode, that.buyerCode) &&
            Objects.equals(buyerName, that.buyerName) &&
            Objects.equals(contactNo, that.contactNo) &&
            Objects.equals(emailId, that.emailId) &&
            Objects.equals(address, that.address) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(auditName, that.auditName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerCode, buyerName, contactNo, emailId, address, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, auditName);
    }

    @Override
    public String toString() {
        return "BuyerMasterBean{" +
            "id=" + id +
            ", buyerCode='" + buyerCode + '\'' +
            ", buyerName='" + buyerName + '\'' +
            ", contactNo='" + contactNo + '\'' +
            ", emailId='" + emailId + '\'' +
            ", address='" + address + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", auditName='" + auditName + '\'' +
            '}';
    }
}
