package io.vamani.application.model;

import io.vamani.application.util.DateUtils;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class DirectBookingEntryBean implements Serializable {
    private Long id;

    private String company;

    private String division;

    private String businessunitcompanycode;

    private String businessunitcode;

    private String factorycode;

    private String factorystate;

    private Instant bookingdate;

    private Date bookingdatedate;

    private String bookingtype;

    private String bookingfor;

    private String suppliercustomertype;

    private String suppliercustomercode;

    private String suppliercustomerdesc;

    private String suppliercustomerstate;

    private String supplierlegalname;

    private String address;

    private String gstin;

    private String billno;

    private Instant billdate;

    private Date billdatedate;

    private Double billamount;

    private String paymenttermcode;

    private String paymenttermdesc;

    private String costcentercode;

    private String costcenterdesc;

    private String remarks;

    private String vehicleNo;

    private String projectcode;

    private String gatenumber;

    private String gateentrynumber;

    private Boolean gateNoRequired;

    private Boolean rcmBill;

    private Boolean shippingBill;

    private Boolean freightApplicable;

    private String freightType;

    private Double freightValue;

    private Boolean discountApplicable;

    private String discountType;

    private Double discountValue;

    private Boolean otherChargesApplicable;

    private String otherChargesType;

    private Double OtherChargesValue;

    private Boolean tcsApplicable;

    private String tcsGlcode;

    private Double tcsValue;

    private Double value;

    private Double itaxvalue;

    private Double ctaxvalue;

    private Double staxvalue;

    private Double taxvalue;

    private Double totalvalue;

    private Double roundOffValue;

    private String roundOffType;

    private Double netAmount;

    private Double tdsValue;

    private Boolean mtds;

    private String flag;

    private String createdby;

    private Instant createddate;

    private String updatedby;

    private Instant updateddate;

    private String findocumentcode;

    private String copyFlag;

    private String styleNo;

    private String customerCode;

    private String customerName;

    private String customerGstName;

    private List<OrderpartnertdsBean> orderpartnertdss;

    private List<DirectBookingDetailsBean> directBookingDetails;

    private List<DirectBookingTdsDetailsBean> directBookingTdsDetails;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessunitcompanycode() {
        return businessunitcompanycode;
    }

    public void setBusinessunitcompanycode(String businessunitcompanycode) {
        this.businessunitcompanycode = businessunitcompanycode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getFactorycode() {
        return factorycode;
    }

    public void setFactorycode(String factorycode) {
        this.factorycode = factorycode;
    }

    public String getFactorystate() {
        return factorystate;
    }

    public void setFactorystate(String factorystate) {
        this.factorystate = factorystate;
    }

    public Instant getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Instant bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getBookingtype() {
        return bookingtype;
    }

    public void setBookingtype(String bookingtype) {
        this.bookingtype = bookingtype;
    }

    public String getBookingfor() {
        return bookingfor;
    }

    public void setBookingfor(String bookingfor) {
        this.bookingfor = bookingfor;
    }

    public String getSuppliercustomertype() {
        return suppliercustomertype;
    }

    public void setSuppliercustomertype(String suppliercustomertype) {
        this.suppliercustomertype = suppliercustomertype;
    }

    public String getSuppliercustomercode() {
        return suppliercustomercode;
    }

    public void setSuppliercustomercode(String suppliercustomercode) {
        this.suppliercustomercode = suppliercustomercode;
    }

    public String getSuppliercustomerdesc() {
        return suppliercustomerdesc;
    }

    public void setSuppliercustomerdesc(String suppliercustomerdesc) {
        this.suppliercustomerdesc = suppliercustomerdesc;
    }

    public String getSuppliercustomerstate() {
        return suppliercustomerstate;
    }

    public void setSuppliercustomerstate(String suppliercustomerstate) {
        this.suppliercustomerstate = suppliercustomerstate;
    }

    public String getSupplierlegalname() {
        return supplierlegalname;
    }

    public void setSupplierlegalname(String supplierlegalname) {
        this.supplierlegalname = supplierlegalname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public Instant getBilldate() {
        return billdate;
    }

    public void setBilldate(Instant billdate) {
        this.billdate = billdate;
    }

    public String getPaymenttermcode() {
        return paymenttermcode;
    }

    public void setPaymenttermcode(String paymenttermcode) {
        this.paymenttermcode = paymenttermcode;
    }

    public String getPaymenttermdesc() {
        return paymenttermdesc;
    }

    public void setPaymenttermdesc(String paymenttermdesc) {
        this.paymenttermdesc = paymenttermdesc;
    }

    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    public String getCostcenterdesc() {
        return costcenterdesc;
    }

    public void setCostcenterdesc(String costcenterdesc) {
        this.costcenterdesc = costcenterdesc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
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

    public Boolean getRcmBill() {
        return rcmBill;
    }

    public void setRcmBill(Boolean rcmBill) {
        this.rcmBill = rcmBill;
    }

    public Boolean getShippingBill() {
        return shippingBill;
    }

    public void setShippingBill(Boolean shippingBill) {
        this.shippingBill = shippingBill;
    }

    public Boolean getFreightApplicable() {
        return freightApplicable;
    }

    public void setFreightApplicable(Boolean freightApplicable) {
        this.freightApplicable = freightApplicable;
    }

    public String getFreightType() {
        return freightType;
    }

    public void setFreightType(String freightType) {
        this.freightType = freightType;
    }

    public Double getFreightValue() {
        return freightValue;
    }

    public void setFreightValue(Double freightValue) {
        this.freightValue = freightValue;
    }

    public Boolean getDiscountApplicable() {
        return discountApplicable;
    }

    public void setDiscountApplicable(Boolean discountApplicable) {
        this.discountApplicable = discountApplicable;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Boolean getOtherChargesApplicable() {
        return otherChargesApplicable;
    }

    public void setOtherChargesApplicable(Boolean otherChargesApplicable) {
        this.otherChargesApplicable = otherChargesApplicable;
    }

    public String getOtherChargesType() {
        return otherChargesType;
    }

    public void setOtherChargesType(String otherChargesType) {
        this.otherChargesType = otherChargesType;
    }

    public Double getOtherChargesValue() {
        return OtherChargesValue;
    }

    public void setOtherChargesValue(Double otherChargesValue) {
        OtherChargesValue = otherChargesValue;
    }

    public Boolean getTcsApplicable() {
        return tcsApplicable;
    }

    public void setTcsApplicable(Boolean tcsApplicable) {
        this.tcsApplicable = tcsApplicable;
    }

    public Double getTcsValue() {
        return tcsValue;
    }

    public void setTcsValue(Double tcsValue) {
        this.tcsValue = tcsValue;
    }

    public List<OrderpartnertdsBean> getOrderpartnertdss() {
        return orderpartnertdss;
    }

    public void setOrderpartnertdss(List<OrderpartnertdsBean> orderpartnertdss) {
        this.orderpartnertdss = orderpartnertdss;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<DirectBookingDetailsBean> getDirectBookingDetails() {
        return directBookingDetails;
    }

    public void setDirectBookingDetails(List<DirectBookingDetailsBean> directBookingDetails) {
        this.directBookingDetails = directBookingDetails;
    }

    public List<DirectBookingTdsDetailsBean> getDirectBookingTdsDetails() {
        return directBookingTdsDetails;
    }

    public void setDirectBookingTdsDetails(List<DirectBookingTdsDetailsBean> directBookingTdsDetails) {
        this.directBookingTdsDetails = directBookingTdsDetails;
    }

    public String getTcsGlcode() {
        return tcsGlcode;
    }

    public void setTcsGlcode(String tcsGlcode) {
        this.tcsGlcode = tcsGlcode;
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

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Instant updateddate) {
        this.updateddate = updateddate;
    }

    public String getFindocumentcode() {
        return findocumentcode;
    }

    public void setFindocumentcode(String findocumentcode) {
        this.findocumentcode = findocumentcode;
    }

    public Double getBillamount() {
        return billamount;
    }

    public void setBillamount(Double billamount) {
        this.billamount = billamount;
    }

    public String getCopyFlag() {
        return copyFlag;
    }

    public void setCopyFlag(String copyFlag) {
        this.copyFlag = copyFlag;
    }

    public Date getBookingdatedate() {
        return bookingdate != null ? Date.from(bookingdate) : null;
    }

    public void setBookingdatedate(Date bookingdatedate) {
        this.bookingdatedate = bookingdatedate;
    }

    public Date getBilldatedate() {
        return billdate != null ? Date.from(billdate) : null;
    }

    public void setBilldatedate(Date billdatedate) {
        this.billdatedate = billdatedate;;
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
}
