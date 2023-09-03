package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "findocumentlinebean")
public class Findocumentlinebean {
    private Long fatherid;
    private Long importautocounter;
    private Integer linenumber;
    private String linetemplatecode;
    private String companycode;
    private String glcode;
    private Short creditline;
    private String currentstatus;
    private Short directentry;
    private String slcustomersuppliertype;
    private String slcustomersuppliercode;
    private BigDecimal amountindc;
    private String documentcurrencycode;
    private BigDecimal exchangerate;
    private BigDecimal amountincc;
    private String companycurrencycode;
    private String statisticalgroupcode;
    private String projectcode;
    private String profitcentercode;
    private String costcentercode;
    private String comments;
    private String destinationcompanycode;
    private String icfdlcompanycode;
    private String icfdlbusinessunitcode;
    private Integer icfdlfinancialyearcode;
    private String icfdldocumenttemplatecode;
    private String icfdlstatisticalgroupcode;
    private String icfdlcode;
    private String referencetext1;
    private String referencetext2;
    private String referencetext3;
    private String referencetext4;
    private String referencetext5;
    private String firstugrpugengrouptypecode;
    private String firstusergrpcode;
    private String sndugrpugenericgrouptypecode;
    private String secondusergrpcode;
    private String thirdugrpugengrouptypecode;
    private String thirdusergrpcode;
    private String frugrpugenericgrouptypecode;
    private String fourthusergrpcode;
    private String fifthugrpugengrouptypecode;
    private String fifthusergrpcode;
    private String sixthugrpugengrouptypecode;
    private String sixthusergrpcode;
    private String seugrpugenericgrouptypecode;
    private String seventhusergrpcode;
    private BigDecimal referenceamt1;
    private BigDecimal referenceamt2;
    private BigDecimal referenceamt3;
    private BigDecimal referenceamt4;
    private BigDecimal referenceamt5;
    private Date reconciliationdate;
    private String reconciledby;
    private String reconciletranno;
    private Timestamp reconciledon;
    private String assetnocountercode;
    private String assetnocode;
    private Integer wsoperation;
    private String impoperationuser;
    private Integer importstatus;
    private Timestamp impcreationdatetime;
    private String impcreationuser;
    private Timestamp implastupdatedatetime;
    private String implastupdateuser;
    private Timestamp importdatetime;
    private Integer retrynr;
    private Long nextretry;
    private Long importid;

    @Basic
    @Column(name = "FATHERID", nullable = true)
    public Long getFatherid() {
        return fatherid;
    }

    public void setFatherid(Long fatherid) {
        this.fatherid = fatherid;
    }

    @Id
    @Column(name = "IMPORTAUTOCOUNTER", nullable = false)
    public Long getImportautocounter() {
        return importautocounter;
    }

    public void setImportautocounter(Long importautocounter) {
        this.importautocounter = importautocounter;
    }

    @Basic
    @Column(name = "LINENUMBER", nullable = true, precision = 0)
    public Integer getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(Integer linenumber) {
        this.linenumber = linenumber;
    }

    @Basic
    @Column(name = "LINETEMPLATECODE", nullable = true, length = 3)
    public String getLinetemplatecode() {
        return linetemplatecode;
    }

    public void setLinetemplatecode(String linetemplatecode) {
        this.linetemplatecode = linetemplatecode;
    }

    @Basic
    @Column(name = "COMPANYCODE", nullable = true, length = 3)
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
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
    @Column(name = "CREDITLINE", nullable = false)
    public Short getCreditline() {
        return creditline;
    }

    public void setCreditline(Short creditline) {
        this.creditline = creditline;
    }

    @Basic
    @Column(name = "CURRENTSTATUS", nullable = true, length = 2)
    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    @Basic
    @Column(name = "DIRECTENTRY", nullable = false)
    public Short getDirectentry() {
        return directentry;
    }

    public void setDirectentry(Short directentry) {
        this.directentry = directentry;
    }

    @Basic
    @Column(name = "SLCUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    public String getSlcustomersuppliertype() {
        return slcustomersuppliertype;
    }

    public void setSlcustomersuppliertype(String slcustomersuppliertype) {
        this.slcustomersuppliertype = slcustomersuppliertype;
    }

    @Basic
    @Column(name = "SLCUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    public String getSlcustomersuppliercode() {
        return slcustomersuppliercode;
    }

    public void setSlcustomersuppliercode(String slcustomersuppliercode) {
        this.slcustomersuppliercode = slcustomersuppliercode;
    }

