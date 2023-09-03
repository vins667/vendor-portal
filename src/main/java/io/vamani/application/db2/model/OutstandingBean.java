package io.vamani.application.db2.model;

import java.math.BigDecimal;

public class OutstandingBean {
    private String supplierCode;
    private BigDecimal amount;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
