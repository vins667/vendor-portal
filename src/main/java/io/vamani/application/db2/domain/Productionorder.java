package io.vamani.application.db2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "productionorder")
public class Productionorder {
    @EmbeddedId
    private ProductionorderId id;
    @JsonIgnore
    private Long absversionnumber;
    @JsonIgnore
    private String divisioncode;
    @JsonIgnore
    private String alloweddivisions;
    @JsonIgnore
    private String proordercountercompanycode;
    private String productionordercountercode;
    @JsonIgnore
    private String demandlist;
    @JsonIgnore
    private Short entry;
    @JsonIgnore
    private Short issue;
    private Date orderdate;
    @JsonIgnore
    private String status;
    @JsonIgnore
    private String progressstatus;
    @JsonIgnore
    private Short automaticcreationdemand;
    @JsonIgnore
    private Short handledyelotuom;
    @JsonIgnore
    private BigDecimal stdproductionbatch;
    @JsonIgnore
    private String stdproductionbatchuomcode;
    private BigDecimal totalprimaryquantity;
    private String primaryunitofmeasurecode;
    @JsonIgnore
    private BigDecimal totalsecondaryquantity;
    @JsonIgnore
    private String secondaryunitofmeasurecode;
    @JsonIgnore
    private BigDecimal totalpackagingquantity;
    @JsonIgnore
    private String packagingunitofmeasurecode;
    @JsonIgnore
    private Timestamp creationdatetime;
    @JsonIgnore
    private String creationuser;
    @JsonIgnore
    private Timestamp lastupdatedatetime;
    @JsonIgnore
    private String lastupdateuser;
    @JsonIgnore
    private Long absuniqueid;

    public ProductionorderId getId() {
        return id;
    }

    public void setId(ProductionorderId id) {
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
    @Column(name = "PROORDERCOUNTERCOMPANYCODE", nullable = true, length = 3)
    public String getProordercountercompanycode() {
        return proordercountercompanycode;
    }

    public void setProordercountercompanycode(String proordercountercompanycode) {
        this.proordercountercompanycode = proordercountercompanycode;
    }

    @Basic
    @Column(name = "PRODUCTIONORDERCOUNTERCODE", nullable = true, length = 8)
    public String getProductionordercountercode() {
        return productionordercountercode;
    }

    public void setProductionordercountercode(String productionordercountercode) {
        this.productionordercountercode = productionordercountercode;
    }

    @Basic
    @Column(name = "DEMANDLIST", nullable = true)
    public String getDemandlist() {
        return demandlist;
    }

    public void setDemandlist(String demandlist) {
        this.demandlist = demandlist;
    }

    @Basic
    @Column(name = "ENTRY", nullable = false)
    public Short getEntry() {
        return entry;
    }

    public void setEntry(Short entry) {
        this.entry = entry;
    }

    @Basic
    @Column(name = "ISSUE", nullable = false)
    public Short getIssue() {
        return issue;
    }

    public void setIssue(Short issue) {
        this.issue = issue;
    }

    @Basic
    @Column(name = "ORDERDATE", nullable = true)
    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "AUTOMATICCREATIONDEMAND", nullable = false)
    public Short getAutomaticcreationdemand() {
        return automaticcreationdemand;
    }

    public void setAutomaticcreationdemand(Short automaticcreationdemand) {
        this.automaticcreationdemand = automaticcreationdemand;
    }

    @Basic
    @Column(name = "HANDLEDYELOTUOM", nullable = false)
    public Short getHandledyelotuom() {
        return handledyelotuom;
    }

    public void setHandledyelotuom(Short handledyelotuom) {
        this.handledyelotuom = handledyelotuom;
    }

    @Basic
    @Column(name = "STDPRODUCTIONBATCH", nullable = true, precision = 5)
    public BigDecimal getStdproductionbatch() {
        return stdproductionbatch;
    }

    public void setStdproductionbatch(BigDecimal stdproductionbatch) {
        this.stdproductionbatch = stdproductionbatch;
    }

    @Basic
    @Column(name = "STDPRODUCTIONBATCHUOMCODE", nullable = true, length = 3)
    public String getStdproductionbatchuomcode() {
        return stdproductionbatchuomcode;
    }

    public void setStdproductionbatchuomcode(String stdproductionbatchuomcode) {
        this.stdproductionbatchuomcode = stdproductionbatchuomcode;
    }

    @Basic
    @Column(name = "TOTALPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getTotalprimaryquantity() {
        return totalprimaryquantity;
    }

    public void setTotalprimaryquantity(BigDecimal totalprimaryquantity) {
        this.totalprimaryquantity = totalprimaryquantity;
    }

    @Basic
    @Column(name = "PRIMARYUNITOFMEASURECODE", nullable = true, length = 3)
    public String getPrimaryunitofmeasurecode() {
        return primaryunitofmeasurecode;
    }

    public void setPrimaryunitofmeasurecode(String primaryunitofmeasurecode) {
        this.primaryunitofmeasurecode = primaryunitofmeasurecode;
    }

    @Basic
    @Column(name = "TOTALSECONDARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getTotalsecondaryquantity() {
        return totalsecondaryquantity;
    }

