package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DirectBookingDetails.
 */
@Entity
@Table(name = "direct_booking_details")
public class DirectBookingDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="directBookingDetailsSeq", sequenceName="direct_booking_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="directBookingDetailsSeq")
    private Long id;

    @Size(max = 20)
    @Column(name = "glcode", length = 20)
    private String glcode;

    @Size(max = 3)
    @Column(name = "itemtypecode", length = 3)
    private String itemtypecode;

    @Size(max = 20)
    @Column(name = "subcode_01", length = 20)
    private String subcode01;

    @Size(max = 10)
    @Column(name = "subcode_02", length = 10)
    private String subcode02;

    @Size(max = 10)
    @Column(name = "subcode_03", length = 10)
    private String subcode03;

    @Size(max = 10)
    @Column(name = "subcode_04", length = 10)
    private String subcode04;

    @Size(max = 10)
    @Column(name = "subcode_05", length = 10)
    private String subcode05;

    @Size(max = 10)
    @Column(name = "subcode_06", length = 10)
    private String subcode06;

    @Size(max = 10)
    @Column(name = "subcode_07", length = 10)
    private String subcode07;

    @Size(max = 10)
    @Column(name = "subcode_08", length = 10)
    private String subcode08;

    @Size(max = 10)
    @Column(name = "subcode_09", length = 10)
    private String subcode09;

    @Size(max = 10)
    @Column(name = "subcode_10", length = 10)
    private String subcode10;

    @Size(max = 200)
    @Column(name = "summerizeddescription", length = 200)
    private String summerizeddescription;

    @Size(max = 3)
    @Column(name = "uom", length = 3)
    private String uom;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "freight")
    private Double freight;

    @Column(name = "others")
    private Double others;

    @Column(name = "taxablevalue")
    private Double taxablevalue;

    @Column(name = "cgstperc")
    private Double cgstperc;

    @Size(max = 20)
    @Column(name = "cgsttaxcode", length = 20)
    private String cgsttaxcode;

    @Size(max = 20)
    @Column(name = "cgstglcode", length = 20)
    private String cgstglcode;

    @Size(max = 100)
    @Column(name = "cgstdesc", length = 100)
    private String cgstdesc;

    @Column(name = "cgstvalue")
    private Double cgstvalue;

    @Column(name = "mcgst")
    private Boolean mcgst;

    @Column(name = "sgstperc")
    private Double sgstperc;

    @Size(max = 20)
    @Column(name = "sgsttaxcode", length = 20)
    private String sgsttaxcode;

    @Size(max = 20)
    @Column(name = "sgstglcode", length = 20)
    private String sgstglcode;

    @Size(max = 100)
    @Column(name = "sgstdesc", length = 100)
    private String sgstdesc;

    @Column(name = "sgstvalue")
    private Double sgstvalue;

    @Column(name = "msgst")
    private Boolean msgst;

    @Column(name = "igstperc")
    private Double igstperc;

    @Size(max = 20)
    @Column(name = "igsttaxcode", length = 20)
    private String igsttaxcode;

    @Size(max = 20)
    @Column(name = "igstglcode", length = 20)
    private String igstglcode;

    @Size(max = 100)
    @Column(name = "igstdesc", length = 100)
    private String igstdesc;

    @Column(name = "igstvalue")
    private Double igstvalue;

    @Column(name = "migst")
    private Boolean migst;

    @Column(name = "gstperc")
    private Double gstperc;

    @Column(name = "gstvalue")
    private Double gstvalue;

    @Column(name = "totalvalue")
    private Double totalvalue;

    @Size(max = 20)
    @Column(name = "hsncode", length = 20)
    private String hsncode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("directBookingDetails")
    @JoinColumn(name = "direct_booking_entry_id")
    private DirectBookingEntry directBookingEntry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGlcode() {
        return glcode;
    }

    public DirectBookingDetails glcode(String glcode) {
        this.glcode = glcode;
        return this;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public DirectBookingDetails itemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
        return this;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public DirectBookingDetails subcode01(String subcode01) {
        this.subcode01 = subcode01;
        return this;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public DirectBookingDetails subcode02(String subcode02) {
        this.subcode02 = subcode02;
        return this;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public DirectBookingDetails subcode03(String subcode03) {
        this.subcode03 = subcode03;
        return this;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public DirectBookingDetails subcode04(String subcode04) {
        this.subcode04 = subcode04;
        return this;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public DirectBookingDetails subcode05(String subcode05) {
        this.subcode05 = subcode05;
        return this;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public DirectBookingDetails subcode06(String subcode06) {
        this.subcode06 = subcode06;
        return this;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public DirectBookingDetails subcode07(String subcode07) {
        this.subcode07 = subcode07;
        return this;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public DirectBookingDetails subcode08(String subcode08) {
        this.subcode08 = subcode08;
        return this;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public DirectBookingDetails subcode09(String subcode09) {
        this.subcode09 = subcode09;
        return this;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public DirectBookingDetails subcode10(String subcode10) {
        this.subcode10 = subcode10;
        return this;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10;
    }

    public String getSummerizeddescription() {
        return summerizeddescription;
    }

    public DirectBookingDetails summerizeddescription(String summerizeddescription) {
        this.summerizeddescription = summerizeddescription;
        return this;
    }

    public void setSummerizeddescription(String summerizeddescription) {
        this.summerizeddescription = summerizeddescription;
    }

    public String getUom() {
        return uom;
    }

    public DirectBookingDetails uom(String uom) {
        this.uom = uom;
        return this;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getQuantity() {
        return quantity;
    }

    public DirectBookingDetails quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public DirectBookingDetails price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public DirectBookingDetails amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCgstperc() {
        return cgstperc;
    }

    public DirectBookingDetails cgstperc(Double cgstperc) {
        this.cgstperc = cgstperc;
        return this;
    }

    public void setCgstperc(Double cgstperc) {
        this.cgstperc = cgstperc;
    }

    public String getCgsttaxcode() {
        return cgsttaxcode;
    }

    public DirectBookingDetails cgsttaxcode(String cgsttaxcode) {
        this.cgsttaxcode = cgsttaxcode;
        return this;
    }

    public void setCgsttaxcode(String cgsttaxcode) {
        this.cgsttaxcode = cgsttaxcode;
    }

    public String getCgstglcode() {
        return cgstglcode;
    }

    public DirectBookingDetails cgstglcode(String cgstglcode) {
        this.cgstglcode = cgstglcode;
        return this;
    }

    public void setCgstglcode(String cgstglcode) {
        this.cgstglcode = cgstglcode;
    }

    public String getCgstdesc() {
        return cgstdesc;
    }

    public DirectBookingDetails cgstdesc(String cgstdesc) {
        this.cgstdesc = cgstdesc;
        return this;
    }

    public void setCgstdesc(String cgstdesc) {
        this.cgstdesc = cgstdesc;
    }

    public Double getCgstvalue() {
        return cgstvalue;
    }

    public DirectBookingDetails cgstvalue(Double cgstvalue) {
        this.cgstvalue = cgstvalue;
        return this;
    }

    public void setCgstvalue(Double cgstvalue) {
        this.cgstvalue = cgstvalue;
    }

    public Boolean getMcgst() {
        return mcgst;
    }

    public void setMcgst(Boolean mcgst) {
        this.mcgst = mcgst;
    }

    public Double getSgstperc() {
        return sgstperc;
    }

    public DirectBookingDetails sgstperc(Double sgstperc) {
        this.sgstperc = sgstperc;
        return this;
    }

    public void setSgstperc(Double sgstperc) {
        this.sgstperc = sgstperc;
    }

    public String getSgsttaxcode() {
        return sgsttaxcode;
    }

    public DirectBookingDetails sgsttaxcode(String sgsttaxcode) {
        this.sgsttaxcode = sgsttaxcode;
        return this;
    }

    public void setSgsttaxcode(String sgsttaxcode) {
        this.sgsttaxcode = sgsttaxcode;
    }

    public String getSgstglcode() {
        return sgstglcode;
    }

    public DirectBookingDetails sgstglcode(String sgstglcode) {
        this.sgstglcode = sgstglcode;
        return this;
    }

    public void setSgstglcode(String sgstglcode) {
        this.sgstglcode = sgstglcode;
    }

    public String getSgstdesc() {
        return sgstdesc;
    }

    public DirectBookingDetails sgstdesc(String sgstdesc) {
        this.sgstdesc = sgstdesc;
        return this;
    }

    public void setSgstdesc(String sgstdesc) {
        this.sgstdesc = sgstdesc;
    }

    public Double getSgstvalue() {
        return sgstvalue;
    }

    public DirectBookingDetails sgstvalue(Double sgstvalue) {
        this.sgstvalue = sgstvalue;
        return this;
    }

    public void setSgstvalue(Double sgstvalue) {
        this.sgstvalue = sgstvalue;
    }

    public Double getIgstperc() {
        return igstperc;
    }

    public Boolean getMsgst() {
        return msgst;
    }

    public void setMsgst(Boolean msgst) {
        this.msgst = msgst;
    }

    public DirectBookingDetails igstperc(Double igstperc) {
        this.igstperc = igstperc;
        return this;
    }

    public void setIgstperc(Double igstperc) {
        this.igstperc = igstperc;
    }

    public String getIgsttaxcode() {
        return igsttaxcode;
    }

    public DirectBookingDetails igsttaxcode(String igsttaxcode) {
        this.igsttaxcode = igsttaxcode;
        return this;
    }

    public void setIgsttaxcode(String igsttaxcode) {
        this.igsttaxcode = igsttaxcode;
    }

    public String getIgstglcode() {
        return igstglcode;
    }

    public DirectBookingDetails igstglcode(String igstglcode) {
        this.igstglcode = igstglcode;
        return this;
    }

    public void setIgstglcode(String igstglcode) {
        this.igstglcode = igstglcode;
    }

    public String getIgstdesc() {
        return igstdesc;
    }

    public DirectBookingDetails igstdesc(String igstdesc) {
        this.igstdesc = igstdesc;
        return this;
    }

    public void setIgstdesc(String igstdesc) {
        this.igstdesc = igstdesc;
    }

    public Double getIgstvalue() {
        return igstvalue;
    }

    public DirectBookingDetails igstvalue(Double igstvalue) {
        this.igstvalue = igstvalue;
        return this;
    }

    public void setIgstvalue(Double igstvalue) {
        this.igstvalue = igstvalue;
    }

    public Boolean getMigst() {
        return migst;
    }

    public void setMigst(Boolean migst) {
        this.migst = migst;
    }

    public Double getGstperc() {
        return gstperc;
    }

    public DirectBookingDetails gstperc(Double gstperc) {
        this.gstperc = gstperc;
        return this;
    }

    public void setGstperc(Double gstperc) {
        this.gstperc = gstperc;
    }

    public Double getGstvalue() {
        return gstvalue;
    }

    public DirectBookingDetails gstvalue(Double gstvalue) {
        this.gstvalue = gstvalue;
        return this;
    }

    public void setGstvalue(Double gstvalue) {
        this.gstvalue = gstvalue;
    }

    public Double getTotalvalue() {
        return totalvalue;
    }

    public DirectBookingDetails totalvalue(Double totalvalue) {
        this.totalvalue = totalvalue;
        return this;
    }

    public void setTotalvalue(Double totalvalue) {
        this.totalvalue = totalvalue;
    }

    public String getHsncode() {
        return hsncode;
    }

    public DirectBookingDetails hsncode(String hsncode) {
        this.hsncode = hsncode;
        return this;
    }

    public void setHsncode(String hsncode) {
        this.hsncode = hsncode;
    }

    public DirectBookingEntry getDirectBookingEntry() {
        return directBookingEntry;
    }

    public DirectBookingDetails directBookingEntry(DirectBookingEntry directBookingEntry) {
        this.directBookingEntry = directBookingEntry;
        return this;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirectBookingDetails)) {
            return false;
        }
        return id != null && id.equals(((DirectBookingDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DirectBookingDetails{" +
            "id=" + getId() +
            ", itemtypecode='" + getItemtypecode() + "'" +
            ", subcode01='" + getSubcode01() + "'" +
            ", subcode02='" + getSubcode02() + "'" +
            ", subcode03='" + getSubcode03() + "'" +
            ", subcode04='" + getSubcode04() + "'" +
            ", subcode05='" + getSubcode05() + "'" +
            ", subcode06='" + getSubcode06() + "'" +
            ", subcode07='" + getSubcode07() + "'" +
            ", subcode08='" + getSubcode08() + "'" +
            ", subcode09='" + getSubcode09() + "'" +
            ", subcode10='" + getSubcode10() + "'" +
            ", summerizeddescription='" + getSummerizeddescription() + "'" +
            ", uom='" + getUom() + "'" +
            ", quantity=" + getQuantity() +
            ", price=" + getPrice() +
            ", amount=" + getAmount() +
            ", cgstperc=" + getCgstperc() +
            ", cgsttaxcode='" + getCgsttaxcode() + "'" +
            ", cgstglcode='" + getCgstglcode() + "'" +
            ", cgstdesc='" + getCgstdesc() + "'" +
            ", cgstvalue=" + getCgstvalue() +
            ", sgstperc=" + getSgstperc() +
            ", sgsttaxcode='" + getSgsttaxcode() + "'" +
            ", sgstglcode='" + getSgstglcode() + "'" +
            ", sgstdesc='" + getSgstdesc() + "'" +
            ", sgstvalue=" + getSgstvalue() +
            ", igstperc=" + getIgstperc() +
            ", igsttaxcode='" + getIgsttaxcode() + "'" +
            ", igstglcode='" + getIgstglcode() + "'" +
            ", igstdesc='" + getIgstdesc() + "'" +
            ", igstvalue=" + getIgstvalue() +
            ", gstperc=" + getGstperc() +
            ", gstvalue=" + getGstvalue() +
            ", totalvalue=" + getTotalvalue() +
            ", hsncode='" + getHsncode() + "'" +
            "}";
    }
}
