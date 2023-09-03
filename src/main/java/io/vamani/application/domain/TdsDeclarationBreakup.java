package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TdsDeclaration.
 */
@Entity
@Table(name = "tds_declaration_breakup")
public class TdsDeclarationBreakup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="tdsDeclarationBreakupSeq", sequenceName="tds_declaration_breakup_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tdsDeclarationBreakupSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "card_no", length = 50)
    private String cardNo;

    @Column(name = "amount")
    private Double  amount;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Column(name = "previous_emp_dtls_id")
    private Long previousEmpDtlsId;

    @ManyToOne
    @JoinColumn(name = "tds_group_details_id")
    @JsonIgnoreProperties("")
    private TdsGroupDetails tdsGroupDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getPreviousEmpDtlsId() {
        return previousEmpDtlsId;
    }

    public void setPreviousEmpDtlsId(Long previousEmpDtlsId) {
        this.previousEmpDtlsId = previousEmpDtlsId;
    }

    public TdsGroupDetails getTdsGroupDetails() {
        return tdsGroupDetails;
    }

    public void setTdsGroupDetails(TdsGroupDetails tdsGroupDetails) {
        this.tdsGroupDetails = tdsGroupDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TdsDeclarationBreakup that = (TdsDeclarationBreakup) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(cardNo, that.cardNo) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastUpdatedBy, that.lastUpdatedBy) &&
            Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
            Objects.equals(previousEmpDtlsId, that.previousEmpDtlsId) &&
            Objects.equals(tdsGroupDetails, that.tdsGroupDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNo, amount, createdBy, createdDate, lastUpdatedBy, lastUpdatedDate, previousEmpDtlsId, tdsGroupDetails);
    }

    @Override
    public String toString() {
        return "TdsDeclarationBreakup{" +
            "id=" + id +
            ", cardNo='" + cardNo + '\'' +
            ", amount=" + amount +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", previousEmpDtlsId=" + previousEmpDtlsId +
            ", tdsGroupDetails=" + tdsGroupDetails +
            '}';
    }
}
