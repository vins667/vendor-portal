package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fullitemkeydecoder")
public class Fullitemkeydecoder {
    @EmbeddedId
    private FullitemkeydecoderId id;
    private String divisioncode;
    private String alloweddivisions;
    private String itemtypecompanycode;
    private Integer identifier;
    private String summarizeddescription;
    private String shortdescription;
    private String searchdescription;
    private String itemcode;
    private String externalcode;
    private String bartypecode;
    private String barcode;
    private String owningcompanycode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private String ordersubcode01;
    private String ordersubcode02;
    private String ordersubcode03;
    private String ordersubcode04;
    private String ordersubcode05;
    private String ordersubcode06;
    private String ordersubcode07;
    private String ordersubcode08;
    private String ordersubcode09;
    private String ordersubcode10;
    private String subcode01Description;
    private String subcode02Description;
    private String subcode03Description;
    private String subcode04Description;
    private String subcode05Description;
    private String subcode06Description;
    private String subcode07Description;
    private String subcode08Description;
    private String subcode09Description;
    private String subcode10Description;
    private String orderitemcode;
    private BigDecimal maxlaylength;
    private Integer maxnolayers;
    private BigDecimal widthrangefrom;
    private BigDecimal widthrangeto;
    private BigDecimal gsmrangefrom;
    private BigDecimal gsmrangeto;
    private BigDecimal shrinkage;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;
    private Long itemuniqueid;

    public FullitemkeydecoderId getId() {
        return id;
    }

    public void setId(FullitemkeydecoderId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DIVISIONCODE", nullable = true, length = 3)
    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    @Basic
    @Column(name = "ALLOWEDDIVISIONS", nullable = true, length = 90)
    public String getAlloweddivisions() {
        return alloweddivisions;
    }

    public void setAlloweddivisions(String alloweddivisions) {
        this.alloweddivisions = alloweddivisions;
    }

    @Basic
    @Column(name = "ITEMTYPECOMPANYCODE", nullable = false, length = 3)
    public String getItemtypecompanycode() {
        return itemtypecompanycode;
    }

    public void setItemtypecompanycode(String itemtypecompanycode) {
        this.itemtypecompanycode = itemtypecompanycode;
    }

    @Basic
    @Column(name = "IDENTIFIER", nullable = false, precision = 0)
    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    @Basic
    @Column(name = "SUMMARIZEDDESCRIPTION", nullable = true, length = 200)
    public String getSummarizeddescription() {
        return summarizeddescription != null ? summarizeddescription.trim() : summarizeddescription;
    }

    public void setSummarizeddescription(String summarizeddescription) {
        this.summarizeddescription = summarizeddescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 80)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 120)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    @Basic
    @Column(name = "ITEMCODE", nullable = true, length = 120)
    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    @Basic
    @Column(name = "EXTERNALCODE", nullable = true, length = 30)
    public String getExternalcode() {
        return externalcode;
    }

    public void setExternalcode(String externalcode) {
        this.externalcode = externalcode;
    }

    @Basic
    @Column(name = "BARTYPECODE", nullable = true, length = 2)
    public String getBartypecode() {
        return bartypecode;
    }

    public void setBartypecode(String bartypecode) {
        this.bartypecode = bartypecode;
    }

    @Basic
    @Column(name = "BARCODE", nullable = true, length = 50)
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Basic
    @Column(name = "OWNINGCOMPANYCODE", nullable = true, length = 3)
    public String getOwningcompanycode() {
        return owningcompanycode;
    }

    public void setOwningcompanycode(String owningcompanycode) {
        this.owningcompanycode = owningcompanycode;
    }

    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    @Basic
    @Column(name = "ORDERSUBCODE01", nullable = false, length = 20)
    public String getOrdersubcode01() {
        return ordersubcode01;
    }

    public void setOrdersubcode01(String ordersubcode01) {
        this.ordersubcode01 = ordersubcode01;
    }

    @Basic
    @Column(name = "ORDERSUBCODE02", nullable = true, length = 10)
    public String getOrdersubcode02() {
        return ordersubcode02;
    }

    public void setOrdersubcode02(String ordersubcode02) {
        this.ordersubcode02 = ordersubcode02;
    }

    @Basic
    @Column(name = "ORDERSUBCODE03", nullable = true, length = 10)
    public String getOrdersubcode03() {
        return ordersubcode03;
    }

    public void setOrdersubcode03(String ordersubcode03) {
        this.ordersubcode03 = ordersubcode03;
    }

    @Basic
    @Column(name = "ORDERSUBCODE04", nullable = true, length = 10)
    public String getOrdersubcode04() {
        return ordersubcode04;
    }

    public void setOrdersubcode04(String ordersubcode04) {
        this.ordersubcode04 = ordersubcode04;
    }

    @Basic
    @Column(name = "ORDERSUBCODE05", nullable = true, length = 10)
    public String getOrdersubcode05() {
        return ordersubcode05;
    }

    public void setOrdersubcode05(String ordersubcode05) {
        this.ordersubcode05 = ordersubcode05;
    }

