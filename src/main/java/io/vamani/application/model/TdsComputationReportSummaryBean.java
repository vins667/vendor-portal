package io.vamani.application.model;

public class TdsComputationReportSummaryBean {
	private String cardNo;
    private String name;
    private String subCodeDesc;
    private String factoryDesc;
    private String doj;
    private Double totalTaxLiability;
    private Double balanceTaxValue;
    private Double totalTaxIncome;
    private Double taxDeductValue;
    
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
	public String getSubCodeDesc() {
		return subCodeDesc;
	}
	public void setSubCodeDesc(String subCodeDesc) {
		this.subCodeDesc = subCodeDesc;
	}
	public String getFactoryDesc() {
		return factoryDesc;
	}
	public void setFactoryDesc(String factoryDesc) {
		this.factoryDesc = factoryDesc;
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
	public Double getTaxDeductValue() {
		return taxDeductValue;
	}
	public void setTaxDeductValue(Double taxDeductValue) {
		this.taxDeductValue = taxDeductValue;
	}
	public Double getTotalTaxIncome() {
		return totalTaxIncome;
	}
	public void setTotalTaxIncome(Double totalTaxIncome) {
		this.totalTaxIncome = totalTaxIncome;
	}

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }
}
