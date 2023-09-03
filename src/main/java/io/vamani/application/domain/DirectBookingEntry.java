package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A DirectBookingEntry.
 */
@Entity
@Table(name = "direct_booking_entry")
public class DirectBookingEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="directBookingEntrySeq", sequenceName="direct_booking_entry_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="directBookingEntrySeq")
    private Long id;

    @NotNull
    @Size(max = 3)
    @Column(name = "company", length = 3, nullable = false)
    private String company;

    @NotNull
    @Size(max = 3)
    @Column(name = "division", length = 3, nullable = false)
    private String division;

    @NotNull
    @Size(max = 3)
    @Column(name = "businessunitcompanycode", length = 3, nullable = false)
    private String businessunitcompanycode;

    @NotNull
    @Size(max = 10)
    @Column(name = "businessunitcode", length = 10, nullable = false)
    private String businessunitcode;

    @NotNull
    @Size(max = 10)
    @Column(name = "factorycode", length = 10, nullable = false)
    private String factorycode;

    @NotNull
    @Size(max = 10)
    @Column(name = "factorystate", length = 10, nullable = false)
    private String factorystate;

    @NotNull
    @Column(name = "bookingdate", nullable = false)
    private Instant bookingdate;

    @NotNull
    @Size(max = 1)
    @Column(name = "bookingtype", length = 1, nullable = false)
    private String bookingtype;

    @NotNull
    @Size(max = 1)
    @Column(name = "bookingfor", length = 1, nullable = false)
    private String bookingfor;

    @NotNull
    @Size(max = 1)
    @Column(name = "suppliercustomertype", length = 1, nullable = false)
    private String suppliercustomertype;

    @NotNull
    @Size(max = 8)
    @Column(name = "suppliercustomercode", length = 8, nullable = false)
    private String suppliercustomercode;

    @Size(max = 100)
    @Column(name = "suppliercustomerdesc", length = 100)
    private String suppliercustomerdesc;

    @NotNull
    @Size(max = 10)
    @Column(name = "suppliercustomerstate", length = 10, nullable = false)
    private String suppliercustomerstate;

    @Size(max = 100)
    @Column(name = "supplierlegalname", length = 100, nullable = false)
    private String supplierlegalname;

    @Size(max = 20)
    @Column(name = "gstin", length = 20)
    private String gstin;

    @Size(max = 1000)
    @Column(name = "address", length = 1000)
    private String address;

    @NotNull
    @Size(max = 25)
    @Column(name = "billno", length = 25, nullable = false)
    private String billno;

    @NotNull
    @Column(name = "billdate", nullable = false)
    private Instant billdate;

    @Column(name = "billamount")
    private Double billamount;

    @NotNull
    @Size(max = 3)
    @Column(name = "paymenttermcode", length = 3, nullable = false)
    private String paymenttermcode;

    @Size(max = 100)
    @Column(name = "paymenttermdesc", length = 100)
    private String paymenttermdesc;

    @NotNull
    @Size(max = 20)
    @Column(name = "costcentercode", length = 20, nullable = false)
    private String costcentercode;

    @Size(max = 100)
    @Column(name = "costcenterdesc", length = 100)
    private String costcenterdesc;

    @Size(max = 20)
    @Column(name = "findocumentcode", length = 20)
    private String findocumentcode;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Size(max = 20)
    @Column(name = "vehicle_no", length = 20)
    private String vehicleNo;

    @Size(max = 100)
    @Column(name = "projectcode", length = 100)
    private String projectcode;

    @Size(max = 100)
    @Column(name = "gatenumber", length = 100)
    private String gatenumber;

    @Size(max = 100)
    @Column(name = "gateentrynumber", length = 100)
    private String gateentrynumber;

    @Column(name = "gate_no_required")
    private Boolean gateNoRequired;

    @Column(name = "rcm_bill")
    private Boolean rcmBill;

    @Column(name = "shipping_bill")
    private Boolean shippingBill;

    @Column(name = "freight_applicable")
    private Boolean freightApplicable;

    @Size(max = 1)
    @Column(name = "freight_type", length = 1)
    private String freightType;

    @Column(name = "freight_value")
    private Double freightValue;

    @Column(name = "discount_applicable")
    private Boolean discountApplicable;

    @Size(max = 1)
    @Column(name = "discount_type", length = 1)
    private String discountType;

    @Column(name = "discount_value")
    private Double discountValue;

    @Column(name = "other_charges_applicable")
    private Boolean otherChargesApplicable;

    @Size(max = 1)
    @Column(name = "other_charges_type", length = 1)
    private String otherChargesType;

    @Column(name = "other_charges_value")
    private Double OtherChargesValue;

    @Column(name = "tcs_applicable")
    private Boolean tcsApplicable;

    @Size(max = 20)
    @Column(name = "tcs_glcode", length = 20)
    private String tcsGlcode;

    @Column(name = "tcs_value")
    private Double tcsValue;

    @Column(name = "value")
    private Double value;

    @Column(name = "itaxvalue")
    private Double itaxvalue;

    @Column(name = "ctaxvalue")
    private Double ctaxvalue;

    @Column(name = "staxvalue")
    private Double staxvalue;

    @Column(name = "taxvalue")
    private Double taxvalue;

    @Column(name = "totalvalue")
    private Double totalvalue;

    @Column(name = "round_off_value")
    private Double roundOffValue;

    @Size(max = 1)
    @Column(name = "round_off_type", length = 1)
    private String roundOffType;

    @Column(name = "net_amount")
    private Double netAmount;

    @Column(name = "tds_value")
    private Double tdsValue;

    @Column(name = "mtds")
    private Boolean mtds;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

    @Size(max = 50)
    @Column(name = "createdby", length = 50)
    private String createdby;

    @NotNull
    @Column(name = "createddate", nullable = false)
    private Instant createddate;

    @Size(max = 50)
    @Column(name = "updatedby", length = 50)
    private String updatedby;

    @NotNull
    @Column(name = "updateddate", nullable = false)
    private Instant updateddate;

    @Size(max = 100)
    @Column(name = "style_no", length = 100)
    private String styleNo;

    @Size(max = 20)
    @Column(name = "customer_code", length = 20)
    private String customerCode;

    @Size(max = 200)
    @Column(name = "customer_name", length = 200)
    private String customerName;

    @Size(max = 200)
    @Column(name = "customer_gst_name", length = 200)
    private String customerGstName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public DirectBookingEntry company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDivision() {
        return division;
    }

    public DirectBookingEntry division(String division) {
        this.division = division;
        return this;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessunitcompanycode() {
        return businessunitcompanycode;
    }

    public DirectBookingEntry businessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
        return this;
    }

    public void setBusinessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public DirectBookingEntry businessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
        return this;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getFactorycode() {
        return factorycode;
    }

    public DirectBookingEntry factorycode(String factorycode) {
        this.factorycode = factorycode;
        return this;
    }

    public void setFactorycode(String factorycode) {
        this.factorycode = factorycode;
    }

    public String getFactorystate() {
        return factorystate;
    }

    public DirectBookingEntry factorystate(String factorystate) {
        this.factorystate = factorystate;
        return this;
    }

    public void setFactorystate(String factorystate) {
        this.factorystate = factorystate;
    }

    public Instant getBookingdate() {
        return bookingdate;
    }

    public DirectBookingEntry bookingdate(Instant bookingdate) {
        this.bookingdate = bookingdate;
        return this;
    }

    public void setBookingdate(Instant bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getBookingtype() {
        return bookingtype;
    }

    public DirectBookingEntry bookingtype(String bookingtype) {
        this.bookingtype = bookingtype;
        return this;
    }

    public void setBookingtype(String bookingtype) {
        this.bookingtype = bookingtype;
    }

    public String getBookingfor() {
        return bookingfor;
    }

    public DirectBookingEntry bookingfor(String bookingfor) {
        this.bookingfor = bookingfor;
        return this;
    }

    public void setBookingfor(String bookingfor) {
        this.bookingfor = bookingfor;
    }

    public String getSuppliercustomertype() {
        return suppliercustomertype;
    }

    public DirectBookingEntry suppliercustomertype(String suppliercustomertype) {
        this.suppliercustomertype = suppliercustomertype;
        return this;
    }

    public void setSuppliercustomertype(String suppliercustomertype) {
        this.suppliercustomertype = suppliercustomertype;
    }

    public String getSuppliercustomercode() {
        return suppliercustomercode;
    }

    public DirectBookingEntry suppliercustomercode(String suppliercustomercode) {
        this.suppliercustomercode = suppliercustomercode;
        return this;
    }

    public void setSuppliercustomercode(String suppliercustomercode) {
        this.suppliercustomercode = suppliercustomercode;
    }

    public String getSuppliercustomerdesc() {
        return suppliercustomerdesc;
    }

    public DirectBookingEntry suppliercustomerdesc(String suppliercustomerdesc) {
        this.suppliercustomerdesc = suppliercustomerdesc;
        return this;
    }

    public void setSuppliercustomerdesc(String suppliercustomerdesc) {
        this.suppliercustomerdesc = suppliercustomerdesc;
    }

    public String getSuppliercustomerstate() {
        return suppliercustomerstate;
    }

    public DirectBookingEntry suppliercustomerstate(String suppliercustomerstate) {
        this.suppliercustomerstate = suppliercustomerstate;
        return this;
    }

    public void setSuppliercustomerstate(String suppliercustomerstate) {
        this.suppliercustomerstate = suppliercustomerstate;
    }

    public String getBillno() {
        return billno;
    }

    public DirectBookingEntry billno(String billno) {
        this.billno = billno;
        return this;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public Instant getBilldate() {
        return billdate;
    }

    public DirectBookingEntry billdate(Instant billdate) {
        this.billdate = billdate;
        return this;
    }

    public void setBilldate(Instant billdate) {
        this.billdate = billdate;
    }

    public Double getBillamount() {
        return billamount;
    }

    public DirectBookingEntry billamount(Double billamount) {
        this.billamount = billamount;
        return this;
    }

    public void setBillamount(Double billamount) {
        this.billamount = billamount;
    }

    public String getPaymenttermcode() {
        return paymenttermcode;
    }

    public DirectBookingEntry paymenttermcode(String paymenttermcode) {
        this.paymenttermcode = paymenttermcode;
        return this;
    }

    public void setPaymenttermcode(String paymenttermcode) {
        this.paymenttermcode = paymenttermcode;
    }

    public String getPaymenttermdesc() {
        return paymenttermdesc;
    }

    public DirectBookingEntry paymenttermdesc(String paymenttermdesc) {
        this.paymenttermdesc = paymenttermdesc;
        return this;
    }

    public void setPaymenttermdesc(String paymenttermdesc) {
        this.paymenttermdesc = paymenttermdesc;
    }

    public String getCostcentercode() {
        return costcentercode;
    }

    public DirectBookingEntry costcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
        return this;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    public String getCostcenterdesc() {
        return costcenterdesc;
    }

    public DirectBookingEntry costcenterdesc(String costcenterdesc) {
        this.costcenterdesc = costcenterdesc;
        return this;
    }

    public void setCostcenterdesc(String costcenterdesc) {
        this.costcenterdesc = costcenterdesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public DirectBookingEntry remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public DirectBookingEntry vehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
        return this;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Boolean getRcmBill() {
        return rcmBill;
    }

    public DirectBookingEntry rcmBill(Boolean rcmBill) {
        this.rcmBill = rcmBill;
        return this;
    }

    public void setRcmBill(Boolean rcmBill) {
        this.rcmBill = rcmBill;
    }

    public Boolean getShippingBill() {
        return shippingBill;
    }

    public DirectBookingEntry shippingBill(Boolean shippingBill) {
        this.shippingBill = shippingBill;
        return this;
    }

    public void setShippingBill(Boolean shippingBill) {
        this.shippingBill = shippingBill;
    }

    public Boolean getFreightApplicable() {
        return freightApplicable;
    }

    public DirectBookingEntry freightApplicable(Boolean freightApplicable) {
        this.freightApplicable = freightApplicable;
        return this;
    }

    public void setFreightApplicable(Boolean freightApplicable) {
        this.freightApplicable = freightApplicable;
    }

    public String getFreightType() {
        return freightType;
    }

    public DirectBookingEntry freightType(String freightType) {
        this.freightType = freightType;
        return this;
    }

    public void setFreightType(String freightType) {
        this.freightType = freightType;
    }

    public Double getFreightValue() {
        return freightValue;
    }

    public DirectBookingEntry freightValue(Double freightValue) {
        this.freightValue = freightValue;
        return this;
    }

    public void setFreightValue(Double freightValue) {
        this.freightValue = freightValue;
    }

    public Boolean getDiscountApplicable() {
        return discountApplicable;
    }

    public DirectBookingEntry discountApplicable(Boolean discountApplicable) {
        this.discountApplicable = discountApplicable;
        return this;
    }

    public void setDiscountApplicable(Boolean discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public String getDiscountType() {
        return discountType;
    }

    public DirectBookingEntry discountType(String discountType) {
        this.discountType = discountType;
        return this;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public DirectBookingEntry discountValue(Double discountValue) {
        this.discountValue = discountValue;
        return this;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Boolean getOtherChargesApplicable() {
        return otherChargesApplicable;
    }

    public DirectBookingEntry otherChargesApplicable(Boolean otherChargesApplicable) {
        this.otherChargesApplicable = otherChargesApplicable;
        return this;
    }

    public void setOtherChargesApplicable(Boolean otherChargesApplicable) {
        this.otherChargesApplicable = otherChargesApplicable;
    }

    public String getOtherChargesType() {
        return otherChargesType;
    }

    public DirectBookingEntry otherChargesType(String otherChargesType) {
        this.otherChargesType = otherChargesType;
        return this;
    }

    public void setOtherChargesType(String otherChargesType) {
        this.otherChargesType = otherChargesType;
    }

    public Double getOtherChargesValue() {
        return OtherChargesValue;
    }

    public DirectBookingEntry OtherChargesValue(Double OtherChargesValue) {
        this.OtherChargesValue = OtherChargesValue;
        return this;
    }

    public void setOtherChargesValue(Double OtherChargesValue) {
        this.OtherChargesValue = OtherChargesValue;
    }

    public Boolean getTcsApplicable() {
        return tcsApplicable;
    }

    public DirectBookingEntry tcsApplicable(Boolean tcsApplicable) {
        this.tcsApplicable = tcsApplicable;
        return this;
    }

    public void setTcsApplicable(Boolean tcsApplicable) {
        this.tcsApplicable = tcsApplicable;
    }

    public String getTcsGlcode() {
        return tcsGlcode;
    }

    public DirectBookingEntry tcsGlcode(String tcsGlcode) {
        this.tcsGlcode = tcsGlcode;
        return this;
    }

    public void setTcsGlcode(String tcsGlcode) {
        this.tcsGlcode = tcsGlcode;
    }

    public Double getTcsValue() {
        return tcsValue;
    }

    public DirectBookingEntry tcsValue(Double tcsValue) {
        this.tcsValue = tcsValue;
        return this;
    }

    public void setTcsValue(Double tcsValue) {
        this.tcsValue = tcsValue;
    }

    public String getFlag() {
        return flag;
    }

    public DirectBookingEntry flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedby() {
        return createdby;
    }

    public DirectBookingEntry createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public DirectBookingEntry createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public DirectBookingEntry updatedby(String updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public DirectBookingEntry updateddate(Instant updateddate) {
        this.updateddate = updateddate;
        return this;
    }

    public void setUpdateddate(Instant updateddate) {
        this.updateddate = updateddate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getItaxvalue() {
        return itaxvalue;
    }

    public void setItaxvalue(Double itaxvalue) {
        this.itaxvalue = itaxvalue;
    }

    public Double getCtaxvalue() {
        return ctaxvalue;
    }

    public void setCtaxvalue(Double ctaxvalue) {
        this.ctaxvalue = ctaxvalue;
    }

    public Double getStaxvalue() {
        return staxvalue;
    }

    public void setStaxvalue(Double staxvalue) {
        this.staxvalue = staxvalue;
    }

    public Double getTaxvalue() {
        return taxvalue;
    }

    public void setTaxvalue(Double taxvalue) {
        this.taxvalue = taxvalue;
    }

    public Double getTotalvalue() {
        return totalvalue;
    }

    public void setTotalvalue(Double totalvalue) {
        this.totalvalue = totalvalue;
    }

    public Double getRoundOffValue() {
        return roundOffValue;
    }

    public void setRoundOffValue(Double roundOffValue) {
        this.roundOffValue = roundOffValue;
    }

    public String getRoundOffType() {
        return roundOffType;
    }

    public void setRoundOffType(String roundOffType) {
        this.roundOffType = roundOffType;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getTdsValue() {
        return tdsValue;
    }

    public void setTdsValue(Double tdsValue) {
        this.tdsValue = tdsValue;
    }

    public Boolean getMtds() {
        return mtds;
    }

    public void setMtds(Boolean mtds) {
        this.mtds = mtds;
    }

    public String getFindocumentcode() {
        return findocumentcode;
    }

    public void setFindocumentcode(String findocumentcode) {
        this.findocumentcode = findocumentcode;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupplierlegalname() {
        return supplierlegalname;
    }

    public void setSupplierlegalname(String supplierlegalname) {
        this.supplierlegalname = supplierlegalname;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getGatenumber() {
        return gatenumber;
    }

    public void setGatenumber(String gatenumber) {
        this.gatenumber = gatenumber;
    }

    public Boolean getGateNoRequired() {
        return gateNoRequired;
    }

    public void setGateNoRequired(Boolean gateNoRequired) {
        this.gateNoRequired = gateNoRequired;
    }

    public String getGateentrynumber() {
        return gateentrynumber;
    }

    public void setGateentrynumber(String gateentrynumber) {
        this.gateentrynumber = gateentrynumber;
    }

    public String getStyleNo() {
        return styleNo;
    }

    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerGstName() {
        return customerGstName;
    }

    public void setCustomerGstName(String customerGstName) {
        this.customerGstName = customerGstName;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirectBookingEntry)) {
            return false;
        }
        return id != null && id.equals(((DirectBookingEntry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DirectBookingEntry{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", division='" + getDivision() + "'" +
            ", businessunitcompanycode='" + getBusinessunitcompanycode() + "'" +
            ", businessunitcode='" + getBusinessunitcode() + "'" +
            ", factorycode='" + getFactorycode() + "'" +
            ", factorystate='" + getFactorystate() + "'" +
            ", bookingdate='" + getBookingdate() + "'" +
            ", bookingtype='" + getBookingtype() + "'" +
            ", bookingfor='" + getBookingfor() + "'" +
            ", suppliercustomertype='" + getSuppliercustomertype() + "'" +
            ", suppliercustomercode='" + getSuppliercustomercode() + "'" +
            ", suppliercustomerdesc='" + getSuppliercustomerdesc() + "'" +
            ", suppliercustomerstate='" + getSuppliercustomerstate() + "'" +
            ", billno='" + getBillno() + "'" +
            ", billdate='" + getBilldate() + "'" +
            ", paymenttermcode='" + getPaymenttermcode() + "'" +
            ", paymenttermdesc='" + getPaymenttermdesc() + "'" +
            ", costcentercode='" + getCostcentercode() + "'" +
            ", costcenterdesc='" + getCostcenterdesc() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", vehicleNo='" + getVehicleNo() + "'" +
            ", rcmBill='" + getRcmBill() + "'" +
            ", shippingBill='" + getShippingBill() + "'" +
            ", freightApplicable='" + getFreightApplicable() + "'" +
            ", freightType='" + getFreightType() + "'" +
            ", freightValue=" + getFreightValue() +
            ", discountApplicable='" + getDiscountApplicable() + "'" +
            ", discountType='" + getDiscountType() + "'" +
            ", discountValue=" + getDiscountValue() +
            ", otherChargesApplicable='" + getOtherChargesApplicable() + "'" +
            ", otherChargesType='" + getOtherChargesType() + "'" +
            ", OtherChargesValue=" + getOtherChargesValue() +
            ", tcsApplicable='" + getTcsApplicable() + "'" +
            ", tcsValue=" + getTcsValue() +
            "}";
    }
}
