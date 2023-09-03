package io.vamani.application.db2.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public class BankReconciliationdetailBean {

    private Long id;
    private String rowcount;
    private String companycode;
    private String businessunitcode;
    private String code;
    private String finyearcode;
    private Integer linenumber;
    private String glcode;
    private String glname;
    private String chequeno;
    private Instant chequedate;
    private String chequeclearingdate;
    private BigDecimal chequeamount;
    private BigDecimal debitamit;
    private BigDecimal creditamt;
    private BigDecimal checkdepositnotclear;
    private BigDecimal checkissuenotclear;
    private String documentype;
    private Instant documentdate;
    private String profitcentercode;
    private String narration;
    private Instant reconciliationdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRowcount() {
        return rowcount;
    }

    public void setRowcount(String rowcount) {
        this.rowcount = rowcount;
    }

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

    public String getFinyearcode() {
        return finyearcode;
    }

    public void setFinyearcode(String finyearcode) {
        this.finyearcode = finyearcode;
    }

    public Integer getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(Integer linenumber) {
        this.linenumber = linenumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getGlname() {
        return glname;
    }

    public void setGlname(String glname) {
        this.glname = glname;
    }

    public String getChequeno() {
        return chequeno;
    }

    public void setChequeno(String chequeno) {
        this.chequeno = chequeno;
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

    public String getDocumentype() {
        return documentype;
    }

    public void setDocumentype(String documentype) {
        this.documentype = documentype;
    }

    public String getProfitcentercode() {
        return profitcentercode;
    }

    public void setProfitcentercode(String profitcentercode) {
        this.profitcentercode = profitcentercode;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public BigDecimal getCheckdepositnotclear() {
        return checkdepositnotclear;
    }

    public void setCheckdepositnotclear(BigDecimal checkdepositnotclear) {
        this.checkdepositnotclear = checkdepositnotclear;
    }

    public BigDecimal getCheckissuenotclear() {
        return checkissuenotclear;
    }

    public void setCheckissuenotclear(BigDecimal checkissuenotclear) {
        this.checkissuenotclear = checkissuenotclear;
    }

    public Instant getChequedate() {
        return chequedate;
    }

    public void setChequedate(Instant chequedate) {
        this.chequedate = chequedate;
    }

    public String getChequeclearingdate() {
        return chequeclearingdate;
    }

    public void setChequeclearingdate(String chequeclearingdate) {
        this.chequeclearingdate = chequeclearingdate;
    }

    public Instant getReconciliationdate() {
        return reconciliationdate;
    }

    public void setReconciliationdate(Instant reconciliationdate) {
        this.reconciliationdate = reconciliationdate;
    }

    public Instant getDocumentdate() {
        return documentdate;
    }

    public void setDocumentdate(Instant documentdate) {
        this.documentdate = documentdate;
    }
}
