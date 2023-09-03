package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TdsGroupMaster.
 */
@Entity
@Table(name = "tds_group_master")
public class TdsGroupMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="tdsGroupMasterSeq", sequenceName="tds_group_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tdsGroupMasterSeq")
    private Long id;

    @NotNull
    @Column(name = "jhi_year", nullable = false)
    private Integer year;

    @NotNull
    @Size(max = 15)
    @Column(name = "group_code", length = 15, nullable = false)
    private String groupCode;

    @NotNull
    @Size(max = 500)
    @Column(name = "group_description", length = 500, nullable = false)
    private String groupDescription;

    @Column(name = "group_limit")
    private Double groupLimit;

    @Column(name = "group_order")
    private Integer groupOrder;

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

    public Integer getYear() {
        return year;
    }

    public TdsGroupMaster year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public TdsGroupMaster groupCode(String groupCode) {
        this.groupCode = groupCode;
        return this;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public TdsGroupMaster groupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
        return this;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Double getGroupLimit() {
        return groupLimit;
    }

    public TdsGroupMaster groupLimit(Double groupLimit) {
        this.groupLimit = groupLimit;
        return this;
    }

    public void setGroupLimit(Double groupLimit) {
        this.groupLimit = groupLimit;
    }

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public TdsGroupMaster groupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
        return this;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TdsGroupMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TdsGroupMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TdsGroupMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TdsGroupMaster lastUpdatedDate(Instant lastUpdatedDate) {
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
        TdsGroupMaster tdsGroupMaster = (TdsGroupMaster) o;
        if (tdsGroupMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tdsGroupMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TdsGroupMaster{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", groupCode='" + getGroupCode() + "'" +
            ", groupDescription='" + getGroupDescription() + "'" +
            ", groupLimit=" + getGroupLimit() +
            ", groupOrder=" + getGroupOrder() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
