package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class HsngstmappingId implements Serializable {
    private String companycode;
    private String modulename;
    private String tarrifcode;
    private String igsttaxtemplatetemplatetype;
    private String igsttaxtemplatecode;
    private String sgsttaxtemplatetemplatetype;
    private String sgsttaxtemplatecode;
    private String intergsttaxtmptemplatetype;
    private String intergsttaxtemplatecode;
    private Date effectivefromdate;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "MODULENAME", nullable = false, length = 1)
    @Id
    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    @Column(name = "TARRIFCODE", nullable = false, length = 20)
    @Id
    public String getTarrifcode() {
        return tarrifcode;
    }

    public void setTarrifcode(String tarrifcode) {
        this.tarrifcode = tarrifcode;
    }

    @Column(name = "IGSTTAXTEMPLATETEMPLATETYPE", nullable = false, length = 2)
    @Id
    public String getIgsttaxtemplatetemplatetype() {
        return igsttaxtemplatetemplatetype;
    }

    public void setIgsttaxtemplatetemplatetype(String igsttaxtemplatetemplatetype) {
        this.igsttaxtemplatetemplatetype = igsttaxtemplatetemplatetype;
    }

    @Column(name = "IGSTTAXTEMPLATECODE", nullable = false, length = 3)
    @Id
    public String getIgsttaxtemplatecode() {
        return igsttaxtemplatecode;
    }

    public void setIgsttaxtemplatecode(String igsttaxtemplatecode) {
        this.igsttaxtemplatecode = igsttaxtemplatecode;
    }

    @Column(name = "SGSTTAXTEMPLATETEMPLATETYPE", nullable = false, length = 2)
    @Id
    public String getSgsttaxtemplatetemplatetype() {
        return sgsttaxtemplatetemplatetype;
    }

    public void setSgsttaxtemplatetemplatetype(String sgsttaxtemplatetemplatetype) {
        this.sgsttaxtemplatetemplatetype = sgsttaxtemplatetemplatetype;
    }

    @Column(name = "SGSTTAXTEMPLATECODE", nullable = false, length = 3)
    @Id
    public String getSgsttaxtemplatecode() {
        return sgsttaxtemplatecode;
    }

    public void setSgsttaxtemplatecode(String sgsttaxtemplatecode) {
        this.sgsttaxtemplatecode = sgsttaxtemplatecode;
    }

    @Column(name = "INTERGSTTAXTMPTEMPLATETYPE", nullable = false, length = 2)
    @Id
    public String getIntergsttaxtmptemplatetype() {
        return intergsttaxtmptemplatetype;
    }

    public void setIntergsttaxtmptemplatetype(String intergsttaxtmptemplatetype) {
        this.intergsttaxtmptemplatetype = intergsttaxtmptemplatetype;
    }

    @Column(name = "INTERGSTTAXTEMPLATECODE", nullable = false, length = 3)
    @Id
    public String getIntergsttaxtemplatecode() {
        return intergsttaxtemplatecode;
    }

    public void setIntergsttaxtemplatecode(String intergsttaxtemplatecode) {
        this.intergsttaxtemplatecode = intergsttaxtemplatecode;
    }

    @Column(name = "EFFECTIVEFROMDATE", nullable = false)
    @Id
    public Date getEffectivefromdate() {
        return effectivefromdate;
    }

    public void setEffectivefromdate(Date effectivefromdate) {
        this.effectivefromdate = effectivefromdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HsngstmappingId that = (HsngstmappingId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(modulename, that.modulename) && Objects.equals(tarrifcode, that.tarrifcode) && Objects.equals(igsttaxtemplatetemplatetype, that.igsttaxtemplatetemplatetype) && Objects.equals(igsttaxtemplatecode, that.igsttaxtemplatecode) && Objects.equals(sgsttaxtemplatetemplatetype, that.sgsttaxtemplatetemplatetype) && Objects.equals(sgsttaxtemplatecode, that.sgsttaxtemplatecode) && Objects.equals(intergsttaxtmptemplatetype, that.intergsttaxtmptemplatetype) && Objects.equals(intergsttaxtemplatecode, that.intergsttaxtemplatecode) && Objects.equals(effectivefromdate, that.effectivefromdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, modulename, tarrifcode, igsttaxtemplatetemplatetype, igsttaxtemplatecode, sgsttaxtemplatetemplatetype, sgsttaxtemplatecode, intergsttaxtmptemplatetype, intergsttaxtemplatecode, effectivefromdate);
    }
}
