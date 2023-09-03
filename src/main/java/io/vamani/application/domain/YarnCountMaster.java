package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A YarnCountMaster.
 */
@Entity
@Table(name = "yarn_count_master")
public class YarnCountMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="yarnCountMasterSeq", sequenceName="yarn_count_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="yarnCountMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "code", length = 15, nullable = false)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_created_by", length = 50)
    private String lastCreatedBy;

    @Column(name = "last_created_date")
    private Instant lastCreatedDate;

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

    public YarnCountMaster code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public YarnCountMaster description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public YarnCountMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public YarnCountMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public YarnCountMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastCreatedBy() {
        return lastCreatedBy;
    }

    public YarnCountMaster lastCreatedBy(String lastCreatedBy) {
        this.lastCreatedBy = lastCreatedBy;
        return this;
    }

    public void setLastCreatedBy(String lastCreatedBy) {
        this.lastCreatedBy = lastCreatedBy;
    }

    public Instant getLastCreatedDate() {
        return lastCreatedDate;
    }

    public YarnCountMaster lastCreatedDate(Instant lastCreatedDate) {
        this.lastCreatedDate = lastCreatedDate;
        return this;
    }

    public void setLastCreatedDate(Instant lastCreatedDate) {
        this.lastCreatedDate = lastCreatedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof YarnCountMaster)) {
            return false;
        }
        return id != null && id.equals(((YarnCountMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "YarnCountMaster{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastCreatedBy='" + getLastCreatedBy() + "'" +
            ", lastCreatedDate='" + getLastCreatedDate() + "'" +
            "}";
    }
}
