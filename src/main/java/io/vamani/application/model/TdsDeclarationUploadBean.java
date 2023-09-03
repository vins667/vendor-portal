package io.vamani.application.model;
import java.time.ZonedDateTime;
import java.util.List;

public class TdsDeclarationUploadBean {
    private Long id;
    private String cardNo;
    private String financialYear;
    private String name;
    private String contactNumber;
    private String emailId;
    private String department;
    private String designation;
    private String factoryCode;
    private String factory;
    private String panNo;
    private ZonedDateTime dateOfBirth;
    private String address;
    private String monthRent;
    private String landLoardName;
    private String landLoardPanNo;
    private String landLoardAddress;
    private String status;
    private List<TdsGroupMasterBeans> tdsGroupMasterbean;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
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

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TdsGroupMasterBeans> getTdsGroupMasterbean() {
		return tdsGroupMasterbean;
	}
	public void setTdsGroupMasterbean(List<TdsGroupMasterBeans> tdsGroupMasterbean) {
		this.tdsGroupMasterbean = tdsGroupMasterbean;
	}


}
