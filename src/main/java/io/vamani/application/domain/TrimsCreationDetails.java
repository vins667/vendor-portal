package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TrimsCreationDetails.
 */
@Entity
@Table(name = "trims_creation_details")
public class TrimsCreationDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "trimsCreationDetailsSeq", sequenceName = "trims_creation_master_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "trimsCreationDetailsSeq")
    private Long id;

    @Size(max = 200)
    @Column(name = "details_value", length = 200)
    private String detailsValue;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "trims_template_details_id")
    private TrimsTemplateDetails trimsTemplateDetails;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "trims_creation_master_id")
    private TrimsCreationMaster trimsCreationMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetailsValue() {
        return detailsValue;
    }

    public TrimsCreationDetails detailsValue(String detailsValue) {
        this.detailsValue = detailsValue;
        return this;
    }

    public void setDetailsValue(String detailsValue) {
        this.detailsValue = detailsValue;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TrimsCreationDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TrimsCreationDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
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

    public TrimsTemplateDetails getTrimsTemplateDetails() {
        return trimsTemplateDetails;
    }

    public TrimsCreationDetails trimsTemplateDetails(TrimsTemplateDetails trimsTemplateDetails) {
        this.trimsTemplateDetails = trimsTemplateDetails;
        return this;
    }

    public void setTrimsTemplateDetails(TrimsTemplateDetails trimsTemplateDetails) {
        this.trimsTemplateDetails = trimsTemplateDetails;
    }

    public TrimsCreationMaster getTrimsCreationMaster() {
        return trimsCreationMaster;
    }

    public TrimsCreationDetails trimsCreationMaster(TrimsCreationMaster trimsCreationMaster) {
        this.trimsCreationMaster = trimsCreationMaster;
        return this;
    }

    public void setTrimsCreationMaster(TrimsCreationMaster trimsCreationMaster) {
        this.trimsCreationMaster = trimsCreationMaster;
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
        TrimsCreationDetails trimsCreationDetails = (TrimsCreationDetails) o;
        if (trimsCreationDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trimsCreationDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrimsCreationDetails{" +
            "id=" + getId() +
            ", detailsValue='" + getDetailsValue() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
