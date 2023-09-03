package io.vamani.application.db2.model;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class BankReconciliationBean {

    private Long id;
    private Instant reconciliationdate;
    private Instant documentdate;
    private String documentno;
    private String bankcode;
    private String bankname;
    private String balance;
    private BigDecimal checkdepositnotclear;
    private BigDecimal checkissuenotclear;
    private BigDecimal ledgerbalance;
    private BigDecimal bankbalance;
    private BigDecimal balancedifference;
    private String entryby;
    private Instant entrydate;
    private List<BankReconciliationdetailBean> bankReconciliationdetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDocumentno() {
        return documentno;
    }

    public void setDocumentno(String documentno) {
        this.documentno = documentno;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEntryby() {
        return entryby;
    }

    public void setEntryby(String entryby) {
        this.entryby = entryby;
    }

    public Instant getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Instant entrydate) {
        this.entrydate = entrydate;
    }

    public List<BankReconciliationdetailBean> getBankReconciliationdetails() {
        return bankReconciliationdetails;
    }

    public void setBankReconciliationdetails(List<BankReconciliationdetailBean> bankReconciliationdetails) {
        this.bankReconciliationdetails = bankReconciliationdetails;
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

    public BigDecimal getLedgerbalance() {
        return ledgerbalance;
    }

    public void setLedgerbalance(BigDecimal ledgerbalance) {
        this.ledgerbalance = ledgerbalance;
    }

    public BigDecimal getBankbalance() {
        return bankbalance;
    }

    public void setBankbalance(BigDecimal bankbalance) {
        this.bankbalance = bankbalance;
    }

    public BigDecimal getBalancedifference() {
        return balancedifference;
    }

    public void setBalancedifference(BigDecimal balancedifference) {
        this.balancedifference = balancedifference;
    }
}
