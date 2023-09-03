package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "productionprogressimport")
public class Productionprogressimport {
    @EmbeddedId
    private ProductionprogressimportId id;
    private Integer importstatus;
    private String nowprogressnumber;
    private String deleteprogressprogressnumber;
    private String externalprogressnumber;
    private Date progressloaddate;
    private String progresstemplatecode;
    private Short partialstep;
    private String operationtype;
    private String productionordercode;
    private Integer groupstepnumber;
    private Integer groupline;
    private String demandcountercode;
    private String demandcode;
    private String elementitemtypeaficode;
    private String elementelementsubcodekey;
    private String elementelementcode;
    private Integer stepnumber;
    private String workcentercode;
    private String operationcode;
    private String datasetcode;
    private String suppliertype;
    private String suppliercode;
    private String progresstype;
    private Date progressstartqueuedate;
    private Time progressstartqueuetime;
    private Date progressstartpreprocessdate;
    private Time progressstartpreprocesstime;
    private Date progressstartprocessdate;
    private Time progressstartprocesstime;
    private Date progressstartpostprocessdate;
    private Time progressstartpostprocesstime;
    private Date progresspartialenddate;
    private String progresspartialendtime;
    private Date progressenddate;
    private Time progressendtime;
    private Integer calshiftdailyinformation;
    private Short queueportionclosed;
    private Short preprocessportionclosed;
    private Short processportionclosed;
    private Short postprocessportionclosed;
    private BigDecimal queuerecordedmachinetime;
    private BigDecimal preprocessrecordedmachinetime;
    private BigDecimal processrecordedmachinetime;
    private BigDecimal postprocessrecordedmachinetime;
    private String costelementcompanycode;
    private String costelementitemtypecode;
    private String costelementsubcode01;
    private String costelementuomcode;
    private BigDecimal queuercordedcostelements;
    private BigDecimal preprocessrcordedcostelements;
    private BigDecimal processrcordedcostelements;
    private BigDecimal postprocessrcordedcostelements;
    private Long aduniqueid;
    private BigDecimal primaryqty;
    private String primaryuomcode;
    private BigDecimal secondaryqty;
    private String secondaryuomcode;
    private BigDecimal packagingqty;
    private String packaginguomcode;
    private String qualityitemtypecompanycode;
    private String qualityitemtypecode;
    private BigInteger qualitycode;
    private String qualityreasoncompanycode;
    private String qualityreasoncode;
    private String machinecode;
    private String operatorcode;
    private BigDecimal dyelotweight;
    private String dyelotweightumcode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;

    public ProductionprogressimportId getId() {
        return id;
    }

    public void setId(ProductionprogressimportId id) {
        this.id = id;
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
    @Column(name = "NOWPROGRESSNUMBER", nullable = true, length = 15)
    public String getNowprogressnumber() {
        return nowprogressnumber;
    }

    public void setNowprogressnumber(String nowprogressnumber) {
        this.nowprogressnumber = nowprogressnumber;
    }

    @Basic
    @Column(name = "DELETEPROGRESSPROGRESSNUMBER", nullable = true, length = 15)
    public String getDeleteprogressprogressnumber() {
        return deleteprogressprogressnumber;
    }

    public void setDeleteprogressprogressnumber(String deleteprogressprogressnumber) {
        this.deleteprogressprogressnumber = deleteprogressprogressnumber;
    }

    @Basic
    @Column(name = "EXTERNALPROGRESSNUMBER", nullable = true, length = 15)
    public String getExternalprogressnumber() {
        return externalprogressnumber;
    }

    public void setExternalprogressnumber(String externalprogressnumber) {
        this.externalprogressnumber = externalprogressnumber;
    }

    @Basic
    @Column(name = "PROGRESSLOADDATE", nullable = false)
    public Date getProgressloaddate() {
        return progressloaddate;
    }

    public void setProgressloaddate(Date progressloaddate) {
        this.progressloaddate = progressloaddate;
    }

    @Basic
    @Column(name = "PROGRESSTEMPLATECODE", nullable = true, length = 3)
    public String getProgresstemplatecode() {
        return progresstemplatecode;
    }

    public void setProgresstemplatecode(String progresstemplatecode) {
        this.progresstemplatecode = progresstemplatecode;
    }

    @Basic
    @Column(name = "PARTIALSTEP", nullable = false)
    public Short getPartialstep() {
        return partialstep;
    }

    public void setPartialstep(Short partialstep) {
        this.partialstep = partialstep;
    }

    @Basic
    @Column(name = "OPERATIONTYPE", nullable = false, length = 1)
    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    @Basic
    @Column(name = "PRODUCTIONORDERCODE", nullable = true, length = 15)
    public String getProductionordercode() {
        return productionordercode;
    }

    public void setProductionordercode(String productionordercode) {
        this.productionordercode = productionordercode;
    }

    @Basic
    @Column(name = "GROUPSTEPNUMBER", nullable = false)
    public Integer getGroupstepnumber() {
        return groupstepnumber;
    }

    public void setGroupstepnumber(Integer groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
    }

    @Basic
    @Column(name = "GROUPLINE", nullable = false)
    public Integer getGroupline() {
        return groupline;
    }

    public void setGroupline(Integer groupline) {
        this.groupline = groupline;
    }

    @Basic
    @Column(name = "DEMANDCOUNTERCODE", nullable = true, length = 8)
    public String getDemandcountercode() {
        return demandcountercode;
    }

    public void setDemandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
    }

