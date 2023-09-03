package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PollMaster.
 */
@Entity
@Table(name = "poll_master")
public class PollMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="pollMasterSeq", sequenceName="poll_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="pollMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 2000)
    @Column(name = "poll_text", length = 2000, nullable = false)
    private String pollText;

    @Column(name = "end_date")
    private Instant endDate;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;

    @NotNull
    @Column(name = "mail_flag", nullable = false)
    private Boolean mailFlag;

    @NotNull
    @Column(name = "notification_flag", nullable = false)
    private Boolean notificationFlag;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    @Column(name = "approved_date")
    private Instant approvedDate;

    @OneToMany(mappedBy = "pollMaster")
    private Set<PollDetails> pollDetails = new HashSet<>();
    @ManyToMany
    @NotNull
    @JoinTable(name = "poll_master_factory_master",
               joinColumns = @JoinColumn(name = "poll_masters_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "factory_masters_id", referencedColumnName = "id"))
    private Set<FactoryMaster> factoryMasters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPollText() {
        return pollText;
    }

    public PollMaster pollText(String pollText) {
        this.pollText = pollText;
        return this;
    }

    public void setPollText(String pollText) {
        this.pollText = pollText;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public PollMaster endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getFlag() {
        return flag;
    }

    public PollMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean isMailFlag() {
        return mailFlag;
    }

    public PollMaster mailFlag(Boolean mailFlag) {
        this.mailFlag = mailFlag;
        return this;
    }

    public void setMailFlag(Boolean mailFlag) {
        this.mailFlag = mailFlag;
    }

    public Boolean isNotificationFlag() {
        return notificationFlag;
    }

    public PollMaster notificationFlag(Boolean notificationFlag) {
        this.notificationFlag = notificationFlag;
        return this;
    }

    public void setNotificationFlag(Boolean notificationFlag) {
        this.notificationFlag = notificationFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PollMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public PollMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public PollMaster approvedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public PollMaster approvedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Set<PollDetails> getPollDetails() {
        return pollDetails;
    }

    public PollMaster pollDetails(Set<PollDetails> pollDetails) {
        this.pollDetails = pollDetails;
        return this;
    }

    public PollMaster addPollDetails(PollDetails pollDetails) {
        this.pollDetails.add(pollDetails);
        pollDetails.setPollMaster(this);
        return this;
    }

    public PollMaster removePollDetails(PollDetails pollDetails) {
        this.pollDetails.remove(pollDetails);
        pollDetails.setPollMaster(null);
        return this;
    }

    public void setPollDetails(Set<PollDetails> pollDetails) {
        this.pollDetails = pollDetails;
    }

    public Set<FactoryMaster> getFactoryMasters() {
        return factoryMasters;
    }

    public PollMaster factoryMasters(Set<FactoryMaster> factoryMasters) {
        this.factoryMasters = factoryMasters;
        return this;
    }

    public void setFactoryMasters(Set<FactoryMaster> factoryMasters) {
        this.factoryMasters = factoryMasters;
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
        PollMaster pollMaster = (PollMaster) o;
        if (pollMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pollMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PollMaster{" +
            "id=" + getId() +
            ", pollText='" + getPollText() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", flag='" + getFlag() + "'" +
            ", mailFlag='" + isMailFlag() + "'" +
            ", notificationFlag='" + isNotificationFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", approvedBy='" + getApprovedBy() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            "}";
    }
}
