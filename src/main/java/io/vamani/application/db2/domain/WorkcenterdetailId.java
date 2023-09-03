package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class WorkcenterdetailId implements Serializable {
    private String workcentercompanycode;
    private String workcentercode;
    private String mainresourcecode;
    private Date validitybegindate;

    @Column(name = "WORKCENTERCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getWorkcentercompanycode() {
        return workcentercompanycode;
    }

    public void setWorkcentercompanycode(String workcentercompanycode) {
        this.workcentercompanycode = workcentercompanycode;
    }

    @Column(name = "WORKCENTERCODE", nullable = false, length = 8)
    @Id
    public String getWorkcentercode() {
        return workcentercode;
    }

    public void setWorkcentercode(String workcentercode) {
        this.workcentercode = workcentercode;
    }

    @Column(name = "MAINRESOURCECODE", nullable = false, length = 8)
    @Id
    public String getMainresourcecode() {
        return mainresourcecode;
    }

    public void setMainresourcecode(String mainresourcecode) {
        this.mainresourcecode = mainresourcecode;
    }

    @Column(name = "VALIDITYBEGINDATE", nullable = false)
    @Id
    public Date getValiditybegindate() {
        return validitybegindate;
    }

    public void setValiditybegindate(Date validitybegindate) {
        this.validitybegindate = validitybegindate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkcenterdetailId that = (WorkcenterdetailId) o;
        return Objects.equals(workcentercompanycode, that.workcentercompanycode) && Objects.equals(workcentercode, that.workcentercode) && Objects.equals(mainresourcecode, that.mainresourcecode) && Objects.equals(validitybegindate, that.validitybegindate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workcentercompanycode, workcentercode, mainresourcecode, validitybegindate);
    }
}
