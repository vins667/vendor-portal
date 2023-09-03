package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FabricCreationContentDetails.
 */
public class FabricCreationContentDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JsonIgnore
    @JoinColumn(name = "fabric_creation_master_id")
    private FabricCreationMaster fabricCreationMaster;

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

    public FabricCreationContentDetailsId() {
    }

    public FabricCreationContentDetailsId(Long id, @NotNull FabricCreationMaster fabricCreationMaster) {
        this.id = id;
        this.fabricCreationMaster = fabricCreationMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FabricCreationContentDetailsId that = (FabricCreationContentDetailsId) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(fabricCreationMaster, that.fabricCreationMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fabricCreationMaster);
    }

    @Override
    public String toString() {
        return "FabricCreationContentDetailsId{" +
            "id=" + id +
            ", fabricCreationMaster=" + fabricCreationMaster +
            '}';
    }
}
