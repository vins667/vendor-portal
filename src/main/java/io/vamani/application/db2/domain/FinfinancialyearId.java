package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


public class FinfinancialyearId implements Serializable {

    private String companycode;

    private Long code;

    @Column(name = "COMPANYCODE")
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "CODE")
    @Id
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public FinfinancialyearId() {
    }

    public FinfinancialyearId(String companycode, Long code) {
        this.companycode = companycode;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinfinancialyearId that = (FinfinancialyearId) o;
        return code == that.code && Objects.equals(companycode, that.companycode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, code);
    }
}
