package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductionprogressId implements Serializable {
    private String companycode;
    private String progressnumber;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "PROGRESSNUMBER", nullable = false, length = 15)
    @Id
    public String getProgressnumber() {
        return progressnumber;
    }

    public void setProgressnumber(String progressnumber) {
        this.progressnumber = progressnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionprogressId that = (ProductionprogressId) o;
        return Objects.equals(companycode, that.companycode) &&
            Objects.equals(progressnumber, that.progressnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, progressnumber);
    }
}
