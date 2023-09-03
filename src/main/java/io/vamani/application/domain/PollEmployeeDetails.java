package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PollEmployeeDetails.
 */
@Entity
@Table(name = "poll_employee_details")
public class PollEmployeeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="pollEmployeeDetailsSeq", sequenceName="poll_employee_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="pollEmployeeDetailsSeq")
    private Long id;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "poll_master_id")
    private PollMaster pollMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "poll_details_id")
    private PollDetails pollDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PollEmployeeDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public PollEmployeeDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public PollMaster getPollMaster() {
        return pollMaster;
    }

    public PollEmployeeDetails pollMaster(PollMaster pollMaster) {
        this.pollMaster = pollMaster;
        return this;
    }

    public void setPollMaster(PollMaster pollMaster) {
        this.pollMaster = pollMaster;
    }

    public PollDetails getPollDetails() {
        return pollDetails;
    }

    public PollEmployeeDetails pollDetails(PollDetails pollDetails) {
        this.pollDetails = pollDetails;
        return this;
    }

    public void setPollDetails(PollDetails pollDetails) {
        this.pollDetails = pollDetails;
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
        PollEmployeeDetails pollEmployeeDetails = (PollEmployeeDetails) o;
        if (pollEmployeeDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pollEmployeeDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PollEmployeeDetails{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
