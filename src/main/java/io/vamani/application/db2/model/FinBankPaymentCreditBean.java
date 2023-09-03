package io.vamani.application.db2.model;

import java.math.BigDecimal;

public class FinBankPaymentCreditBean {
    private BigDecimal amountincc;
    private BigDecimal amountindc;
    private String chequeRef;
    private String comments;
    private String costcentercode;
    private String costcenterdescription;
    private String descriptions;
    private String entrydate;
    private String findocumentcode;
    private String glcode;
    private String orderpartner;
    private String orderpartnercode;
    private String orderpartnerdesc;
    private String orderpartnername;
    private String profitcentercode;
    private BigDecimal exchangerate;
    private String profitcenterdesc;

    public BigDecimal getAmountincc() {
        return amountincc;
    }

    public void setAmountincc(BigDecimal amountincc) {
        this.amountincc = amountincc;
    }

    public String getProfitcenterdesc() {
        return profitcenterdesc;
    }

    public void setProfitcenterdesc(String profitcenterdesc) {
        this.profitcenterdesc = profitcenterdesc;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    public BigDecimal getAmountindc() {
        return amountindc;
    }

    public void setAmountindc(BigDecimal amountindc) {
        this.amountindc = amountindc;
    }

    public String getChequeRef() {
        return chequeRef;
    }

    public void setChequeRef(String chequeRef) {
        this.chequeRef = chequeRef;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    public String getCostcenterdescription() {
        return costcenterdescription;
    }

    public void setCostcenterdescription(String costcenterdescription) {
        this.costcenterdescription = costcenterdescription;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getFindocumentcode() {
        return findocumentcode;
    }

    public void setFindocumentcode(String findocumentcode) {
        this.findocumentcode = findocumentcode;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getOrderpartner() {
        return orderpartner;
    }

    public void setOrderpartner(String orderpartner) {
        this.orderpartner = orderpartner;
    }

    public String getOrderpartnercode() {
        return orderpartnercode;
    }

    public void setOrderpartnercode(String orderpartnercode) {
        this.orderpartnercode = orderpartnercode;
    }

    public String getOrderpartnerdesc() {
        return orderpartnerdesc;
    }

    public void setOrderpartnerdesc(String orderpartnerdesc) {
        this.orderpartnerdesc = orderpartnerdesc;
    }

    public String getOrderpartnername() {
        return orderpartnername;
    }

    public void setOrderpartnername(String orderpartnername) {
        this.orderpartnername = orderpartnername;
    }

    public String getProfitcentercode() {
        return profitcentercode;
    }

    public void setProfitcentercode(String profitcentercode) {
        this.profitcentercode = profitcentercode;
    }
}
