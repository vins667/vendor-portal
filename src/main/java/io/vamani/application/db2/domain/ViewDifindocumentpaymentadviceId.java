package io.vamani.application.db2.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class ViewDifindocumentpaymentadviceId implements Serializable {
    @Column(name = "COMPANYCODE")
    private String companycode;
    @Column(name = "BUSINESSUNITCODE")
    private String businessunitcode;
    @Column(name = "FINANCIALYEARCODE")
    private Integer financialyearcode;
    @Column(name = "CODE")
    private String code;
    @Column(name = "LINENUMBER")
    private Integer linenumber;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public Integer getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(Integer financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(Integer linenumber) {
        this.linenumber = linenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDifindocumentpaymentadviceId that = (ViewDifindocumentpaymentadviceId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(financialyearcode, that.financialyearcode) && Objects.equals(code, that.code) && Objects.equals(linenumber, that.linenumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, businessunitcode, financialyearcode, code, linenumber);
    }
}