    @Basic
    @Column(name = "DEMANDCODE", nullable = true, length = 15)
    public String getDemandcode() {
        return demandcode;
    }

    public void setDemandcode(String demandcode) {
        this.demandcode = demandcode;
    }

    @Basic
    @Column(name = "ELEMENTITEMTYPEAFICODE", nullable = true, length = 3)
    public String getElementitemtypeaficode() {
        return elementitemtypeaficode;
    }

    public void setElementitemtypeaficode(String elementitemtypeaficode) {
        this.elementitemtypeaficode = elementitemtypeaficode;
    }

    @Basic
    @Column(name = "ELEMENTELEMENTSUBCODEKEY", nullable = true, length = 20)
    public String getElementelementsubcodekey() {
        return elementelementsubcodekey;
    }

    public void setElementelementsubcodekey(String elementelementsubcodekey) {
        this.elementelementsubcodekey = elementelementsubcodekey;
    }

    @Basic
    @Column(name = "ELEMENTELEMENTCODE", nullable = true, length = 15)
    public String getElementelementcode() {
        return elementelementcode;
    }

    public void setElementelementcode(String elementelementcode) {
        this.elementelementcode = elementelementcode;
    }

    @Basic
    @Column(name = "STEPNUMBER", nullable = true, precision = 0)
    public Integer getStepnumber() {
        return stepnumber;
    }

    public void setStepnumber(Integer stepnumber) {
        this.stepnumber = stepnumber;
    }

    @Basic
    @Column(name = "WORKCENTERCODE", nullable = true, length = 8)
    public String getWorkcentercode() {
        return workcentercode;
    }

    public void setWorkcentercode(String workcentercode) {
        this.workcentercode = workcentercode;
    }

    @Basic
    @Column(name = "OPERATIONCODE", nullable = true, length = 8)
    public String getOperationcode() {
        return operationcode;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
    }

    @Basic
    @Column(name = "DATASETCODE", nullable = true, length = 20)
    public String getDatasetcode() {
        return datasetcode;
    }

    public void setDatasetcode(String datasetcode) {
        this.datasetcode = datasetcode;
    }

    @Basic
    @Column(name = "SUPPLIERTYPE", nullable = true, length = 1)
    public String getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    @Basic
    @Column(name = "SUPPLIERCODE", nullable = true, length = 8)
    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    @Basic
    @Column(name = "PROGRESSTYPE", nullable = false, length = 1)
    public String getProgresstype() {
        return progresstype;
    }

    public void setProgresstype(String progresstype) {
        this.progresstype = progresstype;
    }

    @Basic
    @Column(name = "PROGRESSSTARTQUEUEDATE", nullable = true)
    public Date getProgressstartqueuedate() {
        return progressstartqueuedate;
    }

    public void setProgressstartqueuedate(Date progressstartqueuedate) {
        this.progressstartqueuedate = progressstartqueuedate;
    }

