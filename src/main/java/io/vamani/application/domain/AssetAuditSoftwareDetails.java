package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetAuditSoftwareDetails.
 */
@Entity
@Table(name = "asset_audit_software_details")
public class AssetAuditSoftwareDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="assetAuditSoftwareDetailsSeq", sequenceName="asset_audit_software_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="assetAuditSoftwareDetailsSeq")
    private Long id;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Size(max = 255)
    @Column(name = "publisher", length = 255)
    private String publisher;

    @Column(name = "installed_on")
    private Instant installedOn;

    @Column(name = "system_id")
    private Long systemId;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AssetAuditSoftwareDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public AssetAuditSoftwareDetails publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Instant getInstalledOn() {
        return installedOn;
    }

    public AssetAuditSoftwareDetails installedOn(Instant installedOn) {
        this.installedOn = installedOn;
        return this;
    }

    public void setInstalledOn(Instant installedOn) {
        this.installedOn = installedOn;
    }

    public Long getSystemId() {
        return systemId;
    }

    public AssetAuditSoftwareDetails systemId(Long systemId) {
        this.systemId = systemId;
        return this;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetAuditSoftwareDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetAuditSoftwareDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
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
        AssetAuditSoftwareDetails assetAuditSoftwareDetails = (AssetAuditSoftwareDetails) o;
        if (assetAuditSoftwareDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetAuditSoftwareDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetAuditSoftwareDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", publisher='" + getPublisher() + "'" +
            ", installedOn='" + getInstalledOn() + "'" +
            ", systemId=" + getSystemId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
