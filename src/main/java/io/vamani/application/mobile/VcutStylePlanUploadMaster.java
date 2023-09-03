package io.vamani.application.mobile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.domain.VcutPlanChangeMaster;
import io.vamani.application.model.VcutMainEntryMasterBean;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VcutStylePlanUploadMaster implements Serializable {
    private Long id;

    private LocalDate planDate;

    private String style;

    private String poNo;

    private String colorName;

    private String buyerName;

    private Integer quantity;

    private Double kickOff;

    private Double smv;

    private Integer days;

    private Integer operators;

    private Integer helpers;

    private String itemName;

    private Integer workingHours;

    private String merchantName;

    private String merchant;

    private String lineNo;

    private String factory;

    private String floor;

    private List<VcutMainEntryMasterBean> vcutMainEntryMasterBeans;

    private Integer ftt;

    private Integer alter;

    private Integer reject;

    private Integer rectified;

    private Integer done;

    private String imageFront;

    private String imageBack;

    private Long vcutSessionMasterId;


    private Boolean sizeValidate;

    private Boolean stickerValidate;

    @JsonIgnoreProperties({"createdBy", "createdDate", "lastUpdatedBy", "lastUpdatedDate"})
    private VcutPlanChangeMaster vcutPlanChangeMaster;

    private List<VcutOperationMasterBean> vcutOperationMasters;

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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public VcutPlanChangeMaster getVcutPlanChangeMaster() {
        return vcutPlanChangeMaster;
    }

    public void setVcutPlanChangeMaster(VcutPlanChangeMaster vcutPlanChangeMaster) {
        this.vcutPlanChangeMaster = vcutPlanChangeMaster;
    }

    public List<VcutOperationMasterBean> getVcutOperationMasters() {
        return vcutOperationMasters;
    }

    public void setVcutOperationMasters(List<VcutOperationMasterBean> vcutOperationMasters) {
        this.vcutOperationMasters = vcutOperationMasters;
    }

    public List<VcutMainEntryMasterBean> getVcutMainEntryMasterBeans() {
        return vcutMainEntryMasterBeans;
    }

    public void setVcutMainEntryMasterBeans(List<VcutMainEntryMasterBean> vcutMainEntryMasterBeans) {
        this.vcutMainEntryMasterBeans = vcutMainEntryMasterBeans;
    }

    public Integer getFtt() {
        return ftt;
    }

    public void setFtt(Integer ftt) {
        this.ftt = ftt;
    }

    public Integer getAlter() {
        return alter;
    }

    public void setAlter(Integer alter) {
        this.alter = alter;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }

    public Integer getRectified() {
        return rectified;
    }

    public void setRectified(Integer rectified) {
        this.rectified = rectified;
    }

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageBack() {
        return imageBack;
    }

    public void setImageBack(String imageBack) {
        this.imageBack = imageBack;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Long getVcutSessionMasterId() {
        return vcutSessionMasterId;
    }

    public void setVcutSessionMasterId(Long vcutSessionMasterId) {
        this.vcutSessionMasterId = vcutSessionMasterId;
    }

    public Boolean getSizeValidate() {
        return sizeValidate == null ? false : sizeValidate;
    }

    public void setSizeValidate(Boolean sizeValidate) {
        this.sizeValidate = sizeValidate == null ? false : sizeValidate;
    }

    public Boolean getStickerValidate() {
        return stickerValidate == null ? false : stickerValidate;
    }

    public void setStickerValidate(Boolean stickerValidate) {
        this.stickerValidate = stickerValidate == null ? false : stickerValidate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutStylePlanUploadMaster that = (VcutStylePlanUploadMaster) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(planDate, that.planDate) &&
            Objects.equals(style, that.style) &&
            Objects.equals(poNo, that.poNo) &&
            Objects.equals(colorName, that.colorName) &&
            Objects.equals(buyerName, that.buyerName) &&
            Objects.equals(quantity, that.quantity) &&
            Objects.equals(kickOff, that.kickOff) &&
            Objects.equals(smv, that.smv) &&
            Objects.equals(days, that.days) &&
            Objects.equals(operators, that.operators) &&
            Objects.equals(helpers, that.helpers) &&
            Objects.equals(itemName, that.itemName) &&
            Objects.equals(workingHours, that.workingHours) &&
            Objects.equals(merchantName, that.merchantName) &&
            Objects.equals(merchant, that.merchant) &&
            Objects.equals(lineNo, that.lineNo) &&
            Objects.equals(factory, that.factory) &&
            Objects.equals(floor, that.floor) &&
            Objects.equals(vcutPlanChangeMaster, that.vcutPlanChangeMaster) &&
            Objects.equals(vcutOperationMasters, that.vcutOperationMasters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planDate, style, poNo, colorName, buyerName, quantity, kickOff, smv, days, operators, helpers, itemName, workingHours, merchantName, merchant, lineNo, factory, floor, vcutPlanChangeMaster, vcutOperationMasters);
    }

    @Override
    public String toString() {
        return "VcutStylePlanUploadMaster{" +
            "id=" + id +
            ", planDate=" + planDate +
            ", style='" + style + '\'' +
            ", poNo='" + poNo + '\'' +
            ", colorName='" + colorName + '\'' +
            ", buyerName='" + buyerName + '\'' +
            ", quantity=" + quantity +
            ", kickOff=" + kickOff +
            ", smv=" + smv +
            ", days=" + days +
            ", operators=" + operators +
            ", helpers=" + helpers +
            ", itemName='" + itemName + '\'' +
            ", workingHours=" + workingHours +
            ", merchantName='" + merchantName + '\'' +
            ", merchant='" + merchant + '\'' +
            ", lineNo='" + lineNo + '\'' +
            ", factory='" + factory + '\'' +
            ", floor='" + floor + '\'' +
            ", vcutPlanChangeMaster=" + vcutPlanChangeMaster +
            ", vcutOperationMasters=" + vcutOperationMasters +
            '}';
    }
}
