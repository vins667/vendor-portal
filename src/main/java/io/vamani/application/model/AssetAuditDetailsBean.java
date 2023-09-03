package io.vamani.application.model;

import io.vamani.application.domain.AssetAuditSoftwareDetails;
import io.vamani.application.domain.AssetAuditSoftwareKeyDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class AssetAuditDetailsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long systemId;

    private String uuid;

    private String name;

    private String ip;

    private String hostname;

    private String manufacturer;

    private String model;

    private String serial;

    private Long storageCount;

    private Long memoryCount;

    private String type;

    private Instant osInstallationDate;

    private String osGroup;

    private String osFamily;

    private String osName;

    private String osVersion;

    private String assetCode;

    private String createdBy;

    private Instant createdDate;

    private List<AssetAuditSoftwareDetails> assetAuditSoftwareDetailsList;

    private List<AssetAuditSoftwareKeyDetails> assetAuditSoftwareKeyDetails;

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

    public AssetAuditDetailsBean systemId(Long systemId) {
        this.systemId = systemId;
        return this;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getUuid() {
        return uuid;
    }

    public AssetAuditDetailsBean uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public AssetAuditDetailsBean name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public AssetAuditDetailsBean ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public AssetAuditDetailsBean hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public AssetAuditDetailsBean manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public AssetAuditDetailsBean model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public AssetAuditDetailsBean serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Long getStorageCount() {
        return storageCount;
    }

    public AssetAuditDetailsBean storageCount(Long storageCount) {
        this.storageCount = storageCount;
        return this;
    }

    public void setStorageCount(Long storageCount) {
        this.storageCount = storageCount;
    }

    public Long getMemoryCount() {
        return memoryCount;
    }

    public AssetAuditDetailsBean memoryCount(Long memoryCount) {
        this.memoryCount = memoryCount;
        return this;
    }

    public void setMemoryCount(Long memoryCount) {
        this.memoryCount = memoryCount;
    }

    public String getType() {
        return type;
    }

    public AssetAuditDetailsBean type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getOsInstallationDate() {
        return osInstallationDate;
    }

    public AssetAuditDetailsBean osInstallationDate(Instant osInstallationDate) {
        this.osInstallationDate = osInstallationDate;
        return this;
    }

    public void setOsInstallationDate(Instant osInstallationDate) {
        this.osInstallationDate = osInstallationDate;
    }

    public String getOsGroup() {
        return osGroup;
    }

    public AssetAuditDetailsBean osGroup(String osGroup) {
        this.osGroup = osGroup;
        return this;
    }

    public void setOsGroup(String osGroup) {
        this.osGroup = osGroup;
    }

    public String getOsFamily() {
        return osFamily;
    }

    public AssetAuditDetailsBean osFamily(String osFamily) {
        this.osFamily = osFamily;
        return this;
    }

    public void setOsFamily(String osFamily) {
        this.osFamily = osFamily;
    }

    public String getOsName() {
        return osName;
    }

    public AssetAuditDetailsBean osName(String osName) {
        this.osName = osName;
        return this;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public AssetAuditDetailsBean osVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AssetAuditDetailsBean createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public AssetAuditDetailsBean createdDate(Instant createdDate) {
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

    public List<AssetAuditSoftwareDetails> getAssetAuditSoftwareDetailsList() {
        return assetAuditSoftwareDetailsList;
    }

    public void setAssetAuditSoftwareDetailsList(List<AssetAuditSoftwareDetails> assetAuditSoftwareDetailsList) {
        this.assetAuditSoftwareDetailsList = assetAuditSoftwareDetailsList;
    }

    public List<AssetAuditSoftwareKeyDetails> getAssetAuditSoftwareKeyDetails() {
        return assetAuditSoftwareKeyDetails;
    }

    public void setAssetAuditSoftwareKeyDetails(List<AssetAuditSoftwareKeyDetails> assetAuditSoftwareKeyDetails) {
        this.assetAuditSoftwareKeyDetails = assetAuditSoftwareKeyDetails;
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
        AssetAuditDetailsBean assetAuditDetails = (AssetAuditDetailsBean) o;
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
