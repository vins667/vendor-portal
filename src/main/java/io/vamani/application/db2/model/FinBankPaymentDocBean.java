package io.vamani.application.db2.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FinBankPaymentDocBean {


    private BigDecimal amountincc;
    private BigDecimal amountindc;
    private BigDecimal exchangerate;
    private BigDecimal openingamount;
    private Date dateSeq;
    private String amountInWord;
    private String chequeRef;
    private String chequedate;
    private String chequenumber;
    private String comments;
    private String costcentercode;
    private String costcenterdescription;
    private String currenctcode;
    private String currentstatus;
    private String newdocumentdate;
    private String descriptions;
    private String doccurrenctcode;
    private String documentdate;
    private String documentno;
    private String documenttype;
    private String entrydate;
    private String financialmonth;
    private String financialyearcode;
    private String glcode;
    private String gldescription;
    private String factory;
    private String narration;
    private String narrationNew;
    private String orderpartner;
    private String orderpartnerdesc;
    private String postingdate;
    private String profitcentercode;
    private String remark;
    private String templatename;
    private String headerTemplate;
    private String preparedBy;
    private String invoiceno;
    private String invoicedate;
    private String payto;
    private String creationdatetime;
    private String partyname;
    private String statename;
    private String gstinnumber;
    private String suppliercode;
    private String profitcenterdesc;
    private String mrn;
    private String eichallandate;
    private String eichallanno;
    private String eiinvoiceno;
    private String eiinvoicedate;
    private String eishippingbillno;
    private String eishippingbilldate;
    private String eileonumber;
    private String eileodate;
    private String eibillno;
    private String eibilldate;
    private String portofloadingcode;
    private String accgroupshort;
    private String groupcode;
    private String groupname;
    private String grp;
    private String boeno;
    private String boedate;
    private String businessunit;
    private String division;
    private String reffindoccode;

    private BigDecimal grrClearingAmount;
    private List<FinBankPaymentCreditBean> finBankPaymentCreditBeans;

    public BigDecimal getAmountincc() {
        return amountincc;
    }

    public void setAmountincc(BigDecimal amountincc) {
        this.amountincc = amountincc;
    }

    public String getNewdocumentdate() {
        return newdocumentdate;
    }

    public String getGldescription() {
        return gldescription;
    }

    public void setGldescription(String gldescription) {
        this.gldescription = gldescription;
    }

    public String getAccgroupshort() {
        return accgroupshort;
    }

    public void setAccgroupshort(String accgroupshort) {
        this.accgroupshort = accgroupshort;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void setNewdocumentdate(String newdocumentdate) {
        this.newdocumentdate = newdocumentdate;
    }

    public String getProfitcenterdesc() {
        return profitcenterdesc;
    }

    public String getPortofloadingcode() {
        return portofloadingcode;
    }

    public void setPortofloadingcode(String portofloadingcode) {
        this.portofloadingcode = portofloadingcode;
    }

    public String getEichallandate() {
        return eichallandate;
    }

    public void setEichallandate(String eichallandate) {
        this.eichallandate = eichallandate;
    }

    public String getEichallanno() {
        return eichallanno;
    }

    public void setEichallanno(String eichallanno) {
        this.eichallanno = eichallanno;
    }

    public String getEiinvoiceno() {
        return eiinvoiceno;
    }

    public void setEiinvoiceno(String eiinvoiceno) {
        this.eiinvoiceno = eiinvoiceno;
    }

    public String getEiinvoicedate() {
        return eiinvoicedate;
    }

    public void setEiinvoicedate(String eiinvoicedate) {
        this.eiinvoicedate = eiinvoicedate;
    }

    public String getEishippingbillno() {
        return eishippingbillno;
    }

    public String getNarrationNew() {
        return narrationNew;
    }

    public void setNarrationNew(String narrationNew) {
        this.narrationNew = narrationNew;
    }

    public void setEishippingbillno(String eishippingbillno) {
        this.eishippingbillno = eishippingbillno;
    }

    public String getEishippingbilldate() {
        return eishippingbilldate;
    }

    public void setEishippingbilldate(String eishippingbilldate) {
        this.eishippingbilldate = eishippingbilldate;
    }

    public String getEileonumber() {
        return eileonumber;
    }

    public void setEileonumber(String eileonumber) {
        this.eileonumber = eileonumber;
    }

    public String getEileodate() {
        return eileodate;
    }

    public void setEileodate(String eileodate) {
        this.eileodate = eileodate;
    }

    public String getEibillno() {
        return eibillno;
    }

    public void setEibillno(String eibillno) {
        this.eibillno = eibillno;
    }

    public String getEibilldate() {
        return eibilldate;
    }

    public void setEibilldate(String eibilldate) {
        this.eibilldate = eibilldate;
    }

    public void setProfitcenterdesc(String profitcenterdesc) {
        this.profitcenterdesc = profitcenterdesc;
    }

    public BigDecimal getAmountindc() {
        return amountindc;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getGstinnumber() {
        return gstinnumber;
    }

    public void setGstinnumber(String gstinnumber) {
        this.gstinnumber = gstinnumber;
    }

    public String getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(String creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public String getPayto() {
        return payto;
    }

    public void setPayto(String payto) {
        this.payto = payto;
    }

    public void setAmountindc(BigDecimal amountindc) {
        this.amountindc = amountindc;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    public BigDecimal getOpeningamount() {
        return openingamount;
    }

    public void setOpeningamount(BigDecimal openingamount) {
        this.openingamount = openingamount;
    }

    public String getAmountInWord() {
        return amountInWord;
    }

    public void setAmountInWord(String amountInWord) {
        this.amountInWord = amountInWord;
    }

    public String getChequeRef() {
        return chequeRef;
    }

    public void setChequeRef(String chequeRef) {
        this.chequeRef = chequeRef;
    }

    public String getChequedate() {
        return chequedate;
    }

    public void setChequedate(String chequedate) {
        this.chequedate = chequedate;
    }

    public String getChequenumber() {
        return chequenumber;
    }

    public void setChequenumber(String chequenumber) {
        this.chequenumber = chequenumber;
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

    public String getCurrenctcode() {
        return currenctcode;
    }

    public void setCurrenctcode(String currenctcode) {
        this.currenctcode = currenctcode;
    }

    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDoccurrenctcode() {
        return doccurrenctcode;
    }

    public void setDoccurrenctcode(String doccurrenctcode) {
        this.doccurrenctcode = doccurrenctcode;
    }

    public String getDocumentdate() {
        return documentdate;
    }

    public void setDocumentdate(String documentdate) {
        this.documentdate = documentdate;
    }

    public String getDocumentno() {
        return documentno;
    }

    public void setDocumentno(String documentno) {
        this.documentno = documentno;
    }

    public String getDocumenttype() {
        return documenttype;
    }

    public void setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getFinancialmonth() {
        return financialmonth;
    }

    public void setFinancialmonth(String financialmonth) {
        this.financialmonth = financialmonth;
    }

    public String getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(String financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getOrderpartner() {
        return orderpartner;
    }

    public void setOrderpartner(String orderpartner) {
        this.orderpartner = orderpartner;
    }

    public String getOrderpartnerdesc() {
        return orderpartnerdesc;
    }

    public void setOrderpartnerdesc(String orderpartnerdesc) {
        this.orderpartnerdesc = orderpartnerdesc;
    }

    public String getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(String postingdate) {
        this.postingdate = postingdate;
    }

    public String getProfitcentercode() {
        return profitcentercode;
    }

    public void setProfitcentercode(String profitcentercode) {
        this.profitcentercode = profitcentercode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTemplatename() {
        return templatename;
    }

    public void setTemplatename(String templatename) {
        this.templatename = templatename;
    }

    public String getHeaderTemplate() {
        return headerTemplate;
    }

    public void setHeaderTemplate(String headerTemplate) {
        this.headerTemplate = headerTemplate;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public Date getDateSeq() {
        return dateSeq;
    }

    public void setDateSeq(Date dateSeq) {
        this.dateSeq = dateSeq;
    }

    public List<FinBankPaymentCreditBean> getFinBankPaymentCreditBeans() {
        return finBankPaymentCreditBeans;
    }

    public void setFinBankPaymentCreditBeans(List<FinBankPaymentCreditBean> finBankPaymentCreditBeans) {
        this.finBankPaymentCreditBeans = finBankPaymentCreditBeans;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getBoeno() {
        return boeno;
    }

    public void setBoeno(String boeno) {
        this.boeno = boeno;
    }

    public String getBoedate() {
        return boedate;
    }

    public void setBoedate(String boedate) {
        this.boedate = boedate;
    }

    public String getBusinessunit() {
        return businessunit;
    }

    public void setBusinessunit(String businessunit) {
        this.businessunit = businessunit;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getReffindoccode() {
        return reffindoccode;
    }

    public void setReffindoccode(String reffindoccode) {
        this.reffindoccode = reffindoccode;
    }

    public BigDecimal getGrrClearingAmount() {
        return grrClearingAmount;
    }

    public void setGrrClearingAmount(BigDecimal grrClearingAmount) {
        this.grrClearingAmount = grrClearingAmount;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
