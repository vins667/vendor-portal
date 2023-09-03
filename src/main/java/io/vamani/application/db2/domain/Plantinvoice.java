package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "plantinvoice")
public class Plantinvoice {
    @EmbeddedId
    private PlantinvoiceId id;
    @Basic
    @Column(name = "INVOICETYPECODE", nullable = true, length = 3)
    private String invoicetypecode;

    @Basic
    @Column(name = "INVOICEDATE", nullable = true)
    private Date invoicedate;

    @Basic
    @Column(name = "CREATIONDATE", nullable = true)
    private Date creationdate;

    @Basic
    @Column(name = "FIRMCODE", nullable = true, length = 3)
    private String firmcode;

    @Basic
    @Column(name = "FIRMBANKBANKCOUNTRYCODE", nullable = true, length = 3)
    private String firmbankbankcountrycode;

    @Basic
    @Column(name = "FIRMBANKCODE", nullable = true, length = 15)
    private String firmbankcode;

    @Basic
    @Column(name = "FIRMBANKBRANCHCODE", nullable = true, length = 6)
    private String firmbankbranchcode;

    @Basic
    @Column(name = "FACTORYCOMPANYCODE", nullable = true, length = 3)
    private String factorycompanycode;

    @Basic
    @Column(name = "FACTORYCODE", nullable = true, length = 3)
    private String factorycode;

    @Basic
    @Column(name = "CONTRACTNOCOUNTERCODE", nullable = true, length = 8)
    private String contractnocountercode;

    @Basic
    @Column(name = "CONTRACTNOCODE", nullable = true, length = 15)
    private String contractnocode;

    @Basic
    @Column(name = "CONTRACTDATE", nullable = true)
    private Date contractdate;

    @Basic
    @Column(name = "EXPORTERREFNO", nullable = true, length = 15)
    private String exporterrefno;

    @Basic
    @Column(name = "BUYERSPOREFNO", nullable = true, length = 200)
    private String buyersporefno;

    @Basic
    @Column(name = "PSINVOICECODE", nullable = true, length = 15)
    private String psinvoicecode;

    @Basic
    @Column(name = "CUSTOMINVOICECODE", nullable = true, length = 20)
    private String custominvoicecode;

    @Basic
    @Column(name = "CUSTOMINVOICETYPECODE", nullable = true, length = 3)
    private String custominvoicetypecode;

    @Basic
    @Column(name = "COMMERCIALINVOICECODE", nullable = true, length = 20)
    private String commercialinvoicecode;

    @Basic
    @Column(name = "COMMERCIALINVOICETYPECODE", nullable = true, length = 3)
    private String commercialinvoicetypecode;

    @Basic
    @Column(name = "MRNHEADERCODE", nullable = true, precision = 0)
    private Integer mrnheadercode;

    @Basic
    @Column(name = "MRNHEADERMRNPREFIXCODE", nullable = true, length = 3)
    private String mrnheadermrnprefixcode;

    @Basic
    @Column(name = "SALINVOICEPRVCOUNTERCODE", nullable = true, length = 8)
    private String salinvoiceprvcountercode;

    @Basic
    @Column(name = "SALESINVOICEPROVISIONALCODE", nullable = true, length = 15)
    private String salesinvoiceprovisionalcode;

