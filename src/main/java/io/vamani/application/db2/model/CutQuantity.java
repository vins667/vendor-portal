package io.vamani.application.db2.model;

import java.math.BigDecimal;

public class CutQuantity {
    private String sizeCode;
    private BigDecimal orderQuantity;
    private BigDecimal toleranceQuantity;
    private BigDecimal netQuantity;

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getToleranceQuantity() {
        return toleranceQuantity;
    }

    public void setToleranceQuantity(BigDecimal toleranceQuantity) {
        this.toleranceQuantity = toleranceQuantity;
    }

    public BigDecimal getNetQuantity() {
        return netQuantity;
    }

    public void setNetQuantity(BigDecimal netQuantity) {
        this.netQuantity = netQuantity;
    }
}
