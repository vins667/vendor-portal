package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A CutPlanBundleDetails.
 */
@Entity
@Table(name = "packing_line_issue_transaction")
public class PackingLineIssueTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="packingLineIssueTransactionSeq", sequenceName="packing_line_issue_transaction_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="packingLineIssueTransactionSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "production_code", length = 20, nullable = false)
    private String productionCode;

    @Column(name = "groupstepnumber")
    private Long groupstepnumber;

    @Size(max = 10)
    @Column(name = "demandcountercode", length = 10)
    private String demandcountercode;

    @Size(max = 20)
    @Column(name = "demandcode", length = 20)
    private String demandcode;

    @Size(max = 20)
    @Column(name = "destination", length = 20)
    private String destination;

    @Size(max = 3)
    @Column(name = "itemtypecode", length = 3)
    private String itemtypecode;

    @Size(max = 20)
    @Column(name = "subcode_01", length = 20)
    private String subcode01;

    @Size(max = 10)
    @Column(name = "subcode_02", length = 10)
    private String subcode02;

    @Size(max = 10)
    @Column(name = "subcode_03", length = 10)
    private String subcode03;

    @Size(max = 10)
    @Column(name = "subcode_04", length = 10)
    private String subcode04;

    @Size(max = 10)
    @Column(name = "subcode_05", length = 10)
    private String subcode05;

    @Size(max = 10)
    @Column(name = "subcode_06", length = 10)
    private String subcode06;

    @Size(max = 10)
    @Column(name = "subcode_07", length = 10)
    private String subcode07;

    @Size(max = 10)
    @Column(name = "subcode_08", length = 10)
    private String subcode08;

    @Size(max = 10)
    @Column(name = "subcode_09", length = 10)
    private String subcode09;

    @Size(max = 10)
    @Column(name = "subcode_10", length = 10)
    private String subcode10;

    @Size(max = 20)
    @Column(name = "logicalwarehousecode", length = 20)
    private String logicalwarehousecode;

    @Column(name = "primaryquantity")
    private Double primaryquantity;

    @Size(max = 20)
    @Column(name = "primaryuomcode", length = 20)
    private String primaryuomcode;

    @Column(name = "secondaryquantity")
    private Double secondaryquantity;

    @Size(max = 20)
    @Column(name = "secondaryuomcode", length = 20)
    private String secondaryuomcode;

    @Size(max = 20)
    @Column(name = "physicalwarehousecode", length = 20)
    private String physicalwarehousecode;

    @Size(max = 20)
    @Column(name = "whslocationwarehousezonecode", length = 20)
    private String whslocationwarehousezonecode;

    @Size(max = 20)
    @Column(name = "warehouselocationcode", length = 20)
    private String warehouselocationcode;

    @Size(max = 20)
    @Column(name = "lotcode", length = 20)
    private String lotcode;

    @Size(max = 20)
    @Column(name = "projectcode", length = 20)
    private String projectcode;

    @Size(max = 20)
    @Column(name = "product_code", length = 20)
    private String productCode;

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

    @Column(name = "packing_line_issue_id")
    private Long packingLineIssueId;

    @Column(name = "stocktransactionid")
    private Long stocktransactionid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public PackingLineIssueTransaction productionCode(String productionCode) {
        this.productionCode = productionCode;
        return this;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public Long getGroupstepnumber() {
        return groupstepnumber;
    }

    public PackingLineIssueTransaction groupstepnumber(Long groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
        return this;
    }

    public void setGroupstepnumber(Long groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
    }

    public String getDemandcountercode() {
        return demandcountercode;
    }

    public PackingLineIssueTransaction demandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
        return this;
    }

    public void setDemandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
    }

    public String getDemandcode() {
        return demandcode;
    }

    public PackingLineIssueTransaction demandcode(String demandcode) {
        this.demandcode = demandcode;
        return this;
    }

    public void setDemandcode(String demandcode) {
        this.demandcode = demandcode;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public PackingLineIssueTransaction itemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
        return this;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public PackingLineIssueTransaction subcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
        return this;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public PackingLineIssueTransaction subcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
        return this;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public PackingLineIssueTransaction subcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
        return this;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public PackingLineIssueTransaction subcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
        return this;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public PackingLineIssueTransaction subcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
        return this;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public PackingLineIssueTransaction subcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
        return this;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public PackingLineIssueTransaction subcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
        return this;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public PackingLineIssueTransaction subcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
        return this;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public PackingLineIssueTransaction subcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
        return this;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public PackingLineIssueTransaction subcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
        return this;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
    }

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public PackingLineIssueTransaction logicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
        return this;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    public Double getPrimaryquantity() {
        return primaryquantity;
    }

    public PackingLineIssueTransaction primaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
        return this;
    }

    public void setPrimaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
    }

    public String getPrimaryuomcode() {
        return primaryuomcode;
    }

    public PackingLineIssueTransaction primaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
        return this;
    }

    public void setPrimaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
    }

    public Double getSecondaryquantity() {
        return secondaryquantity;
    }

    public PackingLineIssueTransaction secondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
        return this;
    }

    public void setSecondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
    }

    public String getSecondaryuomcode() {
        return secondaryuomcode;
    }

    public PackingLineIssueTransaction secondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
        return this;
    }

    public void setSecondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
    }

    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public PackingLineIssueTransaction physicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
        return this;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public PackingLineIssueTransaction whslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
        return this;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public PackingLineIssueTransaction warehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
        return this;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public PackingLineIssueTransaction lotcode(String lotcode) {
        this.lotcode = lotcode;
        return this;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public PackingLineIssueTransaction projectcode(String projectcode) {
        this.projectcode = projectcode;
        return this;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getProductCode() {
        return productCode;
    }

    public PackingLineIssueTransaction productCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCreatedby() {
        return createdby;
    }

    public PackingLineIssueTransaction createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public PackingLineIssueTransaction createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public PackingLineIssueTransaction lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public PackingLineIssueTransaction lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    public Long getPackingLineIssueId() {
        return packingLineIssueId;
    }

    public void setPackingLineIssueId(Long packingLineIssueId) {
        this.packingLineIssueId = packingLineIssueId;
    }

    public Long getStocktransactionid() {
        return stocktransactionid;
    }

    public void setStocktransactionid(Long stocktransactionid) {
        this.stocktransactionid = stocktransactionid;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PackingLineIssueTransaction)) {
            return false;
        }
        return id != null && id.equals(((PackingLineIssueTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutPlanBundleDetails{" +
            "id=" + getId() +
            ", productionCode='" + getProductionCode() + "'" +
            ", groupstepnumber=" + getGroupstepnumber() +
            ", demandcountercode='" + getDemandcountercode() + "'" +
            ", demandcode='" + getDemandcode() + "'" +
            ", itemtypecode='" + getItemtypecode() + "'" +
            ", subcode01='" + getSubcode01() + "'" +
            ", subcode02='" + getSubcode02() + "'" +
            ", subcode03='" + getSubcode03() + "'" +
            ", subcode04='" + getSubcode04() + "'" +
            ", subcode05='" + getSubcode05() + "'" +
            ", subcode06='" + getSubcode06() + "'" +
            ", subcode07='" + getSubcode07() + "'" +
            ", subcode08='" + getSubcode08() + "'" +
            ", subcode09='" + getSubcode09() + "'" +
            ", subcode10='" + getSubcode10() + "'" +
            ", logicalwarehousecode='" + getLogicalwarehousecode() + "'" +
            ", primaryquantity=" + getPrimaryquantity() +
            ", primaryuomcode='" + getPrimaryuomcode() + "'" +
            ", secondaryquantity=" + getSecondaryquantity() +
            ", secondaryuomcode='" + getSecondaryuomcode() + "'" +
            ", physicalwarehousecode='" + getPhysicalwarehousecode() + "'" +
            ", whslocationwarehousezonecode='" + getWhslocationwarehousezonecode() + "'" +
            ", warehouselocationcode='" + getWarehouselocationcode() + "'" +
            ", lotcode='" + getLotcode() + "'" +
            ", projectcode='" + getProjectcode() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", lastupdatedby='" + getLastupdatedby() + "'" +
            ", lastupdateddate='" + getLastupdateddate() + "'" +
            "}";
    }
}
