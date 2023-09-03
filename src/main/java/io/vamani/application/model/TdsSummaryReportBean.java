package io.vamani.application.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class TdsSummaryReportBean implements Serializable {
    private String factoryDesc;
    private String cardNo;
    private String name;
    private String doj;
    private String resignDate;
    private String pan;
    private Double paidDays;
    private Double basic;
    private Double hra;
    private Double convAllow;
    private Double others;
    private Double specialAllow;
    private Double medical;
    private Double grossSalary;
    private Double previousEmployerSalary;
    private Double previousEmployerTax;
    private String uploadInformation;
    private Double uploadInformationAmount;
    private Double upload80EAmount;
    private Double upload80DDAmount;
    private String regime;
    private String groupCode;
    private Double tax;
    private Double monthlyDed;
    private Double balance;
    private Double totalTaxLiability;
    private Double balanceTaxValue;
    private Double totalTaxIncome;
    private Double taxDeductValue;
    private Integer pendingMonth;
    private Integer monthNo;
    private String monthYear;
    private Double tdsAmount;
    private Double rentExempt;

    public String getFactoryDesc() {
        return factoryDesc;
    }

    public void setFactoryDesc(String factoryDesc) {
        this.factoryDesc = factoryDesc;
    }

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

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getResignDate() {
        return resignDate;
    }

    public void setResignDate(String resignDate) {
        this.resignDate = resignDate;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Double getPaidDays() {
        return paidDays;
    }

    public void setPaidDays(Double paidDays) {
        this.paidDays = paidDays;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getHra() {
        return hra;
    }

    public void setHra(Double hra) {
        this.hra = hra;
    }

    public Double getConvAllow() {
        return convAllow;
    }

    public void setConvAllow(Double convAllow) {
        this.convAllow = convAllow;
    }

    public Double getOthers() {
        return others;
    }

    public void setOthers(Double others) {
        this.others = others;
    }

    public Double getSpecialAllow() {
        return specialAllow;
    }

    public void setSpecialAllow(Double specialAllow) {
        this.specialAllow = specialAllow;
    }

    public Double getMedical() {
        return medical;
    }

    public void setMedical(Double medical) {
        this.medical = medical;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Double getPreviousEmployerSalary() {
        return previousEmployerSalary;
    }

    public void setPreviousEmployerSalary(Double previousEmployerSalary) {
        this.previousEmployerSalary = previousEmployerSalary;
    }

    public Double getPreviousEmployerTax() {
        return previousEmployerTax;
    }

    public void setPreviousEmployerTax(Double previousEmployerTax) {
        this.previousEmployerTax = previousEmployerTax;
    }

    public String getUploadInformation() {
        return uploadInformation;
    }

    public void setUploadInformation(String uploadInformation) {
        this.uploadInformation = uploadInformation;
    }

    public Double getUploadInformationAmount() {
        return uploadInformationAmount;
    }

    public void setUploadInformationAmount(Double uploadInformationAmount) {
        this.uploadInformationAmount = uploadInformationAmount;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getMonthlyDed() {
        return monthlyDed;
    }

    public void setMonthlyDed(Double monthlyDed) {
        this.monthlyDed = monthlyDed;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotalTaxLiability() {
        return totalTaxLiability;
    }

    public void setTotalTaxLiability(Double totalTaxLiability) {
        this.totalTaxLiability = totalTaxLiability;
    }

    public Double getBalanceTaxValue() {
        return balanceTaxValue;
    }

    public void setBalanceTaxValue(Double balanceTaxValue) {
        this.balanceTaxValue = balanceTaxValue;
    }

    public Double getTotalTaxIncome() {
        return totalTaxIncome;
    }

    public void setTotalTaxIncome(Double totalTaxIncome) {
        this.totalTaxIncome = totalTaxIncome;
    }

    public Double getTaxDeductValue() {
        return taxDeductValue;
    }

    public void setTaxDeductValue(Double taxDeductValue) {
        this.taxDeductValue = taxDeductValue;
    }

    public Integer getPendingMonth() {
        return pendingMonth;
    }

    public void setPendingMonth(Integer pendingMonth) {
        this.pendingMonth = pendingMonth;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public Double getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(Double tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public Integer getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(Integer monthNo) {
        this.monthNo = monthNo;
    }

    public Double getRentExempt() {
        return rentExempt;
    }

    public void setRentExempt(Double rentExempt) {
        this.rentExempt = rentExempt;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Double getUpload80EAmount() {
        return upload80EAmount;
    }

    public void setUpload80EAmount(Double upload80EAmount) {
        this.upload80EAmount = upload80EAmount;
    }

    public Double getUpload80DDAmount() {
        return upload80DDAmount;
    }

    public void setUpload80DDAmount(Double upload80DDAmount) {
        this.upload80DDAmount = upload80DDAmount;
    }
}