    public void setTotalsecondaryquantity(BigDecimal totalsecondaryquantity) {
        this.totalsecondaryquantity = totalsecondaryquantity;
    }

    @Basic
    @Column(name = "SECONDARYUNITOFMEASURECODE", nullable = true, length = 3)
    public String getSecondaryunitofmeasurecode() {
        return secondaryunitofmeasurecode;
    }

    public void setSecondaryunitofmeasurecode(String secondaryunitofmeasurecode) {
        this.secondaryunitofmeasurecode = secondaryunitofmeasurecode;
    }

    @Basic
    @Column(name = "TOTALPACKAGINGQUANTITY", nullable = true, precision = 5)
    public BigDecimal getTotalpackagingquantity() {
        return totalpackagingquantity;
    }

    public void setTotalpackagingquantity(BigDecimal totalpackagingquantity) {
        this.totalpackagingquantity = totalpackagingquantity;
    }

    @Basic
    @Column(name = "PACKAGINGUNITOFMEASURECODE", nullable = true, length = 3)
    public String getPackagingunitofmeasurecode() {
        return packagingunitofmeasurecode;
    }

    public void setPackagingunitofmeasurecode(String packagingunitofmeasurecode) {
        this.packagingunitofmeasurecode = packagingunitofmeasurecode;
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

    public Productionorder() {
    }

    public Productionorder(ProductionorderId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productionorder that = (Productionorder) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(absversionnumber, that.absversionnumber) &&
            Objects.equals(divisioncode, that.divisioncode) &&
            Objects.equals(alloweddivisions, that.alloweddivisions) &&
            Objects.equals(proordercountercompanycode, that.proordercountercompanycode) &&
            Objects.equals(productionordercountercode, that.productionordercountercode) &&
            Objects.equals(demandlist, that.demandlist) &&
            Objects.equals(entry, that.entry) &&
            Objects.equals(issue, that.issue) &&
            Objects.equals(orderdate, that.orderdate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(progressstatus, that.progressstatus) &&
            Objects.equals(automaticcreationdemand, that.automaticcreationdemand) &&
            Objects.equals(handledyelotuom, that.handledyelotuom) &&
            Objects.equals(stdproductionbatch, that.stdproductionbatch) &&
            Objects.equals(stdproductionbatchuomcode, that.stdproductionbatchuomcode) &&
            Objects.equals(totalprimaryquantity, that.totalprimaryquantity) &&
            Objects.equals(primaryunitofmeasurecode, that.primaryunitofmeasurecode) &&
            Objects.equals(totalsecondaryquantity, that.totalsecondaryquantity) &&
            Objects.equals(secondaryunitofmeasurecode, that.secondaryunitofmeasurecode) &&
            Objects.equals(totalpackagingquantity, that.totalpackagingquantity) &&
            Objects.equals(packagingunitofmeasurecode, that.packagingunitofmeasurecode) &&
            Objects.equals(creationdatetime, that.creationdatetime) &&
            Objects.equals(creationuser, that.creationuser) &&
            Objects.equals(lastupdatedatetime, that.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, that.lastupdateuser) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, absversionnumber, divisioncode, alloweddivisions, proordercountercompanycode, productionordercountercode, demandlist, entry, issue, orderdate, status, progressstatus, automaticcreationdemand, handledyelotuom, stdproductionbatch, stdproductionbatchuomcode, totalprimaryquantity, primaryunitofmeasurecode, totalsecondaryquantity, secondaryunitofmeasurecode, totalpackagingquantity, packagingunitofmeasurecode, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }

    @Override
    public String toString() {
        return "Productionorder{" +
            "id=" + id +
            ", absversionnumber=" + absversionnumber +
            ", divisioncode=" + divisioncode +
            ", alloweddivisions=" + alloweddivisions +
            ", proordercountercompanycode=" + proordercountercompanycode +
            ", productionordercountercode=" + productionordercountercode +
            ", demandlist='" + demandlist + '\'' +
            ", entry=" + entry +
            ", issue=" + issue +
            ", orderdate=" + orderdate +
            ", status=" + status +
            ", progressstatus=" + progressstatus +
            ", automaticcreationdemand=" + automaticcreationdemand +
            ", handledyelotuom=" + handledyelotuom +
            ", stdproductionbatch=" + stdproductionbatch +
            ", stdproductionbatchuomcode=" + stdproductionbatchuomcode +
            ", totalprimaryquantity=" + totalprimaryquantity +
            ", primaryunitofmeasurecode=" + primaryunitofmeasurecode +
            ", totalsecondaryquantity=" + totalsecondaryquantity +
            ", secondaryunitofmeasurecode=" + secondaryunitofmeasurecode +
            ", totalpackagingquantity=" + totalpackagingquantity +
            ", packagingunitofmeasurecode=" + packagingunitofmeasurecode +
            ", creationdatetime=" + creationdatetime +
            ", creationuser=" + creationuser +
            ", lastupdatedatetime=" + lastupdatedatetime +
            ", lastupdateuser=" + lastupdateuser +
            ", absuniqueid=" + absuniqueid +
            '}';
    }
}
