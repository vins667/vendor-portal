package io.vamani.application.model;

import io.vamani.application.domain.TdsSlabMaster;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class TdsComputationBean implements Serializable {
    private Long id;
    private String financialYear;
    private String financialYearRange;
    private String cardNo;
    private Boolean regime;
    private String earnLabel1;
    private Double earnAmount1;
    private String earnLabel2;
    private Double earnAmount2;
    private String earnLabel3;
    private Double earnAmount3;
    private String earnLabel4;
    private Double earnAmount4;
    private String earnLabel5;
    private Double earnAmount5;
    private String earnLabel6;
    private Double earnAmount6;
    private String earnLabel7;
    private Double earnAmount7;
    private String earnLabel8;
    private Double earnAmount8;
    private String earnLabel9;
    private Double earnAmount9;
    private String earnLabel10;
    private Double earnAmount10;
    private BigDecimal previousEmployerAmount;
    private BigDecimal previousEmployerTdsDeduction;
    private BigDecimal incentiveAmount;
    private Double totalEarnAmount;
    private Double rentDeclare;
    private Double rentExempt;
    private Double earnTotal;
    private Double standardDeduction;
    private Double standardTotal;

    private String deductCode1;
    private String deductCode2;
    private String deductCode3;
    private String deductCode4;
    private String deductCode5;
    private String deductCode6;
    private String deductCode7;
    private String deductCode8;
    private String deductCode9;
    private String deductCode10;
    private String deductCode11;
    private String deductCode12;
    private String deductCode13;
    private String deductCode14;
    private String deductCode15;
    private String deductCode16;
    private String deductCode17;
    private String deductCode18;
    private String deductCode19;
    private String deductCode20;

    private Double deductAmount1;
    private Double deductAmount2;
    private Double deductAmount3;
    private Double deductAmount4;
    private Double deductAmount5;
    private Double deductAmount6;
    private Double deductAmount7;
    private Double deductAmount8;
    private Double deductAmount9;
    private Double deductAmount10;
    private Double deductAmount11;
    private Double deductAmount12;
    private Double deductAmount13;
    private Double deductAmount14;
    private Double deductAmount15;
    private Double deductAmount16;
    private Double deductAmount17;
    private Double deductAmount18;
    private Double deductAmount19;
    private Double deductAmount20;
    private Instant processDate;

    private Double totalTaxIncome;
    private Double taxValue;
    private Double cessValue;
    private Double taxDeductValue;
    private Double balanceTaxValue;
    private Double totalTaxLiability;
    private Integer pendingMonth;

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

    private Boolean locked;

    private List<TdsGroupMasterBean> groupMasterBeans;
    private List<TdsSlabMaster> tdsSlabMasters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEarnLabel1() {
        return earnLabel1;
    }

    public void setEarnLabel1(String earnLabel1) {
        this.earnLabel1 = earnLabel1;
    }

    public Double getEarnAmount1() {
        return earnAmount1;
    }

    public void setEarnAmount1(Double earnAmount1) {
        this.earnAmount1 = earnAmount1;
    }

    public String getEarnLabel2() {
        return earnLabel2;
    }

    public void setEarnLabel2(String earnLabel2) {
        this.earnLabel2 = earnLabel2;
    }

    public Double getEarnAmount2() {
        return earnAmount2;
    }

    public void setEarnAmount2(Double earnAmount2) {
        this.earnAmount2 = earnAmount2;
    }

    public String getEarnLabel3() {
        return earnLabel3;
    }

    public void setEarnLabel3(String earnLabel3) {
        this.earnLabel3 = earnLabel3;
    }

    public Double getEarnAmount3() {
        return earnAmount3;
    }

    public void setEarnAmount3(Double earnAmount3) {
        this.earnAmount3 = earnAmount3;
    }

    public String getEarnLabel4() {
        return earnLabel4;
    }

    public void setEarnLabel4(String earnLabel4) {
        this.earnLabel4 = earnLabel4;
    }

    public Double getEarnAmount4() {
        return earnAmount4;
    }

    public void setEarnAmount4(Double earnAmount4) {
        this.earnAmount4 = earnAmount4;
    }

    public String getEarnLabel5() {
        return earnLabel5;
    }

    public void setEarnLabel5(String earnLabel5) {
        this.earnLabel5 = earnLabel5;
    }

    public Double getEarnAmount5() {
        return earnAmount5;
    }

    public void setEarnAmount5(Double earnAmount5) {
        this.earnAmount5 = earnAmount5;
    }

    public String getEarnLabel6() {
        return earnLabel6;
    }

    public void setEarnLabel6(String earnLabel6) {
        this.earnLabel6 = earnLabel6;
    }

    public Double getEarnAmount6() {
        return earnAmount6;
    }

    public void setEarnAmount6(Double earnAmount6) {
        this.earnAmount6 = earnAmount6;
    }

    public String getEarnLabel7() {
        return earnLabel7;
    }

    public void setEarnLabel7(String earnLabel7) {
        this.earnLabel7 = earnLabel7;
    }

    public Double getEarnAmount7() {
        return earnAmount7;
    }

    public void setEarnAmount7(Double earnAmount7) {
        this.earnAmount7 = earnAmount7;
    }

    public String getEarnLabel8() {
        return earnLabel8;
    }

    public void setEarnLabel8(String earnLabel8) {
        this.earnLabel8 = earnLabel8;
    }

    public Double getEarnAmount8() {
        return earnAmount8;
    }

    public void setEarnAmount8(Double earnAmount8) {
        this.earnAmount8 = earnAmount8;
    }

    public String getEarnLabel9() {
        return earnLabel9;
    }

    public void setEarnLabel9(String earnLabel9) {
        this.earnLabel9 = earnLabel9;
    }

    public Double getEarnAmount9() {
        return earnAmount9;
    }

    public void setEarnAmount9(Double earnAmount9) {
        this.earnAmount9 = earnAmount9;
    }

    public String getEarnLabel10() {
        return earnLabel10;
    }

    public void setEarnLabel10(String earnLabel10) {
        this.earnLabel10 = earnLabel10;
    }

    public Double getEarnAmount10() {
        return earnAmount10;
    }

    public void setEarnAmount10(Double earnAmount10) {
        this.earnAmount10 = earnAmount10;
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

    public String getFinancialYearRange() {
        return financialYearRange;
    }

    public void setFinancialYearRange(String financialYearRange) {
        this.financialYearRange = financialYearRange;
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

    public Double getEarnTotal() {
        return earnTotal;
    }

    public void setEarnTotal(Double earnTotal) {
        this.earnTotal = earnTotal;
    }

    public Double getRentDeclare() {
        return rentDeclare;
    }

    public void setRentDeclare(Double rentDeclare) {
        this.rentDeclare = rentDeclare;
    }

    public Double getRentExempt() {
        return rentExempt;
    }

    public void setRentExempt(Double rentExempt) {
        this.rentExempt = rentExempt;
    }

    public Double getStandardDeduction() {
        return standardDeduction;
    }

    public void setStandardDeduction(Double standardDeduction) {
        this.standardDeduction = standardDeduction;
    }

    public Double getStandardTotal() {
        return standardTotal;
    }

    public void setStandardTotal(Double standardTotal) {
        this.standardTotal = standardTotal;
    }

    public Double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public Double getCessValue() {
        return cessValue;
    }

    public void setCessValue(Double cessValue) {
        this.cessValue = cessValue;
    }

    public Double getTaxDeductValue() {
        return taxDeductValue;
    }

    public void setTaxDeductValue(Double taxDeductValue) {
        this.taxDeductValue = taxDeductValue;
    }

    public Double getBalanceTaxValue() {
        return balanceTaxValue;
    }

    public void setBalanceTaxValue(Double balanceTaxValue) {
        this.balanceTaxValue = balanceTaxValue;
    }

    public Double getTotalTaxLiability() {
        return totalTaxLiability;
    }

    public void setTotalTaxLiability(Double totalTaxLiability) {
        this.totalTaxLiability = totalTaxLiability;
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
        TdsComputationBean that = (TdsComputationBean) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(financialYear, that.financialYear) &&
            Objects.equals(cardNo, that.cardNo) &&
            Objects.equals(earnLabel1, that.earnLabel1) &&
            Objects.equals(earnAmount1, that.earnAmount1) &&
            Objects.equals(earnLabel2, that.earnLabel2) &&
            Objects.equals(earnAmount2, that.earnAmount2) &&
            Objects.equals(earnLabel3, that.earnLabel3) &&
            Objects.equals(earnAmount3, that.earnAmount3) &&
            Objects.equals(earnLabel4, that.earnLabel4) &&
            Objects.equals(earnAmount4, that.earnAmount4) &&
            Objects.equals(earnLabel5, that.earnLabel5) &&
            Objects.equals(earnAmount5, that.earnAmount5) &&
            Objects.equals(earnLabel6, that.earnLabel6) &&
            Objects.equals(earnAmount6, that.earnAmount6) &&
            Objects.equals(earnLabel7, that.earnLabel7) &&
            Objects.equals(earnAmount7, that.earnAmount7) &&
            Objects.equals(earnLabel8, that.earnLabel8) &&
            Objects.equals(earnAmount8, that.earnAmount8) &&
            Objects.equals(earnLabel9, that.earnLabel9) &&
            Objects.equals(earnAmount9, that.earnAmount9) &&
            Objects.equals(earnLabel10, that.earnLabel10) &&
            Objects.equals(earnAmount10, that.earnAmount10) &&
            Objects.equals(name, that.name) &&
            Objects.equals(designation, that.designation) &&
            Objects.equals(panNo, that.panNo) &&
            Objects.equals(dateOfBirth, that.dateOfBirth) &&
            Objects.equals(contactNumber, that.contactNumber) &&
            Objects.equals(emailId, that.emailId) &&
            Objects.equals(address, that.address);
    }

    public String getDeductCode1() {
        return deductCode1;
    }

    public void setDeductCode1(String deductCode1) {
        this.deductCode1 = deductCode1;
    }

    public String getDeductCode2() {
        return deductCode2;
    }

    public void setDeductCode2(String deductCode2) {
        this.deductCode2 = deductCode2;
    }

    public String getDeductCode3() {
        return deductCode3;
    }

    public void setDeductCode3(String deductCode3) {
        this.deductCode3 = deductCode3;
    }

    public String getDeductCode4() {
        return deductCode4;
    }

    public void setDeductCode4(String deductCode4) {
        this.deductCode4 = deductCode4;
    }

    public String getDeductCode5() {
        return deductCode5;
    }

    public void setDeductCode5(String deductCode5) {
        this.deductCode5 = deductCode5;
    }

    public String getDeductCode6() {
        return deductCode6;
    }

    public void setDeductCode6(String deductCode6) {
        this.deductCode6 = deductCode6;
    }

    public String getDeductCode7() {
        return deductCode7;
    }

    public void setDeductCode7(String deductCode7) {
        this.deductCode7 = deductCode7;
    }

    public String getDeductCode8() {
        return deductCode8;
    }

    public void setDeductCode8(String deductCode8) {
        this.deductCode8 = deductCode8;
    }

    public String getDeductCode9() {
        return deductCode9;
    }

    public void setDeductCode9(String deductCode9) {
        this.deductCode9 = deductCode9;
    }

    public String getDeductCode10() {
        return deductCode10;
    }

    public void setDeductCode10(String deductCode10) {
        this.deductCode10 = deductCode10;
    }

    public String getDeductCode11() {
        return deductCode11;
    }

    public void setDeductCode11(String deductCode11) {
        this.deductCode11 = deductCode11;
    }

    public String getDeductCode12() {
        return deductCode12;
    }

    public void setDeductCode12(String deductCode12) {
        this.deductCode12 = deductCode12;
    }

    public String getDeductCode13() {
        return deductCode13;
    }

    public void setDeductCode13(String deductCode13) {
        this.deductCode13 = deductCode13;
    }

    public String getDeductCode14() {
        return deductCode14;
    }

    public void setDeductCode14(String deductCode14) {
        this.deductCode14 = deductCode14;
    }

    public String getDeductCode15() {
        return deductCode15;
    }

    public void setDeductCode15(String deductCode15) {
        this.deductCode15 = deductCode15;
    }

    public String getDeductCode16() {
        return deductCode16;
    }

    public void setDeductCode16(String deductCode16) {
        this.deductCode16 = deductCode16;
    }

    public String getDeductCode17() {
        return deductCode17;
    }

    public void setDeductCode17(String deductCode17) {
        this.deductCode17 = deductCode17;
    }

    public String getDeductCode18() {
        return deductCode18;
    }

    public void setDeductCode18(String deductCode18) {
        this.deductCode18 = deductCode18;
    }

    public String getDeductCode19() {
        return deductCode19;
    }

    public void setDeductCode19(String deductCode19) {
        this.deductCode19 = deductCode19;
    }

    public String getDeductCode20() {
        return deductCode20;
    }

    public void setDeductCode20(String deductCode20) {
        this.deductCode20 = deductCode20;
    }

    public Double getDeductAmount1() {
        return deductAmount1;
    }

    public void setDeductAmount1(Double deductAmount1) {
        this.deductAmount1 = deductAmount1;
    }

    public Double getDeductAmount2() {
        return deductAmount2;
    }

    public void setDeductAmount2(Double deductAmount2) {
        this.deductAmount2 = deductAmount2;
    }

    public Double getDeductAmount3() {
        return deductAmount3;
    }

    public void setDeductAmount3(Double deductAmount3) {
        this.deductAmount3 = deductAmount3;
    }

    public Double getDeductAmount4() {
        return deductAmount4;
    }

    public void setDeductAmount4(Double deductAmount4) {
        this.deductAmount4 = deductAmount4;
    }

    public Double getDeductAmount5() {
        return deductAmount5;
    }

    public void setDeductAmount5(Double deductAmount5) {
        this.deductAmount5 = deductAmount5;
    }

    public Double getDeductAmount6() {
        return deductAmount6;
    }

    public void setDeductAmount6(Double deductAmount6) {
        this.deductAmount6 = deductAmount6;
    }

    public Double getDeductAmount7() {
        return deductAmount7;
    }

    public void setDeductAmount7(Double deductAmount7) {
        this.deductAmount7 = deductAmount7;
    }

    public Double getDeductAmount8() {
        return deductAmount8;
    }

    public void setDeductAmount8(Double deductAmount8) {
        this.deductAmount8 = deductAmount8;
    }

    public Double getDeductAmount9() {
        return deductAmount9;
    }

    public void setDeductAmount9(Double deductAmount9) {
        this.deductAmount9 = deductAmount9;
    }

    public Double getDeductAmount10() {
        return deductAmount10;
    }

    public void setDeductAmount10(Double deductAmount10) {
        this.deductAmount10 = deductAmount10;
    }

    public Double getDeductAmount11() {
        return deductAmount11;
    }

    public void setDeductAmount11(Double deductAmount11) {
        this.deductAmount11 = deductAmount11;
    }

    public Double getDeductAmount12() {
        return deductAmount12;
    }

    public void setDeductAmount12(Double deductAmount12) {
        this.deductAmount12 = deductAmount12;
    }

    public Double getDeductAmount13() {
        return deductAmount13;
    }

    public void setDeductAmount13(Double deductAmount13) {
        this.deductAmount13 = deductAmount13;
    }

    public Double getDeductAmount14() {
        return deductAmount14;
    }

    public void setDeductAmount14(Double deductAmount14) {
        this.deductAmount14 = deductAmount14;
    }

    public Double getDeductAmount15() {
        return deductAmount15;
    }

    public void setDeductAmount15(Double deductAmount15) {
        this.deductAmount15 = deductAmount15;
    }

    public Double getDeductAmount16() {
        return deductAmount16;
    }

    public void setDeductAmount16(Double deductAmount16) {
        this.deductAmount16 = deductAmount16;
    }

    public Double getDeductAmount17() {
        return deductAmount17;
    }

    public void setDeductAmount17(Double deductAmount17) {
        this.deductAmount17 = deductAmount17;
    }

    public Double getDeductAmount18() {
        return deductAmount18;
    }

    public void setDeductAmount18(Double deductAmount18) {
        this.deductAmount18 = deductAmount18;
    }

    public Double getDeductAmount19() {
        return deductAmount19;
    }

    public void setDeductAmount19(Double deductAmount19) {
        this.deductAmount19 = deductAmount19;
    }

    public Double getDeductAmount20() {
        return deductAmount20;
    }

    public void setDeductAmount20(Double deductAmount20) {
        this.deductAmount20 = deductAmount20;
    }

    public Double getTotalTaxIncome() {
        return totalTaxIncome;
    }

    public void setTotalTaxIncome(Double totalTaxIncome) {
        this.totalTaxIncome = totalTaxIncome;
    }

    public Integer getPendingMonth() {
        return pendingMonth;
    }

    public void setPendingMonth(Integer pendingMonth) {
        this.pendingMonth = pendingMonth;
    }

    public Instant getProcessDate() { return processDate; }

    public void setProcessDate(Instant processDate) {
        this.processDate = processDate;
    }

    public Boolean getLocked() { return locked; }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<TdsSlabMaster> getTdsSlabMasters() {
        return tdsSlabMasters;
    }

    public void setTdsSlabMasters(List<TdsSlabMaster> tdsSlabMasters) {
        this.tdsSlabMasters = tdsSlabMasters;
    }

    public Double getTotalEarnAmount() {
        return totalEarnAmount;
    }

    public void setTotalEarnAmount(Double totalEarnAmount) {
        this.totalEarnAmount = totalEarnAmount;
    }

    public Boolean getRegime() {
        return regime;
    }

    public void setRegime(Boolean regime) {
        this.regime = regime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, financialYear, cardNo, earnLabel1, earnAmount1, earnLabel2, earnAmount2, earnLabel3, earnAmount3, earnLabel4, earnAmount4, earnLabel5, earnAmount5, earnLabel6, earnAmount6, earnLabel7, earnAmount7, earnLabel8, earnAmount8, earnLabel9, earnAmount9, earnLabel10, earnAmount10, name, designation, panNo, dateOfBirth, contactNumber, emailId, address);
    }

    @Override
    public String toString() {
        return "TdsComputationBean{" +
            "id=" + id +
            ", financialYear='" + financialYear + '\'' +
            ", cardNo='" + cardNo + '\'' +
            ", earnLabel1='" + earnLabel1 + '\'' +
            ", earnAmount1=" + earnAmount1 +
            ", earnLabel2='" + earnLabel2 + '\'' +
            ", earnAmount2=" + earnAmount2 +
            ", earnLabel3='" + earnLabel3 + '\'' +
            ", earnAmount3=" + earnAmount3 +
            ", earnLabel4='" + earnLabel4 + '\'' +
            ", earnAmount4=" + earnAmount4 +
            ", earnLabel5='" + earnLabel5 + '\'' +
            ", earnAmount5=" + earnAmount5 +
            ", earnLabel6='" + earnLabel6 + '\'' +
            ", earnAmount6=" + earnAmount6 +
            ", earnLabel7='" + earnLabel7 + '\'' +
            ", earnAmount7=" + earnAmount7 +
            ", earnLabel8='" + earnLabel8 + '\'' +
            ", earnAmount8=" + earnAmount8 +
            ", earnLabel9='" + earnLabel9 + '\'' +
            ", earnAmount9=" + earnAmount9 +
            ", earnLabel10='" + earnLabel10 + '\'' +
            ", earnAmount10=" + earnAmount10 +
            ", name='" + name + '\'' +
            ", designation='" + designation + '\'' +
            ", panNo='" + panNo + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", contactNumber='" + contactNumber + '\'' +
            ", emailId='" + emailId + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
