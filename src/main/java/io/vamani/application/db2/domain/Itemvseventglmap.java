package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "itemvseventglmap")
public class Itemvseventglmap {
    @EmbeddedId
    private ItemvseventglmapId id;
    private String itemtypecompanycode;
    private String usergenericgrpnametypecode;
    private String logicalwarehousecompanycode;
    private String stocktrntemplatecompanycode;
    private String bookingfor;
    private String debitglcompanycode;
    private String debitglcode;
    private String creditglcompanycode;
    private String creditglcode;
    private String differenceglcompanycode;
    private String differenceglcode;
    private Date effectivetodate;
    private Integer postingflag;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;

    @Basic
    @Column(name = "ITEMTYPECOMPANYCODE", nullable = false, length = 3)
    public String getItemtypecompanycode() {
        return itemtypecompanycode;
    }

    public void setItemtypecompanycode(String itemtypecompanycode) {
        this.itemtypecompanycode = itemtypecompanycode;
    }

    @Basic
    @Column(name = "USERGENERICGRPNAMETYPECODE", nullable = false, length = 3)
    public String getUsergenericgrpnametypecode() {
        return usergenericgrpnametypecode;
    }

    public void setUsergenericgrpnametypecode(String usergenericgrpnametypecode) {
        this.usergenericgrpnametypecode = usergenericgrpnametypecode;
    }

    @Basic
    @Column(name = "LOGICALWAREHOUSECOMPANYCODE", nullable = false, length = 3)
    public String getLogicalwarehousecompanycode() {
        return logicalwarehousecompanycode;
    }

    public void setLogicalwarehousecompanycode(String logicalwarehousecompanycode) {
        this.logicalwarehousecompanycode = logicalwarehousecompanycode;
    }

    @Basic
    @Column(name = "STOCKTRNTEMPLATECOMPANYCODE", nullable = false, length = 3)
    public String getStocktrntemplatecompanycode() {
        return stocktrntemplatecompanycode;
    }

    public void setStocktrntemplatecompanycode(String stocktrntemplatecompanycode) {
        this.stocktrntemplatecompanycode = stocktrntemplatecompanycode;
    }

    @Basic
    @Column(name = "BOOKINGFOR", nullable = true, length = 1)
    public String getBookingfor() {
        return bookingfor;
    }

    public void setBookingfor(String bookingfor) {
        this.bookingfor = bookingfor;
    }

    @Basic
    @Column(name = "DEBITGLCOMPANYCODE", nullable = true, length = 3)
    public String getDebitglcompanycode() {
        return debitglcompanycode;
    }

    public void setDebitglcompanycode(String debitglcompanycode) {
        this.debitglcompanycode = debitglcompanycode;
    }

    @Basic
    @Column(name = "DEBITGLCODE", nullable = true, length = 20)
    public String getDebitglcode() {
        return debitglcode;
    }

    public void setDebitglcode(String debitglcode) {
        this.debitglcode = debitglcode;
    }

    @Basic
    @Column(name = "CREDITGLCOMPANYCODE", nullable = true, length = 3)
    public String getCreditglcompanycode() {
        return creditglcompanycode;
    }

    public void setCreditglcompanycode(String creditglcompanycode) {
        this.creditglcompanycode = creditglcompanycode;
    }

    @Basic
    @Column(name = "CREDITGLCODE", nullable = true, length = 20)
    public String getCreditglcode() {
        return creditglcode;
    }

    public void setCreditglcode(String creditglcode) {
        this.creditglcode = creditglcode;
    }

    @Basic
    @Column(name = "DIFFERENCEGLCOMPANYCODE", nullable = true, length = 3)
    public String getDifferenceglcompanycode() {
        return differenceglcompanycode;
    }

    public void setDifferenceglcompanycode(String differenceglcompanycode) {
        this.differenceglcompanycode = differenceglcompanycode;
    }

    @Basic
    @Column(name = "DIFFERENCEGLCODE", nullable = true, length = 20)
    public String getDifferenceglcode() {
        return differenceglcode;
    }

    public void setDifferenceglcode(String differenceglcode) {
        this.differenceglcode = differenceglcode;
    }

    @Basic
    @Column(name = "EFFECTIVETODATE", nullable = true)
    public Date getEffectivetodate() {
        return effectivetodate;
    }

    public void setEffectivetodate(Date effectivetodate) {
        this.effectivetodate = effectivetodate;
    }

    @Basic
    @Column(name = "POSTINGFLAG", nullable = false)
    public Integer getPostingflag() {
        return postingflag;
    }

    public void setPostingflag(Integer postingflag) {
        this.postingflag = postingflag;
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

    public ItemvseventglmapId getId() {
        return id;
    }

    public void setId(ItemvseventglmapId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itemvseventglmap that = (Itemvseventglmap) o;
        return Objects.equals(id, that.id) && Objects.equals(itemtypecompanycode, that.itemtypecompanycode) && Objects.equals(usergenericgrpnametypecode, that.usergenericgrpnametypecode) && Objects.equals(logicalwarehousecompanycode, that.logicalwarehousecompanycode) && Objects.equals(stocktrntemplatecompanycode, that.stocktrntemplatecompanycode) && Objects.equals(bookingfor, that.bookingfor) && Objects.equals(debitglcompanycode, that.debitglcompanycode) && Objects.equals(debitglcode, that.debitglcode) && Objects.equals(creditglcompanycode, that.creditglcompanycode) && Objects.equals(creditglcode, that.creditglcode) && Objects.equals(differenceglcompanycode, that.differenceglcompanycode) && Objects.equals(differenceglcode, that.differenceglcode) && Objects.equals(effectivetodate, that.effectivetodate) && Objects.equals(postingflag, that.postingflag) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemtypecompanycode, usergenericgrpnametypecode, logicalwarehousecompanycode, stocktrntemplatecompanycode, bookingfor, debitglcompanycode, debitglcode, creditglcompanycode, creditglcode, differenceglcompanycode, differenceglcode, effectivetodate, postingflag, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
