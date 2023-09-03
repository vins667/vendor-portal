package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CutPlanIssueStitch.
 */
@Entity
@Table(name = "cut_plan_issue_stitch")
public class CutPlanIssueStitch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="cutPlanIssueStitchSeq", sequenceName="cut_plan_issue_stitch_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="cutPlanIssueStitchSeq")
    private Long id;

    @Size(max = 1)
    @Column(name = "transaction_type", length = 1)
    private String transactionType;

    @NotNull
    @Size(max = 20)
    @Column(name = "plant_code", length = 20, nullable = false)
    private String plantCode;

    @Size(max = 100)
    @Column(name = "plant_description", length = 100)
    private String plantDescription;

    @NotNull
    @Size(max = 20)
    @Column(name = "destination_plant_code", length = 20, nullable = false)
    private String destinationPlantCode;

    @Size(max = 100)
    @Column(name = "destination_plant_description", length = 100)
    private String destinationPlantDescription;

    @NotNull
    @Size(max = 50)
    @Column(name = "projectcode", length = 50, nullable = false)
    private String projectcode;

    @NotNull
    @Size(max = 20)
    @Column(name = "style", length = 20, nullable = false)
    private String style;

    @NotNull
    @Size(max = 20)
    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Size(max = 100)
    @Column(name = "colordescription", length = 100)
    private String colordescription;

    @Size(max = 20)
    @Column(name = "destination", length = 20, nullable = false)
    private String destination;

    @Size(max = 100)
    @Column(name = "destination_desc", length = 100)
    private String destinationDesc;

    @Size(max = 3)
    @Column(name = "termsofdeliverycode", length = 3)
    private String termsofdeliverycode;

    @Size(max = 200)
    @Column(name = "termsofdeliverydescription", length = 200)
    private String termsofdeliverydescription;

    @Size(max = 2)
    @Column(name = "termsofshippingcode", length = 2)
    private String termsofshippingcode;

    @Size(max = 200)
    @Column(name = "termsofshippingdescription", length = 200)
    private String termsofshippingdescription;

    @Size(max = 20)
    @Column(name = "eway", length = 20)
    private String eway;

    @Size(max = 20)
    @Column(name = "createdby", length = 20)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 20)
    @Column(name = "lastupdatedby", length = 20)
    private String lastupdatedby;

    @Column(name = "lastupdateddate")
    private Instant lastupdateddate;

    @Size(max = 20)
    @Column(name = "posted_by", length = 20)
    private String postedBy;

    @Column(name = "posted_date")
    private Instant postedDate;

    @Size(max = 20)
    @Column(name = "reciept_posted_by", length = 20)
    private String recieptPostedBy;

    @Column(name = "reciept_posted_date")
    private Instant recieptPostedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public CutPlanIssueStitch transactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public CutPlanIssueStitch plantCode(String plantCode) {
        this.plantCode = plantCode;
        return this;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public CutPlanIssueStitch plantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
        return this;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getDestinationPlantCode() {
        return destinationPlantCode;
    }

    public CutPlanIssueStitch destinationPlantCode(String destinationPlantCode) {
        this.destinationPlantCode = destinationPlantCode;
        return this;
    }

    public void setDestinationPlantCode(String destinationPlantCode) {
        this.destinationPlantCode = destinationPlantCode;
    }

    public String getDestinationPlantDescription() {
        return destinationPlantDescription;
    }

    public CutPlanIssueStitch destinationPlantDescription(String destinationPlantDescription) {
        this.destinationPlantDescription = destinationPlantDescription;
        return this;
    }

    public void setDestinationPlantDescription(String destinationPlantDescription) {
        this.destinationPlantDescription = destinationPlantDescription;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public CutPlanIssueStitch projectcode(String projectcode) {
        this.projectcode = projectcode;
        return this;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getStyle() {
        return style;
    }

    public CutPlanIssueStitch style(String style) {
        this.style = style;
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public CutPlanIssueStitch color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColordescription() {
        return colordescription;
    }

    public CutPlanIssueStitch colordescription(String colordescription) {
        this.colordescription = colordescription;
        return this;
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

    public CutPlanIssueStitch termsofdeliverycode(String termsofdeliverycode) {
        this.termsofdeliverycode = termsofdeliverycode;
        return this;
    }

    public void setTermsofdeliverycode(String termsofdeliverycode) {
        this.termsofdeliverycode = termsofdeliverycode;
    }

    public String getTermsofdeliverydescription() {
        return termsofdeliverydescription;
    }

    public CutPlanIssueStitch termsofdeliverydescription(String termsofdeliverydescription) {
        this.termsofdeliverydescription = termsofdeliverydescription;
        return this;
    }

    public void setTermsofdeliverydescription(String termsofdeliverydescription) {
        this.termsofdeliverydescription = termsofdeliverydescription;
    }

    public String getTermsofshippingcode() {
        return termsofshippingcode;
    }

    public CutPlanIssueStitch termsofshippingcode(String termsofshippingcode) {
        this.termsofshippingcode = termsofshippingcode;
        return this;
    }

    public void setTermsofshippingcode(String termsofshippingcode) {
        this.termsofshippingcode = termsofshippingcode;
    }

    public String getTermsofshippingdescription() {
        return termsofshippingdescription;
    }

    public CutPlanIssueStitch termsofshippingdescription(String termsofshippingdescription) {
        this.termsofshippingdescription = termsofshippingdescription;
        return this;
    }

    public void setTermsofshippingdescription(String termsofshippingdescription) {
        this.termsofshippingdescription = termsofshippingdescription;
    }

    public String getEway() {
        return eway;
    }

    public CutPlanIssueStitch eway(String eway) {
        this.eway = eway;
        return this;
    }

    public void setEway(String eway) {
        this.eway = eway;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CutPlanIssueStitch createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CutPlanIssueStitch createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public CutPlanIssueStitch lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public CutPlanIssueStitch lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutPlanIssueStitch)) {
            return false;
        }
        return id != null && id.equals(((CutPlanIssueStitch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutPlanIssueStitch{" +
            "id=" + getId() +
            ", transactionType='" + getTransactionType() + "'" +
            ", plantCode='" + getPlantCode() + "'" +
            ", plantDescription='" + getPlantDescription() + "'" +
            ", destinationPlantCode='" + getDestinationPlantCode() + "'" +
            ", destinationPlantDescription='" + getDestinationPlantDescription() + "'" +
            ", projectcode='" + getProjectcode() + "'" +
            ", style='" + getStyle() + "'" +
            ", color='" + getColor() + "'" +
            ", colordescription='" + getColordescription() + "'" +
            ", termsofdeliverycode='" + getTermsofdeliverycode() + "'" +
            ", termsofdeliverydescription='" + getTermsofdeliverydescription() + "'" +
            ", termsofshippingcode='" + getTermsofshippingcode() + "'" +
            ", termsofshippingdescription='" + getTermsofshippingdescription() + "'" +
            ", eway='" + getEway() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", lastupdatedby='" + getLastupdatedby() + "'" +
            ", lastupdateddate='" + getLastupdateddate() + "'" +
            "}";
    }
}
