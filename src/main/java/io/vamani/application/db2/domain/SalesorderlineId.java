package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SalesorderlineId implements Serializable {
    private String salesordercompanycode;
    private String salesordercountercode;
    private String salesordercode;
    private Integer orderline;
    private Integer ordersubline;
    private Integer componentorderline;

    @Column(name = "SALESORDERCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getSalesordercompanycode() {
        return salesordercompanycode;
    }

    public void setSalesordercompanycode(String salesordercompanycode) {
        this.salesordercompanycode = salesordercompanycode;
    }

    @Column(name = "SALESORDERCOUNTERCODE", nullable = false, length = 8)
    @Id
    public String getSalesordercountercode() {
        return salesordercountercode;
    }

    public void setSalesordercountercode(String salesordercountercode) {
        this.salesordercountercode = salesordercountercode;
    }

    @Column(name = "SALESORDERCODE", nullable = false, length = 15)
    @Id
    public String getSalesordercode() {
        return salesordercode;
    }

    public void setSalesordercode(String salesordercode) {
        this.salesordercode = salesordercode;
    }

    @Column(name = "ORDERLINE", nullable = false, precision = 0)
    @Id
    public Integer getOrderline() {
        return orderline;
    }

    public void setOrderline(Integer orderline) {
        this.orderline = orderline;
    }

    @Column(name = "ORDERSUBLINE", nullable = false, precision = 0)
    @Id
    public Integer getOrdersubline() {
        return ordersubline;
    }

    public void setOrdersubline(Integer ordersubline) {
        this.ordersubline = ordersubline;
    }

    @Column(name = "COMPONENTORDERLINE", nullable = false, precision = 0)
    @Id
    public Integer getComponentorderline() {
        return componentorderline;
    }

    public void setComponentorderline(Integer componentorderline) {
        this.componentorderline = componentorderline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesorderlineId that = (SalesorderlineId) o;
        return Objects.equals(salesordercompanycode, that.salesordercompanycode) && Objects.equals(salesordercountercode, that.salesordercountercode) && Objects.equals(salesordercode, that.salesordercode) && Objects.equals(orderline, that.orderline) && Objects.equals(ordersubline, that.ordersubline) && Objects.equals(componentorderline, that.componentorderline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesordercompanycode, salesordercountercode, salesordercode, orderline, ordersubline, componentorderline);
    }
}
