package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TdsGroupDetails.
 */
@Entity
@Table(name = "tds_group_details")
public class TdsGroupDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="tdsGroupDetailsSeq", sequenceName="tds_group_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tdsGroupDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "perk_code", length = 15, nullable = false)
    private String perkCode;

    @NotNull
    @Size(max = 500)
    @Column(name = "perk_description", length = 500, nullable = false)
    private String perkDescription;

    @NotNull
    @Size(max = 100)
    @Column(name = "perk_limit", length = 100, nullable = false)
    private String perkLimit;

    @Column(name = "perk_type")
    private String perkType;

    @Column(name = "perk_mode")
    private String perkMode;

    @Column(name = "cal_type")
    private String calType;

    @Column(name = "for_comp")
    private String forComp;

    @Column(name = "print_order")
    private Integer printOrder;

    @Size(max = 200)
    @Column(name = "remarks", length = 200)
    private String remarks;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "display_flag")
    private String displayFlag;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "tds_group_master_id")
    @JsonIgnoreProperties("")
    private TdsGroupMaster tdsGroupMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerkCode() {
        return perkCode;
    }

    public TdsGroupDetails perkCode(String perkCode) {
        this.perkCode = perkCode;
        return this;
    }

    public void setPerkCode(String perkCode) {
        this.perkCode = perkCode;
    }

    public String getPerkDescription() {
        return perkDescription;
    }

    public TdsGroupDetails perkDescription(String perkDescription) {
        this.perkDescription = perkDescription;
        return this;
    }

    public void setPerkDescription(String perkDescription) {
        this.perkDescription = perkDescription;
    }

    public String getPerkLimit() {
        return perkLimit;
    }

    public TdsGroupDetails perkLimit(String perkLimit) {
        this.perkLimit = perkLimit;
        return this;
    }

    public void setPerkLimit(String perkLimit) {
        this.perkLimit = perkLimit;
    }

    public String getPerkType() {
        return perkType;
    }

    public TdsGroupDetails perkType(String perkType) {
        this.perkType = perkType;
        return this;
    }

    public void setPerkType(String perkType) {
        this.perkType = perkType;
    }

    public String getPerkMode() {
        return perkMode;
    }

    public TdsGroupDetails perkMode(String perkMode) {
        this.perkMode = perkMode;
        return this;
    }

    public void setPerkMode(String perkMode) {
        this.perkMode = perkMode;
    }

    public String getCalType() {
        return calType;
    }

    public TdsGroupDetails calType(String calType) {
        this.calType = calType;
        return this;
    }

    public void setCalType(String calType) {
        this.calType = calType;
    }

    public String getForComp() {
        return forComp;
    }

    public TdsGroupDetails forComp(String forComp) {
        this.forComp = forComp;
        return this;
    }

    public void setForComp(String forComp) {
        this.forComp = forComp;
    }

    public Integer getPrintOrder() {
        return printOrder;
    }

    public TdsGroupDetails printOrder(Integer printOrder) {
        this.printOrder = printOrder;
        return this;
    }

    public void setPrintOrder(Integer printOrder) {
        this.printOrder = printOrder;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TdsGroupDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TdsGroupDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TdsGroupDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TdsGroupDetails createdDate(Instant createdDate) {
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
        this.remarks = remarks;
    }

    public TdsGroupMaster getTdsGroupMaster() {
        return tdsGroupMaster;
    }

    public TdsGroupDetails tdsGroupMaster(TdsGroupMaster tdsGroupMaster) {
        this.tdsGroupMaster = tdsGroupMaster;
        return this;
    }

    public void setTdsGroupMaster(TdsGroupMaster tdsGroupMaster) {
        this.tdsGroupMaster = tdsGroupMaster;
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
        TdsGroupDetails tdsGroupDetails = (TdsGroupDetails) o;
        if (tdsGroupDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tdsGroupDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TdsGroupDetails{" +
            "id=" + getId() +
            ", perkCode='" + getPerkCode() + "'" +
            ", perkDescription='" + getPerkDescription() + "'" +
            ", perkLimit='" + getPerkLimit() + "'" +
            ", perkType='" + getPerkType() + "'" +
            ", perkMode='" + getPerkMode() + "'" +
            ", calType='" + getCalType() + "'" +
            ", forComp='" + getForComp() + "'" +
            ", printOrder=" + getPrintOrder() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
