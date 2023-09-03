package io.vamani.application.db2.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class ViewprodorderdemandId implements Serializable {
    @Basic
    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    private String companycode;

    @Basic
    @Column(name = "POCOUNTERCODE", nullable = true, length = 8)
    private String pocountercode;

    @Basic
    @Column(name = "CODE", nullable = false, length = 15)
    private String code;

    @Basic
    @Column(name = "PDCOUNTERCODE", nullable = false, length = 8)
    private String pdcountercode;

    @Basic
    @Column(name = "PDCODE", nullable = false, length = 15)
    private String pdcode;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getPocountercode() {
        return pocountercode;
    }

    public void setPocountercode(String pocountercode) {
        this.pocountercode = pocountercode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPdcountercode() {
        return pdcountercode;
    }

    public void setPdcountercode(String pdcountercode) {
        this.pdcountercode = pdcountercode;
    }

    public String getPdcode() {
        return pdcode;
    }

    public void setPdcode(String pdcode) {
        this.pdcode = pdcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewprodorderdemandId that = (ViewprodorderdemandId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(pocountercode, that.pocountercode) && Objects.equals(code, that.code) && Objects.equals(pdcountercode, that.pdcountercode) && Objects.equals(pdcode, that.pdcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, pocountercode, code, pdcountercode, pdcode);
    }
}
