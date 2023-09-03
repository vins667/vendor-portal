package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductionprogressimportId implements Serializable {
    private String companycode;
    private String progressnumberprefix;
    private Integer progressnumber;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "PROGRESSNUMBERPREFIX", nullable = false, length = 5)
    @Id
    public String getProgressnumberprefix() {
        return progressnumberprefix;
    }

    public void setProgressnumberprefix(String progressnumberprefix) {
        this.progressnumberprefix = progressnumberprefix;
    }

    @Column(name = "PROGRESSNUMBER", nullable = false)
    @Id
    public Integer getProgressnumber() {
        return progressnumber;
    }

    public void setProgressnumber(Integer progressnumber) {
        this.progressnumber = progressnumber;
    }

    public ProductionprogressimportId() {
    }

    public ProductionprogressimportId(String companycode, String progressnumberprefix, Integer progressnumber) {
        this.companycode = companycode;
        this.progressnumberprefix = progressnumberprefix;
        this.progressnumber = progressnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionprogressimportId that = (ProductionprogressimportId) o;
        return Objects.equals(companycode, that.companycode) &&
            Objects.equals(progressnumberprefix, that.progressnumberprefix) &&
            Objects.equals(progressnumber, that.progressnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, progressnumberprefix, progressnumber);
    }

    @Override
    public String toString() {
        return "ProductionprogressimportId{" +
            "companycode='" + companycode + '\'' +
            ", progressnumberprefix='" + progressnumberprefix + '\'' +
            ", progressnumber=" + progressnumber +
            '}';
    }
}


