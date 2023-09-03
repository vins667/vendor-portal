package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * A CutPlanIssueStitch.
 */
@Entity
@Table(name = "stitch_line_issue")
public class StitchLineIssue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="stitchLineIssueSeq", sequenceName="stitch_line_issue_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="stitchLineIssueSeq")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "porduction_counter_code", length = 10, nullable = false)
    private String porductionCounterCode;


    @NotNull
    @Size(max = 20)
    @Column(name = "production_code", length = 20, nullable = false)
    private String productionCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "plant_code", length = 20, nullable = false)
    private String plantCode;

    @Size(max = 100)
    @Column(name = "plant_description", length = 100)
    private String plantDescription;

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

    @Size(max = 20)
    @Column(name = "line", length = 20, nullable = false)
    private String line;

    @Size(max = 100)
    @Column(name = "line_desc", length = 100)
    private String lineDesc;

    @Column(name = "issuedate")
    private Instant issuedate;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public StitchLineIssue plantCode(String plantCode) {
        this.plantCode = plantCode;
        return this;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public StitchLineIssue plantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
        return this;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public StitchLineIssue projectcode(String projectcode) {
        this.projectcode = projectcode;
        return this;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getStyle() {
        return style;
    }

    public StitchLineIssue style(String style) {
        this.style = style;
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public StitchLineIssue color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColordescription() {
        return colordescription;
    }

    public StitchLineIssue colordescription(String colordescription) {
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

    public StitchLineIssue createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public StitchLineIssue createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public StitchLineIssue lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public StitchLineIssue lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

}
