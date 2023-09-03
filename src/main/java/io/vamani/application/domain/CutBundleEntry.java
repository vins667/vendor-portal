package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CutBundleEntry.
 */
@Entity
@Table(name = "cut_bundle_entry")
public class CutBundleEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="cutBundleEntrySeq", sequenceName="cut_bundle_entry_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="cutBundleEntrySeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "porduction_counter_code", length = 10, nullable = false)
    private String porductionCounterCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "production_code", length = 20, nullable = false)
    private String productionCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "plant_code", length = 20, nullable = false)
    private String plantCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "style", length = 20, nullable = false)
    private String style;

    @NotNull
    @Size(max = 20)
    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Size(max = 20)
    @Column(name = "destination", length = 20)
    private String destination;

    @Size(max = 100)
    @Column(name = "destination_desc", length = 100)
    private String destinationDesc;

    @NotNull
    @Size(max = 20)
    @Column(name = "size", length = 20, nullable = false)
    private String size;

    @Column(name = "bundle_size")
    private Double bundleSize;

    @Column(name = "bundle_pcs")
    private Double bundlePcs;

    @Column(name = "save_flag")
    private Boolean saveFlag;

    @Size(max = 20)
    @Column(name = "createdby", length = 20)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 20)
    @Column(name = "lastupdatedby", length = 20)
    private String lastupdatedby;

    @Column(name = "lastupdateddate")
    private Instant lastupdateddate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPorductionCounterCode() {
        return porductionCounterCode;
    }

    public CutBundleEntry porductionCounterCode(String porductionCounterCode) {
        this.porductionCounterCode = porductionCounterCode;
        return this;
    }

    public void setPorductionCounterCode(String porductionCounterCode) {
        this.porductionCounterCode = porductionCounterCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public CutBundleEntry productionCode(String productionCode) {
        this.productionCode = productionCode != null ? productionCode.trim() : productionCode;
        return this;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode != null ? productionCode.trim() : productionCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public CutBundleEntry plantCode(String plantCode) {
        this.plantCode = plantCode;
        return this;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getStyle() {
        return style;
    }

    public CutBundleEntry style(String style) {
        this.style = style;
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public CutBundleEntry color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public String getSize() {
        return size;
    }

    public CutBundleEntry size(String size) {
        this.size = size;
        return this;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getBundleSize() {
        return bundleSize;
    }

    public CutBundleEntry bundleSize(Double bundleSize) {
        this.bundleSize = bundleSize;
        return this;
    }

    public void setBundleSize(Double bundleSize) {
        this.bundleSize = bundleSize;
    }

    public Double getBundlePcs() {
        return bundlePcs;
    }

    public CutBundleEntry bundlePcs(Double bundlePcs) {
        this.bundlePcs = bundlePcs;
        return this;
    }

    public void setBundlePcs(Double bundlePcs) {
        this.bundlePcs = bundlePcs;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Boolean getSaveFlag() {
        return saveFlag;
    }

    public CutBundleEntry saveFlag(Boolean saveFlag) {
        this.saveFlag = saveFlag;
        return this;
    }

    public void setSaveFlag(Boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CutBundleEntry createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CutBundleEntry createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public CutBundleEntry lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public CutBundleEntry lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutBundleEntry)) {
            return false;
        }
        return id != null && id.equals(((CutBundleEntry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutBundleEntry{" +
            "id=" + getId() +
            ", porductionCounterCode='" + getPorductionCounterCode() + "'" +
            ", productionCode='" + getProductionCode() + "'" +
            ", plantCode='" + getPlantCode() + "'" +
            ", style='" + getStyle() + "'" +
            ", color='" + getColor() + "'" +
            ", size='" + getSize() + "'" +
            ", bundle_size=" + getBundleSize() +
            ", bundle_pcs=" + getBundlePcs() +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", lastupdatedby='" + getLastupdatedby() + "'" +
            ", lastupdateddate='" + getLastupdateddate() + "'" +
            "}";
    }
}
