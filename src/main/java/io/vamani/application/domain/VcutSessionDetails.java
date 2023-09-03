package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VcutSessionDetails.
 */
@Entity
@Table(name = "vcut_session_details")
public class VcutSessionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutSessionDetailsSeq", sequenceName="vcut_session_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutSessionDetailsSeq")
    private Long id;

    @Size(max = 10)
    @Column(name = "start_time", length = 10)
    private String startTime;

    @Size(max = 10)
    @Column(name = "end_time", length = 10)
    private String endTime;

    @Size(max = 20)
    @Column(name = "jhi_type", length = 20)
    private String type;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "jhi_order")
    private Integer order;

    @Column(name = "cumulative_mins")
    private Integer cumulativeMins;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "display_flag")
    private String displayFlag;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vcut_session_master_id")
    @NotNull
    @JsonIgnoreProperties("")
    private VcutSessionMaster vcutSessionMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public VcutSessionDetails startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public VcutSessionDetails endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public VcutSessionDetails type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public VcutSessionDetails duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getOrder() {
        return order;
    }

    public VcutSessionDetails order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCumulativeMins() {
        return cumulativeMins;
    }

    public VcutSessionDetails cumulativeMins(Integer cumulativeMins) {
        this.cumulativeMins = cumulativeMins;
        return this;
    }

    public void setCumulativeMins(Integer cumulativeMins) {
        this.cumulativeMins = cumulativeMins;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VcutSessionDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VcutSessionDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VcutSessionDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VcutSessionDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public VcutSessionMaster getVcutSessionMaster() {
        return vcutSessionMaster;
    }

    public VcutSessionDetails vcutSessionMaster(VcutSessionMaster vcutSessionMaster) {
        this.vcutSessionMaster = vcutSessionMaster;
        return this;
    }

    public void setVcutSessionMaster(VcutSessionMaster vcutSessionMaster) {
        this.vcutSessionMaster = vcutSessionMaster;
    }

    public String getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(String displayFlag) {
        this.displayFlag = displayFlag;
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
        VcutSessionDetails vcutSessionDetails = (VcutSessionDetails) o;
        if (vcutSessionDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vcutSessionDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VcutSessionDetails{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", type='" + getType() + "'" +
            ", duration=" + getDuration() +
            ", order=" + getOrder() +
            ", cumulativeMins=" + getCumulativeMins() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
