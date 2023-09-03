package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class CutBundleLockId implements Serializable {
    @Column(name = "production_code", length = 20)
    private String productionCode;

    @Column(name = "plant_code", length = 10)
    private String plantCode;

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CutBundleLockId that = (CutBundleLockId) o;
        return Objects.equals(productionCode, that.productionCode) && Objects.equals(plantCode, that.plantCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productionCode, plantCode);
    }

    public CutBundleLockId() {
    }

    public CutBundleLockId(String productionCode, String plantCode) {
        this.productionCode = productionCode;
        this.plantCode = plantCode;
    }
}
