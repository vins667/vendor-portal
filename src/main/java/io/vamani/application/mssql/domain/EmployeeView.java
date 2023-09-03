package io.vamani.application.mssql.domain;


import io.vamani.application.util.MD5UrlEncryption;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * A EmployeeView.
 */
@Entity
@Table(name = "employee_view")
public class EmployeeView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "factory")
    private String factory;

    @Column(name = "factory_desc")
    private String factoryDesc;

    @NotNull
    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "name")
    private String name;

    @Column(name = "sub_code")
    private String subCode;

    @Column(name = "sub_sname")
    private String subSname;

    @Column(name = "sub_code_desc")
    private String subCodeDesc;

    @Column(name = "sub_code_address")
    private String subCodeAddress;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "shared_image_path")
    private String sharedImagePath;

    @Column(name = "dep_code")
    private String depCode;

    @Column(name = "dep_code_desc")
    private String depCodeDesc;

    @Column(name = "des_code")
    private String desCode;

    @Column(name = "des_code_desc")
    private String desCodeDesc;

    @Column(name = "add1")
    private String add1;

    @Column(name = "g_code")
    private String gCode;

    @Column(name = "doj")
    private ZonedDateTime doj;

    @Column(name = "dob")
    private ZonedDateTime dob;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "pay_code")
    private String payCode;

    @Column(name = "pan")
    private String pan;

    @Column(name = "sft_code")
    private String sftCode;

    @Column(name = "adh_no")
    private String adhNo;

    @Column(name = "supervisor")
    private String supervisor;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_no")
    private String bankNo;

    @Column(name = "pf_no")
    private String pfNo;

    @Column(name = "esi_no")
    private String esiNo;

    @Column(name = "uan")
    private String uan;

    @Column(name = "pay_mode")
    private String payMode;

    @Column(name = "m_f")
    private String mf;

    @Column(name = "tot_sal")
    private Double totSal;

    @Column(name = "search_text")
    private String searchText;

    @Column(name = "pf_vol_fix")
    private double pfVolFix;

    @Column(name = "pfallowed")
    private short pfallowed;


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
        if(imagePath != null) {
            try {
                return MD5UrlEncryption.fakeUrl(imagePath);
            } catch(Exception e) {}
        } else {
            return "";
        }
        return "";
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

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public double getPfVolFix() {
        return pfVolFix;
    }

    public void setPfVolFix(double pfVolFix) {
        this.pfVolFix = pfVolFix;
    }

    public short getPfallowed() {
        return pfallowed;
    }

    public void setPfallowed(short pfallowed) {
        this.pfallowed = pfallowed;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeView that = (EmployeeView) o;
        return Double.compare(that.pfVolFix, pfVolFix) == 0 && pfallowed == that.pfallowed && Objects.equals(login, that.login) && Objects.equals(factory, that.factory) && Objects.equals(factoryDesc, that.factoryDesc) && Objects.equals(empCode, that.empCode) && Objects.equals(name, that.name) && Objects.equals(subCode, that.subCode) && Objects.equals(subSname, that.subSname) && Objects.equals(subCodeDesc, that.subCodeDesc) && Objects.equals(subCodeAddress, that.subCodeAddress) && Objects.equals(imagePath, that.imagePath) && Objects.equals(sharedImagePath, that.sharedImagePath) && Objects.equals(depCode, that.depCode) && Objects.equals(depCodeDesc, that.depCodeDesc) && Objects.equals(desCode, that.desCode) && Objects.equals(desCodeDesc, that.desCodeDesc) && Objects.equals(add1, that.add1) && Objects.equals(gCode, that.gCode) && Objects.equals(doj, that.doj) && Objects.equals(dob, that.dob) && Objects.equals(cardNo, that.cardNo) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(payCode, that.payCode) && Objects.equals(pan, that.pan) && Objects.equals(sftCode, that.sftCode) && Objects.equals(adhNo, that.adhNo) && Objects.equals(supervisor, that.supervisor) && Objects.equals(fName, that.fName) && Objects.equals(bankCode, that.bankCode) && Objects.equals(bankName, that.bankName) && Objects.equals(bankNo, that.bankNo) && Objects.equals(pfNo, that.pfNo) && Objects.equals(esiNo, that.esiNo) && Objects.equals(uan, that.uan) && Objects.equals(payMode, that.payMode) && Objects.equals(mf, that.mf) && Objects.equals(totSal, that.totSal) && Objects.equals(searchText, that.searchText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, factory, factoryDesc, empCode, name, subCode, subSname, subCodeDesc, subCodeAddress, imagePath, sharedImagePath, depCode, depCodeDesc, desCode, desCodeDesc, add1, gCode, doj, dob, cardNo, email, phone, payCode, pan, sftCode, adhNo, supervisor, fName, bankCode, bankName, bankNo, pfNo, esiNo, uan, payMode, mf, totSal, searchText, pfVolFix, pfallowed);
    }

    @Override
    public String toString() {
        return "EmployeeView{" +
            "login='" + login + '\'' +
            ", factory='" + factory + '\'' +
            ", factoryDesc='" + factoryDesc + '\'' +
            ", empCode='" + empCode + '\'' +
            ", name='" + name + '\'' +
            ", subCode='" + subCode + '\'' +
            ", subSname='" + subSname + '\'' +
            ", subCodeDesc='" + subCodeDesc + '\'' +
            ", subCodeAddress='" + subCodeAddress + '\'' +
            ", imagePath='" + imagePath + '\'' +
            ", sharedImagePath='" + sharedImagePath + '\'' +
            ", depCode='" + depCode + '\'' +
            ", depCodeDesc='" + depCodeDesc + '\'' +
            ", desCode='" + desCode + '\'' +
            ", desCodeDesc='" + desCodeDesc + '\'' +
            ", add1='" + add1 + '\'' +
            ", gCode='" + gCode + '\'' +
            ", doj=" + doj +
            ", dob=" + dob +
            ", cardNo='" + cardNo + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", payCode='" + payCode + '\'' +
            ", pan='" + pan + '\'' +
            ", sftCode='" + sftCode + '\'' +
            ", adhNo='" + adhNo + '\'' +
            ", supervisor='" + supervisor + '\'' +
            ", fName='" + fName + '\'' +
            ", bankCode='" + bankCode + '\'' +
            ", bankName='" + bankName + '\'' +
            ", bankNo='" + bankNo + '\'' +
            ", pfNo='" + pfNo + '\'' +
            ", esiNo='" + esiNo + '\'' +
            ", uan='" + uan + '\'' +
            ", payMode='" + payMode + '\'' +
            ", mf='" + mf + '\'' +
            ", totSal=" + totSal +
            ", searchText='" + searchText + '\'' +
            ", pfVolFix=" + pfVolFix +
            ", pfallowed=" + pfallowed +
            '}';
    }
}
