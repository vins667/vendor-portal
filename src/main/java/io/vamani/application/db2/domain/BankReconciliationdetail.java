package io.vamani.application.db2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "DI_BANKRECONCILATIONDETAILS")
public class BankReconciliationdetail implements Serializable {

    @Id
    @SequenceGenerator(name = "bankReconciliationdetailSeq", sequenceName = "di_bankreconcilationdetails_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bankReconciliationdetailSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMPANYCODE", nullable = true)
    private String companycode;

    @Column(name = "BUSINESSUNITCODE", nullable = true)
    private String businessunitcode;

    @Column(name = "FINDOCUMENTCODE", nullable = true)
    private String code;

    @Column(name = "GLCODE", nullable = true)
    private String glcode;

    @Column(name = "GLNAME", nullable = true)
    private String glname;

    @Column(name = "CHEQUENO", nullable = true)
    private String chequeno;

    @Column(name = "CHEQUEDATE", nullable = true)
    private String chequedate;

    @Column(name = "CHEQUECLEARINGDATE", nullable = true)
    private String chequeclearingdate;

    @Column(name = "CHEQUEAMOUNT", nullable = true)
    private BigDecimal chequeamount;

    @Column(name = "DEBITAMIT", nullable = true)
    private BigDecimal debitamit;

    @Column(name = "CREDITAMT", nullable = true)
    private BigDecimal creditamt;

    @Column(name = "DOCUMENTYYPE", nullable = true)
    private String documentyype;

    @Column(name = "PROFITCENTERCODE", nullable = true)
    private String profitcentercode;

    @Column(name = "NARRATION", nullable = true)
    private String narration;

    @Column(name = "RECONCILIATIONDATE", nullable = true)
    private String reconciliationdate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "BANKRECONCILATIONID", referencedColumnName = "ID")
    private BankReconciliation bankReconciliation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode != null ? companycode.trim() : "";
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode != null ? businessunitcode.trim() : "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code != null ? code.trim() : "";
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode != null ? glcode.trim() : "";
    }

    public String getGlname() {
        return glname;
    }

    public void setGlname(String glname) {
        this.glname = glname != null ? glname.trim() : "";
    }

    public String getChequeno() {
        return chequeno;
    }

    public void setChequeno(String chequeno) {
        this.chequeno = chequeno != null ? chequeno.trim() : "";
    }

    public String getChequedate() {
        return chequedate;
    }

    public void setChequedate(String chequedate) {
        this.chequedate = chequedate;
    }

    public String getChequeclearingdate() {
        return chequeclearingdate;
    }

    public void setChequeclearingdate(String chequeclearingdate) {
        this.chequeclearingdate = chequeclearingdate;
    }

    public BigDecimal getChequeamount() {
        return chequeamount;
    }

    public void setChequeamount(BigDecimal chequeamount) {
        this.chequeamount = chequeamount;
    }

    public BigDecimal getDebitamit() {
        return debitamit;
    }

    public void setDebitamit(BigDecimal debitamit) {
        this.debitamit = debitamit;
    }

    public BigDecimal getCreditamt() {
        return creditamt;
    }

    public void setCreditamt(BigDecimal creditamt) {
        this.creditamt = creditamt;
    }

    public String getDocumentyype() {
        return documentyype;
    }

    public void setDocumentyype(String documentyype) {
        this.documentyype = documentyype != null ? documentyype.trim() : "";
    }

    public String getProfitcentercode() {
        return profitcentercode;
    }

    public void setProfitcentercode(String profitcentercode) {
        this.profitcentercode = profitcentercode != null ? profitcentercode.trim() : "";
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration != null ? narration.trim() : narration;
    }

    public String getReconciliationdate() {
        return reconciliationdate;
    }

    public void setReconciliationdate(String reconciliationdate) {
        this.reconciliationdate = reconciliationdate;
    }

    public BankReconciliation getBankReconciliation() {
        return bankReconciliation;
    }

    public void setBankReconciliation(BankReconciliation bankReconciliation) {
        this.bankReconciliation = bankReconciliation;
    }
}
