package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "VIEWFINDOCUMENT")
public class Viewfindocument {
    @EmbeddedId
    private ViewfindocumentId id;
    private String findocstatisticalgroupcode;
    private Date documentdate;
    private String glcode;
    private String documenttypecode;
    private String documenttypedescription;
    private Date postingdate;
    private String commercialinvoicecode;
    private String commercialinvoicedivisioncode;
    private String plantinvoicedivisioncode;
    private String plantinvoicecode;
    private String poadvancepurordercountercode;
    private String poadvancepurchaseordercode;
    private Integer poadvancelineno;
    private String mrndivisioncode;
    private String mrncode;
    private String referencetext1;
    private String headerremark;
    private String headerreport;
    private String mrnmrnprefixcode;
    private String purchaseinvoicedivisioncode;
    private String purchaseinvoicecode;
    private Date purchaseinvoiceinvoicedate;
    private Date duedate;
    private long absuniqueid;
    private Date chequedate;
    private String chequenumber;
    private String directinvoicedivisioncode;
    private String directinvoicecountercode;
    private String directinvoicecode;
    private String sdcreditprovisionalcountercode;
    private String sdcreditprovisionalcode;
    private String employeecode;
    private String expenseinvoicedivisioncode;
    private String expenseinvoicecode;
    private Date expenseinvoiceinvoicedate;
    private String slcustomersuppliertype;
    private String slcustomersuppliercode;
    private BigDecimal amountincc;
    private BigDecimal amountindc;
    private String comments;
    private String profitcentercode;
    private String costcentercode;
    private String companycurrencycode;
    private String documentcurrencycode;
    private String reconciletranno;
    private String firstusergenericgrouptypecode;
    private String firstcode;
    private String secusergenericgrouptypecode;
    private String seccode;
    private String thirdusergenericgrouptypecode;
    private String thirdcode;
    private String frtusergenericgrouptypecode;
    private String frtcode;
    private String fifthusergenericgrouptypecode;
    private String fifthcode;
    private String sixthusergenericgrouptypecode;
    private String sixthcode;
    private String svnusergenericgrouptypecode;
    private String svncode;
    private String assetnocountercode;
    private String assetnocode;
    private String fdlreferencetext1;
    private String gldescription;
    private String bsplflag;
    private String faclassificationtype;
    private String legalname1;
    private String businessunitdescription;
    private String reffindoccode;

    public ViewfindocumentId getId() {
        return id;
    }

