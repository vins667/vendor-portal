package io.vamani.application.model;

import java.io.Serializable;

public class CutPlanBundleSizesBean implements Serializable {
    private String sizeCode;
    private Double quantity;

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
