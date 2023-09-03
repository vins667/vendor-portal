package io.vamani.application.db2.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class DiCostanalysisId implements Serializable {
    private String projectcode;
    private String fatherproductcode;
    private String workattributes;
    private String itemnature;
    private String itemtypecode;
    private String itemsubcode01;
    private String itemsubcode02;
    private String itemsubcode03;
    private String itemsubcode04;
    private String itemsubcode05;
    private String itemsubcode06;
    private String itemsubcode07;
    private String itemsubcode08;
    private String itemsubcode09;
    private String itemsubcode10;

    @Basic
    @Column(name = "PROJECTCODE", nullable = true, length = 50)
    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @Basic
    @Column(name = "FATHERPRODUCTCODE", nullable = true, length = 3)
    public String getFatherproductcode() {
        return fatherproductcode;
    }

    public void setFatherproductcode(String fatherproductcode) {
        this.fatherproductcode = fatherproductcode != null ? fatherproductcode.trim() : "";
    }

    @Basic
    @Column(name = "WORKATTRIBUTES", nullable = true, length = 80)
    public String getWorkattributes() {
        return workattributes;
    }

    public void setWorkattributes(String workattributes) {
        this.workattributes = workattributes != null ? workattributes.trim() : "";
    }

    @Basic
    @Column(name = "ITEMNATURE", nullable = true, length = 1)
    public String getItemnature() {
        return itemnature;
    }

    public void setItemnature(String itemnature) {
        this.itemnature = itemnature != null ? itemnature.trim() : "";
    }

    @Basic
    @Column(name = "ITEMTYPECODE", nullable = true, length = 3)
    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode != null ? itemtypecode.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE01", nullable = true, length = 20)
    public String getItemsubcode01() {
        return itemsubcode01;
    }

    public void setItemsubcode01(String itemsubcode01) {
        this.itemsubcode01 = itemsubcode01 != null ? itemsubcode01.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE02", nullable = true, length = 10)
    public String getItemsubcode02() {
        return itemsubcode02;
    }

    public void setItemsubcode02(String itemsubcode02) {
        this.itemsubcode02 = itemsubcode02 != null ? itemsubcode02.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE03", nullable = true, length = 10)
    public String getItemsubcode03() {
        return itemsubcode03;
    }

    public void setItemsubcode03(String itemsubcode03) {
        this.itemsubcode03 = itemsubcode03 != null ? itemsubcode03.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE04", nullable = true, length = 10)
    public String getItemsubcode04() {
        return itemsubcode04;
    }

    public void setItemsubcode04(String itemsubcode04) {
        this.itemsubcode04 = itemsubcode04 != null ? itemsubcode04.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE05", nullable = true, length = 10)
    public String getItemsubcode05() {
        return itemsubcode05;
    }

    public void setItemsubcode05(String itemsubcode05) {
        this.itemsubcode05 = itemsubcode05 != null ? itemsubcode05.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE06", nullable = true, length = 10)
    public String getItemsubcode06() {
        return itemsubcode06;
    }

    public void setItemsubcode06(String itemsubcode06) {
        this.itemsubcode06 = itemsubcode06 != null ? itemsubcode06.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE07", nullable = true, length = 10)
    public String getItemsubcode07() {
        return itemsubcode07;
    }

    public void setItemsubcode07(String itemsubcode07) {
        this.itemsubcode07 = itemsubcode07 != null ? itemsubcode07.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE08", nullable = true, length = 10)
    public String getItemsubcode08() {
        return itemsubcode08;
    }

    public void setItemsubcode08(String itemsubcode08) {
        this.itemsubcode08 = itemsubcode08 != null ? itemsubcode08.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE09", nullable = true, length = 10)
    public String getItemsubcode09() {
        return itemsubcode09;
    }

    public void setItemsubcode09(String itemsubcode09) {
        this.itemsubcode09 = itemsubcode09 != null ? itemsubcode09.trim() : "";
    }

    @Basic
    @Column(name = "ITEMSUBCODE10", nullable = true, length = 10)
    public String getItemsubcode10() {
        return itemsubcode10;
    }

    public void setItemsubcode10(String itemsubcode10) {
        this.itemsubcode10 = itemsubcode10 != null ? itemsubcode10.trim() : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiCostanalysisId that = (DiCostanalysisId) o;
        return Objects.equals(projectcode, that.projectcode) && Objects.equals(fatherproductcode, that.fatherproductcode) && Objects.equals(workattributes, that.workattributes) && Objects.equals(itemnature, that.itemnature) && Objects.equals(itemtypecode, that.itemtypecode) && Objects.equals(itemsubcode01, that.itemsubcode01) && Objects.equals(itemsubcode02, that.itemsubcode02) && Objects.equals(itemsubcode03, that.itemsubcode03) && Objects.equals(itemsubcode04, that.itemsubcode04) && Objects.equals(itemsubcode05, that.itemsubcode05) && Objects.equals(itemsubcode06, that.itemsubcode06) && Objects.equals(itemsubcode07, that.itemsubcode07) && Objects.equals(itemsubcode08, that.itemsubcode08) && Objects.equals(itemsubcode09, that.itemsubcode09) && Objects.equals(itemsubcode10, that.itemsubcode10);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectcode, fatherproductcode, workattributes, itemnature, itemtypecode, itemsubcode01, itemsubcode02, itemsubcode03, itemsubcode04, itemsubcode05, itemsubcode06, itemsubcode07, itemsubcode08, itemsubcode09, itemsubcode10);
    }
}
