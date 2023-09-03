package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "finopendocuments")
public class Finopendocuments implements Serializable {
    @EmbeddedId
    private FinopendocumentsId id;
    private BigInteger absversionnumber;
    private String businessunitcompanycode;
    private String financialyearcompanycode;
    private String documenttemplatecompanycode;
    private String statisticalgroupcompanycode;
    private String documenttypecode;
    private boolean creditline;
    private String orderpartnertype;
    private String orderpartnercode;
    private String glcompanycode;
    private String glcode;
    private String progressstatus;
    private boolean underproposal;
    private Date postingdate;
    private Date duedate;
    private String termsofpaymentcompanycode;
    private String termsofpaymentcode;
    private BigDecimal amountindc;
    private String documentcurrencycode;
    private BigDecimal exchangerate;
    private BigDecimal amountincc;
    private String companycurrencycode;
    private BigDecimal clearedamount;
    private String customerreference;
    private Date customerreferencedate;
    private String vendorreference;
    private Date vendorreferencedate;
    private boolean carryforwardflag;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private BigInteger absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;
    private String projectcode;

    public FinopendocumentsId getId() {
        return id;
    }

    public void setId(FinopendocumentsId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ABSVERSIONNUMBER")
    public BigInteger getAbsversionnumber() {
        return absversionnumber;
    }

    public void setAbsversionnumber(BigInteger absversionnumber) {
        this.absversionnumber = absversionnumber;
    }

    @Basic
    @Column(name = "BUSINESSUNITCOMPANYCODE")
    public String getBusinessunitcompanycode() {
        return businessunitcompanycode;
    }

    public void setBusinessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
    }

    @Basic
    @Column(name = "FINANCIALYEARCOMPANYCODE")
    public String getFinancialyearcompanycode() {
        return financialyearcompanycode;
    }

    public void setFinancialyearcompanycode(String financialyearcompanycode) {
        this.financialyearcompanycode = financialyearcompanycode;
    }

    @Basic
    @Column(name = "DOCUMENTTEMPLATECOMPANYCODE")
    public String getDocumenttemplatecompanycode() {
        return documenttemplatecompanycode;
    }

    public void setDocumenttemplatecompanycode(String documenttemplatecompanycode) {
        this.documenttemplatecompanycode = documenttemplatecompanycode;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCOMPANYCODE")
    public String getStatisticalgroupcompanycode() {
        return statisticalgroupcompanycode;
    }

    public void setStatisticalgroupcompanycode(String statisticalgroupcompanycode) {
        this.statisticalgroupcompanycode = statisticalgroupcompanycode;
    }

    @Basic
    @Column(name = "DOCUMENTTYPECODE")
    public String getDocumenttypecode() {
        return documenttypecode;
    }

    public void setDocumenttypecode(String documenttypecode) {
        this.documenttypecode = documenttypecode;
    }

    @Basic
    @Column(name = "CREDITLINE")
    public boolean isCreditline() {
        return creditline;
    }

    public void setCreditline(boolean creditline) {
        this.creditline = creditline;
    }

    @Basic
    @Column(name = "ORDERPARTNERTYPE")
    public String getOrderpartnertype() {
        return orderpartnertype;
    }

    public void setOrderpartnertype(String orderpartnertype) {
        this.orderpartnertype = orderpartnertype;
    }

    @Basic
    @Column(name = "ORDERPARTNERCODE")
    public String getOrderpartnercode() {
        return orderpartnercode;
    }

    public void setOrderpartnercode(String orderpartnercode) {
        this.orderpartnercode = orderpartnercode;
    }

    @Basic
    @Column(name = "GLCOMPANYCODE")
    public String getGlcompanycode() {
        return glcompanycode;
    }

    public void setGlcompanycode(String glcompanycode) {
        this.glcompanycode = glcompanycode;
    }

    @Basic
    @Column(name = "GLCODE")
    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    @Basic
    @Column(name = "PROGRESSSTATUS")
    public String getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(String progressstatus) {
        this.progressstatus = progressstatus;
    }

    @Basic
    @Column(name = "UNDERPROPOSAL")
    public boolean isUnderproposal() {
        return underproposal;
    }

    public void setUnderproposal(boolean underproposal) {
        this.underproposal = underproposal;
    }

    @Basic
    @Column(name = "POSTINGDATE")
    public Date getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(Date postingdate) {
        this.postingdate = postingdate;
    }

    @Basic
    @Column(name = "DUEDATE")
    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    @Basic
    @Column(name = "TERMSOFPAYMENTCOMPANYCODE")
    public String getTermsofpaymentcompanycode() {
        return termsofpaymentcompanycode;
    }

    public void setTermsofpaymentcompanycode(String termsofpaymentcompanycode) {
        this.termsofpaymentcompanycode = termsofpaymentcompanycode;
    }

    @Basic
    @Column(name = "TERMSOFPAYMENTCODE")
    public String getTermsofpaymentcode() {
        return termsofpaymentcode;
    }

    public void setTermsofpaymentcode(String termsofpaymentcode) {
        this.termsofpaymentcode = termsofpaymentcode;
    }

    @Basic
    @Column(name = "AMOUNTINDC")
    public BigDecimal getAmountindc() {
        return amountindc;
    }

    public void setAmountindc(BigDecimal amountindc) {
        this.amountindc = amountindc;
    }

    @Basic
    @Column(name = "DOCUMENTCURRENCYCODE")
    public String getDocumentcurrencycode() {
        return documentcurrencycode;
    }

    public void setDocumentcurrencycode(String documentcurrencycode) {
        this.documentcurrencycode = documentcurrencycode;
    }

    @Basic
    @Column(name = "EXCHANGERATE")
    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    @Basic
    @Column(name = "AMOUNTINCC")
    public BigDecimal getAmountincc() {
        return amountincc;
    }

    public void setAmountincc(BigDecimal amountincc) {
        this.amountincc = amountincc;
    }

    @Basic
    @Column(name = "COMPANYCURRENCYCODE")
    public String getCompanycurrencycode() {
        return companycurrencycode;
    }

    public void setCompanycurrencycode(String companycurrencycode) {
        this.companycurrencycode = companycurrencycode;
    }

    @Basic
    @Column(name = "CLEAREDAMOUNT")
    public BigDecimal getClearedamount() {
        return clearedamount;
    }

    public void setClearedamount(BigDecimal clearedamount) {
        this.clearedamount = clearedamount;
    }

    @Basic
    @Column(name = "CUSTOMERREFERENCE")
    public String getCustomerreference() {
        return customerreference;
    }

    public void setCustomerreference(String customerreference) {
        this.customerreference = customerreference;
    }

    @Basic
    @Column(name = "CUSTOMERREFERENCEDATE")
    public Date getCustomerreferencedate() {
        return customerreferencedate;
    }

    public void setCustomerreferencedate(Date customerreferencedate) {
        this.customerreferencedate = customerreferencedate;
    }

    @Basic
    @Column(name = "VENDORREFERENCE")
    public String getVendorreference() {
        return vendorreference;
    }

    public void setVendorreference(String vendorreference) {
        this.vendorreference = vendorreference;
    }

    @Basic
    @Column(name = "VENDORREFERENCEDATE")
    public Date getVendorreferencedate() {
        return vendorreferencedate;
    }

    public void setVendorreferencedate(Date vendorreferencedate) {
        this.vendorreferencedate = vendorreferencedate;
    }

    @Basic
    @Column(name = "CARRYFORWARDFLAG")
    public boolean isCarryforwardflag() {
        return carryforwardflag;
    }

    public void setCarryforwardflag(boolean carryforwardflag) {
        this.carryforwardflag = carryforwardflag;
    }

    @Basic
    @Column(name = "CREATIONDATETIME")
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "CREATIONUSER")
    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME")
    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER")
    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    @Basic
    @Column(name = "ABSUNIQUEID")
    public BigInteger getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(BigInteger absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Basic
    @Column(name = "CREATIONDATETIMEUTC")
    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC")
    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }

