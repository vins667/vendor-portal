package io.vamani.application.mobile;

import java.io.Serializable;
import java.util.Objects;

public class VcutTvcCordinate implements Serializable {
    private String coordinateType;
    private String coordinateX;
    private String coordinateY;

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

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

    @Override
    public String toString() {
        return "VcutTvcCordinate{" +
            "coordinateType='" + coordinateType + '\'' +
            ", coordinateX='" + coordinateX + '\'' +
            ", coordinateY='" + coordinateY + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutTvcCordinate that = (VcutTvcCordinate) o;
        return Objects.equals(coordinateType, that.coordinateType) &&
            Objects.equals(coordinateX, that.coordinateX) &&
            Objects.equals(coordinateY, that.coordinateY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateType, coordinateX, coordinateY);
    }
}
