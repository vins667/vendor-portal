package io.vamani.application.model;

import java.io.Serializable;
import java.util.Objects;

public class VcutMainEntryCoordinateDetails implements Serializable {
    private String coordinateX;
    private String coordinateY;
    private String coordinateType;

    public String getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutMainEntryCoordinateDetails that = (VcutMainEntryCoordinateDetails) o;
        return Objects.equals(coordinateX, that.coordinateX) &&
            Objects.equals(coordinateY, that.coordinateY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return "VcutMainEntryCoordinateDetails{" +
            "coordinateX='" + coordinateX + '\'' +
            ", coordinateY='" + coordinateY + '\'' +
            '}';
    }
}
