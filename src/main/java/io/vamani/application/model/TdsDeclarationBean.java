package io.vamani.application.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class TdsDeclarationBean {
    private String cardNo;
    private String name;
    private String designation;
    private String panNo;
    private ZonedDateTime dateOfBirth;
    private String contactNumber;
    private String emailId;
    private String address;
    private String monthRent;
    private String landLoardName;
    private String landLoardPanNo;
    private String landLoardAddress;
    private Boolean regime;
    private String regimeType;
    private String tempLock;
    private String yearLock;
    private BigDecimal previousEmployerAmount;
    private BigDecimal previousEmployerTdsDeduction;
    private BigDecimal incentiveAmount;
    List<TdsGroupMasterBean> groupMasterBeans;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(ZonedDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public String getMonthRent() {
        return monthRent;
    }

    public void setMonthRent(String monthRent) {
        this.monthRent = monthRent;
    }

    public String getLandLoardName() {
        return landLoardName;
    }

    public void setLandLoardName(String landLoardName) {
        this.landLoardName = landLoardName;
    }

    public String getLandLoardPanNo() {
        return landLoardPanNo;
    }

    public void setLandLoardPanNo(String landLoardPanNo) {
        this.landLoardPanNo = landLoardPanNo;
    }

    public String getLandLoardAddress() {
        return landLoardAddress;
    }

    public void setLandLoardAddress(String landLoardAddress) {
        this.landLoardAddress = landLoardAddress;
    }

    public Boolean getRegime() {
        return regime;
    }

    public void setRegime(Boolean regime) {
        this.regime = regime;
    }

    public String getRegimeType() {
        return regimeType;
    }

    public void setRegimeType(String regimeType) {
        this.regimeType = regimeType;
    }

    public String getTempLock() {
        return tempLock;
    }

    public void setTempLock(String tempLock) {
        this.tempLock = tempLock;
    }

    public String getYearLock() {
        return yearLock;
    }

    public void setYearLock(String yearLock) {
        this.yearLock = yearLock;
    }

    public BigDecimal getPreviousEmployerAmount() {
        return previousEmployerAmount;
    }

    public void setPreviousEmployerAmount(BigDecimal previousEmployerAmount) {
        this.previousEmployerAmount = previousEmployerAmount;
    }

    public BigDecimal getPreviousEmployerTdsDeduction() {
        return previousEmployerTdsDeduction;
    }

    public void setPreviousEmployerTdsDeduction(BigDecimal previousEmployerTdsDeduction) {
        this.previousEmployerTdsDeduction = previousEmployerTdsDeduction;
    }

    public BigDecimal getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(BigDecimal incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
    }

    public List<TdsGroupMasterBean> getGroupMasterBeans() {
        return groupMasterBeans;
    }

    public void setGroupMasterBeans(List<TdsGroupMasterBean> groupMasterBeans) {
        this.groupMasterBeans = groupMasterBeans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TdsDeclarationBean that = (TdsDeclarationBean) o;
        return Objects.equals(cardNo, that.cardNo) && Objects.equals(name, that.name) && Objects.equals(designation, that.designation) && Objects.equals(panNo, that.panNo) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(contactNumber, that.contactNumber) && Objects.equals(emailId, that.emailId) && Objects.equals(address, that.address) && Objects.equals(monthRent, that.monthRent) && Objects.equals(landLoardName, that.landLoardName) && Objects.equals(landLoardPanNo, that.landLoardPanNo) && Objects.equals(landLoardAddress, that.landLoardAddress) && Objects.equals(regime, that.regime) && Objects.equals(tempLock, that.tempLock) && Objects.equals(yearLock, that.yearLock) && Objects.equals(groupMasterBeans, that.groupMasterBeans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, name, designation, panNo, dateOfBirth, contactNumber, emailId, address, monthRent, landLoardName, landLoardPanNo, landLoardAddress, regime, tempLock, yearLock, groupMasterBeans);
    }

    @Override
    public String toString() {
        return "TdsDeclarationBean{" +
            "cardNo='" + cardNo + '\'' +
            ", name='" + name + '\'' +
            ", designation='" + designation + '\'' +
            ", panNo='" + panNo + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", contactNumber='" + contactNumber + '\'' +
            ", emailId='" + emailId + '\'' +
            ", address='" + address + '\'' +
            ", monthRent='" + monthRent + '\'' +
            ", landLoardName='" + landLoardName + '\'' +
            ", landLoardPanNo='" + landLoardPanNo + '\'' +
            ", landLoardAddress='" + landLoardAddress + '\'' +
            ", regime=" + regime +
            ", tempLock='" + tempLock + '\'' +
            ", yearLock='" + yearLock + '\'' +
            ", groupMasterBeans=" + groupMasterBeans +
            '}';
    }
}
