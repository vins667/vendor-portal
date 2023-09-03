package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DI_DIRBILLOREDERPARTNER")
public class DiDirbillorederpartner {
    private String customersuppliercompanycode;
    private String customersuppliertype;
    private String customersuppliercode;
    private String paymentmethodcode;
    private String legalname1;
    private String statecode;
    private String address;
    private String gstinnumber;
    private String addressee;

    private String countrycode;

    @Basic
    @Column(name = "CUSTOMERSUPPLIERCOMPANYCODE", nullable = false, length = 3)
    public String getCustomersuppliercompanycode() {
        return customersuppliercompanycode;
    }

    public void setCustomersuppliercompanycode(String customersuppliercompanycode) {
        this.customersuppliercompanycode = customersuppliercompanycode;
    }

    @Basic
    @Column(name = "CUSTOMERSUPPLIERTYPE", nullable = false, length = 1)
    public String getCustomersuppliertype() {
        return customersuppliertype;
    }

    public void setCustomersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
    }

    @Basic
    @Column(name = "CUSTOMERSUPPLIERCODE", nullable = false, length = 8)
    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    @Basic
    @Column(name = "PAYMENTMETHODCODE", nullable = true, length = 3)
    public String getPaymentmethodcode() {
        return paymentmethodcode;
    }

    public void setPaymentmethodcode(String paymentmethodcode) {
        this.paymentmethodcode = paymentmethodcode;
    }

    @Basic
    @Column(name = "LEGALNAME1", nullable = false, length = 270)
    public String getLegalname1() {
        return legalname1;
    }

    public void setLegalname1(String legalname1) {
        this.legalname1 = legalname1;
    }

    @Basic
    @Column(name = "STATECODE", nullable = true, length = 3)
    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = false, length = 724)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "GSTINNUMBER")
    public String getGstinnumber() {
        return gstinnumber;
    }

    public void setGstinnumber(String gstinnumber) {
        this.gstinnumber = gstinnumber;
    }

    @Id
    @Column(name = "addressee")
    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    @Column(name = "COUNTRYCODE")
    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiDirbillorederpartner that = (DiDirbillorederpartner) o;
        return Objects.equals(customersuppliercompanycode, that.customersuppliercompanycode) && Objects.equals(customersuppliertype, that.customersuppliertype) && Objects.equals(customersuppliercode, that.customersuppliercode) && Objects.equals(paymentmethodcode, that.paymentmethodcode) && Objects.equals(legalname1, that.legalname1) && Objects.equals(statecode, that.statecode) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customersuppliercompanycode, customersuppliertype, customersuppliercode, paymentmethodcode, legalname1, statecode, address);
    }
}
