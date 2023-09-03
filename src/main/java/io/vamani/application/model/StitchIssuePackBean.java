package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class StitchIssuePackBean implements Serializable {
    private Long id;

    private String transactionType;

    private String plantCode;

    private String plantDescription;

    private String plantAddress;

    private String destinationPlantCode;

    private String destinationPlantDescription;

    private String destinationPlantAddress;

    private String projectcode;

    private String style;

    private String color;

    private String colordescription;

    private String destination;

    private String destinationDesc;

    private String termsofdeliverycode;

    private String termsofdeliverydescription;

    private String termsofshippingcode;

    private String termsofshippingdescription;

    private String eway;

    private String createdby;

    private Instant createddate;

    private Date createddatenew;

    private String lastupdatedby;

    private Instant lastupdateddate;

    private String postedBy;

    private Instant postedDate;

    private String recieptPostedBy;

    private Instant recieptPostedDate;

    private List<StitchIssuePackDetailsBean> stitchIssuePackDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getDestinationPlantCode() {
        return destinationPlantCode;
    }

    public void setDestinationPlantCode(String destinationPlantCode) {
        this.destinationPlantCode = destinationPlantCode;
    }

    public String getDestinationPlantDescription() {
        return destinationPlantDescription;
    }

    public void setDestinationPlantDescription(String destinationPlantDescription) {
        this.destinationPlantDescription = destinationPlantDescription;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColordescription() {
        return colordescription;
    }

    public void setColordescription(String colordescription) {
        this.colordescription = colordescription;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public String getTermsofdeliverycode() {
        return termsofdeliverycode;
    }

    public void setTermsofdeliverycode(String termsofdeliverycode) {
        this.termsofdeliverycode = termsofdeliverycode;
    }

    public String getTermsofdeliverydescription() {
        return termsofdeliverydescription;
    }

    public void setTermsofdeliverydescription(String termsofdeliverydescription) {
        this.termsofdeliverydescription = termsofdeliverydescription;
    }

    public String getTermsofshippingcode() {
        return termsofshippingcode;
    }

    public void setTermsofshippingcode(String termsofshippingcode) {
        this.termsofshippingcode = termsofshippingcode;
    }

    public String getTermsofshippingdescription() {
        return termsofshippingdescription;
    }

    public void setTermsofshippingdescription(String termsofshippingdescription) {
        this.termsofshippingdescription = termsofshippingdescription;
    }

    public String getEway() {
        return eway;
    }

    public void setEway(String eway) {
        this.eway = eway;
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

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Instant getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Instant postedDate) {
        this.postedDate = postedDate;
    }

    public String getRecieptPostedBy() {
        return recieptPostedBy;
    }

    public void setRecieptPostedBy(String recieptPostedBy) {
        this.recieptPostedBy = recieptPostedBy;
    }

    public Instant getRecieptPostedDate() {
        return recieptPostedDate;
    }

    public void setRecieptPostedDate(Instant recieptPostedDate) {
        this.recieptPostedDate = recieptPostedDate;
    }

    public Date getCreateddatenew() { return createddatenew; }

    public void setCreateddatenew(Date createddatenew) { this.createddatenew = createddatenew; }

    public List<StitchIssuePackDetailsBean> getStitchIssuePackDetails() {
        return stitchIssuePackDetails;
    }

    public void setStitchIssuePackDetails(List<StitchIssuePackDetailsBean> stitchIssuePackDetails) {
        this.stitchIssuePackDetails = stitchIssuePackDetails;
    }

    public String getPlantAddress() {
        return plantAddress;
    }

    public void setPlantAddress(String plantAddress) {
        this.plantAddress = plantAddress;
    }

    public String getDestinationPlantAddress() {
        return destinationPlantAddress;
    }

    public void setDestinationPlantAddress(String destinationPlantAddress) {
        this.destinationPlantAddress = destinationPlantAddress;
    }
}
