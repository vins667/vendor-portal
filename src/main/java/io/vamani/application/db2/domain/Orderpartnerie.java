package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orderpartnerie")
public class Orderpartnerie {
    @EmbeddedId
    private OrderpartnerieId id;
    private String rangecode;
    private String rangedescription;
    private String rangedivisioncode;
    private String rangedivisiondescription;
    private String commissionerate;
    private String ceregistrationno;
    private Date ceregistrationdate;
    private String eccno;
    private Date eccdate;
    private String cstno;
    private Date cstdate;
    private String sstno;
    private Date sstdate;
    private String salestaxcode;
    private String ssinumber;
    private Date ssidate;
    private String taxtemplateheadertemplatetype;
    private String taxtemplateheadercode;
    private String taxtemplatedetailtemplatetype;
    private String taxtemplatedetailcode;
    private String glcompanycode;
    private String glcode;
    private BigDecimal insurancecharges;
    private String flag;
    private String sapmessage;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    public OrderpartnerieId getId() {
        return id;
    }

    public void setId(OrderpartnerieId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RANGECODE", nullable = true, length = 15)
    public String getRangecode() {
        return rangecode;
    }

    public void setRangecode(String rangecode) {
        this.rangecode = rangecode;
    }

    @Basic
    @Column(name = "RANGEDESCRIPTION", nullable = true, length = 100)
    public String getRangedescription() {
        return rangedescription;
    }

    public void setRangedescription(String rangedescription) {
        this.rangedescription = rangedescription;
    }

    @Basic
    @Column(name = "RANGEDIVISIONCODE", nullable = true, length = 15)
    public String getRangedivisioncode() {
        return rangedivisioncode;
    }

    public void setRangedivisioncode(String rangedivisioncode) {
        this.rangedivisioncode = rangedivisioncode;
    }

    @Basic
    @Column(name = "RANGEDIVISIONDESCRIPTION", nullable = true, length = 100)
    public String getRangedivisiondescription() {
        return rangedivisiondescription;
    }

    public void setRangedivisiondescription(String rangedivisiondescription) {
        this.rangedivisiondescription = rangedivisiondescription;
    }

    @Basic
    @Column(name = "COMMISSIONERATE", nullable = true, length = 30)
    public String getCommissionerate() {
        return commissionerate;
    }

    public void setCommissionerate(String commissionerate) {
        this.commissionerate = commissionerate;
    }

    @Basic
    @Column(name = "CEREGISTRATIONNO", nullable = true, length = 30)
    public String getCeregistrationno() {
        return ceregistrationno;
    }

    public void setCeregistrationno(String ceregistrationno) {
        this.ceregistrationno = ceregistrationno;
    }

    @Basic
    @Column(name = "CEREGISTRATIONDATE", nullable = true)
    public Date getCeregistrationdate() {
        return ceregistrationdate;
    }

    public void setCeregistrationdate(Date ceregistrationdate) {
        this.ceregistrationdate = ceregistrationdate;
    }

    @Basic
    @Column(name = "ECCNO", nullable = true, length = 30)
    public String getEccno() {
        return eccno;
    }

    public void setEccno(String eccno) {
        this.eccno = eccno;
    }

    @Basic
    @Column(name = "ECCDATE", nullable = true)
    public Date getEccdate() {
        return eccdate;
    }

    public void setEccdate(Date eccdate) {
        this.eccdate = eccdate;
    }

    @Basic
    @Column(name = "CSTNO", nullable = true, length = 30)
    public String getCstno() {
        return cstno;
    }

    public void setCstno(String cstno) {
        this.cstno = cstno;
    }

    @Basic
    @Column(name = "CSTDATE", nullable = true)
    public Date getCstdate() {
        return cstdate;
    }

    public void setCstdate(Date cstdate) {
        this.cstdate = cstdate;
    }

    @Basic
    @Column(name = "SSTNO", nullable = true, length = 30)
    public String getSstno() {
        return sstno;
    }

    public void setSstno(String sstno) {
        this.sstno = sstno;
    }

    @Basic
    @Column(name = "SSTDATE", nullable = true)
    public Date getSstdate() {
        return sstdate;
    }

    public void setSstdate(Date sstdate) {
        this.sstdate = sstdate;
    }

    @Basic
    @Column(name = "SALESTAXCODE", nullable = true, length = 30)
    public String getSalestaxcode() {
        return salestaxcode;
    }

    public void setSalestaxcode(String salestaxcode) {
        this.salestaxcode = salestaxcode;
    }

    @Basic
    @Column(name = "SSINUMBER", nullable = true, length = 30)
    public String getSsinumber() {
        return ssinumber;
    }

    public void setSsinumber(String ssinumber) {
        this.ssinumber = ssinumber;
    }

    @Basic
    @Column(name = "SSIDATE", nullable = true)
    public Date getSsidate() {
        return ssidate;
    }

    public void setSsidate(Date ssidate) {
        this.ssidate = ssidate;
    }

    @Basic
    @Column(name = "TAXTEMPLATEHEADERTEMPLATETYPE", nullable = true, length = 2)
    public String getTaxtemplateheadertemplatetype() {
        return taxtemplateheadertemplatetype;
    }

    public void setTaxtemplateheadertemplatetype(String taxtemplateheadertemplatetype) {
        this.taxtemplateheadertemplatetype = taxtemplateheadertemplatetype;
    }

    @Basic
    @Column(name = "TAXTEMPLATEHEADERCODE", nullable = true, length = 3)
    public String getTaxtemplateheadercode() {
        return taxtemplateheadercode;
    }

    public void setTaxtemplateheadercode(String taxtemplateheadercode) {
        this.taxtemplateheadercode = taxtemplateheadercode;
    }

    @Basic
    @Column(name = "TAXTEMPLATEDETAILTEMPLATETYPE", nullable = true, length = 2)
    public String getTaxtemplatedetailtemplatetype() {
        return taxtemplatedetailtemplatetype;
    }

    public void setTaxtemplatedetailtemplatetype(String taxtemplatedetailtemplatetype) {
        this.taxtemplatedetailtemplatetype = taxtemplatedetailtemplatetype;
    }

    @Basic
    @Column(name = "TAXTEMPLATEDETAILCODE", nullable = true, length = 3)
    public String getTaxtemplatedetailcode() {
        return taxtemplatedetailcode;
    }

    public void setTaxtemplatedetailcode(String taxtemplatedetailcode) {
        this.taxtemplatedetailcode = taxtemplatedetailcode;
    }

    @Basic
    @Column(name = "GLCOMPANYCODE", nullable = true, length = 3)
    public String getGlcompanycode() {
        return glcompanycode;
    }

    public void setGlcompanycode(String glcompanycode) {
        this.glcompanycode = glcompanycode;
    }

    @Basic
    @Column(name = "GLCODE", nullable = true, length = 20)
    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    @Basic
    @Column(name = "INSURANCECHARGES", nullable = true, precision = 5)
    public BigDecimal getInsurancecharges() {
        return insurancecharges;
    }

    public void setInsurancecharges(BigDecimal insurancecharges) {
        this.insurancecharges = insurancecharges;
    }

    @Basic
    @Column(name = "FLAG", nullable = true, length = 15)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "SAPMESSAGE", nullable = true, length = 32700)
    public String getSapmessage() {
        return sapmessage;
    }

