package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FinancemonthId implements Serializable {
    private String companycode;
    private Integer financialyearcode;
    private String businessunitcode;
    private Integer code;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "FINANCIALYEARCODE", nullable = false, precision = 0)
    @Id
    public Integer getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(Integer financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    @Column(name = "BUSINESSUNITCODE", nullable = false, length = 10)
    @Id
    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    @Column(name = "CODE", nullable = false)
    @Id
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancemonthId that = (FinancemonthId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(financialyearcode, that.financialyearcode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, financialyearcode, businessunitcode, code);
    }
}
