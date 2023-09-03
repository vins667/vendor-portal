package io.vamani.application.model;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import io.vamani.application.domain.DeliveryChallanDetails;
public class DeliveryChallanBean {
	private Long id;
    private String factCode;
    private String factDescription;
    private String fAddressLine1;
    private String fAddressLine2;
    private String fAddressLine3;
    private String fAddressLine4;
    private String fAddressLine5;
    private String postalCode;
    private String town;
    private String district;
    private String state;
    private String stateCode;
    private String fGSTNumber;
    private String showAddress1;
    private String showAddress2;
    private String suppliercode;
    private String bLegalname1;
    private String bAddressLine1;
    private String bAddressLine2;
    private String bAddressLine3;
    private String bAddressLine4;
    private String bAddressLine5;
    private String bPostalCode;
    private String bTown;
    private String bDistrict;
    private String bState;
    private String bStateCode;
    private String bGSTNumber;
    private String challanType;
    private LocalDate challanDate;
    private String eWayBillNo;
    private LocalDate eWayBillDate;
    private LocalDate expReturnDate;
    private LocalDate acReturnDate;
    private String remarks;
    private String flag;
    private String createdBy;
    private Instant createdDate;
    private String lastUpdatedBy;
    private Instant lastUpdatedDate;
    private String approvedBy;
    private Instant approvedDate;
    private List<DeliveryChallanDetails> deliveryChallanDetails;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFactCode() {
		return factCode;
	}
	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}
	public String getFactDescription() {
		return factDescription;
	}
	public void setFactDescription(String factDescription) {
		this.factDescription = factDescription;
	}
	public String getfAddressLine1() {
		return fAddressLine1;
	}
	public void setfAddressLine1(String fAddressLine1) {
		this.fAddressLine1 = fAddressLine1;
	}
	public String getfAddressLine2() {
		return fAddressLine2;
	}
	public void setfAddressLine2(String fAddressLine2) {
		this.fAddressLine2 = fAddressLine2;
	}
	public String getfAddressLine3() {
		return fAddressLine3;
	}
	public void setfAddressLine3(String fAddressLine3) {
		this.fAddressLine3 = fAddressLine3;
	}
	public String getfAddressLine4() {
		return fAddressLine4;
	}
	public void setfAddressLine4(String fAddressLine4) {
		this.fAddressLine4 = fAddressLine4;
	}
	public String getfAddressLine5() {
		return fAddressLine5;
	}
	public void setfAddressLine5(String fAddressLine5) {
		this.fAddressLine5 = fAddressLine5;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getfGSTNumber() {
		return fGSTNumber;
	}
	public void setfGSTNumber(String fGSTNumber) {
		this.fGSTNumber = fGSTNumber;
	}
	
	public String getSuppliercode() {
		return suppliercode;
	}
	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}
	public String getbLegalname1() {
		return bLegalname1;
	}
	public void setbLegalname1(String bLegalname1) {
		this.bLegalname1 = bLegalname1;
	}
	public String getbAddressLine1() {
		return bAddressLine1;
	}
	public void setbAddressLine1(String bAddressLine1) {
		this.bAddressLine1 = bAddressLine1;
	}
	public String getbAddressLine2() {
		return bAddressLine2;
	}
	public void setbAddressLine2(String bAddressLine2) {
		this.bAddressLine2 = bAddressLine2;
	}
	public String getbAddressLine3() {
		return bAddressLine3;
	}
	public void setbAddressLine3(String bAddressLine3) {
		this.bAddressLine3 = bAddressLine3;
	}
	public String getbAddressLine4() {
		return bAddressLine4;
	}
	public void setbAddressLine4(String bAddressLine4) {
		this.bAddressLine4 = bAddressLine4;
	}
	public String getbAddressLine5() {
		return bAddressLine5;
	}
	public void setbAddressLine5(String bAddressLine5) {
		this.bAddressLine5 = bAddressLine5;
	}
	public String getbPostalCode() {
		return bPostalCode;
	}
	public void setbPostalCode(String bPostalCode) {
		this.bPostalCode = bPostalCode;
	}
	public String getbTown() {
		return bTown;
	}
	public void setbTown(String bTown) {
		this.bTown = bTown;
	}
	public String getbDistrict() {
		return bDistrict;
	}
	public void setbDistrict(String bDistrict) {
		this.bDistrict = bDistrict;
	}
	public String getbState() {
		return bState;
	}
	public void setbState(String bState) {
		this.bState = bState;
	}
	public String getbStateCode() {
		return bStateCode;
	}
	public void setbStateCode(String bStateCode) {
		this.bStateCode = bStateCode;
	}
	public String getbGSTNumber() {
		return bGSTNumber;
	}
	public void setbGSTNumber(String bGSTNumber) {
		this.bGSTNumber = bGSTNumber;
	}
	public String getChallanType() {
		return challanType;
	}
	public void setChallanType(String challanType) {
		this.challanType = challanType;
	}
	public LocalDate getChallanDate() {
		return challanDate;
	}
	public void setChallanDate(LocalDate challanDate) {
		this.challanDate = challanDate;
	}
	public String geteWayBillNo() {
		return eWayBillNo;
	}
	public void seteWayBillNo(String eWayBillNo) {
		this.eWayBillNo = eWayBillNo;
	}
	public LocalDate geteWayBillDate() {
		return eWayBillDate;
	}
	public void seteWayBillDate(LocalDate eWayBillDate) {
		this.eWayBillDate = eWayBillDate;
	}
	public LocalDate getExpReturnDate() {
		return expReturnDate;
	}
	public void setExpReturnDate(LocalDate expReturnDate) {
		this.expReturnDate = expReturnDate;
	}
	public LocalDate getAcReturnDate() {
		return acReturnDate;
	}
	public void setAcReturnDate(LocalDate acReturnDate) {
		this.acReturnDate = acReturnDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getShowAddress1() {
		return showAddress1;
	}
	public void setShowAddress1(String showAddress1) {
		this.showAddress1 = showAddress1;
	}
	public String getShowAddress2() {
		return showAddress2;
	}
	public void setShowAddress2(String showAddress2) {
		this.showAddress2 = showAddress2;
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
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Instant getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Instant approvedDate) {
		this.approvedDate = approvedDate;
	}
	public List<DeliveryChallanDetails> getDeliveryChallanDetails() {
		return deliveryChallanDetails;
	}
	public void setDeliveryChallanDetails(List<DeliveryChallanDetails> deliveryChallanDetails) {
		this.deliveryChallanDetails = deliveryChallanDetails;
	}    

}
