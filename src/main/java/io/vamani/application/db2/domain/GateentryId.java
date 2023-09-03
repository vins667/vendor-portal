package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

public class GateentryId implements Serializable {
    private String companycode;
    private String maingateentrysrno;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "MAINGATEENTRYSRNO", nullable = false)
    @Id
    public String getMaingateentrysrno() {
        return maingateentrysrno;
    }

    public void setMaingateentrysrno(String maingateentrysrno) {
        this.maingateentrysrno = maingateentrysrno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GateentryId that = (GateentryId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(maingateentrysrno, that.maingateentrysrno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, maingateentrysrno);
    }
}
