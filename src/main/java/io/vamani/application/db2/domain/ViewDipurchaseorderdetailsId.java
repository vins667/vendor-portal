package io.vamani.application.db2.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ViewDipurchaseorderdetailsId implements Serializable {
    private static final long serialVersionUID = 9046427627554229788L;
    @Basic
    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    private String companycode;
    @Basic
    @Column(name = "CODE", nullable = false, length = 15)
    private String code;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDipurchaseorderdetailsId that = (ViewDipurchaseorderdetailsId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, code);
    }
}
