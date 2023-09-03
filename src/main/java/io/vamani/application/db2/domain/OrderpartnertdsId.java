package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderpartnertdsId implements Serializable {
    private String companycode;
    private String csmsupcustomersuppliertype;
    private String csmsupcustomersuppliercode;
    private String tdsteusergenericgrouptypecode;
    private String tdstypecode;
    private String tdscode;
    private String tdsitaxcode;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "CSMSUPCUSTOMERSUPPLIERTYPE", nullable = false, length = 1)
    @Id
    public String getCsmsupcustomersuppliertype() {
        return csmsupcustomersuppliertype;
    }

    public void setCsmsupcustomersuppliertype(String csmsupcustomersuppliertype) {
        this.csmsupcustomersuppliertype = csmsupcustomersuppliertype;
    }

    @Column(name = "CSMSUPCUSTOMERSUPPLIERCODE", nullable = false, length = 8)
    @Id
    public String getCsmsupcustomersuppliercode() {
        return csmsupcustomersuppliercode;
    }

    public void setCsmsupcustomersuppliercode(String csmsupcustomersuppliercode) {
        this.csmsupcustomersuppliercode = csmsupcustomersuppliercode;
    }

    @Column(name = "TDSTEUSERGENERICGROUPTYPECODE", nullable = false, length = 3)
    @Id
    public String getTdsteusergenericgrouptypecode() {
        return tdsteusergenericgrouptypecode;
    }

    public void setTdsteusergenericgrouptypecode(String tdsteusergenericgrouptypecode) {
        this.tdsteusergenericgrouptypecode = tdsteusergenericgrouptypecode;
    }

    @Column(name = "TDSTYPECODE", nullable = false, length = 10)
    @Id
    public String getTdstypecode() {
        return tdstypecode;
    }

    public void setTdstypecode(String tdstypecode) {
        this.tdstypecode = tdstypecode;
    }

    @Column(name = "TDSCODE", nullable = false, length = 6)
    @Id
    public String getTdscode() {
        return tdscode;
    }

    public void setTdscode(String tdscode) {
        this.tdscode = tdscode;
    }

    @Column(name = "TDSITAXCODE", nullable = false, length = 3)
    @Id
    public String getTdsitaxcode() {
        return tdsitaxcode;
    }

    public void setTdsitaxcode(String tdsitaxcode) {
        this.tdsitaxcode = tdsitaxcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderpartnertdsId that = (OrderpartnertdsId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(csmsupcustomersuppliertype, that.csmsupcustomersuppliertype) && Objects.equals(csmsupcustomersuppliercode, that.csmsupcustomersuppliercode) && Objects.equals(tdsteusergenericgrouptypecode, that.tdsteusergenericgrouptypecode) && Objects.equals(tdstypecode, that.tdstypecode) && Objects.equals(tdscode, that.tdscode) && Objects.equals(tdsitaxcode, that.tdsitaxcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, csmsupcustomersuppliertype, csmsupcustomersuppliercode, tdsteusergenericgrouptypecode, tdstypecode, tdscode, tdsitaxcode);
    }
}
