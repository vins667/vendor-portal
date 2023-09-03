package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "itemtype")
public class Itemtype {
    @EmbeddedId
    private ItemtypeId id;
    private String divisioncode;
    private String alloweddivisions;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private String itemnature;
    private Integer codemaxlength;
    private Short sellingtype;
    private String internalitemtypecompanycode;
    private String internalitemtypecode;
    private Short valid;
    private Short managedbybox;
    private String structure;
    private String statusallowed;
    private String defaultprimaryuomcode;
    private Short secondaryunitcontrolled;
    private String defaultsecondaryuomcode;
    private BigDecimal secondaryunsteadycvsfactor;
    private Short packagingunitcontrolled;
    private String uompackagingtype;
    private String defaultpackaginguomcode;
    private BigDecimal packagingunsteadycvsfactor;
    private String closingcriteriapolicycode;
    private String specializedtype;
    private Short washsymbolcontrolled;
    private Short qualitycontrolled;
    private String qualitylabel;
    private Short costforquality;
    private Short costforfailedquality;
    private Short lotcontrolled;
    private String lotlabel;
    private String chooselotcode;
    private Short containercontrolled;
    private String containeritemtypecompanycode;
    private String containeritemtypecode;
    private String choosecontainercode;
    private Short elementcontrolled;
    private String elementlabel;
    private String elementcountercompanycode;
    private String elementcountercode;
    private String chooseelementscode;
    private Short projectcontrolled;
    private Short statisticalgroupcontrolled;
    private Short costforstatisticalgroup;
    private Short customercontrolled;
    private Short suppliercontrolled;
    private Short logmanagement;
    private Short iwllogmanagement;
    private String checkproductcode;
    private String productexternalcodecode;
    private String fullitemdescriptionpolicycode;
    private String alternativeproductpolicycode;
    private String checkitemwarehouselinkcode;
    private Integer valuationsequence;
    private Short aceprojectcontrolled;
    private Short acestatisticalgroupcontrolled;
    private Short acecustomercontrolled;
    private Short acesuppliercontrolled;
    private Short acelotignored;
    private Short aceitemelementignored;
    private Short acequalitylevelignored;
    private String dftjobqueueplanningname;
    private Integer horizontalmatrixsubcodenr;
    private Integer verticalmatrixsubcodenr;
    private String firstusergrpcompanycode;
    private String firstusergrpcode;
    private String secondusergrpcompanycode;
    private String secondusergrpcode;
    private String thirdusergrpcompanycode;
    private String thirdusergrpcode;
    private String fourthusergrpcompanycode;
    private String fourthusergrpcode;
    private String fifthusergrpcompanycode;
    private String fifthusergrpcode;
    private Short bomvalid;
    private String choosebomcode;
    private Integer bomlastsubcodenr;
    private Short routingvalid;
    private String chooseroutingcode;
    private Integer rtglastsubcodenr;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Integer lastsubcodenr;
    private Integer lastprimarynr;
    private Integer firstsecondarynr;
    private Integer lastforwhsmgtnr;
    private String itemcodemask;
    private String subcodelengths;
    private String routingcodemask;
    private String routingsubcodelengths;
    private String bomcodemask;
    private String bomsubcodelengths;
    private String owningcompanycode;
    private Long absuniqueid;

    public ItemtypeId getId() {
        return id;
    }

