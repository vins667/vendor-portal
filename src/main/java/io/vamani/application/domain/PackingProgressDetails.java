package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CutIssueStitchDetails.
 */
@Entity
@Table(name = "packing_progress_details")
public class PackingProgressDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="packingProgressDetailsSeq", sequenceName="packing_progress_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="packingProgressDetailsSeq")
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

    @Column(name = "packing_line_issue_details_id")
    private Long packingLineIssueDetailsId;

    @Column(name = "cut_plan_bundle_details_id")
    private Long cutPlanBundleDetailsId;

    @Column(name = "cut_plan_bundle_id")
    private Long cutPlanBundleId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("packingProgressDetails")
    @JoinColumn(name = "packing_progress_entry_id")
    private PackingProgressEntry packingProgressEntry;

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

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getDecosubcode01() {
        return decosubcode01;
    }

    public void setDecosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01;
    }

    public String getDecosubcode02() {
        return decosubcode02;
    }

    public void setDecosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02;
    }

    public String getDecosubcode03() {
        return decosubcode03;
    }

    public void setDecosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03;
    }

    public String getDecosubcode04() {
        return decosubcode04;
    }

    public void setDecosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04;
    }

    public String getDecosubcode05() {
        return decosubcode05;
    }

    public void setDecosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05;
    }

    public String getDecosubcode06() {
        return decosubcode06;
    }

    public void setDecosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06;
    }

    public String getDecosubcode07() {
        return decosubcode07;
    }

    public void setDecosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07;
    }

    public String getDecosubcode08() {
        return decosubcode08;
    }

    public void setDecosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08;
    }

    public String getDecosubcode09() {
        return decosubcode09;
    }

    public void setDecosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09;
    }

    public String getDecosubcode10() {
        return decosubcode10;
    }

    public void setDecosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10;
    }

    public String getLogicalwarehousecode() {
        return logicalwarehousecode;
    }

    public void setLogicalwarehousecode(String logicalwarehousecode) {
        this.logicalwarehousecode = logicalwarehousecode;
    }

    public String getPhysicalwarehousecode() {
        return physicalwarehousecode;
    }

    public void setPhysicalwarehousecode(String physicalwarehousecode) {
        this.physicalwarehousecode = physicalwarehousecode;
    }

    public String getWhslocationwarehousezonecode() {
        return whslocationwarehousezonecode;
    }

    public void setWhslocationwarehousezonecode(String whslocationwarehousezonecode) {
        this.whslocationwarehousezonecode = whslocationwarehousezonecode;
    }

    public String getWarehouselocationcode() {
        return warehouselocationcode;
    }

    public void setWarehouselocationcode(String warehouselocationcode) {
        this.warehouselocationcode = warehouselocationcode;
    }

    public Long getQualitylevelcode() {
        return qualitylevelcode;
    }

    public void setQualitylevelcode(Long qualitylevelcode) {
        this.qualitylevelcode = qualitylevelcode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public String getContaineritemtypecode() {
        return containeritemtypecode;
    }

    public void setContaineritemtypecode(String containeritemtypecode) {
        this.containeritemtypecode = containeritemtypecode;
    }

    public String getContainersubcode01() {
        return containersubcode01;
    }

    public void setContainersubcode01(String containersubcode01) {
        this.containersubcode01 = containersubcode01;
    }

    public String getContainerelementcode() {
        return containerelementcode;
    }

    public void setContainerelementcode(String containerelementcode) {
        this.containerelementcode = containerelementcode;
    }

    public String getElementssubcodekey() {
        return elementssubcodekey;
    }

    public void setElementssubcodekey(String elementssubcodekey) {
        this.elementssubcodekey = elementssubcodekey;
    }

    public String getElementscode() {
        return elementscode;
    }

    public void setElementscode(String elementscode) {
        this.elementscode = elementscode;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(String suppliertype) {
        this.suppliertype = suppliertype;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBaseprimaryunitcode() {
        return baseprimaryunitcode;
    }

    public void setBaseprimaryunitcode(String baseprimaryunitcode) {
        this.baseprimaryunitcode = baseprimaryunitcode;
    }

    public Double getBaseprimaryquantityunit() {
        return baseprimaryquantityunit;
    }

    public void setBaseprimaryquantityunit(Double baseprimaryquantityunit) {
        this.baseprimaryquantityunit = baseprimaryquantityunit;
    }

    public String getBasesecondaryunitcode() {
        return basesecondaryunitcode;
    }

    public void setBasesecondaryunitcode(String basesecondaryunitcode) {
        this.basesecondaryunitcode = basesecondaryunitcode;
    }

    public Double getBasesecondaryquantityunit() {
        return basesecondaryquantityunit;
    }

    public void setBasesecondaryquantityunit(Double basesecondaryquantityunit) {
        this.basesecondaryquantityunit = basesecondaryquantityunit;
    }

    public String getPackagingcode() {
        return packagingcode;
    }

    public void setPackagingcode(String packagingcode) {
        this.packagingcode = packagingcode;
    }

    public Double getPackagingquantityunit() {
        return packagingquantityunit;
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

    public Long getPackingLineIssueDetailsId() {
        return packingLineIssueDetailsId;
    }

    public void setPackingLineIssueDetailsId(Long packingLineIssueDetailsId) {
        this.packingLineIssueDetailsId = packingLineIssueDetailsId;
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

    public PackingProgressEntry getPackingProgressEntry() {
        return packingProgressEntry;
    }

    public void setPackingProgressEntry(PackingProgressEntry packingProgressEntry) {
        this.packingProgressEntry = packingProgressEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackingProgressDetails that = (PackingProgressDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(itemtypecode, that.itemtypecode) && Objects.equals(decosubcode01, that.decosubcode01) && Objects.equals(decosubcode02, that.decosubcode02) && Objects.equals(decosubcode03, that.decosubcode03) && Objects.equals(decosubcode04, that.decosubcode04) && Objects.equals(decosubcode05, that.decosubcode05) && Objects.equals(decosubcode06, that.decosubcode06) && Objects.equals(decosubcode07, that.decosubcode07) && Objects.equals(decosubcode08, that.decosubcode08) && Objects.equals(decosubcode09, that.decosubcode09) && Objects.equals(decosubcode10, that.decosubcode10) && Objects.equals(logicalwarehousecode, that.logicalwarehousecode) && Objects.equals(physicalwarehousecode, that.physicalwarehousecode) && Objects.equals(whslocationwarehousezonecode, that.whslocationwarehousezonecode) && Objects.equals(warehouselocationcode, that.warehouselocationcode) && Objects.equals(qualitylevelcode, that.qualitylevelcode) && Objects.equals(lotcode, that.lotcode) && Objects.equals(containeritemtypecode, that.containeritemtypecode) && Objects.equals(containersubcode01, that.containersubcode01) && Objects.equals(containerelementcode, that.containerelementcode) && Objects.equals(elementssubcodekey, that.elementssubcodekey) && Objects.equals(elementscode, that.elementscode) && Objects.equals(customertype, that.customertype) && Objects.equals(customercode, that.customercode) && Objects.equals(suppliertype, that.suppliertype) && Objects.equals(suppliercode, that.suppliercode) && Objects.equals(projectcode, that.projectcode) && Objects.equals(baseprimaryunitcode, that.baseprimaryunitcode) && Objects.equals(baseprimaryquantityunit, that.baseprimaryquantityunit) && Objects.equals(basesecondaryunitcode, that.basesecondaryunitcode) && Objects.equals(basesecondaryquantityunit, that.basesecondaryquantityunit) && Objects.equals(packagingcode, that.packagingcode) && Objects.equals(packagingquantityunit, that.packagingquantityunit) && Objects.equals(productCode, that.productCode) && Objects.equals(createdby, that.createdby) && Objects.equals(createddate, that.createddate) && Objects.equals(lastupdatedby, that.lastupdatedby) && Objects.equals(lastupdateddate, that.lastupdateddate) && Objects.equals(packingLineIssueDetailsId, that.packingLineIssueDetailsId) && Objects.equals(cutPlanBundleDetailsId, that.cutPlanBundleDetailsId) && Objects.equals(cutPlanBundleId, that.cutPlanBundleId) && Objects.equals(packingProgressEntry, that.packingProgressEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemtypecode, decosubcode01, decosubcode02, decosubcode03, decosubcode04, decosubcode05, decosubcode06, decosubcode07, decosubcode08, decosubcode09, decosubcode10, logicalwarehousecode, physicalwarehousecode, whslocationwarehousezonecode, warehouselocationcode, qualitylevelcode, lotcode, containeritemtypecode, containersubcode01, containerelementcode, elementssubcodekey, elementscode, customertype, customercode, suppliertype, suppliercode, projectcode, baseprimaryunitcode, baseprimaryquantityunit, basesecondaryunitcode, basesecondaryquantityunit, packagingcode, packagingquantityunit, productCode, createdby, createddate, lastupdatedby, lastupdateddate, packingLineIssueDetailsId, cutPlanBundleDetailsId, cutPlanBundleId, packingProgressEntry);
    }
}