    @Basic
    @Column(name = "PROJECTCODE")
    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Finopendocuments that = (Finopendocuments) o;
        return creditline == that.creditline && underproposal == that.underproposal && carryforwardflag == that.carryforwardflag && Objects.equals(id, that.id) && Objects.equals(absversionnumber, that.absversionnumber) && Objects.equals(businessunitcompanycode, that.businessunitcompanycode) && Objects.equals(financialyearcompanycode, that.financialyearcompanycode) && Objects.equals(documenttemplatecompanycode, that.documenttemplatecompanycode) && Objects.equals(statisticalgroupcompanycode, that.statisticalgroupcompanycode) && Objects.equals(documenttypecode, that.documenttypecode) && Objects.equals(orderpartnertype, that.orderpartnertype) && Objects.equals(orderpartnercode, that.orderpartnercode) && Objects.equals(glcompanycode, that.glcompanycode) && Objects.equals(glcode, that.glcode) && Objects.equals(progressstatus, that.progressstatus) && Objects.equals(postingdate, that.postingdate) && Objects.equals(duedate, that.duedate) && Objects.equals(termsofpaymentcompanycode, that.termsofpaymentcompanycode) && Objects.equals(termsofpaymentcode, that.termsofpaymentcode) && Objects.equals(amountindc, that.amountindc) && Objects.equals(documentcurrencycode, that.documentcurrencycode) && Objects.equals(exchangerate, that.exchangerate) && Objects.equals(amountincc, that.amountincc) && Objects.equals(companycurrencycode, that.companycurrencycode) && Objects.equals(clearedamount, that.clearedamount) && Objects.equals(customerreference, that.customerreference) && Objects.equals(customerreferencedate, that.customerreferencedate) && Objects.equals(vendorreference, that.vendorreference) && Objects.equals(vendorreferencedate, that.vendorreferencedate) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc) && Objects.equals(projectcode, that.projectcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, absversionnumber, businessunitcompanycode, financialyearcompanycode, documenttemplatecompanycode, statisticalgroupcompanycode, documenttypecode, creditline, orderpartnertype, orderpartnercode, glcompanycode, glcode, progressstatus, underproposal, postingdate, duedate, termsofpaymentcompanycode, termsofpaymentcode, amountindc, documentcurrencycode, exchangerate, amountincc, companycurrencycode, clearedamount, customerreference, customerreferencedate, vendorreference, vendorreferencedate, carryforwardflag, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc, projectcode);
    }
}
