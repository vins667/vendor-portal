package io.vamani.application.model;

import io.vamani.application.domain.VcutOperationMaster;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VcutOperationMasterBean implements Serializable {
    private String itemName;
    private String style;
    private List<VcutOperationMaster> vcutOperationMasters;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<VcutOperationMaster> getVcutOperationMasters() {
        return vcutOperationMasters;
    }

    public void setVcutOperationMasters(List<VcutOperationMaster> vcutOperationMasters) {
        this.vcutOperationMasters = vcutOperationMasters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutOperationMasterBean that = (VcutOperationMasterBean) o;
        return Objects.equals(itemName, that.itemName) &&
            Objects.equals(style, that.style) &&
            Objects.equals(vcutOperationMasters, that.vcutOperationMasters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, style, vcutOperationMasters);
    }

    @Override
    public String toString() {
        return "VcutOperationMasterBean{" +
            "itemName='" + itemName + '\'' +
            ", style='" + style + '\'' +
            ", vcutOperationMasters=" + vcutOperationMasters +
            '}';
    }
}
