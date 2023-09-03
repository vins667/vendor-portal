package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FabricCreationWeftDetails.
 */
@Entity
@Table(name = "fabric_creation_weft_details")
public class FabricCreationWeftDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FabricCreationWeftDetailsId id;

    @Size(max = 10)
    @Column(name = "weft_1", length = 10)
    private String weft1;

    @Size(max = 10)
    @Column(name = "weft_2", length = 10)
    private String weft2;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "fabric_uom_master_id")
    private FabricUomMaster fabricUomMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public FabricCreationWeftDetailsId getId() {
        return id;
    }

    public void setId(FabricCreationWeftDetailsId id) {
        this.id = id;
    }

    public String getWeft1() {
        return weft1;
    }

    public void setWeft1(String weft1) {
        this.weft1 = weft1;
    }

    public String getWeft2() {
        return weft2;
    }

    public void setWeft2(String weft2) {
        this.weft2 = weft2;
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
        FabricCreationWeftDetails that = (FabricCreationWeftDetails) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(weft1, that.weft1) &&
            Objects.equals(weft2, that.weft2) &&
            Objects.equals(fabricUomMaster, that.fabricUomMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weft1, weft2, fabricUomMaster);
    }

    @Override
    public String toString() {
        return "FabricCreationWeftDetails{" +
            "id=" + id +
            ", weft1='" + weft1 + '\'' +
            ", weft2='" + weft2 + '\'' +
            ", fabricUomMaster=" + fabricUomMaster +
            '}';
    }
}
