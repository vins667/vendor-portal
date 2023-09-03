package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CostingEfficiencyMaste.
 */
@Entity
@Table(name = "costing_efficiency_master")
public class CostingEfficiencyMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="costingEfficiencyMasterSeq", sequenceName="costing_efficiency_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="costingEfficiencyMasterSeq")
    private Long id;

    @NotNull
    @Column(name = "from_quantity", nullable = false)
    private Double fromQuantity;

    @NotNull
    @Column(name = "to_quantity", nullable = false)
    private Double toQuantity;

    @NotNull
    @Column(name = "efficiency_perc", nullable = false)
    private String efficiencyPerc;

    @Size(max = 60)
    @Column(name = "createdby", length = 60)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 60)
    @Column(name = "updatedby", length = 60)
    private String updatedby;

    @Column(name = "updateddate")
    private Instant updateddate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFromQuantity() {
        return fromQuantity;
    }

    public CostingEfficiencyMaster fromQuantity(Double fromQuantity) {
        this.fromQuantity = fromQuantity;
        return this;
    }

    public void setFromQuantity(Double fromQuantity) {
        this.fromQuantity = fromQuantity;
    }

    public Double getToQuantity() {
        return toQuantity;
    }

    public CostingEfficiencyMaster toQuantity(Double toQuantity) {
        this.toQuantity = toQuantity;
        return this;
    }

    public void setToQuantity(Double toQuantity) {
        this.toQuantity = toQuantity;
    }

    public String getEfficiencyPerc() {
        return efficiencyPerc;
    }

    public CostingEfficiencyMaster efficiencyPerc(String efficiencyPerc) {
        this.efficiencyPerc = efficiencyPerc;
        return this;
    }

    public void setEfficiencyPerc(String efficiencyPerc) {
        this.efficiencyPerc = efficiencyPerc;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CostingEfficiencyMaster createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CostingEfficiencyMaster createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public CostingEfficiencyMaster updatedby(String updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public CostingEfficiencyMaster updateddate(Instant updateddate) {
        this.updateddate = updateddate;
        return this;
    }

    public void setUpdateddate(Instant updateddate) {
        this.updateddate = updateddate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostingEfficiencyMaster)) {
            return false;
        }
        return id != null && id.equals(((CostingEfficiencyMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CostingEfficiencyMaste{" +
            "id=" + getId() +
            ", fromQuantity=" + getFromQuantity() +
            ", toQuantity=" + getToQuantity() +
            ", efficiencyPerc='" + getEfficiencyPerc() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            ", updateddate='" + getUpdateddate() + "'" +
            "}";
    }
}
