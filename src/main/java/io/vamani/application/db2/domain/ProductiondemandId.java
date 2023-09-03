package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductiondemandId implements Serializable {
    private String companycode;
    private String countercode;
    private String code;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "COUNTERCODE", nullable = false, length = 8)
    public String getCountercode() {
        return countercode;
    }

    public void setCountercode(String countercode) {
        this.countercode = countercode;
    }

    @Column(name = "CODE", nullable = false, length = 15)
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
        ProductiondemandId that = (ProductiondemandId) o;
        return Objects.equals(companycode, that.companycode) &&
            Objects.equals(countercode, that.countercode) &&
            Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, countercode, code);
    }

    public ProductiondemandId() {
    }

    public ProductiondemandId(String companycode, String countercode, String code) {
        this.companycode = companycode;
        this.countercode = countercode;
        this.code = code;
    }
}