    @Basic
    @Column(name = "ORDERSUBCODE06", nullable = true, length = 10)
    public String getOrdersubcode06() {
        return ordersubcode06;
    }

    public void setOrdersubcode06(String ordersubcode06) {
        this.ordersubcode06 = ordersubcode06;
    }

    @Basic
    @Column(name = "ORDERSUBCODE07", nullable = true, length = 10)
    public String getOrdersubcode07() {
        return ordersubcode07;
    }

    public void setOrdersubcode07(String ordersubcode07) {
        this.ordersubcode07 = ordersubcode07;
    }

    @Basic
    @Column(name = "ORDERSUBCODE08", nullable = true, length = 10)
    public String getOrdersubcode08() {
        return ordersubcode08;
    }

    public void setOrdersubcode08(String ordersubcode08) {
        this.ordersubcode08 = ordersubcode08;
    }

    @Basic
    @Column(name = "ORDERSUBCODE09", nullable = true, length = 10)
    public String getOrdersubcode09() {
        return ordersubcode09;
    }

    public void setOrdersubcode09(String ordersubcode09) {
        this.ordersubcode09 = ordersubcode09;
    }

    @Basic
    @Column(name = "ORDERSUBCODE10", nullable = true, length = 10)
    public String getOrdersubcode10() {
        return ordersubcode10;
    }

    public void setOrdersubcode10(String ordersubcode10) {
        this.ordersubcode10 = ordersubcode10;
    }

    @Basic
    @Column(name = "SUBCODE01DESCRIPTION", nullable = true, length = 120)
    public String getSubcode01Description() {
        return subcode01Description;
    }

    public void setSubcode01Description(String subcode01Description) {
        this.subcode01Description = subcode01Description;
    }

    @Basic
    @Column(name = "SUBCODE02DESCRIPTION", nullable = true, length = 120)
    public String getSubcode02Description() {
        return subcode02Description;
    }

    public void setSubcode02Description(String subcode02Description) {
        this.subcode02Description = subcode02Description;
    }

    @Basic
    @Column(name = "SUBCODE03DESCRIPTION", nullable = true, length = 120)
    public String getSubcode03Description() {
        return subcode03Description;
    }

    public void setSubcode03Description(String subcode03Description) {
        this.subcode03Description = subcode03Description;
    }

    @Basic
    @Column(name = "SUBCODE04DESCRIPTION", nullable = true, length = 120)
    public String getSubcode04Description() {
        return subcode04Description;
    }

    public void setSubcode04Description(String subcode04Description) {
        this.subcode04Description = subcode04Description;
    }

    @Basic
    @Column(name = "SUBCODE05DESCRIPTION", nullable = true, length = 120)
    public String getSubcode05Description() {
        return subcode05Description;
    }

    public void setSubcode05Description(String subcode05Description) {
        this.subcode05Description = subcode05Description;
    }

    @Basic
    @Column(name = "SUBCODE06DESCRIPTION", nullable = true, length = 120)
    public String getSubcode06Description() {
        return subcode06Description;
    }

    public void setSubcode06Description(String subcode06Description) {
        this.subcode06Description = subcode06Description;
    }

    @Basic
    @Column(name = "SUBCODE07DESCRIPTION", nullable = true, length = 120)
    public String getSubcode07Description() {
        return subcode07Description;
    }

    public void setSubcode07Description(String subcode07Description) {
        this.subcode07Description = subcode07Description;
    }

    @Basic
    @Column(name = "SUBCODE08DESCRIPTION", nullable = true, length = 120)
    public String getSubcode08Description() {
        return subcode08Description;
    }

    public void setSubcode08Description(String subcode08Description) {
        this.subcode08Description = subcode08Description;
    }

    @Basic
    @Column(name = "SUBCODE09DESCRIPTION", nullable = true, length = 120)
    public String getSubcode09Description() {
        return subcode09Description;
    }

    public void setSubcode09Description(String subcode09Description) {
        this.subcode09Description = subcode09Description;
    }

    @Basic
    @Column(name = "SUBCODE10DESCRIPTION", nullable = true, length = 120)
    public String getSubcode10Description() {
        return subcode10Description;
    }

    public void setSubcode10Description(String subcode10Description) {
        this.subcode10Description = subcode10Description;
    }

    @Basic
    @Column(name = "ORDERITEMCODE", nullable = true, length = 120)
    public String getOrderitemcode() {
        return orderitemcode;
    }

    public void setOrderitemcode(String orderitemcode) {
        this.orderitemcode = orderitemcode;
    }

    @Basic
    @Column(name = "MAXLAYLENGTH", nullable = true, precision = 2)
    public BigDecimal getMaxlaylength() {
        return maxlaylength;
    }

    public void setMaxlaylength(BigDecimal maxlaylength) {
        this.maxlaylength = maxlaylength;
    }

    @Basic
    @Column(name = "MAXNOLAYERS", nullable = false)
    public Integer getMaxnolayers() {
        return maxnolayers;
    }

    public void setMaxnolayers(Integer maxnolayers) {
        this.maxnolayers = maxnolayers;
    }

