package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FincostcenterdetailId implements Serializable {
    private String finbusinessvsprofitcompanycode;
    private String finbvsprofitbusinessunitcode;
    private String finbvsprofitprofitcentercode;
    private String costcentercode;

    @Column(name = "FINBUSINESSVSPROFITCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getFinbusinessvsprofitcompanycode() {
        return finbusinessvsprofitcompanycode;
    }

    public void setFinbusinessvsprofitcompanycode(String finbusinessvsprofitcompanycode) {
        this.finbusinessvsprofitcompanycode = finbusinessvsprofitcompanycode;
    }

    @Column(name = "FINBVSPROFITBUSINESSUNITCODE", nullable = false, length = 10)
    @Id
    public String getFinbvsprofitbusinessunitcode() {
        return finbvsprofitbusinessunitcode;
    }

    public void setFinbvsprofitbusinessunitcode(String finbvsprofitbusinessunitcode) {
        this.finbvsprofitbusinessunitcode = finbvsprofitbusinessunitcode;
    }

    @Column(name = "FINBVSPROFITPROFITCENTERCODE", nullable = false, length = 10)
    @Id
    public String getFinbvsprofitprofitcentercode() {
        return finbvsprofitprofitcentercode;
    }

    public void setFinbvsprofitprofitcentercode(String finbvsprofitprofitcentercode) {
        this.finbvsprofitprofitcentercode = finbvsprofitprofitcentercode;
    }

    @Column(name = "COSTCENTERCODE", nullable = false, length = 20)
    @Id
    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FincostcenterdetailId that = (FincostcenterdetailId) o;
        return Objects.equals(finbusinessvsprofitcompanycode, that.finbusinessvsprofitcompanycode) && Objects.equals(finbvsprofitbusinessunitcode, that.finbvsprofitbusinessunitcode) && Objects.equals(finbvsprofitprofitcentercode, that.finbvsprofitprofitcentercode) && Objects.equals(costcentercode, that.costcentercode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finbusinessvsprofitcompanycode, finbvsprofitbusinessunitcode, finbvsprofitprofitcentercode, costcentercode);
    }
}
