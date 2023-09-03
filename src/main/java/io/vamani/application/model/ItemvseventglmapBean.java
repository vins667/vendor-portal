package io.vamani.application.model;

import io.vamani.application.db2.domain.ItemvseventglmapId;

import java.sql.Date;
import java.sql.Timestamp;

public class ItemvseventglmapBean {
    private ItemvseventglmapId id;
    private String itemtypecompanycode;
    private String usergenericgrpnametypecode;
    private String logicalwarehousecompanycode;
    private String stocktrntemplatecompanycode;
    private String bookingfor;
    private String debitglcompanycode;
    private String debitglcode;
    private String debitgldescription;
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

    public ItemvseventglmapId getId() {
        return id;
    }

    public void setId(ItemvseventglmapId id) {
        this.id = id;
    }

    public String getItemtypecompanycode() {
        return itemtypecompanycode;
    }

    public void setItemtypecompanycode(String itemtypecompanycode) {
        this.itemtypecompanycode = itemtypecompanycode;
    }

    public String getUsergenericgrpnametypecode() {
        return usergenericgrpnametypecode;
    }

    public void setUsergenericgrpnametypecode(String usergenericgrpnametypecode) {
        this.usergenericgrpnametypecode = usergenericgrpnametypecode;
    }

    public String getLogicalwarehousecompanycode() {
        return logicalwarehousecompanycode;
    }

    public void setLogicalwarehousecompanycode(String logicalwarehousecompanycode) {
        this.logicalwarehousecompanycode = logicalwarehousecompanycode;
    }

    public String getStocktrntemplatecompanycode() {
        return stocktrntemplatecompanycode;
    }

    public void setStocktrntemplatecompanycode(String stocktrntemplatecompanycode) {
        this.stocktrntemplatecompanycode = stocktrntemplatecompanycode;
    }

    public String getBookingfor() {
        return bookingfor;
    }

    public void setBookingfor(String bookingfor) {
        this.bookingfor = bookingfor;
    }

    public String getDebitglcompanycode() {
        return debitglcompanycode;
    }

    public void setDebitglcompanycode(String debitglcompanycode) {
        this.debitglcompanycode = debitglcompanycode;
    }

    public String getDebitglcode() {
        return debitglcode;
    }

    public void setDebitglcode(String debitglcode) {
        this.debitglcode = debitglcode;
    }

    public String getDebitgldescription() {
        return debitgldescription;
    }

    public void setDebitgldescription(String debitgldescription) {
        this.debitgldescription = debitgldescription;
    }

    public String getCreditglcompanycode() {
        return creditglcompanycode;
    }

    public void setCreditglcompanycode(String creditglcompanycode) {
        this.creditglcompanycode = creditglcompanycode;
    }

    public String getCreditglcode() {
        return creditglcode;
    }

    public void setCreditglcode(String creditglcode) {
        this.creditglcode = creditglcode;
    }

    public String getDifferenceglcompanycode() {
        return differenceglcompanycode;
    }

    public void setDifferenceglcompanycode(String differenceglcompanycode) {
        this.differenceglcompanycode = differenceglcompanycode;
    }

    public String getDifferenceglcode() {
        return differenceglcode;
    }

    public void setDifferenceglcode(String differenceglcode) {
        this.differenceglcode = differenceglcode;
    }

    public Date getEffectivetodate() {
        return effectivetodate;
    }

    public void setEffectivetodate(Date effectivetodate) {
        this.effectivetodate = effectivetodate;
    }

    public Integer getPostingflag() {
        return postingflag;
    }

    public void setPostingflag(Integer postingflag) {
        this.postingflag = postingflag;
    }

    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    public Timestamp getCreationdatetimeutc() {
        return creationdatetimeutc;
    }

    public void setCreationdatetimeutc(Timestamp creationdatetimeutc) {
        this.creationdatetimeutc = creationdatetimeutc;
    }

    public Timestamp getLastupdatedatetimeutc() {
        return lastupdatedatetimeutc;
    }

    public void setLastupdatedatetimeutc(Timestamp lastupdatedatetimeutc) {
        this.lastupdatedatetimeutc = lastupdatedatetimeutc;
    }
}
