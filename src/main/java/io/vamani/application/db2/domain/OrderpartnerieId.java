package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderpartnerieId implements Serializable {
    private String customersuppliercompanycode;
    private String customersuppliertype;
    private String customersuppliercode;

    @Column(name = "CUSTOMERSUPPLIERCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCustomersuppliercompanycode() {
        return customersuppliercompanycode;
    }

    public void setCustomersuppliercompanycode(String customersuppliercompanycode) {
        this.customersuppliercompanycode = customersuppliercompanycode;
    }

    @Column(name = "CUSTOMERSUPPLIERTYPE", nullable = false, length = 1)
    @Id
    public String getCustomersuppliertype() {
        return customersuppliertype;
    }

    public void setCustomersuppliertype(String customersuppliertype) {
        this.customersuppliertype = customersuppliertype;
    }

    @Column(name = "CUSTOMERSUPPLIERCODE", nullable = false, length = 8)
    @Id
    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderpartnerieId that = (OrderpartnerieId) o;
        return Objects.equals(customersuppliercompanycode, that.customersuppliercompanycode) && Objects.equals(customersuppliertype, that.customersuppliertype) && Objects.equals(customersuppliercode, that.customersuppliercode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customersuppliercompanycode, customersuppliertype, customersuppliercode);
    }

    public OrderpartnerieId() {
    }

    public OrderpartnerieId(String customersuppliercompanycode, String customersuppliertype, String customersuppliercode) {
        this.customersuppliercompanycode = customersuppliercompanycode;
        this.customersuppliertype = customersuppliertype;
        this.customersuppliercode = customersuppliercode;
    }
}
