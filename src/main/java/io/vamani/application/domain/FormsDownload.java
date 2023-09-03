package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A FormsDownload.
 */
@Entity
@Table(name = "forms_download")
public class FormsDownload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="formsDownloadSeq", sequenceName="forms_download_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="formsDownloadSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "form_name", length = 100, nullable = false)
    private String formName;

    @NotNull
    @Size(max = 200)
    @Column(name = "file_name", length = 200, nullable = false)
    private String fileName;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public FormsDownload formName(String formName) {
        this.formName = formName;
        return this;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFileName() {
        return fileName;
    }

    public FormsDownload fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public FormsDownload createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public FormsDownload createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
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
        FormsDownload formsDownload = (FormsDownload) o;
        if (formsDownload.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), formsDownload.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FormsDownload{" +
            "id=" + getId() +
            ", formName='" + getFormName() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
