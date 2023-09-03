package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CompOffMaster.
 */
@Entity
@Table(name = "comp_off_master")
public class CompOffMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="compOffMasterSeq", sequenceName="comp_off_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="compOffMasterSeq")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name="emp_code", referencedColumnName="login")
    private User userCode;

    @NotNull
    @Column(name = "comp_off_date", nullable = false)
    private Instant compOffDate;

    @Size(max = 5)
    @Column(name = "time_from", length = 5)
    private String timeFrom;

    @Size(max = 5)
    @Column(name = "time_to", length = 5)
    private String timeTo;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "avail_date")
    private Instant availDate;

    @Size(max = 50)
    @Column(name = "hod_approved_by", length = 50)
    private String hodApprovedBy;

    @Column(name = "hod_approved_date")
    private Instant hodApprovedDate;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserCode() {
        return userCode;
    }

    public void setUserCode(User userCode) {
        this.userCode = userCode;
    }

    public Instant getCompOffDate() {
        return compOffDate;
    }

    public CompOffMaster compOffDate(Instant compOffDate) {
        this.compOffDate = compOffDate;
        return this;
    }

    public void setCompOffDate(Instant compOffDate) {
        this.compOffDate = compOffDate;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public CompOffMaster timeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public CompOffMaster timeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Double getBalance() {
        return balance;
    }

    public CompOffMaster balance(Double balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Instant getAvailDate() {
        return availDate;
    }

    public CompOffMaster availDate(Instant availDate) {
        this.availDate = availDate;
        return this;
    }

    public void setAvailDate(Instant availDate) {
        this.availDate = availDate;
    }

    public String getHodApprovedBy() {
        return hodApprovedBy;
    }

    public CompOffMaster hodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
        return this;
    }

    public void setHodApprovedBy(String hodApprovedBy) {
        this.hodApprovedBy = hodApprovedBy;
    }

    public Instant getHodApprovedDate() {
        return hodApprovedDate;
    }

    public CompOffMaster hodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
        return this;
    }

    public void setHodApprovedDate(Instant hodApprovedDate) {
        this.hodApprovedDate = hodApprovedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CompOffMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CompOffMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks != null ? remarks.toUpperCase() : "";
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
        CompOffMaster compOffMaster = (CompOffMaster) o;
        if (compOffMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compOffMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompOffMaster{" +
            "id=" + getId() +
            ", empCode='" + getUserCode() + "'" +
            ", compOffDate='" + getCompOffDate() + "'" +
            ", timeFrom='" + getTimeFrom() + "'" +
            ", timeTo='" + getTimeTo() + "'" +
            ", balance=" + getBalance() +
            ", availDate='" + getAvailDate() + "'" +
            ", hodApprovedBy='" + getHodApprovedBy() + "'" +
            ", hodApprovedDate='" + getHodApprovedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