    public void setSapmessage(String sapmessage) {
        this.sapmessage = sapmessage;
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
        Orderpartnerie that = (Orderpartnerie) o;
        return Objects.equals(id, that.id) && Objects.equals(rangecode, that.rangecode) && Objects.equals(rangedescription, that.rangedescription) && Objects.equals(rangedivisioncode, that.rangedivisioncode) && Objects.equals(rangedivisiondescription, that.rangedivisiondescription) && Objects.equals(commissionerate, that.commissionerate) && Objects.equals(ceregistrationno, that.ceregistrationno) && Objects.equals(ceregistrationdate, that.ceregistrationdate) && Objects.equals(eccno, that.eccno) && Objects.equals(eccdate, that.eccdate) && Objects.equals(cstno, that.cstno) && Objects.equals(cstdate, that.cstdate) && Objects.equals(sstno, that.sstno) && Objects.equals(sstdate, that.sstdate) && Objects.equals(salestaxcode, that.salestaxcode) && Objects.equals(ssinumber, that.ssinumber) && Objects.equals(ssidate, that.ssidate) && Objects.equals(taxtemplateheadertemplatetype, that.taxtemplateheadertemplatetype) && Objects.equals(taxtemplateheadercode, that.taxtemplateheadercode) && Objects.equals(taxtemplatedetailtemplatetype, that.taxtemplatedetailtemplatetype) && Objects.equals(taxtemplatedetailcode, that.taxtemplatedetailcode) && Objects.equals(glcompanycode, that.glcompanycode) && Objects.equals(glcode, that.glcode) && Objects.equals(insurancecharges, that.insurancecharges) && Objects.equals(flag, that.flag) && Objects.equals(sapmessage, that.sapmessage) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rangecode, rangedescription, rangedivisioncode, rangedivisiondescription, commissionerate, ceregistrationno, ceregistrationdate, eccno, eccdate, cstno, cstdate, sstno, sstdate, salestaxcode, ssinumber, ssidate, taxtemplateheadertemplatetype, taxtemplateheadercode, taxtemplatedetailtemplatetype, taxtemplatedetailcode, glcompanycode, glcode, insurancecharges, flag, sapmessage, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
