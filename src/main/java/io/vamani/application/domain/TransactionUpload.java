package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * A TransactionUpload.
 */
@Entity
@Table(name = "transaction_upload")
public class TransactionUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="transactionUploadSeq", sequenceName="transaction_upload_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="transactionUploadSeq")
    private Long id;

    @Column(name = "transaction_posted_date")
    private Instant transactionPostedDate;

    @Size(max = 30)
    @Column(name = "cheque_no",nullable = true)
    private String chequeNo;

    @Size(max = 200)
    @Column(name = "description", length = 200,nullable = true)
    private String description;

    @Size(max = 20)
    @Column(name = "mode", length = 20,nullable = true)
    private String mode;

    @Column(name = "transaction_amount", nullable = true)
    private BigDecimal transactionAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTransactionPostedDate() {
        return transactionPostedDate;
    }

    public TransactionUpload transactionPostedDate(Instant transactionPostedDate) {
        this.transactionPostedDate = transactionPostedDate;
        return this;
    }

    public void setTransactionPostedDate(Instant transactionPostedDate) {
        this.transactionPostedDate = transactionPostedDate;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public TransactionUpload chequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
        return this;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getDescription() {
        return description;
    }

    public TransactionUpload description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMode() {
        return mode;
    }

    public TransactionUpload mode(String mode) {
        this.mode = mode;
        return this;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionUpload transactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionUpload)) {
            return false;
        }
        return id != null && id.equals(((TransactionUpload) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransactionUpload{" +
            "id=" + getId() +
            ", transactionPostedDate='" + getTransactionPostedDate() + "'" +
            ", chequeNo='" + getChequeNo() + "'" +
            ", description='" + getDescription() + "'" +
            ", mode='" + getMode() + "'" +
            ", transactionAmount=" + getTransactionAmount() +
            "}";
    }
}
