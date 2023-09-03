package io.vamani.application.domain;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A CostingGroupDetails.
 */
@Entity
@Table(name = "costing_group_details")
public class CostingGroupDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="costingGroupDetailsSeq", sequenceName="costing_group_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="costingGroupDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @NotNull
    @Size(max = 4)
    @Column(name = "value_type", length = 4, nullable = false)
    private String valueType;

    @NotNull
    @Column(name = "master_type", nullable = false)
    private String masterType;

    @NotNull
    @Column(name = "ugc_type", nullable = false)
    private String ugcType;

    @Size(max = 60)
    @Column(name = "created_by", length = 60)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 60)
    @Column(name = "last_updated_by", length = 60)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name="costing_group_master_id")
    private CostingGroupMaster costingGroupMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CostingGroupDetails code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public CostingGroupDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValueType() {
        return valueType;
    }

    public CostingGroupDetails valueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getMasterType() {
        return masterType;
    }

    public CostingGroupDetails masterType(String masterType) {
        this.masterType = masterType;
        return this;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }

    public String getUgcType() {
        return ugcType;
    }

    public CostingGroupDetails ugcType(String ugcType) {
        this.ugcType = ugcType;
        return this;
    }

    public void setUgcType(String ugcType) {
        this.ugcType = ugcType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CostingGroupDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CostingGroupDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CostingGroupDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CostingGroupDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public CostingGroupMaster getCostingGroupMaster() {
        return costingGroupMaster;
    }

    public CostingGroupDetails costingGroupMaster(CostingGroupMaster costingGroupMaster) {
        this.costingGroupMaster = costingGroupMaster;
        return this;
    }

    public void setCostingGroupMaster(CostingGroupMaster costingGroupMaster) {
        this.costingGroupMaster = costingGroupMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostingGroupDetails)) {
            return false;
        }
        return id != null && id.equals(((CostingGroupDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CostingGroupDetails{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", valueType='" + getValueType() + "'" +
            ", masterType='" + getMasterType() + "'" +
            ", ugcType='" + getUgcType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
