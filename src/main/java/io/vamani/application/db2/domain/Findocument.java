package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "findocument")
public class Findocument {
    @EmbeddedId
    private FindocumentId id;
    private Long absversionnumber;
    private String currentstatus;
    private String progressstatus;
    private BigDecimal tdspercentage;
    private BigDecimal tdsapplicableamount;
    private BigDecimal tdsamount;
    private Date financedocumentdate;
    private Date postingdate;
    private Date duedate;
    private String projectcode;
    private BigDecimal documentamount;
    private BigDecimal exchangerate;
    private String chequenumber;
    private Date chequedate;
    private String customerreference;
    private Date customerreferencedate;
    private String vendorreference;
    private Date vendorreferencedate;
    private String suppliertype;
    private String suppliercode;
    private String referencetext1;
    private String referencetext2;
    private String referencetext3;
    private String referencetext4;
    private String referencetext5;
    private BigDecimal referenceamt1;
    private BigDecimal referenceamt2;
    private BigDecimal referenceamt3;
    private BigDecimal referenceamt4;
    private BigDecimal referenceamt5;
    private String remark;
    private String purchaseinvoicedivisioncode;
    private String purinvoiceordprncsmsuptype;
    private String purinvoiceordprncsmsupcode;
    private String purchaseinvoicecode;
    private Date purchaseinvoiceinvoicedate;
    private String expenseinvoicedivisioncode;
    private String expenseinvoiceordprncsmsupte;
    private String expenseinvoiceordprncsmsupcod;
    private String expenseinvoicecode;
    private Date expenseinvoiceinvoicedate;
    private String mrnrejmdmrnheaderdivisioncode;
    private String mrnrejmdmrnheadermrnprefixcode;
    private String mrnrejmdmrnheadercode;
    private Integer mrnrejmdlineid;
    private Integer mrnrejrejectionlineid;
    private String poadvancepurordercountercode;
    private String poadvancepurchaseordercode;
    private Integer poadvancelineno;
    private String plantinvoicedivisioncode;
    private String plantinvoicecode;
    private String commercialinvoicedivisioncode;
    private String commercialinvoicecode;
    private String sdcreditprovisionalcountercode;
    private String sdcreditprovisionalcode;
    private String directinvoicedivisioncode;
    private String directinvoicecountercode;
    private String directinvoicecode;
    private String mrndivisioncode;
    private String mrnmrnprefixcode;
    private String mrncode;
    private String stocktrntransactionnumber;
    private Integer stocktrntrndetailnumber;
    private String consumptiondivisioncode;
    private String consumptionitemtypecode;
    private String consumptionbusinessareacode;
    private Date consumptionstartdate;
    private Date consumptionenddate;
    private Long payrollpostingsno;
    private String payrollpostingpayrollcode;
    private String payrollpostingprocessperiod;
    private String internalordercountercode;
    private String internalordercode;
    private String rg23Iiaexciseyearregno;
    private String rg23Iiaexciseyearcode;
    private String rg23Iiacode;
    private String rg23Iicexciseyearregno;
    private String rg23Iicexciseyearcode;
    private String rg23Iiccode;
    private String exportshippingbilldivisioncode;
    private String exportshippingbillcode;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;
    private String mrninvoiceno;
    private Date mrninvoicedate;
    private Timestamp creationdatetimeutc;
    private Timestamp lastupdatedatetimeutc;
    private String invoiceno;
    private Date invoicedate;
    private String consumptionlglwarehousecode;
    private String reffindoccode;
    //private List<Findocumentline> findocumentline;

    public FindocumentId getId() {
        return id;
    }