    public void setId(ViewfindocumentId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FINDOCSTATISTICALGROUPCODE", nullable = false, length = 6)
    public String getFindocstatisticalgroupcode() {
        return findocstatisticalgroupcode;
    }

    public void setFindocstatisticalgroupcode(String findocstatisticalgroupcode) {
        this.findocstatisticalgroupcode = findocstatisticalgroupcode;
    }

    @Basic
    @Column(name = "DOCUMENTDATE", nullable = false)
    public Date getDocumentdate() {
        return documentdate;
    }

    public void setDocumentdate(Date documentdate) {
        this.documentdate = documentdate;
    }

    @Basic
    @Column(name = "GLCODE", nullable = true, length = 20)
    public String getGlcode() {
        return glcode != null ? glcode.trim() : glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
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
    @Column(name = "DOCUMENTTYPEDESCRIPTION", nullable = true, length = 200)
    public String getDocumenttypedescription() {
        return documenttypedescription;
    }

    public void setDocumenttypedescription(String documenttypedescription) {
        this.documenttypedescription = documenttypedescription;
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
    @Column(name = "COMMERCIALINVOICECODE", nullable = true, length = 20)
    public String getCommercialinvoicecode() {
        return commercialinvoicecode;
    }

    public void setCommercialinvoicecode(String commercialinvoicecode) {
        this.commercialinvoicecode = commercialinvoicecode;
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
    @Column(name = "MRNDIVISIONCODE", nullable = true, length = 3)
    public String getMrndivisioncode() {
        return mrndivisioncode;
    }

    public void setMrndivisioncode(String mrndivisioncode) {
        this.mrndivisioncode = mrndivisioncode;
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
    @Column(name = "REFERENCETEXT1", nullable = true, length = 20)
    public String getReferencetext1() {
        return referencetext1;
    }

    public void setReferencetext1(String referencetext1) {
        this.referencetext1 = referencetext1;
    }

    @Basic
    @Column(name = "HEADERREMARK", nullable = true, length = 255)
    public String getHeaderremark() {
        return headerremark;
    }

    public void setHeaderremark(String headerremark) {
        this.headerremark = headerremark;
    }

    @Basic
    @Column(name = "HEADERREPORT", nullable = true, length = 255)
    public String getHeaderreport() {
        return headerreport;
    }

    public void setHeaderreport(String headerreport) {
        this.headerreport = headerreport;
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
    @Column(name = "PURCHASEINVOICEDIVISIONCODE", nullable = true, length = 3)
    public String getPurchaseinvoicedivisioncode() {
        return purchaseinvoicedivisioncode;
    }

    public void setPurchaseinvoicedivisioncode(String purchaseinvoicedivisioncode) {
        this.purchaseinvoicedivisioncode = purchaseinvoicedivisioncode;
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
    @Column(name = "DUEDATE", nullable = true)
    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(long absuniqueid) {
        this.absuniqueid = absuniqueid;
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
    @Column(name = "CHEQUENUMBER", nullable = true, length = 20)
    public String getChequenumber() {
        return chequenumber;
    }

    public void setChequenumber(String chequenumber) {
        this.chequenumber = chequenumber;
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
    @Column(name = "EMPLOYEECODE", nullable = true, length = 9)
    public String getEmployeecode() {
        return employeecode;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode;
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
    @Column(name = "SLCUSTOMERSUPPLIERTYPE", nullable = true, length = 1)
    public String getSlcustomersuppliertype() {
        return slcustomersuppliertype;
    }

    public void setSlcustomersuppliertype(String slcustomersuppliertype) {
        this.slcustomersuppliertype = slcustomersuppliertype;
    }

    @Basic
    @Column(name = "SLCUSTOMERSUPPLIERCODE", nullable = true, length = 8)
    public String getSlcustomersuppliercode() {
        return slcustomersuppliercode;
    }

    public void setSlcustomersuppliercode(String slcustomersuppliercode) {
        this.slcustomersuppliercode = slcustomersuppliercode;
    }

    @Basic
    @Column(name = "AMOUNTINCC", nullable = false, precision = 5)
    public BigDecimal getAmountincc() {
        return amountincc;
    }

    public void setAmountincc(BigDecimal amountincc) {
        this.amountincc = amountincc;
    }

    @Basic
    @Column(name = "AMOUNTINDC", nullable = false, precision = 5)
    public BigDecimal getAmountindc() {
        return amountindc;
    }

    public void setAmountindc(BigDecimal amountindc) {
        this.amountindc = amountindc;
    }

    @Basic
    @Column(name = "COMMENTS", nullable = true, length = 255)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
    @Column(name = "COMPANYCURRENCYCODE", nullable = true, length = 4)
    public String getCompanycurrencycode() {
        return companycurrencycode;
    }

    public void setCompanycurrencycode(String companycurrencycode) {
        this.companycurrencycode = companycurrencycode;
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
    @Column(name = "RECONCILETRANNO", nullable = true, length = 15)
    public String getReconciletranno() {
        return reconciletranno;
    }

    public void setReconciletranno(String reconciletranno) {
        this.reconciletranno = reconciletranno;
    }

    @Basic
    @Column(name = "FIRSTUSERGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getFirstusergenericgrouptypecode() {
        return firstusergenericgrouptypecode;
    }

    public void setFirstusergenericgrouptypecode(String firstusergenericgrouptypecode) {
        this.firstusergenericgrouptypecode = firstusergenericgrouptypecode;
    }

    @Basic
    @Column(name = "FIRSTCODE", nullable = true, length = 10)
    public String getFirstcode() {
        return firstcode;
    }

    public void setFirstcode(String firstcode) {
        this.firstcode = firstcode;
    }

    @Basic
    @Column(name = "SECUSERGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getSecusergenericgrouptypecode() {
        return secusergenericgrouptypecode;
    }

    public void setSecusergenericgrouptypecode(String secusergenericgrouptypecode) {
        this.secusergenericgrouptypecode = secusergenericgrouptypecode;
    }

    @Basic
    @Column(name = "SECCODE", nullable = true, length = 10)
    public String getSeccode() {
        return seccode;
    }

    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    @Basic
    @Column(name = "THIRDUSERGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getThirdusergenericgrouptypecode() {
        return thirdusergenericgrouptypecode;
    }

    public void setThirdusergenericgrouptypecode(String thirdusergenericgrouptypecode) {
        this.thirdusergenericgrouptypecode = thirdusergenericgrouptypecode;
    }

    @Basic
    @Column(name = "THIRDCODE", nullable = true, length = 10)
    public String getThirdcode() {
        return thirdcode;
    }

    public void setThirdcode(String thirdcode) {
        this.thirdcode = thirdcode;
    }

    @Basic
    @Column(name = "FRTUSERGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getFrtusergenericgrouptypecode() {
        return frtusergenericgrouptypecode;
    }

    public void setFrtusergenericgrouptypecode(String frtusergenericgrouptypecode) {
        this.frtusergenericgrouptypecode = frtusergenericgrouptypecode;
    }

    @Basic
    @Column(name = "FRTCODE", nullable = true, length = 10)
    public String getFrtcode() {
        return frtcode;
    }

    public void setFrtcode(String frtcode) {
        this.frtcode = frtcode;
    }

    @Basic
    @Column(name = "FIFTHUSERGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getFifthusergenericgrouptypecode() {
        return fifthusergenericgrouptypecode;
    }

    public void setFifthusergenericgrouptypecode(String fifthusergenericgrouptypecode) {
        this.fifthusergenericgrouptypecode = fifthusergenericgrouptypecode;
    }

    @Basic
    @Column(name = "FIFTHCODE", nullable = true, length = 10)
    public String getFifthcode() {
        return fifthcode;
    }

    public void setFifthcode(String fifthcode) {
        this.fifthcode = fifthcode;
    }

    @Basic
    @Column(name = "SIXTHUSERGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getSixthusergenericgrouptypecode() {
        return sixthusergenericgrouptypecode;
    }

    public void setSixthusergenericgrouptypecode(String sixthusergenericgrouptypecode) {
        this.sixthusergenericgrouptypecode = sixthusergenericgrouptypecode;
    }

    @Basic
    @Column(name = "SIXTHCODE", nullable = true, length = 10)
    public String getSixthcode() {
        return sixthcode;
    }

    public void setSixthcode(String sixthcode) {
        this.sixthcode = sixthcode;
    }

    @Basic
    @Column(name = "SVNUSERGENERICGROUPTYPECODE", nullable = true, length = 3)
    public String getSvnusergenericgrouptypecode() {
        return svnusergenericgrouptypecode;
    }

    public void setSvnusergenericgrouptypecode(String svnusergenericgrouptypecode) {
        this.svnusergenericgrouptypecode = svnusergenericgrouptypecode;
    }

    @Basic
    @Column(name = "SVNCODE", nullable = true, length = 10)
    public String getSvncode() {
        return svncode;
    }

    public void setSvncode(String svncode) {
        this.svncode = svncode;
    }

    @Basic
    @Column(name = "ASSETNOCOUNTERCODE", nullable = true, length = 8)
    public String getAssetnocountercode() {
        return assetnocountercode;
    }

    public void setAssetnocountercode(String assetnocountercode) {
        this.assetnocountercode = assetnocountercode;
    }

    @Basic
    @Column(name = "ASSETNOCODE", nullable = true, length = 15)
    public String getAssetnocode() {
        return assetnocode;
    }

    public void setAssetnocode(String assetnocode) {
        this.assetnocode = assetnocode;
    }

    @Basic
    @Column(name = "FDLREFERENCETEXT1", nullable = true, length = 50)
    public String getFdlreferencetext1() {
        return fdlreferencetext1;
    }

    public void setFdlreferencetext1(String fdlreferencetext1) {
        this.fdlreferencetext1 = fdlreferencetext1;
    }

    @Basic
    @Column(name = "GLDESCRIPTION", nullable = true, length = 200)
    public String getGldescription() {
        return gldescription;
    }

    public void setGldescription(String gldescription) {
        this.gldescription = gldescription;
    }

    @Basic
    @Column(name = "BSPLFLAG", nullable = true, length = 1)
    public String getBsplflag() {
        return bsplflag;
    }

    public void setBsplflag(String bsplflag) {
        this.bsplflag = bsplflag;
    }

    @Basic
    @Column(name = "FACLASSIFICATIONTYPE", nullable = true, length = 1)
    public String getFaclassificationtype() {
        return faclassificationtype;
    }

    public void setFaclassificationtype(String faclassificationtype) {
        this.faclassificationtype = faclassificationtype;
    }

    @Basic
    @Column(name = "LEGALNAME1", nullable = true, length = 270)
    public String getLegalname1() {
        return legalname1;
    }

    public void setLegalname1(String legalname1) {
        this.legalname1 = legalname1;
    }

    @Basic
    @Column(name = "BUSINESSUNITDESCRIPTION", nullable = false, length = 200)
    public String getBusinessunitdescription() {
        return businessunitdescription;
    }

    public void setBusinessunitdescription(String businessunitdescription) {
        this.businessunitdescription = businessunitdescription;
    }

    @Basic
    @Column(name = "REFFINDOCCODE", nullable = true, length = 15)
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
        Viewfindocument that = (Viewfindocument) o;
        return absuniqueid == that.absuniqueid && Objects.equals(id, that.id) && Objects.equals(findocstatisticalgroupcode, that.findocstatisticalgroupcode) && Objects.equals(documentdate, that.documentdate) && Objects.equals(glcode, that.glcode) && Objects.equals(documenttypecode, that.documenttypecode) && Objects.equals(documenttypedescription, that.documenttypedescription) && Objects.equals(postingdate, that.postingdate) && Objects.equals(commercialinvoicecode, that.commercialinvoicecode) && Objects.equals(commercialinvoicedivisioncode, that.commercialinvoicedivisioncode) && Objects.equals(plantinvoicedivisioncode, that.plantinvoicedivisioncode) && Objects.equals(plantinvoicecode, that.plantinvoicecode) && Objects.equals(poadvancepurordercountercode, that.poadvancepurordercountercode) && Objects.equals(poadvancepurchaseordercode, that.poadvancepurchaseordercode) && Objects.equals(poadvancelineno, that.poadvancelineno) && Objects.equals(mrndivisioncode, that.mrndivisioncode) && Objects.equals(mrncode, that.mrncode) && Objects.equals(referencetext1, that.referencetext1) && Objects.equals(headerremark, that.headerremark) && Objects.equals(headerreport, that.headerreport) && Objects.equals(mrnmrnprefixcode, that.mrnmrnprefixcode) && Objects.equals(purchaseinvoicedivisioncode, that.purchaseinvoicedivisioncode) && Objects.equals(purchaseinvoicecode, that.purchaseinvoicecode) && Objects.equals(purchaseinvoiceinvoicedate, that.purchaseinvoiceinvoicedate) && Objects.equals(duedate, that.duedate) && Objects.equals(chequedate, that.chequedate) && Objects.equals(chequenumber, that.chequenumber) && Objects.equals(directinvoicedivisioncode, that.directinvoicedivisioncode) && Objects.equals(directinvoicecountercode, that.directinvoicecountercode) && Objects.equals(directinvoicecode, that.directinvoicecode) && Objects.equals(sdcreditprovisionalcountercode, that.sdcreditprovisionalcountercode) && Objects.equals(sdcreditprovisionalcode, that.sdcreditprovisionalcode) && Objects.equals(employeecode, that.employeecode) && Objects.equals(expenseinvoicedivisioncode, that.expenseinvoicedivisioncode) && Objects.equals(expenseinvoicecode, that.expenseinvoicecode) && Objects.equals(expenseinvoiceinvoicedate, that.expenseinvoiceinvoicedate) && Objects.equals(slcustomersuppliertype, that.slcustomersuppliertype) && Objects.equals(slcustomersuppliercode, that.slcustomersuppliercode) && Objects.equals(amountincc, that.amountincc) && Objects.equals(amountindc, that.amountindc) && Objects.equals(comments, that.comments) && Objects.equals(profitcentercode, that.profitcentercode) && Objects.equals(costcentercode, that.costcentercode) && Objects.equals(companycurrencycode, that.companycurrencycode) && Objects.equals(documentcurrencycode, that.documentcurrencycode) && Objects.equals(reconciletranno, that.reconciletranno) && Objects.equals(firstusergenericgrouptypecode, that.firstusergenericgrouptypecode) && Objects.equals(firstcode, that.firstcode) && Objects.equals(secusergenericgrouptypecode, that.secusergenericgrouptypecode) && Objects.equals(seccode, that.seccode) && Objects.equals(thirdusergenericgrouptypecode, that.thirdusergenericgrouptypecode) && Objects.equals(thirdcode, that.thirdcode) && Objects.equals(frtusergenericgrouptypecode, that.frtusergenericgrouptypecode) && Objects.equals(frtcode, that.frtcode) && Objects.equals(fifthusergenericgrouptypecode, that.fifthusergenericgrouptypecode) && Objects.equals(fifthcode, that.fifthcode) && Objects.equals(sixthusergenericgrouptypecode, that.sixthusergenericgrouptypecode) && Objects.equals(sixthcode, that.sixthcode) && Objects.equals(svnusergenericgrouptypecode, that.svnusergenericgrouptypecode) && Objects.equals(svncode, that.svncode) && Objects.equals(assetnocountercode, that.assetnocountercode) && Objects.equals(assetnocode, that.assetnocode) && Objects.equals(fdlreferencetext1, that.fdlreferencetext1) && Objects.equals(gldescription, that.gldescription) && Objects.equals(bsplflag, that.bsplflag) && Objects.equals(faclassificationtype, that.faclassificationtype) && Objects.equals(legalname1, that.legalname1) && Objects.equals(businessunitdescription, that.businessunitdescription) && Objects.equals(reffindoccode, that.reffindoccode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, findocstatisticalgroupcode, documentdate, glcode, documenttypecode, documenttypedescription, postingdate, commercialinvoicecode, commercialinvoicedivisioncode, plantinvoicedivisioncode, plantinvoicecode, poadvancepurordercountercode, poadvancepurchaseordercode, poadvancelineno, mrndivisioncode, mrncode, referencetext1, headerremark, headerreport, mrnmrnprefixcode, purchaseinvoicedivisioncode, purchaseinvoicecode, purchaseinvoiceinvoicedate, duedate, absuniqueid, chequedate, chequenumber, directinvoicedivisioncode, directinvoicecountercode, directinvoicecode, sdcreditprovisionalcountercode, sdcreditprovisionalcode, employeecode, expenseinvoicedivisioncode, expenseinvoicecode, expenseinvoiceinvoicedate, slcustomersuppliertype, slcustomersuppliercode, amountincc, amountindc, comments, profitcentercode, costcentercode, companycurrencycode, documentcurrencycode, reconciletranno, firstusergenericgrouptypecode, firstcode, secusergenericgrouptypecode, seccode, thirdusergenericgrouptypecode, thirdcode, frtusergenericgrouptypecode, frtcode, fifthusergenericgrouptypecode, fifthcode, sixthusergenericgrouptypecode, sixthcode, svnusergenericgrouptypecode, svncode, assetnocountercode, assetnocode, fdlreferencetext1, gldescription, bsplflag, faclassificationtype, legalname1, businessunitdescription, reffindoccode);
    }
}