    @Basic
    @Column(name = "WIDTHRANGEFROM", nullable = true, precision = 2)
    public BigDecimal getWidthrangefrom() {
        return widthrangefrom;
    }

    public void setWidthrangefrom(BigDecimal widthrangefrom) {
        this.widthrangefrom = widthrangefrom;
    }

    @Basic
    @Column(name = "WIDTHRANGETO", nullable = true, precision = 2)
    public BigDecimal getWidthrangeto() {
        return widthrangeto;
    }

    public void setWidthrangeto(BigDecimal widthrangeto) {
        this.widthrangeto = widthrangeto;
    }

    @Basic
    @Column(name = "GSMRANGEFROM", nullable = true, precision = 2)
    public BigDecimal getGsmrangefrom() {
        return gsmrangefrom;
    }

    public void setGsmrangefrom(BigDecimal gsmrangefrom) {
        this.gsmrangefrom = gsmrangefrom;
    }

    @Basic
    @Column(name = "GSMRANGETO", nullable = true, precision = 2)
    public BigDecimal getGsmrangeto() {
        return gsmrangeto;
    }

    public void setGsmrangeto(BigDecimal gsmrangeto) {
        this.gsmrangeto = gsmrangeto;
    }

    @Basic
    @Column(name = "SHRINKAGE", nullable = true, precision = 2)
    public BigDecimal getShrinkage() {
        return shrinkage;
    }

    public void setShrinkage(BigDecimal shrinkage) {
        this.shrinkage = shrinkage;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Basic
    @Column(name = "CREATIONDATETIMEUTC", nullable = true)
    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }

    @Basic
    @Column(name = "ITEMUNIQUEID", nullable = false)
    public Long getItemuniqueid() {
        return itemuniqueid;
    }

    public void setItemuniqueid(Long itemuniqueid) {
        this.itemuniqueid = itemuniqueid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fullitemkeydecoder that = (Fullitemkeydecoder) o;
        return Objects.equals(id, that.id) && Objects.equals(divisioncode, that.divisioncode) && Objects.equals(alloweddivisions, that.alloweddivisions) && Objects.equals(itemtypecompanycode, that.itemtypecompanycode) && Objects.equals(identifier, that.identifier) && Objects.equals(summarizeddescription, that.summarizeddescription) && Objects.equals(shortdescription, that.shortdescription) && Objects.equals(searchdescription, that.searchdescription) && Objects.equals(itemcode, that.itemcode) && Objects.equals(externalcode, that.externalcode) && Objects.equals(bartypecode, that.bartypecode) && Objects.equals(barcode, that.barcode) && Objects.equals(owningcompanycode, that.owningcompanycode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(ordersubcode01, that.ordersubcode01) && Objects.equals(ordersubcode02, that.ordersubcode02) && Objects.equals(ordersubcode03, that.ordersubcode03) && Objects.equals(ordersubcode04, that.ordersubcode04) && Objects.equals(ordersubcode05, that.ordersubcode05) && Objects.equals(ordersubcode06, that.ordersubcode06) && Objects.equals(ordersubcode07, that.ordersubcode07) && Objects.equals(ordersubcode08, that.ordersubcode08) && Objects.equals(ordersubcode09, that.ordersubcode09) && Objects.equals(ordersubcode10, that.ordersubcode10) && Objects.equals(subcode01Description, that.subcode01Description) && Objects.equals(subcode02Description, that.subcode02Description) && Objects.equals(subcode03Description, that.subcode03Description) && Objects.equals(subcode04Description, that.subcode04Description) && Objects.equals(subcode05Description, that.subcode05Description) && Objects.equals(subcode06Description, that.subcode06Description) && Objects.equals(subcode07Description, that.subcode07Description) && Objects.equals(subcode08Description, that.subcode08Description) && Objects.equals(subcode09Description, that.subcode09Description) && Objects.equals(subcode10Description, that.subcode10Description) && Objects.equals(orderitemcode, that.orderitemcode) && Objects.equals(maxlaylength, that.maxlaylength) && Objects.equals(maxnolayers, that.maxnolayers) && Objects.equals(widthrangefrom, that.widthrangefrom) && Objects.equals(widthrangeto, that.widthrangeto) && Objects.equals(gsmrangefrom, that.gsmrangefrom) && Objects.equals(gsmrangeto, that.gsmrangeto) && Objects.equals(shrinkage, that.shrinkage) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc) && Objects.equals(itemuniqueid, that.itemuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, divisioncode, alloweddivisions, itemtypecompanycode, identifier, summarizeddescription, shortdescription, searchdescription, itemcode, externalcode, bartypecode, barcode, owningcompanycode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, ordersubcode01, ordersubcode02, ordersubcode03, ordersubcode04, ordersubcode05, ordersubcode06, ordersubcode07, ordersubcode08, ordersubcode09, ordersubcode10, subcode01Description, subcode02Description, subcode03Description, subcode04Description, subcode05Description, subcode06Description, subcode07Description, subcode08Description, subcode09Description, subcode10Description, orderitemcode, maxlaylength, maxnolayers, widthrangefrom, widthrangeto, gsmrangefrom, gsmrangeto, shrinkage, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc, itemuniqueid);
    }
}
