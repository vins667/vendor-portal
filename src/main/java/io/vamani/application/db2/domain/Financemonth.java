package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Financemonth")
public class Financemonth {
    @EmbeddedId
    private FinancemonthId id;
    private String financialyearcompanycode;
    private Date monthstartdate;
    private Date monthenddate;
    private Short monthclosed;
    private Short closingjvpassed;
    private String documenttemplatecompanycode;
    private String documenttemplatecode;
    private String owningcompanycode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public FinancemonthId getId() {
        return id;
    }

    public void setId(FinancemonthId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FINANCIALYEARCOMPANYCODE", nullable = false, length = 3)
    public String getFinancialyearcompanycode() {
        return financialyearcompanycode;
    }

    public void setFinancialyearcompanycode(String financialyearcompanycode) {
        this.financialyearcompanycode = financialyearcompanycode;
    }

    @Basic
    @Column(name = "MONTHSTARTDATE", nullable = true)
    public Date getMonthstartdate() {
        return monthstartdate;
    }

    public void setMonthstartdate(Date monthstartdate) {
        this.monthstartdate = monthstartdate;
    }

    @Basic
    @Column(name = "MONTHENDDATE", nullable = true)
    public Date getMonthenddate() {
        return monthenddate;
    }

    public void setMonthenddate(Date monthenddate) {
        this.monthenddate = monthenddate;
    }

    @Basic
    @Column(name = "MONTHCLOSED", nullable = false)
    public Short getMonthclosed() {
        return monthclosed;
    }

    public void setMonthclosed(Short monthclosed) {
        this.monthclosed = monthclosed;
    }

    @Basic
    @Column(name = "CLOSINGJVPASSED", nullable = false)
    public Short getClosingjvpassed() {
        return closingjvpassed;
    }

    public void setClosingjvpassed(Short closingjvpassed) {
        this.closingjvpassed = closingjvpassed;
    }

    @Basic
    @Column(name = "DOCUMENTTEMPLATECOMPANYCODE", nullable = true, length = 3)
    public String getDocumenttemplatecompanycode() {
        return documenttemplatecompanycode;
    }

    public void setDocumenttemplatecompanycode(String documenttemplatecompanycode) {
        this.documenttemplatecompanycode = documenttemplatecompanycode;
    }

    @Basic
    @Column(name = "DOCUMENTTEMPLATECODE", nullable = true, length = 3)
    public String getDocumenttemplatecode() {
        return documenttemplatecode;
    }

    public void setDocumenttemplatecode(String documenttemplatecode) {
        this.documenttemplatecode = documenttemplatecode;
    }

    @Basic
    @Column(name = "OWNINGCOMPANYCODE", nullable = true, length = 3)
    public String getOwningcompanycode() {
        return owningcompanycode;
    }

    public void setOwningcompanycode(String owningcompanycode) {
        this.owningcompanycode = owningcompanycode;
    }

    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Basic
    @Column(name = "CREATIONDATETIMEUTC", nullable = true)
    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Financemonth that = (Financemonth) o;
        return Objects.equals(id, that.id) && Objects.equals(financialyearcompanycode, that.financialyearcompanycode) && Objects.equals(monthstartdate, that.monthstartdate) && Objects.equals(monthenddate, that.monthenddate) && Objects.equals(monthclosed, that.monthclosed) && Objects.equals(closingjvpassed, that.closingjvpassed) && Objects.equals(documenttemplatecompanycode, that.documenttemplatecompanycode) && Objects.equals(documenttemplatecode, that.documenttemplatecode) && Objects.equals(owningcompanycode, that.owningcompanycode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, financialyearcompanycode, monthstartdate, monthenddate, monthclosed, closingjvpassed, documenttemplatecompanycode, documenttemplatecode, owningcompanycode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
