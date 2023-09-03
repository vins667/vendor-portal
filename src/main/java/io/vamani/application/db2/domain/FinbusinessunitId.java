package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FinbusinessunitId implements Serializable {
    private String companycode;
    private String code;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "CODE", nullable = false, length = 10)
    @Id
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
        FinbusinessunitId that = (FinbusinessunitId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, code);
    }
}