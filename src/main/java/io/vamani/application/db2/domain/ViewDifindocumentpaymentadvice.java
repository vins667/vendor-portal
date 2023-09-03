package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "VIEW_DIFINDOCUMENTPAYMENTADVICE")
public class ViewDifindocumentpaymentadvice {

    @EmbeddedId
    private ViewDifindocumentpaymentadviceId id;
    @Basic
    @Column(name = "POSTINGDATE")
    private Date postingdate;
    @Basic
    @Column(name = "PROPOSALREFNO")
    private String proposalrefno;
    @Basic
    @Column(name = "GLCODE")
    private String glcode;
    @Basic
    @Column(name = "GLDESCRIPTION")
    private String gldescription;
    @Basic
    @Column(name = "CUSTOMERSUPPLIERCODE")
    private String customersuppliercode;
    @Basic
    @Column(name = "CUSTOMERSUPPLIERNAME")
    private String customersuppliername;
    @Basic
    @Column(name = "GSTINNUMBER")
    private String gstinnumber;
    @Basic
    @Column(name = "EMAILADDRESS")
    private String emailaddress;
    @Basic
    @Column(name = "AMOUNTINCC")
    private BigDecimal amountincc;
    @Basic
    @Column(name = "CHEQUENUMBER")
    private String chequenumber;
    @Basic
    @Column(name = "UTRNUMBER")
    private String utrnumber;
    @Basic
    @Column(name = "UTRDATE")
    private Timestamp utrdate;
    @Basic
    @Column(name = "ADVICESENT")
    private Boolean advicesent;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDifindocumentpaymentadvice that = (ViewDifindocumentpaymentadvice) o;
        return Objects.equals(id, that.id) && Objects.equals(postingdate, that.postingdate) && Objects.equals(proposalrefno, that.proposalrefno) && Objects.equals(glcode, that.glcode) && Objects.equals(gldescription, that.gldescription) && Objects.equals(customersuppliercode, that.customersuppliercode) && Objects.equals(customersuppliername, that.customersuppliername) && Objects.equals(gstinnumber, that.gstinnumber) && Objects.equals(amountincc, that.amountincc) && Objects.equals(chequenumber, that.chequenumber) && Objects.equals(utrnumber, that.utrnumber) && Objects.equals(utrdate, that.utrdate) && Objects.equals(advicesent, that.advicesent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postingdate, proposalrefno, glcode, gldescription, customersuppliercode, customersuppliername, gstinnumber, amountincc, chequenumber, utrnumber, utrdate, advicesent);
    }
}
