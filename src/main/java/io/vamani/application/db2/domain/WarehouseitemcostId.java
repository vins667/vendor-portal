package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class WarehouseitemcostId implements Serializable {
    private String companycode;
    private String warehouseaccountinggroupcode;
    private String itemtypeaficode;
    private String subcode01;
    private String subcode02;
    private String subcode03;
    private String subcode04;
    private String subcode05;
    private String subcode06;
    private String subcode07;
    private String subcode08;
    private String subcode09;
    private String subcode10;
    private BigInteger qualitylevelcode;
    private String statisticalgroupcode;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "WAREHOUSEACCOUNTINGGROUPCODE", nullable = false, length = 3)
    @Id
    public String getWarehouseaccountinggroupcode() {
        return warehouseaccountinggroupcode;
    }

    public void setWarehouseaccountinggroupcode(String warehouseaccountinggroupcode) {
        this.warehouseaccountinggroupcode = warehouseaccountinggroupcode;
    }

    @Column(name = "ITEMTYPEAFICODE", nullable = false, length = 3)
    @Id
    public String getItemtypeaficode() {
        return itemtypeaficode;
    }

    public void setItemtypeaficode(String itemtypeaficode) {
        this.itemtypeaficode = itemtypeaficode;
    }

    @Column(name = "SUBCODE01", nullable = false, length = 20)
    @Id
    public String getSubcode01() {
        return subcode01;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01;
    }

    @Column(name = "SUBCODE02", nullable = false, length = 10)
    @Id
    public String getSubcode02() {
        return subcode02;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02;
    }

    @Column(name = "SUBCODE03", nullable = false, length = 10)
    @Id
    public String getSubcode03() {
        return subcode03;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03;
    }

    @Column(name = "SUBCODE04", nullable = false, length = 10)
    @Id
    public String getSubcode04() {
        return subcode04;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04;
    }

    @Column(name = "SUBCODE05", nullable = false, length = 10)
    @Id
    public String getSubcode05() {
        return subcode05;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05;
    }

    @Column(name = "SUBCODE06", nullable = false, length = 10)
    @Id
    public String getSubcode06() {
        return subcode06;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06;
    }

    @Column(name = "SUBCODE07", nullable = false, length = 10)
    @Id
    public String getSubcode07() {
        return subcode07;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07;
    }

    @Column(name = "SUBCODE08", nullable = false, length = 10)
    @Id
    public String getSubcode08() {
        return subcode08;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08;
    }

    @Column(name = "SUBCODE09", nullable = false, length = 10)
    @Id
    public String getSubcode09() {
        return subcode09;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09;
    }

    @Column(name = "SUBCODE10", nullable = false, length = 10)
    @Id
    public String getSubcode10() {
        return subcode10;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10;
    }

    @Column(name = "QUALITYLEVELCODE", nullable = false, precision = 0)
    @Id
    public BigInteger getQualitylevelcode() {
        return qualitylevelcode;
    }

    public void setQualitylevelcode(BigInteger qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
    }

    @Column(name = "STATISTICALGROUPCODE", nullable = false, length = 6)
    @Id
    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseitemcostId that = (WarehouseitemcostId) o;
        return Objects.equals(companycode, that.companycode) &&
                Objects.equals(warehouseaccountinggroupcode, that.warehouseaccountinggroupcode) &&
                Objects.equals(itemtypeaficode, that.itemtypeaficode) &&
                Objects.equals(subcode01, that.subcode01) &&
                Objects.equals(subcode02, that.subcode02) &&
                Objects.equals(subcode03, that.subcode03) &&
                Objects.equals(subcode04, that.subcode04) &&
                Objects.equals(subcode05, that.subcode05) &&
                Objects.equals(subcode06, that.subcode06) &&
                Objects.equals(subcode07, that.subcode07) &&
                Objects.equals(subcode08, that.subcode08) &&
                Objects.equals(subcode09, that.subcode09) &&
                Objects.equals(subcode10, that.subcode10) &&
                Objects.equals(qualitylevelcode, that.qualitylevelcode) &&
                Objects.equals(statisticalgroupcode, that.statisticalgroupcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, warehouseaccountinggroupcode, itemtypeaficode, subcode01, subcode02, subcode03, subcode04, subcode05, subcode06, subcode07, subcode08, subcode09, subcode10, qualitylevelcode, statisticalgroupcode);
    }
}
