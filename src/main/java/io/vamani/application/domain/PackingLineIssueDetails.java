package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

/**
 * A CutIssueStitchDetails.
 */
@Entity
@Table(name = "packing_line_issue_details")
public class PackingLineIssueDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="packingLineIssueDetailsSeq", sequenceName="packing_line_issue_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="packingLineIssueDetailsSeq")
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

    @Size(max = 20)
    @Column(name = "product_code", length = 20)
    private String productCode;

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

    @Column(name = "stitch_issue_pack_details_id")
    private Long stitchIssuePackDetailsId;

    @Column(name = "cut_plan_bundle_details_id")
    private Long cutPlanBundleDetailsId;

    @Column(name = "cut_plan_bundle_id")
    private Long cutPlanBundleId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("packingLineIssueDetails")
    @JoinColumn(name = "packing_line_issue_id")
    private PackingLineIssue packingLineIssue;

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

    public PackingLineIssueDetails itemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
        return this;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getDecosubcode01() {
        return decosubcode01;
    }

    public PackingLineIssueDetails decosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01;
        return this;
    }

    public void setDecosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01;
    }

    public String getDecosubcode02() {
        return decosubcode02;
    }

    public PackingLineIssueDetails decosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02;
        return this;
    }

    public void setDecosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02;
    }

    public String getDecosubcode03() {
        return decosubcode03;
    }

    public PackingLineIssueDetails decosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03;
        return this;
    }

    public void setDecosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03;
    }

    public String getDecosubcode04() {
        return decosubcode04;
    }

    public PackingLineIssueDetails decosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04;
        return this;
    }

    public void setDecosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04;
    }

    public String getDecosubcode05() {
        return decosubcode05;
    }

    public PackingLineIssueDetails decosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05;
        return this;
    }

    public void setDecosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05;
    }

    public String getDecosubcode06() {
        return decosubcode06;
    }

    public PackingLineIssueDetails decosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06;
        return this;
    }

    public void setDecosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06;
    }

    public String getDecosubcode07() {
        return decosubcode07;
    }

    public PackingLineIssueDetails decosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07;
        return this;
    }

    public void setDecosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07;
    }

    public String getDecosubcode08() {
        return decosubcode08;
    }

    public PackingLineIssueDetails decosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08;
        return this;
    }

    public void setDecosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08;
    }

    public String getDecosubcode09() {
        return decosubcode09;
    }

    public PackingLineIssueDetails decosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09;
        return this;
    }

    public void setDecosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09;
    }

    public String getDecosubcode10() {
        return decosubcode10;
    }

    public PackingLineIssueDetails decosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10;
        return this;
    }

    public void setDecosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10;
    }

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public PackingLineIssueDetails logicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
        return this;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public PackingLineIssueDetails physicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
        return this;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public PackingLineIssueDetails whslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
        return this;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public PackingLineIssueDetails warehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
        return this;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    public Long getQualitylevelcode() {
        return qualitylevelcode;
    }

    public PackingLineIssueDetails qualitylevelcode(Long qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
        return this;
    }

    public void setQualitylevelcode(Long qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public PackingLineIssueDetails lotcode(String lotcode) {
        this.lotcode = lotcode != null ? lotcode.trim()  : lotcode;
        return this;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode != null ? lotcode.trim() : lotcode;
    }

    public String getContaineritemtypecode() {
        return containeritemtypecode;
    }

    public PackingLineIssueDetails containeritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
        return this;
    }

    public void setContaineritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
    }

    public String getContainersubcode01() {
        return containersubcode01;
    }

    public PackingLineIssueDetails containersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
        return this;
    }

    public void setContainersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
    }

    public String getContainerelementcode() {
        return containerelementcode;
    }

    public PackingLineIssueDetails containerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
        return this;
    }

    public void setContainerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
    }

    public String getElementssubcodekey() {
        return elementssubcodekey;
    }

    public PackingLineIssueDetails elementssubcodekey(String elementssubcodekey) {
        this.elementssubcodekey = elementssubcodekey;
        return this;
    }

    public void setElementssubcodekey(String elementssubcodekey) {
        this.elementssubcodekey = elementssubcodekey;
    }

    public String getElementscode() {
        return elementscode;
    }

    public PackingLineIssueDetails elementscode(String elementscode) {
        this.elementscode = elementscode;
        return this;
    }

    public void setElementscode(String elementscode) {
        this.elementscode = elementscode;
    }

    public String getCustomertype() {
        return customertype;
    }

    public PackingLineIssueDetails customertype(String customertype) {
        this.customertype = customertype;
        return this;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getCustomercode() {
        return customercode;
    }

    public PackingLineIssueDetails customercode(String customercode) {
        this.customercode = customercode;
        return this;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getSuppliertype() {
        return suppliertype;
    }

    public PackingLineIssueDetails suppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
        return this;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public PackingLineIssueDetails suppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
        return this;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public PackingLineIssueDetails projectcode(String projectcode) {
        this.projectcode = projectcode;
        return this;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBaseprimaryunitcode() {
        return baseprimaryunitcode;
    }

    public PackingLineIssueDetails baseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
        return this;
    }

    public void setBaseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
    }

    public Double getBaseprimaryquantityunit() {
        return baseprimaryquantityunit;
    }

    public PackingLineIssueDetails baseprimaryquantityunit(Double baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
        return this;
    }

    public void setBaseprimaryquantityunit(Double baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
    }

    public String getBasesecondaryunitcode() {
        return basesecondaryunitcode;
    }

    public PackingLineIssueDetails basesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
        return this;
    }

    public void setBasesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
    }

    public Double getBasesecondaryquantityunit() {
        return basesecondaryquantityunit;
    }

    public PackingLineIssueDetails basesecondaryquantityunit(Double basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
        return this;
    }

    public void setBasesecondaryquantityunit(Double basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
    }

    public String getPackagingcode() {
        return packagingcode;
    }

    public PackingLineIssueDetails packagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
        return this;
    }

    public void setPackagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
    }

    public Double getPackagingquantityunit() {
        return packagingquantityunit;
    }

    public PackingLineIssueDetails packagingquantityunit(Double packagingquantityunit) {
        this.packagingquantityunit = packagingquantityunit;
        return this;
    }

    public void setPackagingquantityunit(Double packagingquantityunit) {
        this.packagingquantityunit = packagingquantityunit;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCreatedby() {
        return createdby;
    }

    public PackingLineIssueDetails createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public PackingLineIssueDetails createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public PackingLineIssueDetails lastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
        return this;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Instant getLastupdateddate() {
        return lastupdateddate;
    }

    public PackingLineIssueDetails lastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
        return this;
    }

    public void setLastupdateddate(Instant lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    public Long getStitchIssuePackDetailsId() {
        return stitchIssuePackDetailsId;
    }

    public void setStitchIssuePackDetailsId(Long stitchIssuePackDetailsId) {
        this.stitchIssuePackDetailsId = stitchIssuePackDetailsId;
    }

    public Long getCutPlanBundleDetailsId() {
        return cutPlanBundleDetailsId;
    }

    public void setCutPlanBundleDetailsId(Long cutPlanBundleDetailsId) {
        this.cutPlanBundleDetailsId = cutPlanBundleDetailsId;
    }

    public Long getCutPlanBundleId() {
        return cutPlanBundleId;
    }

    public void setCutPlanBundleId(Long cutPlanBundleId) {
        this.cutPlanBundleId = cutPlanBundleId;
    }

    public PackingLineIssue getPackingLineIssue() {
        return packingLineIssue;
    }

    public void setPackingLineIssue(PackingLineIssue packingLineIssue) {
        this.packingLineIssue = packingLineIssue;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PackingLineIssueDetails)) {
            return false;
        }
        return id != null && id.equals(((PackingLineIssueDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CutIssueStitchDetails{" +
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
}
