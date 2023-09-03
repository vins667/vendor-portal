package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.ViewDifindocumentpaymentadviceId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ViewDifindocumentpaymentadviceReport implements Serializable {
    private String companycode;
    private String businessunitcode;
    private Integer financialyearcode;
    private String code;
    private String postingdate;
    private String proposalrefno;
    private String glcode;
    private String gldescription;
    private String customersuppliercode;
    private String customersuppliername;
    private String gstinnumber;
    private String emailaddress;
    private BigDecimal amountincc;
    private String chequenumber;
    private String utrnumber;
    private String utrdate;
    private Boolean advicesent;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public Integer getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(Integer financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(String postingdate) {
        this.postingdate = postingdate;
    }

    public String getProposalrefno() {
        return proposalrefno;
    }

    public void setProposalrefno(String proposalrefno) {
        this.proposalrefno = proposalrefno;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getGldescription() {
        return gldescription;
    }

    public void setGldescription(String gldescription) {
        this.gldescription = gldescription;
    }

    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    public String getCustomersuppliername() {
        return customersuppliername;
    }

    public void setCustomersuppliername(String customersuppliername) {
        this.customersuppliername = customersuppliername;
    }

    public String getGstinnumber() {
        return gstinnumber;
    }

    public void setGstinnumber(String gstinnumber) {
        this.gstinnumber = gstinnumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public BigDecimal getAmountincc() {
        return amountincc;
    }

    public void setAmountincc(BigDecimal amountincc) {
        this.amountincc = amountincc;
    }

    public String getChequenumber() {
        return chequenumber;
    }

    public void setChequenumber(String chequenumber) {
        this.chequenumber = chequenumber;
    }

    public String getUtrnumber() {
        return utrnumber;
    }

    public void setUtrnumber(String utrnumber) {
        this.utrnumber = utrnumber;
    }

    public String getUtrdate() {
        return utrdate;
    }

    public void setUtrdate(String utrdate) {
        this.utrdate = utrdate;
    }

    public Boolean getAdvicesent() {
        return advicesent;
    }

    public void setAdvicesent(Boolean advicesent) {
        this.advicesent = advicesent;
    }
}
