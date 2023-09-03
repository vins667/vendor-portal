package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CutPlanBundleDetails.
 */
@Entity
@Table(name = "vcut_operation_pass_details")
public class VcutOperationPassDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutOperationPassDetailsSeq", sequenceName="vcut_operation_pass_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutOperationPassDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "production_counter_code", length = 20, nullable = false)
    private String productionCounterCode;

    @NotNull
    @Size(max = 20)
    @Column(name = "production_code", length = 20, nullable = false)
    private String productionCode;

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
    @Column(name = "projectcode", length = 20)
    private String projectcode;

    @Size(max = 20)
    @Column(name = "product_code", length = 20)
    private String productCode;

    @Size(max = 200)
    @Column(name = "operation", length = 200)
    private String operation;

    @Size(max = 20)
    @Column(name = "request_type", length = 20)
    private String requestType;

    @Size(max = 20)
    @Column(name = "createdby", length = 20)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Column(name = "cut_plan_bundle_details_id")
    private Long cutPlanBundleDetailsId;

    @Column(name = "cut_plan_bundle_id")
    private Long cutPlanBundleId;

    @Column(name = "vcut_style_plan_upload_id")
    private Long vcutStylePlanUploadId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductionCounterCode() {
        return productionCounterCode;
    }

    public void setProductionCounterCode(String productionCounterCode) {
        this.productionCounterCode = productionCounterCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    public String getSubcode01() {
        return subcode01;
    }

    public void setSubcode01(String subcode01) {
        this.subcode01 = subcode01;
    }

    public String getSubcode02() {
        return subcode02;
    }

    public void setSubcode02(String subcode02) {
        this.subcode02 = subcode02;
    }

    public String getSubcode03() {
        return subcode03;
    }

    public void setSubcode03(String subcode03) {
        this.subcode03 = subcode03;
    }

    public String getSubcode04() {
        return subcode04;
    }

    public void setSubcode04(String subcode04) {
        this.subcode04 = subcode04;
    }

    public String getSubcode05() {
        return subcode05;
    }

    public void setSubcode05(String subcode05) {
        this.subcode05 = subcode05;
    }

    public String getSubcode06() {
        return subcode06;
    }

    public void setSubcode06(String subcode06) {
        this.subcode06 = subcode06;
    }

    public String getSubcode07() {
        return subcode07;
    }

    public void setSubcode07(String subcode07) {
        this.subcode07 = subcode07;
    }

    public String getSubcode08() {
        return subcode08;
    }

    public void setSubcode08(String subcode08) {
        this.subcode08 = subcode08;
    }

    public String getSubcode09() {
        return subcode09;
    }

    public void setSubcode09(String subcode09) {
        this.subcode09 = subcode09;
    }

    public String getSubcode10() {
        return subcode10;
    }

    public void setSubcode10(String subcode10) {
        this.subcode10 = subcode10;
    }

    public Double getPrimaryquantity() {
        return primaryquantity;
    }

    public void setPrimaryquantity(Double primaryquantity) {
        this.primaryquantity = primaryquantity;
    }

    public String getPrimaryuomcode() {
        return primaryuomcode;
    }

    public void setPrimaryuomcode(String primaryuomcode) {
        this.primaryuomcode = primaryuomcode;
    }

    public Double getSecondaryquantity() {
        return secondaryquantity;
    }

    public void setSecondaryquantity(Double secondaryquantity) {
        this.secondaryquantity = secondaryquantity;
    }

    public String getSecondaryuomcode() {
        return secondaryuomcode;
    }

    public void setSecondaryuomcode(String secondaryuomcode) {
        this.secondaryuomcode = secondaryuomcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    public Long getVcutStylePlanUploadId() {
        return vcutStylePlanUploadId;
    }

    public void setVcutStylePlanUploadId(Long vcutStylePlanUploadId) {
        this.vcutStylePlanUploadId = vcutStylePlanUploadId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutOperationPassDetails that = (VcutOperationPassDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(productionCounterCode, that.productionCounterCode) && Objects.equals(productionCode, that.productionCode) && Objects.equals(destination, that.destination) && Objects.equals(itemtypecode, that.itemtypecode) && Objects.equals(subcode01, that.subcode01) && Objects.equals(subcode02, that.subcode02) && Objects.equals(subcode03, that.subcode03) && Objects.equals(subcode04, that.subcode04) && Objects.equals(subcode05, that.subcode05) && Objects.equals(subcode06, that.subcode06) && Objects.equals(subcode07, that.subcode07) && Objects.equals(subcode08, that.subcode08) && Objects.equals(subcode09, that.subcode09) && Objects.equals(subcode10, that.subcode10) && Objects.equals(primaryquantity, that.primaryquantity) && Objects.equals(primaryuomcode, that.primaryuomcode) && Objects.equals(secondaryquantity, that.secondaryquantity) && Objects.equals(secondaryuomcode, that.secondaryuomcode) && Objects.equals(projectcode, that.projectcode) && Objects.equals(productCode, that.productCode) && Objects.equals(operation, that.operation) && Objects.equals(createdby, that.createdby) && Objects.equals(createddate, that.createddate) && Objects.equals(cutPlanBundleDetailsId, that.cutPlanBundleDetailsId) && Objects.equals(cutPlanBundleId, that.cutPlanBundleId) && Objects.equals(vcutStylePlanUploadId, that.vcutStylePlanUploadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productionCounterCode, productionCode, destination, itemtypecode, subcode01, subcode02, subcode03, subcode04, subcode05, subcode06, subcode07, subcode08, subcode09, subcode10, primaryquantity, primaryuomcode, secondaryquantity, secondaryuomcode, projectcode, productCode, operation, createdby, createddate, cutPlanBundleDetailsId, cutPlanBundleId, vcutStylePlanUploadId);
    }
}
