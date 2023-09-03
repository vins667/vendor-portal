package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BalanceId implements Serializable {
    private String companycode;
    private Integer numberid;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "NUMBERID", nullable = false, precision = 0)
    @Id
    public Integer getNumberid() {
        return numberid;
    }

    public void setNumberid(Integer numberid) {
        this.numberid = numberid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceId balanceId = (BalanceId) o;
        return Objects.equals(companycode, balanceId.companycode) && Objects.equals(numberid, balanceId.numberid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, numberid);
    }
}
