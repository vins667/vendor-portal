package io.vamani.application.vendorportal.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class ProfileWorkFlowBean implements Serializable {
    private Long id;

    private String userCode;

    private String userName;

    private String forwardCode;

    private String forwardName;

    private String forwardFlag;

    private String userType;

    private String remarks;

    private Instant createdDate;

    private String createdBy;

    private Long vendorId;

    private String vendorCode;

    private String vendorShortName;

    private Long payTermMasterId;

    private Long shipmentTermMasterId;

    private Long currencyMasterId;

    private Boolean orderAllowed;

    private Long deliveryTermMasterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getForwardCode() {
        return forwardCode;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getForwardFlag() {
        return forwardFlag;
    }

    public void setForwardFlag(String forwardFlag) {
        this.forwardFlag = forwardFlag;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorShortName() {
        return vendorShortName;
    }

    public void setVendorShortName(String vendorShortName) {
        this.vendorShortName = vendorShortName;
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

    public Boolean getOrderAllowed() {
        return orderAllowed;
    }

    public void setOrderAllowed(Boolean orderAllowed) {
        this.orderAllowed = orderAllowed;
    }

    public Long getDeliveryTermMasterId() {
        return deliveryTermMasterId;
    }

    public void setDeliveryTermMasterId(Long deliveryTermMasterId) {
        this.deliveryTermMasterId = deliveryTermMasterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileWorkFlowBean that = (ProfileWorkFlowBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(userCode, that.userCode) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(forwardCode, that.forwardCode) &&
            Objects.equals(forwardName, that.forwardName) &&
            Objects.equals(forwardFlag, that.forwardFlag) &&
            Objects.equals(userType, that.userType) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(vendorId, that.vendorId) &&
            Objects.equals(vendorCode, that.vendorCode) &&
            Objects.equals(vendorShortName, that.vendorShortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userCode, userName, forwardCode, forwardName, forwardFlag, userType, remarks, createdDate, createdBy, vendorId, vendorCode, vendorShortName);
    }

    @Override
    public String toString() {
        return "ProfileWorkFlowBean{" +
            "id=" + id +
            ", userCode='" + userCode + '\'' +
            ", userName='" + userName + '\'' +
            ", forwardCode='" + forwardCode + '\'' +
            ", forwardName='" + forwardName + '\'' +
            ", forwardFlag='" + forwardFlag + '\'' +
            ", userType='" + userType + '\'' +
            ", remarks='" + remarks + '\'' +
            ", createdDate=" + createdDate +
            ", createdBy='" + createdBy + '\'' +
            ", vendorId=" + vendorId +
            ", vendorCode='" + vendorCode + '\'' +
            ", vendorShortName='" + vendorShortName + '\'' +
            '}';
    }
}
