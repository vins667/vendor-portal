package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "purchaseinvoice")
public class Purchaseinvoice {
    @EmbeddedId
    private PurchaseinvoiceId id;
    @Basic
    @Column(name = "STEP", nullable = true, length = 1)
    private String step;
    @Basic
    @Column(name = "DFINDOCBUSINESSUNITCODE", nullable = true, length = 10)
    private String dfindocbusinessunitcode;
    @Basic
    @Column(name = "DFINDOCFINANCIALYEARCODE", nullable = true, precision = 0)
    private Integer dfindocfinancialyearcode;
    @Basic
    @Column(name = "DFINDOCTEMPLATECODE", nullable = true, length = 3)
    private String dfindoctemplatecode;
    @Basic
    @Column(name = "INVOICEPARKINGDATE", nullable = false)
    private Date invoiceparkingdate;
    @Basic
    @Column(name = "DFINDOCSTATISTICALGROUPCODE", nullable = true, length = 6)
    private String dfindocstatisticalgroupcode;
    @Basic
    @Column(name = "INVOICECURRENCYCODE", nullable = true, length = 4)
    private String invoicecurrencycode;
    @Basic
    @Column(name = "DFINDOCCODE", nullable = true, length = 15)
    private String dfindoccode;
    @Basic
    @Column(name = "PURCHASEORDERCOUNTERCODE", nullable = true, length = 8)
    private String purchaseordercountercode;
    @Basic
    @Column(name = "PURCHASEORDERCODE", nullable = true, length = 15)
    private String purchaseordercode;
    @Basic
    @Column(name = "TERMSOFPAYMENTCOMPANYCODE", nullable = true, length = 3)
    private String termsofpaymentcompanycode;
    @Basic
    @Column(name = "TERMSOFPAYMENTCODE", nullable = true, length = 3)
    private String termsofpaymentcode;
    @Basic
    @Column(name = "FOBDELIVERYTERMSCOMPANYCODE", nullable = true, length = 3)
    private String fobdeliverytermscompanycode;
    @Basic
    @Column(name = "FOBDELIVERYTERMSCODE", nullable = true, length = 3)
    private String fobdeliverytermscode;
    @Basic
    @Column(name = "PAYDUEDATE", nullable = true)
    private Date payduedate;
    @Basic
    @Column(name = "BASICVALUE", nullable = false, precision = 5)
    private BigDecimal basicvalue;
    @Basic
    @Column(name = "INVOICEAMOUNT", nullable = false, precision = 5)
    private BigDecimal invoiceamount;
    @Basic
    @Column(name = "GROSSVALUE", nullable = false, precision = 5)
    private BigDecimal grossvalue;
    @Basic
    @Column(name = "TAXVALUE", nullable = true, precision = 5)
    private BigDecimal taxvalue;
    @Basic
    @Column(name = "BILLPASSEDAMOUNT", nullable = false, precision = 5)
    private BigDecimal billpassedamount;
    @Basic
    @Column(name = "ACTUALBILLPASSEDAMOUNT", nullable = false, precision = 5)
    private BigDecimal actualbillpassedamount;
    @Basic
    @Column(name = "DEDUCTIONAMOUNT", nullable = false, precision = 5)
    private BigDecimal deductionamount;
    @Basic
    @Column(name = "LCLCNO", nullable = true, length = 35)
    private String lclcno;
    @Basic
    @Column(name = "LCLCDATE", nullable = true)
    private Date lclcdate;
    @Basic
    @Column(name = "TAXTEMPLATETEMPLATETYPE", nullable = true, length = 2)
    private String taxtemplatetemplatetype;
    @Basic
    @Column(name = "TAXTEMPLATECODE", nullable = true, length = 3)
    private String taxtemplatecode;
    @Basic
    @Column(name = "RG23ISCODE", nullable = true, length = 15)
    private String rg23Iscode;
    @Basic
    @Column(name = "RG23IISCODE", nullable = true, length = 15)
    private String rg23Iiscode;
    @Basic
    @Column(name = "PAYMENTMADE", nullable = false)
    private int paymentmade;
    @Basic
    @Column(name = "USEDCOUNTER", nullable = false)
    private int usedcounter;
    @Basic
    @Column(name = "POSTINGFLAG", nullable = false)
    private int postingflag;
    @Basic
    @Column(name = "CFORMNO", nullable = true, length = 20)
    private String cformno;
    @Basic
    @Column(name = "OPTDSTDSTEUSERGENGRPTYPECODE", nullable = true, length = 3)
    private String optdstdsteusergengrptypecode;
    @Basic
    @Column(name = "OPTDSTDSTYPECODE", nullable = true, length = 10)
    private String optdstdstypecode;
    @Basic
    @Column(name = "OPTDSTDSCODE", nullable = true, length = 6)
    private String optdstdscode;
    @Basic
    @Column(name = "OPTDSTDSITAXCODE", nullable = true, length = 3)
    private String optdstdsitaxcode;
    @Basic
    @Column(name = "TDSPERCENTAGE", nullable = true, precision = 2)
    private BigDecimal tdspercentage;
    @Basic
    @Column(name = "TDSAPPLICABLEAMOUNT", nullable = true, precision = 5)
    private BigDecimal tdsapplicableamount;
    @Basic
    @Column(name = "TDSAMOUNT", nullable = true, precision = 5)
    private BigDecimal tdsamount;
    @Basic
    @Column(name = "TDSGLCOMPANYCODE", nullable = true, length = 3)
    private String tdsglcompanycode;
    @Basic
    @Column(name = "TDSGLCODE", nullable = true, length = 20)
    private String tdsglcode;
    @Basic
    @Column(name = "FLAG", nullable = true, length = 15)
    private String flag;
    @Basic
    @Column(name = "SAPMESSAGE", nullable = true, length = 32700)
    private String sapmessage;
    @Basic
    @Column(name = "DEBITNOTEFLAG", nullable = true, length = 15)
    private String debitnoteflag;
    @Basic
    @Column(name = "DEBITNOTEMESSAGE", nullable = true, length = 32700)
    private String debitnotemessage;
    @Basic
    @Column(name = "FINDOCBUSINESSUNITCODE", nullable = true, length = 10)
    private String findocbusinessunitcode;
    @Basic
    @Column(name = "FINDOCFINANCIALYEARCODE", nullable = true, precision = 0)
    private Integer findocfinancialyearcode;
    @Basic
    @Column(name = "FINDOCTEMPLATECODE", nullable = true, length = 3)
    private String findoctemplatecode;
    @Basic
    @Column(name = "FINDOCSTATISTICALGROUPCODE", nullable = true, length = 6)
    private String findocstatisticalgroupcode;
    @Basic
    @Column(name = "FINDOCCODE", nullable = true, length = 15)
    private String findoccode;
    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    private Timestamp creationdatetime;
    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    private String creationuser;
    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    private Timestamp lastupdatedatetime;
    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    private String lastupdateuser;
    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    private long absuniqueid;
    @Basic
    @Column(name = "EXTOPCOUNTERCODE", nullable = true, length = 8)
    private String extopcountercode;
    @Basic
    @Column(name = "EXTOPCODE", nullable = true, length = 15)
    private String extopcode;
    @Basic
    @Column(name = "EXTOPORDERLINE", nullable = true, precision = 0)
    private Integer extoporderline;
    @Basic
    @Column(name = "OTHFINDOCCODE", nullable = true, length = 15)
    private String othfindoccode;
    @Basic
    @Column(name = "OTHFINDOCCODE2", nullable = true, length = 15)
    private String othfindoccode2;
    @Basic
    @Column(name = "CREATIONDATETIMEUTC", nullable = true)
    private Timestamp creationdatetimeutc;
    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    private Timestamp lastupdatedatetimeutc;