    @Basic
    @Column(name = "AMOUNTINDC", nullable = true, precision = 5)
    public BigDecimal getAmountindc() {
        return amountindc;
    }

    public void setAmountindc(BigDecimal amountindc) {
        this.amountindc = amountindc;
    }

    @Basic
    @Column(name = "DOCUMENTCURRENCYCODE", nullable = true, length = 4)
    public String getDocumentcurrencycode() {
        return documentcurrencycode;
    }

    public void setDocumentcurrencycode(String documentcurrencycode) {
        this.documentcurrencycode = documentcurrencycode;
    }

    @Basic
    @Column(name = "EXCHANGERATE", nullable = true, precision = 15)
    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    @Basic
    @Column(name = "AMOUNTINCC", nullable = true, precision = 5)
    public BigDecimal getAmountincc() {
        return amountincc;
    }

    public void setAmountincc(BigDecimal amountincc) {
        this.amountincc = amountincc;
    }

    @Basic
    @Column(name = "COMPANYCURRENCYCODE", nullable = true, length = 4)
    public String getCompanycurrencycode() {
        return companycurrencycode;
    }

    public void setCompanycurrencycode(String companycurrencycode) {
        this.companycurrencycode = companycurrencycode;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCODE", nullable = true, length = 6)
    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    @Basic
    @Column(name = "PROJECTCODE", nullable = true, length = 20)
    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @Basic
    @Column(name = "PROFITCENTERCODE", nullable = true, length = 10)
    public String getProfitcentercode() {
        return profitcentercode;
    }

    public void setProfitcentercode(String profitcentercode) {
        this.profitcentercode = profitcentercode;
    }

    @Basic
    @Column(name = "COSTCENTERCODE", nullable = true, length = 20)
    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    @Basic
    @Column(name = "COMMENTS", nullable = true, length = 255)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "DESTINATIONCOMPANYCODE", nullable = true, length = 3)
    public String getDestinationcompanycode() {
        return destinationcompanycode;
    }

    public void setDestinationcompanycode(String destinationcompanycode) {
        this.destinationcompanycode = destinationcompanycode;
    }

    @Basic
    @Column(name = "ICFDLCOMPANYCODE", nullable = true, length = 3)
    public String getIcfdlcompanycode() {
        return icfdlcompanycode;
    }

    public void setIcfdlcompanycode(String icfdlcompanycode) {
        this.icfdlcompanycode = icfdlcompanycode;
    }

    @Basic
    @Column(name = "ICFDLBUSINESSUNITCODE", nullable = true, length = 10)
    public String getIcfdlbusinessunitcode() {
        return icfdlbusinessunitcode;
    }

    public void setIcfdlbusinessunitcode(String icfdlbusinessunitcode) {
        this.icfdlbusinessunitcode = icfdlbusinessunitcode;
    }

    @Basic
    @Column(name = "ICFDLFINANCIALYEARCODE", nullable = true, precision = 0)
    public Integer getIcfdlfinancialyearcode() {
        return icfdlfinancialyearcode;
    }

    public void setIcfdlfinancialyearcode(Integer icfdlfinancialyearcode) {
        this.icfdlfinancialyearcode = icfdlfinancialyearcode;
    }

    @Basic
    @Column(name = "ICFDLDOCUMENTTEMPLATECODE", nullable = true, length = 3)
    public String getIcfdldocumenttemplatecode() {
        return icfdldocumenttemplatecode;
    }

    public void setIcfdldocumenttemplatecode(String icfdldocumenttemplatecode) {
        this.icfdldocumenttemplatecode = icfdldocumenttemplatecode;
    }

    @Basic
    @Column(name = "ICFDLSTATISTICALGROUPCODE", nullable = true, length = 6)
    public String getIcfdlstatisticalgroupcode() {
        return icfdlstatisticalgroupcode;
    }

    public void setIcfdlstatisticalgroupcode(String icfdlstatisticalgroupcode) {
        this.icfdlstatisticalgroupcode = icfdlstatisticalgroupcode;
    }

    @Basic
    @Column(name = "ICFDLCODE", nullable = true, length = 15)
    public String getIcfdlcode() {
        return icfdlcode;
    }

    public void setIcfdlcode(String icfdlcode) {
        this.icfdlcode = icfdlcode;
    }

    @Basic
    @Column(name = "REFERENCETEXT1", nullable = true, length = 50)
    public String getReferencetext1() {
        return referencetext1;
    }

