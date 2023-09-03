package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * A VcutStylePlanUpload.
 */
@Entity
@Table(name = "vcut_style_plan_upload")
public class VcutStylePlanUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutStylePlanUploadSeq", sequenceName="vcut_style_plan_upload_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutStylePlanUploadSeq")
    private Long id;

    @Column(name = "plan_date")
    private LocalDate planDate;

    @Size(max = 20)
    @Column(name = "plant_code", length = 20)
    private String plantCode;

    @Size(max = 100)
    @Column(name = "plant_description", length = 100)
    private String plantDescription;

    @Size(max = 50)
    @Column(name = "line_no", length = 50)
    private String lineNo;

    @Size(max = 100)
    @Column(name = "line_desc", length = 100)
    private String lineDesc;

    @Size(max = 20)
    @Column(name = "po_no_counter", length = 20)
    private String poNoCounter;

    @Size(max = 20)
    @Column(name = "po_no", length = 20)
    private String poNo;

    @Size(max = 20)
    @Column(name = "projectcode", length = 20)
    private String projectcode;

    @Size(max = 50)
    @Column(name = "style", length = 50)
    private String style;

    @Size(max = 20)
    @Column(name = "color", length = 20)
    private String color;

    @Size(max = 100)
    @Column(name = "color_name", length = 100)
    private String colorName;

    @Size(max = 20)
    @Column(name = "destination", length = 20)
    private String destination;

    @Size(max = 100)
    @Column(name = "destination_desc", length = 100)
    private String destinationDesc;

    @Size(max = 20)
    @Column(name = "buyer", length = 20)
    private String buyer;

    @Size(max = 100)
    @Column(name = "buyer_name", length = 100)
    private String buyerName;

    @Size(max = 20)
    @Column(name = "item_type", length = 20)
    private String itemType;

    @Size(max = 100)
    @Column(name = "item_name", length = 100)
    private String itemName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "kick_off")
    private Double kickOff;

    @Column(name = "smv")
    private Double smv;

    @Column(name = "days")
    private Integer days;

    @Column(name = "operators")
    private Integer operators;

    @Column(name = "helpers")
    private Integer helpers;

    @Column(name = "working_hours")
    private Integer workingHours;

    @Size(max = 50)
    @Column(name = "merchant", length = 50)
    private String merchant;

    @Size(max = 100)
    @Column(name = "merchant_name", length = 100)
    private String merchantName;

    @Size(max = 50)
    @Column(name = "factory", length = 50)
    private String factory;

    @Size(max = 50)
    @Column(name = "floor", length = 50)
    private String floor;

    @Size(max = 50)
    @Column(name = "create_by", length = 50)
    private String createBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("vcutStylePlanUploads")
    @JoinColumn(name = "vcut_plan_change_master_id")
    private VcutPlanChangeMaster vcutPlanChangeMaster;


    @Column(name = "vcut_session_master_id")
    private Long vcutSessionMasterId;

    @Column(name = "active_plan")
    private Boolean activePlan;

    @Column(name = "size_validate")
    private Boolean sizeValidate;

    @Column(name = "sticker_validate")
    private Boolean stickerValidate;

    private static DecimalFormat df = new DecimalFormat("#.##");

    public VcutStylePlanUpload() {
    }

    public VcutStylePlanUpload(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode != null ? plantCode.trim().toUpperCase() : plantCode;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription != null ? plantDescription.trim().toUpperCase() : plantDescription;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo != null ? lineNo.trim().toUpperCase() : lineNo;
    }

    public String getLineDesc() {
        return lineDesc;
    }

    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc != null ? lineDesc.trim().toUpperCase() : lineDesc;
    }

    public String getPoNoCounter() {
        return poNoCounter;
    }

    public void setPoNoCounter(String poNoCounter) {
        this.poNoCounter = poNoCounter != null ? poNoCounter.trim().toUpperCase() : poNoCounter;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo != null ? poNo.trim().toUpperCase() : poNo;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode != null ? projectcode.trim().toUpperCase() : projectcode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style != null ? style.trim().toUpperCase() : style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color != null ? color.trim().toUpperCase() : color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName != null ? colorName.trim().toUpperCase() : colorName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination != null ? destination.trim().toUpperCase() : destination;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc != null ? destinationDesc.trim().toUpperCase() : destinationDesc;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer != null ? buyer.trim().toUpperCase() : buyer;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName != null ? buyerName.trim().toUpperCase() : buyerName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType != null ? itemType.trim().toUpperCase() : itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName != null ? itemName.trim().toUpperCase() : itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getKickOff() {
        return kickOff;
    }

    public void setKickOff(Double kickOff) {
        this.kickOff = kickOff;
    }

    public Double getSmv() {
        return smv;
    }

    public void setSmv(Double smv) {
        this.smv = smv;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getOperators() {
        return operators;
    }

    public void setOperators(Integer operators) {
        this.operators = operators;
    }

    public Integer getHelpers() {
        return helpers;
    }

    public void setHelpers(Integer helpers) {
        this.helpers = helpers;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant != null ? merchant.trim().toUpperCase() : merchant;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName != null ? merchantName.trim().toUpperCase() : merchantName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory != null ? factory.trim().toUpperCase() : factory;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor != null ? floor.trim().toUpperCase() : floor;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public VcutPlanChangeMaster getVcutPlanChangeMaster() {
        return vcutPlanChangeMaster;
    }

    public void setVcutPlanChangeMaster(VcutPlanChangeMaster vcutPlanChangeMaster) {
        this.vcutPlanChangeMaster = vcutPlanChangeMaster;
    }

    public Long getVcutSessionMasterId() {
        return vcutSessionMasterId;
    }

    public void setVcutSessionMasterId(Long vcutSessionMasterId) {
        this.vcutSessionMasterId = vcutSessionMasterId;
    }

    public Boolean getActivePlan() {
        return activePlan;
    }

    public void setActivePlan(Boolean activePlan) {
        this.activePlan = activePlan;
    }

    public Boolean getSizeValidate() {
        return sizeValidate;
    }

    public void setSizeValidate(Boolean sizeValidate) {
        this.sizeValidate = sizeValidate != null ? sizeValidate : false;
    }

    public Boolean getStickerValidate() {
        return stickerValidate;
    }

    public void setStickerValidate(Boolean stickerValidate) {
        this.stickerValidate = stickerValidate != null ? stickerValidate : false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutStylePlanUpload that = (VcutStylePlanUpload) o;
        return Objects.equals(id, that.id) && Objects.equals(planDate, that.planDate) && Objects.equals(plantCode, that.plantCode) && Objects.equals(plantDescription, that.plantDescription) && Objects.equals(lineNo, that.lineNo) && Objects.equals(lineDesc, that.lineDesc) && Objects.equals(poNoCounter, that.poNoCounter) && Objects.equals(poNo, that.poNo) && Objects.equals(projectcode, that.projectcode) && Objects.equals(style, that.style) && Objects.equals(color, that.color) && Objects.equals(colorName, that.colorName) && Objects.equals(destination, that.destination) && Objects.equals(destinationDesc, that.destinationDesc) && Objects.equals(buyer, that.buyer) && Objects.equals(buyerName, that.buyerName) && Objects.equals(itemType, that.itemType) && Objects.equals(itemName, that.itemName) && Objects.equals(quantity, that.quantity) && Objects.equals(kickOff, that.kickOff) && Objects.equals(smv, that.smv) && Objects.equals(days, that.days) && Objects.equals(operators, that.operators) && Objects.equals(helpers, that.helpers) && Objects.equals(workingHours, that.workingHours) && Objects.equals(merchant, that.merchant) && Objects.equals(merchantName, that.merchantName) && Objects.equals(factory, that.factory) && Objects.equals(floor, that.floor) && Objects.equals(createBy, that.createBy) && Objects.equals(createdDate, that.createdDate) && Objects.equals(vcutPlanChangeMaster, that.vcutPlanChangeMaster) && Objects.equals(vcutSessionMasterId, that.vcutSessionMasterId) && Objects.equals(activePlan, that.activePlan) && Objects.equals(sizeValidate, that.sizeValidate) && Objects.equals(stickerValidate, that.stickerValidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planDate, plantCode, plantDescription, lineNo, lineDesc, poNoCounter, poNo, projectcode, style, color, colorName, destination, destinationDesc, buyer, buyerName, itemType, itemName, quantity, kickOff, smv, days, operators, helpers, workingHours, merchant, merchantName, factory, floor, createBy, createdDate, vcutPlanChangeMaster, vcutSessionMasterId, activePlan, sizeValidate, stickerValidate);
    }

    @Override
    public String toString() {
        return "VcutStylePlanUpload{" +
            "id=" + id +
            ", planDate=" + planDate +
            ", plantCode='" + plantCode + '\'' +
            ", plantDescription='" + plantDescription + '\'' +
            ", lineNo='" + lineNo + '\'' +
            ", lineDesc='" + lineDesc + '\'' +
            ", poNoCounter='" + poNoCounter + '\'' +
            ", poNo='" + poNo + '\'' +
            ", projectcode='" + projectcode + '\'' +
            ", style='" + style + '\'' +
            ", color='" + color + '\'' +
            ", colorName='" + colorName + '\'' +
            ", destination='" + destination + '\'' +
            ", destinationDesc='" + destinationDesc + '\'' +
            ", buyer='" + buyer + '\'' +
            ", buyerName='" + buyerName + '\'' +
            ", itemType='" + itemType + '\'' +
            ", itemName='" + itemName + '\'' +
            ", quantity=" + quantity +
            ", kickOff=" + kickOff +
            ", smv=" + smv +
            ", days=" + days +
            ", operators=" + operators +
            ", helpers=" + helpers +
            ", workingHours=" + workingHours +
            ", merchant='" + merchant + '\'' +
            ", merchantName='" + merchantName + '\'' +
            ", factory='" + factory + '\'' +
            ", floor='" + floor + '\'' +
            ", createBy='" + createBy + '\'' +
            ", createdDate=" + createdDate +
            ", vcutPlanChangeMaster=" + vcutPlanChangeMaster +
            ", vcutSessionMasterId=" + vcutSessionMasterId +
            ", activePlan=" + activePlan +
            ", sizeValidate=" + sizeValidate +
            ", stickerValidate=" + stickerValidate +
            '}';
    }
}