    public PurchaseinvoiceId getId() {
        return id;
    }

    public void setId(PurchaseinvoiceId id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getDfindocbusinessunitcode() {
        return dfindocbusinessunitcode;
    }

    public void setDfindocbusinessunitcode(String dfindocbusinessunitcode) {
        this.dfindocbusinessunitcode = dfindocbusinessunitcode;
    }

    public Integer getDfindocfinancialyearcode() {
        return dfindocfinancialyearcode;
    }

    public void setDfindocfinancialyearcode(Integer dfindocfinancialyearcode) {
        this.dfindocfinancialyearcode = dfindocfinancialyearcode;
    }

    public String getDfindoctemplatecode() {
        return dfindoctemplatecode;
    }

    public void setDfindoctemplatecode(String dfindoctemplatecode) {
        this.dfindoctemplatecode = dfindoctemplatecode;
    }

    public Date getInvoiceparkingdate() {
        return invoiceparkingdate;
    }

    public void setInvoiceparkingdate(Date invoiceparkingdate) {
        this.invoiceparkingdate = invoiceparkingdate;
    }

    public String getDfindocstatisticalgroupcode() {
        return dfindocstatisticalgroupcode;
    }

    public void setDfindocstatisticalgroupcode(String dfindocstatisticalgroupcode) {
        this.dfindocstatisticalgroupcode = dfindocstatisticalgroupcode;
    }

    public String getInvoicecurrencycode() {
        return invoicecurrencycode;
    }

    public void setInvoicecurrencycode(String invoicecurrencycode) {
        this.invoicecurrencycode = invoicecurrencycode;
    }

    public String getDfindoccode() {
        return dfindoccode;
    }

    public void setDfindoccode(String dfindoccode) {
        this.dfindoccode = dfindoccode;
    }

    public String getPurchaseordercountercode() {
        return purchaseordercountercode;
    }

    public void setPurchaseordercountercode(String purchaseordercountercode) {
        this.purchaseordercountercode = purchaseordercountercode;
    }

    public String getPurchaseordercode() {
        return purchaseordercode;
    }

    public void setPurchaseordercode(String purchaseordercode) {
        this.purchaseordercode = purchaseordercode;
    }

    public String getTermsofpaymentcompanycode() {
        return termsofpaymentcompanycode;
    }

    public void setTermsofpaymentcompanycode(String termsofpaymentcompanycode) {
        this.termsofpaymentcompanycode = termsofpaymentcompanycode;
    }

    public String getTermsofpaymentcode() {
        return termsofpaymentcode;
    }

    public void setTermsofpaymentcode(String termsofpaymentcode) {
        this.termsofpaymentcode = termsofpaymentcode;
    }

    public String getFobdeliverytermscompanycode() {
        return fobdeliverytermscompanycode;
    }

    public void setFobdeliverytermscompanycode(String fobdeliverytermscompanycode) {
        this.fobdeliverytermscompanycode = fobdeliverytermscompanycode;
    }

    public String getFobdeliverytermscode() {
        return fobdeliverytermscode;
    }

    public void setFobdeliverytermscode(String fobdeliverytermscode) {
        this.fobdeliverytermscode = fobdeliverytermscode;
    }

    public Date getPayduedate() {
        return payduedate;
    }

    public void setPayduedate(Date payduedate) {
        this.payduedate = payduedate;
    }

    public BigDecimal getBasicvalue() {
        return basicvalue;
    }

    public void setBasicvalue(BigDecimal basicvalue) {
        this.basicvalue = basicvalue;
    }

    public BigDecimal getInvoiceamount() {
        return invoiceamount;
    }

    public void setInvoiceamount(BigDecimal invoiceamount) {
        this.invoiceamount = invoiceamount;
    }

    public BigDecimal getGrossvalue() {
        return grossvalue;
    }

    public void setGrossvalue(BigDecimal grossvalue) {
        this.grossvalue = grossvalue;
    }

    public BigDecimal getTaxvalue() {
        return taxvalue;
    }

    public void setTaxvalue(BigDecimal taxvalue) {
        this.taxvalue = taxvalue;
    }

    public BigDecimal getBillpassedamount() {
        return billpassedamount;
    }

    public void setBillpassedamount(BigDecimal billpassedamount) {
        this.billpassedamount = billpassedamount;
    }

    public BigDecimal getActualbillpassedamount() {
        return actualbillpassedamount;
    }

    public void setActualbillpassedamount(BigDecimal actualbillpassedamount) {
        this.actualbillpassedamount = actualbillpassedamount;
    }

    public BigDecimal getDeductionamount() {
        return deductionamount;
    }

    public void setDeductionamount(BigDecimal deductionamount) {
        this.deductionamount = deductionamount;
    }

    public String getLclcno() {
        return lclcno;
    }

    public void setLclcno(String lclcno) {
        this.lclcno = lclcno;
    }

    public Date getLclcdate() {
        return lclcdate;
    }

    public void setLclcdate(Date lclcdate) {
        this.lclcdate = lclcdate;
    }

    public String getTaxtemplatetemplatetype() {
        return taxtemplatetemplatetype;
    }

    public void setTaxtemplatetemplatetype(String taxtemplatetemplatetype) {
        this.taxtemplatetemplatetype = taxtemplatetemplatetype;
    }

    public String getTaxtemplatecode() {
        return taxtemplatecode;
    }

    public void setTaxtemplatecode(String taxtemplatecode) {
        this.taxtemplatecode = taxtemplatecode;
    }

    public String getRg23Iscode() {
        return rg23Iscode;
    }

    public void setRg23Iscode(String rg23Iscode) {
        this.rg23Iscode = rg23Iscode;
    }

    public String getRg23Iiscode() {
        return rg23Iiscode;
    }

    public void setRg23Iiscode(String rg23Iiscode) {
        this.rg23Iiscode = rg23Iiscode;
    }

    public int getPaymentmade() {
        return paymentmade;
    }

    public void setPaymentmade(int paymentmade) {
        this.paymentmade = paymentmade;
    }

    public int getUsedcounter() {
        return usedcounter;
    }

    public void setUsedcounter(int usedcounter) {
        this.usedcounter = usedcounter;
    }

    public int getPostingflag() {
        return postingflag;
    }

    public void setPostingflag(int postingflag) {
        this.postingflag = postingflag;
    }

    public String getCformno() {
        return cformno;
    }

    public void setCformno(String cformno) {
        this.cformno = cformno;
    }

    public String getOptdstdsteusergengrptypecode() {
        return optdstdsteusergengrptypecode;
    }

    public void setOptdstdsteusergengrptypecode(String optdstdsteusergengrptypecode) {
        this.optdstdsteusergengrptypecode = optdstdsteusergengrptypecode;
    }

    public String getOptdstdstypecode() {
        return optdstdstypecode;
    }

    public void setOptdstdstypecode(String optdstdstypecode) {
        this.optdstdstypecode = optdstdstypecode;
    }

    public String getOptdstdscode() {
        return optdstdscode;
    }

    public void setOptdstdscode(String optdstdscode) {
        this.optdstdscode = optdstdscode;
    }

    public String getOptdstdsitaxcode() {
        return optdstdsitaxcode;
    }

    public void setOptdstdsitaxcode(String optdstdsitaxcode) {
        this.optdstdsitaxcode = optdstdsitaxcode;
    }

    public BigDecimal getTdspercentage() {
        return tdspercentage;
    }

    public void setTdspercentage(BigDecimal tdspercentage) {
        this.tdspercentage = tdspercentage;
    }

    public BigDecimal getTdsapplicableamount() {
        return tdsapplicableamount;
    }

    public void setTdsapplicableamount(BigDecimal tdsapplicableamount) {
        this.tdsapplicableamount = tdsapplicableamount;
    }

    public BigDecimal getTdsamount() {
        return tdsamount;
    }

    public void setTdsamount(BigDecimal tdsamount) {
        this.tdsamount = tdsamount;
    }

    public String getTdsglcompanycode() {
        return tdsglcompanycode;
    }

    public void setTdsglcompanycode(String tdsglcompanycode) {
        this.tdsglcompanycode = tdsglcompanycode;
    }

    public String getTdsglcode() {
        return tdsglcode;
    }

    public void setTdsglcode(String tdsglcode) {
        this.tdsglcode = tdsglcode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSapmessage() {
        return sapmessage;
    }

    public void setSapmessage(String sapmessage) {
        this.sapmessage = sapmessage;
    }

    public String getDebitnoteflag() {
        return debitnoteflag;
    }

    public void setDebitnoteflag(String debitnoteflag) {
        this.debitnoteflag = debitnoteflag;
    }

    public String getDebitnotemessage() {
        return debitnotemessage;
    }

    public void setDebitnotemessage(String debitnotemessage) {
        this.debitnotemessage = debitnotemessage;
    }

    public String getFindocbusinessunitcode() {
        return findocbusinessunitcode;
    }

    public void setFindocbusinessunitcode(String findocbusinessunitcode) {
        this.findocbusinessunitcode = findocbusinessunitcode;
    }

    public Integer getFindocfinancialyearcode() {
        return findocfinancialyearcode;
    }

    public void setFindocfinancialyearcode(Integer findocfinancialyearcode) {
        this.findocfinancialyearcode = findocfinancialyearcode;
    }

    public String getFindoctemplatecode() {
        return findoctemplatecode;
    }

    public void setFindoctemplatecode(String findoctemplatecode) {
        this.findoctemplatecode = findoctemplatecode;
    }

    public String getFindocstatisticalgroupcode() {
        return findocstatisticalgroupcode;
    }

    public void setFindocstatisticalgroupcode(String findocstatisticalgroupcode) {
        this.findocstatisticalgroupcode = findocstatisticalgroupcode;
    }

    public String getFindoccode() {
        return findoccode;
    }

    public void setFindoccode(String findoccode) {
        this.findoccode = findoccode;
    }

    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    public String getExtopcountercode() {
        return extopcountercode;
    }

    public void setExtopcountercode(String extopcountercode) {
        this.extopcountercode = extopcountercode;
    }

    public String getExtopcode() {
        return extopcode;
    }

    public void setExtopcode(String extopcode) {
        this.extopcode = extopcode;
    }

    public Integer getExtoporderline() {
        return extoporderline;
    }

    public void setExtoporderline(Integer extoporderline) {
        this.extoporderline = extoporderline;
    }

    public String getOthfindoccode() {
        return othfindoccode;
    }

    public void setOthfindoccode(String othfindoccode) {
        this.othfindoccode = othfindoccode;
    }

    public String getOthfindoccode2() {
        return othfindoccode2;
    }

    public void setOthfindoccode2(String othfindoccode2) {
        this.othfindoccode2 = othfindoccode2;
    }

    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

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
        Purchaseinvoice that = (Purchaseinvoice) o;
        return paymentmade == that.paymentmade && usedcounter == that.usedcounter && postingflag == that.postingflag && absuniqueid == that.absuniqueid && Objects.equals(step, that.step) && Objects.equals(dfindocbusinessunitcode, that.dfindocbusinessunitcode) && Objects.equals(dfindocfinancialyearcode, that.dfindocfinancialyearcode) && Objects.equals(dfindoctemplatecode, that.dfindoctemplatecode) && Objects.equals(invoiceparkingdate, that.invoiceparkingdate) && Objects.equals(dfindocstatisticalgroupcode, that.dfindocstatisticalgroupcode) && Objects.equals(invoicecurrencycode, that.invoicecurrencycode) && Objects.equals(dfindoccode, that.dfindoccode) && Objects.equals(purchaseordercountercode, that.purchaseordercountercode) && Objects.equals(purchaseordercode, that.purchaseordercode) && Objects.equals(termsofpaymentcompanycode, that.termsofpaymentcompanycode) && Objects.equals(termsofpaymentcode, that.termsofpaymentcode) && Objects.equals(fobdeliverytermscompanycode, that.fobdeliverytermscompanycode) && Objects.equals(fobdeliverytermscode, that.fobdeliverytermscode) && Objects.equals(payduedate, that.payduedate) && Objects.equals(basicvalue, that.basicvalue) && Objects.equals(invoiceamount, that.invoiceamount) && Objects.equals(grossvalue, that.grossvalue) && Objects.equals(taxvalue, that.taxvalue) && Objects.equals(billpassedamount, that.billpassedamount) && Objects.equals(actualbillpassedamount, that.actualbillpassedamount) && Objects.equals(deductionamount, that.deductionamount) && Objects.equals(lclcno, that.lclcno) && Objects.equals(lclcdate, that.lclcdate) && Objects.equals(taxtemplatetemplatetype, that.taxtemplatetemplatetype) && Objects.equals(taxtemplatecode, that.taxtemplatecode) && Objects.equals(rg23Iscode, that.rg23Iscode) && Objects.equals(rg23Iiscode, that.rg23Iiscode) && Objects.equals(cformno, that.cformno) && Objects.equals(optdstdsteusergengrptypecode, that.optdstdsteusergengrptypecode) && Objects.equals(optdstdstypecode, that.optdstdstypecode) && Objects.equals(optdstdscode, that.optdstdscode) && Objects.equals(optdstdsitaxcode, that.optdstdsitaxcode) && Objects.equals(tdspercentage, that.tdspercentage) && Objects.equals(tdsapplicableamount, that.tdsapplicableamount) && Objects.equals(tdsamount, that.tdsamount) && Objects.equals(tdsglcompanycode, that.tdsglcompanycode) && Objects.equals(tdsglcode, that.tdsglcode) && Objects.equals(flag, that.flag) && Objects.equals(sapmessage, that.sapmessage) && Objects.equals(debitnoteflag, that.debitnoteflag) && Objects.equals(debitnotemessage, that.debitnotemessage) && Objects.equals(findocbusinessunitcode, that.findocbusinessunitcode) && Objects.equals(findocfinancialyearcode, that.findocfinancialyearcode) && Objects.equals(findoctemplatecode, that.findoctemplatecode) && Objects.equals(findocstatisticalgroupcode, that.findocstatisticalgroupcode) && Objects.equals(findoccode, that.findoccode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(extopcountercode, that.extopcountercode) && Objects.equals(extopcode, that.extopcode) && Objects.equals(extoporderline, that.extoporderline) && Objects.equals(othfindoccode, that.othfindoccode) && Objects.equals(othfindoccode2, that.othfindoccode2) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(step, dfindocbusinessunitcode, dfindocfinancialyearcode, dfindoctemplatecode, invoiceparkingdate, dfindocstatisticalgroupcode, invoicecurrencycode, dfindoccode, purchaseordercountercode, purchaseordercode, termsofpaymentcompanycode, termsofpaymentcode, fobdeliverytermscompanycode, fobdeliverytermscode, payduedate, basicvalue, invoiceamount, grossvalue, taxvalue, billpassedamount, actualbillpassedamount, deductionamount, lclcno, lclcdate, taxtemplatetemplatetype, taxtemplatecode, rg23Iscode, rg23Iiscode, paymentmade, usedcounter, postingflag, cformno, optdstdsteusergengrptypecode, optdstdstypecode, optdstdscode, optdstdsitaxcode, tdspercentage, tdsapplicableamount, tdsamount, tdsglcompanycode, tdsglcode, flag, sapmessage, debitnoteflag, debitnotemessage, findocbusinessunitcode, findocfinancialyearcode, findoctemplatecode, findocstatisticalgroupcode, findoccode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, extopcountercode, extopcode, extoporderline, othfindoccode, othfindoccode2, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
