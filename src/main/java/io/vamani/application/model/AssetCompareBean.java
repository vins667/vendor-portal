package io.vamani.application.model;

import io.vamani.application.domain.AssetAuditRunTime;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class AssetCompareBean implements Serializable {
    private Instant startTime;
    private Instant endTime;
    private List<AssetHardwareCompareBean> assetHardwareCompareBeans;
    private List<AssetSoftwareCompareBean> assetSoftwareCompareBeans;
    private List<AssetSoftwareKeyCompareBean> assetSoftwareKeyCompareBeans;

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public List<AssetHardwareCompareBean> getAssetHardwareCompareBeans() {
        return assetHardwareCompareBeans;
    }

    public void setAssetHardwareCompareBeans(List<AssetHardwareCompareBean> assetHardwareCompareBeans) {
        this.assetHardwareCompareBeans = assetHardwareCompareBeans;
    }

    public List<AssetSoftwareCompareBean> getAssetSoftwareCompareBeans() {
        return assetSoftwareCompareBeans;
    }

    public void setAssetSoftwareCompareBeans(List<AssetSoftwareCompareBean> assetSoftwareCompareBeans) {
        this.assetSoftwareCompareBeans = assetSoftwareCompareBeans;
    }

    public List<AssetSoftwareKeyCompareBean> getAssetSoftwareKeyCompareBeans() {
        return assetSoftwareKeyCompareBeans;
    }

    public void setAssetSoftwareKeyCompareBeans(List<AssetSoftwareKeyCompareBean> assetSoftwareKeyCompareBeans) {
        this.assetSoftwareKeyCompareBeans = assetSoftwareKeyCompareBeans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetCompareBean that = (AssetCompareBean) o;
        return Objects.equals(startTime, that.startTime) &&
            Objects.equals(endTime, that.endTime) &&
            Objects.equals(assetHardwareCompareBeans, that.assetHardwareCompareBeans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, assetHardwareCompareBeans);
    }

    @Override
    public String toString() {
        return "AssetCompareBean{" +
            "startTime=" + startTime +
            ", endTime=" + endTime +
            ", assetHardwareCompareBeans=" + assetHardwareCompareBeans +
            '}';
    }
}
