package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

import java.util.Objects;

public class AssetAuditSearch {
    private String uuid;
    private String name;
    private String ip;
    private String serial;
    private String assetCode;
    private Pageable page;
    private int size;
    private int pageNo;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetAuditSearch that = (AssetAuditSearch) o;
        return Objects.equals(uuid, that.uuid) &&
            Objects.equals(name, that.name) &&
            Objects.equals(ip, that.ip) &&
            Objects.equals(serial, that.serial) &&
            Objects.equals(assetCode, that.assetCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, ip, serial, assetCode);
    }

    @Override
    public String toString() {
        return "AssetAuditSearch{" +
            "uuid='" + uuid + '\'' +
            ", name='" + name + '\'' +
            ", ip='" + ip + '\'' +
            ", serial='" + serial + '\'' +
            ", assetCode='" + assetCode + '\'' +
            '}';
    }
}
