package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "findocumentbean")
public class Findocumentbean {
    private Long importautocounter;
    private String companycode;
    private String businessunitcode;
    private Short directentry;
    private Integer financialyearcode;
    private String documenttemplatecode;
    private Integer financemonthcode;
    private String code;
    private String documenttypecode;
    private String revaluationbusinessunitcode;
    private Integer revaluationfinancialyearcode;
    private Date revaluationrevaluationdate;
    private String revaluationprocesstype;
    private String currentstatus;
    private String progressstatus;
    private String customertype;
    private String customercode;
    private String suppliertype;
    private String suppliercode;
    private String employeecode;
    private String othercustomertype;
    private String othercustomercode;
    private String othervendortype;
    private String othervendorcode;
    private String glcode;
    private Short notdsapplicable;
    private String optdstdsteugengrouptypecode;
    private String optdstdstypecode;
    private String optdstdscode;
    private String optdstdsitaxcode;
    private BigDecimal tdspercentage;
    private BigDecimal tdsapplicableamount;
    private BigDecimal tdsamount;
    private String tdsglcode;
    private Date financedocumentdate;
    private Date postingdate;
    private Date duedate;
    private String termsofpaymentcode;
    private String statisticalgroupcode;
    private String projectcode;
    private BigDecimal creditamount;
    private BigDecimal debitamount;
    private BigDecimal documentamount;
    private Short dynamicclearing;
    private String documentcurrencycode;
    private BigDecimal exchangerate;
    private String doccompanycurrencycode;
    private String chequelotcode;
    private String chequenumber;
    private Date chequedate;
    private String customerreference;
    private Date customerreferencedate;
    private String vendorreference;
    private Date vendorreferencedate;
    private String reffindocbusinessunitcode;
    private Integer reffindocfinancialyearcode;
    private String reffindocdocumenttemplatecode;
    private String reffindocstatisticalgroupcode;
    private String reffindoccode;
    private String referencetext1;
    private String referencetext2;
    private String referencetext3;
    private String referencetext4;
    private String referencetext5;
    private String firstugrpugengrouptypecode;
    private String firstusergrpcode;
    private String sndugrpugenericgrouptypecode;
    private String secondusergrpcode;
    private String thirdugrpugengrouptypecode;
    private String thirdusergrpcode;
    private String frugrpugenericgrouptypecode;
    private String fourthusergrpcode;
    private String fifthugrpugengrouptypecode;
    private String fifthusergrpcode;
    private BigDecimal referenceamt1;
    private BigDecimal referenceamt2;
    private BigDecimal referenceamt3;
    private BigDecimal referenceamt4;
    private BigDecimal referenceamt5;
    private String remark;
    private String profitcentercode;
    private String costcentercode;
    private String agent1Code;
    private String agent2Code;
    private String agent3Code;
    private String agent4Code;
    private String agent5Code;
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
    private Integer mrnrejmdmrnheadercode;
    private Integer mrnrejmdlineid;
    private Integer mrnrejrejectionlineid;
    private String poadvancepurordercountercode;
    private String poadvancepurchaseordercode;
    private Integer poadvancelineno;
    private String empladvancelrdivisioncode;
    private String empladvancelremployeeidcode;
    private Integer empladvancelrequestloantype;
    private String empladvancelrequestloancode;
    private Integer empladvancelrloanvoucherno;
    private Long emploanadvancecode;
    private String plantinvoicedivisioncode;
    private String plantinvoicecode;
    private String commercialinvoicedivisioncode;
    private String commercialinvoicecode;
    private String sdcreditprovisionalcountercode;
    private String sdcreditprovisionalcode;
    private String directinvoicedivisioncode;
    private String directinvoicecountercode;
    private String directinvoicecode;
    private String invoiceno;
    private Date invoicedate;
    private String mrndivisioncode;
    private String mrnmrnprefixcode;
    private Integer mrncode;
    private String stocktrntransactionnumber;
    private Integer stocktrntrndetailnumber;
    private String consumptiondivisioncode;
    private String consumptionitemtypecode;
    private String consumptionbusinessareacode;
    private Date consumptionstartdate;
    private Date consumptionenddate;
    private String consumptionlglwarehousecode;
    private Long payrollpostingsno;
    private String payrollpostingpayrollcode;
    private Integer payrollpostingprocessperiod;
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
    private String mrninvoiceno;
    private Date mrninvoicedate;
    private Integer wsoperation;
    private String impoperationuser;
    private Integer importstatus;
    private Timestamp impcreationdatetime;
    private String impcreationuser;
    private Timestamp implastupdatedatetime;
    private String implastupdateuser;
    private Timestamp importdatetime;
    private Integer retrynr;
    private Long nextretry;
    private Long importid;

    @Id
    @Column(name = "IMPORTAUTOCOUNTER", nullable = false)
    public Long getImportautocounter() {
        return importautocounter;
    }

    public void setImportautocounter(Long importautocounter) {
        this.importautocounter = importautocounter;
    }

    @Basic
    @Column(name = "COMPANYCODE", nullable = true, length = 3)
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Basic
    @Column(name = "BUSINESSUNITCODE", nullable = true, length = 10)
    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    @Basic
    @Column(name = "DIRECTENTRY", nullable = false)
    public Short getDirectentry() {
        return directentry;
    }

    public void setDirectentry(Short directentry) {
        this.directentry = directentry;
    }

