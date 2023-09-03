package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A WorkerDocumentDetails.
 */
@Entity
@Table(name = "worker_document_details")
public class WorkerDocumentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerDocumentDetailsSeq", sequenceName="worker_document_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerDocumentDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 1)
    @Column(name = "document_type", length = 1, nullable = false)
    private String documentType;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @NotNull
    @Column(name = "attached", nullable = false)
    private Boolean attached;

    @Size(max = 100)
    @Column(name = "file_name", length = 100)
    private String fileName;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_document_master_id")
    @JsonIgnoreProperties("")
    private RecruitmentDocumentMaster recruitmentDocumentMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "worker_joining_id")
    @JsonIgnoreProperties("")
    private WorkerJoining workerJoining;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public WorkerDocumentDetails documentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getRemarks() {
        return remarks;
    }

    public WorkerDocumentDetails remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean isAttached() {
        return attached;
    }

    public WorkerDocumentDetails attached(Boolean attached) {
        this.attached = attached;
        return this;
    }

    public void setAttached(Boolean attached) {
        this.attached = attached;
    }

    public String getFileName() {
        return fileName;
    }

    public WorkerDocumentDetails fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerDocumentDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerDocumentDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public RecruitmentDocumentMaster getRecruitmentDocumentMaster() {
        return recruitmentDocumentMaster;
    }

    public WorkerDocumentDetails recruitmentDocumentMaster(RecruitmentDocumentMaster recruitmentDocumentMaster) {
        this.recruitmentDocumentMaster = recruitmentDocumentMaster;
        return this;
    }

    public void setRecruitmentDocumentMaster(RecruitmentDocumentMaster recruitmentDocumentMaster) {
        this.recruitmentDocumentMaster = recruitmentDocumentMaster;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerDocumentDetails workerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
        return this;
    }

    public void setWorkerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkerDocumentDetails workerDocumentDetails = (WorkerDocumentDetails) o;
        if (workerDocumentDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerDocumentDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerDocumentDetails{" +
            "id=" + getId() +
            ", documentType='" + getDocumentType() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", attached='" + isAttached() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
