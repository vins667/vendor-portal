package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "productionprogressimporterror")
public class Productionprogressimporterror {
    private Long errortimestamp;
    private String importlinecompanycode;
    private String importlineprogressnumberprefix;
    private Integer importlineprogressnumber;
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
    @Column(name = "IMPORTLINEPROGRESSNUMBERPREFIX", nullable = true, length = 5)
    public String getImportlineprogressnumberprefix() {
        return importlineprogressnumberprefix;
    }

    public void setImportlineprogressnumberprefix(String importlineprogressnumberprefix) {
        this.importlineprogressnumberprefix = importlineprogressnumberprefix;
    }

    @Basic
    @Column(name = "IMPORTLINEPROGRESSNUMBER", nullable = true)
    public Integer getImportlineprogressnumber() {
        return importlineprogressnumber;
    }

    public void setImportlineprogressnumber(Integer importlineprogressnumber) {
        this.importlineprogressnumber = importlineprogressnumber;
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
        Productionprogressimporterror that = (Productionprogressimporterror) o;
        return Objects.equals(errortimestamp, that.errortimestamp) && Objects.equals(importlinecompanycode, that.importlinecompanycode) && Objects.equals(importlineprogressnumberprefix, that.importlineprogressnumberprefix) && Objects.equals(importlineprogressnumber, that.importlineprogressnumber) && Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errortimestamp, importlinecompanycode, importlineprogressnumberprefix, importlineprogressnumber, absuniqueid);
    }
}
