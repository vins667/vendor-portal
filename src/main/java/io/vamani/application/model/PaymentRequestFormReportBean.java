package io.vamani.application.model;

import io.vamani.application.domain.PaymentRequestFormDetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * A PaymentRequestForm.
 */
public class PaymentRequestFormReportBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String company;
    private String division;
    private String businessunitcode;
    private String paymentType;
    private String requestDate;
    private String supplierType;
    private String supplierCode;
    private String supplierName;
    private String supplierGstName;
    private String gstin;
    private String gstrBFilling;
    private String gstrFillingMonth;
    private String gstr2a;
    private String countrycode;
    private String currencycode;
    private String msmeNo;
    private String requestType;
    private String poNo;
    private Instant poDate;
    private String piNo;
    private Instant piDate;
    private String invoiceNo;
    private Instant invoiceDate;
    private String paymenttermcode;
    private String paymenttermdesc;
    private BigDecimal conversionRate;
    private BigDecimal piAmount;
    private BigDecimal outstandingAmount;

    private Long paymentRelease;

    private BigDecimal poBasic;

    private BigDecimal poGst;
    private BigDecimal totalPoValue;

    private BigDecimal piBasic;

    private BigDecimal piGst;
    private BigDecimal piGstPerc;
    private BigDecimal totalPiValue;
    private String freightRequired;
    private BigDecimal freightValue;
    private BigDecimal requestAmount;
    private BigDecimal requestGst;
    private String tdsType;
    private BigDecimal tdsValue;
    private BigDecimal totalReleaseAmount;
    private String chequeNo;
    private String utrNo;
    private String utrDate;
    private String findocbusinessunitcode;
    private String findocfinancialyearcode;
    private String findoccode;
    private String forwardCode;
    private String forwardName;
    private String poFile;
    private String piFile;
    private String remarks;
    private String status;
    private String createdBy;
    private String createdDate;
    private String approvedBy;
    private String approvedDate;

    private List<PaymentRequestFormDetails> paymentRequestFormDetails;

    private List<PaymentRequestInvoiceBean> invoices;
    private PaymentRequestFormDetails paymentRequestFormDetail;

    private List<MasterSearch> forwards;

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

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierGstName() {
        return supplierGstName;
    }

    public void setSupplierGstName(String supplierGstName) {
        this.supplierGstName = supplierGstName;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getGstrBFilling() {
        return gstrBFilling;
    }

    public void setGstrBFilling(String gstrBFilling) {
        this.gstrBFilling = gstrBFilling;
    }

    public String getGstr2a() {
        return gstr2a;
    }

    public void setGstr2a(String gstr2a) {
        this.gstr2a = gstr2a;
    }

    public String getGstrFillingMonth() {
        return gstrFillingMonth;
    }

    public void setGstrFillingMonth(String gstrFillingMonth) {
        this.gstrFillingMonth = gstrFillingMonth;
    }

    public String getMsmeNo() {
        return msmeNo;
    }

    public void setMsmeNo(String msmeNo) {
        this.msmeNo = msmeNo;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Instant getPoDate() {
        return poDate;
    }

    public void setPoDate(Instant poDate) {
        this.poDate = poDate;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public Instant getPiDate() {
        return piDate;
    }

    public void setPiDate(Instant piDate) {
        this.piDate = piDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Instant getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Instant invoiceDate) {
        this.invoiceDate = invoiceDate;
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

    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public Long getPaymentRelease() {
        return paymentRelease;
    }

    public void setPaymentRelease(Long paymentRelease) {
        this.paymentRelease = paymentRelease;
    }

    public BigDecimal getPoBasic() {
        return poBasic;
    }

    public void setPoBasic(BigDecimal poBasic) {
        this.poBasic = poBasic;
    }

    public BigDecimal getPoGst() {
        return poGst;
    }

    public void setPoGst(BigDecimal poGst) {
        this.poGst = poGst;
    }

    public BigDecimal getPiBasic() {
        return piBasic;
    }

    public void setPiBasic(BigDecimal piBasic) {
        this.piBasic = piBasic;
    }

    public BigDecimal getPiGstPerc() {
        return piGstPerc;
    }

    public void setPiGstPerc(BigDecimal piGstPerc) {
        this.piGstPerc = piGstPerc;
    }

    public BigDecimal getPiGst() {
        return piGst;
    }

    public void setPiGst(BigDecimal piGst) {
        this.piGst = piGst;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public BigDecimal getRequestGst() {
        return requestGst;
    }

    public void setRequestGst(BigDecimal requestGst) {
        this.requestGst = requestGst;
    }

    public String getTdsType() {
        return tdsType;
    }

    public void setTdsType(String tdsType) {
        this.tdsType = tdsType;
    }

    public BigDecimal getTdsValue() {
        return tdsValue;
    }

    public void setTdsValue(BigDecimal tdsValue) {
        this.tdsValue = tdsValue;
    }

    public BigDecimal getTotalReleaseAmount() {
        return totalReleaseAmount;
    }

    public void setTotalReleaseAmount(BigDecimal totalReleaseAmount) {
        this.totalReleaseAmount = totalReleaseAmount;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getUtrNo() {
        return utrNo;
    }

    public void setUtrNo(String utrNo) {
        this.utrNo = utrNo;
    }

    public String getUtrDate() {
        return utrDate;
    }

    public void setUtrDate(String utrDate) {
        this.utrDate = utrDate;
    }

    public String getFindocbusinessunitcode() {
        return findocbusinessunitcode;
    }

    public void setFindocbusinessunitcode(String findocbusinessunitcode) {
        this.findocbusinessunitcode = findocbusinessunitcode;
    }

    public String getFindocfinancialyearcode() {
        return findocfinancialyearcode;
    }

    public void setFindocfinancialyearcode(String findocfinancialyearcode) {
        this.findocfinancialyearcode = findocfinancialyearcode;
    }

    public String getFindoccode() {
        return findoccode;
    }

    public void setFindoccode(String findoccode) {
        this.findoccode = findoccode;
    }

    public String getForwardCode() {
        return forwardCode;
    }

    public void setForwardCode(String forwardCode) {
        this.forwardCode = forwardCode;
    }

    public String getForwardName() {
        return forwardName;
    }

    public void setForwardName(String forwardName) {
        this.forwardName = forwardName;
    }

    public String getPoFile() {
        return poFile;
    }

    public void setPoFile(String poFile) {
        this.poFile = poFile;
    }

    public String getPiFile() {
        return piFile;
    }

    public void setPiFile(String piFile) {
        this.piFile = piFile;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public BigDecimal getTotalPoValue() {
        return totalPoValue;
    }

    public void setTotalPoValue(BigDecimal totalPoValue) {
        this.totalPoValue = totalPoValue;
    }

    public BigDecimal getTotalPiValue() {
        return totalPiValue;
    }

    public void setTotalPiValue(BigDecimal totalPiValue) {
        this.totalPiValue = totalPiValue;
    }

    public String getFreightRequired() {
        return freightRequired;
    }

    public void setFreightRequired(String freightRequired) {
        this.freightRequired = freightRequired;
    }

    public BigDecimal getFreightValue() {
        return freightValue;
    }

    public void setFreightValue(BigDecimal freightValue) {
        this.freightValue = freightValue;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public BigDecimal getPiAmount() {
        return piAmount;
    }

    public void setPiAmount(BigDecimal piAmount) {
        this.piAmount = piAmount;
    }

    public List<PaymentRequestFormDetails> getPaymentRequestFormDetails() {
        return paymentRequestFormDetails;
    }

    public void setPaymentRequestFormDetails(List<PaymentRequestFormDetails> paymentRequestFormDetails) {
        this.paymentRequestFormDetails = paymentRequestFormDetails;
    }

    public List<PaymentRequestInvoiceBean> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<PaymentRequestInvoiceBean> invoices) {
        this.invoices = invoices;
    }

    public PaymentRequestFormDetails getPaymentRequestFormDetail() {
        return paymentRequestFormDetail;
    }

    public void setPaymentRequestFormDetail(PaymentRequestFormDetails paymentRequestFormDetail) {
        this.paymentRequestFormDetail = paymentRequestFormDetail;
    }

    public List<MasterSearch> getForwards() {
        return forwards;
    }

    public void setForwards(List<MasterSearch> forwards) {
        this.forwards = forwards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequestFormReportBean that = (PaymentRequestFormReportBean) o;
        return Objects.equals(id, that.id) && Objects.equals(company, that.company) && Objects.equals(division, that.division) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(paymentType, that.paymentType) && Objects.equals(requestDate, that.requestDate) && Objects.equals(supplierType, that.supplierType) && Objects.equals(supplierCode, that.supplierCode) && Objects.equals(supplierName, that.supplierName) && Objects.equals(supplierGstName, that.supplierGstName) && Objects.equals(gstin, that.gstin) && Objects.equals(gstrBFilling, that.gstrBFilling) && Objects.equals(gstrFillingMonth, that.gstrFillingMonth) && Objects.equals(msmeNo, that.msmeNo) && Objects.equals(poNo, that.poNo) && Objects.equals(poDate, that.poDate) && Objects.equals(piNo, that.piNo) && Objects.equals(piDate, that.piDate) && Objects.equals(invoiceNo, that.invoiceNo) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(paymenttermcode, that.paymenttermcode) && Objects.equals(paymenttermdesc, that.paymenttermdesc) && Objects.equals(outstandingAmount, that.outstandingAmount) && Objects.equals(paymentRelease, that.paymentRelease) && Objects.equals(poBasic, that.poBasic) && Objects.equals(poGst, that.poGst) && Objects.equals(piBasic, that.piBasic) && Objects.equals(piGst, that.piGst) && Objects.equals(requestAmount, that.requestAmount) && Objects.equals(requestGst, that.requestGst) && Objects.equals(tdsType, that.tdsType) && Objects.equals(tdsValue, that.tdsValue) && Objects.equals(chequeNo, that.chequeNo) && Objects.equals(utrNo, that.utrNo) && Objects.equals(utrDate, that.utrDate) && Objects.equals(findocbusinessunitcode, that.findocbusinessunitcode) && Objects.equals(findocfinancialyearcode, that.findocfinancialyearcode) && Objects.equals(findoccode, that.findoccode) && Objects.equals(forwardCode, that.forwardCode) && Objects.equals(forwardName, that.forwardName) && Objects.equals(poFile, that.poFile) && Objects.equals(piFile, that.piFile) && Objects.equals(remarks, that.remarks) && Objects.equals(status, that.status) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(approvedBy, that.approvedBy) && Objects.equals(approvedDate, that.approvedDate) && Objects.equals(paymentRequestFormDetails, that.paymentRequestFormDetails) && Objects.equals(invoices, that.invoices) && Objects.equals(paymentRequestFormDetail, that.paymentRequestFormDetail) && Objects.equals(forwards, that.forwards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, division, businessunitcode, paymentType, requestDate, supplierType, supplierCode, supplierName, supplierGstName, gstin, gstrBFilling, gstrFillingMonth, msmeNo, poNo, poDate, piNo, piDate, invoiceNo, invoiceDate, paymenttermcode, paymenttermdesc, outstandingAmount, paymentRelease, poBasic, poGst, piBasic, piGst, requestAmount, requestGst, tdsType, tdsValue, chequeNo, utrNo, utrDate, findocbusinessunitcode, findocfinancialyearcode, findoccode, forwardCode, forwardName, poFile, piFile, remarks, status, createdBy, createdDate, approvedBy, approvedDate, paymentRequestFormDetails, invoices, paymentRequestFormDetail, forwards);
    }
}
