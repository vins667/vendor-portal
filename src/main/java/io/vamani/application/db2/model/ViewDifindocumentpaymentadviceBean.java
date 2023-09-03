package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.ViewDifindocumentpaymentadviceId;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ViewDifindocumentpaymentadviceBean implements Serializable {
    private ViewDifindocumentpaymentadviceId id;
    private Date postingdate;
    private String proposalrefno;
    private String glcode;
    private String gldescription;
    private String customersuppliercode;
    private String customersuppliername;
    private String gstinnumber;
    private BigDecimal amountincc;
    private String chequenumber;
    private String utrnumber;
    private Timestamp utrdate;
    private Boolean advicesent;
    private Boolean flag;

    public ViewDifindocumentpaymentadviceId getId() {
        return id;
    }

    public void setId(ViewDifindocumentpaymentadviceId id) {
        this.id = id;
    }

    public Date getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(Date postingdate) {
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

    public Timestamp getUtrdate() {
        return utrdate;
    }

    public void setUtrdate(Timestamp utrdate) {
        this.utrdate = utrdate;
    }

    public Boolean getAdvicesent() {
        return advicesent;
    }

    public void setAdvicesent(Boolean advicesent) {
        this.advicesent = advicesent;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDifindocumentpaymentadviceBean that = (ViewDifindocumentpaymentadviceBean) o;
        return Objects.equals(id, that.id) && Objects.equals(postingdate, that.postingdate) && Objects.equals(proposalrefno, that.proposalrefno) && Objects.equals(glcode, that.glcode) && Objects.equals(gldescription, that.gldescription) && Objects.equals(customersuppliercode, that.customersuppliercode) && Objects.equals(customersuppliername, that.customersuppliername) && Objects.equals(gstinnumber, that.gstinnumber) && Objects.equals(amountincc, that.amountincc) && Objects.equals(chequenumber, that.chequenumber) && Objects.equals(utrnumber, that.utrnumber) && Objects.equals(utrdate, that.utrdate) && Objects.equals(advicesent, that.advicesent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postingdate, proposalrefno, glcode, gldescription, customersuppliercode, customersuppliername, gstinnumber, amountincc, chequenumber, utrnumber, utrdate, advicesent);
    }
}
