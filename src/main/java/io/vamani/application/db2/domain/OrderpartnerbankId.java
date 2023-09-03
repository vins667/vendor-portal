package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderpartnerbankId implements Serializable {
    private String ordprncsmsuppliercompanycode;
    private String ordprncustomersuppliertype;
    private String ordprncustomersuppliercode;
    private Integer identifier;

    @Column(name = "ORDPRNCSMSUPPLIERCOMPANYCODE")
    @Id
    public String getOrdprncsmsuppliercompanycode() {
        return ordprncsmsuppliercompanycode;
    }

    public void setOrdprncsmsuppliercompanycode(String ordprncsmsuppliercompanycode) {
        this.ordprncsmsuppliercompanycode = ordprncsmsuppliercompanycode;
    }

    @Column(name = "ORDPRNCUSTOMERSUPPLIERTYPE")
    @Id
    public String getOrdprncustomersuppliertype() {
        return ordprncustomersuppliertype;
    }

    public void setOrdprncustomersuppliertype(String ordprncustomersuppliertype) {
        this.ordprncustomersuppliertype = ordprncustomersuppliertype;
    }

    @Column(name = "ORDPRNCUSTOMERSUPPLIERCODE")
    @Id
    public String getOrdprncustomersuppliercode() {
        return ordprncustomersuppliercode;
    }

    public void setOrdprncustomersuppliercode(String ordprncustomersuppliercode) {
        this.ordprncustomersuppliercode = ordprncustomersuppliercode;
    }

    @Column(name = "IDENTIFIER")
    @Id
    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderpartnerbankId that = (OrderpartnerbankId) o;
        return Objects.equals(ordprncsmsuppliercompanycode, that.ordprncsmsuppliercompanycode) && Objects.equals(ordprncustomersuppliertype, that.ordprncustomersuppliertype) && Objects.equals(ordprncustomersuppliercode, that.ordprncustomersuppliercode) && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordprncsmsuppliercompanycode, ordprncustomersuppliertype, ordprncustomersuppliercode, identifier);
    }
}