    @Basic
    @Column(name = "PROGRESSSTARTQUEUETIME", nullable = true)
    public Time getProgressstartqueuetime() {
        return progressstartqueuetime;
    }

    public void setProgressstartqueuetime(Time progressstartqueuetime) {
        this.progressstartqueuetime = progressstartqueuetime;
    }

    @Basic
    @Column(name = "PROGRESSSTARTPREPROCESSDATE", nullable = true)
    public Date getProgressstartpreprocessdate() {
        return progressstartpreprocessdate;
    }

    public void setProgressstartpreprocessdate(Date progressstartpreprocessdate) {
        this.progressstartpreprocessdate = progressstartpreprocessdate;
    }

    @Basic
    @Column(name = "PROGRESSSTARTPREPROCESSTIME", nullable = true)
    public Time getProgressstartpreprocesstime() {
        return progressstartpreprocesstime;
    }

    public void setProgressstartpreprocesstime(Time progressstartpreprocesstime) {
        this.progressstartpreprocesstime = progressstartpreprocesstime;
    }

    @Basic
    @Column(name = "PROGRESSSTARTPROCESSDATE", nullable = true)
    public Date getProgressstartprocessdate() {
        return progressstartprocessdate;
    }

    public void setProgressstartprocessdate(Date progressstartprocessdate) {
        this.progressstartprocessdate = progressstartprocessdate;
    }

    @Basic
    @Column(name = "PROGRESSSTARTPROCESSTIME", nullable = true)
    public Time getProgressstartprocesstime() {
        return progressstartprocesstime;
    }

    public void setProgressstartprocesstime(Time progressstartprocesstime) {
        this.progressstartprocesstime = progressstartprocesstime;
    }

    @Basic
    @Column(name = "PROGRESSSTARTPOSTPROCESSDATE", nullable = true)
    public Date getProgressstartpostprocessdate() {
        return progressstartpostprocessdate;
    }

    public void setProgressstartpostprocessdate(Date progressstartpostprocessdate) {
        this.progressstartpostprocessdate = progressstartpostprocessdate;
    }

    @Basic
    @Column(name = "PROGRESSSTARTPOSTPROCESSTIME", nullable = true)
    public Time getProgressstartpostprocesstime() {
        return progressstartpostprocesstime;
    }

    public void setProgressstartpostprocesstime(Time progressstartpostprocesstime) {
        this.progressstartpostprocesstime = progressstartpostprocesstime;
    }

    @Basic
    @Column(name = "PROGRESSPARTIALENDDATE", nullable = true)
    public Date getProgresspartialenddate() {
        return progresspartialenddate;
    }

    public void setProgresspartialenddate(Date progresspartialenddate) {
        this.progresspartialenddate = progresspartialenddate;
    }

    @Basic
    @Column(name = "PROGRESSPARTIALENDTIME", nullable = true)
    public String getProgresspartialendtime() {
        return progresspartialendtime;
    }

    public void setProgresspartialendtime(String progresspartialendtime) {
        this.progresspartialendtime = progresspartialendtime;
    }

    @Basic
    @Column(name = "PROGRESSENDDATE", nullable = true)
    public Date getProgressenddate() {
        return progressenddate;
    }

    public void setProgressenddate(Date progressenddate) {
        this.progressenddate = progressenddate;
    }

    @Basic
    @Column(name = "PROGRESSENDTIME", nullable = true)
    public Time getProgressendtime() {
        return progressendtime;
    }

    public void setProgressendtime(Time progressendtime) {
        this.progressendtime = progressendtime;
    }

    @Basic
    @Column(name = "CALSHIFTDAILYINFORMATION", nullable = false)
    public Integer getCalshiftdailyinformation() {
        return calshiftdailyinformation;
    }

    public void setCalshiftdailyinformation(Integer calshiftdailyinformation) {
        this.calshiftdailyinformation = calshiftdailyinformation;
    }

    @Basic
    @Column(name = "QUEUEPORTIONCLOSED", nullable = false)
    public Short getQueueportionclosed() {
        return queueportionclosed;
    }

    public void setQueueportionclosed(Short queueportionclosed) {
        this.queueportionclosed = queueportionclosed;
    }

