package io.vamani.application.mssql.mobile;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

public class EmployeeView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;

    private String empCode;

    private String name;

    private String subCode;

    private String subSname;

    private String subCodeDesc;

    private String subCodeAddress;

    private String imagePath;

    private String sharedImagePath;

    private String depCode;

    private String depCodeDesc;

    private String desCode;

    private String desCodeDesc;

    private String add1;

    private String gCode;

    private ZonedDateTime doj;

    private ZonedDateTime dob;

    private String cardNo;

    private String email;

    private String phone;

    private String payCode;

    private String pan;

    private String sftCode;

    private String adhNo;

    private String supervisor;

    private String fName;

    private String bankCode;

    private String bankName;

    private String bankNo;

    private String pfNo;

    private String esiNo;

    private String uan;

    private String payMode;

    private String mf;

    private Double totSal;

    private Boolean exist;

    private String errorMessage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getLogin() {
        return login;
    }

    public EmployeeView login(String login) {
        this.login = login.toUpperCase();
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmpCode() {
        return empCode;
    }

    public EmployeeView empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getName() {
        return name;
    }

    public EmployeeView name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubCode() {
        return subCode;
    }

    public EmployeeView subCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubSname() {
        return subSname;
    }

    public EmployeeView subSname(String subSname) {
        this.subSname = subSname;
        return this;
    }

    public void setSubSname(String subSname) {
        this.subSname = subSname;
    }

    public String getSubCodeDesc() {
        return subCodeDesc;
    }

    public void setSubCodeDesc(String subCodeDesc) {
        this.subCodeDesc = subCodeDesc;
    }

    public EmployeeView subCodeDesc(String subCodeDesc) {
        this.subCodeDesc = subCodeDesc;
        return this;
    }

    public String getSubCodeAddress() {
        return subCodeAddress;
    }

    public void setSubCodeAddress(String subCodeAddress) {
        this.subCodeAddress = subCodeAddress;
    }

    public EmployeeView subCodeAddress(String subCodeAddress) {
        this.subCodeAddress = subCodeAddress;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public EmployeeView imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDepCode() {
        return depCode;
    }

    public EmployeeView depCode(String depCode) {
        this.depCode = depCode;
        return this;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepCodeDesc() {
        return depCodeDesc;
    }

    public EmployeeView depCodeDesc(String depCodeDesc) {
        this.depCodeDesc = depCodeDesc;
        return this;
    }

    public void setDepCodeDesc(String depCodeDesc) {
        this.depCodeDesc = depCodeDesc;
    }

    public String getDesCode() {
        return desCode;
    }

    public EmployeeView desCode(String desCode) {
        this.desCode = desCode;
        return this;
    }

    public void setDesCode(String desCode) {
        this.desCode = desCode;
    }

    public String getDesCodeDesc() {
        return desCodeDesc;
    }

    public EmployeeView desCodeDesc(String desCodeDesc) {
        this.desCodeDesc = desCodeDesc;
        return this;
    }

    public void setDesCodeDesc(String desCodeDesc) {
        this.desCodeDesc = desCodeDesc;
    }

    public String getAdd1() {
        return add1;
    }

    public EmployeeView add1(String add1) {
        this.add1 = add1;
        return this;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getgCode() {
        return gCode;
    }

    public EmployeeView gCode(String gCode) {
        this.gCode = gCode;
        return this;
    }

    public void setgCode(String gCode) {
        this.gCode = gCode;
    }

    public ZonedDateTime getDoj() {
        return doj;
    }

    public EmployeeView doj(ZonedDateTime doj) {
        this.doj = doj;
        return this;
    }

    public void setDoj(ZonedDateTime doj) {
        this.doj = doj;
    }

    public ZonedDateTime getDob() {
        return dob;
    }

    public EmployeeView dob(ZonedDateTime dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(ZonedDateTime dob) {
        this.dob = dob;
    }

    public String getCardNo() {
        return cardNo;
    }

    public EmployeeView cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeView email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public EmployeeView phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayCode() {
        return payCode;
    }

    public EmployeeView payCode(String payCode) {
        this.payCode = payCode;
        return this;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPan() {
        return pan;
    }

    public EmployeeView pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSftCode() { return sftCode; }

    public void setSftCode(String sftCode) { this.sftCode = sftCode; }

    public EmployeeView sftCode(String sftCode) {
        this.sftCode = sftCode;
        return this;
    }

    public String getSupervisor() { return supervisor; }

    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }

    public EmployeeView supervisor(String supervisor) {
        this.supervisor = supervisor;
        return this;
    }

    public String getAdhNo() { return adhNo; }

    public void setAdhNo(String adhNo) { this.adhNo = adhNo; }

    public EmployeeView adhNo(String adhNo) {
        this.adhNo = adhNo;
        return this;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getPfNo() {
        return pfNo;
    }

    public void setPfNo(String pfNo) {
        this.pfNo = pfNo;
    }

    public String getEsiNo() {
        return esiNo;
    }

    public void setEsiNo(String esiNo) {
        this.esiNo = esiNo;
    }

    public String getUan() {
        return uan;
    }

    public void setUan(String uan) {
        this.uan = uan;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public Double getTotSal() {
        return totSal;
    }

    public void setTotSal(Double totSal) {
        this.totSal = totSal;
    }

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }

    public String getSharedImagePath() {
        return sharedImagePath;
    }

    public void setSharedImagePath(String sharedImagePath) {
        this.sharedImagePath = sharedImagePath;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
        EmployeeView employeeView = (EmployeeView) o;
        if (employeeView.getLogin() == null || getLogin() == null) {
            return false;
        }
        return Objects.equals(getLogin(), employeeView.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLogin());
    }

    @Override
    public String toString() {
        return "EmployeeView{" +
            ", login='" + getLogin() + "'" +
            ", empCode='" + getEmpCode() + "'" +
            ", name='" + getName() + "'" +
            ", subCode='" + getSubCode() + "'" +
            ", subSname='" + getSubSname() + "'" +
            ", subCodeDesc='" + getSubCodeDesc() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", depCode='" + getDepCode() + "'" +
            ", depCodeDesc='" + getDepCodeDesc() + "'" +
            ", desCode='" + getDesCode() + "'" +
            ", desCodeDesc='" + getDesCodeDesc() + "'" +
            ", add1='" + getAdd1() + "'" +
            ", gCode='" + getgCode() + "'" +
            ", doj='" + getDoj() + "'" +
            ", dob='" + getDob() + "'" +
            ", card_no='" + getCardNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", payCode='" + getPayCode() + "'" +
            ", pan='" + getPan() + "'" +
            "}";
    }
}
