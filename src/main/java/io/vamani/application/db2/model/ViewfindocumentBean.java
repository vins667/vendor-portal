package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.ViewfindocumentId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class ViewfindocumentBean implements Serializable {
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
    private String narration;

    public ViewfindocumentId getId() {
        return id;
    }

    public void setId(ViewfindocumentId id) {
        this.id = id;
    }

    public String getFindocstatisticalgroupcode() {
        return findocstatisticalgroupcode;
    }

    public void setFindocstatisticalgroupcode(String findocstatisticalgroupcode) {
        this.findocstatisticalgroupcode = findocstatisticalgroupcode;
    }

    public Date getDocumentdate() {
        return documentdate;
    }

    public void setDocumentdate(Date documentdate) {
        this.documentdate = documentdate;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getDocumenttypecode() {
        return documenttypecode;
    }

    public void setDocumenttypecode(String documenttypecode) {
        this.documenttypecode = documenttypecode;
    }

    public String getDocumenttypedescription() {
        return documenttypedescription;
    }

    public void setDocumenttypedescription(String documenttypedescription) {
        this.documenttypedescription = documenttypedescription;
    }

    public Date getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(Date postingdate) {
        this.postingdate = postingdate;
    }

    public String getCommercialinvoicecode() {
        return commercialinvoicecode;
    }

    public void setCommercialinvoicecode(String commercialinvoicecode) {
        this.commercialinvoicecode = commercialinvoicecode;
    }

    public String getCommercialinvoicedivisioncode() {
        return commercialinvoicedivisioncode;
    }

    public void setCommercialinvoicedivisioncode(String commercialinvoicedivisioncode) {
        this.commercialinvoicedivisioncode = commercialinvoicedivisioncode;
    }

    public String getPlantinvoicedivisioncode() {
        return plantinvoicedivisioncode;
    }

    public void setPlantinvoicedivisioncode(String plantinvoicedivisioncode) {
        this.plantinvoicedivisioncode = plantinvoicedivisioncode;
    }

    public String getPlantinvoicecode() {
        return plantinvoicecode;
    }

    public void setPlantinvoicecode(String plantinvoicecode) {
        this.plantinvoicecode = plantinvoicecode;
    }

    public String getPoadvancepurordercountercode() {
        return poadvancepurordercountercode;
    }

    public void setPoadvancepurordercountercode(String poadvancepurordercountercode) {
        this.poadvancepurordercountercode = poadvancepurordercountercode;
    }

    public String getPoadvancepurchaseordercode() {
        return poadvancepurchaseordercode;
    }

    public void setPoadvancepurchaseordercode(String poadvancepurchaseordercode) {
        this.poadvancepurchaseordercode = poadvancepurchaseordercode;
    }

    public Integer getPoadvancelineno() {
        return poadvancelineno;
    }

    public void setPoadvancelineno(Integer poadvancelineno) {
        this.poadvancelineno = poadvancelineno;
    }

    public String getMrndivisioncode() {
        return mrndivisioncode;
    }

    public void setMrndivisioncode(String mrndivisioncode) {
        this.mrndivisioncode = mrndivisioncode;
    }

    public String getMrncode() {
        return mrncode;
    }

    public void setMrncode(String mrncode) {
        this.mrncode = mrncode;
    }

    public String getReferencetext1() {
        return referencetext1;
    }

    public void setReferencetext1(String referencetext1) {
        this.referencetext1 = referencetext1;
    }

    public String getHeaderremark() {
        return headerremark;
    }

    public void setHeaderremark(String headerremark) {
        this.headerremark = headerremark;
    }

    public String getHeaderreport() {
        return headerreport;
    }

    public void setHeaderreport(String headerreport) {
        this.headerreport = headerreport;
    }

    public String getMrnmrnprefixcode() {
        return mrnmrnprefixcode;
    }

    public void setMrnmrnprefixcode(String mrnmrnprefixcode) {
        this.mrnmrnprefixcode = mrnmrnprefixcode;
    }

    public String getPurchaseinvoicedivisioncode() {
        return purchaseinvoicedivisioncode;
    }

    public void setPurchaseinvoicedivisioncode(String purchaseinvoicedivisioncode) {
        this.purchaseinvoicedivisioncode = purchaseinvoicedivisioncode;
    }

    public String getPurchaseinvoicecode() {
        return purchaseinvoicecode;
    }

    public void setPurchaseinvoicecode(String purchaseinvoicecode) {
        this.purchaseinvoicecode = purchaseinvoicecode;
    }

    public Date getPurchaseinvoiceinvoicedate() {
        return purchaseinvoiceinvoicedate;
    }

    public void setPurchaseinvoiceinvoicedate(Date purchaseinvoiceinvoicedate) {
        this.purchaseinvoiceinvoicedate = purchaseinvoiceinvoicedate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    public Date getChequedate() {
        return chequedate;
    }

    public void setChequedate(Date chequedate) {
        this.chequedate = chequedate;
    }

    public String getChequenumber() {
        return chequenumber;
    }

    public void setChequenumber(String chequenumber) {
        this.chequenumber = chequenumber;
    }

    public String getDirectinvoicedivisioncode() {
        return directinvoicedivisioncode;
    }

    public void setDirectinvoicedivisioncode(String directinvoicedivisioncode) {
        this.directinvoicedivisioncode = directinvoicedivisioncode;
    }

    public String getDirectinvoicecountercode() {
        return directinvoicecountercode;
    }

    public void setDirectinvoicecountercode(String directinvoicecountercode) {
        this.directinvoicecountercode = directinvoicecountercode;
    }

    public String getDirectinvoicecode() {
        return directinvoicecode;
    }

    public void setDirectinvoicecode(String directinvoicecode) {
        this.directinvoicecode = directinvoicecode;
    }

    public String getSdcreditprovisionalcountercode() {
        return sdcreditprovisionalcountercode;
    }

    public void setSdcreditprovisionalcountercode(String sdcreditprovisionalcountercode) {
        this.sdcreditprovisionalcountercode = sdcreditprovisionalcountercode;
    }

    public String getSdcreditprovisionalcode() {
        return sdcreditprovisionalcode;
    }

    public void setSdcreditprovisionalcode(String sdcreditprovisionalcode) {
        this.sdcreditprovisionalcode = sdcreditprovisionalcode;
    }

    public String getEmployeecode() {
        return employeecode;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode;
    }

    public String getExpenseinvoicedivisioncode() {
        return expenseinvoicedivisioncode;
    }

    public void setExpenseinvoicedivisioncode(String expenseinvoicedivisioncode) {
        this.expenseinvoicedivisioncode = expenseinvoicedivisioncode;
    }

    public String getExpenseinvoicecode() {
        return expenseinvoicecode;
    }

    public void setExpenseinvoicecode(String expenseinvoicecode) {
        this.expenseinvoicecode = expenseinvoicecode;
    }

    public Date getExpenseinvoiceinvoicedate() {
        return expenseinvoiceinvoicedate;
    }

    public void setExpenseinvoiceinvoicedate(Date expenseinvoiceinvoicedate) {
        this.expenseinvoiceinvoicedate = expenseinvoiceinvoicedate;
    }

    public String getSlcustomersuppliertype() {
        return slcustomersuppliertype;
    }

    public void setSlcustomersuppliertype(String slcustomersuppliertype) {
        this.slcustomersuppliertype = slcustomersuppliertype;
    }

    public String getSlcustomersuppliercode() {
        return slcustomersuppliercode;
    }

    public void setSlcustomersuppliercode(String slcustomersuppliercode) {
        this.slcustomersuppliercode = slcustomersuppliercode;
    }

    public BigDecimal getAmountincc() {
        return amountincc;
    }

    public void setAmountincc(BigDecimal amountincc) {
        this.amountincc = amountincc;
    }

    public BigDecimal getAmountindc() {
        return amountindc;
    }

    public void setAmountindc(BigDecimal amountindc) {
        this.amountindc = amountindc;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProfitcentercode() {
        return profitcentercode;
    }

    public void setProfitcentercode(String profitcentercode) {
        this.profitcentercode = profitcentercode;
    }

    public String getCostcentercode() {
        return costcentercode;
    }

    public void setCostcentercode(String costcentercode) {
        this.costcentercode = costcentercode;
    }

    public String getCompanycurrencycode() {
        return companycurrencycode;
    }

    public void setCompanycurrencycode(String companycurrencycode) {
        this.companycurrencycode = companycurrencycode;
    }

    public String getDocumentcurrencycode() {
        return documentcurrencycode;
    }

    public void setDocumentcurrencycode(String documentcurrencycode) {
        this.documentcurrencycode = documentcurrencycode;
    }

    public String getReconciletranno() {
        return reconciletranno;
    }

    public void setReconciletranno(String reconciletranno) {
        this.reconciletranno = reconciletranno;
    }

    public String getFirstusergenericgrouptypecode() {
        return firstusergenericgrouptypecode;
    }

    public void setFirstusergenericgrouptypecode(String firstusergenericgrouptypecode) {
        this.firstusergenericgrouptypecode = firstusergenericgrouptypecode;
    }

    public String getFirstcode() {
        return firstcode;
    }

    public void setFirstcode(String firstcode) {
        this.firstcode = firstcode;
    }

    public String getSecusergenericgrouptypecode() {
        return secusergenericgrouptypecode;
    }

    public void setSecusergenericgrouptypecode(String secusergenericgrouptypecode) {
        this.secusergenericgrouptypecode = secusergenericgrouptypecode;
    }

    public String getSeccode() {
        return seccode;
    }

    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    public String getThirdusergenericgrouptypecode() {
        return thirdusergenericgrouptypecode;
    }

    public void setThirdusergenericgrouptypecode(String thirdusergenericgrouptypecode) {
        this.thirdusergenericgrouptypecode = thirdusergenericgrouptypecode;
    }

    public String getThirdcode() {
        return thirdcode;
    }

    public void setThirdcode(String thirdcode) {
        this.thirdcode = thirdcode;
    }

    public String getFrtusergenericgrouptypecode() {
        return frtusergenericgrouptypecode;
    }

    public void setFrtusergenericgrouptypecode(String frtusergenericgrouptypecode) {
        this.frtusergenericgrouptypecode = frtusergenericgrouptypecode;
    }

    public String getFrtcode() {
        return frtcode;
    }

    public void setFrtcode(String frtcode) {
        this.frtcode = frtcode;
    }

    public String getFifthusergenericgrouptypecode() {
        return fifthusergenericgrouptypecode;
    }

    public void setFifthusergenericgrouptypecode(String fifthusergenericgrouptypecode) {
        this.fifthusergenericgrouptypecode = fifthusergenericgrouptypecode;
    }

    public String getFifthcode() {
        return fifthcode;
    }

    public void setFifthcode(String fifthcode) {
        this.fifthcode = fifthcode;
    }

    public String getSixthusergenericgrouptypecode() {
        return sixthusergenericgrouptypecode;
    }

    public void setSixthusergenericgrouptypecode(String sixthusergenericgrouptypecode) {
        this.sixthusergenericgrouptypecode = sixthusergenericgrouptypecode;
    }

    public String getSixthcode() {
        return sixthcode;
    }

    public void setSixthcode(String sixthcode) {
        this.sixthcode = sixthcode;
    }

    public String getSvnusergenericgrouptypecode() {
        return svnusergenericgrouptypecode;
    }

    public void setSvnusergenericgrouptypecode(String svnusergenericgrouptypecode) {
        this.svnusergenericgrouptypecode = svnusergenericgrouptypecode;
    }

    public String getSvncode() {
        return svncode;
    }

    public void setSvncode(String svncode) {
        this.svncode = svncode;
    }

    public String getAssetnocountercode() {
        return assetnocountercode;
    }

    public void setAssetnocountercode(String assetnocountercode) {
        this.assetnocountercode = assetnocountercode;
    }

    public String getAssetnocode() {
        return assetnocode;
    }

    public void setAssetnocode(String assetnocode) {
        this.assetnocode = assetnocode;
    }

    public String getFdlreferencetext1() {
        return fdlreferencetext1;
    }

    public void setFdlreferencetext1(String fdlreferencetext1) {
        this.fdlreferencetext1 = fdlreferencetext1;
    }

    public String getGldescription() {
        return gldescription;
    }

    public void setGldescription(String gldescription) {
        this.gldescription = gldescription;
    }

    public String getBsplflag() {
        return bsplflag;
    }

    public void setBsplflag(String bsplflag) {
        this.bsplflag = bsplflag;
    }

    public String getFaclassificationtype() {
        return faclassificationtype;
    }

    public void setFaclassificationtype(String faclassificationtype) {
        this.faclassificationtype = faclassificationtype;
    }

    public String getLegalname1() {
        return legalname1;
    }

    public void setLegalname1(String legalname1) {
        this.legalname1 = legalname1;
    }

    public String getBusinessunitdescription() {
        return businessunitdescription;
    }

    public void setBusinessunitdescription(String businessunitdescription) {
        this.businessunitdescription = businessunitdescription;
    }

    public String getReffindoccode() {
        return reffindoccode;
    }

    public void setReffindoccode(String reffindoccode) {
        this.reffindoccode = reffindoccode;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }
}