    @Basic
    @Column(name = "PREPROCESSPORTIONCLOSED", nullable = false)
    public Short getPreprocessportionclosed() {
        return preprocessportionclosed;
    }

    public void setPreprocessportionclosed(Short preprocessportionclosed) {
        this.preprocessportionclosed = preprocessportionclosed;
    }

    @Basic
    @Column(name = "PROCESSPORTIONCLOSED", nullable = false)
    public Short getProcessportionclosed() {
        return processportionclosed;
    }

    public void setProcessportionclosed(Short processportionclosed) {
        this.processportionclosed = processportionclosed;
    }

    @Basic
    @Column(name = "POSTPROCESSPORTIONCLOSED", nullable = false)
    public Short getPostprocessportionclosed() {
        return postprocessportionclosed;
    }

    public void setPostprocessportionclosed(Short postprocessportionclosed) {
        this.postprocessportionclosed = postprocessportionclosed;
    }

    @Basic
    @Column(name = "QUEUERECORDEDMACHINETIME", nullable = true, precision = 5)
    public BigDecimal getQueuerecordedmachinetime() {
        return queuerecordedmachinetime;
    }

    public void setQueuerecordedmachinetime(BigDecimal queuerecordedmachinetime) {
        this.queuerecordedmachinetime = queuerecordedmachinetime;
    }

    @Basic
    @Column(name = "PREPROCESSRECORDEDMACHINETIME", nullable = true, precision = 5)
    public BigDecimal getPreprocessrecordedmachinetime() {
        return preprocessrecordedmachinetime;
    }

    public void setPreprocessrecordedmachinetime(BigDecimal preprocessrecordedmachinetime) {
        this.preprocessrecordedmachinetime = preprocessrecordedmachinetime;
    }

    @Basic
    @Column(name = "PROCESSRECORDEDMACHINETIME", nullable = true, precision = 5)
    public BigDecimal getProcessrecordedmachinetime() {
        return processrecordedmachinetime;
    }

    public void setProcessrecordedmachinetime(BigDecimal processrecordedmachinetime) {
        this.processrecordedmachinetime = processrecordedmachinetime;
    }

    @Basic
    @Column(name = "POSTPROCESSRECORDEDMACHINETIME", nullable = true, precision = 5)
    public BigDecimal getPostprocessrecordedmachinetime() {
        return postprocessrecordedmachinetime;
    }

    public void setPostprocessrecordedmachinetime(BigDecimal postprocessrecordedmachinetime) {
        this.postprocessrecordedmachinetime = postprocessrecordedmachinetime;
    }

    @Basic
    @Column(name = "COSTELEMENTCOMPANYCODE", nullable = true, length = 3)
    public String getCostelementcompanycode() {
        return costelementcompanycode;
    }

    public void setCostelementcompanycode(String costelementcompanycode) {
        this.costelementcompanycode = costelementcompanycode;
    }

    @Basic
    @Column(name = "COSTELEMENTITEMTYPECODE", nullable = true, length = 3)
    public String getCostelementitemtypecode() {
        return costelementitemtypecode;
    }

    public void setCostelementitemtypecode(String costelementitemtypecode) {
        this.costelementitemtypecode = costelementitemtypecode;
    }

    @Basic
    @Column(name = "COSTELEMENTSUBCODE01", nullable = true, length = 20)
    public String getCostelementsubcode01() {
        return costelementsubcode01;
    }

    public void setCostelementsubcode01(String costelementsubcode01) {
        this.costelementsubcode01 = costelementsubcode01;
    }

    @Basic
    @Column(name = "COSTELEMENTUOMCODE", nullable = true, length = 3)
    public String getCostelementuomcode() {
        return costelementuomcode;
    }

    public void setCostelementuomcode(String costelementuomcode) {
        this.costelementuomcode = costelementuomcode;
    }

    @Basic
    @Column(name = "QUEUERCORDEDCOSTELEMENTS", nullable = true, precision = 5)
    public BigDecimal getQueuercordedcostelements() {
        return queuercordedcostelements;
    }

    public void setQueuercordedcostelements(BigDecimal queuercordedcostelements) {
        this.queuercordedcostelements = queuercordedcostelements;
    }

