package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TdsDeclarationUpload.
 */
@Entity
@Table(name = "tds_declaration_upload")
public class TdsDeclarationUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="tdsDeclarationUploadSeq", sequenceName="tds_declaration_upload_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tdsDeclarationUploadSeq")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "card_no", length = 50, nullable = false)
    private String cardNo;

    @NotNull
    @Size(max = 6)
    @Column(name = "financial_year", length = 6, nullable = false)
    private String financialYear;

    @Size(max = 100)
    @Column(name = "file_name")
    private String fileName;

    @Size(max = 100)
    @Column(name = "original_file_name", length = 100)
    private String originalFileName;

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

    @Column(name = "approval_flag", length = 1)
    private String approvalFlag;

    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    @Column(name = "approved_date", length = 50)
    private Instant approvedDate;

    @ManyToOne
	@JoinColumn(name = "tds_group_master_id")
    @JsonIgnoreProperties("")
    private TdsGroupMaster tdsGroupMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public TdsDeclarationUpload cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public TdsDeclarationUpload financialYear(String financialYear) {
        this.financialYear = financialYear;
        return this;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getFileName() {
        return fileName;
    }

    public TdsDeclarationUpload fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public TdsDeclarationUpload originalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
        return this;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TdsDeclarationUpload createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TdsDeclarationUpload createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TdsDeclarationUpload lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TdsDeclarationUpload lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public TdsGroupMaster getTdsGroupMaster() {
        return tdsGroupMaster;
    }

    public TdsDeclarationUpload tdsGroupMaster(TdsGroupMaster tdsGroupMaster) {
        this.tdsGroupMaster = tdsGroupMaster;
        return this;
    }

    public void setTdsGroupMaster(TdsGroupMaster tdsGroupMaster) {
        this.tdsGroupMaster = tdsGroupMaster;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TdsDeclarationUpload)) {
            return false;
        }
        return id != null && id.equals(((TdsDeclarationUpload) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TdsDeclarationUpload{" +
            "id=" + getId() +
            ", cardNo='" + getCardNo() + "'" +
            ", financialYear='" + getFinancialYear() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", originalFileName='" + getOriginalFileName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
