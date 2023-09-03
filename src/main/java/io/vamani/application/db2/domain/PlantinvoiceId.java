package io.vamani.application.db2.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class PlantinvoiceId implements Serializable {
    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    private String companycode;

    @Column(name = "DIVISIONCODE", nullable = false, length = 3)
    private String divisioncode;

    @Column(name = "CODE", nullable = false, length = 15)
    private String code;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
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
        PlantinvoiceId that = (PlantinvoiceId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(divisioncode, that.divisioncode) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, divisioncode, code);
    }
}