    @Basic
    @Column(name = "CONSIGNEECUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    private String consigneecustomersuppliertype;

    @Basic
    @Column(name = "CONSIGNEECUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    private String consigneecustomersuppliercode;

    @Basic
    @Column(name = "DELIVERYPOINTUNIQUEID", nullable = true)
    private Long deliverypointuniqueid;

    @Basic
    @Column(name = "DELIVERYPOINTCODE", nullable = true, length = 8)
    private String deliverypointcode;

    @Basic
    @Column(name = "DELIVERYPOINTTYPE", nullable = true, length = 2)
    private String deliverypointtype;

    @Basic
    @Column(name = "BUYERIFOTCCUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    private String buyerifotccustomersuppliertype;

    @Basic
    @Column(name = "BUYERIFOTCCUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    private String buyerifotccustomersuppliercode;

    @Basic
    @Column(name = "PLANTINVOICEDATE", nullable = true)
    private Date plantinvoicedate;

    @Basic
    @Column(name = "NOTIFYPARTYCSMSUPPLIERTYPE", nullable = true, length = 1)
    private String notifypartycsmsuppliertype;

    @Basic
    @Column(name = "NOTIFYPARTYCSMSUPPLIERCODE", nullable = true, length = 8)
    private String notifypartycsmsuppliercode;

    @Basic
    @Column(name = "BUYERSBANKBANKCOUNTRYCODE", nullable = true, length = 3)
    private String buyersbankbankcountrycode;

    @Basic
    @Column(name = "BUYERSBANKCODE", nullable = true, length = 15)
    private String buyersbankcode;

    @Basic
    @Column(name = "BUYERSBANKBRANCHCODE", nullable = true, length = 6)
    private String buyersbankbranchcode;

    @Basic
    @Column(name = "AGENT1CODE", nullable = true, length = 3)
    private String agent1Code;

    @Basic
    @Column(name = "AGENT1ONCOMPANYCODE", nullable = true, length = 3)
    private String agent1Oncompanycode;

    @Basic
    @Column(name = "AGENT1ONCODE", nullable = true, length = 3)
    private String agent1Oncode;

    @Basic
    @Column(name = "AGENT1CURRENCYCODE", nullable = true, length = 4)
    private String agent1Currencycode;

    @Basic
    @Column(name = "AGENT1AMOUNT", nullable = true, precision = 5)
    private BigDecimal agent1Amount;

    @Basic
    @Column(name = "AGENT1COMMISSIONPERCENTAGE", nullable = true, precision = 5)
    private BigDecimal agent1Commissionpercentage;

    @Basic
    @Column(name = "AGENT2CODE", nullable = true, length = 3)
    private String agent2Code;

    @Basic
    @Column(name = "AGENT2ONCOMPANYCODE", nullable = true, length = 3)
    private String agent2Oncompanycode;

    @Basic
    @Column(name = "AGENT2ONCODE", nullable = true, length = 3)
    private String agent2Oncode;

    @Basic
    @Column(name = "AGENT2CURRENCYCODE", nullable = true, length = 4)
    private String agent2Currencycode;

    @Basic
    @Column(name = "AGENT2AMOUNT", nullable = true, precision = 5)
    private BigDecimal agent2Amount;

    @Basic
    @Column(name = "AGENT2COMMISSIONPERCENTAGE", nullable = true, precision = 5)
    private BigDecimal agent2Commissionpercentage;

    @Basic
    @Column(name = "AGENT3CODE", nullable = true, length = 3)
    private String agent3Code;

    @Basic
    @Column(name = "AGENT3ONCOMPANYCODE", nullable = true, length = 3)
    private String agent3Oncompanycode;

    @Basic
    @Column(name = "AGENT3ONCODE", nullable = true, length = 3)
    private String agent3Oncode;

    @Basic
    @Column(name = "AGENT3CURRENCYCODE", nullable = true, length = 4)
    private String agent3Currencycode;

    @Basic
    @Column(name = "AGENT3AMOUNT", nullable = true, precision = 5)
    private BigDecimal agent3Amount;

    @Basic
    @Column(name = "AGENT3COMMISSIONPERCENTAGE", nullable = true, precision = 5)
    private BigDecimal agent3Commissionpercentage;

    @Basic
    @Column(name = "AGENT4CODE", nullable = true, length = 3)
    private String agent4Code;

    @Basic
    @Column(name = "AGENT4ONCOMPANYCODE", nullable = true, length = 3)
    private String agent4Oncompanycode;

    @Basic
    @Column(name = "AGENT4ONCODE", nullable = true, length = 3)
    private String agent4Oncode;
    @Basic
    @Column(name = "AGENT4CURRENCYCODE", nullable = true, length = 4)
    private String agent4Currencycode;
    @Basic
    @Column(name = "AGENT4AMOUNT", nullable = true, precision = 5)
    private BigDecimal agent4Amount;
    @Basic
    @Column(name = "AGENT4COMMISSIONPERCENTAGE", nullable = true, precision = 5)
    private BigDecimal agent4Commissionpercentage;
    @Basic
    @Column(name = "AGENT5CODE", nullable = true, length = 3)
    private String agent5Code;
    @Basic
    @Column(name = "AGENT5ONCOMPANYCODE", nullable = true, length = 3)
    private String agent5Oncompanycode;
    @Basic
    @Column(name = "AGENT5ONCODE", nullable = true, length = 3)
    private String agent5Oncode;
    @Basic
    @Column(name = "AGENT5CURRENCYCODE", nullable = true, length = 4)
    private String agent5Currencycode;
    @Basic
    @Column(name = "AGENT5AMOUNT", nullable = true, precision = 5)
    private BigDecimal agent5Amount;
    @Basic
    @Column(name = "AGENT5COMMISSIONPERCENTAGE", nullable = true, precision = 5)
    private BigDecimal agent5Commissionpercentage;
    @Basic
    @Column(name = "BOTTLESEALNO", nullable = true, length = 15)
    private String bottlesealno;
    @Basic
    @Column(name = "CUSTOMERBOTTLESEALNO", nullable = true, length = 25)
    private String customerbottlesealno;
    @Basic
    @Column(name = "COMPANYSEALNO", nullable = true, length = 15)
    private String companysealno;
    @Basic
    @Column(name = "GOODSORIGINCOUNTRYCODE", nullable = true, length = 3)
    private String goodsorigincountrycode;
    @Basic
    @Column(name = "DESTINATIONCOUNTRYCODE", nullable = true, length = 3)
    private String destinationcountrycode;
    @Basic
    @Column(name = "TERMSOFSHIPPINGCOMPANYCODE", nullable = true, length = 3)
    private String termsofshippingcompanycode;
    @Basic
    @Column(name = "TERMSOFSHIPPINGCODE", nullable = true, length = 2)
    private String termsofshippingcode;
    @Basic
    @Column(name = "PRECARRIAGEBY", nullable = true, length = 15)
    private String precarriageby;
    @Basic
    @Column(name = "PLACEOFRECEIPTBYPRECARRIAGE", nullable = true, length = 10)
    private String placeofreceiptbyprecarriage;
    @Basic
    @Column(name = "VESSELFLIGHTNO", nullable = true, length = 30)
    private String vesselflightno;
    @Basic
    @Column(name = "PORTOFLOADINGCODE", nullable = true, length = 10)
    private String portofloadingcode;
    @Basic
    @Column(name = "PORTOFDISCHARGECODE", nullable = true, length = 10)
    private String portofdischargecode;
    @Basic
    @Column(name = "FINALDESTINATIONCODE", nullable = true, length = 3)
    private String finaldestinationcode;
    @Basic
    @Column(name = "WEIGHTUMCODE", nullable = true, length = 3)
    private String weightumcode;
    @Basic
    @Column(name = "GROSSWEIGHT", nullable = true, precision = 5)
    private BigDecimal grossweight;
    @Basic
    @Column(name = "NETTWEIGHT", nullable = true, precision = 5)
    private BigDecimal nettweight;
    @Basic
    @Column(name = "TOTALQUANTITY", nullable = true, precision = 5)
    private BigDecimal totalquantity;
    @Basic
    @Column(name = "TOTALNUMBEROFBALES", nullable = false)
    private int totalnumberofbales;
    @Basic
    @Column(name = "PACKDIMENSIONIN", nullable = true, length = 15)
    private String packdimensionin;
    @Basic
    @Column(name = "SAMPLEDRAWN", nullable = false)
    private int sampledrawn;
    @Basic
    @Column(name = "PACKINGLISTNO", nullable = true, length = 15)
    private String packinglistno;
    @Basic
    @Column(name = "PACKINGLISTDATE", nullable = true)
    private Date packinglistdate;
    @Basic
    @Column(name = "SHIPLINECUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    private String shiplinecustomersuppliertype;
    @Basic
    @Column(name = "SHIPLINECUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    private String shiplinecustomersuppliercode;
    @Basic
    @Column(name = "CHACUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    private String chacustomersuppliertype;
    @Basic
    @Column(name = "CHACUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    private String chacustomersuppliercode;
    @Basic
    @Column(name = "FORWARDERCUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    private String forwardercustomersuppliertype;
    @Basic
    @Column(name = "FORWARDERCUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    private String forwardercustomersuppliercode;
    @Basic
    @Column(name = "ETADATE", nullable = true)
    private Date etadate;
    @Basic
    @Column(name = "ETDDATE", nullable = true)
    private Date etddate;
    @Basic
    @Column(name = "EXWORKDATE", nullable = true)
    private Date exworkdate;
    @Basic
    @Column(name = "LCLFCL", nullable = false)
    private int lclfcl;
    @Basic
    @Column(name = "CHALLANNO", nullable = true, length = 15)
    private String challanno;
    @Basic
    @Column(name = "CHALLANDATE", nullable = true)
    private Date challandate;
    @Basic
    @Column(name = "LRNO", nullable = true, length = 15)
    private String lrno;
    @Basic
    @Column(name = "LRDATE", nullable = true)
    private Date lrdate;
    @Basic
    @Column(name = "HOUSEAWBBILLNO", nullable = true, length = 15)
    private String houseawbbillno;
    @Basic
    @Column(name = "HOUSEAWBBILLDATE", nullable = true)
    private Date houseawbbilldate;
    @Basic
    @Column(name = "BLDATE", nullable = true)
    private Date bldate;
    @Basic
    @Column(name = "BLNUMBER", nullable = true, length = 25)
    private String blnumber;
    @Basic
    @Column(name = "TRANSPORTERCODCSMSUPPLIERTYPE", nullable = true, length = 1)
    private String transportercodcsmsuppliertype;
    @Basic
    @Column(name = "TRANSPORTERCODCSMSUPPLIERCODE", nullable = true, length = 8)
    private String transportercodcsmsuppliercode;
    @Basic
    @Column(name = "TRUCKNO", nullable = true, length = 15)
    private String truckno;
    @Basic
    @Column(name = "CONTAINERNO", nullable = true, length = 30)
    private String containerno;
    @Basic
    @Column(name = "CONTAINERSIZE", nullable = true, length = 15)
    private String containersize;
    @Basic
    @Column(name = "CATEGORY", nullable = true, length = 30)
    private String category;
    @Basic
    @Column(name = "ITEMDESCRIPTION", nullable = true, length = 30)
    private String itemdescription;
    @Basic
    @Column(name = "SHIPPINGMARKS1", nullable = true, length = 250)
    private String shippingmarks1;
    @Basic
    @Column(name = "SHIPPINGMARKS2", nullable = true, length = 250)
    private String shippingmarks2;
    @Basic
    @Column(name = "SHIPPINGMARKS3", nullable = true, length = 250)
    private String shippingmarks3;
    @Basic
    @Column(name = "SHIPPINGMARKS4", nullable = true, length = 250)
    private String shippingmarks4;
    @Basic
    @Column(name = "AR3CODE", nullable = true, length = 20)
    private String ar3Code;
    @Basic
    @Column(name = "AR3EXCISEYEARREGNO", nullable = true, length = 30)
    private String ar3Exciseyearregno;
    @Basic
    @Column(name = "AR3EXCISEYEARCODE", nullable = true, length = 4)
    private String ar3Exciseyearcode;
    @Basic
    @Column(name = "AR3DATE", nullable = true)
    private Date ar3Date;
    @Basic
    @Column(name = "AR4CODE", nullable = true, length = 20)
    private String ar4Code;
    @Basic
    @Column(name = "AR4EXCISEYEARREGNO", nullable = true, length = 30)
    private String ar4Exciseyearregno;
    @Basic
    @Column(name = "AR4EXCISEYEARCODE", nullable = true, length = 4)
    private String ar4Exciseyearcode;
    @Basic
    @Column(name = "AR4DATE", nullable = true)
    private Date ar4Date;
    @Basic
    @Column(name = "SEALINGOPTION", nullable = false)
    private int sealingoption;
    @Basic
    @Column(name = "EXAMINATIONAT", nullable = false)
    private int examinationat;
    @Basic
    @Column(name = "UNDERREBATE", nullable = false)
    private int underrebate;
    @Basic
    @Column(name = "TIMEOFREMOVALOFGOODS", nullable = true)
    private Date timeofremovalofgoods;
    @Basic
    @Column(name = "INVOICEISSUETIME", nullable = true)
    private Timestamp invoiceissuetime;
    @Basic
    @Column(name = "FOOTERLINES", nullable = true, length = 100)
    private String footerlines;
    @Basic
    @Column(name = "IPPOLICYNO", nullable = true, length = 20)
    private String ippolicyno;
    @Basic
    @Column(name = "IPPOLICYDATE", nullable = true)
    private Date ippolicydate;
    @Basic
    @Column(name = "INSURANCECOMPANY", nullable = true, length = 60)
    private String insurancecompany;
    @Basic
    @Column(name = "POLICYPREMIUMRATE", nullable = true, precision = 5)
    private BigDecimal policypremiumrate;
    @Basic
    @Column(name = "CUSTOMERPREMIUMRATE", nullable = true, precision = 5)
    private BigDecimal customerpremiumrate;
    @Basic
    @Column(name = "INSURANCEMARKUP", nullable = true, precision = 5)
    private BigDecimal insurancemarkup;
    @Basic
    @Column(name = "MARINEINSURANCEMICNO", nullable = true, length = 12)
    private String marineinsurancemicno;
    @Basic
    @Column(name = "SCHEMETYPECODE", nullable = true, length = 3)
    private String schemetypecode;
    @Basic
    @Column(name = "ALADVANCELICENSECODE", nullable = true, length = 30)
    private String aladvancelicensecode;
    @Basic
    @Column(name = "ALAPPLICATIONDATE", nullable = true)
    private Date alapplicationdate;
    @Basic
    @Column(name = "ADVANCELICENSENO", nullable = true, length = 15)
    private String advancelicenseno;
    @Basic
    @Column(name = "ADVANCELICENSEDATE", nullable = true)
    private Date advancelicensedate;
    @Basic
    @Column(name = "EPCGEPCGAPPLICATIONCODE", nullable = true, length = 30)
    private String epcgepcgapplicationcode;
    @Basic
    @Column(name = "EPCGAPPLICATIONDATE", nullable = true)
    private Date epcgapplicationdate;
    @Basic
    @Column(name = "EPCGLICENSENO", nullable = true, length = 30)
    private String epcglicenseno;
    @Basic
    @Column(name = "EPCGLICENSEDATE", nullable = true)
    private Date epcglicensedate;
    @Basic
    @Column(name = "ADVANCELICENSEFILENO", nullable = true, length = 15)
    private String advancelicensefileno;
    @Basic
    @Column(name = "ADVANCELICENSEFILEDATE", nullable = true)
    private Date advancelicensefiledate;
    @Basic
    @Column(name = "INVOICECURRENCYCODE", nullable = true, length = 4)
    private String invoicecurrencycode;
    @Basic
    @Column(name = "ORDERCURRENCYCODE", nullable = true, length = 4)
    private String ordercurrencycode;
    @Basic
    @Column(name = "EXCHANGERATEOFCONTRACT", nullable = true, precision = 5)
    private BigDecimal exchangerateofcontract;
    @Basic
    @Column(name = "TERMSOFDELIVERYCOMPANYCODE", nullable = true, length = 3)
    private String termsofdeliverycompanycode;
    @Basic
    @Column(name = "TERMSOFDELIVERYCODE", nullable = true, length = 3)
    private String termsofdeliverycode;
    @Basic
    @Column(name = "TERMSOFPAYMENTCOMPANYCODE", nullable = true, length = 3)
    private String termsofpaymentcompanycode;
    @Basic
    @Column(name = "TERMSOFPAYMENTCODE", nullable = true, length = 3)
    private String termsofpaymentcode;
    @Basic
    @Column(name = "LCLCNO", nullable = true, length = 35)
    private String lclcno;
    @Basic
    @Column(name = "LCLCDATE", nullable = true)
    private Date lclcdate;
    @Basic
    @Column(name = "BCFORMOFRS", nullable = true, precision = 5)
    private BigDecimal bcformofrs;
    @Basic
    @Column(name = "PRICELISTORDERTYPE", nullable = true, length = 1)
    private String pricelistordertype;
    @Basic
    @Column(name = "PRICELISTCODE", nullable = true, length = 8)
    private String pricelistcode;
    @Basic
    @Column(name = "BASICVALUE", nullable = true, precision = 5)
    private BigDecimal basicvalue;
    @Basic
    @Column(name = "GROSSVALUE", nullable = true, precision = 5)
    private BigDecimal grossvalue;
    @Basic
    @Column(name = "TAXTEMPLATETEMPLATETYPE", nullable = true, length = 2)
    private String taxtemplatetemplatetype;
    @Basic
    @Column(name = "TAXTEMPLATECODE", nullable = true, length = 3)
    private String taxtemplatecode;
    @Basic
    @Column(name = "ROUNDOFFVALUE", nullable = true, precision = 5)
    private BigDecimal roundoffvalue;
    @Basic
    @Column(name = "NETTVALUE", nullable = true, precision = 5)
    private BigDecimal nettvalue;
    @Basic
    @Column(name = "ROUNDOFFITAXCODE", nullable = true, length = 3)
    private String roundoffitaxcode;
    @Basic
    @Column(name = "USEDCOUNTER", nullable = false)
    private int usedcounter;
    @Basic
    @Column(name = "POSTINGFLAG", nullable = false)
    private int postingflag;
    @Basic
    @Column(name = "PRINTDATEANDTIME", nullable = true)
    private Timestamp printdateandtime;
    @Basic
    @Column(name = "PRINTUSER", nullable = true, length = 25)
    private String printuser;
    @Basic
    @Column(name = "REPRINTDATEANDTIME", nullable = true)
    private Timestamp reprintdateandtime;
    @Basic
    @Column(name = "REPRINTUSER", nullable = true, length = 25)
    private String reprintuser;
    @Basic
    @Column(name = "INVOICERATEOPTION", nullable = true, length = 2)
    private String invoicerateoption;
    @Basic
    @Column(name = "INVOICECREATIONFROM", nullable = true, length = 2)
    private String invoicecreationfrom;
    @Basic
    @Column(name = "TERMSOFLOGCODE", nullable = true, length = 2)
    private String termsoflogcode;
    @Basic
    @Column(name = "LOGREASONCODE", nullable = true, length = 2)
    private String logreasoncode;
    @Basic
    @Column(name = "FINDOCBUSINESSUNITCODE", nullable = true, length = 10)
    private String findocbusinessunitcode;
    @Basic
    @Column(name = "FINDOCFINANCIALYEARCODE", nullable = true, precision = 0)
    private Integer findocfinancialyearcode;
    @Basic
    @Column(name = "FINDOCTEMPLATECODE", nullable = true, length = 3)
    private String findoctemplatecode;
    @Basic
    @Column(name = "FINDOCSTATISTICALGROUPCODE", nullable = true, length = 6)
    private String findocstatisticalgroupcode;
    @Basic
    @Column(name = "FINDOCCODE", nullable = true, length = 15)
    private String findoccode;
    @Basic
    @Column(name = "FLAG", nullable = true, length = 15)
    private String flag;
    @Basic
    @Column(name = "SAPMESSAGE", nullable = true, length = 32700)
    private String sapmessage;
    @Basic
    @Column(name = "DOMEXCISEFLAG", nullable = true, length = 15)
    private String domexciseflag;
    @Basic
    @Column(name = "DOMEXCISESAPMESSAGE", nullable = true, length = 32700)
    private String domexcisesapmessage;
    @Basic
    @Column(name = "EXCISEFLAG", nullable = true, length = 15)
    private String exciseflag;
    @Basic
    @Column(name = "EXCISESAPMESSAGE", nullable = true, length = 32700)
    private String excisesapmessage;
    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    private Timestamp creationdatetime;
    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    private String creationuser;
    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    private Timestamp lastupdatedatetime;
    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    private String lastupdateuser;
    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    private long absuniqueid;
    @Basic
    @Column(name = "OTHFINDOCCODE", nullable = true, length = 15)
    private String othfindoccode;
    @Basic
    @Column(name = "CREATIONDATETIMEUTC", nullable = true)
    private Timestamp creationdatetimeutc;
    @Basic
    @Column(name = "LASTUPDATEDATETIMEUTC", nullable = true)
    private Timestamp lastupdatedatetimeutc;

    public PlantinvoiceId getId() {
        return id;
    }

    public void setId(PlantinvoiceId id) {
        this.id = id;
    }

    public String getInvoicetypecode() {
        return invoicetypecode;
    }

    public void setInvoicetypecode(String invoicetypecode) {
        this.invoicetypecode = invoicetypecode;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public String getFirmcode() {
        return firmcode;
    }

    public void setFirmcode(String firmcode) {
        this.firmcode = firmcode;
    }

    public String getFirmbankbankcountrycode() {
        return firmbankbankcountrycode;
    }

    public void setFirmbankbankcountrycode(String firmbankbankcountrycode) {
        this.firmbankbankcountrycode = firmbankbankcountrycode;
    }

    public String getFirmbankcode() {
        return firmbankcode;
    }

    public void setFirmbankcode(String firmbankcode) {
        this.firmbankcode = firmbankcode;
    }

    public String getFirmbankbranchcode() {
        return firmbankbranchcode;
    }

    public void setFirmbankbranchcode(String firmbankbranchcode) {
        this.firmbankbranchcode = firmbankbranchcode;
    }

    public String getFactorycompanycode() {
        return factorycompanycode;
    }

    public void setFactorycompanycode(String factorycompanycode) {
        this.factorycompanycode = factorycompanycode;
    }

    public String getFactorycode() {
        return factorycode;
    }

    public void setFactorycode(String factorycode) {
        this.factorycode = factorycode;
    }

    public String getContractnocountercode() {
        return contractnocountercode;
    }

    public void setContractnocountercode(String contractnocountercode) {
        this.contractnocountercode = contractnocountercode;
    }

    public String getContractnocode() {
        return contractnocode;
    }

    public void setContractnocode(String contractnocode) {
        this.contractnocode = contractnocode;
    }

    public Date getContractdate() {
        return contractdate;
    }

    public void setContractdate(Date contractdate) {
        this.contractdate = contractdate;
    }

    public String getExporterrefno() {
        return exporterrefno;
    }

    public void setExporterrefno(String exporterrefno) {
        this.exporterrefno = exporterrefno;
    }

    public String getBuyersporefno() {
        return buyersporefno;
    }

    public void setBuyersporefno(String buyersporefno) {
        this.buyersporefno = buyersporefno;
    }

    public String getPsinvoicecode() {
        return psinvoicecode;
    }

    public void setPsinvoicecode(String psinvoicecode) {
        this.psinvoicecode = psinvoicecode;
    }

    public String getCustominvoicecode() {
        return custominvoicecode;
    }

    public void setCustominvoicecode(String custominvoicecode) {
        this.custominvoicecode = custominvoicecode;
    }

    public String getCustominvoicetypecode() {
        return custominvoicetypecode;
    }

    public void setCustominvoicetypecode(String custominvoicetypecode) {
        this.custominvoicetypecode = custominvoicetypecode;
    }

    public String getCommercialinvoicecode() {
        return commercialinvoicecode;
    }

    public void setCommercialinvoicecode(String commercialinvoicecode) {
        this.commercialinvoicecode = commercialinvoicecode;
    }

    public String getCommercialinvoicetypecode() {
        return commercialinvoicetypecode;
    }

    public void setCommercialinvoicetypecode(String commercialinvoicetypecode) {
        this.commercialinvoicetypecode = commercialinvoicetypecode;
    }

    public Integer getMrnheadercode() {
        return mrnheadercode;
    }

    public void setMrnheadercode(Integer mrnheadercode) {
        this.mrnheadercode = mrnheadercode;
    }

    public String getMrnheadermrnprefixcode() {
        return mrnheadermrnprefixcode;
    }

    public void setMrnheadermrnprefixcode(String mrnheadermrnprefixcode) {
        this.mrnheadermrnprefixcode = mrnheadermrnprefixcode;
    }

    public String getSalinvoiceprvcountercode() {
        return salinvoiceprvcountercode;
    }

    public void setSalinvoiceprvcountercode(String salinvoiceprvcountercode) {
        this.salinvoiceprvcountercode = salinvoiceprvcountercode;
    }

    public String getSalesinvoiceprovisionalcode() {
        return salesinvoiceprovisionalcode;
    }

    public void setSalesinvoiceprovisionalcode(String salesinvoiceprovisionalcode) {
        this.salesinvoiceprovisionalcode = salesinvoiceprovisionalcode;
    }

    public String getConsigneecustomersuppliertype() {
        return consigneecustomersuppliertype;
    }

    public void setConsigneecustomersuppliertype(String consigneecustomersuppliertype) {
        this.consigneecustomersuppliertype = consigneecustomersuppliertype;
    }

    public String getConsigneecustomersuppliercode() {
        return consigneecustomersuppliercode;
    }

    public void setConsigneecustomersuppliercode(String consigneecustomersuppliercode) {
        this.consigneecustomersuppliercode = consigneecustomersuppliercode;
    }

    public Long getDeliverypointuniqueid() {
        return deliverypointuniqueid;
    }

    public void setDeliverypointuniqueid(Long deliverypointuniqueid) {
        this.deliverypointuniqueid = deliverypointuniqueid;
    }

    public String getDeliverypointcode() {
        return deliverypointcode;
    }

    public void setDeliverypointcode(String deliverypointcode) {
        this.deliverypointcode = deliverypointcode;
    }

    public String getDeliverypointtype() {
        return deliverypointtype;
    }

    public void setDeliverypointtype(String deliverypointtype) {
        this.deliverypointtype = deliverypointtype;
    }

    public String getBuyerifotccustomersuppliertype() {
        return buyerifotccustomersuppliertype;
    }

    public void setBuyerifotccustomersuppliertype(String buyerifotccustomersuppliertype) {
        this.buyerifotccustomersuppliertype = buyerifotccustomersuppliertype;
    }

    public String getBuyerifotccustomersuppliercode() {
        return buyerifotccustomersuppliercode;
    }

    public void setBuyerifotccustomersuppliercode(String buyerifotccustomersuppliercode) {
        this.buyerifotccustomersuppliercode = buyerifotccustomersuppliercode;
    }

    public Date getPlantinvoicedate() {
        return plantinvoicedate;
    }

    public void setPlantinvoicedate(Date plantinvoicedate) {
        this.plantinvoicedate = plantinvoicedate;
    }

    public String getNotifypartycsmsuppliertype() {
        return notifypartycsmsuppliertype;
    }

    public void setNotifypartycsmsuppliertype(String notifypartycsmsuppliertype) {
        this.notifypartycsmsuppliertype = notifypartycsmsuppliertype;
    }

    public String getNotifypartycsmsuppliercode() {
        return notifypartycsmsuppliercode;
    }

    public void setNotifypartycsmsuppliercode(String notifypartycsmsuppliercode) {
        this.notifypartycsmsuppliercode = notifypartycsmsuppliercode;
    }

    public String getBuyersbankbankcountrycode() {
        return buyersbankbankcountrycode;
    }

    public void setBuyersbankbankcountrycode(String buyersbankbankcountrycode) {
        this.buyersbankbankcountrycode = buyersbankbankcountrycode;
    }

    public String getBuyersbankcode() {
        return buyersbankcode;
    }

    public void setBuyersbankcode(String buyersbankcode) {
        this.buyersbankcode = buyersbankcode;
    }

    public String getBuyersbankbranchcode() {
        return buyersbankbranchcode;
    }

    public void setBuyersbankbranchcode(String buyersbankbranchcode) {
        this.buyersbankbranchcode = buyersbankbranchcode;
    }

    public String getAgent1Code() {
        return agent1Code;
    }

    public void setAgent1Code(String agent1Code) {
        this.agent1Code = agent1Code;
    }

    public String getAgent1Oncompanycode() {
        return agent1Oncompanycode;
    }

    public void setAgent1Oncompanycode(String agent1Oncompanycode) {
        this.agent1Oncompanycode = agent1Oncompanycode;
    }

    public String getAgent1Oncode() {
        return agent1Oncode;
    }

    public void setAgent1Oncode(String agent1Oncode) {
        this.agent1Oncode = agent1Oncode;
    }

    public String getAgent1Currencycode() {
        return agent1Currencycode;
    }

    public void setAgent1Currencycode(String agent1Currencycode) {
        this.agent1Currencycode = agent1Currencycode;
    }

    public BigDecimal getAgent1Amount() {
        return agent1Amount;
    }

    public void setAgent1Amount(BigDecimal agent1Amount) {
        this.agent1Amount = agent1Amount;
    }

    public BigDecimal getAgent1Commissionpercentage() {
        return agent1Commissionpercentage;
    }

    public void setAgent1Commissionpercentage(BigDecimal agent1Commissionpercentage) {
        this.agent1Commissionpercentage = agent1Commissionpercentage;
    }

    public String getAgent2Code() {
        return agent2Code;
    }

    public void setAgent2Code(String agent2Code) {
        this.agent2Code = agent2Code;
    }

    public String getAgent2Oncompanycode() {
        return agent2Oncompanycode;
    }

    public void setAgent2Oncompanycode(String agent2Oncompanycode) {
        this.agent2Oncompanycode = agent2Oncompanycode;
    }

    public String getAgent2Oncode() {
        return agent2Oncode;
    }

    public void setAgent2Oncode(String agent2Oncode) {
        this.agent2Oncode = agent2Oncode;
    }

    public String getAgent2Currencycode() {
        return agent2Currencycode;
    }

    public void setAgent2Currencycode(String agent2Currencycode) {
        this.agent2Currencycode = agent2Currencycode;
    }

    public BigDecimal getAgent2Amount() {
        return agent2Amount;
    }

    public void setAgent2Amount(BigDecimal agent2Amount) {
        this.agent2Amount = agent2Amount;
    }

    public BigDecimal getAgent2Commissionpercentage() {
        return agent2Commissionpercentage;
    }

    public void setAgent2Commissionpercentage(BigDecimal agent2Commissionpercentage) {
        this.agent2Commissionpercentage = agent2Commissionpercentage;
    }

    public String getAgent3Code() {
        return agent3Code;
    }

    public void setAgent3Code(String agent3Code) {
        this.agent3Code = agent3Code;
    }

    public String getAgent3Oncompanycode() {
        return agent3Oncompanycode;
    }

    public void setAgent3Oncompanycode(String agent3Oncompanycode) {
        this.agent3Oncompanycode = agent3Oncompanycode;
    }

    public String getAgent3Oncode() {
        return agent3Oncode;
    }

    public void setAgent3Oncode(String agent3Oncode) {
        this.agent3Oncode = agent3Oncode;
    }

    public String getAgent3Currencycode() {
        return agent3Currencycode;
    }

    public void setAgent3Currencycode(String agent3Currencycode) {
        this.agent3Currencycode = agent3Currencycode;
    }

    public BigDecimal getAgent3Amount() {
        return agent3Amount;
    }

    public void setAgent3Amount(BigDecimal agent3Amount) {
        this.agent3Amount = agent3Amount;
    }

    public BigDecimal getAgent3Commissionpercentage() {
        return agent3Commissionpercentage;
    }

    public void setAgent3Commissionpercentage(BigDecimal agent3Commissionpercentage) {
        this.agent3Commissionpercentage = agent3Commissionpercentage;
    }

    public String getAgent4Code() {
        return agent4Code;
    }

    public void setAgent4Code(String agent4Code) {
        this.agent4Code = agent4Code;
    }

    public String getAgent4Oncompanycode() {
        return agent4Oncompanycode;
    }

    public void setAgent4Oncompanycode(String agent4Oncompanycode) {
        this.agent4Oncompanycode = agent4Oncompanycode;
    }

    public String getAgent4Oncode() {
        return agent4Oncode;
    }

    public void setAgent4Oncode(String agent4Oncode) {
        this.agent4Oncode = agent4Oncode;
    }

    public String getAgent4Currencycode() {
        return agent4Currencycode;
    }

    public void setAgent4Currencycode(String agent4Currencycode) {
        this.agent4Currencycode = agent4Currencycode;
    }

    public BigDecimal getAgent4Amount() {
        return agent4Amount;
    }

    public void setAgent4Amount(BigDecimal agent4Amount) {
        this.agent4Amount = agent4Amount;
    }

    public BigDecimal getAgent4Commissionpercentage() {
        return agent4Commissionpercentage;
    }

    public void setAgent4Commissionpercentage(BigDecimal agent4Commissionpercentage) {
        this.agent4Commissionpercentage = agent4Commissionpercentage;
    }

    public String getAgent5Code() {
        return agent5Code;
    }

    public void setAgent5Code(String agent5Code) {
        this.agent5Code = agent5Code;
    }

    public String getAgent5Oncompanycode() {
        return agent5Oncompanycode;
    }

    public void setAgent5Oncompanycode(String agent5Oncompanycode) {
        this.agent5Oncompanycode = agent5Oncompanycode;
    }

    public String getAgent5Oncode() {
        return agent5Oncode;
    }

    public void setAgent5Oncode(String agent5Oncode) {
        this.agent5Oncode = agent5Oncode;
    }

    public String getAgent5Currencycode() {
        return agent5Currencycode;
    }

    public void setAgent5Currencycode(String agent5Currencycode) {
        this.agent5Currencycode = agent5Currencycode;
    }

    public BigDecimal getAgent5Amount() {
        return agent5Amount;
    }

    public void setAgent5Amount(BigDecimal agent5Amount) {
        this.agent5Amount = agent5Amount;
    }

    public BigDecimal getAgent5Commissionpercentage() {
        return agent5Commissionpercentage;
    }

    public void setAgent5Commissionpercentage(BigDecimal agent5Commissionpercentage) {
        this.agent5Commissionpercentage = agent5Commissionpercentage;
    }

    public String getBottlesealno() {
        return bottlesealno;
    }

    public void setBottlesealno(String bottlesealno) {
        this.bottlesealno = bottlesealno;
    }

    public String getCustomerbottlesealno() {
        return customerbottlesealno;
    }

    public void setCustomerbottlesealno(String customerbottlesealno) {
        this.customerbottlesealno = customerbottlesealno;
    }

    public String getCompanysealno() {
        return companysealno;
    }

    public void setCompanysealno(String companysealno) {
        this.companysealno = companysealno;
    }

    public String getGoodsorigincountrycode() {
        return goodsorigincountrycode;
    }

    public void setGoodsorigincountrycode(String goodsorigincountrycode) {
        this.goodsorigincountrycode = goodsorigincountrycode;
    }

    public String getDestinationcountrycode() {
        return destinationcountrycode;
    }

    public void setDestinationcountrycode(String destinationcountrycode) {
        this.destinationcountrycode = destinationcountrycode;
    }

    public String getTermsofshippingcompanycode() {
        return termsofshippingcompanycode;
    }

    public void setTermsofshippingcompanycode(String termsofshippingcompanycode) {
        this.termsofshippingcompanycode = termsofshippingcompanycode;
    }

    public String getTermsofshippingcode() {
        return termsofshippingcode;
    }

    public void setTermsofshippingcode(String termsofshippingcode) {
        this.termsofshippingcode = termsofshippingcode;
    }

    public String getPrecarriageby() {
        return precarriageby;
    }

    public void setPrecarriageby(String precarriageby) {
        this.precarriageby = precarriageby;
    }

    public String getPlaceofreceiptbyprecarriage() {
        return placeofreceiptbyprecarriage;
    }

    public void setPlaceofreceiptbyprecarriage(String placeofreceiptbyprecarriage) {
        this.placeofreceiptbyprecarriage = placeofreceiptbyprecarriage;
    }

    public String getVesselflightno() {
        return vesselflightno;
    }

    public void setVesselflightno(String vesselflightno) {
        this.vesselflightno = vesselflightno;
    }

    public String getPortofloadingcode() {
        return portofloadingcode;
    }

    public void setPortofloadingcode(String portofloadingcode) {
        this.portofloadingcode = portofloadingcode;
    }

    public String getPortofdischargecode() {
        return portofdischargecode;
    }

    public void setPortofdischargecode(String portofdischargecode) {
        this.portofdischargecode = portofdischargecode;
    }

    public String getFinaldestinationcode() {
        return finaldestinationcode;
    }

    public void setFinaldestinationcode(String finaldestinationcode) {
        this.finaldestinationcode = finaldestinationcode;
    }

    public String getWeightumcode() {
        return weightumcode;
    }

    public void setWeightumcode(String weightumcode) {
        this.weightumcode = weightumcode;
    }

    public BigDecimal getGrossweight() {
        return grossweight;
    }

    public void setGrossweight(BigDecimal grossweight) {
        this.grossweight = grossweight;
    }

    public BigDecimal getNettweight() {
        return nettweight;
    }

    public void setNettweight(BigDecimal nettweight) {
        this.nettweight = nettweight;
    }

    public BigDecimal getTotalquantity() {
        return totalquantity;
    }

    public void setTotalquantity(BigDecimal totalquantity) {
        this.totalquantity = totalquantity;
    }

    public int getTotalnumberofbales() {
        return totalnumberofbales;
    }

    public void setTotalnumberofbales(int totalnumberofbales) {
        this.totalnumberofbales = totalnumberofbales;
    }

    public String getPackdimensionin() {
        return packdimensionin;
    }

    public void setPackdimensionin(String packdimensionin) {
        this.packdimensionin = packdimensionin;
    }

    public int getSampledrawn() {
        return sampledrawn;
    }

    public void setSampledrawn(int sampledrawn) {
        this.sampledrawn = sampledrawn;
    }

    public String getPackinglistno() {
        return packinglistno;
    }

    public void setPackinglistno(String packinglistno) {
        this.packinglistno = packinglistno;
    }

    public Date getPackinglistdate() {
        return packinglistdate;
    }

    public void setPackinglistdate(Date packinglistdate) {
        this.packinglistdate = packinglistdate;
    }

    public String getShiplinecustomersuppliertype() {
        return shiplinecustomersuppliertype;
    }

    public void setShiplinecustomersuppliertype(String shiplinecustomersuppliertype) {
        this.shiplinecustomersuppliertype = shiplinecustomersuppliertype;
    }

    public String getShiplinecustomersuppliercode() {
        return shiplinecustomersuppliercode;
    }

    public void setShiplinecustomersuppliercode(String shiplinecustomersuppliercode) {
        this.shiplinecustomersuppliercode = shiplinecustomersuppliercode;
    }

    public String getChacustomersuppliertype() {
        return chacustomersuppliertype;
    }

    public void setChacustomersuppliertype(String chacustomersuppliertype) {
        this.chacustomersuppliertype = chacustomersuppliertype;
    }

    public String getChacustomersuppliercode() {
        return chacustomersuppliercode;
    }

    public void setChacustomersuppliercode(String chacustomersuppliercode) {
        this.chacustomersuppliercode = chacustomersuppliercode;
    }

    public String getForwardercustomersuppliertype() {
        return forwardercustomersuppliertype;
    }

    public void setForwardercustomersuppliertype(String forwardercustomersuppliertype) {
        this.forwardercustomersuppliertype = forwardercustomersuppliertype;
    }

    public String getForwardercustomersuppliercode() {
        return forwardercustomersuppliercode;
    }

    public void setForwardercustomersuppliercode(String forwardercustomersuppliercode) {
        this.forwardercustomersuppliercode = forwardercustomersuppliercode;
    }

    public Date getEtadate() {
        return etadate;
    }

    public void setEtadate(Date etadate) {
        this.etadate = etadate;
    }

    public Date getEtddate() {
        return etddate;
    }

    public void setEtddate(Date etddate) {
        this.etddate = etddate;
    }

    public Date getExworkdate() {
        return exworkdate;
    }

    public void setExworkdate(Date exworkdate) {
        this.exworkdate = exworkdate;
    }

    public int getLclfcl() {
        return lclfcl;
    }

    public void setLclfcl(int lclfcl) {
        this.lclfcl = lclfcl;
    }

    public String getChallanno() {
        return challanno != null ? challanno.trim() : challanno;
    }

    public void setChallanno(String challanno) {
        this.challanno = challanno;
    }

    public Date getChallandate() {
        return challandate;
    }

    public void setChallandate(Date challandate) {
        this.challandate = challandate;
    }

    public String getLrno() {
        return lrno;
    }

    public void setLrno(String lrno) {
        this.lrno = lrno;
    }

    public Date getLrdate() {
        return lrdate;
    }

    public void setLrdate(Date lrdate) {
        this.lrdate = lrdate;
    }

    public String getHouseawbbillno() {
        return houseawbbillno;
    }

    public void setHouseawbbillno(String houseawbbillno) {
        this.houseawbbillno = houseawbbillno;
    }

    public Date getHouseawbbilldate() {
        return houseawbbilldate;
    }

    public void setHouseawbbilldate(Date houseawbbilldate) {
        this.houseawbbilldate = houseawbbilldate;
    }

    public Date getBldate() {
        return bldate;
    }

    public void setBldate(Date bldate) {
        this.bldate = bldate;
    }

    public String getBlnumber() {
        return blnumber;
    }

    public void setBlnumber(String blnumber) {
        this.blnumber = blnumber;
    }

    public String getTransportercodcsmsuppliertype() {
        return transportercodcsmsuppliertype;
    }

    public void setTransportercodcsmsuppliertype(String transportercodcsmsuppliertype) {
        this.transportercodcsmsuppliertype = transportercodcsmsuppliertype;
    }

    public String getTransportercodcsmsuppliercode() {
        return transportercodcsmsuppliercode;
    }

    public void setTransportercodcsmsuppliercode(String transportercodcsmsuppliercode) {
        this.transportercodcsmsuppliercode = transportercodcsmsuppliercode;
    }

    public String getTruckno() {
        return truckno;
    }

    public void setTruckno(String truckno) {
        this.truckno = truckno;
    }

    public String getContainerno() {
        return containerno;
    }

    public void setContainerno(String containerno) {
        this.containerno = containerno;
    }

    public String getContainersize() {
        return containersize;
    }

    public void setContainersize(String containersize) {
        this.containersize = containersize;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public String getShippingmarks1() {
        return shippingmarks1;
    }

    public void setShippingmarks1(String shippingmarks1) {
        this.shippingmarks1 = shippingmarks1;
    }

    public String getShippingmarks2() {
        return shippingmarks2;
    }

    public void setShippingmarks2(String shippingmarks2) {
        this.shippingmarks2 = shippingmarks2;
    }

    public String getShippingmarks3() {
        return shippingmarks3;
    }

    public void setShippingmarks3(String shippingmarks3) {
        this.shippingmarks3 = shippingmarks3;
    }

    public String getShippingmarks4() {
        return shippingmarks4;
    }

    public void setShippingmarks4(String shippingmarks4) {
        this.shippingmarks4 = shippingmarks4;
    }

    public String getAr3Code() {
        return ar3Code;
    }

    public void setAr3Code(String ar3Code) {
        this.ar3Code = ar3Code;
    }

    public String getAr3Exciseyearregno() {
        return ar3Exciseyearregno;
    }

    public void setAr3Exciseyearregno(String ar3Exciseyearregno) {
        this.ar3Exciseyearregno = ar3Exciseyearregno;
    }

    public String getAr3Exciseyearcode() {
        return ar3Exciseyearcode;
    }

    public void setAr3Exciseyearcode(String ar3Exciseyearcode) {
        this.ar3Exciseyearcode = ar3Exciseyearcode;
    }

    public Date getAr3Date() {
        return ar3Date;
    }

    public void setAr3Date(Date ar3Date) {
        this.ar3Date = ar3Date;
    }

    public String getAr4Code() {
        return ar4Code;
    }

    public void setAr4Code(String ar4Code) {
        this.ar4Code = ar4Code;
    }

    public String getAr4Exciseyearregno() {
        return ar4Exciseyearregno;
    }

    public void setAr4Exciseyearregno(String ar4Exciseyearregno) {
        this.ar4Exciseyearregno = ar4Exciseyearregno;
    }

    public String getAr4Exciseyearcode() {
        return ar4Exciseyearcode;
    }

    public void setAr4Exciseyearcode(String ar4Exciseyearcode) {
        this.ar4Exciseyearcode = ar4Exciseyearcode;
    }

    public Date getAr4Date() {
        return ar4Date;
    }

    public void setAr4Date(Date ar4Date) {
        this.ar4Date = ar4Date;
    }

    public int getSealingoption() {
        return sealingoption;
    }

    public void setSealingoption(int sealingoption) {
        this.sealingoption = sealingoption;
    }

    public int getExaminationat() {
        return examinationat;
    }

    public void setExaminationat(int examinationat) {
        this.examinationat = examinationat;
    }

    public int getUnderrebate() {
        return underrebate;
    }

    public void setUnderrebate(int underrebate) {
        this.underrebate = underrebate;
    }

    public Date getTimeofremovalofgoods() {
        return timeofremovalofgoods;
    }

    public void setTimeofremovalofgoods(Date timeofremovalofgoods) {
        this.timeofremovalofgoods = timeofremovalofgoods;
    }

    public Timestamp getInvoiceissuetime() {
        return invoiceissuetime;
    }

    public void setInvoiceissuetime(Timestamp invoiceissuetime) {
        this.invoiceissuetime = invoiceissuetime;
    }

    public String getFooterlines() {
        return footerlines;
    }

    public void setFooterlines(String footerlines) {
        this.footerlines = footerlines;
    }

    public String getIppolicyno() {
        return ippolicyno;
    }

    public void setIppolicyno(String ippolicyno) {
        this.ippolicyno = ippolicyno;
    }

    public Date getIppolicydate() {
        return ippolicydate;
    }

    public void setIppolicydate(Date ippolicydate) {
        this.ippolicydate = ippolicydate;
    }

    public String getInsurancecompany() {
        return insurancecompany;
    }

    public void setInsurancecompany(String insurancecompany) {
        this.insurancecompany = insurancecompany;
    }

    public BigDecimal getPolicypremiumrate() {
        return policypremiumrate;
    }

    public void setPolicypremiumrate(BigDecimal policypremiumrate) {
        this.policypremiumrate = policypremiumrate;
    }

    public BigDecimal getCustomerpremiumrate() {
        return customerpremiumrate;
    }

    public void setCustomerpremiumrate(BigDecimal customerpremiumrate) {
        this.customerpremiumrate = customerpremiumrate;
    }

    public BigDecimal getInsurancemarkup() {
        return insurancemarkup;
    }

    public void setInsurancemarkup(BigDecimal insurancemarkup) {
        this.insurancemarkup = insurancemarkup;
    }

    public String getMarineinsurancemicno() {
        return marineinsurancemicno;
    }

    public void setMarineinsurancemicno(String marineinsurancemicno) {
        this.marineinsurancemicno = marineinsurancemicno;
    }

    public String getSchemetypecode() {
        return schemetypecode;
    }

    public void setSchemetypecode(String schemetypecode) {
        this.schemetypecode = schemetypecode;
    }

    public String getAladvancelicensecode() {
        return aladvancelicensecode;
    }

    public void setAladvancelicensecode(String aladvancelicensecode) {
        this.aladvancelicensecode = aladvancelicensecode;
    }

    public Date getAlapplicationdate() {
        return alapplicationdate;
    }

    public void setAlapplicationdate(Date alapplicationdate) {
        this.alapplicationdate = alapplicationdate;
    }

    public String getAdvancelicenseno() {
        return advancelicenseno;
    }

    public void setAdvancelicenseno(String advancelicenseno) {
        this.advancelicenseno = advancelicenseno;
    }

    public Date getAdvancelicensedate() {
        return advancelicensedate;
    }

    public void setAdvancelicensedate(Date advancelicensedate) {
        this.advancelicensedate = advancelicensedate;
    }

    public String getEpcgepcgapplicationcode() {
        return epcgepcgapplicationcode;
    }

    public void setEpcgepcgapplicationcode(String epcgepcgapplicationcode) {
        this.epcgepcgapplicationcode = epcgepcgapplicationcode;
    }

    public Date getEpcgapplicationdate() {
        return epcgapplicationdate;
    }

    public void setEpcgapplicationdate(Date epcgapplicationdate) {
        this.epcgapplicationdate = epcgapplicationdate;
    }

    public String getEpcglicenseno() {
        return epcglicenseno;
    }

    public void setEpcglicenseno(String epcglicenseno) {
        this.epcglicenseno = epcglicenseno;
    }

    public Date getEpcglicensedate() {
        return epcglicensedate;
    }

    public void setEpcglicensedate(Date epcglicensedate) {
        this.epcglicensedate = epcglicensedate;
    }

    public String getAdvancelicensefileno() {
        return advancelicensefileno;
    }

    public void setAdvancelicensefileno(String advancelicensefileno) {
        this.advancelicensefileno = advancelicensefileno;
    }

    public Date getAdvancelicensefiledate() {
        return advancelicensefiledate;
    }

    public void setAdvancelicensefiledate(Date advancelicensefiledate) {
        this.advancelicensefiledate = advancelicensefiledate;
    }

    public String getInvoicecurrencycode() {
        return invoicecurrencycode;
    }

    public void setInvoicecurrencycode(String invoicecurrencycode) {
        this.invoicecurrencycode = invoicecurrencycode;
    }

    public String getOrdercurrencycode() {
        return ordercurrencycode;
    }

    public void setOrdercurrencycode(String ordercurrencycode) {
        this.ordercurrencycode = ordercurrencycode;
    }

    public BigDecimal getExchangerateofcontract() {
        return exchangerateofcontract;
    }

    public void setExchangerateofcontract(BigDecimal exchangerateofcontract) {
        this.exchangerateofcontract = exchangerateofcontract;
    }

    public String getTermsofdeliverycompanycode() {
        return termsofdeliverycompanycode;
    }

    public void setTermsofdeliverycompanycode(String termsofdeliverycompanycode) {
        this.termsofdeliverycompanycode = termsofdeliverycompanycode;
    }

    public String getTermsofdeliverycode() {
        return termsofdeliverycode;
    }

    public void setTermsofdeliverycode(String termsofdeliverycode) {
        this.termsofdeliverycode = termsofdeliverycode;
    }

    public String getTermsofpaymentcompanycode() {
        return termsofpaymentcompanycode;
    }

    public void setTermsofpaymentcompanycode(String termsofpaymentcompanycode) {
        this.termsofpaymentcompanycode = termsofpaymentcompanycode;
    }

    public String getTermsofpaymentcode() {
        return termsofpaymentcode;
    }

    public void setTermsofpaymentcode(String termsofpaymentcode) {
        this.termsofpaymentcode = termsofpaymentcode;
    }

    public String getLclcno() {
        return lclcno;
    }

    public void setLclcno(String lclcno) {
        this.lclcno = lclcno;
    }

    public Date getLclcdate() {
        return lclcdate;
    }

    public void setLclcdate(Date lclcdate) {
        this.lclcdate = lclcdate;
    }

    public BigDecimal getBcformofrs() {
        return bcformofrs;
    }

    public void setBcformofrs(BigDecimal bcformofrs) {
        this.bcformofrs = bcformofrs;
    }

    public String getPricelistordertype() {
        return pricelistordertype;
    }

    public void setPricelistordertype(String pricelistordertype) {
        this.pricelistordertype = pricelistordertype;
    }

    public String getPricelistcode() {
        return pricelistcode;
    }

    public void setPricelistcode(String pricelistcode) {
        this.pricelistcode = pricelistcode;
    }

    public BigDecimal getBasicvalue() {
        return basicvalue;
    }

    public void setBasicvalue(BigDecimal basicvalue) {
        this.basicvalue = basicvalue;
    }

    public BigDecimal getGrossvalue() {
        return grossvalue;
    }

    public void setGrossvalue(BigDecimal grossvalue) {
        this.grossvalue = grossvalue;
    }

    public String getTaxtemplatetemplatetype() {
        return taxtemplatetemplatetype;
    }

    public void setTaxtemplatetemplatetype(String taxtemplatetemplatetype) {
        this.taxtemplatetemplatetype = taxtemplatetemplatetype;
    }

    public String getTaxtemplatecode() {
        return taxtemplatecode;
    }

    public void setTaxtemplatecode(String taxtemplatecode) {
        this.taxtemplatecode = taxtemplatecode;
    }

    public BigDecimal getRoundoffvalue() {
        return roundoffvalue;
    }

    public void setRoundoffvalue(BigDecimal roundoffvalue) {
        this.roundoffvalue = roundoffvalue;
    }

    public BigDecimal getNettvalue() {
        return nettvalue;
    }

    public void setNettvalue(BigDecimal nettvalue) {
        this.nettvalue = nettvalue;
    }

    public String getRoundoffitaxcode() {
        return roundoffitaxcode;
    }

    public void setRoundoffitaxcode(String roundoffitaxcode) {
        this.roundoffitaxcode = roundoffitaxcode;
    }

    public int getUsedcounter() {
        return usedcounter;
    }

    public void setUsedcounter(int usedcounter) {
        this.usedcounter = usedcounter;
    }

    public int getPostingflag() {
        return postingflag;
    }

    public void setPostingflag(int postingflag) {
        this.postingflag = postingflag;
    }

    public Timestamp getPrintdateandtime() {
        return printdateandtime;
    }

    public void setPrintdateandtime(Timestamp printdateandtime) {
        this.printdateandtime = printdateandtime;
    }

    public String getPrintuser() {
        return printuser;
    }

    public void setPrintuser(String printuser) {
        this.printuser = printuser;
    }

    public Timestamp getReprintdateandtime() {
        return reprintdateandtime;
    }

    public void setReprintdateandtime(Timestamp reprintdateandtime) {
        this.reprintdateandtime = reprintdateandtime;
    }

    public String getReprintuser() {
        return reprintuser;
    }

    public void setReprintuser(String reprintuser) {
        this.reprintuser = reprintuser;
    }

    public String getInvoicerateoption() {
        return invoicerateoption;
    }

    public void setInvoicerateoption(String invoicerateoption) {
        this.invoicerateoption = invoicerateoption;
    }

    public String getInvoicecreationfrom() {
        return invoicecreationfrom;
    }

    public void setInvoicecreationfrom(String invoicecreationfrom) {
        this.invoicecreationfrom = invoicecreationfrom;
    }

    public String getTermsoflogcode() {
        return termsoflogcode;
    }

    public void setTermsoflogcode(String termsoflogcode) {
        this.termsoflogcode = termsoflogcode;
    }

    public String getLogreasoncode() {
        return logreasoncode;
    }

    public void setLogreasoncode(String logreasoncode) {
        this.logreasoncode = logreasoncode;
    }

    public String getFindocbusinessunitcode() {
        return findocbusinessunitcode;
    }

    public void setFindocbusinessunitcode(String findocbusinessunitcode) {
        this.findocbusinessunitcode = findocbusinessunitcode;
    }

    public Integer getFindocfinancialyearcode() {
        return findocfinancialyearcode;
    }

    public void setFindocfinancialyearcode(Integer findocfinancialyearcode) {
        this.findocfinancialyearcode = findocfinancialyearcode;
    }

    public String getFindoctemplatecode() {
        return findoctemplatecode;
    }

    public void setFindoctemplatecode(String findoctemplatecode) {
        this.findoctemplatecode = findoctemplatecode;
    }

    public String getFindocstatisticalgroupcode() {
        return findocstatisticalgroupcode;
    }

    public void setFindocstatisticalgroupcode(String findocstatisticalgroupcode) {
        this.findocstatisticalgroupcode = findocstatisticalgroupcode;
    }

    public String getFindoccode() {
        return findoccode;
    }

    public void setFindoccode(String findoccode) {
        this.findoccode = findoccode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSapmessage() {
        return sapmessage;
    }

    public void setSapmessage(String sapmessage) {
        this.sapmessage = sapmessage;
    }

    public String getDomexciseflag() {
        return domexciseflag;
    }

    public void setDomexciseflag(String domexciseflag) {
        this.domexciseflag = domexciseflag;
    }

    public String getDomexcisesapmessage() {
        return domexcisesapmessage;
    }

    public void setDomexcisesapmessage(String domexcisesapmessage) {
        this.domexcisesapmessage = domexcisesapmessage;
    }

    public String getExciseflag() {
        return exciseflag;
    }

    public void setExciseflag(String exciseflag) {
        this.exciseflag = exciseflag;
    }

    public String getExcisesapmessage() {
        return excisesapmessage;
    }

    public void setExcisesapmessage(String excisesapmessage) {
        this.excisesapmessage = excisesapmessage;
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

    public long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    public String getOthfindoccode() {
        return othfindoccode;
    }

    public void setOthfindoccode(String othfindoccode) {
        this.othfindoccode = othfindoccode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plantinvoice that = (Plantinvoice) o;
        return totalnumberofbales == that.totalnumberofbales && sampledrawn == that.sampledrawn && lclfcl == that.lclfcl && sealingoption == that.sealingoption && examinationat == that.examinationat && underrebate == that.underrebate && usedcounter == that.usedcounter && postingflag == that.postingflag && absuniqueid == that.absuniqueid && Objects.equals(id, that.id) && Objects.equals(invoicetypecode, that.invoicetypecode) && Objects.equals(invoicedate, that.invoicedate) && Objects.equals(creationdate, that.creationdate) && Objects.equals(firmcode, that.firmcode) && Objects.equals(firmbankbankcountrycode, that.firmbankbankcountrycode) && Objects.equals(firmbankcode, that.firmbankcode) && Objects.equals(firmbankbranchcode, that.firmbankbranchcode) && Objects.equals(factorycompanycode, that.factorycompanycode) && Objects.equals(factorycode, that.factorycode) && Objects.equals(contractnocountercode, that.contractnocountercode) && Objects.equals(contractnocode, that.contractnocode) && Objects.equals(contractdate, that.contractdate) && Objects.equals(exporterrefno, that.exporterrefno) && Objects.equals(buyersporefno, that.buyersporefno) && Objects.equals(psinvoicecode, that.psinvoicecode) && Objects.equals(custominvoicecode, that.custominvoicecode) && Objects.equals(custominvoicetypecode, that.custominvoicetypecode) && Objects.equals(commercialinvoicecode, that.commercialinvoicecode) && Objects.equals(commercialinvoicetypecode, that.commercialinvoicetypecode) && Objects.equals(mrnheadercode, that.mrnheadercode) && Objects.equals(mrnheadermrnprefixcode, that.mrnheadermrnprefixcode) && Objects.equals(salinvoiceprvcountercode, that.salinvoiceprvcountercode) && Objects.equals(salesinvoiceprovisionalcode, that.salesinvoiceprovisionalcode) && Objects.equals(consigneecustomersuppliertype, that.consigneecustomersuppliertype) && Objects.equals(consigneecustomersuppliercode, that.consigneecustomersuppliercode) && Objects.equals(deliverypointuniqueid, that.deliverypointuniqueid) && Objects.equals(deliverypointcode, that.deliverypointcode) && Objects.equals(deliverypointtype, that.deliverypointtype) && Objects.equals(buyerifotccustomersuppliertype, that.buyerifotccustomersuppliertype) && Objects.equals(buyerifotccustomersuppliercode, that.buyerifotccustomersuppliercode) && Objects.equals(plantinvoicedate, that.plantinvoicedate) && Objects.equals(notifypartycsmsuppliertype, that.notifypartycsmsuppliertype) && Objects.equals(notifypartycsmsuppliercode, that.notifypartycsmsuppliercode) && Objects.equals(buyersbankbankcountrycode, that.buyersbankbankcountrycode) && Objects.equals(buyersbankcode, that.buyersbankcode) && Objects.equals(buyersbankbranchcode, that.buyersbankbranchcode) && Objects.equals(agent1Code, that.agent1Code) && Objects.equals(agent1Oncompanycode, that.agent1Oncompanycode) && Objects.equals(agent1Oncode, that.agent1Oncode) && Objects.equals(agent1Currencycode, that.agent1Currencycode) && Objects.equals(agent1Amount, that.agent1Amount) && Objects.equals(agent1Commissionpercentage, that.agent1Commissionpercentage) && Objects.equals(agent2Code, that.agent2Code) && Objects.equals(agent2Oncompanycode, that.agent2Oncompanycode) && Objects.equals(agent2Oncode, that.agent2Oncode) && Objects.equals(agent2Currencycode, that.agent2Currencycode) && Objects.equals(agent2Amount, that.agent2Amount) && Objects.equals(agent2Commissionpercentage, that.agent2Commissionpercentage) && Objects.equals(agent3Code, that.agent3Code) && Objects.equals(agent3Oncompanycode, that.agent3Oncompanycode) && Objects.equals(agent3Oncode, that.agent3Oncode) && Objects.equals(agent3Currencycode, that.agent3Currencycode) && Objects.equals(agent3Amount, that.agent3Amount) && Objects.equals(agent3Commissionpercentage, that.agent3Commissionpercentage) && Objects.equals(agent4Code, that.agent4Code) && Objects.equals(agent4Oncompanycode, that.agent4Oncompanycode) && Objects.equals(agent4Oncode, that.agent4Oncode) && Objects.equals(agent4Currencycode, that.agent4Currencycode) && Objects.equals(agent4Amount, that.agent4Amount) && Objects.equals(agent4Commissionpercentage, that.agent4Commissionpercentage) && Objects.equals(agent5Code, that.agent5Code) && Objects.equals(agent5Oncompanycode, that.agent5Oncompanycode) && Objects.equals(agent5Oncode, that.agent5Oncode) && Objects.equals(agent5Currencycode, that.agent5Currencycode) && Objects.equals(agent5Amount, that.agent5Amount) && Objects.equals(agent5Commissionpercentage, that.agent5Commissionpercentage) && Objects.equals(bottlesealno, that.bottlesealno) && Objects.equals(customerbottlesealno, that.customerbottlesealno) && Objects.equals(companysealno, that.companysealno) && Objects.equals(goodsorigincountrycode, that.goodsorigincountrycode) && Objects.equals(destinationcountrycode, that.destinationcountrycode) && Objects.equals(termsofshippingcompanycode, that.termsofshippingcompanycode) && Objects.equals(termsofshippingcode, that.termsofshippingcode) && Objects.equals(precarriageby, that.precarriageby) && Objects.equals(placeofreceiptbyprecarriage, that.placeofreceiptbyprecarriage) && Objects.equals(vesselflightno, that.vesselflightno) && Objects.equals(portofloadingcode, that.portofloadingcode) && Objects.equals(portofdischargecode, that.portofdischargecode) && Objects.equals(finaldestinationcode, that.finaldestinationcode) && Objects.equals(weightumcode, that.weightumcode) && Objects.equals(grossweight, that.grossweight) && Objects.equals(nettweight, that.nettweight) && Objects.equals(totalquantity, that.totalquantity) && Objects.equals(packdimensionin, that.packdimensionin) && Objects.equals(packinglistno, that.packinglistno) && Objects.equals(packinglistdate, that.packinglistdate) && Objects.equals(shiplinecustomersuppliertype, that.shiplinecustomersuppliertype) && Objects.equals(shiplinecustomersuppliercode, that.shiplinecustomersuppliercode) && Objects.equals(chacustomersuppliertype, that.chacustomersuppliertype) && Objects.equals(chacustomersuppliercode, that.chacustomersuppliercode) && Objects.equals(forwardercustomersuppliertype, that.forwardercustomersuppliertype) && Objects.equals(forwardercustomersuppliercode, that.forwardercustomersuppliercode) && Objects.equals(etadate, that.etadate) && Objects.equals(etddate, that.etddate) && Objects.equals(exworkdate, that.exworkdate) && Objects.equals(challanno, that.challanno) && Objects.equals(challandate, that.challandate) && Objects.equals(lrno, that.lrno) && Objects.equals(lrdate, that.lrdate) && Objects.equals(houseawbbillno, that.houseawbbillno) && Objects.equals(houseawbbilldate, that.houseawbbilldate) && Objects.equals(bldate, that.bldate) && Objects.equals(blnumber, that.blnumber) && Objects.equals(transportercodcsmsuppliertype, that.transportercodcsmsuppliertype) && Objects.equals(transportercodcsmsuppliercode, that.transportercodcsmsuppliercode) && Objects.equals(truckno, that.truckno) && Objects.equals(containerno, that.containerno) && Objects.equals(containersize, that.containersize) && Objects.equals(category, that.category) && Objects.equals(itemdescription, that.itemdescription) && Objects.equals(shippingmarks1, that.shippingmarks1) && Objects.equals(shippingmarks2, that.shippingmarks2) && Objects.equals(shippingmarks3, that.shippingmarks3) && Objects.equals(shippingmarks4, that.shippingmarks4) && Objects.equals(ar3Code, that.ar3Code) && Objects.equals(ar3Exciseyearregno, that.ar3Exciseyearregno) && Objects.equals(ar3Exciseyearcode, that.ar3Exciseyearcode) && Objects.equals(ar3Date, that.ar3Date) && Objects.equals(ar4Code, that.ar4Code) && Objects.equals(ar4Exciseyearregno, that.ar4Exciseyearregno) && Objects.equals(ar4Exciseyearcode, that.ar4Exciseyearcode) && Objects.equals(ar4Date, that.ar4Date) && Objects.equals(timeofremovalofgoods, that.timeofremovalofgoods) && Objects.equals(invoiceissuetime, that.invoiceissuetime) && Objects.equals(footerlines, that.footerlines) && Objects.equals(ippolicyno, that.ippolicyno) && Objects.equals(ippolicydate, that.ippolicydate) && Objects.equals(insurancecompany, that.insurancecompany) && Objects.equals(policypremiumrate, that.policypremiumrate) && Objects.equals(customerpremiumrate, that.customerpremiumrate) && Objects.equals(insurancemarkup, that.insurancemarkup) && Objects.equals(marineinsurancemicno, that.marineinsurancemicno) && Objects.equals(schemetypecode, that.schemetypecode) && Objects.equals(aladvancelicensecode, that.aladvancelicensecode) && Objects.equals(alapplicationdate, that.alapplicationdate) && Objects.equals(advancelicenseno, that.advancelicenseno) && Objects.equals(advancelicensedate, that.advancelicensedate) && Objects.equals(epcgepcgapplicationcode, that.epcgepcgapplicationcode) && Objects.equals(epcgapplicationdate, that.epcgapplicationdate) && Objects.equals(epcglicenseno, that.epcglicenseno) && Objects.equals(epcglicensedate, that.epcglicensedate) && Objects.equals(advancelicensefileno, that.advancelicensefileno) && Objects.equals(advancelicensefiledate, that.advancelicensefiledate) && Objects.equals(invoicecurrencycode, that.invoicecurrencycode) && Objects.equals(ordercurrencycode, that.ordercurrencycode) && Objects.equals(exchangerateofcontract, that.exchangerateofcontract) && Objects.equals(termsofdeliverycompanycode, that.termsofdeliverycompanycode) && Objects.equals(termsofdeliverycode, that.termsofdeliverycode) && Objects.equals(termsofpaymentcompanycode, that.termsofpaymentcompanycode) && Objects.equals(termsofpaymentcode, that.termsofpaymentcode) && Objects.equals(lclcno, that.lclcno) && Objects.equals(lclcdate, that.lclcdate) && Objects.equals(bcformofrs, that.bcformofrs) && Objects.equals(pricelistordertype, that.pricelistordertype) && Objects.equals(pricelistcode, that.pricelistcode) && Objects.equals(basicvalue, that.basicvalue) && Objects.equals(grossvalue, that.grossvalue) && Objects.equals(taxtemplatetemplatetype, that.taxtemplatetemplatetype) && Objects.equals(taxtemplatecode, that.taxtemplatecode) && Objects.equals(roundoffvalue, that.roundoffvalue) && Objects.equals(nettvalue, that.nettvalue) && Objects.equals(roundoffitaxcode, that.roundoffitaxcode) && Objects.equals(printdateandtime, that.printdateandtime) && Objects.equals(printuser, that.printuser) && Objects.equals(reprintdateandtime, that.reprintdateandtime) && Objects.equals(reprintuser, that.reprintuser) && Objects.equals(invoicerateoption, that.invoicerateoption) && Objects.equals(invoicecreationfrom, that.invoicecreationfrom) && Objects.equals(termsoflogcode, that.termsoflogcode) && Objects.equals(logreasoncode, that.logreasoncode) && Objects.equals(findocbusinessunitcode, that.findocbusinessunitcode) && Objects.equals(findocfinancialyearcode, that.findocfinancialyearcode) && Objects.equals(findoctemplatecode, that.findoctemplatecode) && Objects.equals(findocstatisticalgroupcode, that.findocstatisticalgroupcode) && Objects.equals(findoccode, that.findoccode) && Objects.equals(flag, that.flag) && Objects.equals(sapmessage, that.sapmessage) && Objects.equals(domexciseflag, that.domexciseflag) && Objects.equals(domexcisesapmessage, that.domexcisesapmessage) && Objects.equals(exciseflag, that.exciseflag) && Objects.equals(excisesapmessage, that.excisesapmessage) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(creationuser, that.creationuser) && Objects.equals(lastupdatedatetime, that.lastupdatedatetime) && Objects.equals(lastupdateuser, that.lastupdateuser) && Objects.equals(othfindoccode, that.othfindoccode) && Objects.equals(creationdatetimeutc, that.creationdatetimeutc) && Objects.equals(lastupdatedatetimeutc, that.lastupdatedatetimeutc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoicetypecode, invoicedate, creationdate, firmcode, firmbankbankcountrycode, firmbankcode, firmbankbranchcode, factorycompanycode, factorycode, contractnocountercode, contractnocode, contractdate, exporterrefno, buyersporefno, psinvoicecode, custominvoicecode, custominvoicetypecode, commercialinvoicecode, commercialinvoicetypecode, mrnheadercode, mrnheadermrnprefixcode, salinvoiceprvcountercode, salesinvoiceprovisionalcode, consigneecustomersuppliertype, consigneecustomersuppliercode, deliverypointuniqueid, deliverypointcode, deliverypointtype, buyerifotccustomersuppliertype, buyerifotccustomersuppliercode, plantinvoicedate, notifypartycsmsuppliertype, notifypartycsmsuppliercode, buyersbankbankcountrycode, buyersbankcode, buyersbankbranchcode, agent1Code, agent1Oncompanycode, agent1Oncode, agent1Currencycode, agent1Amount, agent1Commissionpercentage, agent2Code, agent2Oncompanycode, agent2Oncode, agent2Currencycode, agent2Amount, agent2Commissionpercentage, agent3Code, agent3Oncompanycode, agent3Oncode, agent3Currencycode, agent3Amount, agent3Commissionpercentage, agent4Code, agent4Oncompanycode, agent4Oncode, agent4Currencycode, agent4Amount, agent4Commissionpercentage, agent5Code, agent5Oncompanycode, agent5Oncode, agent5Currencycode, agent5Amount, agent5Commissionpercentage, bottlesealno, customerbottlesealno, companysealno, goodsorigincountrycode, destinationcountrycode, termsofshippingcompanycode, termsofshippingcode, precarriageby, placeofreceiptbyprecarriage, vesselflightno, portofloadingcode, portofdischargecode, finaldestinationcode, weightumcode, grossweight, nettweight, totalquantity, totalnumberofbales, packdimensionin, sampledrawn, packinglistno, packinglistdate, shiplinecustomersuppliertype, shiplinecustomersuppliercode, chacustomersuppliertype, chacustomersuppliercode, forwardercustomersuppliertype, forwardercustomersuppliercode, etadate, etddate, exworkdate, lclfcl, challanno, challandate, lrno, lrdate, houseawbbillno, houseawbbilldate, bldate, blnumber, transportercodcsmsuppliertype, transportercodcsmsuppliercode, truckno, containerno, containersize, category, itemdescription, shippingmarks1, shippingmarks2, shippingmarks3, shippingmarks4, ar3Code, ar3Exciseyearregno, ar3Exciseyearcode, ar3Date, ar4Code, ar4Exciseyearregno, ar4Exciseyearcode, ar4Date, sealingoption, examinationat, underrebate, timeofremovalofgoods, invoiceissuetime, footerlines, ippolicyno, ippolicydate, insurancecompany, policypremiumrate, customerpremiumrate, insurancemarkup, marineinsurancemicno, schemetypecode, aladvancelicensecode, alapplicationdate, advancelicenseno, advancelicensedate, epcgepcgapplicationcode, epcgapplicationdate, epcglicenseno, epcglicensedate, advancelicensefileno, advancelicensefiledate, invoicecurrencycode, ordercurrencycode, exchangerateofcontract, termsofdeliverycompanycode, termsofdeliverycode, termsofpaymentcompanycode, termsofpaymentcode, lclcno, lclcdate, bcformofrs, pricelistordertype, pricelistcode, basicvalue, grossvalue, taxtemplatetemplatetype, taxtemplatecode, roundoffvalue, nettvalue, roundoffitaxcode, usedcounter, postingflag, printdateandtime, printuser, reprintdateandtime, reprintuser, invoicerateoption, invoicecreationfrom, termsoflogcode, logreasoncode, findocbusinessunitcode, findocfinancialyearcode, findoctemplatecode, findocstatisticalgroupcode, findoccode, flag, sapmessage, domexciseflag, domexcisesapmessage, exciseflag, excisesapmessage, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid, othfindoccode, creationdatetimeutc, lastupdatedatetimeutc);
    }
}
