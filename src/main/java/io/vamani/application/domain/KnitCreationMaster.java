package io.vamani.application.domain;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A KnitCreationMaster.
 */
@Entity
@Table(name = "knit_creation_master")
public class KnitCreationMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="knitCreationMasterSeq", sequenceName="knit_creation_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="knitCreationMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "code", length = 60, nullable = false)
    private String code;

    @NotNull
    @Size(max = 1000)
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

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

    @ManyToOne
    @JsonIgnoreProperties("")
    @JoinColumn(name = "yarn_count_master_id")
    private YarnCountMaster yarnCountMaster;

    @ManyToOne
    @JsonIgnoreProperties("")
    @JoinColumn(name = "yarn_type_master_id")
    private YarnTypeMaster yarnTypeMaster;

    @ManyToOne
    @JsonIgnoreProperties("")
    @JoinColumn(name = "knit_type_master_id")
    private KnitTypeMaster knitTypeMaster;

    @ManyToOne
    @JsonIgnoreProperties("")
    @JoinColumn(name = "knit_process_master_id")
    private KnitProcessMaster knitProcessMaster;

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

    public KnitCreationMaster code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public KnitCreationMaster description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public KnitCreationMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public KnitCreationMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public KnitCreationMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public KnitCreationMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public YarnCountMaster getYarnCountMaster() {
        return yarnCountMaster;
    }

    public KnitCreationMaster yarnCountMaster(YarnCountMaster yarnCountMaster) {
        this.yarnCountMaster = yarnCountMaster;
        return this;
    }

    public void setYarnCountMaster(YarnCountMaster yarnCountMaster) {
        this.yarnCountMaster = yarnCountMaster;
    }

    public YarnTypeMaster getYarnTypeMaster() {
        return yarnTypeMaster;
    }

    public KnitCreationMaster yarnTypeMaster(YarnTypeMaster yarnTypeMaster) {
        this.yarnTypeMaster = yarnTypeMaster;
        return this;
    }

    public void setYarnTypeMaster(YarnTypeMaster yarnTypeMaster) {
        this.yarnTypeMaster = yarnTypeMaster;
    }

    public KnitTypeMaster getKnitTypeMaster() {
        return knitTypeMaster;
    }

    public KnitCreationMaster knitTypeMaster(KnitTypeMaster knitTypeMaster) {
        this.knitTypeMaster = knitTypeMaster;
        return this;
    }

    public void setKnitTypeMaster(KnitTypeMaster knitTypeMaster) {
        this.knitTypeMaster = knitTypeMaster;
    }

    public KnitProcessMaster getKnitProcessMaster() {
        return knitProcessMaster;
    }

    public KnitCreationMaster knitProcessMaster(KnitProcessMaster knitProcessMaster) {
        this.knitProcessMaster = knitProcessMaster;
        return this;
    }

    public void setKnitProcessMaster(KnitProcessMaster knitProcessMaster) {
        this.knitProcessMaster = knitProcessMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KnitCreationMaster)) {
            return false;
        }
        return id != null && id.equals(((KnitCreationMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "KnitCreationMaster{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
