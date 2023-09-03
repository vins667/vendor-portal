package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CutPlanProgress.
 */
@Entity
@Table(name = "cut_plan_progress")
public class CutPlanProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="cutPlanProgressSeq", sequenceName="cut_plan_progress_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="cutPlanProgressSeq")
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
    @Column(name = "workcentercode", length = 20)
    private String workcentercode;

    @Size(max = 10)
    @Column(name = "resourcecode", length = 10)
    private String resourcecode;

    @Size(max = 20)
    @Column(name = "operationcode", length = 20)
    private String operationcode;

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

    @Column(name = "productionprogressimportid")
    private Long productionprogressimportid;

    @Column(name = "productionprogressid")
    private Long productionprogressid;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("cutPlanProgresses")
    @JoinColumn(name = "cut_plan_entry_id")
    private CutPlanEntry cutPlanEntry;

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

    public CutPlanProgress productionCode(String productionCode) {
        this.productionCode = productionCode;
        return this;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public Long getGroupstepnumber() {
        return groupstepnumber;
    }

    public CutPlanProgress groupstepnumber(Long groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
        return this;
    }

    public void setGroupstepnumber(Long groupstepnumber) {
        this.groupstepnumber = groupstepnumber;
    }

    public String getDemandcountercode() {
        return demandcountercode;
    }

    public CutPlanProgress demandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
        return this;
    }

    public void setDemandcountercode(String demandcountercode) {
        this.demandcountercode = demandcountercode;
    }

    public String getDemandcode() {
        return demandcode;
    }

    public CutPlanProgress demandcode(String demandcode) {
        this.demandcode = demandcode;
        return this;
    }

    public void setDemandcode(String demandcode) {
        this.demandcode = demandcode;
    }

    public String getWorkcentercode() {
        return workcentercode;
    }

    public CutPlanProgress workcentercode(String workcentercode) {
        this.workcentercode = workcentercode;
        return this;
    }

    public void setWorkcentercode(String workcentercode) {
        this.workcentercode = workcentercode;
    }

    public String getOperationcode() {
        return operationcode;
    }

    public CutPlanProgress operationcode(String operationcode) {
        this.operationcode = operationcode;
        return this;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
    }

    public Double getPrimaryquantity() {
        return primaryquantity;
    }

    public CutPlanProgress primaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
        return this;
    }

    public void setPrimaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
    }

    public String getPrimaryuomcode() {
        return primaryuomcode;
    }

    public CutPlanProgress primaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
        return this;
    }

    public void setPrimaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
    }

    public Double getSecondaryquantity() {
        return secondaryquantity;
    }

    public CutPlanProgress secondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
        return this;
    }

    public void setSecondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
    }

    public String getSecondaryuomcode() {
        return secondaryuomcode;
    }

    public CutPlanProgress secondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
        return this;
    }

    public void setSecondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public CutPlanProgress itemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
        return this;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public CutPlanProgress subcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
        return this;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01 != null ? subcode01.trim() : subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public CutPlanProgress subcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
        return this;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02 != null ? subcode02.trim() : subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public CutPlanProgress subcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
        return this;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03 != null ? subcode03.trim() : subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public CutPlanProgress subcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
        return this;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04 != null ? subcode04.trim() : subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public CutPlanProgress subcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
        return this;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05 != null ? subcode05.trim() : subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public CutPlanProgress subcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
        return this;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06 != null ? subcode06.trim() : subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public CutPlanProgress subcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
        return this;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07 != null ? subcode07.trim() : subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public CutPlanProgress subcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
        return this;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08 != null ? subcode08.trim() : subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public CutPlanProgress subcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
        return this;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09 != null ? subcode09.trim() : subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public CutPlanProgress subcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
        return this;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10 != null ? subcode10.trim() : subcode10;
    }

    public Long getProductionprogressimportid() {
        return productionprogressimportid;
    }

    public CutPlanProgress productionprogressimportid(Long productionprogressimportid) {
        this.productionprogressimportid = productionprogressimportid;
        return this;
    }

    public void setProductionprogressimportid(Long productionprogressimportid) {
        this.productionprogressimportid = productionprogressimportid;
    }

    public Long getProductionprogressid() {
        return productionprogressid;
    }

    public CutPlanProgress productionprogressid(Long productionprogressid) {
        this.productionprogressid = productionprogressid;
        return this;
    }

    public void setProductionprogressid(Long productionprogressid) {
        this.productionprogressid = productionprogressid;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CutPlanProgress createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CutPlanProgress createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public CutPlanProgress lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public CutPlanProgress lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    public String getResourcecode() {
        return resourcecode;
    }

    public void setResourcecode(String resourcecode) {
        this.resourcecode = resourcecode;
    }

    public CutPlanEntry getCutPlanEntry() {
        return cutPlanEntry;
    }

    public CutPlanProgress cutPlanEntry(CutPlanEntry cutPlanEntry) {
        this.cutPlanEntry = cutPlanEntry;
        return this;
    }

    public void setCutPlanEntry(CutPlanEntry cutPlanEntry) {
        this.cutPlanEntry = cutPlanEntry;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutPlanProgress)) {
            return false;
        }
        return id != null && id.equals(((CutPlanProgress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutPlanProgress{" +
            "id=" + getId() +
            ", productionCode='" + getProductionCode() + "'" +
            ", groupstepnumber=" + getGroupstepnumber() +
            ", demandcountercode='" + getDemandcountercode() + "'" +
            ", demandcode='" + getDemandcode() + "'" +
            ", workcentercode='" + getWorkcentercode() + "'" +
            ", operationcode='" + getOperationcode() + "'" +
            ", primaryquantity=" + getPrimaryquantity() +
            ", primaryuomcode='" + getPrimaryuomcode() + "'" +
            ", secondaryquantity=" + getSecondaryquantity() +
            ", secondaryuomcode='" + getSecondaryuomcode() + "'" +
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
            ", productionprogressimportid=" + getProductionprogressimportid() +
            ", productionprogressid=" + getProductionprogressid() +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", lastupdatedby='" + getLastupdatedby() + "'" +
            ", lastupdateddate='" + getLastupdateddate() + "'" +
            "}";
    }
}
