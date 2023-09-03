package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A VcutOperationMaster.
 */
@Entity
@Table(name = "vcut_operation_master")
public class VcutOperationMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutOperationMasterSeq", sequenceName="vcut_operation_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutOperationMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Size(max = 200)
    @Column(name = "description_local", length = 200)
    private String descriptionLocal;

    @Size(max = 50)
    @Column(name = "style", length = 50)
    private String style;

    @Size(max = 50)
    @Column(name = "item_name", length = 50)
    private String itemName;

    @Size(max = 50)
    @Column(name = "machine", length = 50)
    private String machine;

    @Column(name = "smv")
    private Double smv;

    @Column(name = "inspection")
    private Boolean inspection;

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

    public String getDescription() {
        return description;
    }

    public VcutOperationMaster description(String description) {
        this.description = description != null ? description.toUpperCase() : "";
        return this;
    }

    public void setDescription(String description) {
        this.description = description != null ? description.toUpperCase() : "";
    }

    public String getDescriptionLocal() {
        return descriptionLocal;
    }

    public VcutOperationMaster descriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal != null ? descriptionLocal.toUpperCase() : "";
        return this;
    }

    public void setDescriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal != null ? descriptionLocal.toUpperCase() : "";
    }

    public String getStyle() {
        return style;
    }

    public VcutOperationMaster style(String style) {
        this.style = style != null ? style.toUpperCase() : "";
        return this;
    }

    public void setStyle(String style) {
        this.style = style != null ? style.toUpperCase() : "";
    }

    public String getItemName() {
        return itemName;
    }

    public VcutOperationMaster itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMachine() {
        return machine;
    }

    public VcutOperationMaster machine(String machine) {
        this.machine = machine;
        return this;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public Double getSmv() {
        return smv;
    }

    public VcutOperationMaster smv(Double smv) {
        this.smv = smv;
        return this;
    }

    public void setSmv(Double smv) {
        this.smv = smv;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VcutOperationMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VcutOperationMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VcutOperationMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VcutOperationMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public VcutOperationMaster() {
    }

    public VcutOperationMaster(Long id) {
        this.id = id;
    }

    public Boolean getInspection() {
        return inspection != null ? inspection.booleanValue() : false;
    }

    public void setInspection(Boolean inspection) {
        this.inspection = inspection != null ? inspection.booleanValue() : false;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutOperationMaster)) {
            return false;
        }
        return id != null && id.equals(((VcutOperationMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutOperationMaster{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", descriptionLocal='" + getDescriptionLocal() + "'" +
            ", style='" + getStyle() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", machine='" + getMachine() + "'" +
            ", smv=" + getSmv() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