    public void setReferencetext1(String referencetext1) {
        this.referencetext1 = referencetext1;
    }

    @Basic
    @Column(name = "REFERENCETEXT2", nullable = true, length = 20)
    public String getReferencetext2() {
        return referencetext2;
    }

    public void setReferencetext2(String referencetext2) {
        this.referencetext2 = referencetext2;
    }

    @Basic
    @Column(name = "REFERENCETEXT3", nullable = true, length = 20)
    public String getReferencetext3() {
        return referencetext3;
    }

    public void setReferencetext3(String referencetext3) {
        this.referencetext3 = referencetext3;
    }

    @Basic
    @Column(name = "REFERENCETEXT4", nullable = true, length = 20)
    public String getReferencetext4() {
        return referencetext4;
    }

    public void setReferencetext4(String referencetext4) {
        this.referencetext4 = referencetext4;
    }

    @Basic
    @Column(name = "REFERENCETEXT5", nullable = true, length = 20)
    public String getReferencetext5() {
        return referencetext5;
    }

    public void setReferencetext5(String referencetext5) {
        this.referencetext5 = referencetext5;
    }

    @Basic
    @Column(name = "FIRSTUGRPUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getFirstugrpugengrouptypecode() {
        return firstugrpugengrouptypecode;
    }

    public void setFirstugrpugengrouptypecode(String firstugrpugengrouptypecode) {
        this.firstugrpugengrouptypecode = firstugrpugengrouptypecode;
    }

    @Basic
    @Column(name = "FIRSTUSERGRPCODE", nullable = true, length = 10)
    public String getFirstusergrpcode() {
        return firstusergrpcode;
    }

    public void setFirstusergrpcode(String firstusergrpcode) {
        this.firstusergrpcode = firstusergrpcode;
    }

    @Basic
    @Column(name = "SNDUGRPUGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getSndugrpugenericgrouptypecode() {
        return sndugrpugenericgrouptypecode;
    }

    public void setSndugrpugenericgrouptypecode(String sndugrpugenericgrouptypecode) {
        this.sndugrpugenericgrouptypecode = sndugrpugenericgrouptypecode;
    }

    @Basic
    @Column(name = "SECONDUSERGRPCODE", nullable = true, length = 10)
    public String getSecondusergrpcode() {
        return secondusergrpcode;
    }

    public void setSecondusergrpcode(String secondusergrpcode) {
        this.secondusergrpcode = secondusergrpcode;
    }

    @Basic
    @Column(name = "THIRDUGRPUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getThirdugrpugengrouptypecode() {
        return thirdugrpugengrouptypecode;
    }

    public void setThirdugrpugengrouptypecode(String thirdugrpugengrouptypecode) {
        this.thirdugrpugengrouptypecode = thirdugrpugengrouptypecode;
    }

    @Basic
    @Column(name = "THIRDUSERGRPCODE", nullable = true, length = 10)
    public String getThirdusergrpcode() {
        return thirdusergrpcode;
    }

    public void setThirdusergrpcode(String thirdusergrpcode) {
        this.thirdusergrpcode = thirdusergrpcode;
    }

    @Basic
    @Column(name = "FRUGRPUGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getFrugrpugenericgrouptypecode() {
        return frugrpugenericgrouptypecode;
    }

    public void setFrugrpugenericgrouptypecode(String frugrpugenericgrouptypecode) {
        this.frugrpugenericgrouptypecode = frugrpugenericgrouptypecode;
    }

    @Basic
    @Column(name = "FOURTHUSERGRPCODE", nullable = true, length = 10)
    public String getFourthusergrpcode() {
        return fourthusergrpcode;
    }

    public void setFourthusergrpcode(String fourthusergrpcode) {
        this.fourthusergrpcode = fourthusergrpcode;
    }

    @Basic
    @Column(name = "FIFTHUGRPUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getFifthugrpugengrouptypecode() {
        return fifthugrpugengrouptypecode;
    }

    public void setFifthugrpugengrouptypecode(String fifthugrpugengrouptypecode) {
        this.fifthugrpugengrouptypecode = fifthugrpugengrouptypecode;
    }

    @Basic
    @Column(name = "FIFTHUSERGRPCODE", nullable = true, length = 10)
    public String getFifthusergrpcode() {
        return fifthusergrpcode;
    }

    public void setFifthusergrpcode(String fifthusergrpcode) {
        this.fifthusergrpcode = fifthusergrpcode;
    }

