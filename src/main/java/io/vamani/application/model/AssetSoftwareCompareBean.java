package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class AssetSoftwareCompareBean implements Serializable {
    private String uuid;
    private String name;
    private String publisher;
    private Instant installedOn;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Instant getInstalledOn() {
        return installedOn;
    }

    public void setInstalledOn(Instant installedOn) {
        this.installedOn = installedOn;
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
        AssetSoftwareCompareBean that = (AssetSoftwareCompareBean) o;
        return Objects.equals(uuid, that.uuid) &&
            Objects.equals(name, that.name) &&
            Objects.equals(publisher, that.publisher) &&
            Objects.equals(installedOn, that.installedOn) &&
            Objects.equals(type, that.type) &&
            Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, publisher, installedOn, type, color);
    }

    @Override
    public String toString() {
        return "AssetSoftwareCompareBean{" +
            "uuid='" + uuid + '\'' +
            ", name='" + name + '\'' +
            ", publisher='" + publisher + '\'' +
            ", installedOn=" + installedOn +
            ", type='" + type + '\'' +
            ", color='" + color + '\'' +
            '}';
    }
}
