package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class StitchLineIssueBean implements Serializable {

    private Long id;
    private String porductionCounterCode;
    private String productionCode;
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
    private String line;
    private String lineDesc;
    private String createdby;
    private Date createddate;
    private String lastupdatedby;
    private Instant lastupdateddate;
    private String postedBy;
    private Instant postedDate;
    private Instant issuedate;
    private List<StitchLineIssueDetailsBean> stitchLineIssueDetails;

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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLineDesc() {
        return lineDesc;
    }

    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
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

    public String getPorductionCounterCode() {
        return porductionCounterCode;
    }

    public void setPorductionCounterCode(String porductionCounterCode) {
        this.porductionCounterCode = porductionCounterCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public Instant getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Instant issuedate) {
        this.issuedate = issuedate;
    }

    public List<StitchLineIssueDetailsBean> getStitchLineIssueDetails() {
        return stitchLineIssueDetails;
    }

    public void setStitchLineIssueDetails(List<StitchLineIssueDetailsBean> stitchLineIssueDetails) {
        this.stitchLineIssueDetails = stitchLineIssueDetails;
    }
}
