package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A WorkerReferenceDetails.
 */
@Entity
@Table(name = "worker_reference_details")
public class WorkerReferenceDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerReferenceDetailsSeq", sequenceName="worker_reference_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerReferenceDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Size(max = 100)
    @Column(name = "jhi_organization", length = 100)
    private String organization;

    @Size(max = 100)
    @Column(name = "designation", length = 100)
    private String designation;

    @Size(max = 20)
    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Size(max = 1)
    @Column(name = "contacted", length = 1)
    private String contacted;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worker_joining_id")
    @NotNull
    @JsonIgnoreProperties("")
    private WorkerJoining workerJoining;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public WorkerReferenceDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public WorkerReferenceDetails organization(String organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDesignation() {
        return designation;
    }

    public WorkerReferenceDetails designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public WorkerReferenceDetails contactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContacted() {
        return contacted;
    }

    public WorkerReferenceDetails contacted(String contacted) {
        this.contacted = contacted;
        return this;
    }

    public void setContacted(String contacted) {
        this.contacted = contacted;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerReferenceDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerReferenceDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerReferenceDetails workerJoining(WorkerJoining workerJoining) {
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
        WorkerReferenceDetails workerReferenceDetails = (WorkerReferenceDetails) o;
        if (workerReferenceDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerReferenceDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerReferenceDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", organization='" + getOrganization() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", contactNumber='" + getContactNumber() + "'" +
            ", contacted='" + getContacted() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
