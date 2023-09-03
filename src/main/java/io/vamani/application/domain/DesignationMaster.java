package io.vamani.application.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DesignationMaster.
 */
@Entity
@Table(name = "designation_master")
public class DesignationMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="designationMasterSeq", sequenceName="designation_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="designationMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "designation_code", length = 15, nullable = false)
    private String designationCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "designation_name", length = 60, nullable = false)
    private String designationName;

    @Size(max = 1)
    @Column(name = "flow_type", length = 1)
    private String flowType;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

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

    public String getDesignationCode() {
        return designationCode;
    }

    public DesignationMaster designationCode(String designationCode) {
        this.designationCode = designationCode;
        return this;
    }

    public void setDesignationCode(String designationCode) {
        this.designationCode = designationCode;
    }

    public String getDesignationName() {
        return designationName;
    }

    public DesignationMaster designationName(String designationName) {
        this.designationName = designationName;
        return this;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public String getFlowType() {
        return flowType;
    }

    public DesignationMaster flowType(String flowType) {
        this.flowType = flowType;
        return this;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getStatus() {
        return status;
    }

    public DesignationMaster status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DesignationMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public DesignationMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public DesignationMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public DesignationMaster lastUpdatedDate(Instant lastUpdatedDate) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DesignationMaster designationMaster = (DesignationMaster) o;
        if (designationMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), designationMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DesignationMaster{" +
            "id=" + getId() +
            ", designationCode='" + getDesignationCode() + "'" +
            ", designationName='" + getDesignationName() + "'" +
            ", flowType='" + getFlowType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
