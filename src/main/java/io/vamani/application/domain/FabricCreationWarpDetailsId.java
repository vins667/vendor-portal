package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FabricCreationWarpDetails.
 */
public class FabricCreationWarpDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JsonIgnore
    @JoinColumn(name = "fabric_creation_master_id")
    private FabricCreationMaster fabricCreationMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FabricCreationMaster getFabricCreationMaster() {
        return fabricCreationMaster;
    }

    public void setFabricCreationMaster(FabricCreationMaster fabricCreationMaster) {
        this.fabricCreationMaster = fabricCreationMaster;
    }

    public FabricCreationWarpDetailsId() {
    }

    public FabricCreationWarpDetailsId(Long id, @NotNull FabricCreationMaster fabricCreationMaster) {
        this.id = id;
        this.fabricCreationMaster = fabricCreationMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FabricCreationWarpDetailsId that = (FabricCreationWarpDetailsId) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(fabricCreationMaster, that.fabricCreationMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fabricCreationMaster);
    }

    @Override
    public String toString() {
        return "FabricCreationWarpDetailsId{" +
            "id=" + id +
            ", fabricCreationMaster=" + fabricCreationMaster +
            '}';
    }
}