    public void setId(FindocumentId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ABSVERSIONNUMBER", nullable = false)
    public Long getAbsversionnumber() {
        return absversionnumber;
    }

    public void setAbsversionnumber(Long absversionnumber) {
        this.absversionnumber = absversionnumber;
    }

    @Basic
    @Column(name = "CURRENTSTATUS", nullable = false, length = 2)
    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    @Basic
    @Column(name = "PROGRESSSTATUS", nullable = false, length = 2)
    public String getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(String progressstatus) {
        this.progressstatus = progressstatus;
    }

    @Basic
    @Column(name = "TDSPERCENTAGE", nullable = true, precision = 2)
    public BigDecimal getTdspercentage() {
        return tdspercentage;
    }

    public void setTdspercentage(BigDecimal tdspercentage) {
        this.tdspercentage = tdspercentage;
    }

    @Basic
    @Column(name = "TDSAPPLICABLEAMOUNT", nullable = true, precision = 5)
    public BigDecimal getTdsapplicableamount() {
        return tdsapplicableamount;
    }

    public void setTdsapplicableamount(BigDecimal tdsapplicableamount) {
        this.tdsapplicableamount = tdsapplicableamount;
    }

    @Basic
    @Column(name = "TDSAMOUNT", nullable = true, precision = 5)
    public BigDecimal getTdsamount() {
        return tdsamount;
    }

    public void setTdsamount(BigDecimal tdsamount) {
        this.tdsamount = tdsamount;
    }

    @Basic
    @Column(name = "FINANCEDOCUMENTDATE", nullable = false)
    public Date getFinancedocumentdate() {
        return financedocumentdate;
    }

    public void setFinancedocumentdate(Date financedocumentdate) {
        this.financedocumentdate = financedocumentdate;
    }

    @Basic
    @Column(name = "POSTINGDATE", nullable = false)
    public Date getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(Date postingdate) {
        this.postingdate = postingdate;
    }


    @Basic
    @Column(name = "DUEDATE", nullable = true)
    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    @Basic
    @Column(name = "PROJECTCODE", nullable = true, length = 20)
    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @Basic
    @Column(name = "DOCUMENTAMOUNT", nullable = true, precision = 5)
    public BigDecimal getDocumentamount() {
        return documentamount;
    }

    public void setDocumentamount(BigDecimal documentamount) {
        this.documentamount = documentamount;
    }

    @Basic
    @Column(name = "EXCHANGERATE", nullable = true, precision = 15)
    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    @Basic
    @Column(name = "CHEQUENUMBER", nullable = true, length = 20)
    public String getChequenumber() {
        return chequenumber;
    }

    public void setChequenumber(String chequenumber) {
        this.chequenumber = chequenumber;
    }

    @Basic
    @Column(name = "CHEQUEDATE", nullable = true)
    public Date getChequedate() {
        return chequedate;
    }

    public void setChequedate(Date chequedate) {
        this.chequedate = chequedate;
    }

    @Basic
    @Column(name = "CUSTOMERREFERENCE", nullable = true, length = 20)
    public String getCustomerreference() {
        return customerreference;
    }

    public void setCustomerreference(String customerreference) {
        this.customerreference = customerreference;
    }

    @Basic
    @Column(name = "CUSTOMERREFERENCEDATE", nullable = true)
    public Date getCustomerreferencedate() {
        return customerreferencedate;
    }

    public void setCustomerreferencedate(Date customerreferencedate) {
        this.customerreferencedate = customerreferencedate;
    }

    @Basic
    @Column(name = "VENDORREFERENCE", nullable = true, length = 20)
    public String getVendorreference() {
        return vendorreference;
    }

    public void setVendorreference(String vendorreference) {
        this.vendorreference = vendorreference;
    }

    @Basic
    @Column(name = "VENDORREFERENCEDATE", nullable = true)
    public Date getVendorreferencedate() {
        return vendorreferencedate;
    }

    public void setVendorreferencedate(Date vendorreferencedate) {
        this.vendorreferencedate = vendorreferencedate;
    }

    @Basic
    @Column(name = "SUPPLIERTYPE", nullable = true, length = 20)
    public String getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    @Basic
    @Column(name = "SUPPLIERCODE", nullable = true, length = 20)
    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    @Basic
    @Column(name = "REFERENCETEXT1", nullable = true, length = 20)
    public String getReferencetext1() {
        return referencetext1;
    }

    public void setReferencetext1(String referencetext1) {
        this.referencetext1 = referencetext1;
    }

    @Basic
    @Column(name = "REFERENCETEXT2", nullable = true, length = 20)
    public String getReferencetext2() {
        return referencetext2;
    }

    public void setReferencetext2(String referencetext2) {
        this.referencetext2 = referencetext2;
    }

    @Basic
    @Column(name = "REFERENCETEXT3", nullable = true, length = 20)
    public String getReferencetext3() {
        return referencetext3;
    }

    public void setReferencetext3(String referencetext3) {
        this.referencetext3 = referencetext3;
    }

    @Basic
    @Column(name = "REFERENCETEXT4", nullable = true, length = 20)
    public String getReferencetext4() {
        return referencetext4;
    }

    public void setReferencetext4(String referencetext4) {
        this.referencetext4 = referencetext4;
    }

    @Basic
    @Column(name = "REFERENCETEXT5", nullable = true, length = 20)
    public String getReferencetext5() {
        return referencetext5;
    }

    public void setReferencetext5(String referencetext5) {
        this.referencetext5 = referencetext5;
    }

    @Basic
    @Column(name = "REFERENCEAMT1", nullable = true, precision = 5)
    public BigDecimal getReferenceamt1() {
        return referenceamt1;
    }

    public void setReferenceamt1(BigDecimal referenceamt1) {
        this.referenceamt1 = referenceamt1;
    }

    @Basic
    @Column(name = "REFERENCEAMT2", nullable = true, precision = 5)
    public BigDecimal getReferenceamt2() {
        return referenceamt2;
    }

    public void setReferenceamt2(BigDecimal referenceamt2) {
        this.referenceamt2 = referenceamt2;
    }

    @Basic
    @Column(name = "REFERENCEAMT3", nullable = true, precision = 5)
    public BigDecimal getReferenceamt3() {
        return referenceamt3;
    }

    public void setReferenceamt3(BigDecimal referenceamt3) {
        this.referenceamt3 = referenceamt3;
    }

    @Basic
    @Column(name = "REFERENCEAMT4", nullable = true, precision = 5)
    public BigDecimal getReferenceamt4() {
        return referenceamt4;
    }

    public void setReferenceamt4(BigDecimal referenceamt4) {
        this.referenceamt4 = referenceamt4;
    }

    @Basic
    @Column(name = "REFERENCEAMT5", nullable = true, precision = 5)
    public BigDecimal getReferenceamt5() {
        return referenceamt5;
    }

    public void setReferenceamt5(BigDecimal referenceamt5) {
        this.referenceamt5 = referenceamt5;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "PURCHASEINVOICEDIVISIONCODE", nullable = true, length = 3)
    public String getPurchaseinvoicedivisioncode() {
        return purchaseinvoicedivisioncode;
    }

    public void setPurchaseinvoicedivisioncode(String purchaseinvoicedivisioncode) {
        this.purchaseinvoicedivisioncode = purchaseinvoicedivisioncode;
    }

    @Basic
    @Column(name = "PURINVOICEORDPRNCSMSUPTYPE", nullable = true, length = 1)
    public String getPurinvoiceordprncsmsuptype() {
        return purinvoiceordprncsmsuptype;
    }

    public void setPurinvoiceordprncsmsuptype(String purinvoiceordprncsmsuptype) {
        this.purinvoiceordprncsmsuptype = purinvoiceordprncsmsuptype;
    }

    @Basic
    @Column(name = "PURINVOICEORDPRNCSMSUPCODE", nullable = true, length = 8)
    public String getPurinvoiceordprncsmsupcode() {
        return purinvoiceordprncsmsupcode;
    }

    public void setPurinvoiceordprncsmsupcode(String purinvoiceordprncsmsupcode) {
        this.purinvoiceordprncsmsupcode = purinvoiceordprncsmsupcode;
    }

    @Basic
    @Column(name = "PURCHASEINVOICECODE", nullable = true, length = 25)
    public String getPurchaseinvoicecode() {
        return purchaseinvoicecode;
    }

    public void setPurchaseinvoicecode(String purchaseinvoicecode) {
        this.purchaseinvoicecode = purchaseinvoicecode;
    }

    @Basic
    @Column(name = "PURCHASEINVOICEINVOICEDATE", nullable = true)
    public Date getPurchaseinvoiceinvoicedate() {
        return purchaseinvoiceinvoicedate;
    }

    public void setPurchaseinvoiceinvoicedate(Date purchaseinvoiceinvoicedate) {
        this.purchaseinvoiceinvoicedate = purchaseinvoiceinvoicedate;
    }

    @Basic
    @Column(name = "EXPENSEINVOICEDIVISIONCODE", nullable = true, length = 3)
    public String getExpenseinvoicedivisioncode() {
        return expenseinvoicedivisioncode;
    }

    public void setExpenseinvoicedivisioncode(String expenseinvoicedivisioncode) {
        this.expenseinvoicedivisioncode = expenseinvoicedivisioncode;
    }

    @Basic
    @Column(name = "EXPENSEINVOICEORDPRNCSMSUPTE", nullable = true, length = 1)
    public String getExpenseinvoiceordprncsmsupte() {
        return expenseinvoiceordprncsmsupte;
    }

    public void setExpenseinvoiceordprncsmsupte(String expenseinvoiceordprncsmsupte) {
        this.expenseinvoiceordprncsmsupte = expenseinvoiceordprncsmsupte;
    }

    @Basic
    @Column(name = "EXPENSEINVOICEORDPRNCSMSUPCOD", nullable = true, length = 8)
    public String getExpenseinvoiceordprncsmsupcod() {
        return expenseinvoiceordprncsmsupcod;
    }

    public void setExpenseinvoiceordprncsmsupcod(String expenseinvoiceordprncsmsupcod) {
        this.expenseinvoiceordprncsmsupcod = expenseinvoiceordprncsmsupcod;
    }

    @Basic
    @Column(name = "EXPENSEINVOICECODE", nullable = true, length = 25)
    public String getExpenseinvoicecode() {
        return expenseinvoicecode;
    }

    public void setExpenseinvoicecode(String expenseinvoicecode) {
        this.expenseinvoicecode = expenseinvoicecode;
    }

    @Basic
    @Column(name = "EXPENSEINVOICEINVOICEDATE", nullable = true)
    public Date getExpenseinvoiceinvoicedate() {
        return expenseinvoiceinvoicedate;
    }

    public void setExpenseinvoiceinvoicedate(Date expenseinvoiceinvoicedate) {
        this.expenseinvoiceinvoicedate = expenseinvoiceinvoicedate;
    }

    @Basic
    @Column(name = "MRNREJMDMRNHEADERDIVISIONCODE", nullable = true, length = 3)
    public String getMrnrejmdmrnheaderdivisioncode() {
        return mrnrejmdmrnheaderdivisioncode;
    }

    public void setMrnrejmdmrnheaderdivisioncode(String mrnrejmdmrnheaderdivisioncode) {
        this.mrnrejmdmrnheaderdivisioncode = mrnrejmdmrnheaderdivisioncode;
    }

    @Basic
    @Column(name = "MRNREJMDMRNHEADERMRNPREFIXCODE", nullable = true, length = 3)
    public String getMrnrejmdmrnheadermrnprefixcode() {
        return mrnrejmdmrnheadermrnprefixcode;
    }

    public void setMrnrejmdmrnheadermrnprefixcode(String mrnrejmdmrnheadermrnprefixcode) {
        this.mrnrejmdmrnheadermrnprefixcode = mrnrejmdmrnheadermrnprefixcode;
    }

    @Basic
    @Column(name = "MRNREJMDMRNHEADERCODE", nullable = true, precision = 0)
    public String getMrnrejmdmrnheadercode() {
        return mrnrejmdmrnheadercode;
    }

    public void setMrnrejmdmrnheadercode(String mrnrejmdmrnheadercode) {
        this.mrnrejmdmrnheadercode = mrnrejmdmrnheadercode;
    }

    @Basic
    @Column(name = "MRNREJMDLINEID", nullable = true)
    public Integer getMrnrejmdlineid() {
        return mrnrejmdlineid;
    }

    public void setMrnrejmdlineid(Integer mrnrejmdlineid) {
        this.mrnrejmdlineid = mrnrejmdlineid;
    }

    @Basic
    @Column(name = "MRNREJREJECTIONLINEID", nullable = true)
    public Integer getMrnrejrejectionlineid() {
        return mrnrejrejectionlineid;
    }

    public void setMrnrejrejectionlineid(Integer mrnrejrejectionlineid) {
        this.mrnrejrejectionlineid = mrnrejrejectionlineid;
    }

    @Basic
    @Column(name = "POADVANCEPURORDERCOUNTERCODE", nullable = true, length = 8)
    public String getPoadvancepurordercountercode() {
        return poadvancepurordercountercode;
    }

    public void setPoadvancepurordercountercode(String poadvancepurordercountercode) {
        this.poadvancepurordercountercode = poadvancepurordercountercode;
    }

    @Basic
    @Column(name = "POADVANCEPURCHASEORDERCODE", nullable = true, length = 15)
    public String getPoadvancepurchaseordercode() {
        return poadvancepurchaseordercode;
    }

    public void setPoadvancepurchaseordercode(String poadvancepurchaseordercode) {
        this.poadvancepurchaseordercode = poadvancepurchaseordercode;
    }

    @Basic
    @Column(name = "POADVANCELINENO", nullable = true)
    public Integer getPoadvancelineno() {
        return poadvancelineno;
    }

    public void setPoadvancelineno(Integer poadvancelineno) {
        this.poadvancelineno = poadvancelineno;
    }

    @Basic
    @Column(name = "PLANTINVOICEDIVISIONCODE", nullable = true, length = 3)
    public String getPlantinvoicedivisioncode() {
        return plantinvoicedivisioncode;
    }

    public void setPlantinvoicedivisioncode(String plantinvoicedivisioncode) {
        this.plantinvoicedivisioncode = plantinvoicedivisioncode;
    }

    @Basic
    @Column(name = "PLANTINVOICECODE", nullable = true, length = 15)
    public String getPlantinvoicecode() {
        return plantinvoicecode;
    }

    public void setPlantinvoicecode(String plantinvoicecode) {
        this.plantinvoicecode = plantinvoicecode;
    }

    @Basic
    @Column(name = "COMMERCIALINVOICEDIVISIONCODE", nullable = true, length = 3)
    public String getCommercialinvoicedivisioncode() {
        return commercialinvoicedivisioncode;
    }

    public void setCommercialinvoicedivisioncode(String commercialinvoicedivisioncode) {
        this.commercialinvoicedivisioncode = commercialinvoicedivisioncode;
    }

    @Basic
    @Column(name = "COMMERCIALINVOICECODE", nullable = true, length = 20)
    public String getCommercialinvoicecode() {
        return commercialinvoicecode;
    }

    public void setCommercialinvoicecode(String commercialinvoicecode) {
        this.commercialinvoicecode = commercialinvoicecode;
    }

    @Basic
    @Column(name = "SDCREDITPROVISIONALCOUNTERCODE", nullable = true, length = 8)
    public String getSdcreditprovisionalcountercode() {
        return sdcreditprovisionalcountercode;
    }

    public void setSdcreditprovisionalcountercode(String sdcreditprovisionalcountercode) {
        this.sdcreditprovisionalcountercode = sdcreditprovisionalcountercode;
    }

    @Basic
    @Column(name = "SDCREDITPROVISIONALCODE", nullable = true, length = 15)
    public String getSdcreditprovisionalcode() {
        return sdcreditprovisionalcode;
    }

    public void setSdcreditprovisionalcode(String sdcreditprovisionalcode) {
        this.sdcreditprovisionalcode = sdcreditprovisionalcode;
    }

    @Basic
    @Column(name = "DIRECTINVOICEDIVISIONCODE", nullable = true, length = 3)
    public String getDirectinvoicedivisioncode() {
        return directinvoicedivisioncode;
    }

    public void setDirectinvoicedivisioncode(String directinvoicedivisioncode) {
        this.directinvoicedivisioncode = directinvoicedivisioncode;
    }

    @Basic
    @Column(name = "DIRECTINVOICECOUNTERCODE", nullable = true, length = 8)
    public String getDirectinvoicecountercode() {
        return directinvoicecountercode;
    }

    public void setDirectinvoicecountercode(String directinvoicecountercode) {
        this.directinvoicecountercode = directinvoicecountercode;
    }

    @Basic
    @Column(name = "DIRECTINVOICECODE", nullable = true, length = 15)
    public String getDirectinvoicecode() {
        return directinvoicecode;
    }

    public void setDirectinvoicecode(String directinvoicecode) {
        this.directinvoicecode = directinvoicecode;
    }

    @Basic
    @Column(name = "MRNDIVISIONCODE", nullable = true, length = 3)
    public String getMrndivisioncode() {
        return mrndivisioncode;
    }

    public void setMrndivisioncode(String mrndivisioncode) {
        this.mrndivisioncode = mrndivisioncode;
    }

    @Basic
    @Column(name = "MRNMRNPREFIXCODE", nullable = true, length = 3)
    public String getMrnmrnprefixcode() {
        return mrnmrnprefixcode;
    }

    public void setMrnmrnprefixcode(String mrnmrnprefixcode) {
        this.mrnmrnprefixcode = mrnmrnprefixcode;
    }

    @Basic
    @Column(name = "MRNCODE", nullable = true)
    public String getMrncode() {
        return mrncode;
    }

    public void setMrncode(String mrncode) {
        this.mrncode = mrncode;
    }

    @Basic
    @Column(name = "STOCKTRNTRANSACTIONNUMBER", nullable = true, length = 15)
    public String getStocktrntransactionnumber() {
        return stocktrntransactionnumber;
    }

    public void setStocktrntransactionnumber(String stocktrntransactionnumber) {
        this.stocktrntransactionnumber = stocktrntransactionnumber;
    }

    @Basic
    @Column(name = "STOCKTRNTRNDETAILNUMBER", nullable = true)
    public Integer getStocktrntrndetailnumber() {
        return stocktrntrndetailnumber;
    }

    public void setStocktrntrndetailnumber(Integer stocktrntrndetailnumber) {
        this.stocktrntrndetailnumber = stocktrntrndetailnumber;
    }

    @Basic
    @Column(name = "CONSUMPTIONDIVISIONCODE", nullable = true, length = 3)
    public String getConsumptiondivisioncode() {
        return consumptiondivisioncode;
    }

    public void setConsumptiondivisioncode(String consumptiondivisioncode) {
        this.consumptiondivisioncode = consumptiondivisioncode;
    }

    @Basic
    @Column(name = "CONSUMPTIONITEMTYPECODE", nullable = true, length = 3)
    public String getConsumptionitemtypecode() {
        return consumptionitemtypecode;
    }

    public void setConsumptionitemtypecode(String consumptionitemtypecode) {
        this.consumptionitemtypecode = consumptionitemtypecode;
    }

    @Basic
    @Column(name = "CONSUMPTIONBUSINESSAREACODE", nullable = true, length = 50)
    public String getConsumptionbusinessareacode() {
        return consumptionbusinessareacode;
    }

    public void setConsumptionbusinessareacode(String consumptionbusinessareacode) {
        this.consumptionbusinessareacode = consumptionbusinessareacode;
    }

    @Basic
    @Column(name = "CONSUMPTIONSTARTDATE", nullable = true)
    public Date getConsumptionstartdate() {
        return consumptionstartdate;
    }

    public void setConsumptionstartdate(Date consumptionstartdate) {
        this.consumptionstartdate = consumptionstartdate;
    }

    @Basic
    @Column(name = "CONSUMPTIONENDDATE", nullable = true)
    public Date getConsumptionenddate() {
        return consumptionenddate;
    }

    public void setConsumptionenddate(Date consumptionenddate) {
        this.consumptionenddate = consumptionenddate;
    }

    @Basic
    @Column(name = "PAYROLLPOSTINGSNO", nullable = true)
    public Long getPayrollpostingsno() {
        return payrollpostingsno;
    }

    public void setPayrollpostingsno(Long payrollpostingsno) {
        this.payrollpostingsno = payrollpostingsno;
    }

    @Basic
    @Column(name = "PAYROLLPOSTINGPAYROLLCODE", nullable = true, length = 3)
    public String getPayrollpostingpayrollcode() {
        return payrollpostingpayrollcode;
    }

    public void setPayrollpostingpayrollcode(String payrollpostingpayrollcode) {
        this.payrollpostingpayrollcode = payrollpostingpayrollcode;
    }

    @Basic
    @Column(name = "PAYROLLPOSTINGPROCESSPERIOD", nullable = true)
    public String getPayrollpostingprocessperiod() {
        return payrollpostingprocessperiod;
    }

    public void setPayrollpostingprocessperiod(String payrollpostingprocessperiod) {
        this.payrollpostingprocessperiod = payrollpostingprocessperiod;
    }

    @Basic
    @Column(name = "INTERNALORDERCOUNTERCODE", nullable = true, length = 8)
    public String getInternalordercountercode() {
        return internalordercountercode;
    }

    public void setInternalordercountercode(String internalordercountercode) {
        this.internalordercountercode = internalordercountercode;
    }

    @Basic
    @Column(name = "INTERNALORDERCODE", nullable = true, length = 15)
    public String getInternalordercode() {
        return internalordercode;
    }

    public void setInternalordercode(String internalordercode) {
        this.internalordercode = internalordercode;
    }

    @Basic
    @Column(name = "RG23IIAEXCISEYEARREGNO", nullable = true, length = 30)
    public String getRg23Iiaexciseyearregno() {
        return rg23Iiaexciseyearregno;
    }

    public void setRg23Iiaexciseyearregno(String rg23Iiaexciseyearregno) {
        this.rg23Iiaexciseyearregno = rg23Iiaexciseyearregno;
    }

    @Basic
    @Column(name = "RG23IIAEXCISEYEARCODE", nullable = true, length = 4)
    public String getRg23Iiaexciseyearcode() {
        return rg23Iiaexciseyearcode;
    }

    public void setRg23Iiaexciseyearcode(String rg23Iiaexciseyearcode) {
        this.rg23Iiaexciseyearcode = rg23Iiaexciseyearcode;
    }

    @Basic
    @Column(name = "RG23IIACODE", nullable = true, length = 15)
    public String getRg23Iiacode() {
        return rg23Iiacode;
    }

    public void setRg23Iiacode(String rg23Iiacode) {
        this.rg23Iiacode = rg23Iiacode;
    }

    @Basic
    @Column(name = "RG23IICEXCISEYEARREGNO", nullable = true, length = 30)
    public String getRg23Iicexciseyearregno() {
        return rg23Iicexciseyearregno;
    }

    public void setRg23Iicexciseyearregno(String rg23Iicexciseyearregno) {
        this.rg23Iicexciseyearregno = rg23Iicexciseyearregno;
    }

    @Basic
    @Column(name = "RG23IICEXCISEYEARCODE", nullable = true, length = 4)
    public String getRg23Iicexciseyearcode() {
        return rg23Iicexciseyearcode;
    }

    public void setRg23Iicexciseyearcode(String rg23Iicexciseyearcode) {
        this.rg23Iicexciseyearcode = rg23Iicexciseyearcode;
    }

    @Basic
    @Column(name = "RG23IICCODE", nullable = true, length = 15)
    public String getRg23Iiccode() {
        return rg23Iiccode;
    }

    public void setRg23Iiccode(String rg23Iiccode) {
        this.rg23Iiccode = rg23Iiccode;
    }

    @Basic
    @Column(name = "EXPORTSHIPPINGBILLDIVISIONCODE", nullable = true, length = 3)
    public String getExportshippingbilldivisioncode() {
        return exportshippingbilldivisioncode;
    }

    public void setExportshippingbilldivisioncode(String exportshippingbilldivisioncode) {
        this.exportshippingbilldivisioncode = exportshippingbilldivisioncode;
    }

    @Basic
    @Column(name = "EXPORTSHIPPINGBILLCODE", nullable = true, length = 12)
    public String getExportshippingbillcode() {
        return exportshippingbillcode;
    }

    public void setExportshippingbillcode(String exportshippingbillcode) {
        this.exportshippingbillcode = exportshippingbillcode;
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
    @Column(name = "MRNINVOICENO", nullable = true, length = 25)
    public String getMrninvoiceno() {
        return mrninvoiceno;
    }

    public void setMrninvoiceno(String mrninvoiceno) {
        this.mrninvoiceno = mrninvoiceno;
    }

    @Basic
    @Column(name = "MRNINVOICEDATE", nullable = true)
    public Date getMrninvoicedate() {
        return mrninvoicedate;
    }

    public void setMrninvoicedate(Date mrninvoicedate) {
        this.mrninvoicedate = mrninvoicedate;
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
    @Column(name = "INVOICENO", nullable = true, length = 25)
    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    @Basic
    @Column(name = "INVOICEDATE", nullable = true)
    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    @Basic
    @Column(name = "CONSUMPTIONLGLWAREHOUSECODE", nullable = true, length = 8)
    public String getConsumptionlglwarehousecode() {
        return consumptionlglwarehousecode;
    }

    public void setConsumptionlglwarehousecode(String consumptionlglwarehousecode) {
        this.consumptionlglwarehousecode = consumptionlglwarehousecode;
    }

    @Basic
    @Column(name = "REFFINDOCCODE")
    public String getReffindoccode() {
        return reffindoccode;
    }

    public void setReffindoccode(String reffindoccode) {
        this.reffindoccode = reffindoccode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Findocument that = (Findocument) o;
        return Objects.equals(id, that.id) && Objects.equals(absversionnumber, that.absversionnumber) && Objects.equals(currentstatus, that.currentstatus) && Objects.equals(progressstatus, that.progressstatus) && Objects.equals(tdspercentage, that.tdspercentage) && Objects.equals(tdsapplicableamount, that.tdsapplicableamount) && Objects.equals(tdsamount, that.tdsamount) && Objects.equals(financedocumentdate, that.financedocumentdate) && Objects.equals(postingdate, that.postingdate) && Objects.equals(duedate, that.duedate) && Objects.equals(projectcode, that.projectcode) && Objects.equals(documentamount, that.documentamount) && Objects.equals(exchangerate, that.exchangerate) && Objects.equals(chequenumber, that.chequenumber) && Objects.equals(chequedate, that.chequedate) && Objects.equals(customerreference, that.customerreference) && Objects.equals(customerreferencedate, that.customerreferencedate) && Objects.equals(vendorreference, that.vendorreference) && Objects.equals(vendorreferencedate, that.vendorreferencedate) && Objects.equals(referencetext1, that.referencetext1) && Objects.equals(referencetext2, that.referencetext2) && Objects.equals(referencetext3, that.referencetext3) && Objects.equals(referencetext4, that.referencetext4) && Objects.equals(referencetext5, that.referencetext5) && Objects.equals(referenceamt1, that.referenceamt1) && Objects.equals(referenceamt2, that.referenceamt2) && Objects.equals(referenceamt3, that.referenceamt3) && Objects.equals(referenceamt4, that.referenceamt4) && Objects.equals(referenceamt5, that.referenceamt5) && Objects.equals(remark, that.remark) && Objects.equals(purchaseinvoicedivisioncode, that.purchaseinvoicedivisioncode) && Objects.equals(purinvoiceordprncsmsuptype, that.purinvoiceordprncsmsuptype) && Objects.equals(purinvoiceordprncsmsupcode, that.purinvoiceordprncsmsupcode) && Objects.equals(purchaseinvoicecode, that.purchaseinvoicecode) && Objects.equals(purchaseinvoiceinvoicedate, that.purchaseinvoiceinvoicedate) && Objects.equals(expenseinvoicedivisioncode, that.expenseinvoicedivisioncode) && Objects.equals(expenseinvoiceordprncsmsupte, that.expenseinvoiceordprncsmsupte) && Objects.equals(expenseinvoiceordprncsmsupcod, that.expenseinvoiceordprncsmsupcod) && Objects.equals(expenseinvoicecode, that.expenseinvoicecode) && Objects.equals(expenseinvoiceinvoicedate, that.expenseinvoiceinvoicedate) && Objects.equals(mrnrejmdmrnheaderdivisioncode, that.mrnrejmdmrnheaderdivisioncode) && Objects.equals(mrnrejmdmrnheadermrnprefixcode, that.mrnrejmdmrnheadermrnprefixcode) && Objects.equals(mrnrejmdmrnheadercode, that.mrnrejmdmrnheadercode) && Objects.equals(mrnrejmdlineid, that.mrnrejmdlineid) && Objects.equals(mrnrejrejectionlineid, that.mrnrejrejectionlineid) && Objects.equals(poadvancepurordercountercode, that.poadvancepurordercountercode) && Objects.equals(poadvancepurchaseordercode, that.poadvancepurchaseordercode) && Objects.equals(poadvancelineno, that.poadvancelineno) && Objects.equals(plantinvoicedivisioncode, that.plantinvoicedivisioncode) && Objects.equals(plantinvoicecode, that.plantinvoicecode) && Objects.equals(commercialinvoicedivisioncode, that.commercialinvoicedivisioncode) && Objects.equals(commercialinvoicecode, that.commercialinvoicecode) && Objects.equals(sdcreditprovisionalcountercode, that.sdcreditprovisionalcountercode) && Objects.equals(sdcreditprovisionalcode, that.sdcreditprovisionalcode) && Objects.equals(directinvoicedivisioncode, that.directinvoicedivisioncode) && Objects.equals(directinvoicecountercode, that.directinvoicecountercode) && Objects.equals(directinvoicecode, that.directinvoicecode) && Objects.equals(mrndivisioncode, that.mrndivisioncode) && Objects.equals(mrnmrnprefixcode, that.mrnmrnprefixcode) && Objects.equals(mrncode, that.mrncode) && Objects.equals(stocktrntransactionnumber, that.stocktrntransactionnumber) && Objects.equals(stocktrntrndetailnumber, that.stocktrntrndetailnumber) && Objects.equals(consumptiondivisioncode, that.consumptiondivisioncode) && Objects.equals(consumptionitemtypecode, that.consumptionitemtypecode) && Objects.equals(consumptionbusinessareacode, that.consumptionbusinessareacode) && Objects.equals(consumptionstartdate, that.consumptionstartdate) && Objects.equals(consumptionenddate, that.consumptionenddate) && Objects.equals(payrollpostingsno, that.payrollpostingsno) && Objects.equals(payrollpostingpayrollcode, that.payrollpostingpayrollcode) && Objects.equals(payrollpostingprocessperiod, that.payrollpostingprocessperiod) && Objects.equals(internalordercountercode, that.internalordercountercode) && Objects.equals(internalordercode, that.internalordercode) && Objects.equals(rg23Iiaexciseyearregno, that.rg23Iiaexciseyearregno) && Objects.equals(rg23Iiaexciseyearcode, that.rg23Iiaexciseyearcode) && Objects.equals(rg23Iiacode, that.rg23Iiacode) && Objects.equals(rg23Iicexciseyearregno, that.rg23Iicexciseyearregno) && Objects.equals(rg23Iicexciseyearcode, that.rg23Iicexciseyearcode) && Objects.equals(rg23Iiccode, that.rg23Iiccode) && Objects.equals(exportshippingbilldivisioncode, that.exportshippingbilldivisioncode) && Objects.equals(exportshippingbillcode, that.exportshippingbillcode) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(absuniqueid, that.absuniqueid) && Objects.equals(mrninvoiceno, that.mrninvoiceno) && Objects.equals(mrninvoicedate, that.mrninvoicedate) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc) && Objects.equals(invoiceno, that.invoiceno) && Objects.equals(invoicedate, that.invoicedate) && Objects.equals(consumptionlglwarehousecode, that.consumptionlglwarehousecode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, absversionnumber, currentstatus, progressstatus, tdspercentage, tdsapplicableamount, tdsamount, financedocumentdate, postingdate, duedate, projectcode, documentamount, exchangerate, chequenumber, chequedate, customerreference, customerreferencedate, vendorreference, vendorreferencedate, referencetext1, referencetext2, referencetext3, referencetext4, referencetext5, referenceamt1, referenceamt2, referenceamt3, referenceamt4, referenceamt5, remark, purchaseinvoicedivisioncode, purinvoiceordprncsmsuptype, purinvoiceordprncsmsupcode, purchaseinvoicecode, purchaseinvoiceinvoicedate, expenseinvoicedivisioncode, expenseinvoiceordprncsmsupte, expenseinvoiceordprncsmsupcod, expenseinvoicecode, expenseinvoiceinvoicedate, mrnrejmdmrnheaderdivisioncode, mrnrejmdmrnheadermrnprefixcode, mrnrejmdmrnheadercode, mrnrejmdlineid, mrnrejrejectionlineid, poadvancepurordercountercode, poadvancepurchaseordercode, poadvancelineno, plantinvoicedivisioncode, plantinvoicecode, commercialinvoicedivisioncode, commercialinvoicecode, sdcreditprovisionalcountercode, sdcreditprovisionalcode, directinvoicedivisioncode, directinvoicecountercode, directinvoicecode, mrndivisioncode, mrnmrnprefixcode, mrncode, stocktrntransactionnumber, stocktrntrndetailnumber, consumptiondivisioncode, consumptionitemtypecode, consumptionbusinessareacode, consumptionstartdate, consumptionenddate, payrollpostingsno, payrollpostingpayrollcode, payrollpostingprocessperiod, internalordercountercode, internalordercode, rg23Iiaexciseyearregno, rg23Iiaexciseyearcode, rg23Iiacode, rg23Iicexciseyearregno, rg23Iicexciseyearcode, rg23Iiccode, exportshippingbilldivisioncode, exportshippingbillcode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, mrninvoiceno, mrninvoicedate, creationdatetimeutc, lastupdatedatetimeutc, invoiceno, invoicedate, consumptionlglwarehousecode);
    }
}
