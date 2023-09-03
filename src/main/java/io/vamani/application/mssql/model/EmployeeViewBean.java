package io.vamani.application.mssql.model;


import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A EmployeeView.
 */
public class EmployeeViewBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;

    private String empCode;
    private String factory;
    private String factoryDesc;

    private BigDecimal salary;
    private String name;

    private String subCode;

    private String subSname;

    private String subCodeDesc;

    private String subCodeAddress;

    private String imagePath;

    private String depCode;

    private String depCodeDesc;

    private String desCode;

    private String desCodeDesc;

    private String add1;

    private String gCode;

    private ZonedDateTime doj;

    private String dojFormat;

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

    private ZonedDateTime resignDate;

    private String tempLock;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getLogin() {
        return login;
    }

    public EmployeeViewBean login(String login) {
        this.login = login.toUpperCase();
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getFactoryDesc() {
        return factoryDesc;
    }

    public void setFactoryDesc(String factoryDesc) {
        this.factoryDesc = factoryDesc;
    }

    public String getEmpCode() {
        return empCode;
    }

    public EmployeeViewBean empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public BigDecimal getSalary() {
        return salary;
    }
    public EmployeeViewBean salary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public EmployeeViewBean name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubCode() {
        return subCode;
    }

    public EmployeeViewBean subCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubSname() {
        return subSname;
    }

    public EmployeeViewBean subSname(String subSname) {
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

    public EmployeeViewBean subCodeDesc(String subCodeDesc) {
        this.subCodeDesc = subCodeDesc;
        return this;
    }

    public String getSubCodeAddress() {
        return subCodeAddress;
    }

    public void setSubCodeAddress(String subCodeAddress) {
        this.subCodeAddress = subCodeAddress;
    }

    public EmployeeViewBean subCodeAddress(String subCodeAddress) {
        this.subCodeAddress = subCodeAddress;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public EmployeeViewBean imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDepCode() {
        return depCode;
    }

    public EmployeeViewBean depCode(String depCode) {
        this.depCode = depCode;
        return this;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepCodeDesc() {
        return depCodeDesc;
    }

    public EmployeeViewBean depCodeDesc(String depCodeDesc) {
        this.depCodeDesc = depCodeDesc;
        return this;
    }

    public void setDepCodeDesc(String depCodeDesc) {
        this.depCodeDesc = depCodeDesc;
    }

    public String getDesCode() {
        return desCode;
    }

    public EmployeeViewBean desCode(String desCode) {
        this.desCode = desCode;
        return this;
    }

    public void setDesCode(String desCode) {
        this.desCode = desCode;
    }

    public String getDesCodeDesc() {
        return desCodeDesc;
    }

    public EmployeeViewBean desCodeDesc(String desCodeDesc) {
        this.desCodeDesc = desCodeDesc;
        return this;
    }

    public void setDesCodeDesc(String desCodeDesc) {
        this.desCodeDesc = desCodeDesc;
    }

    public String getAdd1() {
        return add1;
    }

    public EmployeeViewBean add1(String add1) {
        this.add1 = add1;
        return this;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getgCode() {
        return gCode;
    }

    public EmployeeViewBean gCode(String gCode) {
        this.gCode = gCode;
        return this;
    }

    public void setgCode(String gCode) {
        this.gCode = gCode;
    }

    public ZonedDateTime getDoj() {
        return doj;
    }

    public EmployeeViewBean doj(ZonedDateTime doj) {
        this.doj = doj;
        return this;
    }

    public void setDoj(ZonedDateTime doj) {
        this.doj = doj;
    }

    public ZonedDateTime getDob() {
        return dob;
    }

    public EmployeeViewBean dob(ZonedDateTime dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(ZonedDateTime dob) {
        this.dob = dob;
    }

    public String getCardNo() {
        return cardNo;
    }

    public EmployeeViewBean cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeViewBean email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public EmployeeViewBean phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayCode() {
        return payCode;
    }

    public EmployeeViewBean payCode(String payCode) {
        this.payCode = payCode;
        return this;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPan() {
        return pan;
    }

    public EmployeeViewBean pan(String pan) {
        this.pan = pan;
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSftCode() { return sftCode; }

    public void setSftCode(String sftCode) { this.sftCode = sftCode; }

    public EmployeeViewBean sftCode(String sftCode) {
        this.sftCode = sftCode;
        return this;
    }

    public String getSupervisor() { return supervisor; }

    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }

    public EmployeeViewBean supervisor(String supervisor) {
        this.supervisor = supervisor;
        return this;
    }

    public String getAdhNo() { return adhNo; }

    public void setAdhNo(String adhNo) { this.adhNo = adhNo; }

    public EmployeeViewBean adhNo(String adhNo) {
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

    public String getTempLock() {
        return tempLock;
    }

    public void setTempLock(String tempLock) {
        this.tempLock = tempLock;
    }

    public ZonedDateTime getResignDate() {
        return resignDate;
    }

    public void setResignDate(ZonedDateTime resignDate) {
        this.resignDate = resignDate;
    }

    public String getDojFormat() {
        return dojFormat;
    }

    public void setDojFormat(String dojFormat) {
        this.dojFormat = dojFormat;
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
        EmployeeViewBean employeeViewBean = (EmployeeViewBean) o;
        if (employeeViewBean.getLogin() == null || getLogin() == null) {
            return false;
        }
        return Objects.equals(getLogin(), employeeViewBean.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLogin());
    }

    @Override
    public String toString() {
        return "EmployeeViewBean{" +
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