    @Basic
    @Column(name = "PREPROCESSRCORDEDCOSTELEMENTS", nullable = true, precision = 5)
    public BigDecimal getPreprocessrcordedcostelements() {
        return preprocessrcordedcostelements;
    }

    public void setPreprocessrcordedcostelements(BigDecimal preprocessrcordedcostelements) {
        this.preprocessrcordedcostelements = preprocessrcordedcostelements;
    }

    @Basic
    @Column(name = "PROCESSRCORDEDCOSTELEMENTS", nullable = true, precision = 5)
    public BigDecimal getProcessrcordedcostelements() {
        return processrcordedcostelements;
    }

    public void setProcessrcordedcostelements(BigDecimal processrcordedcostelements) {
        this.processrcordedcostelements = processrcordedcostelements;
    }

    @Basic
    @Column(name = "POSTPROCESSRCORDEDCOSTELEMENTS", nullable = true, precision = 5)
    public BigDecimal getPostprocessrcordedcostelements() {
        return postprocessrcordedcostelements;
    }

    public void setPostprocessrcordedcostelements(BigDecimal postprocessrcordedcostelements) {
        this.postprocessrcordedcostelements = postprocessrcordedcostelements;
    }

    @Basic
    @Column(name = "ADUNIQUEID", nullable = false)
    public Long getAduniqueid() {
        return aduniqueid;
    }

    public void setAduniqueid(Long aduniqueid) {
        this.aduniqueid = aduniqueid;
    }

    @Basic
    @Column(name = "PRIMARYQTY", nullable = true, precision = 5)
    public BigDecimal getPrimaryqty() {
        return primaryqty;
    }

    public void setPrimaryqty(BigDecimal primaryqty) {
        this.primaryqty = primaryqty;
    }

    @Basic
    @Column(name = "PRIMARYUOMCODE", nullable = true, length = 3)
    public String getPrimaryuomcode() {
        return primaryuomcode;
    }

