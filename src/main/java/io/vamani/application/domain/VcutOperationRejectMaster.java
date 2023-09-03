package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A VcutOperationRejectMaster.
 */
@Entity
@Table(name = "vcut_operation_reject_master")
public class VcutOperationRejectMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutOperationRejectMasterSeq", sequenceName="vcut_operation_reject_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutOperationRejectMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Size(max = 200)
    @Column(name = "description_local", length = 200)
    private String descriptionLocal;

    @Size(max = 2)
    @Column(name = "type", length = 2)
    private String type;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public VcutOperationRejectMaster description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLocal() {
        return descriptionLocal;
    }

    public VcutOperationRejectMaster descriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal;
        return this;
    }

    public void setDescriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal;
    }

    public String getType() {
        return type;
    }

    public VcutOperationRejectMaster type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VcutOperationRejectMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VcutOperationRejectMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VcutOperationRejectMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VcutOperationRejectMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutOperationRejectMaster)) {
            return false;
        }
        return id != null && id.equals(((VcutOperationRejectMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutOperationRejectMaster{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", descriptionLocal='" + getDescriptionLocal() + "'" +
            ", type='" + getType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
