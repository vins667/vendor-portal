package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AssetAuditDetails.
 */
@Entity
@Table(name = "asset_audit_details")
public class AssetAuditDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="assetAuditDetailsSeq", sequenceName="asset_audit_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="assetAuditDetailsSeq")
    private Long id;

    @NotNull
    @Column(name = "system_id", nullable = false)
    private Long systemId;

    @Size(max = 100)
    @Column(name = "uuid", length = 100)
    private String uuid;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Size(max = 45)
    @Column(name = "ip", length = 45)
    private String ip;

    @Size(max = 100)
    @Column(name = "hostname", length = 100)
    private String hostname;

    @Size(max = 100)
    @Column(name = "manufacturer", length = 100)
    private String manufacturer;

    @Size(max = 200)
    @Column(name = "model", length = 200)
    private String model;

    @Size(max = 200)
    @Column(name = "serial", length = 200)
    private String serial;

    @Column(name = "storage_count")
    private Long storageCount;

    @Column(name = "memory_count")
    private Long memoryCount;

    @Size(max = 50)
    @Column(name = "jhi_type", length = 50)
    private String type;

    @Column(name = "os_installation_date")
    private Instant osInstallationDate;

    @Size(max = 50)
    @Column(name = "os_group", length = 50)
    private String osGroup;

    @Size(max = 50)
    @Column(name = "os_family", length = 50)
    private String osFamily;

    @Size(max = 100)
    @Column(name = "os_name", length = 100)
    private String osName;

    @Size(max = 50)
    @Column(name = "os_version", length = 50)
    private String osVersion;

    @Size(max = 100)
    @Column(name = "asset_code", length = 100)
    private String assetCode;

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

    public Long getSystemId() {
        return systemId;
    }

    public AssetAuditDetails systemId(Long systemId) {
        this.systemId = systemId;
        return this;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getUuid() {
        return uuid;
    }

    public AssetAuditDetails uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public AssetAuditDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public AssetAuditDetails ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public AssetAuditDetails hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public AssetAuditDetails manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public AssetAuditDetails model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public AssetAuditDetails serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Long getStorageCount() {
        return storageCount;
    }

    public AssetAuditDetails storageCount(Long storageCount) {
        this.storageCount = storageCount;
        return this;
    }

    public void setStorageCount(Long storageCount) {
        this.storageCount = storageCount;
    }

    public Long getMemoryCount() {
        return memoryCount;
    }

    public AssetAuditDetails memoryCount(Long memoryCount) {
        this.memoryCount = memoryCount;
        return this;
    }

    public void setMemoryCount(Long memoryCount) {
        this.memoryCount = memoryCount;
    }

    public String getType() {
        return type;
    }

    public AssetAuditDetails type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getOsInstallationDate() {
        return osInstallationDate;
    }

    public AssetAuditDetails osInstallationDate(Instant osInstallationDate) {
        this.osInstallationDate = osInstallationDate;
        return this;
    }

    public void setOsInstallationDate(Instant osInstallationDate) {
        this.osInstallationDate = osInstallationDate;
    }

    public String getOsGroup() {
        return osGroup;
    }

    public AssetAuditDetails osGroup(String osGroup) {
        this.osGroup = osGroup;
        return this;
    }

    public void setOsGroup(String osGroup) {
        this.osGroup = osGroup;
    }

    public String getOsFamily() {
        return osFamily;
    }

    public AssetAuditDetails osFamily(String osFamily) {
        this.osFamily = osFamily;
        return this;
    }

    public void setOsFamily(String osFamily) {
        this.osFamily = osFamily;
    }

    public String getOsName() {
        return osName;
    }

    public AssetAuditDetails osName(String osName) {
        this.osName = osName;
        return this;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public AssetAuditDetails osVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetAuditDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetAuditDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
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
        AssetAuditDetails assetAuditDetails = (AssetAuditDetails) o;
        if (assetAuditDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetAuditDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetAuditDetails{" +
            "id=" + getId() +
            ", systemId=" + getSystemId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", ip='" + getIp() + "'" +
            ", hostname='" + getHostname() + "'" +
            ", manufacturer='" + getManufacturer() + "'" +
            ", model='" + getModel() + "'" +
            ", serial='" + getSerial() + "'" +
            ", storageCount=" + getStorageCount() +
            ", memoryCount=" + getMemoryCount() +
            ", type='" + getType() + "'" +
            ", osInstallationDate='" + getOsInstallationDate() + "'" +
            ", osGroup='" + getOsGroup() + "'" +
            ", osFamily='" + getOsFamily() + "'" +
            ", osName='" + getOsName() + "'" +
            ", osVersion='" + getOsVersion() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
