package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductspecializedsizeId implements Serializable {
    private String productcompanycode;
    private String productitemtypecode;
    private String productsubcode01;
    private String productsubcode02;
    private String productsubcode03;
    private String productsubcode04;
    private String productsubcode05;
    private String productsubcode06;
    private String productsubcode07;
    private String productsubcode08;

    @Column(name = "PRODUCTCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getProductcompanycode() {
        return productcompanycode;
    }

    public void setProductcompanycode(String productcompanycode) {
        this.productcompanycode = productcompanycode;
    }

    @Column(name = "PRODUCTITEMTYPECODE", nullable = false, length = 3)
    @Id
    public String getProductitemtypecode() {
        return productitemtypecode;
    }

    public void setProductitemtypecode(String productitemtypecode) {
        this.productitemtypecode = productitemtypecode;
    }

    @Column(name = "PRODUCTSUBCODE01", nullable = false, length = 20)
    @Id
    public String getProductsubcode01() {
        return productsubcode01;
    }

    public void setProductsubcode01(String productsubcode01) {
        this.productsubcode01 = productsubcode01;
    }

    @Column(name = "PRODUCTSUBCODE02", nullable = false, length = 10)
    @Id
    public String getProductsubcode02() {
        return productsubcode02;
    }

    public void setProductsubcode02(String productsubcode02) {
        this.productsubcode02 = productsubcode02;
    }

    @Column(name = "PRODUCTSUBCODE03", nullable = false, length = 10)
    @Id
    public String getProductsubcode03() {
        return productsubcode03;
    }

    public void setProductsubcode03(String productsubcode03) {
        this.productsubcode03 = productsubcode03;
    }

    @Column(name = "PRODUCTSUBCODE04", nullable = false, length = 10)
    @Id
    public String getProductsubcode04() {
        return productsubcode04;
    }

    public void setProductsubcode04(String productsubcode04) {
        this.productsubcode04 = productsubcode04;
    }

    @Column(name = "PRODUCTSUBCODE05", nullable = false, length = 10)
    @Id
    public String getProductsubcode05() {
        return productsubcode05;
    }

    public void setProductsubcode05(String productsubcode05) {
        this.productsubcode05 = productsubcode05;
    }

    @Column(name = "PRODUCTSUBCODE06", nullable = false, length = 10)
    @Id
    public String getProductsubcode06() {
        return productsubcode06;
    }

    public void setProductsubcode06(String productsubcode06) {
        this.productsubcode06 = productsubcode06;
    }

    @Column(name = "PRODUCTSUBCODE07", nullable = false, length = 10)
    @Id
    public String getProductsubcode07() {
        return productsubcode07;
    }

    public void setProductsubcode07(String productsubcode07) {
        this.productsubcode07 = productsubcode07;
    }

    @Column(name = "PRODUCTSUBCODE08", nullable = false, length = 10)
    @Id
    public String getProductsubcode08() {
        return productsubcode08;
    }

    public void setProductsubcode08(String productsubcode08) {
        this.productsubcode08 = productsubcode08;
    }

    public ProductspecializedsizeId() {
    }

    public ProductspecializedsizeId(String productcompanycode, String productitemtypecode, String productsubcode01, String productsubcode02, String productsubcode03, String productsubcode04, String productsubcode05, String productsubcode06, String productsubcode07, String productsubcode08) {
        this.productcompanycode = productcompanycode;
        this.productitemtypecode = productitemtypecode;
        this.productsubcode01 = productsubcode01;
        this.productsubcode02 = productsubcode02;
        this.productsubcode03 = productsubcode03;
        this.productsubcode04 = productsubcode04;
        this.productsubcode05 = productsubcode05;
        this.productsubcode06 = productsubcode06;
        this.productsubcode07 = productsubcode07;
        this.productsubcode08 = productsubcode08;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductspecializedsizeId that = (ProductspecializedsizeId) o;
        return Objects.equals(productcompanycode, that.productcompanycode) &&
            Objects.equals(productitemtypecode, that.productitemtypecode) &&
            Objects.equals(productsubcode01, that.productsubcode01) &&
            Objects.equals(productsubcode02, that.productsubcode02) &&
            Objects.equals(productsubcode03, that.productsubcode03) &&
            Objects.equals(productsubcode04, that.productsubcode04) &&
            Objects.equals(productsubcode05, that.productsubcode05) &&
            Objects.equals(productsubcode06, that.productsubcode06) &&
            Objects.equals(productsubcode07, that.productsubcode07) &&
            Objects.equals(productsubcode08, that.productsubcode08);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productcompanycode, productitemtypecode, productsubcode01, productsubcode02, productsubcode03, productsubcode04, productsubcode05, productsubcode06, productsubcode07, productsubcode08);
    }

    @Override
    public String toString() {
        return "ProductspecializedsizeId{" +
            "productcompanycode='" + productcompanycode + '\'' +
            ", productitemtypecode='" + productitemtypecode + '\'' +
            ", productsubcode01='" + productsubcode01 + '\'' +
            ", productsubcode02='" + productsubcode02 + '\'' +
            ", productsubcode03='" + productsubcode03 + '\'' +
            ", productsubcode04='" + productsubcode04 + '\'' +
            ", productsubcode05='" + productsubcode05 + '\'' +
            ", productsubcode06='" + productsubcode06 + '\'' +
            ", productsubcode07='" + productsubcode07 + '\'' +
            ", productsubcode08='" + productsubcode08 + '\'' +
            '}';
    }
}
