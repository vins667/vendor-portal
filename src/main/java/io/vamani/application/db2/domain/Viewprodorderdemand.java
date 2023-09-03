package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "viewprodorderdemand")
public class Viewprodorderdemand {
    @EmbeddedId
    private ViewprodorderdemandId id;

    @Basic
    @Column(name = "PODATE", nullable = true)
    private Date podate;

    @Basic
    @Column(name = "PDDATE", nullable = false)
    private Date pddate;
    @Basic
    @Column(name = "ITEMTYPECODE", nullable = true, length = 3)
    private String itemtypecode;
    @Basic
    @Column(name = "SUBCODE01", nullable = true, length = 20)
    private String subcode01;
    @Basic
    @Column(name = "SUBCODE02", nullable = true, length = 10)
    private String subcode02;
    @Basic
    @Column(name = "SUBCODE03", nullable = true, length = 10)
    private String subcode03;
    @Basic
    @Column(name = "SUBCODE04", nullable = true, length = 10)
    private String subcode04;
    @Basic
    @Column(name = "SUBCODE05", nullable = true, length = 10)
    private String subcode05;
    @Basic
    @Column(name = "SUBCODE06", nullable = true, length = 10)
    private String subcode06;
    @Basic
    @Column(name = "SUBCODE07", nullable = true, length = 10)
    private String subcode07;
    @Basic
    @Column(name = "SUBCODE08", nullable = true, length = 10)
    private String subcode08;
    @Basic
    @Column(name = "SUBCODE09", nullable = true, length = 10)
    private String subcode09;
    @Basic
    @Column(name = "SUBCODE10", nullable = true, length = 10)
    private String subcode10;
    @Basic
    @Column(name = "QUANTITY", nullable = true, precision = 5)
    private BigDecimal quantity;
    @Basic
    @Column(name = "UOMCODE", nullable = true, length = 3)
    private String uomcode;

    public ViewprodorderdemandId getId() {
        return id;
    }

    public void setId(ViewprodorderdemandId id) {
        this.id = id;
    }

    public Date getPodate() {
        return podate;
    }

    public void setPodate(Date podate) {
        this.podate = podate;
    }

    public Date getPddate() {
        return pddate;
    }

    public void setPddate(Date pddate) {
        this.pddate = pddate;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUomcode() {
        return uomcode;
    }

    public void setUomcode(String uomcode) {
        this.uomcode = uomcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viewprodorderdemand that = (Viewprodorderdemand) o;
        return Objects.equals(id, that.id) && Objects.equals(podate, that.podate) && Objects.equals(pddate, that.pddate) && Objects.equals(itemtypecode, that.itemtypecode) && Objects.equals(subcode01, that.subcode01) && Objects.equals(subcode02, that.subcode02) && Objects.equals(subcode03, that.subcode03) && Objects.equals(subcode04, that.subcode04) && Objects.equals(subcode05, that.subcode05) && Objects.equals(subcode06, that.subcode06) && Objects.equals(subcode07, that.subcode07) && Objects.equals(subcode08, that.subcode08) && Objects.equals(subcode09, that.subcode09) && Objects.equals(subcode10, that.subcode10) && Objects.equals(quantity, that.quantity) && Objects.equals(uomcode, that.uomcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, podate, pddate, itemtypecode, subcode01, subcode02, subcode03, subcode04, subcode05, subcode06, subcode07, subcode08, subcode09, subcode10, quantity, uomcode);
    }
}