    public void setId(ItemtypeId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DIVISIONCODE", nullable = true, length = 3)
    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    @Basic
    @Column(name = "ALLOWEDDIVISIONS", nullable = true, length = 90)
    public String getAlloweddivisions() {
        return alloweddivisions;
    }

    public void setAlloweddivisions(String alloweddivisions) {
        this.alloweddivisions = alloweddivisions;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = false, length = 100)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 40)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 60)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    @Basic
    @Column(name = "ITEMNATURE", nullable = false, length = 1)
    public String getItemnature() {
        return itemnature;
    }

    public void setItemnature(String itemnature) {
        this.itemnature = itemnature;
    }

    @Basic
    @Column(name = "CODEMAXLENGTH", nullable = false)
    public Integer getCodemaxlength() {
        return codemaxlength;
    }

    public void setCodemaxlength(Integer codemaxlength) {
        this.codemaxlength = codemaxlength;
    }

    @Basic
    @Column(name = "SELLINGTYPE", nullable = false)
    public Short getSellingtype() {
        return sellingtype;
    }

    public void setSellingtype(Short sellingtype) {
        this.sellingtype = sellingtype;
    }

    @Basic
    @Column(name = "INTERNALITEMTYPECOMPANYCODE", nullable = true, length = 3)
    public String getInternalitemtypecompanycode() {
        return internalitemtypecompanycode;
    }

    public void setInternalitemtypecompanycode(String internalitemtypecompanycode) {
        this.internalitemtypecompanycode = internalitemtypecompanycode;
    }

    @Basic
    @Column(name = "INTERNALITEMTYPECODE", nullable = true, length = 3)
    public String getInternalitemtypecode() {
        return internalitemtypecode;
    }

    public void setInternalitemtypecode(String internalitemtypecode) {
        this.internalitemtypecode = internalitemtypecode;
    }

    @Basic
    @Column(name = "VALID", nullable = false)
    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "MANAGEDBYBOX", nullable = false)
    public Short getManagedbybox() {
        return managedbybox;
    }

    public void setManagedbybox(Short managedbybox) {
        this.managedbybox = managedbybox;
    }

    @Basic
    @Column(name = "STRUCTURE", nullable = false, length = 2)
    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    @Basic
    @Column(name = "STATUSALLOWED", nullable = false, length = 2)
    public String getStatusallowed() {
        return statusallowed;
    }

    public void setStatusallowed(String statusallowed) {
        this.statusallowed = statusallowed;
    }

    @Basic
    @Column(name = "DEFAULTPRIMARYUOMCODE", nullable = true, length = 3)
    public String getDefaultprimaryuomcode() {
        return defaultprimaryuomcode;
    }

    public void setDefaultprimaryuomcode(String defaultprimaryuomcode) {
        this.defaultprimaryuomcode = defaultprimaryuomcode;
    }

    @Basic
    @Column(name = "SECONDARYUNITCONTROLLED", nullable = false)
    public Short getSecondaryunitcontrolled() {
        return secondaryunitcontrolled;
    }

    public void setSecondaryunitcontrolled(Short secondaryunitcontrolled) {
        this.secondaryunitcontrolled = secondaryunitcontrolled;
    }

    @Basic
    @Column(name = "DEFAULTSECONDARYUOMCODE", nullable = true, length = 3)
    public String getDefaultsecondaryuomcode() {
        return defaultsecondaryuomcode;
    }

    public void setDefaultsecondaryuomcode(String defaultsecondaryuomcode) {
        this.defaultsecondaryuomcode = defaultsecondaryuomcode;
    }

    @Basic
    @Column(name = "SECONDARYUNSTEADYCVSFACTOR", nullable = true, precision = 5)
    public BigDecimal getSecondaryunsteadycvsfactor() {
        return secondaryunsteadycvsfactor;
    }

    public void setSecondaryunsteadycvsfactor(BigDecimal secondaryunsteadycvsfactor) {
        this.secondaryunsteadycvsfactor = secondaryunsteadycvsfactor;
    }

    @Basic
    @Column(name = "PACKAGINGUNITCONTROLLED", nullable = false)
    public Short getPackagingunitcontrolled() {
        return packagingunitcontrolled;
    }

    public void setPackagingunitcontrolled(Short packagingunitcontrolled) {
        this.packagingunitcontrolled = packagingunitcontrolled;
    }

    @Basic
    @Column(name = "UOMPACKAGINGTYPE", nullable = true, length = 2)
    public String getUompackagingtype() {
        return uompackagingtype;
    }

    public void setUompackagingtype(String uompackagingtype) {
        this.uompackagingtype = uompackagingtype;
    }

    @Basic
    @Column(name = "DEFAULTPACKAGINGUOMCODE", nullable = true, length = 3)
    public String getDefaultpackaginguomcode() {
        return defaultpackaginguomcode;
    }

    public void setDefaultpackaginguomcode(String defaultpackaginguomcode) {
        this.defaultpackaginguomcode = defaultpackaginguomcode;
    }

    @Basic
    @Column(name = "PACKAGINGUNSTEADYCVSFACTOR", nullable = true, precision = 5)
    public BigDecimal getPackagingunsteadycvsfactor() {
        return packagingunsteadycvsfactor;
    }

    public void setPackagingunsteadycvsfactor(BigDecimal packagingunsteadycvsfactor) {
        this.packagingunsteadycvsfactor = packagingunsteadycvsfactor;
    }

    @Basic
    @Column(name = "CLOSINGCRITERIAPOLICYCODE", nullable = true, length = 20)
    public String getClosingcriteriapolicycode() {
        return closingcriteriapolicycode;
    }

    public void setClosingcriteriapolicycode(String closingcriteriapolicycode) {
        this.closingcriteriapolicycode = closingcriteriapolicycode;
    }

    @Basic
    @Column(name = "SPECIALIZEDTYPE", nullable = false, length = 2)
    public String getSpecializedtype() {
        return specializedtype;
    }

    public void setSpecializedtype(String specializedtype) {
        this.specializedtype = specializedtype;
    }

    @Basic
    @Column(name = "WASHSYMBOLCONTROLLED", nullable = false)
    public Short getWashsymbolcontrolled() {
        return washsymbolcontrolled;
    }

    public void setWashsymbolcontrolled(Short washsymbolcontrolled) {
        this.washsymbolcontrolled = washsymbolcontrolled;
    }

    @Basic
    @Column(name = "QUALITYCONTROLLED", nullable = false)
    public Short getQualitycontrolled() {
        return qualitycontrolled;
    }

    public void setQualitycontrolled(Short qualitycontrolled) {
        this.qualitycontrolled = qualitycontrolled;
    }

    @Basic
    @Column(name = "QUALITYLABEL", nullable = true, length = 40)
    public String getQualitylabel() {
        return qualitylabel;
    }

    public void setQualitylabel(String qualitylabel) {
        this.qualitylabel = qualitylabel;
    }

    @Basic
    @Column(name = "COSTFORQUALITY", nullable = false)
    public Short getCostforquality() {
        return costforquality;
    }

    public void setCostforquality(Short costforquality) {
        this.costforquality = costforquality;
    }

    @Basic
    @Column(name = "COSTFORFAILEDQUALITY", nullable = false)
    public Short getCostforfailedquality() {
        return costforfailedquality;
    }

    public void setCostforfailedquality(Short costforfailedquality) {
        this.costforfailedquality = costforfailedquality;
    }

    @Basic
    @Column(name = "LOTCONTROLLED", nullable = false)
    public Short getLotcontrolled() {
        return lotcontrolled;
    }

    public void setLotcontrolled(Short lotcontrolled) {
        this.lotcontrolled = lotcontrolled;
    }

    @Basic
    @Column(name = "LOTLABEL", nullable = true, length = 40)
    public String getLotlabel() {
        return lotlabel;
    }

    public void setLotlabel(String lotlabel) {
        this.lotlabel = lotlabel;
    }

    @Basic
    @Column(name = "CHOOSELOTCODE", nullable = true, length = 20)
    public String getChooselotcode() {
        return chooselotcode;
    }

    public void setChooselotcode(String chooselotcode) {
        this.chooselotcode = chooselotcode;
    }

    @Basic
    @Column(name = "CONTAINERCONTROLLED", nullable = false)
    public Short getContainercontrolled() {
        return containercontrolled;
    }

    public void setContainercontrolled(Short containercontrolled) {
        this.containercontrolled = containercontrolled;
    }

    @Basic
    @Column(name = "CONTAINERITEMTYPECOMPANYCODE", nullable = true, length = 3)
    public String getContaineritemtypecompanycode() {
        return containeritemtypecompanycode;
    }

    public void setContaineritemtypecompanycode(String containeritemtypecompanycode) {
        this.containeritemtypecompanycode = containeritemtypecompanycode;
    }

    @Basic
    @Column(name = "CONTAINERITEMTYPECODE", nullable = true, length = 3)
    public String getContaineritemtypecode() {
        return containeritemtypecode;
    }

    public void setContaineritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
    }

    @Basic
    @Column(name = "CHOOSECONTAINERCODE", nullable = true, length = 20)
    public String getChoosecontainercode() {
        return choosecontainercode;
    }

    public void setChoosecontainercode(String choosecontainercode) {
        this.choosecontainercode = choosecontainercode;
    }

    @Basic
    @Column(name = "ELEMENTCONTROLLED", nullable = false)
    public Short getElementcontrolled() {
        return elementcontrolled;
    }

    public void setElementcontrolled(Short elementcontrolled) {
        this.elementcontrolled = elementcontrolled;
    }

    @Basic
    @Column(name = "ELEMENTLABEL", nullable = true, length = 40)
    public String getElementlabel() {
        return elementlabel;
    }

    public void setElementlabel(String elementlabel) {
        this.elementlabel = elementlabel;
    }

    @Basic
    @Column(name = "ELEMENTCOUNTERCOMPANYCODE", nullable = true, length = 3)
    public String getElementcountercompanycode() {
        return elementcountercompanycode;
    }

    public void setElementcountercompanycode(String elementcountercompanycode) {
        this.elementcountercompanycode = elementcountercompanycode;
    }

    @Basic
    @Column(name = "ELEMENTCOUNTERCODE", nullable = true, length = 8)
    public String getElementcountercode() {
        return elementcountercode;
    }

    public void setElementcountercode(String elementcountercode) {
        this.elementcountercode = elementcountercode;
    }

    @Basic
    @Column(name = "CHOOSEELEMENTSCODE", nullable = true, length = 20)
    public String getChooseelementscode() {
        return chooseelementscode;
    }

    public void setChooseelementscode(String chooseelementscode) {
        this.chooseelementscode = chooseelementscode;
    }

    @Basic
    @Column(name = "PROJECTCONTROLLED", nullable = false)
    public Short getProjectcontrolled() {
        return projectcontrolled;
    }

    public void setProjectcontrolled(Short projectcontrolled) {
        this.projectcontrolled = projectcontrolled;
    }

    @Basic
    @Column(name = "STATISTICALGROUPCONTROLLED", nullable = false)
    public Short getStatisticalgroupcontrolled() {
        return statisticalgroupcontrolled;
    }

    public void setStatisticalgroupcontrolled(Short statisticalgroupcontrolled) {
        this.statisticalgroupcontrolled = statisticalgroupcontrolled;
    }

    @Basic
    @Column(name = "COSTFORSTATISTICALGROUP", nullable = false)
    public Short getCostforstatisticalgroup() {
        return costforstatisticalgroup;
    }

    public void setCostforstatisticalgroup(Short costforstatisticalgroup) {
        this.costforstatisticalgroup = costforstatisticalgroup;
    }

    @Basic
    @Column(name = "CUSTOMERCONTROLLED", nullable = false)
    public Short getCustomercontrolled() {
        return customercontrolled;
    }

    public void setCustomercontrolled(Short customercontrolled) {
        this.customercontrolled = customercontrolled;
    }

    @Basic
    @Column(name = "SUPPLIERCONTROLLED", nullable = false)
    public Short getSuppliercontrolled() {
        return suppliercontrolled;
    }

    public void setSuppliercontrolled(Short suppliercontrolled) {
        this.suppliercontrolled = suppliercontrolled;
    }

    @Basic
    @Column(name = "LOGMANAGEMENT", nullable = false)
    public Short getLogmanagement() {
        return logmanagement;
    }

    public void setLogmanagement(Short logmanagement) {
        this.logmanagement = logmanagement;
    }

    @Basic
    @Column(name = "IWLLOGMANAGEMENT", nullable = false)
    public Short getIwllogmanagement() {
        return iwllogmanagement;
    }

    public void setIwllogmanagement(Short iwllogmanagement) {
        this.iwllogmanagement = iwllogmanagement;
    }

    @Basic
    @Column(name = "CHECKPRODUCTCODE", nullable = true, length = 20)
    public String getCheckproductcode() {
        return checkproductcode;
    }

    public void setCheckproductcode(String checkproductcode) {
        this.checkproductcode = checkproductcode;
    }

    @Basic
    @Column(name = "PRODUCTEXTERNALCODECODE", nullable = true, length = 20)
    public String getProductexternalcodecode() {
        return productexternalcodecode;
    }

    public void setProductexternalcodecode(String productexternalcodecode) {
        this.productexternalcodecode = productexternalcodecode;
    }

    @Basic
    @Column(name = "FULLITEMDESCRIPTIONPOLICYCODE", nullable = true, length = 20)
    public String getFullitemdescriptionpolicycode() {
        return fullitemdescriptionpolicycode;
    }

    public void setFullitemdescriptionpolicycode(String fullitemdescriptionpolicycode) {
        this.fullitemdescriptionpolicycode = fullitemdescriptionpolicycode;
    }

    @Basic
    @Column(name = "ALTERNATIVEPRODUCTPOLICYCODE", nullable = true, length = 20)
    public String getAlternativeproductpolicycode() {
        return alternativeproductpolicycode;
    }

    public void setAlternativeproductpolicycode(String alternativeproductpolicycode) {
        this.alternativeproductpolicycode = alternativeproductpolicycode;
    }

    @Basic
    @Column(name = "CHECKITEMWAREHOUSELINKCODE", nullable = true, length = 20)
    public String getCheckitemwarehouselinkcode() {
        return checkitemwarehouselinkcode;
    }

    public void setCheckitemwarehouselinkcode(String checkitemwarehouselinkcode) {
        this.checkitemwarehouselinkcode = checkitemwarehouselinkcode;
    }

    @Basic
    @Column(name = "VALUATIONSEQUENCE", nullable = false)
    public Integer getValuationsequence() {
        return valuationsequence;
    }

    public void setValuationsequence(Integer valuationsequence) {
        this.valuationsequence = valuationsequence;
    }

    @Basic
    @Column(name = "ACEPROJECTCONTROLLED", nullable = false)
    public Short getAceprojectcontrolled() {
        return aceprojectcontrolled;
    }

    public void setAceprojectcontrolled(Short aceprojectcontrolled) {
        this.aceprojectcontrolled = aceprojectcontrolled;
    }

    @Basic
    @Column(name = "ACESTATISTICALGROUPCONTROLLED", nullable = false)
    public Short getAcestatisticalgroupcontrolled() {
        return acestatisticalgroupcontrolled;
    }

    public void setAcestatisticalgroupcontrolled(Short acestatisticalgroupcontrolled) {
        this.acestatisticalgroupcontrolled = acestatisticalgroupcontrolled;
    }

    @Basic
    @Column(name = "ACECUSTOMERCONTROLLED", nullable = false)
    public Short getAcecustomercontrolled() {
        return acecustomercontrolled;
    }

    public void setAcecustomercontrolled(Short acecustomercontrolled) {
        this.acecustomercontrolled = acecustomercontrolled;
    }

    @Basic
    @Column(name = "ACESUPPLIERCONTROLLED", nullable = false)
    public Short getAcesuppliercontrolled() {
        return acesuppliercontrolled;
    }

    public void setAcesuppliercontrolled(Short acesuppliercontrolled) {
        this.acesuppliercontrolled = acesuppliercontrolled;
    }

    @Basic
    @Column(name = "ACELOTIGNORED", nullable = false)
    public Short getAcelotignored() {
        return acelotignored;
    }

    public void setAcelotignored(Short acelotignored) {
        this.acelotignored = acelotignored;
    }

    @Basic
    @Column(name = "ACEITEMELEMENTIGNORED", nullable = false)
    public Short getAceitemelementignored() {
        return aceitemelementignored;
    }

    public void setAceitemelementignored(Short aceitemelementignored) {
        this.aceitemelementignored = aceitemelementignored;
    }

    @Basic
    @Column(name = "ACEQUALITYLEVELIGNORED", nullable = false)
    public Short getAcequalitylevelignored() {
        return acequalitylevelignored;
    }

    public void setAcequalitylevelignored(Short acequalitylevelignored) {
        this.acequalitylevelignored = acequalitylevelignored;
    }

    @Basic
    @Column(name = "DFTJOBQUEUEPLANNINGNAME", nullable = true, length = 20)
    public String getDftjobqueueplanningname() {
        return dftjobqueueplanningname;
    }

    public void setDftjobqueueplanningname(String dftjobqueueplanningname) {
        this.dftjobqueueplanningname = dftjobqueueplanningname;
    }

    @Basic
    @Column(name = "HORIZONTALMATRIXSUBCODENR", nullable = false)
    public Integer getHorizontalmatrixsubcodenr() {
        return horizontalmatrixsubcodenr;
    }

    public void setHorizontalmatrixsubcodenr(Integer horizontalmatrixsubcodenr) {
        this.horizontalmatrixsubcodenr = horizontalmatrixsubcodenr;
    }

    @Basic
    @Column(name = "VERTICALMATRIXSUBCODENR", nullable = false)
    public Integer getVerticalmatrixsubcodenr() {
        return verticalmatrixsubcodenr;
    }

    public void setVerticalmatrixsubcodenr(Integer verticalmatrixsubcodenr) {
        this.verticalmatrixsubcodenr = verticalmatrixsubcodenr;
    }

    @Basic
    @Column(name = "FIRSTUSERGRPCOMPANYCODE", nullable = true, length = 3)
    public String getFirstusergrpcompanycode() {
        return firstusergrpcompanycode;
    }

    public void setFirstusergrpcompanycode(String firstusergrpcompanycode) {
        this.firstusergrpcompanycode = firstusergrpcompanycode;
    }

    @Basic
    @Column(name = "FIRSTUSERGRPCODE", nullable = true, length = 3)
    public String getFirstusergrpcode() {
        return firstusergrpcode;
    }

    public void setFirstusergrpcode(String firstusergrpcode) {
        this.firstusergrpcode = firstusergrpcode;
    }

    @Basic
    @Column(name = "SECONDUSERGRPCOMPANYCODE", nullable = true, length = 3)
    public String getSecondusergrpcompanycode() {
        return secondusergrpcompanycode;
    }

    public void setSecondusergrpcompanycode(String secondusergrpcompanycode) {
        this.secondusergrpcompanycode = secondusergrpcompanycode;
    }

    @Basic
    @Column(name = "SECONDUSERGRPCODE", nullable = true, length = 3)
    public String getSecondusergrpcode() {
        return secondusergrpcode;
    }

    public void setSecondusergrpcode(String secondusergrpcode) {
        this.secondusergrpcode = secondusergrpcode;
    }

    @Basic
    @Column(name = "THIRDUSERGRPCOMPANYCODE", nullable = true, length = 3)
    public String getThirdusergrpcompanycode() {
        return thirdusergrpcompanycode;
    }

    public void setThirdusergrpcompanycode(String thirdusergrpcompanycode) {
        this.thirdusergrpcompanycode = thirdusergrpcompanycode;
    }

    @Basic
    @Column(name = "THIRDUSERGRPCODE", nullable = true, length = 3)
    public String getThirdusergrpcode() {
        return thirdusergrpcode;
    }

    public void setThirdusergrpcode(String thirdusergrpcode) {
        this.thirdusergrpcode = thirdusergrpcode;
    }

    @Basic
    @Column(name = "FOURTHUSERGRPCOMPANYCODE", nullable = true, length = 3)
    public String getFourthusergrpcompanycode() {
        return fourthusergrpcompanycode;
    }

    public void setFourthusergrpcompanycode(String fourthusergrpcompanycode) {
        this.fourthusergrpcompanycode = fourthusergrpcompanycode;
    }

    @Basic
    @Column(name = "FOURTHUSERGRPCODE", nullable = true, length = 3)
    public String getFourthusergrpcode() {
        return fourthusergrpcode;
    }

    public void setFourthusergrpcode(String fourthusergrpcode) {
        this.fourthusergrpcode = fourthusergrpcode;
    }

    @Basic
    @Column(name = "FIFTHUSERGRPCOMPANYCODE", nullable = true, length = 3)
    public String getFifthusergrpcompanycode() {
        return fifthusergrpcompanycode;
    }

    public void setFifthusergrpcompanycode(String fifthusergrpcompanycode) {
        this.fifthusergrpcompanycode = fifthusergrpcompanycode;
    }

    @Basic
    @Column(name = "FIFTHUSERGRPCODE", nullable = true, length = 3)
    public String getFifthusergrpcode() {
        return fifthusergrpcode;
    }

    public void setFifthusergrpcode(String fifthusergrpcode) {
        this.fifthusergrpcode = fifthusergrpcode;
    }

    @Basic
    @Column(name = "BOMVALID", nullable = false)
    public Short getBomvalid() {
        return bomvalid;
    }

    public void setBomvalid(Short bomvalid) {
        this.bomvalid = bomvalid;
    }

    @Basic
    @Column(name = "CHOOSEBOMCODE", nullable = true, length = 20)
    public String getChoosebomcode() {
        return choosebomcode;
    }

    public void setChoosebomcode(String choosebomcode) {
        this.choosebomcode = choosebomcode;
    }

    @Basic
    @Column(name = "BOMLASTSUBCODENR", nullable = false)
    public Integer getBomlastsubcodenr() {
        return bomlastsubcodenr;
    }

    public void setBomlastsubcodenr(Integer bomlastsubcodenr) {
        this.bomlastsubcodenr = bomlastsubcodenr;
    }

    @Basic
    @Column(name = "ROUTINGVALID", nullable = false)
    public Short getRoutingvalid() {
        return routingvalid;
    }

    public void setRoutingvalid(Short routingvalid) {
        this.routingvalid = routingvalid;
    }

    @Basic
    @Column(name = "CHOOSEROUTINGCODE", nullable = true, length = 20)
    public String getChooseroutingcode() {
        return chooseroutingcode;
    }

    public void setChooseroutingcode(String chooseroutingcode) {
        this.chooseroutingcode = chooseroutingcode;
    }

    @Basic
    @Column(name = "RTGLASTSUBCODENR", nullable = false)
    public Integer getRtglastsubcodenr() {
        return rtglastsubcodenr;
    }

    public void setRtglastsubcodenr(Integer rtglastsubcodenr) {
        this.rtglastsubcodenr = rtglastsubcodenr;
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
    @Column(name = "LASTSUBCODENR", nullable = false)
    public Integer getLastsubcodenr() {
        return lastsubcodenr;
    }

    public void setLastsubcodenr(Integer lastsubcodenr) {
        this.lastsubcodenr = lastsubcodenr;
    }

    @Basic
    @Column(name = "LASTPRIMARYNR", nullable = false)
    public Integer getLastprimarynr() {
        return lastprimarynr;
    }

    public void setLastprimarynr(Integer lastprimarynr) {
        this.lastprimarynr = lastprimarynr;
    }

    @Basic
    @Column(name = "FIRSTSECONDARYNR", nullable = false)
    public Integer getFirstsecondarynr() {
        return firstsecondarynr;
    }

    public void setFirstsecondarynr(Integer firstsecondarynr) {
        this.firstsecondarynr = firstsecondarynr;
    }

    @Basic
    @Column(name = "LASTFORWHSMGTNR", nullable = false)
    public Integer getLastforwhsmgtnr() {
        return lastforwhsmgtnr;
    }

    public void setLastforwhsmgtnr(Integer lastforwhsmgtnr) {
        this.lastforwhsmgtnr = lastforwhsmgtnr;
    }

    @Basic
    @Column(name = "ITEMCODEMASK", nullable = true, length = 150)
    public String getItemcodemask() {
        return itemcodemask;
    }

    public void setItemcodemask(String itemcodemask) {
        this.itemcodemask = itemcodemask;
    }

    @Basic
    @Column(name = "SUBCODELENGTHS", nullable = true, length = 50)
    public String getSubcodelengths() {
        return subcodelengths;
    }

    public void setSubcodelengths(String subcodelengths) {
        this.subcodelengths = subcodelengths;
    }

    @Basic
    @Column(name = "ROUTINGCODEMASK", nullable = true, length = 150)
    public String getRoutingcodemask() {
        return routingcodemask;
    }

    public void setRoutingcodemask(String routingcodemask) {
        this.routingcodemask = routingcodemask;
    }

    @Basic
    @Column(name = "ROUTINGSUBCODELENGTHS", nullable = true, length = 50)
    public String getRoutingsubcodelengths() {
        return routingsubcodelengths;
    }

    public void setRoutingsubcodelengths(String routingsubcodelengths) {
        this.routingsubcodelengths = routingsubcodelengths;
    }

    @Basic
    @Column(name = "BOMCODEMASK", nullable = true, length = 150)
    public String getBomcodemask() {
        return bomcodemask;
    }

    public void setBomcodemask(String bomcodemask) {
        this.bomcodemask = bomcodemask;
    }

    @Basic
    @Column(name = "BOMSUBCODELENGTHS", nullable = true, length = 50)
    public String getBomsubcodelengths() {
        return bomsubcodelengths;
    }

    public void setBomsubcodelengths(String bomsubcodelengths) {
        this.bomsubcodelengths = bomsubcodelengths;
    }

    @Basic
    @Column(name = "OWNINGCOMPANYCODE", nullable = true, length = 3)
    public String getOwningcompanycode() {
        return owningcompanycode;
    }

    public void setOwningcompanycode(String owningcompanycode) {
        this.owningcompanycode = owningcompanycode;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itemtype itemtype = (Itemtype) o;
        return Objects.equals(id, itemtype.id) &&
            Objects.equals(divisioncode, itemtype.divisioncode) &&
            Objects.equals(alloweddivisions, itemtype.alloweddivisions) &&
            Objects.equals(longdescription, itemtype.longdescription) &&
            Objects.equals(shortdescription, itemtype.shortdescription) &&
            Objects.equals(searchdescription, itemtype.searchdescription) &&
            Objects.equals(itemnature, itemtype.itemnature) &&
            Objects.equals(codemaxlength, itemtype.codemaxlength) &&
            Objects.equals(sellingtype, itemtype.sellingtype) &&
            Objects.equals(internalitemtypecompanycode, itemtype.internalitemtypecompanycode) &&
            Objects.equals(internalitemtypecode, itemtype.internalitemtypecode) &&
            Objects.equals(valid, itemtype.valid) &&
            Objects.equals(managedbybox, itemtype.managedbybox) &&
            Objects.equals(structure, itemtype.structure) &&
            Objects.equals(statusallowed, itemtype.statusallowed) &&
            Objects.equals(defaultprimaryuomcode, itemtype.defaultprimaryuomcode) &&
            Objects.equals(secondaryunitcontrolled, itemtype.secondaryunitcontrolled) &&
            Objects.equals(defaultsecondaryuomcode, itemtype.defaultsecondaryuomcode) &&
            Objects.equals(secondaryunsteadycvsfactor, itemtype.secondaryunsteadycvsfactor) &&
            Objects.equals(packagingunitcontrolled, itemtype.packagingunitcontrolled) &&
            Objects.equals(uompackagingtype, itemtype.uompackagingtype) &&
            Objects.equals(defaultpackaginguomcode, itemtype.defaultpackaginguomcode) &&
            Objects.equals(packagingunsteadycvsfactor, itemtype.packagingunsteadycvsfactor) &&
            Objects.equals(closingcriteriapolicycode, itemtype.closingcriteriapolicycode) &&
            Objects.equals(specializedtype, itemtype.specializedtype) &&
            Objects.equals(washsymbolcontrolled, itemtype.washsymbolcontrolled) &&
            Objects.equals(qualitycontrolled, itemtype.qualitycontrolled) &&
            Objects.equals(qualitylabel, itemtype.qualitylabel) &&
            Objects.equals(costforquality, itemtype.costforquality) &&
            Objects.equals(costforfailedquality, itemtype.costforfailedquality) &&
            Objects.equals(lotcontrolled, itemtype.lotcontrolled) &&
            Objects.equals(lotlabel, itemtype.lotlabel) &&
            Objects.equals(chooselotcode, itemtype.chooselotcode) &&
            Objects.equals(containercontrolled, itemtype.containercontrolled) &&
            Objects.equals(containeritemtypecompanycode, itemtype.containeritemtypecompanycode) &&
            Objects.equals(containeritemtypecode, itemtype.containeritemtypecode) &&
            Objects.equals(choosecontainercode, itemtype.choosecontainercode) &&
            Objects.equals(elementcontrolled, itemtype.elementcontrolled) &&
            Objects.equals(elementlabel, itemtype.elementlabel) &&
            Objects.equals(elementcountercompanycode, itemtype.elementcountercompanycode) &&
            Objects.equals(elementcountercode, itemtype.elementcountercode) &&
            Objects.equals(chooseelementscode, itemtype.chooseelementscode) &&
            Objects.equals(projectcontrolled, itemtype.projectcontrolled) &&
            Objects.equals(statisticalgroupcontrolled, itemtype.statisticalgroupcontrolled) &&
            Objects.equals(costforstatisticalgroup, itemtype.costforstatisticalgroup) &&
            Objects.equals(customercontrolled, itemtype.customercontrolled) &&
            Objects.equals(suppliercontrolled, itemtype.suppliercontrolled) &&
            Objects.equals(logmanagement, itemtype.logmanagement) &&
            Objects.equals(iwllogmanagement, itemtype.iwllogmanagement) &&
            Objects.equals(checkproductcode, itemtype.checkproductcode) &&
            Objects.equals(productexternalcodecode, itemtype.productexternalcodecode) &&
            Objects.equals(fullitemdescriptionpolicycode, itemtype.fullitemdescriptionpolicycode) &&
            Objects.equals(alternativeproductpolicycode, itemtype.alternativeproductpolicycode) &&
            Objects.equals(checkitemwarehouselinkcode, itemtype.checkitemwarehouselinkcode) &&
            Objects.equals(valuationsequence, itemtype.valuationsequence) &&
            Objects.equals(aceprojectcontrolled, itemtype.aceprojectcontrolled) &&
            Objects.equals(acestatisticalgroupcontrolled, itemtype.acestatisticalgroupcontrolled) &&
            Objects.equals(acecustomercontrolled, itemtype.acecustomercontrolled) &&
            Objects.equals(acesuppliercontrolled, itemtype.acesuppliercontrolled) &&
            Objects.equals(acelotignored, itemtype.acelotignored) &&
            Objects.equals(aceitemelementignored, itemtype.aceitemelementignored) &&
            Objects.equals(acequalitylevelignored, itemtype.acequalitylevelignored) &&
            Objects.equals(dftjobqueueplanningname, itemtype.dftjobqueueplanningname) &&
            Objects.equals(horizontalmatrixsubcodenr, itemtype.horizontalmatrixsubcodenr) &&
            Objects.equals(verticalmatrixsubcodenr, itemtype.verticalmatrixsubcodenr) &&
            Objects.equals(firstusergrpcompanycode, itemtype.firstusergrpcompanycode) &&
            Objects.equals(firstusergrpcode, itemtype.firstusergrpcode) &&
            Objects.equals(secondusergrpcompanycode, itemtype.secondusergrpcompanycode) &&
            Objects.equals(secondusergrpcode, itemtype.secondusergrpcode) &&
            Objects.equals(thirdusergrpcompanycode, itemtype.thirdusergrpcompanycode) &&
            Objects.equals(thirdusergrpcode, itemtype.thirdusergrpcode) &&
            Objects.equals(fourthusergrpcompanycode, itemtype.fourthusergrpcompanycode) &&
            Objects.equals(fourthusergrpcode, itemtype.fourthusergrpcode) &&
            Objects.equals(fifthusergrpcompanycode, itemtype.fifthusergrpcompanycode) &&
            Objects.equals(fifthusergrpcode, itemtype.fifthusergrpcode) &&
            Objects.equals(bomvalid, itemtype.bomvalid) &&
            Objects.equals(choosebomcode, itemtype.choosebomcode) &&
            Objects.equals(bomlastsubcodenr, itemtype.bomlastsubcodenr) &&
            Objects.equals(routingvalid, itemtype.routingvalid) &&
            Objects.equals(chooseroutingcode, itemtype.chooseroutingcode) &&
            Objects.equals(rtglastsubcodenr, itemtype.rtglastsubcodenr) &&
            Objects.equals(creationdatetime, itemtype.creationdatetime) &&
            Objects.equals(creationuser, itemtype.creationuser) &&
            Objects.equals(lastupdatedatetime, itemtype.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, itemtype.lastupdateuser) &&
            Objects.equals(lastsubcodenr, itemtype.lastsubcodenr) &&
            Objects.equals(lastprimarynr, itemtype.lastprimarynr) &&
            Objects.equals(firstsecondarynr, itemtype.firstsecondarynr) &&
            Objects.equals(lastforwhsmgtnr, itemtype.lastforwhsmgtnr) &&
            Objects.equals(itemcodemask, itemtype.itemcodemask) &&
            Objects.equals(subcodelengths, itemtype.subcodelengths) &&
            Objects.equals(routingcodemask, itemtype.routingcodemask) &&
            Objects.equals(routingsubcodelengths, itemtype.routingsubcodelengths) &&
            Objects.equals(bomcodemask, itemtype.bomcodemask) &&
            Objects.equals(bomsubcodelengths, itemtype.bomsubcodelengths) &&
            Objects.equals(owningcompanycode, itemtype.owningcompanycode) &&
            Objects.equals(absuniqueid, itemtype.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, divisioncode, alloweddivisions, longdescription, shortdescription, searchdescription, itemnature, codemaxlength, sellingtype, internalitemtypecompanycode, internalitemtypecode, valid, managedbybox, structure, statusallowed, defaultprimaryuomcode, secondaryunitcontrolled, defaultsecondaryuomcode, secondaryunsteadycvsfactor, packagingunitcontrolled, uompackagingtype, defaultpackaginguomcode, packagingunsteadycvsfactor, closingcriteriapolicycode, specializedtype, washsymbolcontrolled, qualitycontrolled, qualitylabel, costforquality, costforfailedquality, lotcontrolled, lotlabel, chooselotcode, containercontrolled, containeritemtypecompanycode, containeritemtypecode, choosecontainercode, elementcontrolled, elementlabel, elementcountercompanycode, elementcountercode, chooseelementscode, projectcontrolled, statisticalgroupcontrolled, costforstatisticalgroup, customercontrolled, suppliercontrolled, logmanagement, iwllogmanagement, checkproductcode, productexternalcodecode, fullitemdescriptionpolicycode, alternativeproductpolicycode, checkitemwarehouselinkcode, valuationsequence, aceprojectcontrolled, acestatisticalgroupcontrolled, acecustomercontrolled, acesuppliercontrolled, acelotignored, aceitemelementignored, acequalitylevelignored, dftjobqueueplanningname, horizontalmatrixsubcodenr, verticalmatrixsubcodenr, firstusergrpcompanycode, firstusergrpcode, secondusergrpcompanycode, secondusergrpcode, thirdusergrpcompanycode, thirdusergrpcode, fourthusergrpcompanycode, fourthusergrpcode, fifthusergrpcompanycode, fifthusergrpcode, bomvalid, choosebomcode, bomlastsubcodenr, routingvalid, chooseroutingcode, rtglastsubcodenr, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, lastsubcodenr, lastprimarynr, firstsecondarynr, lastforwhsmgtnr, itemcodemask, subcodelengths, routingcodemask, routingsubcodelengths, bomcodemask, bomsubcodelengths, owningcompanycode, absuniqueid);
    }
}
