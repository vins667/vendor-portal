package io.vamani.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.domain.RecruitmentDocumentMaster;
import io.vamani.application.domain.WorkerJoining;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * A WorkerDocumentDetailsBean.
 */
public class WorkerDocumentDetailsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String documentType;

    private String remarks;
    private Boolean attached;

    private String fileName;

    private String createdBy;

    private Instant createdDate;

    private RecruitmentDocumentMaster recruitmentDocumentMaster;

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

    public WorkerDocumentDetailsBean documentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getRemarks() {
        return remarks;
    }

    public WorkerDocumentDetailsBean remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean isAttached() {
        return attached;
    }

    public WorkerDocumentDetailsBean attached(Boolean attached) {
        this.attached = attached;
        return this;
    }

    public void setAttached(Boolean attached) {
        this.attached = attached;
    }

    public String getFileName() {
        return fileName;
    }

    public WorkerDocumentDetailsBean fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerDocumentDetailsBean createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerDocumentDetailsBean createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public RecruitmentDocumentMaster getRecruitmentDocumentMaster() {
        return recruitmentDocumentMaster;
    }

    public WorkerDocumentDetailsBean recruitmentDocumentMaster(RecruitmentDocumentMaster recruitmentDocumentMaster) {
        this.recruitmentDocumentMaster = recruitmentDocumentMaster;
        return this;
    }

    public void setRecruitmentDocumentMaster(RecruitmentDocumentMaster recruitmentDocumentMaster) {
        this.recruitmentDocumentMaster = recruitmentDocumentMaster;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerDocumentDetailsBean workerJoining(WorkerJoining workerJoining) {
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
        WorkerDocumentDetailsBean workerDocumentDetails = (WorkerDocumentDetailsBean) o;
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
