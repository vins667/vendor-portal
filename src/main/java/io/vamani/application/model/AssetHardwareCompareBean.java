package io.vamani.application.model;

import java.io.Serializable;
import java.util.Objects;

public class AssetHardwareCompareBean implements Serializable {
    private String uuid;
    private String name;
    private String hostname;
    private String ip;
    private String assetCode;
    private Long storageCount;
    private Long memoryCount;
    private Long systemId;
    private String type;
    private String color;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Long getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(Long storageCount) {
        this.storageCount = storageCount;
    }

    public Long getMemoryCount() {
        return memoryCount;
    }

    public void setMemoryCount(Long memoryCount) {
        this.memoryCount = memoryCount;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetHardwareCompareBean that = (AssetHardwareCompareBean) o;
        return Objects.equals(uuid, that.uuid) &&
            Objects.equals(name, that.name) &&
            Objects.equals(hostname, that.hostname) &&
            Objects.equals(ip, that.ip) &&
            Objects.equals(assetCode, that.assetCode) &&
            Objects.equals(storageCount, that.storageCount) &&
            Objects.equals(memoryCount, that.memoryCount) &&
            Objects.equals(systemId, that.systemId) &&
            Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, hostname, ip, assetCode, storageCount, memoryCount, systemId, type);
    }

    @Override
    public String toString() {
        return "AssetHardwareCompareBean{" +
            "uuid='" + uuid + '\'' +
            ", name='" + name + '\'' +
            ", hostname='" + hostname + '\'' +
            ", ip='" + ip + '\'' +
            ", assetCode='" + assetCode + '\'' +
            ", storageCount=" + storageCount +
            ", memoryCount=" + memoryCount +
            ", systemId=" + systemId +
            ", type='" + type + '\'' +
            '}';
    }
}