    @Basic
    @Column(name = "SIXTHUGRPUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getSixthugrpugengrouptypecode() {
        return sixthugrpugengrouptypecode;
    }

    public void setSixthugrpugengrouptypecode(String sixthugrpugengrouptypecode) {
        this.sixthugrpugengrouptypecode = sixthugrpugengrouptypecode;
    }

    @Basic
    @Column(name = "SIXTHUSERGRPCODE", nullable = true, length = 10)
    public String getSixthusergrpcode() {
        return sixthusergrpcode;
    }

    public void setSixthusergrpcode(String sixthusergrpcode) {
        this.sixthusergrpcode = sixthusergrpcode;
    }

    @Basic
    @Column(name = "SEUGRPUGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getSeugrpugenericgrouptypecode() {
        return seugrpugenericgrouptypecode;
    }

    public void setSeugrpugenericgrouptypecode(String seugrpugenericgrouptypecode) {
        this.seugrpugenericgrouptypecode = seugrpugenericgrouptypecode;
    }

    @Basic
    @Column(name = "SEVENTHUSERGRPCODE", nullable = true, length = 10)
    public String getSeventhusergrpcode() {
        return seventhusergrpcode;
    }

    public void setSeventhusergrpcode(String seventhusergrpcode) {
        this.seventhusergrpcode = seventhusergrpcode;
    }

    @Basic
    @Column(name = "REFERENCEAMT1", nullable = true, precision = 5)
    public BigDecimal getReferenceamt1() {
        return referenceamt1;
    }

    public void setReferenceamt1(BigDecimal referenceamt1) {
        this.referenceamt1 = referenceamt1;
    }

    @Basic
    @Column(name = "REFERENCEAMT2", nullable = true, precision = 5)
    public BigDecimal getReferenceamt2() {
        return referenceamt2;
    }

    public void setReferenceamt2(BigDecimal referenceamt2) {
        this.referenceamt2 = referenceamt2;
    }

    @Basic
    @Column(name = "REFERENCEAMT3", nullable = true, precision = 5)
    public BigDecimal getReferenceamt3() {
        return referenceamt3;
    }

    public void setReferenceamt3(BigDecimal referenceamt3) {
        this.referenceamt3 = referenceamt3;
    }

    @Basic
    @Column(name = "REFERENCEAMT4", nullable = true, precision = 5)
    public BigDecimal getReferenceamt4() {
        return referenceamt4;
    }

    public void setReferenceamt4(BigDecimal referenceamt4) {
        this.referenceamt4 = referenceamt4;
    }

    @Basic
    @Column(name = "REFERENCEAMT5", nullable = true, precision = 5)
    public BigDecimal getReferenceamt5() {
        return referenceamt5;
    }

    public void setReferenceamt5(BigDecimal referenceamt5) {
        this.referenceamt5 = referenceamt5;
    }

    @Basic
    @Column(name = "RECONCILIATIONDATE", nullable = true)
    public Date getReconciliationdate() {
        return reconciliationdate;
    }

    public void setReconciliationdate(Date reconciliationdate) {
        this.reconciliationdate = reconciliationdate;
    }

    @Basic
    @Column(name = "RECONCILEDBY", nullable = true, length = 25)
    public String getReconciledby() {
        return reconciledby;
    }

    public void setReconciledby(String reconciledby) {
        this.reconciledby = reconciledby;
    }

    @Basic
    @Column(name = "RECONCILETRANNO", nullable = true, length = 15)
    public String getReconciletranno() {
        return reconciletranno;
    }

    public void setReconciletranno(String reconciletranno) {
        this.reconciletranno = reconciletranno;
    }

    @Basic
    @Column(name = "RECONCILEDON", nullable = true)
    public Timestamp getReconciledon() {
        return reconciledon;
    }

    public void setReconciledon(Timestamp reconciledon) {
        this.reconciledon = reconciledon;
    }

    @Basic
    @Column(name = "ASSETNOCOUNTERCODE", nullable = true, length = 8)
    public String getAssetnocountercode() {
        return assetnocountercode;
    }

    public void setAssetnocountercode(String assetnocountercode) {
        this.assetnocountercode = assetnocountercode;
    }

    @Basic
    @Column(name = "ASSETNOCODE", nullable = true, length = 15)
    public String getAssetnocode() {
        return assetnocode;
    }

    public void setAssetnocode(String assetnocode) {
        this.assetnocode = assetnocode;
    }

