package io.vamani.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.domain.DirectBookingEntry;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class DirectBookingDetailsBean implements Serializable {
    private Long id;

    private String glcode;

    private String gldescription;

    private String itemtypecode;

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

    private String summerizeddescription;

    private String uom;

    private Double quantity;

    private Double price;

    private Double amount;

    private Double discount;

    private Double freight;

    private Double others;

    private Double taxablevalue;

    private Double cgstperc;

    private String cgsttaxcode;

    private String cgstglcode;

    private String cgstdesc;

    private Double cgstvalue;

    private Boolean mcgst;

    private Double sgstperc;

    private String sgsttaxcode;

    private String sgstglcode;

    private String sgstdesc;

    private Double sgstvalue;

    private Boolean msgst;

    private Double igstperc;

    private String igsttaxcode;

    private String igstglcode;

    private String igstdesc;

    private Double igstvalue;

    private Boolean migst;

    private Double gstperc;

    private Double gstvalue;

    private Double totalvalue;

    private String hsncode;

    private String copyFlag;

    private DirectBookingEntry directBookingEntry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSummerizeddescription() {
        return summerizeddescription;
    }

    public void setSummerizeddescription(String summerizeddescription) {
        this.summerizeddescription = summerizeddescription;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCgstperc() {
        return cgstperc;
    }

    public void setCgstperc(Double cgstperc) {
        this.cgstperc = cgstperc;
    }

    public String getCgsttaxcode() {
        return cgsttaxcode;
    }

    public void setCgsttaxcode(String cgsttaxcode) {
        this.cgsttaxcode = cgsttaxcode;
    }

    public String getCgstglcode() {
        return cgstglcode;
    }

    public void setCgstglcode(String cgstglcode) {
        this.cgstglcode = cgstglcode;
    }

    public String getCgstdesc() {
        return cgstdesc;
    }

    public void setCgstdesc(String cgstdesc) {
        this.cgstdesc = cgstdesc;
    }

    public Double getCgstvalue() {
        return cgstvalue;
    }

    public void setCgstvalue(Double cgstvalue) {
        this.cgstvalue = cgstvalue;
    }

    public Double getSgstperc() {
        return sgstperc;
    }

    public void setSgstperc(Double sgstperc) {
        this.sgstperc = sgstperc;
    }

    public String getSgsttaxcode() {
        return sgsttaxcode;
    }

    public void setSgsttaxcode(String sgsttaxcode) {
        this.sgsttaxcode = sgsttaxcode;
    }

    public String getSgstglcode() {
        return sgstglcode;
    }

    public void setSgstglcode(String sgstglcode) {
        this.sgstglcode = sgstglcode;
    }

    public String getSgstdesc() {
        return sgstdesc;
    }

    public void setSgstdesc(String sgstdesc) {
        this.sgstdesc = sgstdesc;
    }

    public Double getSgstvalue() {
        return sgstvalue;
    }

    public void setSgstvalue(Double sgstvalue) {
        this.sgstvalue = sgstvalue;
    }

    public Double getIgstperc() {
        return igstperc;
    }

    public void setIgstperc(Double igstperc) {
        this.igstperc = igstperc;
    }

    public String getIgsttaxcode() {
        return igsttaxcode;
    }

    public void setIgsttaxcode(String igsttaxcode) {
        this.igsttaxcode = igsttaxcode;
    }

    public String getIgstglcode() {
        return igstglcode;
    }

    public void setIgstglcode(String igstglcode) {
        this.igstglcode = igstglcode;
    }

    public String getIgstdesc() {
        return igstdesc;
    }

    public void setIgstdesc(String igstdesc) {
        this.igstdesc = igstdesc;
    }

    public Double getIgstvalue() {
        return igstvalue;
    }

    public void setIgstvalue(Double igstvalue) {
        this.igstvalue = igstvalue;
    }

    public Double getGstperc() {
        return gstperc;
    }

    public void setGstperc(Double gstperc) {
        this.gstperc = gstperc;
    }

    public Double getGstvalue() {
        return gstvalue;
    }

    public void setGstvalue(Double gstvalue) {
        this.gstvalue = gstvalue;
    }

    public Double getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(Double totalvalue) {
        this.totalvalue = totalvalue;
    }

    public String getHsncode() {
        return hsncode;
    }

    public void setHsncode(String hsncode) {
        this.hsncode = hsncode;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getGldescription() {
        return gldescription;
    }

    public void setGldescription(String gldescription) {
        this.gldescription = gldescription;
    }

    public DirectBookingEntry getDirectBookingEntry() {
        return directBookingEntry;
    }

    public void setDirectBookingEntry(DirectBookingEntry directBookingEntry) {
        this.directBookingEntry = directBookingEntry;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getOthers() {
        return others;
    }

    public void setOthers(Double others) {
        this.others = others;
    }

    public Double getTaxablevalue() {
        return taxablevalue;
    }

    public void setTaxablevalue(Double taxablevalue) {
        this.taxablevalue = taxablevalue;
    }

    public Boolean getMcgst() {
        return mcgst;
    }

    public void setMcgst(Boolean mcgst) {
        this.mcgst = mcgst;
    }

    public Boolean getMsgst() {
        return msgst;
    }

    public void setMsgst(Boolean msgst) {
        this.msgst = msgst;
    }

    public Boolean getMigst() {
        return migst;
    }

    public void setMigst(Boolean migst) {
        this.migst = migst;
    }

    public String getCopyFlag() {
        return copyFlag;
    }

    public void setCopyFlag(String copyFlag) {
        this.copyFlag = copyFlag;
    }
}
