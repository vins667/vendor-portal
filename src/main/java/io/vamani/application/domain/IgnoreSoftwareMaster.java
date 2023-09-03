package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A IgnoreSoftwareMaster.
 */
@Entity
@Table(name = "ignore_software_master")
public class IgnoreSoftwareMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="ignoreSoftwareMasterSeq", sequenceName="ignore_software_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="ignoreSoftwareMasterSeq")
    private Long id;

    @Size(max = 200)
    @Column(name = "sw_name", length = 200)
    private String swName;

    @Size(max = 200)
    @Column(name = "sw_publisher", length = 200)
    private String swPublisher;

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

    public String getSwName() {
        return swName;
    }

    public IgnoreSoftwareMaster swName(String swName) {
        this.swName = swName;
        return this;
    }

    public void setSwName(String swName) {
        this.swName = swName;
    }

    public String getSwPublisher() {
        return swPublisher;
    }

    public IgnoreSoftwareMaster swPublisher(String swPublisher) {
        this.swPublisher = swPublisher;
        return this;
    }

    public void setSwPublisher(String swPublisher) {
        this.swPublisher = swPublisher;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public IgnoreSoftwareMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public IgnoreSoftwareMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public IgnoreSoftwareMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public IgnoreSoftwareMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        IgnoreSoftwareMaster ignoreSoftwareMaster = (IgnoreSoftwareMaster) o;
        if (ignoreSoftwareMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ignoreSoftwareMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IgnoreSoftwareMaster{" +
            "id=" + getId() +
            ", swName='" + getSwName() + "'" +
            ", swPublisher='" + getSwPublisher() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}
