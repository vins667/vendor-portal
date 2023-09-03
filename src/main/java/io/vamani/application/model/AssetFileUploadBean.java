package io.vamani.application.model;

import io.vamani.application.domain.AssetFileUploadDetails;
import io.vamani.application.domain.AssetFileUploadMaster;
import io.vamani.application.domain.AssetMaster;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AssetFileUploadBean implements Serializable {
    private AssetMaster assetMaster;
    private List<AssetFileUploadMasterBean> assetFileUploadMasters;
    private List<AssetFileUploadDetails> assetFileUploadDetails;

    public AssetMaster getAssetMaster() {
        return assetMaster;
    }

    public void setAssetMaster(AssetMaster assetMaster) {
        this.assetMaster = assetMaster;
    }

    public List<AssetFileUploadMasterBean> getAssetFileUploadMasters() {
        return assetFileUploadMasters;
    }

    public void setAssetFileUploadMasters(List<AssetFileUploadMasterBean> assetFileUploadMasters) {
        this.assetFileUploadMasters = assetFileUploadMasters;
    }

    public List<AssetFileUploadDetails> getAssetFileUploadDetails() {
        return assetFileUploadDetails;
    }

    public void setAssetFileUploadDetails(List<AssetFileUploadDetails> assetFileUploadDetails) {
        this.assetFileUploadDetails = assetFileUploadDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetFileUploadBean that = (AssetFileUploadBean) o;
        return Objects.equals(assetMaster, that.assetMaster) &&
            Objects.equals(assetFileUploadMasters, that.assetFileUploadMasters) &&
            Objects.equals(assetFileUploadDetails, that.assetFileUploadDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetMaster, assetFileUploadMasters, assetFileUploadDetails);
    }

    @Override
    public String toString() {
        return "AssetFileUploadBean{" +
            "assetMaster=" + assetMaster +
            ", assetFileUploadMasters=" + assetFileUploadMasters +
            ", assetFileUploadDetails=" + assetFileUploadDetails +
            '}';
    }
}