    @Basic
    @Column(name = "WSOPERATION", nullable = false)
    public Integer getWsoperation() {
        return wsoperation;
    }

    public void setWsoperation(Integer wsoperation) {
        this.wsoperation = wsoperation;
    }

    @Basic
    @Column(name = "IMPOPERATIONUSER", nullable = true, length = 25)
    public String getImpoperationuser() {
        return impoperationuser;
    }

    public void setImpoperationuser(String impoperationuser) {
        this.impoperationuser = impoperationuser;
    }

    @Basic
    @Column(name = "IMPORTSTATUS", nullable = false)
    public Integer getImportstatus() {
        return importstatus;
    }

    public void setImportstatus(Integer importstatus) {
        this.importstatus = importstatus;
    }

    @Basic
    @Column(name = "IMPCREATIONDATETIME", nullable = true)
    public Timestamp getImpcreationdatetime() {
        return impcreationdatetime;
    }

    public void setImpcreationdatetime(Timestamp impcreationdatetime) {
        this.impcreationdatetime = impcreationdatetime;
    }

    @Basic
    @Column(name = "IMPCREATIONUSER", nullable = true, length = 25)
    public String getImpcreationuser() {
        return impcreationuser;
    }

    public void setImpcreationuser(String impcreationuser) {
        this.impcreationuser = impcreationuser;
    }

    @Basic
    @Column(name = "IMPLASTUPDATEDATETIME", nullable = true)
    public Timestamp getImplastupdatedatetime() {
        return implastupdatedatetime;
    }

    public void setImplastupdatedatetime(Timestamp implastupdatedatetime) {
        this.implastupdatedatetime = implastupdatedatetime;
    }

    @Basic
    @Column(name = "IMPLASTUPDATEUSER", nullable = true, length = 25)
    public String getImplastupdateuser() {
        return implastupdateuser;
    }

    public void setImplastupdateuser(String implastupdateuser) {
        this.implastupdateuser = implastupdateuser;
    }

    @Basic
    @Column(name = "IMPORTDATETIME", nullable = true)
    public Timestamp getImportdatetime() {
        return importdatetime;
    }

    public void setImportdatetime(Timestamp importdatetime) {
        this.importdatetime = importdatetime;
    }

    @Basic
    @Column(name = "RETRYNR", nullable = false)
    public Integer getRetrynr() {
        return retrynr;
    }

    public void setRetrynr(Integer retrynr) {
        this.retrynr = retrynr;
    }

    @Basic
    @Column(name = "NEXTRETRY", nullable = false)
    public Long getNextretry() {
        return nextretry;
    }

    public void setNextretry(Long nextretry) {
        this.nextretry = nextretry;
    }

    @Basic
    @Column(name = "IMPORTID", nullable = false)
    public Long getImportid() {
        return importid;
    }

