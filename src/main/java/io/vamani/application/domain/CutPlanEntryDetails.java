package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.db2.model.BalanceBean;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A CutPlanEntryDetails.
 */
@Entity
@Table(name = "cut_plan_entry_details")
public class CutPlanEntryDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="cutPlanEntryDetailsSeq", sequenceName="cut_plan_entry_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="cutPlanEntryDetailsSeq")
    private Long id;

    @Size(max = 3)
    @Column(name = "itemtypecode", length = 3)
    private String itemtypecode;

    @Size(max = 20)
    @Column(name = "decosubcode_01", length = 20)
    private String decosubcode01;

    @Size(max = 10)
    @Column(name = "decosubcode_02", length = 10)
    private String decosubcode02;

    @Size(max = 10)
    @Column(name = "decosubcode_03", length = 10)
    private String decosubcode03;

    @Size(max = 10)
    @Column(name = "decosubcode_04", length = 10)
    private String decosubcode04;

    @Size(max = 10)
    @Column(name = "decosubcode_05", length = 10)
    private String decosubcode05;

    @Size(max = 10)
    @Column(name = "decosubcode_06", length = 10)
    private String decosubcode06;

    @Size(max = 10)
    @Column(name = "decosubcode_07", length = 10)
    private String decosubcode07;

    @Size(max = 10)
    @Column(name = "decosubcode_08", length = 10)
    private String decosubcode08;

    @Size(max = 10)
    @Column(name = "decosubcode_09", length = 10)
    private String decosubcode09;

    @Size(max = 10)
    @Column(name = "decosubcode_10", length = 10)
    private String decosubcode10;

    @Size(max = 8)
    @Column(name = "logicalwarehousecode", length = 8)
    private String logicalwarehousecode;

    @Size(max = 8)
    @Column(name = "physicalwarehousecode", length = 8)
    private String physicalwarehousecode;

    @Size(max = 3)
    @Column(name = "whslocationwarehousezonecode", length = 3)
    private String whslocationwarehousezonecode;

    @Size(max = 10)
    @Column(name = "warehouselocationcode", length = 10)
    private String warehouselocationcode;

    @Column(name = "qualitylevelcode")
    private Long qualitylevelcode;

    @Size(max = 10)
    @Column(name = "lotcode", length = 10)
    private String lotcode;

    @Size(max = 3)
    @Column(name = "containeritemtypecode", length = 3)
    private String containeritemtypecode;

    @Size(max = 20)
    @Column(name = "containersubcode_01", length = 20)
    private String containersubcode01;

    @Size(max = 15)
    @Column(name = "containerelementcode", length = 15)
    private String containerelementcode;

    @Size(max = 20)
    @Column(name = "elementssubcodekey", length = 20)
    private String elementssubcodekey;

    @Size(max = 15)
    @Column(name = "elementscode", length = 15)
    private String elementscode;

    @Size(max = 1)
    @Column(name = "customertype", length = 1)
    private String customertype;

    @Size(max = 8)
    @Column(name = "customercode", length = 8)
    private String customercode;

    @Size(max = 1)
    @Column(name = "suppliertype", length = 1)
    private String suppliertype;

    @Size(max = 8)
    @Column(name = "suppliercode", length = 8)
    private String suppliercode;

    @Size(max = 20)
    @Column(name = "projectcode", length = 20)
    private String projectcode;

    @Size(max = 3)
    @Column(name = "baseprimaryunitcode", length = 3)
    private String baseprimaryunitcode;

    @Column(name = "baseprimaryquantityunit")
    private Double baseprimaryquantityunit;

    @Size(max = 3)
    @Column(name = "basesecondaryunitcode", length = 3)
    private String basesecondaryunitcode;

    @Column(name = "basesecondaryquantityunit")
    private Double basesecondaryquantityunit;

    @Size(max = 3)
    @Column(name = "packagingcode", length = 3)
    private String packagingcode;

    @Column(name = "packagingquantityunit")
    private Double packagingquantityunit;

    @Size(max = 50)
    @Column(name = "createdby", length = 50)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 50)
    @Column(name = "lastupdatedby", length = 50)
    private String lastupdatedby;

    @Column(name = "lastupdateddate")
    private Instant lastupdateddate;

    @Column(name = "no_plies")
    private Long noPlies;

    @Column(name = "end_bits")
    private Double endBits;

    @Column(name = "split_no_plies")
    private Long splitNoPlies;

    @Column(name = "split_end_bits")
    private Double splitEndBits;

    @Size(max = 1)
    @Column(name = "split_flag", length = 1)
    private String splitFlag;

    @Column(name = "split_transactionnumber")
    private Long splitTransactionnumber;

    @Size(max = 50)
    @Column(name = "issued_by", length = 50)
    private String issuedBy;

    @Column(name = "issued_date")
    private Instant issuedDate;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Size(max = 15)
    @Column(name = "resource_code", length = 15)
    private String resourceCode;

    @Size(max = 100)
    @Column(name = "resource_description", length = 100)
    private String resourceDescription;

    @Column(name = "actual_end_bits")
    private Double actualEndBits;

    @Column(name = "actual_no_plies")
    private Long actualNoPlies;

    @Column(name = "actual_roll_qty")
    private Double actualRollQty;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("cutPlanEntryDetails")
    @JoinColumn(name = "cut_plan_entry_id")
    private CutPlanEntry cutPlanEntry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public CutPlanEntryDetails itemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
        return this;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getDecosubcode01() {
        return decosubcode01;
    }

    public CutPlanEntryDetails decosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01 != null ? decosubcode01.trim() : decosubcode01;
        return this;
    }

    public void setDecosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01 != null ? decosubcode01.trim() : decosubcode01;
    }

    public String getDecosubcode02() {
        return decosubcode02;
    }

    public CutPlanEntryDetails decosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02 != null ? decosubcode02.trim() : decosubcode02;
        return this;
    }

    public void setDecosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02 != null ? decosubcode02.trim() : decosubcode02;
    }

    public String getDecosubcode03() {
        return decosubcode03;
    }

    public CutPlanEntryDetails decosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03 != null ? decosubcode03.trim() : decosubcode03;
        return this;
    }

    public void setDecosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03 != null ? decosubcode03.trim() : decosubcode03;
    }

    public String getDecosubcode04() {
        return decosubcode04;
    }

    public CutPlanEntryDetails decosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04 != null ? decosubcode04.trim() : decosubcode04;
        return this;
    }

    public void setDecosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04 != null ? decosubcode04.trim() : decosubcode04;
    }

    public String getDecosubcode05() {
        return decosubcode05;
    }

    public CutPlanEntryDetails decosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05 != null ? decosubcode05.trim() : decosubcode05;
        return this;
    }

    public void setDecosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05 != null ? decosubcode05.trim() : decosubcode05;
    }

    public String getDecosubcode06() {
        return decosubcode06;
    }

    public CutPlanEntryDetails decosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06 != null ? decosubcode06.trim() : decosubcode06;
        return this;
    }

    public void setDecosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06 != null ? decosubcode06.trim() : decosubcode06;
    }

    public String getDecosubcode07() {
        return decosubcode07;
    }

    public CutPlanEntryDetails decosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07 != null ? decosubcode07.trim() : decosubcode07;
        return this;
    }

    public void setDecosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07 != null ? decosubcode07.trim() : decosubcode07;
    }

    public String getDecosubcode08() {
        return decosubcode08;
    }

    public CutPlanEntryDetails decosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08 != null ? decosubcode08.trim() : decosubcode08;
        return this;
    }

    public void setDecosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08 != null ? decosubcode08.trim() : decosubcode08;
    }

    public String getDecosubcode09() {
        return decosubcode09;
    }

    public CutPlanEntryDetails decosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09 != null ? decosubcode09.trim() : decosubcode09;
        return this;
    }

    public void setDecosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09 != null ? decosubcode09.trim() : decosubcode09;
    }

    public String getDecosubcode10() {
        return decosubcode10;
    }

    public CutPlanEntryDetails decosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10 != null ? decosubcode10.trim() : decosubcode10;
        return this;
    }

    public void setDecosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10 != null ? decosubcode10.trim() : decosubcode10;
    }

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public CutPlanEntryDetails logicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
        return this;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public CutPlanEntryDetails physicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
        return this;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public CutPlanEntryDetails whslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
        return this;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public CutPlanEntryDetails warehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
        return this;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    public Long getQualitylevelcode() {
        return qualitylevelcode;
    }

    public CutPlanEntryDetails qualitylevelcode(Long qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
        return this;
    }

    public void setQualitylevelcode(Long qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public CutPlanEntryDetails lotcode(String lotcode) {
        this.lotcode = lotcode;
        return this;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public String getContaineritemtypecode() {
        return containeritemtypecode;
    }

    public CutPlanEntryDetails containeritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
        return this;
    }

    public void setContaineritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
    }

    public String getContainersubcode01() {
        return containersubcode01;
    }

    public CutPlanEntryDetails containersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
        return this;
    }

    public void setContainersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
    }

    public String getContainerelementcode() {
        return containerelementcode;
    }

    public CutPlanEntryDetails containerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
        return this;
    }

    public void setContainerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
    }

    public String getElementssubcodekey() {
        return elementssubcodekey;
    }

    public CutPlanEntryDetails elementssubcodekey(String elementssubcodekey) {
        this.elementssubcodekey = elementssubcodekey;
        return this;
    }

    public void setElementssubcodekey(String elementssubcodekey) {
        this.elementssubcodekey = elementssubcodekey;
    }

    public String getElementscode() {
        return elementscode;
    }

    public CutPlanEntryDetails elementscode(String elementscode) {
        this.elementscode = elementscode != null ? elementscode.trim() : elementscode;
        return this;
    }

    public void setElementscode(String elementscode) {
        this.elementscode = elementscode != null ? elementscode.trim() : elementscode;
    }

    public String getCustomertype() {
        return customertype;
    }

    public CutPlanEntryDetails customertype(String customertype) {
        this.customertype = customertype;
        return this;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getCustomercode() {
        return customercode;
    }

    public CutPlanEntryDetails customercode(String customercode) {
        this.customercode = customercode;
        return this;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getSuppliertype() {
        return suppliertype;
    }

    public CutPlanEntryDetails suppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
        return this;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public CutPlanEntryDetails suppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
        return this;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public CutPlanEntryDetails projectcode(String projectcode) {
        this.projectcode = projectcode;
        return this;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBaseprimaryunitcode() {
        return baseprimaryunitcode;
    }

    public CutPlanEntryDetails baseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
        return this;
    }

    public void setBaseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
    }

    public Double getBaseprimaryquantityunit() {
        return baseprimaryquantityunit;
    }

    public CutPlanEntryDetails baseprimaryquantityunit(Double baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
        return this;
    }

    public void setBaseprimaryquantityunit(Double baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
    }

    public String getBasesecondaryunitcode() {
        return basesecondaryunitcode;
    }

    public CutPlanEntryDetails basesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
        return this;
    }

    public void setBasesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
    }

    public Double getBasesecondaryquantityunit() {
        return basesecondaryquantityunit;
    }

    public CutPlanEntryDetails basesecondaryquantityunit(Double basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
        return this;
    }

    public void setBasesecondaryquantityunit(Double basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
    }

    public String getPackagingcode() {
        return packagingcode;
    }

    public CutPlanEntryDetails packagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
        return this;
    }

    public void setPackagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
    }

    public Double getPackagingquantityunit() {
        return packagingquantityunit;
    }

    public CutPlanEntryDetails packagingquantityunit(Double packagingquantityunit) {
        this.packagingquantityunit = packagingquantityunit;
        return this;
    }

    public void setPackagingquantityunit(Double packagingquantityunit) {
        this.packagingquantityunit = packagingquantityunit;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CutPlanEntryDetails createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CutPlanEntryDetails createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public CutPlanEntryDetails lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public CutPlanEntryDetails lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    public Long getNoPlies() {
        return noPlies;
    }

    public void setNoPlies(Long noPlies) {
        this.noPlies = noPlies;
    }

    public Double getEndBits() {
        return endBits;
    }

    public void setEndBits(Double endBits) {
        this.endBits = endBits;
    }

    public Long getSplitNoPlies() {
        return splitNoPlies;
    }

    public void setSplitNoPlies(Long splitNoPlies) {
        this.splitNoPlies = splitNoPlies;
    }

    public Double getSplitEndBits() {
        return splitEndBits;
    }

    public void setSplitEndBits(Double splitEndBits) {
        this.splitEndBits = splitEndBits;
    }

    public String getSplitFlag() {
        return splitFlag;
    }

    public void setSplitFlag(String splitFlag) {
        this.splitFlag = splitFlag;
    }

    public Long getSplitTransactionnumber() {
        return splitTransactionnumber;
    }

    public void setSplitTransactionnumber(Long splitTransactionnumber) {
        this.splitTransactionnumber = splitTransactionnumber;
    }

    public CutPlanEntry getCutPlanEntry() {
        return cutPlanEntry;
    }

    public CutPlanEntryDetails cutPlanEntry(CutPlanEntry cutPlanEntry) {
        this.cutPlanEntry = cutPlanEntry;
        return this;
    }

    public void setCutPlanEntry(CutPlanEntry cutPlanEntry) {
        this.cutPlanEntry = cutPlanEntry;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Instant getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Instant issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode != null ? resourceCode.trim() : resourceCode;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public Double getActualEndBits() {
        return actualEndBits;
    }

    public void setActualEndBits(Double actualEndBits) {
        this.actualEndBits = actualEndBits;
    }

    public Long getActualNoPlies() {
        return actualNoPlies;
    }

    public void setActualNoPlies(Long actualNoPlies) {
        this.actualNoPlies = actualNoPlies;
    }

    public Double getActualRollQty() {
        return actualRollQty;
    }

    public void setActualRollQty(Double actualRollQty) {
        this.actualRollQty = actualRollQty;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CutPlanEntryDetails)) {
            return false;
        }
        return id != null && id.equals(((CutPlanEntryDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutPlanEntryDetails{" +
            "id=" + getId() +
            ", itemtypecode='" + getItemtypecode() + "'" +
            ", decosubcode01='" + getDecosubcode01() + "'" +
            ", decosubcode02='" + getDecosubcode02() + "'" +
            ", decosubcode03='" + getDecosubcode03() + "'" +
            ", decosubcode04='" + getDecosubcode04() + "'" +
            ", decosubcode05='" + getDecosubcode05() + "'" +
            ", decosubcode06='" + getDecosubcode06() + "'" +
            ", decosubcode07='" + getDecosubcode07() + "'" +
            ", decosubcode08='" + getDecosubcode08() + "'" +
            ", decosubcode09='" + getDecosubcode09() + "'" +
            ", decosubcode10='" + getDecosubcode10() + "'" +
            ", logicalwarehousecode='" + getLogicalwarehousecode() + "'" +
            ", physicalwarehousecode='" + getPhysicalwarehousecode() + "'" +
            ", whslocationwarehousezonecode='" + getWhslocationwarehousezonecode() + "'" +
            ", warehouselocationcode='" + getWarehouselocationcode() + "'" +
            ", qualitylevelcode=" + getQualitylevelcode() +
            ", lotcode='" + getLotcode() + "'" +
            ", containeritemtypecode='" + getContaineritemtypecode() + "'" +
            ", containersubcode01='" + getContainersubcode01() + "'" +
            ", containerelementcode='" + getContainerelementcode() + "'" +
            ", elementssubcodekey='" + getElementssubcodekey() + "'" +
            ", elementscode='" + getElementscode() + "'" +
            ", customertype='" + getCustomertype() + "'" +
            ", customercode='" + getCustomercode() + "'" +
            ", suppliertype='" + getSuppliertype() + "'" +
            ", suppliercode='" + getSuppliercode() + "'" +
            ", projectcode='" + getProjectcode() + "'" +
            ", baseprimaryunitcode='" + getBaseprimaryunitcode() + "'" +
            ", baseprimaryquantityunit=" + getBaseprimaryquantityunit() +
            ", basesecondaryunitcode='" + getBasesecondaryunitcode() + "'" +
            ", basesecondaryquantityunit=" + getBasesecondaryquantityunit() +
            ", packagingcode='" + getPackagingcode() + "'" +
            ", packagingquantityunit=" + getPackagingquantityunit() +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", lastupdatedby='" + getLastupdatedby() + "'" +
            ", lastupdateddate='" + getLastupdateddate() + "'" +
            "}";
    }

    public CutPlanEntryDetails() {
    }

    public CutPlanEntryDetails(BalanceBean balanceBean) {
        this.id = balanceBean.getDetailId();
        this.itemtypecode = balanceBean.getItemtypecode();
        this.decosubcode01 = balanceBean.getDecosubcode01() != null ? balanceBean.getDecosubcode01().trim() : balanceBean.getDecosubcode01();
        this.decosubcode02 = balanceBean.getDecosubcode02() != null ? balanceBean.getDecosubcode02().trim() : balanceBean.getDecosubcode02();
        this.decosubcode03 = balanceBean.getDecosubcode03() != null ? balanceBean.getDecosubcode03().trim() : balanceBean.getDecosubcode03();
        this.decosubcode04 = balanceBean.getDecosubcode04() != null ? balanceBean.getDecosubcode04().trim() : balanceBean.getDecosubcode04();
        this.decosubcode05 = balanceBean.getDecosubcode05() != null ? balanceBean.getDecosubcode05().trim() : balanceBean.getDecosubcode05();
        this.decosubcode06 = balanceBean.getDecosubcode06() != null ? balanceBean.getDecosubcode06().trim() : balanceBean.getDecosubcode06();
        this.decosubcode07 = balanceBean.getDecosubcode07() != null ? balanceBean.getDecosubcode07().trim() : balanceBean.getDecosubcode07();
        this.decosubcode08 = balanceBean.getDecosubcode08() != null ? balanceBean.getDecosubcode08().trim() : balanceBean.getDecosubcode08();
        this.decosubcode09 = balanceBean.getDecosubcode09() != null ? balanceBean.getDecosubcode09().trim() : balanceBean.getDecosubcode09();
        this.decosubcode10 = balanceBean.getDecosubcode10() != null ? balanceBean.getDecosubcode10().trim() : balanceBean.getDecosubcode10();
        this.logicalwarehousecode = balanceBean.getLogicalwarehousecode();
        this.physicalwarehousecode = balanceBean.getPhysicalwarehousecode();
        this.whslocationwarehousezonecode = balanceBean.getWhslocationwarehousezonecode();
        this.warehouselocationcode = balanceBean.getWarehouselocationcode();
        this.qualitylevelcode = balanceBean.getQualitylevelcode().longValue();
        this.lotcode = balanceBean.getLotcode().trim();
        this.containeritemtypecode = balanceBean.getContaineritemtypecode();
        this.containersubcode01 = balanceBean.getContainersubcode01();
        this.containerelementcode = balanceBean.getContainerelementcode();
        this.elementssubcodekey = balanceBean.getElementssubcodekey();
        this.elementscode = balanceBean.getElementscode()  != null ? balanceBean.getElementscode().trim() : balanceBean.getElementscode();
        this.customertype = balanceBean.getCustomertype();
        this.customercode = balanceBean.getCustomercode();
        this.suppliertype = balanceBean.getSuppliertype();
        this.suppliercode = balanceBean.getSuppliercode();
        this.projectcode = balanceBean.getProjectcode();
        this.baseprimaryunitcode = balanceBean.getBaseprimaryunitcode();
        this.baseprimaryquantityunit = balanceBean.getBaseprimaryquantityunit().doubleValue();
        this.basesecondaryunitcode = balanceBean.getBasesecondaryunitcode();
        this.basesecondaryquantityunit = balanceBean.getBasesecondaryquantityunit().doubleValue();
        this.packagingcode = balanceBean.getPackagingcode();
        this.packagingquantityunit = balanceBean.getPackagingquantityunit().doubleValue();
        this.noPlies = balanceBean.getNoPlies();
        this.endBits = balanceBean.getEndBits();
        this.splitNoPlies = balanceBean.getSplitNoPlies();
        this.splitEndBits = balanceBean.getSplitEndBits();
        this.splitFlag = balanceBean.getSplitFlag();
        this.splitTransactionnumber = balanceBean.getSplitTransactionnumber();
        this.actualNoPlies = balanceBean.getActualNoPlies();
        this.actualRollQty = balanceBean.getActualRollQty();
        this.actualEndBits = balanceBean.getActualEndBits();
    }
}