    @Basic
    @Column(name = "FINANCIALYEARCODE", nullable = true, precision = 0)
    public Integer getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(Integer financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    @Basic
    @Column(name = "DOCUMENTTEMPLATECODE", nullable = true, length = 3)
    public String getDocumenttemplatecode() {
        return documenttemplatecode;
    }

    public void setDocumenttemplatecode(String documenttemplatecode) {
        this.documenttemplatecode = documenttemplatecode;
    }

    @Basic
    @Column(name = "FINANCEMONTHCODE", nullable = false)
    public Integer getFinancemonthcode() {
        return financemonthcode;
    }

    public void setFinancemonthcode(Integer financemonthcode) {
        this.financemonthcode = financemonthcode;
    }

    @Basic
    @Column(name = "CODE", nullable = true, length = 15)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "DOCUMENTTYPECODE", nullable = true, length = 3)
    public String getDocumenttypecode() {
        return documenttypecode;
    }

    public void setDocumenttypecode(String documenttypecode) {
        this.documenttypecode = documenttypecode;
    }

    @Basic
    @Column(name = "REVALUATIONBUSINESSUNITCODE", nullable = true, length = 10)
    public String getRevaluationbusinessunitcode() {
        return revaluationbusinessunitcode;
    }

    public void setRevaluationbusinessunitcode(String revaluationbusinessunitcode) {
        this.revaluationbusinessunitcode = revaluationbusinessunitcode;
    }

    @Basic
    @Column(name = "REVALUATIONFINANCIALYEARCODE", nullable = true, precision = 0)
    public Integer getRevaluationfinancialyearcode() {
        return revaluationfinancialyearcode;
    }

    public void setRevaluationfinancialyearcode(Integer revaluationfinancialyearcode) {
        this.revaluationfinancialyearcode = revaluationfinancialyearcode;
    }

    @Basic
    @Column(name = "REVALUATIONREVALUATIONDATE", nullable = true)
    public Date getRevaluationrevaluationdate() {
        return revaluationrevaluationdate;
    }

    public void setRevaluationrevaluationdate(Date revaluationrevaluationdate) {
        this.revaluationrevaluationdate = revaluationrevaluationdate;
    }

    @Basic
    @Column(name = "REVALUATIONPROCESSTYPE", nullable = true, length = 1)
    public String getRevaluationprocesstype() {
        return revaluationprocesstype;
    }

    public void setRevaluationprocesstype(String revaluationprocesstype) {
        this.revaluationprocesstype = revaluationprocesstype;
    }

    @Basic
    @Column(name = "CURRENTSTATUS", nullable = true, length = 2)
    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    @Basic
    @Column(name = "PROGRESSSTATUS", nullable = true, length = 2)
    public String getProgressstatus() {
        return progressstatus;
    }

    public void setProgressstatus(String progressstatus) {
        this.progressstatus = progressstatus;
    }

    @Basic
    @Column(name = "CUSTOMERTYPE", nullable = true, length = 1)
    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    @Basic
    @Column(name = "CUSTOMERCODE", nullable = true, length = 8)
    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    @Basic
    @Column(name = "SUPPLIERTYPE", nullable = true, length = 1)
    public String getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    @Basic
    @Column(name = "SUPPLIERCODE", nullable = true, length = 8)
    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    @Basic
    @Column(name = "EMPLOYEECODE", nullable = true, length = 9)
    public String getEmployeecode() {
        return employeecode;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode;
    }

    @Basic
    @Column(name = "OTHERCUSTOMERTYPE", nullable = true, length = 1)
    public String getOthercustomertype() {
        return othercustomertype;
    }

    public void setOthercustomertype(String othercustomertype) {
        this.othercustomertype = othercustomertype;
    }

    @Basic
    @Column(name = "OTHERCUSTOMERCODE", nullable = true, length = 8)
    public String getOthercustomercode() {
        return othercustomercode;
    }

    public void setOthercustomercode(String othercustomercode) {
        this.othercustomercode = othercustomercode;
    }

    @Basic
    @Column(name = "OTHERVENDORTYPE", nullable = true, length = 1)
    public String getOthervendortype() {
        return othervendortype;
    }

    public void setOthervendortype(String othervendortype) {
        this.othervendortype = othervendortype;
    }

    @Basic
    @Column(name = "OTHERVENDORCODE", nullable = true, length = 8)
    public String getOthervendorcode() {
        return othervendorcode;
    }

    public void setOthervendorcode(String othervendorcode) {
        this.othervendorcode = othervendorcode;
    }

    @Basic
    @Column(name = "GLCODE", nullable = true, length = 20)
    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    @Basic
    @Column(name = "NOTDSAPPLICABLE", nullable = false)
    public Short getNotdsapplicable() {
        return notdsapplicable;
    }

    public void setNotdsapplicable(Short notdsapplicable) {
        this.notdsapplicable = notdsapplicable;
    }

    @Basic
    @Column(name = "OPTDSTDSTEUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getOptdstdsteugengrouptypecode() {
        return optdstdsteugengrouptypecode;
    }

    public void setOptdstdsteugengrouptypecode(String optdstdsteugengrouptypecode) {
        this.optdstdsteugengrouptypecode = optdstdsteugengrouptypecode;
    }

    @Basic
    @Column(name = "OPTDSTDSTYPECODE", nullable = true, length = 10)
    public String getOptdstdstypecode() {
        return optdstdstypecode;
    }

