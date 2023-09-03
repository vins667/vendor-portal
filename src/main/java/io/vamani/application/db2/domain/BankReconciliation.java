package io.vamani.application.db2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "DI_BANKRECONCILATION")
public class BankReconciliation implements Serializable {

    @Id
    @SequenceGenerator(name = "bankReconciliationSeq", sequenceName = "di_bankreconcilation_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bankReconciliationSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "RECONCILIATIONDATE", nullable = true)
    private Instant reconciliationdate;

    @Column(name = "DOCUMENTDATE", nullable = true)
    private Instant documentdate;

    @Column(name = "DOCUMENTNO", nullable = true)
    private String documentno;

    @Column(name = "BANKCODE", nullable = true)
    private String bankcode;

    @Column(name = "BANKNAME", nullable = true)
    private String bankname;

    @Column(name = "BALANCE", nullable = true)
    private String balance;

    @Column(name = "ENTRYBY", nullable = true)
    private String entryby;

    @Column(name = "ENTRYDATE", nullable = true)
    private String entrydate;

    @Column(name = "LEDGERBALANCE", nullable = true)
    private BigDecimal ledgerbalance;

    @Column(name = "CHECKDEPOSITNOTCLEAR", nullable = true)
    private BigDecimal checkdepositnotclear;

    @Column(name = "checkissuenotclear", nullable = true)
    private BigDecimal checkissuenotclear;

    @Column(name = "BANKBALANCE", nullable = true)
    private BigDecimal bankbalance;

    @Column(name = "BALANCEDIFFERENCE", nullable = true)
    private BigDecimal balancedifference;

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

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public BigDecimal getLedgerbalance() {
        return ledgerbalance;
    }

    public void setLedgerbalance(BigDecimal ledgerbalance) {
        this.ledgerbalance = ledgerbalance;
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
