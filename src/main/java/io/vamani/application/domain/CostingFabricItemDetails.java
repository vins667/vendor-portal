package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A CostingFabricItemDetails.
 */
@Entity
@Table(name = "costing_fabric_item_details")
public class CostingFabricItemDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="costingFabricItemDetailsSeq", sequenceName="costing_fabric_item_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="costingFabricItemDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "item_type", length = 10, nullable = false)
    private String itemType;

    @NotNull
    @Size(max = 10)
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "descrption", length = 100, nullable = false)
    private String descrption;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public CostingFabricItemDetails itemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getCode() {
        return code;
    }

    public CostingFabricItemDetails code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescrption() {
        return descrption;
    }

    public CostingFabricItemDetails descrption(String descrption) {
        this.descrption = descrption;
        return this;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CostingFabricItemDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public CostingFabricItemDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CostingFabricItemDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CostingFabricItemDetails lastUpdatedDate(Instant lastUpdatedDate) {
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
        if (!(o instanceof CostingFabricItemDetails)) {
            return false;
        }
        return id != null && id.equals(((CostingFabricItemDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CostingFabricItemDetails{" +
            "id=" + getId() +
            ", itemType='" + getItemType() + "'" +
            ", code='" + getCode() + "'" +
            ", descrption='" + getDescrption() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
