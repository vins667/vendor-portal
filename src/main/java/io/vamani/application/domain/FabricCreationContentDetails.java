package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FabricCreationContentDetails.
 */
@Entity
@Table(name = "fabric_creation_content_dtls")
public class FabricCreationContentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FabricCreationContentDetailsId id;

    @NotNull
    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "fabric_content_master_id")
    private FabricContentMaster fabricContentMaster;

    public FabricCreationContentDetailsId getId() {
        return id;
    }

    public void setId(FabricCreationContentDetailsId id) {
        this.id = id;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public FabricContentMaster getFabricContentMaster() {
        return fabricContentMaster;
    }

    public void setFabricContentMaster(FabricContentMaster fabricContentMaster) {
        this.fabricContentMaster = fabricContentMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FabricCreationContentDetails that = (FabricCreationContentDetails) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(percentage, that.percentage) &&
            Objects.equals(fabricContentMaster, that.fabricContentMaster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentage, fabricContentMaster);
    }

    @Override
    public String toString() {
        return "FabricCreationContentDetails{" +
            "id=" + id +
            ", percentage=" + percentage +
            ", fabricContentMaster=" + fabricContentMaster +
            '}';
    }
}