    public void setOptdstdstypecode(String optdstdstypecode) {
        this.optdstdstypecode = optdstdstypecode;
    }

    @Basic
    @Column(name = "OPTDSTDSCODE", nullable = true, length = 6)
    public String getOptdstdscode() {
        return optdstdscode;
    }

    public void setOptdstdscode(String optdstdscode) {
        this.optdstdscode = optdstdscode;
    }

    @Basic
    @Column(name = "OPTDSTDSITAXCODE", nullable = true, length = 3)
    public String getOptdstdsitaxcode() {
        return optdstdsitaxcode;
    }

    public void setOptdstdsitaxcode(String optdstdsitaxcode) {
        this.optdstdsitaxcode = optdstdsitaxcode;
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
    @Column(name = "TDSGLCODE", nullable = true, length = 20)
    public String getTdsglcode() {
        return tdsglcode;
    }

    public void setTdsglcode(String tdsglcode) {
        this.tdsglcode = tdsglcode;
    }

    @Basic
    @Column(name = "FINANCEDOCUMENTDATE", nullable = true)
    public Date getFinancedocumentdate() {
        return financedocumentdate;
    }

    public void setFinancedocumentdate(Date financedocumentdate) {
        this.financedocumentdate = financedocumentdate;
    }

    @Basic
    @Column(name = "POSTINGDATE", nullable = true)
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
    @Column(name = "TERMSOFPAYMENTCODE", nullable = true, length = 3)
    public String getTermsofpaymentcode() {
        return termsofpaymentcode;
    }

    public void setTermsofpaymentcode(String termsofpaymentcode) {
        this.termsofpaymentcode = termsofpaymentcode;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCODE", nullable = true, length = 6)
    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
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
    @Column(name = "CREDITAMOUNT", nullable = true, precision = 5)
    public BigDecimal getCreditamount() {
        return creditamount;
    }

    public void setCreditamount(BigDecimal creditamount) {
        this.creditamount = creditamount;
    }

    @Basic
    @Column(name = "DEBITAMOUNT", nullable = true, precision = 5)
    public BigDecimal getDebitamount() {
        return debitamount;
    }

    public void setDebitamount(BigDecimal debitamount) {
        this.debitamount = debitamount;
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
    @Column(name = "DYNAMICCLEARING", nullable = false)
    public Short getDynamicclearing() {
        return dynamicclearing;
    }

    public void setDynamicclearing(Short dynamicclearing) {
        this.dynamicclearing = dynamicclearing;
    }

    @Basic
    @Column(name = "DOCUMENTCURRENCYCODE", nullable = true, length = 4)
    public String getDocumentcurrencycode() {
        return documentcurrencycode;
    }

    public void setDocumentcurrencycode(String documentcurrencycode) {
        this.documentcurrencycode = documentcurrencycode;
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
    @Column(name = "DOCCOMPANYCURRENCYCODE", nullable = true, length = 4)
    public String getDoccompanycurrencycode() {
        return doccompanycurrencycode;
    }

    public void setDoccompanycurrencycode(String doccompanycurrencycode) {
        this.doccompanycurrencycode = doccompanycurrencycode;
    }

    @Basic
    @Column(name = "CHEQUELOTCODE", nullable = true, length = 10)
    public String getChequelotcode() {
        return chequelotcode;
    }

    public void setChequelotcode(String chequelotcode) {
        this.chequelotcode = chequelotcode;
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
    @Column(name = "REFFINDOCBUSINESSUNITCODE", nullable = true, length = 10)
    public String getReffindocbusinessunitcode() {
        return reffindocbusinessunitcode;
    }

    public void setReffindocbusinessunitcode(String reffindocbusinessunitcode) {
        this.reffindocbusinessunitcode = reffindocbusinessunitcode;
    }

    @Basic
    @Column(name = "REFFINDOCFINANCIALYEARCODE", nullable = true, precision = 0)
    public Integer getReffindocfinancialyearcode() {
        return reffindocfinancialyearcode;
    }

    public void setReffindocfinancialyearcode(Integer reffindocfinancialyearcode) {
        this.reffindocfinancialyearcode = reffindocfinancialyearcode;
    }

    @Basic
    @Column(name = "REFFINDOCDOCUMENTTEMPLATECODE", nullable = true, length = 3)
    public String getReffindocdocumenttemplatecode() {
        return reffindocdocumenttemplatecode;
    }

    public void setReffindocdocumenttemplatecode(String reffindocdocumenttemplatecode) {
        this.reffindocdocumenttemplatecode = reffindocdocumenttemplatecode;
    }

    @Basic
    @Column(name = "REFFINDOCSTATISTICALGROUPCODE", nullable = true, length = 6)
    public String getReffindocstatisticalgroupcode() {
        return reffindocstatisticalgroupcode;
    }

    public void setReffindocstatisticalgroupcode(String reffindocstatisticalgroupcode) {
        this.reffindocstatisticalgroupcode = reffindocstatisticalgroupcode;
    }

    @Basic
    @Column(name = "REFFINDOCCODE", nullable = true, length = 15)
    public String getReffindoccode() {
        return reffindoccode;
    }

    public void setReffindoccode(String reffindoccode) {
        this.reffindoccode = reffindoccode;
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
    @Column(name = "FIRSTUGRPUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getFirstugrpugengrouptypecode() {
        return firstugrpugengrouptypecode;
    }

    public void setFirstugrpugengrouptypecode(String firstugrpugengrouptypecode) {
        this.firstugrpugengrouptypecode = firstugrpugengrouptypecode;
    }

    @Basic
    @Column(name = "FIRSTUSERGRPCODE", nullable = true, length = 10)
    public String getFirstusergrpcode() {
        return firstusergrpcode;
    }

    public void setFirstusergrpcode(String firstusergrpcode) {
        this.firstusergrpcode = firstusergrpcode;
    }

    @Basic
    @Column(name = "SNDUGRPUGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getSndugrpugenericgrouptypecode() {
        return sndugrpugenericgrouptypecode;
    }

    public void setSndugrpugenericgrouptypecode(String sndugrpugenericgrouptypecode) {
        this.sndugrpugenericgrouptypecode = sndugrpugenericgrouptypecode;
    }

    @Basic
    @Column(name = "SECONDUSERGRPCODE", nullable = true, length = 10)
    public String getSecondusergrpcode() {
        return secondusergrpcode;
    }

    public void setSecondusergrpcode(String secondusergrpcode) {
        this.secondusergrpcode = secondusergrpcode;
    }

    @Basic
    @Column(name = "THIRDUGRPUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getThirdugrpugengrouptypecode() {
        return thirdugrpugengrouptypecode;
    }

    public void setThirdugrpugengrouptypecode(String thirdugrpugengrouptypecode) {
        this.thirdugrpugengrouptypecode = thirdugrpugengrouptypecode;
    }

    @Basic
    @Column(name = "THIRDUSERGRPCODE", nullable = true, length = 10)
    public String getThirdusergrpcode() {
        return thirdusergrpcode;
    }

    public void setThirdusergrpcode(String thirdusergrpcode) {
        this.thirdusergrpcode = thirdusergrpcode;
    }

    @Basic
    @Column(name = "FRUGRPUGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getFrugrpugenericgrouptypecode() {
        return frugrpugenericgrouptypecode;
    }

    public void setFrugrpugenericgrouptypecode(String frugrpugenericgrouptypecode) {
        this.frugrpugenericgrouptypecode = frugrpugenericgrouptypecode;
    }

    @Basic
    @Column(name = "FOURTHUSERGRPCODE", nullable = true, length = 10)
    public String getFourthusergrpcode() {
        return fourthusergrpcode;
    }

    public void setFourthusergrpcode(String fourthusergrpcode) {
        this.fourthusergrpcode = fourthusergrpcode;
    }

    @Basic
    @Column(name = "FIFTHUGRPUGENGROUPTYPECODE", nullable = true, length = 3)
    public String getFifthugrpugengrouptypecode() {
        return fifthugrpugengrouptypecode;
    }

    public void setFifthugrpugengrouptypecode(String fifthugrpugengrouptypecode) {
        this.fifthugrpugengrouptypecode = fifthugrpugengrouptypecode;
    }

    @Basic
    @Column(name = "FIFTHUSERGRPCODE", nullable = true, length = 10)
    public String getFifthusergrpcode() {
        return fifthusergrpcode;
    }

    public void setFifthusergrpcode(String fifthusergrpcode) {
        this.fifthusergrpcode = fifthusergrpcode;
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
    @Column(name = "PROFITCENTERCODE", nullable = true, length = 10)
    public String getProfitcentercode() {
        return profitcentercode;
    }

    public void setProfitcentercode(String profitcentercode) {
        this.profitcentercode = profitcentercode;
    }

    @Basic
    @Column(name = "COSTCENTERCODE", nullable = true, length = 20)
    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    @Basic
    @Column(name = "AGENT1CODE", nullable = true, length = 3)
    public String getAgent1Code() {
        return agent1Code;
    }

    public void setAgent1Code(String agent1Code) {
        this.agent1Code = agent1Code;
    }

    @Basic
    @Column(name = "AGENT2CODE", nullable = true, length = 3)
    public String getAgent2Code() {
        return agent2Code;
    }

    public void setAgent2Code(String agent2Code) {
        this.agent2Code = agent2Code;
    }

    @Basic
    @Column(name = "AGENT3CODE", nullable = true, length = 3)
    public String getAgent3Code() {
        return agent3Code;
    }

    public void setAgent3Code(String agent3Code) {
        this.agent3Code = agent3Code;
    }

    @Basic
    @Column(name = "AGENT4CODE", nullable = true, length = 3)
    public String getAgent4Code() {
        return agent4Code;
    }

    public void setAgent4Code(String agent4Code) {
        this.agent4Code = agent4Code;
    }

    @Basic
    @Column(name = "AGENT5CODE", nullable = true, length = 3)
    public String getAgent5Code() {
        return agent5Code;
    }

    public void setAgent5Code(String agent5Code) {
        this.agent5Code = agent5Code;
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
    public Integer getMrnrejmdmrnheadercode() {
        return mrnrejmdmrnheadercode;
    }

    public void setMrnrejmdmrnheadercode(Integer mrnrejmdmrnheadercode) {
        this.mrnrejmdmrnheadercode = mrnrejmdmrnheadercode;
    }

    @Basic
    @Column(name = "MRNREJMDLINEID", nullable = false)
    public Integer getMrnrejmdlineid() {
        return mrnrejmdlineid;
    }

    public void setMrnrejmdlineid(Integer mrnrejmdlineid) {
        this.mrnrejmdlineid = mrnrejmdlineid;
    }

    @Basic
    @Column(name = "MRNREJREJECTIONLINEID", nullable = false)
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
    @Column(name = "POADVANCELINENO", nullable = false)
    public Integer getPoadvancelineno() {
        return poadvancelineno;
    }

    public void setPoadvancelineno(Integer poadvancelineno) {
        this.poadvancelineno = poadvancelineno;
    }

    @Basic
    @Column(name = "EMPLADVANCELRDIVISIONCODE", nullable = true, length = 3)
    public String getEmpladvancelrdivisioncode() {
        return empladvancelrdivisioncode;
    }

    public void setEmpladvancelrdivisioncode(String empladvancelrdivisioncode) {
        this.empladvancelrdivisioncode = empladvancelrdivisioncode;
    }

    @Basic
    @Column(name = "EMPLADVANCELREMPLOYEEIDCODE", nullable = true, length = 9)
    public String getEmpladvancelremployeeidcode() {
        return empladvancelremployeeidcode;
    }

    public void setEmpladvancelremployeeidcode(String empladvancelremployeeidcode) {
        this.empladvancelremployeeidcode = empladvancelremployeeidcode;
    }

    @Basic
    @Column(name = "EMPLADVANCELREQUESTLOANTYPE", nullable = false)
    public Integer getEmpladvancelrequestloantype() {
        return empladvancelrequestloantype;
    }

    public void setEmpladvancelrequestloantype(Integer empladvancelrequestloantype) {
        this.empladvancelrequestloantype = empladvancelrequestloantype;
    }

    @Basic
    @Column(name = "EMPLADVANCELREQUESTLOANCODE", nullable = true, length = 4)
    public String getEmpladvancelrequestloancode() {
        return empladvancelrequestloancode;
    }

    public void setEmpladvancelrequestloancode(String empladvancelrequestloancode) {
        this.empladvancelrequestloancode = empladvancelrequestloancode;
    }

    @Basic
    @Column(name = "EMPLADVANCELRLOANVOUCHERNO", nullable = true, precision = 0)
    public Integer getEmpladvancelrloanvoucherno() {
        return empladvancelrloanvoucherno;
    }

    public void setEmpladvancelrloanvoucherno(Integer empladvancelrloanvoucherno) {
        this.empladvancelrloanvoucherno = empladvancelrloanvoucherno;
    }

    @Basic
    @Column(name = "EMPLOANADVANCECODE", nullable = false)
    public Long getEmploanadvancecode() {
        return emploanadvancecode;
    }

    public void setEmploanadvancecode(Long emploanadvancecode) {
        this.emploanadvancecode = emploanadvancecode;
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
    @Column(name = "MRNCODE", nullable = true, precision = 0)
    public Integer getMrncode() {
        return mrncode;
    }

    public void setMrncode(Integer mrncode) {
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
    @Column(name = "STOCKTRNTRNDETAILNUMBER", nullable = false)
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
    @Column(name = "CONSUMPTIONLGLWAREHOUSECODE", nullable = true, length = 8)
    public String getConsumptionlglwarehousecode() {
        return consumptionlglwarehousecode;
    }

    public void setConsumptionlglwarehousecode(String consumptionlglwarehousecode) {
        this.consumptionlglwarehousecode = consumptionlglwarehousecode;
    }

    @Basic
    @Column(name = "PAYROLLPOSTINGSNO", nullable = false)
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
    @Column(name = "PAYROLLPOSTINGPROCESSPERIOD", nullable = false)
    public Integer getPayrollpostingprocessperiod() {
        return payrollpostingprocessperiod;
    }

    public void setPayrollpostingprocessperiod(Integer payrollpostingprocessperiod) {
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
    @Column(name = "WSOPERATION", nullable = false)
    public Integer getWsoperation() {
        return wsoperation;
    }

    public void setWsoperation(Integer wsoperation) {
        this.wsoperation = wsoperation;
    }

    @Basic
    @Column(name = "IMPOPERATIONUSER", nullable = true, length = 25)
    public String getImpoperationuser() {
        return impoperationuser;
    }

    public void setImpoperationuser(String impoperationuser) {
        this.impoperationuser = impoperationuser;
    }

    @Basic
    @Column(name = "IMPORTSTATUS", nullable = false)
    public Integer getImportstatus() {
        return importstatus;
    }

    public void setImportstatus(Integer importstatus) {
        this.importstatus = importstatus;
    }

    @Basic
    @Column(name = "IMPCREATIONDATETIME", nullable = true)
    public Timestamp getImpcreationdatetime() {
        return impcreationdatetime;
    }

    public void setImpcreationdatetime(Timestamp impcreationdatetime) {
        this.impcreationdatetime = impcreationdatetime;
    }

    @Basic
    @Column(name = "IMPCREATIONUSER", nullable = true, length = 25)
    public String getImpcreationuser() {
        return impcreationuser;
    }

    public void setImpcreationuser(String impcreationuser) {
        this.impcreationuser = impcreationuser;
    }

    @Basic
    @Column(name = "IMPLASTUPDATEDATETIME", nullable = true)
    public Timestamp getImplastupdatedatetime() {
        return implastupdatedatetime;
    }

    public void setImplastupdatedatetime(Timestamp implastupdatedatetime) {
        this.implastupdatedatetime = implastupdatedatetime;
    }

    @Basic
    @Column(name = "IMPLASTUPDATEUSER", nullable = true, length = 25)
    public String getImplastupdateuser() {
        return implastupdateuser;
    }

    public void setImplastupdateuser(String implastupdateuser) {
        this.implastupdateuser = implastupdateuser;
    }

    @Basic
    @Column(name = "IMPORTDATETIME", nullable = true)
    public Timestamp getImportdatetime() {
        return importdatetime;
    }

    public void setImportdatetime(Timestamp importdatetime) {
        this.importdatetime = importdatetime;
    }

    @Basic
    @Column(name = "RETRYNR", nullable = false)
    public Integer getRetrynr() {
        return retrynr;
    }

    public void setRetrynr(Integer retrynr) {
        this.retrynr = retrynr;
    }

    @Basic
    @Column(name = "NEXTRETRY", nullable = false)
    public Long getNextretry() {
        return nextretry;
    }

    public void setNextretry(Long nextretry) {
        this.nextretry = nextretry;
    }

    @Basic
    @Column(name = "IMPORTID", nullable = false)
    public Long getImportid() {
        return importid;
    }

    public void setImportid(Long importid) {
        this.importid = importid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Findocumentbean that = (Findocumentbean) o;
        return Objects.equals(importautocounter, that.importautocounter) && Objects.equals(companycode, that.companycode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(directentry, that.directentry) && Objects.equals(financialyearcode, that.financialyearcode) && Objects.equals(documenttemplatecode, that.documenttemplatecode) && Objects.equals(financemonthcode, that.financemonthcode) && Objects.equals(code, that.code) && Objects.equals(documenttypecode, that.documenttypecode) && Objects.equals(revaluationbusinessunitcode, that.revaluationbusinessunitcode) && Objects.equals(revaluationfinancialyearcode, that.revaluationfinancialyearcode) && Objects.equals(revaluationrevaluationdate, that.revaluationrevaluationdate) && Objects.equals(revaluationprocesstype, that.revaluationprocesstype) && Objects.equals(currentstatus, that.currentstatus) && Objects.equals(progressstatus, that.progressstatus) && Objects.equals(customertype, that.customertype) && Objects.equals(customercode, that.customercode) && Objects.equals(suppliertype, that.suppliertype) && Objects.equals(suppliercode, that.suppliercode) && Objects.equals(employeecode, that.employeecode) && Objects.equals(othercustomertype, that.othercustomertype) && Objects.equals(othercustomercode, that.othercustomercode) && Objects.equals(othervendortype, that.othervendortype) && Objects.equals(othervendorcode, that.othervendorcode) && Objects.equals(glcode, that.glcode) && Objects.equals(notdsapplicable, that.notdsapplicable) && Objects.equals(optdstdsteugengrouptypecode, that.optdstdsteugengrouptypecode) && Objects.equals(optdstdstypecode, that.optdstdstypecode) && Objects.equals(optdstdscode, that.optdstdscode) && Objects.equals(optdstdsitaxcode, that.optdstdsitaxcode) && Objects.equals(tdspercentage, that.tdspercentage) && Objects.equals(tdsapplicableamount, that.tdsapplicableamount) && Objects.equals(tdsamount, that.tdsamount) && Objects.equals(tdsglcode, that.tdsglcode) && Objects.equals(financedocumentdate, that.financedocumentdate) && Objects.equals(postingdate, that.postingdate) && Objects.equals(duedate, that.duedate) && Objects.equals(termsofpaymentcode, that.termsofpaymentcode) && Objects.equals(statisticalgroupcode, that.statisticalgroupcode) && Objects.equals(projectcode, that.projectcode) && Objects.equals(creditamount, that.creditamount) && Objects.equals(debitamount, that.debitamount) && Objects.equals(documentamount, that.documentamount) && Objects.equals(dynamicclearing, that.dynamicclearing) && Objects.equals(documentcurrencycode, that.documentcurrencycode) && Objects.equals(exchangerate, that.exchangerate) && Objects.equals(doccompanycurrencycode, that.doccompanycurrencycode) && Objects.equals(chequelotcode, that.chequelotcode) && Objects.equals(chequenumber, that.chequenumber) && Objects.equals(chequedate, that.chequedate) && Objects.equals(customerreference, that.customerreference) && Objects.equals(customerreferencedate, that.customerreferencedate) && Objects.equals(vendorreference, that.vendorreference) && Objects.equals(vendorreferencedate, that.vendorreferencedate) && Objects.equals(reffindocbusinessunitcode, that.reffindocbusinessunitcode) && Objects.equals(reffindocfinancialyearcode, that.reffindocfinancialyearcode) && Objects.equals(reffindocdocumenttemplatecode, that.reffindocdocumenttemplatecode) && Objects.equals(reffindocstatisticalgroupcode, that.reffindocstatisticalgroupcode) && Objects.equals(reffindoccode, that.reffindoccode) && Objects.equals(referencetext1, that.referencetext1) && Objects.equals(referencetext2, that.referencetext2) && Objects.equals(referencetext3, that.referencetext3) && Objects.equals(referencetext4, that.referencetext4) && Objects.equals(referencetext5, that.referencetext5) && Objects.equals(firstugrpugengrouptypecode, that.firstugrpugengrouptypecode) && Objects.equals(firstusergrpcode, that.firstusergrpcode) && Objects.equals(sndugrpugenericgrouptypecode, that.sndugrpugenericgrouptypecode) && Objects.equals(secondusergrpcode, that.secondusergrpcode) && Objects.equals(thirdugrpugengrouptypecode, that.thirdugrpugengrouptypecode) && Objects.equals(thirdusergrpcode, that.thirdusergrpcode) && Objects.equals(frugrpugenericgrouptypecode, that.frugrpugenericgrouptypecode) && Objects.equals(fourthusergrpcode, that.fourthusergrpcode) && Objects.equals(fifthugrpugengrouptypecode, that.fifthugrpugengrouptypecode) && Objects.equals(fifthusergrpcode, that.fifthusergrpcode) && Objects.equals(referenceamt1, that.referenceamt1) && Objects.equals(referenceamt2, that.referenceamt2) && Objects.equals(referenceamt3, that.referenceamt3) && Objects.equals(referenceamt4, that.referenceamt4) && Objects.equals(referenceamt5, that.referenceamt5) && Objects.equals(remark, that.remark) && Objects.equals(profitcentercode, that.profitcentercode) && Objects.equals(costcentercode, that.costcentercode) && Objects.equals(agent1Code, that.agent1Code) && Objects.equals(agent2Code, that.agent2Code) && Objects.equals(agent3Code, that.agent3Code) && Objects.equals(agent4Code, that.agent4Code) && Objects.equals(agent5Code, that.agent5Code) && Objects.equals(purchaseinvoicedivisioncode, that.purchaseinvoicedivisioncode) && Objects.equals(purinvoiceordprncsmsuptype, that.purinvoiceordprncsmsuptype) && Objects.equals(purinvoiceordprncsmsupcode, that.purinvoiceordprncsmsupcode) && Objects.equals(purchaseinvoicecode, that.purchaseinvoicecode) && Objects.equals(purchaseinvoiceinvoicedate, that.purchaseinvoiceinvoicedate) && Objects.equals(expenseinvoicedivisioncode, that.expenseinvoicedivisioncode) && Objects.equals(expenseinvoiceordprncsmsupte, that.expenseinvoiceordprncsmsupte) && Objects.equals(expenseinvoiceordprncsmsupcod, that.expenseinvoiceordprncsmsupcod) && Objects.equals(expenseinvoicecode, that.expenseinvoicecode) && Objects.equals(expenseinvoiceinvoicedate, that.expenseinvoiceinvoicedate) && Objects.equals(mrnrejmdmrnheaderdivisioncode, that.mrnrejmdmrnheaderdivisioncode) && Objects.equals(mrnrejmdmrnheadermrnprefixcode, that.mrnrejmdmrnheadermrnprefixcode) && Objects.equals(mrnrejmdmrnheadercode, that.mrnrejmdmrnheadercode) && Objects.equals(mrnrejmdlineid, that.mrnrejmdlineid) && Objects.equals(mrnrejrejectionlineid, that.mrnrejrejectionlineid) && Objects.equals(poadvancepurordercountercode, that.poadvancepurordercountercode) && Objects.equals(poadvancepurchaseordercode, that.poadvancepurchaseordercode) && Objects.equals(poadvancelineno, that.poadvancelineno) && Objects.equals(empladvancelrdivisioncode, that.empladvancelrdivisioncode) && Objects.equals(empladvancelremployeeidcode, that.empladvancelremployeeidcode) && Objects.equals(empladvancelrequestloantype, that.empladvancelrequestloantype) && Objects.equals(empladvancelrequestloancode, that.empladvancelrequestloancode) && Objects.equals(empladvancelrloanvoucherno, that.empladvancelrloanvoucherno) && Objects.equals(emploanadvancecode, that.emploanadvancecode) && Objects.equals(plantinvoicedivisioncode, that.plantinvoicedivisioncode) && Objects.equals(plantinvoicecode, that.plantinvoicecode) && Objects.equals(commercialinvoicedivisioncode, that.commercialinvoicedivisioncode) && Objects.equals(commercialinvoicecode, that.commercialinvoicecode) && Objects.equals(sdcreditprovisionalcountercode, that.sdcreditprovisionalcountercode) && Objects.equals(sdcreditprovisionalcode, that.sdcreditprovisionalcode) && Objects.equals(directinvoicedivisioncode, that.directinvoicedivisioncode) && Objects.equals(directinvoicecountercode, that.directinvoicecountercode) && Objects.equals(directinvoicecode, that.directinvoicecode) && Objects.equals(invoiceno, that.invoiceno) && Objects.equals(invoicedate, that.invoicedate) && Objects.equals(mrndivisioncode, that.mrndivisioncode) && Objects.equals(mrnmrnprefixcode, that.mrnmrnprefixcode) && Objects.equals(mrncode, that.mrncode) && Objects.equals(stocktrntransactionnumber, that.stocktrntransactionnumber) && Objects.equals(stocktrntrndetailnumber, that.stocktrntrndetailnumber) && Objects.equals(consumptiondivisioncode, that.consumptiondivisioncode) && Objects.equals(consumptionitemtypecode, that.consumptionitemtypecode) && Objects.equals(consumptionbusinessareacode, that.consumptionbusinessareacode) && Objects.equals(consumptionstartdate, that.consumptionstartdate) && Objects.equals(consumptionenddate, that.consumptionenddate) && Objects.equals(consumptionlglwarehousecode, that.consumptionlglwarehousecode) && Objects.equals(payrollpostingsno, that.payrollpostingsno) && Objects.equals(payrollpostingpayrollcode, that.payrollpostingpayrollcode) && Objects.equals(payrollpostingprocessperiod, that.payrollpostingprocessperiod) && Objects.equals(internalordercountercode, that.internalordercountercode) && Objects.equals(internalordercode, that.internalordercode) && Objects.equals(rg23Iiaexciseyearregno, that.rg23Iiaexciseyearregno) && Objects.equals(rg23Iiaexciseyearcode, that.rg23Iiaexciseyearcode) && Objects.equals(rg23Iiacode, that.rg23Iiacode) && Objects.equals(rg23Iicexciseyearregno, that.rg23Iicexciseyearregno) && Objects.equals(rg23Iicexciseyearcode, that.rg23Iicexciseyearcode) && Objects.equals(rg23Iiccode, that.rg23Iiccode) && Objects.equals(exportshippingbilldivisioncode, that.exportshippingbilldivisioncode) && Objects.equals(exportshippingbillcode, that.exportshippingbillcode) && Objects.equals(mrninvoiceno, that.mrninvoiceno) && Objects.equals(mrninvoicedate, that.mrninvoicedate) && Objects.equals(wsoperation, that.wsoperation) && Objects.equals(impoperationuser, that.impoperationuser) && Objects.equals(importstatus, that.importstatus) && Objects.equals(impcreationdatetime, that.impcreationdatetime) && Objects.equals(impcreationuser, that.impcreationuser) && Objects.equals(implastupdatedatetime, that.implastupdatedatetime) && Objects.equals(implastupdateuser, that.implastupdateuser) && Objects.equals(importdatetime, that.importdatetime) && Objects.equals(retrynr, that.retrynr) && Objects.equals(nextretry, that.nextretry) && Objects.equals(importid, that.importid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importautocounter, companycode, businessunitcode, directentry, financialyearcode, documenttemplatecode, financemonthcode, code, documenttypecode, revaluationbusinessunitcode, revaluationfinancialyearcode, revaluationrevaluationdate, revaluationprocesstype, currentstatus, progressstatus, customertype, customercode, suppliertype, suppliercode, employeecode, othercustomertype, othercustomercode, othervendortype, othervendorcode, glcode, notdsapplicable, optdstdsteugengrouptypecode, optdstdstypecode, optdstdscode, optdstdsitaxcode, tdspercentage, tdsapplicableamount, tdsamount, tdsglcode, financedocumentdate, postingdate, duedate, termsofpaymentcode, statisticalgroupcode, projectcode, creditamount, debitamount, documentamount, dynamicclearing, documentcurrencycode, exchangerate, doccompanycurrencycode, chequelotcode, chequenumber, chequedate, customerreference, customerreferencedate, vendorreference, vendorreferencedate, reffindocbusinessunitcode, reffindocfinancialyearcode, reffindocdocumenttemplatecode, reffindocstatisticalgroupcode, reffindoccode, referencetext1, referencetext2, referencetext3, referencetext4, referencetext5, firstugrpugengrouptypecode, firstusergrpcode, sndugrpugenericgrouptypecode, secondusergrpcode, thirdugrpugengrouptypecode, thirdusergrpcode, frugrpugenericgrouptypecode, fourthusergrpcode, fifthugrpugengrouptypecode, fifthusergrpcode, referenceamt1, referenceamt2, referenceamt3, referenceamt4, referenceamt5, remark, profitcentercode, costcentercode, agent1Code, agent2Code, agent3Code, agent4Code, agent5Code, purchaseinvoicedivisioncode, purinvoiceordprncsmsuptype, purinvoiceordprncsmsupcode, purchaseinvoicecode, purchaseinvoiceinvoicedate, expenseinvoicedivisioncode, expenseinvoiceordprncsmsupte, expenseinvoiceordprncsmsupcod, expenseinvoicecode, expenseinvoiceinvoicedate, mrnrejmdmrnheaderdivisioncode, mrnrejmdmrnheadermrnprefixcode, mrnrejmdmrnheadercode, mrnrejmdlineid, mrnrejrejectionlineid, poadvancepurordercountercode, poadvancepurchaseordercode, poadvancelineno, empladvancelrdivisioncode, empladvancelremployeeidcode, empladvancelrequestloantype, empladvancelrequestloancode, empladvancelrloanvoucherno, emploanadvancecode, plantinvoicedivisioncode, plantinvoicecode, commercialinvoicedivisioncode, commercialinvoicecode, sdcreditprovisionalcountercode, sdcreditprovisionalcode, directinvoicedivisioncode, directinvoicecountercode, directinvoicecode, invoiceno, invoicedate, mrndivisioncode, mrnmrnprefixcode, mrncode, stocktrntransactionnumber, stocktrntrndetailnumber, consumptiondivisioncode, consumptionitemtypecode, consumptionbusinessareacode, consumptionstartdate, consumptionenddate, consumptionlglwarehousecode, payrollpostingsno, payrollpostingpayrollcode, payrollpostingprocessperiod, internalordercountercode, internalordercode, rg23Iiaexciseyearregno, rg23Iiaexciseyearcode, rg23Iiacode, rg23Iicexciseyearregno, rg23Iicexciseyearcode, rg23Iiccode, exportshippingbilldivisioncode, exportshippingbillcode, mrninvoiceno, mrninvoicedate, wsoperation, impoperationuser, importstatus, impcreationdatetime, impcreationuser, implastupdatedatetime, implastupdateuser, importdatetime, retrynr, nextretry, importid);
    }
}
