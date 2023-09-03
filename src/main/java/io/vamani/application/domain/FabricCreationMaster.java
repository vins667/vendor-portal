package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FabricCreationMaster.
 */
@Entity
@Table(name = "fabric_creation_master")
public class FabricCreationMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="fabricCreationMasterSeq", sequenceName="fabric_creation_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="fabricCreationMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "code", length = 15, nullable = false)
    private String code;

    @NotNull
    @Size(max = 1000)
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "epi")
    private Double epi;

    @Column(name = "ppi")
    private Double ppi;

    @Size(max = 50)
    @Column(name = "oth", length = 50)
    private String oth;

    @Column(name = "status")
    private String status;

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

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("")
    @JoinColumn(name = "fabric_substract_master_id")
    private FabricSubstractMaster fabricSubstractMaster;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("")
    @JoinColumn(name = "fabric_substract_details_id")
    private FabricSubstractDetails fabricSubstractDetails;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("")
    @JoinColumn(name = "fabric_spl_finish_master_id")
    private FabricSplFinishMaster fabricSplFinishMaster;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("")
    @JoinColumn(name = "fabric_others_master_id")
    private FabricOthersMaster fabricOthersMaster;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fabric_creation_master_id", referencedColumnName = "id")
    private Set<FabricCreationWeftDetails> fabricCreationWeftDetails = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fabric_creation_master_id", referencedColumnName = "id")
    private Set<FabricCreationWarpDetails> fabricCreationWarpDetails = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fabric_creation_master_id", referencedColumnName = "id")
    private Set<FabricCreationContentDetails> fabricCreationContentDetails = new HashSet<>();
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

    public FabricCreationMaster code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public FabricCreationMaster description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getEpi() {
        return epi;
    }

    public FabricCreationMaster epi(Double epi) {
        this.epi = epi;
        return this;
    }

    public void setEpi(Double epi) {
        this.epi = epi;
    }

    public Double getPpi() {
        return ppi;
    }

    public FabricCreationMaster ppi(Double ppi) {
        this.ppi = ppi;
        return this;
    }

    public void setPpi(Double ppi) {
        this.ppi = ppi;
    }

    public String getOth() {
        return oth;
    }

    public FabricCreationMaster oth(String oth) {
        this.oth = oth;
        return this;
    }

    public void setOth(String oth) {
        this.oth = oth;
    }

    public String getStatus() {
        return status;
    }

    public FabricCreationMaster status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public FabricCreationMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public FabricCreationMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public FabricCreationMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public FabricCreationMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public FabricSubstractMaster getFabricSubstractMaster() {
        return fabricSubstractMaster;
    }

    public FabricCreationMaster fabricSubstractMaster(FabricSubstractMaster fabricSubstractMaster) {
        this.fabricSubstractMaster = fabricSubstractMaster;
        return this;
    }

    public void setFabricSubstractMaster(FabricSubstractMaster fabricSubstractMaster) {
        this.fabricSubstractMaster = fabricSubstractMaster;
    }

    public FabricSubstractDetails getFabricSubstractDetails() {
        return fabricSubstractDetails;
    }

    public FabricCreationMaster fabricSubstractDetails(FabricSubstractDetails fabricSubstractDetails) {
        this.fabricSubstractDetails = fabricSubstractDetails;
        return this;
    }

    public void setFabricSubstractDetails(FabricSubstractDetails fabricSubstractDetails) {
        this.fabricSubstractDetails = fabricSubstractDetails;
    }

    public FabricSplFinishMaster getFabricSplFinishMaster() {
        return fabricSplFinishMaster;
    }

    public FabricCreationMaster fabricSplFinishMaster(FabricSplFinishMaster fabricSplFinishMaster) {
        this.fabricSplFinishMaster = fabricSplFinishMaster;
        return this;
    }

    public void setFabricSplFinishMaster(FabricSplFinishMaster fabricSplFinishMaster) {
        this.fabricSplFinishMaster = fabricSplFinishMaster;
    }

    public FabricOthersMaster getFabricOthersMaster() {
        return fabricOthersMaster;
    }

    public FabricCreationMaster fabricOthersMaster(FabricOthersMaster fabricOthersMaster) {
        this.fabricOthersMaster = fabricOthersMaster;
        return this;
    }

    public void setFabricOthersMaster(FabricOthersMaster fabricOthersMaster) {
        this.fabricOthersMaster = fabricOthersMaster;
    }

    public Set<FabricCreationWeftDetails> getFabricCreationWeftDetails() {
        return fabricCreationWeftDetails;
    }

    public FabricCreationMaster fabricCreationWeftDetails(Set<FabricCreationWeftDetails> fabricCreationWeftDetails) {
        this.fabricCreationWeftDetails = fabricCreationWeftDetails;
        return this;
    }

    public FabricCreationMaster addFabricCreationWeftDetails(FabricCreationWeftDetails fabricCreationWeftDetails) {
        this.fabricCreationWeftDetails.add(fabricCreationWeftDetails);
        return this;
    }

    public FabricCreationMaster removeFabricCreationWeftDetails(FabricCreationWeftDetails fabricCreationWeftDetails) {
        this.fabricCreationWeftDetails.remove(fabricCreationWeftDetails);
        return this;
    }

    public void setFabricCreationWeftDetails(Set<FabricCreationWeftDetails> fabricCreationWeftDetails) {
        this.fabricCreationWeftDetails = fabricCreationWeftDetails;
    }

    public Set<FabricCreationWarpDetails> getFabricCreationWarpDetails() {
        return fabricCreationWarpDetails;
    }

    public FabricCreationMaster fabricCreationWarpDetails(Set<FabricCreationWarpDetails> fabricCreationWarpDetails) {
        this.fabricCreationWarpDetails = fabricCreationWarpDetails;
        return this;
    }

    public FabricCreationMaster addFabricCreationWarpDetails(FabricCreationWarpDetails fabricCreationWarpDetails) {
        this.fabricCreationWarpDetails.add(fabricCreationWarpDetails);
        return this;
    }

    public FabricCreationMaster removeFabricCreationWarpDetails(FabricCreationWarpDetails fabricCreationWarpDetails) {
        this.fabricCreationWarpDetails.remove(fabricCreationWarpDetails);
        return this;
    }

    public void setFabricCreationWarpDetails(Set<FabricCreationWarpDetails> fabricCreationWarpDetails) {
        this.fabricCreationWarpDetails = fabricCreationWarpDetails;
    }

    public Set<FabricCreationContentDetails> getFabricCreationContentDetails() {
        return fabricCreationContentDetails;
    }

    public FabricCreationMaster fabricCreationContentDetails(Set<FabricCreationContentDetails> fabricCreationContentDetails) {
        this.fabricCreationContentDetails = fabricCreationContentDetails;
        return this;
    }

    public FabricCreationMaster addFabricCreationContentDetails(FabricCreationContentDetails fabricCreationContentDetails) {
        this.fabricCreationContentDetails.add(fabricCreationContentDetails);
        return this;
    }

    public FabricCreationMaster removeFabricCreationContentDetails(FabricCreationContentDetails fabricCreationContentDetails) {
        this.fabricCreationContentDetails.remove(fabricCreationContentDetails);
        return this;
    }

    public void setFabricCreationContentDetails(Set<FabricCreationContentDetails> fabricCreationContentDetails) {
        this.fabricCreationContentDetails = fabricCreationContentDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FabricCreationMaster fabricCreationMaster = (FabricCreationMaster) o;
        if (fabricCreationMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fabricCreationMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FabricCreationMaster{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", epi=" + getEpi() +
            ", ppi=" + getPpi() +
            ", oth='" + getOth() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
