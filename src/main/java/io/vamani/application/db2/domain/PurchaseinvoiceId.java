package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.Objects;

public class PurchaseinvoiceId implements Serializable {
    private String companycode;
    private String divisioncode;
    private String ordprncustomersuppliertype;
    private String ordprncustomersuppliercode;
    private String code;
    private Instant invoicedate;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "DIVISIONCODE", nullable = false, length = 3)
    @Id
    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    @Column(name = "ORDPRNCUSTOMERSUPPLIERTYPE", nullable = false, length = 1)
    @Id
    public String getOrdprncustomersuppliertype() {
        return ordprncustomersuppliertype;
    }

    public void setOrdprncustomersuppliertype(String ordprncustomersuppliertype) {
        this.ordprncustomersuppliertype = ordprncustomersuppliertype;
    }

    @Column(name = "ORDPRNCUSTOMERSUPPLIERCODE", nullable = false, length = 8)
    @Id
    public String getOrdprncustomersuppliercode() {
        return ordprncustomersuppliercode;
    }

    public void setOrdprncustomersuppliercode(String ordprncustomersuppliercode) {
        this.ordprncustomersuppliercode = ordprncustomersuppliercode;
    }

    @Column(name = "CODE", nullable = false, length = 25)
    @Id
    public String getCode() {
        return code.trim();
    }

    public void setCode(String code) {
        this.code = code.trim();
    }

    @Column(name = "INVOICEDATE", nullable = false)
    @Id
    public Instant getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Instant invoicedate) {
        this.invoicedate = invoicedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseinvoiceId that = (PurchaseinvoiceId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(divisioncode, that.divisioncode) && Objects.equals(ordprncustomersuppliertype, that.ordprncustomersuppliertype) && Objects.equals(ordprncustomersuppliercode, that.ordprncustomersuppliercode) && Objects.equals(code, that.code) && Objects.equals(invoicedate, that.invoicedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, divisioncode, ordprncustomersuppliertype, ordprncustomersuppliercode, code, invoicedate);
    }
}
