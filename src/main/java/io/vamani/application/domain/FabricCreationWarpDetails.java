package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FabricCreationWarpDetails.
 */
@Entity
@Table(name = "fabric_creation_warp_details")
public class FabricCreationWarpDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FabricCreationWarpDetailsId id;

    @Size(max = 10)
    @Column(name = "warp_1", length = 10)
    private String warp1;

    @Size(max = 10)
    @Column(name = "warp_2", length = 10)
    private String warp2;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "fabric_uom_master_id")
    private FabricUomMaster fabricUomMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public FabricCreationWarpDetailsId getId() {
        return id;
    }

    public void setId(FabricCreationWarpDetailsId id) {
        this.id = id;
    }

    public String getWarp1() {
        return warp1;
    }

    public void setWarp1(String warp1) {
        this.warp1 = warp1;
    }

    public String getWarp2() {
        return warp2;
    }

    public void setWarp2(String warp2) {
        this.warp2 = warp2;
    }

    public FabricUomMaster getFabricUomMaster() {
        return fabricUomMaster;
    }

    public void setFabricUomMaster(FabricUomMaster fabricUomMaster) {
        this.fabricUomMaster = fabricUomMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FabricCreationWarpDetails that = (FabricCreationWarpDetails) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(warp1, that.warp1) &&
            Objects.equals(warp2, that.warp2) &&
            Objects.equals(fabricUomMaster, that.fabricUomMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, warp1, warp2, fabricUomMaster);
    }

    @Override
    public String toString() {
        return "FabricCreationWarpDetails{" +
            "id=" + id +
            ", warp1='" + warp1 + '\'' +
            ", warp2='" + warp2 + '\'' +
            ", fabricUomMaster=" + fabricUomMaster +
            '}';
    }
}
