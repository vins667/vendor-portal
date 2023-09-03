package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetAuditSoftwareKeyDetails.
 */
@Entity
@Table(name = "asset_audit_software_key_details")
public class AssetAuditSoftwareKeyDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="assetAuditSoftwareKeyDetailsSeq", sequenceName="asset_audit_software_key_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="assetAuditSoftwareKeyDetailsSeq")
    private Long id;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Size(max = 100)
    @Column(name = "jhi_key", length = 100)
    private String key;

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

    public AssetAuditSoftwareKeyDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public AssetAuditSoftwareKeyDetails key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getSystemId() {
        return systemId;
    }

    public AssetAuditSoftwareKeyDetails systemId(Long systemId) {
        this.systemId = systemId;
        return this;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetAuditSoftwareKeyDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetAuditSoftwareKeyDetails createdDate(Instant createdDate) {
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
        AssetAuditSoftwareKeyDetails assetAuditSoftwareKeyDetails = (AssetAuditSoftwareKeyDetails) o;
        if (assetAuditSoftwareKeyDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetAuditSoftwareKeyDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetAuditSoftwareKeyDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", key='" + getKey() + "'" +
            ", systemId=" + getSystemId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
