package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A PaymentRequestForm.
 */
@Entity
@Table(name = "payment_request_form")
public class PaymentRequestForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "paymentRequestFormSeq", sequenceName = "payment_request_form_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "paymentRequestFormSeq")
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
    @Size(max = 10)
    @Column(name = "businessunitcode", length = 10, nullable = false)
    private String businessunitcode;

    @Size(max = 2)
    @Column(name = "payment_type", length = 2, nullable = false)
    private String paymentType;

    @Column(name = "request_date")
    private Instant requestDate;

    @Size(max = 1)
    @Column(name = "supplier_type", length = 1, nullable = true)
    private String supplierType;

    @Size(max = 8)
    @Column(name = "supplier_code", length = 8, nullable = true)
    private String supplierCode;

    @Size(max = 200)
    @Column(name = "supplier_name", length = 200, nullable = true)
    private String supplierName;

    @Size(max = 200)
    @Column(name = "supplier_gst_name", length = 200, nullable = true)
    private String supplierGstName;

    @Size(max = 20)
    @Column(name = "gstin", length = 20, nullable = true)
    private String gstin;

    @Size(max = 50)
    @Column(name = "gstr_b_filling", length = 50, nullable = true)
    private String gstrBFilling;

    @Size(max = 50)
    @Column(name = "gstr_filling_month", length = 50, nullable = true)
    private String gstrFillingMonth;

    @Size(max = 1)
    @Column(name = "gstr_2a", length = 1, nullable = true)
    private String gstr2a;

    @Size(max = 10)
    @Column(name = "countrycode", length = 10, nullable = true)
    private String countrycode;

    @Size(max = 10)
    @Column(name = "currencycode", length = 10, nullable = true)
    private String currencycode;

    @Size(max = 50)
    @Column(name = "msme_no", length = 50, nullable = true)
    private String msmeNo;

    @Size(max = 1)
    @Column(name = "request_type", length = 1, nullable = true)
    private String requestType;

    @Size(max = 50)
    @Column(name = "po_no", length = 50, nullable = true)
    private String poNo;

    @Column(name = "po_date")
    private Instant poDate;

    @Size(max = 50)
    @Column(name = "pi_no", length = 50, nullable = true)
    private String piNo;

    @Column(name = "pi_date")
    private Instant piDate;

    @Size(max = 500)
    @Column(name = "invoice_no", length = 500, nullable = true)
    private String invoiceNo;

    @Column(name = "invoice_date")
    private Instant invoiceDate;

    @Size(max = 3)
    @Column(name = "paymenttermcode", length = 3, nullable = true)
    private String paymenttermcode;

    @Size(max = 100)
    @Column(name = "paymenttermdesc", length = 100, nullable = true)
    private String paymenttermdesc;

    @Column(name = "conversion_rate")
    private BigDecimal conversionRate;

    @Column(name = "pi_amount")
    private BigDecimal piAmount;

    @Column(name = "outstanding_amount")
    private BigDecimal outstandingAmount;

    @Column(name = "payment_release", nullable = true)
    private Long paymentRelease;

    @Column(name = "po_basic")
    private BigDecimal poBasic;

    @Column(name = "po_gst")
    private BigDecimal poGst;

    @Column(name = "total_po_value")
    private BigDecimal totalPoValue;

    @Column(name = "pi_basic")
    private BigDecimal piBasic;

    @Column(name = "pi_gst_perc")
    private BigDecimal piGstPerc;

    @Column(name = "pi_gst")
    private BigDecimal piGst;

    @Column(name = "total_pi_value")
    private BigDecimal totalPiValue;

    @Column(name = "freight_required", length = 1)
    private String freightRequired;

    @Column(name = "freight_value")
    private BigDecimal freightValue;

    @Column(name = "request_amount")
    private BigDecimal requestAmount;

    @Column(name = "request_gst")
    private BigDecimal requestGst;

    @Size(max = 20)
    @Column(name = "tds_type", length = 20, nullable = true)
    private String tdsType;

    @Column(name = "tds_value")
    private BigDecimal tdsValue;

    @Column(name = "total_release_amount")
    private BigDecimal totalReleaseAmount;

    @Size(max = 50)
    @Column(name = "cheque_no", length = 50, nullable = true)
    private String chequeNo;

    @Size(max = 50)
    @Column(name = "utr_no", length = 50, nullable = true)
    private String utrNo;

    @Column(name = "utr_date")
    private Instant utrDate;

    @Size(max = 3)
    @Column(name = "findocbusinessunitcode", length = 3, nullable = true)
    private String findocbusinessunitcode;

    @Size(max = 4)
    @Column(name = "findocfinancialyearcode", length = 4, nullable = true)
    private String findocfinancialyearcode;

    @Size(max = 20)
    @Column(name = "findoccode", length = 20, nullable = true)
    private String findoccode;

    @Size(max = 50)
    @Column(name = "forward_code", length = 50)
    private String forwardCode;

    @Size(max = 200)
    @Column(name = "forward_name", length = 200)
    private String forwardName;

    @Size(max = 50)
    @Column(name = "po_file", length = 50, nullable = true)
    private String poFile;


    @Size(max = 50)
    @Column(name = "pi_file", length = 50, nullable = true)
    private String piFile;

    @Size(max = 50)
    @Column(name = "remarks", length = 50, nullable = true)
    private String remarks;

    @Size(max = 1)
    @Column(name = "status", length = 1, nullable = true)
    private String status;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    @Column(name = "approved_date")
    private Instant approvedDate;

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

    public Instant getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Instant requestDate) {
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

    public String getGstrFillingMonth() {
        return gstrFillingMonth;
    }

    public void setGstrFillingMonth(String gstrFillingMonth) {
        this.gstrFillingMonth = gstrFillingMonth;
    }

    public String getGstr2a() {
        return gstr2a;
    }

    public void setGstr2a(String gstr2a) {
        this.gstr2a = gstr2a;
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

    public Instant getUtrDate() {
        return utrDate;
    }

    public void setUtrDate(Instant utrDate) {
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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequestForm that = (PaymentRequestForm) o;
        return Objects.equals(id, that.id) && Objects.equals(company, that.company) && Objects.equals(division, that.division) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(paymentType, that.paymentType) && Objects.equals(requestDate, that.requestDate) && Objects.equals(supplierType, that.supplierType) && Objects.equals(supplierCode, that.supplierCode) && Objects.equals(supplierName, that.supplierName) && Objects.equals(supplierGstName, that.supplierGstName) && Objects.equals(gstin, that.gstin) && Objects.equals(gstrBFilling, that.gstrBFilling) && Objects.equals(gstrFillingMonth, that.gstrFillingMonth) && Objects.equals(msmeNo, that.msmeNo) && Objects.equals(poNo, that.poNo) && Objects.equals(poDate, that.poDate) && Objects.equals(piNo, that.piNo) && Objects.equals(piDate, that.piDate) && Objects.equals(invoiceNo, that.invoiceNo) && Objects.equals(invoiceDate, that.invoiceDate) && Objects.equals(paymenttermcode, that.paymenttermcode) && Objects.equals(paymenttermdesc, that.paymenttermdesc) && Objects.equals(outstandingAmount, that.outstandingAmount) && Objects.equals(paymentRelease, that.paymentRelease) && Objects.equals(poBasic, that.poBasic) && Objects.equals(poGst, that.poGst) && Objects.equals(piBasic, that.piBasic) && Objects.equals(piGst, that.piGst) && Objects.equals(requestAmount, that.requestAmount) && Objects.equals(requestGst, that.requestGst) && Objects.equals(tdsType, that.tdsType) && Objects.equals(tdsValue, that.tdsValue) && Objects.equals(chequeNo, that.chequeNo) && Objects.equals(utrNo, that.utrNo) && Objects.equals(utrDate, that.utrDate) && Objects.equals(findocbusinessunitcode, that.findocbusinessunitcode) && Objects.equals(findocfinancialyearcode, that.findocfinancialyearcode) && Objects.equals(findoccode, that.findoccode) && Objects.equals(forwardCode, that.forwardCode) && Objects.equals(forwardName, that.forwardName) && Objects.equals(poFile, that.poFile) && Objects.equals(piFile, that.piFile) && Objects.equals(remarks, that.remarks) && Objects.equals(status, that.status) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(approvedBy, that.approvedBy) && Objects.equals(approvedDate, that.approvedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, division, businessunitcode, paymentType, requestDate, supplierType, supplierCode, supplierName, supplierGstName, gstin, gstrBFilling, gstrFillingMonth, msmeNo, poNo, poDate, piNo, piDate, invoiceNo, invoiceDate, paymenttermcode, paymenttermdesc, outstandingAmount, paymentRelease, poBasic, poGst, piBasic, piGst, requestAmount, requestGst, tdsType, tdsValue, chequeNo, utrNo, utrDate, findocbusinessunitcode, findocfinancialyearcode, findoccode, forwardCode, forwardName, poFile, piFile, remarks, status, createdBy, createdDate, approvedBy, approvedDate);
    }
}