    public void setImportid(Long importid) {
        this.importid = importid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Findocumentlinebean that = (Findocumentlinebean) o;
        return Objects.equals(fatherid, that.fatherid) && Objects.equals(importautocounter, that.importautocounter) && Objects.equals(linenumber, that.linenumber) && Objects.equals(linetemplatecode, that.linetemplatecode) && Objects.equals(companycode, that.companycode) && Objects.equals(glcode, that.glcode) && Objects.equals(creditline, that.creditline) && Objects.equals(currentstatus, that.currentstatus) && Objects.equals(directentry, that.directentry) && Objects.equals(slcustomersuppliertype, that.slcustomersuppliertype) && Objects.equals(slcustomersuppliercode, that.slcustomersuppliercode) && Objects.equals(amountindc, that.amountindc) && Objects.equals(documentcurrencycode, that.documentcurrencycode) && Objects.equals(exchangerate, that.exchangerate) && Objects.equals(amountincc, that.amountincc) && Objects.equals(companycurrencycode, that.companycurrencycode) && Objects.equals(statisticalgroupcode, that.statisticalgroupcode) && Objects.equals(projectcode, that.projectcode) && Objects.equals(profitcentercode, that.profitcentercode) && Objects.equals(costcentercode, that.costcentercode) && Objects.equals(comments, that.comments) && Objects.equals(destinationcompanycode, that.destinationcompanycode) && Objects.equals(icfdlcompanycode, that.icfdlcompanycode) && Objects.equals(icfdlbusinessunitcode, that.icfdlbusinessunitcode) && Objects.equals(icfdlfinancialyearcode, that.icfdlfinancialyearcode) && Objects.equals(icfdldocumenttemplatecode, that.icfdldocumenttemplatecode) && Objects.equals(icfdlstatisticalgroupcode, that.icfdlstatisticalgroupcode) && Objects.equals(icfdlcode, that.icfdlcode) && Objects.equals(referencetext1, that.referencetext1) && Objects.equals(referencetext2, that.referencetext2) && Objects.equals(referencetext3, that.referencetext3) && Objects.equals(referencetext4, that.referencetext4) && Objects.equals(referencetext5, that.referencetext5) && Objects.equals(firstugrpugengrouptypecode, that.firstugrpugengrouptypecode) && Objects.equals(firstusergrpcode, that.firstusergrpcode) && Objects.equals(sndugrpugenericgrouptypecode, that.sndugrpugenericgrouptypecode) && Objects.equals(secondusergrpcode, that.secondusergrpcode) && Objects.equals(thirdugrpugengrouptypecode, that.thirdugrpugengrouptypecode) && Objects.equals(thirdusergrpcode, that.thirdusergrpcode) && Objects.equals(frugrpugenericgrouptypecode, that.frugrpugenericgrouptypecode) && Objects.equals(fourthusergrpcode, that.fourthusergrpcode) && Objects.equals(fifthugrpugengrouptypecode, that.fifthugrpugengrouptypecode) && Objects.equals(fifthusergrpcode, that.fifthusergrpcode) && Objects.equals(sixthugrpugengrouptypecode, that.sixthugrpugengrouptypecode) && Objects.equals(sixthusergrpcode, that.sixthusergrpcode) && Objects.equals(seugrpugenericgrouptypecode, that.seugrpugenericgrouptypecode) && Objects.equals(seventhusergrpcode, that.seventhusergrpcode) && Objects.equals(referenceamt1, that.referenceamt1) && Objects.equals(referenceamt2, that.referenceamt2) && Objects.equals(referenceamt3, that.referenceamt3) && Objects.equals(referenceamt4, that.referenceamt4) && Objects.equals(referenceamt5, that.referenceamt5) && Objects.equals(reconciliationdate, that.reconciliationdate) && Objects.equals(reconciledby, that.reconciledby) && Objects.equals(reconciletranno, that.reconciletranno) && Objects.equals(reconciledon, that.reconciledon) && Objects.equals(assetnocountercode, that.assetnocountercode) && Objects.equals(assetnocode, that.assetnocode) && Objects.equals(wsoperation, that.wsoperation) && Objects.equals(impoperationuser, that.impoperationuser) && Objects.equals(importstatus, that.importstatus) && Objects.equals(impcreationdatetime, that.impcreationdatetime) && Objects.equals(impcreationuser, that.impcreationuser) && Objects.equals(implastupdatedatetime, that.implastupdatedatetime) && Objects.equals(implastupdateuser, that.implastupdateuser) && Objects.equals(importdatetime, that.importdatetime) && Objects.equals(retrynr, that.retrynr) && Objects.equals(nextretry, that.nextretry) && Objects.equals(importid, that.importid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fatherid, importautocounter, linenumber, linetemplatecode, companycode, glcode, creditline, currentstatus, directentry, slcustomersuppliertype, slcustomersuppliercode, amountindc, documentcurrencycode, exchangerate, amountincc, companycurrencycode, statisticalgroupcode, projectcode, profitcentercode, costcentercode, comments, destinationcompanycode, icfdlcompanycode, icfdlbusinessunitcode, icfdlfinancialyearcode, icfdldocumenttemplatecode, icfdlstatisticalgroupcode, icfdlcode, referencetext1, referencetext2, referencetext3, referencetext4, referencetext5, firstugrpugengrouptypecode, firstusergrpcode, sndugrpugenericgrouptypecode, secondusergrpcode, thirdugrpugengrouptypecode, thirdusergrpcode, frugrpugenericgrouptypecode, fourthusergrpcode, fifthugrpugengrouptypecode, fifthusergrpcode, sixthugrpugengrouptypecode, sixthusergrpcode, seugrpugenericgrouptypecode, seventhusergrpcode, referenceamt1, referenceamt2, referenceamt3, referenceamt4, referenceamt5, reconciliationdate, reconciledby, reconciletranno, reconciledon, assetnocountercode, assetnocode, wsoperation, impoperationuser, importstatus, impcreationdatetime, impcreationuser, implastupdatedatetime, implastupdateuser, importdatetime, retrynr, nextretry, importid);
    }
}
