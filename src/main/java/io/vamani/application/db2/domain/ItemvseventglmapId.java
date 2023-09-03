package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ItemvseventglmapId implements Serializable {
    private String companycode;
    private String eventcode;
    private String divisioncode;
    private String mrnprefixcode;
    private String invoicetypecode;
    private String itemtypecode;
    private String usergenericgrpcode;
    private String usergenericgrpnamecode;
    private String logicalwarehousecode;
    private String stocktransactiontemplatecode;
    private String document;
    private String templatecode;
    private Date effectivefromdate;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "EVENTCODE", nullable = false, length = 15)
    @Id
    public String getEventcode() {
        return eventcode;
    }

    public void setEventcode(String eventcode) {
        this.eventcode = eventcode;
    }

    @Column(name = "DIVISIONCODE", nullable = false, length = 3)
    @Id
    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    @Column(name = "MRNPREFIXCODE", nullable = false, length = 3)
    @Id
    public String getMrnprefixcode() {
        return mrnprefixcode;
    }

    public void setMrnprefixcode(String mrnprefixcode) {
        this.mrnprefixcode = mrnprefixcode;
    }

    @Column(name = "INVOICETYPECODE", nullable = false, length = 3)
    @Id
    public String getInvoicetypecode() {
        return invoicetypecode;
    }

    public void setInvoicetypecode(String invoicetypecode) {
        this.invoicetypecode = invoicetypecode;
    }

    @Column(name = "ITEMTYPECODE", nullable = false, length = 3)
    @Id
    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    @Column(name = "USERGENERICGRPCODE", nullable = false, length = 4)
    @Id
    public String getUsergenericgrpcode() {
        return usergenericgrpcode;
    }

    public void setUsergenericgrpcode(String usergenericgrpcode) {
        this.usergenericgrpcode = usergenericgrpcode;
    }

    @Column(name = "USERGENERICGRPNAMECODE", nullable = false, length = 10)
    @Id
    public String getUsergenericgrpnamecode() {
        return usergenericgrpnamecode;
    }

    public void setUsergenericgrpnamecode(String usergenericgrpnamecode) {
        this.usergenericgrpnamecode = usergenericgrpnamecode;
    }

    @Column(name = "LOGICALWAREHOUSECODE", nullable = false, length = 8)
    @Id
    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    @Column(name = "STOCKTRANSACTIONTEMPLATECODE", nullable = false, length = 3)
    @Id
    public String getStocktransactiontemplatecode() {
        return stocktransactiontemplatecode;
    }

    public void setStocktransactiontemplatecode(String stocktransactiontemplatecode) {
        this.stocktransactiontemplatecode = stocktransactiontemplatecode;
    }

    @Column(name = "DOCUMENT", nullable = false, length = 1)
    @Id
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Column(name = "TEMPLATECODE", nullable = false, length = 8)
    @Id
    public String getTemplatecode() {
        return templatecode;
    }

    public void setTemplatecode(String templatecode) {
        this.templatecode = templatecode;
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
        ItemvseventglmapId that = (ItemvseventglmapId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(eventcode, that.eventcode) && Objects.equals(divisioncode, that.divisioncode) && Objects.equals(mrnprefixcode, that.mrnprefixcode) && Objects.equals(invoicetypecode, that.invoicetypecode) && Objects.equals(itemtypecode, that.itemtypecode) && Objects.equals(usergenericgrpcode, that.usergenericgrpcode) && Objects.equals(usergenericgrpnamecode, that.usergenericgrpnamecode) && Objects.equals(logicalwarehousecode, that.logicalwarehousecode) && Objects.equals(stocktransactiontemplatecode, that.stocktransactiontemplatecode) && Objects.equals(document, that.document) && Objects.equals(templatecode, that.templatecode) && Objects.equals(effectivefromdate, that.effectivefromdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, eventcode, divisioncode, mrnprefixcode, invoicetypecode, itemtypecode, usergenericgrpcode, usergenericgrpnamecode, logicalwarehousecode, stocktransactiontemplatecode, document, templatecode, effectivefromdate);
    }
}
