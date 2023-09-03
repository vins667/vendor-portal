package io.vamani.application.model;

import java.io.Serializable;
import java.util.Objects;

public class AssetSoftwareKeyCompareBean implements Serializable {
    private String uuid;
    private String name;
    private String jhiKey;
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

    public String getJhiKey() {
        return jhiKey;
    }

    public void setJhiKey(String jhiKey) {
        this.jhiKey = jhiKey;
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
        AssetSoftwareKeyCompareBean that = (AssetSoftwareKeyCompareBean) o;
        return Objects.equals(uuid, that.uuid) &&
            Objects.equals(name, that.name) &&
            Objects.equals(jhiKey, that.jhiKey) &&
            Objects.equals(type, that.type) &&
            Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, jhiKey, type, color);
    }

    @Override
    public String toString() {
        return "AssetSoftwareKeyCompareBean{" +
            "uuid='" + uuid + '\'' +
            ", name='" + name + '\'' +
            ", jhiKey='" + jhiKey + '\'' +
            ", type='" + type + '\'' +
            ", color='" + color + '\'' +
            '}';
    }
}
