package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "hsngstmapping")
public class Hsngstmapping {
    @EmbeddedId
    private HsngstmappingId id;
    private String exportgsttaxtmptemplatetype;
    private String exportgsttaxtemplatecode;
    private String rcmigsttaxtemplatetemplatetype;
    private String rcmigsttaxtemplatecode;
    private String rcmsgsttaxtemplatetemplatetype;
    private String rcmsgsttaxtemplatecode;
    private Date effectivetodate;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    @Basic
    @Column(name = "EXPORTGSTTAXTMPTEMPLATETYPE", nullable = true, length = 2)
    public String getExportgsttaxtmptemplatetype() {
        return exportgsttaxtmptemplatetype;
    }

    public void setExportgsttaxtmptemplatetype(String exportgsttaxtmptemplatetype) {
        this.exportgsttaxtmptemplatetype = exportgsttaxtmptemplatetype;
    }

    @Basic
    @Column(name = "EXPORTGSTTAXTEMPLATECODE", nullable = true, length = 3)
    public String getExportgsttaxtemplatecode() {
        return exportgsttaxtemplatecode;
    }

    public void setExportgsttaxtemplatecode(String exportgsttaxtemplatecode) {
        this.exportgsttaxtemplatecode = exportgsttaxtemplatecode;
    }

    @Basic
    @Column(name = "RCMIGSTTAXTEMPLATETEMPLATETYPE", nullable = true, length = 2)
    public String getRcmigsttaxtemplatetemplatetype() {
        return rcmigsttaxtemplatetemplatetype;
    }

    public void setRcmigsttaxtemplatetemplatetype(String rcmigsttaxtemplatetemplatetype) {
        this.rcmigsttaxtemplatetemplatetype = rcmigsttaxtemplatetemplatetype;
    }

    @Basic
    @Column(name = "RCMIGSTTAXTEMPLATECODE", nullable = true, length = 3)
    public String getRcmigsttaxtemplatecode() {
        return rcmigsttaxtemplatecode;
    }

    public void setRcmigsttaxtemplatecode(String rcmigsttaxtemplatecode) {
        this.rcmigsttaxtemplatecode = rcmigsttaxtemplatecode;
    }

    @Basic
    @Column(name = "RCMSGSTTAXTEMPLATETEMPLATETYPE", nullable = true, length = 2)
    public String getRcmsgsttaxtemplatetemplatetype() {
        return rcmsgsttaxtemplatetemplatetype;
    }

    public void setRcmsgsttaxtemplatetemplatetype(String rcmsgsttaxtemplatetemplatetype) {
        this.rcmsgsttaxtemplatetemplatetype = rcmsgsttaxtemplatetemplatetype;
    }

    @Basic
    @Column(name = "RCMSGSTTAXTEMPLATECODE", nullable = true, length = 3)
    public String getRcmsgsttaxtemplatecode() {
        return rcmsgsttaxtemplatecode;
    }

    public void setRcmsgsttaxtemplatecode(String rcmsgsttaxtemplatecode) {
        this.rcmsgsttaxtemplatecode = rcmsgsttaxtemplatecode;
    }

    @Basic
    @Column(name = "EFFECTIVETODATE", nullable = true)
    public Date getEffectivetodate() {
        return effectivetodate;
    }

    public void setEffectivetodate(Date effectivetodate) {
        this.effectivetodate = effectivetodate;
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
        Hsngstmapping that = (Hsngstmapping) o;
        return Objects.equals(id, that.id) && Objects.equals(exportgsttaxtmptemplatetype, that.exportgsttaxtmptemplatetype) && Objects.equals(exportgsttaxtemplatecode, that.exportgsttaxtemplatecode) && Objects.equals(rcmigsttaxtemplatetemplatetype, that.rcmigsttaxtemplatetemplatetype) && Objects.equals(rcmigsttaxtemplatecode, that.rcmigsttaxtemplatecode) && Objects.equals(rcmsgsttaxtemplatetemplatetype, that.rcmsgsttaxtemplatetemplatetype) && Objects.equals(rcmsgsttaxtemplatecode, that.rcmsgsttaxtemplatecode) && Objects.equals(effectivetodate, that.effectivetodate) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exportgsttaxtmptemplatetype, exportgsttaxtemplatecode, rcmigsttaxtemplatetemplatetype, rcmigsttaxtemplatecode, rcmsgsttaxtemplatetemplatetype, rcmsgsttaxtemplatecode, effectivetodate, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
