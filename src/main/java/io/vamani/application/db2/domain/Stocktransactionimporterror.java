package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stocktransactionimporterror")
public class Stocktransactionimporterror {
    private Long errortimestamp;
    private String importlinecompanycode;
    private String importlinetransactionnumber;
    private Integer implinetrndetailnumber;
    private Long absuniqueid;

    @Id
    @Column(name = "ERRORTIMESTAMP", nullable = false)
    public Long getErrortimestamp() {
        return errortimestamp;
    }

    public void setErrortimestamp(Long errortimestamp) {
        this.errortimestamp = errortimestamp;
    }

    @Basic
    @Column(name = "IMPORTLINECOMPANYCODE", nullable = true, length = 3)
    public String getImportlinecompanycode() {
        return importlinecompanycode;
    }

    public void setImportlinecompanycode(String importlinecompanycode) {
        this.importlinecompanycode = importlinecompanycode;
    }

    @Basic
    @Column(name = "IMPORTLINETRANSACTIONNUMBER", nullable = true, length = 15)
    public String getImportlinetransactionnumber() {
        return importlinetransactionnumber;
    }

    public void setImportlinetransactionnumber(String importlinetransactionnumber) {
        this.importlinetransactionnumber = importlinetransactionnumber;
    }

    @Basic
    @Column(name = "IMPLINETRNDETAILNUMBER", nullable = true)
    public Integer getImplinetrndetailnumber() {
        return implinetrndetailnumber;
    }

    public void setImplinetrndetailnumber(Integer implinetrndetailnumber) {
        this.implinetrndetailnumber = implinetrndetailnumber;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stocktransactionimporterror that = (Stocktransactionimporterror) o;
        return Objects.equals(errortimestamp, that.errortimestamp) && Objects.equals(importlinecompanycode, that.importlinecompanycode) && Objects.equals(importlinetransactionnumber, that.importlinetransactionnumber) && Objects.equals(implinetrndetailnumber, that.implinetrndetailnumber) && Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errortimestamp, importlinecompanycode, importlinetransactionnumber, implinetrndetailnumber, absuniqueid);
    }
}
