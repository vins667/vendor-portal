package io.vamani.application.db2.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class PurchaseorderlineId implements Serializable {
    @Column(name = "PURCHASEORDERCOMPANYCODE")
    private String purchaseordercompanycode;
    @Column(name = "PURCHASEORDERCOUNTERCODE")
    private String purchaseordercountercode;
    @Column(name = "PURCHASEORDERCODE")
    private String purchaseordercode;
    @Column(name = "ORDERLINE")
    private Integer orderline;
    @Column(name = "ORDERSUBLINE")
    private Integer ordersubline;

    public String getPurchaseordercompanycode() {
        return purchaseordercompanycode;
    }

    public void setPurchaseordercompanycode(String purchaseordercompanycode) {
        this.purchaseordercompanycode = purchaseordercompanycode;
    }

    public String getPurchaseordercountercode() {
        return purchaseordercountercode;
    }

    public void setPurchaseordercountercode(String purchaseordercountercode) {
        this.purchaseordercountercode = purchaseordercountercode;
    }

    public String getPurchaseordercode() {
        return purchaseordercode;
    }

    public void setPurchaseordercode(String purchaseordercode) {
        this.purchaseordercode = purchaseordercode;
    }

    public Integer getOrderline() {
        return orderline;
    }

    public void setOrderline(Integer orderline) {
        this.orderline = orderline;
    }

    public Integer getOrdersubline() {
        return ordersubline;
    }

    public void setOrdersubline(Integer ordersubline) {
        this.ordersubline = ordersubline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseorderlineId that = (PurchaseorderlineId) o;
        return Objects.equals(purchaseordercompanycode, that.purchaseordercompanycode) && Objects.equals(purchaseordercountercode, that.purchaseordercountercode) && Objects.equals(purchaseordercode, that.purchaseordercode) && Objects.equals(orderline, that.orderline) && Objects.equals(ordersubline, that.ordersubline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseordercompanycode, purchaseordercountercode, purchaseordercode, orderline, ordersubline);
    }
}
