package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A PollDetails.
 */
@Entity
@Table(name = "poll_details")
public class PollDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="pollDetailsSeq", sequenceName="poll_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="pollDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 500)
    @Column(name = "poll_option", length = 500, nullable = false)
    private String pollOption;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnore
    @JoinColumn(name = "poll_master_id")
    private PollMaster pollMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPollOption() {
        return pollOption;
    }

    public PollDetails pollOption(String pollOption) {
        this.pollOption = pollOption;
        return this;
    }

    public void setPollOption(String pollOption) {
        this.pollOption = pollOption;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PollDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public PollDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public PollMaster getPollMaster() {
        return pollMaster;
    }

    public PollDetails pollMaster(PollMaster pollMaster) {
        this.pollMaster = pollMaster;
        return this;
    }

    public void setPollMaster(PollMaster pollMaster) {
        this.pollMaster = pollMaster;
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
        PollDetails pollDetails = (PollDetails) o;
        if (pollDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pollDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PollDetails{" +
            "id=" + getId() +
            ", pollOption='" + getPollOption() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
