package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class TaxtemplatedetailId implements Serializable {
    private String taxtemplateheadercompanycode;
    private String taxtemplateheadercode;
    private Date taxtmpheadereffectivefromdate;
    private String itaxcode;

    @Column(name = "TAXTEMPLATEHEADERCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getTaxtemplateheadercompanycode() {
        return taxtemplateheadercompanycode;
    }

    public void setTaxtemplateheadercompanycode(String taxtemplateheadercompanycode) {
        this.taxtemplateheadercompanycode = taxtemplateheadercompanycode;
    }

    @Column(name = "TAXTEMPLATEHEADERCODE", nullable = false, length = 3)
    @Id
    public String getTaxtemplateheadercode() {
        return taxtemplateheadercode;
    }

    public void setTaxtemplateheadercode(String taxtemplateheadercode) {
        this.taxtemplateheadercode = taxtemplateheadercode;
    }

    @Column(name = "TAXTMPHEADEREFFECTIVEFROMDATE", nullable = false)
    @Id
    public Date getTaxtmpheadereffectivefromdate() {
        return taxtmpheadereffectivefromdate;
    }

    public void setTaxtmpheadereffectivefromdate(Date taxtmpheadereffectivefromdate) {
        this.taxtmpheadereffectivefromdate = taxtmpheadereffectivefromdate;
    }

    @Column(name = "ITAXCODE", nullable = false, length = 3)
    @Id
    public String getItaxcode() {
        return itaxcode;
    }

    public void setItaxcode(String itaxcode) {
        this.itaxcode = itaxcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxtemplatedetailId that = (TaxtemplatedetailId) o;
        return Objects.equals(taxtemplateheadercompanycode, that.taxtemplateheadercompanycode) && Objects.equals(taxtemplateheadercode, that.taxtemplateheadercode) && Objects.equals(taxtmpheadereffectivefromdate, that.taxtmpheadereffectivefromdate) && Objects.equals(itaxcode, that.itaxcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxtemplateheadercompanycode, taxtemplateheadercode, taxtmpheadereffectivefromdate, itaxcode);
    }
}