    public void setPrimaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
    }

    @Basic
    @Column(name = "SECONDARYQTY", nullable = true, precision = 5)
    public BigDecimal getSecondaryqty() {
        return secondaryqty;
    }

    public void setSecondaryqty(BigDecimal secondaryqty) {
        this.secondaryqty = secondaryqty;
    }

    @Basic
    @Column(name = "SECONDARYUOMCODE", nullable = true, length = 3)
    public String getSecondaryuomcode() {
        return secondaryuomcode;
    }

    public void setSecondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
    }

    @Basic
    @Column(name = "PACKAGINGQTY", nullable = true, precision = 5)
    public BigDecimal getPackagingqty() {
        return packagingqty;
    }

    public void setPackagingqty(BigDecimal packagingqty) {
        this.packagingqty = packagingqty;
    }

    @Basic
    @Column(name = "PACKAGINGUOMCODE", nullable = true, length = 3)
    public String getPackaginguomcode() {
        return packaginguomcode;
    }

    public void setPackaginguomcode(String packaginguomcode) {
        this.packaginguomcode = packaginguomcode;
    }

    @Basic
    @Column(name = "QUALITYITEMTYPECOMPANYCODE", nullable = true, length = 3)
    public String getQualityitemtypecompanycode() {
        return qualityitemtypecompanycode;
    }

    public void setQualityitemtypecompanycode(String qualityitemtypecompanycode) {
        this.qualityitemtypecompanycode = qualityitemtypecompanycode;
    }

    @Basic
    @Column(name = "QUALITYITEMTYPECODE", nullable = true, length = 3)
    public String getQualityitemtypecode() {
        return qualityitemtypecode;
    }

    public void setQualityitemtypecode(String qualityitemtypecode) {
        this.qualityitemtypecode = qualityitemtypecode;
    }

    @Basic
    @Column(name = "QUALITYCODE", nullable = true, precision = 0)
    public BigInteger getQualitycode() {
        return qualitycode;
    }

    public void setQualitycode(BigInteger qualitycode) {
        this.qualitycode = qualitycode;
    }

    @Basic
    @Column(name = "QUALITYREASONCOMPANYCODE", nullable = true, length = 3)
    public String getQualityreasoncompanycode() {
        return qualityreasoncompanycode;
    }

    public void setQualityreasoncompanycode(String qualityreasoncompanycode) {
        this.qualityreasoncompanycode = qualityreasoncompanycode;
    }

    @Basic
    @Column(name = "QUALITYREASONCODE", nullable = true, length = 3)
    public String getQualityreasoncode() {
        return qualityreasoncode;
    }

    public void setQualityreasoncode(String qualityreasoncode) {
        this.qualityreasoncode = qualityreasoncode;
    }

    @Basic
    @Column(name = "MACHINECODE", nullable = true, length = 8)
    public String getMachinecode() {
        return machinecode;
    }

    public void setMachinecode(String machinecode) {
        this.machinecode = machinecode;
    }

    @Basic
    @Column(name = "OPERATORCODE", nullable = true, length = 8)
    public String getOperatorcode() {
        return operatorcode;
    }

    public void setOperatorcode(String operatorcode) {
        this.operatorcode = operatorcode;
    }

    @Basic
    @Column(name = "DYELOTWEIGHT", nullable = true, precision = 5)
    public BigDecimal getDyelotweight() {
        return dyelotweight;
    }

    public void setDyelotweight(BigDecimal dyelotweight) {
        this.dyelotweight = dyelotweight;
    }

    @Basic
    @Column(name = "DYELOTWEIGHTUMCODE", nullable = true, length = 3)
    public String getDyelotweightumcode() {
        return dyelotweightumcode;
    }

    public void setDyelotweightumcode(String dyelotweightumcode) {
        this.dyelotweightumcode = dyelotweightumcode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productionprogressimport that = (Productionprogressimport) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(importstatus, that.importstatus) &&
            Objects.equals(nowprogressnumber, that.nowprogressnumber) &&
            Objects.equals(deleteprogressprogressnumber, that.deleteprogressprogressnumber) &&
            Objects.equals(externalprogressnumber, that.externalprogressnumber) &&
            Objects.equals(progressloaddate, that.progressloaddate) &&
            Objects.equals(progresstemplatecode, that.progresstemplatecode) &&
            Objects.equals(partialstep, that.partialstep) &&
            Objects.equals(operationtype, that.operationtype) &&
            Objects.equals(productionordercode, that.productionordercode) &&
            Objects.equals(groupstepnumber, that.groupstepnumber) &&
            Objects.equals(groupline, that.groupline) &&
            Objects.equals(demandcountercode, that.demandcountercode) &&
            Objects.equals(demandcode, that.demandcode) &&
            Objects.equals(elementitemtypeaficode, that.elementitemtypeaficode) &&
            Objects.equals(elementelementsubcodekey, that.elementelementsubcodekey) &&
            Objects.equals(elementelementcode, that.elementelementcode) &&
            Objects.equals(stepnumber, that.stepnumber) &&
            Objects.equals(workcentercode, that.workcentercode) &&
            Objects.equals(operationcode, that.operationcode) &&
            Objects.equals(datasetcode, that.datasetcode) &&
            Objects.equals(suppliertype, that.suppliertype) &&
            Objects.equals(suppliercode, that.suppliercode) &&
            Objects.equals(progresstype, that.progresstype) &&
            Objects.equals(progressstartqueuedate, that.progressstartqueuedate) &&
            Objects.equals(progressstartqueuetime, that.progressstartqueuetime) &&
            Objects.equals(progressstartpreprocessdate, that.progressstartpreprocessdate) &&
            Objects.equals(progressstartpreprocesstime, that.progressstartpreprocesstime) &&
            Objects.equals(progressstartprocessdate, that.progressstartprocessdate) &&
            Objects.equals(progressstartprocesstime, that.progressstartprocesstime) &&
            Objects.equals(progressstartpostprocessdate, that.progressstartpostprocessdate) &&
            Objects.equals(progressstartpostprocesstime, that.progressstartpostprocesstime) &&
            Objects.equals(progresspartialenddate, that.progresspartialenddate) &&
            Objects.equals(progresspartialendtime, that.progresspartialendtime) &&
            Objects.equals(progressenddate, that.progressenddate) &&
            Objects.equals(progressendtime, that.progressendtime) &&
            Objects.equals(calshiftdailyinformation, that.calshiftdailyinformation) &&
            Objects.equals(queueportionclosed, that.queueportionclosed) &&
            Objects.equals(preprocessportionclosed, that.preprocessportionclosed) &&
            Objects.equals(processportionclosed, that.processportionclosed) &&
            Objects.equals(postprocessportionclosed, that.postprocessportionclosed) &&
            Objects.equals(queuerecordedmachinetime, that.queuerecordedmachinetime) &&
            Objects.equals(preprocessrecordedmachinetime, that.preprocessrecordedmachinetime) &&
            Objects.equals(processrecordedmachinetime, that.processrecordedmachinetime) &&
            Objects.equals(postprocessrecordedmachinetime, that.postprocessrecordedmachinetime) &&
            Objects.equals(costelementcompanycode, that.costelementcompanycode) &&
            Objects.equals(costelementitemtypecode, that.costelementitemtypecode) &&
            Objects.equals(costelementsubcode01, that.costelementsubcode01) &&
            Objects.equals(costelementuomcode, that.costelementuomcode) &&
            Objects.equals(queuercordedcostelements, that.queuercordedcostelements) &&
            Objects.equals(preprocessrcordedcostelements, that.preprocessrcordedcostelements) &&
            Objects.equals(processrcordedcostelements, that.processrcordedcostelements) &&
            Objects.equals(postprocessrcordedcostelements, that.postprocessrcordedcostelements) &&
            Objects.equals(aduniqueid, that.aduniqueid) &&
            Objects.equals(primaryqty, that.primaryqty) &&
            Objects.equals(primaryuomcode, that.primaryuomcode) &&
            Objects.equals(secondaryqty, that.secondaryqty) &&
            Objects.equals(secondaryuomcode, that.secondaryuomcode) &&
            Objects.equals(packagingqty, that.packagingqty) &&
            Objects.equals(packaginguomcode, that.packaginguomcode) &&
            Objects.equals(qualityitemtypecompanycode, that.qualityitemtypecompanycode) &&
            Objects.equals(qualityitemtypecode, that.qualityitemtypecode) &&
            Objects.equals(qualitycode, that.qualitycode) &&
            Objects.equals(qualityreasoncompanycode, that.qualityreasoncompanycode) &&
            Objects.equals(qualityreasoncode, that.qualityreasoncode) &&
            Objects.equals(machinecode, that.machinecode) &&
            Objects.equals(operatorcode, that.operatorcode) &&
            Objects.equals(dyelotweight, that.dyelotweight) &&
            Objects.equals(dyelotweightumcode, that.dyelotweightumcode) &&
            Objects.equals(creationdatetime, that.creationdatetime) &&
            Objects.equals(creationuser, that.creationuser) &&
            Objects.equals(lastupdatedatetime, that.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, that.lastupdateuser) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, importstatus, nowprogressnumber, deleteprogressprogressnumber, externalprogressnumber, progressloaddate, progresstemplatecode, partialstep, operationtype, productionordercode, groupstepnumber, groupline, demandcountercode, demandcode, elementitemtypeaficode, elementelementsubcodekey, elementelementcode, stepnumber, workcentercode, operationcode, datasetcode, suppliertype, suppliercode, progresstype, progressstartqueuedate, progressstartqueuetime, progressstartpreprocessdate, progressstartpreprocesstime, progressstartprocessdate, progressstartprocesstime, progressstartpostprocessdate, progressstartpostprocesstime, progresspartialenddate, progresspartialendtime, progressenddate, progressendtime, calshiftdailyinformation, queueportionclosed, preprocessportionclosed, processportionclosed, postprocessportionclosed, queuerecordedmachinetime, preprocessrecordedmachinetime, processrecordedmachinetime, postprocessrecordedmachinetime, costelementcompanycode, costelementitemtypecode, costelementsubcode01, costelementuomcode, queuercordedcostelements, preprocessrcordedcostelements, processrcordedcostelements, postprocessrcordedcostelements, aduniqueid, primaryqty, primaryuomcode, secondaryqty, secondaryuomcode, packagingqty, packaginguomcode, qualityitemtypecompanycode, qualityitemtypecode, qualitycode, qualityreasoncompanycode, qualityreasoncode, machinecode, operatorcode, dyelotweight, dyelotweightumcode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }
}
